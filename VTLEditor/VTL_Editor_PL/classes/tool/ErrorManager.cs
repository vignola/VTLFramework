using System;
using System.IO;
using System.Windows.Forms;
using VTL_Editor_PL.classes.common;
using VTL_Editor_PL.classes.gui;

namespace VTL_Editor_PL.classes.tool
{
    class ErrorAndLogManager
    {
        private string _LogPath;

        public string LogPath {
            get 
            {
                return _LogPath;
            }            
            set
            {
                _LogPath = value + "\\LogFile.log";
            }
        }

        public ErrorAndLogManager() 
        {
            _LogPath = null;
        }

        public ErrorAndLogManager(String LogFolder) 
        { 
            _LogPath = LogFolder + "\\LogFile.log";
        }
        
        public void ErrorManagement(Exception ex, bool drastic, System.Windows.Forms.Form frm)
        {            
            //MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            frmErrorDetails frmError = new frmErrorDetails();            
            frmError.ErrorMessage = ex.Message;
            if (frm != null) frmError.ErrorMessage += " Module " + frm.Name + "[" + ex.StackTrace + "]";
            frmError.ShowDialog();

            if (CommonItem.CurrentSettings.LogActive && CommonItem.CurrentSettings.LogPath!=String.Empty) WriteLog(ex.Message);

            //Set cursor to default without exception (multithreads compatibility)
            try
            {
                frm.Invoke(new MethodInvoker(delegate
                {
                    frm.Cursor = System.Windows.Forms.Cursors.Default;                    
                }));
            } 
            catch (Exception) 
            {              
            }

            //frm.Cursor = System.Windows.Forms.Cursors.Default;

            if (drastic)
            {
                frm.Close();
            }
        }       

        public void WriteLog(String message) 
        {
            StreamWriter s = null;
            try
            {
                if (!File.Exists(_LogPath))
                    s = new StreamWriter(_LogPath);
                else
                    s = File.AppendText(_LogPath);

                s.WriteLine(message + "[" + DateTime.Now + "]");
                
            }
            catch (Exception ex) 
            {
                throw new Exception("Error, [Common.ErrorManager.WriteLog]  " + ex.Message);
            }
            finally
            {
               if(s!=null) s.Close();
            }
            
        }
    }
}
