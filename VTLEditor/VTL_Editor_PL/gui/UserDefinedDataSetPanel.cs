using System;
using System.Windows.Forms;
using ArtefactInfo.model;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.net;

namespace VTL_Editor_PL.gui
{
    public partial class UserDefinedDataSetPanel : UserControl
    {
        public DataGridView DSDdataGridViewControl 
        {
            get { return this.DSDdataGridView; }
            set { this.DSDdataGridView = value; }
        }

        public UserDefinedDataSetPanel()
        {
            InitializeComponent();
        }

        private void UserDefinedDataSetPanel_Load(object sender, EventArgs e)
        {
            try
            {
                FillDataStructureForDataset();
            }
            catch (Exception)
            {
                throw;
            }
        }

        private void FillDataStructureForDataset()
        {
            try
            {
                CommonItem.WaitOn();
                BaseArtefactInfo[] dsList;

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                dsList = VTL_service.GetDataStructures();                

                if (dsList == null)
                {
                    MessageBox.Show("There are not DataStructures available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                DSDdataGridViewControl.Rows.Clear();

                foreach (BaseArtefactInfo aInfo in dsList)
                {
                    int rw = DSDdataGridViewControl.Rows.Add();
                    DSDdataGridViewControl.Rows[rw].Cells[0].Value = aInfo.vtlId;
                    DSDdataGridViewControl.Rows[rw].Cells[1].Value = aInfo.name[0].value;
                }
                CommonItem.WaitOff();

            }
            catch (Exception)
            {
                throw;
            }
        }
    }
}
