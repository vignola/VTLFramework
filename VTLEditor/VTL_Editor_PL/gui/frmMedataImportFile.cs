using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml;
using ArtefactInfo.model;
using SDMXMetadataLoader.service;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.net;

namespace VTL_Editor_PL.gui
{
    public partial class frmMedataImportFile : Form
    {
        private string _fileName;
        private XmlDocument _xmlDoc;
        private List<BaseArtefactInfo> _dsdList;
        private List<BaseArtefactInfo> _codelistList;

        public frmMedataImportFile(string FileName)
        {
            InitializeComponent();
            _fileName = FileName;
            LoadStructureFile();
        }

        private void LoadStructureFile() 
        {
            _xmlDoc = new XmlDocument();
            _xmlDoc.Load(_fileName);
            _dsdList = MetadataRetriever.ParseDataStructureResponse(_xmlDoc, "Full");

            if (_dsdList.Count > 0)
                foreach (BaseArtefactInfo bs in _dsdList) 
                {
                    checkedListBoxArtefact.Items.Add("[DSD]: " + bs.sdmxAgency + ":" + bs.sdmxId + "(" + bs.sdmxVersion + ")");                    
                }

            _codelistList = MetadataRetriever.ParseValueDomainResponse(_xmlDoc, "Full");

            if (_codelistList.Count > 0)
                foreach (BaseArtefactInfo bs in _codelistList)
                {
                    checkedListBoxArtefact.Items.Add("[Codelist]: " + bs.sdmxAgency + ":" + bs.sdmxId + "(" + bs.sdmxVersion + ")");
                }
        }

        private void frmMedataImportFile_Load(object sender, EventArgs e)
        {

        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            ActAllCheckBoxes(true);
        }

        private void linkLabel2_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            ActAllCheckBoxes(false);
        }

        private void ActAllCheckBoxes(bool CheckThem)
        {
            for (int i = 0; i <= (checkedListBoxArtefact.Items.Count - 1); i++)
            {
                if (CheckThem)
                {
                    checkedListBoxArtefact.SetItemCheckState(i, CheckState.Checked);
                }
                else
                {
                    checkedListBoxArtefact.SetItemCheckState(i, CheckState.Unchecked);
                }
            }
        }

        private void linkLabel3_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            for (int i = 0; i <= (checkedListBoxArtefact.Items.Count - 1); i++)
            {
                if (checkedListBoxArtefact.GetItemChecked(i))
                    checkedListBoxArtefact.SetItemCheckState(i, CheckState.Unchecked);
                else
                    checkedListBoxArtefact.SetItemCheckState(i, CheckState.Checked);
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("Do you want to import the selected artefacts?", "Import", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
            {
                foreach (string cb in checkedListBoxArtefact.CheckedItems)
                {
                    if (cb.IndexOf("[DSD]")>-1) 
                    { 
                    }
                    else 
                    {
                        string val = cb.Replace("[Codelist]: ", "");
                        string[] id_rest = val.Split(':');
                        string agency = id_rest[0].Trim();
                        string id = id_rest[1].Substring(0, id_rest[1].IndexOf('(')).Trim();
                        string version = id_rest[1].Substring(id_rest[1].IndexOf('(') + 1).Replace(")", "").Trim();

                        foreach (BaseArtefactInfo bs in _codelistList)
                            if (bs.sdmxId == id && bs.sdmxVersion == version && bs.sdmxAgency == agency)
                            {
                                CommonItem.WaitOn();
                                
                                VTLInt_Service.ServiceClient VTLMan = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                                BaseArtefactInfo[] one_item={bs};
                                VTLMan.InsertValueDomain(id, agency, version, bs.name.ToArray(),one_item , true, BaseArtefactInfo.CREATION_TYPE.SDMX_WS_UPLOAD, ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE.String);

                                CommonItem.WaitOff();
                            }
                    }
                }
                MessageBox.Show("Selected artefacts imported!");
            }
        }
        
    }
}
