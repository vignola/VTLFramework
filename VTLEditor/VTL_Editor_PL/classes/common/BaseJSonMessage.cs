using System.Collections.Generic;

namespace VTL_Editor_PL.classes.common
{
    //{"errorResponse":
    //{"code":"VTL001","message":"An unexpected error occurred while processing your request.","command":null}
    //,"resultStatements":null,
    //"resultSemantic":null,
    //"resultSQL":null}

    public class BaseJSonMessage
    {
        public class ErrorResponse 
        {
            public string code { get; set; }
            public string message { get; set; }
            public string command { get; set; }
        }

        public class Measure
        {
            public int id { get; set; }
            public string type { get; set; }
            public string name { get; set; }
            public string domainValue { get; set; }
            public string domainValueParent { get; set; }
            public string vtlComponentRole { get; set; }
        }

        public class Result
        {
            public int id { get; set; }
            public string name { get; set; }
            public object description { get; set; }
            public List<object> attributes { get; set; }
            public List<Measure> measures { get; set; }
            public List<object> viral { get; set; }
            public bool persistent { get; set; }
            public List<object> identifiers { get; set; }
        }

        public class ResultComponent
        {
            public int id { get; set; }
            public string type { get; set; }
            public string name { get; set; }
            public string domainValue { get; set; }
            public string domainValueParent { get; set; }
            public string vtlComponentRole { get; set; }
        }

        public class ScalarComponent
        {
            public int id { get; set; }
            public string type { get; set; }
            public string name { get; set; }
            public string domainValue { get; set; }
            public string domainValueParent { get; set; }
            public string vtlComponentRole { get; set; }
        }

        public class ResultStatement
        {
            public int oid { get; set; }
            public string command { get; set; }
            public string @operator { get; set; }
            public string resultExpression { get; set; }
            public string type { get; set; }
            public string sqlResult { get; set; }
            public string uuid { get; set; }
            public string clazz { get; set; }
            public string vtlExpression { get; set; }
            public string vtlDefineFunction { get; set; }
        }

        public class ResultSemantic
        {
            public Result result { get; set; }
            public ResultComponent resultComponent { get; set; }
            public List<object> messages { get; set; }
            public string command { get; set; }
            public bool acomponent { get; set; }
            public ScalarComponent scalarComponent { get; set; }
            public bool ascalar { get; set; }
            public bool adataset { get; set; }
        }


        public ErrorResponse errorResponse { get; set; }
        public List<ResultStatement> resultStatements { get; set; }
        public List<ResultSemantic> resultSemantic { get; set; }
        public List<string> resultSQL { get; set; }
        
    }
}

