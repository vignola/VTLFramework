namespace VTL_Editor_PL.gui
{
    partial class UserDefinedValueDomainSubsetPanel
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.ValueDomainContainerPanel = new System.Windows.Forms.Panel();
            this.label4 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.ValueDomainListComboBox = new System.Windows.Forms.ComboBox();
            this.label8 = new System.Windows.Forms.Label();
            this.EnumeratedPanel = new System.Windows.Forms.Panel();
            this.linkLabel3 = new System.Windows.Forms.LinkLabel();
            this.linkLabel2 = new System.Windows.Forms.LinkLabel();
            this.linkLabel1 = new System.Windows.Forms.LinkLabel();
            this.ValuesCheckedListBox = new System.Windows.Forms.CheckedListBox();
            this.DescribedPanel = new System.Windows.Forms.Panel();
            this.valDomParentDescrTextBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.DescribedCriterionTextBox = new System.Windows.Forms.TextBox();
            this.ValueDomainContainerPanel.SuspendLayout();
            this.EnumeratedPanel.SuspendLayout();
            this.DescribedPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // ValueDomainContainerPanel
            // 
            this.ValueDomainContainerPanel.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.ValueDomainContainerPanel.BackColor = System.Drawing.Color.LightSteelBlue;
            this.ValueDomainContainerPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ValueDomainContainerPanel.Controls.Add(this.label4);
            this.ValueDomainContainerPanel.Controls.Add(this.label2);
            this.ValueDomainContainerPanel.Controls.Add(this.ValueDomainListComboBox);
            this.ValueDomainContainerPanel.Controls.Add(this.label8);
            this.ValueDomainContainerPanel.Controls.Add(this.EnumeratedPanel);
            this.ValueDomainContainerPanel.Controls.Add(this.DescribedPanel);
            this.ValueDomainContainerPanel.Location = new System.Drawing.Point(0, 0);
            this.ValueDomainContainerPanel.Name = "ValueDomainContainerPanel";
            this.ValueDomainContainerPanel.Size = new System.Drawing.Size(791, 501);
            this.ValueDomainContainerPanel.TabIndex = 21;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(4, 152);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(64, 13);
            this.label4.TabIndex = 17;
            this.label4.Text = "Enumerated";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(4, 40);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(55, 13);
            this.label2.TabIndex = 16;
            this.label2.Text = "Described";
            // 
            // ValueDomainListComboBox
            // 
            this.ValueDomainListComboBox.FormattingEnabled = true;
            this.ValueDomainListComboBox.Location = new System.Drawing.Point(83, 7);
            this.ValueDomainListComboBox.Name = "ValueDomainListComboBox";
            this.ValueDomainListComboBox.Size = new System.Drawing.Size(324, 21);
            this.ValueDomainListComboBox.TabIndex = 15;
            this.ValueDomainListComboBox.SelectedIndexChanged += new System.EventHandler(this.ValueDomainListComboBox_SelectedIndexChanged);
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(9, 15);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(71, 13);
            this.label8.TabIndex = 14;
            this.label8.Text = "Value domain";
            // 
            // EnumeratedPanel
            // 
            this.EnumeratedPanel.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.EnumeratedPanel.BackColor = System.Drawing.Color.SteelBlue;
            this.EnumeratedPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.EnumeratedPanel.Controls.Add(this.linkLabel3);
            this.EnumeratedPanel.Controls.Add(this.linkLabel2);
            this.EnumeratedPanel.Controls.Add(this.linkLabel1);
            this.EnumeratedPanel.Controls.Add(this.ValuesCheckedListBox);
            this.EnumeratedPanel.Location = new System.Drawing.Point(3, 168);
            this.EnumeratedPanel.Name = "EnumeratedPanel";
            this.EnumeratedPanel.Size = new System.Drawing.Size(784, 327);
            this.EnumeratedPanel.TabIndex = 13;
            // 
            // linkLabel3
            // 
            this.linkLabel3.AutoSize = true;
            this.linkLabel3.ForeColor = System.Drawing.Color.White;
            this.linkLabel3.LinkColor = System.Drawing.Color.White;
            this.linkLabel3.Location = new System.Drawing.Point(154, 301);
            this.linkLabel3.Name = "linkLabel3";
            this.linkLabel3.Size = new System.Drawing.Size(79, 13);
            this.linkLabel3.TabIndex = 3;
            this.linkLabel3.TabStop = true;
            this.linkLabel3.Text = "Invert selection";
            this.linkLabel3.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkLabel3_LinkClicked);
            // 
            // linkLabel2
            // 
            this.linkLabel2.AutoSize = true;
            this.linkLabel2.ForeColor = System.Drawing.Color.White;
            this.linkLabel2.LinkColor = System.Drawing.Color.White;
            this.linkLabel2.Location = new System.Drawing.Point(79, 301);
            this.linkLabel2.Name = "linkLabel2";
            this.linkLabel2.Size = new System.Drawing.Size(62, 13);
            this.linkLabel2.TabIndex = 2;
            this.linkLabel2.TabStop = true;
            this.linkLabel2.Text = "Deselect all";
            this.linkLabel2.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkLabel2_LinkClicked);
            // 
            // linkLabel1
            // 
            this.linkLabel1.AutoSize = true;
            this.linkLabel1.ForeColor = System.Drawing.Color.White;
            this.linkLabel1.LinkColor = System.Drawing.Color.White;
            this.linkLabel1.Location = new System.Drawing.Point(18, 301);
            this.linkLabel1.Name = "linkLabel1";
            this.linkLabel1.Size = new System.Drawing.Size(50, 13);
            this.linkLabel1.TabIndex = 1;
            this.linkLabel1.TabStop = true;
            this.linkLabel1.Text = "Select all";
            this.linkLabel1.LinkClicked += new System.Windows.Forms.LinkLabelLinkClickedEventHandler(this.linkLabel1_LinkClicked);
            // 
            // ValuesCheckedListBox
            // 
            this.ValuesCheckedListBox.FormattingEnabled = true;
            this.ValuesCheckedListBox.Location = new System.Drawing.Point(6, 5);
            this.ValuesCheckedListBox.Name = "ValuesCheckedListBox";
            this.ValuesCheckedListBox.Size = new System.Drawing.Size(769, 289);
            this.ValuesCheckedListBox.TabIndex = 0;
            // 
            // DescribedPanel
            // 
            this.DescribedPanel.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.DescribedPanel.BackColor = System.Drawing.Color.SteelBlue;
            this.DescribedPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.DescribedPanel.Controls.Add(this.valDomParentDescrTextBox);
            this.DescribedPanel.Controls.Add(this.label1);
            this.DescribedPanel.Controls.Add(this.label3);
            this.DescribedPanel.Controls.Add(this.DescribedCriterionTextBox);
            this.DescribedPanel.Location = new System.Drawing.Point(2, 59);
            this.DescribedPanel.Name = "DescribedPanel";
            this.DescribedPanel.Size = new System.Drawing.Size(785, 80);
            this.DescribedPanel.TabIndex = 11;
            // 
            // valDomParentDescrTextBox
            // 
            this.valDomParentDescrTextBox.BackColor = System.Drawing.Color.LightSteelBlue;
            this.valDomParentDescrTextBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.valDomParentDescrTextBox.Location = new System.Drawing.Point(185, 5);
            this.valDomParentDescrTextBox.Name = "valDomParentDescrTextBox";
            this.valDomParentDescrTextBox.ReadOnly = true;
            this.valDomParentDescrTextBox.Size = new System.Drawing.Size(593, 20);
            this.valDomParentDescrTextBox.TabIndex = 13;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.ForeColor = System.Drawing.Color.White;
            this.label1.Location = new System.Drawing.Point(17, 10);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(161, 13);
            this.label1.TabIndex = 12;
            this.label1.Text = "Value domain parent description:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.ForeColor = System.Drawing.Color.White;
            this.label3.Location = new System.Drawing.Point(17, 33);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(57, 13);
            this.label3.TabIndex = 11;
            this.label3.Text = "Restriction";
            // 
            // DescribedCriterionTextBox
            // 
            this.DescribedCriterionTextBox.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.DescribedCriterionTextBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.DescribedCriterionTextBox.Location = new System.Drawing.Point(80, 33);
            this.DescribedCriterionTextBox.Multiline = true;
            this.DescribedCriterionTextBox.Name = "DescribedCriterionTextBox";
            this.DescribedCriterionTextBox.Size = new System.Drawing.Size(698, 42);
            this.DescribedCriterionTextBox.TabIndex = 10;
            // 
            // UserDefinedValueDomainSubsetPanel
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.ValueDomainContainerPanel);
            this.Name = "UserDefinedValueDomainSubsetPanel";
            this.Size = new System.Drawing.Size(791, 501);
            this.Load += new System.EventHandler(this.UserDefinedValueDomainPanel_Load);
            this.ValueDomainContainerPanel.ResumeLayout(false);
            this.ValueDomainContainerPanel.PerformLayout();
            this.EnumeratedPanel.ResumeLayout(false);
            this.EnumeratedPanel.PerformLayout();
            this.DescribedPanel.ResumeLayout(false);
            this.DescribedPanel.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel ValueDomainContainerPanel;
        private System.Windows.Forms.ComboBox ValueDomainListComboBox;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Panel EnumeratedPanel;
        private System.Windows.Forms.Panel DescribedPanel;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox DescribedCriterionTextBox;
        private System.Windows.Forms.LinkLabel linkLabel3;
        private System.Windows.Forms.LinkLabel linkLabel2;
        private System.Windows.Forms.LinkLabel linkLabel1;
        private System.Windows.Forms.CheckedListBox ValuesCheckedListBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox valDomParentDescrTextBox;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label2;
    }
}
