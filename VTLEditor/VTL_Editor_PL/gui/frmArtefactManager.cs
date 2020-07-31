using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using ArtefactInfo.model;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.net;
using VTL_Editor_PL.classes.tool;

namespace VTL_Editor_PL.gui
{
    public partial class frmArtefactManager : Form
    {
        public enum ARTEFACT_TYPE 
        { 
            NONE,
            DATA_STRUCTURE,
            DATA_SET,
            VALUE_DOMAIN,
            VALUE_DOMAIN_SUBSET
        }        

        private ARTEFACT_TYPE _activeArtefact;

        public bool ApplyLanguageToWholeArtefact;
        public string[] ArtefactLanguages;
        private string _currentWS;

        private UserDefinedDataStructurePanel _dataStructurePanel;
        private UserDefinedDataSetPanel _dataSetPanel;
        private UserDefinedValueDomainPanel _valueDomainPanel;
        private UserDefinedValueDomainSubsetPanel _valueDomainSubsetPanel;

        private TreeNode _valDomainNode, _dataStructureNode, _dataSetNode, _valDomainSubsetNode;

        public frmArtefactManager()
        {
            InitializeComponent();
        }

        private void frmArtefactManager_Load(object sender, EventArgs e)
        {
            try
            {                             
                TreeNode rootNode=ArtefactTreeView.Nodes.Add("Artefacts");
                _dataStructureNode=rootNode.Nodes.Add("Data structure");
                _dataSetNode= rootNode.Nodes.Add("Dataset");
                _valDomainNode=rootNode.Nodes.Add("Value domain");
                _valDomainSubsetNode = rootNode.Nodes.Add("Value domain subset");                

                LoadDataStructures(string.Empty);
                LoadDataFlows(string.Empty);
                LoadCodelist(string.Empty);
                LoadValueDomainSubset(string.Empty);
                rootNode.Expand();

                ArtefactTreeViewSwitch.BackColor = Color.SteelBlue;                

                ApplyLanguageToWholeArtefact = false;
                _activeArtefact = ARTEFACT_TYPE.NONE;

                this.ArtefactTreeViewSwitch.SwitchingPhase += switchTreeView_SwitchingPhase;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

       private void EditDescription_Click(object sender, EventArgs e)
        {
            try
            {
                frmArtefactDescription frmArtDescr = new frmArtefactDescription();
                if (_activeArtefact == ARTEFACT_TYPE.DATA_STRUCTURE)
                {
                    frmArtDescr.ApplyArtefactCheckBoxEnabled(true);
                    frmArtDescr.ApplyToWholeArtifact = ApplyLanguageToWholeArtefact;
                    if (ApplyLanguageToWholeArtefact && ArtefactLanguages != null && ArtefactLanguages.Length > 0)
                        frmArtDescr.Languages = ArtefactLanguages;
                }
                else
                {
                    frmArtDescr.ApplyArtefactCheckBoxEnabled(false);
                    frmArtDescr.ApplyToWholeArtifact = false;
                }

                if (vtl_descriptionComboBox.Items.Count > 0) 
                {
                    List<KeyValuePair<string, string>> tmpList = new List<KeyValuePair<string, string>>();

                    foreach (string str in vtl_descriptionComboBox.Items)
                    {
                        string[] couple=str.Split(" - ".ToCharArray());
                        if (couple.Length >0)
                        {
                            KeyValuePair<string, string> tmpCouple = new KeyValuePair<string, string>(couple[0], couple[3]);
                            tmpList.Add(tmpCouple);
                        }
                    }

                    frmArtDescr.Descriptions = tmpList;
                }

                
                if (frmArtDescr.ShowDialog() == DialogResult.OK) 
                {
                    vtl_descriptionComboBox.Items.Clear();
                    foreach (KeyValuePair<string, string> couple in frmArtDescr.Descriptions)
                    {
                        int ind=vtl_descriptionComboBox.Items.Add(couple.Key + " - " + couple.Value);                    
                    }
                    vtl_descriptionComboBox.SelectedIndex = 0;

                    ApplyLanguageToWholeArtefact = frmArtDescr.ApplyToWholeArtifact;
                    if (ApplyLanguageToWholeArtefact) ArtefactLanguages = frmArtDescr.Languages;
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void ArtefactTreeView_NodeMouseClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            try
            {
                ManagePanels();
                SaveButton.Visible = false;
                ArtefactIDPanel.Visible = false;
                _activeArtefact = ARTEFACT_TYPE.NONE;
                _currentWS = e.Node.Name;

                switch (e.Node.Text)
                {
                    case "Data structure":                
                        //LoadDataStructures(e.Node.Name);
                        ArtefactTypeLabel.Text = "Data structure";
                        _activeArtefact = ARTEFACT_TYPE.DATA_STRUCTURE;
                        break;
                    case "Dataset":                        
                        //LoadDataFlows(e.Node.Name);
                        ArtefactTypeLabel.Text = "Dataset";
                        _activeArtefact = ARTEFACT_TYPE.DATA_SET;
                        break;
                    case "Value domain":                        
                        //LoadCodelist(e.Node.Name);
                        ArtefactTypeLabel.Text = "Value domain";
                        _activeArtefact = ARTEFACT_TYPE.VALUE_DOMAIN;                
                        break;
                    case "Value domain subset":
                        //LoadValueDomainSubset(e.Node.Name);
                        ArtefactTypeLabel.Text = "Value domain subset";
                        _activeArtefact = ARTEFACT_TYPE.VALUE_DOMAIN_SUBSET;
                        break; 
                    default:
                        if (e.Node.Parent == null) return;
                        if (e.Node.Parent.Text != "Artefacts") 
                        {
                            switch (e.Node.Parent.Text)
                            {
                                case "Data structure":                                
                                    ArtefactTypeLabel.Text = "Data structure";
                                    _activeArtefact = ARTEFACT_TYPE.DATA_STRUCTURE;
                                     if (e.Node.Tag.GetType() == typeof(ArtefactNodeInfo))
                                    {
                                        ManagePanels(ARTEFACT_TYPE.DATA_STRUCTURE);
                                        ArtefactIDPanel.Visible = true;
                                        SaveButton.Visible = true;
                                        FillDataStructurePanel((ArtefactNodeInfo)e.Node.Tag, _dataStructurePanel);
                                    }
                                    else
                                    {
                                        throw new Exception("Artefact information not present into the tree node.");
                                    }
                                    break;
                                case "Dataset":
                                    _activeArtefact = ARTEFACT_TYPE.DATA_SET;
                                    ArtefactTypeLabel.Text = "Dataset";
                                    if (e.Node.Tag.GetType() == typeof(ArtefactNodeInfo))
                                    {
                                        ManagePanels(ARTEFACT_TYPE.DATA_SET);
                                        ArtefactIDPanel.Visible = true;
                                        SaveButton.Visible = true;
                                        FillDataSetPanel((ArtefactNodeInfo)e.Node.Tag, _dataSetPanel);
                                    }
                                    else
                                    {
                                        throw new Exception("Artefact information not present into the tree node.");
                                    }
                                    break;
                                case "Value domain":
                                    _activeArtefact = ARTEFACT_TYPE.VALUE_DOMAIN;
                                    ArtefactTypeLabel.Text = "Value domain";
                                    if (e.Node.Tag.GetType() == typeof(ArtefactNodeInfo))
                                    {
                                        ManagePanels(ARTEFACT_TYPE.VALUE_DOMAIN);
                                        ArtefactIDPanel.Visible = true;                                        
                                        SaveButton.Visible = true;
                                        FillValueDomainPanel((ArtefactNodeInfo)e.Node.Tag, _valueDomainPanel);
                                    }
                                    else 
                                    {
                                        throw new Exception("Artefact information not present into the tree node.");
                                    }
                                    break;
                                case "Value domain subset":
                                    _activeArtefact = ARTEFACT_TYPE.VALUE_DOMAIN_SUBSET;
                                    ArtefactTypeLabel.Text = "Value domain subset";
                                    if (e.Node.Tag.GetType() == typeof(ArtefactNodeInfo))
                                    {
                                        ManagePanels(ARTEFACT_TYPE.VALUE_DOMAIN_SUBSET);
                                        ArtefactIDPanel.Visible = true;
                                        SaveButton.Visible = true;
                                        FillValueDomainSubsetPanel((ArtefactNodeInfo)e.Node.Tag, _valueDomainSubsetPanel);
                                    }
                                    else
                                    {
                                        throw new Exception("Artefact information not present into the tree node.");
                                    }
                                    break;  
                            }
                        }
                        break;
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void LoadDataStructures(string webserviceId)
        {
            try
            {
                CommonItem.WaitOn();
                BaseArtefactInfo[] dsList;
                bool raiseError = false;

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                dsList = VTL_service.GetDataStructures();

                _dataStructureNode.Nodes.Clear();                

                if (dsList == null)
                {
                    //MessageBox.Show("There are not DataStructures available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                foreach (BaseArtefactInfo aInfo in dsList)
                {
                    if (aInfo.creationType == BaseArtefactInfo.CREATION_TYPE.USER_DEFINED)
                    {
                        string text;
                        if (ArtefactTreeViewSwitch.isCode())
                            text = aInfo.vtlId;
                        else
                            if (aInfo.name.Count == 0)
                            {
                                raiseError = true;
                                text = aInfo.vtlId;
                            }
                            else
                                text = aInfo.name[0].value.ToString();

                        TreeNode tmp = _dataStructureNode.Nodes.Add(aInfo.sdmxId, text);
                        tmp.Tag = new ArtefactNodeInfo(webserviceId, aInfo, ArtefactNodeInfo.ArtefactType.DataStructures);
                    }
                }
                CommonItem.WaitOff();

                if (raiseError)
                    MessageBox.Show("The description is not available for one or more items. The ID will be shown", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);

            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }


        private void LoadDataFlows(string webserviceId)
        {
            try
            {
                CommonItem.WaitOn();
                BaseArtefactInfo[] dfList = null;

                bool raiseError = false;
                
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                dfList = VTL_service.GetDataSets();


                _dataSetNode.Nodes.Clear();

                if (dfList == null)
                {
                    //MessageBox.Show("There are not DataSet available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                foreach (DataSetInfo aInfo in dfList)
                {
                    if (aInfo.creationType == BaseArtefactInfo.CREATION_TYPE.USER_DEFINED)
                    {
                        string text;
                        if (this.ArtefactTreeViewSwitch.isCode())
                            text = aInfo.sdmxId;
                        else
                            if (aInfo.name.Count == 0)
                            {
                                raiseError = true;
                                text = aInfo.vtlId;
                            }
                            else
                                text = aInfo.name[0].value.ToString();

                        TreeNode tmp = _dataSetNode.Nodes.Add(aInfo.sdmxId,CommonConstant.ToGlobalID(aInfo.sdmxId,aInfo.sdmxAgency,aInfo.sdmxVersion));
                        tmp.Tag = new ArtefactNodeInfo(webserviceId, aInfo, ArtefactNodeInfo.ArtefactType.DataFlows);
                    }
                }
                CommonItem.WaitOff();

                if (raiseError)
                    MessageBox.Show("The description is not available for one or more items.The ID will be shown", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);

            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }


        private void LoadCodelist(string webserviceId)
        {
            try
            {
                CommonItem.WaitOn();
                BaseArtefactInfo[] vlList;
                bool raiseError = false;

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                vlList = VTL_service.GetValueDomains();


                _valDomainNode.Nodes.Clear();

                if (vlList == null)
                {
                    //MessageBox.Show("There are not ValueDomain available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                foreach (BaseArtefactInfo aInfo in vlList)
                {
                    if (aInfo.creationType == BaseArtefactInfo.CREATION_TYPE.USER_DEFINED)
                    {
                        string text;
                        if (this.ArtefactTreeViewSwitch.isCode())
                            text = aInfo.vtlId;
                        else
                            if (aInfo.name.Count == 0)
                            {
                                raiseError = true;
                                text = aInfo.vtlId;
                            }
                            else
                                text = aInfo.name[0].value.ToString();

                        TreeNode tmp = _valDomainNode.Nodes.Add(aInfo.sdmxId, text);
                        tmp.Tag = new ArtefactNodeInfo(webserviceId, aInfo, ArtefactNodeInfo.ArtefactType.ValueDomains);
                    }
                }

                CommonItem.WaitOff();

                if (raiseError)
                    MessageBox.Show("The description is not available for one or more items.The ID will be shown", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        public void LoadValueDomainSubset(string webserviceId) 
        { 
            try
            {
                CommonItem.WaitOn();
                ValueDomainSubsetInfo[] vlList;
                bool raiseError = false;

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                vlList = VTL_service.GetValueDomainSubset();


                _valDomainSubsetNode.Nodes.Clear();

                if (vlList == null)
                {
                    //MessageBox.Show("There are not ValueDomain subset available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                foreach (ValueDomainSubsetInfo aInfo in vlList)
                {
                    if (aInfo.creationType == BaseArtefactInfo.CREATION_TYPE.USER_DEFINED)
                    {
                        string text;
                        if (this.ArtefactTreeViewSwitch.isCode())
                            text = aInfo.vtlId;
                        else
                            if (aInfo.name.Count == 0)
                            {
                                raiseError = true;
                                text = aInfo.vtlId;
                            }
                            else
                                text = aInfo.name[0].value.ToString();

                        TreeNode tmp = _valDomainSubsetNode.Nodes.Add(aInfo.sdmxId, text);
                        tmp.Tag = new ArtefactNodeInfo(webserviceId, aInfo, ArtefactNodeInfo.ArtefactType.ValueDomainSubsets);
                    }
                }

                CommonItem.WaitOff();

                if (raiseError)
                    MessageBox.Show("The description is not available for one or more items.The ID will be shown", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
 
             }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void switchTreeView_SwitchingPhase(object sender, Switch.SwitchEventArgs e)
        {
            try
            {
                bool raiseError = false;
                CommonItem.WaitOn();
                
                TreeNode[] nodesList = { _dataStructureNode, _dataSetNode, _valDomainNode };

                foreach (TreeNode currentBranch in nodesList)
                {
                    foreach (TreeNode tr in currentBranch.Nodes)
                    {
                        if (tr.Tag != null)
                        {
                            BaseArtefactInfo bs = ((ArtefactNodeInfo)tr.Tag).artefactInfo;
                            if (ArtefactTreeViewSwitch.isCode())
                            {
                                if (bs.vtlId != null)
                                    tr.Text = bs.vtlId;
                                else
                                    tr.Text = bs.sdmxId;
                            }
                            else
                                if (bs.name == null)
                                    raiseError = true;
                                else if (bs.name.Count == 0)
                                    raiseError = true;
                                else
                                    tr.Text = bs.name[0].value.ToString();
                        }
                    }
                }
                CommonItem.WaitOff();
                if (raiseError)
                    MessageBox.Show("The description is not available for one or more items.The ID will be shown", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void AddArtefactButton_Click(object sender, EventArgs e)
        {
            try
            {
                ManagePanels(_activeArtefact);
                ArtefactIDPanel.Visible = true;
                SaveButton.Visible = true;
                ArtefactPanelCleaner();              
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void ArtefactPanelCleaner() 
        {
            this.vtl_IdTextBox.Clear();
            this.SdmxAgencyTextBox.Clear();
            this.SdmxVersionTextBox.Clear();
            this.vtl_descriptionComboBox.Text = "";
            this.vtl_descriptionComboBox.Items.Clear();
            this.vtl_descriptionComboBox.Text = "";
        }

        private void ManagePanels(ARTEFACT_TYPE panel_type = ARTEFACT_TYPE.NONE) 
        {
            try
            {
                UserControl panelControl = null;                

                panelStartMessage.Visible = false;
                if (_dataStructurePanel != null) _dataStructurePanel.Visible = false;
                if (_valueDomainPanel != null) _valueDomainPanel.Visible = false;
                if (_dataSetPanel != null) _dataSetPanel.Visible = false;
                if (_valueDomainSubsetPanel != null) _valueDomainSubsetPanel.Visible = false;

                SaveButton.Visible = false;

                if (panel_type != ARTEFACT_TYPE.NONE)
                {
                    switch (panel_type)
                    {
                        case ARTEFACT_TYPE.VALUE_DOMAIN:
                            _valueDomainPanel=new UserDefinedValueDomainPanel();
                            foreach (Control item in splitContainer1.Panel2.Controls.OfType<UserDefinedValueDomainPanel>())                   
                                splitContainer1.Panel2.Controls.Remove(item);
                            panelControl = _valueDomainPanel;                            
                            break;
                        case ARTEFACT_TYPE.VALUE_DOMAIN_SUBSET:
                            _valueDomainSubsetPanel = new UserDefinedValueDomainSubsetPanel();
                            foreach (Control item in splitContainer1.Panel2.Controls.OfType<UserDefinedValueDomainSubsetPanel>())                   
                                splitContainer1.Panel2.Controls.Remove(item);
                            panelControl = _valueDomainSubsetPanel;
                            break;
                        case ARTEFACT_TYPE.DATA_STRUCTURE:
                            _dataStructurePanel=new UserDefinedDataStructurePanel();
                            foreach (Control item in splitContainer1.Panel2.Controls.OfType<UserDefinedDataStructurePanel>())
                                splitContainer1.Panel2.Controls.Remove(item);
                            panelControl = _dataStructurePanel;                                                        
                            break;
                        case ARTEFACT_TYPE.DATA_SET:
                            _dataSetPanel=new UserDefinedDataSetPanel();
                            foreach (Control item in splitContainer1.Panel2.Controls.OfType<UserDefinedDataSetPanel>())
                                splitContainer1.Panel2.Controls.Remove(item);                            
                            panelControl = _dataSetPanel;                                                 
                            break;
                    }
                   
                    splitContainer1.Panel2.Controls.Add(panelControl);
                    panelControl.Location = new Point(0, 93);
                    panelControl.Anchor = AnchorStyles.Top | AnchorStyles.Left | AnchorStyles.Right;
                    

                    panelControl.BringToFront();
                }
            } 
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }        
        

        private void SaveButton_Click(object sender, EventArgs e)
        {
            try
            {
                VTLInt_Service.ServiceClient VTLMan;

                if (!checkIdPanel()) return;

                if (check_idVTL()) 
                {
                    switch (_activeArtefact)
                    {
                        case ARTEFACT_TYPE.VALUE_DOMAIN:
                            if (_valueDomainPanel == null) return;

                            if (_valueDomainPanel.IsDescribed && string.IsNullOrEmpty(_valueDomainPanel.describedCriterion))
                            {
                                MessageBox.Show("The is not a criterion defined. Please, insert the criteria to define the value domain", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                                return;
                            }

                            if (!_valueDomainPanel.IsDescribed && _valueDomainPanel.valueDomainDataGridControl.RowCount == 0)
                            {
                                MessageBox.Show("The are no values for the value domain. Please, insert at least one code and its description", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                                return;
                            }

                            VTLMan = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                            ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE status; 
                                Enum.TryParse<ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE>(_valueDomainPanel.getDataType(), out status);

                                if (!_valueDomainPanel.IsDescribed)
                            {

                                string result = VTLMan.InsertValueDomain(vtl_IdTextBox.Text.Trim(), SdmxAgencyTextBox.Text.Trim(), SdmxVersionTextBox.Text.Trim(), getNamesFrom_descriptionComboBox(vtl_descriptionComboBox).ToArray(), getArtefactInfoFrom_valueDomainDataGrid(), true, BaseArtefactInfo.CREATION_TYPE.USER_DEFINED, status);
                                if (result == null) 
                                { 
                                    MessageBox.Show("Value domain already present. Please change ID or Agency or Version", "Alredy Present", MessageBoxButtons.OK, MessageBoxIcon.Error);
                                    return;
                                }
                            }
                            else 
                            {
                                string result=VTLMan.InsertValueDomainDescribed(vtl_IdTextBox.Text.Trim(), SdmxAgencyTextBox.Text.Trim(), SdmxVersionTextBox.Text.Trim(), getNamesFrom_descriptionComboBox(vtl_descriptionComboBox).ToArray(), _valueDomainPanel.describedCriterion, true, BaseArtefactInfo.CREATION_TYPE.USER_DEFINED, status);
                                if (result==null)
                                {
                                    MessageBox.Show("Value domain described already present. Please change ID or Agency or Version", "Alredy Present", MessageBoxButtons.OK, MessageBoxIcon.Error);
                                    return;
                                }
                            }

                            MessageBox.Show("Value domain saved!");
                            LoadCodelist(string.Empty);

                            break;

                        case ARTEFACT_TYPE.VALUE_DOMAIN_SUBSET:
                            if (_valueDomainSubsetPanel == null) return;

                            BaseArtefactInfo parentVtl = _valueDomainSubsetPanel.getParentValueDomain();

                            if (parentVtl == null)
                            {
                                MessageBox.Show("Select at least a parent value domain", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                                return;
                            }
                            
                            if (!((ArtefactInfo.model.ValueDomainInfo)parentVtl).isEnumerated && String.IsNullOrEmpty(_valueDomainSubsetPanel.describedCriterion.Trim()))
                            {
                                MessageBox.Show("The is not a value domain restriction. Please, insert the criteria to define the value domain subset", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                                return;                          
                            }

                            if (_valueDomainSubsetPanel.getCheckedItems().Length == 0 && ((ArtefactInfo.model.ValueDomainInfo)parentVtl).isEnumerated) 
                            {
                                MessageBox.Show("The are not values selected from the value domain parent. Please, select at least one value of the value domain parent.", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                                return;                          
                            }

                            VTLMan = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);

                            if (!((ArtefactInfo.model.ValueDomainInfo)parentVtl).isEnumerated)
                            {

                                if (!VTLMan.InsertValueDomainSubsetDescribed(vtl_IdTextBox.Text.Trim(), SdmxAgencyTextBox.Text.Trim(), SdmxVersionTextBox.Text.Trim(), parentVtl.sdmxId, parentVtl.sdmxAgency, parentVtl.sdmxVersion, getNamesFrom_descriptionComboBox(vtl_descriptionComboBox).ToArray(), _valueDomainSubsetPanel.describedCriterion, BaseArtefactInfo.CREATION_TYPE.USER_DEFINED)) 
                                {
                                    MessageBox.Show("Value domain subset already present. Please change ID or Agency or Version", "Alredy Present", MessageBoxButtons.OK, MessageBoxIcon.Error);
                                    return;
                                }
                            }
                            else 
                            {
                                List<int> selectedValue = new List<int>();
                                foreach (UserDefinedValueDomainSubsetPanel.ValValueDomainObject chk in _valueDomainSubsetPanel.getCheckedItems()) 
                                {
                                    selectedValue.Add(chk.value_seq_id);
                                }
                                if (!VTLMan.InsertValueDomainSubset(vtl_IdTextBox.Text.Trim(), SdmxAgencyTextBox.Text.Trim(), SdmxVersionTextBox.Text.Trim(), parentVtl.sdmxId, parentVtl.sdmxAgency, parentVtl.sdmxVersion, getNamesFrom_descriptionComboBox(vtl_descriptionComboBox).ToArray(), selectedValue.ToArray(), BaseArtefactInfo.CREATION_TYPE.USER_DEFINED)) 
                                {
                                    MessageBox.Show("Value domain subset already present. Please change ID or Agency or Version", "Alredy Present", MessageBoxButtons.OK, MessageBoxIcon.Error);
                                    return;
                                }
                            }
                            
                            MessageBox.Show("Value domain subset saved!");
                            LoadValueDomainSubset(string.Empty);
                            break;

                        case ARTEFACT_TYPE.DATA_STRUCTURE:
                            if (_dataStructurePanel == null) return;

                            if (_dataStructurePanel.dataStructureComponentGridControl.RowCount < 2) 
                            {
                                MessageBox.Show("The are not enough components in the Data structure. Please, insert at least one identifier and one measure", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                                return;
                            }

                            VTLMan = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                            BaseComponentInfo[] vlDSLIst = getValueDomainComponentFromComponentGrid();
                            string resultIns=VTLMan.InsertDataStructure(vtl_IdTextBox.Text.Trim(), SdmxAgencyTextBox.Text.Trim(), SdmxVersionTextBox.Text.Trim(), getNamesFrom_descriptionComboBox(vtl_descriptionComboBox).ToArray(), vlDSLIst, BaseArtefactInfo.CREATION_TYPE.USER_DEFINED);

                            if (resultIns == null)
                            {
                                MessageBox.Show("Data structure already present. Please change ID or Agency or Version", "Alredy Present", MessageBoxButtons.OK, MessageBoxIcon.Error);
                                return;
                            }

                            MessageBox.Show("Data structure saved!");
                            LoadDataStructures(string.Empty);
                            break;

                        case ARTEFACT_TYPE.DATA_SET:
                            if (_dataSetPanel == null) return;

                            if (_dataSetPanel.DSDdataGridViewControl.SelectedRows.Count == 0) 
                            {
                                MessageBox.Show("Please select one DSD.", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                                return;
                            }
                            VTLMan = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);

                            string dsdVTLId = ((string)_dataSetPanel.DSDdataGridViewControl.SelectedRows[0].Cells[0].Value);
                            string[] id=CommonConstant.splitGlobalID(dsdVTLId);                                                       

                            DataStructureComponentInfo[] component=VTLMan.GetDataStructureComp(dsdVTLId);                            
                            List<BaseComponentInfo> ds_componentsList = new List<BaseComponentInfo>();
                            BaseComponentInfo tempComponent;

                            foreach (BaseComponentInfo comp in component)
                            {
                                tempComponent = new BaseComponentInfo();
                                tempComponent.Role = ((ArtefactInfo.model.DataStructureComponentInfo)(comp)).role;
                                tempComponent.datastructure_id_ref = ((ArtefactInfo.model.DataStructureComponentInfo)(comp)).datastructure_id;
                                ValueDomainInfoInDataStructure tmpVLInfo = new ValueDomainInfoInDataStructure();
                                string[] globalID= CommonConstant.splitGlobalID(((ArtefactInfo.model.DataStructureComponentInfo)(comp)).valuedomain_id);
                                tmpVLInfo.vd_id=globalID[0];
                                tmpVLInfo.vd_agency=globalID[1];
                                tmpVLInfo.vd_version=globalID[2];
                                tmpVLInfo.values = null;//Must be already present
                                tempComponent.ValueDomainInfo = tmpVLInfo;
                                ds_componentsList.Add(tempComponent);
                            }
                            
                            string resultds=VTLMan.InsertDataSet(id[0], id[1], id[2], vtl_IdTextBox.Text.Trim(), SdmxAgencyTextBox.Text.Trim(), SdmxVersionTextBox.Text.Trim(), getNamesFrom_descriptionComboBox(vtl_descriptionComboBox).ToArray(), ds_componentsList.ToArray(), BaseArtefactInfo.CREATION_TYPE.USER_DEFINED);
                           
                            MessageBox.Show("Data set saved!");
                            LoadDataFlows(string.Empty);
                            break;
                    }
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }
        
        private bool CheckFillGrid()
        {
            try
            {

                foreach (DataGridViewRow rw in _dataStructurePanel.dataStructureComponentGridControl.Rows)
                { 
                    if (String.IsNullOrEmpty((string)rw.Cells[_dataStructurePanel.dataStructureComponentGridControl.Columns["ComponentID"].Index].Value))
                    {
                        MessageBox.Show("Please fill each component ID","Component ID empty", MessageBoxButtons.OK,MessageBoxIcon.Error);
                        return false;
                    }

                    if (String.IsNullOrEmpty((string)rw.Cells[_dataStructurePanel.dataStructureComponentGridControl.Columns["ComponentName"].Index].Value))
                    {
                        MessageBox.Show("Please fill each component Name","Component Name empty", MessageBoxButtons.OK,MessageBoxIcon.Error);
                        return false;
                    }

                    if (String.IsNullOrEmpty((string)rw.Cells[_dataStructurePanel.dataStructureComponentGridControl.Columns["ValueDomain"].Index].Value))
                    {
                        MessageBox.Show("Please fill each related Value domain","Component value domain empty", MessageBoxButtons.OK,MessageBoxIcon.Error);
                        return false;
                    }

                    if (String.IsNullOrEmpty((string)rw.Cells[_dataStructurePanel.dataStructureComponentGridControl.Columns["Role"].DisplayIndex].Value))
                    {
                        MessageBox.Show("Please select a role", "Component value roel not selected ", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        return false;
                    }
                }

                return true;
             }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return false;
            }
        }

        private BaseComponentInfo[] getValueDomainComponentFromComponentGrid() 
        {
            try
            {
                List<BaseComponentInfo> lst = new List<BaseComponentInfo>();
                BaseComponentInfo tmpComponent;
                ValueDomainInfoInDataStructure tmpValueDomain;

                foreach (DataGridViewRow rw in _dataStructurePanel.dataStructureComponentGridControl.Rows) 
                {
                    tmpComponent = new BaseComponentInfo();
                    tmpComponent.vtlId = (string)rw.Cells[_dataStructurePanel.dataStructureComponentGridControl.Columns["ComponentID"].DisplayIndex].Value;
                    tmpComponent.Role = (string)rw.Cells[_dataStructurePanel.dataStructureComponentGridControl.Columns["Role"].DisplayIndex].Value;//RoleColumnn
                    DataGridViewComboBoxCell componentNamesCombo = (DataGridViewComboBoxCell)rw.Cells[_dataStructurePanel.dataStructureComponentGridControl.Columns["ComponentName"].DisplayIndex];//Component names
                    tmpComponent.name = getNamesFrom_descriptionComboBox(componentNamesCombo);
                    
                    
                    tmpValueDomain = new ValueDomainInfoInDataStructure();
                    
                    ValueDomainInfo componentValInfo = (ValueDomainInfo)rw.Cells[_dataStructurePanel.dataStructureComponentGridControl.Columns["ValueDomain"].DisplayIndex].Value;//Value domain info
                    tmpValueDomain.vd_id = componentValInfo.sdmxId;
                    tmpValueDomain.vd_agency = componentValInfo.sdmxAgency;
                    tmpValueDomain.vd_version = componentValInfo.sdmxVersion;
                    tmpValueDomain.values = null;//Values and components must be already present into the VTL_DB                    

                    tmpComponent.ValueDomainInfo = tmpValueDomain;

                    lst.Add(tmpComponent);
                }                      

                return lst.ToArray();
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return null;
            }
        }

        private List<LocalizedValue> getNamesFrom_descriptionComboBox(ComboBox comboBoxSource) 
        {
            try
            {
                List<LocalizedValue> tmpList = new List<LocalizedValue>();
                foreach (string str in comboBoxSource.Items)
                {
                    string[] couple = str.Split(" - ".ToCharArray());
                    if (couple.Length > 0)
                    {                    
                        tmpList.Add(new LocalizedValue(couple[0], couple[3]));
                    }
                }
                return tmpList;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return null;
            }
            
        }

        private void setNamesTo_descriptionComboBox(List<LocalizedValue> descriptions, ComboBox comboBoxDestination)
        {
            try
            {
                foreach (LocalizedValue loc in descriptions) 
                {
                    comboBoxDestination.Items.Add(loc.lang + " - " + loc.value);
                }                
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);                
            }

        }

        private List<LocalizedValue> getNamesFrom_descriptionComboBox(DataGridViewComboBoxCell comboBoxSource)
        {
            try
            {
                List<LocalizedValue> tmpList = new List<LocalizedValue>();
                foreach (string str in comboBoxSource.Items)
                {
                    string[] couple = str.Split(" - ".ToCharArray());
                    if (couple.Length > 0)
                    {
                        tmpList.Add(new LocalizedValue(couple[0], couple[3]));
                    }
                }
                return tmpList;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return null;
            }
        }       

        private BaseArtefactInfo[] getArtefactInfoFrom_valueDomainDataGrid() 
        {
            try
            {
                BaseArtefactInfo[] tmpList = new BaseArtefactInfo[1];
                BaseArtefactInfo tmp;

                tmp = new BaseArtefactInfo();
                tmp.vtlId = vtl_IdTextBox.Text.Trim();
                tmp.sdmxAgency = SdmxAgencyTextBox.Text.Trim();
                tmp.sdmxVersion = SdmxVersionTextBox.Text.Trim();
                tmp.sdmxId = vtl_IdTextBox.Text.Trim();
                tmp.name = getNamesFrom_descriptionComboBox(vtl_descriptionComboBox);
                tmp.DataStructureDetails = new BaseArtefactInfo.DataStructureInformation();
                tmp.DataStructureDetails.Components = new List<BaseComponentInfo>();

                for (int i = 0; i < _valueDomainPanel.valueDomainDataGridControl.RowCount-1; i++) 
                {
                
                
                    BaseComponentInfo tmpComponent = new BaseComponentInfo();
                    tmpComponent.name = new List<LocalizedValue>();

                    for (int lg = 1; lg < _valueDomainPanel.valueDomainDataGridControl.ColumnCount; lg++) 
                    {
                        tmpComponent.vtlId = _valueDomainPanel.valueDomainDataGridControl.Rows[i].Cells[0].Value.ToString();
                        LocalizedValue tmpLv = new LocalizedValue(_valueDomainPanel.valueDomainDataGridControl.Columns[lg].HeaderText, _valueDomainPanel.valueDomainDataGridControl.Rows[i].Cells[lg].Value.ToString());
                        tmpComponent.name.Add(tmpLv);
                    }
                    tmp.DataStructureDetails.Components.Add(tmpComponent);
                }

                tmpList[0]=tmp;

                return tmpList;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return null;
            }
        }

        private bool check_idVTL() 
        {
            try
            {
                if (string.IsNullOrEmpty(vtl_IdTextBox.Text))
                {
                    MessageBox.Show("The VTL ID is empty. Please, insert an unique ID ", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return false;
                }

                if (string.IsNullOrEmpty(SdmxAgencyTextBox.Text))
                {
                    MessageBox.Show("The Agency ID is empty. Please, fill the specific text box ", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return false;
                }
                if (string.IsNullOrEmpty(SdmxVersionTextBox.Text))
                {
                    MessageBox.Show("The artefact versione is empty. Please, fill the specific text box ", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return false;
                }
                if (string.IsNullOrEmpty(vtl_IdTextBox.Text))
                {
                    MessageBox.Show("The VTL Description is empty. Please, insert a description for at least one language ", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return false;
                }
                return true;
                }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return false;
            }
        }

        

        private void CloseButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }        

        private bool checkIdPanel() 
        {
            try
            {
                
                Regex r = new Regex(@"[a-zA-Z0-9]", RegexOptions.IgnoreCase);
                if (!r.IsMatch(this.vtl_IdTextBox.Text.Trim()))
                {
                    MessageBox.Show("Wrong ID format. Please use a SDMX ID format [A-Z, a-z, 0-9, _, -, $, @])", "ID wrong format", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return false;
                }

                r = new Regex(@"^(\d{1}\.?){1,2}\d{1}$");
                if (!r.IsMatch(this.SdmxVersionTextBox.Text.Trim()))
                {
                    MessageBox.Show("Wrong version format. Please use a correct version format tre numbers separated by a dot", "Version wrong format", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return false;
                }

                if (string.IsNullOrEmpty(this.SdmxAgencyTextBox.Text))
                {
                    MessageBox.Show("The Angency value is empty. Please insert and agency ID", "Agency", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return false;
                }
                
                if (this.vtl_descriptionComboBox.Items.Count == 0) 
                {
                    MessageBox.Show("The Name value is empty. Please insert at least a name", "Name", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    return false;
                }

                return true;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return false;
            }
        }

        private void RemoveArtefactButton_Click(object sender, EventArgs e)
        {
            try
            {
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);

                if (_activeArtefact == ARTEFACT_TYPE.NONE || ArtefactTreeView.SelectedNode.Tag == null)
                {
                    MessageBox.Show("Please select an artefact", "Artefact selection", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return;
                }

                TreeNode tr = ArtefactTreeView.SelectedNode;
                ArtefactNodeInfo artInfo = (ArtefactNodeInfo)tr.Tag;

                MessageBox.Show("Do you want to remove the artefact: " + artInfo.artefactInfo.vtlId + " with all the referenced ones?", "Artefact remove", MessageBoxButtons.YesNo, MessageBoxIcon.Question);

                //CHECK TRANSFORMATION//

                switch (_activeArtefact)
                {
                    case ARTEFACT_TYPE.DATA_STRUCTURE:
                        throw new NotImplementedException("Remove data structure method not yet implemented");
                        break;
                    case ARTEFACT_TYPE.DATA_SET:
                        VTL_service.RemoveDataSet(artInfo.artefactInfo.sdmxId, artInfo.artefactInfo.sdmxAgency, artInfo.artefactInfo.sdmxVersion);
                        _dataSetNode.Nodes.Clear();
                        LoadDataFlows(CommonItem.CurrentSettings.InteractionWebService.WebService_ID.ToString());
                        break;

                    case ARTEFACT_TYPE.VALUE_DOMAIN:
                        VTL_service.RemoveValueDomain(artInfo.artefactInfo.sdmxId, artInfo.artefactInfo.sdmxAgency, artInfo.artefactInfo.sdmxVersion);
                        _valDomainNode.Nodes.Clear();
                        LoadCodelist(CommonItem.CurrentSettings.InteractionWebService.WebService_ID.ToString());
                        break;

                    case ARTEFACT_TYPE.VALUE_DOMAIN_SUBSET:
                        VTL_service.RemoveValueDomainSubset(artInfo.artefactInfo.sdmxId, artInfo.artefactInfo.sdmxAgency, artInfo.artefactInfo.sdmxVersion);
                        _valDomainSubsetNode.Nodes.Clear();
                        LoadValueDomainSubset(CommonItem.CurrentSettings.InteractionWebService.WebService_ID.ToString());
                        break;
                    default:

                        break;
                }

                MessageBox.Show("Artefact removed!");
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);                
            }
        }

        private void FillArtefactIDPanel(ArtefactNodeInfo artInfo) 
        { 
            try
            {
                this.vtl_IdTextBox.Text = artInfo.artefactInfo.sdmxId;
                this.SdmxAgencyTextBox.Text = artInfo.artefactInfo.sdmxAgency;
                this.SdmxVersionTextBox.Text = artInfo.artefactInfo.sdmxVersion;
                vtl_descriptionComboBox.Items.Clear();

                setNamesTo_descriptionComboBox(artInfo.artefactInfo.name,vtl_descriptionComboBox);

                vtl_descriptionComboBox.SelectedIndex = 0;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);                
            }
        }

        private void FillDataStructurePanel(ArtefactNodeInfo artInfo, UserDefinedDataStructurePanel pan) 
        { 
            try
            {
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                FillArtefactIDPanel(artInfo);
                ValueDomainInfo[] vlList = VTL_service.GetValueDomains();

                DataStructureComponentInfo[] DataStructureComponents = VTL_service.GetDataStructureComp(artInfo.artefactInfo.vtlId);
                foreach (DataStructureComponentInfo comp in DataStructureComponents) 
                {
                    
                    string componentID = comp.vtlId;                    
                    List<LocalizedValue> names = comp.name;
                    string role = comp.role;
                    string valueDomainId = comp.valuedomain_id;

                    int rw=pan.dataStructureComponentGridControl.Rows.Add();
                    pan.dataStructureComponentGridControl.Rows[rw].Cells["ComponentID"].Value = componentID;

                    DataGridViewComboBoxCell comboName = (DataGridViewComboBoxCell)pan.dataStructureComponentGridControl.Rows[rw].Cells["ComponentName"];
                    foreach (LocalizedValue lv in names)
                    {
                        string itemString = lv.lang + " - " + lv.value;
                        int indexRow=comboName.Items.Add(itemString);
                        comboName.Value = lv.lang + " - " + lv.value;//SelectedItem
                    }

                    DataGridViewComboBoxCell comboRole = (DataGridViewComboBoxCell)pan.dataStructureComponentGridControl.Rows[rw].Cells["Role"];
                    comboRole.Value = role;

                    IEnumerable<ValueDomainInfo> valDom = from valDomain in vlList
                                     where valDomain.vtlId == valueDomainId
                                     select valDomain;

                    pan.dataStructureComponentGridControl.Rows[rw].Cells["ValueDomain"].Value = valDom.First();
                }
 
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);                
            }
        }
 

        private void FillValueDomainPanel(ArtefactNodeInfo artInfo, UserDefinedValueDomainPanel pan) 
        { 
            try
            {
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                FillArtefactIDPanel(artInfo);

                pan.setComboDataType(((ArtefactInfo.model.ValueDomainInfo)(artInfo.artefactInfo)).data_type);                

                if (((ArtefactInfo.model.ValueDomainInfo)(artInfo.artefactInfo)).isEnumerated)
                {
                    pan.IsDescribed = false;
                    BaseComponentInfo[] ValueDomainValue = VTL_service.GetValueDomainValues(artInfo.artefactInfo.vtlId);
                    int columnNumber = ValueDomainValue[0].name.Count;

                    if (pan.valueDomainDataGridControl.Columns.Count > 1)
                        for (int i = pan.valueDomainDataGridControl.Columns.Count; i > 1; i--)
                            pan.valueDomainDataGridControl.Columns.RemoveAt(i-1);

                    //Creates the columns
                    for (int i = 0; i < columnNumber; i++)
                    {
                        string headerText = ValueDomainValue[0].name[i].lang;
                        pan.valueDomainDataGridControl.Columns.Add(headerText.Replace(" ","").Trim(),headerText);
                        
                    }

                    //Fill the rows
                    foreach (BaseComponentInfo bs in ValueDomainValue) 
                    {
                        int rwIndex= pan.valueDomainDataGridControl.Rows.Add();
                        pan.valueDomainDataGridControl.Rows[rwIndex].Cells[0].Value = bs.vtlId;

                        for (int i = 0; i < bs.name.Count;i++ )
                        {
                            pan.valueDomainDataGridControl.Rows[rwIndex].Cells[i+1].Value = bs.name[i].value;
                        }
                    }
                }
                else
                {
                    pan.IsDescribed = true;
                    pan.describedCriterion = ((ArtefactInfo.model.ValueDomainInfo)(artInfo.artefactInfo)).value_restriction;
                }                                 
                
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);                
            }
        }

        private void FillValueDomainSubsetPanel(ArtefactNodeInfo artInfo, UserDefinedValueDomainSubsetPanel pan)
        {
            try
            {
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                FillArtefactIDPanel(artInfo);

                if (((ArtefactInfo.model.ValueDomainSubsetInfo)(artInfo.artefactInfo)).isEnumerated) 
                {
                    pan.setEnumeratedDescribedView(true, artInfo.artefactInfo);
                } 
                else 
                {
                    pan.setEnumeratedDescribedView(false, artInfo.artefactInfo);
                }

             }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);                
            }
        }

        private void FillDataSetPanel(ArtefactNodeInfo artInfo, UserDefinedDataSetPanel pan)
        { 
            try
            {
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                FillArtefactIDPanel(artInfo);
                int i = 0;
                foreach (DataGridViewRow rw in pan.DSDdataGridViewControl.Rows) 
                {
                    string storedID = ((string)rw.Cells[0].Value).Trim();
                    if (storedID == ((ArtefactInfo.model.DataSetInfo)(artInfo.artefactInfo)).datastructure_id)
                    {
                        pan.DSDdataGridViewControl.Rows[i].Selected = true;                    
                        break;
                    }
                    i++;
                }
                
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void vtl_descriptionComboBox_KeyPress(object sender, KeyPressEventArgs e)
        {
            e.Handled = true;
        }
    }
}
