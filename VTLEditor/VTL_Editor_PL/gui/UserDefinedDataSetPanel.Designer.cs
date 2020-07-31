namespace VTL_Editor_PL.gui
{
    partial class UserDefinedDataSetPanel
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
            this.DataSetContainerPanel = new System.Windows.Forms.Panel();
            this.DSDdataGridView = new System.Windows.Forms.DataGridView();
            this.dataStructureID = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.dataStructureName = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.label7 = new System.Windows.Forms.Label();
            this.DataSetContainerPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DSDdataGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // DataSetContainerPanel
            // 
            this.DataSetContainerPanel.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.DataSetContainerPanel.BackColor = System.Drawing.Color.LightSteelBlue;
            this.DataSetContainerPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.DataSetContainerPanel.Controls.Add(this.DSDdataGridView);
            this.DataSetContainerPanel.Controls.Add(this.label7);
            this.DataSetContainerPanel.Location = new System.Drawing.Point(0, 0);
            this.DataSetContainerPanel.Name = "DataSetContainerPanel";
            this.DataSetContainerPanel.Size = new System.Drawing.Size(791, 501);
            this.DataSetContainerPanel.TabIndex = 22;
            // 
            // DSDdataGridView
            // 
            this.DSDdataGridView.AllowUserToAddRows = false;
            this.DSDdataGridView.AllowUserToDeleteRows = false;
            this.DSDdataGridView.AllowUserToOrderColumns = true;
            this.DSDdataGridView.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.DSDdataGridView.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.DSDdataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.DSDdataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.dataStructureID,
            this.dataStructureName});
            this.DSDdataGridView.Location = new System.Drawing.Point(6, 31);
            this.DSDdataGridView.MultiSelect = false;
            this.DSDdataGridView.Name = "DSDdataGridView";
            this.DSDdataGridView.RowHeadersVisible = false;
            this.DSDdataGridView.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.DSDdataGridView.Size = new System.Drawing.Size(776, 455);
            this.DSDdataGridView.TabIndex = 1;
            // 
            // dataStructureID
            // 
            this.dataStructureID.HeaderText = "ID";
            this.dataStructureID.Name = "dataStructureID";
            // 
            // dataStructureName
            // 
            this.dataStructureName.HeaderText = "Name";
            this.dataStructureName.Name = "dataStructureName";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(3, 10);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(513, 13);
            this.label7.TabIndex = 0;
            this.label7.Text = "Select the data structure from which the new dataset has to inherits the componen" +
    "ts and the value domains";
            // 
            // UserDefinedDataSetPanel
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.DataSetContainerPanel);
            this.Name = "UserDefinedDataSetPanel";
            this.Size = new System.Drawing.Size(793, 501);
            this.Load += new System.EventHandler(this.UserDefinedDataSetPanel_Load);
            this.DataSetContainerPanel.ResumeLayout(false);
            this.DataSetContainerPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DSDdataGridView)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel DataSetContainerPanel;
        private System.Windows.Forms.DataGridView DSDdataGridView;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataStructureID;
        private System.Windows.Forms.DataGridViewTextBoxColumn dataStructureName;
        private System.Windows.Forms.Label label7;
    }
}
