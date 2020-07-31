using System;
using System.Drawing;
using System.Windows.Forms;

namespace VTL_Editor_PL.gui
{
    public partial class UserDefinedValueDomainPanel : UserControl
    {
        public DataGridView valueDomainDataGridControl 
        {
            get { return this.valueDomainDataGrid; }
            set { this.valueDomainDataGrid = value; }
        }

        public bool IsDescribed
        {
            get { return this.DescribedRadioButton.Checked; }
            set 
            {
                if (value)
                {
                    this.DescribedRadioButton.Checked = true;
                    this.EnumeratedRadioButton.Checked = false;
                }
                else 
                {
                    this.DescribedRadioButton.Checked = false;
                    this.EnumeratedRadioButton.Checked = true;
                }
            }
        }      

        public string describedCriterion 
        {
            get { return this.DescribedCriterionTextBox.Text; }
            set { this.DescribedCriterionTextBox.Text = value; }
        }
      
        public UserDefinedValueDomainPanel()
        {
            InitializeComponent();
        }

        private void UserDefinedValueDomainPanel_Load(object sender, EventArgs e)
        {
            DataTypeValueDomainComboBox.DataSource = Enum.GetValues(typeof(ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE));
            DataTypeValueDomainComboBox.DropDownStyle = ComboBoxStyle.DropDownList;
            DataTypeValueDomainComboBox.SelectedIndex = 0;
            DescribedRadioButton.Checked = true;
            EnumeratedPanel.Enabled = false;
            EnumeratedPanel.BackColor = Color.Gray;
            ToolTip tpAddButton = new ToolTip();
            tpAddButton.SetToolTip(AddLanguageButton, "Add language column");
            ToolTip tpRemoveButton = new ToolTip();
            tpRemoveButton.SetToolTip(RemoveLanguageButton, "Remove language column"); 
        }

        private void EnumeratedRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            try
            {
                if (EnumeratedRadioButton.Checked)
                {
                    DescribedPanel.Enabled = false;
                    DescribedPanel.BackColor = Color.Gray;
                    EnumeratedPanel.Enabled = true;
                    EnumeratedPanel.BackColor = Color.SteelBlue;
                }
                else
                {
                    DescribedPanel.Enabled = true;
                    DescribedPanel.BackColor = Color.SteelBlue;
                    EnumeratedPanel.Enabled = false;
                    EnumeratedPanel.BackColor = Color.Gray;
                }
            }
            catch (Exception)
            {
                throw;
            }

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
                        int col = this.valueDomainDataGrid.Columns.Add(columnName, columnName);
                    }
                }
            }
            catch (Exception ex)
            {
                throw;
            }
        }

        private void RemoveLanguageButton_Click(object sender, EventArgs e)
        {
            try
            {                

                if (this.valueDomainDataGrid.SelectedCells.Count == 0)
                {
                    MessageBox.Show("Please, select at least one column.", "Select", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                int index = this.valueDomainDataGrid.SelectedCells[0].ColumnIndex;

                if (MessageBox.Show("Do you want to remove the " + this.valueDomainDataGrid.Columns[index].HeaderText + " column?", "Remove", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    if (this.valueDomainDataGrid.Columns.Count <= 2)
                    {
                        MessageBox.Show("The description requires at least one language. It is not possible to remove all the languages!", "Cannot remove last language", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                        return;
                    }
                    else
                    {
                        this.valueDomainDataGrid.Columns.Remove(this.valueDomainDataGrid.Columns[index]);
                    }
                }
            }
            catch (Exception ex)
            {
                throw;
            }
        }

        public void setComboDataType(string vtl_data_type)
        {
            try
            {
                DataTypeValueDomainComboBox.SelectedIndex = DataTypeValueDomainComboBox.FindStringExact(vtl_data_type);
            }
            catch (Exception ex)
            {
                throw;
            }
        }

        public string getDataType(){
            try
            {
                return DataTypeValueDomainComboBox.SelectedValue.ToString();
             }
            catch (Exception ex)
            {
                throw;
            }
        }
    }
}
