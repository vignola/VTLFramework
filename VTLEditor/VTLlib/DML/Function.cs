using System.Text;


namespace VTLlib
{
    public class Function 
    {
 
        public class length : Operator
        {
            public length()
            {
                this.Syntax = "length(StringExpr)";
                this.opGroup= VTLGroup.String;
        
            }                    
             public int Evaluate(string op)
            {
                return op.Length;
            }
        }
        public class get : Operator
        {
                       
            public get()
            {
                this.Syntax = "get([dsList])";
                this.opGroup= VTLGroup.General;
        
            }      
             public void Evaluate()
            {
               
            }
        }
        public class eval : Operator
        {
                        
            public eval()
            {
                this.Syntax = "eval(language{, script}{, programPath}{, parameter_list}{,[ds_list]} )";
                this.opGroup= VTLGroup.General;
        
            }      
 
            // evaluate the eval  operator
            public void Evaluate()
            {
                
            }
        }
        public class trim : Operator
        {
             public trim()
            {
                this.Syntax = "trim(StringExpr)";
                this.opGroup= VTLGroup.String;
        
            }      
             public void Evaluate()
            {

            }
        }
        public class ltrim : Operator
        {
              public ltrim()
            {
                this.Syntax = "ltrim(StringExpr)";
                this.opGroup= VTLGroup.String;
        
            }      
             public void Evaluate()
            {

            }
        }
        public class rtrim : Operator
        {
            public rtrim()
            {
                this.Syntax = "rtrim(StringExpr)";
                this.opGroup= VTLGroup.String;
        
            }      
            public void Evaluate()
            {

            }
        }
        public class upper : Operator
        {
             public upper()
            {
                this.Syntax = "upper(StringExpr)";
                this.opGroup= VTLGroup.String;
        
            }      
            public void Evaluate()
            {

            }
        }
        public class lower : Operator
        {
              public lower()
            {
                this.Syntax = "lower(StringExpr)";
                this.opGroup= VTLGroup.String;
        
            }      
            public void Evaluate()
            {

            }
        }
        public class substr : Operator
        {
             public substr()
            {
                this.Syntax = "substr(StringExpr, start, length)";
                this.opGroup= VTLGroup.String;
        
            }      
            public void Evaluate()
            {

            }
        }
        public class instr : Operator
        {
              public instr()
            {
                this.Syntax = "instr(StringExpr,pattern,start,instance)";
                this.opGroup= VTLGroup.String;
        
            }  
             public void Evaluate()
            {

            }
        }
        public class replace : Operator
        {
              
            public replace()
            {
                this.Syntax = "replace(StringExpr, pattern1,pattern2)";
                this.opGroup= VTLGroup.String;
        
            }  
            public void Evaluate()
            {

            }
        }
        public class round : Operator
        {
            public round()
            {
                this.Syntax = "round(NumericExpr, digit)";
                this.opGroup = VTLGroup.Numeric;
        
            }  
            public void Evaluate()
            {

            }
        }
        public class ceil : Operator
        {
             public ceil()
            {
                this.Syntax = "ceil(NumericExpr)";
                this.opGroup = VTLGroup.Numeric;
        
            }  
            public void Evaluate()
            {

            }
        }
        public class floor : Operator
        {
            public floor()
            {
                this.Syntax = "floor(NumericExpr)";
                this.opGroup = VTLGroup.Numeric;
        
            }  
             public void Evaluate()
            {

            }
        }
        public class trunc : Operator
        {
            public trunc()
            {
                this.Syntax = "trunc(NumericExpr, digit)";
                this.opGroup = VTLGroup.Numeric;
        
            }  
            public void Evaluate()
            {

            }
        }
        public class current_date : Operator
        {
            public current_date()
            {
                this.Syntax = "current_date()";
                this.opGroup = VTLGroup.Date;

            }
             public void Evaluate()
            {

            }
        }
        public class inner_join : Operator
        {
            public inner_join()
            {
                var sb = new StringBuilder();

                sb.Append("inner_join((  ScalarExpr1 { as alias1 }, … , ScalarExpr1N { as aliasN }  { using using_list }");
                sb.AppendLine();
                sb.Append("{ filter condition }");
                sb.AppendLine();
                sb.Append("{ apply expr } ");
                sb.AppendLine();
                sb.Append("{ calc {role} comp := calc_comp {, comp := calc_comp}* }");
                sb.AppendLine();
                sb.Append("{ keep comp_list }");
                sb.AppendLine();
                sb.Append("{ drop comp_list }");
                sb.AppendLine();
                sb.Append("{ rename comp_from to comp_to {, comp_from to comp_to}* }");
                sb.AppendLine();
                sb.Append("))");
                this.Syntax= sb.ToString();
                this.Keyword = "inner_join|filter|apply|keep|drop|rename| to| calc ";
                this.opGroup = VTLGroup.Relational;
                this.Name = "inner join";
            }
            public void Evaluate()
            {

            }
        }
        public class left_join : Operator
        {
            public left_join()
            {
                var sb = new StringBuilder();

                sb.Append("left_join((  ScalarExpr1 { as alias1 }, … , ScalarExpr1N { as aliasN }  { using using_list }");
                sb.AppendLine();
                sb.Append("{ filter condition }");
                sb.AppendLine();
                sb.Append("{ apply expr } ");
                sb.AppendLine();
                sb.Append("{ calc {role} comp := calc_comp {, comp := calc_comp}* }");
                sb.AppendLine();
                sb.Append("{ keep comp_list }");
                sb.AppendLine();
                sb.Append("{ drop comp_list }");
                sb.AppendLine();
                sb.Append("{ rename comp_from to comp_to {, comp_from to comp_to}* }");
                sb.AppendLine();
                sb.Append("))");
                this.Syntax = sb.ToString();
                this.Keyword = "left_join|filter|apply|keep|drop|rename| to| calc ";
                this.opGroup = VTLGroup.Relational;
                this.Name = "left join";
            }
            public void Evaluate()
            {

            }
        }
        public class full_join : Operator
        {
            public full_join()
            {
                var sb = new StringBuilder();

                sb.Append("full_join((  ScalarExpr1 { as alias1 }, … , ScalarExpr1N { as aliasN }  { using using_list }");
                sb.AppendLine();
                sb.Append("{ filter condition }");
                sb.AppendLine();
                sb.Append("{ apply expr } ");
                sb.AppendLine();
                sb.Append("{ calc {role} comp := calc_comp {, comp := calc_comp}* }");
                sb.AppendLine();
                sb.Append("{ keep comp_list }");
                sb.AppendLine();
                sb.Append("{ drop comp_list }");
                sb.AppendLine();
                sb.Append("{ rename comp_from to comp_to {, comp_from to comp_to}* }");
                sb.AppendLine();
                sb.Append("))");
                this.Syntax = sb.ToString();
                this.Keyword = "full_join|filter|apply|keep|drop|rename| to| calc ";
                this.opGroup = VTLGroup.Relational;
                this.Name = "full join";
            }
            public void Evaluate()
            {

            }
        }
        public class cross_join : Operator
        {
            public cross_join()
            {
                var sb = new StringBuilder();

                sb.Append("cross_join((  ScalarExpr1 { as alias1 }, … , ScalarExpr1N { as aliasN }  { using using_list }");
                sb.AppendLine();
                sb.Append("{ filter condition }");
                sb.AppendLine();
                sb.Append("{ apply expr } ");
                sb.AppendLine();
                sb.Append("{ calc {role} comp := calc_comp {, comp := calc_comp}* }");
                sb.AppendLine();
                sb.Append("{ keep comp_list }");
                sb.AppendLine();
                sb.Append("{ drop comp_list }");
                sb.AppendLine();
                sb.Append("{ rename comp_from to comp_to {, comp_from to comp_to}* }");
                sb.AppendLine();
                sb.Append("))");
                this.Syntax = sb.ToString();
                this.Keyword = "cross_join|filter|apply|keep|drop|rename| to| calc ";
                this.opGroup = VTLGroup.Relational;
                this.Name = "cross join";
            }
            public void Evaluate()
            {

            }
        }
        public class datapoint_check : Operator
        {

            public datapoint_check()
            {
                var sb = new StringBuilder();
                sb.Append("check (ScalarExpr , dpr");
                sb.AppendLine();
                sb.Append("           {, not valid | valid | all }");
                sb.AppendLine();
                sb.Append("           { , condition |  measures }");
                sb.AppendLine();
                sb.Append("           )");
                this.Syntax = sb.ToString(); this.opGroup = VTLGroup.Validation;
                this.Name = "check (with datapoint ruleset)";
                this.Keyword = "not valid|valid|all|condition|measures";
            }
            public void Evaluate()
            {

            }
        }
        public class hierarchical_check : Operator
        {

            public hierarchical_check()
            {
                var sb = new StringBuilder();
                sb.Append("check ( ScalarExpr , hr");
                sb.AppendLine();
                sb.Append("           { , threshold ( threshold ) }");
                sb.AppendLine();
                sb.Append("           {, not valid | valid | all }");
                sb.AppendLine();
                sb.Append("           {, condition |  measures }");
                sb.AppendLine();
                sb.Append("           )");
                this.Syntax = sb.ToString(); this.opGroup = VTLGroup.Validation;
                this.Name = "check (with hierarchical ruleset)";
                this.Keyword = "threshold|not valid|valid|all|condition|measures";
            }
            public void Evaluate()
            {

            }
        }
        public class single_check : Operator
        {

            public single_check()
            {
                var sb = new StringBuilder();
                sb.Append("check ( ScalarExpr , hr");
                sb.AppendLine();
                sb.Append("           { , threshold ( threshold ) }");
                sb.AppendLine();
                sb.Append("           {, not valid | valid | all }");
                sb.AppendLine();
                sb.Append("           {, condition |  measures }");
                sb.AppendLine();
                sb.Append("           {, imbalance ( imb )}");
                sb.AppendLine();
                sb.Append("           {, errorcode ( error_code) }");
                sb.AppendLine();
                sb.Append("           {, errorlevel ( error_level )");
                sb.AppendLine();
                sb.Append("           )");
                this.Syntax = sb.ToString(); this.opGroup = VTLGroup.Validation;
                this.Name = "single check";
                this.Keyword = "threshold|not valid|valid|all|condition|measures|errorcode|errorlevel|imbalance";
            }
            public void Evaluate()
            {

            }
        }
        public class check_value_domain : Operator
        {

            public check_value_domain()
            {
                this.Name = "check value domain subset";
                this.Syntax = "check_value_domain_subset ( ScalarExpr,  [ components | { compList ({compIndent}+), valueDomain }], vds)";  
                this.opGroup = VTLGroup.Validation;
                this.Keyword = "check_value_domain_subset";
            }
            public void Evaluate()
            {

            }
        }
        public class fill_time_series : Operator
        {

            public fill_time_series()
            {
                this.Name = "fill time series";
                this.Syntax = "fill_time_series( ScalarExpr, freq, { , timePeriodName  { , timeFormat } )"; 
                this.opGroup = VTLGroup.Time_Series;
                this.Keyword = "fill_time_series";
            }
            public void Evaluate()
            {

            }
        }
        public class flow_to_stock : Operator
        {

            public flow_to_stock()
            {
                this.Name = "flow to stock";
                this.Syntax = "flow_to_stock( ScalarExpr )"; 
                this.opGroup = VTLGroup.Time_Series;
                this.Keyword = "flow_to_stock";
            }
            public void Evaluate()
            {

            }
        }
        public class stock_to_flow : Operator
        {

            public stock_to_flow()
            {
                this.Name = "stock to flow";
                this.Syntax = "stock_to_flow( ScalarExpr )"; 
                this.opGroup = VTLGroup.Time_Series;
                this.Keyword = "stock_to_flow";
            }
            public void Evaluate()
            {

            }
        }
        public class timeshift : Operator
        {

            public timeshift()
            {
                this.Name = "timeshift";
                this.Syntax = "timeshift( ScalarExpr , timeId, unit = [A|M|Q|D], lag )";
                this.opGroup = VTLGroup.Time_Series;
                this.Keyword = "timeshift|A|M|Q|D";
            }
            public void Evaluate()
            {

            }
        }
        public class nvl : Operator
        {

            public nvl()
            {
                this.Name = "null value";
                this.Syntax = "nval( LeftScalarExpr , RigthScalarExpr)";
                this.opGroup = VTLGroup.Time_Series;
                this.Keyword = "nval";
            }
            public void Evaluate()
            {

            }
        }
    }    
}