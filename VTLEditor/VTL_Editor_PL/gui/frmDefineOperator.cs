using System;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;

namespace VTL_Editor_PL.gui
{
    public partial class frmDefineOperator : Form
    {
        public frmDefineOperator()
        {
            InitializeComponent();
        }

        private void frmDefineOperator_Load(object sender, EventArgs e)
        {
            returnTypeComboBox.Items.Add("string");
            returnTypeComboBox.Items.Add("number");
            returnTypeComboBox.Items.Add("integer");
            returnTypeComboBox.Items.Add("time");
            returnTypeComboBox.Items.Add("date");
            returnTypeComboBox.Items.Add("time_period");
            returnTypeComboBox.Items.Add("duration");
            returnTypeComboBox.Items.Add("boolean");

            DataGridViewComboBoxColumn DataStructureTypeColumn = (DataGridViewComboBoxColumn)this.parametersDataGridView.Columns["ParameterType"];            
            DataStructureTypeColumn.Items.Add("string");
            DataStructureTypeColumn.Items.Add("number");
            DataStructureTypeColumn.Items.Add("integer");
            DataStructureTypeColumn.Items.Add("time");
            DataStructureTypeColumn.Items.Add("date");
            DataStructureTypeColumn.Items.Add("time_period");
            DataStructureTypeColumn.Items.Add("duration");
            DataStructureTypeColumn.Items.Add("boolean");
            DataStructureTypeColumn.FlatStyle= FlatStyle.Flat;
            DataGridViewComboBoxCell cell=new DataGridViewComboBoxCell();
            cell.DisplayStyle = DataGridViewComboBoxDisplayStyle.ComboBox;
            DataStructureTypeColumn.CellTemplate= cell;
        }

        public string getDefineOperatorStatement() 
        {   try
            {
                string operatorName = this.nameTextBox.Text.Trim() == "" ? "<operatorName>" : this.nameTextBox.Text.Trim();
                string operatorBody = this.bodyTextBox.Text.Trim() == "" ? "<operatorBody>" : this.bodyTextBox.Text.Trim();
                string outputType = this.returnTypeComboBox.Text.Trim() == "" ? "\treturns <outputType>\n" : "\treturns " + this.returnTypeComboBox.Text.Trim() + "\n";
                string parameters = "";

                foreach (DataGridViewRow rw in this.parametersDataGridView.Rows) 
                {
                    if (rw.Cells[0].Value != null && rw.Cells[1].Value != null)
                        if (rw.Cells[0].Value.ToString().Trim() != "" && rw.Cells[1].Value.ToString().Trim() != "")
                        parameters += rw.Cells[0].Value.ToString().Trim() + " " + rw.Cells[1].Value.ToString().Trim() + ",";
                }
                if (parameters.Length > 0) parameters = parameters.Substring(0, parameters.Length - 1);

                string statement = "define operator " + operatorName +" ( "+ parameters +" )\n" +            
                     outputType +
                     "\tis " + operatorBody + " \n" +
                "end operator;";

                return statement;
             }
                catch (Exception ex)
                {
                    CommonItem.ErrManger.ErrorManagement(ex, false, this);
                    return null;
                }
        }

        private void OkButton_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
        }

        private void parametersDataGridView_RowsAdded(object sender, DataGridViewRowsAddedEventArgs e)
        {            
            DataGridViewComboBoxColumn DataStructureTypeColumn = (DataGridViewComboBoxColumn)this.parametersDataGridView.Columns["ParameterType"];
            DataStructureTypeColumn.Items.Add("string");
            DataStructureTypeColumn.Items.Add("number");
            DataStructureTypeColumn.Items.Add("integer");
            DataStructureTypeColumn.Items.Add("time");
            DataStructureTypeColumn.Items.Add("date");
            DataStructureTypeColumn.Items.Add("time_period");
            DataStructureTypeColumn.Items.Add("duration");
            DataStructureTypeColumn.Items.Add("boolean");
            DataStructureTypeColumn.FlatStyle = FlatStyle.Flat;
            DataGridViewComboBoxCell cell = new DataGridViewComboBoxCell();
            cell.DisplayStyle = DataGridViewComboBoxDisplayStyle.ComboBox;
            DataStructureTypeColumn.CellTemplate = cell;
        }
        
    }
}
