package demo.stpl.com.tpsmergedbuild.baseClass;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.pt.minilcd.MiniLcd;
import android.pt.scan.Scan;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ViceScreen.ViceScrUtil;
import com.google.gson.Gson;
import com.telpo.tps550.api.TelpoException;
import com.telpo.tps550.api.magnetic.MagneticCard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.activity.ActivityDgeGames;
import demo.stpl.com.tpsmergedbuild.activity.ActivityDgeGamesTps390;
import demo.stpl.com.tpsmergedbuild.activity.ActivityLockScreen;
import demo.stpl.com.tpsmergedbuild.activity.ActivitySleGames;
import demo.stpl.com.tpsmergedbuild.activity.ActivitySleGamesTps390;
import demo.stpl.com.tpsmergedbuild.activity.HomeActivityTpsGame;
import demo.stpl.com.tpsmergedbuild.activity.Ola;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;
import demo.stpl.com.tpsmergedbuild.beans.LoginResponse;
import demo.stpl.com.tpsmergedbuild.beans.SleGameBean;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryTktBean;
import demo.stpl.com.tpsmergedbuild.beans.SportsSaleBean;
import demo.stpl.com.tpsmergedbuild.betslip.ActivityBetSlipCamera;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;
import demo.stpl.com.tpsmergedbuild.interfaces.Tps515PrintCall;

//import android.pt.minilcd.MiniLcd;
//import android.pt.scan.Scan;
//import com.ViceScreen.ViceScrUtil;
//import com.telpo.tps550.api.TelpoException;
//import com.telpo.tps550.api.magnetic.MagneticCard;

//import skilrock.com.tpsgame.R;
//import tpsgames.HttpRequest;
//import tpsgames.Utility;
//import tpsgames.activity.ActivityDgeGames;
//import tpsgames.activity.ActivityDgeGamesTps390;
//import tpsgames.activity.ActivityLockScreen;
//import tpsgames.activity.ActivitySleGames;
//import tpsgames.activity.ActivitySleGamesTps390;
//import tpsgames.activity.HomeActivityTpsGame;
//import tpsgames.activity.Ola;
//import tpsgames.beans.GameBean;
//import tpsgames.beans.LoginResponse;
//import tpsgames.beans.SleGameBean;
//import tpsgames.beans.SportsLotteryTktBean;
//import tpsgames.beans.SportsSaleBean;
//import tpsgames.betslip.ActivityBetSlipCamera;
//import tpsgames.interfaces.ResponseLis;

/**
 * Created by stpl on 9/2/2016.
 */
public class TpsGamesClass {
    private static TpsGamesClass tpsGamesClass;
    private Dialog dialog;
    private Ola olaActivity=null;
    private SharedPreferences.Editor variablesPreferencesEditor;
    private SharedPreferences variablesPreferences;
    public String PREF_NMAE = "TPS_GAMES";
    private static Context context;
    private boolean isResponsibleGamingOn = false;
    private Gson gson;
    private boolean isMagnaticCardOpen = false;
    private String[] tracData = null;
    private int screenWidth;
    private int screenHeight;
    private int widthRatio, heightRatio;
    private GameBean gameBean;
    private LoginResponse loginResponse;
    private String responseOffLine;
    private String deviceName;
    private boolean isCameraOpen = false;
    private SleGameBean sleGameBean;
    private SleGameBean.SleData.GameData.GameTypeData soccer6, soccer13;
    private ActivityLockScreen activityLockScreen;
    private boolean isActivityLockScreenActivated = false;
    private String printerNameExternal = "";
    private long startTime, difference;
    private long differenceOfTime = 0;
    private boolean isFrontScreenLogicStarted = false;
    private String printResponseForAzt = "";
    private String callFront = "";
    private String bluetoothPrinterName = "";

    private boolean isOlaFirstTime = false;

    private Tps515PrintCall tps515PrintCall;

    private GameBean.Games luckyNumber, miniKeno, miniRoulette, superKeno, fortune;

    private String[] totalView = {"one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen",
            "twenty",
            "twenty_one",
            "twenty_two",
            "twenty_three",
            "twenty_four",
            "twenty_five",
            "twenty_six",
            "twenty_seven",
            "twenty_eight",
            "twenty_nine",
            "thirty",
            "thirty_one",
            "thirty_two",
            "thirty_three",
            "thirty_four",
            "thirty_five",
            "thirty_six",
            "thirty_seven",
            "thirty_eight",
            "thirty_nine",
            "forty",
            "forty_one",
            "forty_two",
            "forty_three",
            "forty_four",
            "forty_five",
            "forty_six",
            "forty_seven",
            "forty_eight",
            "forty_nine",
            "fifty",
            "fifty_one",
            "fifty_two",
            "fifty_three",
            "fifty_four",
            "fifty_five",
            "fifty_six",
            "fifty_seven",
            "fifty_eight",
            "fifty_nine",
            "sixty",
            "sixty_one",
            "sixty_two",
            "sixty_three",
            "sixty_four",
            "sixty_five",
            "sixty_six",
            "sixty_seven",
            "sixty_eight",
            "sixty_nine",
            "seventy",
            "seventy_one",
            "seventy_two",
            "seventy_three",
            "seventy_four",
            "seventy_five",
            "seventy_six",
            "seventy_seven",
            "seventy_eight",
            "seventy_nine",
            "eighty",
            "eighty_one",
            "eighty_two",
            "eighty_three",
            "eighty_four",
            "eighty_five",
            "eighty_six",
            "eighty_seven",
            "eighty_eight",
            "eighty_nine",
            "ninety"};
    MiniLcd minilcd;
    private int ret;
    Bitmap bitmap1;
    public static boolean printBoolean=true;
    private Scan barcodeScan = null;

    private boolean isBarcodeScanOpen = false;
    private ActivityBetSlipCamera activityBetSlipCamera;

    private String barcodeValueForBetSlip;

    private String topMwessage;

    private Toast toast;

    private  UsbDevice mDevice;

    private HttpRequest httpRequest;

    private ReadThread readThread;

    private String fetchGameJson = "";

    private HomeActivityTpsGame homeActivityTpsGame;

    private ActivityDgeGames activityDgeGames;
    private ActivityDgeGamesTps390 activityDgeGamesTps390;

    private boolean verified;

    private String messageForNotVarified = "";

    private Typeface bold, light, medium, regular, thin;

    private String sportsResponse;

    private ActivitySleGames activitySleGames;
    private ActivitySleGamesTps390 activitySleGamesTps390;
    private String currentActivity = "";

    //singleton class
    private TpsGamesClass() {
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd k:mm:ss");
        variablesPreferencesEditor = context.getSharedPreferences(PREF_NMAE, Context.MODE_PRIVATE).edit();
        gson = new Gson();
        httpRequest = new HttpRequest();
    }

    public void setIsOlaFirstTime(boolean isOlaFirstTime){
        this.isOlaFirstTime = isOlaFirstTime;
    }

    public boolean getIsOlaFirstTime(){
        return isOlaFirstTime;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }

    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setBluetoothPrinterName(String bluetoothPrinterName){
        this.bluetoothPrinterName = bluetoothPrinterName;
    }

    public String getBluetoothPrinterName(){
        return bluetoothPrinterName;
    }




    public void setFrontScreenLogicStarted(){
        isFrontScreenLogicStarted = false;
    }

    public synchronized void startFrontScreen(String callFront) {
        this.callFront = callFront;
        startTime = new Date().getTime();

        if (!isFrontScreenLogicStarted) {
            isFrontScreenLogicStarted = true;
            new FrontScreenUpdateThread().start();
        }
    }

    public void setPrinterNameExternal(String printerNameExternal) {
        this.printerNameExternal = printerNameExternal;
    }

    public String getPrinterNameExternal() {
        return printerNameExternal;
    }

    public void setActivityLockScreen(ActivityLockScreen activityLockScreen) {
        isActivityLockScreenActivated = true;
        this.activityLockScreen = activityLockScreen;
    }

    public void finishActivityLockScreen() {
        if (activityLockScreen != null) {
            isActivityLockScreenActivated = false;
            activityLockScreen.finish();
        }
    }

    public void setActivitySleGames(ActivitySleGames activitySleGames) {
        this.activitySleGames = activitySleGames;
    }
    public void setActivitySleGamesTps390(ActivitySleGamesTps390 activitySleGamesTps390) {
        this.activitySleGamesTps390 = activitySleGamesTps390;
    }
    public void setPrintResponseForAzt(String response) {
        printResponseForAzt = response;
    }

    public String getPrintResponseForAzt() {
        return printResponseForAzt;
    }


    public void checkUsbPrintPermission(Activity activity){
        UsbManager manager = (UsbManager)activity.getSystemService(Context.USB_SERVICE);

        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();

        for (UsbDevice device : deviceList.values()) {
            Log.i("discover", "Model    :" + device.getDeviceName());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(device.getManufacturerName().equalsIgnoreCase("epson")){
                    mDevice = device;
                    if (!manager.hasPermission(device)) {
                        IntentFilter filter = new IntentFilter(Utility.ACTION_USB_PERMISSION);
                        activity.registerReceiver(new UsbBroadCastReciver(),filter);
                        Log.i("discover", "No permission to access, so requesting it now from user (async)");
                        manager.requestPermission(device, PendingIntent.getBroadcast(activity, 0, new Intent(Utility.ACTION_USB_PERMISSION), 0));

                    }else{
getTps515PrintCall().onUsbPermissionSuccess();
                    }
                }
            }
//            if (!manager.hasPermission(device)) {
//                Log.i("discover", "No permission to access, so requesting it now from user (async)");
//               // manager.requestPermission(device, mPermissionIntent);
//            } else {
//
//            }
        }

    }

    public void setIsActivityLockActivated(boolean isActivityLockActivated) {
        this.isActivityLockScreenActivated = isActivityLockActivated;
    }

    public boolean getIsActivityLockActivated() {
        return isActivityLockScreenActivated;
    }


    public void setSleGameBean(SleGameBean sleGameBean) {
        this.sleGameBean = sleGameBean;
        if (sleGameBean != null) {
            int totalGame = 0;
            for (int i = 0; i < sleGameBean.getSleData().getGameData()[0].getGameTypeData().length; i++) {
                SleGameBean.SleData.GameData.GameTypeData gameTypeData = sleGameBean.getSleData().getGameData()[0].getGameTypeData()[i];
                if (gameTypeData.getGameTypeDevName().contains("6")) {
                    totalGame++;
                    soccer6 = gameTypeData;
                } else if (gameTypeData.getGameTypeDevName().contains("13")) {
                    soccer13 = gameTypeData;
                }
                if (totalGame == 2) {
                    break;
                }
            }
        }
    }

    public boolean getIfHasCamera() {
        int numCameras = Camera.getNumberOfCameras();
        if (numCameras > 0) {
            return true;
        }
        return false;
    }


    public SleGameBean.SleData.GameData.GameTypeData getGameSle(String gameName) {
        if (gameName.equalsIgnoreCase("soccer6")) {
            return soccer6;
        } else if (gameName.equalsIgnoreCase("soccer13")) {
            return soccer13;
        }
        return null;
    }

    public SleGameBean getSleGameBean() {
        return sleGameBean;
    }

    public void setActivityDgeGames(ActivityDgeGames activityDgeGames) {
        this.activityDgeGames = activityDgeGames;
    }

    public ActivityDgeGames getActivityDgeGames() {
        return activityDgeGames;
    }
    public void setActivityDgeGamesTps390(ActivityDgeGamesTps390 activityDgeGamesTps390) {
        this.activityDgeGamesTps390 = activityDgeGamesTps390;
    }
    public ActivityDgeGamesTps390 getActivityDgeGamesTps390() {
        return activityDgeGamesTps390;
    }
    public void setCameraOpen(boolean isCameraOpen) {
        this.isCameraOpen = isCameraOpen;
    }

    public boolean isCameraOpen() {
        return isCameraOpen;
    }

    public void openCard() {
        if(isMagnaticCardOpen){
            return;
        }
        try {
            MagneticCard.open();
            readThread = new ReadThread();
            readThread.start();
            isMagnaticCardOpen = true;
        } catch (TelpoException e) {
            isMagnaticCardOpen = false;
            e.printStackTrace();
        }
    }


    public void stopCard() {
        if (!TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("PT7003") && readThread != null)
            readThread.interrupt();
        try {
            if(isMagnaticCardOpen)
            MagneticCard.close();
        } catch (Exception e) {

        }
        isMagnaticCardOpen = false;

    }

    public Scan getBarcodeScan() {
        if (!isBarcodeScanOpen) {
            isBarcodeScanOpen = true;
            barcodeScan = new Scan();
            barcodeScan.open();
        } else if (barcodeScan != null) {
            barcodeScan.open();
        }
        return barcodeScan;
    }

    public void setFrontScreen(final int position, final Context context) {
        TpsGamesClass.getInstance().closeScreen(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (position == 2) {
                    TpsGamesClass.getInstance().saveImage("mini_roulette.png", context);
                    TpsGamesClass.getInstance().pcsoIcon("/sdcard/mini_roulette.png");
                    ViceScrUtil.Display(context);
                } else if (position == 0) {
                    TpsGamesClass.getInstance().saveImage("five_nine.png", context);
                    TpsGamesClass.getInstance().pcsoIcon("/sdcard/five_nine.png");
                    ViceScrUtil.Display(context);

                } else if (position == 1) {
                    TpsGamesClass.getInstance().saveImage("twleve_by_twenty.png", context);
                    TpsGamesClass.getInstance().pcsoIcon("/sdcard/twleve_by_twenty.png");
                    ViceScrUtil.Display(context);
                } else if (position == 4) {
                    TpsGamesClass.getInstance().saveImage("fortune.png", context);
                    TpsGamesClass.getInstance().pcsoIcon("/sdcard/fortune.png");
                    ViceScrUtil.Display(context);
                }else if (position == 3) {
                    TpsGamesClass.getInstance().saveImage("super_keno_front.png", context);
                    TpsGamesClass.getInstance().pcsoIcon("/sdcard/super_keno_front.png");
                    ViceScrUtil.Display(context);
                }
            }
        }).start();

    }

    public String getAmountWithTwoDecim(String amount){
        if (amount.contains(".")) {
            String[] decimalAmount = amount.split("[.]");
            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        return amount;
    }


    public void setTopMessage(String topMessage) {
        this.topMwessage = topMessage;
    }

    public String getTopMwessage() {
        return topMwessage;
    }


    public void setSportsResponse(String sportsResponse) {
        this.sportsResponse = sportsResponse;
    }

    public String getSportsResponse() {
        return sportsResponse;
    }


    public void setPlayerVerified(boolean verified, String messageForNotVarified) {
        this.verified = verified;
        this.messageForNotVarified = messageForNotVarified;
    }


    public void setActivityBetSlipCamera(ActivityBetSlipCamera activityBetSlipCamera) {
        this.activityBetSlipCamera = activityBetSlipCamera;
    }

    public ActivityBetSlipCamera getActivityBetSlipCamera() {
        return activityBetSlipCamera;
    }


    public String getMessageForNotVarified() {
        return messageForNotVarified;
    }

    public boolean getPlayerId() {
        return this.verified;
    }

    public void setHomeActivityTpsGame(Activity homeActivityTpsGame) {
        this.homeActivityTpsGame = (HomeActivityTpsGame) homeActivityTpsGame;
    }

    public HomeActivityTpsGame getHomeActivityTpsGame() {
        return homeActivityTpsGame;
    }

    // one instance of singleton class with context
    public static TpsGamesClass getInstance(Context context) {
        if (tpsGamesClass == null) {
            TpsGamesClass.context = context;
            tpsGamesClass = new TpsGamesClass();
        }
        return tpsGamesClass;
    }


    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDevicString() {
        return deviceName;
    }

    // setting responsible gaming
    public void setResponsibleGamingOn(boolean isResponsibleGamingOn) {
        this.isResponsibleGamingOn = isResponsibleGamingOn;
    }


    public boolean isResponsibleGamingOn() {
        return isResponsibleGamingOn;
    }

    public void setStringPreferences(String key, String value) {
        variablesPreferencesEditor.putString(key, value);
        variablesPreferencesEditor.commit();
    }

    public String getStringPreferences(String key) {
        variablesPreferences = context.getSharedPreferences(PREF_NMAE, Context.MODE_PRIVATE);
        return variablesPreferences.getString(key, "");
    }

    // one instance of singleton class
    public static TpsGamesClass getInstance() {
        if (tpsGamesClass == null) {
            tpsGamesClass = new TpsGamesClass();
        }
        return tpsGamesClass;
    }

    public void setOlaActivity(Activity olaActivity) {
        this.olaActivity = (Ola) olaActivity;
    }

    public void hideKeyBoard(Activity activity,Context context){
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void setSoccerScreen(String gameName, Context context) {
        if (gameName.equalsIgnoreCase("13")) {

            TpsGamesClass.getInstance().closeScreen(context);

            TpsGamesClass.getInstance().saveImage("soccer_thirteen.png", context);
            String url = "/sdcard/soccer_thirteen.png";
            TpsGamesClass.getInstance().pcsoIcon(url);
            TpsGamesClass.getInstance().displayScreen(context);
        } else if (gameName.equalsIgnoreCase("6")) {
            TpsGamesClass.getInstance().closeScreen(context);

            TpsGamesClass.getInstance().saveImage("soccer_six.png", context);
            String url = "/sdcard/soccer_six.png";
            TpsGamesClass.getInstance().pcsoIcon(url);
            TpsGamesClass.getInstance().displayScreen(context);
        }
    }

    public void setBarcodeValueForBetSlip(String barcodeValueForBetSlip) {
        this.barcodeValueForBetSlip = barcodeValueForBetSlip;
    }

    public String getBarcodeValueForBetSlip() {
        return barcodeValueForBetSlip;
    }


    //checking if network is available or not
    public boolean isNetworkAvailable(Context context) {
        boolean outcome = false;

        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo[] networkInfos = cm.getAllNetworkInfo();

            for (NetworkInfo tempNetworkInfo : networkInfos) {


                /**
                 * Can also check if the user is in roaming
                 */
                if (tempNetworkInfo.isConnected()) {
                    outcome = true;
                    break;
                }
            }
        }

        return outcome;
    }


    public void setFetchGameJson(String json) {
        this.fetchGameJson = json;
    }

    public String getFetchGameJson() {
        return fetchGameJson;
    }

    // device model for application
    public String getDeviceName() {
        return Build.MODEL;
    }

    public int greatestCommonFactor(int width, int height) {
        return (height == 0) ? width : greatestCommonFactor(height, width % height);
    }

    // get the aspect ratio of the phone
    public int getPhoneAspectRatio() {


        int factor = greatestCommonFactor(screenWidth, screenHeight);

        widthRatio = screenWidth / factor;
        heightRatio = screenHeight / factor;
        return widthRatio;
    }

    public void startLoader(Context context) {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = new DialogLoader(context).createStartManualGameDialog("Hello");
        dialog.show();
    }

    public void stopLoader() {
        try{
            if (dialog != null) {
                dialog.dismiss();
            }
        }catch(Exception e){

        }

    }


    public Typeface getTypeFace(String tyface) {
        Typeface typeface = null;
        if (tyface.equalsIgnoreCase("bold")) {
            if (bold == null) {
                bold = Typeface.createFromAsset(context.getAssets(), "bold.ttf");
            }
            typeface = bold;

        } else if (tyface.equalsIgnoreCase("light")) {
            if (light == null) {
                light = Typeface.createFromAsset(context.getAssets(), "light.ttf");
            }
            typeface = light;
        } else if (tyface.equalsIgnoreCase("medium")) {
            if (medium == null) {
                medium = Typeface.createFromAsset(context.getAssets(), "medium.ttf");
            }
            typeface = medium;
        } else if (tyface.equalsIgnoreCase("regular")) {
            if (regular == null) {
                regular = Typeface.createFromAsset(context.getAssets(), "regular.ttf");
            }
            typeface = regular;
        } else if (tyface.equalsIgnoreCase("thin")) {
            if (thin == null) {
                thin = Typeface.createFromAsset(context.getAssets(), "thin.ttf");
            }
            typeface = thin;
        }

        return typeface;
    }

    public void pleaseWait(String msg) {
        ViceScrUtil.Add_Text(0, msg);
        ViceScrUtil.Set_Font(0, 16);
        ViceScrUtil.Set_Align(0, 2);
        ViceScrUtil.Add_TextCoord(0, 0, 120);
    }

    public void pleaseWait(String msg, String imagePah) {
        ViceScrUtil.Add_Image(0, imagePah);
        ViceScrUtil.Add_ImageCoord(0, 0, 0, 310, 25);
        ViceScrUtil.Add_Text(1, msg);
        ViceScrUtil.Set_Font(1, 16);
        ViceScrUtil.Set_Align(1, 3);
        ViceScrUtil.Add_TextCoord(1, 0, 120);

    }

    public void pcsoIcon(String imagePah) {
        ViceScrUtil.Add_Image(0, imagePah);
        ViceScrUtil.Add_ImageCoord(0, 0, 0, 310, 230);

    }

    public String getSportsLotteryServices() {

        String path = "com/skilrock/sle/embedded/gameDrawData/playMgmt/Action/fetchSLEDrawData.action?merCode=RMS&";
        String params = "userName=" + TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName() +
                "&version=" + "1.0" +
                "&sessId=" + TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId() +
                "&reqCounter=0" + "&respcounter=" +
                "&CID=52261" + "&LAC=106" + "&slLstTxnId=" + "0" + "&LSTktNo=0" + "&time=" + "&date=9/18/2016" + "&random=" + getRandom();
        return Utility.baseUrl + Utility.portNumber + "/SportsLottery/" + path + params;
    }

    public SportsLotteryTktBean getSportLoater(String saleResponseSle) {
        SportsLotteryTktBean sportsLotteryTktBean = new SportsLotteryTktBean();
        String[] strings = saleResponseSle.split("[|]");
        for (int i = 0; i < strings.length; i++) {
            String currentString = strings[i];
            if (currentString.contains("currDate")) {
                sportsLotteryTktBean.setCurrDate(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("currTime")) {
                sportsLotteryTktBean.setCurrTime(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("ticketNo")) {
                sportsLotteryTktBean.setTicketNo(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("brCd")) {
                sportsLotteryTktBean.setBrCd(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("trxId")) {
                sportsLotteryTktBean.setTrxId(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("ticketAmt")) {
                sportsLotteryTktBean.setTicketAmt(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("balance")) {
                sportsLotteryTktBean.setBalance(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("gameId")) {
                sportsLotteryTktBean.setGameId(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("gameTypeId")) {
                sportsLotteryTktBean.setGameTypeId(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("gameName")) {
                sportsLotteryTktBean.setGameName(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("gameTypeName")) {
                sportsLotteryTktBean.setGameTypeName(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            } else if (currentString.contains("drawInfo")) {
                if (currentString.lastIndexOf(":") != -1) {
                    String allError = currentString.substring(currentString.lastIndexOf(":") + 1, currentString.length());
                    sportsLotteryTktBean.setAllMesage(allError.split("[~]"));
                }

                String[] allString = currentString.split("[~]");
                for (int k = 0; k < allString.length; k++) {
                    if (k == 0) {
                        String[] restData = allString[k].split("[,]");
                        sportsLotteryTktBean.setDrawDate(restData[0].substring(restData[0].indexOf(":") + 1, restData[0].length()));
                        sportsLotteryTktBean.setDrawTime(restData[1]);
                        sportsLotteryTktBean.setDrawName(restData[2]);

                        sportsLotteryTktBean.setDrawValidity(restData[4]);

                        sportsLotteryTktBean.setUnitPrice(restData[5]);
                        sportsLotteryTktBean.setBetAmount(restData[6]);
                        sportsLotteryTktBean.setBoardAmount(restData[7]);

                    } else if (k == 1) {
                        String[] drawAll = allString[k].substring(0, allString[k].indexOf(":") - 10).split("[#]");
                        sportsLotteryTktBean.setDrawInfo(drawAll);
                        Log.i("", "");
                    }

                }
//                sportsLotteryTktBean.setGameTypeName(currentString.substring(currentString.indexOf(":") + 1, currentString.length()));
            }
        }
        return sportsLotteryTktBean;
    }

    public String getTicketSaleServicesForSle(Context context, String username, String gameId, String gameTypeId, String drawInfo, String drawCount, String ticketAmt, SportsSaleBean sportsSaleBean) {
//userName=testret&gameId=1&gameTypeId=1&drawInfo=957~1~4335@A$4343@H,A$4337@H$4338@A$4333@H,A$4334@H$4346@A$4340@H,A$4341@H$4336@A$4342@H,A$4339@H$4344@A$$&drawCount=1&ticketAmt=16.0&merCode=RMS&slLstTxnId=0&LSTktNo=0&LRespTime=&CID=52261&LAC=106&reqCounter=31&respCounter=0&sessId=BEF21044C848B02C3186B345E0C0FF65&time=&date=9/22/2016&random=116
        String path = "/com/skilrock/sle/embedded/playMgmt/Action/sportsLotteryPurchaseTicket.action?";
        String params = "userName=" + username + "&gameId=" + gameId + "&gameTypeId=" + gameTypeId /*+ "&version=" + TerminalInfo.getVersion(context) + "&reqCounter=" + preferences.getReqCounter() + "&respcounter="
                + preferences.getRespCounter() + "&LSTktNo=" + preferences.getLSTktNo() + "&slLstTxnId=" + preferences.getSlLstTxnId()*/
                + "&drawInfo=" + drawInfo + "$&drawCount=" + drawCount + "&ticketAmt=" + ticketAmt.trim() + "&merCode=" + "RMS"

                + "&slLstTxnId=" + "0" + "&LSTktNo=0" + "&LRespTime="

                + "&CID=" + sportsSaleBean.getCID().substring(sportsSaleBean.getCID().indexOf("=") + 1, sportsSaleBean.getCID().length()) + "&LAC=" + sportsSaleBean.getLAC().substring(sportsSaleBean.getLAC().indexOf("=") + 1, sportsSaleBean.getLAC().length())
                + "&reqCounter=" + sportsSaleBean.getReqCounter().substring(sportsSaleBean.getReqCounter().indexOf("=") + 1, sportsSaleBean.getReqCounter().length()) + "&respCounter=0"
                + "&sessId=" + TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId()
                + "&time=" + sportsSaleBean.getTime().substring(sportsSaleBean.getTime().indexOf("=") + 1, sportsSaleBean.getTime().length()) + "&date=" + sportsSaleBean.getDate().substring(sportsSaleBean.getDate().indexOf("=") + 1, sportsSaleBean.getDate().length()) + "&random=" + getRandom();
        String uri = Utility.baseUrl + Utility.portNumber + "/SportsLottery/" + path + params;
        return Uri.parse(uri).toString();
    }

    public HttpRequest getHttpRequest(String url, ResponseLis responseLis, String loadingMessage, Context context, String requestMethod) {
        httpRequest.setAllParameter(url, responseLis, loadingMessage, context, requestMethod);
        return httpRequest;
    }

    public HttpRequest getHttpRequest(String url, ResponseLis responseLis, String loadingMessage, Context context, String requestMethod, String header) {
        httpRequest.setAllParameter(url, responseLis, loadingMessage, context, requestMethod, header);
        return httpRequest;
    }

    int random;

    public int getRandom() {
        random = (int) (Math.random() * 1000);
        return random;
    }

    public Gson getGson() {
        return gson;
    }

    public SportsSaleBean getSportsBean(String response) {
        response = response.replaceAll("[{]", "");
        response = response.replaceAll("[}]", "");
        response = response.substring(response.indexOf(":") + 2, response.length());

        String sleResponse = response.substring(response.indexOf("|") + 1, response.length());
        SportsSaleBean sportsSaleBean = new SportsSaleBean();
        sportsSaleBean.setGameName(response.substring(0, response.indexOf("|")));
        response = sleResponse;
        String otherString = response.substring(response.indexOf("$") + 1, response.length());


        String gameString = response.substring(0, response.indexOf("$"));
        if (gameString != null && gameString.trim().length() > 0) {
            String[] allGame = gameString.substring(gameString.indexOf(":") + 1, gameString.length()).split("[&]");
            if (allGame != null && allGame.length > 0) {
                sportsSaleBean.setAllGame(allGame);
            }
        }
//                String[] allGame = gameString.substring(gameString.indexOf(":")+1,gameString.length()).split("[&]");


        if (otherString != null && otherString.trim().length() > 0) {
            String[] allData = otherString.split("[&]");
            if (allData != null && allData.length > 0) {
                for (int j = 0; j < allData.length; j++) {
                    String currentString = allData[j];
                    if (currentString.contains("userName")) {
                        sportsSaleBean.setUserName(currentString);
                    } else if (currentString.contains("gameId")) {
                        sportsSaleBean.setGameId(currentString);
                    } else if (currentString.contains("gameTypeId")) {
                        sportsSaleBean.setGameTypeId(currentString);
                    } else if (currentString.contains("drawInfo")) {

                        sportsSaleBean.setDrawInfoString(currentString);
                        String[] drawInfo = currentString.substring(currentString.lastIndexOf("~") + 1, currentString.length()).split("[$]");
                        sportsSaleBean.setDrawInfo(drawInfo);
                        Log.i("", "");
//                                sportsSaleBean.setDrawInfo(currentString);
                    } else if (currentString.contains("drawCount")) {
                        sportsSaleBean.setDrawCount(currentString);
                    } else if (currentString.contains("ticketAmt")) {
                        sportsSaleBean.setTicketAmt(currentString);
                    } else if (currentString.contains("merCode")) {
                        sportsSaleBean.setMerCode(currentString);
                    } else if (currentString.contains("LSTktNo")) {
                        sportsSaleBean.setLSTktNo(currentString);
                    } else if (currentString.contains("sessId")) {
                        sportsSaleBean.setSessId(currentString);
                    } else if (currentString.contains("slLstTxnId")) {
                        sportsSaleBean.setSlLstTxnId(currentString);
                    } else if (currentString.contains("CID")) {
                        sportsSaleBean.setCID(currentString);
                    } else if (currentString.contains("LAC")) {
                        sportsSaleBean.setLAC(currentString);
                    } else if (currentString.contains("simType")) {
                        sportsSaleBean.setSimType(currentString);
                    } else if (currentString.contains("deviceType")) {
                        sportsSaleBean.setDeviceType(currentString);
                    } else if (currentString.contains("reqCounter")) {
                        sportsSaleBean.setReqCounter(currentString);
                    } else if (currentString.contains("respCounter")) {
                        sportsSaleBean.setRespCounter(currentString);
                    } else if (currentString.contains("time")) {
                        sportsSaleBean.setTime(currentString);
                    } else if (currentString.contains("date")) {
                        sportsSaleBean.setDate(currentString);
                    } else if (currentString.contains("random")) {
                        sportsSaleBean.setRandom(currentString);
                    }
                }
            }
        }
//                String[] allData = otherString.split("[&]");
        Log.i("", "");


        return sportsSaleBean;

    }

    public void closeScreen(final Context context) {

        System.gc();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!deviceName.contains("TPS550") && !deviceName.contains("hdx")){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ViceScrUtil.Close(context);
                        }
                    }).start();
                }
            }
        }).start();
    }

    public void displayScreen(final Context context) {
        System.gc();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!deviceName.contains("TPS550") && !deviceName.contains("hdx")){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ViceScrUtil.Display(context);
                        }
                    }).start();
                }
            }
        }).start();
    }

    public void saveImage(String imageName, Context context) {
        try {
            File folder = new File(Environment.getExternalStorageDirectory() + "");
            boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdir();
            }
            if (success) {
                InputStream in = null;
                OutputStream out = null;
                try {

                    in = context.getAssets().open(imageName);

                    File dir = new File(Environment.getExternalStorageDirectory() + "");
                    if (!dir.exists()) {
                        dir.mkdirs();

                    }
                    File fileZip = new File(dir, imageName);

                    out = new FileOutputStream(fileZip);
                    copyFile(in, out);
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;

                } catch (IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + e.getMessage());
                }
                Log.e("", "");
                // Do something on success
            } else {
                Log.e("", "");
                // Do something else on failure
            }


            Log.e("", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }


    private class FrontScreenUpdateThread extends Thread {

        @Override
        public void run() {
            super.run();

            while (differenceOfTime < 2) {
                difference = new Date().getTime() - startTime;

                differenceOfTime = TimeUnit.MILLISECONDS.toSeconds(difference);
                System.out.println("printing from thread");
                System.out.println("Difference of time is=======" + differenceOfTime);
            }
            isFrontScreenLogicStarted = false;
            differenceOfTime = 0;
            if (callFront.equalsIgnoreCase("dge")) {
                activityDgeGames.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activityDgeGames.updateFrontScreen();
                    }
                });
            } else if (callFront.equalsIgnoreCase("sle")) {
                activitySleGames.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activitySleGames.updateFrontScreen();
                    }
                });
            }
        }
    }

    public void openKeyBoard(Activity activity){
        View view = activity.getCurrentFocus();
        InputMethodManager inputMethodManager =
                (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(
                view.getApplicationWindowToken(),
                InputMethodManager.SHOW_FORCED, 0);
    }

    private class ReadThread extends Thread {


        @Override
        public void run() {
            MagneticCard.startReading();
            while (!Thread.interrupted()) {
                try {
                    tracData = MagneticCard.check(1000);
                    if (tracData != null) {
                        myLoop:
                        for (final String s : tracData) {
                            if (s.trim().length() > 0 && currentActivity != null) {
                                if (currentActivity.equalsIgnoreCase("dge")) {
                                    activityDgeGames.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            activityDgeGames.isValueSetToDialog = false;
                                            activityDgeGames.cardSwipeVerify(s, "");
                                        }
                                    });
                                }else if (currentActivity.equalsIgnoreCase("dge390")) {
                                    activityDgeGamesTps390.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            activityDgeGamesTps390.isValueSetToDialog = false;
                                            activityDgeGamesTps390.cardSwipeVerify(s, "");
                                        }
                                    });
                                }
                                else if (currentActivity.equalsIgnoreCase("sle")) {
                                    activitySleGames.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            activitySleGames.isValueSetToDialog = false;
                                            activitySleGames.cardSwipeVerify(s, "");
                                        }
                                    });
                                }else if (currentActivity.equalsIgnoreCase("sle390")) {
                                    activitySleGamesTps390.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            activitySleGamesTps390.isValueSetToDialog = false;
                                            activitySleGamesTps390.cardSwipeVerify(s, "");
                                        }
                                    });
                                }
                                else if(currentActivity.equalsIgnoreCase("ola")){
                                    olaActivity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            olaActivity.updateWeaverCard(s);
                                        }
                                    });
                                }


                                break myLoop;
                            }
                        }
                        Log.v("", "");
                    }
                    MagneticCard.startReading();
                } catch (TelpoException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public void changeBackScreen(int pos) {
        if (pos == 0)
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.luckynumber_tousei);
        else if (pos == 1)
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.minikeno_tousei);
        else if (pos == 2)
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.miniroulette_tousei);
        else if (pos == 3)
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.superkeno_tousei);
        else if (pos == 4)
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.fortune_tousei);
        else if (pos == 13)
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.soccerthirteen_tousei);
        else if (pos == 6)
            bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.soccersix_tousei);


        TpsGamesClass.getInstance().displayImagebyAbsolutePath("", bitmap1);
    }

    public void displayImagebyAbsolutePath(final String msg, final Bitmap path) {

        if(!tpsGamesClass.getInstance().getDeviceName().contains("7003")){
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                display(msg, path);
            }
        }).start();


    }

    public synchronized void display(String msg, Bitmap path) {
        minilcd = new MiniLcd();
        minilcd.open();
        ret = -1;
        if (TextUtils.isEmpty(msg)) {
            minilcd.fullScreen(Color.rgb(255, 255, 255));
            if (path != null) {
                minilcd.displayPicture(0, 0, path);
            }
//            while (ret < 0) {
//                ret = minilcd.displayPictureByAbsolutePath(0, 0, path);
//            }
        } else {
            minilcd.fullScreen(Color.rgb(255, 255, 255));
            if (path != null) {
                minilcd.displayPicture(0, 0, path);
            }
            //   minilcd.displayPictureByAbsolutePath(0, 0, path);
            if (msg.length() > 30)
                minilcd.displayString(5, 160, Color.rgb(0, 0, 0), Color.rgb(255, 255, 255), msg);
            else if (msg.length() > 20 && msg.length() < 30)
                minilcd.displayString(80, 160, Color.rgb(0, 0, 0), Color.rgb(255, 255, 255), msg);
            else
                minilcd.displayString(160, 160, Color.rgb(0, 0, 0), Color.rgb(255, 255, 255), msg);
        }
        minilcd.close();
        printBoolean=false;


    }
    // get the magnetic card response string
    public String[] getTracData() {
        return tracData;
    }

    // get the instance of magnetic card reader thread
    public ReadThread getReadThread() {
        return readThread;
    }

    //restart the magnetic thread if stop
    public void startReaderThread() {
        if (readThread.isInterrupted()) {
            readThread.start();
        }
    }

    public void setWidthAndHeight(Activity context) {
        WindowManager w = context.getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
// since SDK_INT = 1;
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                screenWidth = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                screenHeight = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17)
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                screenWidth = realSize.x;
                screenHeight = realSize.y;
            } catch (Exception ignored) {
            }
    }

    public void setGameBean(GameBean gameBean) {
        this.gameBean = gameBean;
        for (int i = 0; i < this.gameBean.getGames().length; i++) {
            GameBean.Games games = this.gameBean.getGames()[i];
            if (games.getGameCode().equalsIgnoreCase("OneToTwelve")) {
                fortune = games;
            } else if (games.getGameCode().equalsIgnoreCase("KenoTwo")) {
                luckyNumber = games;
            } else if (games.getGameCode().equalsIgnoreCase("TwelveByTwentyFour")) {
                miniKeno = games;
            } else if (games.getGameCode().equalsIgnoreCase("MiniRoulette")) {
                miniRoulette = games;
            } else if (games.getGameCode().equalsIgnoreCase("KenoSix")) {
                superKeno = games;
            }
        }
    }

    public GameBean getGameBean() {
        return gameBean;
    }

    public GameBean.Games getFortune() {
        return fortune;
    }

    public GameBean.Games getLuckyNumber() {
        return luckyNumber;
    }

    public GameBean.Games getMiniKeno() {
        return miniKeno;
    }

    public GameBean.Games getMiniRoulette() {
        return miniRoulette;
    }

    public GameBean.Games getSuperKeno() {
        return superKeno;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public void showAToast(String msg, Context context, int toastDuration) { //"Toast toast" is declared in the class

        try {
            toast.getView().isShown();     // true if visible
            toast.setText(msg);
        } catch (Exception e) {         // invisible if exception
            toast = Toast.makeText(context, msg, toastDuration);
        }
        toast.show();  //finally display it
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public String getLoginResponseOffLine() {
        responseOffLine = "{\n" +
                "\t\"isSuccess\": true,\n" +
                "\t\"errorMsg\": \"\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"retailerName\": \"testret\",\n" +
                "\t\t\"availableBalance\": 9.99999187E8,\n" +
                "\t\t\"retailerOrgCode\": 3,\n" +
                "\t\t\"sessionId\": \"EE3C579D0B22FA8FFFFD382731C4A04A\"\n" +
                "\t}\n" +
                "}";

        return responseOffLine;
    }

    public String getDrawGameFetchDataOffLine() {
        responseOffLine = "{\"responseCode\":0,\"responseMsg\":\"success\",\"games\":[{\"gameCode\":\"KenoTwo\",\"gameDispName\":\"Lucky Numbers\",\"ticketExpiry\":60,\"jackpotLimit\":0.0,\"gameType\":0,\"timeToFetchUpdatedGameInfo\":\"12-09-2016 13:14:00\",\"gameId\":1,\"bets\":[{\"unitPrice\":0.1,\"maxBetAmtMul\":1,\"betDispName\":\"Perm1\",\"betCode\":1,\"betName\":\"Perm1\"},{\"unitPrice\":0.1,\"maxBetAmtMul\":1,\"betDispName\":\"Perm2\",\"betCode\":2,\"betName\":\"Perm2\"},{\"unitPrice\":0.1,\"maxBetAmtMul\":1,\"betDispName\":\"Perm3\",\"betCode\":3,\"betName\":\"Perm3\"}],\"draws\":[{\"drawId\":83011,\"drawName\":\"Top-up Monday26\",\"drawDateTime\":\"12-09-2016 13:15:00\",\"drawFreezeTime\":\"12-09-2016 13:14:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":83012,\"drawName\":\"Top-up Monday27\",\"drawDateTime\":\"12-09-2016 13:30:00\",\"drawFreezeTime\":\"12-09-2016 13:29:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":83013,\"drawName\":\"Top-up Monday28\",\"drawDateTime\":\"12-09-2016 13:45:00\",\"drawFreezeTime\":\"12-09-2016 13:44:00\",\"drawStatus\":\"ACTIVE\"}],\"displayOrder\":1,\"drawFrequencyType\":\"L\",\"lastDrawFreezeTime\":\"12-09-2016 12:44:00\",\"lastDrawDateTime\":\"12-09-2016 12:45:00\",\"lastDrawWinningResult\":[]},{\"gameCode\":\"Zerotonine\",\"gameDispName\":\"Fast Lotto\",\"ticketExpiry\":60,\"jackpotLimit\":0.0,\"gameType\":0,\"timeToFetchUpdatedGameInfo\":\"12-09-2016 13:04:30\",\"lastDrawResult\":\"Zero(0)\",\"lastDrawTime\":\"12-07-2016 23:00:00\",\"gameId\":12,\"bets\":[{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"zeroToNine\",\"betCode\":1,\"betName\":\"zeroToNine\"}],\"draws\":[{\"drawId\":158727,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:05:00\",\"drawFreezeTime\":\"12-09-2016 13:04:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158728,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:10:00\",\"drawFreezeTime\":\"12-09-2016 13:09:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158729,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:15:00\",\"drawFreezeTime\":\"12-09-2016 13:14:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158730,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:20:00\",\"drawFreezeTime\":\"12-09-2016 13:19:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158731,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:25:00\",\"drawFreezeTime\":\"12-09-2016 13:24:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158732,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:30:00\",\"drawFreezeTime\":\"12-09-2016 13:29:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158733,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:35:00\",\"drawFreezeTime\":\"12-09-2016 13:34:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158734,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:40:00\",\"drawFreezeTime\":\"12-09-2016 13:39:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158735,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:45:00\",\"drawFreezeTime\":\"12-09-2016 13:44:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158736,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:50:00\",\"drawFreezeTime\":\"12-09-2016 13:49:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158737,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:55:00\",\"drawFreezeTime\":\"12-09-2016 13:54:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158738,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 14:00:00\",\"drawFreezeTime\":\"12-09-2016 13:59:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158739,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 14:05:00\",\"drawFreezeTime\":\"12-09-2016 14:04:30\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":158740,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 14:10:00\",\"drawFreezeTime\":\"12-09-2016 14:09:30\",\"drawStatus\":\"ACTIVE\"}],\"displayOrder\":1,\"drawFrequencyType\":\"L\",\"lastDrawFreezeTime\":\"12-09-2016 12:44:30\",\"lastDrawDateTime\":\"12-09-2016 12:45:00\",\"lastDrawWinningResult\":[{\"lastDrawDateTime\":\"12-07-2016 23:00:00\",\"winningNumber\":\"Zero(0)\"},{\"lastDrawDateTime\":\"12-07-2016 22:55:00\",\"winningNumber\":\"Eight(8)\"},{\"lastDrawDateTime\":\"12-07-2016 22:50:00\",\"winningNumber\":\"Eight(8)\"},{\"lastDrawDateTime\":\"12-07-2016 22:45:00\",\"winningNumber\":\"Four(4)\"},{\"lastDrawDateTime\":\"12-07-2016 22:40:00\",\"winningNumber\":\"One(1)\"}]},{\"gameCode\":\"TwelveByTwentyFour\",\"gameDispName\":\"Power Play\",\"ticketExpiry\":60,\"jackpotLimit\":0.0,\"gameType\":0,\"timeToFetchUpdatedGameInfo\":\"12-09-2016 19:30:00\",\"lastDrawResult\":\"22,16,2,19,9,15,24,6,4,7,11,1\",\"lastDrawTime\":\"11-07-2016 19:45:00\",\"gameId\":15,\"bets\":[{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"Direct12\",\"betCode\":1,\"betName\":\"Direct12\"},{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"First12\",\"betCode\":2,\"betName\":\"First12\"},{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"Last12\",\"betCode\":3,\"betName\":\"Last12\"},{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"AllOdd\",\"betCode\":4,\"betName\":\"AllOdd\"},{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"AllEven\",\"betCode\":5,\"betName\":\"AllEven\"},{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"OddEven\",\"betCode\":6,\"betName\":\"OddEven\"},{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"EvenOdd\",\"betCode\":7,\"betName\":\"EvenOdd\"},{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"JumpEvenOdd\",\"betCode\":8,\"betName\":\"JumpEvenOdd\"},{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"JumpOddEven\",\"betCode\":9,\"betName\":\"JumpOddEven\"},{\"unitPrice\":0.5,\"maxBetAmtMul\":100,\"betDispName\":\"Perm12\",\"betCode\":10,\"betName\":\"Perm12\"}],\"draws\":[{\"drawId\":237,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 19:45:00\",\"drawFreezeTime\":\"12-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":238,\"drawName\":\"null\",\"drawDateTime\":\"13-09-2016 19:45:00\",\"drawFreezeTime\":\"13-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":239,\"drawName\":\"null\",\"drawDateTime\":\"14-09-2016 19:45:00\",\"drawFreezeTime\":\"14-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":240,\"drawName\":\"null\",\"drawDateTime\":\"15-09-2016 19:45:00\",\"drawFreezeTime\":\"15-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":241,\"drawName\":\"null\",\"drawDateTime\":\"16-09-2016 19:45:00\",\"drawFreezeTime\":\"16-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"}],\"displayOrder\":1,\"drawFrequencyType\":\"L\",\"lastDrawFreezeTime\":\"11-09-2016 19:30:00\",\"lastDrawDateTime\":\"11-09-2016 19:45:00\",\"lastDrawWinningResult\":[{\"lastDrawDateTime\":\"11-07-2016 19:45:00\",\"winningNumber\":\"22,16,2,19,9,15,24,6,4,7,11,1\"},{\"lastDrawDateTime\":\"10-07-2016 19:45:00\",\"winningNumber\":\"14,13,12,1,19,11,4,20,21,15,9,2\"},{\"lastDrawDateTime\":\"09-07-2016 19:45:00\",\"winningNumber\":\"23,2,22,20,16,13,15,5,1,18,7,14\"},{\"lastDrawDateTime\":\"08-07-2016 19:45:00\",\"winningNumber\":\"7,18,11,8,10,6,1,5,13,21,2,4\"},{\"lastDrawDateTime\":\"07-07-2016 19:45:00\",\"winningNumber\":\"16,15,24,3,12,9,7,1,18,21,19,6\"}]},{\"gameCode\":\"ZimLottoBonus\",\"gameDispName\":\"Bonus Lotto\",\"ticketExpiry\":60,\"jackpotLimit\":50000.0,\"gameType\":0,\"timeToFetchUpdatedGameInfo\":\"14-09-2016 19:30:00\",\"lastDrawResult\":\"27,33,21,30,15,20,2\",\"lastDrawTime\":\"09-07-2016 19:45:00\",\"gameId\":5,\"bets\":[{\"unitPrice\":0.2,\"maxBetAmtMul\":100,\"betDispName\":\"Direct6\",\"betCode\":1,\"betName\":\"Direct6\"},{\"unitPrice\":0.2,\"maxBetAmtMul\":100,\"betDispName\":\"Perm6\",\"betCode\":2,\"betName\":\"Perm6\"}],\"draws\":[{\"drawId\":258,\"drawName\":\"null\",\"drawDateTime\":\"14-09-2016 19:45:00\",\"drawFreezeTime\":\"14-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":259,\"drawName\":\"null\",\"drawDateTime\":\"17-09-2016 19:45:00\",\"drawFreezeTime\":\"17-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":260,\"drawName\":\"null\",\"drawDateTime\":\"21-09-2016 19:45:00\",\"drawFreezeTime\":\"21-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":261,\"drawName\":\"null\",\"drawDateTime\":\"24-09-2016 19:45:00\",\"drawFreezeTime\":\"24-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":262,\"drawName\":\"null\",\"drawDateTime\":\"28-09-2016 19:45:00\",\"drawFreezeTime\":\"28-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":263,\"drawName\":\"null\",\"drawDateTime\":\"01-10-2016 19:45:00\",\"drawFreezeTime\":\"01-10-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"}],\"displayOrder\":1,\"drawFrequencyType\":\"L\",\"lastDrawFreezeTime\":\"10-09-2016 19:30:00\",\"lastDrawDateTime\":\"10-09-2016 19:45:00\",\"lastDrawWinningResult\":[{\"lastDrawDateTime\":\"09-07-2016 19:45:00\",\"winningNumber\":\"27,33,21,30,15,20,2\"},{\"lastDrawDateTime\":\"06-07-2016 19:45:00\",\"winningNumber\":\"19,27,17,13,8,38,14\"},{\"lastDrawDateTime\":\"02-07-2016 19:45:00\",\"winningNumber\":\"15,9,20,13,34,32,22\"},{\"lastDrawDateTime\":\"29-06-2016 19:45:00\",\"winningNumber\":\"20,33,9,24,22,11,8\"},{\"lastDrawDateTime\":\"25-06-2016 19:45:00\",\"winningNumber\":\"3,5,10,15,18,28,24\"}]},{\"gameCode\":\"MiniRoulette\",\"gameDispName\":\"roulette\",\"ticketExpiry\":60,\"jackpotLimit\":0.0,\"gameType\":0,\"timeToFetchUpdatedGameInfo\":\"12-09-2016 13:04:00\",\"gameId\":18,\"bets\":[{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"thirdRow\",\"betCode\":1,\"betName\":\"firstRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"secondRow\",\"betCode\":1,\"betName\":\"firstRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"firstQuarter\",\"betCode\":1,\"betName\":\"firstQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"firstRow\",\"betCode\":1,\"betName\":\"firstRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"blackNumbers\",\"betCode\":1,\"betName\":\"Roulette\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"fourthColumn\",\"betCode\":1,\"betName\":\"fourthColumn\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"redNumbers\",\"betCode\":1,\"betName\":\"Roulette\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"thirdColumn\",\"betCode\":1,\"betName\":\"thirdColumn\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"roulette\",\"betCode\":1,\"betName\":\"Roulette\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"secondColumn\",\"betCode\":1,\"betName\":\"secondColumn\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"sixthQuarter\",\"betCode\":1,\"betName\":\"sixthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"firstColumn\",\"betCode\":1,\"betName\":\"firstColumn\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"fifthQuarter\",\"betCode\":1,\"betName\":\"fifthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"allEvenNumbers\",\"betCode\":1,\"betName\":\"allEvenNumbers\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"fourthQuarter\",\"betCode\":1,\"betName\":\"fourthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"allOddNumbers\",\"betCode\":1,\"betName\":\"allOddNumbers\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"thirdQuarter\",\"betCode\":1,\"betName\":\"thirdQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"zero\",\"betCode\":1,\"betName\":\"zero\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"secondQuarter\",\"betCode\":1,\"betName\":\"secondQuarter\"}],\"draws\":[{\"drawId\":1335,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:05:00\",\"drawFreezeTime\":\"12-09-2016 13:04:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":1336,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:10:00\",\"drawFreezeTime\":\"12-09-2016 13:09:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":1337,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:15:00\",\"drawFreezeTime\":\"12-09-2016 13:14:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":1338,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:20:00\",\"drawFreezeTime\":\"12-09-2016 13:19:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":1339,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:25:00\",\"drawFreezeTime\":\"12-09-2016 13:24:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":1340,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:30:00\",\"drawFreezeTime\":\"12-09-2016 13:29:00\",\"drawStatus\":\"ACTIVE\"}],\"displayOrder\":1,\"drawFrequencyType\":\"L\",\"lastDrawFreezeTime\":\"12-09-2016 12:44:00\",\"lastDrawDateTime\":\"12-09-2016 12:45:00\",\"lastDrawWinningResult\":[]},{\"gameCode\":\"KenoSix\",\"gameDispName\":\"5/90 Spot Game\",\"ticketExpiry\":7,\"jackpotLimit\":0.0,\"gameType\":0,\"timeToFetchUpdatedGameInfo\":\"12-09-2016 20:30:00\",\"gameId\":11,\"bets\":[{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Direct1\",\"betCode\":1,\"betName\":\"Direct1\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Direct2\",\"betCode\":2,\"betName\":\"Direct2\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Direct3\",\"betCode\":3,\"betName\":\"Direct3\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Direct4\",\"betCode\":4,\"betName\":\"Direct4\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Direct5\",\"betCode\":5,\"betName\":\"Direct5\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Direct6\",\"betCode\":6,\"betName\":\"Direct6\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Direct7\",\"betCode\":7,\"betName\":\"Direct7\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Direct8\",\"betCode\":8,\"betName\":\"Direct8\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Direct9\",\"betCode\":9,\"betName\":\"Direct9\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Direct10\",\"betCode\":10,\"betName\":\"Direct10\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":1000,\"betDispName\":\"Perm2\",\"betCode\":11,\"betName\":\"Perm2\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"Perm3\",\"betCode\":12,\"betName\":\"Perm3\"}],\"draws\":[{\"drawId\":5,\"drawName\":\"MSP\",\"drawDateTime\":\"12-09-2016 20:50:00\",\"drawFreezeTime\":\"12-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":6,\"drawName\":\"TUESDAY LUCKY\",\"drawDateTime\":\"13-09-2016 20:50:00\",\"drawFreezeTime\":\"13-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":7,\"drawName\":\"MID WEEK\",\"drawDateTime\":\"14-09-2016 20:50:00\",\"drawFreezeTime\":\"14-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":8,\"drawName\":\"FORTUNE\",\"drawDateTime\":\"15-09-2016 20:50:00\",\"drawFreezeTime\":\"15-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":9,\"drawName\":\"BONANZA\",\"drawDateTime\":\"16-09-2016 20:50:00\",\"drawFreezeTime\":\"16-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":10,\"drawName\":\"NATIONAL\",\"drawDateTime\":\"17-09-2016 20:50:00\",\"drawFreezeTime\":\"17-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":11,\"drawName\":\"MSP\",\"drawDateTime\":\"19-09-2016 20:50:00\",\"drawFreezeTime\":\"19-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":12,\"drawName\":\"TUESDAY LUCKY\",\"drawDateTime\":\"20-09-2016 20:50:00\",\"drawFreezeTime\":\"20-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":13,\"drawName\":\"MID WEEK\",\"drawDateTime\":\"21-09-2016 20:50:00\",\"drawFreezeTime\":\"21-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":14,\"drawName\":\"FORTUNE\",\"drawDateTime\":\"22-09-2016 20:50:00\",\"drawFreezeTime\":\"22-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":15,\"drawName\":\"BONANZA\",\"drawDateTime\":\"23-09-2016 20:50:00\",\"drawFreezeTime\":\"23-09-2016 20:30:00\",\"drawStatus\":\"ACTIVE\"}],\"displayOrder\":1,\"drawFrequencyType\":\"L\",\"lastDrawFreezeTime\":\"10-09-2016 20:30:00\",\"lastDrawDateTime\":\"10-09-2016 20:50:00\",\"lastDrawWinningResult\":[]},{\"gameCode\":\"FullRoulette\",\"gameDispName\":\"FullRoulette\",\"ticketExpiry\":60,\"jackpotLimit\":0.0,\"gameType\":0,\"timeToFetchUpdatedGameInfo\":\"12-09-2016 13:14:00\",\"gameId\":17,\"bets\":[{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"secondHalf\",\"betCode\":1,\"betName\":\"secondHalf\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"firstQuarter\",\"betCode\":1,\"betName\":\"firstQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"sixthRow\",\"betCode\":1,\"betName\":\"sixthRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"eleventhRow\",\"betCode\":1,\"betName\":\"eleventhRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"twentySecondQuarter\",\"betCode\":1,\"betName\":\"twentySecondQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"zero\",\"betCode\":1,\"betName\":\"zero\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"thirdColumn\",\"betCode\":1,\"betName\":\"thirdColumn\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"eleventhQuarter\",\"betCode\":1,\"betName\":\"eleventhQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"firstHalf\",\"betCode\":1,\"betName\":\"firstHalf\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"roulette\",\"betCode\":1,\"betName\":\"roulette\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"fifthRow\",\"betCode\":1,\"betName\":\"fifthRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"tenthRow\",\"betCode\":1,\"betName\":\"tenthRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"twentyFirstQuarter\",\"betCode\":1,\"betName\":\"twentyFirstQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"secondColumn\",\"betCode\":1,\"betName\":\"secondColumn\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"tenthQuarter\",\"betCode\":1,\"betName\":\"tenthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"thirdDozen\",\"betCode\":1,\"betName\":\"thirdDozen\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"blackNumbers\",\"betCode\":1,\"betName\":\"blackNumbers\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"fourthRow\",\"betCode\":1,\"betName\":\"fourthRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"ninthRow\",\"betCode\":1,\"betName\":\"ninthRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"twentiethQuarter\",\"betCode\":1,\"betName\":\"twentiethQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"firstColumn\",\"betCode\":1,\"betName\":\"firstColumn\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"ninthQuarter\",\"betCode\":1,\"betName\":\"ninthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"secondDozen\",\"betCode\":1,\"betName\":\"secondDozen\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"redNumbers\",\"betCode\":1,\"betName\":\"redNumbers\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"thirdRow\",\"betCode\":1,\"betName\":\"thirdRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"eighthRow\",\"betCode\":1,\"betName\":\"eighthRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"ninteenthQuarter\",\"betCode\":1,\"betName\":\"ninteenthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"allEvenNumbers\",\"betCode\":1,\"betName\":\"allEvenNumbers\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"eighthQuarter\",\"betCode\":1,\"betName\":\"eighthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"firstDozen\",\"betCode\":1,\"betName\":\"firstDozen\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"street\",\"betCode\":1,\"betName\":\"street\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"secondRow\",\"betCode\":1,\"betName\":\"secondRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"seventhRow\",\"betCode\":1,\"betName\":\"seventhRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"eighteenthQuarter\",\"betCode\":1,\"betName\":\"eighteenthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"allOddNumbers\",\"betCode\":1,\"betName\":\"allOddNumbers\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"seventhQuarter\",\"betCode\":1,\"betName\":\"seventhQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"twelfthRow\",\"betCode\":1,\"betName\":\"twelfthRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"firstRow\",\"betCode\":1,\"betName\":\"firstRow\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"seventeenthQuarter\",\"betCode\":1,\"betName\":\"seventeenthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"sixthQuarter\",\"betCode\":1,\"betName\":\"sixthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"sixteenthQuarter\",\"betCode\":1,\"betName\":\"sixteenthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"fifthQuarter\",\"betCode\":1,\"betName\":\"fifthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"fifteenthQuarter\",\"betCode\":1,\"betName\":\"fifteenthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"fourthQuarter\",\"betCode\":1,\"betName\":\"fourthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"fourteenthQuarter\",\"betCode\":1,\"betName\":\"fourteenthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"thirdQuarter\",\"betCode\":1,\"betName\":\"thirdQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"thirteenthQuarter\",\"betCode\":1,\"betName\":\"thirteenthQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"secondQuarter\",\"betCode\":1,\"betName\":\"secondQuarter\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"twelfthQuarter\",\"betCode\":1,\"betName\":\"twelfthQuarter\"}],\"draws\":[{\"drawId\":47,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 13:15:00\",\"drawFreezeTime\":\"12-09-2016 13:14:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":48,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 16:15:00\",\"drawFreezeTime\":\"12-09-2016 16:14:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":49,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 19:15:00\",\"drawFreezeTime\":\"12-09-2016 19:14:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":50,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 22:15:00\",\"drawFreezeTime\":\"12-09-2016 22:14:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":51,\"drawName\":\"null\",\"drawDateTime\":\"13-09-2016 01:15:00\",\"drawFreezeTime\":\"13-09-2016 01:14:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":52,\"drawName\":\"null\",\"drawDateTime\":\"13-09-2016 07:15:00\",\"drawFreezeTime\":\"13-09-2016 07:14:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":53,\"drawName\":\"null\",\"drawDateTime\":\"13-09-2016 10:15:00\",\"drawFreezeTime\":\"13-09-2016 10:14:00\",\"drawStatus\":\"ACTIVE\"}],\"displayOrder\":1,\"drawFrequencyType\":\"L\",\"lastDrawFreezeTime\":\"12-09-2016 10:14:00\",\"lastDrawDateTime\":\"12-09-2016 10:15:00\",\"lastDrawWinningResult\":[]},{\"gameCode\":\"TenByTwenty\",\"gameDispName\":\"10-20-Game\",\"ticketExpiry\":7,\"jackpotLimit\":0.0,\"gameType\":0,\"timeToFetchUpdatedGameInfo\":\"12-09-2016 19:30:00\",\"lastDrawResult\":\"1,2,3,4,5,6,7,8,9,10\",\"lastDrawTime\":\"09-09-2016 19:45:00\",\"gameId\":16,\"bets\":[{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"Direct10\",\"betCode\":1,\"betName\":\"Direct10\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"First10\",\"betCode\":2,\"betName\":\"First10\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"Last10\",\"betCode\":3,\"betName\":\"Last10\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"AllOdd\",\"betCode\":4,\"betName\":\"AllOdd\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"AllEven\",\"betCode\":5,\"betName\":\"AllEven\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"OddEven\",\"betCode\":6,\"betName\":\"OddEven\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"EvenOdd\",\"betCode\":7,\"betName\":\"EvenOdd\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"JumpOddEven\",\"betCode\":8,\"betName\":\"JumpOddEven\"},{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"JumpEvenOdd\",\"betCode\":9,\"betName\":\"JumpEvenOdd\"}],\"draws\":[{\"drawId\":12,\"drawName\":\"null\",\"drawDateTime\":\"12-09-2016 19:45:00\",\"drawFreezeTime\":\"12-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":13,\"drawName\":\"null\",\"drawDateTime\":\"13-09-2016 19:45:00\",\"drawFreezeTime\":\"13-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":14,\"drawName\":\"null\",\"drawDateTime\":\"14-09-2016 19:45:00\",\"drawFreezeTime\":\"14-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":15,\"drawName\":\"null\",\"drawDateTime\":\"15-09-2016 19:45:00\",\"drawFreezeTime\":\"15-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":16,\"drawName\":\"null\",\"drawDateTime\":\"16-09-2016 19:45:00\",\"drawFreezeTime\":\"16-09-2016 19:30:00\",\"drawStatus\":\"ACTIVE\"}],\"displayOrder\":1,\"drawFrequencyType\":\"L\",\"lastDrawFreezeTime\":\"11-09-2016 19:30:00\",\"lastDrawDateTime\":\"11-09-2016 19:45:00\",\"lastDrawWinningResult\":[{\"lastDrawDateTime\":\"09-09-2016 19:45:00\",\"winningNumber\":\"1,2,3,4,5,6,7,8,9,10\"}]},{\"gameCode\":\"OneToTwelve\",\"gameDispName\":\"One To Twelve\",\"ticketExpiry\":60,\"jackpotLimit\":0.0,\"gameType\":0,\"timeToFetchUpdatedGameInfo\":\"12-09-2016 14:50:00\",\"gameId\":19,\"bets\":[{\"unitPrice\":10.0,\"maxBetAmtMul\":100,\"betDispName\":\"oneToTwelve\",\"betCode\":1,\"betName\":\"\"}],\"draws\":[{\"drawId\":14,\"drawName\":\"CONTINENTAL\",\"drawDateTime\":\"12-09-2016 15:00:00\",\"drawFreezeTime\":\"12-09-2016 14:50:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":15,\"drawName\":\"WINLOT JUBILEE\",\"drawDateTime\":\"12-09-2016 21:00:00\",\"drawFreezeTime\":\"12-09-2016 20:50:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":16,\"drawName\":\"Fantasy 5\",\"drawDateTime\":\"13-09-2016 11:00:00\",\"drawFreezeTime\":\"13-09-2016 10:50:00\",\"drawStatus\":\"ACTIVE\"},{\"drawId\":17,\"drawName\":\"GOLD COAST\",\"drawDateTime\":\"13-09-2016 15:00:00\",\"drawFreezeTime\":\"13-09-2016 14:50:00\",\"drawStatus\":\"ACTIVE\"}],\"displayOrder\":1,\"drawFrequencyType\":\"L\",\"lastDrawFreezeTime\":\"12-09-2016 10:50:00\",\"lastDrawDateTime\":\"12-09-2016 11:00:00\",\"lastDrawWinningResult\":[]}]}";
        return responseOffLine;
    }

    public String getKenoSixOffLineData() {
        responseOffLine = "{\"isSuccess\":true,\"errorMsg\":\"\",\"mainData\":{\"commonSaleData\":{\"ticketNumber\":\"82553325817128001\",\"gameName\":\"5/90 Spot Game\",\"purchaseDate\":\"2016-09-14\",\"purchaseTime\":\"12:58:27\",\"purchaseAmt\":10,\"barcodeCount\":\"82553325817128001074\",\"drawData\":[{\"drawDate\":\"14-09-2016\",\"drawName\":\"MID WEEK\",\"drawTime\":\"20:50:00\"}]},\"betTypeData\":[{\"isQp\":true,\"betName\":\"Direct10\",\"pickedNumbers\":\"01,09,21,39,42,44,49,63,66,74\",\"numberPicked\":\"10\",\"unitPrice\":10,\"noOfLines\":1,\"betAmtMul\":1,\"panelPrice\":10}],\"advMessage\":{},\"orgName\":\"Test Retailer\",\"userName\":\"testret\",\"AvlblCreditAmt\":\"999999999.00\"},\"isPromo\":false,\"errorCode\":2002}";
        return responseOffLine;
    }

    public String getOneTwoTweleveOffLineData() {
        responseOffLine = "{\"isSuccess\":true,\"errorMsg\":\"\",\"mainData\":{\"commonSaleData\":{\"ticketNumber\":\"15783825817128004\",\"barcodeCount\":\"15783825817128004059\",\"gameName\":\"One To Twelve\",\"purchaseDate\":\"2016-09-14\",\"purchaseTime\":\"13:00:15\",\"purchaseAmt\":20,\"drawData\":[{\"drawDate\":\"14-09-2016\",\"drawName\":\"WINLOT SPECIAL\",\"drawTime\":\"15:00:00\"}]},\"betTypeData\":[{\"isQp\":true,\"betName\":\"oneToTwelve\",\"pickedNumbers\":\"1\",\"numberPicked\":\"1\",\"unitPrice\":10,\"noOfLines\":1,\"betAmtMul\":1,\"panelPrice\":10},{\"isQp\":true,\"betName\":\"oneToTwelve\",\"pickedNumbers\":\"10\",\"numberPicked\":\"1\",\"unitPrice\":10,\"noOfLines\":1,\"betAmtMul\":1,\"panelPrice\":10}],\"advMessage\":{},\"orgName\":\"Test Retailer\",\"userName\":\"testret\",\"AvlblCreditAmt\":\"999999999.00\"},\"isPromo\":false,\"errorCode\":2002}";
        return responseOffLine;
    }

    public String getMiniRouletteOffData() {
        responseOffLine = "{\"isSuccess\":true,\"errorMsg\":\"\",\"mainData\":{\"commonSaleData\":{\"ticketNumber\":\"26843225817128001\",\"barcodeCount\":\"26843225817128001016\",\"gameName\":\"roulette\",\"purchaseDate\":\"2016-09-14\",\"purchaseTime\":\"13:03:53\",\"purchaseAmt\":20,\"drawData\":[{\"drawDate\":\"14-09-2016\",\"drawTime\":\"13:20:00\"}]},\"betTypeData\":[{\"isQp\":false,\"betName\":\"zero\",\"pickedNumbers\":\"0\",\"numberPicked\":\"1\",\"unitPrice\":10,\"noOfLines\":1,\"betAmtMul\":1,\"panelPrice\":10},{\"isQp\":false,\"betName\":\"roulette\",\"pickedNumbers\":\"01\",\"numberPicked\":\"1\",\"unitPrice\":10,\"noOfLines\":1,\"betAmtMul\":1,\"panelPrice\":10}],\"advMessage\":{},\"orgName\":\"Test Retailer\",\"userName\":\"testret\"},\"isPromo\":false}";
        return responseOffLine;
    }

    public String getReprintTicketOffLineData() {
        responseOffLine = "{\"isSuccess\":true,\"errorMsg\":\"\",\"mainData\":{\"commonReprintData\":{\"ticketNumber\":\"268432258171280011\",\"reprintCount\":\"1\",\"barcodeCount\":\"26843225817128001116\",\"gameName\":\"roulette\",\"gameDevName\":\"MiniRoulette\",\"purchaseDate\":\"2016-09-14\",\"purchaseTime\":\"13:03:53.0\",\"purchaseAmt\":20,\"drawData\":[{\"drawDate\":\"2016-09-14\",\"drawTime\":\"13:20:00\"}]},\"betTypeData\":[{\"isQp\":false,\"betName\":\"zero\",\"pickedNumbers\":\"0\",\"unitPrice\":10,\"noOfLines\":1,\"betAmtMul\":1,\"panelPrice\":10},{\"isQp\":false,\"betName\":\"Roulette\",\"pickedNumbers\":\"1\",\"unitPrice\":10,\"noOfLines\":1,\"betAmtMul\":1,\"panelPrice\":10}],\"advMessage\":{},\"orgName\":\"Test Retailer\",\"userName\":\"testret\"},\"isPromo\":false}";
        return responseOffLine;
    }

    public void setTps515PrintCall(Tps515PrintCall tps515PrintCall){
        this.tps515PrintCall = tps515PrintCall;
    }

    public Tps515PrintCall getTps515PrintCall(){
        return tps515PrintCall;
    }


}
