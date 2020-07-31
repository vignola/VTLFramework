using System;
using System.Drawing;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;

namespace VTL_Editor_PL.classes.gui
{
    public partial class frmErrorDetails : Form
    {
        private string _errorMessage;
        private string _title;
        private string _shortError;

        public string ErrorMessage
        {
            get { return _errorMessage; }
            set { _errorMessage = value; }
        }

        public frmErrorDetails()
        {
            InitializeComponent();
        }

        private void CopyButton_Click(object sender, EventArgs e)
        {
            try
            {
                ErrorMessageTextBox.SelectAll();
                ErrorMessageTextBox.Copy();
                MessageBox.Show("Error message copied to the Clipboard");
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void DetailsButton_Click(object sender, EventArgs e)
        {
            if (this.Height == 139)
                this.Height = 473;
            else
                this.Height = 139;
            this.CenterToScreen();
        }

        private void frmErrorDetails_Load(object sender, EventArgs e)
        {
            string WSurl = "N/A";
            this.Height = 139;
            ErrorMessageTextBox.Text = _errorMessage;
            ParseErrorMessage();
            TitleLabel.Text = _title;
            SDescriptionLabel.Text = _shortError;

            try
            {
                //WSurl = CommonItem.CurrentSettings.getWebserviceInfoByID(CommonItem.CurrentSettings.SelectedWebServiceID).WebService_public_URL;
            }
            catch (Exception)
            { }

            wsLabel.Text = "Active web service: " + WSurl;

            this.CenterToScreen();
        }

        private void ParseErrorMessage()
        {
            _title = "Error";
            _shortError = "Generic error";

            if (_errorMessage.IndexOf("SOAP FAULT") > -1)
            {
                _title = "Web service response error";
                _shortError = "Soap fault error";
                int selstart = _errorMessage.IndexOf("<ErrorMessage>") + 14;
                if (selstart > -1)
                {
                    int selend = _errorMessage.IndexOf("</ErrorMessage>");
                    _shortError = _errorMessage.Substring(selstart, selend - selstart);
                }
            }

            if (_errorMessage.IndexOf("500") > -1)
            {
                _title = "Web service response error";
                _shortError = "Internal web service error";

                if (_errorMessage.IndexOf("SetCategorySchema") > -1)
                {
                    _title = "Internal web service error";
                    _shortError = "Web service response error: Categories cannot be correctly retrieved";
                }

                if (_errorMessage.IndexOf("SetDataFlows") > -1)
                {
                    _title = "Internal web service error";
                    _shortError = "Web service response error: Dataflow cannot be correctly retrieved";
                }
            }

            if (_errorMessage.IndexOf("Timeout") > -1)
            {
                _title = "Connection timeout";
                _shortError = "Web service response error";


                if (_errorMessage.IndexOf("SetCategorySchema") > -1)
                {
                    _shortError = "Web service response error: Categories cannot be correctly retrieved";
                }

                if (_errorMessage.IndexOf("SetDataFlows") > -1)
                {
                    _shortError = "Web service response error: Dataflow cannot be correctly retrieved";
                }
            }

            if (_errorMessage.IndexOf("Semantic Error") > -1)
            {
                _title = "Web service response error";
                _shortError = "The message retrieved from the web service is not correct: semantic error";
            }

            if (_errorMessage.IndexOf("Category tree cannot be loaded") > -1)
            {
                _title = "Web service response error";
                _shortError = "The category tree cannot be loaded.";
            }

            if (_errorMessage.IndexOf("Time interval definition error") > -1)
            {
                _title = "Time interval definition error";
                _shortError = "Start time greater than end time";
            }
        }

        private void CloseButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void CloseLabel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void CloseLabel_MouseHover(object sender, EventArgs e)
        {
            CloseLabel.ForeColor = Color.Red;
        }

        private void CloseLabel_MouseLeave(object sender, EventArgs e)
        {
            CloseLabel.ForeColor = Color.Gray;
        }

        private void CloseButton_Click_1(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
