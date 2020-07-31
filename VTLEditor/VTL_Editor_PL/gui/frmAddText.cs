using System;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;

namespace VTL_Editor_PL.gui
{
    public partial class frmAddText : Form
    {
        public String AddedText
        {
            get { return MainTextBox.Text; }
            set { MainTextBox.Text = value; }
        }

        public frmAddText()
        {
            InitializeComponent();
        }

        private void frmAddText_Load(object sender, EventArgs e)
        {
            MainTextBox.Clear();
        }

        private void OkButton_Click(object sender, EventArgs e)
        {
            try
            {
                if (MainTextBox.Text.Trim()==""){
                    MessageBox.Show("The text cannot be empty. Plase, fill the text box", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }
                this.DialogResult = DialogResult.OK;          
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void CancelButton_Click(object sender, EventArgs e)
        {
            try
            {
                MainTextBox.Clear();
                this.DialogResult = DialogResult.No;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }
    }
}
