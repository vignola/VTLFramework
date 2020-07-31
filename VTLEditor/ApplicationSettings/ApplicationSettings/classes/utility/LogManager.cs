using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Summary description for LogManager
/// </summary>
namespace ApplicationSettings.classes.utility 
{ 
public class LogManager
{
    private string _filePath;

    public LogManager(string path)
    {
        try
        {
            _filePath = path;
        }
        catch (Exception ex) 
        {
            throw new Exception(ex.Message);
        }
    }

    public void WriteLogMessage(string message) 
    {
        try
        {
            using (System.IO.StreamWriter LogFile =
                new System.IO.StreamWriter(_filePath, true)) 
            {
                LogFile.WriteLine("[" + DateTime.Now + "] " + message);
            }
        }
        catch (Exception ex) 
        {
            throw new Exception(ex.Message);
        }
    }
    
}
}