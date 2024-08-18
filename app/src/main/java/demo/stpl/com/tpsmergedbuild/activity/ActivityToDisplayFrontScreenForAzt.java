package demo.stpl.com.tpsmergedbuild.activity;

import a1088sdk.PrnDspA1088Activity;

/**
 * Created by stpl on 04-Nov-16.
 */
public class ActivityToDisplayFrontScreenForAzt extends PrnDspA1088Activity {

    public void printText(String text) {
        int nRet = DSP_Open("/dev/ttyS2", 2400, DISPLAY_LED);
        if (nRet < ZQ_SUCCESS) {
            return;
        }
        nRet = DSP_Clear();
        if (nRet < ZQ_SUCCESS) {
            return;
        }

        DSP_DisplayString(text);
        DSP_Close();
    }

}
