using System.ComponentModel;
/*
 * © 2009-2010 by the European Community, represented by Eurostat. All rights Reserved
 * Created by SharpDevelop.
 * User: tasos
 * Date: 11/8/2010
 * Time: 8:39 μμ
 * 
 * 
 */
using System.Configuration;
namespace WebServiceConnector
{
    /// <summary>
    /// Configuration section &lt;WsConfiguration&gt;
    /// </summary>
    /// <remarks>
    /// Assign properties to your child class that has the attribute
    /// <c>[ConfigurationProperty]</c> to store said properties in the xml.
    /// </remarks>
    public sealed class WsConfigurationSettings : ConfigurationSection
    {
        System.Configuration.Configuration _Config;


        #region ConfigurationProperties
        
        /*
         *  Uncomment the following section and add a Configuration Collection
         *  from the with the file named WsConfiguration.cs
         */
        // /// <summary>
        // /// A custom XML section for an application's configuration file.
        // /// </summary>
        // [ConfigurationProperty("customSection", IsDefaultCollection = true)]
        // public WsConfigurationCollection WsConfiguration
        // {
        //     get { return (WsConfigurationCollection) base["customSection"]; }
        // }

        /// <summary>
        /// Collection of <c>WsConfigurationElement(s)</c>
        /// A custom XML section for an applications configuration file.
        /// </summary>

        /// <summary>
        /// Get or set the Web Service Endpoint URL
        /// </summary>
        [ConfigurationProperty("EndPoint")]
        [Description("Web Service Endpoint URL"), Category("Endpoint")]
        public string EndPoint {
            get { return (string) this["EndPoint"]; }
            set { this["EndPoint"] = value; }
        }
        /// <summary>
        /// Get or set the Web Service Endpoint URL
        /// </summary>
        [ConfigurationProperty("WSDL")]
        [Description("Web Service WSDL URL"), Category("Endpoint")]
        public string WSDL {
            get { return (string) this["WSDL"]; }
            set { this["WSDL"] = value; }
        }
        /// <summary>
        /// Get or set the Web Service Endpoint URL
        /// </summary>
        [ConfigurationProperty("Operation")]
        [Description("Web Service Operation"), Category("Endpoint")]
        public string Operation {
            get { return (string) this["Operation"]; }
            set { this["Operation"] = value; }
        }
//
        //        [ConfigurationProperty("Namespace")]
        //        [Description("Namespace"), Category("Endpoint")]
        //        public string Namespace {
        //            get { return (string) this["Namespace"]; }
        //            set { this["Namespace"] = value; }
        //        }
        /// <summary>
        /// Get or set the Web Service Endpoint URL
        /// </summary>
        [ConfigurationProperty("Prefix",DefaultValue="web")]
        [Description("Web service Namespace prefix"), Category("Endpoint"),DefaultValue("web")]
        public string Prefix {
            get { return (string) this["Prefix"]; }
            set { this["Prefix"] = value; }
        }
        /// <summary>
        /// Enable or disable HTTP Authentication
        /// </summary>
        [ConfigurationProperty("EnableHTTPAuthenication",DefaultValue=false)]
        [Description("Enable HTTP Authentication"), Category("HTTP Authentication"),DefaultValue(false)]
        public bool EnableHTTPAuthenication {
            get { return (bool) this["EnableHTTPAuthenication"]; }
            set { this["EnableHTTPAuthenication"] = value; }
        }
        /// <summary>
        /// Get or set the HTTP User name
        /// </summary>
        [ConfigurationProperty("Username")]
        [Description("HTTP User name"), Category("HTTP Authentication")]
        public string Username {
            get { return (string) this["Username"]; }
            set { this["Username"] = value; }
        }
        /// <summary>
        /// Get or set the HTTP Password
        /// </summary>
        [ConfigurationProperty("Password")]
        [Description("HTTP Password"), Category("HTTP Authentication"),PasswordPropertyText(true)]
        public string Password {
            get { return (string) this["Password"]; }
            set { this["Password"] = value; }
        }
        /// <summary>
        /// Get or set the Domain
        /// </summary>
        [ConfigurationProperty("Domain")]
        [Description("Domain"), Category("HTTP Authentication")]
        public string Domain {
            get { return (string) this["Domain"]; }
            set { this["Domain"] = value; }
        }
        /// <summary>
        /// Enable or disable Proxy
        /// </summary>
        [ConfigurationProperty("EnableProxy")]
        [Description("Enable Proxy"), Category("Proxy"),DefaultValue(false)]
        public bool EnableProxy {
            get { return (bool) this["EnableProxy"]; }
            set { this["EnableProxy"] = value; }
        }
        /// <summary>
        /// Enable or disable use of <see cref="System.Net.WebRequest.DefaultWebProxy"/> proxy configuration
        /// </summary>
        [ConfigurationProperty("UseSystemProxy")]
        [Description("Use default proxy configuration"), Category("Proxy"),DefaultValue(false)]
        public bool UseSystemProxy {
            get { return (bool) this["UseSystemProxy"]; }
            set { this["UseSystemProxy"] = value; }
        }
        /// <summary>
        /// Get or set the Proxy server
        /// </summary>
        [ConfigurationProperty("ProxyServer")]
        [Description("Proxy server"), Category("Proxy")]
        public string ProxyServer {
            get { return (string) this["ProxyServer"]; }
            set { this["ProxyServer"] = value; }
        }
        /// <summary>
        /// Get or set the Proxy port
        /// </summary>
        [ConfigurationProperty("ProxyServerPort")]
        [Description("Proxy port"), Category("Proxy")]
        public int ProxyServerPort {
            get { return (int) this["ProxyServerPort"]; }
            set { this["ProxyServerPort"] = value; }
        }
        /// <summary>
        /// Get or set the Proxy User name
        /// </summary>
        [ConfigurationProperty("ProxyUsername")]
        [Description("Proxy User name"), Category("Proxy")]
        public string ProxyUsername {
            get { return (string) this["ProxyUsername"]; }
            set { this["ProxyUsername"] = value; }
        }
        /// <summary>
        /// Get or set the Proxy Password
        /// </summary>
        [ConfigurationProperty("ProxyPassword")]
        [Description("Proxy Password"), Category("Proxy"),PasswordPropertyText(true)]
        public string ProxyPassword {
            get { return (string) this["ProxyPassword"]; }
            set { this["ProxyPassword"] = value; }
        }
        #endregion
        #region Shadow unneeded properties.

        [Browsable(false)]
        public new bool LockItem { get { return base.LockItem; } set { base.LockItem = value; } }

        [Browsable(false)]
        public new ElementInformation ElementInformation { get { return base.ElementInformation; } }

        [Browsable(false)]
        public new ConfigurationLockCollection LockAllAttributesExcept { get { return base.LockAllAttributesExcept; } }

        [Browsable(false)]
        public new ConfigurationLockCollection LockAllElementsExcept { get { return base.LockAllElementsExcept; } }

        [Browsable(false)]
        public new ConfigurationLockCollection LockAttributes { get { return base.LockAttributes; } }

        [Browsable(false)]
        public new ConfigurationLockCollection LockElements { get { return base.LockElements; } }
        [Browsable(false)]
        public new SectionInformation SectionInformation { get { return base.SectionInformation; }}
        #endregion
        /// <summary>
        /// Constructor used by our factory method.
        /// </summary>
        public WsConfigurationSettings () : base () {            
            this.SectionInformation.AllowExeDefinition =
                ConfigurationAllowExeDefinition.MachineToLocalUser;
        }

        #region Public Methods
        
        /// <summary>
        /// Saves the configuration to the config file.
        /// </summary>
        public void Save() {
            _Config.Save();
        }
        
        #endregion
        
        #region Static Members
        
        /// <summary>
        /// Gets the current applications &lt;WsConfiguration&gt; section.
        /// </summary>
        /// <param name="ConfigLevel">
        /// The &lt;ConfigurationUserLevel&gt; that the config file
        /// is retrieved from.
        /// </param>
        /// <returns>
        /// The configuration file's &lt;WsConfiguration&gt; section.
        /// </returns>
        public static WsConfigurationSettings GetSection (ConfigurationUserLevel ConfigLevel) {
            /* 
             * This class is setup using a factory pattern that forces you to
             * name the section &lt;WsConfiguration&gt; in the config file.
             * If you would prefer to be able to specify the name of the section,
             * then remove this method and mark the constructor public.
             */
            System.Configuration.Configuration Config = ConfigurationManager.OpenExeConfiguration
                (ConfigLevel);
            WsConfigurationSettings oWsConfigurationSettings;
            
            oWsConfigurationSettings =
                (WsConfigurationSettings)Config.GetSection("WsConfigurationSettings");
            if (oWsConfigurationSettings == null) {
                oWsConfigurationSettings = new WsConfigurationSettings();
                Config.Sections.Add("WsConfigurationSettings", oWsConfigurationSettings);
            }
            oWsConfigurationSettings._Config = Config;
            
            return oWsConfigurationSettings;
        }
        
        #endregion
    }
}

