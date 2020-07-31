namespace VTL_Editor_PL.gui
{
    partial class frmMetadataImport
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(frmMetadataImport));
            this.treeView1 = new System.Windows.Forms.TreeView();
            this.panelLabelFirstTreeView = new System.Windows.Forms.Panel();
            this.switchTreeView = new VTL_Editor_PL.gui.Switch();
            this.lblFirstTreeView = new System.Windows.Forms.Label();
            this.button2 = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.infoLabel = new System.Windows.Forms.Label();
            this.backgroundWorker1 = new System.ComponentModel.BackgroundWorker();
            this.imageList1 = new System.Windows.Forms.ImageList(this.components);
            this.panelLabelFirstTreeView.SuspendLayout();
            this.SuspendLayout();
            // 
            // treeView1
            // 
            this.treeView1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.treeView1.Location = new System.Drawing.Point(3, 29);
            this.treeView1.Name = "treeView1";
            this.treeView1.Size = new System.Drawing.Size(933, 424);
            this.treeView1.TabIndex = 0;
            this.treeView1.NodeMouseClick += new System.Windows.Forms.TreeNodeMouseClickEventHandler(this.treeView1_NodeMouseClick);
            this.treeView1.MouseDown += new System.Windows.Forms.MouseEventHandler(this.treeView1_MouseDown);
            // 
            // panelLabelFirstTreeView
            // 
            this.panelLabelFirstTreeView.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.panelLabelFirstTreeView.BackColor = System.Drawing.Color.SteelBlue;
            this.panelLabelFirstTreeView.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panelLabelFirstTreeView.Controls.Add(this.switchTreeView);
            this.panelLabelFirstTreeView.Controls.Add(this.lblFirstTreeView);
            this.panelLabelFirstTreeView.Location = new System.Drawing.Point(3, 6);
            this.panelLabelFirstTreeView.Name = "panelLabelFirstTreeView";
            this.panelLabelFirstTreeView.Size = new System.Drawing.Size(933, 23);
            this.panelLabelFirstTreeView.TabIndex = 13;
            // 
            // switchTreeView
            // 
            this.switchTreeView.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.switchTreeView.BackColor = System.Drawing.Color.SteelBlue;
            this.switchTreeView.Location = new System.Drawing.Point(797, 0);
            this.switchTreeView.Name = "switchTreeView";
            this.switchTreeView.Size = new System.Drawing.Size(125, 21);
            this.switchTreeView.TabIndex = 1;
            // 
            // lblFirstTreeView
            // 
            this.lblFirstTreeView.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.lblFirstTreeView.AutoSize = true;
            this.lblFirstTreeView.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblFirstTreeView.ForeColor = System.Drawing.Color.White;
            this.lblFirstTreeView.Location = new System.Drawing.Point(6, 6);
            this.lblFirstTreeView.Name = "lblFirstTreeView";
            this.lblFirstTreeView.Size = new System.Drawing.Size(108, 13);
            this.lblFirstTreeView.TabIndex = 0;
            this.lblFirstTreeView.Text = "Metadata browser";
            // 
            // button2
            // 
            this.button2.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.button2.BackColor = System.Drawing.Color.Gainsboro;
            this.button2.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.button2.Location = new System.Drawing.Point(428, 459);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 15;
            this.button2.Text = "Close";
            this.button2.UseVisualStyleBackColor = false;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(221, 464);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(35, 13);
            this.label1.TabIndex = 16;
            this.label1.Text = "label1";
            this.label1.Visible = false;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(619, 464);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(35, 13);
            this.label2.TabIndex = 17;
            this.label2.Text = "label2";
            this.label2.Visible = false;
            // 
            // infoLabel
            // 
            this.infoLabel.AutoSize = true;
            this.infoLabel.Location = new System.Drawing.Point(10, 464);
            this.infoLabel.Name = "infoLabel";
            this.infoLabel.Size = new System.Drawing.Size(10, 13);
            this.infoLabel.TabIndex = 18;
            this.infoLabel.Text = "-";
            // 
            // imageList1
            // 
            this.imageList1.ImageStream = ((System.Windows.Forms.ImageListStreamer)(resources.GetObject("imageList1.ImageStream")));
            this.imageList1.TransparentColor = System.Drawing.Color.Transparent;
            this.imageList1.Images.SetKeyName(0, "icons8-chevron-right-16.png");
            this.imageList1.Images.SetKeyName(1, "icons8-server-16.png");
            this.imageList1.Images.SetKeyName(2, "icons8-table-filled-16.png");
            this.imageList1.Images.SetKeyName(3, "icons8-grid-16.png");
            this.imageList1.Images.SetKeyName(4, "icons8-checklist-16.png");
            this.imageList1.Images.SetKeyName(5, "icons8-internet-16.png");
            this.imageList1.Images.SetKeyName(6, "icons8-outgoing-data-16.png");
            // 
            // frmMetadataImport
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightSteelBlue;
            this.ClientSize = new System.Drawing.Size(938, 484);
            this.Controls.Add(this.infoLabel);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.panelLabelFirstTreeView);
            this.Controls.Add(this.treeView1);
            this.Name = "frmMetadataImport";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Metadata Import";
            this.Load += new System.EventHandler(this.frmMetadataImport_Load);
            this.panelLabelFirstTreeView.ResumeLayout(false);
            this.panelLabelFirstTreeView.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TreeView treeView1;
        private System.Windows.Forms.Panel panelLabelFirstTreeView;
        private System.Windows.Forms.Label lblFirstTreeView;
        private gui.Switch switchTreeView;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label infoLabel;
        private System.ComponentModel.BackgroundWorker backgroundWorker1;
        private System.Windows.Forms.ImageList imageList1;

    }
}