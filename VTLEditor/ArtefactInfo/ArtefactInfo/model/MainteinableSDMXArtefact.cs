using System;

namespace ArtefactInfo.model
{
    public class MainteinableSDMXArtefact
    {
        private string _agencyID;
        private string _version;
        private string _Id;


        public string ID 
        { 
            get { return _Id;}
            set { _Id=value;}
        }

        public string AgencyID
        {
            get { return _agencyID; }
            set { _agencyID = value; }
        }

        public string Version 
        {
            get { return _version; }
            set { _version = value; }
        }

        public MainteinableSDMXArtefact() { }

        public MainteinableSDMXArtefact(string ID, string Agency, string Version) 
        {
            _agencyID = Agency;
            _Id = ID;
            _version = Version;
        }

        public static MainteinableSDMXArtefact CreateKeyFamilyArtefact(string ID, string AgencyID, string Version)
        {
            try
            {
                return new MainteinableSDMXArtefact(ID, AgencyID, Version);
            }
            catch (Exception ex)
            {
                throw new Exception("[ExcelGetSDMXData.classes.Controller.DataLoadingController.CreateKeyFamilyArtefact]" + ex.Message);
            }
        }
    }
}
