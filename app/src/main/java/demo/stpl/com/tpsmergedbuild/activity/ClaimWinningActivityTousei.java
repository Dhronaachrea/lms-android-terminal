package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.pt.scan.Scan;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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

public class ClaimWinningActivityTousei extends Activity implements View.OnClickListener, ResponseLis {

    FrameLayout add_view_frame;
    CustomEditText edit_view1;
    TextView btn_positive1, btn_negative1, textMessage;
    Scan barcodescan;
    public String scanResult;
    Integer pos;
    String pos1;
    Bitmap bitmap1;
    Bundle bundle;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setFinishOnTouchOutside(false);
        setContentView(R.layout.layout_claim_winning_tousei);
        bundle = getIntent().getExtras();
        if (bundle != null)
            pos = bundle.getInt("selectedPosition");
        btn_negative1 = (TextView) findViewById(R.id.btn_negative1);
        btn_positive1 = (TextView) findViewById(R.id.btn_positive1);
        textMessage = (TextView) findViewById(R.id.textMessage);
        textMessage.setVisibility(View.VISIBLE);
        btn_positive1.setText("scan");
        btn_positive1.setOnClickListener(this);
        btn_negative1.setOnClickListener(this);
        edit_view1 = (CustomEditText) findViewById(R.id.edit_view1);
        add_view_frame = (FrameLayout) findViewById(R.id.add_view_frame1);
        // add_view_frame.setVisibility(View.VISIBLE);
        // add_view_frame.addView(new Preview(this));
        getWindow().setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.9), (int) (LinearLayout.LayoutParams.WRAP_CONTENT));

        Bitmap bitmap1 = BitmapFactory.decodeResource(ClaimWinningActivityTousei.this.getResources(), R.drawable.banner_tousei);
        TpsGamesClass.getInstance().displayImagebyAbsolutePath("Winning Claim..", bitmap1);

//        TpsGamesClass.getInstance().saveImage("banner1(1).png", this);
//        String url = "/sdcard/banner1(1).png";
        //  TpsGamesClass.getInstance().displayImagebyAbsolutePath("Winning Claim..", url);

        edit_view1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 0) {
                    btn_negative1.setText("Cancel");
                    btn_positive1.setText("scan");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private boolean isBarcodeScanOpen = false;
    String playerId;

    public void scanResults() {

        barcodescan = TpsGamesClass.getInstance().getBarcodeScan();
        isBarcodeScanOpen = true;

//        if(scan < 0){
//            TpsGamesClass.getInstance().showAToast("Open fail", ClaimWinningActivity.this, Toast.LENGTH_LONG);
//            return;
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                String string = barcodescan.scan(0);
                playerId = string.replaceAll("[^0-9]", "");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (playerId != null) {
                            setTextValue(playerId);

                        }
                    }
                });


            }
        }).start();


    }

    public void setTextValue(String textValue) {
        Log.i("barcode", textValue);
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
            jsonObject.put("ticketNumber", ticketNumber + "0");
            jsonObject.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            jsonObject.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/pwtMgmt/verifyTicket.action?json=" + jsonObject.toString();
        HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ClaimWinningActivityTousei.this, "checkWinning", ClaimWinningActivityTousei.this, "winning");
        TpsGamesClass.getInstance().startLoader(ClaimWinningActivityTousei.this);
        httpRequest.executeTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   TpsGamesClass.getInstance().setCameraOpen(false);
        if (isBarcodeScanOpen)
            barcodescan.close();
        if (bundle != null && !flag)
            TpsGamesClass.getInstance().changeBackScreen(pos);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_negative1:
                if (btn_negative1.getText().toString().trim().equals("Clear")) {
                    edit_view1.setText("");
                    btn_negative1.setText("Cancel");
                    btn_positive1.setText("scan");
                } else {

                    finish();
                }
                break;
            case R.id.btn_positive1:
                if (btn_positive1.getText().toString().equalsIgnoreCase("scan")) {
                    btn_positive1.setText("Wait");
                    scanResults();
                    return;
                }
                if (btn_positive1.getText().toString().equalsIgnoreCase("Wait")) {

                    return;
                }


                if (edit_view1.getText().toString().trim().length() > 0)
                    checkWinning();
                break;
        }
    }

    protected void moveToLogin() {
        Intent intent = new Intent(ClaimWinningActivityTousei.this, LoginActivityTpsGame.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onResponse(String response, String requestedMethod) {
        TpsGamesClass.getInstance().stopLoader();
        if (response.contains("<HTML>")) {
            TpsGamesClass.getInstance().showAToast("Connect to internet", ClaimWinningActivityTousei.this, Toast.LENGTH_SHORT);
            moveToLogin();
            finish();
            return;
        }
//        {"isSuccess":false,"errorCode":1008,"errorMsg":"Invalid Ticket"}
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.has("isSuccess")) {
                if (jsonObject.optBoolean("isSuccess") == false) {
                    flag = true;
                    Bitmap bitmap1 = BitmapFactory.decodeResource(ClaimWinningActivityTousei.this.getResources(), R.drawable.banner_tousei);
                    TpsGamesClass.getInstance().displayImagebyAbsolutePath(jsonObject.optString("errorMsg"), bitmap1);
                    Intent intent = new Intent();
                    intent.putExtra("message", jsonObject.optString("errorMsg"));
                    intent.putExtra("showDialog", "open");
                    setResult(055, intent);

                    finish();
//                    TpsGamesClass.getInstance().showAToast(jsonObject.optString("errorMsg"), ClaimWinningActivity.this, Toast.LENGTH_SHORT);
                } else if (jsonObject != null && jsonObject.optBoolean("isSuccess") == true) {
                    flag = true;
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
