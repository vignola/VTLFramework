using System.Collections.Generic;
using System.Xml;

namespace VTL_Editor_PL.classes.tool
{
    public class AutocompleteVocabulary
    {
        
        private XmlDocument _operators;
        private List<AutocompleteVocabularyItem> _operatorList;
        private List<string> _keywords;
        private List<string> _declarations;

        public class AutocompleteVocabularyItem 
        {
            public string ID { get; set; }
            public string method { get; set; }
            public string description { get; set; }
            public string help { get; set; }
        }

        public AutocompleteVocabularyItem[] OperatorList 
        {
            get { return _operatorList.ToArray(); }
            set { _operatorList = new List<AutocompleteVocabularyItem>(value); }
        }

        public string[] KeyWords
        {
            get { return _keywords.ToArray(); }
            set { _keywords = new List<string>(value); }
        }

        public string[] Declarations
        {
            get { return _declarations.ToArray(); }
            set { _declarations = new List<string>(value); }
        }

        public AutocompleteVocabulary() 
        {
            _operatorList = new List<AutocompleteVocabularyItem>();
            _keywords = new List<string>();
            _declarations = new List<string>();
        }

        public void LoadFromXml(string fileName) 
        {            
            _operators = new XmlDocument();
            _operators.Load(fileName);
            XmlNodeList result = _operators.SelectNodes("./AutoCompleteVocabulary/operators/operator");
            foreach (XmlNode node in result) 
            {
                AutocompleteVocabularyItem itm= new AutocompleteVocabularyItem();
                itm.ID = node.Attributes["ID"].Value;
                itm.method = node.ChildNodes[0].InnerText;
                itm.description = node.ChildNodes[1].InnerText;
                itm.help = node.ChildNodes[2].InnerText;
                _operatorList.Add(itm);
            }

            result = _operators.SelectNodes("./AutoCompleteVocabulary/keywords/key");
            foreach (XmlNode node in result)
            {
                _keywords.Add(node.InnerText);
            }

            result = _operators.SelectNodes("./AutoCompleteVocabulary/declarations/declaration");
            foreach (XmlNode node in result)
            {
                _declarations.Add(node.InnerText);
            }
        }
    }
}
