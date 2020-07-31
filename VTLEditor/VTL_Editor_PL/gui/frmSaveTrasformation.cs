using System;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using ArtefactInfo.model;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.net;

namespace VTL_Editor_PL.gui
{
    public partial class frmSaveTrasformation : Form
    {
        private BaseArtefactInfo[] _transfInfo;

        public string TrasformationName = null;
        public string AgencyID = null;
        public string Version = null;

        public frmSaveTrasformation()
        {
            InitializeComponent();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            try
            {
                Regex r = new Regex(@"[a-zA-Z0-9]", RegexOptions.IgnoreCase);
                if (!r.IsMatch(trasfTextBox.Text.Trim()))
                {
                    MessageBox.Show("Wrong ID format. Please use a SDMX ID format [A-Z, a-z, 0-9, _, -, $, @])", "Wrong format", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }

                string tmpString = CommonConstant.ToGlobalID(trasfTextBox.Text.Trim(), AgencyTextBox.Text.Trim(), VersionTextBox.Text.Trim());
                foreach (BaseArtefactInfo artInfo in _transfInfo) 
                {
                    if (artInfo.vtlId == tmpString)
                    {
                        MessageBox.Show("The Trasformation scheme :\n" +
                            trasfTextBox.Text.Trim() + " " + AgencyTextBox.Text.Trim() + " " + VersionTextBox.Text.Trim() + "\n" +
                            " is already present. Please insert a different ID, Agency or change the version.", "Already present", MessageBoxButtons.OK, MessageBoxIcon.Stop);
                        return;
                    }
                }

                r = new Regex(@"^(\d{1}\.?){1,2}\d{1}$");
                if (!r.IsMatch(VersionTextBox.Text.Trim()))
                {
                    MessageBox.Show("Wrong version format. Please use a correct version format tre numbers separated by a dot", "Wrong format", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }

                if (string.IsNullOrEmpty(AgencyTextBox.Text.Trim()))
                {
                    MessageBox.Show("The Angency value is empty. Please insert and agency ID", "Agency", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return;
                }

                TrasformationName = trasfTextBox.Text.Trim();
                AgencyID = AgencyTextBox.Text.Trim();
                Version = VersionTextBox.Text.Trim();

                this.DialogResult = DialogResult.OK;
                this.Close();
            }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        private void frmSaveTrasformation_Load(object sender, EventArgs e)
        {
            try
            {
                TrasformationName = null;
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                _transfInfo = VTL_service.GetTransformationList();
            }
            catch (Exception ex) 
            {
                throw new Exception(ex.Message);
            }
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
        }

       
    }
}
