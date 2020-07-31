
namespace ArtefactInfo.model
{
    public class LocalizedValue
    {
        public string lang { get; set; }
        public string value { get; set; }

        public LocalizedValue()
        {

        }
        public LocalizedValue(string language, string value)
        {
            this.lang = language;
            this.value = value;
        }
    }
}
