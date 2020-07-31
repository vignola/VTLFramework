using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ArtefactInfo.model;

namespace VTLInt_WS.VTLDB
{
    public class VTLSqlStatement
    {
        class IDA_TYPE
        {
            public const string DataStructure = "DS";
            public const string DataSet = "DSet";
            public const string Component = "CMP";
            public const string ValueDomain = "VD";
            public const string ValueDomainSubset = "VDS";
        }              

        public static List<string> seq_id_declare_begin(List<string[]> variables)
        {
            try
            {
                List<string> statements = new List<string>();
                statements.Add("DECLARE");
                statements.Add("appo_variable NUMBER;");
                foreach (string[] couple in variables)
                {
                    statements.Add(couple[0] + " " + couple[1] + ";");
                }
                statements.Add("BEGIN");
                return statements;
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        public static List<string> seq_id_storeProcedure_begin(List<string[]> variables)
        {
            try
            {
                List<string> statements = new List<string>();
                statements.Add("CREATE PROCEDURE insertDatastrure()");
                statements.Add("IS");
                statements.Add("DEFINE");
                statements.Add("appo_variable NUMBER;");
                foreach (string[] couple in variables)
                {
                    statements.Add(couple[0] + " " + couple[1] + ";");
                }
                statements.Add("BEGIN");
                return statements;
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        public const string closeProcedure = "END;";
        public const string closeProcedureInsert = "END insertDatastrure;";

     #region DATASTRUCTURE

        public const string DataStructure = "SELECT SDMX_ID || '__' || SDMX_AGENCY || '__' || SDMX_VERSION   AS ARTEFACT_ID, SDMX_ID, SDMX_AGENCY, SDMX_VERSION, CREATION_TYPE, NAME, LANGUAGE " +
                                            "FROM WW_DATA_STRUCTURE " +
                                            "ORDER BY ARTEFACT_ID, LANGUAGE";

        //public const string DataStructure = "SELECT SDMX_ID || '__' || SDMX_AGENCY || '__' || SDMX_VERSION   AS ARTEFACT_ID, SDMX_ID, SDMX_AGENCY, SDMX_VERSION, LABEL, LANGUAGE " +
                                           //"FROM WW_DATA_STRUCTURE " +
                                           //"ORDER BY ARTEFACT_ID, LANGUAGE";

        public const string DataStructureComp = "SELECT distinct COMPONENTID,  NAME, COMPONENTTYPE,  CODELISTREF,  LANGUAGE " +
                                                "FROM WW_COMPONENT_DATA_STRUCTURE " +
                                                "WHERE ARTEFACT_ID = :ARTID " +
                                                "ORDER BY COMPONENTID, LANGUAGE";

        //public static List<string> getDataStructureComponentSQLStatement(List<LocalizedValue> names, string role, string code, string dsd_seq_variable, string valueDomain_seq_variable, string component_seq_variable, string item_seq_variable)
        public static List<string> getDataStructureComponentSQLStatement(List<LocalizedValue> names, string role, string code, string dsd_seq_variable, string valueDomain_seq_variable, string item_seq_variable)
        {
            try
            {
                List<string> statements = new List<string>();
                
                statements.Add("select nvl(max(ITEM_SEQ_ID),0)+1 into " + item_seq_variable + " from ITEM;");
                statements.Add("insert into ITEM values (" + item_seq_variable + ",'" + code + "');");
                statements.Add("insert into COMPONENT values("+ item_seq_variable + " , " + dsd_seq_variable + ", " + valueDomain_seq_variable + ", '" + role + "');");
                
                if (names.Count > 0)
                {
                    string[] lang = { names[0].lang.ToString(), names[1].lang.ToString() };
                    string[] descr = { names[0].value.ToString().Replace("'", "''"), names[1].value.ToString().Replace("'", "''") };
                    bool first = true;
                    int l = 0;
                    statements.Add("select nvl(max(LS_SEQ_ID),0)+1 into appo_variable from LOCALISED_STRING;");

                    foreach (string str in lang)
                    {                        
                        if (first)
                        {
                            statements.Add("insert into LOCALISED_STRING values(appo_variable, '" + str + "', '" + descr[l] + "', 'Description', null," + item_seq_variable + ");");
                            first = false;
                        }
                        else
                            statements.Add("insert into LOCALISED_STRING values(appo_variable, '" + str + "', '" + descr[l] + "', 'Description', null, " + item_seq_variable + ");");
                        l++; 
                    }
                }

                return statements;
            }
             catch (Exception ex)
             {
                 throw new Exception(ex.Message);
             }
        }

        public static List<string> getDataStructureSQLStatement(string dsd_ID, string dsd_Agency, string dsd_Version, List<LocalizedValue> names, string sequentialVariable) 
        { 
             try
            {

                List<string> statements = new List<string>();
                string GlobalID = CommonConstant.ToGlobalID(dsd_ID ,dsd_Agency ,dsd_Version);                          
                statements.Add("select nvl(max(ARTEFACT_SEQ_ID),0)+1 into " + sequentialVariable + " from ARTEFACT;");
                statements.Add("insert into ARTEFACT values(" + sequentialVariable + ", '" + GlobalID + "', '" + dsd_ID + "', '" + dsd_Agency + "', '" + dsd_Version + "');");                
                statements.Add("insert into DATA_STRUCTURE values (" + sequentialVariable +" );");
                

                if (names.Count > 0)
                {
                    string[] lang = { names[0].lang.ToString(), names[1].lang.ToString() };
                    string[] descr = { names[0].value.ToString().Replace("'", "''"), names[1].value.ToString().Replace("'", "''") };
                    bool first = true;
                    int l = 0;
                    statements.Add("select nvl(max(LS_SEQ_ID),0)+1 into appo_variable from LOCALISED_STRING;");

                    foreach (string str in lang)
                    {                        
                        if (first)
                        {
                            statements.Add("insert into LOCALISED_STRING values(appo_variable, '" + str + "', '" + descr[l] + "', 'Description', " + sequentialVariable + ", null);");
                            first = false;
                        }
                        else
                            statements.Add("insert into LOCALISED_STRING values(appo_variable, '" + str + "', '" + descr[l] + "', 'Description', " + sequentialVariable + ", null);");
                        l++;
                    }
                }
                return statements;
            }
             catch (Exception ex)
             {
                 throw new Exception(ex.Message);
             }
        }

        public static List<string> getDataComponentSQLStatement(List<BaseArtefactInfo> componentInfo, string datastructureVariable) 
        { 
            try
            {
                List<string> statements = new List<string>();

                return null;
            }
             catch (Exception ex)
             {
                 throw new Exception(ex.Message);
             }
        }        

     #endregion


     #region DATASET

        //public const string DataSet = "SELECT ARTEFACT_SEQ_ID, SDMX_ID || '__' || SDMX_AGENCY || '__' || SDMX_VERSION   AS ARTEFACT_ID, SDMX_ID, SDMX_AGENCY, SDMX_VERSION, NAME, LANGUAGE, DATA_STRUCTURE_ID " +
        //                              "FROM WW_DATASET " +
        //                              "ORDER BY ARTEFACT_ID, LANGUAGE";

        public const string DataSet = "SELECT * " +
                                      "FROM WW_DATASET " +
                                      "ORDER BY ARTEFACT_ID, LANGUAGE";

        public const string DataSetComp = "SELECT COMPONENTID,  NAME, COMPONENTTYPE,  CODELISTREF,  LANGUAGE " +
                                          "FROM WW_COMPONENT_DATASET " +
                                          "WHERE ARTEFACT_ID = :ARTID " +
                                          "ORDER BY COMPONENTID, LANGUAGE";
    #endregion

        
    #region VALUE DOMAIN
        
        //public const string ValueDomain = "SELECT ARTEFACT_ID, SDMX_ID, SDMX_AGENCY, SDMX_VERSION, NAME, LANGUAGE " +
        //                                  "FROM WW_VALUE_DOMAIN " +
        //                                  "ORDER BY ARTEFACT_ID, LANGUAGE";

        public const string ValueDomain = "SELECT * " +
                                          "FROM WW_VALUE_DOMAIN " +
                                          "ORDER BY ARTEFACT_ID, LANGUAGE";
        
        public const string ValueDomainValue = "SELECT ITEM_SEQ_ID, ITEM_ID, NAME, LANGUAGE " +
                                               "FROM WW_VALUE_DOMAIN_CODE " +
                                               "WHERE  VD_REF_ID = :VD_ID " +
                                               "ORDER BY ITEM_ID, LANGUAGE";

        public const string ValueDomainSubSet = "SELECT ARTEFACT_SEQ_ID, ARTEFACT_ID, SDMX_ID, SDMX_AGENCY, SDMX_VERSION, NAME, LANGUAGE,  " +
                                                "  IS_ENUMERATED, SET_CRITERION_ID,  " +
                                                "  VALUE_DOMAIN_SEQ_ID, VD_ID_REF, CREATION_TYPE " +
                                                "FROM WW_VD_SUBSET " +
                                                "ORDER BY ARTEFACT_ID, LANGUAGE";
        

        public const string SubSetList = "SELECT distinct item.item_seq_id, item.item_id, localised_string.label, localised_string.language " +
                                        "FROM subset_list, item, localised_string, artefact " + 
                                        "WHERE subset_seq_id = artefact.artefact_seq_id " +
                                        "AND item.item_seq_id = subset_list.subset_value_seq_id " +
                                        "AND item.item_seq_id = localised_string.item_seq_id " +
                                        "AND artefact_id=:ID " +
                                        "ORDER BY item.ITEM_ID";

        public const string ValueDomainSubsetSeqId= "SELECT subset_value_seq_id FROM subset_list, artefact WHERE artefact_id=:ID and ARTEFACT_SEQ_ID=SUBSET_SEQ_ID";
        public static List<string> getValueDomainInsertSQLStatement(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion, List<LocalizedValue> names, ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE data_type, List<BaseArtefactInfo> valuesList, string sequentialVariable, string value_domain_seq_id) 
        {
            try
            {
                List<string> statements = new List<string>();
                string GlobalID = CommonConstant.ToGlobalID(ValueDomainID , ValueDomainAgency , ValueDomainVersion);          
                
                statements.Add( "insert into ARTEFACT (select nvl(max(ARTEFACT_SEQ_ID),0)+1, '" + GlobalID + "', '" + ValueDomainID + "', '" + ValueDomainAgency + "', '" + ValueDomainVersion + "' from ARTEFACT);");
                statements.Add("insert into VALUE_DOMAIN (select nvl(max(ARTEFACT_SEQ_ID),0), 1, '" + data_type.ToString() + "', null from ARTEFACT);");
                statements.Add("select nvl(max(VALUE_DOMAIN_SEQ_ID),0) into " + value_domain_seq_id + " from VALUE_DOMAIN;");

                if (names.Count > 0) 
                {
                    string[] lang = { names[0].lang.ToString(), names[1].lang.ToString() };
                    string[] descr = { names[0].value.ToString().Replace("'", "''"), names[1].value.ToString().Replace("'", "''") };
                    bool first = true;
                    int l = 0;
                    statements.Add("select nvl(max(LS_SEQ_ID),0)+1 into appo_variable from LOCALISED_STRING;");

                    foreach (string str in lang)
                    {
                        
                        if (first)
                        {
                            statements.Add("insert into LOCALISED_STRING values(appo_variable , '" + str + "', '" + descr[l] + "', 'Description', " + value_domain_seq_id + ", null);");
                            first = false;
                        }
                        else
                            statements.Add("insert into LOCALISED_STRING values(appo_variable , '" + str + "', '" + descr[l] + "', 'Description', " + value_domain_seq_id + ", null);");
                        l++;
                    }
                }

                foreach (BaseComponentInfo compInfo in valuesList[0].DataStructureDetails.Components)
                {
                    string code = compInfo.vtlId;
                    string[] lang = { compInfo.name[0].lang.ToString(), compInfo.name[1].lang.ToString() };
                    string[] descr = { compInfo.name[0].value.ToString().Replace("'", "''"), compInfo.name[1].value.ToString().Replace("'", "''") };

                    statements.Add("select nvl(max(ITEM_SEQ_ID),0)+1 into " + sequentialVariable + " from ITEM;");
                    statements.Add("insert into ITEM values(" + sequentialVariable +", '" + code + "');");
                    statements.Add("insert into VALUE values(" + sequentialVariable +"," +value_domain_seq_id +");");

                    int l = 0;
                    bool first = true;
                    statements.Add("select nvl(max(LS_SEQ_ID),0)+1 into appo_variable from LOCALISED_STRING;");

                    foreach (string str in lang)
                    {                        
                        if (first)
                        {
                            statements.Add("insert into LOCALISED_STRING values(appo_variable  , '" + str + "', '" + descr[l] + "', 'Description', null , " + sequentialVariable + ");");
                            first = false;
                        }
                        else
                            statements.Add("insert into LOCALISED_STRING values(appo_variable , '" + str + "', '" + descr[l] + "', 'Description', null , " + sequentialVariable + ");");
                        l++;
                    }                   

                }   
                
                return statements;
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        public static string ValueSeqIDValueDomain = "select item_id, value_seq_id from item, value, artefact " +
                                                   "where value.value_domain_seq_id=artefact.artefact_seq_id and item_seq_id= value_seq_id and " +
                                                   "artefact.artefact_id=:ID";

    #endregion

    #region TRASFORMATION


        public const string Trasformations = "SELECT ARTEFACT.ARTEFACT_SEQ_ID, ARTEFACT.ARTEFACT_ID, TRANSFORMATION_SCHEME_TEXT.TRANSFORMATION_SCHEME_TEXT " +
                                             "FROM ARTEFACT " +
                                             "INNER JOIN TRANSFORMATION_SCHEME_TEXT " +
                                             "ON ARTEFACT.ARTEFACT_SEQ_ID = TRANSFORMATION_SCHEME_TEXT.TRANSFORMATION_SCHEME_SEQ_ID " +
                                             "ORDER BY ARTEFACT.ARTEFACT_ID";

        public static string getSingleTransformationSQLStatement(string TrasformationID)
        {
            return "SELECT TRANSFORMATION_SCHEME_TEXT.TRANSFORMATION_SCHEME_TEXT " +
                                              "FROM ARTEFACT " +
                                              "INNER JOIN TRANSFORMATION_SCHEME_TEXT " +
                                              "ON ARTEFACT.ARTEFACT_SEQ_ID = TRANSFORMATION_SCHEME_TEXT.TRANSFORMATION_SCHEME_SEQ_ID " +
                                              "WHERE ARTEFACT_ID='" + TrasformationID + "'";
                
        }        

    #endregion

    #region USER_DEFINED_OPERATORS

        public const string UserDefinedOperator = "select operator_seq_id, operator_id, operator_body, operator_type from user_define_operators";

    #endregion

    }
}
