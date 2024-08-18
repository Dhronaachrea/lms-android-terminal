package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.TpsSplashActivity;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.ConstantField;
import demo.stpl.com.tpsmergedbuild.baseClass.RobotoCommonTextView;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.Communcations;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;
import demo.stpl.com.tpsmergedbuild.beans.SleGameBean;
import demo.stpl.com.tpsmergedbuild.beans.SportsSaleBean;
import demo.stpl.com.tpsmergedbuild.betslip.ActivityBetSlipCamera;
import demo.stpl.com.tpsmergedbuild.dialog.DrawDialog;
import demo.stpl.com.tpsmergedbuild.dialog.WinnigDialog;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;
import demo.stpl.com.tpsmergedbuild.utils.widgets.PurchaseLabels;

//import skilrock.com.tpsgame.R;
//import tpsgames.HttpRequest;
//import tpsgames.TpsSplashActivity;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.ConstantField;
//importdemo.stpl.com.tpsmergedbuild.baseClass.RobotoCommonTextView;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.Communcations;
//import tpsgames.beans.GameBean;
//import tpsgames.beans.SleGameBean;
//import tpsgames.beans.SportsSaleBean;
//import tpsgames.betslip.ActivityBetSlipCamera;
//import tpsgames.dialog.DrawDialog;
//import tpsgames.dialog.WinnigDialog;
//import tpsgames.interfaces.ResponseLis;
//import demo.stpl.com.tpsmergedbuild.utils.widgets.PurchaseLabels;

//import com.example.mylibrary.Utils.PurchaseLabels;

/**
 * Created by stpl on 14-Oct-16.
 */
public class ActivityDgeGamesTps390 extends Activity implements View.OnClickListener, ResponseLis,DrawerLayout.DrawerListener {
    DrawerLayout sideDrawer;
    int sideDrawerItemId=0;
    LinearLayout olaButton;
    ImageButton threeLines;
    LinearLayout ebet_btn, sport_btn;

    View luckyNumber, mini_keno, mini_roulette, super_keno, one_to_twelve;
    LinearLayout common_bet_slip_btn, draws2, draws_mini_roulette, layout_selected_numbers, common_winning_claim_btn, buy_btn, draws, linearLayout, more_col_id, layout_below_sub_header, bottom_layout, main_header, game_selection, bet_type_layout, quick_pick_number_layout_upper;
    private LinearLayout bet_slip;
    LayoutInflater inflater;
    View mainviewToadd;
    TextView user_name, user_balance, selected_numbers, no_of_lines, buy_amt, bet_type1, bet_type2, bet_type3, bet_type4, bet_type5, bet_type6,
            bet_type7, bet_type8, bet_type9, bet_type10, btn_quick_pick, first_amount, second_amount, third_amount, four_amount,
            five_amount, six_amount, seven_amount, eight_amount, nine_amount, bet_other, more_draw, more_draw2, draw_date_time, draw_date_time2, number_picked;
    View lastBetSelected, lastBetAmountSelected;
    LinearLayout.LayoutParams param, param2, params3, params4;
    String selectedTypeMiniKeno = "";
    int selectedPosition = -1, lastBetAmountPosition = 1, betTypeSelected = 1;
    ImageView list_icon, pick_down, pick_up, btn_refresh;

    Intent intent;

    private String betMultiPle;

    private boolean isDrawSelected = false;

    int miniroulettebetamount, selectedPositionBeforeOther = -1;

    String balance = "";

    private WinnigDialog winnigDialog;

    double unitPrice, amountOther, betAmtMul;

    public boolean isValueSetToDialog;

    String playerId;

    int totalNumberToBeSelectedSuperKeno = 1;

    FrameLayout horizontalScrollView;

    int totalMinLucky = 1, totalMaxLucky = 1;

    int fortuenMaxData = 5;

    int fortuneMinData = 1;

    View commonView;

    int totalCountNumbers = -1;

    GameBean.Games currentGame;

    String currentBetType = "";

    DrawDialog dialog;

    String amount;

    int totalSelected = 1;

    HashMap<Integer, Integer> hashMap = new HashMap<>();
    private Map<String, GameBean.Games.Bets> mini_roulettte_betsMap = new HashMap<>();
    String[] mini_roulettte_betsMap_names = {"roulette", "roulette", "roulette", "roulette", "roulette", "roulette", "roulette", "roulette", "roulette", "roulette", "roulette", "roulette", "firstColumn", "secondColumn", "thirdColumn", "fourthColumn", "firstRow", "secondRow", "thirdRow", "roulette", "roulette", "roulette", "allEvenNumbers", "redNumbers", "blackNumbers", "allOddNumbers", "zero", "firstQuarter", "thirdQuarter", "fifthQuarter", "secondQuarter", "fourthQuarter", "sixthQuarter"};
    String[] mini_roulettte_betsMap_pickedNumbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "01,02,03", "04,05,06", "07,08,09", "10,11,12", "03,06,09,12", "02,05,08,11", "01,04,07,10", "01,02,03,04,05,06", "04,05,06,07,08,09", "07,08,09,10,11,12", "02,04,06,08,10,12", "01,03,05,07,09,11", "02,04,06,08,10,12", "01,03,05,07,09,11", "0", "02,03,05,06", "05,06,08,09", "07,08,10,11", "01,02,05,04", "04,05,07,08", "08,09,11,12"};
    String[] mini_roulettte_betsMap_nopicked = {"01", "01", "01", "01", "01", "01", "01", "01", "01", "01", "01", "01", "03", "03", "03", "03", "04", "04", "04", "06", "06", "06", "06", "06", "06", "06", "01", "04", "04", "04", "04", "04", "04"};


    String[] miniKenobet = {"Direct12", "First12", "Last12", "AllOdd", "AllEven", "OddEven", "EvenOdd", "JumpEvenOdd", "JumpOddEven", "QP"};

    ArrayList<View> views = new ArrayList<>();
    ArrayList<View> numbersView = new ArrayList<>();
    ArrayList<View> miniroulette_chip = new ArrayList<>();
    String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    private int[] miniroulettechip = {
            R.id.one_chip,
            R.id.two_chip,
            R.id.three_chip,
            R.id.four_chip,
            R.id.five_chip,
            R.id.six_chip,
            R.id.seven_chip,
            R.id.eight_chip,
            R.id.nine_chip,
            R.id.ten_chip,
            R.id.eleven_chip,
            R.id.twelve_chip,
            R.id.thirteen_chip,
            R.id.fourteen_chip,
            R.id.fifteen_chip,
            R.id.sixteen_chip,
            R.id.seventeen_chip,
            R.id.eighteen_chip,
            R.id.nineteen_chip,
            R.id.twenty_chip,
            R.id.twenty_one_chip,
            R.id.twenty_two_chip,
            R.id.twenty_three_chip,
            R.id.twenty_four_chip,
            R.id.twenty_five_chip,
            R.id.twenty_six_chip,
            R.id.twenty_seven_chip,
            R.id.twenty_eight_chip,
            R.id.twenty_nine_chip,
            R.id.thirty_chip,
            R.id.thirty_one_chip,
            R.id.thirty_two_chip,
            R.id.thirty_three_chip,
    };

    private int[] totalView = {R.id.one,
            R.id.two,
            R.id.three,
            R.id.four,
            R.id.five,
            R.id.six,
            R.id.seven,
            R.id.eight,
            R.id.nine,
            R.id.ten,
            R.id.eleven,
            R.id.twelve,
            R.id.thirteen,
            R.id.fourteen,
            R.id.fifteen,
            R.id.sixteen,
            R.id.seventeen,
            R.id.eighteen,
            R.id.nineteen,
            R.id.twenty,
            R.id.twenty_one,
            R.id.twenty_two,
            R.id.twenty_three,
            R.id.twenty_four,
            R.id.twenty_five,
            R.id.twenty_six,
            R.id.twenty_seven,
            R.id.twenty_eight,
            R.id.twenty_nine,
            R.id.thirty,
            R.id.thirty_one,
            R.id.thirty_two,
            R.id.thirty_three,
            R.id.thirty_four,
            R.id.thirty_five,
            R.id.thirty_six,
            R.id.thirty_seven,
            R.id.thirty_eight,
            R.id.thirty_nine,
            R.id.fourty,
            R.id.fourty_one,
            R.id.fourty_two,
            R.id.fourty_three,
            R.id.fourty_four,
            R.id.fourty_five,
            R.id.fourty_six,
            R.id.fourty_seven,
            R.id.fourty_eight,
            R.id.fourty_nine,
            R.id.fifty,
            R.id.fifty_one,
            R.id.fifty_two,
            R.id.fifty_three,
            R.id.fifty_four,
            R.id.fifty_five,
            R.id.fifty_six,
            R.id.fifty_seven,
            R.id.fifty_eight,
            R.id.fifty_nine,
            R.id.sixty,
            R.id.sixty_one,
            R.id.sixty_two,
            R.id.sixty_three,
            R.id.sixty_four,
            R.id.sixty_five,
            R.id.sixty_six,
            R.id.sixty_seven,
            R.id.sixty_eight,
            R.id.sixty_nine,
            R.id.seventy,
            R.id.seventy_one,
            R.id.seventy_two,
            R.id.seventy_three,
            R.id.seventy_four,
            R.id.seventy_five,
            R.id.seventy_six,
            R.id.seventy_seven,
            R.id.seventy_eight,
            R.id.seventy_nine,
            R.id.eighty,
            R.id.eighty_one,
            R.id.eighty_two,
            R.id.eighty_three,
            R.id.eighty_four,
            R.id.eighty_five,
            R.id.eighty_six,
            R.id.eighty_seven,
            R.id.eighty_eight,
            R.id.eighty_nine,
            R.id.ninety};

    LinearLayout draw_btn;
    private boolean isRgCall = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (TpsGamesClass.getInstance().getDeviceName().contains("550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx053")) {
            setContentView(R.layout.layout_dge_tps_land);
        } else {
            setContentView(R.layout.layout_dge_tps390);
        }
        luckyNumber = findViewById(R.id.lucky_numbers);
        mini_keno = findViewById(R.id.mini_keno);
        mini_roulette = findViewById(R.id.mini_roulette);
        super_keno = findViewById(R.id.super_keno);
        one_to_twelve = findViewById(R.id.one_to_twelve);

        more_col_id = (LinearLayout) findViewById(R.id.more_col_id);
        bet_slip = (LinearLayout) findViewById(R.id.bet_slip);

        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().contains("515") || TpsGamesClass.getInstance().getDeviceName().contains("rk30")) {
            bet_slip.setVisibility(View.GONE);
        }

        linearLayout = (LinearLayout) findViewById(R.id.main_view);

        main_header = (LinearLayout) findViewById(R.id.main_header);

        bottom_layout = (LinearLayout) findViewById(R.id.bottom_layout);

        layout_below_sub_header = (LinearLayout) findViewById(R.id.below_sub_header);

        bet_type_layout = (LinearLayout) findViewById(R.id.bet_type_layout);

        btn_quick_pick = (TextView) findViewById(R.id.btn_quick_pick);

        list_icon = (ImageView) findViewById(R.id.list_icon);

        first_amount = (TextView) findViewById(R.id.first_amount);

        more_draw = (TextView) findViewById(R.id.more_draw);
        draw_date_time = (TextView) findViewById(R.id.draw_date_time);

        draws = (LinearLayout) findViewById(R.id.draws);

        buy_btn = (LinearLayout) findViewById(R.id.buy_btn);

        number_picked = (TextView) findViewById(R.id.number_picked);

        pick_down = (ImageView) findViewById(R.id.pick_down);

        draw_btn = (LinearLayout) findViewById(R.id.draw_btn);

        pick_up = (ImageView) findViewById(R.id.pick_up);

        common_winning_claim_btn = (LinearLayout) findViewById(R.id.common_winning_claim_btn);

        selected_numbers = (TextView) findViewById(R.id.selected_numbers);

        layout_selected_numbers = (LinearLayout) findViewById(R.id.layout_selected_numbers);

        draws_mini_roulette = (LinearLayout) findViewById(R.id.draws_mini_roulette);

        draws2 = (LinearLayout) draws_mini_roulette.findViewById(R.id.draws);

        draw_date_time2 = (TextView) draws_mini_roulette.findViewById(R.id.draw_date_time);

        more_draw2 = (TextView) draws_mini_roulette.findViewById(R.id.more_draw);

        btn_refresh = (ImageView) findViewById(R.id.btn_refresh);

        ebet_btn = (LinearLayout) findViewById(R.id.ebet_btn);
        sport_btn = (LinearLayout) findViewById(R.id.sport_btn);

        olaButton = (LinearLayout) findViewById(R.id.ola_btn);
        sideDrawer=(DrawerLayout)findViewById(R.id.drawer_layout_new);
        olaButton=(LinearLayout) findViewById(R.id.ola_btn);
        threeLines=(ImageButton)findViewById(R.id.threeLines);
        sideDrawer.addDrawerListener(this);
        olaButton.setOnClickListener(this);
        threeLines.setOnClickListener(this);

        sport_btn.setOnClickListener(this);

        olaButton.setOnClickListener(this);

        common_bet_slip_btn = (LinearLayout) findViewById(R.id.common_bet_slip_btn);

        user_name = (TextView) findViewById(R.id.user_name);

        user_balance = (TextView) findViewById(R.id.user_balance);

        sport_btn = (LinearLayout) findViewById(R.id.sport_btn);

        olaButton = (LinearLayout) findViewById(R.id.ola_btn);

        sport_btn.setOnClickListener(this);

        olaButton.setOnClickListener(this);

        common_bet_slip_btn.setOnClickListener(this);

        ebet_btn.setOnClickListener(this);

        btn_refresh.setOnClickListener(this);

        draws2.setOnClickListener(this);

        common_winning_claim_btn.setOnClickListener(this);

        pick_down.setOnClickListener(this);
        pick_up.setOnClickListener(this);

        buy_btn.setOnClickListener(this);

        draws.setOnClickListener(this);

        more_draw.setVisibility(View.GONE);
        more_draw2.setVisibility(View.GONE);

        second_amount = (TextView) findViewById(R.id.second_amount);
        third_amount = (TextView) findViewById(R.id.third_amount);
        four_amount = (TextView) findViewById(R.id.four_amount);
        five_amount = (TextView) findViewById(R.id.five_amount);
        six_amount = (TextView) findViewById(R.id.six_amount);
        seven_amount = (TextView) findViewById(R.id.seven_amount);
        eight_amount = (TextView) findViewById(R.id.eight_amount);
        nine_amount = (TextView) findViewById(R.id.nine_amount);
        bet_other = (TextView) findViewById(R.id.bet_other);

        first_amount.setOnClickListener(this);
        second_amount.setOnClickListener(this);
        third_amount.setOnClickListener(this);
        four_amount.setOnClickListener(this);
        five_amount.setOnClickListener(this);
        six_amount.setOnClickListener(this);
        seven_amount.setOnClickListener(this);
        eight_amount.setOnClickListener(this);
        nine_amount.setOnClickListener(this);
        bet_other.setOnClickListener(this);

        first_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
        first_amount.setSelected(true);
        lastBetAmountSelected = first_amount;

        buy_amt = (TextView) findViewById(R.id.buy_amt);
        no_of_lines = (TextView) findViewById(R.id.no_of_lines);
        selected_numbers.setText("0");
        no_of_lines.setText("0");
        buy_amt.setText("$ 0.0");


        list_icon.setVisibility(View.GONE);

        bet_type1 = (TextView) findViewById(R.id.bet_type1);
        bet_type2 = (TextView) findViewById(R.id.bet_type2);

        bet_type3 = (TextView) findViewById(R.id.bet_type3);
        bet_type4 = (TextView) findViewById(R.id.bet_type4);
        bet_type5 = (TextView) findViewById(R.id.bet_type5);
        bet_type6 = (TextView) findViewById(R.id.bet_type6);
        bet_type7 = (TextView) findViewById(R.id.bet_type7);
        bet_type8 = (TextView) findViewById(R.id.bet_type8);
        bet_type9 = (TextView) findViewById(R.id.bet_type9);
        bet_type10 = (TextView) findViewById(R.id.bet_type10);


        quick_pick_number_layout_upper = (LinearLayout) findViewById(R.id.quick_pick_number_layout_upper);

        inflater = LayoutInflater.from(this);


//        mainviewToadd = inflater.inflate(R.layout.layout_five_by_ninty, null);

        param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 8.0f);
//        param2 = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT, 9.0f);
//        params3 = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
//        params4 = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT, 2f);
//        mainviewToadd.setLayoutParams(param);

//        linearLayout.addView(mainviewToadd);


        luckyNumber.setOnClickListener(this);
        mini_keno.setOnClickListener(this);
        mini_roulette.setOnClickListener(this);
        super_keno.setOnClickListener(this);
        one_to_twelve.setOnClickListener(this);
        more_col_id.setOnClickListener(this);

        bet_type1.setOnClickListener(this);
        bet_type2.setOnClickListener(this);
        bet_type3.setOnClickListener(this);
        bet_type4.setOnClickListener(this);
        bet_type5.setOnClickListener(this);
        bet_type6.setOnClickListener(this);
        bet_type7.setOnClickListener(this);
        bet_type8.setOnClickListener(this);
        bet_type9.setOnClickListener(this);
        bet_type10.setOnClickListener(this);
        btn_quick_pick.setOnClickListener(this);


        views.add(luckyNumber);
        views.add(mini_keno);
        views.add(mini_roulette);
        views.add(super_keno);
        views.add(one_to_twelve);
        luckyNumber.performClick();
//        RecyclerView game_name = (RecyclerView) findViewById(R.id.game_name);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        game_name.setLayoutManager(layoutManager);
//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
//        game_name.setAdapter(recyclerViewAdapter);

        horizontalScrollView = (FrameLayout) findViewById(R.id.horizontalScroll);
        setBetAmount();
        buy_amt.setText("$ 0.0");
        user_name.setText(TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
        String balance = TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance() + "";

        if (balance.contains(".")) {
            String[] decimalAmount = balance.split("[.]");
            balance = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        user_balance.setText("$ " + balance);

        TpsGamesClass.getInstance().setActivityDgeGamesTps390(this);
//        horizontalScrollView.scro;

    }

    protected void openCard() {
        if (!TpsGamesClass.getInstance().getPlayerId()) {
            if (!TpsGamesClass.getInstance().getPlayerId()) {
                TpsGamesClass.getInstance().openCard();
                shoDialog();
            } else {

                TpsGamesClass.getInstance().showAToast(TpsGamesClass.getInstance().getMessageForNotVarified().trim().length() == 0 ? "Player Not Verified" : TpsGamesClass.getInstance().getMessageForNotVarified(), this, Toast.LENGTH_SHORT);
            }
            return;
        }
    }

    private void shoDialog() {
//        TpsGamesClass.getInstance().closeScreen(this);
//
//        TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
//        String url = "/sdcard/rainbow_header.png";
//        TpsGamesClass.getInstance().pleaseWait("Swipe Your Card...", url);
//        TpsGamesClass.getInstance().displayScreen(this);

//        TpsGamesClass.getInstance().openCard();

        winnigDialog = (WinnigDialog) new WinnigDialog(this).setNegativeButton("SKIP", negativeClick).setPositiveButton("DONE", positiveClick).setTitle("WINNING CLAIM");
        winnigDialog.setMessages("Enter Your Mobile Number or Swipe Your Weaver Card.");
        winnigDialog.setTitle("Player Verification");
        winnigDialog.setEditInput(true);
        winnigDialog.setCanceledOnTouchOutside(false);

        winnigDialog.show();

        if (TpsGamesClass.getInstance().getDeviceName().contains("515") || TpsGamesClass.getInstance().getDeviceName().contains("rk30")) {
            winnigDialog.edit_view.requestFocus();
        }

        winnigDialog.edit_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 16) {
                    cardSwipeVerify(winnigDialog.edit_view.getText().toString(), "");
                }

            }
        });
    }


    public synchronized void cardSwipeVerify(String playerId, String mobileNumber) {
        if (isRgCall) {
            return;
        }
        isRgCall = true;
        playerId = playerId.replaceAll("[^0-9]", "");
        if (winnigDialog != null && !isValueSetToDialog && mobileNumber.trim().length() == 0) {
            winnigDialog.dismiss();
        }
        String url = "";
//        TpsGamesClass.getInstance().closeScreen(this);
//
//        TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
//        String url = "/sdcard/rainbow_header.png";
//        TpsGamesClass.getInstance().pleaseWait("Verifying Player...", url);
//        TpsGamesClass.getInstance().displayScreen(this);
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
        HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityDgeGamesTps390.this, "verifyUser", ActivityDgeGamesTps390.this, "rgCall", Utility.rGGamingHeader);
        TpsGamesClass.getInstance().startLoader(this);
        httpRequest.setIsParams(true, jsonObject.toString());
        httpRequest.executeTask();
    }


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
                TpsGamesClass.getInstance().showAToast("Please Enter Some Value", ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
            }
        }
    };
    View.OnClickListener negativeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

//            TpsGamesClass.getInstance().closeScreen(HomeActivityTpsGame.this);
//            ServerCommClass.getServerCommClass().getOnKeyListener().onFragmentChange();
            if (selectedPosition == 2) {
                purchaseRequestMiniRoulette();
            } else {
                purchageRequest();
            }

            winnigDialog.dismiss();
        }
    };

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

    private void serverRequestCall(String serverRequest, String requestServer) {
        if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.ONETOTWELVE)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/oneToTwelveBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityDgeGamesTps390.this, "oneTwoTwelve", ActivityDgeGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();

        } else if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.TWELVEBYTWENTYFOUR)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/twelveByTwentyFourBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityDgeGamesTps390.this, "TwleveByTwenty", ActivityDgeGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();

        } else if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.MINIROULETTE)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/oneToTwelveRouletteBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityDgeGamesTps390.this, "TwleveByTwenty", ActivityDgeGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();

        } else if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.KENOSIX)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/kenoSixBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityDgeGamesTps390.this, "TwleveByTwenty", ActivityDgeGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();

        } else if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.KENOTWO)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/kenoTwoBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityDgeGamesTps390.this, "TwleveByTwenty", ActivityDgeGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();

        } else if (serverRequest != null && serverRequest.equalsIgnoreCase("SLE_SALE")) {
            SportsSaleBean sportsSaleBean = TpsGamesClass.getInstance().getSportsBean(requestServer);

            String gameId = sportsSaleBean.getGameId().substring(sportsSaleBean.getGameId().indexOf("=") + 1, sportsSaleBean.getGameId().length());
            String gameTypeId = sportsSaleBean.getGameTypeId().substring(sportsSaleBean.getGameTypeId().indexOf("=") + 1, sportsSaleBean.getGameTypeId().length());
            String drawCoount = sportsSaleBean.getDrawCount().substring(sportsSaleBean.getDrawCount().indexOf("=") + 1, sportsSaleBean.getDrawCount().length());
            String ticketAmount = sportsSaleBean.getTicketAmt().substring(sportsSaleBean.getTicketAmt().indexOf("=") + 1, sportsSaleBean.getTicketAmt().length());
            String url = TpsGamesClass.getInstance().getTicketSaleServicesForSle(ActivityDgeGamesTps390.this, TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName()
                    , gameId, gameTypeId, sportsSaleBean.getDrawInfoString().substring(sportsSaleBean.getDrawInfoString().indexOf("=") + 1, sportsSaleBean.getDrawInfoString().length()), drawCoount, ticketAmount, sportsSaleBean);
//                url = url + "&tokenId=" + responseData.getTokenId();
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityDgeGamesTps390.this, "ebet slip", ActivityDgeGamesTps390.this, "SaleSports", null);
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();
        }
    }

    protected String getDataFormat(String data) {
//        17-10-2016 06:40:00
        String[] date = data.split("[ ]");
        String[] dateFormat = date[0].split("[-]");
        String month = months[Integer.parseInt(dateFormat[1]) - 1];
        String time = date[1].substring(0, date[1].lastIndexOf(":"));
        return dateFormat[0] + "-" + month + ", " + time;

    }

    protected void setOnClick(int totalCount, boolean isFortune, boolean isroulette) {
        for (int i = 0; i < totalCount; i++) {
//            if (i != selectedPosition && i < 4) {
//                views.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selector_normal));
//            }
            if (isFortune) {
                LinearLayout linearLayout = (LinearLayout) mainviewToadd.findViewById(totalView[i]);
                linearLayout.setOnClickListener(ActivityDgeGamesTps390.this);
                linearLayout.setSelected(false);
                numbersView.add(linearLayout);
            } else if (isroulette) {
                LinearLayout linearLayout = (LinearLayout) mainviewToadd.findViewById(totalView[i]);
                Button button = (Button) mainviewToadd.findViewById(miniroulettechip[i]);
                if (i < 27) {
                    button.setVisibility(View.GONE);
                    linearLayout.setOnClickListener(ActivityDgeGamesTps390.this);
                    linearLayout.setSelected(false);
                } else {
                    button.setBackgroundResource(R.drawable.roulette_button_circle);
                    button.setOnClickListener(ActivityDgeGamesTps390.this);
                    button.setSelected(false);
                }
//                linearLayout.setOnClickListener(ActivityDgeGamesTps390.this);
//                linearLayout.setSelected(false);
                numbersView.add(linearLayout);
                miniroulette_chip.add(button);
            } else {
                RobotoCommonTextView robotoCommonTextView = (RobotoCommonTextView) mainviewToadd.findViewById(totalView[i]);
                robotoCommonTextView.setOnClickListener(ActivityDgeGamesTps390.this);
                robotoCommonTextView.setSelected(false);
                numbersView.add(robotoCommonTextView);
            }

        }
    }

    protected void resetEveryThing() {
        resetData();
        no_of_lines.setText("0");
        buy_amt.setText("$ 0.0");
        if (quick_pick_number_layout_upper != null && number_picked != null && selectedPosition == 4) {
            quick_pick_number_layout_upper.setVisibility(View.INVISIBLE);
            number_picked.setText("0");
        }
        changeDraws();
        changeCurrentSelectionGame();
        setAllBets();

        user_name.setText(TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
        String balance = TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance() + "";

        if (balance.contains(".")) {
            String[] decimalAmount = balance.split("[.]");
            balance = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        user_balance.setText("$ " + balance);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && data.hasExtra("resetRequest")) {
            betTypeSelected = 1;
            if (selectedPosition == 2) {
                resetData();
            } else {
                resetEveryThing();
            }
        } else if (data != null && data.hasExtra("showDialog")) {
            Communcations.winningInside(ActivityDgeGamesTps390.this, data.getStringExtra("message"));
        } else if (data != null && data.hasExtra("json")) {
            serverRequestCall(data.getStringExtra("methodName"), data.getStringExtra("json"));
        } else if (data != null && data.hasExtra("printResponse")) {

            if (TpsGamesClass.getInstance().getDeviceName().contains("rk30")) {
                intent = new Intent(ActivityDgeGamesTps390.this, PrintWinningAzt.class);
            } else if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M")) {
                intent = new Intent(ActivityDgeGamesTps390.this, ActivityBluetoothPrint.class);
            } else {
                intent = new Intent(ActivityDgeGamesTps390.this, PrintWinningClaim.class);
            }

            intent.putExtra("response", data.getExtras().getString("printResponse"));
            startActivity(intent);
        }
    }

    protected void changeCurrentSelectionGame() {
        isDrawSelected = false;
        hashMap.clear();
        selected_numbers.setText("0");
        no_of_lines.setText("0");
        buy_amt.setText("$ 0.0");
        btn_quick_pick.setSelected(false);
        currentGame.getDraws()[0].setIsChecked(true);
        draw_date_time.setText(getDataFormat(currentGame.getDraws()[0].getDrawDateTime()));
        draw_date_time2.setText(getDataFormat(currentGame.getDraws()[0].getDrawDateTime()));
        for (int i = 0; i < views.size(); i++) {

            if (i != selectedPosition) {
                views.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.top_game_selector));
            } else {
                views.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
            }
        }


        if (TpsGamesClass.getInstance().getDeviceName().contains("TPS580")) {
            TpsGamesClass.getInstance().startFrontScreen("dge");
        }
    }

    public void updateFrontScreen() {
        if (TpsGamesClass.getInstance().getDeviceName().contains("TPS580")) {
            TpsGamesClass.getInstance().setFrontScreen(selectedPosition, this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView imageView= (ImageView) draw_btn.findViewById(R.id.draw_img);
        imageView.setImageResource(R.mipmap.drawssslect);
        TpsGamesClass.getInstance().setCurrentActivity("dge390");
//        updateFrontScreen();
        String balance = TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance() + "";
        if (balance.contains(".")) {
            String[] decimalAmount = balance.split("[.]");
            balance = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        user_balance.setText("$ " + balance);
    }

    protected void setSuperKeno(boolean isQp) {
        resetData();

        if (currentBetType.equalsIgnoreCase("direct1")) {
            totalNumberToBeSelectedSuperKeno = 1;
        } else if (currentBetType.equalsIgnoreCase("direct2")) {
            totalNumberToBeSelectedSuperKeno = 2;
        } else if (currentBetType.equalsIgnoreCase("direct3")) {
            totalNumberToBeSelectedSuperKeno = 3;
        } else if (currentBetType.equalsIgnoreCase("direct4")) {
            totalNumberToBeSelectedSuperKeno = 4;
        } else if (currentBetType.equalsIgnoreCase("direct5")) {
            totalNumberToBeSelectedSuperKeno = 5;
        } else if (currentBetType.equalsIgnoreCase("direct6")) {
            totalNumberToBeSelectedSuperKeno = 6;
        } else if (currentBetType.equalsIgnoreCase("direct7")) {
            totalNumberToBeSelectedSuperKeno = 7;
        } else if (currentBetType.equalsIgnoreCase("direct8")) {
            totalNumberToBeSelectedSuperKeno = 8;
        } else if (currentBetType.equalsIgnoreCase("direct9")) {
            totalNumberToBeSelectedSuperKeno = 9;
        } else if (currentBetType.equalsIgnoreCase("direct10")) {
            totalNumberToBeSelectedSuperKeno = 10;
        }
        if (isQp) {

            int totalCount = 0;
            while (totalCount != totalNumberToBeSelectedSuperKeno) {
                int rnd = generate(0, 79);

                if (hashMap.get(rnd) == null) {

                    hashMap.put(rnd, rnd);
                    numbersView.get(rnd).setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                    numbersView.get(rnd).setSelected(true);
                    totalCount++;
                }
            }
            selected_numbers.setText(hashMap.size() + "");
            hashMap.clear();
            no_of_lines.setText("1");
            setBetAmount();
        }
    }


    int totalCountFortune = 0;

    protected void setFortuenData(int totalNumberSelect) {
        resetData();
        totalCountFortune = 0;
        while (totalCountFortune != totalNumberSelect) {
            int rnd = generate(0, 11);

            if (hashMap.get(rnd) == null) {

                hashMap.put(rnd, rnd);
                numbersView.get(rnd).setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                numbersView.get(rnd).setSelected(true);
                totalCountFortune++;
            }
        }
        selected_numbers.setText(hashMap.size() + "");
        hashMap.clear();
        no_of_lines.setText("1");
        setBetAmount(totalNumberSelect);
    }

    protected void setMiniKeno(boolean isQp) {
        resetData();
        if (isQp) {
            if (lastBetSelected != null && lastBetSelected.isSelected()) {
                lastBetSelected.setBackgroundColor(Color.parseColor("#000FF0"));
                lastBetSelected.setSelected(false);
            }
            int totalCount = 0;
            while (totalCount != 12) {
                int rnd = generate(0, 23);

                if (hashMap.get(rnd) == null) {

                    hashMap.put(rnd, rnd);
                    numbersView.get(rnd).setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                    numbersView.get(rnd).setSelected(true);
                    totalCount++;
                }
            }

            selected_numbers.setText("12");
            setBetAmount();
            hashMap.clear();
        } else {
            btn_quick_pick.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selector_normal));
            btn_quick_pick.setSelected(false);
            int totalBetSelect = 0;
            int totalToBeSelected = 0;
            if (currentBetType.equalsIgnoreCase("First12")) {
                totalBetSelect = 0;
                totalToBeSelected = 12;
            } else if (currentBetType.equalsIgnoreCase("Last12")) {
                totalBetSelect = 12;
                totalToBeSelected = 24;
            } else if (currentBetType.equalsIgnoreCase("allodd")) {
                totalBetSelect = 0;
                totalToBeSelected = 24;
            } else if (currentBetType.equalsIgnoreCase("alleven")) {
                totalBetSelect = 0;
                totalToBeSelected = 24;
            } else if (currentBetType.equalsIgnoreCase("oddeven")) {
                totalBetSelect = 0;
                totalToBeSelected = 24;
            } else if (currentBetType.equalsIgnoreCase("evenodd")) {
                totalBetSelect = 0;
                totalToBeSelected = 24;
            } else if (currentBetType.equalsIgnoreCase("jumpevenodd")) {
                totalBetSelect = 0;
                totalToBeSelected = 24;
            } else if (currentBetType.equalsIgnoreCase("jumpoddeven")) {
                totalBetSelect = 0;
                totalToBeSelected = 24;
            }
            Boolean firstodd = false;
            Boolean firsteven = false;
            for (int i = totalBetSelect; i < totalToBeSelected; i++) {
                if (currentBetType.equalsIgnoreCase("allodd")) {
                    int number = Integer.parseInt(((TextView) numbersView.get(i)).getText().toString());
                    if (number % 2 == 1) {
                        commonView = numbersView.get(i);
                        commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                        commonView.setSelected(true);
                    }

                } else if (currentBetType.equalsIgnoreCase("alleven")) {
                    int number = Integer.parseInt(((TextView) numbersView.get(i)).getText().toString());
                    if (number % 2 == 0) {
                        commonView = numbersView.get(i);
                        commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                        commonView.setSelected(true);
                    }
                } else if (currentBetType.equalsIgnoreCase("oddeven")) {
                    int number = Integer.parseInt(((TextView) numbersView.get(i)).getText().toString());
                    if (number % 2 == 1 && number <= 12) {
                        commonView = numbersView.get(i);
                        commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                        commonView.setSelected(true);
                    } else if (number % 2 == 0 && number > 12 && number <= 24) {
                        commonView = numbersView.get(i);
                        commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                        commonView.setSelected(true);
                    }
                } else if (currentBetType.equalsIgnoreCase("evenodd")) {
                    int number = Integer.parseInt(((TextView) numbersView.get(i)).getText().toString());
                    if (number % 2 == 0 && number <= 12) {
                        commonView = numbersView.get(i);
                        commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                        commonView.setSelected(true);
                    } else if (number % 2 == 1 && number > 12 && number <= 24) {
                        commonView = numbersView.get(i);
                        commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                        commonView.setSelected(true);
                    }
                } else if (currentBetType.equalsIgnoreCase("jumpevenodd")) {
                    int number = Integer.parseInt(((TextView) numbersView.get(i)).getText().toString());

                    if (number % 2 == 0) {
                        if (!firstodd) {
                            firstodd = true;
                            continue;
                        }
                        firstodd = false;
                        commonView = numbersView.get(i);
                        commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                        commonView.setSelected(true);
                    } else if (number % 2 == 1) {
                        if (!firsteven) {
                            firsteven = true;
                            continue;
                        }
                        firsteven = false;
                        commonView = numbersView.get(i);
                        commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                        commonView.setSelected(true);
                    }
                } else if (currentBetType.equalsIgnoreCase("jumpoddeven")) {
                    int number = Integer.parseInt(((TextView) numbersView.get(i)).getText().toString());

                    if (number % 2 == 0) {
                        if (!firstodd) {
                            firstodd = true;
                            commonView = numbersView.get(i);
                            commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                            commonView.setSelected(true);
                            continue;
                        }
                        firstodd = false;

                    } else if (number % 2 == 1) {
                        if (!firsteven) {
                            firsteven = true;
                            commonView = numbersView.get(i);
                            commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                            commonView.setSelected(true);
                            continue;
                        }
                        firsteven = false;

                    }
                } else {
                    commonView = numbersView.get(i);
                    commonView.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                    commonView.setSelected(true);
                }

            }
            selected_numbers.setText("12");
        }


    }

    public void showDrawDialog(Context context, final GameBean.Games.Draws[] draws) {

        dialog = (DrawDialog) new DrawDialog(context, true).setNegativeButton("CANCEL", this).setPositiveButton("DONE", this).setTitle("SELECT DRAW");
        ((DrawDialog) dialog).setDraw(draws);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public void showNumberDialog(Context context, int minValue, int maxValue) {

        dialog = (DrawDialog) new DrawDialog(context, true).setNegativeButton("CANCEL", this).setPositiveButton("DONE", this).setTitle("SELECT NUMBERS");
        ((DrawDialog) dialog).setBetAmount(minValue, maxValue, true, totalNumberSelected);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public void showOtherDialog(Context context) {

        dialog = (DrawDialog) new DrawDialog(context, true).setNegativeButton("CANCEL", null).setPositiveButton("DONE", null).setTitle("CHOOSE UNIT PRICE");
        double amountToStart = 0;
//        if (bet_other.getText().toString().contains("OTHER")) {
//            amountToStart = currentGame.getBets()[0].getUnitPrice() * lastBetAmountPosition;
//        } else {
//            amountToStart = amountOther;
//        }
        ((DrawDialog) dialog).setBetAmountOther(currentGame.getBets()[0].getUnitPrice(), Double.parseDouble(currentGame.getBets()[0].getMaxBetAmtMul() + ""), false, currentGame.getBets()[0].getUnitPrice(), currentGame.getBets()[0].getUnitPrice(), totalAmountOther, (amountToStart + ""));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    DrawDialog.TotalAmountOther totalAmountOther = new DrawDialog.TotalAmountOther() {
        @Override
        public void otherAmount(double amount, int betAmtMul) {
            ActivityDgeGamesTps390.this.amountOther = amount;
            ActivityDgeGamesTps390.this.betAmtMul = betAmtMul;
            lastBetAmountPosition = selectedPositionBeforeOther = betAmtMul;
            unitPrice = currentGame.getBets()[0].getUnitPrice();
            String amount1 = (unitPrice * (lastBetAmountPosition)) + "";
            if (amount1.contains(".")) {
                String[] decimalAmount = amount1.split("[.]");
                amount1 = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
            }

            setBetAmount(Double.parseDouble(amount1));
            bet_other.setText("$ " + amount);
//            TpsGamesClass.getInstance().showAToast("Amount = " + amount + "\n betMul = " + betAmtMul, ActivityDgeGamesTps390.this, Toast.LENGTH_LONG);

        }

        @Override
        public void canClick() {
            if (selectedPositionBeforeOther != lastBetAmountPosition) {
                lastBetAmountPosition = selectedPositionBeforeOther;
                if (selectedPositionBeforeOther == 1) {
                    first_amount.performClick();
                } else if (selectedPositionBeforeOther == 2) {
                    second_amount.performClick();
                } else if (selectedPositionBeforeOther == 3) {
                    third_amount.performClick();
                } else if (selectedPositionBeforeOther == 4) {
                    four_amount.performClick();
                } else if (selectedPositionBeforeOther == 5) {
                    five_amount.performClick();
                } else if (selectedPositionBeforeOther == 6) {
                    six_amount.performClick();
                } else if (selectedPositionBeforeOther == 7) {
                    seven_amount.performClick();
                } else if (selectedPositionBeforeOther == 8) {
                    eight_amount.performClick();
                } else if (selectedPositionBeforeOther == 9) {
                    nine_amount.performClick();
                } else if (selectedPositionBeforeOther == 10) {
                    bet_other.performClick();
                }
            }
        }
    };

    DrawDialog.TotalNumberSelected totalNumberSelected = new DrawDialog.TotalNumberSelected() {
        @Override
        public void onNumberSelected(long totalNumber) {
            int totalCount = 0;
            while (totalCount != totalNumber) {
                int rnd = generate(0, 89);

                if (hashMap.get(rnd) == null) {

                    hashMap.put(rnd, rnd);
                    numbersView.get(rnd).setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                    numbersView.get(rnd).setSelected(true);
                    totalCount++;
                }
            }
            selected_numbers.setText(hashMap.size() + "");
            hashMap.clear();
            long totalNoOfLine = 0;
            if (currentBetType.equalsIgnoreCase("perm2")) {
                totalNoOfLine = factorial(totalNumber) / (factorial(2) * factorial(totalNumber - 2));
            } else {
                totalNoOfLine = factorial(totalNumber) / (factorial(3) * factorial(totalNumber - 3));
            }
            no_of_lines.setText(totalNoOfLine + "");
            setBetAmount(totalNoOfLine);

        }
    };

    protected void resetData() {
        hashMap.clear();
        if (selectedPosition == 2) {
            for (int i = 0; i < numbersView.size(); i++) {
                if (numbersView.get(i).isSelected() || miniroulette_chip.get(i).isSelected()) {
                    Button selected = (Button) miniroulette_chip.get(i);
                    numbersView.get(i).setSelected(false);
                    miniroulette_chip.get(i).setSelected(false);
                    if (i < 27) {
                        selected.setText("0");
                        selected.setVisibility(View.GONE);
                    } else {
                        selected.setText("");
                        selected.setBackgroundResource(R.drawable.roulette_button_circle);
                    }
                }
            }
            miniroulettebetamount = 0;
            buy_amt.setText("$ 0.0");
            no_of_lines.setText("0");
            changeDraws();
            setAllBets();
            changeCurrentSelectionGame();
        } else {
            if (!btn_quick_pick.isSelected()) {
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                first_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                first_amount.setSelected(true);
                lastBetAmountSelected = first_amount;
            }

            for (int i = 0; i < totalCountNumbers; i++) {
                View view = numbersView.get(i);
                if (view.isSelected()) {
                    view.setSelected(false);
                    view.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selector_normal));
                }
            }
        }
    }

    protected void setVisible(int selectedPosition, boolean isVisible, String value) {
        if (selectedPosition == 0) {
            bet_type1.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            bet_type1.setText(value);
            bet_type1.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
            bet_type1.setSelected(true);
            if (lastBetSelected != null && lastBetSelected.getId() != bet_type1.getId()) {
                lastBetSelected.setBackgroundColor(Color.parseColor("#000FF0"));
                lastBetSelected.setSelected(false);
            }
            btn_quick_pick.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selector_normal));
            btn_quick_pick.setSelected(false);
            currentBetType = bet_type1.getText().toString();
            lastBetSelected = bet_type1;
            if (selectedPosition == 0) {
                setMaxBetLuckyNumber();
            }
        } else if (selectedPosition == 1) {
            bet_type2.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            bet_type2.setText(value);
            bet_type2.setSelected(false);
        } else if (selectedPosition == 2) {
            bet_type3.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            bet_type3.setText(value);
            bet_type3.setSelected(false);
        } else if (selectedPosition == 3) {
            bet_type4.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            bet_type4.setText(value);
            bet_type4.setSelected(false);
        } else if (selectedPosition == 4) {
            bet_type5.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            bet_type5.setText(value);
            bet_type5.setSelected(false);
        } else if (selectedPosition == 5) {
            bet_type6.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            bet_type6.setText(value);
            bet_type6.setSelected(false);
        } else if (selectedPosition == 6) {
            bet_type7.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            bet_type7.setText(value);
            bet_type7.setSelected(false);
        } else if (selectedPosition == 7) {
            bet_type8.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            bet_type8.setText(value);
            bet_type8.setSelected(false);
        } else if (selectedPosition == 8) {
            bet_type9.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            bet_type9.setText(value);
            bet_type9.setSelected(false);
        } else if (selectedPosition == 9) {
            bet_type10.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
            bet_type10.setText(value);
            bet_type10.setSelected(false);
        }
    }

    protected void setLuckyNumberQp() {
        int totalNumberToSelect = 0;
        int totalMinimumSelected = 0;
        if (currentBetType.equalsIgnoreCase("direct5")) {
            totalMinLucky = 5;
            totalNumberToSelect = 5;

        } else if (currentBetType.equalsIgnoreCase("direct4")) {
            totalMinLucky = 4;
            totalNumberToSelect = 4;
        } else if (currentBetType.equalsIgnoreCase("direct3")) {
            totalMinLucky = 3;
            totalNumberToSelect = 3;
        } else if (currentBetType.equalsIgnoreCase("direct2")) {
            totalMinLucky = 2;
            totalNumberToSelect = 2;
        } else if (currentBetType.equalsIgnoreCase("direct1")) {
            totalMinLucky = 1;
            totalNumberToSelect = 1;
        } else if (currentBetType.equalsIgnoreCase("perm1")) {
            totalMinLucky = 10;
            totalNumberToSelect = 10;
        } else if (currentBetType.equalsIgnoreCase("perm2")) {
            totalMinLucky = 3;
            totalMinimumSelected = 3;
            totalNumberToSelect = 20;
        } else if (currentBetType.equalsIgnoreCase("perm3")) {
            totalMinLucky = 4;
            totalMinimumSelected = 4;
            totalNumberToSelect = 20;
        }


        resetData();
        hashMap.clear();
        if (currentBetType.equalsIgnoreCase("perm3") || currentBetType.equalsIgnoreCase("perm2")) {

            showNumberDialog(this, totalMinimumSelected, totalNumberToSelect);
        } else {
            int totalCount = 0;
            while (totalCount != totalNumberToSelect) {
                int rnd = generate(0, 89);

                if (hashMap.get(rnd) == null) {

                    hashMap.put(rnd, rnd);
                    numbersView.get(rnd).setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                    numbersView.get(rnd).setSelected(true);
                    totalCount++;
                }
            }

            if (currentBetType.equalsIgnoreCase("perm1")) {
                no_of_lines.setText("10");
                setBetAmount(10);
            } else {
                no_of_lines.setText("1");
                setBetAmount();
            }
            selected_numbers.setText(hashMap.size() + "");
            hashMap.clear();
        }

    }


    protected void setAllBets() {
        totalNumberToBeSelectedSuperKeno = 1;
        int totalBets = currentGame.getBets().length;
        unitPrice = currentGame.getBets()[0].getUnitPrice();
        for (int i = 0; i < 10; i++) {
//            if (i < totalBets) {
            if (i < totalBets) {
                String betName = currentGame.getBets()[i].getBetName();
                if (selectedPosition == 1 && betName.equalsIgnoreCase("perm12")) {
                    setVisible(i, false, "");
                } else {
                    setVisible(i, true, currentGame.getBets()[i].getBetName());
                }
            } else {
                setVisible(i, false, "");
            }


            if (i == 0) {
                amount = (unitPrice * (i + 1)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    first_amount.setText("$ " + decimalAmount[0] + "." + decimalAmount[1].substring(0, 1));
                } else {
                    first_amount.setText("$ " + amount);
                }
                if (lastBetAmountSelected != null && lastBetAmountSelected.isSelected()) {
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);
                }
                if (first_amount.isSelected()) {
                    first_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    first_amount.setSelected(false);
                }
                first_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                first_amount.setSelected(true);
                lastBetAmountSelected = first_amount;
                lastBetAmountPosition = 1;
                first_amount.setVisibility(View.VISIBLE);
            } else if (i == 1) {
                amount = (unitPrice * (i + 1)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    second_amount.setText("$ " + decimalAmount[0] + "." + decimalAmount[1].substring(0, 1));
                } else {
                    second_amount.setText("$ " + amount);
                }
                second_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                second_amount.setSelected(false);
                second_amount.setVisibility(View.VISIBLE);
            } else if (i == 2) {
                amount = (unitPrice * (i + 1)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    third_amount.setText("$ " + decimalAmount[0] + "." + decimalAmount[1].substring(0, 1));
                } else {
                    third_amount.setText("$ " + amount);
                }
                third_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                third_amount.setSelected(false);
                third_amount.setVisibility(View.VISIBLE);
            } else if (i == 3) {
                amount = (unitPrice * (i + 1)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    four_amount.setText("$ " + decimalAmount[0] + "." + decimalAmount[1].substring(0, 1));
                } else {
                    four_amount.setText("$ " + amount);
                }
                four_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                four_amount.setSelected(false);
                four_amount.setVisibility(View.VISIBLE);
            } else if (i == 4) {
                amount = (unitPrice * (i + 1)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    five_amount.setText("$ " + decimalAmount[0] + "." + decimalAmount[1].substring(0, 1));
                } else {
                    five_amount.setText("$ " + amount);
                }
                five_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                five_amount.setSelected(false);
                five_amount.setVisibility(View.VISIBLE);
            } else if (i == 5) {
                amount = (unitPrice * (i + 1)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    six_amount.setText("$ " + decimalAmount[0] + "." + decimalAmount[1].substring(0, 1));
                } else {
                    six_amount.setText("$ " + amount);
                }
                six_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                six_amount.setSelected(false);
                six_amount.setVisibility(View.VISIBLE);
            } else if (i == 6) {
                amount = (unitPrice * (i + 1)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    seven_amount.setText("$ " + decimalAmount[0] + "." + decimalAmount[1].substring(0, 1));
                } else {
                    seven_amount.setText("$ " + amount);
                }
                seven_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                seven_amount.setSelected(false);
                seven_amount.setVisibility(View.VISIBLE);
            } else if (i == 7) {
                amount = (unitPrice * (i + 1)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    eight_amount.setText("$ " + decimalAmount[0] + "." + decimalAmount[1].substring(0, 1));
                } else {
                    eight_amount.setText("$ " + amount);
                }
                eight_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                eight_amount.setSelected(false);
                eight_amount.setVisibility(View.VISIBLE);
            } else if (i == 8) {
                amount = (unitPrice * (i + 1)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    nine_amount.setText("$ " + decimalAmount[0] + "." + decimalAmount[1].substring(0, 1));
                } else {
                    nine_amount.setText("$ " + amount);
                }
                nine_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                nine_amount.setSelected(false);
                nine_amount.setVisibility(View.VISIBLE);
            } else if (i == 9) {
                if (selectedPosition == 2) {
                    bet_other.setText("$ 100.0");
                } else {
                    bet_other.setText("OTHER");
                }
                betAmtMul = 0;
                amountOther = 0;
                nine_amount.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                nine_amount.setSelected(false);
                nine_amount.setVisibility(View.VISIBLE);
            }

//            }
//            else {
//                setVisible(i, false, "");
//            }

        }


//        int totalInvisible = 10 - totalBets;
//        while (totalInvisible > 0) {
//            if (totalInvisible == 1) {
//                nine_amount.setVisibility(View.INVISIBLE);
//            } else if (totalInvisible == 2) {
//                eight_amount.setVisibility(View.INVISIBLE);
//            } else if (totalInvisible == 3) {
//                seven_amount.setVisibility(View.INVISIBLE);
//            } else if (totalInvisible == 4) {
//                six_amount.setVisibility(View.INVISIBLE);
//            } else if (totalInvisible == 5) {
//                five_amount.setVisibility(View.INVISIBLE);
//            } else if (totalInvisible == 6) {
//                four_amount.setVisibility(View.INVISIBLE);
//            } else if (totalInvisible == 7) {
//                third_amount.setVisibility(View.INVISIBLE);
//            } else if (totalInvisible == 8) {
//                second_amount.setVisibility(View.INVISIBLE);
//            } else if (totalInvisible == 9) {
//                first_amount.setVisibility(View.INVISIBLE);
//            }
//            totalInvisible--;
//        }
    }


    protected void setBetSelection(View currentBet) {
        lastBetAmountPosition = 1;
        if (selectedPosition != 2) {
            betAmtMul = 0;
            amountOther = 0;
            bet_other.setText("OTHER");
        }
        number_picked.setText("1");
//        quick_pick_number_layout_upper.setVisibility(View.INVISIBLE);
        lastBetSelected.setBackgroundColor(Color.parseColor("#000FF0"));
        lastBetSelected.setSelected(false);
        currentBet.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
        currentBet.setSelected(true);
        lastBetSelected = currentBet;
        btn_quick_pick.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selector_normal));
        btn_quick_pick.setSelected(false);

        resetData();
        currentBetType = ((TextView) currentBet).getText().toString();
        if (selectedPosition == 1) {
            if (currentBetType.equalsIgnoreCase("direct12")) {
                selected_numbers.setText(hashMap.size() + "");
                no_of_lines.setText(hashMap.size() == 12 ? "1" : "0");
                buy_amt.setText("$ 0.0");
            } else {
                selected_numbers.setText(hashMap.size() + "");
                no_of_lines.setText("1");
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                buy_amt.setText("$ " + (unitPrice * totalSelected) + "");
            }
            setMiniKeno(false);
        } else if (selectedPosition == 0) {
            selected_numbers.setText(hashMap.size() + "");
            no_of_lines.setText("0");
            buy_amt.setText("$ 0.0");
        } else if (selectedPosition == 3) {
            selected_numbers.setText(hashMap.size() + "");
            no_of_lines.setText("0");
            buy_amt.setText("$ 0.0");
        }

    }

    protected long factorial(long n) {
        return (n < 1) ? 1 : n * factorial(n - 1);
    }

    public int generate(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    protected void setBetAmount(double amount) {
        if (!no_of_lines.getText().equals("0") && selectedPosition == 0) {
            this.amount = (amount * totalSelected * Integer.parseInt(no_of_lines.getText().toString())) + "";
            if (this.amount.contains(".")) {
                String[] decimalAmount = this.amount.split("[.]");
                this.amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
            }
            buy_amt.setText("$ " + this.amount);
        } else if (selectedPosition == 4) {
            unitPrice = totalSelected * currentGame.getBets()[0].getUnitPrice() * lastBetAmountPosition;
            int totalCount = hashMap.size();
            if (totalCount == 0) {
                totalCount = Integer.parseInt(number_picked.getText().toString());
            }
            this.amount = (unitPrice * totalCount) + "";
            if (this.amount.contains(".")) {
                String[] decimalAmount = this.amount.split("[.]");
                this.amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
            }
            buy_amt.setText("$ " + this.amount);
        } else if (!no_of_lines.getText().equals("0")) {
            this.amount = (amount * totalSelected) + "";
            if (this.amount.contains(".")) {
                String[] decimalAmount = this.amount.split("[.]");
                this.amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
            }
            buy_amt.setText("$ " + this.amount);
        }

    }

    protected void setBetAmount() {
        unitPrice = currentGame.getBets()[0].getUnitPrice();
        amount = (unitPrice * (lastBetAmountPosition)) + "";
        if (amount.contains(".")) {
            String[] decimalAmount = amount.split("[.]");
            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        amount = (Double.parseDouble(amount) * totalSelected) + "";
        if (amount.contains(".")) {
            String[] decimalAmount = amount.split("[.]");
            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        buy_amt.setText("$ " + amount);
    }

    protected void setBetAmount(int totalNumber) {
        unitPrice = currentGame.getBets()[0].getUnitPrice();
        amount = (unitPrice * (lastBetAmountPosition)) + "";
        if (amount.contains(".")) {
            String[] decimalAmount = amount.split("[.]");
            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        amount = (Double.parseDouble(amount) * totalSelected * totalNumber) + "";
        if (amount.contains(".")) {
            String[] decimalAmount = amount.split("[.]");
            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        buy_amt.setText("$ " + amount);
    }

    protected void setBetAmount(long noOfLine) {
        unitPrice = currentGame.getBets()[0].getUnitPrice();
        amount = (unitPrice * totalSelected * (lastBetAmountPosition)) + "";
        if (amount.contains(".")) {
            String[] decimalAmount = amount.split("[.]");
            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        amount = (Double.parseDouble(amount) * noOfLine) + "";
        if (amount.contains(".")) {
            String[] decimalAmount = amount.split("[.]");
            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        buy_amt.setText("$ " + amount);
    }


    protected void changeDraws() {
        if (btn_quick_pick.isSelected()) {
            btn_quick_pick.setSelected(false);
            hashMap.clear();
        }
        more_draw.setVisibility(View.GONE);
        more_draw2.setVisibility(View.GONE);
        totalSelected = 1;
        for (GameBean.Games.Draws draws : currentGame.getDraws()) {
            draws.setIsChecked(false);
        }
    }

    protected void purchaseRequestMiniRoulette() {
        try {

            JSONObject superOjbedt = new JSONObject();
            JSONObject mainObject = new JSONObject();

            JSONArray drawData = new JSONArray();
            for (GameBean.Games.Draws draw : currentGame.getDraws()) {
                if (draw.isChecked()) {
                    JSONObject drawId = new JSONObject();
                    drawId.put(PurchaseLabels.drawId, draw.getDrawId() + "");
                    drawData.put(drawId);
                }
            }

            mainObject.put("drawData", drawData);
            mainObject.put("gameName", currentGame.getGameCode());
            mainObject.put("noOfDraws", totalSelected > 1 ? totalSelected : 1);
            if (isDrawSelected) {
                mainObject.put("isAdvancePlay", true);
                mainObject.put("isDrawManual", false);
            } else {
                mainObject.put("isAdvancePlay", false);
                mainObject.put("isDrawManual", true);
            }


            JSONArray betTypeData = new JSONArray();

            for (int i = 0; i < numbersView.size(); i++) {
                if (numbersView.get(i).isSelected() || miniroulette_chip.get(i).isSelected()) {

                    Button selected = (Button) miniroulette_chip.get(i);
                    JSONObject int_ten = new JSONObject();

                    int_ten.put("noPicked", mini_roulettte_betsMap_nopicked[i] + "");
                    int_ten.put("betAmtMul", "1");
                    int_ten.put("isQp", "false");
                    int_ten.put("pickedNumbers", mini_roulettte_betsMap_pickedNumbers[i]);
                    int_ten.put("betName", mini_roulettte_betsMap_names[i]);
                    int_ten.put("unitPrice", Integer.parseInt(selected.getText().toString()));
                    int_ten.put("noOfLines", 1);

                    betTypeData.put(int_ten);

                }
            }
            superOjbedt.put("commonSaleData", mainObject);
            superOjbedt.put("betTypeData", betTypeData);
            superOjbedt.put(PurchaseLabels.totalPurchaseAmt, (miniroulettebetamount * totalSelected) + "");
            superOjbedt.put(PurchaseLabels.noOfPanel, betTypeData.length());
            superOjbedt.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            superOjbedt.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/oneToTwelveRouletteBuy.action?json=" + superOjbedt.toString();
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityDgeGamesTps390.this, "TwleveByTwenty", ActivityDgeGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(ActivityDgeGamesTps390.this);
            httpRequest.executeTask();
            Log.i("Json == ", superOjbedt.toString());
//            resetData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    protected void purchageRequest() {
        JSONObject superOjbedt = new JSONObject();
        JSONObject mainObject = new JSONObject();
//        mainObject.put(PurchaseLabels.merchantCode, "Weaver");
        try {


            JSONArray drawData = new JSONArray();
            for (GameBean.Games.Draws draw : currentGame.getDraws()) {
                if (draw.isChecked()) {
                    JSONObject drawId = new JSONObject();
                    drawId.put(PurchaseLabels.drawId, draw.getDrawId());
                    drawData.put(drawId);
                }
            }
            mainObject.put("drawData", drawData);
            mainObject.put("gameName", currentGame.getGameCode());
            mainObject.put("noOfDraws", totalSelected > 1 ? totalSelected : 1);
            if (isDrawSelected) {
                mainObject.put("isAdvancePlay", true);
                mainObject.put("isDrawManual", false);
            } else {
                mainObject.put("isAdvancePlay", false);
                mainObject.put("isDrawManual", true);
            }


            String selectedNumber = "";

            int totalNumberPicked = 0;

            JSONArray panelData = new JSONArray();
            JSONObject panel = new JSONObject();
            if (selectedPosition != 4) {
                for (int i = 0; i < numbersView.size(); i++) {
                    if (numbersView.get(i).isSelected()) {
                        if (selectedNumber.trim().length() == 0) {
                            if (selectedPosition == 4) {
                                LinearLayout linearLayout = ((LinearLayout) numbersView.get(i));
//                            String number = ((TextView) linearLayout.getChildAt(0)).getText().toString();
                                selectedNumber = selectedNumber + (hashMap.get(i));
                            } else {
                                selectedNumber = selectedNumber + ((TextView) numbersView.get(i)).getText().toString();
                            }

                        } else {
                            if (selectedPosition == 4) {
                                selectedNumber = selectedNumber + "," + (hashMap.get(i) < 10 ? ("0" + hashMap.get(i)) : hashMap.get(i));
                            } else {
                                selectedNumber = selectedNumber + "," + ((TextView) numbersView.get(i)).getText().toString();
                            }

                        }
                        totalNumberPicked++;
                    }

                }

                if (selectedPosition == 1 && btn_quick_pick.isSelected()) {
                    panel.put("betName", "Direct12");
                } else {
                    panel.put("betName", currentGame.getBets()[betTypeSelected - 1].getBetName());
                }


                if (selectedPosition == 0) {
                    panel.put("noPicked", totalNumberPicked + "");
                } else if (selectedPosition == 1) {
                    panel.put("noPicked", "12");
                } else if (selectedPosition == 3) {
                    panel.put("noPicked", totalNumberPicked + "");
                } else if (selectedPosition == 4) {
                    panel.put("noPicked", "1");
                }

                panel.put("isQp", btn_quick_pick.isSelected());
                panel.put("pickedNumbers", selectedNumber);
                panel.put("betAmtMul", lastBetAmountPosition);
                panel.put("QPPreGenerated", btn_quick_pick.isSelected());
//            panel.put(PurchaseLabels.noOfLines, no_of_lines.getText().toString() + "");
                panelData.put(panel);

//                superOjbedt.put("commonSaleData", mainObject);
//                superOjbedt.put("betTypeData", panelData);
            } else {
                for (int i = 0; i < numbersView.size(); i++) {
                    if (numbersView.get(i).isSelected()) {
                        LinearLayout linearLayout = ((LinearLayout) numbersView.get(i));
                        selectedNumber = ((TextView) linearLayout.getChildAt(0)).getText().toString();

                        panel = new JSONObject();
                        panel.put("betName", currentGame.getBets()[0].getBetName());
                        panel.put("noPicked", "1");


                        panel.put("isQp", btn_quick_pick.isSelected());
                        panel.put("pickedNumbers", selectedNumber);
                        panel.put("betAmtMul", lastBetAmountPosition);
                        panel.put("QPPreGenerated", btn_quick_pick.isSelected());
//            panel.put(PurchaseLabels.noOfLines, no_of_lines.getText().toString() + "");
                        panelData.put(panel);
                    }

                }


            }
            superOjbedt.put("commonSaleData", mainObject);
            superOjbedt.put("betTypeData", panelData);
            unitPrice = currentGame.getBets()[0].getUnitPrice();
            amount = (unitPrice * (lastBetAmountPosition)) + "";
            if (amount.contains(".")) {
                String[] decimalAmount = amount.split("[.]");
                amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
            }
            if (selectedPosition == 1) {
                amount = (Double.parseDouble(amount) * totalSelected) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                superOjbedt.put(PurchaseLabels.totalPurchaseAmt, amount + "");
            } else if (selectedPosition == 0) {
                amount = ((Double.parseDouble(amount) * totalSelected * Integer.parseInt(no_of_lines.getText().toString()))) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                superOjbedt.put(PurchaseLabels.totalPurchaseAmt, amount + "");
            } else if (selectedPosition == 3) {
                amount = (Double.parseDouble(amount) * totalSelected) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                superOjbedt.put(PurchaseLabels.totalPurchaseAmt, amount + "");
            } else if (selectedPosition == 4) {
                amount = (Double.parseDouble(amount) * hashMap.size() * totalSelected) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                superOjbedt.put(PurchaseLabels.totalPurchaseAmt, amount + "");
            }
            superOjbedt.put(PurchaseLabels.noOfPanel, 1);
            superOjbedt.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            superOjbedt.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());
            String url = "";
            if (selectedPosition == 0) {
//                http://162.13.131.240:80/LMSLinuxNew/com/skilrock/lms/web/drawGames/playMgmt/kenoTwoBuy.action
                url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/kenoTwoBuy.action?json=" + superOjbedt.toString();
            } else if (selectedPosition == 1) {
                url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/twelveByTwentyFourBuy.action?json=" + superOjbedt.toString();
            } else if (selectedPosition == 3) {
                url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/kenoSixBuy.action?json=" + superOjbedt.toString();
            } else if (selectedPosition == 4) {
                url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/oneToTwelveBuy.action?json=" + superOjbedt.toString();
            }

            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivityDgeGamesTps390.this, "TwleveByTwenty", ActivityDgeGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(ActivityDgeGamesTps390.this);
            httpRequest.executeTask();
            Log.i("Json == ", mainObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected void setMaxBetLuckyNumber() {
        if (currentBetType.equalsIgnoreCase("direct1")) {
            totalMinLucky = 1;
            totalMaxLucky = 1;
        } else if (currentBetType.equalsIgnoreCase("direct2")) {
            totalMinLucky = 2;
            totalMaxLucky = 2;
        } else if (currentBetType.equalsIgnoreCase("direct3")) {
            totalMinLucky = 3;
            totalMaxLucky = 3;
        } else if (currentBetType.equalsIgnoreCase("direct4")) {
            totalMinLucky = 4;
            totalMaxLucky = 4;
        } else if (currentBetType.equalsIgnoreCase("direct5")) {
            totalMinLucky = 5;
            totalMaxLucky = 5;
        } else if (currentBetType.equalsIgnoreCase("perm1")) {
            totalMinLucky = 10;
            totalMaxLucky = 10;
        } else if (currentBetType.equalsIgnoreCase("perm2")) {
            totalMinLucky = 3;
            totalMaxLucky = 20;
        } else if (currentBetType.equalsIgnoreCase("perm3")) {
            totalMinLucky = 4;
            totalMaxLucky = 20;
        }
    }

    protected void callSportData() {
        String url = Utility.baseUrl + Utility.portNumber + Utility.sportProjectName;
        HttpRequest httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(url, this, "fetching game data", this, "sports", Utility.eBetSlipHeader);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("merchantCode", "PMS");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        httpRequest.setIsParams(true, jsonObject.toString());
        TpsGamesClass.getInstance().startLoader(this);
        httpRequest.executeTask();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sport_btn:
                sideDrawerItemId=v.getId();
                sideDrawer.closeDrawers();
                break;
            case R.id.ola_btn:
                sideDrawerItemId=v.getId();
                sideDrawer.closeDrawers();
                break;
            case R.id.threeLines:
                sideDrawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.ebet_btn:
                sideDrawerItemId=v.getId();
                sideDrawer.closeDrawers();
                break;

            case R.id.lucky_numbers:
                if (selectedPosition == 0) {
                    return;
                }

                selectedPosition = 0;
                layout_selected_numbers.setVisibility(View.VISIBLE);
                draws_mini_roulette.setVisibility(View.GONE);
                bet_type_layout.setVisibility(View.VISIBLE);
                quick_pick_number_layout_upper.setVisibility(View.GONE);
                layout_below_sub_header.setVisibility(View.VISIBLE);
                currentGame = TpsGamesClass.getInstance().getLuckyNumber();
                changeDraws();
                numbersView.clear();
                mainviewToadd = inflater.inflate(R.layout.layout_five_by_ninty, null);
                mainviewToadd.setLayoutParams(param);
                linearLayout.removeAllViews();
                linearLayout.addView(mainviewToadd);
                totalCountNumbers = 90;
                changeCurrentSelectionGame();

//                luckyNumber.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                setOnClick(totalCountNumbers, false, false);
                setAllBets();
                break;
            case R.id.mini_keno:
                if (selectedPosition == 1) {
                    return;
                }
                selectedPosition = 1;
                layout_selected_numbers.setVisibility(View.VISIBLE);
                draws_mini_roulette.setVisibility(View.GONE);
                bet_type_layout.setVisibility(View.VISIBLE);
                quick_pick_number_layout_upper.setVisibility(View.GONE);
                layout_below_sub_header.setVisibility(View.VISIBLE);
                currentGame = TpsGamesClass.getInstance().getMiniKeno();
                changeDraws();
                numbersView.clear();
                mainviewToadd = inflater.inflate(R.layout.game_keno, null);
                mainviewToadd.setLayoutParams(param);
                linearLayout.removeAllViews();
                linearLayout.addView(mainviewToadd);
                totalCountNumbers = 24;
                changeCurrentSelectionGame();
//                mini_keno.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                setOnClick(totalCountNumbers, false, false);
                setAllBets();

                break;

            case R.id.mini_roulette:
                if (selectedPosition == 2) {
                    return;
                }
                selectedPosition = 2;
                layout_selected_numbers.setVisibility(View.GONE);
                draws_mini_roulette.setVisibility(View.VISIBLE);
                layout_below_sub_header.setVisibility(View.GONE);
                currentGame = TpsGamesClass.getInstance().getMiniRoulette();
                changeDraws();
                numbersView.clear();
                miniroulettebetamount = 0;
                miniroulette_chip.clear();
                mini_roulettte_betsMap.clear();
                mainviewToadd = inflater.inflate(R.layout.mini_roulette_game, null);
                mainviewToadd.setLayoutParams(param);
                linearLayout.removeAllViews();
                linearLayout.addView(mainviewToadd);
                totalCountNumbers = 33;
                buy_amt.setText("$ 0.0");
                for (int x = 0; x < currentGame.getBets().length; x++) {
                    mini_roulettte_betsMap.put(currentGame.getBets()[x].getBetName(), currentGame.getBets()[x]);
                }
                changeCurrentSelectionGame();
                setAllBets();
                setOnClick(totalCountNumbers, false, true);
//                mini_roulette.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                break;
            case R.id.super_keno:
                if (selectedPosition == 3) {
                    return;
                }
                selectedPosition = 3;
                layout_selected_numbers.setVisibility(View.VISIBLE);
                draws_mini_roulette.setVisibility(View.GONE);
                bet_type_layout.setVisibility(View.VISIBLE);
                quick_pick_number_layout_upper.setVisibility(View.GONE);
                layout_below_sub_header.setVisibility(View.VISIBLE);
                currentGame = TpsGamesClass.getInstance().getSuperKeno();
                changeDraws();
                numbersView.clear();
                mainviewToadd = inflater.inflate(R.layout.game_super_keno, null);
                mainviewToadd.setLayoutParams(param);
                linearLayout.removeAllViews();
                linearLayout.addView(mainviewToadd);
                totalCountNumbers = 80;
                changeCurrentSelectionGame();
//                super_keno.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                setOnClick(totalCountNumbers, false, false);

                setAllBets();

                break;
            case R.id.one_to_twelve:
                if (selectedPosition == 4) {
                    return;
                }
                selectedPosition = 4;
                layout_selected_numbers.setVisibility(View.VISIBLE);
                draws_mini_roulette.setVisibility(View.GONE);
                bet_type_layout.setVisibility(View.GONE);
                quick_pick_number_layout_upper.setVisibility(View.VISIBLE);
                quick_pick_number_layout_upper.setVisibility(View.INVISIBLE);
                layout_below_sub_header.setVisibility(View.VISIBLE);
                currentGame = TpsGamesClass.getInstance().getFortune();
                changeDraws();
                numbersView.clear();
                mainviewToadd = inflater.inflate(R.layout.game_one_to_twelve, null);
                mainviewToadd.setLayoutParams(param);
                linearLayout.removeAllViews();
                linearLayout.addView(mainviewToadd);
                totalCountNumbers = 12;
                changeCurrentSelectionGame();
//                one_to_twelve.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                setOnClick(totalCountNumbers, true, false);
                setAllBets();
                break;
            case R.id.more_col_id:
                if (horizontalScrollView instanceof HorizontalScrollView)
                    ((HorizontalScrollView) horizontalScrollView).fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                else
                    ((ScrollView) horizontalScrollView).fullScroll(View.FOCUS_DOWN);
                break;
            case R.id.bet_type1:
                if (bet_type1.isSelected()) {
                    return;
                }

                betTypeSelected = 1;
                setBetSelection(bet_type1);
                if (selectedPosition == 3) {
                    setSuperKeno(false);
                } else if (selectedPosition == 1) {
                    setMiniKeno(false);
                } else if (selectedPosition == 0) {
                    setMaxBetLuckyNumber();
                }
                break;
            case R.id.bet_type2:
                if (bet_type2.isSelected()) {
                    return;
                }

                betTypeSelected = 2;
                setBetSelection(bet_type2);
                if (selectedPosition == 3) {
                    setSuperKeno(false);
                } else if (selectedPosition == 1) {
                    setMiniKeno(false);
                } else if (selectedPosition == 0) {
                    setMaxBetLuckyNumber();
                }

                break;
            case R.id.bet_type3:
                if (bet_type3.isSelected()) {
                    return;
                }

                betTypeSelected = 3;
                setBetSelection(bet_type3);
                if (selectedPosition == 3) {
                    setSuperKeno(false);
                } else if (selectedPosition == 1) {
                    setMiniKeno(false);
                } else if (selectedPosition == 0) {
                    setMaxBetLuckyNumber();
                }
                break;
            case R.id.bet_type4:
                if (bet_type4.isSelected()) {
                    return;
                }

                betTypeSelected = 4;
                setBetSelection(bet_type4);
                if (selectedPosition == 3) {
                    setSuperKeno(false);
                } else if (selectedPosition == 1) {
                    setMiniKeno(false);
                } else if (selectedPosition == 0) {
                    setMaxBetLuckyNumber();
                }
                break;
            case R.id.bet_type5:
                if (bet_type5.isSelected()) {
                    return;
                }

                betTypeSelected = 5;
                setBetSelection(bet_type5);
                if (selectedPosition == 3) {
                    setSuperKeno(false);
                } else if (selectedPosition == 1) {
                    setMiniKeno(false);
                } else if (selectedPosition == 0) {
                    setMaxBetLuckyNumber();
                }
                break;
            case R.id.bet_type6:
                if (bet_type6.isSelected()) {
                    return;
                }

                betTypeSelected = 6;
                setBetSelection(bet_type6);
                if (selectedPosition == 3) {
                    setSuperKeno(false);
                } else if (selectedPosition == 1) {
                    setMiniKeno(false);
                } else if (selectedPosition == 0) {
                    setMaxBetLuckyNumber();
                }
                break;
            case R.id.bet_type7:
                if (bet_type7.isSelected()) {
                    return;
                }

                betTypeSelected = 7;
                setBetSelection(bet_type7);
                if (selectedPosition == 3) {
                    setSuperKeno(false);
                } else if (selectedPosition == 1) {
                    setMiniKeno(false);
                } else if (selectedPosition == 0) {
                    setMaxBetLuckyNumber();
                }
                break;
            case R.id.bet_type8:
                if (bet_type8.isSelected()) {
                    return;
                }
                betTypeSelected = 8;
                setBetSelection(bet_type8);
                if (selectedPosition == 3) {
                    setSuperKeno(false);
                } else if (selectedPosition == 1) {
                    setMiniKeno(false);
                } else if (selectedPosition == 0) {
                    setMaxBetLuckyNumber();
                }
                break;
            case R.id.bet_type9:
                if (bet_type9.isSelected()) {
                    return;
                }
                betTypeSelected = 9;
                setBetSelection(bet_type9);
                if (selectedPosition == 3) {
                    setSuperKeno(false);
                } else if (selectedPosition == 1) {
                    setMiniKeno(false);
                } else if (selectedPosition == 0) {
                    setMaxBetLuckyNumber();
                }
                break;
            case R.id.bet_type10:
                if (bet_type10.isSelected()) {
                    return;
                }
                betTypeSelected = 10;
                setBetSelection(bet_type10);
                if (selectedPosition == 3) {
                    setSuperKeno(false);
                } else if (selectedPosition == 1) {
                    setMiniKeno(false);
                } else if (selectedPosition == 0) {
                    setMaxBetLuckyNumber();
                }
                break;
            case R.id.btn_quick_pick:
                if (!btn_quick_pick.isSelected()) {
                    if (selectedPosition == 1) {
                        currentBetType = "";
                    }
                    btn_quick_pick.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                    btn_quick_pick.setSelected(true);
                    number_picked.setText("1");
                }


                if (selectedPosition == 0) {
                    setLuckyNumberQp();
                } else if (selectedPosition == 1) {
                    no_of_lines.setText("1");
                    setMiniKeno(true);

                } else if (selectedPosition == 3) {
                    setSuperKeno(true);
                } else if (selectedPosition == 4) {

                    quick_pick_number_layout_upper.setVisibility(View.VISIBLE);
                    no_of_lines.setText("1");
                    setFortuenData(Integer.parseInt(number_picked.getText().toString()));
                }
                break;

            case R.id.first_amount:
                if (lastBetAmountSelected != null && lastBetAmountSelected.getId() == v.getId()) {
                    return;
                }
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                v.setSelected(true);
                lastBetAmountSelected = v;
                lastBetAmountPosition = 1;
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                amount = (unitPrice * (lastBetAmountPosition)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }

                setBetAmount(Double.parseDouble(amount));
                break;
            case R.id.second_amount:
                if (lastBetAmountSelected != null && lastBetAmountSelected.getId() == v.getId()) {
                    return;
                }
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                v.setSelected(true);
                lastBetAmountSelected = v;
                lastBetAmountPosition = 2;
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                amount = (unitPrice * (lastBetAmountPosition)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                setBetAmount(Double.parseDouble(amount));
                break;
            case R.id.third_amount:
                if (lastBetAmountSelected != null && lastBetAmountSelected.getId() == v.getId()) {
                    return;
                }
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                v.setSelected(true);
                lastBetAmountSelected = v;
                lastBetAmountPosition = 3;
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                amount = (unitPrice * (lastBetAmountPosition)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                setBetAmount(Double.parseDouble(amount));
                break;
            case R.id.four_amount:
                if (lastBetAmountSelected != null && lastBetAmountSelected.getId() == v.getId()) {
                    return;
                }
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                v.setSelected(true);
                lastBetAmountSelected = v;
                lastBetAmountPosition = 4;
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                amount = (unitPrice * (lastBetAmountPosition)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                setBetAmount(Double.parseDouble(amount));
                break;
            case R.id.five_amount:
                if (lastBetAmountSelected != null && lastBetAmountSelected.getId() == v.getId()) {
                    return;
                }
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                v.setSelected(true);
                lastBetAmountSelected = v;
                lastBetAmountPosition = 5;
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                amount = (unitPrice * (lastBetAmountPosition)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                setBetAmount(Double.parseDouble(amount));
                break;
            case R.id.six_amount:
                if (lastBetAmountSelected != null && lastBetAmountSelected.getId() == v.getId()) {
                    return;
                }
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                v.setSelected(true);
                lastBetAmountSelected = v;
                lastBetAmountPosition = 6;
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                amount = (unitPrice * (lastBetAmountPosition)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                setBetAmount(Double.parseDouble(amount));
                break;
            case R.id.seven_amount:
                if (lastBetAmountSelected != null && lastBetAmountSelected.getId() == v.getId()) {
                    return;
                }
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                v.setSelected(true);
                lastBetAmountSelected = v;
                lastBetAmountPosition = 7;
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                amount = (unitPrice * (lastBetAmountPosition)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                setBetAmount(Double.parseDouble(amount));
                break;
            case R.id.eight_amount:
                if (lastBetAmountSelected != null && lastBetAmountSelected.getId() == v.getId()) {
                    return;
                }
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                v.setSelected(true);
                lastBetAmountSelected = v;
                lastBetAmountPosition = 8;
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                amount = (unitPrice * (lastBetAmountPosition)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                setBetAmount(Double.parseDouble(amount));
                break;
            case R.id.nine_amount:
                if (lastBetAmountSelected != null && lastBetAmountSelected.getId() == v.getId()) {
                    return;
                }
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                v.setSelected(true);
                lastBetAmountSelected = v;
                lastBetAmountPosition = 9;
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                amount = (unitPrice * (lastBetAmountPosition)) + "";
                if (amount.contains(".")) {
                    String[] decimalAmount = amount.split("[.]");
                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                }
                setBetAmount(Double.parseDouble(amount));
                break;
            case R.id.bet_other:
//                if (lastBetAmountSelected != null && lastBetAmountSelected.getId() == v.getId()) {
//                    return;
//                }
                if (lastBetAmountSelected.isSelected()) {
//                            game_selector_normal
                    lastBetAmountSelected.setBackgroundDrawable(getResources().getDrawable(R.drawable.bet_amount_selector_normal_ebet));
                    lastBetAmountSelected.setSelected(false);

                }
                v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                v.setSelected(true);
                lastBetAmountSelected = v;
                selectedPositionBeforeOther = lastBetAmountPosition;
                lastBetAmountPosition = 10;
                if (selectedPosition != 2) {
                    showOtherDialog(ActivityDgeGamesTps390.this);
                }

                break;
            case R.id.draws:

                showDrawDialog(ActivityDgeGamesTps390.this, currentGame.getDraws());
                break;
            case R.id.btn_negative:
                if (dialog != null) {
                    dialog.dismiss();
                }

                if (selectedPosition == 0) {
                    if (no_of_lines.getText().toString().equalsIgnoreCase("0")) {
                        btn_quick_pick.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selector_normal));
                        btn_quick_pick.setSelected(false);
                        selected_numbers.setText(hashMap.size() + "");
                        no_of_lines.setText("0");
                        buy_amt.setText("$ 0.0");
                    }

                }
                break;
            case R.id.btn_positive:
                totalSelected = 0;
                isDrawSelected = true;
                for (GameBean.Games.Draws draws : currentGame.getDraws()) {
                    if (draws.isChecked()) {
                        if (totalSelected == 0) {
                            draw_date_time.setText(getDataFormat(draws.getDrawDateTime()));
                            draw_date_time2.setText(getDataFormat(draws.getDrawDateTime()));
                        }
                        totalSelected++;
                    }
                }
                if (totalSelected == 0) {
                    totalSelected = 1;
                }
                if (totalSelected > 1) {
                    more_draw.setVisibility(View.VISIBLE);
                    more_draw.setText("+" + (totalSelected - 1) + " more");
                    more_draw2.setVisibility(View.VISIBLE);
                    more_draw2.setText("+" + (totalSelected - 1) + " more");
                } else {
                    more_draw.setVisibility(View.GONE);
                    more_draw2.setVisibility(View.GONE);
                }
                unitPrice = currentGame.getBets()[0].getUnitPrice();
                if (selectedPosition == 0) {
                    setBetAmount(Long.parseLong(no_of_lines.getText().toString()));
                } else {
                    if (selectedPosition != 2) {
                        if (selectedPosition == 4) {
                            this.amount = (unitPrice * lastBetAmountPosition * totalSelected * Integer.parseInt(selected_numbers.getText().toString())) + "";
                            if (this.amount.contains(".")) {
                                String[] decimalAmount = this.amount.split("[.]");
                                this.amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                            }
                            buy_amt.setText("$ " + amount);
                        } else if (selectedPosition == 1 || selectedPosition == 3) {
                            this.amount = (unitPrice * lastBetAmountPosition * totalSelected * Integer.parseInt(no_of_lines.getText().toString())) + "";
                            if (this.amount.contains(".")) {
                                String[] decimalAmount = this.amount.split("[.]");
                                this.amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                            }
                            buy_amt.setText("$ " + amount);
                        } else {
                            this.amount = (unitPrice * lastBetAmountPosition * totalSelected) + "";
                            if (this.amount.contains(".")) {
                                String[] decimalAmount = this.amount.split("[.]");
                                this.amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                            }
                            buy_amt.setText("$ " + amount);
                        }
                    } else {
                        this.amount = (miniroulettebetamount * totalSelected) + "";
                        if (this.amount.contains(".")) {
                            String[] decimalAmount = this.amount.split("[.]");
                            this.amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                        }
                        buy_amt.setText("$ " + amount + ".0");
                    }
                }

                break;
            case R.id.buy_btn:
                if (selectedPosition == 2) {
                    if (!buy_amt.getText().toString().equals("$ 0.0")) {
                        openCard();
                    }
                } else {
                    if (selectedPosition == 0 && hashMap.size() < totalMinLucky && !btn_quick_pick.isSelected()) {
                        return;
                    } else if (selectedPosition == 1 && (selected_numbers.getText().toString().equalsIgnoreCase("0")) && !btn_quick_pick.isSelected()) {
                        return;
                    } else if (selectedPosition == 3 && hashMap.size() < totalNumberToBeSelectedSuperKeno && !btn_quick_pick.isSelected()) {
                        return;
                    } else if (selectedPosition == 4 && hashMap.size() < fortuneMinData && !btn_quick_pick.isSelected()) {
                        return;
                    } else if (selectedPosition == 1 && hashMap.size() < 12 && currentBetType.equalsIgnoreCase("direct12") && !btn_quick_pick.isSelected()) {
                        return;
                    }
                    openCard();

                }
                break;
            case R.id.pick_down:
                int dec = Integer.parseInt(number_picked.getText().toString());
                if (dec > 1) {
                    dec--;
                    number_picked.setText(dec + "");
                }
                btn_quick_pick.performClick();
                break;
            case R.id.pick_up:
                int inc = Integer.parseInt(number_picked.getText().toString());
                if (inc < fortuenMaxData) {
                    inc++;
                    number_picked.setText(inc + "");
                }
                btn_quick_pick.performClick();
                break;

            case R.id.common_winning_claim_btn:
                if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("PT7003")) {
                    intent = new Intent(ActivityDgeGamesTps390.this, ClaimWinningActivityTousei.class);
                    startActivityForResult(intent, 055);
                    break;
                } else {
//                    if (!TpsGamesClass.getInstance().getIfHasCamera()) {
//                        TpsGamesClass.getInstance().showAToast("Device has No Camera", ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
//                        return;
//                    }
                    if (TpsGamesClass.getInstance().isCameraOpen()) {
                        return;
                    }
                    TpsGamesClass.getInstance().setCameraOpen(true);
                    intent = new Intent(ActivityDgeGamesTps390.this, ClaimWinningActivityTps390.class);
                    startActivityForResult(intent, 055);
                    break;
                }
            case R.id.btn_refresh:
                if (selectedPosition == 2) {
                    resetData();
                } else {
                    resetEveryThing();
                }
                break;

            case R.id.common_bet_slip_btn:
                boolean isOpenBetSlip = false;
                if (TpsGamesClass.getInstance().getDeviceName().contains("550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx053")||TpsGamesClass.getInstance().getDeviceName().contains("390")) {
                    new AlertDialog.Builder(ActivityDgeGamesTps390.this)
                            .setMessage("This service is not available.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    // continue with delete
                                }
                            })
                            .show();
                    return;
                } else if (!TpsGamesClass.getInstance().getIfHasCamera()) {
                    new AlertDialog.Builder(ActivityDgeGamesTps390.this)
                            .setMessage("This service is not available.")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    // continue with delete
                                }
                            })
                            .show();
                    return;
                }

                if (TpsGamesClass.getInstance().isCameraOpen()) {
                    return;
                }
                if (!isOpenBetSlip) {
                    TpsGamesClass.getInstance().setCameraOpen(true);
                    startActivityForResult(new Intent(ActivityDgeGamesTps390.this, ActivityBetSlipCamera.class), 056);
                }
                break;

            default:

                if (btn_quick_pick.isSelected() || (selectedPosition == 1 && currentBetType.trim().length() > 0 && !currentBetType.equalsIgnoreCase("direct12"))) {
                    if (selectedPosition == 4) {
                        btn_quick_pick.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selector_normal));
                        btn_quick_pick.setSelected(false);
                        quick_pick_number_layout_upper.setVisibility(View.INVISIBLE);
                    }
                    if (selectedPosition == 4 && totalCountFortune > 0) {
                        quick_pick_number_layout_upper.setVisibility(View.INVISIBLE);
                        if (totalCountFortune > 0) {
                            for (int i = 0; i < numbersView.size(); i++) {
                                if (numbersView.get(i).isSelected()) {
                                    hashMap.put(i, i);
                                }
                            }
                        }
                        totalCountFortune = 0;
                        number_picked.setText("0");
                    }
                    return;
                }

                if (hashMap.size() == 12 && selectedPosition == 1 && !v.isSelected()) {
                    return;
                }

                if (selectedPosition == 0 && hashMap.size() == totalMaxLucky && !v.isSelected()) {
                    return;
                }

                if (selectedPosition == 3 && hashMap.size() == totalNumberToBeSelectedSuperKeno && !v.isSelected()) {
                    return;
                }
                if (selectedPosition == 4 && totalCountFortune > 0) {
                    quick_pick_number_layout_upper.setVisibility(View.INVISIBLE);
                    if (totalCountFortune > 0) {
                        for (int i = 0; i < numbersView.size(); i++) {
                            if (numbersView.get(i).isSelected()) {
                                hashMap.put(i, i);
                            }
                        }
                    }
                    totalCountFortune = 0;
                }
                if (selectedPosition == 4 && hashMap.size() == fortuenMaxData && !v.isSelected()) {
                    return;
                }


                for (int i = 0; i < totalCountNumbers; i++) {
                    if (selectedPosition == 2) {
                        GameBean.Games.Bets games = null;
                        if (v.getId() == totalView[i] || v.getId() == miniroulettechip[i]) {
                            games = mini_roulettte_betsMap.get(mini_roulettte_betsMap_names[i]);
                            Button selected = (Button) miniroulette_chip.get(i);
                            double betamount = Double.parseDouble((games.getUnitPrice() + ""));
                            if (selected.getText().toString().equals("")) {
                                selected.setText("0");
                            }
                            int currentbetamount = (((int) betamount) * lastBetAmountPosition);
                            if ((currentbetamount + Integer.parseInt(selected.getText() + "")) <= games.getMaxBetAmtMul()) {
                                miniroulette_chip.get(i).setVisibility(View.VISIBLE);
                                miniroulettebetamount = miniroulettebetamount + currentbetamount;
                                selected.setText(((((int) betamount) * lastBetAmountPosition) + Integer.parseInt(selected.getText() + "")) + "");
                                selected.setBackgroundResource(R.mipmap.chip_image);
                                buy_amt.setText("$ " + miniroulettebetamount * totalSelected + ".0");
                            } else {
                                TpsGamesClass.getInstance().showAToast("Max bet amount allowed on this bet is $" + currentGame.getBets()[lastBetAmountPosition].getMaxBetAmtMul(), ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
                            }
                            v.setSelected(true);
                            break;
                        }
                    } else if (v.getId() == totalView[i]) {
                        int number = 0;
                        if (v instanceof TextView) {
                            number = Integer.parseInt(((TextView) v).getText().toString());
                        } else {
                            number = i;
                        }

                        if (v.isSelected()) {
//                            game_selector_normal
                            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selector_normal));
                            v.setSelected(false);
                            hashMap.remove(number);

                        } else {
                            v.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
                            v.setSelected(true);
                            hashMap.put(number, number);
//                            R.drawable.game_selection_bg_pressed_new
                        }

                        if (selectedPosition == 1) {
                            selected_numbers.setText(hashMap.size() + "");
                            no_of_lines.setText(hashMap.size() == 12 ? "1" : "0");
                        }

                        selected_numbers.setText(hashMap.size() + "");
//                        TpsGamesClass.getInstance().showAToast(((RobotoCommonTextView) v).getText().toString(), ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
                        break;
                    }
                }
                if (selectedPosition == 1) {
                    if (hashMap.size() == 12) {
                        unitPrice = currentGame.getBets()[0].getUnitPrice() * lastBetAmountPosition;
                        amount = unitPrice + "";
                        if (amount.contains(".")) {
                            String[] decimalAmount = amount.split("[.]");
                            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                        }
                        amount = (Double.parseDouble(amount) * totalSelected) + "";
                        if (amount.contains(".")) {
                            String[] decimalAmount = amount.split("[.]");
                            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                        }
                        buy_amt.setText("$ " + amount + "");
                    } else {
                        buy_amt.setText("$ 0.0");
                    }
                } else if (selectedPosition == 0) {

                    int totalNumber = hashMap.size();
                    if (totalNumber >= totalMinLucky) {

                        if (currentBetType.equalsIgnoreCase("perm2") || currentBetType.equalsIgnoreCase("perm3")) {
                            long totalNoOfLine = 0;
                            if (currentBetType.equalsIgnoreCase("perm2")) {
                                totalNoOfLine = factorial(totalNumber) / (factorial(2) * factorial(totalNumber - 2));
                            } else {
                                totalNoOfLine = factorial(totalNumber) / (factorial(3) * factorial(totalNumber - 3));
                            }
                            selected_numbers.setText(hashMap.size() + "");
                            no_of_lines.setText(totalNoOfLine + "");
                            setBetAmount(totalNoOfLine);
                        } else {
                            if (!currentBetType.equalsIgnoreCase("perm1")) {
                                unitPrice = currentGame.getBets()[0].getUnitPrice() * lastBetAmountPosition;
                                amount = unitPrice + "";
                                if (amount.contains(".")) {
                                    String[] decimalAmount = amount.split("[.]");
                                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                                }
                                amount = (Double.parseDouble(amount) * totalSelected) +
                                        "";
                                if (amount.contains(".")) {
                                    String[] decimalAmount = amount.split("[.]");
                                    amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                                }
                                no_of_lines.setText(1 + "");
                                buy_amt.setText("$ " + amount + "");

                            } else if (currentBetType.equalsIgnoreCase("perm1") && hashMap.size() == 10) {
                                selected_numbers.setText(hashMap.size() + "");
                                no_of_lines.setText(10 + "");
                                setBetAmount(10);
                            }

                        }


                    } else {
                        selected_numbers.setText(hashMap.size() + "");
                        no_of_lines.setText(0 + "");
                        setBetAmount(0);
                    }

                } else if (selectedPosition == 3) {
                    if (hashMap.size() == totalNumberToBeSelectedSuperKeno) {
                        unitPrice = currentGame.getBets()[0].getUnitPrice() * lastBetAmountPosition;
                        amount = unitPrice + "";
                        if (amount.contains(".")) {
                            String[] decimalAmount = amount.split("[.]");
                            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                        }
                        amount = (Double.parseDouble(amount) * totalSelected) + "";
                        if (amount.contains(".")) {
                            String[] decimalAmount = amount.split("[.]");
                            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                        }
                        selected_numbers.setText(hashMap.size() + "");
                        no_of_lines.setText("1");
                        buy_amt.setText("$ " + amount + "");
                    } else {
                        selected_numbers.setText(hashMap.size() + "");
                        no_of_lines.setText("0");
                        buy_amt.setText("$ 0.0");
                    }
                } else if (selectedPosition == 4) {
                    if (hashMap.size() == 0) {
                        selected_numbers.setText(hashMap.size() + "");
                        no_of_lines.setText("0");
                    } else {
                        selected_numbers.setText(hashMap.size() + "");
                        no_of_lines.setText("1");
                    }
                    unitPrice = currentGame.getBets()[0].getUnitPrice();
                    amount = unitPrice * lastBetAmountPosition + "";

                    if (amount.contains(".")) {
                        String[] decimalAmount = amount.split("[.]");
                        amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                    }
                    amount = (Double.parseDouble(amount) * totalSelected * hashMap.size()) + "";
                    if (amount.contains(".")) {
                        String[] decimalAmount = amount.split("[.]");
                        amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
                    }
                    buy_amt.setText("$ " + amount);
                }

                break;
        }
    }

    @Override
    public void onResponse(String response, String requestedMethod) {
        isRgCall = false;
        TpsGamesClass.getInstance().stopLoader();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject == null) {

                TpsGamesClass.getInstance().showAToast("No data available!", ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
                return;
            } else if ((jsonObject.has("isSuccess") && jsonObject.optBoolean("isSuccess") == false)) {

                TpsGamesClass.getInstance().showAToast(jsonObject.optString("errorMsg"), ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
                return;
            } else if (jsonObject.has("responseCode") && jsonObject.optString("responseCode").equals("500")) {

                TpsGamesClass.getInstance().showAToast(jsonObject.optString("responseMsg"), ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (response.equalsIgnoreCase("failed")) {

            TpsGamesClass.getInstance().showAToast("Server Error", ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
            return;
        }

        if (response.contains("login again")) {
            TpsGamesClass.getInstance().showAToast("Timer Out", ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
            Intent intent = new Intent(ActivityDgeGamesTps390.this, TpsSplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return;
        }
        if (response.contains("<HTML>")) {
            TpsGamesClass.getInstance().showAToast("Connect to internet", ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
            Intent intent = new Intent(ActivityDgeGamesTps390.this, TpsSplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return;
        }

        if (response.contains("<html>") || response.contains("<!DOCTYPE HTML PUBLIC")) {
            TpsGamesClass.getInstance().showAToast("Server not sending correct data", ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
            Intent intent = new Intent(ActivityDgeGamesTps390.this, TpsSplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return;
        }
        if (requestedMethod.equalsIgnoreCase("fail")) {

            TpsGamesClass.getInstance().showAToast("No data available!", ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
            return;
        } else if (requestedMethod.equals("TwleveByTwenty")) {


//            USERNAME = getIntent().getStringExtra("userName") != null ? getIntent().getStringExtra("userName") : "";
//            SESSIONID = getIntent().getStringExtra("sessionId") != null ? getIntent().getStringExtra("sessionId") : "";
//            LASTDRAWRESULT = getIntent().getStringExtra("lastDrawResult") != null ? getIntent().getStringExtra("lastDrawResult") : "";
//            PLAYERBALANCE = getIntent().getStringExtra("playerBalance") != null ? getIntent().getStringExtra("playerBalance") : ""

            if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("PT7003")) {
                intent = new Intent(ActivityDgeGamesTps390.this, PrintActivityAllGamesTousei.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            } else if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("TPS515")) {
                intent = new Intent(ActivityDgeGamesTps390.this, PrintActivityDgeExternalPrinter.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            } else if (TpsGamesClass.getInstance().getDeviceName().contains("rk30")) {
                TpsGamesClass.getInstance().setPrintResponseForAzt(response);
                intent = new Intent(ActivityDgeGamesTps390.this, PrintActivityAZT.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            } else {
                intent = new Intent(ActivityDgeGamesTps390.this, PrintActivityAllGames.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            }

        } else if (requestedMethod.equals("sports")) {
            SleGameBean sleGameBean = TpsGamesClass.getInstance().getGson().fromJson(response, SleGameBean.class);
            TpsGamesClass.getInstance().setSleGameBean(sleGameBean);
            int totalError = 0;
            if (TpsGamesClass.getInstance().getGameSle("soccer13") == null) {
                totalError++;
            } else if ((TpsGamesClass.getInstance().getGameSle("soccer13").getDrawData() == null)) {
                totalError++;


            } else if (TpsGamesClass.getInstance().getGameSle("soccer13").getDrawData().length == 0) {
                totalError++;
            }

            if (TpsGamesClass.getInstance().getGameSle("soccer6") == null) {
                totalError++;
            } else if ((TpsGamesClass.getInstance().getGameSle("soccer6").getDrawData() == null)) {
                totalError++;


            } else if (TpsGamesClass.getInstance().getGameSle("soccer6").getDrawData().length == 0) {
                totalError++;
            }

            if (totalError >= 2) {
                TpsGamesClass.getInstance().showAToast("No Data Available For Sports", ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
                return;
            }
            Log.e("", "");
            TpsGamesClass.getInstance().closeScreen(this);
            intent = new Intent(ActivityDgeGamesTps390.this, ActivitySleGamesTps390.class);
////            intent.putExtra("game", response);
////            intent.putExtra("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
////            intent.putExtra("playerBalance", TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance() + "");
            startActivity(intent);
        } else if (requestedMethod.equals("rgCall")) {

            TpsGamesClass.getInstance().stopCard();
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject != null && jsonObject.optInt("responseCode") == 100) {

                    TpsGamesClass.getInstance().setPlayerVerified(true, "");
                    try {

                    } catch (Exception e) {

                    }
                    if (selectedPosition == 2) {
                        purchaseRequestMiniRoulette();
                    } else {
                        purchageRequest();
                    }

                } else {
                    try {

//                        if (methodNameServer != null && methodNameServer.trim().length() > 0) {

//                            TpsGamesClass.getInstance().closeScreen(this);
//
//                            TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
//                            String url = "/sdcard/rainbow_header.png";
//                            TpsGamesClass.getInstance().pleaseWait(jsonObject.optString("responseMsg"), url);
//                            TpsGamesClass.getInstance().displayScreen(this);
                        TpsGamesClass.getInstance().showAToast(jsonObject.optString("responseMsg"), ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
//                        }
                    } catch (Exception e) {

                    }

                    TpsGamesClass.getInstance().setPlayerVerified(false, jsonObject.optString("responseMsg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (requestedMethod.equals("SaleSports")) {
            if (response.contains("ErrorCode")) {
                TpsGamesClass.getInstance().showAToast(response.substring(response.indexOf(":") + 1, response.indexOf("|")), ActivityDgeGamesTps390.this, Toast.LENGTH_SHORT);
                return;
            }
            Intent intent = null;
            if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("TPS515")) {
                intent = new Intent(ActivityDgeGamesTps390.this, PrintActivitySportsExternal.class);
            }  else {
                intent = new Intent(ActivityDgeGamesTps390.this, PrintActivitySportsGame.class);
            }
            intent.putExtra("response", response);
            startActivityForResult(intent, 59);
        }
        Log.i("", "");
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        switch (sideDrawerItemId)
        {
            case R.id.ola_btn:
                intent=new Intent(this,Ola.class);
                startActivity(intent);
                break;
            case R.id.ebet_btn:
                Intent intent=new Intent(ActivityDgeGamesTps390.this, ActivityEbetSlipTpsGame.class);
                intent.putExtra("selectedPosition",selectedPosition);
                startActivity(intent);
                break;

            case R.id.sport_btn:
                callSportData();
                break;
        }
        sideDrawerItemId=0;

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
