namespace VTL_Editor_PL.gui
{
    partial class UserDefinedDataStructurePanel
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
            this.DataStructurePanel = new System.Windows.Forms.Panel();
            this.AddComponentButton = new System.Windows.Forms.Button();
            this.RemoveComponentButton = new System.Windows.Forms.Button();
            this.DataStructureComponentDataGridView = new System.Windows.Forms.DataGridView();
            this.ComponentID = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.ComponentName = new System.Windows.Forms.DataGridViewComboBoxColumn();
            this.EditName = new System.Windows.Forms.DataGridViewButtonColumn();
            this.Role = new System.Windows.Forms.DataGridViewComboBoxColumn();
            this.ValueDomain = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.ValueDomainButton = new System.Windows.Forms.DataGridViewButtonColumn();
            this.DataStructurePanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.DataStructureComponentDataGridView)).BeginInit();
            this.SuspendLayout();
            // 
            // DataStructurePanel
            // 
            this.DataStructurePanel.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.DataStructurePanel.BackColor = System.Drawing.Color.SteelBlue;
            this.DataStructurePanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.DataStructurePanel.Controls.Add(this.AddComponentButton);
            this.DataStructurePanel.Controls.Add(this.RemoveComponentButton);
            this.DataStructurePanel.Controls.Add(this.DataStructureComponentDataGridView);
            this.DataStructurePanel.Location = new System.Drawing.Point(0, 0);
            this.DataStructurePanel.Name = "DataStructurePanel";
            this.DataStructurePanel.Size = new System.Drawing.Size(793, 500);
            this.DataStructurePanel.TabIndex = 23;
            // 
            // AddComponentButton
            // 
            this.AddComponentButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.AddComponentButton.FlatAppearance.BorderSize = 0;
            this.AddComponentButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.AddComponentButton.Font = new System.Drawing.Font("Arial", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.AddComponentButton.ForeColor = System.Drawing.Color.White;
            this.AddComponentButton.Location = new System.Drawing.Point(744, 3);
            this.AddComponentButton.Name = "AddComponentButton";
            this.AddComponentButton.Size = new System.Drawing.Size(47, 37);
            this.AddComponentButton.TabIndex = 20;
            this.AddComponentButton.Text = "+";
            this.AddComponentButton.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.AddComponentButton.UseVisualStyleBackColor = true;
            this.AddComponentButton.Click += new System.EventHandler(this.AddComponentButton_Click);
            // 
            // RemoveComponentButton
            // 
            this.RemoveComponentButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.RemoveComponentButton.FlatAppearance.BorderSize = 0;
            this.RemoveComponentButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.RemoveComponentButton.Font = new System.Drawing.Font("Arial", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.RemoveComponentButton.ForeColor = System.Drawing.Color.White;
            this.RemoveComponentButton.Location = new System.Drawing.Point(744, 46);
            this.RemoveComponentButton.Name = "RemoveComponentButton";
            this.RemoveComponentButton.Size = new System.Drawing.Size(47, 37);
            this.RemoveComponentButton.TabIndex = 19;
            this.RemoveComponentButton.Text = "-";
            this.RemoveComponentButton.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.RemoveComponentButton.UseVisualStyleBackColor = true;
            this.RemoveComponentButton.Click += new System.EventHandler(this.RemoveComponentButton_Click);
            // 
            // DataStructureComponentDataGridView
            // 
            this.DataStructureComponentDataGridView.AllowUserToAddRows = false;
            this.DataStructureComponentDataGridView.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.DataStructureComponentDataGridView.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
            this.DataStructureComponentDataGridView.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.ComponentID,
            this.ComponentName,
            this.EditName,
            this.Role,
            this.ValueDomain,
            this.ValueDomainButton});
            this.DataStructureComponentDataGridView.Location = new System.Drawing.Point(6, 3);
            this.DataStructureComponentDataGridView.Name = "DataStructureComponentDataGridView";
            this.DataStructureComponentDataGridView.RowHeadersVisible = false;
            this.DataStructureComponentDataGridView.Size = new System.Drawing.Size(738, 488);
            this.DataStructureComponentDataGridView.TabIndex = 0;
            this.DataStructureComponentDataGridView.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.DataStructureComponentDataGridView_CellContentClick);
            // 
            // ComponentID
            // 
            this.ComponentID.FillWeight = 271.6817F;
            this.ComponentID.HeaderText = "Component ID";
            this.ComponentID.MinimumWidth = 140;
            this.ComponentID.Name = "ComponentID";
            // 
            // ComponentName
            // 
            this.ComponentName.FillWeight = 186.2584F;
            this.ComponentName.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.ComponentName.HeaderText = "Component name";
            this.ComponentName.MinimumWidth = 120;
            this.ComponentName.Name = "ComponentName";
            // 
            // EditName
            // 
            this.EditName.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.None;
            this.EditName.FillWeight = 126.9036F;
            this.EditName.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.EditName.HeaderText = "";
            this.EditName.MinimumWidth = 80;
            this.EditName.Name = "EditName";
            this.EditName.Resizable = System.Windows.Forms.DataGridViewTriState.False;
            this.EditName.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.Automatic;
            this.EditName.Text = "Edit name";
            this.EditName.Width = 80;
            // 
            // Role
            // 
            this.Role.FillWeight = 6.982755F;
            this.Role.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.Role.HeaderText = "Role";
            this.Role.MinimumWidth = 80;
            this.Role.Name = "Role";
            // 
            // ValueDomain
            // 
            this.ValueDomain.FillWeight = 5.390367F;
            this.ValueDomain.HeaderText = "Value domain";
            this.ValueDomain.MinimumWidth = 150;
            this.ValueDomain.Name = "ValueDomain";
            this.ValueDomain.Resizable = System.Windows.Forms.DataGridViewTriState.True;
            this.ValueDomain.SortMode = System.Windows.Forms.DataGridViewColumnSortMode.NotSortable;
            // 
            // ValueDomainButton
            // 
            this.ValueDomainButton.FillWeight = 2.783161F;
            this.ValueDomainButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.ValueDomainButton.HeaderText = "";
            this.ValueDomainButton.MinimumWidth = 90;
            this.ValueDomainButton.Name = "ValueDomainButton";
            this.ValueDomainButton.Text = "Get domain...";
            // 
            // UserDefinedDataStructurePanel
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.DataStructurePanel);
            this.Name = "UserDefinedDataStructurePanel";
            this.Size = new System.Drawing.Size(793, 501);
            this.Load += new System.EventHandler(this.UserDefinedDataStructurePanel_Load);
            this.DataStructurePanel.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.DataStructureComponentDataGridView)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel DataStructurePanel;
        private System.Windows.Forms.Button AddComponentButton;
        private System.Windows.Forms.Button RemoveComponentButton;
        private System.Windows.Forms.DataGridView DataStructureComponentDataGridView;
        private System.Windows.Forms.DataGridViewTextBoxColumn ComponentID;
        private System.Windows.Forms.DataGridViewComboBoxColumn ComponentName;
        private System.Windows.Forms.DataGridViewButtonColumn EditName;
        private System.Windows.Forms.DataGridViewComboBoxColumn Role;
        private System.Windows.Forms.DataGridViewTextBoxColumn ValueDomain;
        private System.Windows.Forms.DataGridViewButtonColumn ValueDomainButton;
    }
}
