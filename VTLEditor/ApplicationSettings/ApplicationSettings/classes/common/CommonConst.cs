
namespace ApplicationSettings.classes.common
{
    public class CommonConst
    {
        public enum OutputFormatType
        {
            FORMAT_EXCEL_CSV = 0,
            FORMAT_EXCEL_TIME_SERIES = 1,
            FORMAT_EXCEL_CUSTOMIZED = 2,
            FORMAT_FILE_SDMX_GENERIC = 3,
            FORMAT_FILE_SDMX_COMPACT = 4,
            FORMAT_FILE_SDMX_CROSSSECTIONAL = 5,
            FORMAT_FILE_CSV = 6,
            FORMAT_FILE_SDMX = 7,
            FORMAT_EXCEL_TIME_SERIES_ONESHEET=8
        }

        public enum ActionType
        {
            ACTION_EXECUTE = 0,
            ACTION_OPEN = 1,
            ACTION_CANCELED = 2,
            ACTION_ERROR =-1
        }

        public enum ParentForm 
        {
            FORM_MAIN = 0,
            FORM_START = 1
        }        

        public enum ParentAttribLevel
        {
            Dataset = 0, Group, Series, Obs
        };

        public enum Loading_Status
        {
            LOADED = 0,
            GENERIC_ERROR = 1,
            CONNECTION_ERROR = 2,
            URL_ERROR = 3,
            WEBSERVICE_NOT_PRESENTS = 4,
            OBSOLETE_VERSION = 5,
            DB_CONNECTION_ERROR=6,
            NOT_FOUND=7,
            DB_CONNECTIONS_NOT_PRESENTS=8
        };

    }
}
