using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace VTL_Editor_PL.gui
{
    public partial class frmStartMessageBoxError : Form
    {
        private STARTMESSAGEBOXERROR_RESULT _result;

        public enum STARTMESSAGEBOXERROR_RESULT 
        { 
            NONE,
            OPENSETTINGS,
            CONTINUE,
            EXIT
        }

        public STARTMESSAGEBOXERROR_RESULT StartMessageBoxErorrResult 
        {
            get { return _result; }
        }

        public bool ContinueEnabled
        {
            get 
            {
                return ContinueButton.Enabled;
            }
            set 
            {
                ContinueButton.Enabled = false;
            }
        }

        public string[] ErrorMessages
        {
            get {
                List<string> messages = new List<string>();
                foreach (string line in ErrorMessagesListBox.Items) 
                {
                    messages.Add(line);
                }
                return messages.ToArray();
            }
            set {
                ErrorMessagesListBox.Items.Clear();
                foreach (string line in value)
                {
                    ErrorMessagesListBox.Items.Add(line);
                }
            }
        }

        public frmStartMessageBoxError()
        {
            InitializeComponent();
        }

        private void frmStartMessageBoxError_Load(object sender, EventArgs e)
        {
            _result=STARTMESSAGEBOXERROR_RESULT.NONE;            
        }

        private void SettingsButton_Click(object sender, EventArgs e)
        {
            _result = STARTMESSAGEBOXERROR_RESULT.OPENSETTINGS;
            this.Close();
        }

        private void ContinueButton_Click(object sender, EventArgs e)
        {            
            _result = STARTMESSAGEBOXERROR_RESULT.CONTINUE;
            this.Close();
        }

        private void CloseButton_Click(object sender, EventArgs e)
        {
            _result = STARTMESSAGEBOXERROR_RESULT.EXIT;
            this.Close();
        }
        
    }
}
