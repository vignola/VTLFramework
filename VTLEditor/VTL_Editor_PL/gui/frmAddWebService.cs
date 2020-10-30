using System;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.config;
using VTL_Editor_PL.classes.tool;

namespace VTL_Editor_PL.gui
{
    public partial class frmAddWebService : Form
    {
        private MainApplicationSettings.WebServiceInfo _wsInfo;
        private bool _modify = false;        

        public MainApplicationSettings.WebServiceInfo WSInfo 
        {
            get { return _wsInfo; }
            set {_wsInfo=value; }
        }
        
        public bool Modify 
        {
            get { return _modify; }
            set { _modify = value; }
        }

        public frmAddWebService()
        {
            InitializeComponent();
        }

        public bool DetailImplementationVisible
        {
            get { return this.DetailImplementationPanel.Visible; }
            set { DetailImplementationPanel.Visible = value; }

        }

        public bool TestConnectionVisible
        {
            get { return this.TestConnectionButton.Visible; }
            set { TestConnectionButton.Visible = value; }
        }

        private void frmAddWebService_Load(object sender, EventArgs e)
        {            
            if (_modify)
            {
                
                WebServiceNameTextBox.Text = _wsInfo.WebService_Name;
                WebServiceDescriptionTextBox.Text=_wsInfo.WebService_Description;
                WebServiceURLtextBox.Text = _wsInfo.WebService_public_URL;
                WebServiceUserNameTextBox.Text=_wsInfo.WebService_UserName;
                WebServicePasswordTextBox.Text=_wsInfo.WebService_Password ;
                if (_wsInfo.WebService_ReturnDetail == MainApplicationSettings.WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.Full) 
                {
                    FullRadioButton.Checked = true;
                } 
                else 
                {
                    CompleteStubRadioButton.Checked = true;
                }                
                
                this.Text = "Update WebService";
                WebServiceNameTextBox.ReadOnly = true;                
                //WebServiceURLtextBox.ReadOnly = true;
                WebServiceNameTextBox.Enabled = false;
                //WebServiceURLtextBox.Enabled = false;
                
           }
            else
            {
                
                this.Text = "Add WebService";
                WebServiceNameTextBox.ReadOnly = false;
                //WebServiceURLtextBox.ReadOnly = false;
                WebServiceNameTextBox.Enabled = true;
                //WebServiceURLtextBox.Enabled = true;

                FullRadioButton.Checked = true;

                WebServiceNameTextBox.Clear();
                WebServiceDescriptionTextBox.Clear();
                WebServiceURLtextBox.Text = "http://";
                WebServiceUserNameTextBox.Clear();
                WebServicePasswordTextBox.Clear();                
            }
        }

        private void OkButton_Click(object sender, EventArgs e)
        {
            try
            {
                
                    if (String.IsNullOrEmpty(WebServiceNameTextBox.Text) || String.IsNullOrEmpty(WebServiceURLtextBox.Text))
                    {
                        MessageBox.Show("Please, enter at least a webservice name and a webservice URL (including http:// or https://)", "Information", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        this.DialogResult = DialogResult.None;
                        return;
                    }

 
                    if (!isValidUrl(WebServiceURLtextBox.Text))
                    {
                        MessageBox.Show("The url of the web service is not correct. Please enter a correct URL (including http:// or https://)", "Information", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        this.DialogResult = DialogResult.None;
                        return;
                    }


                    WebServiceURLtextBox.Text = (WebServiceURLtextBox.Text).Replace('\\', '/');
                    _wsInfo = new MainApplicationSettings.WebServiceInfo(WebServiceNameTextBox.Text, WebServiceURLtextBox.Text);
                    _wsInfo.WebService_Description = WebServiceDescriptionTextBox.Text;
                    _wsInfo.WebService_UserName = WebServiceUserNameTextBox.Text;
                    _wsInfo.WebService_Password = WebServicePasswordTextBox.Text;
                    _wsInfo.WebService_ReturnDetail = FullRadioButton.Checked ? MainApplicationSettings.WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.Full : ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo.RETURN_DETAIL_IMPLEMENTATION.CompleteStub;
                    

                this.Close();
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void TestConnectionButton_Click(object sender, EventArgs e)
        {
             try
            {
                string UrlToTest = WebServiceURLtextBox.Text;

                if (RemoteFileExists.Check(UrlToTest, true))                    
                    MessageBox.Show("Connection succeded.");
                else
                    if (RemoteFileExists.Check(UrlToTest, false))
                            MessageBox.Show("Connection succeded.");
                else
                        MessageBox.Show("Connection failed. Insert a correct web service url","Connection error",MessageBoxButtons.OK,MessageBoxIcon.Error);
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

        private bool isValidUrl(string url)
        {
            string pattern = @"^((http|https)?:\/\/)";
            //string pattern = @"(http(s)?:\/\/)?([\w-]+\.)+[\w-]+(/[\w- ;,./?%&=]*)?";
            Regex reg = new Regex(pattern, RegexOptions.Compiled | RegexOptions.IgnoreCase);

            return reg.IsMatch(url);
            //return true;
        }   
    }
}

