namespace VTL_Editor_PL.gui
{
    partial class frmArtefactManager
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
            this.splitContainer1 = new System.Windows.Forms.SplitContainer();
            this.ArtefactContainerPanel = new System.Windows.Forms.Panel();
            this.AddArtefactButton = new System.Windows.Forms.Button();
            this.RemoveArtefactButton = new System.Windows.Forms.Button();
            this.ArtefactTreeView = new System.Windows.Forms.TreeView();
            this.label4 = new System.Windows.Forms.Label();
            this.CloseButton = new System.Windows.Forms.Button();
            this.ArtefactIDPanel = new System.Windows.Forms.Panel();
            this.label5 = new System.Windows.Forms.Label();
            this.AgencyLabel = new System.Windows.Forms.Label();
            this.SdmxVersionTextBox = new System.Windows.Forms.TextBox();
            this.SdmxAgencyTextBox = new System.Windows.Forms.TextBox();
            this.ArtafactLabelPanel = new System.Windows.Forms.Panel();
            this.ArtefactTypeLabel = new System.Windows.Forms.Label();
            this.DescriptionEditButton = new System.Windows.Forms.Button();
            this.vtl_descriptionComboBox = new System.Windows.Forms.ComboBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.vtl_IdTextBox = new System.Windows.Forms.TextBox();
            this.SaveButton = new System.Windows.Forms.Button();
            this.panelStartMessage = new System.Windows.Forms.Panel();
            this.label6 = new System.Windows.Forms.Label();
            this.ArtefactTreeViewSwitch = new VTL_Editor_PL.gui.Switch();
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).BeginInit();
            this.splitContainer1.Panel1.SuspendLayout();
            this.splitContainer1.Panel2.SuspendLayout();
            this.splitContainer1.SuspendLayout();
            this.ArtefactContainerPanel.SuspendLayout();
            this.ArtefactIDPanel.SuspendLayout();
            this.ArtafactLabelPanel.SuspendLayout();
            this.panelStartMessage.SuspendLayout();
            this.SuspendLayout();
            // 
            // splitContainer1
            // 
            this.splitContainer1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.splitContainer1.Location = new System.Drawing.Point(0, 0);
            this.splitContainer1.Name = "splitContainer1";
            // 
            // splitContainer1.Panel1
            // 
            this.splitContainer1.Panel1.Controls.Add(this.ArtefactContainerPanel);
            // 
            // splitContainer1.Panel2
            // 
            this.splitContainer1.Panel2.Controls.Add(this.CloseButton);
            this.splitContainer1.Panel2.Controls.Add(this.ArtefactIDPanel);
            this.splitContainer1.Panel2.Controls.Add(this.SaveButton);
            this.splitContainer1.Panel2.Controls.Add(this.panelStartMessage);
            this.splitContainer1.Size = new System.Drawing.Size(1030, 627);
            this.splitContainer1.SplitterDistance = 233;
            this.splitContainer1.TabIndex = 18;
            // 
            // ArtefactContainerPanel
            // 
            this.ArtefactContainerPanel.BackColor = System.Drawing.Color.SteelBlue;
            this.ArtefactContainerPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ArtefactContainerPanel.Controls.Add(this.ArtefactTreeViewSwitch);
            this.ArtefactContainerPanel.Controls.Add(this.AddArtefactButton);
            this.ArtefactContainerPanel.Controls.Add(this.RemoveArtefactButton);
            this.ArtefactContainerPanel.Controls.Add(this.ArtefactTreeView);
            this.ArtefactContainerPanel.Controls.Add(this.label4);
            this.ArtefactContainerPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.ArtefactContainerPanel.Location = new System.Drawing.Point(0, 0);
            this.ArtefactContainerPanel.Name = "ArtefactContainerPanel";
            this.ArtefactContainerPanel.Size = new System.Drawing.Size(233, 627);
            this.ArtefactContainerPanel.TabIndex = 17;
            // 
            // AddArtefactButton
            // 
            this.AddArtefactButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.AddArtefactButton.FlatAppearance.BorderSize = 0;
            this.AddArtefactButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.AddArtefactButton.Font = new System.Drawing.Font("Arial", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.AddArtefactButton.ForeColor = System.Drawing.Color.White;
            this.AddArtefactButton.Location = new System.Drawing.Point(128, 584);
            this.AddArtefactButton.Name = "AddArtefactButton";
            this.AddArtefactButton.Size = new System.Drawing.Size(47, 37);
            this.AddArtefactButton.TabIndex = 16;
            this.AddArtefactButton.Text = "+";
            this.AddArtefactButton.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.AddArtefactButton.UseVisualStyleBackColor = true;
            this.AddArtefactButton.Click += new System.EventHandler(this.AddArtefactButton_Click);
            // 
            // RemoveArtefactButton
            // 
            this.RemoveArtefactButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.RemoveArtefactButton.FlatAppearance.BorderSize = 0;
            this.RemoveArtefactButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.RemoveArtefactButton.Font = new System.Drawing.Font("Arial", 20.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.RemoveArtefactButton.ForeColor = System.Drawing.Color.White;
            this.RemoveArtefactButton.Location = new System.Drawing.Point(181, 584);
            this.RemoveArtefactButton.Name = "RemoveArtefactButton";
            this.RemoveArtefactButton.Size = new System.Drawing.Size(47, 37);
            this.RemoveArtefactButton.TabIndex = 17;
            this.RemoveArtefactButton.Text = "-";
            this.RemoveArtefactButton.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.RemoveArtefactButton.UseVisualStyleBackColor = true;
            this.RemoveArtefactButton.Click += new System.EventHandler(this.RemoveArtefactButton_Click);
            // 
            // ArtefactTreeView
            // 
            this.ArtefactTreeView.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.ArtefactTreeView.Location = new System.Drawing.Point(3, 24);
            this.ArtefactTreeView.Name = "ArtefactTreeView";
            this.ArtefactTreeView.Size = new System.Drawing.Size(225, 561);
            this.ArtefactTreeView.TabIndex = 14;
            this.ArtefactTreeView.NodeMouseClick += new System.Windows.Forms.TreeNodeMouseClickEventHandler(this.ArtefactTreeView_NodeMouseClick);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.ForeColor = System.Drawing.Color.White;
            this.label4.Location = new System.Drawing.Point(13, 7);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(67, 13);
            this.label4.TabIndex = 15;
            this.label4.Text = "VTL Artefact";
            // 
            // CloseButton
            // 
            this.CloseButton.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.CloseButton.BackColor = System.Drawing.Color.Gainsboro;
            this.CloseButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.CloseButton.Location = new System.Drawing.Point(396, 601);
            this.CloseButton.Name = "CloseButton";
            this.CloseButton.Size = new System.Drawing.Size(103, 23);
            this.CloseButton.TabIndex = 23;
            this.CloseButton.Text = "Close";
            this.CloseButton.UseVisualStyleBackColor = false;
            this.CloseButton.Click += new System.EventHandler(this.CloseButton_Click);
            // 
            // ArtefactIDPanel
            // 
            this.ArtefactIDPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ArtefactIDPanel.Controls.Add(this.label5);
            this.ArtefactIDPanel.Controls.Add(this.AgencyLabel);
            this.ArtefactIDPanel.Controls.Add(this.SdmxVersionTextBox);
            this.ArtefactIDPanel.Controls.Add(this.SdmxAgencyTextBox);
            this.ArtefactIDPanel.Controls.Add(this.ArtafactLabelPanel);
            this.ArtefactIDPanel.Controls.Add(this.DescriptionEditButton);
            this.ArtefactIDPanel.Controls.Add(this.vtl_descriptionComboBox);
            this.ArtefactIDPanel.Controls.Add(this.label2);
            this.ArtefactIDPanel.Controls.Add(this.label1);
            this.ArtefactIDPanel.Controls.Add(this.vtl_IdTextBox);
            this.ArtefactIDPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.ArtefactIDPanel.Location = new System.Drawing.Point(0, 0);
            this.ArtefactIDPanel.Name = "ArtefactIDPanel";
            this.ArtefactIDPanel.Size = new System.Drawing.Size(793, 87);
            this.ArtefactIDPanel.TabIndex = 21;
            this.ArtefactIDPanel.Visible = false;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(587, 35);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(42, 13);
            this.label5.TabIndex = 21;
            this.label5.Text = "Version";
            // 
            // AgencyLabel
            // 
            this.AgencyLabel.AutoSize = true;
            this.AgencyLabel.Location = new System.Drawing.Point(341, 35);
            this.AgencyLabel.Name = "AgencyLabel";
            this.AgencyLabel.Size = new System.Drawing.Size(43, 13);
            this.AgencyLabel.TabIndex = 20;
            this.AgencyLabel.Text = "Agency";
            // 
            // SdmxVersionTextBox
            // 
            this.SdmxVersionTextBox.Location = new System.Drawing.Point(636, 28);
            this.SdmxVersionTextBox.Name = "SdmxVersionTextBox";
            this.SdmxVersionTextBox.Size = new System.Drawing.Size(147, 20);
            this.SdmxVersionTextBox.TabIndex = 13;
            // 
            // SdmxAgencyTextBox
            // 
            this.SdmxAgencyTextBox.Location = new System.Drawing.Point(395, 28);
            this.SdmxAgencyTextBox.Name = "SdmxAgencyTextBox";
            this.SdmxAgencyTextBox.Size = new System.Drawing.Size(137, 20);
            this.SdmxAgencyTextBox.TabIndex = 12;
            // 
            // ArtafactLabelPanel
            // 
            this.ArtafactLabelPanel.BackColor = System.Drawing.Color.SteelBlue;
            this.ArtafactLabelPanel.Controls.Add(this.ArtefactTypeLabel);
            this.ArtafactLabelPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.ArtafactLabelPanel.Location = new System.Drawing.Point(0, 0);
            this.ArtafactLabelPanel.Name = "ArtafactLabelPanel";
            this.ArtafactLabelPanel.Size = new System.Drawing.Size(791, 21);
            this.ArtafactLabelPanel.TabIndex = 17;
            // 
            // ArtefactTypeLabel
            // 
            this.ArtefactTypeLabel.AutoSize = true;
            this.ArtefactTypeLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ArtefactTypeLabel.ForeColor = System.Drawing.Color.White;
            this.ArtefactTypeLabel.Location = new System.Drawing.Point(3, 5);
            this.ArtefactTypeLabel.Name = "ArtefactTypeLabel";
            this.ArtefactTypeLabel.Size = new System.Drawing.Size(83, 13);
            this.ArtefactTypeLabel.TabIndex = 16;
            this.ArtefactTypeLabel.Text = "Value domain";
            // 
            // DescriptionEditButton
            // 
            this.DescriptionEditButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.DescriptionEditButton.BackColor = System.Drawing.Color.Gainsboro;
            this.DescriptionEditButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.DescriptionEditButton.Location = new System.Drawing.Point(693, 59);
            this.DescriptionEditButton.Name = "DescriptionEditButton";
            this.DescriptionEditButton.Size = new System.Drawing.Size(90, 23);
            this.DescriptionEditButton.TabIndex = 15;
            this.DescriptionEditButton.Text = "Edit...";
            this.DescriptionEditButton.UseVisualStyleBackColor = false;
            this.DescriptionEditButton.Click += new System.EventHandler(this.EditDescription_Click);
            // 
            // vtl_descriptionComboBox
            // 
            this.vtl_descriptionComboBox.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.vtl_descriptionComboBox.FormattingEnabled = true;
            this.vtl_descriptionComboBox.ItemHeight = 13;
            this.vtl_descriptionComboBox.Location = new System.Drawing.Point(83, 58);
            this.vtl_descriptionComboBox.Name = "vtl_descriptionComboBox";
            this.vtl_descriptionComboBox.Size = new System.Drawing.Size(604, 21);
            this.vtl_descriptionComboBox.TabIndex = 14;
            this.vtl_descriptionComboBox.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.vtl_descriptionComboBox_KeyPress);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(17, 61);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(35, 13);
            this.label2.TabIndex = 13;
            this.label2.Text = "Name";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(17, 35);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(18, 13);
            this.label1.TabIndex = 12;
            this.label1.Text = "ID";
            // 
            // vtl_IdTextBox
            // 
            this.vtl_IdTextBox.Location = new System.Drawing.Point(83, 28);
            this.vtl_IdTextBox.Name = "vtl_IdTextBox";
            this.vtl_IdTextBox.Size = new System.Drawing.Size(195, 20);
            this.vtl_IdTextBox.TabIndex = 11;
            // 
            // SaveButton
            // 
            this.SaveButton.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.SaveButton.BackColor = System.Drawing.Color.Gainsboro;
            this.SaveButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.SaveButton.Location = new System.Drawing.Point(287, 601);
            this.SaveButton.Name = "SaveButton";
            this.SaveButton.Size = new System.Drawing.Size(103, 23);
            this.SaveButton.TabIndex = 18;
            this.SaveButton.Text = "Save";
            this.SaveButton.UseVisualStyleBackColor = false;
            this.SaveButton.Visible = false;
            this.SaveButton.Click += new System.EventHandler(this.SaveButton_Click);
            // 
            // panelStartMessage
            // 
            this.panelStartMessage.BackColor = System.Drawing.Color.White;
            this.panelStartMessage.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panelStartMessage.Controls.Add(this.label6);
            this.panelStartMessage.Location = new System.Drawing.Point(10, 10);
            this.panelStartMessage.Name = "panelStartMessage";
            this.panelStartMessage.Size = new System.Drawing.Size(781, 41);
            this.panelStartMessage.TabIndex = 19;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label6.Location = new System.Drawing.Point(12, 10);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(708, 16);
            this.label6.TabIndex = 0;
            this.label6.Text = "In order to create, modify or remove a user defined artefact: click on the tree m" +
    "enu on the left and select an artefact type.";
            // 
            // ArtefactTreeViewSwitch
            // 
            this.ArtefactTreeViewSwitch.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.ArtefactTreeViewSwitch.BackColor = System.Drawing.Color.Gray;
            this.ArtefactTreeViewSwitch.Location = new System.Drawing.Point(103, 3);
            this.ArtefactTreeViewSwitch.Name = "ArtefactTreeViewSwitch";
            this.ArtefactTreeViewSwitch.Size = new System.Drawing.Size(125, 21);
            this.ArtefactTreeViewSwitch.TabIndex = 18;
            // 
            // frmArtefactManager
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightSteelBlue;
            this.ClientSize = new System.Drawing.Size(1030, 627);
            this.Controls.Add(this.splitContainer1);
            this.Name = "frmArtefactManager";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "User defined artefact manager";
            this.Load += new System.EventHandler(this.frmArtefactManager_Load);
            this.splitContainer1.Panel1.ResumeLayout(false);
            this.splitContainer1.Panel2.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.splitContainer1)).EndInit();
            this.splitContainer1.ResumeLayout(false);
            this.ArtefactContainerPanel.ResumeLayout(false);
            this.ArtefactContainerPanel.PerformLayout();
            this.ArtefactIDPanel.ResumeLayout(false);
            this.ArtefactIDPanel.PerformLayout();
            this.ArtafactLabelPanel.ResumeLayout(false);
            this.ArtafactLabelPanel.PerformLayout();
            this.panelStartMessage.ResumeLayout(false);
            this.panelStartMessage.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.SplitContainer splitContainer1;
        private System.Windows.Forms.Panel ArtefactContainerPanel;
        private Switch ArtefactTreeViewSwitch;
        private System.Windows.Forms.Button AddArtefactButton;
        private System.Windows.Forms.Button RemoveArtefactButton;
        private System.Windows.Forms.TreeView ArtefactTreeView;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Panel ArtefactIDPanel;
        private System.Windows.Forms.Panel ArtafactLabelPanel;
        private System.Windows.Forms.Label ArtefactTypeLabel;
        private System.Windows.Forms.Button DescriptionEditButton;
        private System.Windows.Forms.ComboBox vtl_descriptionComboBox;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox vtl_IdTextBox;
        private System.Windows.Forms.Button SaveButton;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label AgencyLabel;
        private System.Windows.Forms.TextBox SdmxVersionTextBox;
        private System.Windows.Forms.TextBox SdmxAgencyTextBox;
        private System.Windows.Forms.Button CloseButton;
        private System.Windows.Forms.Panel panelStartMessage;
        private System.Windows.Forms.Label label6;

    }
}