
namespace ArtefactInfo.model
{
    public class ValueDomainInfo : BaseArtefactInfo
    {
        public bool isEnumerated { get; set; }
        public string data_type { get; set; }
        public string value_restriction { get; set; }

        public ValueDomainInfo()
            : base()
        {
            isEnumerated = false;
        }
    }
}
