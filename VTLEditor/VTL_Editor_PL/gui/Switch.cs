using System;
using System.Windows.Forms;

namespace VTL_Editor_PL.gui
{
    public partial class Switch : UserControl
    {

    #region Raise Event 

        public class SwitchEventArgs : EventArgs
        {
            private bool _switching;

            public SwitchEventArgs(bool switchingStatus) 
            {
                _switching = switchingStatus;

            }

            public bool isCodeActive
            {
                get { return _switching; }
            }

            public bool isDescriptioActive
            {
                get { return !_switching; }
            }

        }

        public delegate void SwitchEventHandler(object sender, SwitchEventArgs e);

    #endregion

        private bool _status = true;

        public event SwitchEventHandler SwitchingPhase;

        public bool isCode() 
        {
            return _status;
        }

        public Switch()
        {
            InitializeComponent();
        }

        private void btnSwitch_Click(object sender, EventArgs e)
        {
            if (_status) 
            {
                lblCode.Enabled = false;
                lblDescr.Enabled = true;
                btnSwitch.Image = imageList1.Images[1];
                _status = false;
            } 
            else 
            {
                lblCode.Enabled = true;
                lblDescr.Enabled = false;
                btnSwitch.Image = imageList1.Images[0];
                _status = true;
            }

            SwitchingPhase(this, new SwitchEventArgs(_status));
        }

        private void Switch_Load(object sender, EventArgs e)
        {
            btnSwitch.Image = imageList1.Images[0];
        }
    }
}
