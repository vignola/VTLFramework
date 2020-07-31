using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace VTL_Editor_PL.gui
{
    public partial class UserDefinedDataStructurePanel : UserControl
    {               
        public DataGridView dataStructureComponentGridControl 
        {
            get { return this.DataStructureComponentDataGridView; }
            set { this.DataStructureComponentDataGridView = value; }
        }

        public UserDefinedDataStructurePanel()
        {
            InitializeComponent();            
        }

        private void UserDefinedDataStructurePanel_Load(object sender, EventArgs e)
        {
            DataGridViewComboBoxColumn DataStructureRoleColumn = (DataGridViewComboBoxColumn)this.DataStructureComponentDataGridView.Columns["Role"];
            DataStructureRoleColumn.Items.Add("Identifier");
            DataStructureRoleColumn.Items.Add("Measure");
            DataStructureRoleColumn.Items.Add("Attribute");
            DataStructureRoleColumn.Items.Add("Viral attribute");
            DataStructureRoleColumn.FlatStyle = FlatStyle.Flat;


            DataGridViewButtonColumn DataStructureEditNameColumn = (DataGridViewButtonColumn)this.DataStructureComponentDataGridView.Columns["EditName"];
            DataStructureEditNameColumn.Text = "Edit name...";
            DataStructureEditNameColumn.UseColumnTextForButtonValue = true;
            //DataStructureComponentDataGridView.Rows[0].Cells[2].Value = "Edit name...";

            DataGridViewButtonColumn DataStructureValueDomainColumn = (DataGridViewButtonColumn)this.DataStructureComponentDataGridView.Columns["ValueDomainButton"];
            DataStructureValueDomainColumn.Text = "Get domain";
            DataStructureValueDomainColumn.UseColumnTextForButtonValue = true;


        }

        private void DataStructureComponentDataGridView_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            try
            {
                if (e.ColumnIndex == DataStructureComponentDataGridView.Columns["EditName"].Index)
                {
                    DataGridViewComboBoxCell descriptionList = (DataGridViewComboBoxCell)this.DataStructureComponentDataGridView.Rows[DataStructureComponentDataGridView.CurrentRow.Index].Cells[1];

                    frmArtefactDescription frmArtDescr = new frmArtefactDescription();
                    if (descriptionList.Items.Count > 0)
                    {
                        List<KeyValuePair<string, string>> tmpList = new List<KeyValuePair<string, string>>();

                        foreach (string str in descriptionList.Items)
                        {
                            string[] couple = str.Split(" - ".ToCharArray());
                            if (couple.Length > 0)
                            {
                                KeyValuePair<string, string> tmpCouple = new KeyValuePair<string, string>(couple[0], couple[3]);
                                tmpList.Add(tmpCouple);
                            }
                        }

                        frmArtDescr.Descriptions = tmpList;
                    }

                    frmArtDescr.ApplyArtefactCheckBoxEnabled(false);
                    bool applyWholeArtefact=((frmArtefactManager)this.Parent.FindForm()).ApplyLanguageToWholeArtefact;

                    frmArtDescr.ApplyToWholeArtifact = applyWholeArtefact;
                    string[] languagesWholeArtefact = ((frmArtefactManager)this.Parent.FindForm()).ArtefactLanguages;

                    if (applyWholeArtefact && languagesWholeArtefact != null && languagesWholeArtefact.Length > 0)
                        frmArtDescr.Languages = languagesWholeArtefact;

                    if (frmArtDescr.ShowDialog() == DialogResult.OK)
                    {
                        descriptionList.Items.Clear();
                        foreach (KeyValuePair<string, string> couple in frmArtDescr.Descriptions)
                        {
                            descriptionList.Items.Add(couple.Key + " - " + couple.Value);
                        }
                        DataStructureComponentDataGridView.Rows[DataStructureComponentDataGridView.CurrentRow.Index].Cells[1].Value = descriptionList.Items[0];
                    }
                }

                if (e.ColumnIndex == DataStructureComponentDataGridView.Columns["ValueDomainButton"].Index)
                {
                    frmSelectValueDomain frmValDom = new frmSelectValueDomain();
                    if (frmValDom.ShowDialog() == DialogResult.OK)
                    {
                        DataStructureComponentDataGridView.Rows[DataStructureComponentDataGridView.CurrentRow.Index].Cells[e.ColumnIndex-1].Value = frmValDom.SelectedValueDomain;                        
                    }
                }
            }
            catch (Exception ex)
            {
                throw;
            }
        }

        private void AddComponentButton_Click(object sender, EventArgs e)
        {
            try
            {
                int addedRowIndex = DataStructureComponentDataGridView.Rows.Add();
            }
            catch (Exception)
            {
                throw;
            }
        }

        private void RemoveComponentButton_Click(object sender, EventArgs e)
        {
            try
            {
                if (DataStructureComponentDataGridView.SelectedRows.Count > 0) 
                {
                    if (MessageBox.Show("Do you want to remove the selected row?", "Remove row", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes) 
                    {
                        DataStructureComponentDataGridView.Rows.Remove(DataStructureComponentDataGridView.SelectedRows[0]);
                    }
                }

            }
            catch (Exception)
            {
                throw;
            }
        }
    }
}
