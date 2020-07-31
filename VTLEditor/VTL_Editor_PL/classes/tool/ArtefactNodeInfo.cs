using ArtefactInfo.model;

namespace VTL_Editor_PL.classes.tool
{
    class ArtefactNodeInfo
    {
        public enum ArtefactType
        {
            DataStructures,
            DataFlows,
            ValueDomains,
            ValueDomainSubsets,
            DataStructureComponents
        }

        public string webServiceID;
        public string dataFlowID;
        public ArtefactType artType;
        public BaseArtefactInfo artefactInfo;
        public BaseComponentInfo artefactComponentInfo;

        public ArtefactNodeInfo(string WsId, BaseArtefactInfo ArtInfo, ArtefactType artTypeInfo) 
        { 
            webServiceID=WsId;
            artType=artTypeInfo;
            artefactInfo=ArtInfo;
            artefactComponentInfo = null;
            dataFlowID = null;
        }

        public ArtefactNodeInfo(string WsId, BaseComponentInfo ArtComponentInfo, ArtefactType artTypeInfo)
        {
            webServiceID = WsId;
            artType = artTypeInfo;
            artefactInfo = null;
            artefactComponentInfo = ArtComponentInfo;
            dataFlowID = null;
        }

        public ArtefactNodeInfo(string WsId, BaseComponentInfo ArtComponentInfo, ArtefactType artTypeInfo, string DataFlowID)
        {
            webServiceID = WsId;
            artType = artTypeInfo;
            artefactInfo = null;
            artefactComponentInfo = ArtComponentInfo;
            dataFlowID = DataFlowID;
        }
    }
}
