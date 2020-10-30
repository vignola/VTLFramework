using System;
using System.Collections.Generic;
using System.Drawing;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using ApplicationSettings.classes.common;
using ArtefactInfo.model;
using Newtonsoft.Json;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.net;
using VTL_Editor_PL.classes.tool;
using VTL_Editor_PL.gui;
using VTLlib;


namespace VTL_Editor_PL
{
    public partial class frmMain : Form
    {
        private string _currentlyLoadedTransformation=null;
        private bool _defineStateActive;        

        public frmMain()
        {
            InitializeComponent();                                   
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            try 
            {
                _defineStateActive = false;

                if (treeView1.Nodes.Count == 0) LoadOperatorList();

                //-----------------------------------------------------------------------------
                
                //this.splitContainerMainVertical.SplitterDistance = ((this.splitContainerMainVertical.Width-12) / 3);
                //this.splitContainerTreeView23.SplitterDistance = ((this.splitContainerMainVertical.Width - 6) / 3);

                this.switchTreeView2.SwitchingPhase += switchTreeView2_SwitchingPhase;
                this.switchTreeView3.SwitchingPhase += switchTreeView3_SwitchingPhase;

                this.treeView3.ShowNodeToolTips = true;

                //this.codeTextBox1.Notifyevent += codeTextBox1_Notifyevent;

                this.ActiveControl = codeTextBox1;

                this.treeView1.Nodes[0].Expand();
                this.treeView1.Nodes[1].Expand();
                this.codeTextBox1.ShowCaretPosition += new fastCodeTextBox.ShowPositionEventHandler(codeTextBox1_ShowCaretPosition);
                this.StatusLabel.Text = "Ready";                
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        void codeTextBox1_ShowCaretPosition(object Sender, ShowCaretPositionEventArgs arg)
        {
            LineLabel.Text = "Line: " + arg.LineNumber;
            ColumnLabel.Text = "Column: " + arg.ColumnNumber;
        }

        void switchTreeView2_SwitchingPhase(object sender, Switch.SwitchEventArgs e)
        {
            try
            {
                bool raiseError = false;
                CommonItem.WaitOn();
                foreach (TreeNode tr in treeView2.Nodes) 
                { 
                    BaseArtefactInfo bs=((ArtefactNodeInfo)tr.Tag).artefactInfo;
                    if (switchTreeView2.isCode())
                    {
                        if (bs.vtlId!=null)
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
                CommonItem.WaitOff();
                if (raiseError)
                    MessageBox.Show("The description is not available for one or more items.The ID will be shown", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        void switchTreeView3_SwitchingPhase(object sender, Switch.SwitchEventArgs e)
        {
            try
            {
                bool raiseError = false;
                CommonItem.WaitOn();
                foreach (TreeNode tr in treeView3.Nodes)
                {
                    BaseComponentInfo bs = ((ArtefactNodeInfo)tr.Tag).artefactComponentInfo;
                    if (switchTreeView3.isCode())
                    {
                        tr.Text = bs.vtlId;
                    }
                    else
                        if (bs.name == null)
                            raiseError = true;
                        else if (bs.name.Count == 0)
                            raiseError = true;
                        else
                            tr.Text = bs.name[0].value.ToString();

                    if (tr.Nodes.Count > 0)
                    {
                        foreach (TreeNode tr2 in tr.Nodes)
                        {
                            BaseComponentInfo bs2 = ((ArtefactNodeInfo)tr2.Tag).artefactComponentInfo;
                            if (switchTreeView3.isCode())
                            {
                                tr2.Text = bs2.vtlId;
                            }
                            else
                                if (bs2.name == null)
                                    raiseError = true;
                                else if (bs2.name.Count == 0)
                                    raiseError = true;
                                else
                                    tr2.Text = bs2.name[0].value.ToString();
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

        private void treeView1_NodeMouseClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            try
            {
                if (e.Node.Parent != null && (e.Node.Parent.Text == "Standard VTL operators"))
                {
                    treeView2.Nodes.Clear();
                    Operators operators = new Operators();
                    List<String[]> opList = new List<String[]>();
                    opList = operators.GetOperatorList(e.Node.Text);

                    for (int i = 0; i < opList.Count; i++)
                    {
                        Operator VTLoperator = new Operator();
                        treeView2.Nodes.Add(opList[i][1].ToString());
                        treeView2.Nodes[i].ToolTipText = VTLoperator.GetOperatorProperty(VTLOperatorProperty.Description, opList[i][1].ToString());
                    }
                }

                switchTreeView2.Visible = false;
                switchTreeView3.Visible = false;
                
                switch (e.Node.Text)
                {
                   case "DataStructure":
                        LoadDataStructures(e.Node.Name);
                        lblSecondTreeView.Text = "DataStructure";
                        switchTreeView2.Visible = true;
                        break;
                    case "DataSet":
                        LoadDataFlows(e.Node.Name);
                        lblSecondTreeView.Text = "DataSet";
                        switchTreeView2.Visible = true;
                        break;
                    case "ValueDomain":
                        LoadCodelist(e.Node.Name);
                        lblSecondTreeView.Text = "Value Domain";
                        switchTreeView2.Visible = true;
                        break;
                    case "ValueDomainSubset":
                        LoadValueDomainSubset(e.Node.Name);
                        lblSecondTreeView.Text = "Value Domain Subset";
                        switchTreeView2.Visible = true;
                        break;
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }

        }

        private void treeView1_NodeMouseDoubleClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            try
            {
                if (e.Node.Parent != null && (e.Node.Parent.Text == "User defined operators" || e.Node.Parent.Text == "User defined rulesets")) 
                {
                    string body = (string)e.Node.Tag;
                    string opName = e.Node.Text;
                    string signature = null;
                    int start = body.IndexOf(opName);

                    if (body.IndexOf("ruleset") > -1)
                    {

                        signature = body.Substring(start, body.IndexOf("(", start) - start ).Trim();
                        signature = signature.Replace("ruleset ", "");
                    }
                    else 
                    { 
                        signature=body.Substring(start, body.IndexOf(")",start)-start+1).Trim();                        
                    }
                    codeTextBox1.InsertTextToCaret(signature);
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void btnWizard_Click(object sender, EventArgs e)
        {
            try
            {
                //codeTextBox1.textBox.Select(0, 5);
                //codeTextBox1.textBox.SelectionFont = new Font("Verdana", 10, FontStyle.Bold);
                //codeTextBox1.textBox.SelectionStart = 6;
                //codeTextBox1.textBox.SelectionFont = new Font("Verdana", 10, FontStyle.Regular);
                //codeTextBox1.textBox.SelectionStart = codeTextBox1.textBox.Text.Length;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void treeView2_NodeMouseClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            try
            {
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                bool raiseError = false;

                ArtefactNodeInfo tagType = (ArtefactNodeInfo)e.Node.Tag;
                switchTreeView3.Visible = true;

                if (tagType != null) //aggiunto da laura
                {                    
                        switch (tagType.artType)
                        {
                            case ArtefactNodeInfo.ArtefactType.DataStructures:                               
                                BaseComponentInfo[] compList = VTL_service.GetDataStructureComp(e.Node.Text);
                                treeView3.Nodes.Clear();
                                lblThirdTreeView.Text = "Datastructure components";

                                if (compList.Length > 0)
                                {
                                    foreach (BaseComponentInfo aInfo in compList)
                                    {
                                        string text;
                                        if (switchTreeView3.isCode())
                                            text = aInfo.vtlId;
                                        else
                                            if (aInfo.name.Count == 0)
                                            {
                                                raiseError = true;
                                                text = aInfo.vtlId;
                                            }
                                            else
                                                text = aInfo.name[0].value.ToString();

                                        TreeNode tmp = treeView3.Nodes.Add(aInfo.vtlId, text);
                                        tmp.ToolTipText = "Value domain: " + ((ArtefactInfo.model.DataStructureComponentInfo)(aInfo)).valuedomain_id;

                                        tmp.Tag = new ArtefactNodeInfo("VTL_DB", aInfo, ArtefactNodeInfo.ArtefactType.DataStructures);
                                    }
                                }
                                else
                                {
                                    TreeNode tmp = treeView3.Nodes.Add("Datastucture components not found");
                                }

                                break;

                            case ArtefactNodeInfo.ArtefactType.DataFlows:                                
                                BaseComponentInfo[] compdfList = VTL_service.GetDataSetComp(e.Node.Text);
                                treeView3.Nodes.Clear();
                                lblThirdTreeView.Text = "Dataflow components";

                                if (compdfList.Length > 0)
                                {
                                    foreach (BaseComponentInfo aInfo in compdfList)
                                    {
                                        string text;
                                        if (switchTreeView3.isCode())
                                            text = aInfo.vtlId;
                                        else
                                            if (aInfo.name.Count == 0)
                                            {
                                                raiseError = true;
                                                text = aInfo.vtlId;
                                            }
                                            else
                                                text = aInfo.name[0].value.ToString();

                                        TreeNode tmp = treeView3.Nodes.Add(aInfo.vtlId, text);
                                        tmp.ToolTipText = "Value domain: " + ((ArtefactInfo.model.DataStructureComponentInfo)(aInfo)).valuedomain_id;
                                        ArtefactNodeInfo dataCompFlowNode = new ArtefactNodeInfo("VTL_DB", aInfo, ArtefactNodeInfo.ArtefactType.DataFlows, e.Node.Text);
                                        tmp.Tag = dataCompFlowNode;
                                    }
                                }
                                else
                                {
                                    TreeNode tmp = treeView3.Nodes.Add("Dataflow components not found");
                                }
                                break;

                            case ArtefactNodeInfo.ArtefactType.ValueDomains:
                                if (!((ArtefactInfo.model.ValueDomainInfo)(((VTL_Editor_PL.classes.tool.ArtefactNodeInfo)(e.Node.Tag)).artefactInfo)).isEnumerated)
                                {
                                    treeView3.Nodes.Clear();
                                    TreeNode tmp = treeView3.Nodes.Add("Described value domain!");
                                    return;
                                }
                                BaseComponentInfo[] vlItemsList = VTL_service.GetValueDomainValues(e.Node.Text);
                                treeView3.Nodes.Clear();
                                lblThirdTreeView.Text = "Value domain items";

                                if (vlItemsList.Length > 0)
                                {
                                    foreach (BaseComponentInfo aInfo in vlItemsList)
                                    {
                                        string text;
                                        if (switchTreeView3.isCode())
                                            text = aInfo.vtlId;
                                        else
                                            if (aInfo.name.Count == 0)
                                            {
                                                raiseError = true;
                                                text = aInfo.vtlId;
                                            }
                                            else
                                                text = aInfo.name[0].value.ToString();

                                        TreeNode tmp = treeView3.Nodes.Add(aInfo.vtlId, text);
                                        tmp.Tag = new ArtefactNodeInfo("VTL_DB", aInfo, ArtefactNodeInfo.ArtefactType.ValueDomains);
                                    }
                                }
                                else
                                {
                                    TreeNode tmp = treeView3.Nodes.Add("Value domain items not found");
                                }
                                break;

                            case ArtefactNodeInfo.ArtefactType.ValueDomainSubsets:
                                if (!((ArtefactInfo.model.ValueDomainSubsetInfo)(((VTL_Editor_PL.classes.tool.ArtefactNodeInfo)(e.Node.Tag)).artefactInfo)).isEnumerated) 
                                {
                                    treeView3.Nodes.Clear();
                                    TreeNode tmp = treeView3.Nodes.Add("Described value domain subset!");
                                    return;
                                }

                                BaseComponentInfo[] valSubsetList = VTL_service.GetSubSetList(e.Node.Text);
                                treeView3.Nodes.Clear();
                                lblThirdTreeView.Text = "Value domain subset items";

                                if (valSubsetList.Length > 0)
                                {
                                    foreach (BaseComponentInfo aInfo in valSubsetList)
                                    {
                                        string text;
                                        if (switchTreeView3.isCode())
                                            text = aInfo.vtlId;
                                        else
                                            if (aInfo.name.Count == 0)
                                            {
                                                raiseError = true;
                                                text = aInfo.vtlId;
                                            }
                                            else
                                                text = aInfo.name[0].value.ToString();

                                        TreeNode tmp = treeView3.Nodes.Add(aInfo.vtlId, text);
                                        tmp.Tag = new ArtefactNodeInfo("VTL_DB", aInfo, ArtefactNodeInfo.ArtefactType.ValueDomains);
                                    }
                                }
                                else
                                {
                                    TreeNode tmp = treeView3.Nodes.Add("Value domain subset items not found");
                                }

                                break;
                        }                    
                    

                    if (raiseError)
                        MessageBox.Show("The description is not available for one or more items.The ID will be shown", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);

                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }


        private void richTextBox1_DoubleClick(object sender, EventArgs e)
        {
            try
            {
             //   int index = codeTextBox1.textBox.SelectionStart;
             //   if (codeTextBox1.textBox.Text.Substring(codeTextBox1.textBox.SelectionStart + codeTextBox1.textBox.SelectionLength - 1, 1) == " ") codeTextBox1.textBox.Select(index, codeTextBox1.textBox.SelectionLength - 1);
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }


        private void treeView2_NodeMouseDoubleClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            try
            {
                if (e.Node.Tag != null)
                {
                    string artefact = ((ArtefactNodeInfo)e.Node.Tag).artType.ToString();

                    if (artefact == "ValueDomains" || artefact == "DataStructures" || artefact == "DataFlows" || artefact=="ValueDomainSubsets")
                    {
                        string token = e.Node.Text.Trim();
                        if (token.IndexOf(".") > -1) token = "'" + token + "'";                                              
                        codeTextBox1.InsertTextToCaret(token);
                    }
                }
                else
                {
                    string syntax;
                    syntax = GetSyntax(e.Node.Text);
                    codeTextBox1.InsertTextToCaret(syntax);
                }

            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void treeView3_NodeMouseDoubleClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            try
            {
                if (e.Node.Tag != null)
                {
                    string token = e.Node.Text.Trim();
                    codeTextBox1.InsertTextToCaret(token);
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void treeView3_MouseDown(object sender, MouseEventArgs e)
        {
            try
            {
                TreeNode tmp = ((System.Windows.Forms.TreeView)sender).SelectedNode;
                if (tmp == null) return;
                if (tmp.Tag == null) return;
                
                CommonItem.WaitOn();

                if ((e.Button.ToString() == "Right") && ((System.Windows.Forms.TreeView)sender).SelectedNode != null)
                {
                    ArtefactNodeInfo artInfo = (ArtefactNodeInfo)tmp.Tag;
                    ContextMenu cm = new ContextMenu();                    
                    if (artInfo.artType != ArtefactNodeInfo.ArtefactType.ValueDomains)
                    {
                        string parentCode = null;
                        switch (artInfo.artType)
                        {
                            case ArtefactNodeInfo.ArtefactType.DataStructures:
                                parentCode = ((ArtefactInfo.model.DataStructureComponentInfo)(artInfo.artefactComponentInfo)).datastructure_id;
                                break;
                            case ArtefactNodeInfo.ArtefactType.DataStructureComponents:
                                parentCode = (artInfo.artefactComponentInfo).datastructure_id_ref;
                                break;
                            case ArtefactNodeInfo.ArtefactType.DataFlows:
                                parentCode = artInfo.dataFlowID;                               
                                break;
                        }
                        MenuItem tmpMen = cm.MenuItems.Add("Add the component as member of: " + parentCode, cm_ItemClicked);
                        tmpMen.Tag = new KeyValuePair<string, object>(parentCode, ((System.Windows.Forms.TreeView)sender).SelectedNode.Text);


                        //Value domain extraction                        
                        MenuItem tmpComp = cm.MenuItems.Add("Load the related value domain", cm_ItemClicked);
                        tmpComp.Tag = new KeyValuePair<string, object>(artInfo.artefactComponentInfo.vtlId.Trim(), ((System.Windows.Forms.TreeView)sender).SelectedNode);

                        tmp.ContextMenu = cm;
                        cm.Show(((System.Windows.Forms.TreeView)sender), new Point(e.X, e.Y));
                    }
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
            finally 
            {
                CommonItem.WaitOff();
            }
        }

        private void cm_ItemClicked(object sender, System.EventArgs e)
        {
            try
            {
                CommonItem.WaitOn();

                string[] globIdent;
                KeyValuePair<string, object> tmpVal = (KeyValuePair<string, object>)((System.Windows.Forms.MenuItem)sender).Tag;
                string textNode;
                bool raiseError = false;
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);

                if (tmpVal.Value.GetType().ToString() == "System.Windows.Forms.TreeNode")
                {
                    TreeNode tmp = (TreeNode)tmpVal.Value;
                    ArtefactNodeInfo artInfo = (ArtefactNodeInfo)tmp.Tag;
                    string wsId = artInfo.webServiceID;
                    if (artInfo.webServiceID == "VTL_DB")
                    {
                        BaseComponentInfo[] vlItemsList = VTL_service.GetValueDomainValues(((ArtefactInfo.model.DataStructureComponentInfo)(artInfo.artefactComponentInfo)).valuedomain_id);
                        foreach (BaseComponentInfo bsComp in vlItemsList)
                        {
                            if (switchTreeView3.isCode())
                                textNode = bsComp.vtlId;
                            else
                                if (bsComp.name.Count == 0)
                                {
                                    raiseError = true;
                                    textNode = bsComp.vtlId;
                                }
                                else
                                    textNode = bsComp.name[0].value.ToString();

                            TreeNode tmpNode = tmp.Nodes.Add(textNode);
                            tmpNode.Tag = new ArtefactNodeInfo("VTL_DB", bsComp, ArtefactNodeInfo.ArtefactType.ValueDomains);
                        }
                    }
                    else
                    {
                        string glident = ((ArtefactInfo.model.DataStructureComponentInfo)(artInfo.artefactComponentInfo)).valuedomain_id;
                        globIdent = CommonConstant.splitGlobalID(glident);

                        List<BaseArtefactInfo> vlItemsList = getCodelist(artInfo.webServiceID, globIdent);

                        if (vlItemsList == null)
                        {
                            MessageBox.Show("The related value domain cannot be found", "Missing value domain", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        }
                        else
                        {
                            foreach (BaseArtefactInfo bsComp in vlItemsList)
                            {
                                if (switchTreeView3.isCode())
                                    textNode = bsComp.vtlId;
                                else
                                    if (bsComp.name.Count == 0)
                                    {
                                        raiseError = true;
                                        textNode = bsComp.vtlId;
                                    }
                                    else
                                        textNode = bsComp.name[0].value.ToString();

                                tmp.Nodes.Add(textNode);
                            }
                        }


                    }

                    tmp.Collapse();
                    if (raiseError)
                        MessageBox.Show("The description is not available for one or more items.The ID will be shown", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);

                }
                else
                {
                    string parentArt = tmpVal.Key.Trim();
                    if (parentArt.IndexOf(".") > -1) parentArt = "'" + parentArt + "'";
                    string token = parentArt + "#" + tmpVal.Value.ToString().Trim();                    
                    codeTextBox1.InsertTextToCaret(token);
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
            finally 
            {
                CommonItem.WaitOff();
            }
        }


        #region loadArtefacts and operators

        private void LoadOperatorList()
        {
            try
            {
                TreeNode Operators_Node = treeView1.Nodes.Add("Operators");
                //Operators_Node.ImageIndex = 0;
                //Operators_Node.SelectedImageIndex = 0;
                
               // TreeNode DML_Node = Operators_Node.Nodes.Add("Standard VTL operators");
                TreeNode UserDefinedOperator_Node = Operators_Node.Nodes.Add("User defined operators");
                TreeNode UserDefinedRuleset_Node = Operators_Node.Nodes.Add("User defined rulesets");
                
                //Gestione operatori
                //Operators operators = new VTLlib.Operators();

                //List<String> grpList = new List<String>();
                //grpList = operators.GetGroups();
                //for (int i = 0; i < grpList.Count; i++)
                //{
                //    TreeNode tmp = DML_Node.Nodes.Add(grpList[i]);
                //    tmp.Tag = grpList[i];
                //}
               

                TreeNode VTLDB_Node = treeView1.Nodes.Add("VTL Metadata");
                //VTLDB_Node.ImageIndex = 1;
                //VTLDB_Node.SelectedImageIndex = 1;
                VTLDB_Node.Nodes.Add("VTL_DB", "DataStructure");
                VTLDB_Node.Nodes.Add("VTL_DB", "DataSet");
                VTLDB_Node.Nodes.Add("VTL_DB", "ValueDomain");
                VTLDB_Node.Nodes.Add("VTL_DB", "ValueDomainSubset");

               
                FillUserDefinedOperatorNodes(UserDefinedOperator_Node, UserDefinedRuleset_Node);
                
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void FillUserDefinedOperatorNodes(TreeNode usd_operators_node, TreeNode usd_ruleset_node)
        {
            try
            {
                usd_operators_node.Nodes.Clear();
                usd_ruleset_node.Nodes.Clear();

                foreach (BaseUserDefinedOperator bsArtefact in getUserDefinedOperatorsList())
                {
                    TreeNode tmpNode = null;
                    if (bsArtefact.Operator_Type == 0)                    
                        tmpNode = usd_operators_node.Nodes.Add(bsArtefact.OperatorName);                    
                    else
                        tmpNode = usd_ruleset_node.Nodes.Add(bsArtefact.OperatorName);

                    tmpNode.Tag = bsArtefact.OperatorBody;
                    
                }
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
                BaseArtefactInfo[] dfList=null;
                
                bool raiseError = false;

                if (webserviceId == "VTL_DB")
                {
                    VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                    dfList = VTL_service.GetDataSets();
                }
                else
                {
                    //MetadataLoader mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(webserviceId)));
                    //dfList = mtl.LoadDataflows21();
                }
                treeView2.Nodes.Clear();
                treeView3.Nodes.Clear();

                if (dfList == null)
                {
                    MessageBox.Show("There are not DataSet available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                foreach (BaseArtefactInfo aInfo in dfList)
                {
                    string text;
                    if (switchTreeView2.isCode())
                        text = aInfo.sdmxId;
                    else
                        if (aInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = aInfo.vtlId;
                        }
                        else
                            text = aInfo.name[0].value.ToString();

                    TreeNode tmp = treeView2.Nodes.Add(aInfo.sdmxId, CommonConstant.ToGlobalID(aInfo.sdmxId,aInfo.sdmxAgency,aInfo.sdmxVersion));
                    tmp.Tag = new ArtefactNodeInfo(webserviceId, aInfo, ArtefactNodeInfo.ArtefactType.DataFlows);
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

        private void LoadDataStructures(string webserviceId)
        {
            try
            {
                CommonItem.WaitOn();
                BaseArtefactInfo[] dsList;
                bool raiseError = false;

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                dsList = VTL_service.GetDataStructures();
               
                treeView2.Nodes.Clear();
                treeView3.Nodes.Clear();

                if (dsList == null)
                {
                    MessageBox.Show("There are not DataStructures available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                foreach (BaseArtefactInfo aInfo in dsList)
                {
                    string text;
                    if (switchTreeView2.isCode())
                        text = aInfo.vtlId;
                    else
                        if (aInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = aInfo.vtlId;
                        }
                        else
                            text = aInfo.name[0].value.ToString();

                    TreeNode tmp = treeView2.Nodes.Add(aInfo.sdmxId, text);
                    tmp.Tag = new ArtefactNodeInfo(webserviceId, aInfo, ArtefactNodeInfo.ArtefactType.DataStructures);
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

        private void LoadCodelist(string webserviceId)
        {
            try
            {
                CommonItem.WaitOn();
                BaseArtefactInfo[] vlList;
                bool raiseError = false;

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                vlList = VTL_service.GetValueDomains();                
               
                treeView2.Nodes.Clear();
                treeView3.Nodes.Clear();

                if (vlList == null)
                {
                    MessageBox.Show("There are not ValueDomain available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                foreach (BaseArtefactInfo aInfo in vlList)
                {
                    string text;
                    if (switchTreeView2.isCode())
                        text = aInfo.vtlId;
                    else
                        if (aInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = aInfo.vtlId;
                        }
                        else
                            text = aInfo.name[0].value.ToString();

                    TreeNode tmp = treeView2.Nodes.Add(aInfo.sdmxId, text);
                    tmp.Tag = new ArtefactNodeInfo(webserviceId, aInfo, ArtefactNodeInfo.ArtefactType.ValueDomains);
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


                treeView2.Nodes.Clear();
                treeView3.Nodes.Clear();

                if (vlList == null)
                {
                    MessageBox.Show("There are not ValueDomain subset available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                foreach (ValueDomainSubsetInfo aInfo in vlList)
                {
                    string text;
                    if (switchTreeView2.isCode())
                        text = aInfo.vtlId;
                    else
                        if (aInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = aInfo.vtlId;
                        }
                        else
                            text = aInfo.name[0].value.ToString();

                    TreeNode tmp = treeView2.Nodes.Add(aInfo.sdmxId, text);
                    tmp.Tag = new ArtefactNodeInfo(webserviceId, aInfo, ArtefactNodeInfo.ArtefactType.ValueDomainSubsets);
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


        private string LoadTrasformation(string transfID) 
        {
            try
            {
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                return VTL_service.GetTransformation(transfID);
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return null;
            }
        }        

        private BaseUserDefinedOperator[] getUserDefinedOperatorsList()
        {
            try
            {
                BaseUserDefinedOperator[] tsList;
                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                tsList = VTL_service.GetUserDefinedOperatorsList();
                return tsList;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return null;
            }
        }

        private  List<BaseArtefactInfo> getCodelist(string webserviceId, string[] ident)
        {
            try 
            {
                List<BaseArtefactInfo> dsList=null;
                //MetadataLoader mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(webserviceId)));
                //dsList = mtl.LoadCodelistCodes21(ident[0], ident[1], ident[2]);
                return dsList;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return null;
            }
        }

        private void LoadCodelistCodes(string webserviceId, string dsd_id)
        {
            try
            {
                List<BaseArtefactInfo> dsList;
                bool raiseError = false;
                CommonItem.WaitOn();
                treeView3.Nodes.Clear();
                string[] globIdent = CommonConstant.splitGlobalID(dsd_id);
                dsList = getCodelist(webserviceId,globIdent);

                //Visit components

                foreach (BaseComponentInfo compInfo in dsList[0].DataStructureDetails.Components)
                {
                    string text;
                    if (switchTreeView3.isCode())
                        text = compInfo.vtlId;
                    else
                        if (compInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = compInfo.vtlId;
                        }
                        else
                            text = compInfo.name[0].value.ToString();

                    TreeNode tmp = treeView3.Nodes.Add(compInfo.vtlId, text);
                    tmp.Tag = new ArtefactNodeInfo(webserviceId, compInfo, ArtefactNodeInfo.ArtefactType.DataStructureComponents);
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


        //#endregion


        
        #endregion
        

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {            
            Application.Exit();
        }

        public string GetSyntax(string nodeName)
        {
            string syntax = "";
            Operator VTLoperator = new Operator();
            syntax = VTLoperator.GetOperatorProperty(VTLOperatorProperty.Syntax, nodeName);
            return syntax;

        }    

        private void treeView2SearchButton_Click(object sender, EventArgs e)
        {
            List<TreeNode> lst = new List<TreeNode>();

            if (!string.IsNullOrEmpty(treeView2SearchTextBox.Text))
                if (treeView3SearchTextBox.Text.Length <= 1) return;

                if (treeView2.Nodes.Count>0)
                {
                foreach (TreeNode tr in treeView2.Nodes) 
                    {
                        if (tr.Text.ToUpper().IndexOf(treeView2SearchTextBox.Text.ToUpper()) > -1) 
                        {
                            lst.Add(tr);
                        }
                    }
            }
            if (lst.Count > 0)
            {
                treeView2.Nodes.Clear();
                foreach (TreeNode tr in lst)
                {
                    treeView2.Nodes.Add(tr);
                }
            }
            else 
            {
                MessageBox.Show("String not found", "Not found", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            TreeView lst = new TreeView();
            bool parentadded = false;

            if (!string.IsNullOrEmpty(treeView3SearchTextBox.Text))
                if (treeView3SearchTextBox.Text.Length <= 1) return;

                if (treeView2.Nodes.Count > 0)
                {
                    parentadded = false;
                    foreach (TreeNode tr in treeView3.Nodes)
                    {
                        if (tr.Text.ToUpper().IndexOf(treeView3SearchTextBox.Text.ToUpper()) > -1)
                        {
                            lst.Nodes.Add((TreeNode)tr.Clone());
                            parentadded = true;
                        }
                        foreach (TreeNode trkids in tr.Nodes) 
                        {
                            if (trkids.Text.ToUpper().IndexOf(treeView3SearchTextBox.Text.ToUpper()) > -1)
                            {
                                try
                                {
                                    if (!parentadded) 
                                    {
                                        TreeNode existTree=TreeViewElementFinder.Search(lst, tr.Text);
                                        if (existTree == null)
                                        {
                                            TreeNode tmpParent = (TreeNode)tr.Clone();
                                            tmpParent.Nodes.Clear();
                                            tmpParent.Nodes.Add((TreeNode)trkids.Clone());
                                            lst.Nodes.Add(tmpParent);                                            
                                        }
                                        else 
                                        {
                                            existTree.Nodes.Add((TreeNode)trkids.Clone());
                                        }
                                    } 
                                }
                                catch (Exception ex)
                                { }
                            }
                        }
                    }
                }

            if (lst.Nodes.Count > 0)
            {
                treeView3.Nodes.Clear();
                foreach (TreeNode tr in lst.Nodes)
                {
                    treeView3.Nodes.Add((TreeNode)tr.Clone());
                }
                treeView3.ExpandAll();
            }
            else
            {
                MessageBox.Show("String not found", "Not found", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }              

        private void settingsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                frmSettings frmSet = new frmSettings();
                if (frmSet.ShowDialog() == DialogResult.OK) 
                {
                    Form1_Load(null, null);
                    List<CommonConst.Loading_Status> settingResult = CommonItem.CurrentSettings.LoadSettings(false);

                    if (settingResult[0] != CommonConst.Loading_Status.LOADED)
                    {
                        throw new Exception(" - Error loading settings, please fix them through the Settings module and restart the application! \n \n" + settingResult.ToString());
                    }
                    StatusLabel.Text = "Settings updated!";
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void sinToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                if (String.IsNullOrEmpty(this.codeTextBox1.Text.Trim())) return;

                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SYNTACTIC_VALIDATION_WEBMETHOD);
                Dictionary<string, string> args = new Dictionary<string, string>();
                resultsListBox.Items.Clear();
                string result = RestWS.SendPostRequest(this.codeTextBox1.Text);
                BaseJSonMessage JSonResult = JsonConvert.DeserializeObject<BaseJSonMessage>(result);
                //resultsListBox.Items.Add(JSonResult.errorResponse.message);   
                if (JSonResult.errorResponse == null)
                {
                    this.resultsListBox.Items.Add("Syntactic validation succeded!");
                    this.StatusLabel.Text = "Transformation syntactically valid!";
                }
                else 
                {
                    string[] errorrs = Regex.Split(JSonResult.errorResponse.message, @"<;>{1}");
                    this.resultsListBox.Items.Add("Syntactic validation failed!");
                    int i = 1;
                    foreach (string str in errorrs)
                    {
                        if (str != "")
                        {
                            string[] compError = str.Split("--".ToCharArray());
                            string ClearedResult = i + ") " +  compError[2] +" - " + compError[0].Replace("nulline","line") + " - " + compError[1];
                            this.resultsListBox.Items.Add(ClearedResult);
                        }
                        i++;
                    }
                    this.StatusLabel.Text = "Syntactic error.";
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }

            //try
            //{
            //    this.resultsListBox.Items.Clear();
            //    if (string.IsNullOrEmpty(codeTextBox1.textBox.Text))
            //    {
            //        MessageBox.Show("Please insert a rule.", "Insert a rule", MessageBoxButtons.OK, MessageBoxIcon.Information);
            //        return;
            //    }

            //    VtlSyntacticParsingManager parserManager = new VtlSyntacticParsingManager();
            //    if (!parserManager.Parse(codeTextBox1.textBox.Text.Split(';')))
            //    {
            //        foreach (VtlParsing.VtlSyntacticParsingManager.ErrorMessage err in parserManager.GetErrors())
            //        {
            //            this.resultsListBox.Items.Add( err.message + "  (Line : " + err.line + " - column :" + err.column + ")");
            //            this.StatusLabel.Text = "Transformation not correct";
            //        }
            //    }
            //    else 
            //    {
            //        this.resultsListBox.Items.Add("Syntactic validation succeded!");
            //        this.StatusLabel.Text = "Transformation validated";
            //    }
                
            //    //VTLlib.rules VTLRule = new VTLlib.rules();
            //    //txtBoxResults.Text = VTLRule.RunParser(codeTextBox1.textBox.Text);
            //}
            //catch (Exception ex)
            //{
            //    CommonItem.ErrManger.ErrorManagement(ex, false, this);
            //}
        }        

        private void selectAllToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.TextSelectAll();            
        }

        private void cutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.TextCut();
        }

        private void copyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.TextCopy();
        }

        private void pasteToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.TextPaste();
        }

        private void treeView1_MouseDown(object sender, MouseEventArgs e)
        {
            try
            {
                if (e.Button.ToString() == "Right" && ((TreeView)sender).SelectedNode!=null)
                {
                    TreeNode selNode = ((TreeView)sender).SelectedNode;
                    if (selNode.Parent.Text == "User defined operators" || selNode.Parent.Text == "User defined rulesets")
                    {
                        ContextMenu cm = new ContextMenu();
                        string usd = selNode.Text;
                        MenuItem tmpMen = cm.MenuItems.Add("Remove user defined operator: " + usd, cm_RemoveUSDClicked);                        
                        tmpMen.Tag = usd;
                        selNode.ContextMenu = cm;
                        MenuItem tmpMen1 = cm.MenuItems.Add("Open in user define editor", cm_CloneUserDefineOperatorClicked);
                        tmpMen1.Tag = selNode.Tag;
                        cm.Show(((System.Windows.Forms.TreeView)sender), new Point(e.X, e.Y));
                    }

                    if (selNode.Parent.Text == "Trasformations")
                    {
                        ContextMenu cm = new ContextMenu();
                        string trasformation = selNode.Text;
                        MenuItem tmpMen = cm.MenuItems.Add("Remove trasformation: " + trasformation, cm_RemoveTrasformationClicked);
                        tmpMen.Tag = trasformation;
                        selNode.ContextMenu = cm;
                        cm.Show(((System.Windows.Forms.TreeView)sender), new Point(e.X, e.Y));
                    }
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void cm_CloneUserDefineOperatorClicked(object sender, System.EventArgs e) 
        {
            try
            {                
                defineModeOn();
                codeTextBox1.Text = (string)((MenuItem)sender).Tag;
             }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void cm_RemoveUSDClicked(object sender, System.EventArgs e)
        {
            try
            {
                string usdName = (string)((MenuItem)sender).Tag;
                if (MessageBox.Show("Do you want to remove the user defined object: " + usdName, "Remove", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                    VTL_service.RemoveUserDefinedOperator(usdName);
                }
                TreeNode trasNode = TreeViewElementFinder.Search(treeView1, usdName);
                trasNode.Remove();
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void cm_RemoveTrasformationClicked(object sender, System.EventArgs e) 
        {
            try 
            {
                string trasName = (string)((MenuItem)sender).Tag;
                if (MessageBox.Show("Do you want to remove trasformation: " + trasName, "Remove", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes) 
                {
                    VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                    VTL_service.RemoveTransformation(trasName);                
                }
                TreeNode trasNode = TreeViewElementFinder.Search(treeView1, trasName);
                trasNode.Remove();
                codeTextBox1.Text = "";                
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            string trasformationText;
            string trasformationID;
            string agencyId;
            string version;
            string transformationOrdered="";

            if (!String.IsNullOrEmpty(codeTextBox1.Text))
            {
                if (!_defineStateActive)
                {
                    frmSaveTrasformation frmSave = new frmSaveTrasformation();
                    if (frmSave.ShowDialog() == DialogResult.OK)
                    {

                        if (!String.IsNullOrEmpty(frmSave.TrasformationName))
                        {
                            CommonItem.WaitOn();
                            trasformationID = frmSave.TrasformationName;
                            agencyId = frmSave.AgencyID;
                            version = frmSave.Version;
                            trasformationText = codeTextBox1.Text;

                            WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.ORDER_STATEMENTS_WEBMETHOD);
                            Dictionary<string, string> args = new Dictionary<string, string>();
                            resultsListBox.Items.Clear();
                            string result = RestWS.SendPostRequest(this.codeTextBox1.Text);
                            BaseJSonMessage JSonResult = JsonConvert.DeserializeObject<BaseJSonMessage>(result);
                            if (JSonResult.errorResponse == null)
                            {
                                if (JSonResult.resultStatements.Count > 0)
                                {
                                    foreach (BaseJSonMessage.ResultStatement res in JSonResult.resultStatements)
                                        transformationOrdered += res.command;
                                }
                                else
                                {
                                    throw (new Exception("Transformations cannot be ordered"));
                                }
                            }
                           

                            VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                            VTL_service.InsertTransformation(trasformationID, agencyId, version, trasformationText, transformationOrdered);

                            _currentlyLoadedTransformation = trasformationID;
                            CommonItem.WaitOff();

                            MessageBox.Show("Trasformation " + trasformationID + " saved", "Trasformation saved", MessageBoxButtons.OK, MessageBoxIcon.Information);                            
                        }
                    }
                }
                else 
                {                    
                    Regex reg = new Regex(@"(\s+(ruleset)\s+|(operator)\s+)[a-z,A-Z,_,0-9]*\s*\(");
                    Match match = reg.Match(codeTextBox1.Text);
                    string nameOp=match.Value.Replace("operator","").Replace("(","").Trim();
                    if (string.IsNullOrEmpty(nameOp)) 
                    {
                        MessageBox.Show("The signature of the operator cannot be matched.Please check the correctness of the statement", "Regex error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        return;
                    }

                    frmSaveOperator frmSaveOp = new frmSaveOperator(nameOp);
                    if (frmSaveOp.ShowDialog() == DialogResult.OK) 
                    {
                        VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                        if (VTL_service.InsertUserDefinedOperator(nameOp, codeTextBox1.Text, frmSaveOp.OperatorType))
                        {
                            TreeNode Node_operator = TreeViewElementFinder.Search(treeView1, "User defined operators");
                            TreeNode Node_ruleset = TreeViewElementFinder.Search(treeView1, "User defined rulesets");
                            if (Node_operator == null || Node_ruleset == null) throw new Exception("The user defined nodes cannot be found");

                            FillUserDefinedOperatorNodes(Node_operator, Node_ruleset);

                            MessageBox.Show("User defined object saved");
                        }
                        else
                            MessageBox.Show("User defined object " + nameOp + " already present. Please change the name of the operator", "Already present", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }

                    
                }
            }
            else
            {
                MessageBox.Show("Please insert a VTL rule into the text box!", "Missing rule", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        private void newToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.Text = "";
            treeView2.Nodes.Clear();
            treeView3.Nodes.Clear();
            resultsListBox.Items.Clear();
            treeView1.CollapseAll();
            treeView1.Nodes[0].Expand();
            treeView1.Nodes[1].Expand();
            this.ActiveControl = codeTextBox1;
            _currentlyLoadedTransformation = null;
        }

        private void redoToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.TextRedo();
        }

        private void undoToolStripMenuItem_Click(object sender, EventArgs e)
        {           
             codeTextBox1.TextUndo();
        }
       
                
        private void closeDefineModeXToolStripMenuItem_Click(object sender, EventArgs e)
        {
            defineModeOff();            
        }

        private void defineModeOn() 
        {
            closeDefineModeXToolStripMenuItem.Visible = true;
            codeBoxPanel.BackColor = Color.SeaGreen;
            this.BackColor = Color.SeaGreen;
            statusBarPanel.BackColor = Color.DarkGreen;
            panelLabelFirstTreeView.BackColor = Color.DarkGreen;                        
            metadataImportToolStripMenuItem.Enabled = false;
            lexiToolStripMenuItem.Enabled = false;            
            _defineStateActive = true;
            codeTextBox1.Text = "";
        }

        private void defineModeOff() 
        { 
            codeBoxPanel.BackColor = Color.SteelBlue;
            this.BackColor = Color.LightSteelBlue;
            statusBarPanel.BackColor = Color.SteelBlue;
            panelLabelFirstTreeView.BackColor = Color.SteelBlue;                        
            metadataImportToolStripMenuItem.Enabled = true;
            lexiToolStripMenuItem.Enabled = true;            
            closeDefineModeXToolStripMenuItem.Visible = false;
            _defineStateActive = false;
            codeTextBox1.Text = "";
        }

        private void operatorToolStripMenuItem_Click(object sender, EventArgs e)
        {

            defineModeOn();
            codeTextBox1.Text = CommonItem.DEFINE_OPERATOR_STATEMENT;
            frmDefineOperator frmDefOperator = new frmDefineOperator();
            if (frmDefOperator.ShowDialog() == DialogResult.OK)
            {
                codeTextBox1.Text = frmDefOperator.getDefineOperatorStatement();
            }
        }

        private void horizontalRulesetToolStripMenuItem_Click(object sender, EventArgs e)
        {
            defineModeOn();
            codeTextBox1.Text = CommonItem.DEFINE_DATAPOINTRULESET_STATEMENT;
            frmDefineDatapointRule frmDefDatRule = new frmDefineDatapointRule();
            if (frmDefDatRule.ShowDialog() == DialogResult.OK)
            {                
                codeTextBox1.Text = frmDefDatRule.getDefineDatapointRulesetStatement();
            }
        }

        private void verticalRulesetToolStripMenuItem_Click(object sender, EventArgs e)
        {
            defineModeOn();
            codeTextBox1.Text = CommonItem.DEFINE_HIERARCHICALRULESET_STATEMENT;
            frmDefineHierarchicalRule frmHRule = new frmDefineHierarchicalRule();
            if (frmHRule.ShowDialog() == DialogResult.OK)
            {
                codeTextBox1.Text = frmHRule.getDefineHierarchicalRulesetStatement();
            }
        }

        private void frmMain_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit();
        }

        private void fromSDMXWebServiceToolStripMenuItem_Click(object sender, EventArgs e)
        {
            frmMetadataImport frmMetadata = new frmMetadataImport();
            frmMetadata.ShowDialog();
        }           

        private void findToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.ShowFindDialog();
        }

        private void replaceToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.ShowReplaceDialog();
        }

        private void commentSelectedLinesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.CommentSelectedLines();
        }

        private void uncommentSelectedLinesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.UncommentSelectedLines();
        }

        private void clearAllToolStripMenuItem_Click(object sender, EventArgs e)
        {
            codeTextBox1.Text = "";
        }        

        private void openToolStripMenuItem_Click(object sender, EventArgs e)
        {
            frmOpenTrasformation frmOpenTransf = new frmOpenTrasformation();
            frmOpenTransf.ShowDialog();
            if (frmOpenTransf.DialogResult == DialogResult.OK && !String.IsNullOrEmpty(frmOpenTransf.TrasformationSchemeID))
            {
                string trans= LoadTrasformation(frmOpenTransf.TrasformationSchemeID);
                if (!String.IsNullOrEmpty(trans))
                {
                    codeTextBox1.Text = trans;
                }
                else 
                {
                    MessageBox.Show("Transformation not present into the DB", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }

        private void artefactCreationToolStripMenuItem_Click(object sender, EventArgs e)
        {
            frmArtefactManager frmValueDomain = new frmArtefactManager();
            frmValueDomain.ShowDialog();
        }

        #region Semantic validation

        private void SemanticValidation(WebServiceConnector.RestWebConnector RestWS)
        {
            try
            {
                if (String.IsNullOrEmpty(this.codeTextBox1.Text.Trim())) return;

                
                Dictionary<string, string> args = new Dictionary<string, string>();
                resultsListBox.Items.Clear();
                string result = RestWS.SendPostRequest(this.codeTextBox1.Text);

                if (CommonItem.IsXml(result)) 
                {
                    saveFileDialog1.Filter = "XML files (*.xml)|*.sql|All files (*.*)|*.*";
                    saveFileDialog1.Title = "Save SDMX persistant structures";

                    if (saveFileDialog1.ShowDialog() == DialogResult.OK)
                    {
                        if (!String.IsNullOrEmpty(saveFileDialog1.FileName))
                        {                            
                            System.IO.File.WriteAllText(saveFileDialog1.FileName, result);
                            MessageBox.Show("SDMX persistant structure saved");                            
                            this.StatusLabel.Text = "Transformation semantically valid!";
                        }
                    }
                    return;
                }

                BaseJSonMessage JSonResult = JsonConvert.DeserializeObject<BaseJSonMessage>(result);
                if (JSonResult.errorResponse == null)
                {
                    this.resultsListBox.Items.Add("Semantic validation succeded!");
                    this.StatusLabel.Text = "Transformation semantically valid!";
                }
                else
                {
                    this.resultsListBox.Items.Add("Semantic validation failed!");

                    if (JSonResult.errorResponse.code == null)
                    {
                        string[] errorrs = Regex.Split(JSonResult.errorResponse.message, @"<;>{1}");

                        int i = 1;
                        foreach (string str in errorrs)
                        {
                            if (str != "")
                            {
                                string[] compError = str.Split("--".ToCharArray());
                                string ClearedResult = i + ") " + compError[2] + " - " + compError[0].Replace("nulline", "line") + " - " + compError[1];
                                this.resultsListBox.Items.Add(ClearedResult);
                            }
                            i++;
                        }
                        this.StatusLabel.Text = "Syntactic error.";
                    }
                    else
                    {
                        this.resultsListBox.Items.Add(JSonResult.errorResponse.message);
                        this.StatusLabel.Text = "Semantic error";
                    }
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        private void semanticValidationToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SEMANTIC_VALIDATION_WEBMETHOD + "/" + CommonItem.SEMANTIC_RESULT.json.ToString());
                SemanticValidation(RestWS);
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void semanticValidationWithSDMXUpdatesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SEMANTIC_VALIDATION_WEBMETHOD + "/" + CommonItem.SEMANTIC_RESULT.xml.ToString());
                SemanticValidation(RestWS);
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }
        
        #endregion

        #region Translate

        public void SQLTranslation(WebServiceConnector.RestWebConnector RestWS, string DialectType) 
        {
            try
            {
                if (String.IsNullOrEmpty(this.codeTextBox1.Text.Trim())) return;

                string sqlCode;
                Dictionary<string, string> args = new Dictionary<string, string>();
                resultsListBox.Items.Clear();
                string result = RestWS.SendPostRequest(this.codeTextBox1.Text);
                BaseJSonMessage JSonResult = JsonConvert.DeserializeObject<BaseJSonMessage>(result);
                if (JSonResult.errorResponse == null)
                {
                    sqlCode = String.Join("\r\n", JSonResult.resultSQL);

                    //frmSQLViewer frmSQLV = new frmSQLViewer();
                    frmDataExecution frmSQLV = new frmDataExecution();
                    frmSQLV.Text = "SQL Viewer - " + DialectType;                    
                    frmSQLV.Show();
                    frmSQLV.SQLCode = sqlCode;
                }
                else
                {
                    this.resultsListBox.Items.Add("Synthesis failed!");

                    if (JSonResult.errorResponse.code == null)
                    {
                        string[] errorrs = Regex.Split(JSonResult.errorResponse.message, @"<;>{1}");

                        int i = 1;
                        foreach (string str in errorrs)
                        {
                            if (str != "")
                            {
                                string[] compError = str.Split("--".ToCharArray());
                                string ClearedResult = i + ") " + compError[2] + " - " + compError[0].Replace("nulline", "line") + " - " + compError[1];
                                this.resultsListBox.Items.Add(ClearedResult);
                            }
                            i++;
                        }
                        this.StatusLabel.Text = "Syntactic error.";
                    }
                    else
                    {
                        this.resultsListBox.Items.Add(JSonResult.errorResponse.message);
                        this.StatusLabel.Text = "Semantic error";
                    }
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        private void SQLToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                UncheckSemanticDialectAll((ToolStripDropDownItem)sender);
               WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SYNTHESIS_VALIDATION_WEBMETHOD + "/" + CommonItem.SQL_DIALECT.oracleSql);
               SQLTranslation(RestWS, CommonItem.SQL_DIALECT.oracleSql.ToString());
             }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void mySQLToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                UncheckSemanticDialectAll((ToolStripDropDownItem)sender);
                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SYNTHESIS_VALIDATION_WEBMETHOD + "/" + CommonItem.SQL_DIALECT.mySql);
                SQLTranslation(RestWS, CommonItem.SQL_DIALECT.mySql.ToString());
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void javaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                UncheckSemanticDialectAll((ToolStripDropDownItem)sender);
                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SYNTHESIS_VALIDATION_WEBMETHOD + "/" + CommonItem.SQL_DIALECT.sqlServer);
                SQLTranslation(RestWS, CommonItem.SQL_DIALECT.sqlServer.ToString());
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void postgrestSQLToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                UncheckSemanticDialectAll((ToolStripDropDownItem)sender);
                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SYNTHESIS_VALIDATION_WEBMETHOD + "/" + CommonItem.SQL_DIALECT.postgreSql);
                SQLTranslation(RestWS , CommonItem.SQL_DIALECT.postgreSql.ToString());
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void UncheckSemanticDialectAll(ToolStripDropDownItem sender)
        {
            if (sender.GetType() == TranslatetoolStripButton.GetType()) return;
            pLSQLToolStripMenuItem1.Checked=false;
            tSQLToolStripMenuItem1.Checked=false;
            mySqlToolStripMenuItem2.Checked=false;
            posToolStripMenuItem.Checked = false;
            ((System.Windows.Forms.ToolStripMenuItem)(sender)).Checked = true;
            
        }
        private void TranslatetoolStripButton_ButtonClick(object sender, EventArgs e)
        {
            try
            {
                if (pLSQLToolStripMenuItem1.Checked)
                {
                    SQLToolStripMenuItem_Click(sender, e);
                    return;
                }
                if (tSQLToolStripMenuItem1.Checked)
                {
                    javaToolStripMenuItem_Click(sender, e);
                    return;
                }
                if (mySqlToolStripMenuItem2.Checked)
                {
                    mySQLToolStripMenuItem_Click(sender, e);
                    return;
                }
                if (posToolStripMenuItem.Checked)
                {                    
                    postgrestSQLToolStripMenuItem_Click(sender, e);
                    return;
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        #endregion

        #region GetSQLPackage

        private void GetSQLPackage(WebServiceConnector.RestWebConnector RestWS, string DialectType) 
        {
            try
            {
                saveFileDialog1.Filter = "SQL files (*.sql)|*.sql|All files (*.*)|*.*";
                saveFileDialog1.Title="Save SQL package - " + DialectType;
                if (saveFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    if (!String.IsNullOrEmpty(saveFileDialog1.FileName))
                    {
                        string result = RestWS.SendGetFileRequest();
                        System.IO.File.WriteAllText(saveFileDialog1.FileName, result);
                        MessageBox.Show("VTL package saved");
                    }
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void tSQLToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                UncheckVtlPackageDialectAll((ToolStripDropDownItem)sender);
                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SQL_PACKAGE + "/" + CommonItem.SQL_DIALECT.sqlServer);
                GetSQLPackage(RestWS, CommonItem.SQL_DIALECT.sqlServer.ToString());
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        private void pLSQLToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                UncheckVtlPackageDialectAll((ToolStripDropDownItem)sender);
                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SQL_PACKAGE + "/" + CommonItem.SQL_DIALECT.oracleSql);
                GetSQLPackage(RestWS, CommonItem.SQL_DIALECT.oracleSql.ToString());
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }        

        private void mySQLToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            try
            {
                UncheckVtlPackageDialectAll((ToolStripDropDownItem)sender);
                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SQL_PACKAGE + "/" + CommonItem.SQL_DIALECT.mySql);
                GetSQLPackage(RestWS,  CommonItem.SQL_DIALECT.mySql.ToString());
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        private void postgrestSQLToolStripMenuItem1_Click(object sender, EventArgs e)
        {
            try
            {
                UncheckVtlPackageDialectAll((ToolStripDropDownItem)sender);
                WebServiceConnector.RestWebConnector RestWS = new WebServiceConnector.RestWebConnector(CommonItem.CurrentSettings.ValidationWebService.WebService_public_URL + CommonItem.SQL_PACKAGE + "/" + CommonItem.SQL_DIALECT.postgreSql);
                GetSQLPackage(RestWS, CommonItem.SQL_DIALECT.postgreSql.ToString());
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }

        private void GetPackagetoolStripButton_ButtonClick(object sender, EventArgs e)
        {
            try
            {
                if (pLSQLToolStripMenuItem2.Checked)
                {
                    pLSQLToolStripMenuItem_Click(sender, e);
                    return;
                }
                if (tSQLToolStripMenuItem2.Checked)
                {
                    tSQLToolStripMenuItem_Click(sender, e);
                    return;
                }
                if (mySqlToolStripMenuItem3.Checked)
                {
                    mySQLToolStripMenuItem1_Click(sender, e);
                    return;
                }
                if (postgreSqlToolStripMenuItem.Checked)
                {
                    postgrestSQLToolStripMenuItem1_Click(sender, e);
                    return;
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void UncheckVtlPackageDialectAll(ToolStripDropDownItem sender)
        {
            if (sender.GetType() == GetPackagetoolStripButton.GetType()) return;
            pLSQLToolStripMenuItem2.Checked = false;
            tSQLToolStripMenuItem2.Checked = false;
            mySqlToolStripMenuItem3.Checked = false;
            postgreSqlToolStripMenuItem.Checked = false;
            ((System.Windows.Forms.ToolStripMenuItem)(sender)).Checked = true;

        }

        #endregion

        private void sDMXFileToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileDialog1.Title = "Import from SDMX structure file";
            openFileDialog1.Filter = "SDMX structure file|*.xml|All files|*.*";
            if (openFileDialog1.ShowDialog() == DialogResult.OK) 
            {
                if (!String.IsNullOrEmpty(openFileDialog1.FileName)) 
                {
                    frmMedataImportFile frmImport = new frmMedataImportFile(openFileDialog1.FileName);
                    frmImport.ShowDialog();
                }
            }
        }
        

    }
}
