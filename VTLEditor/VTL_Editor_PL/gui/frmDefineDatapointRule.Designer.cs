namespace VTL_Editor_PL.gui
{
    partial class frmDefineDatapointRule
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
            this.nameLabel = new System.Windows.Forms.Label();
            this.nameTextBox = new System.Windows.Forms.TextBox();
            this.CancelButton = new System.Windows.Forms.Button();
            this.OkButton = new System.Windows.Forms.Button();
            this.InputListPanel = new System.Windows.Forms.Panel();
            this.ruleSignatureHelpButton = new System.Windows.Forms.Button();
            this.ValVarDataGridView = new System.Windows.Forms.DataGridView();
            this.ValVarName = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.AliasName = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.label1 = new System.Windows.Forms.Label();
            this.VariableRadioButton = new System.Windows.Forms.RadioButton();
            this.ValueDomainRadioButton = new System.Windows.Forms.RadioButton();
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
            this.nameHelpButton = new System.Windows.Forms.Button();
            this.InputListPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.ValVarDataGridView)).BeginInit();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // nameLabel
            // 
            this.nameLabel.AutoSize = true;
            this.nameLabel.Location = new System.Drawing.Point(14, 18);
            this.nameLabel.Name = "nameLabel";
            this.nameLabel.Size = new System.Drawing.Size(35, 13);
            this.nameLabel.TabIndex = 5;
            this.nameLabel.Text = "Name";
            // 
            // nameTextBox
            // 
            this.nameTextBox.Location = new System.Drawing.Point(106, 12);
            this.nameTextBox.Name = "nameTextBox";
            this.nameTextBox.Size = new System.Drawing.Size(482, 20);
            this.nameTextBox.TabIndex = 4;
            // 
            // CancelButton
            // 
            this.CancelButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.CancelButton.Location = new System.Drawing.Point(314, 565);
            this.CancelButton.Name = "CancelButton";
            this.CancelButton.Size = new System.Drawing.Size(75, 23);
            this.CancelButton.TabIndex = 13;
            this.CancelButton.Text = "Cancel";
            this.CancelButton.UseVisualStyleBackColor = true;
            // 
            // OkButton
            // 
            this.OkButton.Location = new System.Drawing.Point(233, 565);
            this.OkButton.Name = "OkButton";
            this.OkButton.Size = new System.Drawing.Size(75, 23);
            this.OkButton.TabIndex = 12;
            this.OkButton.Text = "Ok";
            this.OkButton.UseVisualStyleBackColor = true;
            this.OkButton.Click += new System.EventHandler(this.OkButton_Click);
            // 
            // InputListPanel
            // 
            this.InputListPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.InputListPanel.Controls.Add(this.ruleSignatureHelpButton);
            this.InputListPanel.Controls.Add(this.ValVarDataGridView);
            this.InputListPanel.Controls.Add(this.label1);
            this.InputListPanel.Controls.Add(this.VariableRadioButton);
            this.InputListPanel.Controls.Add(this.ValueDomainRadioButton);
            this.InputListPanel.Location = new System.Drawing.Point(9, 41);
            this.InputListPanel.Name = "InputListPanel";
            this.InputListPanel.Size = new System.Drawing.Size(606, 168);
            this.InputListPanel.TabIndex = 14;
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
            this.ValVarDataGridView.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.ValVarDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.ValVarDataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.ValVarName,
            this.AliasName});
            this.ValVarDataGridView.Location = new System.Drawing.Point(97, 37);
            this.ValVarDataGridView.Name = "ValVarDataGridView";
            this.ValVarDataGridView.RowHeadersVisible = false;
            this.ValVarDataGridView.Size = new System.Drawing.Size(498, 120);
            this.ValVarDataGridView.TabIndex = 3;
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
            this.VariableRadioButton.TabIndex = 1;
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
            this.ValueDomainRadioButton.TabIndex = 0;
            this.ValueDomainRadioButton.TabStop = true;
            this.ValueDomainRadioButton.Text = "Value domain";
            this.ValueDomainRadioButton.UseVisualStyleBackColor = true;
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
            this.panel1.Location = new System.Drawing.Point(9, 211);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(606, 348);
            this.panel1.TabIndex = 15;
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
            this.label7.Location = new System.Drawing.Point(5, 116);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(56, 13);
            this.label7.TabIndex = 31;
            this.label7.Text = "Error code";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(5, 90);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(54, 13);
            this.label6.TabIndex = 30;
            this.label6.Text = "Error level";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(2, 64);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(94, 13);
            this.label5.TabIndex = 29;
            this.label5.Text = "Consequent cond.";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(4, 38);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(92, 13);
            this.label4.TabIndex = 28;
            this.label4.Text = "Antecedent cond.";
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
            this.label2.Location = new System.Drawing.Point(5, 13);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(58, 13);
            this.label2.TabIndex = 16;
            this.label2.Text = "Rule name";
            // 
            // nameHelpButton
            // 
            this.nameHelpButton.BackColor = System.Drawing.Color.SteelBlue;
            this.nameHelpButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.nameHelpButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 7F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.nameHelpButton.ForeColor = System.Drawing.Color.White;
            this.nameHelpButton.Location = new System.Drawing.Point(594, 12);
            this.nameHelpButton.Name = "nameHelpButton";
            this.nameHelpButton.Size = new System.Drawing.Size(21, 21);
            this.nameHelpButton.TabIndex = 32;
            this.nameHelpButton.Text = "?";
            this.nameHelpButton.UseVisualStyleBackColor = false;
            // 
            // frmDefineDatapointRule
            // 
            this.AcceptButton = this.OkButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightSteelBlue;
            this.ClientSize = new System.Drawing.Size(619, 588);
            this.Controls.Add(this.nameHelpButton);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.InputListPanel);
            this.Controls.Add(this.CancelButton);
            this.Controls.Add(this.OkButton);
            this.Controls.Add(this.nameLabel);
            this.Controls.Add(this.nameTextBox);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "frmDefineDatapointRule";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Define Datapoint Ruleset";
            this.Load += new System.EventHandler(this.frmDefineDatapointRule_Load);
            this.InputListPanel.ResumeLayout(false);
            this.InputListPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.ValVarDataGridView)).EndInit();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label nameLabel;
        private System.Windows.Forms.TextBox nameTextBox;
        private System.Windows.Forms.Button CancelButton;
        private System.Windows.Forms.Button OkButton;
        private System.Windows.Forms.Panel InputListPanel;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.RadioButton VariableRadioButton;
        private System.Windows.Forms.RadioButton ValueDomainRadioButton;
        private System.Windows.Forms.DataGridView ValVarDataGridView;
        private System.Windows.Forms.DataGridViewTextBoxColumn ValVarName;
        private System.Windows.Forms.DataGridViewTextBoxColumn AliasName;
        private System.Windows.Forms.Panel panel1;
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
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button nameHelpButton;
        private System.Windows.Forms.Button ruleHelpButton;
        private System.Windows.Forms.Button ruleSignatureHelpButton;
    }
}