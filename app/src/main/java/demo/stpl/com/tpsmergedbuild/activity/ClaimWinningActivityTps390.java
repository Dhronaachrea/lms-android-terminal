package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;
import demo.stpl.com.tpsmergedbuild.utils.CustomEditText;

//import skilrock.com.tpsgame.R;
//import tpsgames.HttpRequest;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.interfaces.ResponseLis;
//import demo.stpl.com.tpsmergedbuild.utils.CustomEditText;

//import tpsgames.DecodeTps390;

/**
 * Created by stpl on 9/21/2016.
 */
public class ClaimWinningActivityTps390 extends Activity implements View.OnClickListener, ResponseLis {

    FrameLayout add_view_frame;
    CustomEditText edit_view1;
    TextView btn_positive1, btn_negative1;
    DecodeTps390 decodeTps390;
    boolean isScannningOn=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setFinishOnTouchOutside(false);
        setContentView(R.layout.layout_claim_winning_tps390);
        getWindow().setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.9), (int) (getResources().getDisplayMetrics().widthPixels * 0.5));
        btn_negative1 = (TextView) findViewById(R.id.btn_negative1);
        btn_positive1 = (TextView) findViewById(R.id.btn_positive1);
        btn_positive1.setOnClickListener(this);
        btn_negative1.setOnClickListener(this);
        edit_view1 = (CustomEditText) findViewById(R.id.edit_view1);
        TpsGamesClass.getInstance().closeScreen(this);

        TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
        String url = "/sdcard/rainbow_header.png";
        TpsGamesClass.getInstance().pleaseWait("Winning Claim..", url);
        TpsGamesClass.getInstance().displayScreen(this);


        edit_view1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 0) {
                    btn_negative1.setText("Cancel");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void removeView() {
        add_view_frame.removeAllViews();
    }

    public void setBtn_positive1(String value) {
        btn_positive1.setText(value);
    }

    public void setTextValue(String textValue) {
        edit_view1.setText(textValue);
        btn_negative1.setText("Clear");
        btn_positive1.setText("Ok");

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
            jsonObject.put("ticketNumber", ticketNumber+"0");
            jsonObject.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            jsonObject.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/pwtMgmt/verifyTicket.action?json=" + jsonObject.toString();
        HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ClaimWinningActivityTps390.this, "checkWinning", ClaimWinningActivityTps390.this, "winning");
        TpsGamesClass.getInstance().startLoader(ClaimWinningActivityTps390.this);
        httpRequest.executeTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TpsGamesClass.getInstance().setCameraOpen(false);
        if(decodeTps390 !=null)
        {
            Log.d("decodeTps390","not null");
            if(!decodeTps390.isInterrupted())
                Log.d("interruped","not");
            isScannningOn=false;
        }


        if(decodeTps390 !=null&&!decodeTps390.isInterrupted())
            decodeTps390.interrupt();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_negative1:
                if (btn_negative1.getText().toString().trim().equals("Clear")) {
                    edit_view1.setText("");
                    btn_negative1.setText("Cancel");
                    btn_positive1.setText("Scan ticket");
                    isScannningOn=false;
                } else {

                    finish();
                }
                break;
            case R.id.btn_positive1:
                if (edit_view1.getText().toString().trim().length() > 0)
                    checkWinning();
                else if (btn_positive1.getText().toString().equalsIgnoreCase("scan ticket") ){
                    {
                        if(!isScannningOn)
                        {
                            decodeTps390 = new DecodeTps390(this);
                            decodeTps390.start();
                            isScannningOn=true;
                        }


                    }

                }

                break;
        }
    }

    protected void moveToLogin() {
        Intent intent = new Intent(ClaimWinningActivityTps390.this, LoginActivityTpsGame.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResponse(String response, String requestedMethod) {
        TpsGamesClass.getInstance().stopLoader();
        if (response.contains("<HTML>")) {
            TpsGamesClass.getInstance().showAToast("Connect to internet", ClaimWinningActivityTps390.this, Toast.LENGTH_SHORT);
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
//                    TpsGamesClass.getInstance().showAToast(jsonObject.optString("errorMsg"), ClaimWinningActivityTps390.this, Toast.LENGTH_SHORT);
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