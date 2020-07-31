using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using System.Web.Services.Protocols;
using ArtefactInfo.model;
using ArtefactInfo.VTL;
using VTLInt_WS.VTLDB;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service" in code, svc and config file together.
public class Service : IService
{    

    public bool TestDatabaseConnection() 
    {
        string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
        VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
        return VTLManOracle.TestDatabaseConnection();
    }

    public DataStructureComponentInfo[] GetDataStructureComp(string DataStuctureID)
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            List<DataStructureComponentInfo> result= VTLDb.GetDataStructureComp(DataStuctureID);
            return result == null ? null : result.ToArray();
         }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public BaseComponentInfo[] GetValueDomainValues(string ValueDomainID)
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            List<BaseComponentInfo> result= VTLDb.GetValueDomainValues(ValueDomainID);
            return result == null ? null : result.ToArray();
         }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public ValueDomainSubsetInfo[] GetValueDomainSubset()
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            List<ValueDomainSubsetInfo> result= VTLDb.GetValueDomainSubset();
            return result == null ? null : result.ToArray();
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public BaseComponentInfo[] GetSubSetList(string ValueDomainSubsetID) 
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            List<BaseComponentInfo> result = VTLDb.GetSubSetList(ValueDomainSubsetID);
            return result == null ? null : result.ToArray();
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public DataStructureComponentInfo[] GetDataSetComp(string DataSetID) 
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            List<DataStructureComponentInfo> result= VTLDb.GetDataSetComp(DataSetID);
            return result == null ? null : result.ToArray();
         }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public BaseArtefactInfo[] GetDataStructures()
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);   
            List<BaseArtefactInfo> result=VTLDb.GetDataStructure();
            return result==null?null:result.ToArray();
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
        
    }

    public DataSetInfo[] GetDataSets()
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            List<DataSetInfo>  result=VTLDb.GetDataSet();          
            return result == null ? null : result.ToArray();
         }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message,new System.Xml.XmlQualifiedName(),ex);                      
        }
    }

    public ValueDomainInfo[] GetValueDomains()
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            List<ValueDomainInfo> result=VTLDb.GetValueDomain();
            return result == null ? null : result.ToArray();
        }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
        
    }

    public string GetTransformation(string TransformationID)
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            return VTLDb.GetTransformation(TransformationID);
        }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public BaseArtefactInfo[] GetTransformationList()
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            List<BaseArtefactInfo> result= VTLDb.GetTransformationList();
            return result == null ? null : result.ToArray();
        }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public BaseUserDefinedOperator[] GetUserDefinedOperatorsList() 
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            List<BaseUserDefinedOperator> result=VTLDb.GetUserDefinedOperators();
            return result == null ? null : result.ToArray();
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public KeyValuePair<int,string>[] GetValueDomain_seq_id(string valueDomainId) 
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManager VTLDb = new VTLDBManager(connectionString);
            return VTLDb.GetValueDomain_seq_id(valueDomainId).ToArray();
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }      
    }

 #region Metadata Upload/Remove

    public void InsertTransformation(string trasformationID, string agency, string version, string trasformationText,string clearedAndOrderedStatements)
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            VTLManOracle.InsertTransformation(trasformationID,agency,version, trasformationText, clearedAndOrderedStatements);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public void RemoveTransformation(string trasName)
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            VTLManOracle.RemoveTransformation(trasName);
         }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public bool InsertUserDefinedOperator(string Operator_ID, string body, int operator_type) 
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            return VTLManOracle.InsertUserDefinedOperator(Operator_ID, body, operator_type);
        }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public void RemoveUserDefinedOperator(string operatorName)
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            VTLManOracle.RemoveUserDefinedOperator(operatorName);
        }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public string InsertValueDomain(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion, LocalizedValue[] names, BaseArtefactInfo[] valueList, bool check_presence, int creationType, VTL_Model.VTL_DATATYPE data_type = VTL_Model.VTL_DATATYPE.String) 
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            return VTLManOracle.InsertValueDomain(ValueDomainID, ValueDomainAgency, ValueDomainVersion, names, valueList,check_presence, creationType,  data_type);
         }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public string InsertValueDomainDescribed(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion, LocalizedValue[] names, string valueRestriction, bool check_presence, int creationType, VTL_Model.VTL_DATATYPE data_type = VTL_Model.VTL_DATATYPE.String)
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            return VTLManOracle.InsertValueDomainDescribed(ValueDomainID, ValueDomainAgency, ValueDomainVersion, names, valueRestriction, check_presence, creationType, data_type);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public void RemoveValueDomain(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion)
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            VTLManOracle.RemoveValueDomain(ValueDomainID, ValueDomainAgency, ValueDomainVersion);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public bool InsertValueDomainSubsetDescribed(string ValueDomainSubsetID, string ValueDomainSubsetAgency, string ValueDomainSubsetVersion, string ref_ValueDomainID, string ref_ValueDomainAgency, string ref_ValueDomainVersion, LocalizedValue[] names, string valueRestriction, int creationType)
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            return VTLManOracle.InsertValueDomainSubsetDescribed(ValueDomainSubsetID, ValueDomainSubsetAgency, ValueDomainSubsetVersion, ref_ValueDomainID, ref_ValueDomainAgency, ref_ValueDomainVersion, names, valueRestriction, creationType);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public bool InsertValueDomainSubset(string ValueDomainSubsetID, string ValueDomainSubsetAgency, string ValueDomainSubsetVersion, string ref_ValueDomainID, string ref_ValueDomainAgency, string ref_ValueDomainVersion, LocalizedValue[] names, int[] valueList, int creationType)
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            return VTLManOracle.InsertValueDomainSubset(ValueDomainSubsetID, ValueDomainSubsetAgency, ValueDomainSubsetVersion, ref_ValueDomainID, ref_ValueDomainAgency, ref_ValueDomainVersion, names, valueList, creationType);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public void RemoveValueDomainSubset(string ValueDomainSubsetID, string ValueDomainSubsetAgency, string ValueDomainSubsetVersion) 
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            VTLManOracle.RemoveValueDomainSubset(ValueDomainSubsetID, ValueDomainSubsetAgency, ValueDomainSubsetVersion);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public string InsertDataStructure(string dsd_ID, string dsd_Agency, string dsd_Version, LocalizedValue[] dsd_names, BaseComponentInfo[] dsd_componentList, int creationType) 
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            return VTLManOracle.InsertDataStructure(dsd_ID, dsd_Agency, dsd_Version, dsd_names, dsd_componentList, creationType);
        }
        catch (Exception ex) 
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public string InsertDataSet(string ref_dsd_ID, string ref_dsd_Agency, string ref_dsd_Version, string dataset_id, string dataset_agency, string dataset_version, LocalizedValue[] dataset_names, BaseComponentInfo[] dsd_componentList, int creationType) 
    {
        try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            return VTLManOracle.InsertDataSet(ref_dsd_ID, ref_dsd_Agency, ref_dsd_Version, dataset_id, dataset_agency, dataset_version, dataset_names, dsd_componentList, creationType);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    public void RemoveDataSet(string dataset_ID, string dataset_Agency, string dataset_Version)
    {
            try
        {
            string connectionString = Util.GetOracleConnectionString(ConfigurationManager.AppSettings["VTLDB_Host"], ConfigurationManager.AppSettings["VTLDB_UserName"], ConfigurationManager.AppSettings["VTLDB_Pwd"]);
            VTLDBManagerOracle VTLManOracle = new VTLDBManagerOracle(connectionString);
            VTLManOracle.RemoveDataSet(dataset_ID, dataset_Agency, dataset_Version);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            throw new SoapException(ex.Message, new System.Xml.XmlQualifiedName(), ex);
        }
    }

    #endregion
}

