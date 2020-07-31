using System;
using System.Windows.Forms;

namespace VTL_Editor_PL.gui
{
    public partial class frmSaveOperator : Form
    {
        
        public int OperatorType 
        {
            get 
            {
                if (UserDefOpRadioButton.Checked) return 0;
                if (DatRulesetRadioButton.Checked) return 1;
                if (HierarchicalRadioButton.Checked) return 2;
                return -1;
            }
            set 
            {
                switch (value) 
                { 
                    case 0:
                        UserDefOpRadioButton.Checked=true;
                        break;
                    case 1:
                        DatRulesetRadioButton.Checked=true;
                        break;
                    case 2:
                        HierarchicalRadioButton.Checked=true;
                        break;                
                }
            }
        }

        public frmSaveOperator(string OperatorName)
        {            
            InitializeComponent();
            OperaratorNameLabel.Text = OperatorName;
        }

        private void SaveButton_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
            this.Close();
        }

        private void CancelButton_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
            this.Close();
        }

        private void frmSaveOperator_Load(object sender, EventArgs e)
        {

        }
    }
}
