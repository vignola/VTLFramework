using System;
using System.Windows.Forms;
using ArtefactInfo.model;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.net;

namespace VTL_Editor_PL.gui
{
    public partial class frmSelectValueDomain : Form
    {
        public ValueDomainInfo SelectedValueDomain { get; set; }

        public frmSelectValueDomain()
        {
            InitializeComponent();
        }

        private void CancelButton_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
            this.Close();
        }

        private void frmSelectValueDomain_Load(object sender, EventArgs e)
        {
        
            try
            {
                CommonItem.WaitOn();
                BaseArtefactInfo[] vlList;

                SelectedValueDomain = null;

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                vlList = VTL_service.GetValueDomains();


                ValuDomainListBox.Items.Clear();

                if (vlList == null)
                {
                    MessageBox.Show("There are not ValueDomain available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                foreach (BaseArtefactInfo aInfo in vlList)
                {
                    ValuDomainListBox.Items.Add(aInfo);                                                            
                }

                CommonItem.WaitOff();
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        
        }

        private void OkButton_Click(object sender, EventArgs e)
        {
            SelectedValueDomain = (ValueDomainInfo)ValuDomainListBox.SelectedItem;
            DialogResult = DialogResult.OK;
            this.Close();
        }
       
    }
}
