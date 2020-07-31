using ISTAT_DB_DAL;
using Oracle.ManagedDataAccess.Client;
using ScintillaNET;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.config;
using VTL_Editor_PL.classes.SQLTextUtils;

namespace VTL_Editor_PL.gui
{
    public partial class frmDataExecution : Form
    {
        private BindingSource _bindingSource = new BindingSource();
        private DataProvider _provider;
        private ScintillaNET.Scintilla _textArea;
        private string _currentConnectionString;
        private string _sqlCode = "";
        private List<string> _previousLoadedTable;

        private struct ItemValue 
        {
            public object key;
            public string value;
            public ItemValue(object keyParameter, string valueParameter) 
            {
                key = keyParameter;
                value = valueParameter;
            }
            public override string ToString() { return value; }
        }
        
        public string SQLCode
        {
            get
            {
                if (_textArea != null)
                    return _textArea.Text;
                else
                    return "";
            }

            set
            {
                if (_textArea != null) _textArea.Text = value;
                _sqlCode = value;
            }
        }
        public frmDataExecution()
        {
            InitializeComponent();
        }

        private void GridPanel_Paint(object sender, PaintEventArgs e)
        {

        }

        private void AlreadyExistingComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            try 
            { 
                if (AlreadyExistingComboBox.SelectedItem.ToString()!="")
                    InitializeDataGridView(AlreadyExistingComboBox.SelectedItem.ToString());
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
}

        private void NewTableComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                if (NewTableComboBox.SelectedItem.ToString() != "")
                InitializeDataGridView(NewTableComboBox.SelectedItem.ToString());
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
}

        private void CloseButton_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void DBConnectionComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            try
            {
                if (DBConnectionComboBox.SelectedItem != null)
                {
                    FillTableListComboBox(ref AlreadyExistingComboBox, false);
                    AlreadyExistingComboBox.Enabled = true;
                    AlreadyExistingLabel.Enabled = true;
                    ExecuteButton.Enabled = true;
                   
                    pictureBox1.Image = imageList2.Images[1];
                }
                else
                {
                    SelectTablePanel.Enabled = false;
                    ExecuteButton.Enabled = false;
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void frmDataExecution_Load(object sender, EventArgs e)
        {
            try 
            { 
                splitContainer1.Panel2Collapsed = true;
                pictureBox1.Image = imageList2.Images[0];
                ToolTip tp = new ToolTip();
                _previousLoadedTable = new List<string>();
                tp.SetToolTip(pictureBox1, "DB connection status");

                foreach (MainApplicationSettings.DBInfo dbInfo in CommonItem.CurrentSettings.DBConnections)
                {
                    DBConnectionComboBox.Items.Add(new ItemValue(dbInfo, dbInfo.DBConnectionName));
                }

                //Init text area
                //---------------------------------------------------------------------------
                // CREATE CONTROL
                _textArea = new ScintillaNET.Scintilla();
                TextPanel.Controls.Add(_textArea);
                _textArea.BorderStyle = BorderStyle.FixedSingle;

                // BASIC CONFIG
                _textArea.Dock = System.Windows.Forms.DockStyle.Fill;
                _textArea.TextChanged += (this.OnTextChanged);

                // INITIAL VIEW CONFIG
                _textArea.WrapMode = WrapMode.None;
                _textArea.IndentationGuides = IndentView.LookBoth;

                // STYLING
                InitColors();
                InitSyntaxColoring();

                // NUMBER MARGIN
                InitNumberMargin();

                // BOOKMARK MARGIN
                InitBookmarkMargin();

                // CODE FOLDING MARGIN
                InitCodeFolding();

                // DRAG DROP
                InitDragDropFile();            

                // INIT HOTKEYS
                InitHotkeys();
                //---------------------------------------------------------------------------

            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private void InitializeDataGridView(string SelectedTable)
        {
            try
            {                
                //dataGridView1.Dock = DockStyle.Fill;                
                dataGridView1.AutoGenerateColumns = true;

                // Set up the data source.
                _bindingSource.DataSource = GetTableData(SelectedTable);
                dataGridView1.DataSource = _bindingSource;
                
                // Automatically resize the visible rows.
                dataGridView1.AutoSizeRowsMode =
                    DataGridViewAutoSizeRowsMode.DisplayedCellsExceptHeaders;
                

                // Set the DataGridView control's border.
                dataGridView1.BorderStyle = BorderStyle.FixedSingle;

                // Put the cells in edit mode when user enters them.
                dataGridView1.EditMode = DataGridViewEditMode.EditProgrammatically;
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
        }

        private DataTable GetTableData(string tableName)
        {
            IDbConnection conn = null;            
            IDbCommand comm = null;
            IDataAdapter adapter = null;      

            try 
            {
                MainApplicationSettings.DBInfo dbInfo = (MainApplicationSettings.DBInfo)((ItemValue)DBConnectionComboBox.SelectedItem).key;

                _currentConnectionString = dbInfo.GetOracleConnectionString();

                this._provider = new DataProvider(_currentConnectionString, ProviderType.Oracle);
                                    

                conn = _provider.OpenConnection();

            
                string SQLStatement = null;
                SQLStatement = "select * from " + tableName;

                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);                        

                adapter = new OracleDataAdapter();
                ((OracleDataAdapter)adapter).SelectCommand = (OracleCommand)comm;

                DataSet dataset = new DataSet();
                dataset.Locale = System.Globalization.CultureInfo.InvariantCulture;
                adapter.Fill(dataset);
                
                return dataset.Tables[0];

             }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
                return null;
            }
            finally 
            {
                _provider.CloseConnection(conn);
            }
        }      

        private void ExecuteButton_Click(object sender, EventArgs e)
        {
            IDbConnection conn=null;
            try 
            { 
                MainApplicationSettings.DBInfo dbInfo = (MainApplicationSettings.DBInfo)((ItemValue)DBConnectionComboBox.SelectedItem).key;

                _currentConnectionString = dbInfo.GetOracleConnectionString();

                this._provider = new DataProvider(_currentConnectionString, ProviderType.Oracle);

                conn = _provider.OpenConnection();                   

                string[] SQLStatements =_textArea.Text.Split(';');

                _provider.ExecuteNonQueryMultiple(SQLStatements);                   

                FillTableListComboBox(ref NewTableComboBox,true);

                NewTableComboBox.Enabled = true;
                NewTableLabel.Enabled = true;
                MessageBox.Show("Execution completed succesfully", "Executed", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
            finally 
            {
                _provider.CloseConnection(conn);
            }
        }


        private void FillTableListComboBox(ref ComboBox listToBeFilled, bool alreadyExisting) 
        {
            IDbConnection conn = null;
            IDbCommand comm = null;
            IDataReader rdr = null;
            try 
            { 
                List<string> actualList = new List<string>();

                MainApplicationSettings.DBInfo dbInfo = (MainApplicationSettings.DBInfo)((ItemValue)DBConnectionComboBox.SelectedItem).key;
                _currentConnectionString = dbInfo.GetOracleConnectionString();

                this._provider = new DataProvider(_currentConnectionString, ProviderType.Oracle);

            
                conn = _provider.OpenConnection();
                

                string SQLStatement = null;
                SQLStatement = "SELECT table_name FROM all_tables where owner =\'" + dbInfo.UserName.ToUpper() + "\' order by table_name";

                comm = new OracleCommand(SQLStatement, (OracleConnection)conn);
                rdr = comm.ExecuteReader(System.Data.CommandBehavior.Default);
                if (rdr == null) return;

                while (rdr.Read())
                {
                    string tmpString = rdr[0].ToString();
                    actualList.Add(tmpString);

                    if (!alreadyExisting) 
                    {                    
                        listToBeFilled.Items.Add(tmpString);
                        _previousLoadedTable.Add(tmpString);
                    }
                    else 
                    {
                        if (!_previousLoadedTable.Contains(tmpString)) 
                        { 
                            listToBeFilled.Items.Add(tmpString);                        
                        }
                    }
                }

                _previousLoadedTable.Clear();
                _previousLoadedTable = actualList;

            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);                
            }
            finally 
            {
                if (rdr!=null) rdr.Close();
                _provider.CloseConnection(conn);
            }
        }

        private void saveToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                saveFileDialog1.Filter = "SQL files (*.sql)|*.sql|All files (*.*)|*.*";
                saveFileDialog1.FilterIndex = 1;
                if (saveFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    if (!String.IsNullOrEmpty(saveFileDialog1.FileName))
                    {
                        System.IO.File.WriteAllText(saveFileDialog1.FileName, _textArea.Text);
                        MessageBox.Show("SQL file saved", "File saved", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                }
            }
            catch (Exception) { throw; }
        }

        private void ExecutionOpenButton_Click(object sender, EventArgs e)
        {
            try
            { 
                if (splitContainer1.Panel2Collapsed)
                {
                    splitContainer1.Panel2Collapsed = false;
                    ExecutionOpenButton.ImageIndex = 1;
                }
                     else
                { 
                    splitContainer1.Panel2Collapsed = true;
                    ExecutionOpenButton.ImageIndex = 0;
                }
            }
            catch (Exception ex)
            {
                CommonItem.ErrManger.ErrorManagement(ex, false, this);
            }
}
        #region SQLEditor


        private void InitColors()
            {

                _textArea.SetSelectionBackColor(true, Color.LightSteelBlue);

            }

            private void InitHotkeys()
            {

                // register the hotkeys with the form
                HotKeyManager.AddHotKey(this, OpenSearch, Keys.F, true);
                HotKeyManager.AddHotKey(this, OpenFindDialog, Keys.F, true, false, true);
                HotKeyManager.AddHotKey(this, OpenReplaceDialog, Keys.R, true);
                HotKeyManager.AddHotKey(this, OpenReplaceDialog, Keys.H, true);
                HotKeyManager.AddHotKey(this, Uppercase, Keys.U, true);
                HotKeyManager.AddHotKey(this, Lowercase, Keys.L, true);
                HotKeyManager.AddHotKey(this, ZoomIn, Keys.Oemplus, true);
                HotKeyManager.AddHotKey(this, ZoomOut, Keys.OemMinus, true);
                HotKeyManager.AddHotKey(this, ZoomDefault, Keys.D0, true);
                HotKeyManager.AddHotKey(this, CloseSearch, Keys.Escape);

                // remove conflicting hotkeys from scintilla
                _textArea.ClearCmdKey(Keys.Control | Keys.F);
                _textArea.ClearCmdKey(Keys.Control | Keys.R);
                _textArea.ClearCmdKey(Keys.Control | Keys.H);
                _textArea.ClearCmdKey(Keys.Control | Keys.L);
                _textArea.ClearCmdKey(Keys.Control | Keys.U);

            }

            private void InitSyntaxColoring()
            {

                // Configure the default style
                _textArea.StyleResetDefault();
                _textArea.Styles[Style.Default].Font = "Consolas";
                _textArea.Styles[Style.Default].Size = 10;
                _textArea.Styles[Style.Default].BackColor = Color.White;
                _textArea.Styles[Style.Default].ForeColor = Color.Black;
                _textArea.StyleClearAll();

                // Configure the CPP (C#) lexer styles
                _textArea.Styles[Style.Cpp.Identifier].ForeColor = Color.Blue;
                _textArea.Styles[Style.Cpp.Comment].ForeColor = Color.Green;
                _textArea.Styles[Style.Cpp.CommentLine].ForeColor = Color.Green;
                _textArea.Styles[Style.Cpp.CommentDoc].ForeColor = Color.Green;
                _textArea.Styles[Style.Cpp.Number].ForeColor = Color.Blue;
                _textArea.Styles[Style.Cpp.String].ForeColor = Color.DarkRed;
                _textArea.Styles[Style.Cpp.Character].ForeColor = Color.Blue;
                _textArea.Styles[Style.Cpp.Preprocessor].ForeColor = Color.Black;
                _textArea.Styles[Style.Cpp.Operator].ForeColor = Color.Violet;
                _textArea.Styles[Style.Cpp.Regex].ForeColor = Color.Black;
                _textArea.Styles[Style.Cpp.CommentLineDoc].ForeColor = Color.Green;
                _textArea.Styles[Style.Cpp.Word].ForeColor = Color.Black;
                _textArea.Styles[Style.Cpp.Word2].ForeColor = Color.Black;
                _textArea.Styles[Style.Cpp.CommentDocKeyword].ForeColor = Color.Green;
                _textArea.Styles[Style.Cpp.CommentDocKeywordError].ForeColor = Color.Green;
                _textArea.Styles[Style.Cpp.GlobalClass].ForeColor = Color.Blue;

                _textArea.Lexer = Lexer.Sql;

                _textArea.SetKeywords(0, "class extends implements import interface new case do while else if for in switch throw get set function var try catch finally while with default break continue delete return each const namespace package include use is as instanceof typeof author copy default deprecated eventType example exampleText exception haxe inheritDoc internal link mtasc mxmlc param private return see serial serialData serialField since throws usage version langversion playerversion productversion dynamic private public partial static intrinsic internal native override protected AS3 final super this arguments null Infinity NaN undefined true false abstract as base bool break by byte case catch char checked class const continue decimal default delegate do double descending explicit event extern else enum false finally fixed float for foreach from goto group if implicit in int interface internal into is lock long new null namespace object operator out override orderby params private protected public readonly ref return switch struct sbyte sealed short sizeof stackalloc static string select this throw true try typeof uint ulong unchecked unsafe ushort using var virtual volatile void while where yield");
                _textArea.SetKeywords(1, "void Null ArgumentError arguments Array Boolean Class Date DefinitionError Error EvalError Function int Math Namespace Number Object RangeError ReferenceError RegExp SecurityError String SyntaxError TypeError uint XML XMLList Boolean Byte Char DateTime Decimal Double Int16 Int32 Int64 IntPtr SByte Single UInt16 UInt32 UInt64 UIntPtr Void Path File System Windows Forms ScintillaNET");

            }

            private void OnTextChanged(object sender, EventArgs e)
            {

            }


            #region Numbers, Bookmarks, Code Folding

            /// <summary>
            /// the background color of the text area
            /// </summary>
            private const int BACK_COLOR = 0xa9a9a9;

            /// <summary>
            /// default text color of the text area
            /// </summary>
            private const int FORE_COLOR = 0x003366;

            /// <summary>
            /// change this to whatever margin you want the line numbers to show in
            /// </summary>
            private const int NUMBER_MARGIN = 1;

            /// <summary>
            /// change this to whatever margin you want the bookmarks/breakpoints to show in
            /// </summary>
            private const int BOOKMARK_MARGIN = 2;
            private const int BOOKMARK_MARKER = 2;

            /// <summary>
            /// change this to whatever margin you want the code folding tree (+/-) to show in
            /// </summary>
            private const int FOLDING_MARGIN = 3;

            /// <summary>
            /// set this true to show circular buttons for code folding (the [+] and [-] buttons on the margin)
            /// </summary>
            private const bool CODEFOLDING_CIRCULAR = true;

            private void InitNumberMargin()
            {

                _textArea.Styles[Style.LineNumber].BackColor = IntToColor(BACK_COLOR);
                _textArea.Styles[Style.LineNumber].ForeColor = IntToColor(FORE_COLOR);
                _textArea.Styles[Style.IndentGuide].ForeColor = IntToColor(FORE_COLOR);
                _textArea.Styles[Style.IndentGuide].BackColor = IntToColor(BACK_COLOR);

                var nums = _textArea.Margins[NUMBER_MARGIN];
                nums.Width = 20;
                nums.Type = MarginType.Number;
                nums.Sensitive = true;
                nums.Mask = 0;

                _textArea.MarginClick += _textArea_MarginClick;
            }

            private void InitBookmarkMargin()
            {

                //_textArea.SetFoldMarginColor(true, IntToColor(BACK_COLOR));

                var margin = _textArea.Margins[BOOKMARK_MARGIN];
                margin.Width = 20;
                margin.Sensitive = true;
                margin.Type = MarginType.Symbol;
                margin.Mask = (1 << BOOKMARK_MARKER);
                //margin.Cursor = MarginCursor.Arrow;

                var marker = _textArea.Markers[BOOKMARK_MARKER];
                marker.Symbol = MarkerSymbol.Circle;
                marker.SetBackColor(IntToColor(0xFF003B));
                marker.SetForeColor(IntToColor(0x000000));
                marker.SetAlpha(100);

            }

            private void InitCodeFolding()
            {

                _textArea.SetFoldMarginColor(true, IntToColor(BACK_COLOR));
                _textArea.SetFoldMarginHighlightColor(true, IntToColor(BACK_COLOR));

                // Enable code folding
                _textArea.SetProperty("fold", "1");
                _textArea.SetProperty("fold.compact", "1");

                // Configure a margin to display folding symbols
                _textArea.Margins[FOLDING_MARGIN].Type = MarginType.Symbol;
                _textArea.Margins[FOLDING_MARGIN].Mask = Marker.MaskFolders;
                _textArea.Margins[FOLDING_MARGIN].Sensitive = true;
                _textArea.Margins[FOLDING_MARGIN].Width = 20;

                // Set colors for all folding markers
                for (int i = 25; i <= 31; i++)
                {
                    _textArea.Markers[i].SetForeColor(IntToColor(BACK_COLOR)); // styles for [+] and [-]
                    _textArea.Markers[i].SetBackColor(IntToColor(FORE_COLOR)); // styles for [+] and [-]
                }

                // Configure folding markers with respective symbols
                _textArea.Markers[Marker.Folder].Symbol = CODEFOLDING_CIRCULAR ? MarkerSymbol.CirclePlus : MarkerSymbol.BoxPlus;
                _textArea.Markers[Marker.FolderOpen].Symbol = CODEFOLDING_CIRCULAR ? MarkerSymbol.CircleMinus : MarkerSymbol.BoxMinus;
                _textArea.Markers[Marker.FolderEnd].Symbol = CODEFOLDING_CIRCULAR ? MarkerSymbol.CirclePlusConnected : MarkerSymbol.BoxPlusConnected;
                _textArea.Markers[Marker.FolderMidTail].Symbol = MarkerSymbol.TCorner;
                _textArea.Markers[Marker.FolderOpenMid].Symbol = CODEFOLDING_CIRCULAR ? MarkerSymbol.CircleMinusConnected : MarkerSymbol.BoxMinusConnected;
                _textArea.Markers[Marker.FolderSub].Symbol = MarkerSymbol.VLine;
                _textArea.Markers[Marker.FolderTail].Symbol = MarkerSymbol.LCorner;

                // Enable automatic folding
                _textArea.AutomaticFold = (AutomaticFold.Show | AutomaticFold.Click | AutomaticFold.Change);

            }

            private void _textArea_MarginClick(object sender, MarginClickEventArgs e)
            {
                if (e.Margin == BOOKMARK_MARGIN)
                {
                    // Do we have a marker for this line?
                    const uint mask = (1 << BOOKMARK_MARKER);
                    var line = _textArea.Lines[_textArea.LineFromPosition(e.Position)];
                    if ((line.MarkerGet() & mask) > 0)
                    {
                        // Remove existing bookmark
                        line.MarkerDelete(BOOKMARK_MARKER);
                    }
                    else
                    {
                        // Add bookmark
                        line.MarkerAdd(BOOKMARK_MARKER);
                    }
                }
            }

            #endregion

            #region Drag & Drop File

            public void InitDragDropFile()
            {

                _textArea.AllowDrop = true;
                _textArea.DragEnter += delegate (object sender, DragEventArgs e) {
                    if (e.Data.GetDataPresent(DataFormats.FileDrop))
                        e.Effect = DragDropEffects.Copy;
                    else
                        e.Effect = DragDropEffects.None;
                };
                _textArea.DragDrop += delegate (object sender, DragEventArgs e) {

                    // get file drop
                    if (e.Data.GetDataPresent(DataFormats.FileDrop))
                    {

                        Array a = (Array)e.Data.GetData(DataFormats.FileDrop);
                        if (a != null)
                        {

                            string path = a.GetValue(0).ToString();

                            LoadDataFromFile(path);

                        }
                    }
                };

            }

            private void LoadDataFromFile(string path)
            {
                if (System.IO.File.Exists(path))
                {
                    //FileName.Text = System.IO.Path.GetFileName(path);
                    _textArea.Text = System.IO.File.ReadAllText(path);
                }
            }

            #endregion

            #region Main Menu Commands

            private void openToolStripMenuItem_Click(object sender, EventArgs e)
            {
                if (openFileDialog1.ShowDialog() == DialogResult.OK)
                {
                    LoadDataFromFile(openFileDialog1.FileName);
                }
            }

            private void findToolStripMenuItem_Click(object sender, EventArgs e)
            {
                OpenSearch();
            }           

            private void cutToolStripMenuItem_Click(object sender, EventArgs e)
            {
                _textArea.Cut();
            }

            private void copyToolStripMenuItem_Click(object sender, EventArgs e)
            {
                _textArea.Copy();
            }

            private void pasteToolStripMenuItem_Click(object sender, EventArgs e)
            {
                _textArea.Paste();
            }

            private void selectAllToolStripMenuItem_Click(object sender, EventArgs e)
            {
                _textArea.SelectAll();
            }

            private void selectLineToolStripMenuItem_Click(object sender, EventArgs e)
            {
                Line line = _textArea.Lines[_textArea.CurrentLine];
                _textArea.SetSelection(line.Position + line.Length, line.Position);
            }

            private void clearSelectionToolStripMenuItem_Click(object sender, EventArgs e)
            {
                _textArea.SetEmptySelection(0);
            }

            private void indentSelectionToolStripMenuItem_Click(object sender, EventArgs e)
            {
                Indent();
            }

            private void outdentSelectionToolStripMenuItem_Click(object sender, EventArgs e)
            {
                Outdent();
            }

            private void uppercaseSelectionToolStripMenuItem_Click(object sender, EventArgs e)
            {
                Uppercase();
            }

            private void lowercaseSelectionToolStripMenuItem_Click(object sender, EventArgs e)
            {
                Lowercase();
            }

            private void wordWrapToolStripMenuItem1_Click(object sender, EventArgs e)
            {

                // toggle word wrap
                wordWrapItem.Checked = !wordWrapItem.Checked;
                _textArea.WrapMode = wordWrapItem.Checked ? WrapMode.Word : WrapMode.None;
            }

            private void indentGuidesToolStripMenuItem_Click(object sender, EventArgs e)
            {

                // toggle indent guides
                indentGuidesItem.Checked = !indentGuidesItem.Checked;
                _textArea.IndentationGuides = indentGuidesItem.Checked ? IndentView.LookBoth : IndentView.None;
            }           

            private void zoomInToolStripMenuItem_Click(object sender, EventArgs e)
            {
                ZoomIn();
            }

            private void zoomOutToolStripMenuItem_Click(object sender, EventArgs e)
            {
                ZoomOut();
            }

            private void zoom100ToolStripMenuItem_Click(object sender, EventArgs e)
            {
                ZoomDefault();
            }

            private void collapseAllToolStripMenuItem_Click(object sender, EventArgs e)
            {
                _textArea.FoldAll(FoldAction.Contract);
            }

            private void expandAllToolStripMenuItem_Click(object sender, EventArgs e)
            {
                _textArea.FoldAll(FoldAction.Expand);
            }


            #endregion

            #region Uppercase / Lowercase

            private void Lowercase()
            {

                // save the selection
                int start = _textArea.SelectionStart;
                int end = _textArea.SelectionEnd;

                // modify the selected text
                _textArea.ReplaceSelection(_textArea.GetTextRange(start, end - start).ToLower());

                // preserve the original selection
                _textArea.SetSelection(start, end);
            }

            private void Uppercase()
            {

                // save the selection
                int start = _textArea.SelectionStart;
                int end = _textArea.SelectionEnd;

                // modify the selected text
                _textArea.ReplaceSelection(_textArea.GetTextRange(start, end - start).ToUpper());

                // preserve the original selection
                _textArea.SetSelection(start, end);
            }

            #endregion

            #region Indent / Outdent

            private void Indent()
            {
                // we use this hack to send "Shift+Tab" to scintilla, since there is no known API to indent,
                // although the indentation function exists. Pressing TAB with the editor focused confirms this.
                GenerateKeystrokes("{TAB}");
            }

            private void Outdent()
            {
                // we use this hack to send "Shift+Tab" to scintilla, since there is no known API to outdent,
                // although the indentation function exists. Pressing Shift+Tab with the editor focused confirms this.
                GenerateKeystrokes("+{TAB}");
            }

            private void GenerateKeystrokes(string keys)
            {
                HotKeyManager.Enable = false;
                _textArea.Focus();
                SendKeys.Send(keys);
                HotKeyManager.Enable = true;
            }

            #endregion

            #region Zoom

            private void ZoomIn()
            {
                _textArea.ZoomIn();
            }

            private void ZoomOut()
            {
                _textArea.ZoomOut();
            }

            private void ZoomDefault()
            {
                _textArea.Zoom = 0;
            }


            #endregion

            #region Quick Search Bar

            bool SearchIsOpen = false;

            private void OpenSearch()
            {

                SearchManager.SearchBox = TxtSearch;
                SearchManager.TextArea = _textArea;

                if (!SearchIsOpen)
                {
                    SearchIsOpen = true;
                    InvokeIfNeeded(delegate () {
                        PanelSearch.Visible = true;
                        TxtSearch.Text = SearchManager.LastSearch;
                        TxtSearch.Focus();
                        TxtSearch.SelectAll();
                    });
                }
                else
                {
                    InvokeIfNeeded(delegate () {
                        TxtSearch.Focus();
                        TxtSearch.SelectAll();
                    });
                }
            }
            private void CloseSearch()
            {
                if (SearchIsOpen)
                {
                    SearchIsOpen = false;
                    InvokeIfNeeded(delegate () {
                        PanelSearch.Visible = false;
                        //CurBrowser.GetBrowser().StopFinding(true);
                    });
                }
            }

            private void BtnClearSearch_Click(object sender, EventArgs e)
            {
                CloseSearch();
            }

            private void BtnPrevSearch_Click(object sender, EventArgs e)
            {
                SearchManager.Find(false, false);
            }
            private void BtnNextSearch_Click(object sender, EventArgs e)
            {
                SearchManager.Find(true, false);
            }
            private void TxtSearch_TextChanged(object sender, EventArgs e)
            {
                SearchManager.Find(true, true);
            }

            private void TxtSearch_KeyDown(object sender, KeyEventArgs e)
            {
                if (HotKeyManager.IsHotkey(e, Keys.Enter))
                {
                    SearchManager.Find(true, false);
                }
                if (HotKeyManager.IsHotkey(e, Keys.Enter, true) || HotKeyManager.IsHotkey(e, Keys.Enter, false, true))
                {
                    SearchManager.Find(false, false);
                }
            }

            #endregion

            #region Find & Replace Dialog

            private void OpenFindDialog()
            {

            }
            private void OpenReplaceDialog()
            {


            }

            #endregion

            #region Utils

            public static Color IntToColor(int rgb)
            {
                return Color.FromArgb(255, (byte)(rgb >> 16), (byte)(rgb >> 8), (byte)rgb);
            }

            public void InvokeIfNeeded(Action action)
            {
                if (this.InvokeRequired)
                {
                    this.BeginInvoke(action);
                }
                else
                {
                    action.Invoke();
                }
            }


        #endregion

        #endregion

       
    }
}
