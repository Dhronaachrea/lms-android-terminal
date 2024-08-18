package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.crashreport.GMailSender;

//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.crashreport.GMailSender;

/**
 * Created by stpl on 05-Nov-16.
 */
public class MainAppCrashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_splash);
        if (getIntent() != null && getIntent().hasExtra("error") && TpsGamesClass.getInstance().isNetworkAvailable(this)) {
            setCrashReport(getIntent().getStringExtra("error"));
        }


        Intent intent = new Intent(this, ActivityCrashReporter.class);
        startActivity(intent);
    }

    protected void setCrashReport(final String error) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                GMailSender.getInstance().authUser("vivekkumar57@gmail.com", "myparents1243", error);

                try {
                    GMailSender.getInstance().sendMail("TPS crash report by vivek kumar singh", error, "vivekkumar57@gmail.com", "vivek.singh@skilrock.com");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
