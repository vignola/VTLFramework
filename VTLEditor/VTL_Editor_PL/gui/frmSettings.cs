using ISTAT_DB_DAL;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.config;

namespace VTL_Editor_PL.gui
{
    public partial class frmSettings : Form
    {
        private MainApplicationSettings _tmp_settings;
        private bool _newDbConnection;

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
                frmAdd.TestConnectionVisible = true;

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

                if (_tmp_settings.LoadSettings(false)[0] == ApplicationSettings.classes.common.CommonConst.Loading_Status.NOT_FOUND)
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

                SaveDBConnButton.Enabled = false;
                RemoveDBConnButton.Enabled = false;
                _newDbConnection = false;

                if (_tmp_settings.DBConnections != null) 
                { 
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
                        RemoveDBConnButton.Enabled = true;
                    }
                }

                if (_tmp_settings.InteractionWebService != null) 
                { 
                    this.IntWSlabelName.Text=_tmp_settings.InteractionWebService.WebService_Name;
                    this.IntWSlabelDescr.Text = _tmp_settings.InteractionWebService.WebService_Description;
                    this.IntWSlabelUrl.Text = _tmp_settings.InteractionWebService.WebService_public_URL;
                    this.IntWSlabelUser.Text = _tmp_settings.InteractionWebService.WebService_UserName;
                    this.IntWSlabelPwd.Text = _tmp_settings.InteractionWebService.WebService_Password;
                }

                if (_tmp_settings.ValidationWebService != null) 
                {
                    this.ValWSlabelName.Text = _tmp_settings.ValidationWebService.WebService_Name;
                    this.ValWSlabelDescription.Text = _tmp_settings.ValidationWebService.WebService_Description;
                    this.ValWSlabelWSUrl.Text = _tmp_settings.ValidationWebService.WebService_public_URL;
                    this.ValWSlabelUser.Text = _tmp_settings.ValidationWebService.WebService_UserName;
                    this.ValWSlabelPwd.Text = _tmp_settings.ValidationWebService.WebService_Password;
                }

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

                if (String.IsNullOrEmpty(tmpIntWs.WebService_public_URL.Replace("-","")) || String.IsNullOrEmpty(tmpIntWs.WebService_Name.Replace("-", ""))) 
                { 
                    if (MessageBox.Show("Interaction web service has not been set. If you continue the system will return some errors. Do you want to proceed without it? ", "Interaction ws", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.No)                    
                        return;                    
                }

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

                if (String.IsNullOrEmpty(tmpValWs.WebService_public_URL.Replace("-", "")) || String.IsNullOrEmpty(tmpValWs.WebService_Name.Replace("-", "")))
                {
                    if (MessageBox.Show("Validation web service has not been set. If you continue you will not able to validate and translate your VTL statements. Do you want to proceed without it? ", "Validatrion ws", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.No)
                        return;
                }

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
                frmAdd.TestConnectionVisible = true;

                if (frmAdd.ShowDialog() == DialogResult.OK)
                {

                    _tmp_settings.InteractionWebService=frmAdd.WSInfo;

                    IntWSlabelName.Text = _tmp_settings.InteractionWebService.WebService_Name;
                    IntWSlabelDescr.Text = _tmp_settings.InteractionWebService.WebService_Description;
                    IntWSlabelUrl.Text = _tmp_settings.InteractionWebService.WebService_public_URL;
                    IntWSlabelUser.Text = _tmp_settings.InteractionWebService.WebService_UserName;
                    IntWSlabelPwd.Text = _tmp_settings.InteractionWebService.WebService_Password;                   

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
                    frmAddWebService frmMod = new frmAddWebService();
                    frmMod.DetailImplementationVisible = false;
                    frmMod.TestConnectionVisible = false;                    

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
            _newDbConnection = true;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            try 
            { 
                MainApplicationSettings.DBInfo dbInfo = new MainApplicationSettings.DBInfo();
                if (DBConnectionNameTextBox.Text.Trim() != "") {
                    if (_tmp_settings.DBConnections.Count > 0) 
                    { 
                        var existingDbNames = from dbnames in _tmp_settings.DBConnections
                                              where dbnames.DBConnectionName == DBConnectionNameTextBox.Text
                                              select dbnames;
                        if (existingDbNames.Count() >0 && _newDbConnection) 
                        {
                            MessageBox.Show("Sorry, a DB connection " + DBConnectionNameTextBox.Text + " already exist.", "Database connection name", MessageBoxButtons.OK, MessageBoxIcon.Information);
                            return;
                        }
                    }
                    dbInfo.DBConnectionName = DBConnectionNameTextBox.Text;
                }
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
                SaveDBConnButton.Enabled = false;
                _newDbConnection = false;
                MessageBox.Show("DBConnection added. Please, to apply this change permanently, click main Save button to close this Settings module", "Removed", MessageBoxButtons.OK); 
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

        private void button2_Click_1(object sender, EventArgs e)
        {
            try 
            {
                
                if (MessageBox.Show("Do you want to remove the selected DBConnection?", "Remove", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    for (int i = 0; i < _tmp_settings.DBConnections.Count; i++)
                    {
                        if (_tmp_settings.DBConnections[i].DBConnectionName == DBConnectionNameTextBox.Text)
                        {
                            _tmp_settings.DBConnections.RemoveAt(i);
                            DBConnectionNameTextBox.Clear();
                            DBUserNameTextBox.Clear();
                            DBPasswordTextBox.Clear();
                            OracleTNSTextBox.Clear();
                            DBConnectionsComboBox.Items.RemoveAt(i);
                            DBConnectionsComboBox.Text = "";
                            RemoveDBConnButton.Enabled = false;
                            MessageBox.Show("DBConnection removed. Please, to apply this change permanently, click main Save button to close this Settings module", "Removed", MessageBoxButtons.OK);
                            break;
                        }
                    }


                }
             }
             catch (Exception ex)
             {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
             }
        }

        private void DBConnectionsComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            try { 
                for (int i = 0; i < _tmp_settings.DBConnections.Count; i++)
                {
                    if (_tmp_settings.DBConnections[i].DBConnectionName == DBConnectionsComboBox.SelectedItem.ToString())
                    {
                        this.DBConnectionsComboBox.SelectedIndex = i;
                        this.DBConnectionNameTextBox.Text = _tmp_settings.DBConnections[i].DBConnectionName;
                        this.DbTypeComboBox.SelectedIndex = DbTypeComboBox.FindStringExact(_tmp_settings.DBConnections[i].DBType.ToString());
                        this.DBUserNameTextBox.Text = _tmp_settings.DBConnections[i].UserName;
                        this.DBPasswordTextBox.Text = _tmp_settings.DBConnections[i].Pwd;
                        this.OracleTNSTextBox.Text = _tmp_settings.DBConnections[i].TSNName;
                        RemoveDBConnButton.Enabled = true;
                        _newDbConnection = false;
                    }
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void DBConnectionNameTextBox_TextChanged(object sender, EventArgs e)
        {
            SaveDBConnButton.Enabled = true;
        }

        private void DbTypeComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            SaveDBConnButton.Enabled = true;
        }

        private void DBUserNameTextBox_TextChanged(object sender, EventArgs e)
        {
            SaveDBConnButton.Enabled = true;
        }

        private void DBPasswordTextBox_TextChanged(object sender, EventArgs e)
        {
            SaveDBConnButton.Enabled = true;
        }

        private void OracleTNSTextBox_TextChanged(object sender, EventArgs e)
        {
            SaveDBConnButton.Enabled = true;
        }
        
    }
}
