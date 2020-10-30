namespace VTL_Editor_PL.gui
{
    partial class frmSettings
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
            this.tabSettings = new System.Windows.Forms.TabControl();
            this.tabPgInteractionWebService = new System.Windows.Forms.TabPage();
            this.IntWSlabelName = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.label14 = new System.Windows.Forms.Label();
            this.IntWSlabelPwd = new System.Windows.Forms.Label();
            this.IntWSlabelUrl = new System.Windows.Forms.Label();
            this.IntWSlabelDescr = new System.Windows.Forms.Label();
            this.label24 = new System.Windows.Forms.Label();
            this.label25 = new System.Windows.Forms.Label();
            this.label26 = new System.Windows.Forms.Label();
            this.IntWSlabelUser = new System.Windows.Forms.Label();
            this.IntWSbuttonAdd = new System.Windows.Forms.Button();
            this.IntWSbuttonModify = new System.Windows.Forms.Button();
            this.tabPgValWebService = new System.Windows.Forms.TabPage();
            this.ValWSlabelName = new System.Windows.Forms.Label();
            this.label22 = new System.Windows.Forms.Label();
            this.label23 = new System.Windows.Forms.Label();
            this.ValWSlabelPwd = new System.Windows.Forms.Label();
            this.ValWSlabelWSUrl = new System.Windows.Forms.Label();
            this.ValWSlabelDescription = new System.Windows.Forms.Label();
            this.label30 = new System.Windows.Forms.Label();
            this.label31 = new System.Windows.Forms.Label();
            this.label32 = new System.Windows.Forms.Label();
            this.ValWSlabelUser = new System.Windows.Forms.Label();
            this.ValWSbuttonAdd = new System.Windows.Forms.Button();
            this.ValWSbuttonModify = new System.Windows.Forms.Button();
            this.tabPgWebServices = new System.Windows.Forms.TabPage();
            this.WebServiceReturnDetailImplementationLabel = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.label12 = new System.Windows.Forms.Label();
            this.WebServicePasswordLabel = new System.Windows.Forms.Label();
            this.WebServiceURLLabel = new System.Windows.Forms.Label();
            this.WebServiceDescriptionLabel = new System.Windows.Forms.Label();
            this.label13 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.WebServiceUserNameLabel = new System.Windows.Forms.Label();
            this.WebServiceAddButton = new System.Windows.Forms.Button();
            this.WebServiceRemoveButton = new System.Windows.Forms.Button();
            this.WebServiceModifyButton = new System.Windows.Forms.Button();
            this.WebServiceNameComboBox = new System.Windows.Forms.ComboBox();
            this.tabPgDBConnection = new System.Windows.Forms.TabPage();
            this.ConnectionNameLabel = new System.Windows.Forms.Label();
            this.DBConnectionNameTextBox = new System.Windows.Forms.TextBox();
            this.AddDBConnectionButton = new System.Windows.Forms.Button();
            this.RemoveDBConnButton = new System.Windows.Forms.Button();
            this.SaveDBConnButton = new System.Windows.Forms.Button();
            this.OracleDBPanel = new System.Windows.Forms.Panel();
            this.TestConnectionButton = new System.Windows.Forms.Button();
            this.OracleTNSTextBox = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.label16 = new System.Windows.Forms.Label();
            this.DbTypeComboBox = new System.Windows.Forms.ComboBox();
            this.label15 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.DBPasswordTextBox = new System.Windows.Forms.TextBox();
            this.DBUserNameTextBox = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.DBConnectionsComboBox = new System.Windows.Forms.ComboBox();
            this.tabPgFolders = new System.Windows.Forms.TabPage();
            this.label20 = new System.Windows.Forms.Label();
            this.LogPathTextBox = new System.Windows.Forms.TextBox();
            this.LogFileCheckBox = new System.Windows.Forms.CheckBox();
            this.BrowseLogPathButton = new System.Windows.Forms.Button();
            this.tabPgDefaultValue = new System.Windows.Forms.TabPage();
            this.OBS_default_panel = new System.Windows.Forms.Panel();
            this.select_TIME_vdbutton = new System.Windows.Forms.Button();
            this.selectOBS_VDbutton = new System.Windows.Forms.Button();
            this.TIME_PERIODTextBox = new System.Windows.Forms.TextBox();
            this.OBS_VALUEtextBox = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label18 = new System.Windows.Forms.Label();
            this.label17 = new System.Windows.Forms.Label();
            this.btnSave = new System.Windows.Forms.Button();
            this.btnCancel = new System.Windows.Forms.Button();
            this.tabSettings.SuspendLayout();
            this.tabPgInteractionWebService.SuspendLayout();
            this.tabPgValWebService.SuspendLayout();
            this.tabPgWebServices.SuspendLayout();
            this.tabPgDBConnection.SuspendLayout();
            this.OracleDBPanel.SuspendLayout();
            this.tabPgFolders.SuspendLayout();
            this.tabPgDefaultValue.SuspendLayout();
            this.OBS_default_panel.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabSettings
            // 
            this.tabSettings.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.tabSettings.Controls.Add(this.tabPgInteractionWebService);
            this.tabSettings.Controls.Add(this.tabPgValWebService);
            this.tabSettings.Controls.Add(this.tabPgWebServices);
            this.tabSettings.Controls.Add(this.tabPgDBConnection);
            this.tabSettings.Controls.Add(this.tabPgFolders);
            this.tabSettings.Controls.Add(this.tabPgDefaultValue);
            this.tabSettings.Location = new System.Drawing.Point(2, 2);
            this.tabSettings.Name = "tabSettings";
            this.tabSettings.SelectedIndex = 0;
            this.tabSettings.Size = new System.Drawing.Size(561, 358);
            this.tabSettings.TabIndex = 0;
            // 
            // tabPgInteractionWebService
            // 
            this.tabPgInteractionWebService.Controls.Add(this.IntWSlabelName);
            this.tabPgInteractionWebService.Controls.Add(this.label1);
            this.tabPgInteractionWebService.Controls.Add(this.label14);
            this.tabPgInteractionWebService.Controls.Add(this.IntWSlabelPwd);
            this.tabPgInteractionWebService.Controls.Add(this.IntWSlabelUrl);
            this.tabPgInteractionWebService.Controls.Add(this.IntWSlabelDescr);
            this.tabPgInteractionWebService.Controls.Add(this.label24);
            this.tabPgInteractionWebService.Controls.Add(this.label25);
            this.tabPgInteractionWebService.Controls.Add(this.label26);
            this.tabPgInteractionWebService.Controls.Add(this.IntWSlabelUser);
            this.tabPgInteractionWebService.Controls.Add(this.IntWSbuttonAdd);
            this.tabPgInteractionWebService.Controls.Add(this.IntWSbuttonModify);
            this.tabPgInteractionWebService.Location = new System.Drawing.Point(4, 22);
            this.tabPgInteractionWebService.Name = "tabPgInteractionWebService";
            this.tabPgInteractionWebService.Padding = new System.Windows.Forms.Padding(3);
            this.tabPgInteractionWebService.Size = new System.Drawing.Size(553, 332);
            this.tabPgInteractionWebService.TabIndex = 4;
            this.tabPgInteractionWebService.Text = "Interaction web service";
            this.tabPgInteractionWebService.UseVisualStyleBackColor = true;
            // 
            // IntWSlabelName
            // 
            this.IntWSlabelName.AutoSize = true;
            this.IntWSlabelName.Location = new System.Drawing.Point(136, 41);
            this.IntWSlabelName.Name = "IntWSlabelName";
            this.IntWSlabelName.Size = new System.Drawing.Size(10, 13);
            this.IntWSlabelName.TabIndex = 81;
            this.IntWSlabelName.Text = "-";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(22, 143);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(58, 13);
            this.label1.TabIndex = 79;
            this.label1.Text = "User name";
            this.label1.Visible = false;
            // 
            // label14
            // 
            this.label14.AutoSize = true;
            this.label14.Location = new System.Drawing.Point(22, 167);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(53, 13);
            this.label14.TabIndex = 80;
            this.label14.Text = "Password";
            this.label14.Visible = false;
            // 
            // IntWSlabelPwd
            // 
            this.IntWSlabelPwd.AutoSize = true;
            this.IntWSlabelPwd.Location = new System.Drawing.Point(135, 167);
            this.IntWSlabelPwd.Name = "IntWSlabelPwd";
            this.IntWSlabelPwd.Size = new System.Drawing.Size(10, 13);
            this.IntWSlabelPwd.TabIndex = 78;
            this.IntWSlabelPwd.Text = "-";
            this.IntWSlabelPwd.Visible = false;
            // 
            // IntWSlabelUrl
            // 
            this.IntWSlabelUrl.AutoSize = true;
            this.IntWSlabelUrl.Location = new System.Drawing.Point(135, 105);
            this.IntWSlabelUrl.Name = "IntWSlabelUrl";
            this.IntWSlabelUrl.Size = new System.Drawing.Size(10, 13);
            this.IntWSlabelUrl.TabIndex = 77;
            this.IntWSlabelUrl.Text = "-";
            // 
            // IntWSlabelDescr
            // 
            this.IntWSlabelDescr.AutoSize = true;
            this.IntWSlabelDescr.Location = new System.Drawing.Point(135, 81);
            this.IntWSlabelDescr.Name = "IntWSlabelDescr";
            this.IntWSlabelDescr.Size = new System.Drawing.Size(10, 13);
            this.IntWSlabelDescr.TabIndex = 76;
            this.IntWSlabelDescr.Text = "-";
            // 
            // label24
            // 
            this.label24.AutoSize = true;
            this.label24.Location = new System.Drawing.Point(20, 84);
            this.label24.Name = "label24";
            this.label24.Size = new System.Drawing.Size(60, 13);
            this.label24.TabIndex = 75;
            this.label24.Text = "Description";
            // 
            // label25
            // 
            this.label25.AutoSize = true;
            this.label25.Location = new System.Drawing.Point(20, 41);
            this.label25.Name = "label25";
            this.label25.Size = new System.Drawing.Size(35, 13);
            this.label25.TabIndex = 74;
            this.label25.Text = "Name";
            // 
            // label26
            // 
            this.label26.AutoSize = true;
            this.label26.Location = new System.Drawing.Point(20, 107);
            this.label26.Name = "label26";
            this.label26.Size = new System.Drawing.Size(92, 13);
            this.label26.TabIndex = 73;
            this.label26.Text = "Web service URL";
            // 
            // IntWSlabelUser
            // 
            this.IntWSlabelUser.AutoSize = true;
            this.IntWSlabelUser.Location = new System.Drawing.Point(136, 143);
            this.IntWSlabelUser.Name = "IntWSlabelUser";
            this.IntWSlabelUser.Size = new System.Drawing.Size(10, 13);
            this.IntWSlabelUser.TabIndex = 71;
            this.IntWSlabelUser.Text = "-";
            this.IntWSlabelUser.Visible = false;
            // 
            // IntWSbuttonAdd
            // 
            this.IntWSbuttonAdd.Location = new System.Drawing.Point(210, 259);
            this.IntWSbuttonAdd.Name = "IntWSbuttonAdd";
            this.IntWSbuttonAdd.Size = new System.Drawing.Size(68, 23);
            this.IntWSbuttonAdd.TabIndex = 69;
            this.IntWSbuttonAdd.Text = "New";
            this.IntWSbuttonAdd.UseVisualStyleBackColor = true;
            this.IntWSbuttonAdd.Click += new System.EventHandler(this.button2_Click);
            // 
            // IntWSbuttonModify
            // 
            this.IntWSbuttonModify.Location = new System.Drawing.Point(284, 259);
            this.IntWSbuttonModify.Name = "IntWSbuttonModify";
            this.IntWSbuttonModify.Size = new System.Drawing.Size(68, 23);
            this.IntWSbuttonModify.TabIndex = 67;
            this.IntWSbuttonModify.Text = "Modify";
            this.IntWSbuttonModify.UseVisualStyleBackColor = true;
            this.IntWSbuttonModify.Click += new System.EventHandler(this.button4_Click);
            // 
            // tabPgValWebService
            // 
            this.tabPgValWebService.Controls.Add(this.ValWSlabelName);
            this.tabPgValWebService.Controls.Add(this.label22);
            this.tabPgValWebService.Controls.Add(this.label23);
            this.tabPgValWebService.Controls.Add(this.ValWSlabelPwd);
            this.tabPgValWebService.Controls.Add(this.ValWSlabelWSUrl);
            this.tabPgValWebService.Controls.Add(this.ValWSlabelDescription);
            this.tabPgValWebService.Controls.Add(this.label30);
            this.tabPgValWebService.Controls.Add(this.label31);
            this.tabPgValWebService.Controls.Add(this.label32);
            this.tabPgValWebService.Controls.Add(this.ValWSlabelUser);
            this.tabPgValWebService.Controls.Add(this.ValWSbuttonAdd);
            this.tabPgValWebService.Controls.Add(this.ValWSbuttonModify);
            this.tabPgValWebService.Location = new System.Drawing.Point(4, 22);
            this.tabPgValWebService.Name = "tabPgValWebService";
            this.tabPgValWebService.Size = new System.Drawing.Size(553, 332);
            this.tabPgValWebService.TabIndex = 5;
            this.tabPgValWebService.Text = "Validation web service";
            this.tabPgValWebService.UseVisualStyleBackColor = true;
            // 
            // ValWSlabelName
            // 
            this.ValWSlabelName.AutoSize = true;
            this.ValWSlabelName.Location = new System.Drawing.Point(136, 41);
            this.ValWSlabelName.Name = "ValWSlabelName";
            this.ValWSlabelName.Size = new System.Drawing.Size(10, 13);
            this.ValWSlabelName.TabIndex = 94;
            this.ValWSlabelName.Text = "-";
            // 
            // label22
            // 
            this.label22.AutoSize = true;
            this.label22.Location = new System.Drawing.Point(22, 143);
            this.label22.Name = "label22";
            this.label22.Size = new System.Drawing.Size(58, 13);
            this.label22.TabIndex = 92;
            this.label22.Text = "User name";
            this.label22.Visible = false;
            // 
            // label23
            // 
            this.label23.AutoSize = true;
            this.label23.Location = new System.Drawing.Point(22, 167);
            this.label23.Name = "label23";
            this.label23.Size = new System.Drawing.Size(53, 13);
            this.label23.TabIndex = 93;
            this.label23.Text = "Password";
            this.label23.Visible = false;
            // 
            // ValWSlabelPwd
            // 
            this.ValWSlabelPwd.AutoSize = true;
            this.ValWSlabelPwd.Location = new System.Drawing.Point(135, 167);
            this.ValWSlabelPwd.Name = "ValWSlabelPwd";
            this.ValWSlabelPwd.Size = new System.Drawing.Size(10, 13);
            this.ValWSlabelPwd.TabIndex = 91;
            this.ValWSlabelPwd.Text = "-";
            this.ValWSlabelPwd.Visible = false;
            // 
            // ValWSlabelWSUrl
            // 
            this.ValWSlabelWSUrl.AutoSize = true;
            this.ValWSlabelWSUrl.Location = new System.Drawing.Point(135, 105);
            this.ValWSlabelWSUrl.Name = "ValWSlabelWSUrl";
            this.ValWSlabelWSUrl.Size = new System.Drawing.Size(10, 13);
            this.ValWSlabelWSUrl.TabIndex = 90;
            this.ValWSlabelWSUrl.Text = "-";
            // 
            // ValWSlabelDescription
            // 
            this.ValWSlabelDescription.AutoSize = true;
            this.ValWSlabelDescription.Location = new System.Drawing.Point(135, 81);
            this.ValWSlabelDescription.Name = "ValWSlabelDescription";
            this.ValWSlabelDescription.Size = new System.Drawing.Size(10, 13);
            this.ValWSlabelDescription.TabIndex = 89;
            this.ValWSlabelDescription.Text = "-";
            // 
            // label30
            // 
            this.label30.AutoSize = true;
            this.label30.Location = new System.Drawing.Point(20, 84);
            this.label30.Name = "label30";
            this.label30.Size = new System.Drawing.Size(60, 13);
            this.label30.TabIndex = 88;
            this.label30.Text = "Description";
            // 
            // label31
            // 
            this.label31.AutoSize = true;
            this.label31.Location = new System.Drawing.Point(20, 41);
            this.label31.Name = "label31";
            this.label31.Size = new System.Drawing.Size(35, 13);
            this.label31.TabIndex = 87;
            this.label31.Text = "Name";
            // 
            // label32
            // 
            this.label32.AutoSize = true;
            this.label32.Location = new System.Drawing.Point(20, 107);
            this.label32.Name = "label32";
            this.label32.Size = new System.Drawing.Size(92, 13);
            this.label32.TabIndex = 86;
            this.label32.Text = "Web service URL";
            // 
            // ValWSlabelUser
            // 
            this.ValWSlabelUser.AutoSize = true;
            this.ValWSlabelUser.Location = new System.Drawing.Point(136, 143);
            this.ValWSlabelUser.Name = "ValWSlabelUser";
            this.ValWSlabelUser.Size = new System.Drawing.Size(10, 13);
            this.ValWSlabelUser.TabIndex = 85;
            this.ValWSlabelUser.Text = "-";
            this.ValWSlabelUser.Visible = false;
            // 
            // ValWSbuttonAdd
            // 
            this.ValWSbuttonAdd.Location = new System.Drawing.Point(210, 259);
            this.ValWSbuttonAdd.Name = "ValWSbuttonAdd";
            this.ValWSbuttonAdd.Size = new System.Drawing.Size(68, 23);
            this.ValWSbuttonAdd.TabIndex = 84;
            this.ValWSbuttonAdd.Text = "New";
            this.ValWSbuttonAdd.UseVisualStyleBackColor = true;
            this.ValWSbuttonAdd.Click += new System.EventHandler(this.ValWSbuttonAdd_Click);
            // 
            // ValWSbuttonModify
            // 
            this.ValWSbuttonModify.Location = new System.Drawing.Point(284, 259);
            this.ValWSbuttonModify.Name = "ValWSbuttonModify";
            this.ValWSbuttonModify.Size = new System.Drawing.Size(68, 23);
            this.ValWSbuttonModify.TabIndex = 82;
            this.ValWSbuttonModify.Text = "Modify";
            this.ValWSbuttonModify.UseVisualStyleBackColor = true;
            this.ValWSbuttonModify.Click += new System.EventHandler(this.ValWSbuttonModify_Click);
            // 
            // tabPgWebServices
            // 
            this.tabPgWebServices.Controls.Add(this.WebServiceReturnDetailImplementationLabel);
            this.tabPgWebServices.Controls.Add(this.label2);
            this.tabPgWebServices.Controls.Add(this.label9);
            this.tabPgWebServices.Controls.Add(this.label12);
            this.tabPgWebServices.Controls.Add(this.WebServicePasswordLabel);
            this.tabPgWebServices.Controls.Add(this.WebServiceURLLabel);
            this.tabPgWebServices.Controls.Add(this.WebServiceDescriptionLabel);
            this.tabPgWebServices.Controls.Add(this.label13);
            this.tabPgWebServices.Controls.Add(this.label10);
            this.tabPgWebServices.Controls.Add(this.label11);
            this.tabPgWebServices.Controls.Add(this.WebServiceUserNameLabel);
            this.tabPgWebServices.Controls.Add(this.WebServiceAddButton);
            this.tabPgWebServices.Controls.Add(this.WebServiceRemoveButton);
            this.tabPgWebServices.Controls.Add(this.WebServiceModifyButton);
            this.tabPgWebServices.Controls.Add(this.WebServiceNameComboBox);
            this.tabPgWebServices.Location = new System.Drawing.Point(4, 22);
            this.tabPgWebServices.Name = "tabPgWebServices";
            this.tabPgWebServices.Size = new System.Drawing.Size(553, 332);
            this.tabPgWebServices.TabIndex = 2;
            this.tabPgWebServices.Text = "SDMX Web services";
            this.tabPgWebServices.UseVisualStyleBackColor = true;
            // 
            // WebServiceReturnDetailImplementationLabel
            // 
            this.WebServiceReturnDetailImplementationLabel.AutoSize = true;
            this.WebServiceReturnDetailImplementationLabel.Location = new System.Drawing.Point(169, 133);
            this.WebServiceReturnDetailImplementationLabel.Name = "WebServiceReturnDetailImplementationLabel";
            this.WebServiceReturnDetailImplementationLabel.Size = new System.Drawing.Size(10, 13);
            this.WebServiceReturnDetailImplementationLabel.TabIndex = 67;
            this.WebServiceReturnDetailImplementationLabel.Text = "-";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(20, 133);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(143, 13);
            this.label2.TabIndex = 66;
            this.label2.Text = "Return detail implementation:";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(22, 162);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(58, 13);
            this.label9.TabIndex = 64;
            this.label9.Text = "User name";
            this.label9.Visible = false;
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(22, 186);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(53, 13);
            this.label12.TabIndex = 65;
            this.label12.Text = "Password";
            this.label12.Visible = false;
            // 
            // WebServicePasswordLabel
            // 
            this.WebServicePasswordLabel.AutoSize = true;
            this.WebServicePasswordLabel.Location = new System.Drawing.Point(135, 186);
            this.WebServicePasswordLabel.Name = "WebServicePasswordLabel";
            this.WebServicePasswordLabel.Size = new System.Drawing.Size(10, 13);
            this.WebServicePasswordLabel.TabIndex = 63;
            this.WebServicePasswordLabel.Text = "-";
            this.WebServicePasswordLabel.Visible = false;
            // 
            // WebServiceURLLabel
            // 
            this.WebServiceURLLabel.AutoSize = true;
            this.WebServiceURLLabel.Location = new System.Drawing.Point(135, 105);
            this.WebServiceURLLabel.Name = "WebServiceURLLabel";
            this.WebServiceURLLabel.Size = new System.Drawing.Size(10, 13);
            this.WebServiceURLLabel.TabIndex = 61;
            this.WebServiceURLLabel.Text = "-";
            // 
            // WebServiceDescriptionLabel
            // 
            this.WebServiceDescriptionLabel.AutoSize = true;
            this.WebServiceDescriptionLabel.Location = new System.Drawing.Point(135, 81);
            this.WebServiceDescriptionLabel.Name = "WebServiceDescriptionLabel";
            this.WebServiceDescriptionLabel.Size = new System.Drawing.Size(10, 13);
            this.WebServiceDescriptionLabel.TabIndex = 60;
            this.WebServiceDescriptionLabel.Text = "-";
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(20, 84);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(60, 13);
            this.label13.TabIndex = 59;
            this.label13.Text = "Description";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(20, 41);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(35, 13);
            this.label10.TabIndex = 58;
            this.label10.Text = "Name";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(20, 107);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(92, 13);
            this.label11.TabIndex = 57;
            this.label11.Text = "Web service URL";
            // 
            // WebServiceUserNameLabel
            // 
            this.WebServiceUserNameLabel.AutoSize = true;
            this.WebServiceUserNameLabel.Location = new System.Drawing.Point(136, 162);
            this.WebServiceUserNameLabel.Name = "WebServiceUserNameLabel";
            this.WebServiceUserNameLabel.Size = new System.Drawing.Size(10, 13);
            this.WebServiceUserNameLabel.TabIndex = 55;
            this.WebServiceUserNameLabel.Text = "-";
            this.WebServiceUserNameLabel.Visible = false;
            // 
            // WebServiceAddButton
            // 
            this.WebServiceAddButton.Location = new System.Drawing.Point(171, 252);
            this.WebServiceAddButton.Name = "WebServiceAddButton";
            this.WebServiceAddButton.Size = new System.Drawing.Size(68, 23);
            this.WebServiceAddButton.TabIndex = 53;
            this.WebServiceAddButton.Text = "Add";
            this.WebServiceAddButton.UseVisualStyleBackColor = true;
            this.WebServiceAddButton.Click += new System.EventHandler(this.WebServiceAddButton_Click);
            // 
            // WebServiceRemoveButton
            // 
            this.WebServiceRemoveButton.Location = new System.Drawing.Point(318, 252);
            this.WebServiceRemoveButton.Name = "WebServiceRemoveButton";
            this.WebServiceRemoveButton.Size = new System.Drawing.Size(68, 23);
            this.WebServiceRemoveButton.TabIndex = 52;
            this.WebServiceRemoveButton.Text = "Remove";
            this.WebServiceRemoveButton.UseVisualStyleBackColor = true;
            this.WebServiceRemoveButton.Click += new System.EventHandler(this.WebServiceRemoveButton_Click_1);
            // 
            // WebServiceModifyButton
            // 
            this.WebServiceModifyButton.Location = new System.Drawing.Point(244, 252);
            this.WebServiceModifyButton.Name = "WebServiceModifyButton";
            this.WebServiceModifyButton.Size = new System.Drawing.Size(68, 23);
            this.WebServiceModifyButton.TabIndex = 51;
            this.WebServiceModifyButton.Text = "Modify";
            this.WebServiceModifyButton.UseVisualStyleBackColor = true;
            this.WebServiceModifyButton.Click += new System.EventHandler(this.WebServiceModifyButton_Click_1);
            // 
            // WebServiceNameComboBox
            // 
            this.WebServiceNameComboBox.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.WebServiceNameComboBox.FormattingEnabled = true;
            this.WebServiceNameComboBox.Location = new System.Drawing.Point(139, 33);
            this.WebServiceNameComboBox.Name = "WebServiceNameComboBox";
            this.WebServiceNameComboBox.Size = new System.Drawing.Size(325, 21);
            this.WebServiceNameComboBox.TabIndex = 50;
            this.WebServiceNameComboBox.SelectedIndexChanged += new System.EventHandler(this.WebServiceNameComboBox_SelectedIndexChanged);
            // 
            // tabPgDBConnection
            // 
            this.tabPgDBConnection.Controls.Add(this.ConnectionNameLabel);
            this.tabPgDBConnection.Controls.Add(this.DBConnectionNameTextBox);
            this.tabPgDBConnection.Controls.Add(this.AddDBConnectionButton);
            this.tabPgDBConnection.Controls.Add(this.RemoveDBConnButton);
            this.tabPgDBConnection.Controls.Add(this.SaveDBConnButton);
            this.tabPgDBConnection.Controls.Add(this.OracleDBPanel);
            this.tabPgDBConnection.Controls.Add(this.label16);
            this.tabPgDBConnection.Controls.Add(this.DbTypeComboBox);
            this.tabPgDBConnection.Controls.Add(this.label15);
            this.tabPgDBConnection.Controls.Add(this.label8);
            this.tabPgDBConnection.Controls.Add(this.DBPasswordTextBox);
            this.tabPgDBConnection.Controls.Add(this.DBUserNameTextBox);
            this.tabPgDBConnection.Controls.Add(this.label6);
            this.tabPgDBConnection.Controls.Add(this.DBConnectionsComboBox);
            this.tabPgDBConnection.Location = new System.Drawing.Point(4, 22);
            this.tabPgDBConnection.Name = "tabPgDBConnection";
            this.tabPgDBConnection.Padding = new System.Windows.Forms.Padding(3);
            this.tabPgDBConnection.Size = new System.Drawing.Size(553, 332);
            this.tabPgDBConnection.TabIndex = 7;
            this.tabPgDBConnection.Text = "DB Connection";
            this.tabPgDBConnection.UseVisualStyleBackColor = true;
            // 
            // ConnectionNameLabel
            // 
            this.ConnectionNameLabel.AutoSize = true;
            this.ConnectionNameLabel.Location = new System.Drawing.Point(48, 56);
            this.ConnectionNameLabel.Name = "ConnectionNameLabel";
            this.ConnectionNameLabel.Size = new System.Drawing.Size(35, 13);
            this.ConnectionNameLabel.TabIndex = 89;
            this.ConnectionNameLabel.Text = "Name";
            // 
            // DBConnectionNameTextBox
            // 
            this.DBConnectionNameTextBox.Location = new System.Drawing.Point(90, 49);
            this.DBConnectionNameTextBox.Name = "DBConnectionNameTextBox";
            this.DBConnectionNameTextBox.Size = new System.Drawing.Size(194, 20);
            this.DBConnectionNameTextBox.TabIndex = 88;
            this.DBConnectionNameTextBox.TextChanged += new System.EventHandler(this.DBConnectionNameTextBox_TextChanged);
            // 
            // AddDBConnectionButton
            // 
            this.AddDBConnectionButton.Location = new System.Drawing.Point(110, 287);
            this.AddDBConnectionButton.Name = "AddDBConnectionButton";
            this.AddDBConnectionButton.Size = new System.Drawing.Size(68, 23);
            this.AddDBConnectionButton.TabIndex = 87;
            this.AddDBConnectionButton.Text = "Add";
            this.AddDBConnectionButton.UseVisualStyleBackColor = true;
            this.AddDBConnectionButton.Click += new System.EventHandler(this.NewButton_Click);
            // 
            // RemoveDBConnButton
            // 
            this.RemoveDBConnButton.Location = new System.Drawing.Point(336, 287);
            this.RemoveDBConnButton.Name = "RemoveDBConnButton";
            this.RemoveDBConnButton.Size = new System.Drawing.Size(149, 23);
            this.RemoveDBConnButton.TabIndex = 86;
            this.RemoveDBConnButton.Text = "Remove DB Connection";
            this.RemoveDBConnButton.UseVisualStyleBackColor = true;
            this.RemoveDBConnButton.Click += new System.EventHandler(this.button2_Click_1);
            // 
            // SaveDBConnButton
            // 
            this.SaveDBConnButton.Location = new System.Drawing.Point(184, 287);
            this.SaveDBConnButton.Name = "SaveDBConnButton";
            this.SaveDBConnButton.Size = new System.Drawing.Size(146, 23);
            this.SaveDBConnButton.TabIndex = 85;
            this.SaveDBConnButton.Text = "Save DB Connection";
            this.SaveDBConnButton.UseVisualStyleBackColor = true;
            this.SaveDBConnButton.Click += new System.EventHandler(this.button3_Click);
            // 
            // OracleDBPanel
            // 
            this.OracleDBPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.OracleDBPanel.Controls.Add(this.TestConnectionButton);
            this.OracleDBPanel.Controls.Add(this.OracleTNSTextBox);
            this.OracleDBPanel.Controls.Add(this.label7);
            this.OracleDBPanel.Location = new System.Drawing.Point(10, 190);
            this.OracleDBPanel.Name = "OracleDBPanel";
            this.OracleDBPanel.Size = new System.Drawing.Size(536, 74);
            this.OracleDBPanel.TabIndex = 11;
            // 
            // TestConnectionButton
            // 
            this.TestConnectionButton.Location = new System.Drawing.Point(422, 10);
            this.TestConnectionButton.Name = "TestConnectionButton";
            this.TestConnectionButton.Size = new System.Drawing.Size(99, 23);
            this.TestConnectionButton.TabIndex = 90;
            this.TestConnectionButton.Text = "Test connection";
            this.TestConnectionButton.UseVisualStyleBackColor = true;
            this.TestConnectionButton.Click += new System.EventHandler(this.TestConnectionButton_Click_1);
            // 
            // OracleTNSTextBox
            // 
            this.OracleTNSTextBox.Location = new System.Drawing.Point(76, 13);
            this.OracleTNSTextBox.Name = "OracleTNSTextBox";
            this.OracleTNSTextBox.Size = new System.Drawing.Size(194, 20);
            this.OracleTNSTextBox.TabIndex = 4;
            this.OracleTNSTextBox.TextChanged += new System.EventHandler(this.OracleTNSTextBox_TextChanged);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(15, 16);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(57, 13);
            this.label7.TabIndex = 6;
            this.label7.Text = "TNS Alias ";
            // 
            // label16
            // 
            this.label16.AutoSize = true;
            this.label16.Location = new System.Drawing.Point(36, 82);
            this.label16.Name = "label16";
            this.label16.Size = new System.Drawing.Size(49, 13);
            this.label16.TabIndex = 10;
            this.label16.Text = "DB Type";
            // 
            // DbTypeComboBox
            // 
            this.DbTypeComboBox.FormattingEnabled = true;
            this.DbTypeComboBox.Items.AddRange(new object[] {
            "Oracle",
            "SQL Server",
            "MySql",
            "Postgresql"});
            this.DbTypeComboBox.Location = new System.Drawing.Point(89, 75);
            this.DbTypeComboBox.Name = "DbTypeComboBox";
            this.DbTypeComboBox.Size = new System.Drawing.Size(194, 21);
            this.DbTypeComboBox.TabIndex = 9;
            this.DbTypeComboBox.SelectedIndexChanged += new System.EventHandler(this.DbTypeComboBox_SelectedIndexChanged);
            // 
            // label15
            // 
            this.label15.AutoSize = true;
            this.label15.Location = new System.Drawing.Point(32, 147);
            this.label15.Name = "label15";
            this.label15.Size = new System.Drawing.Size(53, 13);
            this.label15.TabIndex = 8;
            this.label15.Text = "Password";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(27, 121);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(58, 13);
            this.label8.TabIndex = 7;
            this.label8.Text = "User name";
            // 
            // DBPasswordTextBox
            // 
            this.DBPasswordTextBox.Location = new System.Drawing.Point(89, 140);
            this.DBPasswordTextBox.Name = "DBPasswordTextBox";
            this.DBPasswordTextBox.PasswordChar = '*';
            this.DBPasswordTextBox.Size = new System.Drawing.Size(194, 20);
            this.DBPasswordTextBox.TabIndex = 3;
            this.DBPasswordTextBox.TextChanged += new System.EventHandler(this.DBPasswordTextBox_TextChanged);
            // 
            // DBUserNameTextBox
            // 
            this.DBUserNameTextBox.Location = new System.Drawing.Point(89, 114);
            this.DBUserNameTextBox.Name = "DBUserNameTextBox";
            this.DBUserNameTextBox.Size = new System.Drawing.Size(194, 20);
            this.DBUserNameTextBox.TabIndex = 2;
            this.DBUserNameTextBox.TextChanged += new System.EventHandler(this.DBUserNameTextBox_TextChanged);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(25, 14);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(61, 13);
            this.label6.TabIndex = 1;
            this.label6.Text = "Connection";
            // 
            // DBConnectionsComboBox
            // 
            this.DBConnectionsComboBox.FormattingEnabled = true;
            this.DBConnectionsComboBox.Location = new System.Drawing.Point(90, 6);
            this.DBConnectionsComboBox.Name = "DBConnectionsComboBox";
            this.DBConnectionsComboBox.Size = new System.Drawing.Size(457, 21);
            this.DBConnectionsComboBox.TabIndex = 0;
            this.DBConnectionsComboBox.SelectedIndexChanged += new System.EventHandler(this.DBConnectionsComboBox_SelectedIndexChanged);
            // 
            // tabPgFolders
            // 
            this.tabPgFolders.Controls.Add(this.label20);
            this.tabPgFolders.Controls.Add(this.LogPathTextBox);
            this.tabPgFolders.Controls.Add(this.LogFileCheckBox);
            this.tabPgFolders.Controls.Add(this.BrowseLogPathButton);
            this.tabPgFolders.Location = new System.Drawing.Point(4, 22);
            this.tabPgFolders.Name = "tabPgFolders";
            this.tabPgFolders.Size = new System.Drawing.Size(553, 332);
            this.tabPgFolders.TabIndex = 3;
            this.tabPgFolders.Text = "Folders";
            this.tabPgFolders.UseVisualStyleBackColor = true;
            // 
            // label20
            // 
            this.label20.AutoSize = true;
            this.label20.Cursor = System.Windows.Forms.Cursors.Hand;
            this.label20.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Underline, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label20.ForeColor = System.Drawing.Color.Blue;
            this.label20.Location = new System.Drawing.Point(477, 312);
            this.label20.Name = "label20";
            this.label20.Size = new System.Drawing.Size(69, 13);
            this.label20.TabIndex = 48;
            this.label20.Text = "Settings path";
            this.label20.Click += new System.EventHandler(this.label20_Click);
            // 
            // LogPathTextBox
            // 
            this.LogPathTextBox.Location = new System.Drawing.Point(82, 17);
            this.LogPathTextBox.Name = "LogPathTextBox";
            this.LogPathTextBox.Size = new System.Drawing.Size(383, 20);
            this.LogPathTextBox.TabIndex = 46;
            // 
            // LogFileCheckBox
            // 
            this.LogFileCheckBox.AutoSize = true;
            this.LogFileCheckBox.Location = new System.Drawing.Point(16, 21);
            this.LogFileCheckBox.Name = "LogFileCheckBox";
            this.LogFileCheckBox.Size = new System.Drawing.Size(60, 17);
            this.LogFileCheckBox.TabIndex = 45;
            this.LogFileCheckBox.Text = "Log file";
            this.LogFileCheckBox.UseVisualStyleBackColor = true;
            // 
            // BrowseLogPathButton
            // 
            this.BrowseLogPathButton.Location = new System.Drawing.Point(471, 14);
            this.BrowseLogPathButton.Name = "BrowseLogPathButton";
            this.BrowseLogPathButton.Size = new System.Drawing.Size(75, 23);
            this.BrowseLogPathButton.TabIndex = 47;
            this.BrowseLogPathButton.Text = "Browse...";
            this.BrowseLogPathButton.UseVisualStyleBackColor = true;
            // 
            // tabPgDefaultValue
            // 
            this.tabPgDefaultValue.Controls.Add(this.OBS_default_panel);
            this.tabPgDefaultValue.Location = new System.Drawing.Point(4, 22);
            this.tabPgDefaultValue.Name = "tabPgDefaultValue";
            this.tabPgDefaultValue.Padding = new System.Windows.Forms.Padding(3);
            this.tabPgDefaultValue.Size = new System.Drawing.Size(553, 332);
            this.tabPgDefaultValue.TabIndex = 6;
            this.tabPgDefaultValue.Text = "SDMX import";
            this.tabPgDefaultValue.UseVisualStyleBackColor = true;
            // 
            // OBS_default_panel
            // 
            this.OBS_default_panel.BackColor = System.Drawing.Color.LightSteelBlue;
            this.OBS_default_panel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.OBS_default_panel.Controls.Add(this.select_TIME_vdbutton);
            this.OBS_default_panel.Controls.Add(this.selectOBS_VDbutton);
            this.OBS_default_panel.Controls.Add(this.TIME_PERIODTextBox);
            this.OBS_default_panel.Controls.Add(this.OBS_VALUEtextBox);
            this.OBS_default_panel.Controls.Add(this.label5);
            this.OBS_default_panel.Controls.Add(this.label4);
            this.OBS_default_panel.Controls.Add(this.label3);
            this.OBS_default_panel.Controls.Add(this.label18);
            this.OBS_default_panel.Controls.Add(this.label17);
            this.OBS_default_panel.Location = new System.Drawing.Point(7, 3);
            this.OBS_default_panel.Name = "OBS_default_panel";
            this.OBS_default_panel.Size = new System.Drawing.Size(539, 155);
            this.OBS_default_panel.TabIndex = 0;
            // 
            // select_TIME_vdbutton
            // 
            this.select_TIME_vdbutton.Location = new System.Drawing.Point(466, 118);
            this.select_TIME_vdbutton.Name = "select_TIME_vdbutton";
            this.select_TIME_vdbutton.Size = new System.Drawing.Size(68, 23);
            this.select_TIME_vdbutton.TabIndex = 19;
            this.select_TIME_vdbutton.Text = "Select";
            this.select_TIME_vdbutton.UseVisualStyleBackColor = true;
            this.select_TIME_vdbutton.Click += new System.EventHandler(this.select_TIME_vdbutton_Click);
            // 
            // selectOBS_VDbutton
            // 
            this.selectOBS_VDbutton.Location = new System.Drawing.Point(466, 56);
            this.selectOBS_VDbutton.Name = "selectOBS_VDbutton";
            this.selectOBS_VDbutton.Size = new System.Drawing.Size(68, 23);
            this.selectOBS_VDbutton.TabIndex = 18;
            this.selectOBS_VDbutton.Text = "Select";
            this.selectOBS_VDbutton.UseVisualStyleBackColor = true;
            this.selectOBS_VDbutton.Click += new System.EventHandler(this.selectOBS_VDbutton_Click);
            // 
            // TIME_PERIODTextBox
            // 
            this.TIME_PERIODTextBox.Enabled = false;
            this.TIME_PERIODTextBox.Location = new System.Drawing.Point(93, 120);
            this.TIME_PERIODTextBox.Name = "TIME_PERIODTextBox";
            this.TIME_PERIODTextBox.Size = new System.Drawing.Size(367, 20);
            this.TIME_PERIODTextBox.TabIndex = 17;
            // 
            // OBS_VALUEtextBox
            // 
            this.OBS_VALUEtextBox.Enabled = false;
            this.OBS_VALUEtextBox.Location = new System.Drawing.Point(93, 56);
            this.OBS_VALUEtextBox.Name = "OBS_VALUEtextBox";
            this.OBS_VALUEtextBox.Size = new System.Drawing.Size(367, 20);
            this.OBS_VALUEtextBox.TabIndex = 16;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(16, 10);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(451, 13);
            this.label5.TabIndex = 15;
            this.label5.Text = "Select the default value domain for the TIME_PERIOD and the Primary Measure OBS_V" +
    "ALUE";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(16, 127);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(71, 13);
            this.label4.TabIndex = 14;
            this.label4.Text = "Value domain";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(16, 63);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(71, 13);
            this.label3.TabIndex = 13;
            this.label3.Text = "Value domain";
            // 
            // label18
            // 
            this.label18.AutoSize = true;
            this.label18.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label18.Location = new System.Drawing.Point(13, 103);
            this.label18.Name = "label18";
            this.label18.Size = new System.Drawing.Size(91, 13);
            this.label18.TabIndex = 12;
            this.label18.Text = "TIME_PERIOD";
            // 
            // label17
            // 
            this.label17.AutoSize = true;
            this.label17.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label17.Location = new System.Drawing.Point(13, 40);
            this.label17.Name = "label17";
            this.label17.Size = new System.Drawing.Size(79, 13);
            this.label17.TabIndex = 11;
            this.label17.Text = "OBS_VALUE";
            // 
            // btnSave
            // 
            this.btnSave.Location = new System.Drawing.Point(399, 364);
            this.btnSave.Name = "btnSave";
            this.btnSave.Size = new System.Drawing.Size(75, 23);
            this.btnSave.TabIndex = 1;
            this.btnSave.Text = "Save";
            this.btnSave.UseVisualStyleBackColor = true;
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
            // 
            // btnCancel
            // 
            this.btnCancel.Location = new System.Drawing.Point(480, 364);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(75, 23);
            this.btnCancel.TabIndex = 2;
            this.btnCancel.Text = "Cancel";
            this.btnCancel.UseVisualStyleBackColor = true;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // frmSettings
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(564, 392);
            this.Controls.Add(this.tabSettings);
            this.Controls.Add(this.btnCancel);
            this.Controls.Add(this.btnSave);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "frmSettings";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "frmSettings";
            this.Load += new System.EventHandler(this.frmSettings_Load);
            this.tabSettings.ResumeLayout(false);
            this.tabPgInteractionWebService.ResumeLayout(false);
            this.tabPgInteractionWebService.PerformLayout();
            this.tabPgValWebService.ResumeLayout(false);
            this.tabPgValWebService.PerformLayout();
            this.tabPgWebServices.ResumeLayout(false);
            this.tabPgWebServices.PerformLayout();
            this.tabPgDBConnection.ResumeLayout(false);
            this.tabPgDBConnection.PerformLayout();
            this.OracleDBPanel.ResumeLayout(false);
            this.OracleDBPanel.PerformLayout();
            this.tabPgFolders.ResumeLayout(false);
            this.tabPgFolders.PerformLayout();
            this.tabPgDefaultValue.ResumeLayout(false);
            this.OBS_default_panel.ResumeLayout(false);
            this.OBS_default_panel.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TabControl tabSettings;
        private System.Windows.Forms.TabPage tabPgWebServices;
        private System.Windows.Forms.TabPage tabPgFolders;
        private System.Windows.Forms.Button btnSave;
        private System.Windows.Forms.Button btnCancel;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Label WebServicePasswordLabel;
        private System.Windows.Forms.Label WebServiceURLLabel;
        private System.Windows.Forms.Label WebServiceDescriptionLabel;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.Label WebServiceUserNameLabel;
        private System.Windows.Forms.Button WebServiceAddButton;
        private System.Windows.Forms.Button WebServiceRemoveButton;
        private System.Windows.Forms.Button WebServiceModifyButton;
        private System.Windows.Forms.ComboBox WebServiceNameComboBox;
        private System.Windows.Forms.TextBox LogPathTextBox;
        private System.Windows.Forms.CheckBox LogFileCheckBox;
        private System.Windows.Forms.Button BrowseLogPathButton;
        private System.Windows.Forms.Label label20;
        private System.Windows.Forms.TabPage tabPgInteractionWebService;
        private System.Windows.Forms.Label IntWSlabelName;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.Label IntWSlabelPwd;
        private System.Windows.Forms.Label IntWSlabelUrl;
        private System.Windows.Forms.Label IntWSlabelDescr;
        private System.Windows.Forms.Label label24;
        private System.Windows.Forms.Label label25;
        private System.Windows.Forms.Label label26;
        private System.Windows.Forms.Label IntWSlabelUser;
        private System.Windows.Forms.Button IntWSbuttonAdd;
        private System.Windows.Forms.Button IntWSbuttonModify;
        private System.Windows.Forms.TabPage tabPgValWebService;
        private System.Windows.Forms.Label ValWSlabelName;
        private System.Windows.Forms.Label label22;
        private System.Windows.Forms.Label label23;
        private System.Windows.Forms.Label ValWSlabelPwd;
        private System.Windows.Forms.Label ValWSlabelWSUrl;
        private System.Windows.Forms.Label ValWSlabelDescription;
        private System.Windows.Forms.Label label30;
        private System.Windows.Forms.Label label31;
        private System.Windows.Forms.Label label32;
        private System.Windows.Forms.Label ValWSlabelUser;
        private System.Windows.Forms.Button ValWSbuttonAdd;
        private System.Windows.Forms.Button ValWSbuttonModify;
        private System.Windows.Forms.Label WebServiceReturnDetailImplementationLabel;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TabPage tabPgDefaultValue;
        private System.Windows.Forms.Label label18;
        private System.Windows.Forms.Panel OBS_default_panel;
        private System.Windows.Forms.Label label17;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Button select_TIME_vdbutton;
        private System.Windows.Forms.Button selectOBS_VDbutton;
        private System.Windows.Forms.TextBox TIME_PERIODTextBox;
        private System.Windows.Forms.TextBox OBS_VALUEtextBox;
        private System.Windows.Forms.TabPage tabPgDBConnection;
        private System.Windows.Forms.ComboBox DbTypeComboBox;
        private System.Windows.Forms.Label label15;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox OracleTNSTextBox;
        private System.Windows.Forms.TextBox DBPasswordTextBox;
        private System.Windows.Forms.TextBox DBUserNameTextBox;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.ComboBox DBConnectionsComboBox;
        private System.Windows.Forms.Button AddDBConnectionButton;
        private System.Windows.Forms.Button RemoveDBConnButton;
        private System.Windows.Forms.Button SaveDBConnButton;
        private System.Windows.Forms.Panel OracleDBPanel;
        private System.Windows.Forms.Label label16;
        private System.Windows.Forms.Label ConnectionNameLabel;
        private System.Windows.Forms.TextBox DBConnectionNameTextBox;
        private System.Windows.Forms.Button TestConnectionButton;
    }
}