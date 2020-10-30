using System;
using System.Collections.Generic;
using System.Reflection;
using System.Xml;
using ApplicationSettings.classes.common;
using ApplicationSettings.classes.utility;

namespace VTL_Editor_PL.classes.config
{
    public class MainApplicationSettings : ApplicationSettings.classes.services.ApplicationSettings
    {
      
        public string getQueryPath()
        {
            try
            {
                //Get the assembly information
                System.Reflection.Assembly assemblyInfo = System.Reflection.Assembly.GetExecutingAssembly();

                //Location is where the assembly is run from
                string assemblyLocation = assemblyInfo.Location.Substring(0, assemblyInfo.Location.LastIndexOf('\\'));
                assemblyLocation += "\\Resources";
                if (System.IO.File.Exists(assemblyLocation + "\\getCategorySchema.xml"))
                {
                    return assemblyLocation;
                }

                //CodeBase is the location of the ClickOnce deployment files
                Uri uriCodeBase = new Uri(assemblyInfo.CodeBase);
                string ClickOnceLocation = System.IO.Path.GetDirectoryName(uriCodeBase.LocalPath.ToString());
                ClickOnceLocation += "\\Resources";
                if (System.IO.File.Exists(ClickOnceLocation + "\\getCategorySchema.xml"))
                {
                    return ClickOnceLocation;
                }

                //UserDataForder
                string userData = System.Windows.Forms.Application.LocalUserAppDataPath + "\\Resources";
                if (System.IO.File.Exists(userData + "\\getCategorySchema.xml"))
                {
                    return userData;
                }

                throw new Exception("Error, [Common.CommonItem.getQueryPath] Resources folder cannot be found!!");
            }
            catch (Exception ex)
            {
                throw new Exception("Error, [Common.CommonItem.getQueryPath] " + ex.Message);
            }
        }

        public List<CommonConst.Loading_Status> LoadSettings(bool initialize)
        {        
            try
            {
                List<CommonConst.Loading_Status> result = new List<CommonConst.Loading_Status>();
                

                if (VTL_Editor_PL.Properties.Settings.Default.SettingVersion == "")
                {
                    result.Add(CommonConst.Loading_Status.NOT_FOUND);
                    return result;
                }
    
                base.SettingsVersion = VTL_Editor_PL.Properties.Settings.Default.SettingVersion;

                base.Language = VTL_Editor_PL.Properties.Settings.Default.Language;

                base.EnableProxy = VTL_Editor_PL.Properties.Settings.Default.EnableProxy;
                base.ProxyServer = VTL_Editor_PL.Properties.Settings.Default.ProxyServer;
                base.ProxyPort = VTL_Editor_PL.Properties.Settings.Default.ProxyPort;
                base.ProxyUser = VTL_Editor_PL.Properties.Settings.Default.ProxyUser;
                base.ProxyPwd = VTL_Editor_PL.Properties.Settings.Default.ProxyPwd;                
                base.QueriesPath = getQueryPath();
                base.LogActive = VTL_Editor_PL.Properties.Settings.Default.LogActive;
                base.LogPath = VTL_Editor_PL.Properties.Settings.Default.LogPath;

                base.OBS_default_valueDomainID = VTL_Editor_PL.Properties.Settings.Default.OBS_default_valueDomainID;
                base.TIME_PERIOD_default_valueDomainID = VTL_Editor_PL.Properties.Settings.Default.TIME_PERIOD_default_valueDomainID;

                //if (VTL_Editor_PL.Properties.Settings.Default.VTLDB != null)
                //    base.VTLDB = DBInfo.FromXml(VTL_Editor_PL.Properties.Settings.Default.VTLDB);

                //if (VTLDB.Host=="")
                //{
                //    result = CommonConst.Loading_Status.DB_CONNECTION_ERROR;
                //    base.SettingsStatus = result;
                //    return result;
                //}
                
                System.Xml.XmlDocument tmpDoc = VTL_Editor_PL.Properties.Settings.Default.WebServices;

                if (tmpDoc == null)
                {
                    result.Add(CommonConst.Loading_Status.SDMX_WEBSERVICE_NOT_PRESENT);
                }
                else
                {
                    result.Add(getWebServices(tmpDoc));
                }


                tmpDoc = VTL_Editor_PL.Properties.Settings.Default.InteractionWebService;
                if (tmpDoc == null)
                {
                    result.Add(CommonConst.Loading_Status.INTERACTION_WS_NOT_PRESENT);
                }
                else
                {
                    result.Add(getInteractionWebService(tmpDoc));
                }

                tmpDoc = VTL_Editor_PL.Properties.Settings.Default.ValidationWebService;
                if (tmpDoc == null)
                {
                    result.Add(CommonConst.Loading_Status.VALIDATION_WS_NOT_PRESENT);
                }
                else
                {
                    result.Add(getValidationWebService(tmpDoc));
                }

                tmpDoc = VTL_Editor_PL.Properties.Settings.Default.DBConnections;
                if (tmpDoc == null)
                {
                    result.Add(CommonConst.Loading_Status.DB_CONNECTIONS_NOT_PRESENT);
                }
                else
                {
                    result.Add(getDBConnections(tmpDoc));
                }

                if (result.Count==0)
                    result.Add(CommonConst.Loading_Status.LOADED);
                base.SettingsStatus = result[0];

                        return result;
            }
            catch (Exception ex)
            {                
                throw new Exception("Error, [Common.CommonItem.LoadSettings] " + ex.Message);                
            }
        }

        public void ReplaceSettings()
        {
            try
            {
                XmlDocument xmlDoc;

                //Interaction WebService
                xmlDoc = base.setXmlDocInteractionWebServices(this.InteractionWebService);
                VTL_Editor_PL.Properties.Settings.Default.InteractionWebService = xmlDoc;
                //------------------------------------------------------------------------------------------------------------------

                //Validation WebService
                xmlDoc = base.setXmlDocValidationWebServices(this.ValidationWebService);
                VTL_Editor_PL.Properties.Settings.Default.ValidationWebService = xmlDoc;
                //------------------------------------------------------------------------------------------------------------------

                //SDMX Webservices list     
                if (this.WebServices.Count > 0)
                {
                    xmlDoc = base.setXmlDocWebServices(this.WebServices);
                    VTL_Editor_PL.Properties.Settings.Default.WebServices = xmlDoc;
                }
                //------------------------------------------------------------------------------------------------------------------

                //DBConnections
                if (this.DBConnections != null) 
                { 
                    if (this.DBConnections.Count > 0) 
                    { 
                        xmlDoc = base.setDBConnections(this.DBConnections);
                        VTL_Editor_PL.Properties.Settings.Default.DBConnections = xmlDoc;
                    }
                }
                //------------------------------------------------------------------------------------------------------------------

                //Further information
                VTL_Editor_PL.Properties.Settings.Default.SettingVersion = Assembly.GetExecutingAssembly().GetName().Version.ToString();                
                VTL_Editor_PL.Properties.Settings.Default.LogActive = base.LogActive;
                VTL_Editor_PL.Properties.Settings.Default.LogPath = base.LogPath;

                VTL_Editor_PL.Properties.Settings.Default.Language = base.Language;

                VTL_Editor_PL.Properties.Settings.Default.EnableProxy = base.EnableProxy;
                VTL_Editor_PL.Properties.Settings.Default.ProxyServer = base.ProxyServer;
                VTL_Editor_PL.Properties.Settings.Default.ProxyPort = base.ProxyPort;
                VTL_Editor_PL.Properties.Settings.Default.ProxyUser = base.ProxyUser;
                VTL_Editor_PL.Properties.Settings.Default.ProxyPwd = base.ProxyPwd;

                VTL_Editor_PL.Properties.Settings.Default.QueriesPath = base.QueriesPath;

                VTL_Editor_PL.Properties.Settings.Default.OBS_default_valueDomainID = base.OBS_default_valueDomainID;
                VTL_Editor_PL.Properties.Settings.Default.TIME_PERIOD_default_valueDomainID = base.TIME_PERIOD_default_valueDomainID;

                //----------------------------------------------------------------------------------------------------------------------

                //Database settings                
                //VTL_Editor_PL.Properties.Settings.Default.VTLDB = DBInfo.ToXml(VTLDB);
                //----------------------------------------------------------------------------------------------------------------------

                VTL_Editor_PL.Properties.Settings.Default.Save();
            }
            catch (Exception ex)
            {
                throw new Exception("Error, [Common.CommonItem.ReplaceSettings] " + ex.Message);
            }
        }

        public static bool selectWSInComboBoxByID(System.Windows.Forms.ComboBox WsComboList, int IDCode)
        {
            try
            {
                int index = 0;
                if (!String.IsNullOrEmpty(WsComboList.Text))
                    foreach (MainApplicationSettings.WebServiceInfo wsInfo in WsComboList.Items)
                    {
                        if (wsInfo.WebService_ID == IDCode)
                        {
                            WsComboList.SelectedIndex = index;
                            return true;
                        }
                        index++;
                    }
                return false;
            }
            catch (Exception ex)
            {
                throw new Exception("Error, [Common.CommonItem.selectWSInComboBoxByID] " + ex.Message);
            }
        }
    }
}
