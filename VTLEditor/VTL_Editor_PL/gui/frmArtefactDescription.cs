using System;
using System.Collections.Generic;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;

namespace VTL_Editor_PL.gui
{
    public partial class frmArtefactDescription : Form
    {   
        public bool ApplyToWholeArtifact
        {
            get { return this.ApplyLanguagesCheckBox.Checked; }
            set { ApplyLanguagesCheckBox.Checked = value; }
        }

        public string[] Languages 
        {
            
            get 
            {
                try
                {
                    List<String> tmpList = new List<string>();

                    for (int i = 0; i < DescriptionsDataGrid.Columns.Count; i++)
                    { 
                        tmpList.Add(DescriptionsDataGrid.Columns[i].HeaderText);
                    }
                    return tmpList.ToArray();
                }
                catch (Exception ex)
                {
                    CommonItem.ErrManger.ErrorManagement(ex, false, this);
                    return null;
                }
            }

            set 
            {
                try
                {
                    DescriptionsDataGrid.Columns.Clear();
                    foreach (string str in value) 
                    {
                        DescriptionsDataGrid.Columns.Add(str, str);
                    }
                }
                catch (Exception ex)
                {
                    CommonItem.ErrManger.ErrorManagement(ex, false, this);
                }
            }
        }

        public List<KeyValuePair<string, string>> Descriptions 
        {
            get 
            {
                try
                {
                    List<KeyValuePair<string, string>> tmpList = new List<KeyValuePair<string, string>>();
                    for (int i = 0; i < DescriptionsDataGrid.Columns.Count; i++) 
                    {
                        string lang = DescriptionsDataGrid.Columns[i].HeaderText;
                        string descr = DescriptionsDataGrid.Rows[0].Cells[i].Value.ToString();
                        KeyValuePair<string, string> tmpValue = new KeyValuePair<string, string>(lang, descr);
                        tmpList.Add(tmpValue);
                    }
                    return tmpList;
                }
                catch (Exception ex)
                {
                    CommonItem.ErrManger.ErrorManagement(ex, false, this);
                    return null;
                }
            }

            set 
            {
                try
                {                    
                    DescriptionsDataGrid.Rows.Clear();
                    DescriptionsDataGrid.Columns.Clear();
                
                    foreach (KeyValuePair<string, string> couple in value) 
                    {
                        int colIndex=DescriptionsDataGrid.Columns.Add(couple.Key, couple.Key);                    
                        if (DescriptionsDataGrid.Rows.Count<1) 
                            DescriptionsDataGrid.Rows.Add();

                        DescriptionsDataGrid.Rows[0].Cells[colIndex].Value = couple.Value;                                     
                    }
                }
                catch (Exception ex)
                {
                    CommonItem.ErrManger.ErrorManagement(ex, false, this);
                }
            }
        }

        public frmArtefactDescription()
        {
            InitializeComponent();
        }

        public void ApplyArtefactCheckBoxEnabled(bool activate)
        {
            ApplyLanguagesCheckBox.Enabled = activate ? true : false;
        }

        private void AddLanguageButton_Click(object sender, EventArgs e)
        {
            try
            {
                frmAddText frmAddLanguageID = new frmAddText();
                if (frmAddLanguageID.ShowDialog() == DialogResult.OK)
                {
                    string columnName = frmAddLanguageID.AddedText;
                    if (columnName != "")
                    {
                        int col = DescriptionsDataGrid.Columns.Add(columnName,columnName);                    
                    }
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void CancelButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void frmArtefactDescription_Load(object sender, EventArgs e)
        {
            try
            {
                if (DescriptionsDataGrid.RowCount==0) DescriptionsDataGrid.Rows.Add();
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void SaveButton_Click(object sender, EventArgs e)
        {
            if (Descriptions.Count > 0)
                this.DialogResult = DialogResult.OK;
            else
                this.DialogResult = DialogResult.Cancel;
            this.Close();
        }

        private void RemoveLanguageButton_Click(object sender, EventArgs e)
        {
            try
            {
                if (DescriptionsDataGrid.SelectedCells.Count == 0) 
                {
                    MessageBox.Show("Please, select at least one column.", "Select", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                int index=DescriptionsDataGrid.SelectedCells[0].ColumnIndex;

                if (MessageBox.Show("Do you want to remove the " + DescriptionsDataGrid.Columns[index].HeaderText + " column?", "Remove", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    if (DescriptionsDataGrid.Columns.Count <= 1)
                    {
                        MessageBox.Show("The description requires at least one language. It is not possible to remove all the languages!", "Cannot remove last language", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        return;
                    }
                    else
                    {
                        DescriptionsDataGrid.Columns.Remove(DescriptionsDataGrid.Columns[index]);
                    }
                }
            }
                catch (Exception ex)
                {
                    CommonItem.ErrManger.ErrorManagement(ex, false, this);
                }
        }
    }
}
