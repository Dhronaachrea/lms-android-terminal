package demo.stpl.com.tpsmergedbuild.betslip;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.opencv.android.OpenCVLoader;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.utils.CustomEditText;

//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import demo.stpl.com.tpsmergedbuild.utils.CustomEditText;

//import com.tablet.stpl.comman.interfaces.ServerCommClass;

/**
 * Created by stpl on 9/21/2016.
 */
public class ActivityBetSlipCamera extends Activity{

    private final static String TAG = "BetSlipReader";

    static {
        if (!OpenCVLoader.initDebug())
            Log.e(TAG, "Failed to load OpenCV!");
    }

    static {
        System.loadLibrary("iconv");
    }

    FrameLayout preLayout;
    CustomEditText edit_view1;
    LinearLayout btns1;
    TextView dialogHeaderText1;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_betslip);

        TpsGamesClass.getInstance().setActivityBetSlipCamera(this);
        final PreviewNew preview = new PreviewNew(ActivityBetSlipCamera.this);
        preLayout = (FrameLayout) findViewById(R.id.add_view_frame1);
        dialogHeaderText1 = (TextView) findViewById(R.id.dialogHeaderText1);
        dialogHeaderText1.setText("Scan BetSlip");
        edit_view1 = (CustomEditText) findViewById(R.id.edit_view1);
        btns1 = (LinearLayout) findViewById(R.id.btns1);
        btns1.setVisibility(View.GONE);
        edit_view1.setVisibility(View.GONE);
        preLayout.setVisibility(View.VISIBLE);
        preLayout.addView(preview);
        preLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview.isbetslipready = true;
            }
        });
        if(TpsGamesClass.getInstance().getDeviceName().contains("TPS580")){
            TpsGamesClass.getInstance().closeScreen(this);

            TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
            String url = "/sdcard/rainbow_header.png";
            TpsGamesClass.getInstance().pleaseWait("Scanning BetSlip..", url);
            TpsGamesClass.getInstance().displayScreen(this);
        }
//        preLayout.setOnTouchListener();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        TpsGamesClass.getInstance().closeScreen(this);
        TpsGamesClass.getInstance().setCameraOpen(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TpsGamesClass.getInstance().closeScreen(this);
    }

    public void showdialog()
    {
        pd = new ProgressDialog(this);
        pd.setMessage("");
        pd.show();
    }
}
