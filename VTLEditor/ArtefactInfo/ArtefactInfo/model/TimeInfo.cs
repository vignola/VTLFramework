using System;

namespace ArtefactInfo.model
{
    public class TimeInfo
    {
        public struct TimeParsingResult
        {
            public int FromPeriod;
            public int ToPeriod;
            public int MaxPeriod;    
            public int FromYear;
            public int ToYear;     
        };

        public static readonly string  DefaultFrequency = "A";

        private string _originalStartTime;
        private string _originalEndTime;

        private DateTime _startTime;
        private DateTime _endTime;
        private string _freq;

        private int _fromPeriod;
        private int _toPeriod;
        private int _maxPeriod;    
        private int _fromYear;
        private int _toYear;       
 
        private string _format;
        private string _freqName;
            
        private string _periodName;

        public DateTime StartTime
        {
            get { return _startTime; }
        }

        public DateTime EndTime
        {
            get { return _endTime; }
        }

        public string Frequency
        {
            get { return _freq; }
            set { _freq = value;  }
        }

        public TimeInfo(String StartTime, String EndTime, string FreqCode)
        {
            try
            {
                //Fixing mapping error
                if (StartTime== "9999") 
                    StartTime = "1950";
                if (EndTime=="0000")
                    EndTime = DateTime.Now.Year.ToString();

                //Annual
                _originalStartTime = StartTime;
                _originalEndTime = EndTime;
                _freq = FreqCode;

                //Check on the base of the string
                if (StartTime.IndexOf('Q') > -1) _freq = "Q";
                if (StartTime.IndexOf('M') > -1) _freq = "M";
                if (StartTime.IndexOf('B') > -1) _freq = "B";
                if (StartTime.IndexOf('T') > -1) _freq = "T";
                if (StartTime.IndexOf('W') > -1) _freq = "W";

                parsePeriods(StartTime, EndTime);

                if (_freq == "A" && StartTime.Length == 4)
                {
                    _startTime = DateTime.Parse(StartTime + "/01/01");
                    _endTime = DateTime.Parse(EndTime + "/01/01");
                }
                else
                {
                    if (StartTime.Length == 7)
                    { 
                        string toFormat=ReplacePeriods(StartTime,_freq).Replace ('-','/');                        
                        _startTime = DateTime.Parse(toFormat + "/01");
                        toFormat = ReplacePeriods(EndTime,_freq).Replace('-', '/');
                        _endTime = DateTime.Parse(toFormat + "/01");
                    }
                    else 
                    {
                        _startTime = DateTime.Parse(StartTime);
                        _endTime = DateTime.Parse(EndTime);
                    }
                }
            }
            catch (Exception ex) 
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.TimeInfo]  " + ex.Message);            
            }
        }
          

        private string ReplacePeriods(string datePeriod, string freq) 
        {
            try
            {
                switch (freq)
                {
                    case "Q":
                        datePeriod = datePeriod.Replace("Q", "0");
                        break;
                    case "B":
                        datePeriod = datePeriod.Replace("B", "0");
                        break;                
                    case "T":
                        datePeriod = datePeriod.Replace("T", "0");
                        break;
                    case "M":
                        if (datePeriod.Split('-')[1].Length<2)
                            datePeriod = datePeriod.Replace("M", "0");
                        else
                            datePeriod = datePeriod.Replace("M", "");
                        break;
                    case "W":
                        if (datePeriod.Split('-')[1].Length < 2)
                            datePeriod = datePeriod.Replace("Q", "0");
                        else
                            datePeriod = datePeriod.Replace("Q", "");
                        break;
                }

                return datePeriod;
            }
            catch (Exception ex) 
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.TimeInfo.ReplacePeriods]  " + ex.Message);            
            }
        }

        private static int validatePeriod(string period, int prefixLen)
        {
            try
            {
                int ret = 0;
                if (!int.TryParse(period.Substring(prefixLen), out ret))
                {
                    //throw new NSIClientException(String.Format("NSI WS time period codelist contains invalid period : '{0}'",period));
                    throw new Exception(String.Format("NSI WS time period codelist contains invalid period : '{0}'", period));
                }
                return ret;
            }
            catch (Exception ex)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.TimeInfo.validatePeriod]  " + ex.Message);
            }
        }

        /// <summary>
        /// Check if specified string that should contain year is valid and if it is valid return the year as an int.
        /// Else it throws an exception
        /// </summary>
        /// <param name="year">The year to check</param>
        /// <returns>The year as an int</returns>
        /// <exception cref="Estat.Nsi.Client.NSIClientException">Year is not valid</exception>
        private static int validateYear(string year)
        {
            try
            {
                int ret = 0;
                if (year.Length != 4 | !int.TryParse(year, out ret))
                {
                    //throw new NSIClientException(String.Format("NSI WS time period codelist contains invalid year: '{0}'",year));
                    throw new Exception(String.Format("NSI WS time period codelist contains invalid year: '{0}'", year));
                }
                return ret;
            }
            catch (Exception ex)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.TimeInfo.validateYear]  " + ex.Message);
            }
        }

        public TimeParsingResult GetParsingResult() 
        {
            try
            {
                TimeParsingResult tp= new TimeParsingResult();
                this.parsePeriods(_originalStartTime, _originalEndTime);
                tp.FromPeriod = _fromPeriod;
                tp.ToPeriod=_toPeriod;
                tp.MaxPeriod=_maxPeriod;    
                tp.FromYear=_fromYear;
                tp.ToYear=_toYear;
                return tp;
            }
            catch (Exception ex)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.TimeInfo.GetParsingResult]  " + ex.Message);
            }
        }

        /// <summary>
        /// parse the <see cref="minTimePeriod"/> and <see cref="maxTimePeriod"/> and populate the resit of the fields
        /// </summary>
        private void parsePeriods(string minTimePeriod, string maxTimePeriod)
        {
            try
                {
                string[] minArray = minTimePeriod.Split('-');
                string[] maxArray = maxTimePeriod.Split('-');
                string yearFrom = minArray[0];
                string yearTo = maxArray[0];
                string periodFrom;
                string periodTo;


                _fromYear = validateYear(yearFrom);
                _toYear = validateYear(yearTo);

                switch (_freq) 
                {
                    case "A":
                            //freqName= Resources.Messages.time_period_yearly;
                            _freqName = "Yearly";
                            _format = "{0}";
                            break;

                    case "M":                    
                            //_freqName = "Resources.Messages.time_period_monthly";
                            periodFrom=minArray[1];
                            periodTo=maxArray[1];
                            _fromPeriod = validatePeriod(periodFrom, 0);
                            _toPeriod = validatePeriod(periodTo, 0);
                            _maxPeriod = 12;
                            _format = "{0}-{1:00}";
                            _periodName = "Resources.Messages.time_period_single_monthly";
                        break;

                    case "Q":                     
                            //_freqName = "Resources.Messages.time_period_quarterly";
                            periodFrom=minArray[1];
                            periodTo=maxArray[1];
                            _fromPeriod = validatePeriod(periodFrom, 1);
                            _toPeriod = validatePeriod(periodTo, 1);
                            _maxPeriod = 4;
                            _format = "{0}-Q{1}";
                            _periodName = "Resources.Messages.time_period_single_quarterly";
                            break;

                    case "B":                    
                            //_freqName = "Resources.Messages.time_period_biannual";
                            periodFrom=minArray[1];
                            periodTo=maxArray[1];
                            _fromPeriod = validatePeriod(periodFrom, 1);
                            _toPeriod = validatePeriod(periodTo, 1);
                            _maxPeriod = 2;
                            _format = "{0}-B{1}";
                            _periodName = "Resources.Messages.time_period_single_biannual";
                            break;

                    case "T":                     
                            //_freqName = "Resources.Messages.time_period_triannual";
                            periodFrom=minArray[1];
                            periodTo=maxArray[1];
                            _fromPeriod = validatePeriod(periodFrom, 1);
                            _toPeriod = validatePeriod(periodTo, 1);
                            _maxPeriod = 3;
                            _format = "{0}-T{1}";
                            _periodName = "Resources.Messages.time_period_triannual";
                            break;

                    case "W":                    
                            //freqName = Resources.Messages.time_period_weekly;
                            periodFrom=minArray[1];
                            periodTo=maxArray[1];
                            _freqName = "Weekly";
                            _fromPeriod = validatePeriod(periodFrom, 1);
                            _toPeriod = validatePeriod(periodTo, 1);
                            _format = "{0}-W{1}";
                            _maxPeriod = 52;
                            _periodName = "Resources.Messages.time_period_single_weekly";
                        break;
                                 
                } // TODO support daily.

            }
            catch (Exception ex)
            {
                throw new Exception("[GetDataDLL.Model.ApplicationModel.TimeInfo.parsePeriods]  " + ex.Message);
            }

        }
    }
}
