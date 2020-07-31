using System;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;

namespace VTL_Editor_PL.gui
{
    public partial class frmDefineDatapointRule : Form
    {
        private const string _NAME_HELP_TOOLTIP="define datapoint ruleset DPR_1 ( valuedomain flow_type as A, numeric_value as B ) is \n" +
	                                       "\trule1: when A = “CREDIT” or A = “DEBIT” then B >= 0 errorcode “Bad value” errorlevel 10 \n" +
	                                       "\t;rule2: when B=>0 then O >= 1 errorcode “Bad value”\n"	+
                                           "end datapoint ruleset";

        private const string _RULESIGNATURE_HELP_TOOLTIP="define datapoint ruleset <rulesetName> ( valuedomain <listValueDomains> | variable <listVariables> ) is \n" +
                                                         "\tlistValueDomains ::= valueDomain { as vdAlias } { , valueDomain { as vdAlias } }*\n"+
                                                         "\tlistVariables ::= variable { as varAlias } { , variable { as varAlias } }*\n\n"+
                                                        "Sample:\n" +
                                                        "\tdefine datapoint ruleset DPR_1 ( valuedomain flow_type as A, numeric_value as B ) is\n" +
                                                        "\tdefine datapoint ruleset DPR_2 ( variable flow as A, obs_value as B ) is";

        private const string _RULES_HELP_TOOLTIP = "define datapoint ruleset <rulesetName> ( <dpRulesetSignature> ) is\n" +
                                                "\tdpRule\n" +
                                                "\t{ ; dpRule }*\n" +
                                                "end datapoint ruleset\n\n" +
                                                "dpRule:\n" +
                                                "\t{ ruleName : } { when antecedentCondition then } consequentCondition\n" +
                                                "\t{ errorcode errorCode }\n" +
                                                "\t{ errorlevel errorLevel }\n\n" +
                                                "Sample:\n" +
                                                "\twhen F = “CREDIT” or F = “DEBIT” then O >= 0 errorcode “Bad value”\n";

        public frmDefineDatapointRule()
        {
            InitializeComponent();
        }

        private void PreviewButton_Click(object sender, EventArgs e)
        {
            this.PreviewTextBox.Text = getCurrentRule();
        }

        private void AddRuleButton_Click(object sender, EventArgs e)
        {
            this.RulesListBox.Items.Add(getCurrentRule());
        }

        private string getCurrentRule() 
        {
            try
            {
                string ruleName = this.RuleNameTextBox.Text.Trim() != "" ? this.RuleNameTextBox.Text.Trim() + ": " : "";
                string antecetend = this.AntCondTextBox.Text.Trim() != "" ? "when " + this.AntCondTextBox.Text.Trim() + " then " : "";
                string consequent = this.ConsCondTextBox.Text.Trim() != "" ? this.ConsCondTextBox.Text.Trim() : "<consequentCondition>";
                string errorCode = this.ErrorCodeTextBox.Text.Trim() != "" ? " errorcode " + this.ErrorCodeTextBox.Text.Trim() : " ";
                string errorLevel = this.ErrorLevelTxtBox.Text.Trim() != "" ? " errorlevel " + this.ErrorLevelTxtBox.Text.Trim() : "";            

                return ruleName + antecetend + consequent + errorCode + errorLevel;
             }
                catch (Exception ex)
                {
                    CommonItem.ErrManger.ErrorManagement(ex, false, this);
                    return null;
                }
        }

        private void RemoveRuleButton_Click(object sender, EventArgs e)
        {
            try
            {
                if (this.RulesListBox.SelectedIndex > -1)
                    if (this.RulesListBox.SelectedItem != null)
                        this.RulesListBox.Items.RemoveAt(this.RulesListBox.SelectedIndex);
             }
                catch (Exception ex)
                {
                    CommonItem.ErrManger.ErrorManagement(ex, false, this);                    
                }
        }

        public string getDefineDatapointRulesetStatement() 
        {
            try
            {
                string statement="";
                string ruleSetName = this.nameTextBox.Text.Trim() != "" ? this.nameTextBox.Text.Trim() : "<rulesetName>";
                string typeList = this.ValueDomainRadioButton.Checked ? "valuedomain" : "variable";
                string listCollection = "";
                string ruleCollection="\t";
            
                foreach (DataGridViewRow row in this.ValVarDataGridView.Rows)
                {
                    string listObj = "";
                    string listAlias = "";

                    if (row.Cells[0].Value != null)
                        listObj = row.Cells[0].Value.ToString().Trim() != "" ? row.Cells[0].Value.ToString().Trim() : "<" + typeList + ">";

                    if (row.Cells[1].Value != null)
                        listAlias = row.Cells[1].Value.ToString().Trim() != "" ? " as " + row.Cells[1].Value.ToString().Trim() : "";

                    listCollection += listObj + listAlias + ",";
                }

                if (listCollection.Trim() == ",")
                    listCollection = "<" + typeList + "List>";
                else
                    if (listCollection.Length > 0) listCollection = listCollection.Substring(0, listCollection.Length - 2);


            
                foreach (string rule in this.RulesListBox.Items) 
                {
                    if (ruleCollection != "\t") ruleCollection += "\n\t;";
                    ruleCollection+= rule;
                }

                statement = "define datapoint ruleset " + ruleSetName + "(" + typeList + " " + listCollection + ") is\n" +
                    ruleCollection + "\n" +
                    "end datapoint ruleset;";

                return statement;
             }
                catch (Exception ex)
                {
                    CommonItem.ErrManger.ErrorManagement(ex, false, this);
                    return null;
                }
        }

        private void OkButton_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
        }

        private void frmDefineDatapointRule_Load(object sender, EventArgs e)
        {
            ToolTip nameHelpToolTip=new ToolTip();
            ToolTip ruleSignatureHelpToolTip=new ToolTip();
            ToolTip ruleHelpToolTip = new ToolTip();

            nameHelpToolTip.ToolTipTitle = "Datapoint ruleset sample";
            nameHelpToolTip.ShowAlways = true;
            nameHelpToolTip.SetToolTip(this.nameHelpButton, _NAME_HELP_TOOLTIP);            

            ruleSignatureHelpToolTip.ToolTipTitle="Rule signature definition";
            ruleSignatureHelpToolTip.ShowAlways=true;
            ruleSignatureHelpToolTip.SetToolTip(this.ruleSignatureHelpButton, _RULESIGNATURE_HELP_TOOLTIP);

            ruleHelpToolTip.ToolTipTitle = "Rule definition";
            ruleHelpToolTip.ShowAlways = true;
            ruleHelpToolTip.SetToolTip(this.ruleHelpButton, _RULES_HELP_TOOLTIP);
        }
    }
}
