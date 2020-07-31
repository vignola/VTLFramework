

namespace ArtefactInfo.model
{
    public class ValueDomainSubsetInfo : BaseArtefactInfo
    {
        public bool isEnumerated { get; set; }
        public string set_criterion_id { get; set; }
        public string vd_id_ref { get; set; }        

        public ValueDomainSubsetInfo()
            : base()
        {
            isEnumerated = false;
        }
    }
}
