using System;
using System.Collections.Generic;
using System.Drawing;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using FastColoredTextBoxNS;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.tool;

namespace VTL_Editor_PL.gui
{
    public partial class fastCodeTextBox : UserControl
    {
        private FastColoredTextBox _codeTextBox;
        private AutocompleteMenu popupMenu;

        TextStyle BlueStyle = new TextStyle(Brushes.Blue, null, FontStyle.Regular);
        TextStyle BoldStyle = new TextStyle(null, null, FontStyle.Bold | FontStyle.Underline);
        TextStyle GrayStyle = new TextStyle(Brushes.Gray, null, FontStyle.Regular);
        TextStyle MagentaStyle = new TextStyle(Brushes.Magenta, null, FontStyle.Regular);
        TextStyle GreenStyle = new TextStyle(Brushes.Green, null, FontStyle.Italic);
        TextStyle BrownStyle = new TextStyle(Brushes.Brown, null, FontStyle.Italic);
        TextStyle MaroonStyle = new TextStyle(Brushes.Maroon, null, FontStyle.Regular);
        TextStyle DarkRedBoldStyle = new TextStyle(Brushes.DarkRed,null, FontStyle.Bold);
        MarkerStyle SameWordsStyle = new MarkerStyle(new SolidBrush(Color.FromArgb(40, Color.Gray)));
        TextStyle GrayOptionalStyle = new TextStyle(Brushes.DarkGray, null, FontStyle.Italic);


        private string[] _keywords;
        private string[] _declarationSnippets;
        private AutocompleteVocabulary.AutocompleteVocabularyItem[] _snippetList;
        string[] methods = {"fake_method"};
         
        //string[] declarationSnippets = { 
        //       "public class ^\n{\n}", "private class ^\n{\n}", "internal class ^\n{\n}",
        //       "public struct ^\n{\n;\n}", "private struct ^\n{\n;\n}", "internal struct ^\n{\n;\n}",
        //       "public void ^()\n{\n;\n}", "private void ^()\n{\n;\n}", "internal void ^()\n{\n;\n}", "protected void ^()\n{\n;\n}",
        //       "public ^{ get; set; }", "private ^{ get; set; }", "internal ^{ get; set; }", "protected ^{ get; set; }"
        //       };

        public delegate void ShowPositionEventHandler(object sender, ShowCaretPositionEventArgs arg);
        public event ShowPositionEventHandler ShowCaretPosition;

        public string Text 
        {
            get { return _codeTextBox.Text; }
            set { _codeTextBox.Text = value; }
        }

        public fastCodeTextBox()
        {
            InitializeComponent();
            _codeTextBox = new FastColoredTextBox();
            _codeTextBox.Dock = DockStyle.Fill;
            _codeTextBox.TextChanged+=_codeTextBox_TextChanged;
            _codeTextBox.MouseClick += _codeTextBox_MouseClick;
            this.Controls.Add(_codeTextBox);

            popupMenu = new AutocompleteMenu(_codeTextBox);
            popupMenu.Items.ImageList = imageList1;
            popupMenu.SearchPattern = @"[\w\.:=!<>]";
            popupMenu.AllowTabKey = true;
            //
            BuildAutocompleteMenu();
        }

        public void ShowFindDialog() 
        {
            _codeTextBox.ShowFindDialog();
        }

        public void ShowReplaceDialog() 
        {
            _codeTextBox.ShowReplaceDialog();
        }

        public void CommentSelectedLines() 
        {
            _codeTextBox.InsertLinePrefix(_codeTextBox.CommentPrefix);
        }

        public void UncommentSelectedLines() 
        {
            _codeTextBox.RemoveLinePrefix(_codeTextBox.CommentPrefix);
        }

        public void InsertTextToCaret(string text_statement) 
        {
            _codeTextBox.InsertText(text_statement, true);
        }

        public bool IsTextSelected() 
        {
            return String.IsNullOrEmpty(_codeTextBox.SelectedText) ? true : false;
        }

        public void TextCopy() 
        { 
            _codeTextBox.Copy(); 
        }

        public void TextPaste() 
        {
            _codeTextBox.Paste(); 
        }

        public void TextCut() 
        { 
            _codeTextBox.Cut(); 
        }

        public void TextSelectAll()
        {
            _codeTextBox.Selection.SelectAll();
        }

        public void TextUndo()
        {
            if (_codeTextBox.UndoEnabled)
                _codeTextBox.Undo();
        }

        public void TextRedo() 
        {
            if (_codeTextBox.RedoEnabled)
                _codeTextBox.Redo();
        }

        private void VTLSyntaxHighlight(TextChangedEventArgs e)
        {
            _codeTextBox.LeftBracket = '(';
            _codeTextBox.RightBracket = ')';
            _codeTextBox.LeftBracket2 = '\x0';
            _codeTextBox.RightBracket2 = '\x0';
            //clear style of changed range
            e.ChangedRange.ClearStyle(BlueStyle, BoldStyle, GrayStyle, MagentaStyle, GreenStyle, BrownStyle);

            //string highlighting
            e.ChangedRange.SetStyle(BrownStyle, @"""""|@""""|''|@"".*?""|(?<!@)(?<range>"".*?[^\\]"")|'.*?[^\\]'");
            //comment highlighting
            e.ChangedRange.SetStyle(GreenStyle, @"//.*$", RegexOptions.Multiline);
            e.ChangedRange.SetStyle(GreenStyle, @"(/\*.*?\*/)|(/\*.*)", RegexOptions.Singleline);
            e.ChangedRange.SetStyle(GreenStyle, @"(/\*.*?\*/)|(.*\*/)", RegexOptions.Singleline | RegexOptions.RightToLeft);
            //number highlighting
            e.ChangedRange.SetStyle(MagentaStyle, @"\b\d+[\.]?\d*([eE]\-?\d+)?[lLdDfF]?\b|\b0x[a-fA-F\d]+\b");
            //attribute highlighting
            e.ChangedRange.SetStyle(GrayStyle, @"^\s*(?<range>\[.+?\])\s*$", RegexOptions.Multiline);
            //define name highlighting
            e.ChangedRange.SetStyle(BoldStyle, @"\b(define\s+operator|define\s+datapoint\s+ruleset|define\s+hierarchical\s+ruleset)\s+(?<range>\w+?)\b");
            //VTL keyword highlighting            
            e.ChangedRange.SetStyle(BlueStyle, @"\b(eval|if|then|else|using|with|current_date|on|drop|keep|calc|attrcalc|rename|as|and|or|xor|not|between|in|not_in|isnull|ex|union|diff|symdiff|intersect|keys|intyear|intmonth|intday|check|exists_in|to|return|imbalance|errorcode|all|aggr|errorlevel|order|by|rank|asc|desc|min|max|first|last|indexof|abs|key|ln|log|trunc|round|power|mod|length|trim|upper|lower|substr|sum|avg|median|count|identifier|measure|attribute|filter|merge|exp|componentRole|viral|match_characters|type|nvl|hierarchy|_|invalid|valuedomain|variable|data|structure|dataset|operator|define|datapoint|hierarchical|ruleset|rule|end|alterDataset|ltrim|rtrim|instr|replace|ceil|floor|sqrt|any|setdiff|stddev_pop|stddev_samp|var_pop|var_samp|group|except|having|first_value|last_value|lag|lead|ratio_to_report|over|preceding|following|unbounded|partition|rows|range|current|valid|fill_time_series|flow_to_stock|stock_to_flow|timeshift|measures|no_measures|condition|boolean|date|time_period|number|string|integer|float|list|record|restrict|yyyy|mm|dd|maxLength|regexp|is|when|from|aggregates|points|point|total|partial|always|inner_join|left_join|cross_join|full_join|maps_from|maps_to|map_to|map_from|returns|pivot|unpivot|sub|apply|conditioned|period_indicator|single|duration|time_agg|unit|Value|valuedomains|variables|input|output|cast|rule_priority|dataset_priority|default|check_datapoint|check_hierarchy|computed|non_null|non_zero|partial_null|partial_zero|always_null|always_zero|components|all_measures|scalar|component|datapoint_on_valuedomains|datapoint_on_variables|hierarchical_on_valuedomains|hierarchical_on_variables|set|language|INTEGER_CONSTANT|POSITIVE_CONSTANT|NEGATIVE_CONSTANT|FLOAT_CONSTANT|BOOLEAN_CONSTANT|null|STRING_CONSTANT|IDENTIFIER|DIGITS0_9|MONTH|DAY|YEAR|WEEK|HOURS|MINUTES|SECONDS|DATE_FORMAT|TIME_FORMAT|TIME_UNIT|TIME|WS|ML_COMMENT|SL_COMMENT|FREQUENCY)\b|#region\b|#endregion\b");
            //VTL optional parameter
            e.ChangedRange.SetStyle(GrayOptionalStyle, @"\b(mask|partitionClause|number_to_apply|exponent|operand|operand1|operand2|ScalarType|substr_start|substr_length|pattern|pattern1|pattern2|str_start|str_occurrence|numDigit|between_from|between_to|pattern_match|true_false_all|single_all|dataset_|dataset_1|dataset_2|thenOperand|elseOperand)\b|#region\b|#endregion\b");
            //VTL Assignament
            e.ChangedRange.SetStyle(DarkRedBoldStyle, @"(:=){1}|(<-){1}");
            //clear folding markers
            e.ChangedRange.ClearFoldingMarkers();

            //set folding markers           
            e.ChangedRange.SetFoldingMarkers(@"/\*", @"\*/");//allow to collapse comment block
        }

        private void InitStylesPriority()
        {           
            //add this style explicitly for drawing under other styles
            _codeTextBox.AddStyle(SameWordsStyle);
        }

        private void _codeTextBox_TextChanged(object sender, TextChangedEventArgs e)
        {            
            //For sample, we will highlight the syntax of C# manually, although could use built-in highlighter
            VTLSyntaxHighlight(e);//custom highlighting                
            if(ShowCaretPosition!=null)
                ShowCaretPosition(this, new ShowCaretPositionEventArgs(_codeTextBox.Selection.Start.iLine+1, _codeTextBox.Selection.Start.iChar));
        }

        void _codeTextBox_MouseClick(object sender, MouseEventArgs e)
        {
            if (ShowCaretPosition != null)
                ShowCaretPosition(this, new ShowCaretPositionEventArgs(_codeTextBox.Selection.Start.iLine + 1, _codeTextBox.Selection.Start.iChar));
        }

         private void fastCodeTextBox_Load(object sender, EventArgs e)
         {
             _codeTextBox.ClearStylesBuffer();
             _codeTextBox.Range.ClearStyle(StyleIndex.All);
             InitStylesPriority();
             _codeTextBox.Language = Language.Custom;
             _codeTextBox.CommentPrefix = "//";             
             _codeTextBox.OnTextChanged();
             _codeTextBox.OnSyntaxHighlight(new TextChangedEventArgs(_codeTextBox.Range));             
         }

         private void BuildAutocompleteMenu()
         {
            try
            {
             List<AutocompleteItem> items = new List<AutocompleteItem>();
             AutocompleteVocabulary vocabulary = new AutocompleteVocabulary();
             AutocompleteVocabulary voc = new AutocompleteVocabulary();
            
             voc.LoadFromXml(CommonItem.AUTOCOMPLETE_VOCABULARY_PATH);
            
             _snippetList = voc.OperatorList;
             _keywords = voc.KeyWords;
             _declarationSnippets = voc.Declarations;
             
             for (int i = 0; i < _snippetList.Length; i++)
             {
                 items.Add(new SnippetAutocompleteItem(_snippetList[i].method, _snippetList[i].description, _snippetList[i].help));
             }

             foreach (var item in _declarationSnippets)
                 items.Add(new DeclarationSnippet(item) { ImageIndex = 0 });
             foreach (var item in methods)
                 items.Add(new MethodAutocompleteItem(item) { ImageIndex = 2 });
             foreach (var item in _keywords)
                 items.Add(new AutocompleteItem(item));

             items.Add(new InsertSpaceSnippet());
             items.Add(new InsertSpaceSnippet(@"^(\w+)([=<>!:]+)(\w+)$"));
             items.Add(new InsertEnterSnippet());

             //set as autocomplete source
             popupMenu.Items.SetAutocompleteItems(items);
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
         }

         /// <summary>
         /// This item appears when any part of snippet text is typed
         /// </summary>
         class DeclarationSnippet : SnippetAutocompleteItem
         {
             public DeclarationSnippet(string snippet)
                 : base(snippet)
             {
             }

             public override CompareResult Compare(string fragmentText)
             {
                 var pattern = Regex.Escape(fragmentText);
                 if (Regex.IsMatch(Text, "\\b" + pattern, RegexOptions.IgnoreCase))
                     return CompareResult.Visible;
                 return CompareResult.Hidden;
             }
         }

         /// <summary>
         /// Divides numbers and words: "123AND456" -> "123 AND 456"
         /// Or "i=2" -> "i = 2"
         /// </summary>
         class InsertSpaceSnippet : AutocompleteItem
         {
             string pattern;

             public InsertSpaceSnippet(string pattern)
                 : base("")
             {
                 this.pattern = pattern;
             }

             public InsertSpaceSnippet()
                 : this(@"^(\d+)([a-zA-Z_]+)(\d*)$")
             {
             }

             public override CompareResult Compare(string fragmentText)
             {
                 if (Regex.IsMatch(fragmentText, pattern))
                 {
                     Text = InsertSpaces(fragmentText);
                     if (Text != fragmentText)
                         return CompareResult.Visible;
                 }
                 return CompareResult.Hidden;
             }

             public string InsertSpaces(string fragment)
             {
                 var m = Regex.Match(fragment, pattern);
                 if (m == null)
                     return fragment;
                 if (m.Groups[1].Value == "" && m.Groups[3].Value == "")
                     return fragment;
                 return (m.Groups[1].Value + " " + m.Groups[2].Value + " " + m.Groups[3].Value).Trim();
             }

             public override string ToolTipTitle
             {
                 get
                 {
                     return Text;
                 }
             }
         }

         /// <summary>
         /// Inerts line break after '}'
         /// </summary>
         class InsertEnterSnippet : AutocompleteItem
         {
             Place enterPlace = Place.Empty;

             public InsertEnterSnippet()
                 : base("[Line break]")
             {
             }

             public override CompareResult Compare(string fragmentText)
             {
                 var r = Parent.Fragment.Clone();
                 while (r.Start.iChar > 0)
                 {
                     if (r.CharBeforeStart == '}')
                     {
                         enterPlace = r.Start;
                         return CompareResult.Visible;
                     }

                     r.GoLeftThroughFolded();
                 }

                 return CompareResult.Hidden;
             }

             public override string GetTextForReplace()
             {
                 //extend range
                 Range r = Parent.Fragment;
                 Place end = r.End;
                 r.Start = enterPlace;
                 r.End = r.End;
                 //insert line break
                 return Environment.NewLine + r.Text;
             }

             public override void OnSelected(AutocompleteMenu popupMenu, SelectedEventArgs e)
             {
                 base.OnSelected(popupMenu, e);
                 if (Parent.Fragment.tb.AutoIndent)
                     Parent.Fragment.tb.DoAutoIndent();
             }

             public override string ToolTipTitle
             {
                 get
                 {
                     return "Insert line break after '}'";
                 }
             }
         }

         /// <summary>
         /// Autocomplete item for code snippets
         /// </summary>
         /// <remarks>Snippet can contain special char ^ for caret position.</remarks>
         class SnippetAutocompleteItem : AutocompleteItem
         {

             public SnippetAutocompleteItem(string snippet)
             {
                 Text = snippet.Replace("\r", "");
                 ToolTipTitle = "Operator";
                 ToolTipText = snippet;
                 ImageIndex = 1;
             }

             public SnippetAutocompleteItem(string snippet, string operatorStructure, string operatorHelp)
             {
                 Text = snippet.Replace("\r", "");
                 ToolTipTitle = operatorStructure;
                 ToolTipText = operatorHelp;
                 ImageIndex = 1;
             }

             public override string ToString()
             {
                 return MenuText ?? Text.Replace("\n", " ").Replace("^", "");
             }

             public override string GetTextForReplace()
             {
                 return Text;
             }

             public override void OnSelected(AutocompleteMenu popupMenu, SelectedEventArgs e)
             {
                 e.Tb.BeginUpdate();
                 e.Tb.Selection.BeginUpdate();
                 //remember places
                 var p1 = popupMenu.Fragment.Start;
                 var p2 = e.Tb.Selection.Start;
                 //do auto indent
                 if (e.Tb.AutoIndent)
                 {
                     for (int iLine = p1.iLine + 1; iLine <= p2.iLine; iLine++)
                     {
                         e.Tb.Selection.Start = new Place(0, iLine);
                         e.Tb.DoAutoIndent(iLine);
                     }
                 }
                 e.Tb.Selection.Start = p1;
                 //move caret position right and find char ^
                 while (e.Tb.Selection.CharBeforeStart != '^')
                     if (!e.Tb.Selection.GoRightThroughFolded())
                         break;
                 //remove char ^
                 e.Tb.Selection.GoLeft(true);
                 e.Tb.InsertText("");
                 //
                 e.Tb.Selection.EndUpdate();
                 e.Tb.EndUpdate();
             }

             /// <summary>
             /// Compares fragment text with this item
             /// </summary>
             public override CompareResult Compare(string fragmentText)
             {
                 if (Text.StartsWith(fragmentText, StringComparison.InvariantCultureIgnoreCase) &&
                        Text != fragmentText)
                     return CompareResult.Visible;

                 return CompareResult.Hidden;
             }
         }
         
    }
}
