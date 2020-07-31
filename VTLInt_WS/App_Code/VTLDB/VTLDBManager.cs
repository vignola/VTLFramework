using ArtefactInfo.model;
using ISTAT_DB_DAL;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;

namespace VTLInt_WS.VTLDB
{
    public class VTLDBManager
    {

        #region PRIVATE FIELDS

        public readonly DataProvider _provider;

        //private string CONNECTION_STRING = "Data Source=LIB1;User Id=sv_vtl;Password=svvtl;Integrated Security=no;";

        #endregion

        public VTLDBManager() { throw new NotImplementedException(); }

        public VTLDBManager(string connectionString)
        {
            try
            {
                this._provider = new DataProvider(connectionString, ProviderType.Oracle);
             }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }
        
        public class LineCountEventArgs : EventArgs
        {
            public LineCountEventArgs(int LineCount) 
            {
                LineNumber = LineCount;
            }
            public int LineNumber;
        }

        public delegate void LineCountEventHandler(object sender, LineCountEventArgs e);
        public event LineCountEventHandler LineCountEvent;

        #region PRIVATE METHODS

        private string GetWhereCondition(string id, string version, string agId)
        {
            string strWhere = String.Empty;
            string strId = String.Empty;
            string strAgency = String.Empty;
            string strVersion = String.Empty;
            bool bFound = false;

            try
            {
                if (!String.IsNullOrEmpty(id))
                {
                    strId = " a.ID = '" + id + "' AND ";
                    bFound = true;
                }
                if (!String.IsNullOrEmpty(agId))
                {
                    strAgency = " agency = '" + agId + "' AND ";
                    bFound = true;
                }
                if (!String.IsNullOrEmpty(version))
                {
                    strVersion = " version = '" + version + "' AND ";
                    bFound = true;
                }

                if (bFound)
                {
                    strWhere = " WHERE " + strId + strAgency + strVersion;

                    strWhere = strWhere.Substring(0, strWhere.LastIndexOf("AND"));
                }
                return strWhere;
            }
            catch (Exception ex)
            {                
               throw new Exception(ex.Message);
            }
        }

        #endregion

        #region PUBLIC METHODS

        #region Get methods

        public List<DataSetInfo> GetDataSet()
        {
            string queryString = VTLSqlStatement.DataSet;

            IDataReader reader = _provider.ExecuteReader(queryString);
            if (reader == null) throw new Exception("Database connection failed");

            var posIdDSet = reader.GetOrdinal("ARTEFACT_ID");

            var posSdmxId = reader.GetOrdinal("SDMX_ID");
            var posAgency = reader.GetOrdinal("SDMX_AGENCY");
            var posVersion = reader.GetOrdinal("SDMX_VERSION");

            var posIdStruttura = reader.GetOrdinal("DATA_STRUCTURE_ID");
            var posCollected = reader.GetOrdinal("IS_COLLECTED");

            var posLocale = reader.GetOrdinal("LANGUAGE");
            var posLabel = reader.GetOrdinal("NAME");

            var posCreationType = reader.GetOrdinal("CREATION_TYPE");

            List<DataSetInfo> l = new List<DataSetInfo>();
            string vtlOld = String.Empty;
            string vtlNew = String.Empty;
            string locale, label, vtlId, refStruttura, sdmxId, sdmxAg, sdmxVer;
            int isCollected, creation_type;

            bool bFirst = true;
            List<LocalizedValue> lv = null;
            DataSetInfo dsi = null; ;
            try
            {
                while (reader.Read())
                {
                    if (bFirst)
                    {
                        bFirst = false;
                        vtlId = reader.GetString(posIdDSet).Trim();
                        refStruttura = reader.GetString(posIdStruttura).Trim();
                        sdmxId = reader.GetString(posSdmxId).Trim();
                        sdmxAg = reader.GetString(posAgency).Trim();
                        sdmxVer = reader.GetString(posVersion).Trim();
                        isCollected = reader.GetInt32(posCollected);
                        creation_type = reader.GetInt32(posCreationType);

                        locale = reader.GetString(posLocale).Trim();
                        label = reader.GetString(posLabel).Trim();

                        dsi = new DataSetInfo { vtlId = vtlId, sdmxId = sdmxId, sdmxAgency = sdmxAg, sdmxVersion = sdmxVer, dataSource = "VTL", isCollected = Util.IntToBool(isCollected), datastructure_id = refStruttura, creationType = creation_type };

                        lv = new List<LocalizedValue>();

                        lv.Add(new LocalizedValue { lang = locale, value = label });

                        vtlOld = vtlId;
                    }
                    else
                    {
                        vtlId = reader.GetString(posIdDSet).Trim();

                        if (!String.Equals(vtlOld, vtlId))
                        {
                            dsi.name = lv;
                            l.Add(dsi);

                            refStruttura = reader.GetString(posIdStruttura).Trim();
                            sdmxId = reader.GetString(posSdmxId).Trim();
                            sdmxAg = reader.GetString(posAgency).Trim();
                            sdmxVer = reader.GetString(posVersion).Trim();
                            isCollected = reader.GetInt32(posCollected);
                            creation_type = reader.GetInt32(posCreationType);

                            dsi = new DataSetInfo { vtlId = vtlId, sdmxId = sdmxId, sdmxAgency = sdmxAg, sdmxVersion = sdmxVer, dataSource = "VTL", isCollected = Util.IntToBool(isCollected), datastructure_id = refStruttura, creationType = creation_type };
                            lv = new List<LocalizedValue>();
                            vtlOld = vtlId;
                        }

                        locale = reader.GetString(posLocale).Trim();
                        label = reader.GetString(posLabel).Trim();

                        lv.Add(new LocalizedValue { lang = locale, value = label });

                    }
                }

                if (lv == null) return null;

                dsi.name = lv;
                l.Add(dsi);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                reader.Close();
            }

            return l;            
        }

        public List<DataStructureComponentInfo> GetDataSetComp(string id)
        {
            try
            {
                string queryString = VTLSqlStatement.DataSetComp;

                IDataReader reader = _provider.ExecuteReaderWithParam(queryString, "ARTID", id, DbType.String);
                if (reader == null) throw new Exception("Database connection failed");

                var posIdComp = reader.GetOrdinal("COMPONENTID");
                var posRole = reader.GetOrdinal("COMPONENTTYPE");
                var posValDomId = reader.GetOrdinal("CODELISTREF");
                var posLocale = reader.GetOrdinal("LANGUAGE");
                var posLabel = reader.GetOrdinal("NAME");
                string idComp = String.Empty;
                string role = String.Empty;
                string valDomId = String.Empty;
                string label = String.Empty;
                string locale = String.Empty;
                bool bFirst = true;
                string vtlOld = String.Empty;
                string vtlNew = String.Empty;
                List<DataStructureComponentInfo> components = new List<DataStructureComponentInfo>();
                DataStructureComponentInfo tmpComp = null;
                List<LocalizedValue> names = null;

                while (reader.Read())
                {
                    idComp = reader.GetString(posIdComp);
                    if (!String.Equals(vtlOld, idComp))
                    {
                        role = reader.GetString(posRole);
                        valDomId = reader.GetString(posValDomId);
                        label = reader.GetString(posLabel);
                        locale = reader.GetString(posLocale);
                        tmpComp = new DataStructureComponentInfo() { vtlId = idComp, role = role, datastructure_id = id, valuedomain_id = valDomId };

                        names = new List<LocalizedValue>();
                        names.Add(new LocalizedValue { lang = locale, value = label });
                        tmpComp.name = names;
                        components.Add(tmpComp);

                    }
                    else
                    {
                        label = reader.GetString(posLabel);
                        locale = reader.GetString(posLocale);
                        names.Add(new LocalizedValue { lang = locale, value = label });
                    }

                    vtlOld = idComp;
                }

                return components;
            }
            catch (Exception ex)
            {
                
                throw new Exception (ex.Message);
            }
        }

        public List<BaseArtefactInfo> GetDataStructure()
        {
            //string strWhere = GetWhereCondition(id, version, agId);
            string queryString = VTLSqlStatement.DataStructure;// +strWhere;

            IDataReader reader = _provider.ExecuteReader(queryString);
            if (reader == null) throw new Exception("Database connection failed");

            var posIdStruttura = reader.GetOrdinal("ARTEFACT_ID");
            var posSdmxId = reader.GetOrdinal("SDMX_ID");
            var posAgency = reader.GetOrdinal("SDMX_AGENCY");
            var posVersion = reader.GetOrdinal("SDMX_VERSION");
            var posLocale = reader.GetOrdinal("LANGUAGE");
            var posLabel = reader.GetOrdinal("NAME");
            var posCreationType = reader.GetOrdinal("CREATION_TYPE");
            //var posFinal = reader.GetOrdinal("FINAL");

            List<BaseArtefactInfo> l = new List<BaseArtefactInfo>();
            string vtlOld = String.Empty;
            string vtlNew = String.Empty;
            string locale, label, vtlId, sdmxId, sdmxAg, sdmxVer;
            int creation_Type;
            bool bFirst = true;
            List<LocalizedValue> lv = null;
            BaseArtefactInfo dsi = null; ;
            try
            {
                while (reader.Read())
                {
                    if (bFirst)
                    {
                        bFirst = false;
                        vtlId = reader.GetString(posIdStruttura).Trim();
                        sdmxId = reader.GetString(posSdmxId).Trim();
                        sdmxAg = reader.GetString(posAgency).Trim();
                        sdmxVer = reader.GetString(posVersion).Trim();
                        locale = reader.GetString(posLocale).Trim();
                        label = reader.GetString(posLabel).Trim();
                        creation_Type = reader.GetInt32(posCreationType);

                        dsi = new BaseArtefactInfo { vtlId = vtlId, sdmxId = sdmxId, sdmxAgency = sdmxAg, sdmxVersion = sdmxVer, dataSource = "VTL", creationType=creation_Type };

                        lv = new List<LocalizedValue>();

                        lv.Add(new LocalizedValue { lang = locale, value = label });

                        vtlOld = vtlId;
                    }
                    else
                    {
                        vtlId = reader.GetString(posIdStruttura).TrimEnd();

                        if (!String.Equals(vtlOld, vtlId))
                        {
                            dsi.name = lv;
                            l.Add(dsi);

                            sdmxId = reader.GetString(posSdmxId).Trim();
                            sdmxAg = reader.GetString(posAgency).Trim();
                            sdmxVer = reader.GetString(posVersion).Trim();
                            creation_Type = reader.GetInt32(posCreationType);

                            dsi = new BaseArtefactInfo { vtlId = vtlId, sdmxId = sdmxId, sdmxAgency = sdmxAg, sdmxVersion = sdmxVer, dataSource = "VTL", creationType = creation_Type };
                            lv = new List<LocalizedValue>();
                            vtlOld = vtlId;
                        }

                        locale = reader.GetString(posLocale).Trim();
                        label = reader.GetString(posLabel).Trim();

                        lv.Add(new LocalizedValue { lang = locale, value = label });

                    }
                }

                if (lv == null) return null;

                dsi.name = lv;
                l.Add(dsi);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                reader.Close();
            }

            return l;
        }

        public List<DataStructureComponentInfo> GetDataStructureComp(string id)
        {
            try
            {
                string queryString = VTLSqlStatement.DataStructureComp;// +strWhere;

                IDataReader reader = _provider.ExecuteReaderWithParam(queryString, "ARTID", id, DbType.String);
                if (reader == null) throw new Exception("Database connection failed");

                if (reader != null)
                {
                    var posIdComp = reader.GetOrdinal("COMPONENTID");
                    var posRole = reader.GetOrdinal("COMPONENTTYPE");
                    var posValDomId = reader.GetOrdinal("CODELISTREF");
                    var posLocale = reader.GetOrdinal("LANGUAGE");
                    var posLabel = reader.GetOrdinal("NAME");

                    string idComp = String.Empty;
                    string role = String.Empty;
                    string valDomId = String.Empty;
                    string label = String.Empty;
                    string locale = String.Empty;
                    bool bFirst = true;
                    string vtlOld = String.Empty;
                    string vtlNew = String.Empty;
                    List<DataStructureComponentInfo> components = new List<DataStructureComponentInfo>();
                    DataStructureComponentInfo tmpComp = null;
                    List<LocalizedValue> names = null;

                    while (reader.Read())
                    {
                        idComp = reader.GetString(posIdComp);
                        if (!String.Equals(vtlOld, idComp))
                        {
                            role = reader.GetString(posRole);
                            valDomId = reader.GetString(posValDomId);
                            label = reader.GetString(posLabel);
                            locale = reader.GetString(posLocale);
                            tmpComp = new DataStructureComponentInfo() { vtlId = idComp, role = role, datastructure_id = id, valuedomain_id = valDomId };

                            names = new List<LocalizedValue>();
                            names.Add(new LocalizedValue { lang = locale, value = label });
                            tmpComp.name = names;
                            components.Add(tmpComp);

                        }
                        else
                        {
                            label = reader.GetString(posLabel);
                            locale = reader.GetString(posLocale);
                            names.Add(new LocalizedValue { lang = locale, value = label });
                        }

                        vtlOld = idComp;
                    }

                    return components;
                }
                else
                {
                    throw new Exception("Data not found");
                }
            }
            catch (Exception)
            {
                
                throw;
            }
        }

        public List<ValueDomainInfo> GetValueDomain()
        {
            string queryString = VTLSqlStatement.ValueDomain;

            IDataReader reader = _provider.ExecuteReader(queryString);
            if (reader == null) throw new Exception("Database connection failed");

            var posIdVD = reader.GetOrdinal("ARTEFACT_ID");
            var posSdmxId = reader.GetOrdinal("SDMX_ID");
            var posAgency = reader.GetOrdinal("SDMX_AGENCY");
            var posVersion = reader.GetOrdinal("SDMX_VERSION");
            var posLocale = reader.GetOrdinal("LANGUAGE");
            var posLabel = reader.GetOrdinal("NAME");
            var posDataType = reader.GetOrdinal("DATA_TYPE");
            var posIsEnumerated = reader.GetOrdinal("IS_ENUMERATED");
            var posValueRestr = reader.GetOrdinal("VALUE_RESTRICTION");
            var posCreationType = reader.GetOrdinal("CREATION_TYPE");
            List<ValueDomainInfo> l = new List<ValueDomainInfo>();
            string vtlOld = String.Empty;
            string vtlNew = String.Empty;
            string locale, label, vtlId, sdmxId, sdmxAg, sdmxVer, dataType, valueRetriction;
            int creation_Type;
            int isEnumerated;
            bool bFirst = true;
            List<LocalizedValue> lv = null;
            ValueDomainInfo vdi = null; ;

            try
            {
                while (reader.Read())
                {
                    if (bFirst)
                    {
                        bFirst = false;
                        vtlId = reader.GetString(posIdVD).Trim();
                        sdmxId = reader.GetString(posSdmxId).Trim();
                        sdmxAg = reader.GetString(posAgency).Trim();
                        sdmxVer = reader.GetString(posVersion).Trim();
                        dataType = Util.SafeGetString(reader, posDataType);
                        isEnumerated = reader.GetInt32(posIsEnumerated);
                        valueRetriction = Util.SafeGetString(reader, posValueRestr);
                        creation_Type = reader.GetInt32(posCreationType);

                        locale = reader.GetString(posLocale).Trim();
                        label = reader.GetString(posLabel).Trim();
                       
                        vdi = new ValueDomainInfo { vtlId = vtlId, sdmxId = sdmxId, sdmxAgency = sdmxAg, sdmxVersion = sdmxVer, dataSource = "VTL", isEnumerated = Util.IntToBool(isEnumerated), data_type = dataType, value_restriction = valueRetriction, creationType=creation_Type };

                        lv = new List<LocalizedValue>();

                        lv.Add(new LocalizedValue { lang = locale, value = label });

                        vtlOld = vtlId;
                    }
                    else
                    {
                        vtlId = reader.GetString(posIdVD).TrimEnd();

                        if (!String.Equals(vtlOld, vtlId))
                        {
                            vdi.name = lv;
                            l.Add(vdi);

                            sdmxId = reader.GetString(posSdmxId).Trim();
                            sdmxAg = reader.GetString(posAgency).Trim();
                            sdmxVer = reader.GetString(posVersion).Trim();
                            dataType = Util.SafeGetString(reader, posDataType);

                            isEnumerated = reader.GetInt32(posIsEnumerated);
                            valueRetriction = Util.SafeGetString(reader, posValueRestr);
                            creation_Type = reader.GetInt32(posCreationType);
                            
                            vdi = new ValueDomainInfo { vtlId = vtlId, sdmxId = sdmxId, sdmxAgency = sdmxAg, sdmxVersion = sdmxVer, dataSource = "VTL", isEnumerated = Util.IntToBool(isEnumerated), data_type = dataType, value_restriction = valueRetriction, creationType = creation_Type };
                            lv = new List<LocalizedValue>();
                            vtlOld = vtlId;
                        }

                        locale = reader.GetString(posLocale).Trim();
                        label = reader.GetString(posLabel).Trim();

                        lv.Add(new LocalizedValue { lang = locale, value = label });

                    }
                }

                if (lv == null) return null;

                vdi.name = lv;
                l.Add(vdi);

                return l;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);            
            }
            finally
            {
                reader.Close();
            }                   
            
        }

        public List<BaseComponentInfo> GetValueDomainValues(string id)
        {
            try
            {
                string queryString = VTLSqlStatement.ValueDomainValue;

                IDataReader reader = _provider.ExecuteReaderWithParam(queryString, "VD_ID", id, DbType.String);
                if (reader == null) throw new Exception("Database connection failed");

                var posValueId = reader.GetOrdinal("ITEM_ID");
                var posLocale = reader.GetOrdinal("LANGUAGE");
                var posLabel = reader.GetOrdinal("NAME");

                string valueId = String.Empty;
                string valueIdOld = String.Empty;
                string label = String.Empty;
                string locale = String.Empty;

                List<BaseComponentInfo> components = new List<BaseComponentInfo>();
                BaseComponentInfo tmpVal = null;
                List<LocalizedValue> names = null;

                valueId = null;
                while (reader.Read())
                {
                    valueId = reader.GetString(posValueId);

                    if (valueId != valueIdOld)
                    {
                        tmpVal = new BaseComponentInfo() { vtlId = valueId };
                        names = new List<LocalizedValue>();
                        tmpVal.name = names;
                    }

                    label = reader.GetString(posLabel);
                    locale = reader.GetString(posLocale);

                    names.Add(new LocalizedValue { lang = locale, value = label });

                    if (valueId != valueIdOld)
                    {
                        components.Add(tmpVal);
                    }
                    valueIdOld = valueId;
                }

                return components;
            }
            catch (Exception)
            {
                
                throw;
            }
        }

        public List<KeyValuePair<int,string>> GetValueDomain_seq_id(string valueDomainId)
        {
            string queryString = VTLSqlStatement.ValueSeqIDValueDomain;
            List<KeyValuePair<int, string>> tmpList = null;
            IDataReader reader = _provider.ExecuteReaderWithParam(queryString, "ID", valueDomainId, DbType.String);
            
            if (reader == null) throw new Exception("Database connection failed");

             try
            {
                tmpList = new List<KeyValuePair<int, string>>();
                while (reader.Read())
                {
                    KeyValuePair<int, string> tmpVal = new KeyValuePair<int, string>(reader.GetInt32(1),reader.GetString(0));
                    tmpList.Add(tmpVal);
                }
                return tmpList;
            }
            catch (Exception)
            {                
                throw;           
            }
            finally
            {
                reader.Close();
            }
            
        }

        public List<ValueDomainSubsetInfo> GetValueDomainSubset()
        {
            string queryString = VTLSqlStatement.ValueDomainSubSet;

            IDataReader reader = _provider.ExecuteReader(queryString);
            if (reader == null) throw new Exception("Database connection failed");

            var posId = reader.GetOrdinal("ARTEFACT_ID");
            var posSdmxId = reader.GetOrdinal("SDMX_ID");
            var posAgency = reader.GetOrdinal("SDMX_AGENCY");
            var posVersion = reader.GetOrdinal("SDMX_VERSION");
            var posIsEnumerated = reader.GetOrdinal("IS_ENUMERATED");
            var posSetCriterion = reader.GetOrdinal("SET_CRITERION_ID");
            var posLocale = reader.GetOrdinal("LANGUAGE");
            var posLabel = reader.GetOrdinal("NAME");
            var posVDIdRef = reader.GetOrdinal("VD_ID_REF");
            var posCreationType = reader.GetOrdinal("CREATION_TYPE");

            List<ValueDomainSubsetInfo> l = new List<ValueDomainSubsetInfo>();
            string vtlOld = String.Empty;
            string vtlNew = String.Empty;
            string locale, label, vtlId, sdmxId, sdmxAg, sdmxVer, setCriterionId, vdIdRef;
            int isEnumerated, creation_Type;
            bool bFirst = true;
            List<LocalizedValue> lv = null;
            ValueDomainSubsetInfo vdi = null; ;

            try
            {
                while (reader.Read())
                {
                    if (bFirst)
                    {
                        bFirst = false;
                        vtlId = reader.GetString(posId).Trim();
                        sdmxId = reader.GetString(posSdmxId).Trim();
                        sdmxAg = reader.GetString(posAgency).Trim();
                        sdmxVer = reader.GetString(posVersion).Trim();

                        creation_Type = reader.GetInt32(posCreationType);

                        isEnumerated = reader.GetInt32(posIsEnumerated);
                        setCriterionId = Util.SafeGetString(reader, posSetCriterion);

                        locale = reader.GetString(posLocale).Trim();
                        label = reader.GetString(posLabel).Trim();
                        vdIdRef = reader.GetString(posVDIdRef);

                        vdi = new ValueDomainSubsetInfo { vtlId = vtlId, sdmxId = sdmxId, sdmxAgency = sdmxAg, sdmxVersion = sdmxVer, dataSource = "VTL", isEnumerated = Util.IntToBool(isEnumerated), set_criterion_id = setCriterionId, vd_id_ref = vdIdRef, creationType =creation_Type};

                        lv = new List<LocalizedValue>();

                        lv.Add(new LocalizedValue { lang = locale, value = label });

                        vtlOld = vtlId;
                    }
                    else
                    {
                        vtlId = reader.GetString(posId).TrimEnd();

                        if (!String.Equals(vtlOld, vtlId))
                        {
                            vdi.name = lv;
                            l.Add(vdi);

                            sdmxId = reader.GetString(posSdmxId).Trim();
                            sdmxAg = reader.GetString(posAgency).Trim();
                            sdmxVer = reader.GetString(posVersion).Trim();

                            creation_Type = reader.GetInt32(posCreationType);

                            isEnumerated = reader.GetInt32(posIsEnumerated);
                            setCriterionId = Util.SafeGetString(reader, posSetCriterion);
                            vdIdRef = reader.GetString(posVDIdRef);

                            vdi = new ValueDomainSubsetInfo { vtlId = vtlId, sdmxId = sdmxId, sdmxAgency = sdmxAg, sdmxVersion = sdmxVer, dataSource = "VTL", isEnumerated = Util.IntToBool(isEnumerated), set_criterion_id = setCriterionId, vd_id_ref = vdIdRef, creationType = creation_Type };
                            lv = new List<LocalizedValue>();
                            vtlOld = vtlId;
                        }

                        locale = reader.GetString(posLocale).Trim();
                        label = reader.GetString(posLabel).Trim();

                        lv.Add(new LocalizedValue { lang = locale, value = label });

                    }
                }

                if (lv == null) return null;

                vdi.name = lv;
                l.Add(vdi);
                return l;
             }
            catch (Exception)
            {                
                throw;
            }
            finally
            {
                reader.Close();                
            }
        }               

        public List<BaseComponentInfo> GetSubSetList(string subsetId)
        {
            try
            {
                string queryString = VTLSqlStatement.SubSetList;

                IDataReader reader = _provider.ExecuteReaderWithParam(queryString, "ID", subsetId, DbType.String);
                if (reader == null) throw new Exception("Database connection failed");

                var posValueId = reader.GetOrdinal("ITEM_ID");
                var posValueSeqId = reader.GetOrdinal("ITEM_SEQ_ID");
                var posLocale = reader.GetOrdinal("LANGUAGE");
                var posLabel = reader.GetOrdinal("LABEL");

                string valueId = String.Empty;
                string valueIdOld = string.Empty;
                string valueSeqId = string.Empty;
                string label = String.Empty;
                string locale = String.Empty;
                
                string vtlOld = String.Empty;
                string vtlNew = String.Empty;
                List<BaseComponentInfo> components = new List<BaseComponentInfo>();
                BaseComponentInfo tmpVal = null;
                List<LocalizedValue> names = null;

                valueId = null;
                while (reader.Read())
                {
                    valueId = reader.GetString(posValueId);
                    valueSeqId = reader.GetInt32(posValueSeqId).ToString();

                    if (valueId != valueIdOld)
                    {
                        tmpVal = new BaseComponentInfo() { vtlId = valueId, seq_id = valueSeqId };
                        names = new List<LocalizedValue>();
                        tmpVal.name = names;
                    }

                    label = reader.GetString(posLabel);
                    locale = reader.GetString(posLocale);

                    names.Add(new LocalizedValue { lang = locale, value = label });

                    if (valueId != valueIdOld)
                    {
                        components.Add(tmpVal);
                    }
                    valueIdOld = valueId;
                }

                return components;
            }
            catch (Exception)
            {
                
                throw;
            }
        }

        public List<BaseArtefactInfo> GetTransformationList() 
        {
            IDataReader reader = null;

            try
            {
                string queryString = VTLSqlStatement.Trasformations;
                reader = _provider.ExecuteReader(queryString);

                List<BaseArtefactInfo> trasformationList = new List<BaseArtefactInfo>();
                int transfId;
                string transfName;

                if (reader == null) throw new Exception("Database connection failed");

                int pos_item_seq_id = reader.GetOrdinal("ARTEFACT_SEQ_ID");
                int pos_transfID = reader.GetOrdinal("ARTEFACT_ID");


                while (reader.Read())
                {
                    transfId = reader.GetInt32(pos_item_seq_id);
                    transfName = reader.GetString(pos_transfID).Trim();
                    BaseArtefactInfo tmp = new BaseArtefactInfo();
                    tmp.vtlId = transfName;
                    trasformationList.Add(tmp);
                }
                return trasformationList;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally 
            {
                if (reader!=null) reader.Close();
            }
        }

        public string GetTransformation(string transformationID) 
        {
            try
            {
                string queryString = VTLSqlStatement.getSingleTransformationSQLStatement(transformationID);
                IDataReader reader = _provider.ExecuteReader(queryString);

                if (reader == null) throw new Exception("Database connection failed");

                reader.Read();
                return reader.GetString(reader.GetOrdinal("TRANSFORMATION_SCHEME_TEXT")).Trim();
                
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        public List<BaseUserDefinedOperator> GetUserDefinedOperators() 
        {
            IDataReader reader = null;

            try
            {
                List<BaseUserDefinedOperator> tmpList = new List<BaseUserDefinedOperator>();

                string queryString = VTLSqlStatement.UserDefinedOperator;
                reader = _provider.ExecuteReader(queryString);                

                if (reader == null) throw new Exception("Database connection failed");

                int pos_usd_seq_id = reader.GetOrdinal("OPERATOR_SEQ_ID");
                int pos_usdID = reader.GetOrdinal("OPERATOR_ID");
                int pos_usd_body = reader.GetOrdinal("OPERATOR_BODY");
                int pos_usdType = reader.GetOrdinal("OPERATOR_TYPE");


                while (reader.Read())
                {
                    BaseUserDefinedOperator tmp = new BaseUserDefinedOperator();
                    tmp.OperatorName = reader.GetString(pos_usdID);
                    tmp.Operator_Seq_Id = reader.GetInt32(pos_usd_seq_id);
                    tmp.OperatorBody = reader.GetString(pos_usd_body);                    
                    int.TryParse(reader.GetString(pos_usdType), out tmp.Operator_Type );
                    
                    tmpList.Add(tmp);
                }                
                
                return tmpList;


             }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }

            finally
            {
                if (reader != null) reader.Close();
            }
        }

        #endregion

        #region Insert method

        public void InsertDataStructure(string dsd_ID, string dsd_Agency, string dsd_Version, List<LocalizedValue> dsd_names, List<ValueDomainInfoInDataStructure> dsd_componentList) 
        {
            try
            {
                List<string[]> parameters = new List<string[]>();
                parameters.Add(new string[] { "datastructure_seq_id", "NUMBER" });
                parameters.Add(new string[] { "valueDomain_seq_id", "NUMBER" });
                parameters.Add(new string[] { "main_value_domain_seq_id", "NUMBER" });
                parameters.Add(new string[] { "item_seq_id", "NUMBER" });                
                List<string> SQLStatement = VTLSqlStatement.seq_id_declare_begin(parameters);
                //List<string> SQLStatement = VTLSqlStatement.seq_id_storeProcedure_begin(parameters);

                SQLStatement.AddRange(VTLSqlStatement.getDataStructureSQLStatement(dsd_ID, dsd_Agency, dsd_Version, dsd_names, "datastructure_seq_id"));

                foreach (ValueDomainInfoInDataStructure bs in dsd_componentList)
                {
                    SQLStatement.AddRange(VTLSqlStatement.getValueDomainInsertSQLStatement(bs.vd_id, bs.vd_agency, bs.vd_version, bs.names, ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE.String, bs.values, "valueDomain_seq_id", "main_value_domain_seq_id"));
                    SQLStatement.AddRange(VTLSqlStatement.getDataStructureComponentSQLStatement(bs.names, bs.role, bs.vd_id, "datastructure_seq_id", "main_value_domain_seq_id", "item_seq_id"));
              
                }

                SQLStatement.Add(VTLSqlStatement.closeProcedure);
                //SQLStatement.Add(VTLSqlStatement.closeProcedureInsert);
            
                string tm1p = String.Join("\n ", SQLStatement);
                OnLineCountEvent(new LineCountEventArgs(tm1p.Count()));

                _provider.ExecuteNonQueryMultiple(tm1p);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        #endregion

        protected void OnLineCountEvent(LineCountEventArgs e)
        {
            try
            {
                LineCountEvent(this, e);
            }
            catch (Exception)
            {
                
                throw;
            }
        } 

        #endregion


    }
}