package demo.stpl.com.tpsmergedbuild;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import demo.stpl.com.tpsmergedbuild.activity.LoginActivityTpsGame;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;
import demo.stpl.com.tpsmergedbuild.beans.LoginResponse;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;

//import tpsgames.activity.LoginActivityTpsGame;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.GameBean;
//import tpsgames.beans.LoginResponse;
//import tpsgames.interfaces.ResponseLis;

public class TpsSplashActivity extends Activity implements ResponseLis {



    String saleResponse = "{\n" +
            "\t\"isSuccess\": true,\n" +
            "\t\"errorMsg\": \"\",\n" +
            "\t\"mainData\": {\n" +
            "\t\t\"commonSaleData\": {\n" +
            "\t\t\t\"ticketNumber\": \"42983325317128003\",\n" +
            "\t\t\t\"gameName\": \"5/90 Spot Game\",\n" +
            "\t\t\t\"purchaseDate\": \"2016-09-09\",\n" +
            "\t\t\t\"purchaseTime\": \"12:19:32\",\n" +
            "\t\t\t\"purchaseAmt\": 50,\n" +
            "\t\t\t\"barcodeCount\": \"42983325317128003012\",\n" +
            "\t\t\t\"drawData\": [{\n" +
            "\t\t\t\t\"drawDate\": \"09-09-2016\",\n" +
            "\t\t\t\t\"drawName\": \"BONANZA\",\n" +
            "\t\t\t\t\"drawTime\": \"20:50:00\"\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"drawDate\": \"10-09-2016\",\n" +
            "\t\t\t\t\"drawName\": \"NATIONAL\",\n" +
            "\t\t\t\t\"drawTime\": \"20:50:00\"\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"drawDate\": \"12-09-2016\",\n" +
            "\t\t\t\t\"drawName\": \"MSP\",\n" +
            "\t\t\t\t\"drawTime\": \"20:50:00\"\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"drawDate\": \"13-09-2016\",\n" +
            "\t\t\t\t\"drawName\": \"TUESDAY LUCKY\",\n" +
            "\t\t\t\t\"drawTime\": \"20:50:00\"\n" +
            "\t\t\t}, {\n" +
            "\t\t\t\t\"drawDate\": \"14-09-2016\",\n" +
            "\t\t\t\t\"drawName\": \"MID WEEK\",\n" +
            "\t\t\t\t\"drawTime\": \"20:50:00\"\n" +
            "\t\t\t}]\n" +
            "\t\t},\n" +
            "\t\t\"betTypeData\": [{\n" +
            "\t\t\t\"isQp\": true,\n" +
            "\t\t\t\"betName\": \"Direct10\",\n" +
            "\t\t\t\"pickedNumbers\": \"01,09,21,39,42,44,49,63,66,74\",\n" +
            "\t\t\t\"numberPicked\": \"10\",\n" +
            "\t\t\t\"unitPrice\": 10,\n" +
            "\t\t\t\"noOfLines\": 1,\n" +
            "\t\t\t\"betAmtMul\": 1,\n" +
            "\t\t\t\"panelPrice\": 50\n" +
            "\t\t}],\n" +
            "\t\t\"advMessage\": {\n" +
            "\t\t\t\"BOTTOM\": [\"bottom msg for all dg\"],\n" +
            "\t\t\t\"TOP\": [\"12-24 LOTTO NOW MATCH 12 IS JACKPOT OF N 2.50 MILLIONS WINNERS SHARE JACKPOT EQUALLY.\", \"top dg message for all game\"]\n" +
            "\t\t},\n" +
            "\t\t\"orgName\": \"Test Retailer\",\n" +
            "\t\t\"userName\": \"testret\"\n" +
            "\t},\n" +
            "\t\"isPromo\": false\n" +
            "}";
    TelephonyManager telephonyManager;
    MyPhoneStateListener myListener;

    BroadcastReceiver mIntentReceiver;
    String TAG;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_splash);

        TpsGamesClass.getInstance().setWidthAndHeight(this);
        TpsGamesClass.getInstance().getPhoneAspectRatio();
        if (TpsGamesClass.getInstance().getDeviceName().contains("7003")) {
            Bitmap bitmap1 = BitmapFactory.decodeResource(TpsSplashActivity.this.getResources(), R.drawable.skilrock_tousei);
            TpsGamesClass.getInstance().displayImagebyAbsolutePath("", bitmap1);
        }
        if (TpsGamesClass.getInstance().getDeviceName().contains("TPS580")) {
            TpsGamesClass.getInstance().saveImage("skilrock.png", TpsSplashActivity.this);
            TpsGamesClass.getInstance().pcsoIcon("/sdcard/skilrock.png");
            TpsGamesClass.getInstance().displayScreen(this);
        }
//
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TpsGamesClass.getInstance().closeScreen(TpsSplashActivity.this);
                intent = new Intent(TpsSplashActivity.this, LoginActivityTpsGame.class);
                startActivity(intent);
//                TpsGamesClass.getInstance().closeScreen(TpsSplashActivity.this);
                finish();
            }
        }, 2000);

//        intent = new Intent(this, PrintActivityAllGames.class);
//        intent.putExtra("response", saleResponse);
//        startActivity(intent);

//        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        telephonyManager.listen(myListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
//        int currsim = HdxUtil.GetCurrentSim();
//
//        SwitchParams params = new SwitchParams();
//        params.sim = 2;
//
//        String name = telephonyManager.getSimOperatorName();
//        new SwitchTask().execute(params);
//        TpsGamesClass.getInstance(this);


//        IntentFilter intentFilter = new IntentFilter("android.intent.action.SERVICE_STATE");
//
//        mIntentReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                Log.d("ANDROID_INFO", "Service state changed");
//                Bundle bundle = intent.getExtras();
//                if (bundle != null) {
//                    int state = bundle.getInt("state");
//                    Log.d("ANDROID_INFO", "state = " + state);
//                    switch (state) {
//                        case 0x00:
//
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                            }
//                            String imsi =telephonyManager.getSubscriberId();     //ȡ��IMSI
//                            Log.v("","");
//
//                            break;
//                        case 1:
//                        default:
//                            break;
//
//                        case ServiceState.STATE_POWER_OFF:
//                            Log.d("ANDROID_INFO", "modem power off");
//                            break;
//                    }
//                } else {
//                    Log.d("ANDROID_INFO", "bundle is null");
//                }
//            }
//        };
//        registerReceiver(mIntentReceiver, intentFilter);

//        JSONObject obj = new JSONObject();
//        try {
//            obj.put("userName", "testret");
//            obj.put("password", "12345678");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//////
//        String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "loginMgmt/userLogin.action?json=" + obj.toString();
//        HttpRequest httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(url, this, "LoginRequest", this, "login");
//        TpsGamesClass.getInstance().startLoader(this);
//        httpRequest.executeTask();
//
//
////        TpsGamesClass.getInstance(this).startLoader(this);
////
////
//        try {
//            int i = ThermalPrinter.checkStatus();
//            System.out.println("Printer Status " + i);
//            if (i == 1) {
//                ThermalPrinter.stop();
//                setResult(104, new Intent());
//                finish();
//            }
//        } catch (Error e) {
////            setResult(103, new Intent());
//            e.getLocalizedMessage();
//        } catch (TelpoException e) {
//            e.printStackTrace();
//        }
//

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class MyPhoneStateListener extends PhoneStateListener {


        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {

            super.onSignalStrengthsChanged(signalStrength);
            Log.v("strength", signalStrength.toString() + "");


        }

    }

    ;

    @Override
    public void onResponse(String response, String requestedMethod) {
        TpsGamesClass.getInstance().stopLoader();

        if (requestedMethod.equals("login")) {
            LoginResponse loginResponse = TpsGamesClass.getInstance(this).getGson().fromJson(response, LoginResponse.class);
            TpsGamesClass.getInstance().setLoginResponse(loginResponse);
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/fetchDrawGameDataPCPOS.action";
            HttpRequest httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(url, this, "LoginRequest", this, "fetchDrawGame");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();
        } else if (requestedMethod.equals("fetchDrawGame")) {
            GameBean gameBean = TpsGamesClass.getInstance().getGson().fromJson(response, GameBean.class);
            TpsGamesClass.getInstance().setFetchGameJson(response);
//            Intent intent = new Intent(TpsSplashActivity.this, MainActivity.class);
            startActivity(intent);


        }


//        GameBean gameBean = TpsGamesClass.getInstance().getGson().fromJson(response, GameBean.class);
        Log.v("", "");
    }

    private class SwitchParams {

        public int sim;
        public int action;
    }

    private class SwitchResult {

        public Exception e;
    }


    long startTime, endTime;

    private class SwitchTask extends AsyncTask<SwitchParams, Void, SwitchResult> {

        @Override
        protected SwitchResult doInBackground(SwitchParams... params) {

            SwitchResult result = new SwitchResult();
            try {

                try {
                    startTime = System.currentTimeMillis();

                    Class<?> c = Class.forName("android.util.Vendor");

                    Method get = c.getMethod("setCurrentSim", int.class);
                    get.invoke(c, params[0].sim);

                } catch (Exception e)

                {
                    e.printStackTrace();
                }


            } catch (Exception e) {

                result.e = e;
            }
            endTime = System.currentTimeMillis();
            long tottal = TimeUnit.MILLISECONDS.toSeconds((endTime - startTime));
            return result;
        }

        @Override
        protected void onPostExecute(SwitchResult result) {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(2);
        Log.d(TAG, "onStart");
    }

    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");

    }


    protected void onFreeze(Bundle outIcicle) {
        Log.d(TAG, "onFreeze");
    }
}
