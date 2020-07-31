using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using ArtefactInfo.model;
using ArtefactInfo.VTL;

// NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService" in both code and config file together.
[ServiceContract]
public interface IService
{
    #region Get methods

    [OperationContract]
    bool TestDatabaseConnection();

    [OperationContract]
    DataStructureComponentInfo[] GetDataStructureComp(string DataStuctureID);

    [OperationContract]
    BaseComponentInfo[] GetValueDomainValues(string ValueDomainID);

    [OperationContract]
    DataSetInfo[] GetDataSets();

    [OperationContract]
    DataStructureComponentInfo[] GetDataSetComp(string DataSetID);

    [OperationContract]
    BaseArtefactInfo[] GetDataStructures();

    [OperationContract]
    ValueDomainInfo[] GetValueDomains();

    [OperationContract]
    ValueDomainSubsetInfo[] GetValueDomainSubset();

    [OperationContract]
    BaseComponentInfo[] GetSubSetList(string ValueDomainSubsetID);

    [OperationContract]
    string GetTransformation(string TransformationID);

    [OperationContract]
    BaseArtefactInfo[] GetTransformationList();

    [OperationContract]
    BaseUserDefinedOperator[] GetUserDefinedOperatorsList();

    [OperationContract]
    KeyValuePair<int, string>[] GetValueDomain_seq_id(string valueDomainId);

    #endregion

    #region Metadata Upload/Remove

    [OperationContract]
    void InsertTransformation(string trasformationID, string agency, string version, string trasformationText, string clearedAndOrderedStatements);

    [OperationContract]
    void RemoveTransformation(string trasName);

    [OperationContract]
    bool InsertUserDefinedOperator(string Operator_ID, string body, int operator_type);
    

    //[OperationContract]
    //string InsertValueDomain(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion, LocalizedValue[] names, BaseArtefactInfo[] valueList, bool check_presence, IDbConnection conn, IDbTransaction tran);

    [OperationContract]    
    string InsertValueDomain(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion, LocalizedValue[] names, BaseArtefactInfo[] valueList, bool check_presence, int creationType, VTL_Model.VTL_DATATYPE data_type = VTL_Model.VTL_DATATYPE.String);
    
    [OperationContract]
    string InsertValueDomainDescribed(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion, LocalizedValue[] names, string valueRestriction, bool check_presence, int creationType, VTL_Model.VTL_DATATYPE data_type = VTL_Model.VTL_DATATYPE.String);

    [OperationContract]
    void RemoveValueDomain(string ValueDomainID, string ValueDomainAgency, string ValueDomainVersion);

    [OperationContract]
    bool InsertValueDomainSubsetDescribed(string ValueDomainSubsetID, string ValueDomainSubsetAgency, string ValueDomainSubsetVersion, string ref_ValueDomainID, string ref_ValueDomainAgency, string ref_ValueDomainVersion, LocalizedValue[] names, string valueRestriction, int creationType);

    [OperationContract]
    bool InsertValueDomainSubset(string ValueDomainSubsetID, string ValueDomainSubsetAgency, string ValueDomainSubsetVersion, string ref_ValueDomainID, string ref_ValueDomainAgency, string ref_ValueDomainVersion, LocalizedValue[] names, int[] valueList, int creationType);

    [OperationContract]
    void RemoveValueDomainSubset(string ValueDomainSubsetID, string ValueDomainSubsetAgency, string ValueDomainSubsetVersion);    

    [OperationContract]
    string InsertDataStructure(string dsd_ID, string dsd_Agency, string dsd_Version, LocalizedValue[] dsd_names, BaseComponentInfo[] dsd_componentList, int creationType);

    [OperationContract]
    string InsertDataSet(string ref_dsd_ID, string ref_dsd_Agency, string ref_dsd_Version, string dataset_id, string dataset_agency, string dataset_version, LocalizedValue[] dataset_names, BaseComponentInfo[] dsd_componentList, int creationType);

    [OperationContract]
    void RemoveDataSet(string dataset_ID, string dataset_Agency, string dataset_Version);

    [OperationContract]
    void RemoveUserDefinedOperator(string operatorName);

    #endregion
}
