using System;
using System.Windows.Forms;
using FastColoredTextBoxNS;

namespace VTL_Editor_PL.gui
{
    public partial class frmSQLViewer : Form
    {
        private FastColoredTextBox _sqlTextBox;
        private string _sqlCode="";

        public string SQLCode 
        {
            get {              
                if (_sqlTextBox!= null)
                    return _sqlTextBox.Text;
                else
                    return "";                
                }

            set { 
                if (_sqlTextBox != null) _sqlTextBox.Text = value;
                _sqlCode = value;
                }
        }

        public frmSQLViewer()
        {
            InitializeComponent();
        }

        private void frmSQLViewer_Load(object sender, EventArgs e)
        {
             _sqlTextBox = new FastColoredTextBox();
             _sqlTextBox.Top = 5;
             _sqlTextBox.Left = 5;
             _sqlTextBox.Height = 470;
             _sqlTextBox.Width = 980;
            _sqlTextBox.Anchor= (AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Top | AnchorStyles.Right);

            _sqlTextBox.Language = Language.SQL;

            _sqlTextBox.Text = _sqlCode;
             this.Controls.Add(_sqlTextBox);
        }

        private void CloseButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void SaveButton_Click(object sender, EventArgs e)
        {
            try
            {
                saveFileDialog1.Filter = "SQL files (*.sql)|*.sql|All files (*.*)|*.*";
                saveFileDialog1.FilterIndex = 1;
                if (saveFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    if (!String.IsNullOrEmpty(saveFileDialog1.FileName))
                    {
                        System.IO.File.WriteAllText(saveFileDialog1.FileName, _sqlTextBox.Text);
                        MessageBox.Show("SQL file saved", "File saved", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                }
            }
            catch (Exception) { throw; }
        }

        private void CopyToButton_Click(object sender, EventArgs e)
        {
            Clipboard.SetText(_sqlTextBox.Text);
            MessageBox.Show("SQL statements copied to the clipboard", "Copy to the clipboard", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
    }
}
