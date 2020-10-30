using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Reflection;
using System.Windows.Forms;
using ApplicationSettings.classes.common;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.config;
using VTL_Editor_PL.classes.net;
using VTL_Editor_PL.classes.tool;
using VTL_Editor_PL.gui;

namespace VTL_Editor_PL
{
    public partial class frmStart : Form
    {
        private int _millisecondDelay = 500;
        private bool _error = false;
        private List<string> _errorMessage;

        public frmStart()
        {
            InitializeComponent();
        }

        private void frmSplashScreen_Load(object sender, EventArgs e)
        {
            try{
                backgroundLoader.WorkerReportsProgress = true;
                backgroundLoader.WorkerSupportsCancellation = true;
                backgroundLoader.RunWorkerAsync();
                backgroundLoader.ProgressChanged += backgroundLoader_ProgressChanged;
                backgroundLoader.RunWorkerCompleted +=backgroundLoader_RunWorkerCompleted;
                
                _errorMessage = new List<string>();
                VersionLabel.Text = "Ver. " + Assembly.GetExecutingAssembly().GetName().Version.ToString();
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }            
        }

        private void LoadingStuff() 
        {
            try
            {
                CommonItem.CurrentSettings = new MainApplicationSettings();
                CommonItem.ErrManger = new classes.tool.ErrorAndLogManager();
                List<CommonConst.Loading_Status> settingResult = CommonItem.CurrentSettings.LoadSettings(true);

                if (settingResult[0] == CommonConst.Loading_Status.NOT_FOUND)
                {
                    _errorMessage.Add(" - Settings not found, please create them through the Settings module and restart the application! \n \n" + settingResult[0].ToString());
                    _error = true;
                    return;
                }

                //LoadSettings test
                //-----------------------------------------------------------------------------
                System.Threading.Thread.Sleep(_millisecondDelay);
                backgroundLoader.ReportProgress(1);
                if (settingResult[0] != CommonConst.Loading_Status.LOADED)
                {
                    string resultList="";
                    foreach (CommonConst.Loading_Status sr in settingResult)
                    {
                        resultList += sr + "  ";
                    }
                    _errorMessage.Add("Some settings are missed or not correct. They can cause malfunctions. \r\n Please fix them through the Settings module and restart the application! [" + resultList + "]");
                    _error = true;
                }

                //Interaction Url test
                //-----------------------------------------------------------------------------
                System.Threading.Thread.Sleep(_millisecondDelay);
                backgroundLoader.ReportProgress(2);
                if (CommonItem.CurrentSettings.InteractionWebService != null)
                {                    
                    if (settingResult.Where(err => err == CommonConst.Loading_Status.INTERACTION_WS_LOADED).Count() >0)
                    { 
                        if (!RemoteFileExists.Check(CommonItem.CurrentSettings.InteractionWebService.WebService_clear_URL, true))
                        {
                            _errorMessage.Add(" - Connection to Interaction web service failed.");
                            _error = true;
                        }
                        else 
                        {                 
                            //Interaction DB test
                            //-----------------------------------------------------------------------------
                            VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);

                            System.Threading.Thread.Sleep(_millisecondDelay);
                            backgroundLoader.ReportProgress(3);
                            if (!VTL_service.TestDatabaseConnection())
                            {
                                _errorMessage.Add(" - VTL Database connection from Interaction web service failed.");
                                _error = true;
                            }
                        }
                    }
                }
                //Validation Url test
                //-----------------------------------------------------------------------------
                System.Threading.Thread.Sleep(_millisecondDelay);
                backgroundLoader.ReportProgress(4);
                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SYNTACTIC_VALIDATION_WEBMETHOD);
                Dictionary<string, string> args = new Dictionary<string, string>();

                try
                {
                    string result = RestWS.SendPostRequest("ds1:=length(1);");
                }
                catch (Exception ex) 
                {
                    _errorMessage.Add(" - Connection to Validation web service failed. [" + ex.Message +"]");
                    _error = true;
                }
                                
                //-----------------------------------------------------------------------------

                //Metadata Url test
                //-----------------------------------------------------------------------------          
                System.Threading.Thread.Sleep(_millisecondDelay);
                backgroundLoader.ReportProgress(5);
                if (CommonItem.CurrentSettings.WebServices.Count > 0)
                {

                    ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo ws = CommonItem.CurrentSettings.WebServices[0];
                    if (!RemoteFileExists.Check(ws.WebService_clear_URL, true))
                    {
                        _errorMessage.Add(" - Connection to SDMX Metadata web service failed.");
                        _error = true;
                    }
                }
                //-----------------------------------------------------------------------------                

            }
            catch (Exception ex)
            {
                _error = true;
                if (_errorMessage.Count() == 0) _errorMessage.Add(" - " + ex.Message);
            }            
        }

        private void backgroundLoader_DoWork(object sender, DoWorkEventArgs e)
        {
            try
            {
             LoadingStuff();
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }            
        }

        private void backgroundLoader_ProgressChanged(object sender, ProgressChangedEventArgs e)
        {
            try{
                string labelText=null;
                switch (e.ProgressPercentage) 
                { 
                    case 1:
                        labelText= "Loading settings...";
                        break;

                    case 2:                    
                        labelText="Testing interaction web service";
                        break;

                    case 3:
                        labelText = "Testing VTL DB";                    
                        break;

                    case 4:
                        labelText="Testing validation web service";
                        break;
                    
                    case 5:
                        labelText="Testing SDMX metadata web service";
                        break;
                }

                StatusLabel.Text = labelText;
                StatusLabel.Refresh();
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }            
        }

        private void backgroundLoader_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e)
        {
            try
            {

                if (_error)
                {
                    if (this.Visible)
                    {
                        frmStartMessageBoxError frmError = new frmStartMessageBoxError();
                        frmError.ErrorMessages = _errorMessage.ToArray<string>();
                        if (_errorMessage.Count > 0)
                            if (_errorMessage[0].IndexOf("NOT_FOUND") > -1)
                                frmError.ContinueEnabled = false;                        
                        frmError.ShowDialog();

                        switch (frmError.StartMessageBoxErorrResult)
                        {
                            case frmStartMessageBoxError.STARTMESSAGEBOXERROR_RESULT.NONE:
                                Application.Exit();
                                break;

                            case frmStartMessageBoxError.STARTMESSAGEBOXERROR_RESULT.CONTINUE:
                                frmMain MainForm = new frmMain();
                                MainForm.Show();
                                backgroundLoader.CancelAsync();
                                this.Visible = false;
                                StatusLabel.Text = "Continue without errors fixing!";
                                break;

                            case frmStartMessageBoxError.STARTMESSAGEBOXERROR_RESULT.EXIT:
                                Application.Exit();
                                break;

                            case frmStartMessageBoxError.STARTMESSAGEBOXERROR_RESULT.OPENSETTINGS:
                                frmSettings frmSet = new frmSettings();
                                if (frmSet.ShowDialog() == DialogResult.OK)
                                {
                                    frmSplashScreen_Load(null, null);
                                    StatusLabel.Text = "Settings updated!";
                                }
                                else 
                                {
                                    MainForm = new frmMain();
                                    MainForm.Show();
                                    backgroundLoader.CancelAsync();
                                    this.Visible = false;
                                    StatusLabel.Text = "Settings not updated, continue without errors fixing!";
                                }
                                break;

                        }
                    }
                }
                else 
                { 
                    frmMain MainForm = new frmMain();
                    MainForm.Show();
                    backgroundLoader.CancelAsync();
                    this.Visible = false;
                }
                
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }            
        }
        
    }
}
