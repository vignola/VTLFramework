namespace VTL_Editor_PL.gui
{
    partial class frmDefineHierarchicalRule
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.nameHelpButton = new System.Windows.Forms.Button();
            this.InputListPanel = new System.Windows.Forms.Panel();
            this.label8 = new System.Windows.Forms.Label();
            this.RuleConditionTextBox = new System.Windows.Forms.TextBox();
            this.ruleSignatureHelpButton = new System.Windows.Forms.Button();
            this.ValVarDataGridView = new System.Windows.Forms.DataGridView();
            this.ValVarName = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.AliasName = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.label1 = new System.Windows.Forms.Label();
            this.VariableRadioButton = new System.Windows.Forms.RadioButton();
            this.ValueDomainRadioButton = new System.Windows.Forms.RadioButton();
            this.CancelButton = new System.Windows.Forms.Button();
            this.OkButton = new System.Windows.Forms.Button();
            this.nameLabel = new System.Windows.Forms.Label();
            this.nameTextBox = new System.Windows.Forms.TextBox();
            this.panel1 = new System.Windows.Forms.Panel();
            this.ruleHelpButton = new System.Windows.Forms.Button();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.RemoveRuleButton = new System.Windows.Forms.Button();
            this.AddRuleButton = new System.Windows.Forms.Button();
            this.RulesListBox = new System.Windows.Forms.ListBox();
            this.PreviewTextBox = new System.Windows.Forms.TextBox();
            this.PreviewButton = new System.Windows.Forms.Button();
            this.ErrorCodeTextBox = new System.Windows.Forms.TextBox();
            this.ErrorLevelTxtBox = new System.Windows.Forms.TextBox();
            this.ConsCondTextBox = new System.Windows.Forms.TextBox();
            this.AntCondTextBox = new System.Windows.Forms.TextBox();
            this.RuleNameTextBox = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.InputListPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.ValVarDataGridView)).BeginInit();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // nameHelpButton
            // 
            this.nameHelpButton.BackColor = System.Drawing.Color.SteelBlue;
            this.nameHelpButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.nameHelpButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 7F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.nameHelpButton.ForeColor = System.Drawing.Color.White;
            this.nameHelpButton.Location = new System.Drawing.Point(589, 9);
            this.nameHelpButton.Name = "nameHelpButton";
            this.nameHelpButton.Size = new System.Drawing.Size(21, 21);
            this.nameHelpButton.TabIndex = 39;
            this.nameHelpButton.Text = "?";
            this.nameHelpButton.UseVisualStyleBackColor = false;
            // 
            // InputListPanel
            // 
            this.InputListPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.InputListPanel.Controls.Add(this.label9);
            this.InputListPanel.Controls.Add(this.label8);
            this.InputListPanel.Controls.Add(this.RuleConditionTextBox);
            this.InputListPanel.Controls.Add(this.ruleSignatureHelpButton);
            this.InputListPanel.Controls.Add(this.ValVarDataGridView);
            this.InputListPanel.Controls.Add(this.label1);
            this.InputListPanel.Controls.Add(this.VariableRadioButton);
            this.InputListPanel.Controls.Add(this.ValueDomainRadioButton);
            this.InputListPanel.Location = new System.Drawing.Point(4, 38);
            this.InputListPanel.Name = "InputListPanel";
            this.InputListPanel.Size = new System.Drawing.Size(606, 119);
            this.InputListPanel.TabIndex = 37;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(10, 68);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(81, 13);
            this.label8.TabIndex = 34;
            this.label8.Text = "Cond. signature";
            // 
            // RuleConditionTextBox
            // 
            this.RuleConditionTextBox.Location = new System.Drawing.Point(97, 42);
            this.RuleConditionTextBox.Name = "RuleConditionTextBox";
            this.RuleConditionTextBox.Size = new System.Drawing.Size(497, 20);
            this.RuleConditionTextBox.TabIndex = 5;            
            // 
            // ruleSignatureHelpButton
            // 
            this.ruleSignatureHelpButton.BackColor = System.Drawing.Color.SteelBlue;
            this.ruleSignatureHelpButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.ruleSignatureHelpButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 7F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ruleSignatureHelpButton.ForeColor = System.Drawing.Color.White;
            this.ruleSignatureHelpButton.Location = new System.Drawing.Point(287, 8);
            this.ruleSignatureHelpButton.Name = "ruleSignatureHelpButton";
            this.ruleSignatureHelpButton.Size = new System.Drawing.Size(21, 21);
            this.ruleSignatureHelpButton.TabIndex = 38;
            this.ruleSignatureHelpButton.Text = "?";
            this.ruleSignatureHelpButton.UseVisualStyleBackColor = false;
            // 
            // ValVarDataGridView
            // 
            this.ValVarDataGridView.AllowUserToAddRows = false;
            this.ValVarDataGridView.AllowUserToDeleteRows = false;
            this.ValVarDataGridView.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.ValVarDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.ValVarDataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.ValVarName,
            this.AliasName});
            this.ValVarDataGridView.Location = new System.Drawing.Point(97, 68);
            this.ValVarDataGridView.Name = "ValVarDataGridView";
            this.ValVarDataGridView.RowHeadersVisible = false;
            this.ValVarDataGridView.Size = new System.Drawing.Size(497, 46);
            this.ValVarDataGridView.TabIndex = 4;
            // 
            // ValVarName
            // 
            this.ValVarName.HeaderText = "Value domain or variable";
            this.ValVarName.Name = "ValVarName";
            // 
            // AliasName
            // 
            this.AliasName.HeaderText = "Alias";
            this.AliasName.Name = "AliasName";
            this.AliasName.Resizable = System.Windows.Forms.DataGridViewTriState.True;
            this.AliasName.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.NotSortable;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(5, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(84, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Apply to a list of:";
            // 
            // VariableRadioButton
            // 
            this.VariableRadioButton.AutoSize = true;
            this.VariableRadioButton.Location = new System.Drawing.Point(218, 9);
            this.VariableRadioButton.Name = "VariableRadioButton";
            this.VariableRadioButton.Size = new System.Drawing.Size(63, 17);
            this.VariableRadioButton.TabIndex = 3;
            this.VariableRadioButton.Text = "Variable";
            this.VariableRadioButton.UseVisualStyleBackColor = true;
            // 
            // ValueDomainRadioButton
            // 
            this.ValueDomainRadioButton.AutoSize = true;
            this.ValueDomainRadioButton.Checked = true;
            this.ValueDomainRadioButton.Location = new System.Drawing.Point(97, 9);
            this.ValueDomainRadioButton.Name = "ValueDomainRadioButton";
            this.ValueDomainRadioButton.Size = new System.Drawing.Size(89, 17);
            this.ValueDomainRadioButton.TabIndex = 2;
            this.ValueDomainRadioButton.TabStop = true;
            this.ValueDomainRadioButton.Text = "Value domain";
            this.ValueDomainRadioButton.UseVisualStyleBackColor = true;
            // 
            // CancelButton
            // 
            this.CancelButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.CancelButton.Location = new System.Drawing.Point(309, 513);
            this.CancelButton.Name = "CancelButton";
            this.CancelButton.Size = new System.Drawing.Size(75, 23);
            this.CancelButton.TabIndex = 36;
            this.CancelButton.Text = "Cancel";
            this.CancelButton.UseVisualStyleBackColor = true;
            this.CancelButton.Click += new System.EventHandler(this.CancelButton_Click);
            // 
            // OkButton
            // 
            this.OkButton.Location = new System.Drawing.Point(228, 513);
            this.OkButton.Name = "OkButton";
            this.OkButton.Size = new System.Drawing.Size(75, 23);
            this.OkButton.TabIndex = 12;
            this.OkButton.Text = "Ok";
            this.OkButton.UseVisualStyleBackColor = true;
            this.OkButton.Click += new System.EventHandler(this.OkButton_Click);
            // 
            // nameLabel
            // 
            this.nameLabel.AutoSize = true;
            this.nameLabel.Location = new System.Drawing.Point(9, 15);
            this.nameLabel.Name = "nameLabel";
            this.nameLabel.Size = new System.Drawing.Size(35, 13);
            this.nameLabel.TabIndex = 34;
            this.nameLabel.Text = "Name";
            // 
            // nameTextBox
            // 
            this.nameTextBox.Location = new System.Drawing.Point(101, 9);
            this.nameTextBox.Name = "nameTextBox";
            this.nameTextBox.Size = new System.Drawing.Size(482, 20);
            this.nameTextBox.TabIndex = 1;
            // 
            // panel1
            // 
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel1.Controls.Add(this.ruleHelpButton);
            this.panel1.Controls.Add(this.label7);
            this.panel1.Controls.Add(this.label6);
            this.panel1.Controls.Add(this.label5);
            this.panel1.Controls.Add(this.label4);
            this.panel1.Controls.Add(this.label3);
            this.panel1.Controls.Add(this.RemoveRuleButton);
            this.panel1.Controls.Add(this.AddRuleButton);
            this.panel1.Controls.Add(this.RulesListBox);
            this.panel1.Controls.Add(this.PreviewTextBox);
            this.panel1.Controls.Add(this.PreviewButton);
            this.panel1.Controls.Add(this.ErrorCodeTextBox);
            this.panel1.Controls.Add(this.ErrorLevelTxtBox);
            this.panel1.Controls.Add(this.ConsCondTextBox);
            this.panel1.Controls.Add(this.AntCondTextBox);
            this.panel1.Controls.Add(this.RuleNameTextBox);
            this.panel1.Controls.Add(this.label2);
            this.panel1.Location = new System.Drawing.Point(4, 161);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(606, 348);
            this.panel1.TabIndex = 40;
            // 
            // ruleHelpButton
            // 
            this.ruleHelpButton.BackColor = System.Drawing.Color.SteelBlue;
            this.ruleHelpButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.ruleHelpButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 7F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ruleHelpButton.ForeColor = System.Drawing.Color.White;
            this.ruleHelpButton.Location = new System.Drawing.Point(358, 5);
            this.ruleHelpButton.Name = "ruleHelpButton";
            this.ruleHelpButton.Size = new System.Drawing.Size(21, 21);
            this.ruleHelpButton.TabIndex = 33;
            this.ruleHelpButton.Text = "?";
            this.ruleHelpButton.UseVisualStyleBackColor = false;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(4, 116);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(56, 13);
            this.label7.TabIndex = 31;
            this.label7.Text = "Error code";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(4, 90);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(54, 13);
            this.label6.TabIndex = 30;
            this.label6.Text = "Error level";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(4, 64);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(72, 13);
            this.label5.TabIndex = 29;
            this.label5.Text = "Code Item rel.";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(4, 38);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(85, 13);
            this.label4.TabIndex = 28;
            this.label4.Text = "Code Item cond.";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(5, 245);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(34, 13);
            this.label3.TabIndex = 27;
            this.label3.Text = "Rules";
            // 
            // RemoveRuleButton
            // 
            this.RemoveRuleButton.BackColor = System.Drawing.Color.SteelBlue;
            this.RemoveRuleButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.RemoveRuleButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.RemoveRuleButton.ForeColor = System.Drawing.Color.White;
            this.RemoveRuleButton.Location = new System.Drawing.Point(567, 261);
            this.RemoveRuleButton.Name = "RemoveRuleButton";
            this.RemoveRuleButton.Size = new System.Drawing.Size(28, 29);
            this.RemoveRuleButton.TabIndex = 26;
            this.RemoveRuleButton.Text = "-";
            this.RemoveRuleButton.UseVisualStyleBackColor = false;
            this.RemoveRuleButton.Click += new System.EventHandler(this.RemoveRuleButton_Click);
            // 
            // AddRuleButton
            // 
            this.AddRuleButton.Location = new System.Drawing.Point(223, 211);
            this.AddRuleButton.Name = "AddRuleButton";
            this.AddRuleButton.Size = new System.Drawing.Size(156, 23);
            this.AddRuleButton.TabIndex = 25;
            this.AddRuleButton.Text = "Add to ruleset";
            this.AddRuleButton.UseVisualStyleBackColor = true;
            this.AddRuleButton.Click += new System.EventHandler(this.AddRuleButton_Click);
            // 
            // RulesListBox
            // 
            this.RulesListBox.FormattingEnabled = true;
            this.RulesListBox.Location = new System.Drawing.Point(8, 261);
            this.RulesListBox.Name = "RulesListBox";
            this.RulesListBox.Size = new System.Drawing.Size(553, 82);
            this.RulesListBox.TabIndex = 24;
            // 
            // PreviewTextBox
            // 
            this.PreviewTextBox.Location = new System.Drawing.Point(97, 143);
            this.PreviewTextBox.Multiline = true;
            this.PreviewTextBox.Name = "PreviewTextBox";
            this.PreviewTextBox.Size = new System.Drawing.Size(497, 62);
            this.PreviewTextBox.TabIndex = 23;
            // 
            // PreviewButton
            // 
            this.PreviewButton.Location = new System.Drawing.Point(8, 143);
            this.PreviewButton.Name = "PreviewButton";
            this.PreviewButton.Size = new System.Drawing.Size(75, 23);
            this.PreviewButton.TabIndex = 22;
            this.PreviewButton.Text = "Preview";
            this.PreviewButton.UseVisualStyleBackColor = true;
            this.PreviewButton.Click += new System.EventHandler(this.PreviewButton_Click);
            // 
            // ErrorCodeTextBox
            // 
            this.ErrorCodeTextBox.Location = new System.Drawing.Point(96, 109);
            this.ErrorCodeTextBox.Name = "ErrorCodeTextBox";
            this.ErrorCodeTextBox.Size = new System.Drawing.Size(257, 20);
            this.ErrorCodeTextBox.TabIndex = 21;
            // 
            // ErrorLevelTxtBox
            // 
            this.ErrorLevelTxtBox.Location = new System.Drawing.Point(96, 83);
            this.ErrorLevelTxtBox.Name = "ErrorLevelTxtBox";
            this.ErrorLevelTxtBox.Size = new System.Drawing.Size(257, 20);
            this.ErrorLevelTxtBox.TabIndex = 20;
            // 
            // ConsCondTextBox
            // 
            this.ConsCondTextBox.Location = new System.Drawing.Point(96, 57);
            this.ConsCondTextBox.Name = "ConsCondTextBox";
            this.ConsCondTextBox.Size = new System.Drawing.Size(498, 20);
            this.ConsCondTextBox.TabIndex = 19;
            // 
            // AntCondTextBox
            // 
            this.AntCondTextBox.Location = new System.Drawing.Point(96, 31);
            this.AntCondTextBox.Name = "AntCondTextBox";
            this.AntCondTextBox.Size = new System.Drawing.Size(499, 20);
            this.AntCondTextBox.TabIndex = 18;
            // 
            // RuleNameTextBox
            // 
            this.RuleNameTextBox.Location = new System.Drawing.Point(96, 5);
            this.RuleNameTextBox.Name = "RuleNameTextBox";
            this.RuleNameTextBox.Size = new System.Drawing.Size(256, 20);
            this.RuleNameTextBox.TabIndex = 17;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(4, 13);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(58, 13);
            this.label2.TabIndex = 16;
            this.label2.Text = "Rule name";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(10, 49);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(66, 13);
            this.label9.TabIndex = 39;
            this.label9.Text = "Var/Val. rule";
            // 
            // frmDefineHierarchicalRule
            // 
            this.AcceptButton = this.OkButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightSteelBlue;
            this.CancelButton = this.CancelButton;
            this.ClientSize = new System.Drawing.Size(615, 538);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.nameHelpButton);
            this.Controls.Add(this.InputListPanel);
            this.Controls.Add(this.CancelButton);
            this.Controls.Add(this.OkButton);
            this.Controls.Add(this.nameLabel);
            this.Controls.Add(this.nameTextBox);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "frmDefineHierarchicalRule";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Define Hierarchical ruleset";
            this.Load += new System.EventHandler(this.frmDefineHierarchicalRule_Load);
            this.InputListPanel.ResumeLayout(false);
            this.InputListPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.ValVarDataGridView)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button nameHelpButton;
        private System.Windows.Forms.Panel InputListPanel;
        private System.Windows.Forms.Button ruleSignatureHelpButton;
        private System.Windows.Forms.DataGridView ValVarDataGridView;
        private System.Windows.Forms.DataGridViewTextBoxColumn ValVarName;
        private System.Windows.Forms.DataGridViewTextBoxColumn AliasName;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.RadioButton VariableRadioButton;
        private System.Windows.Forms.RadioButton ValueDomainRadioButton;
        private System.Windows.Forms.Button CancelButton;
        private System.Windows.Forms.Button OkButton;
        private System.Windows.Forms.Label nameLabel;
        private System.Windows.Forms.TextBox nameTextBox;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.TextBox RuleConditionTextBox;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button ruleHelpButton;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button RemoveRuleButton;
        private System.Windows.Forms.Button AddRuleButton;
        private System.Windows.Forms.ListBox RulesListBox;
        private System.Windows.Forms.TextBox PreviewTextBox;
        private System.Windows.Forms.Button PreviewButton;
        private System.Windows.Forms.TextBox ErrorCodeTextBox;
        private System.Windows.Forms.TextBox ErrorLevelTxtBox;
        private System.Windows.Forms.TextBox ConsCondTextBox;
        private System.Windows.Forms.TextBox AntCondTextBox;
        private System.Windows.Forms.TextBox RuleNameTextBox;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label9;
    }
}