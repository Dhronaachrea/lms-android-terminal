package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.usb.IUsbManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.ServiceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.baseClass.UsbBroadCastReciver;
import demo.stpl.com.tpsmergedbuild.beans.LoginResponse;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;

//import skilrock.com.tpsgame.R;
//import tpsgames.HttpRequest;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.LoginResponse;
//import tpsgames.interfaces.ResponseLis;

/**
 * Created by stpl on 9/13/2016.
 */
public class LoginActivityTpsGame extends Activity implements View.OnClickListener, ResponseLis {
    private EditText userName, password;
    private LinearLayout loginLayout;
    private boolean isLoginPerformed;
    private TextView logintext;
    static {
        System.loadLibrary("system_util");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_login_screen_tpsgames);

//        IntentFilter filter = new IntentFilter();
//        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
//        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
       // this.registerReceiver(new UsbBroadCastReciver(), filter);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
//        logintext.setText("");
        init();

        if (TpsGamesClass.getInstance().getDeviceName().contains("TPS580")) {
            TpsGamesClass.getInstance().saveImage("skilrock.png", LoginActivityTpsGame.this);
            TpsGamesClass.getInstance().pcsoIcon("/sdcard/skilrock.png");
            TpsGamesClass.getInstance().displayScreen(this);
//            getSportsBean();
        }
//
        loginLayout.setOnClickListener(this);
        if (TpsGamesClass.getInstance().getStringPreferences(Utility.LAST_TICKET_NUMBER_SPORTS) == null || TpsGamesClass.getInstance().getStringPreferences(Utility.LAST_TICKET_NUMBER_SPORTS).equalsIgnoreCase("")) {
            TpsGamesClass.getInstance().setStringPreferences(Utility.LAST_TICKET_NUMBER_SPORTS, "0");
            TpsGamesClass.getInstance().setStringPreferences(Utility.LAST_TXT_NUMBER_SPORTS, "0");
        }
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    loginLayout.performClick();
                }
                return false;
            }
        });
    }


    protected void init() {
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
        logintext = (TextView) findViewById(R.id.logintext);
        logintext.setTypeface(TpsGamesClass.getInstance().getTypeFace(Utility.BOLD));
    }


    private void performLogin() {
        if (userName.getText().toString().trim().length() == 0) {
            isLoginPerformed = false;
            return;
        } else if (password.getText().toString().trim().length() == 0) {
            isLoginPerformed = false;
            return;
        }

//        String url = Utility.eBetSlipUrl;
//        HttpRequest httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(url, this, "ebetslip", this, "ebetslip", Utility.eBetSlipHeader);
//        TpsGamesClass.getInstance().startLoader(this);
//        httpRequest.executeTask();

        JSONObject obj = new JSONObject();
        try {
            obj.put("userName", userName.getText().toString().trim());
            obj.put("password", password.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
////
        String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "loginMgmt/userLogin.action?json=" + obj.toString();
        HttpRequest httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(url, this, "LoginRequest", this, "login");
        TpsGamesClass.getInstance().startLoader(this);
        httpRequest.executeTask();
    }

    TextView exception;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginLayout:
//                exception.setText("");
                if (!isLoginPerformed) {
                    isLoginPerformed = true;
                    performLogin();
                }
                break;

        }
    }

    @Override
    public void onResponse(String response, String requestedMethod) {
        isLoginPerformed = false;
        TpsGamesClass.getInstance().stopLoader();
        if (response.contains("<HTML><HEAD>")) {
            TpsGamesClass.getInstance().showAToast("Connect to internet!", LoginActivityTpsGame.this, Toast.LENGTH_SHORT);
            return;
        }

//        LoginResponse loginResponse1 = TpsGamesClass.getInstance().getGson().fromJson(response, LoginResponse.class);
        if (requestedMethod.equalsIgnoreCase("fail") || response.equalsIgnoreCase("Failed")) {
            TpsGamesClass.getInstance().showAToast("Server is not responding!", LoginActivityTpsGame.this, Toast.LENGTH_SHORT);
            return;
        }
        if (response != null) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject.optString("isSuccess").equalsIgnoreCase("false")) {
                    TpsGamesClass.getInstance().showAToast(jsonObject.optString("errorMsg"), LoginActivityTpsGame.this, Toast.LENGTH_SHORT);
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LoginResponse loginResponse = TpsGamesClass.getInstance().getGson().fromJson(response, LoginResponse.class);
            if (loginResponse.isSuccess()) {

                TpsGamesClass.getInstance().setLoginResponse(loginResponse);
                Intent intent = new Intent(this, HomeActivityTpsGame.class);
                startActivity(intent);
                TpsGamesClass.getInstance().closeScreen(this);
                finish();

            } else {

                TpsGamesClass.getInstance().showAToast(loginResponse.getErrorMsg(), this, Toast.LENGTH_SHORT);
            }
        } else {
            TpsGamesClass.getInstance().showAToast(response, this, Toast.LENGTH_SHORT);

        }

    }

    BroadcastReceiver onPermissionGrant = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.print("");
        }
    };
//    BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//
//            System.out.println("BroadcastReceiver Event");
//            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
////                Parcelable targetParcelable = intent.getParcelableExtra(Intent.EXTRA_INTENT);
////                Intent target = (Intent)targetParcelable;
////                UsbDevice mDevice = (UsbDevice)target.getParcelableExtra(UsbManager.EXTRA_DEVICE);
////                PackageManager pm = context.getPackageManager();
////                try{
////                    ApplicationInfo ai = pm.getApplicationInfo( "demo.stpl.com.tpsmergedbuild", 0 );
////                    if( ai != null )
////                    {
////                        UsbManager manager = (UsbManager) context.getSystemService( Context.USB_SERVICE );
////                        IBinder b = ServiceManager.getService(Context.USB_SERVICE);
////                        IUsbManager service = IUsbManager.Stub.asInterface( b );
////
////                        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
////                        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
////                        while( deviceIterator.hasNext() )
////                        {
////                            UsbDevice device = deviceIterator.next();
////                            int vender = device.getVendorId();
////                            if( service.hasDevicePermission(device) )
////                            {
//////                                service.requestAccessoryPermission( device, ai.uid );
//////                                service.grantDevicePermission( device, ai.uid );
//////                                service.setDevicePackage( device, "demo.stpl.com.tpsmergedbuild" );
////                            }
////                        }
////                    }
////                }catch (Exception e){
////
////                    System.out.print(e.getLocalizedMessage());
////                }
////
//////                mSerial.usbAttached(intent);
//////                mSerial.begin(mBaudrate);
//////                loadDefaultSettingValues();
//////                Run = true;
//////                start();
////                System.out.println("BroadcastReceiver USB Connected");
//
//            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
////                mSerial.usbDetached(intent);
////                mSerial.end();
////                Run = false;
//                System.out.println("BroadcastReceiver USB Disconnected");
//            }
//        }
//    };
}
