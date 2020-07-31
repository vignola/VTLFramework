namespace VTL_Editor_PL.classes.gui
{
    partial class frmErrorDetails
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
            this.ErrorMessageTextBox = new System.Windows.Forms.TextBox();
            this.DetailsButton = new System.Windows.Forms.Button();
            this.wsLabel = new System.Windows.Forms.Label();
            this.SDescriptionLabel = new System.Windows.Forms.Label();
            this.TitleLabel = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.CloseLabel = new System.Windows.Forms.Label();
            this.CloseButton = new System.Windows.Forms.Button();
            this.BorderPanel = new System.Windows.Forms.Panel();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // ErrorMessageTextBox
            // 
            this.ErrorMessageTextBox.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ErrorMessageTextBox.Location = new System.Drawing.Point(6, 140);
            this.ErrorMessageTextBox.Multiline = true;
            this.ErrorMessageTextBox.Name = "ErrorMessageTextBox";
            this.ErrorMessageTextBox.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.ErrorMessageTextBox.Size = new System.Drawing.Size(570, 299);
            this.ErrorMessageTextBox.TabIndex = 11;
            // 
            // DetailsButton
            // 
            this.DetailsButton.BackColor = System.Drawing.Color.Gainsboro;
            this.DetailsButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.DetailsButton.Location = new System.Drawing.Point(6, 114);
            this.DetailsButton.Name = "DetailsButton";
            this.DetailsButton.Size = new System.Drawing.Size(75, 23);
            this.DetailsButton.TabIndex = 9;
            this.DetailsButton.Text = "Details...";
            this.DetailsButton.UseVisualStyleBackColor = false;
            this.DetailsButton.Click += new System.EventHandler(this.DetailsButton_Click);
            // 
            // wsLabel
            // 
            this.wsLabel.AutoSize = true;
            this.wsLabel.ForeColor = System.Drawing.Color.Gray;
            this.wsLabel.Location = new System.Drawing.Point(85, 74);
            this.wsLabel.Name = "wsLabel";
            this.wsLabel.Size = new System.Drawing.Size(67, 13);
            this.wsLabel.TabIndex = 6;
            this.wsLabel.Text = "Web service";
            // 
            // SDescriptionLabel
            // 
            this.SDescriptionLabel.AutoSize = true;
            this.SDescriptionLabel.Location = new System.Drawing.Point(84, 53);
            this.SDescriptionLabel.Name = "SDescriptionLabel";
            this.SDescriptionLabel.Size = new System.Drawing.Size(109, 13);
            this.SDescriptionLabel.TabIndex = 4;
            this.SDescriptionLabel.Text = "Error short description";
            // 
            // TitleLabel
            // 
            this.TitleLabel.AutoSize = true;
            this.TitleLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.TitleLabel.ForeColor = System.Drawing.Color.SteelBlue;
            this.TitleLabel.Location = new System.Drawing.Point(82, 18);
            this.TitleLabel.Name = "TitleLabel";
            this.TitleLabel.Size = new System.Drawing.Size(96, 25);
            this.TitleLabel.TabIndex = 3;
            this.TitleLabel.Text = "Error type";
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.Color.White;
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel1.Controls.Add(this.pictureBox1);
            this.panel1.Controls.Add(this.wsLabel);
            this.panel1.Controls.Add(this.CloseLabel);
            this.panel1.Controls.Add(this.SDescriptionLabel);
            this.panel1.Controls.Add(this.TitleLabel);
            this.panel1.Location = new System.Drawing.Point(5, 5);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(570, 104);
            this.panel1.TabIndex = 12;
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.pictureBox1.ErrorImage = null;
            this.pictureBox1.Image = global::VTL_Editor_PL.Properties.Resources.ErrorIcon;
            this.pictureBox1.InitialImage = null;
            this.pictureBox1.Location = new System.Drawing.Point(40, 13);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(36, 36);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 2;
            this.pictureBox1.TabStop = false;
            // 
            // CloseLabel
            // 
            this.CloseLabel.AutoSize = true;
            this.CloseLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.CloseLabel.ForeColor = System.Drawing.Color.Gray;
            this.CloseLabel.Location = new System.Drawing.Point(550, 4);
            this.CloseLabel.Name = "CloseLabel";
            this.CloseLabel.Size = new System.Drawing.Size(17, 16);
            this.CloseLabel.TabIndex = 5;
            this.CloseLabel.Text = "X";
            this.CloseLabel.Click += new System.EventHandler(this.CloseLabel_Click);
            this.CloseLabel.MouseLeave += new System.EventHandler(this.CloseLabel_MouseLeave);
            this.CloseLabel.MouseHover += new System.EventHandler(this.CloseLabel_MouseHover);
            // 
            // CloseButton
            // 
            this.CloseButton.BackColor = System.Drawing.Color.Gainsboro;
            this.CloseButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.CloseButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.CloseButton.Location = new System.Drawing.Point(501, 114);
            this.CloseButton.Name = "CloseButton";
            this.CloseButton.Size = new System.Drawing.Size(75, 23);
            this.CloseButton.TabIndex = 10;
            this.CloseButton.Text = "Close";
            this.CloseButton.UseVisualStyleBackColor = false;
            this.CloseButton.Click += new System.EventHandler(this.CloseButton_Click_1);
            // 
            // BorderPanel
            // 
            this.BorderPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.BorderPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.BorderPanel.Location = new System.Drawing.Point(0, 0);
            this.BorderPanel.Name = "BorderPanel";
            this.BorderPanel.Size = new System.Drawing.Size(582, 444);
            this.BorderPanel.TabIndex = 13;
            // 
            // frmErrorDetails
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightSteelBlue;
            this.CancelButton = this.CloseButton;
            this.ClientSize = new System.Drawing.Size(582, 444);
            this.ControlBox = false;
            this.Controls.Add(this.ErrorMessageTextBox);
            this.Controls.Add(this.DetailsButton);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.CloseButton);
            this.Controls.Add(this.BorderPanel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "frmErrorDetails";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "frmErrorDetails";
            this.Load += new System.EventHandler(this.frmErrorDetails_Load);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox ErrorMessageTextBox;
        private System.Windows.Forms.Button DetailsButton;
        private System.Windows.Forms.Label wsLabel;
        private System.Windows.Forms.Label SDescriptionLabel;
        private System.Windows.Forms.Label TitleLabel;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Label CloseLabel;
        private System.Windows.Forms.Button CloseButton;
        private System.Windows.Forms.Panel BorderPanel;
    }
}