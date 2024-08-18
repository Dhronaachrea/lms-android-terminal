package demo.stpl.com.tpsmergedbuild.screenoffreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

import demo.stpl.com.tpsmergedbuild.TpsApplicationClass;
import demo.stpl.com.tpsmergedbuild.activity.ActivityLockScreen;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

//import tpsgames.TpsApplicationClass;
//import tpsgames.activity.ActivityLockScreen;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;


/**
 * Created by stpl on 11/23/2015.
 * class for checking the screen behaviour of the application
 */
public class OnScreenOffReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        //receive the intent when the screen gets off
        TpsApplicationClass appContextApplication = (TpsApplicationClass) context.getApplicationContext();
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()) && !TpsGamesClass.getInstance().getIsActivityLockActivated()) {
            wakeUpDevice(appContextApplication);
            Intent i = new Intent(context, ActivityLockScreen.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            wakeUpDevice(appContextApplication);
            TpsGamesClass.getInstance().finishActivityLockScreen();
        }
    }

    // waking the device up if the screen is off automatically or by the user
    private void wakeUpDevice(TpsApplicationClass context) {
        PowerManager.WakeLock wakeLock = context.getWakeLock(); // get WakeLock reference via AppContext
        if (wakeLock.isHeld()) {
            wakeLock.release(); // release old wake lock
        }

        // create a new wake lock...
        wakeLock.acquire();

        // ... and release again
        wakeLock.release();
    }
}
