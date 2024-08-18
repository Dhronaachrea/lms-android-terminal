package demo.stpl.com.tpsmergedbuild.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

/**
 * Created by stpl on 24-Oct-16.
 */
public class ActivityLockScreen extends Activity {
    ImageView off_screen_image;
    int[] ids = {R.drawable.fivebynintybanner,
            R.drawable.fortune, R.drawable.mini_keno, R.drawable.miniroulette, R.drawable.soccer_six, R.drawable.soccer_thirteen, R.drawable.super_keno_banner};

    int totalCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.layout_screen_off);
        TpsGamesClass.getInstance().setActivityLockScreen(this);
        off_screen_image = (ImageView) findViewById(R.id.off_screen_image);
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);

                        runOnUiThread(new Runnable() {
                            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void run() {
                                if (off_screen_image != null) {
                                    if (totalCount == 6) {
                                        totalCount = 0;
                                    }
                                    off_screen_image.setBackground(ActivityLockScreen.this.getResources().getDrawable(ids[totalCount]));
                                    totalCount++;
                                }

                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        off_screen_image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TpsGamesClass.getInstance().setIsActivityLockActivated(false);
    }
}
