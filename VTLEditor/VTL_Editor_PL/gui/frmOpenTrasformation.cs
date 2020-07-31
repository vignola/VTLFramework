using System;
using System.Windows.Forms;
using ArtefactInfo.model;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.net;

namespace VTL_Editor_PL.gui
{
    public partial class frmOpenTrasformation : Form
    {
        public string TrasformationSchemeID 
        {
            get { return (string)TrasDataGridView.SelectedRows[0].Cells[0].Value; }
        }

        public frmOpenTrasformation()
        {
            InitializeComponent();
        }

        private void frmOpenTrasformation_Load(object sender, EventArgs e)
        {
             try
            {
                TrasDataGridView.Rows.Clear();
                BaseArtefactInfo[] transList = getTrasformationList();
                if (transList.Length > 0) 
                { 
                    foreach (BaseArtefactInfo bsArtefact in getTrasformationList())
                    {
                        string[] sdmxGlobal=CommonConstant.splitGlobalID(bsArtefact.vtlId);
                        TrasDataGridView.Rows.Add(bsArtefact.vtlId, sdmxGlobal[0], sdmxGlobal[1], sdmxGlobal[2]);
                    }
                }
                ToolTip tp = new ToolTip();
                tp.SetToolTip(RemoveButton, "Remove tranformation scheme");
            }
             catch (Exception ex)
             {
                 CommonItem.ErrManger.ErrorManagement(ex, false, this);              
             }
        }

        private BaseArtefactInfo[] getTrasformationList()
        {
            try
            {
                BaseArtefactInfo[] tsList;
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                tsList = VTL_service.GetTransformationList();
                return tsList;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return null;
            }
        }

        private void CloseButton_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
        }

        private void OpenButton_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
        }
       

        private void RemoveButton_Click(object sender, EventArgs e)
        {
            try
            {
                if (TrasDataGridView.SelectedRows.Count > 0)
                {
                    if (MessageBox.Show("Do you want to remove the transformation scheme: " + TrasDataGridView.SelectedRows[0].Cells[0].Value , "Remove", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                    {
                        VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                        VTL_service.RemoveTransformation((string)TrasDataGridView.SelectedRows[0].Cells[0].Value);
                        TrasDataGridView.Rows.RemoveAt(TrasDataGridView.SelectedRows[0].Index);
                        MessageBox.Show("Transformation removed");
                    }
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void TrasDataGridView_CellContentDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            this.DialogResult = DialogResult.OK;
        }
    }
}
