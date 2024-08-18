package demo.stpl.com.tpsmergedbuild;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;

import com.crittercism.app.Crittercism;
import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.execution.CommandCapture;

import demo.stpl.com.tpsmergedbuild.baseClass.RespondOverException;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.screenoffreciever.OnScreenOffReceiver;

//importdemo.stpl.com.tpsmergedbuild.baseClass.RespondOverException;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.screenoffreciever.OnScreenOffReceiver;

/**
 * Created by stpl on 9/8/2016.
 */
public class TpsApplicationClass extends Application {

    private PowerManager.WakeLock wakeLock;
    private OnScreenOffReceiver onScreenOffReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        //302686f984ca1a11622153a156204fd42ffc0674

        Crittercism.initialize(this, "4d416dc26b294e8993f57b8f1c63d1b500555300");
        Thread.setDefaultUncaughtExceptionHandler(new RespondOverException(this.getApplicationContext()));
        TpsGamesClass.getInstance(this);
        TpsGamesClass.getInstance().setResponsibleGamingOn(true);
        TpsGamesClass.getInstance().setDeviceName(Build.MODEL);
        registerKioskModeScreenOffReceiver();
        hideSystemBar();

    }

    // waking the phone up
    public PowerManager.WakeLock getWakeLock() {
        if (wakeLock == null) {
            // lazy loading: first call, create wakeLock via PowerManager.
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "wakeup");
        }
        return wakeLock;
    }


    //registering the kiosk mode so that screen will not sleep
    private void registerKioskModeScreenOffReceiver() {
        // register screen off receiver
        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        onScreenOffReceiver = new OnScreenOffReceiver();
        registerReceiver(onScreenOffReceiver, filter);
    }

    // hide notification and bottom bar from tps
    private void hideSystemBar() {
        try {
            //REQUIRES ROOT
            Build.VERSION_CODES vc = new Build.VERSION_CODES();
            Build.VERSION vr = new Build.VERSION();
            String ProcID = "79"; //HONEYCOMB AND OLDER

            //v.RELEASE  //4.0.3
            if (vr.SDK_INT >= vc.ICE_CREAM_SANDWICH) {
                ProcID = "42"; //ICS AND NEWER
            }

            if(TpsGamesClass.getInstance().getDeviceName().contains("550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx053") || TpsGamesClass.getInstance().getDeviceName().contains("390") ){
                String commandStr = "service call activity " +
                    ProcID + " s16 com.android.systemui";
            runAsRoot(commandStr);
            }else{
                String commandStr = "pm enable com.android.systemui";
                runAsRoot(commandStr);
            }
//            String commandStr = "service call activity " +
//                    ProcID + " s16 com.android.systemui";
//            runAsRoot(commandStr);

//            String commandStr = "pm disable com.android.systemui";
//            runAsRoot(commandStr);



//            String tr = "am startservice -n com.android.systemui/.SystemUIService";
//            runAsRoot(tr);

//            String s = "su -c service call activity " + ProcID + " s16 com.android.systemui";
//            runAsRoot(s);


        } catch (Exception e) {
            e.getLocalizedMessage();
            // something went wrong, deal with it here
        }
    }

    private void runAsRoot(String commandStr) {
        try {
            CommandCapture command = new CommandCapture(0, commandStr);
            RootTools.getShell(true).add(command).wait();
        } catch (Exception e) {
            // something went wrong, deal with it here
            e.getMessage();
        }
    }
}
