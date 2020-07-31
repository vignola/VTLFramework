namespace VTL_Editor_PL.gui
{
    partial class frmSaveOperator
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
            this.label1 = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.UserDefOpRadioButton = new System.Windows.Forms.RadioButton();
            this.DatRulesetRadioButton = new System.Windows.Forms.RadioButton();
            this.HierarchicalRadioButton = new System.Windows.Forms.RadioButton();
            this.SaveButton = new System.Windows.Forms.Button();
            this.CancelButton = new System.Windows.Forms.Button();
            this.OperaratorNameLabel = new System.Windows.Forms.Label();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(54, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Operator: ";
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.Color.SteelBlue;
            this.panel1.Controls.Add(this.HierarchicalRadioButton);
            this.panel1.Controls.Add(this.DatRulesetRadioButton);
            this.panel1.Controls.Add(this.UserDefOpRadioButton);
            this.panel1.Location = new System.Drawing.Point(9, 42);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(423, 89);
            this.panel1.TabIndex = 1;
            // 
            // UserDefOpRadioButton
            // 
            this.UserDefOpRadioButton.AutoSize = true;
            this.UserDefOpRadioButton.ForeColor = System.Drawing.Color.White;
            this.UserDefOpRadioButton.Location = new System.Drawing.Point(42, 7);
            this.UserDefOpRadioButton.Name = "UserDefOpRadioButton";
            this.UserDefOpRadioButton.Size = new System.Drawing.Size(127, 17);
            this.UserDefOpRadioButton.TabIndex = 0;
            this.UserDefOpRadioButton.TabStop = true;
            this.UserDefOpRadioButton.Text = "User defined operator";
            this.UserDefOpRadioButton.UseVisualStyleBackColor = true;
            // 
            // DatRulesetRadioButton
            // 
            this.DatRulesetRadioButton.AutoSize = true;
            this.DatRulesetRadioButton.ForeColor = System.Drawing.Color.White;
            this.DatRulesetRadioButton.Location = new System.Drawing.Point(42, 34);
            this.DatRulesetRadioButton.Name = "DatRulesetRadioButton";
            this.DatRulesetRadioButton.Size = new System.Drawing.Size(105, 17);
            this.DatRulesetRadioButton.TabIndex = 1;
            this.DatRulesetRadioButton.TabStop = true;
            this.DatRulesetRadioButton.Text = "Datapoint ruleset";
            this.DatRulesetRadioButton.UseVisualStyleBackColor = true;
            // 
            // HierarchicalRadioButton
            // 
            this.HierarchicalRadioButton.AutoSize = true;
            this.HierarchicalRadioButton.ForeColor = System.Drawing.Color.White;
            this.HierarchicalRadioButton.Location = new System.Drawing.Point(42, 63);
            this.HierarchicalRadioButton.Name = "HierarchicalRadioButton";
            this.HierarchicalRadioButton.Size = new System.Drawing.Size(115, 17);
            this.HierarchicalRadioButton.TabIndex = 2;
            this.HierarchicalRadioButton.TabStop = true;
            this.HierarchicalRadioButton.Text = "Hierarchical ruleset";
            this.HierarchicalRadioButton.UseVisualStyleBackColor = true;
            // 
            // SaveButton
            // 
            this.SaveButton.BackColor = System.Drawing.Color.Gainsboro;
            this.SaveButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.SaveButton.Location = new System.Drawing.Point(142, 137);
            this.SaveButton.Name = "SaveButton";
            this.SaveButton.Size = new System.Drawing.Size(75, 23);
            this.SaveButton.TabIndex = 2;
            this.SaveButton.Text = "Save";
            this.SaveButton.UseVisualStyleBackColor = false;
            this.SaveButton.Click += new System.EventHandler(this.SaveButton_Click);
            // 
            // CancelButton
            // 
            this.CancelButton.BackColor = System.Drawing.Color.Gainsboro;
            this.CancelButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.CancelButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.CancelButton.Location = new System.Drawing.Point(223, 137);
            this.CancelButton.Name = "CancelButton";
            this.CancelButton.Size = new System.Drawing.Size(75, 23);
            this.CancelButton.TabIndex = 3;
            this.CancelButton.Text = "Cancel";
            this.CancelButton.UseVisualStyleBackColor = false;
            this.CancelButton.Click += new System.EventHandler(this.CancelButton_Click);
            // 
            // OperaratorNameLabel
            // 
            this.OperaratorNameLabel.AutoSize = true;
            this.OperaratorNameLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.OperaratorNameLabel.Location = new System.Drawing.Point(62, 13);
            this.OperaratorNameLabel.Name = "OperaratorNameLabel";
            this.OperaratorNameLabel.Size = new System.Drawing.Size(11, 13);
            this.OperaratorNameLabel.TabIndex = 4;
            this.OperaratorNameLabel.Text = "-";
            // 
            // frmSaveOperator
            // 
            this.AcceptButton = this.SaveButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightSteelBlue;
            this.CancelButton = this.CancelButton;
            this.ClientSize = new System.Drawing.Size(441, 165);
            this.Controls.Add(this.OperaratorNameLabel);
            this.Controls.Add(this.CancelButton);
            this.Controls.Add(this.SaveButton);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.label1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "frmSaveOperator";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Save user defined operator";
            this.Load += new System.EventHandler(this.frmSaveOperator_Load);
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.RadioButton HierarchicalRadioButton;
        private System.Windows.Forms.RadioButton DatRulesetRadioButton;
        private System.Windows.Forms.RadioButton UserDefOpRadioButton;
        private System.Windows.Forms.Button SaveButton;
        private System.Windows.Forms.Button CancelButton;
        private System.Windows.Forms.Label OperaratorNameLabel;
    }
}