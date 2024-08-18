package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.TpsSplashActivity;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.ConstantField;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;
import demo.stpl.com.tpsmergedbuild.beans.SportsSaleBean;
import demo.stpl.com.tpsmergedbuild.betslip.ActivityBetSlipCamera;
import demo.stpl.com.tpsmergedbuild.dialog.WinnigDialog;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;

//import skilrock.com.tpsgame.R;
//import tpsgames.HttpRequest;
//import tpsgames.TpsSplashActivity;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.ConstantField;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.GameBean;
//import tpsgames.beans.SportsSaleBean;
//import tpsgames.betslip.ActivityBetSlipCamera;
//import tpsgames.dialog.WinnigDialog;
//import tpsgames.interfaces.ResponseLis;
//import tpsgames.interfaces.ServerResponse;

//import tpsgames.interfaces.ServerCommClass;

//import com.example.mylibrary.Beans.FatchGameData;
//import com.sparkzeal.sportslottery.ActivityTpsSports;
//import com.tablet.stpl.comman.interfaces.DisplayChangeListener;
//import com.tablet.stpl.comman.interfaces.ServerCommClass;
//import com.tablet.stpl.comman.interfaces.ServerRequest;
//import com.tablet.stpl.comman.interfaces.ServerResponse;

/**
 * Created by stpl on 9/13/2016.
 */
public class HomeActivityTpsGame extends Activity implements View.OnClickListener, ResponseLis {

    private LinearLayout drawGames, sportsGames;
    private boolean isClickPerformed;
   // private ServerResponse serverResponse;
    private String gameId, gameTypeId, drawInfo, drawCount, ticketAmt, merCode, LSTktNo, simType, deviceType, reqCounter, respCounter;
    Intent intent;
    String url;
    private WinnigDialog winnigDialog;
    HttpRequest httpRequest;
    String playerId;

    private String requestServer;
    private String methodNameServer;

    public boolean isValueSetToDialog;


    private String callType;

    private boolean isSwipeCall;

    static {
        System.loadLibrary("iconv");
    }

    View.OnClickListener negativeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            TpsGamesClass.getInstance().closeScreen(HomeActivityTpsGame.this);
//            ServerCommClass.getServerCommClass().getOnKeyListener().onFragmentChange();
            winnigDialog.dismiss();
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
//        if (ServerCommClass.getServerCommClass().getOnKeyListener() != null)
//            ServerCommClass.getServerCommClass().getOnKeyListener().onFragmentChange();
    }

//    DisplayChangeListener displayChangeListener = new DisplayChangeListener() {
//        @Override
//        public void changeDisplay(String gameName) {
//            TpsGamesClass.getInstance().closeScreen(HomeActivityTpsGame.this);
//            if (gameName.equalsIgnoreCase("MiniRoulette")) {
//                TpsGamesClass.getInstance().saveImage("mini_roulette.png", HomeActivityTpsGame.this);
//                TpsGamesClass.getInstance().pcsoIcon("/sdcard/mini_roulette.png");
//                TpsGamesClass.getInstance().displayScreen(HomeActivityTpsGame.this);
//            } else if (gameName.equalsIgnoreCase("FiveByNinty")) {
//                TpsGamesClass.getInstance().saveImage("five_nine.png", HomeActivityTpsGame.this);
//                TpsGamesClass.getInstance().pcsoIcon("/sdcard/five_nine.png");
//                TpsGamesClass.getInstance().displayScreen(HomeActivityTpsGame.this);
//
//            } else if (gameName.equalsIgnoreCase("TwelveByTwentyFour")) {
//                TpsGamesClass.getInstance().saveImage("twleve_by_twenty.png", HomeActivityTpsGame.this);
//                TpsGamesClass.getInstance().pcsoIcon("/sdcard/twleve_by_twenty.png");
//                TpsGamesClass.getInstance().displayScreen(HomeActivityTpsGame.this);
//            } else if (gameName.equalsIgnoreCase("OneByTwelve")) {
//                TpsGamesClass.getInstance().saveImage("fortune.png", HomeActivityTpsGame.this);
//                TpsGamesClass.getInstance().pcsoIcon("/sdcard/fortune.png");
//                TpsGamesClass.getInstance().displayScreen(HomeActivityTpsGame.this);
//            }
//        }
//    };

    View.OnClickListener positiveClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (winnigDialog.getEditTextValue().trim().length() > 0) {

                if (winnigDialog.getEditTextValue().toString().trim().length() > 10) {
                    cardSwipeVerify(winnigDialog.getEditTextValue().toString(), "");
                } else {

                    cardSwipeVerify("", winnigDialog.getEditTextValue().toString());
                }
            } else {
//                TpsGamesClass.getInstance().showAToast("Please Enter Some Value", ServerCommClass.getServerCommClass().getFragmentActivity(), Toast.LENGTH_SHORT);
            }
        }
    };


    private void shoDialog() {
        TpsGamesClass.getInstance().closeScreen(this);

        TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
        String url = "/sdcard/rainbow_header.png";
        TpsGamesClass.getInstance().pleaseWait("Swipe Your Card...", url);
        TpsGamesClass.getInstance().displayScreen(this);

      //  winnigDialog = (WinnigDialog) new WinnigDialog(ServerCommClass.getServerCommClass().getFragmentActivity()).setNegativeButton("CANCEL", negativeClick).setPositiveButton("DONE", positiveClick).setTitle("WINNING CLAIM");
        winnigDialog.setMessages("Enter Your Mobile Number or Swipe Your Card.");
        winnigDialog.setTitle("Player Verification");
        winnigDialog.setEditInput(true);

        winnigDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_home_screen);

      //  ServerCommClass.getServerCommClass().setDisplayChangeListener(displayChangeListener);
        gameId = "1";
        gameTypeId = "1";
        respCounter = "31";
        reqCounter = "31";
        drawInfo = "957~1~4335@A$4343@H,A$4337@H$4338@A$4333@H,A$4334@H$4346@A$4340@H,A$4341@H$4336@A$4342@H,A$4339@H$4344@A$";
        ticketAmt = "16.0";
        drawCount = "1";
        merCode = "RMS";
        simType = "ECONET";
        LSTktNo = "0";
        deviceType = "TPS580";
        drawGames = (LinearLayout) findViewById(R.id.draws_games);
        sportsGames = (LinearLayout) findViewById(R.id.sports_games);
        drawGames.setOnClickListener(this);
        sportsGames.setOnClickListener(this);
//        ServerCommClass.getServerCommClass().setServerRequest(serverRequest);
//        serverResponse = ServerCommClass.getServerCommClass().getServerResponse();
        TpsGamesClass.getInstance().setHomeActivityTpsGame(this);
        drawGames.performClick();

//        String url = TpsGamesClass.getInstance().getTicketSaleServicesForSle(this,TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName(),gameId,gameTypeId,drawInfo,drawCount,ticketAmt);
//        HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, HomeActivityTpsGame.this, "verifyUser", HomeActivityTpsGame.this, "sports");
//        TpsGamesClass.getInstance().startLoader(HomeActivityTpsGame.this);
//        httpRequest.executeTask();
    }

    public synchronized void cardSwipeVerify(String playerId, String mobileNumber) {
        playerId = playerId.replaceAll("[^0-9]", "");
        if (winnigDialog != null && !isValueSetToDialog && mobileNumber.trim().length() == 0) {
            winnigDialog.dismiss();
        }

        TpsGamesClass.getInstance().closeScreen(this);

        TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
        String url = "/sdcard/rainbow_header.png";
        TpsGamesClass.getInstance().pleaseWait("Verifying Player...", url);
        TpsGamesClass.getInstance().displayScreen(this);
        this.playerId = playerId;
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            if (playerId.trim().length() > 0) {
                jsonObject.put("playerId", playerId);
                TpsGamesClass.getInstance().setTopMessage("Player Id:- " + playerId);
            } else if (mobileNumber.trim().length() > 0) {
                jsonObject.put("mobileNum", mobileNumber);
                TpsGamesClass.getInstance().setTopMessage("Player No.:- " + mobileNumber);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        url = Utility.rGGamingUrl;
        HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, HomeActivityTpsGame.this, "verifyUser", HomeActivityTpsGame.this, "rgCall", Utility.rGGamingHeader);
       // TpsGamesClass.getInstance().startLoader(ServerCommClass.getServerCommClass().getFragmentActivity());
        httpRequest.setIsParams(true, jsonObject.toString());
        httpRequest.executeTask();
    }


    private void serverRequestCall(String serverRequest) {
        if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.ONETOTWELVE)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/oneToTwelveBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, HomeActivityTpsGame.this, "oneTwoTwelve", HomeActivityTpsGame.this, methodNameServer);
          //  TpsGamesClass.getInstance().startLoader(ServerCommClass.getServerCommClass().getFragmentActivity());
            httpRequest.executeTask();
            if (winnigDialog != null)
                winnigDialog.dismiss();
        } else if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.TWELVEBYTWENTYFOUR)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/twelveByTwentyFourBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, HomeActivityTpsGame.this, methodNameServer, HomeActivityTpsGame.this, methodNameServer);
           // TpsGamesClass.getInstance().startLoader(ServerCommClass.getServerCommClass().getFragmentActivity());
            httpRequest.executeTask();
            if (winnigDialog != null)
                winnigDialog.dismiss();
        } else if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.MINIROULETTE)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/oneToTwelveRouletteBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, HomeActivityTpsGame.this, methodNameServer, HomeActivityTpsGame.this, methodNameServer);
         //   TpsGamesClass.getInstance().startLoader(ServerCommClass.getServerCommClass().getFragmentActivity());
            httpRequest.executeTask();
            if (winnigDialog != null)
                winnigDialog.dismiss();
        }
    }

    public String getExtraParms(Object response) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject((String) response);
            jsonObject.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());
            jsonObject.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject != null ? jsonObject.toString() : "";
    }

    protected void moveToLogin() {
        Intent intent = new Intent(HomeActivityTpsGame.this, LoginActivityTpsGame.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


   // ServerRequest serverRequest = new ServerRequest() {
      //  @Override
        public void request(Object request, String methodName) {

            if(methodName.equalsIgnoreCase("openProgress")){
             //   TpsGamesClass.getInstance().startLoader(ServerCommClass.getServerCommClass().getFragmentActivity());
            }else if(methodName.equalsIgnoreCase("closeProgress")){
                TpsGamesClass.getInstance().stopLoader();
            }
            else if (methodName.equalsIgnoreCase(ConstantField.ONETOTWELVE)) {
                requestServer = (String) request;
                methodNameServer = methodName;
                if (!TpsGamesClass.getInstance().getPlayerId()) {
                    if (!TpsGamesClass.getInstance().getPlayerId()) {
                        TpsGamesClass.getInstance().openCard();
                        shoDialog();
                    } else {

                      //  TpsGamesClass.getInstance().showAToast(TpsGamesClass.getInstance().getMessageForNotVarified().trim().length() == 0 ? "Player Not Verified" : TpsGamesClass.getInstance().getMessageForNotVarified(), ServerCommClass.getServerCommClass().getFragmentActivity(), Toast.LENGTH_SHORT);
                    }
                    return;
                }


            } else if (ConstantField.BETSLIP.equalsIgnoreCase(methodName)) {
//                Toast.makeText(HomeActivityTpsGame.this, methodName + " requestServer", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivityTpsGame.this, ActivityBetSlipCamera.class));
              //  ServerCommClass.getServerCommClass().getServerResponse().response("resposne of betSlip");
            } else if (ConstantField.EBETSLIP.equalsIgnoreCase(methodName)) {
//                Toast.makeText(HomeActivityTpsGame.this, methodName + " requestServer", Toast.LENGTH_SHORT).show();
              //  ServerCommClass.getServerCommClass().getServerResponse().response("resposne of betSlip");
                TpsGamesClass.getInstance().closeScreen(HomeActivityTpsGame.this);

//                TpsGamesClass.getInstance().saveImage("rainbow_header.png", HomeActivityTpsGame.this);
//                String url = "/sdcard/rainbow_header.png";
//                TpsGamesClass.getInstance().pleaseWait(jsonObject.optString("responseMsg"), url);
//                TpsGamesClass.getInstance().displayScreen(this);
                startActivity(new Intent(HomeActivityTpsGame.this, ActivityEbetSlipTpsGame.class));
            } else if (ConstantField.DGE.equalsIgnoreCase(methodName)) {
//                Toast.makeText(HomeActivityTpsGame.this, methodName + " requestServer", Toast.LENGTH_SHORT).show();
              //  ServerCommClass.getServerCommClass().getServerResponse().response("resposne of betSlip");
                drawGames.performClick();
            } else if (ConstantField.SLE.equalsIgnoreCase(methodName)) {
//                Toast.makeText(HomeActivityTpsGame.this, methodName + " request", Toast.LENGTH_SHORT).show();
                TpsGamesClass.getInstance().closeScreen(HomeActivityTpsGame.this);
               // ServerCommClass.getServerCommClass().getServerResponse().response("resposne of betSlip");
                sportsGames.performClick();
            } else if (methodName.equalsIgnoreCase("SLE_SALE")) {
                SportsSaleBean sportsSaleBean = TpsGamesClass.getInstance().getSportsBean((String) request);

                String gameId = sportsSaleBean.getGameId().substring(sportsSaleBean.getGameId().indexOf("=") + 1, sportsSaleBean.getGameId().length());
                String gameTypeId = sportsSaleBean.getGameTypeId().substring(sportsSaleBean.getGameTypeId().indexOf("=") + 1, sportsSaleBean.getGameTypeId().length());
                String drawCoount = sportsSaleBean.getDrawCount().substring(sportsSaleBean.getDrawCount().indexOf("=") + 1, sportsSaleBean.getDrawCount().length());
                String ticketAmount = sportsSaleBean.getTicketAmt().substring(sportsSaleBean.getTicketAmt().indexOf("=") + 1, sportsSaleBean.getTicketAmt().length());
                String url = TpsGamesClass.getInstance().getTicketSaleServicesForSle(HomeActivityTpsGame.this, TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName()
                        , gameId, gameTypeId, sportsSaleBean.getDrawInfoString().substring(sportsSaleBean.getDrawInfoString().indexOf("=") + 1, sportsSaleBean.getDrawInfoString().length()), drawCoount, ticketAmount, sportsSaleBean);
//                url = url + "&tokenId=" + responseData.getTokenId();
                HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, HomeActivityTpsGame.this, "ebet slip", HomeActivityTpsGame.this, "SaleSports", null);
               // TpsGamesClass.getInstance().startLoader(ServerCommClass.getServerCommClass().getFragmentActivity());
                httpRequest.executeTask();
              //  ServerCommClass.getServerCommClass().getServerResponse().response("resposne of betSlip");
//                sportsGames.performClick();
            } else if (ConstantField.WINNINGCLAIM.equalsIgnoreCase(methodName)) {
//                Communcations.winningDialog(ServerCommClass.getServerCommClass().getFragmentActivity());
                TpsGamesClass.getInstance().closeScreen(HomeActivityTpsGame.this);
                intent = new Intent(HomeActivityTpsGame.this, ClaimWinningActivity.class);
                startActivity(intent);
//                Toast.makeText(HomeActivityTpsGame.this, methodName + " requestServer", Toast.LENGTH_SHORT).show();
              //  ServerCommClass.getServerCommClass().getServerResponse().response("resposne of winning");
            } else if (ConstantField.DOWNLOADDIALOG.equalsIgnoreCase(methodName)) {
//                Toast.makeText(HomeActivityTpsGame.this, methodName + " requestServer", Toast.LENGTH_SHORT).show();
              //  ServerCommClass.getServerCommClass().getServerResponse().response("resposne of downloading");
            } else if (ConstantField.TWELVEBYTWENTYFOUR.equalsIgnoreCase(methodName)) {
                requestServer = (String) request;
                methodNameServer = methodName;
                if (!TpsGamesClass.getInstance().getPlayerId()) {
                    if (!TpsGamesClass.getInstance().getPlayerId()) {
                        TpsGamesClass.getInstance().openCard();
                        shoDialog();
                    } else {

                       // TpsGamesClass.getInstance().showAToast(TpsGamesClass.getInstance().getMessageForNotVarified().trim().length() == 0 ? "Player Not Verified" : TpsGamesClass.getInstance().getMessageForNotVarified(), ServerCommClass.getServerCommClass().getFragmentActivity(), Toast.LENGTH_SHORT);
                    }
                    return;
                }


            } else if (ConstantField.MINIROULETTE.equalsIgnoreCase(methodName)) {
                requestServer = (String) request;
                methodNameServer = methodName;
                if (!TpsGamesClass.getInstance().getPlayerId()) {
                    if (!TpsGamesClass.getInstance().getPlayerId()) {
                        TpsGamesClass.getInstance().openCard();
                        shoDialog();
                    } else {

                      //  TpsGamesClass.getInstance().showAToast(TpsGamesClass.getInstance().getMessageForNotVarified().trim().length() == 0 ? "Player Not Verified" : TpsGamesClass.getInstance().getMessageForNotVarified(), ServerCommClass.getServerCommClass().getFragmentActivity(), Toast.LENGTH_SHORT);
                    }
                    return;
                }


            } else if (methodName.equalsIgnoreCase("drawDialog")) {
              //  DrawGameDialogs.showDrawDialog(ServerCommClass.getServerCommClass().getFragmentActivity(), (FatchGameData.Games.Draws[]) request);
//                ServerCommClass.getServerCommClass().getServerResponse().response("");
            } else if (methodName.equalsIgnoreCase("quick")) {
                String[] unitPrize = request.toString().split("&");
              //  Communcations.numberPickedDialogRequest(ServerCommClass.getServerCommClass().getFragmentActivity(), unitPrize[0], unitPrize[1]);
            } else if (methodName.equalsIgnoreCase("betAmtDialog")) {
                String[] betAmts = request.toString().split("&");
             //   DrawGameDialogs.showBetAmountDialog(ServerCommClass.getServerCommClass().getFragmentActivity(), Double.parseDouble(betAmts[0]), Double.parseDouble(betAmts[2]),
                    //    Double.parseDouble(betAmts[1]), Double.parseDouble(betAmts[3]));
            } else if (methodName.equalsIgnoreCase("fatchGameData")) {
                TpsGamesClass.getInstance().startLoader(HomeActivityTpsGame.this);
             //   ServerCommClass.getServerCommClass().getFragmentActivity().finish();
                drawGames.performClick();
//                url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/fetchDrawGameDataPCPOS.action";
//                httpRequest = TpsGamesClass.getInstance(HomeActivityTpsGame.this).getHttpRequest(url, HomeActivityTpsGame.this, "fetching game data", HomeActivityTpsGame.this, "gameData");
//                httpRequest.executeTask();
            } else if (methodName.equalsIgnoreCase("KenoTwo")) {

                String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/kenoTwoBuy.action?json=" + getExtraParms(request);
//                url = url.replaceAll("\"", "%22");
//                try {
//                    url =  URLEncoder.encode(url, "UTF-8")
//                            .replaceAll("\\+", "%20")
//                            .replaceAll("\\%21", "!")
//                            .replaceAll("\\%27", "'")
//                            .replaceAll("\\%28", "(")
//                            .replaceAll("\\%29", ")")
//                            .replaceAll("\\%7E", "~");;
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                url = Uri.parse(url).toString();
                HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, HomeActivityTpsGame.this, methodName, HomeActivityTpsGame.this, methodName);
//                TpsGamesClass.getInstance().startLoader(ServerCommClass.getServerCommClass().getFragmentActivity());
                httpRequest.executeTask();
            } else if (methodName.equalsIgnoreCase("13")) {

                TpsGamesClass.getInstance().closeScreen(HomeActivityTpsGame.this);

                TpsGamesClass.getInstance().saveImage("soccer_thirteen.png", HomeActivityTpsGame.this);
                String url = "/sdcard/soccer_thirteen.png";
                TpsGamesClass.getInstance().pcsoIcon(url);
                TpsGamesClass.getInstance().displayScreen(HomeActivityTpsGame.this);
            } else if (methodName.equalsIgnoreCase("6")) {
                TpsGamesClass.getInstance().closeScreen(HomeActivityTpsGame.this);

                TpsGamesClass.getInstance().saveImage("soccer_six.png", HomeActivityTpsGame.this);
                String url = "/sdcard/soccer_six.png";
                TpsGamesClass.getInstance().pcsoIcon(url);
                TpsGamesClass.getInstance().displayScreen(HomeActivityTpsGame.this);
            } else if (methodName.contains("betSlip")) {
                requestServer = (String) request;
                methodNameServer = methodName.replace("betSlip", "");
                serverRequestCall(methodNameServer);
            }
        }
    //};

    @Override
    public void onClick(View v) {
        if (isClickPerformed) {
            return;
        }
        switch (v.getId()) {
            case R.id.draws_games:

                if (isClickPerformed) {
                    return;
                }
                isClickPerformed = true;
                url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/fetchDrawGameDataPCPOS.action";
                httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(url, this, "fetching game data", this, "gameData");
                TpsGamesClass.getInstance().startLoader(this);
                httpRequest.executeTask();

                break;
            case R.id.sports_games:

                if (isClickPerformed) {
                    return;
                }
                isClickPerformed = true;
                url = Utility.baseUrl + Utility.portNumber + Utility.sportProjectName;
                httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(url, this, "fetching game data", this, "sports", Utility.eBetSlipHeader);
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("merchantCode", "PMS");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                httpRequest.setIsParams(true, jsonObject.toString());
                TpsGamesClass.getInstance().startLoader(this);
                httpRequest.executeTask();
                break;
        }

    }


    @Override
    public void onResponse(String response, String requestedMethod) {
//        ErrorMsg:Time Out, Login Again!!|ErrorCode:01|
//        {"isSuccess":false,"errorCode":1008,"errorMsg":"Invalid Ticket"}

        TpsGamesClass.getInstance().stopLoader();
        isClickPerformed = false;
        isSwipeCall = false;
        //{"isSuccess":false,"errorCode":2002,"errorMsg":"Some Internal Error !"}
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject == null) {
             //   if (ServerCommClass.getServerCommClass().getFragmentActivity() == null) {
                    moveToLogin();
              //  }
                TpsGamesClass.getInstance().showAToast("No data available!", HomeActivityTpsGame.this, Toast.LENGTH_SHORT);
                return;
            } else if ((jsonObject.has("isSuccess") && jsonObject.optBoolean("isSuccess") == false)) {
               // if (ServerCommClass.getServerCommClass().getFragmentActivity() == null) {
                    moveToLogin();
              //  }
                TpsGamesClass.getInstance().showAToast(jsonObject.optString("errorMsg"), HomeActivityTpsGame.this, Toast.LENGTH_SHORT);
                return;
            } else if (jsonObject.has("responseCode") && jsonObject.optString("responseCode").equals("500")) {
              //  if (ServerCommClass.getServerCommClass().getFragmentActivity() == null) {
                    moveToLogin();
              //  }
                TpsGamesClass.getInstance().showAToast(jsonObject.optString("responseMsg"), HomeActivityTpsGame.this, Toast.LENGTH_SHORT);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (response.equalsIgnoreCase("failed")) {
            //if (ServerCommClass.getServerCommClass().getFragmentActivity() == null) {
                moveToLogin();
          //  }
            TpsGamesClass.getInstance().showAToast("Server Error", HomeActivityTpsGame.this, Toast.LENGTH_SHORT);
            return;
        }

        if (response.contains("login again")) {
            TpsGamesClass.getInstance().showAToast("Timer Out", HomeActivityTpsGame.this, Toast.LENGTH_SHORT);
            Intent intent = new Intent(HomeActivityTpsGame.this, TpsSplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return;
        }

        if (requestedMethod.equalsIgnoreCase("fail")) {
          //  if (ServerCommClass.getServerCommClass().getFragmentActivity() == null) {
                moveToLogin();
          //  }
            TpsGamesClass.getInstance().showAToast("No data available!", HomeActivityTpsGame.this, Toast.LENGTH_SHORT);
            return;
        } else if (requestedMethod.equals("sports")) {

            if (response.contains("<HTML>")) {
              //  TpsGamesClass.getInstance().showAToast("Connect to internet", ServerCommClass.getServerCommClass().getFragmentActivity(), Toast.LENGTH_SHORT);
                return;
            }
            TpsGamesClass.getInstance().setSportsResponse(response);

//            intent = new Intent(HomeActivityTpsGame.this, ActivityTpsSports.class);
//            intent.putExtra("game", response);
//            intent.putExtra("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
//            intent.putExtra("playerBalance", TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance() + "");
//            startActivity(intent);
        } else if (requestedMethod.equals("gameData")) {


            TpsGamesClass.getInstance().setGameBean(TpsGamesClass.getInstance().getGson().fromJson(response, GameBean.class));


//            USERNAME = getIntent().getStringExtra("userName") != null ? getIntent().getStringExtra("userName") : "";
//            SESSIONID = getIntent().getStringExtra("sessionId") != null ? getIntent().getStringExtra("sessionId") : "";
//            LASTDRAWRESULT = getIntent().getStringExtra("lastDrawResult") != null ? getIntent().getStringExtra("lastDrawResult") : "";
//            PLAYERBALANCE = getIntent().getStringExtra("playerBalance") != null ? getIntent().getStringExtra("playerBalance") : ""
           if(TpsGamesClass.getInstance().getDeviceName().contains("390"))
               intent = new Intent(HomeActivityTpsGame.this, ActivityDgeGamesTps390.class);
            else
            intent = new Intent(HomeActivityTpsGame.this, ActivityDgeGames.class);
            intent.putExtra("game", response);
            intent.putExtra("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            intent.putExtra("playerBalance", TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance() + "");
            startActivity(intent);
            finish();
        } else if (requestedMethod.equalsIgnoreCase(ConstantField.TWELVEBYTWENTYFOUR)) {
            TpsGamesClass.getInstance().closeScreen(this);
            methodNameServer = "";
            requestServer = "";
            intent = new Intent(HomeActivityTpsGame.this, PrintActivityAllGames.class);
            intent.putExtra("response", response);
            startActivity(intent);
           // ServerCommClass.getServerCommClass().getServerResponse().response(response);
        } else if (requestedMethod.equalsIgnoreCase(ConstantField.ONETOTWELVE)) {
            TpsGamesClass.getInstance().closeScreen(this);
            methodNameServer = "";
            requestServer = "";
            intent = new Intent(HomeActivityTpsGame.this, PrintActivityAllGames.class);
            intent.putExtra("response", response);
            startActivity(intent);
         //   ServerCommClass.getServerCommClass().getServerResponse().response(response);
        } else if (requestedMethod.equalsIgnoreCase("KenoTwo")) {
            methodNameServer = "";
            requestServer = "";
            intent = new Intent(HomeActivityTpsGame.this, PrintActivityAllGames.class);
            intent.putExtra("response", response);
            startActivity(intent);
          //  ServerCommClass.getServerCommClass().getServerResponse().response(response);
        } else if (requestedMethod.equalsIgnoreCase(ConstantField.MINIROULETTE)) {
            TpsGamesClass.getInstance().closeScreen(this);
            methodNameServer = "";
            requestServer = "";
            intent = new Intent(HomeActivityTpsGame.this, PrintActivityAllGames.class);
            intent.putExtra("response", response);
            startActivity(intent);
          //  ServerCommClass.getServerCommClass().getServerResponse().response(response);
        } else if (requestedMethod.equals("rgCall")) {
            TpsGamesClass.getInstance().stopCard();
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject != null && jsonObject.optInt("responseCode") == 100) {

                    TpsGamesClass.getInstance().setPlayerVerified(true, "");
                    try {

                    } catch (Exception e) {

                    }
                    if (methodNameServer.trim().length() > 0) {
                        serverRequestCall(HomeActivityTpsGame.this.methodNameServer);
                    }
                } else {
                    try {

                        if (methodNameServer != null && methodNameServer.trim().length() > 0) {

                            TpsGamesClass.getInstance().closeScreen(this);

                            TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
                            String url = "/sdcard/rainbow_header.png";
                            TpsGamesClass.getInstance().pleaseWait(jsonObject.optString("responseMsg"), url);
                            TpsGamesClass.getInstance().displayScreen(this);
                       //     TpsGamesClass.getInstance().showAToast(jsonObject.optString("responseMsg"), ServerCommClass.getServerCommClass().getFragmentActivity(), Toast.LENGTH_SHORT);
                        }
                    } catch (Exception e) {

                    }

                    TpsGamesClass.getInstance().setPlayerVerified(false, jsonObject.optString("responseMsg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (requestedMethod.equalsIgnoreCase("SaleSports")) {
            //ErrorMsg:Time Out, Login Again!!|ErrorCode:01|
            TpsGamesClass.getInstance().closeScreen(this);
            if (response.contains("ErrorCode")) {
                TpsGamesClass.getInstance().showAToast(response.substring(response.indexOf(":") + 1, response.indexOf("|")), HomeActivityTpsGame.this, Toast.LENGTH_SHORT);
                return;
            }
            Intent intent = new Intent(HomeActivityTpsGame.this, PrintActivitySportsGame.class);
            intent.putExtra("response", response);
            startActivity(intent);
        }

        isClickPerformed = false;

    }
}
