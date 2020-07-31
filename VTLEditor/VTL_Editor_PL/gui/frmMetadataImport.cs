using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using ArtefactInfo.model;
using SDMXMetadataLoader.service;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.config;
using VTL_Editor_PL.classes.net;
using VTL_Editor_PL.classes.tool;

namespace VTL_Editor_PL.gui
{
    public partial class frmMetadataImport : Form
    {        

        public frmMetadataImport()
        {
            InitializeComponent();
        }

        private void frmMetadataImport_Load(object sender, EventArgs e)
        {
            TreeNode MetadataWebService_Node = treeView1.Nodes.Add("Metadata Web Services");
            treeView1.ImageList = imageList1;            
            foreach (MainApplicationSettings.WebServiceInfo wsInfo in CommonItem.CurrentSettings.WebServices)
            {
                TreeNode tmp = MetadataWebService_Node.Nodes.Add("WS", wsInfo.WebService_Name);
                tmp.ImageIndex = 1;
                tmp.StateImageIndex = 1;
                TreeNode tmp2=tmp.Nodes.Add(wsInfo.WebService_ID.ToString(), "DataStructure");
                tmp2.ImageIndex = 2;
                tmp2.SelectedImageIndex = 2;
                tmp2 = tmp.Nodes.Add(wsInfo.WebService_ID.ToString(), "DataFlow");
                tmp2.ImageIndex = 3;
                tmp2.SelectedImageIndex = 3;
                tmp2 = tmp.Nodes.Add(wsInfo.WebService_ID.ToString(), "Codelist");
                tmp2.ImageIndex = 4;
                tmp2.SelectedImageIndex = 4;
            }

            this.switchTreeView.SwitchingPhase += switchTreeView_SwitchingPhase;
            treeView1.Nodes[0].Expand();
            treeView1.Nodes[0].ImageIndex = 5;
            treeView1.Nodes[0].SelectedImageIndex = 5;
            treeView1.ShowNodeToolTips = true;
        }

        private void LoadDataStructures(string webserviceId, TreeNode parent)
        {
            try
            {
                CommonItem.WaitOn();

                List<BaseArtefactInfo> dsList;
                bool raiseError = false;

                MetadataLoader mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(webserviceId)));
                dsList = mtl.LoadDataStructures21();

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                BaseArtefactInfo[] alreadyPresent = VTL_service.GetDataStructures();

                foreach (BaseArtefactInfo aInfo in dsList)
                {
                    string text;
                    if (switchTreeView.isCode())
                        text = aInfo.vtlId;
                    else
                        if (aInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = aInfo.vtlId;
                        }
                        else
                            text = aInfo.name[0].value.ToString();

                    TreeNode tmp = parent.Nodes.Add(aInfo.sdmxId, text);
                    tmp.ForeColor = Color.Black;

                    if (alreadyPresent != null)
                    {
                        var found = from i in alreadyPresent
                                    where i.vtlId == text
                                    select i;
                        if (found.Count() > 0) tmp.ForeColor = Color.DarkGreen;
                    }

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

        private void LoadDataFlows(string webserviceId, TreeNode trNode)
        {
            try
            {
                CommonItem.WaitOn();
                List<BaseArtefactInfo> dfList;
                bool raiseError = false;
                string vtlIdCode=null;

                MetadataLoader mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(webserviceId)));
                dfList = mtl.LoadDataflows21();

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                BaseArtefactInfo[] alreadyPresent = VTL_service.GetDataSets();
                BaseArtefactInfo[] alreadyPresentDsD = VTL_service.GetDataStructures();

                foreach (BaseArtefactInfo aInfo in dfList)
                {
                    string text;
                    if (switchTreeView.isCode())
                        text = aInfo.sdmxId;
                    else
                        if (aInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = aInfo.vtlId;
                        }
                        else
                            text = aInfo.name[0].value.ToString();

                    vtlIdCode=CommonConstant.ToGlobalID(aInfo.sdmxId,aInfo.sdmxAgency, aInfo.sdmxVersion);
                    TreeNode tmp = trNode.Nodes.Add(vtlIdCode);
                    tmp.ForeColor = Color.Gray;

                    string dsdInfo = CommonConstant.ToGlobalID(((ArtefactInfo.model.DataSetInfo)(aInfo)).sdmx_DataStructure_id , ((DataSetInfo)aInfo).sdmx_DataStructure_agency , ((DataSetInfo)aInfo).sdmx_DataStructure_version);
                    if (alreadyPresentDsD!=null)
                    {
                        var found = from i in alreadyPresentDsD
                                    where (CommonConstant.ToGlobalID(i.sdmxId,i.sdmxAgency,i.sdmxVersion)) == dsdInfo
                                    select i;
                        if (found.Count() > 0) tmp.ForeColor = Color.Black;
                    }

                    if (alreadyPresent != null)
                    {
                        var found = from i in alreadyPresent
                                    where (i.vtlId) == vtlIdCode
                                    select i;
                        if (found.Count() > 0) tmp.ForeColor = Color.DarkGreen;
                    }

                    tmp.Tag = new ArtefactNodeInfo(webserviceId, aInfo, ArtefactNodeInfo.ArtefactType.DataFlows);
                    tmp.ToolTipText = "Datastructure ref: " + ((ArtefactInfo.model.DataSetInfo)(aInfo)).sdmxAgency + ":" + ((ArtefactInfo.model.DataSetInfo)(aInfo)).datastructure_id + "(" + ((ArtefactInfo.model.DataSetInfo)(aInfo)).sdmx_DataStructure_version + ")";                    
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

        private void LoadCodelist(string webserviceId, TreeNode trNode)
        {
            try
            {
               CommonItem.WaitOn();
                List<BaseArtefactInfo> vlList;
                bool raiseError = false;

                MetadataLoader mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(webserviceId)));
                vlList = mtl.LoadCodelist21();

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);              
                BaseArtefactInfo[] alreadyPresent = VTL_service.GetValueDomains();

                foreach (BaseArtefactInfo aInfo in vlList)
                {
                    string text;
                    if (switchTreeView.isCode())
                        text = aInfo.vtlId;
                    else
                        if (aInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = aInfo.vtlId;
                        }
                        else
                            text = aInfo.name[0].value.ToString();

                    TreeNode tmp = trNode.Nodes.Add(aInfo.sdmxId, text);
                    tmp.ForeColor = Color.Black;

                    if (alreadyPresent != null)
                    {
                        var found = from i in alreadyPresent
                                    where i.vtlId == text
                                    select i;
                        if (found.Count() > 0) tmp.ForeColor = Color.DarkGreen;
                    }

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

        private void LoadDataFlowComponents(string webserviceId, string dsd_id, TreeNode treeNodeSecLevel)
        {            
            try
            {
                List<BaseArtefactInfo> dsList;
                bool raiseError = false;
               CommonItem.WaitOn();
                string[] globIdent = CommonConstant.splitGlobalID(dsd_id);

                MetadataLoader mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(webserviceId)));
                string dsdID = ((ArtefactInfo.model.DataSetInfo)(((VTL_Editor_PL.classes.tool.ArtefactNodeInfo)(treeNodeSecLevel.Tag)).artefactInfo)).sdmx_DataStructure_id;
                string dsdAgencyID = ((ArtefactInfo.model.DataSetInfo)(((VTL_Editor_PL.classes.tool.ArtefactNodeInfo)(treeNodeSecLevel.Tag)).artefactInfo)).sdmx_DataStructure_agency;
                string dsdVersionID = ((ArtefactInfo.model.DataSetInfo)(((VTL_Editor_PL.classes.tool.ArtefactNodeInfo)(treeNodeSecLevel.Tag)).artefactInfo)).sdmx_DataStructure_version;

                dsList = mtl.LoadDataStructuresComponents21(dsdID, dsdAgencyID, dsdVersionID);
                //dsList = mtl.LoadDataStructuresComponents21(globIdent[0], globIdent[1], globIdent[2]);


                //Visit components
                if (dsList.Count > 1) throw new Exception("More than one DSD with an ID");

                foreach (BaseComponentInfo compInfo in dsList[0].DataStructureDetails.Components)
                {
                    string text;
                    if (switchTreeView.isCode())
                        if (string.IsNullOrEmpty(((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).valuedomain_id))
                        {
                            text = compInfo.vtlId;
                        }
                        else
                        {
                            text = ((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).valuedomain_id;
                        }
                    else
                        if (compInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = ((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).valuedomain_id;
                        }
                        else
                            text = compInfo.name[0].value.ToString();
                    compInfo.datastructure_id_ref = dsd_id;

                    TreeNode tmp = treeNodeSecLevel.Nodes.Add(((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).valuedomain_id, text);

                    //if (((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).role == "TimeDimension" || ((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).role == "PrimaryMeasure")
                    //{
                    //    tmp.ForeColor = Color.Gray;
                    //}

                    tmp.ForeColor = Color.Gray;
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

        private void LoadDataStructuresComponents(string webserviceId, string dsd_id, TreeNode treeNodeSecLevel)
        {
            try
            {
                List<BaseArtefactInfo> dsList;
                bool raiseError = false;
               CommonItem.WaitOn();
                string[] globIdent = CommonConstant.splitGlobalID(dsd_id);

                MetadataLoader mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(webserviceId)));               
                dsList = mtl.LoadDataStructuresComponents21(globIdent[0], globIdent[1], globIdent[2]);


                //Visit components
                if (dsList.Count > 1) throw new Exception("More than one DSD with an ID");

                foreach (BaseComponentInfo compInfo in dsList[0].DataStructureDetails.Components)
                {
                    string text;
                    if (switchTreeView.isCode())
                        if (string.IsNullOrEmpty(((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).valuedomain_id))
                        {
                            text = compInfo.vtlId;
                        }
                        else
                        {
                            text = ((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).valuedomain_id;
                        }
                    else
                        if (compInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = ((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).valuedomain_id;
                        }
                        else
                            text = compInfo.name[0].value.ToString();
                    compInfo.datastructure_id_ref = dsd_id;

                    TreeNode tmp = treeNodeSecLevel.Nodes.Add(((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).valuedomain_id, text);

                    //if (((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).role == "TimeDimension" || ((ArtefactInfo.model.DataStructureComponentInfo)(compInfo)).role == "PrimaryMeasure")
                    //{
                    //    tmp.ForeColor = Color.Gray;
                    //}

                    tmp.ForeColor = Color.Gray;
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

        private void LoadCodelistCodes(string webserviceId, string dsd_id, TreeNode treeNodeSecLevel)
        {
            try
            {
                List<BaseArtefactInfo> dsList;
                bool raiseError = false;
                CommonItem.WaitOn();
                                
                
                MetadataLoader mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(webserviceId)));

                string[] ident = CommonConstant.splitGlobalID(dsd_id);
                dsList = mtl.LoadCodelistCodes21(ident[0], ident[1], ident[2]);                

                //Visit components

                foreach (BaseComponentInfo compInfo in dsList[0].DataStructureDetails.Components)
                {
                    string text;
                    if (switchTreeView.isCode())
                        text = compInfo.vtlId;
                    else
                        if (compInfo.name.Count == 0)
                        {
                            raiseError = true;
                            text = compInfo.vtlId;
                        }
                        else
                            text = compInfo.name[0].value.ToString();

                    TreeNode tmp = treeNodeSecLevel.Nodes.Add(compInfo.vtlId, text);
                    tmp.ForeColor = Color.Gray;
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

        private void treeView1_NodeMouseClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            if (e.Node.Nodes.Count > 0) return;

            CommonItem.WaitOn();

            switch (e.Node.Text)
            {
                case "DataStructure":
                    
                    LoadDataStructures(e.Node.Name, e.Node);
                    e.Node.Expand();
                    break;
                case "DataFlow":
                    LoadDataFlows(e.Node.Name, e.Node);
                    e.Node.Expand();
                    break;
                case "Codelist":
                    LoadCodelist(e.Node.Name, e.Node);
                    e.Node.Expand();
                    break;
            }

            CommonItem.WaitOff();
           
        }

        private void treeView1_MouseDown(object sender, MouseEventArgs e)
        {
            if ((e.Button.ToString() == "Right") && ((System.Windows.Forms.TreeView)sender).SelectedNode != null)
            { 
               TreeNode clickedNode=((System.Windows.Forms.TreeView)sender).SelectedNode;
               ArtefactNodeInfo artInfo = (ArtefactNodeInfo)clickedNode.Tag;
               if (artInfo != null)
               {
                   if (clickedNode.ForeColor == Color.Black || clickedNode.ForeColor == Color.DarkGreen)
                   {
                       ContextMenu cm = new ContextMenu();
                       if (clickedNode.ForeColor == Color.Black)
                       {
                           MenuItem tmpMen = cm.MenuItems.Add("Import the " + artInfo.artType.ToString() + " into VTL DB", cm_ItemImportClicked);
                           tmpMen.Tag = clickedNode;
                       }

                       MenuItem tmpMen2 = cm.MenuItems.Add("Drill down", cm_DrillDowntClicked);
                       tmpMen2.Tag = clickedNode;

                       clickedNode.ContextMenu = cm;
                       cm.Show(((System.Windows.Forms.TreeView)sender), new Point(e.X, e.Y));
                   }
               }
            }
        }        

        private void cm_ItemImportClicked(object sender, System.EventArgs e)
        {
            try
            {
                TreeNode tmpNode = (TreeNode)((MenuItem)sender).Tag;
                ArtefactNodeInfo artInfo = (ArtefactNodeInfo)tmpNode.Tag;
                VTLInt_Service.ServiceClient VTLMan = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);

                MetadataLoader mtl = null;

                //VTLMan.LineCountEvent += LineCountEventManager;
           

                switch (artInfo.artType)
                {
                    case ArtefactNodeInfo.ArtefactType.ValueDomains:
                        string vd_id = artInfo.artefactInfo.sdmxId;
                        string vd_agency = artInfo.artefactInfo.sdmxAgency;
                        string vd_version = artInfo.artefactInfo.sdmxVersion;

                        if (MessageBox.Show("Do you want import into the VTL database the codelist: \n " + vd_id + " " + vd_agency + " " + vd_version, "Import codelist", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                        {
                            CommonItem.WaitOn();

                            mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(artInfo.webServiceID)));
                            List<BaseArtefactInfo> dsList = mtl.LoadCodelistCodes21(vd_id, vd_agency, vd_version);

                            VTLMan.InsertValueDomain(vd_id, vd_agency, vd_version, artInfo.artefactInfo.name.ToArray(), dsList.ToArray(), true, BaseArtefactInfo.CREATION_TYPE.SDMX_WS_UPLOAD, ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE.String);

                            CommonItem.WaitOff();

                            MessageBox.Show("Value domain :" + vd_id + " " + vd_agency + " " + vd_version + " successufully imported", "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);
                            tmpNode.ForeColor = Color.Gray;
                        }

                        break;

                    case ArtefactNodeInfo.ArtefactType.DataStructureComponents:
                        string[] globIdent = CommonConstant.splitGlobalID(((ArtefactInfo.model.DataStructureComponentInfo)(artInfo.artefactComponentInfo)).valuedomain_id);

                        string cmp_id = globIdent[0];
                        string cmp_agency = globIdent[1];
                        string cmp_version = globIdent[2];

                        CommonItem.WaitOn();

                        mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(artInfo.webServiceID)));
                        List<BaseArtefactInfo> cmpList = mtl.LoadCodelistCodes21(cmp_id, cmp_agency, cmp_version);

                        VTLMan.InsertValueDomain(cmp_id, cmp_agency, cmp_version, artInfo.artefactInfo.name.ToArray(), cmpList.ToArray(), true, BaseArtefactInfo.CREATION_TYPE.SDMX_WS_UPLOAD, ArtefactInfo.VTL.VTL_Model.VTL_DATATYPE.String);

                        CommonItem.WaitOff();

                        MessageBox.Show("Value domain :" + cmp_id + " " + cmp_agency + " " + cmp_version + " successufully imported", "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        tmpNode.ForeColor = Color.Gray;
                        break;

                    case ArtefactNodeInfo.ArtefactType.DataStructures:
                        string dsd_id = artInfo.artefactInfo.sdmxId;
                        string dsd_agency = artInfo.artefactInfo.sdmxAgency;
                        string dsd_version = artInfo.artefactInfo.sdmxVersion;
                        List<BaseArtefactInfo> valueDomainValueList;
                        List<BaseComponentInfo> componentsList = new List<BaseComponentInfo>();

                        if (MessageBox.Show("Do you want import into the VTL database the Datastructure: \n " + dsd_id + " " + dsd_agency + " " + dsd_version + "\n and all the referenced artefacts?", "Import datastructure", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                        {
                            CommonItem.WaitOn();
                            label1.Text = DateTime.Now.ToString();

                            mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(artInfo.webServiceID)));
                            List<BaseArtefactInfo> componentList = mtl.LoadDataStructuresComponents21(dsd_id, dsd_agency, dsd_version);

                            foreach (BaseComponentInfo comp in componentList[0].DataStructureDetails.Components)
                            {
                                BaseComponentInfo tmpComponentInfo = new BaseComponentInfo();
                                ValueDomainInfoInDataStructure tmpInfo = new ValueDomainInfoInDataStructure();
                                string[] globalId = null;

                                tmpComponentInfo.vtlId = comp.vtlId;
                                tmpComponentInfo.name = comp.name;
                                tmpComponentInfo.datastructure_id_ref = comp.datastructure_id_ref;
                                tmpComponentInfo.seq_id = comp.seq_id;

                                switch (((ArtefactInfo.model.DataStructureComponentInfo)(comp)).role)
                                {
                                    case "Dimension":
                                        tmpComponentInfo.Role = "Identifier";
                                        if (!String.IsNullOrEmpty(((ArtefactInfo.model.DataStructureComponentInfo)(comp)).valuedomain_id))
                                        {
                                            globalId = CommonConstant.splitGlobalID(((ArtefactInfo.model.DataStructureComponentInfo)(comp)).valuedomain_id);                                                
                                        }
                                        break;
                                    case "TimeDimension":
                                        tmpComponentInfo.Role = "Identifier";
                                        globalId = CommonConstant.splitGlobalID(CommonItem.CurrentSettings.TIME_PERIOD_default_valueDomainID);
                                        break;
                                    case "PrimaryMeasure":
                                        tmpComponentInfo.Role = "Measure";
                                        globalId = CommonConstant.splitGlobalID(CommonItem.CurrentSettings.OBS_default_valueDomainID);
                                        break;
                                }
                                    
                                tmpInfo.vd_id = globalId[0];
                                tmpInfo.vd_agency = globalId[1];
                                tmpInfo.vd_version = globalId[2];

                                valueDomainValueList = mtl.LoadCodelistCodes21(globalId[0], globalId[1], globalId[2]);
                                
                                if (valueDomainValueList!=null)   
                                    tmpInfo.values = valueDomainValueList;                                    

                                if (comp.name != null)
                                    tmpInfo.names = comp.name;
                                else 
                                {
                                    tmpInfo.names = new List<LocalizedValue>();
                                    tmpInfo.names.Add(new LocalizedValue("EN", comp.vtlId));
                                }

                                tmpComponentInfo.ValueDomainInfo=tmpInfo;

                                componentsList.Add(tmpComponentInfo);                                
                            }
                            try
                            {
                                VTLMan.InsertDataStructure(dsd_id, dsd_agency, dsd_version, artInfo.artefactInfo.name.ToArray(), componentsList.ToArray(), BaseArtefactInfo.CREATION_TYPE.SDMX_WS_UPLOAD);
                            }
                            catch (TimeoutException ext) 
                            {
                                MessageBox.Show("Time out exception");
                                return;
                            }

                            label2.Text = DateTime.Now.ToString();
                            CommonItem.WaitOff();

                            MessageBox.Show("Data structures :" + dsd_id + " " + dsd_agency + " " + dsd_version + "  and all the linked components successufully imported", "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);
                            tmpNode.ForeColor = Color.Gray;
                        }
                        break;

                    case ArtefactNodeInfo.ArtefactType.DataFlows:
                        string ref_dsd_id = ((DataSetInfo)artInfo.artefactInfo).sdmx_DataStructure_id;
                        string ref_dsd_agency = ((DataSetInfo)artInfo.artefactInfo).sdmx_DataStructure_agency;
                        string ref_dsd_version = ((DataSetInfo)artInfo.artefactInfo).sdmx_DataStructure_version;
                        string df_id = ((DataSetInfo)artInfo.artefactInfo).sdmxId;
                        string df_agency = ((DataSetInfo)artInfo.artefactInfo).sdmxAgency;
                        string df_version = ((DataSetInfo)artInfo.artefactInfo).sdmxVersion;

                        List<BaseArtefactInfo> ds_valueDomainValueList;
                        List<BaseComponentInfo> ds_componentsList = new List<BaseComponentInfo>();

                        if (MessageBox.Show("Do you want import into the VTL database the DataSet: \n " + df_id + " " + df_agency + " " + df_version + "\n and all the referenced artefacts?", "Import datastructure", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                        {
                            CommonItem.WaitOn();
                            label1.Text = DateTime.Now.ToString();

                            mtl = new MetadataLoader(CommonItem.CurrentSettings.QueriesPath, new WebServiceLayer.classes.service.Net.WebServiceLayer(CommonItem.CurrentSettings, int.Parse(artInfo.webServiceID)));
                            List<BaseArtefactInfo> componentList = mtl.LoadDataStructuresComponents21(ref_dsd_id, ref_dsd_agency, ref_dsd_version);

                            BaseComponentInfo tmpInfo;
                            ValueDomainInfoInDataStructure tmpValueDomain;

                            foreach (BaseComponentInfo comp in componentList[0].DataStructureDetails.Components)
                            {
                                tmpInfo = new BaseComponentInfo();
                                tmpInfo.vtlId = comp.vtlId;
                                tmpInfo.name = comp.name;
                                string[] globalId = null;


                                switch (((ArtefactInfo.model.DataStructureComponentInfo)(comp)).role)
                                {
                                    case "Dimension":
                                        tmpInfo.Role = "Identifier";
                                        if (!String.IsNullOrEmpty(((ArtefactInfo.model.DataStructureComponentInfo)(comp)).valuedomain_id))
                                        {
                                            globalId = CommonConstant.splitGlobalID(((ArtefactInfo.model.DataStructureComponentInfo)(comp)).valuedomain_id);
                                        }
                                        break;
                                    case "TimeDimension":
                                        tmpInfo.Role = "Identifier";
                                        globalId = CommonConstant.splitGlobalID(CommonItem.CurrentSettings.TIME_PERIOD_default_valueDomainID);
                                        break;
                                    case "PrimaryMeasure":
                                        tmpInfo.Role = "Measure";
                                        globalId = CommonConstant.splitGlobalID(CommonItem.CurrentSettings.OBS_default_valueDomainID);
                                        break;
                                }
                                tmpValueDomain = new ValueDomainInfoInDataStructure();

                                tmpValueDomain.vd_id = globalId[0];
                                tmpValueDomain.vd_agency = globalId[1];
                                tmpValueDomain.vd_version = globalId[2];

                                if (comp.name != null)
                                    tmpValueDomain.names = comp.name;
                                else
                                {
                                    tmpValueDomain.names = new List<LocalizedValue>();
                                    tmpValueDomain.names.Add(new LocalizedValue("EN", comp.vtlId));
                                }
                                
                                ds_valueDomainValueList = mtl.LoadCodelistCodes21(globalId[0], globalId[1], globalId[2]);

                                if (ds_valueDomainValueList != null)
                                    tmpValueDomain.values = ds_valueDomainValueList;

                                tmpInfo.ValueDomainInfo = tmpValueDomain;
                                ds_componentsList.Add(tmpInfo);  
                            }
                            try
                            {
                                VTLMan.InsertDataSet(ref_dsd_id, ref_dsd_agency, ref_dsd_version, df_id, df_agency, df_version, artInfo.artefactInfo.name.ToArray(), ds_componentsList.ToArray(), BaseArtefactInfo.CREATION_TYPE.SDMX_WS_UPLOAD);
                            }
                            catch (TimeoutException ds_ext) 
                            {
                                MessageBox.Show("Time out exception");
                                return;
                            }

                            label2.Text = DateTime.Now.ToString();
                            CommonItem.WaitOff();

                            MessageBox.Show("Data set :" + df_id + " " + df_agency + " " + df_version + "  and all the linked components successufully imported", "Success", MessageBoxButtons.OK, MessageBoxIcon.Information);
                            tmpNode.ForeColor = Color.Gray;
                        }
                        break;
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

        //private void LineCountEventManager(object sender, VTLDBManager.LineCountEventArgs e) 
        //{
        //    infoLabel.Text = "Script line count: " + e.LineNumber;
        //    infoLabel.Refresh();
        //}

        private void cm_DrillDowntClicked(object sender, System.EventArgs e)
        {
            try
            {
                TreeNode tmpNode = (TreeNode)((MenuItem)sender).Tag;
                ArtefactNodeInfo artInfo = (ArtefactNodeInfo)tmpNode.Tag;

               CommonItem.WaitOn();

                if (tmpNode.Level == 3 && tmpNode.Parent != null && tmpNode.Nodes.Count == 0)
                {                
                    switch (tmpNode.Parent.Text)
                    {
                        case "DataStructure":
                            LoadDataStructuresComponents(artInfo.webServiceID, tmpNode.Text, tmpNode);
                            break;
                        case "DataFlow":
                            LoadDataFlowComponents(artInfo.webServiceID, tmpNode.Text, tmpNode);
                            break;
                        case "Codelist":
                            LoadCodelistCodes(artInfo.webServiceID, tmpNode.Text, tmpNode);
                            break;
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

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private bool SwitchRecursive(TreeNode treeNode)
        {
            bool raiseError = false;
            if (treeNode.Tag != null)
            {
                BaseArtefactInfo bs = ((ArtefactNodeInfo)treeNode.Tag).artefactInfo;
                if (switchTreeView.isCode())
                {
                    if (bs.vtlId != null)
                        treeNode.Text = bs.vtlId;
                    else
                        treeNode.Text = bs.sdmxId;
                }
                else
                    if (bs.name == null)
                        raiseError = true;
                    else if (bs.name.Count == 0)
                        raiseError = true;
                    else
                        treeNode.Text = bs.name[0].value.ToString();
            }            

            // Print each node recursively.  
            foreach (TreeNode tn in treeNode.Nodes)
            {
                SwitchRecursive(tn);
                
            }
            return raiseError;
        } 

        void switchTreeView_SwitchingPhase(object sender, Switch.SwitchEventArgs e)
        {
            try
            {
                bool raiseError = false;
                CommonItem.WaitOn();

                foreach (TreeNode tr in treeView1.Nodes)
                {
                    raiseError=SwitchRecursive(tr);
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
    }
}
