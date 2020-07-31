using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using ArtefactInfo.model;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.net;

namespace VTL_Editor_PL.gui
{
    public partial class UserDefinedValueDomainSubsetPanel : UserControl
    {
        private const string DESCRIBED_SUFFIX = " - <Described>";
        private BaseArtefactInfo _selectedParent;

        public struct ValValueDomainObject 
        {
            public string Vtl_ID;
            public int value_seq_id;
            public string name;

            public override string ToString()
            { 	
                return Vtl_ID + " - " + name ;
            }
        }

        private class ValueDomainContainer
        {
            public string vtlID;
            public BaseArtefactInfo componentObject;
            public bool isEnumerated;
            public string restrictionText;

            public ValueDomainContainer(BaseArtefactInfo compInfo)
            {
                vtlID = compInfo.vtlId;
                componentObject = compInfo;
                isEnumerated = ((ArtefactInfo.model.ValueDomainInfo)compInfo).isEnumerated;
                restrictionText = ((ArtefactInfo.model.ValueDomainInfo)compInfo).value_restriction;
            }

                public override string ToString() 
            {
                if (isEnumerated)
                    return vtlID;
                else
                    return vtlID + DESCRIBED_SUFFIX;
            }
        }                         

        public string describedCriterion 
        {
            get { return this.DescribedCriterionTextBox.Text; }
            set { this.DescribedCriterionTextBox.Text = value; }
        }       

        public BaseArtefactInfo getParentValueDomain()
        {
            return _selectedParent;
        } 

        public UserDefinedValueDomainSubsetPanel()
        {
            InitializeComponent();
        }

        private void UserDefinedValueDomainPanel_Load(object sender, EventArgs e)
        {                                    
            EnumeratedPanel.Enabled = false;
            EnumeratedPanel.BackColor = Color.Gray;

            FillValueDomainListBox();
            ValueDomainListComboBox.SelectedIndex = 0;
            _selectedParent = null;
        }       
        
        private void FillValueDomainListBox()
        {
            try
            {
                CommonItem.WaitOn();
                BaseArtefactInfo[] dsList;

                VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);
                dsList = VTL_service.GetValueDomains();

                if (dsList == null)
                {
                    MessageBox.Show("There are not Value Domain available into the database. Please, import the metadata from the metadata repository. (Metadata import)", "Empty", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    return;
                }

                
                this.ValueDomainListComboBox.Items.Clear();
                this.ValueDomainListComboBox.Items.Add("Select a value domain parent...");

                foreach (BaseArtefactInfo aInfo in dsList)
                {                    
                    ValueDomainListComboBox.Items.Add(new ValueDomainContainer(aInfo));                       
                }
                CommonItem.WaitOff();

            }
            catch (Exception)
            {
                throw;
            }
        }

        private void ValueDomainListComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                BaseComponentInfo[] values;
                KeyValuePair<int, string>[] value_seq_id;
                DescribedCriterionTextBox.Clear();
                valDomParentDescrTextBox.Clear();
                ValuesCheckedListBox.Items.Clear();
                if (this.ValueDomainListComboBox.SelectedItem.GetType().ToString() != "System.String")
                {
                    ValueDomainContainer valCont = (ValueDomainContainer)this.ValueDomainListComboBox.SelectedItem;
                    _selectedParent = valCont.componentObject;

                    if (valCont.isEnumerated)
                    {
                        DescribedPanel.Enabled = false;
                        DescribedPanel.BackColor = Color.Gray;
                        EnumeratedPanel.Enabled = true;
                        EnumeratedPanel.BackColor = Color.SteelBlue;

                        VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);

                        values = VTL_service.GetValueDomainValues(valCont.vtlID);
                        value_seq_id = VTL_service.GetValueDomain_seq_id(valCont.vtlID);

                        if (values.Count() == 0) 
                        {
                            MessageBox.Show("No values found!", "Values missing", MessageBoxButtons.OK, MessageBoxIcon.Error);
                            return;
                        }

                        foreach (BaseComponentInfo val in values)
                        {
                            ValValueDomainObject tmpVal = new ValValueDomainObject();
                            tmpVal.name = val.name[0].value;
                            tmpVal.Vtl_ID = val.vtlId;
                            var tmpVar = from seq_id in value_seq_id
                                                  where seq_id.Value == val.vtlId
                                                  select seq_id.Key;

                            tmpVal.value_seq_id = (int)tmpVar.First();
                            ValuesCheckedListBox.Items.Add(tmpVal);                                      
                        }
                    }
                    else
                    {
                        DescribedPanel.Enabled = true;
                        DescribedPanel.BackColor = Color.SteelBlue;
                        EnumeratedPanel.Enabled = false;
                        EnumeratedPanel.BackColor = Color.Gray;
                        valDomParentDescrTextBox.Text = valCont.restrictionText;
                    }
                }
                else
                    _selectedParent = null;
                
             }
            catch (Exception)
            {
                throw;
            }
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            ActAllCheckBoxes(true);
        }

        private void linkLabel2_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            ActAllCheckBoxes(false);
        }

        private void linkLabel3_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            for (int i = 0; i <= (ValuesCheckedListBox.Items.Count - 1); i++)
            {
                if (ValuesCheckedListBox.GetItemChecked(i))                
                    ValuesCheckedListBox.SetItemCheckState(i, CheckState.Unchecked);                
                else
                    ValuesCheckedListBox.SetItemCheckState(i, CheckState.Checked);
            }
        }

        private void ActAllCheckBoxes(bool CheckThem)
        {
            for (int i = 0; i <= (ValuesCheckedListBox.Items.Count - 1); i++)
            {
                if (CheckThem)
                {
                    ValuesCheckedListBox.SetItemCheckState(i, CheckState.Checked);
                }
                else
                {
                    ValuesCheckedListBox.SetItemCheckState(i, CheckState.Unchecked);
                }
            }
        }

        public void setEnumeratedDescribedView(bool Enumerated, BaseArtefactInfo artInfo) 
        {
            try
            {
                DescribedPanel.Enabled = false;
                EnumeratedPanel.Enabled = false;

                if (Enumerated)
                {
                    EnumeratedPanel.Enabled = true;
                    ValueDomainListComboBox.SelectedIndex = ValueDomainListComboBox.FindStringExact(((ArtefactInfo.model.ValueDomainSubsetInfo)(artInfo)).vd_id_ref);
                    VTLInt_Service.ServiceClient VTL_service = VTLInt_ServiceManager.GetClient(CommonItem.CurrentSettings.InteractionWebService);

                    BaseComponentInfo[] values = VTL_service.GetSubSetList(((ArtefactInfo.model.ValueDomainSubsetInfo)(artInfo)).vtlId);

                    foreach (BaseComponentInfo bs in values) 
                    {                    
                        for (int i=0;i<ValuesCheckedListBox.Items.Count;i++)
                        {
                            ValValueDomainObject vl=(ValValueDomainObject)ValuesCheckedListBox.Items[i];
                            if (vl.value_seq_id.ToString() == bs.seq_id)
                                ValuesCheckedListBox.SetItemCheckState(i, CheckState.Checked);
                        }                    
                    }
                }
                else
                {
                    DescribedPanel.Enabled = true;
                    ValueDomainListComboBox.SelectedIndex = ValueDomainListComboBox.FindStringExact(((ArtefactInfo.model.ValueDomainSubsetInfo)(artInfo)).vd_id_ref + DESCRIBED_SUFFIX);
                    DescribedCriterionTextBox.Text = ((ArtefactInfo.model.ValueDomainSubsetInfo)(artInfo)).set_criterion_id;
                }
             }
            catch (Exception)
            {
                throw;
            }
        }

        public ValValueDomainObject[] getCheckedItems()
        {
            try
            {
                List<ValValueDomainObject> tmpList = new List<ValValueDomainObject>();
                foreach (ValValueDomainObject val in ValuesCheckedListBox.CheckedItems)
                {
                    tmpList.Add(val);
                }
                return tmpList.ToArray();  
            }
            catch (Exception)
            {
                throw;
            }
        }
  
    }
}
