using ISTAT_DB_DAL;
using System;
using System.Collections.Generic;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.config;

namespace VTL_Editor_PL.gui
{
    public partial class frmSettings : Form
    {
        private MainApplicationSettings _tmp_settings;       

        public frmSettings()
        {
            InitializeComponent();
        }
       

        private void WebServiceAddButton_Click(object sender, EventArgs e)
        {
            try
            {
                frmAddWebService frmAdd = new frmAddWebService();
                frmAdd.DetailImplementationVisible = true;
                if (frmAdd.ShowDialog() == DialogResult.OK)
                {

                    _tmp_settings.WebServices.Add(frmAdd.WSInfo);

                    WebServiceNameComboBox.Items.Add(frmAdd.WSInfo);


                    if (_tmp_settings.WebServices.Count == 1)
                    {
                        WebServiceNameComboBox.SelectedIndex = 0;
                    }
                    
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void WebServiceModifyButton_Click_1(object sender, EventArgs e)
        {            
            MainApplicationSettings.WebServiceInfo tmp = (MainApplicationSettings.WebServiceInfo)WebServiceNameComboBox.SelectedItem;
            if (tmp != null)
            {
                frmAddWebService frmMod = new frmAddWebService();
                frmMod.DetailImplementationVisible = true;
                frmMod.Modify = true;
                frmMod.WSInfo = tmp;

                if (frmMod.ShowDialog() == DialogResult.OK)
                {
                    _tmp_settings.WebServices.Remove(tmp);
                    _tmp_settings.WebServices.Add(frmMod.WSInfo);
                    WebServiceDescriptionLabel.Text = frmMod.WSInfo.WebService_Description;
                    WebServiceURLLabel.Text = frmMod.WSInfo.WebService_public_URL;
                    WebServiceUserNameLabel.Text = frmMod.WSInfo.WebService_UserName;
                    WebServicePasswordLabel.Text = frmMod.WSInfo.WebService_Password;
                    WebServiceReturnDetailImplementationLabel.Text = frmMod.WSInfo.WebService_ReturnDetail.ToString();
                }
            }
            else
            {
                MessageBox.Show("Please select a webservice.", "Information", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }            
        }

        private void WebServiceRemoveButton_Click_1(object sender, EventArgs e)
        {
            try
            {
                //inizio  this.CurrentWSToolStripLabel.Text

                MainApplicationSettings.WebServiceInfo tmpItem = (MainApplicationSettings.WebServiceInfo)WebServiceNameComboBox.SelectedItem;

                if (tmpItem != null)
                {
                    _tmp_settings.WebServices.Remove(tmpItem);
                 
                    WebServiceDescriptionLabel.Text = "";
                    WebServiceURLLabel.Text = "";
                    WebServiceUserNameLabel.Text = "";
                    WebServicePasswordLabel.Text = "";

                    WebServiceNameComboBox.Items.Remove(WebServiceNameComboBox.SelectedItem);
                    //this.SettingsCancelButton.Enabled = false;
                                         
                }
                else
                {
                    MessageBox.Show("Please select a webservice", "Information", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                //fine
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void frmSettings_Load(object sender, EventArgs e)
        {
             try
            {             

                _tmp_settings = new MainApplicationSettings();
                _tmp_settings.WebServices = new List<MainApplicationSettings.WebServiceInfo>();

                if (_tmp_settings.LoadSettings(false) == ApplicationSettings.classes.common.CommonConst.Loading_Status.NOT_FOUND)
                    return;
                
                if (_tmp_settings.WebServices.Count > 0) { 
                foreach (MainApplicationSettings.WebServiceInfo wsInfo in _tmp_settings.WebServices)
                    {
                        WebServiceNameComboBox.Items.Add(wsInfo);                    
                    }

                    WebServiceNameComboBox.SelectedIndex = 0;                
                    MainApplicationSettings.WebServiceInfo tmpWs=(MainApplicationSettings.WebServiceInfo) WebServiceNameComboBox.SelectedItem;
                    this.WebServiceDescriptionLabel.Text = tmpWs.WebService_Description;
                    this.WebServiceURLLabel.Text = tmpWs.WebService_public_URL;
                    this.WebServiceUserNameLabel.Text = tmpWs.WebService_UserName;
                    this.WebServicePasswordLabel.Text = tmpWs.WebService_Password;
                    this.WebServiceReturnDetailImplementationLabel.Text = tmpWs.WebService_ReturnDetail.ToString();
                }

                if (_tmp_settings.DBConnections.Count > 0)
                {
                    this.DBConnectionsComboBox.Items.Clear();
                    foreach (MainApplicationSettings.DBInfo dbInfo in _tmp_settings.DBConnections) 
                    {
                        this.DBConnectionsComboBox.Items.Add(dbInfo.DBConnectionName);
                    }
                    this.DBConnectionsComboBox.SelectedIndex = 0;
                    this.DBConnectionNameTextBox.Text = _tmp_settings.DBConnections[0].DBConnectionName;
                    this.DbTypeComboBox.SelectedIndex = DbTypeComboBox.FindStringExact(_tmp_settings.DBConnections[0].DBType.ToString());
                    this.DBUserNameTextBox.Text = _tmp_settings.DBConnections[0].UserName;
                    this.DBPasswordTextBox.Text = _tmp_settings.DBConnections[0].Pwd;
                    this.OracleTNSTextBox.Text = _tmp_settings.DBConnections[0].TSNName;
                }

                this.IntWSlabelName.Text=_tmp_settings.InteractionWebService.WebService_Name;
                this.IntWSlabelDescr.Text = _tmp_settings.InteractionWebService.WebService_Description;
                this.IntWSlabelUrl.Text = _tmp_settings.InteractionWebService.WebService_public_URL;
                this.IntWSlabelUser.Text = _tmp_settings.InteractionWebService.WebService_UserName;
                this.IntWSlabelPwd.Text = _tmp_settings.InteractionWebService.WebService_Password;

                this.ValWSlabelName.Text = _tmp_settings.ValidationWebService.WebService_Name;
                this.ValWSlabelDescription.Text = _tmp_settings.ValidationWebService.WebService_Description;
                this.ValWSlabelWSUrl.Text = _tmp_settings.ValidationWebService.WebService_public_URL;
                this.ValWSlabelUser.Text = _tmp_settings.ValidationWebService.WebService_UserName;
                this.ValWSlabelPwd.Text = _tmp_settings.ValidationWebService.WebService_Password;

                this.TIME_PERIODTextBox.Text = _tmp_settings.TIME_PERIOD_default_valueDomainID;
                this.OBS_VALUEtextBox.Text = _tmp_settings.OBS_default_valueDomainID;
                
                //Initialize provider
                //InitializeProviders();
            }
             catch (Exception ex)
             {
                 CommonItem.ErrManger.ErrorManagement(ex, false, this);
             }
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            try
            {                                

                MainApplicationSettings.WebServiceInfo tmpIntWs = new ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo();
                tmpIntWs.WebService_Name = this.IntWSlabelName.Text;
                tmpIntWs.WebService_Description = this.IntWSlabelDescr.Text;
                tmpIntWs.WebService_public_URL = this.IntWSlabelUrl.Text;
                tmpIntWs.WebService_UserName = this.IntWSlabelUser.Text;
                tmpIntWs.WebService_Password = this.IntWSlabelPwd.Text;
                tmpIntWs.WebService_Crypted = false;                

                _tmp_settings.InteractionWebService = tmpIntWs;

                MainApplicationSettings.WebServiceInfo tmpValWs = new ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo();
                tmpValWs.WebService_Name = this.ValWSlabelName.Text;
                tmpValWs.WebService_Description = this.ValWSlabelDescription.Text;
                tmpValWs.WebService_public_URL = this.ValWSlabelWSUrl.Text;
                tmpValWs.WebService_UserName = this.ValWSlabelUser.Text;
                tmpValWs.WebService_Password = this.ValWSlabelPwd.Text;
                tmpValWs.WebService_Crypted = false;

                _tmp_settings.OBS_default_valueDomainID = this.OBS_VALUEtextBox.Text;
                _tmp_settings.TIME_PERIOD_default_valueDomainID = this.TIME_PERIODTextBox.Text;

                _tmp_settings.ValidationWebService = tmpValWs;

                _tmp_settings.ReplaceSettings();

                this.DialogResult = DialogResult.OK;

                this.Close();
            }
            catch (Exception ex)
            {
                this.DialogResult = DialogResult.Cancel;
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }

        }

        
        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.Cancel;
            this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            throw new NotImplementedException();
        }

        private void label20_Click(object sender, EventArgs e)
        {
            MessageBox.Show(System.Configuration.ConfigurationManager.OpenExeConfiguration(System.Configuration.ConfigurationUserLevel.PerUserRoamingAndLocal).FilePath);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                frmAddWebService frmAdd = new frmAddWebService();
                frmAdd.DetailImplementationVisible = false;

                if (frmAdd.ShowDialog() == DialogResult.OK)
                {

                    _tmp_settings.InteractionWebService=frmAdd.WSInfo;

                    IntWSlabelName.Text = _tmp_settings.InteractionWebService.WebService_Name;
                    IntWSlabelDescr.Text = _tmp_settings.InteractionWebService.WebService_Description;
                    IntWSlabelUrl.Text = _tmp_settings.InteractionWebService.WebService_public_URL;
                    IntWSlabelUser.Text = _tmp_settings.InteractionWebService.WebService_UserName;
                    IntWSlabelPwd.Text = _tmp_settings.InteractionWebService.WebService_Password;

                    if (_tmp_settings.WebServices.Count == 1)
                    {
                        WebServiceNameComboBox.SelectedIndex = 0;
                    }

                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            try
            {
                MainApplicationSettings.WebServiceInfo tmp = new ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo();
                tmp = _tmp_settings.InteractionWebService;

                if (tmp != null)
                {
                    frmAddWebService frmMod = new frmAddWebService();
                    frmMod.DetailImplementationVisible = false;
                    frmMod.Modify = true;
                    frmMod.WSInfo = tmp;

                    if (frmMod.ShowDialog() == DialogResult.OK)
                    {
                        _tmp_settings.InteractionWebService = frmMod.WSInfo;
                        IntWSlabelName.Text = _tmp_settings.InteractionWebService.WebService_Name;
                        IntWSlabelDescr.Text = _tmp_settings.InteractionWebService.WebService_Description;
                        IntWSlabelUrl.Text = _tmp_settings.InteractionWebService.WebService_public_URL;
                        IntWSlabelUser.Text = _tmp_settings.InteractionWebService.WebService_UserName;
                        IntWSlabelPwd.Text = _tmp_settings.InteractionWebService.WebService_Password;
                    }
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void ValWSbuttonAdd_Click(object sender, EventArgs e)
        {
            try
            {
                MainApplicationSettings.WebServiceInfo tmp = new ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo();
                tmp = _tmp_settings.InteractionWebService;

                if (tmp != null)
                {
                    frmAddWebService frmMod = new frmAddWebService();
                    frmMod.DetailImplementationVisible = false;
                    frmMod.WSInfo = tmp;

                    if (frmMod.ShowDialog() == DialogResult.OK)
                    {
                        _tmp_settings.ValidationWebService = frmMod.WSInfo;
                        ValWSlabelName.Text = _tmp_settings.ValidationWebService.WebService_Name;
                        ValWSlabelDescription.Text = _tmp_settings.ValidationWebService.WebService_Description;
                        ValWSlabelWSUrl.Text = _tmp_settings.ValidationWebService.WebService_public_URL;
                        ValWSlabelUser.Text = _tmp_settings.ValidationWebService.WebService_UserName;
                        ValWSlabelPwd.Text = _tmp_settings.ValidationWebService.WebService_Password;
                    }
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void ValWSbuttonModify_Click(object sender, EventArgs e)
        {
            try
            {
                MainApplicationSettings.WebServiceInfo tmp = new ApplicationSettings.classes.services.ApplicationSettings.WebServiceInfo();
                tmp = _tmp_settings.ValidationWebService;

                if (tmp != null)
                {
                    frmAddWebService frmMod = new frmAddWebService();
                    frmMod.DetailImplementationVisible = false;
                    frmMod.Modify = true;
                    frmMod.WSInfo = tmp;

                    if (frmMod.ShowDialog() == DialogResult.OK)
                    {
                        _tmp_settings.ValidationWebService = frmMod.WSInfo;
                        ValWSlabelName.Text = _tmp_settings.ValidationWebService.WebService_Name;
                        ValWSlabelDescription.Text = _tmp_settings.ValidationWebService.WebService_Description;
                        ValWSlabelWSUrl.Text = _tmp_settings.ValidationWebService.WebService_public_URL;
                        ValWSlabelUser.Text = _tmp_settings.ValidationWebService.WebService_UserName;
                        ValWSlabelPwd.Text = _tmp_settings.ValidationWebService.WebService_Password;
                    }
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }       

        private void WebServiceNameComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            try 
            { 
                MainApplicationSettings.WebServiceInfo tmpWs = (MainApplicationSettings.WebServiceInfo)WebServiceNameComboBox.SelectedItem;
                this.WebServiceDescriptionLabel.Text = tmpWs.WebService_Description;
                this.WebServiceURLLabel.Text = tmpWs.WebService_public_URL;
                this.WebServiceUserNameLabel.Text = tmpWs.WebService_UserName;
                this.WebServicePasswordLabel.Text = tmpWs.WebService_Password;
                this.WebServiceReturnDetailImplementationLabel.Text = tmpWs.WebService_ReturnDetail.ToString();
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
}

        private void selectOBS_VDbutton_Click(object sender, EventArgs e)
        {
            try 
            { 
                frmSelectValueDomain frmselect = new frmSelectValueDomain();
                if (frmselect.ShowDialog() == DialogResult.OK) 
                {
                    if (frmselect.SelectedValueDomain!=null)
                        OBS_VALUEtextBox.Text = frmselect.SelectedValueDomain.vtlId;
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void select_TIME_vdbutton_Click(object sender, EventArgs e)
        {
            try 
            { 
                frmSelectValueDomain frmselect = new frmSelectValueDomain();
                if (frmselect.ShowDialog() == DialogResult.OK)
                {
                    if (frmselect.SelectedValueDomain != null)
                        TIME_PERIODTextBox.Text = frmselect.SelectedValueDomain.vtlId;
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void NewButton_Click(object sender, EventArgs e)
        {
            DBConnectionNameTextBox.Clear();
            DBUserNameTextBox.Clear();
            DBPasswordTextBox.Clear();
            OracleTNSTextBox.Clear();            
        }

        private void button3_Click(object sender, EventArgs e)
        {
            try 
            { 
                MainApplicationSettings.DBInfo dbInfo = new MainApplicationSettings.DBInfo();
                if (DBConnectionNameTextBox.Text.Trim()!="")
                    dbInfo.DBConnectionName = DBConnectionNameTextBox.Text;
                else 
                {
                    MessageBox.Show("Please, insert a Database connection name", "Database connection name", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return;
                }

                dbInfo.UserName = DBUserNameTextBox.Text;
                dbInfo.Pwd = DBPasswordTextBox.Text;
                dbInfo.TSNName = OracleTNSTextBox.Text;
                dbInfo.DBType = ProviderType.Oracle;
                dbInfo.ID = dbInfo.DBConnectionName.GetHashCode();

                _tmp_settings.DBConnections.Add(dbInfo);
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void TestConnectionButton_Click_1(object sender, EventArgs e)
        {
            try
            {
                MainApplicationSettings.DBInfo dbInfo = new ApplicationSettings.classes.services.ApplicationSettings.DBInfo();
                dbInfo.TSNName = this.OracleTNSTextBox.Text;
                dbInfo.UserName = this.DBUserNameTextBox.Text;
                dbInfo.Pwd = this.DBPasswordTextBox.Text;

                if (DataProvider.TestDatabaseConnection(dbInfo.GetOracleConnectionString(), ProviderType.Oracle))
                {
                    MessageBox.Show("Successfully connected to " + this.OracleTNSTextBox.Text);
                }
                else 
                {
                    MessageBox.Show("Connection failed");
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
}
    }
}
