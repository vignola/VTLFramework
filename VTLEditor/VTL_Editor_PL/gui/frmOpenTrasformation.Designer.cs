namespace VTL_Editor_PL.gui
{
    partial class frmOpenTrasformation
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
            this.OpenButton = new System.Windows.Forms.Button();
            this.CloseButton = new System.Windows.Forms.Button();
            this.RemoveButton = new System.Windows.Forms.Button();
            this.TrasDataGridView = new System.Windows.Forms.DataGridView();
            this.VTL_ID = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.SDMX_ID = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.SDMX_Agency = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.SDMX_Version = new System.Windows.Forms.DataGridViewTextBoxColumn();
            ((System.ComponentModel.ISupportInitialize)(this.TrasDataGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // OpenButton
            // 
            this.OpenButton.BackColor = System.Drawing.Color.Gainsboro;
            this.OpenButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.OpenButton.Location = new System.Drawing.Point(198, 208);
            this.OpenButton.Name = "OpenButton";
            this.OpenButton.Size = new System.Drawing.Size(75, 23);
            this.OpenButton.TabIndex = 1;
            this.OpenButton.Text = "Open";
            this.OpenButton.UseVisualStyleBackColor = false;
            this.OpenButton.Click += new System.EventHandler(this.OpenButton_Click);
            // 
            // CloseButton
            // 
            this.CloseButton.BackColor = System.Drawing.Color.Gainsboro;
            this.CloseButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.CloseButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.CloseButton.Location = new System.Drawing.Point(279, 208);
            this.CloseButton.Name = "CloseButton";
            this.CloseButton.Size = new System.Drawing.Size(75, 23);
            this.CloseButton.TabIndex = 2;
            this.CloseButton.Text = "Close";
            this.CloseButton.UseVisualStyleBackColor = false;
            this.CloseButton.Click += new System.EventHandler(this.CloseButton_Click);
            // 
            // RemoveButton
            // 
            this.RemoveButton.FlatAppearance.BorderSize = 0;
            this.RemoveButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.RemoveButton.Font = new System.Drawing.Font("Arial", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.RemoveButton.ForeColor = System.Drawing.Color.SteelBlue;
            this.RemoveButton.Location = new System.Drawing.Point(514, 4);
            this.RemoveButton.Name = "RemoveButton";
            this.RemoveButton.Size = new System.Drawing.Size(33, 38);
            this.RemoveButton.TabIndex = 3;
            this.RemoveButton.Text = "-";
            this.RemoveButton.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.RemoveButton.UseVisualStyleBackColor = true;
            this.RemoveButton.Click += new System.EventHandler(this.RemoveButton_Click);
            // 
            // TrasDataGridView
            // 
            this.TrasDataGridView.AllowUserToAddRows = false;
            this.TrasDataGridView.AllowUserToDeleteRows = false;
            this.TrasDataGridView.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.TrasDataGridView.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.TrasDataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.VTL_ID,
            this.SDMX_ID,
            this.SDMX_Agency,
            this.SDMX_Version});
            this.TrasDataGridView.Location = new System.Drawing.Point(3, 4);
            this.TrasDataGridView.Name = "TrasDataGridView";
            this.TrasDataGridView.RowHeadersVisible = false;
            this.TrasDataGridView.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.TrasDataGridView.Size = new System.Drawing.Size(505, 198);
            this.TrasDataGridView.TabIndex = 4;
            this.TrasDataGridView.CellContentDoubleClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.TrasDataGridView_CellContentDoubleClick);
            // 
            // VTL_ID
            // 
            this.VTL_ID.HeaderText = "VTL ID";
            this.VTL_ID.Name = "VTL_ID";
            // 
            // SDMX_ID
            // 
            this.SDMX_ID.FillWeight = 60F;
            this.SDMX_ID.HeaderText = "SDMX ID";
            this.SDMX_ID.Name = "SDMX_ID";
            // 
            // SDMX_Agency
            // 
            this.SDMX_Agency.FillWeight = 60F;
            this.SDMX_Agency.HeaderText = "Agency";
            this.SDMX_Agency.Name = "SDMX_Agency";
            // 
            // SDMX_Version
            // 
            this.SDMX_Version.FillWeight = 40F;
            this.SDMX_Version.HeaderText = "Version";
            this.SDMX_Version.Name = "SDMX_Version";
            // 
            // frmOpenTrasformation
            // 
            this.AcceptButton = this.OpenButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightSteelBlue;
            this.CancelButton = this.CloseButton;
            this.ClientSize = new System.Drawing.Size(551, 235);
            this.Controls.Add(this.TrasDataGridView);
            this.Controls.Add(this.RemoveButton);
            this.Controls.Add(this.CloseButton);
            this.Controls.Add(this.OpenButton);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "frmOpenTrasformation";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Transformation scheme";
            this.Load += new System.EventHandler(this.frmOpenTrasformation_Load);
            ((System.ComponentModel.ISupportInitialize)(this.TrasDataGridView)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button OpenButton;
        private System.Windows.Forms.Button CloseButton;
        private System.Windows.Forms.Button RemoveButton;
        private System.Windows.Forms.DataGridView TrasDataGridView;
        private System.Windows.Forms.DataGridViewTextBoxColumn VTL_ID;
        private System.Windows.Forms.DataGridViewTextBoxColumn SDMX_ID;
        private System.Windows.Forms.DataGridViewTextBoxColumn SDMX_Agency;
        private System.Windows.Forms.DataGridViewTextBoxColumn SDMX_Version;
    }
}