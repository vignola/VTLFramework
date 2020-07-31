using ArtefactInfo.model;
using Oracle.ManagedDataAccess.Client;
using System;
using System.Collections.Generic;
using System.Data;
using System.Text.RegularExpressions;

namespace VTLInt_WS.VTLDB
{
    public class VTLDBManagerOracle:VTLDBManager
    {
        private string _errorMessage;
        private LogManager _logMan;
       
        public string ErrorMessage
        {
            get
            {
                return _errorMessage;
            }
        }
       public VTLDBManagerOracle(string ConnectionString) : base (ConnectionString)
       {
            _errorMessage = null;
            //this._logMan = new LogManager(AppDomain.CurrentDomain.BaseDirectory + "LogFile.txt");
            this._logMan = new LogManager(@"c:\VTLIntLog\LogFile.txt");
        }       

       public bool TestDatabaseConnection() 
       { 
            IDbConnection conn = null;
            IDbTransaction tran = null;
            IDbCommand comm = null;
            try
            {

                conn = _provider.OpenConnection();                
                IDataReader rdr = null;
                
                string SQLStatement = null;
                SQLStatement = "select count(*) from all_objects where object_type in ('TABLE')";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr == null) return false;
                rdr.Read();
                if (rdr.GetInt32(0) == 0) return false;

                rdr.Close();
                _provider.CloseConnection(conn);
                return true;
            }
            catch (Exception ex)
            {                
                _provider.CloseConnection(conn);
                _errorMessage = ex.Message;
                _logMan.WriteLogMessage(_errorMessage);
                return false;
            }
       }

       #region InsertArtefact

       public string InsertValueDomain(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion, LocalizedValue[] names, BaseArtefactInfo[] valueList, bool check_presence, int creationType, ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE data_type)
        {
            IDbConnection conn = null;
            IDbTransaction tran = null;
            IDbCommand comm = null;
            string valueDomainID = null;
            try
            {                

                conn=_provider.OpenConnection();
                tran = _provider.OpenTransaction(conn);
                IDataReader rdr = null;
                string value_domain_seq_id=null;
                string appo_variable = null;
                string sequentialVariable = null;

                string SQLStatement = null;
                string GlobalID = CommonConstant.ToGlobalID(ValueDomainID, ValueDomainAgency , ValueDomainVersion);

                if (check_presence)
                {
                    SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + GlobalID + "'";
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        value_domain_seq_id = rdr.GetValue(0).ToString();
                    rdr.Close();
                    if (value_domain_seq_id != null) return null;
                }

                SQLStatement = "insert into ARTEFACT (select nvl(max(ARTEFACT_SEQ_ID),0)+1, '" + GlobalID + "', '" + ValueDomainID + "', '" + ValueDomainAgency + "', '" + ValueDomainVersion + "'," + creationType + " from ARTEFACT)";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);                
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                SQLStatement = "insert into VALUE_DOMAIN (select nvl(max(ARTEFACT_SEQ_ID),0), 1, '" + data_type.ToString() + "', null from ARTEFACT)";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);  
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                //Value_Domain ID
                SQLStatement= ("select nvl(max(VALUE_DOMAIN_SEQ_ID),0) from VALUE_DOMAIN");
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    value_domain_seq_id = rdr.GetValue(0).ToString();
                rdr.Close();
                //----------------------------------------------------------------

                valueDomainID = value_domain_seq_id;

                if (names.Length > 0) 
                {
                    List<string> langList = new List<string>();
                    List<string> valueNamesList = new List<string>();

                    foreach (LocalizedValue locValue in names)
                    {
                        langList.Add(locValue.lang);
                        valueNamesList.Add(locValue.value);

                    }
                    string[] lang = langList.ToArray();
                    string[] descr = valueNamesList.ToArray();

                    bool first = true;
                    int l = 0;

                    //Localised string ID
                    SQLStatement=("select nvl(max(LS_SEQ_ID),0)+1 from LOCALISED_STRING");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        appo_variable = rdr.GetValue(0).ToString();
                    rdr.Close();
                    //-----------------------------------------------------------------

                    foreach (string str in lang)
                    {

                        if (first)
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + " , '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', " + value_domain_seq_id + ", null)");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);  
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                            first = false;
                        }
                        else
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + " , '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', " + value_domain_seq_id + ", null)");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);  
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        l++;
                    }
                }

                foreach (BaseComponentInfo compInfo in valueList[0].DataStructureDetails.Components)
                {
                    string code = compInfo.vtlId;

                    List<string> langList = new List<string>();
                    List<string> valueNamesList = new List<string>();

                    foreach (LocalizedValue locValue in compInfo.name)
                    {
                        langList.Add(locValue.lang);
                        valueNamesList.Add(locValue.value);

                    }
                    string[] lang = langList.ToArray();
                    string[] descr = valueNamesList.ToArray();

                    //Sequential_variable
                    SQLStatement = ("select nvl(max(ITEM_SEQ_ID),0)+1 from ITEM");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        sequentialVariable = rdr.GetValue(0).ToString();
                    rdr.Close();
                    //---------------------------------------------------------------


                    SQLStatement = ("insert into ITEM values(" + sequentialVariable + ", '" + code + "')");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);  
                    comm.Transaction = tran;
                    comm.ExecuteNonQuery();
                    SQLStatement = ("insert into VALUE values(" + sequentialVariable + "," + value_domain_seq_id + ")");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);  
                    comm.Transaction = tran;
                    comm.ExecuteNonQuery();

                    int l = 0;
                    bool first = true;

                    //Localised_string
                    SQLStatement = ("select nvl(max(LS_SEQ_ID),0)+1 from LOCALISED_STRING");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        appo_variable = rdr.GetValue(0).ToString();
                    rdr.Close();
                    //-------------------------------------------------------------

                    foreach (string str in lang)
                    {
                        if (first)
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + "  , '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', null , " + sequentialVariable + ")");
                            first = false;
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);  
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        else
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + ", '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', null , " + sequentialVariable + ")");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);  
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        l++;
                    }                   

                }

                tran.Commit();
                _provider.CloseConnection(conn);
                return valueDomainID;
            }
            catch (Exception ex)
            {
                _provider.RollBackTransaction(tran);
                _provider.CloseConnection(conn);
                throw new Exception(ex.Message);
            }
        }

       private string InsertValueDomain(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion, List<LocalizedValue> names, List<BaseArtefactInfo> valueList, bool check_presence, IDbConnection conn, IDbTransaction tran)
        {
            IDbCommand comm = null;
            string valueDomainID = null;
            try
            {                
                IDataReader rdr = null;
                string value_domain_seq_id = null;
                string appo_variable = null;
                string sequentialVariable = null;

                string SQLStatement = null;
                string GlobalID = CommonConstant.ToGlobalID(ValueDomainID , ValueDomainAgency , ValueDomainVersion);

                if (check_presence)
                {
                    SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + GlobalID + "'";
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        value_domain_seq_id = rdr.GetValue(0).ToString();
                    rdr.Close();
                    if (value_domain_seq_id != null) return value_domain_seq_id;
                }

                SQLStatement = "insert into ARTEFACT (select nvl(max(ARTEFACT_SEQ_ID),0)+1, '" + GlobalID + "', '" + ValueDomainID + "', '" + ValueDomainAgency + "', '" + ValueDomainVersion + "',0 from ARTEFACT)";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                SQLStatement = "insert into VALUE_DOMAIN (select nvl(max(ARTEFACT_SEQ_ID),0), 1, '" + ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE.String.ToString() + "', null from ARTEFACT)";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                //Value_Domain ID
                SQLStatement = ("select nvl(max(VALUE_DOMAIN_SEQ_ID),0) from VALUE_DOMAIN");
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    value_domain_seq_id = rdr.GetValue(0).ToString();
                rdr.Close();
                //----------------------------------------------------------------

                valueDomainID = value_domain_seq_id;

                if (names.Count > 0)
                {
                    List<string> langList = new List<string>();
                    List<string> valueNamesList = new List<string>();

                    foreach (LocalizedValue locValue in names)
                    {
                        langList.Add(locValue.lang);
                        valueNamesList.Add(locValue.value);

                    }
                    string[] lang = langList.ToArray();
                    string[] descr = valueNamesList.ToArray();

                    bool first = true;
                    int l = 0;

                    //Localised string ID
                    SQLStatement = ("select nvl(max(LS_SEQ_ID),0)+1 from LOCALISED_STRING");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        appo_variable = rdr.GetValue(0).ToString();
                    rdr.Close();
                    //-----------------------------------------------------------------

                    foreach (string str in lang)
                    {

                        if (first)
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + " , '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', " + value_domain_seq_id + ", null)");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                            first = false;
                        }
                        else
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + " , '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', " + value_domain_seq_id + ", null)");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        l++;
                    }
                }

                foreach (BaseComponentInfo compInfo in valueList[0].DataStructureDetails.Components)
                {
                    string code = compInfo.vtlId;
                    List<string> langList = new List<string>();
                    List<string> valueNamesList = new List<string>();

                    foreach (LocalizedValue locValue in compInfo.name)
                    {
                        langList.Add(locValue.lang);
                        valueNamesList.Add(locValue.value);

                    }
                    string[] lang = langList.ToArray();
                    string[] descr = valueNamesList.ToArray();
                    //Sequential_variable
                    SQLStatement = ("select nvl(max(ITEM_SEQ_ID),0)+1 from ITEM");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        sequentialVariable = rdr.GetValue(0).ToString();
                    rdr.Close();
                    //---------------------------------------------------------------


                    SQLStatement = ("insert into ITEM values(" + sequentialVariable + ", '" + code + "')");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    comm.ExecuteNonQuery();
                    SQLStatement = ("insert into VALUE values(" + sequentialVariable + "," + value_domain_seq_id + ")");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    comm.ExecuteNonQuery();

                    int l = 0;
                    bool first = true;

                    //Localised_string
                    SQLStatement = ("select nvl(max(LS_SEQ_ID),0)+1 from LOCALISED_STRING");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        appo_variable = rdr.GetValue(0).ToString();
                    rdr.Close();
                    //-------------------------------------------------------------

                    foreach (string str in lang)
                    {
                        if (first)
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + "  , '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', null , " + sequentialVariable + ")");
                            first = false;
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        else
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + ", '" + str + "', '" + descr[l].Replace("'","''") + "', 'Description', null , " + sequentialVariable + ")");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        l++;
                    }

                }
               
                return valueDomainID;
            }
            catch (Exception ex)
            {
                _provider.RollBackTransaction(tran);
                _provider.CloseConnection(conn);
                throw new Exception(ex.Message);
            }
        }

       public string InsertValueDomainDescribed(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion, LocalizedValue[] names, string valueRestriction, bool check_presence, int creationType, ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE data_type)
        {
            IDbConnection conn = null;
            IDbTransaction tran = null;
            IDbCommand comm = null;
            string valueDomainID = null;
            try
            {

                conn = _provider.OpenConnection();
                tran = _provider.OpenTransaction(conn);
                IDataReader rdr = null;
                string value_domain_seq_id = null;
                string appo_variable = null;
                string sequentialVariable = null;

                string SQLStatement = null;
                string GlobalID = CommonConstant.ToGlobalID(ValueDomainID, ValueDomainAgency, ValueDomainVersion);

                if (check_presence)
                {
                    SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + GlobalID + "'";
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        value_domain_seq_id = rdr.GetValue(0).ToString();
                    rdr.Close();
                    if (value_domain_seq_id != null) return null;
                }

                SQLStatement = "insert into ARTEFACT (select nvl(max(ARTEFACT_SEQ_ID),0)+1, '" + GlobalID + "', '" + ValueDomainID + "', '" + ValueDomainAgency + "', '" + ValueDomainVersion + "'," + creationType + " from ARTEFACT)";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                SQLStatement = "insert into VALUE_DOMAIN (select nvl(max(ARTEFACT_SEQ_ID),0), 0, '" + data_type.ToString() + "', '" + valueRestriction + "' from ARTEFACT)";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                //Value_Domain ID
                SQLStatement = ("select nvl(max(VALUE_DOMAIN_SEQ_ID),0) from VALUE_DOMAIN");
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    value_domain_seq_id = rdr.GetValue(0).ToString();
                rdr.Close();
                //----------------------------------------------------------------

                valueDomainID = value_domain_seq_id;

                if (names.Length > 0)
                {
                    List<string> langList = new List<string>();
                    List<string> valueNamesList = new List<string>();

                    foreach (LocalizedValue locValue in names)
                    {
                        langList.Add(locValue.lang);
                        valueNamesList.Add(locValue.value);

                    }
                    string[] lang = langList.ToArray();
                    string[] descr = valueNamesList.ToArray();

                    bool first = true;
                    int l = 0;

                    //Localised string ID
                    SQLStatement = ("select nvl(max(LS_SEQ_ID),0)+1 from LOCALISED_STRING");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        appo_variable = rdr.GetValue(0).ToString();
                    rdr.Close();
                    //-----------------------------------------------------------------

                    foreach (string str in lang)
                    {

                        if (first)
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + " , '" + str + "', '" + descr[l] + "', 'Description', " + value_domain_seq_id + ", null)");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                            first = false;
                        }
                        else
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + " , '" + str + "', '" + descr[l] + "', 'Description', " + value_domain_seq_id + ", null)");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        l++;
                    }
                }
                
                tran.Commit();
                _provider.CloseConnection(conn);
                return valueDomainID;
            }
            catch (Exception ex)
            {
                _provider.RollBackTransaction(tran);
                _provider.CloseConnection(conn);
                throw new Exception(ex.Message);
            }
        }

       public bool InsertValueDomainSubset(string ValueDomainSubsetID, string ValueDomainSubsetAgency, string ValueDomainSubsetVersion, string ref_ValueDomainID, string ref_ValueDomainAgency, string ref_ValueDomainVersion, LocalizedValue[] names, int[] valueList, int creationType)        
       {
           IDbConnection conn = null;
           IDbTransaction tran = null;
           IDbCommand comm = null;

           try
           {

               conn = _provider.OpenConnection();
               tran = _provider.OpenTransaction(conn);
               IDataReader rdr = null;
               string value_domain_seq_id = null;
               string value_domain_subset_seq_id = null;
               string SQLStatement = null;
               string appo_variable = null;
               string GlobalID = CommonConstant.ToGlobalID(ValueDomainSubsetID, ValueDomainSubsetAgency, ValueDomainSubsetVersion);

               //check_presence

               SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + GlobalID + "'";
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
               if (rdr.Read())
                   value_domain_seq_id = rdr.GetValue(0).ToString();
               rdr.Close();
               if (value_domain_seq_id != null) return false;

               SQLStatement = "select nvl(max(ARTEFACT_SEQ_ID),0)+1 from ARTEFACT";
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
               if (rdr.Read())
                   value_domain_subset_seq_id = rdr.GetValue(0).ToString();
               rdr.Close();


               SQLStatement = "insert into ARTEFACT values(" + value_domain_subset_seq_id + ",'" + GlobalID + "', '" + ValueDomainSubsetID + "', '" + ValueDomainSubsetAgency + "', '" + ValueDomainSubsetVersion + "'," + creationType + ")";
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               string ref_ValueDomainGlobalID = CommonConstant.ToGlobalID(ref_ValueDomainID, ref_ValueDomainAgency, ref_ValueDomainVersion);

               SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + ref_ValueDomainGlobalID + "'";
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
               if (rdr.Read())
                   value_domain_seq_id = rdr.GetValue(0).ToString();
               rdr.Close();
               if (value_domain_seq_id == null) throw new Exception("Value domain cannot be found");

               SQLStatement = "insert into VALUE_DOMAIN_SUBSET values(" + value_domain_subset_seq_id + "," + value_domain_seq_id + ",1, '')";
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               foreach (int val in valueList) 
               {
                   SQLStatement = "insert into SUBSET_LIST values(" + value_domain_subset_seq_id + "," + val +")";
                   comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                   comm.Transaction = tran;
                   comm.ExecuteNonQuery();
               }

               if (names.Length > 0)
               {
                   List<string> langList = new List<string>();
                   List<string> valueNamesList = new List<string>();

                   foreach (LocalizedValue locValue in names)
                   {
                       langList.Add(locValue.lang);
                       valueNamesList.Add(locValue.value);

                   }
                   string[] lang = langList.ToArray();
                   string[] descr = valueNamesList.ToArray();

                   bool first = true;
                   int l = 0;

                   //Localised string ID
                   SQLStatement = ("select nvl(max(LS_SEQ_ID),0)+1 from LOCALISED_STRING");
                   comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                   comm.Transaction = tran;
                   rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                   if (rdr.Read())
                       appo_variable = rdr.GetValue(0).ToString();
                   rdr.Close();
                   //-----------------------------------------------------------------

                   foreach (string str in lang)
                   {

                       if (first)
                       {
                           SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + " , '" + str + "', '" + descr[l] + "', 'Description', " + value_domain_subset_seq_id + ", null)");
                           comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                           comm.Transaction = tran;
                           comm.ExecuteNonQuery();
                           first = false;
                       }
                       else
                       {
                           SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + " , '" + str + "', '" + descr[l] + "', 'Description', " + value_domain_subset_seq_id + ", null)");
                           comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                           comm.Transaction = tran;
                           comm.ExecuteNonQuery();
                       }
                       l++;
                   }
               }

               tran.Commit();
               _provider.CloseConnection(conn);
               return true;
           }
           catch (Exception ex)
           {
               _provider.RollBackTransaction(tran);
               _provider.CloseConnection(conn);
               throw new Exception(ex.Message);
           }
       }

       public bool InsertValueDomainSubsetDescribed(string ValueDomainSubsetID, string ValueDomainSubsetAgency, string ValueDomainSubsetVersion, string ref_ValueDomainID, string ref_ValueDomainAgency, string ref_ValueDomainVersion, LocalizedValue[] names, string valueRestriction, int creationType)
       { 
            IDbConnection conn = null;
            IDbTransaction tran = null;
            IDbCommand comm = null;
            
            try
            {

                conn = _provider.OpenConnection();
                tran = _provider.OpenTransaction(conn);
                IDataReader rdr = null;
                string value_domain_seq_id = null;                
                string value_domain_subset_seq_id=null;
                string SQLStatement = null;
                string appo_variable = null;
                string GlobalID = CommonConstant.ToGlobalID(ValueDomainSubsetID, ValueDomainSubsetAgency, ValueDomainSubsetVersion);

                //check_presence
                
                SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + GlobalID + "'";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    value_domain_seq_id = rdr.GetValue(0).ToString();
                rdr.Close();
                if (value_domain_seq_id != null) return false;

                SQLStatement = "select nvl(max(ARTEFACT_SEQ_ID),0)+1 from ARTEFACT";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    value_domain_subset_seq_id = rdr.GetValue(0).ToString();
                rdr.Close();
                
               
                SQLStatement = "insert into ARTEFACT values(" + value_domain_subset_seq_id + ",'" + GlobalID + "', '" + ValueDomainSubsetID + "', '" + ValueDomainSubsetAgency + "', '" + ValueDomainSubsetVersion + "'," + creationType + ")";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                string ref_ValueDomainGlobalID = CommonConstant.ToGlobalID(ref_ValueDomainID, ref_ValueDomainAgency, ref_ValueDomainVersion);

                SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + ref_ValueDomainGlobalID + "'";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    value_domain_seq_id = rdr.GetValue(0).ToString();
                rdr.Close();
                if (value_domain_seq_id == null) throw new Exception("Value domain cannot be found");

                SQLStatement = "insert into VALUE_DOMAIN_SUBSET values(" + value_domain_subset_seq_id + "," + value_domain_seq_id + ",0, '" + valueRestriction + "')";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                if (names.Length > 0)
                {
                    List<string> langList = new List<string>();
                    List<string> valueNamesList = new List<string>();

                    foreach (LocalizedValue locValue in names)
                    {
                        langList.Add(locValue.lang);
                        valueNamesList.Add(locValue.value);

                    }
                    string[] lang = langList.ToArray();
                    string[] descr = valueNamesList.ToArray();

                    bool first = true;
                    int l = 0;

                    //Localised string ID
                    SQLStatement = ("select nvl(max(LS_SEQ_ID),0)+1 from LOCALISED_STRING");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        appo_variable = rdr.GetValue(0).ToString();
                    rdr.Close();
                    //-----------------------------------------------------------------

                    foreach (string str in lang)
                    {

                        if (first)
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + " , '" + str + "', '" + descr[l] + "', 'Description', " + value_domain_subset_seq_id + ", null)");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                            first = false;
                        }
                        else
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + " , '" + str + "', '" + descr[l] + "', 'Description', " + value_domain_subset_seq_id + ", null)");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        l++;
                    }
                }

                tran.Commit();
                _provider.CloseConnection(conn);
                return true;
            }
            catch (Exception ex)
            {
                _provider.RollBackTransaction(tran);
                _provider.CloseConnection(conn);
                throw new Exception(ex.Message);
            }
       }

       public string InsertDataStructure(string dsd_ID, string dsd_Agency, string dsd_Version, LocalizedValue[] dsd_names, BaseComponentInfo[] dsd_componentList, int creationType)
        {
            IDbConnection conn = null;
            IDbTransaction tran = null;
            IDbCommand comm = null;
            try
            {

                conn = _provider.OpenConnection();
                tran = _provider.OpenTransaction(conn);
                IDataReader rdr = null;
                string item_seq_variable = null;
                string appo_variable = null;
                string dsd_seq_id = null;
                string value_domain_seq_id = null;
                string item_comp_seq_id = null;

                string SQLStatement = null;

                string GlobalID =CommonConstant.ToGlobalID( dsd_ID, dsd_Agency, dsd_Version);

                //Check presence-------------------------------------------------------------------------------
                
                SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + GlobalID + "'";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    dsd_seq_id = rdr.GetValue(0).ToString();
                rdr.Close();
                if (dsd_seq_id != null) return null;
                
                //----------------------------------------------------------------------------------------------

                SQLStatement=("select nvl(max(ARTEFACT_SEQ_ID),0)+1 from ARTEFACT");
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    dsd_seq_id = rdr.GetValue(0).ToString();
                rdr.Close();

                SQLStatement = ("insert into ARTEFACT values(" + dsd_seq_id + ", '" + GlobalID + "', '" + dsd_ID + "', '" + dsd_Agency + "', '" + dsd_Version + "'," + creationType + ")");
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                SQLStatement = ("insert into DATA_STRUCTURE values (" + dsd_seq_id + " )");
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();


                if (dsd_names.Length > 0)
                {
                    List<string> langList = new List<string>();
                    List<string> valueList = new List<string>();
                    foreach (LocalizedValue locValue in dsd_names)
                    {
                        langList.Add(locValue.lang);
                        valueList.Add(locValue.value);

                    }
                    string[] lang = langList.ToArray();
                    string[] descr = valueList.ToArray();
                    bool first = true;
                    int l = 0;

                    SQLStatement = ("select nvl(max(LS_SEQ_ID),0)+1 from LOCALISED_STRING");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        appo_variable = rdr.GetValue(0).ToString();
                    rdr.Close();

                    foreach (string str in lang)
                    {
                        if (first)
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + ", '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', " + dsd_seq_id + ", null)");
                            first = false;
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        else
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + ", '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', " + dsd_seq_id + ", null)");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        l++;
                    }
                }
                //--------------------------------------------------------------------------------------------------                


                foreach (BaseComponentInfo bs in dsd_componentList)
                {                    

                    if (bs.ValueDomainInfo.vd_id != null)
                    {
                        value_domain_seq_id = InsertValueDomain(bs.ValueDomainInfo.vd_id, bs.ValueDomainInfo.vd_agency, bs.ValueDomainInfo.vd_version, bs.ValueDomainInfo.names, bs.ValueDomainInfo.values, true, conn, tran);

                        //SQLStatement.AddRange(VTLSqlStatement.getDataStructureComponentSQLStatement(bs.names, bs.role, bs.vd_id, "datastructure_seq_id", "main_value_domain_seq_id", "item_seq_id"));

                        SQLStatement = ("select nvl(max(ITEM_SEQ_ID),0)+1 from ITEM");
                        //SQLStatement = ("select nvl(max(COMPONENT_SEQ_ID),0)+1 from COMPONENT");
                        comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                        comm.Transaction = tran;
                        rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                        if (rdr.Read())
                            item_comp_seq_id = rdr.GetValue(0).ToString();
                        rdr.Close();

                        SQLStatement = ("insert into ITEM values (" + item_comp_seq_id + ",'" + bs.vtlId + "')");
                        comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                        comm.Transaction = tran;
                        comm.ExecuteNonQuery();

                        SQLStatement = ("insert into COMPONENT values(" + item_comp_seq_id + " , " + dsd_seq_id + ", " + value_domain_seq_id + ", '" + bs.Role + "')");
                        comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                        comm.Transaction = tran;
                        comm.ExecuteNonQuery();

                        List<LocalizedValue> listName=new List<LocalizedValue>();

                        if (bs.name!=null)
                            listName = bs.name.Count > 0 ? bs.name : bs.ValueDomainInfo.names;

                        else
                             if (bs.ValueDomainInfo.names!=null)
                                if (bs.ValueDomainInfo.names.Count > 0) listName = bs.ValueDomainInfo.names;

                        if (listName.Count == 0) 
                        {
                            listName.Add(new LocalizedValue("EN", "Empty field"));
                        }

                        
                        List<string> langList = new List<string>();
                        List<string> valueList = new List<string>();

                        foreach (LocalizedValue locValue in listName)
                        {
                            langList.Add(locValue.lang);
                            valueList.Add(locValue.value);

                        }
                        string[] lang = langList.ToArray();
                        string[] descr = valueList.ToArray();
                        bool first = true;
                        int l = 0;                            

                        foreach (string str in lang)
                        {
                            SQLStatement = ("select nvl(max(LS_SEQ_ID),0)+1 from LOCALISED_STRING");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                            if (rdr.Read())
                                appo_variable = rdr.GetValue(0).ToString();
                            rdr.Close();

                            if (first)
                            {
                                SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + ", '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', null," + item_comp_seq_id + ")");
                                first = false;
                                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                                comm.Transaction = tran;
                                comm.ExecuteNonQuery();
                            }
                            else
                            {
                                SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + ", '" + str + "', '" + descr[l].Replace("'", "''") + "', 'Description', null, " + item_comp_seq_id + ")");
                                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                                comm.Transaction = tran;
                                comm.ExecuteNonQuery();
                            }
                            l++;
                        }                        
                    }
                    //-----------------------------------------------------------------------------------------------
                }                                

                tran.Commit();
                _provider.CloseConnection(conn);
                return dsd_seq_id;
            }
            catch (Exception ex)
            {
                _provider.RollBackTransaction(tran);
                _provider.CloseConnection(conn);
                throw new Exception(ex.Message);
            }
        }

       public string InsertDataSet(string ref_dsd_ID, string ref_dsd_Agency, string ref_dsd_Version, string dataset_id, string dataset_agency, string dataset_version, LocalizedValue[] dataset_names, BaseComponentInfo[] dsd_componentList, int creationType)
        {
            IDbConnection conn = null;
            IDbTransaction tran = null;
            IDbCommand comm = null;
            try
            {

                conn = _provider.OpenConnection();
                tran = _provider.OpenTransaction(conn);
                IDataReader rdr = null;
                IDataReader rdr_ds = null;
                string item_seq_variable = null;
                string appo_variable = null;
                string sequentialVariable = null;
                string value_domain_seq_id = null;
                string ds_seq_id = null;

                string SQLStatement = null;

                string GlobalID = CommonConstant.ToGlobalID(dataset_id, dataset_agency,dataset_version);

                //Check presence-------------------------------------------------------------------------------

                SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + GlobalID + "'";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    ds_seq_id = rdr.GetValue(0).ToString();
                rdr.Close();
                if (ds_seq_id != null) return null;

                //----------------------------------------------------------------------------------------------

                SQLStatement = ("select nvl(max(ARTEFACT_SEQ_ID),0)+1 from ARTEFACT");
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    sequentialVariable = rdr.GetValue(0).ToString();
                rdr.Close();

                SQLStatement = ("insert into ARTEFACT values(" + sequentialVariable + ", '" + GlobalID + "', '" + dataset_id + "', '" + dataset_agency + "', '" + dataset_version + "'," + creationType + ")");
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                string dataStructure_seq = null;
                SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where SDMX_ID='" + ref_dsd_ID + "' and SDMX_AGENCY='" + ref_dsd_Agency + "' and SDMX_VERSION='" + ref_dsd_Version + "'";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr_ds = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr_ds.Read())
                    dataStructure_seq = rdr_ds.GetValue(0).ToString();
                else
                    throw new Exception("Referenced data Structure not found!["+ ref_dsd_ID + " " + ref_dsd_Agency + " " + ref_dsd_Version + "]");

                rdr_ds.Close();

                SQLStatement = ("insert into DATASET values (" + sequentialVariable + "," +  dataStructure_seq + ",1 )");
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();


                if (dataset_names.Length > 0)
                {
                    List<string> langList=new List<string>();
                    List<string> valueList=new List<string>();
                    foreach (LocalizedValue locValue in dataset_names)
                    {
                        langList.Add(locValue.lang);
                        valueList.Add(locValue.value);

                    }
                    string[] lang= langList.ToArray();
                    string[] descr = valueList.ToArray();

                    bool first = true;
                    int l = 0;

                    SQLStatement = ("select nvl(max(LS_SEQ_ID),0)+1 from LOCALISED_STRING");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        appo_variable = rdr.GetValue(0).ToString();
                    rdr.Close();

                    foreach (string str in lang)
                    {
                        if (first)
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + ", '" + str + "', '" + descr[l] + "', 'Description', " + sequentialVariable + ", null)");
                            first = false;
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        else
                        {
                            SQLStatement = ("insert into LOCALISED_STRING values(" + appo_variable + ", '" + str + "', '" + descr[l] + "', 'Description', " + sequentialVariable + ", null)");
                            comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                            comm.Transaction = tran;
                            comm.ExecuteNonQuery();
                        }
                        l++;
                    }
                }
                //--------------------------------------------------------------------------------------------------                


                foreach (BaseComponentInfo bs in dsd_componentList)
                {
                    value_domain_seq_id = InsertValueDomain(bs.ValueDomainInfo.vd_id, bs.ValueDomainInfo.vd_agency, bs.ValueDomainInfo.vd_version, bs.ValueDomainInfo.names, bs.ValueDomainInfo.values, true, conn, tran);

                    //SQLStatement.AddRange(VTLSqlStatement.getDataStructureComponentSQLStatement(bs.names, bs.role, bs.vd_id, "datastructure_seq_id", "main_value_domain_seq_id", "item_seq_id"));

                    //SQLStatement = ("select nvl(max(ITEM_SEQ_ID),0)+1 from ITEM");

                    string dsd_id = CommonConstant.ToGlobalID(ref_dsd_ID ,ref_dsd_Agency ,ref_dsd_Version);
                    string val_id = CommonConstant.ToGlobalID(bs.ValueDomainInfo.vd_id, bs.ValueDomainInfo.vd_agency, bs.ValueDomainInfo.vd_version);

                    SQLStatement = "select COMPONENT.COMPONENT_SEQ_ID from ARTEFACT, COMPONENT where ARTEFACT_ID='" + val_id + "' AND COMPONENT.VALUE_DOMAIN_SEQ_ID=ARTEFACT.ARTEFACT_SEQ_ID" +
                                    " and COMPONENT.DATA_STRUCTURE_SEQ_ID = (select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID = '" + dsd_id + "'" +
                                     " AND ARTEFACT.ARTEFACT_SEQ_ID  in (select DATA_STRUCTURE_SEQ_ID from DATA_STRUCTURE))";
                    
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        item_seq_variable = rdr.GetValue(0).ToString();
                    rdr.Close();

                    SQLStatement = ("insert into COMPONENT_DATASET values(" + item_seq_variable + " , " + sequentialVariable + ", " + value_domain_seq_id + ", 0)");
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    comm.ExecuteNonQuery();
                   
                }

                tran.Commit();
                _provider.CloseConnection(conn);
                return ds_seq_id;
            }
            catch (Exception ex)
            {
                _provider.RollBackTransaction(tran);
                _provider.CloseConnection(conn);
                throw new Exception(ex.Message);
            }
        }
     
       public void InsertTransformation(string transformationID, string agency, string version, string transformationText, string clearedAndOrderedStatements)
        {
            IDbConnection conn = null;
            IDbTransaction tran = null;
            IDbCommand comm = null;

            try
            {

                string[] splittedStatements = Regex.Split(clearedAndOrderedStatements, @"(?<=[;])");

                conn = _provider.OpenConnection();
                tran = _provider.OpenTransaction(conn);
                IDataReader rdr = null;                
                string artefact_seq_variable = null;
                string item_seq_id = null;                
                string SQLStatement = null;
                

                SQLStatement = "select nvl(max(ARTEFACT_SEQ_ID),0)+1 from ARTEFACT";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    artefact_seq_variable = rdr.GetValue(0).ToString();
                rdr.Close();

                string globalId = CommonConstant.ToGlobalID(transformationID, agency , version);
                SQLStatement = "insert into ARTEFACT values(" + artefact_seq_variable + ", '" + globalId + "', '" + transformationID + "', '" + agency + "', '" + version + "',0)";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                SQLStatement = "insert into TRANSFORMATION_SCHEME_TEXT values('" + artefact_seq_variable + "', '" + transformationText.Replace("'","''") + "')";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                foreach (string stat in splittedStatements) 
                {
                    string statCleared = stat.Replace("\n", "").Replace("'","''").Trim();
                    if (!string.IsNullOrEmpty(statCleared))
                    {
                        SQLStatement = "select nvl(max(ITEM_SEQ_ID),0)+1 from ITEM";
                        comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                        comm.Transaction = tran;
                        rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                        if (rdr.Read())
                            item_seq_id = rdr.GetValue(0).ToString();
                        rdr.Close();

                        SQLStatement = "insert into ITEM values ('" + item_seq_id + "', '" + statCleared + "')";
                        comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                        comm.Transaction = tran;
                        comm.ExecuteNonQuery();

                        SQLStatement = "insert into TRANSFORMATION values('" + item_seq_id + "', 'DCIT_ITB')";
                        comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                        comm.Transaction = tran;
                        comm.ExecuteNonQuery();

                        SQLStatement = "insert into TRANSFORMATION_SCHEME values('" + artefact_seq_variable + "', '" + item_seq_id + "')";
                        comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                        comm.Transaction = tran;
                        comm.ExecuteNonQuery();
                    }
                }
                             

                tran.Commit();
                _provider.CloseConnection(conn);
            }
            catch (Exception ex)
            {
                _provider.RollBackTransaction(tran);
                _provider.CloseConnection(conn);
                throw new Exception(ex.Message);
            }
        }

       public bool InsertUserDefinedOperator(string Operator_ID, string body, int operator_type) 
       { 
            IDbConnection conn = null;
            IDbTransaction tran = null;
            IDbCommand comm = null;            

            try
            {                

                conn = _provider.OpenConnection();
                tran = _provider.OpenTransaction(conn);
                IDataReader rdr = null;                
                string artefact_seq_variable = null;                        
                string SQLStatement = null;

                SQLStatement = "select operator_id from USER_DEFINE_OPERATORS where operator_id='" + Operator_ID + "'";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                {
                    rdr.Close();
                    return false;
                }
                

                SQLStatement = "select nvl(max(OPERATOR_SEQ_ID),0)+1 from USER_DEFINE_OPERATORS";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                if (rdr.Read())
                    artefact_seq_variable = rdr.GetValue(0).ToString();
                rdr.Close();

                SQLStatement = "insert into USER_DEFINE_OPERATORS(operator_seq_id, operator_id, description, operator_type, group_id, operator_body) values(" + artefact_seq_variable + ", '" + Operator_ID + "', '', " + operator_type + ", '','" + body + "')";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                comm.Transaction = tran;
                comm.ExecuteNonQuery();

                tran.Commit();
                _provider.CloseConnection(conn);
                return true;
            }
            catch (Exception ex)
            {
                _provider.RollBackTransaction(tran);
                _provider.CloseConnection(conn);
                throw new Exception(ex.Message);
            }
       }

       #endregion

       #region RemoveArtefact

       public void RemoveDataStructure(string dsd_DI, string dsd_Agency, string dsd_Version)
       {
           IDbConnection conn = null;
           IDbTransaction tran = null;
           IDbCommand comm = null;
           try
           {

               conn = _provider.OpenConnection();
               tran = _provider.OpenTransaction(conn);




               tran.Commit();
               _provider.CloseConnection(conn);
           }
           catch (Exception ex)
           {
               _provider.RollBackTransaction(tran);
               _provider.CloseConnection(conn);
               throw new Exception(ex.Message);
           }
       }

       public void RemoveDataSet(string dataset_ID, string dataset_Agency, string dataset_Version)
       {
           IDbConnection conn = null;
           IDbTransaction tran = null;
           IDbCommand comm = null;
           try
           {

               conn = _provider.OpenConnection();
               tran = _provider.OpenTransaction(conn);
               IDataReader rdr = null;
               int artefact_seq_variable = 0;
               string SQLStatement = null;
               string globalID = CommonConstant.ToGlobalID(dataset_ID, dataset_Agency, dataset_Version);

               SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + globalID + "'";
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
               if (rdr.Read())
                   artefact_seq_variable = rdr.GetInt32(0);
               rdr.Close();

               SQLStatement = "delete from COMPONENT_DATASET where DATASET_SEQ_ID=" + artefact_seq_variable;
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               SQLStatement = "delete from LOCALISED_STRING where ARTEFACT_SEQ_ID=" + artefact_seq_variable;
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();


               SQLStatement = "delete from DATASET where DATASET_SEQ_ID=" + artefact_seq_variable;
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               SQLStatement = "delete from ARTEFACT where ARTEFACT_SEQ_ID=" + artefact_seq_variable;
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               tran.Commit();
               _provider.CloseConnection(conn);
           }
           catch (Exception ex)
           {
               _provider.RollBackTransaction(tran);
               _provider.CloseConnection(conn);
               throw new Exception(ex.Message);
           }
       }

       public void RemoveValueDomain(string dataset_ID, string dataset_Agency, string dataset_Version)
       {
           IDbConnection conn = null;
           IDbTransaction tran = null;
           IDbCommand comm = null;
           string SQLStatement = null;
           int value_variable = 0;

           try
           {

               conn = _provider.OpenConnection();
               tran = _provider.OpenTransaction(conn);

               IDataReader rdr = null;
               int appo_variable = 0;

               SQLStatement = ("select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + CommonConstant.ToGlobalID(dataset_ID, dataset_Agency, dataset_Version) + "'");
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
               if (rdr.Read())
                   appo_variable = rdr.GetInt32(0);
               rdr.Close();


               SQLStatement = "delete from LOCALISED_STRING string where ITEM_SEQ_ID in (select ITEM_SEQ_ID from ITEM, VALUE where ITEM.ITEM_SEQ_ID=VALUE.VALUE_SEQ_ID and VALUE.VALUE_DOMAIN_SEQ_ID=" + appo_variable.ToString() + ")";
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();


               SQLStatement = ("select VALUE_SEQ_ID from VALUE where VALUE_DOMAIN_SEQ_ID=" + appo_variable.ToString());
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
               while (rdr.Read())
               {
                   value_variable = rdr.GetInt32(0);
                   SQLStatement = "delete from VALUE where VALUE_DOMAIN_SEQ_ID=" + appo_variable.ToString();
                   comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                   comm.Transaction = tran;
                   comm.ExecuteNonQuery();

                   SQLStatement = "delete from ITEM where ITEM_SEQ_ID =" + value_variable.ToString();
                   comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                   comm.Transaction = tran;
                   comm.ExecuteNonQuery();

               }
               rdr.Close();

               SQLStatement = "delete from VALUE_DOMAIN where VALUE_DOMAIN_SEQ_ID=" + appo_variable.ToString();
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               SQLStatement = "delete from LOCALISED_STRING string where ARTEFACT_SEQ_ID=" + appo_variable.ToString();
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               SQLStatement = "delete from ARTEFACT where ARTEFACT_SEQ_ID=" + appo_variable.ToString();
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               tran.Commit();
               _provider.CloseConnection(conn);
           }
           catch (Exception ex)
           {
               _provider.RollBackTransaction(tran);
               _provider.CloseConnection(conn);
               throw new Exception(ex.Message);
           }
       }

       public void RemoveValueDomainSubset(string ValueDomainSubsetID, string ValueDomainSubsetAgency, string ValueDomainSubsetVersion) 
       { 
                      IDbConnection conn = null;
           IDbTransaction tran = null;
           IDbCommand comm = null;
           string SQLStatement = null;           

           try
           {

               conn = _provider.OpenConnection();
               tran = _provider.OpenTransaction(conn);

               IDataReader rdr = null;
               int appo_variable = 0;

               SQLStatement = ("select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + CommonConstant.ToGlobalID(ValueDomainSubsetID, ValueDomainSubsetAgency, ValueDomainSubsetVersion) + "'");
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
               if (rdr.Read())
                   appo_variable = rdr.GetInt32(0);
               rdr.Close();

               SQLStatement = "delete from LOCALISED_STRING string where ITEM_SEQ_ID in (select ITEM_SEQ_ID from ITEM, VALUE where ITEM.ITEM_SEQ_ID=VALUE.VALUE_SEQ_ID and VALUE.VALUE_DOMAIN_SEQ_ID=" + appo_variable.ToString() + ")";
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               SQLStatement = "delete from SUBSET_LIST where SUBSET_SEQ_ID=" + appo_variable;
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               SQLStatement = "delete from VALUE_DOMAIN_SUBSET where SUBSET_VALUE_SEQ_ID=" + appo_variable;
               comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
               comm.Transaction = tran;
               comm.ExecuteNonQuery();

               tran.Commit();
               _provider.CloseConnection(conn);
           }
           catch (Exception ex)
           {
               _provider.RollBackTransaction(tran);
               _provider.CloseConnection(conn);
               throw new Exception(ex.Message);
           }
       }

       public void RemoveTransformation(string transformationScheme_ID) 
            {
                IDbConnection conn = null;
                IDbTransaction tran = null;
                IDbCommand comm = null;
                
                try
                {
                    string SQLStatement=null;                    
                    conn = _provider.OpenConnection();
                    tran = _provider.OpenTransaction(conn);
                    IDataReader rdr = null;
                    string transformationscheme_seq_id=null;

                    List<int> transformationIDs = new List<int>();

                    SQLStatement = "select ARTEFACT_SEQ_ID from ARTEFACT where ARTEFACT_ID='" + transformationScheme_ID + "'";
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    if (rdr.Read())
                        transformationscheme_seq_id = rdr.GetValue(0).ToString();
                    rdr.Close();


                    SQLStatement = "delete from TRANSFORMATION_SCHEME_TEXT where TRANSFORMATION_SCHEME_SEQ_ID=" + transformationscheme_seq_id;
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    comm.ExecuteNonQuery();

                    SQLStatement = "select TRANSFORMATION_SEQ_ID from TRANSFORMATION_SCHEME where TRANSFORMATION_SCHEME_SEQ_ID=" + transformationscheme_seq_id;
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    rdr = comm.ExecuteReader(System.Data.CommandBehavior.SingleRow);
                    while (rdr.Read())
                        transformationIDs.Add(rdr.GetInt32(0));
                    rdr.Close();

                    foreach (int trandID in transformationIDs) 
                    {
                        SQLStatement = "delete from TRANSFORMATION where TRANSFORMATION_SEQ_ID=" + trandID;
                        comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                        comm.Transaction = tran;
                        comm.ExecuteNonQuery();

                        SQLStatement = "delete from ITEM where ITEM_SEQ_ID=" + trandID;
                        comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                        comm.Transaction = tran;
                        comm.ExecuteNonQuery();
                    }                    

                    SQLStatement = "delete from TRANSFORMATION_SCHEME where TRANSFORMATION_SCHEME_SEQ_ID=" + transformationscheme_seq_id;
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    comm.ExecuteNonQuery();

                    SQLStatement = "delete from ARTEFACT where ARTEFACT_SEQ_ID=" + transformationscheme_seq_id;
                    comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                    comm.Transaction = tran;
                    comm.ExecuteNonQuery();

                    tran.Commit();
                    _provider.CloseConnection(conn);
                }
                catch (Exception ex)
                {
                    _provider.RollBackTransaction(tran);
                    _provider.CloseConnection(conn);
                    throw new Exception(ex.Message);
                }
            }

       public void RemoveUserDefinedOperator(string operatorName) 
       { 
            IDbConnection conn = null;                
            IDbCommand comm = null;
                
            try
            {
                string SQLStatement=null;                    
                conn = _provider.OpenConnection();

                SQLStatement = "delete from USER_DEFINE_OPERATORS where OPERATOR_ID='" + operatorName + "'";
                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);                
                comm.ExecuteNonQuery();
                
                _provider.CloseConnection(conn);
            }
            catch (Exception ex)
            {                
                _provider.CloseConnection(conn);
                throw new Exception(ex.Message);
            }
       }

       #endregion

    }    

}

