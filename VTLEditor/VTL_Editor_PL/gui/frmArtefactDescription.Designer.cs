namespace VTL_Editor_PL.gui
{
    partial class frmArtefactDescription
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
            this.RemoveLanguageButton = new System.Windows.Forms.Button();
            this.AddLanguageButton = new System.Windows.Forms.Button();
            this.DescriptionsDataGrid = new System.Windows.Forms.DataGridView();
            this.Language_2_Column = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.EnumeratedPanel = new System.Windows.Forms.Panel();
            this.CancelButton = new System.Windows.Forms.Button();
            this.SaveButton = new System.Windows.Forms.Button();
            this.ApplyLanguagesCheckBox = new System.Windows.Forms.CheckBox();
            ((System.ComponentModel.ISupportInitialize)(this.DescriptionsDataGrid)).BeginInit();
            this.EnumeratedPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // RemoveLanguageButton
            // 
            this.RemoveLanguageButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.RemoveLanguageButton.FlatAppearance.BorderSize = 0;
            this.RemoveLanguageButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.RemoveLanguageButton.Font = new System.Drawing.Font("Arial", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.RemoveLanguageButton.ForeColor = System.Drawing.Color.White;
            this.RemoveLanguageButton.Location = new System.Drawing.Point(637, 41);
            this.RemoveLanguageButton.Name = "RemoveLanguageButton";
            this.RemoveLanguageButton.Size = new System.Drawing.Size(38, 37);
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
            this.AddLanguageButton.Location = new System.Drawing.Point(634, -1);
            this.AddLanguageButton.Name = "AddLanguageButton";
            this.AddLanguageButton.Size = new System.Drawing.Size(47, 37);
            this.AddLanguageButton.TabIndex = 14;
            this.AddLanguageButton.Text = "+";
            this.AddLanguageButton.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.AddLanguageButton.UseVisualStyleBackColor = true;
            this.AddLanguageButton.Click += new System.EventHandler(this.AddLanguageButton_Click);
            // 
            // DescriptionsDataGrid
            // 
            this.DescriptionsDataGrid.AllowUserToAddRows = false;
            this.DescriptionsDataGrid.AllowUserToDeleteRows = false;
            this.DescriptionsDataGrid.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.DescriptionsDataGrid.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.DescriptionsDataGrid.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DescriptionsDataGrid.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.Language_2_Column});
            this.DescriptionsDataGrid.Location = new System.Drawing.Point(4, 3);
            this.DescriptionsDataGrid.Name = "DescriptionsDataGrid";
            this.DescriptionsDataGrid.RowHeadersVisible = false;
            this.DescriptionsDataGrid.Size = new System.Drawing.Size(627, 85);
            this.DescriptionsDataGrid.TabIndex = 7;
            // 
            // Language_2_Column
            // 
            this.Language_2_Column.HeaderText = "EN";
            this.Language_2_Column.Name = "Language_2_Column";
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
            this.EnumeratedPanel.Controls.Add(this.DescriptionsDataGrid);
            this.EnumeratedPanel.Location = new System.Drawing.Point(3, 3);
            this.EnumeratedPanel.Name = "EnumeratedPanel";
            this.EnumeratedPanel.Size = new System.Drawing.Size(682, 93);
            this.EnumeratedPanel.TabIndex = 11;
            // 
            // CancelButton
            // 
            this.CancelButton.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.CancelButton.BackColor = System.Drawing.Color.Gainsboro;
            this.CancelButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.CancelButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.CancelButton.Location = new System.Drawing.Point(349, 121);
            this.CancelButton.Name = "CancelButton";
            this.CancelButton.Size = new System.Drawing.Size(75, 23);
            this.CancelButton.TabIndex = 14;
            this.CancelButton.Text = "Cancel";
            this.CancelButton.UseVisualStyleBackColor = false;
            this.CancelButton.Click += new System.EventHandler(this.CancelButton_Click);
            // 
            // SaveButton
            // 
            this.SaveButton.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.SaveButton.BackColor = System.Drawing.Color.Gainsboro;
            this.SaveButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.SaveButton.Location = new System.Drawing.Point(268, 121);
            this.SaveButton.Name = "SaveButton";
            this.SaveButton.Size = new System.Drawing.Size(75, 23);
            this.SaveButton.TabIndex = 13;
            this.SaveButton.Text = "Save";
            this.SaveButton.UseVisualStyleBackColor = false;
            this.SaveButton.Click += new System.EventHandler(this.SaveButton_Click);
            // 
            // ApplyLanguagesCheckBox
            // 
            this.ApplyLanguagesCheckBox.AutoSize = true;
            this.ApplyLanguagesCheckBox.Location = new System.Drawing.Point(8, 99);
            this.ApplyLanguagesCheckBox.Name = "ApplyLanguagesCheckBox";
            this.ApplyLanguagesCheckBox.Size = new System.Drawing.Size(222, 17);
            this.ApplyLanguagesCheckBox.TabIndex = 15;
            this.ApplyLanguagesCheckBox.Text = "Apply the languages to the whole artefact";
            this.ApplyLanguagesCheckBox.UseVisualStyleBackColor = true;
            // 
            // frmArtefactDescription
            // 
            this.AcceptButton = this.SaveButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightSteelBlue;
            this.ClientSize = new System.Drawing.Size(688, 148);
            this.Controls.Add(this.ApplyLanguagesCheckBox);
            this.Controls.Add(this.CancelButton);
            this.Controls.Add(this.SaveButton);
            this.Controls.Add(this.EnumeratedPanel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "frmArtefactDescription";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Artefact name";
            this.Load += new System.EventHandler(this.frmArtefactDescription_Load);
            ((System.ComponentModel.ISupportInitialize)(this.DescriptionsDataGrid)).EndInit();
            this.EnumeratedPanel.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button RemoveLanguageButton;
        private System.Windows.Forms.Button AddLanguageButton;
        private System.Windows.Forms.DataGridView DescriptionsDataGrid;
        private System.Windows.Forms.Panel EnumeratedPanel;
        private System.Windows.Forms.Button CancelButton;
        private System.Windows.Forms.Button SaveButton;
        private System.Windows.Forms.DataGridViewTextBoxColumn Language_2_Column;
        private System.Windows.Forms.CheckBox ApplyLanguagesCheckBox;
    }
}