package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.TpsSplashActivity;

//import skilrock.com.tpsgame.R;
//import tpsgames.TpsSplashActivity;

/**
 * Created by stpl on 05-Nov-16.
 */
public class ActivityCrashReporter extends Activity{
    TextView dialogHeaderText,dialogTextContent,btn_positive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setFinishOnTouchOutside(false);
        setContentView(R.layout.common_custom_dialog_app);
        dialogHeaderText = (TextView) findViewById(R.id.dialogHeaderText);
        dialogTextContent = (TextView) findViewById(R.id.dialogTextContent);
        btn_positive = (TextView) findViewById(R.id.btn_positive);

        dialogHeaderText.setText("Error");
        dialogTextContent.setText("Server Error!");
        btn_positive.setText("Ok");
        btn_positive.setVisibility(View.VISIBLE);
        btn_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLogin();
            }
        });
    }

    protected void moveToLogin() {
        Intent intent = new Intent(this, TpsSplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
