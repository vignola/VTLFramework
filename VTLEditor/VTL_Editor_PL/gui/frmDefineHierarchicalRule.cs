using System;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;

namespace VTL_Editor_PL.gui
{
    public partial class frmDefineHierarchicalRule : Form
    {
        private const string _NAME_HELP_TOOLTIP = "define hierachical ruleset DPR_1 ( valuedomain condition Residence as Res rule GeoArea) is \n" +
                                           "\trule1: when Res = “resident” then Countr1=Country1 \n" +
                                           "\t;rule2: when Res = “non-resident” then Countr1=RegionCountry1 \n" +
                                           "end hierachical ruleset";

        private const string _RULESIGNATURE_HELP_TOOLTIP = "define hierachical ruleset <rulesetName> ( valuedomain | variable condition <condition signature> rule <rule Var/ValDomain> ) is \n\n" +                                                         
                                                        "Sample:\n" +
                                                        "\tdefine hierachical ruleset DPR_1 ( valuedomain rule flow_type) is\n" +
                                                        "\tdefine hierachical ruleset DPR_2 ( variable rule flow) is" +
                                                        "\tdefine hierachical ruleset DPR_1 ( valuedomain Residence as res rule ref_area) is\n"+
                                                        "\tdefine hierachical ruleset DPR_2 ( variable condition Residence as res rule GeoArea) is";

        private const string _RULES_HELP_TOOLTIP = "define hierachical ruleset rulesetName ( dpRulesetSignature ) is\n" +
                                                "\tdpRule\n" +
                                                "\t{ ; dpRule }*\n" +
                                                "end hierachical ruleset\n\n" +
                                                "dpRule:\n" +
                                                "\t{ ruleName : } { when leftCondition then leftCodeItem  }\n" +
                                                "\t{ errorcode errorCode }\n" +
                                                "\t{ errorlevel errorLevel }\n\n" +
                                                "Sample:\n" +
                                                "\twhen F = “CREDIT” then COUNTRY=IT+GR \n";

        public frmDefineHierarchicalRule()
        {
            InitializeComponent();
        }

        private void frmDefineHierarchicalRule_Load(object sender, EventArgs e)
        {
            this.ValVarDataGridView.Rows.Add();
            nameTextBox.Focus();

            ToolTip nameHelpToolTip = new ToolTip();
            ToolTip ruleSignatureHelpToolTip = new ToolTip();
            ToolTip ruleHelpToolTip = new ToolTip();

            nameHelpToolTip.ToolTipTitle = "Hierachical ruleset sample";
            nameHelpToolTip.ShowAlways = true;
            nameHelpToolTip.SetToolTip(this.nameHelpButton, _NAME_HELP_TOOLTIP);

            ruleSignatureHelpToolTip.ToolTipTitle = "Rule signature definition";
            ruleSignatureHelpToolTip.ShowAlways = true;
            ruleSignatureHelpToolTip.SetToolTip(this.ruleSignatureHelpButton, _RULESIGNATURE_HELP_TOOLTIP);

            ruleHelpToolTip.ToolTipTitle = "Rule definition";
            ruleHelpToolTip.ShowAlways = true;
            ruleHelpToolTip.SetToolTip(this.ruleHelpButton, _RULES_HELP_TOOLTIP);
        }

        private void OkButton_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
        }

        private void CancelButton_Click(object sender, EventArgs e)
        {

        }

        private void PreviewButton_Click(object sender, EventArgs e)
        {
            this.PreviewTextBox.Text = getCurrentRule();
        }

        private string getCurrentRule()
        {
            try
            {
                string ruleName = this.RuleNameTextBox.Text.Trim() != "" ? this.RuleNameTextBox.Text.Trim() + ": " : "";
                string antecetend = this.AntCondTextBox.Text.Trim() != "" ? "when " + this.AntCondTextBox.Text.Trim() + " then " : "";
                string consequent = this.ConsCondTextBox.Text.Trim() != "" ? this.ConsCondTextBox.Text.Trim() : "<left/right code Item>";
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

        private void AddRuleButton_Click(object sender, EventArgs e)
        {
            this.RulesListBox.Items.Add(getCurrentRule());
        }

        public string getDefineHierarchicalRulesetStatement()
        {
            try
            {
                string statement = "";
                string ConditionSignature="";
                string RuleCondition="";
                string ruleSetName = this.nameTextBox.Text.Trim() != "" ? this.nameTextBox.Text.Trim() : "<rulesetName>";
                string typeList = this.ValueDomainRadioButton.Checked ? "valuedomain" : "variable";                
                string ruleCollection = "\t";

                
                string ConditionSignObj = "";
                string ConditionSignAlias = "";

                RuleCondition = RuleConditionTextBox.Text != "" ? RuleConditionTextBox.Text : "<rule Var/ValueDomain>";

                if (this.ValVarDataGridView.Rows[0].Cells[0].Value != null)
                    ConditionSignObj = this.ValVarDataGridView.Rows[0].Cells[0].Value.ToString().Trim() != "" ? this.ValVarDataGridView.Rows[0].Cells[0].Value.ToString().Trim() : "<" + typeList + ">";

                if (this.ValVarDataGridView.Rows[0].Cells[1].Value != null)
                    ConditionSignAlias = this.ValVarDataGridView.Rows[0].Cells[1].Value.ToString().Trim() != "" ? " as " + this.ValVarDataGridView.Rows[0].Cells[1].Value.ToString().Trim() : "";

                if (ConditionSignObj != "")
                    ConditionSignature = "condition " + ConditionSignObj + ConditionSignAlias;

                foreach (string rule in this.RulesListBox.Items)
                {
                    if (ruleCollection != "\t") ruleCollection += "\n\t;";
                    ruleCollection += rule;
                }

                statement = "define hierarchical ruleset " + ruleSetName + "(" + typeList + " " + ConditionSignature + " rule " + RuleCondition + ") is\n" +
                    ruleCollection + "\n" +
                    "end hierarchical ruleset;";

                return statement;
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
    }
}
