namespace VTL_Editor_PL.gui
{
    partial class UserDefinedValueDomainPanel
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
            this.DataTypeValueDomainComboBox = new System.Windows.Forms.ComboBox();
            this.label8 = new System.Windows.Forms.Label();
            this.EnumeratedPanel = new System.Windows.Forms.Panel();
            this.RemoveLanguageButton = new System.Windows.Forms.Button();
            this.AddLanguageButton = new System.Windows.Forms.Button();
            this.valueDomainDataGrid = new System.Windows.Forms.DataGridView();
            this.CodeItemColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Language_2_Column = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.EnumeratedRadioButton = new System.Windows.Forms.RadioButton();
            this.DescribedPanel = new System.Windows.Forms.Panel();
            this.label3 = new System.Windows.Forms.Label();
            this.DescribedCriterionTextBox = new System.Windows.Forms.TextBox();
            this.DescribedRadioButton = new System.Windows.Forms.RadioButton();
            this.ValueDomainContainerPanel.SuspendLayout();
            this.EnumeratedPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.valueDomainDataGrid)).BeginInit();
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
            this.ValueDomainContainerPanel.Controls.Add(this.DataTypeValueDomainComboBox);
            this.ValueDomainContainerPanel.Controls.Add(this.label8);
            this.ValueDomainContainerPanel.Controls.Add(this.EnumeratedPanel);
            this.ValueDomainContainerPanel.Controls.Add(this.EnumeratedRadioButton);
            this.ValueDomainContainerPanel.Controls.Add(this.DescribedPanel);
            this.ValueDomainContainerPanel.Controls.Add(this.DescribedRadioButton);
            this.ValueDomainContainerPanel.Location = new System.Drawing.Point(0, 0);
            this.ValueDomainContainerPanel.Name = "ValueDomainContainerPanel";
            this.ValueDomainContainerPanel.Size = new System.Drawing.Size(791, 501);
            this.ValueDomainContainerPanel.TabIndex = 21;
            // 
            // DataTypeValueDomainComboBox
            // 
            this.DataTypeValueDomainComboBox.FormattingEnabled = true;
            this.DataTypeValueDomainComboBox.Items.AddRange(new object[] {
            "Select a data type..."});
            this.DataTypeValueDomainComboBox.Location = new System.Drawing.Point(83, 7);
            this.DataTypeValueDomainComboBox.Name = "DataTypeValueDomainComboBox";
            this.DataTypeValueDomainComboBox.Size = new System.Drawing.Size(301, 21);
            this.DataTypeValueDomainComboBox.TabIndex = 15;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(9, 15);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(53, 13);
            this.label8.TabIndex = 14;
            this.label8.Text = "Data type";
            // 
            // EnumeratedPanel
            // 
            this.EnumeratedPanel.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.EnumeratedPanel.BackColor = System.Drawing.Color.SteelBlue;
            this.EnumeratedPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.EnumeratedPanel.Controls.Add(this.RemoveLanguageButton);
            this.EnumeratedPanel.Controls.Add(this.AddLanguageButton);
            this.EnumeratedPanel.Controls.Add(this.valueDomainDataGrid);
            this.EnumeratedPanel.Location = new System.Drawing.Point(3, 168);
            this.EnumeratedPanel.Name = "EnumeratedPanel";
            this.EnumeratedPanel.Size = new System.Drawing.Size(784, 327);
            this.EnumeratedPanel.TabIndex = 13;
            // 
            // RemoveLanguageButton
            // 
            this.RemoveLanguageButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.RemoveLanguageButton.FlatAppearance.BorderSize = 0;
            this.RemoveLanguageButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.RemoveLanguageButton.Font = new System.Drawing.Font("Arial", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.RemoveLanguageButton.ForeColor = System.Drawing.Color.White;
            this.RemoveLanguageButton.Location = new System.Drawing.Point(734, 41);
            this.RemoveLanguageButton.Name = "RemoveLanguageButton";
            this.RemoveLanguageButton.Size = new System.Drawing.Size(47, 37);
            this.RemoveLanguageButton.TabIndex = 15;
            this.RemoveLanguageButton.Text = "-";
            this.RemoveLanguageButton.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.RemoveLanguageButton.UseVisualStyleBackColor = true;
            this.RemoveLanguageButton.Click += new System.EventHandler(this.RemoveLanguageButton_Click);
            // 
            // AddLanguageButton
            // 
            this.AddLanguageButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.AddLanguageButton.FlatAppearance.BorderSize = 0;
            this.AddLanguageButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.AddLanguageButton.Font = new System.Drawing.Font("Arial", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.AddLanguageButton.ForeColor = System.Drawing.Color.White;
            this.AddLanguageButton.Location = new System.Drawing.Point(736, -1);
            this.AddLanguageButton.Name = "AddLanguageButton";
            this.AddLanguageButton.Size = new System.Drawing.Size(47, 37);
            this.AddLanguageButton.TabIndex = 14;
            this.AddLanguageButton.Text = "+";
            this.AddLanguageButton.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.AddLanguageButton.UseVisualStyleBackColor = true;
            this.AddLanguageButton.Click += new System.EventHandler(this.AddLanguageButton_Click);
            // 
            // valueDomainDataGrid
            // 
            this.valueDomainDataGrid.AllowUserToResizeRows = false;
            this.valueDomainDataGrid.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.valueDomainDataGrid.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.valueDomainDataGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.valueDomainDataGrid.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.CodeItemColumn,
            this.Language_2_Column});
            this.valueDomainDataGrid.Location = new System.Drawing.Point(3, 7);
            this.valueDomainDataGrid.Name = "valueDomainDataGrid";
            this.valueDomainDataGrid.RowHeadersVisible = false;
            this.valueDomainDataGrid.Size = new System.Drawing.Size(727, 315);
            this.valueDomainDataGrid.TabIndex = 7;
            // 
            // CodeItemColumn
            // 
            this.CodeItemColumn.HeaderText = "Code item";
            this.CodeItemColumn.Name = "CodeItemColumn";
            // 
            // Language_2_Column
            // 
            this.Language_2_Column.HeaderText = "EN";
            this.Language_2_Column.Name = "Language_2_Column";
            // 
            // EnumeratedRadioButton
            // 
            this.EnumeratedRadioButton.AutoSize = true;
            this.EnumeratedRadioButton.Checked = true;
            this.EnumeratedRadioButton.ForeColor = System.Drawing.Color.Black;
            this.EnumeratedRadioButton.Location = new System.Drawing.Point(6, 145);
            this.EnumeratedRadioButton.Name = "EnumeratedRadioButton";
            this.EnumeratedRadioButton.Size = new System.Drawing.Size(82, 17);
            this.EnumeratedRadioButton.TabIndex = 12;
            this.EnumeratedRadioButton.TabStop = true;
            this.EnumeratedRadioButton.Text = "Enumerated";
            this.EnumeratedRadioButton.UseVisualStyleBackColor = true;
            this.EnumeratedRadioButton.CheckedChanged += new System.EventHandler(this.EnumeratedRadioButton_CheckedChanged);
            // 
            // DescribedPanel
            // 
            this.DescribedPanel.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.DescribedPanel.BackColor = System.Drawing.Color.SteelBlue;
            this.DescribedPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.DescribedPanel.Controls.Add(this.label3);
            this.DescribedPanel.Controls.Add(this.DescribedCriterionTextBox);
            this.DescribedPanel.Location = new System.Drawing.Point(2, 59);
            this.DescribedPanel.Name = "DescribedPanel";
            this.DescribedPanel.Size = new System.Drawing.Size(785, 74);
            this.DescribedPanel.TabIndex = 11;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.ForeColor = System.Drawing.Color.White;
            this.label3.Location = new System.Drawing.Point(16, 9);
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
            this.DescribedCriterionTextBox.Location = new System.Drawing.Point(80, 6);
            this.DescribedCriterionTextBox.Multiline = true;
            this.DescribedCriterionTextBox.Name = "DescribedCriterionTextBox";
            this.DescribedCriterionTextBox.Size = new System.Drawing.Size(698, 63);
            this.DescribedCriterionTextBox.TabIndex = 10;
            // 
            // DescribedRadioButton
            // 
            this.DescribedRadioButton.AutoSize = true;
            this.DescribedRadioButton.ForeColor = System.Drawing.Color.Black;
            this.DescribedRadioButton.Location = new System.Drawing.Point(7, 36);
            this.DescribedRadioButton.Name = "DescribedRadioButton";
            this.DescribedRadioButton.Size = new System.Drawing.Size(73, 17);
            this.DescribedRadioButton.TabIndex = 7;
            this.DescribedRadioButton.Text = "Described";
            this.DescribedRadioButton.UseVisualStyleBackColor = true;
            // 
            // UserDefinedValueDomainPanel
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.ValueDomainContainerPanel);
            this.Name = "UserDefinedValueDomainPanel";
            this.Size = new System.Drawing.Size(791, 501);
            this.Load += new System.EventHandler(this.UserDefinedValueDomainPanel_Load);
            this.ValueDomainContainerPanel.ResumeLayout(false);
            this.ValueDomainContainerPanel.PerformLayout();
            this.EnumeratedPanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.valueDomainDataGrid)).EndInit();
            this.DescribedPanel.ResumeLayout(false);
            this.DescribedPanel.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel ValueDomainContainerPanel;
        private System.Windows.Forms.ComboBox DataTypeValueDomainComboBox;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Panel EnumeratedPanel;
        private System.Windows.Forms.RadioButton EnumeratedRadioButton;
        private System.Windows.Forms.Panel DescribedPanel;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox DescribedCriterionTextBox;
        private System.Windows.Forms.RadioButton DescribedRadioButton;
        private System.Windows.Forms.Button RemoveLanguageButton;
        private System.Windows.Forms.Button AddLanguageButton;
        private System.Windows.Forms.DataGridView valueDomainDataGrid;
        private System.Windows.Forms.DataGridViewTextBoxColumn CodeItemColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn Language_2_Column;
    }
}
