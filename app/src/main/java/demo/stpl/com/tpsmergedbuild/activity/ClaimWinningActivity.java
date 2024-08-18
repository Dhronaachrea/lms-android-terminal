package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.Preview;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;
import demo.stpl.com.tpsmergedbuild.utils.CustomEditText;

//import skilrock.com.tpsgame.R;
//import tpsgames.HttpRequest;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.Preview;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.interfaces.ResponseLis;
//import demo.stpl.com.tpsmergedbuild.utils.CustomEditText;

/**
 * Created by stpl on 9/21/2016.
 */
public class ClaimWinningActivity extends Activity implements View.OnClickListener, ResponseLis {

    FrameLayout add_view_frame;
    CustomEditText edit_view1;
    TextView btn_positive1, btn_negative1;
    Preview preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setFinishOnTouchOutside(false);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_claim_winning);
        btn_negative1 = (TextView) findViewById(R.id.btn_negative1);
        btn_positive1 = (TextView) findViewById(R.id.btn_positive1);
        btn_positive1.setOnClickListener(this);
        btn_negative1.setOnClickListener(this);
        edit_view1 = (CustomEditText) findViewById(R.id.edit_view1);
        add_view_frame = (FrameLayout) findViewById(R.id.add_view_frame1);
        if (TpsGamesClass.getInstance().getIfHasCamera()) {
            add_view_frame.setVisibility(View.VISIBLE);
            preview = new Preview(this);
            add_view_frame.addView(preview);
        }


        if(TpsGamesClass.getInstance().getDeviceName().contains("TPS580")){
            TpsGamesClass.getInstance().closeScreen(this);

            TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
            String url = "/sdcard/rainbow_header.png";
            TpsGamesClass.getInstance().pleaseWait("Winning Claim..", url);
            TpsGamesClass.getInstance().displayScreen(this);
        }


        edit_view1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 0) {
                    btn_negative1.setText("Cancel");
                }

                if (edit_view1.getText().toString().trim().length() >= 17) {
                    btn_positive1.performClick();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edit_view1.requestFocus();
    }

    public void removeView() {
        add_view_frame.removeAllViews();
    }

    public void setTextValue(String textValue) {
        edit_view1.setText(textValue);
        btn_negative1.setText("Clear");
    }

    private void checkWinning() {
        JSONObject jsonObject = new JSONObject();
        try {
            String ticketNumber = "";
            if (edit_view1.getText().toString().length() > 17) {
                ticketNumber = edit_view1.getText().toString().substring(0, 17);
            } else {
                ticketNumber = edit_view1.getText().toString();
            }
            jsonObject.put("ticketNumber", ticketNumber + "0");
            jsonObject.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            jsonObject.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/pwtMgmt/verifyTicket.action?json=" + jsonObject.toString();
        HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ClaimWinningActivity.this, "checkWinning", ClaimWinningActivity.this, "winning");
        TpsGamesClass.getInstance().startLoader(ClaimWinningActivity.this);
        httpRequest.executeTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TpsGamesClass.getInstance().setCameraOpen(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_negative1:
                if (btn_negative1.getText().toString().trim().equals("Clear")) {
                    edit_view1.setText("");
                    btn_negative1.setText("Cancel");
                } else {
                    finish();
                }
                break;
            case R.id.btn_positive1:
                if (edit_view1.getText().toString().trim().length() > 0)
                    checkWinning();
                break;
        }
    }

    protected void moveToLogin() {
        Intent intent = new Intent(ClaimWinningActivity.this, LoginActivityTpsGame.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResponse(String response, String requestedMethod) {
        TpsGamesClass.getInstance().stopLoader();
        if (response.contains("<HTML>")) {
            TpsGamesClass.getInstance().showAToast("Connect to internet", ClaimWinningActivity.this, Toast.LENGTH_SHORT);
            moveToLogin();
            finish();
            return;
        }
        if (response.contains("<html>") || response.contains("<!DOCTYPE HTML PUBLIC")) {
            TpsGamesClass.getInstance().showAToast("Server not sending correct data", ClaimWinningActivity.this, Toast.LENGTH_SHORT);
            moveToLogin();
            finish();
            return;
        }
//        {"isSuccess":false,"errorCode":1008,"errorMsg":"Invalid Ticket"}
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.has("isSuccess")) {
                if (jsonObject.optBoolean("isSuccess") == false) {
                    TpsGamesClass.getInstance().closeScreen(this);

                    TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
                    String url = "/sdcard/rainbow_header.png";
                    TpsGamesClass.getInstance().pleaseWait(jsonObject.optString("errorMsg"), url);
                    TpsGamesClass.getInstance().displayScreen(this);

                    Intent intent = new Intent();
                    intent.putExtra("message", jsonObject.optString("errorMsg"));
                    intent.putExtra("showDialog", "open");
                    setResult(055, intent);

                    finish();
//                    TpsGamesClass.getInstance().showAToast(jsonObject.optString("errorMsg"), ClaimWinningActivity.this, Toast.LENGTH_SHORT);
                } else if (jsonObject != null && jsonObject.optBoolean("isSuccess") == true) {
                    Intent intent = new Intent();
                    intent.putExtra("printResponse", jsonObject.toString());
                    setResult(055, intent);

                    finish();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        TpsGamesClass.getInstance().closeScreen(this);
    }
}
