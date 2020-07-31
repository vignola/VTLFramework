namespace VTL_Editor_PL.gui
{
    partial class frmSQLViewer
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
            this.SaveButton = new System.Windows.Forms.Button();
            this.CloseButton = new System.Windows.Forms.Button();
            this.CopyToButton = new System.Windows.Forms.Button();
            this.saveFileDialog1 = new System.Windows.Forms.SaveFileDialog();
            this.SuspendLayout();
            // 
            // SaveButton
            // 
            this.SaveButton.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.SaveButton.BackColor = System.Drawing.Color.Gainsboro;
            this.SaveButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.SaveButton.Location = new System.Drawing.Point(367, 481);
            this.SaveButton.Name = "SaveButton";
            this.SaveButton.Size = new System.Drawing.Size(75, 23);
            this.SaveButton.TabIndex = 1;
            this.SaveButton.Text = "Save";
            this.SaveButton.UseVisualStyleBackColor = false;
            this.SaveButton.Click += new System.EventHandler(this.SaveButton_Click);
            // 
            // CloseButton
            // 
            this.CloseButton.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.CloseButton.BackColor = System.Drawing.Color.Gainsboro;
            this.CloseButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.CloseButton.Location = new System.Drawing.Point(560, 481);
            this.CloseButton.Name = "CloseButton";
            this.CloseButton.Size = new System.Drawing.Size(75, 23);
            this.CloseButton.TabIndex = 3;
            this.CloseButton.Text = "Close";
            this.CloseButton.UseVisualStyleBackColor = false;
            this.CloseButton.Click += new System.EventHandler(this.CloseButton_Click);
            // 
            // CopyToButton
            // 
            this.CopyToButton.Anchor = System.Windows.Forms.AnchorStyles.Bottom;
            this.CopyToButton.BackColor = System.Drawing.Color.Gainsboro;
            this.CopyToButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.CopyToButton.Location = new System.Drawing.Point(448, 481);
            this.CopyToButton.Name = "CopyToButton";
            this.CopyToButton.Size = new System.Drawing.Size(107, 23);
            this.CopyToButton.TabIndex = 2;
            this.CopyToButton.Text = "Copy to clipboard";
            this.CopyToButton.UseVisualStyleBackColor = false;
            this.CopyToButton.Click += new System.EventHandler(this.CopyToButton_Click);
            // 
            // frmSQLViewer
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightSteelBlue;
            this.ClientSize = new System.Drawing.Size(994, 505);
            this.Controls.Add(this.CloseButton);
            this.Controls.Add(this.CopyToButton);
            this.Controls.Add(this.SaveButton);
            this.Name = "frmSQLViewer";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "SQL Viewer";
            this.Load += new System.EventHandler(this.frmSQLViewer_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button SaveButton;
        private System.Windows.Forms.Button CloseButton;
        private System.Windows.Forms.Button CopyToButton;
        private System.Windows.Forms.SaveFileDialog saveFileDialog1;        
    }
}