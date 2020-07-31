namespace VTL_Editor_PL.gui
{
    partial class frmAddWebService
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
            this.panel1 = new System.Windows.Forms.Panel();
            this.DetailImplementationPanel = new System.Windows.Forms.Panel();
            this.FullRadioButton = new System.Windows.Forms.RadioButton();
            this.CompleteStubRadioButton = new System.Windows.Forms.RadioButton();
            this.label5 = new System.Windows.Forms.Label();
            this.CancelButton = new System.Windows.Forms.Button();
            this.label4 = new System.Windows.Forms.Label();
            this.OkButton = new System.Windows.Forms.Button();
            this.TestConnectionButton = new System.Windows.Forms.Button();
            this.WebServiceNameTextBox = new System.Windows.Forms.TextBox();
            this.label13 = new System.Windows.Forms.Label();
            this.WebServiceDescriptionTextBox = new System.Windows.Forms.TextBox();
            this.label10 = new System.Windows.Forms.Label();
            this.WebServicePasswordTextBox = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.WebServiceUserNameTextBox = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.WebServiceURLtextBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.panel1.SuspendLayout();
            this.DetailImplementationPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.DetailImplementationPanel);
            this.panel1.Location = new System.Drawing.Point(3, 4);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(576, 188);
            this.panel1.TabIndex = 0;
            // 
            // DetailImplementationPanel
            // 
            this.DetailImplementationPanel.BackColor = System.Drawing.Color.SteelBlue;
            this.DetailImplementationPanel.Controls.Add(this.FullRadioButton);
            this.DetailImplementationPanel.Controls.Add(this.CompleteStubRadioButton);
            this.DetailImplementationPanel.Controls.Add(this.label5);
            this.DetailImplementationPanel.Location = new System.Drawing.Point(2, 149);
            this.DetailImplementationPanel.Name = "DetailImplementationPanel";
            this.DetailImplementationPanel.Size = new System.Drawing.Size(571, 36);
            this.DetailImplementationPanel.TabIndex = 0;
            // 
            // FullRadioButton
            // 
            this.FullRadioButton.AutoSize = true;
            this.FullRadioButton.ForeColor = System.Drawing.Color.White;
            this.FullRadioButton.Location = new System.Drawing.Point(366, 10);
            this.FullRadioButton.Name = "FullRadioButton";
            this.FullRadioButton.Size = new System.Drawing.Size(163, 17);
            this.FullRadioButton.TabIndex = 2;
            this.FullRadioButton.TabStop = true;
            this.FullRadioButton.Text = "Full (SDMX-RI reccomended)";
            this.FullRadioButton.UseVisualStyleBackColor = true;
            // 
            // CompleteStubRadioButton
            // 
            this.CompleteStubRadioButton.AutoSize = true;
            this.CompleteStubRadioButton.ForeColor = System.Drawing.Color.White;
            this.CompleteStubRadioButton.Location = new System.Drawing.Point(209, 8);
            this.CompleteStubRadioButton.Name = "CompleteStubRadioButton";
            this.CompleteStubRadioButton.Size = new System.Drawing.Size(92, 17);
            this.CompleteStubRadioButton.TabIndex = 1;
            this.CompleteStubRadioButton.TabStop = true;
            this.CompleteStubRadioButton.Text = "Complete stub";
            this.CompleteStubRadioButton.UseVisualStyleBackColor = true;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.ForeColor = System.Drawing.Color.White;
            this.label5.Location = new System.Drawing.Point(3, 10);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(158, 13);
            this.label5.TabIndex = 0;
            this.label5.Text = "Return details implementation* : ";
            // 
            // CancelButton
            // 
            this.CancelButton.BackColor = System.Drawing.Color.Gainsboro;
            this.CancelButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.CancelButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.CancelButton.Location = new System.Drawing.Point(297, 198);
            this.CancelButton.Name = "CancelButton";
            this.CancelButton.Size = new System.Drawing.Size(75, 23);
            this.CancelButton.TabIndex = 65;
            this.CancelButton.Text = "Cancel";
            this.CancelButton.UseVisualStyleBackColor = false;
            this.CancelButton.Click += new System.EventHandler(this.CancelButton_Click);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 8F, System.Drawing.FontStyle.Italic, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.ForeColor = System.Drawing.Color.RoyalBlue;
            this.label4.Location = new System.Drawing.Point(503, 195);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(76, 13);
            this.label4.TabIndex = 66;
            this.label4.Text = "*required fields";
            // 
            // OkButton
            // 
            this.OkButton.BackColor = System.Drawing.Color.Gainsboro;
            this.OkButton.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.OkButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.OkButton.Location = new System.Drawing.Point(216, 198);
            this.OkButton.Name = "OkButton";
            this.OkButton.Size = new System.Drawing.Size(75, 23);
            this.OkButton.TabIndex = 64;
            this.OkButton.Text = "Ok";
            this.OkButton.UseVisualStyleBackColor = false;
            this.OkButton.Click += new System.EventHandler(this.OkButton_Click);
            // 
            // TestConnectionButton
            // 
            this.TestConnectionButton.Location = new System.Drawing.Point(466, 74);
            this.TestConnectionButton.Name = "TestConnectionButton";
            this.TestConnectionButton.Size = new System.Drawing.Size(106, 23);
            this.TestConnectionButton.TabIndex = 77;
            this.TestConnectionButton.Text = "Test connection...";
            this.TestConnectionButton.UseVisualStyleBackColor = true;
            this.TestConnectionButton.Click += new System.EventHandler(this.TestConnectionButton_Click);
            // 
            // WebServiceNameTextBox
            // 
            this.WebServiceNameTextBox.Location = new System.Drawing.Point(112, 12);
            this.WebServiceNameTextBox.Name = "WebServiceNameTextBox";
            this.WebServiceNameTextBox.Size = new System.Drawing.Size(460, 20);
            this.WebServiceNameTextBox.TabIndex = 67;
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(11, 54);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(60, 13);
            this.label13.TabIndex = 76;
            this.label13.Text = "Description";
            // 
            // WebServiceDescriptionTextBox
            // 
            this.WebServiceDescriptionTextBox.Location = new System.Drawing.Point(112, 48);
            this.WebServiceDescriptionTextBox.Name = "WebServiceDescriptionTextBox";
            this.WebServiceDescriptionTextBox.Size = new System.Drawing.Size(460, 20);
            this.WebServiceDescriptionTextBox.TabIndex = 68;
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(11, 15);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(39, 13);
            this.label10.TabIndex = 75;
            this.label10.Text = "Name*";
            // 
            // WebServicePasswordTextBox
            // 
            this.WebServicePasswordTextBox.Location = new System.Drawing.Point(112, 127);
            this.WebServicePasswordTextBox.Name = "WebServicePasswordTextBox";
            this.WebServicePasswordTextBox.Size = new System.Drawing.Size(216, 20);
            this.WebServicePasswordTextBox.TabIndex = 71;
            this.WebServicePasswordTextBox.UseSystemPasswordChar = true;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(11, 104);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(58, 13);
            this.label2.TabIndex = 73;
            this.label2.Text = "User name";
            // 
            // WebServiceUserNameTextBox
            // 
            this.WebServiceUserNameTextBox.Location = new System.Drawing.Point(112, 101);
            this.WebServiceUserNameTextBox.Name = "WebServiceUserNameTextBox";
            this.WebServiceUserNameTextBox.Size = new System.Drawing.Size(216, 20);
            this.WebServiceUserNameTextBox.TabIndex = 70;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(11, 130);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(53, 13);
            this.label3.TabIndex = 74;
            this.label3.Text = "Password";
            // 
            // WebServiceURLtextBox
            // 
            this.WebServiceURLtextBox.Location = new System.Drawing.Point(112, 74);
            this.WebServiceURLtextBox.Name = "WebServiceURLtextBox";
            this.WebServiceURLtextBox.Size = new System.Drawing.Size(348, 20);
            this.WebServiceURLtextBox.TabIndex = 69;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(11, 81);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(96, 13);
            this.label1.TabIndex = 72;
            this.label1.Text = "Web service URL*";
            // 
            // frmAddWebService
            // 
            this.AcceptButton = this.OkButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightSteelBlue;
            this.ClientSize = new System.Drawing.Size(582, 226);
            this.ControlBox = false;
            this.Controls.Add(this.TestConnectionButton);
            this.Controls.Add(this.WebServiceNameTextBox);
            this.Controls.Add(this.label13);
            this.Controls.Add(this.WebServiceDescriptionTextBox);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.WebServicePasswordTextBox);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.WebServiceUserNameTextBox);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.WebServiceURLtextBox);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.CancelButton);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.OkButton);
            this.Controls.Add(this.panel1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "frmAddWebService";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Add/Update webservice";
            this.Load += new System.EventHandler(this.frmAddWebService_Load);
            this.panel1.ResumeLayout(false);
            this.DetailImplementationPanel.ResumeLayout(false);
            this.DetailImplementationPanel.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Button CancelButton;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button OkButton;
        private System.Windows.Forms.Button TestConnectionButton;
        private System.Windows.Forms.TextBox WebServiceNameTextBox;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.TextBox WebServiceDescriptionTextBox;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.TextBox WebServicePasswordTextBox;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox WebServiceUserNameTextBox;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox WebServiceURLtextBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Panel DetailImplementationPanel;
        private System.Windows.Forms.RadioButton FullRadioButton;
        private System.Windows.Forms.RadioButton CompleteStubRadioButton;
        private System.Windows.Forms.Label label5;
    }
}