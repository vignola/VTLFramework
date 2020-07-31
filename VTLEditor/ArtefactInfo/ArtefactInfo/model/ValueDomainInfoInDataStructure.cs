using System.Collections.Generic;

namespace ArtefactInfo.model
{
    public class ValueDomainInfoInDataStructure
    {
        public string vd_id;
        public string vd_agency;
        public string vd_version;
        public List<LocalizedValue> names;
        public List<BaseArtefactInfo> values;
        public string role;
    }
}
