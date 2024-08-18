package demo.stpl.com.tpsmergedbuild.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import demo.stpl.com.tpsmergedbuild.HttpRequest;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.ClickForSportsData;
import demo.stpl.com.tpsmergedbuild.baseClass.ConstantField;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.Communcations;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;
import demo.stpl.com.tpsmergedbuild.beans.SleGameBean;
import demo.stpl.com.tpsmergedbuild.beans.SportsBean;
import demo.stpl.com.tpsmergedbuild.beans.SportsGameSelectionBean;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryData;
import demo.stpl.com.tpsmergedbuild.beans.SportsSaleBean;
import demo.stpl.com.tpsmergedbuild.betslip.ActivityBetSlipCamera;
import demo.stpl.com.tpsmergedbuild.dialog.DrawDialog;
import demo.stpl.com.tpsmergedbuild.dialog.WinnigDialog;
import demo.stpl.com.tpsmergedbuild.interfaces.OnSportsItemClick;
import demo.stpl.com.tpsmergedbuild.interfaces.ResponseLis;
import demo.stpl.com.tpsmergedbuild.utils.DrawDataCreator;

//import skilrock.com.tpsgame.R;
//import tpsgames.HttpRequest;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.ClickForSportsData;
//importdemo.stpl.com.tpsmergedbuild.baseClass.ConstantField;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.Communcations;
//import tpsgames.beans.GameBean;
//import tpsgames.beans.SleGameBean;
//import tpsgames.beans.SportsBean;
//import tpsgames.beans.SportsGameSelectionBean;
//import tpsgames.beans.SportsLotteryData;
//import tpsgames.beans.SportsSaleBean;
//import tpsgames.betslip.ActivityBetSlipCamera;
//import tpsgames.dialog.DrawDialog;
//import tpsgames.dialog.WinnigDialog;
//import tpsgames.interfaces.OnSportsItemClick;
//import tpsgames.interfaces.ResponseLis;
//import demo.stpl.com.tpsmergedbuild.utils.DrawDataCreator;

//import tpsgames.interfaces.ServerCommClass;

//import com.sparkzeal.sportslottery.beans.SportsBean;
//import com.sparkzeal.sportslottery.beans.SportsLotteryData;
//import com.sparkzeal.sportslottery.utils.DrawDataCreator;
//import com.tablet.stpl.comman.interfaces.ServerCommClass;

/**
 * Created by stpl on 20-Oct-16.
 */
public class ActivitySleGamesTps390 extends Activity implements View.OnClickListener, ResponseLis,DrawerLayout.DrawerListener {
    ImageButton threeLines;
    DrawerLayout sideDrawer;
    int sideDrawerItemId=0;
    LinearLayout olaButton;
    LinearLayout ebet_btn,draw_btn;

    LinearLayout below_sub_header, number_layout, middle_line, bet_slip, more_col_id,
            lucky_numbers, mini_keno, mini_roulette, super_keno, one_to_twelve, main_view,
            game1, game2, game3, game4, game5, game6, game7, game8, game9, game10, game11, game12, game13, buy_btn, common_winning_claim_btn, common_bet_slip_btn;

    ImageView list_icon, lucky_numbers_img, mini_keno_img;
    TextView buy_amt, game_text, mini_keno_text, cb_home_extra, cb_home, cb_draw, cb_away, cb_away_extra, no_of_lines, first_amount, second_amount, third_amount, four_amount,
            five_amount, six_amount, seven_amount, eight_amount, nine_amount, bet_other;

//    TextView txt_venue1, txt_venue2, txt_venue3, txt_venue4, txt_venue5, txt_venue6, txt_venue7, txt_venue8, txt_venue9, txt_venue10,
//            txt_venue11, txt_venue12, txt_venue13, txt_time1, txt_time2, txt_time3, txt_time4, txt_time5, txt_time6,
//            txt_time7, txt_time8, txt_time9, txt_time10, txt_time11, txt_time12, txt_time13, txt_home1, txt_home2, txt_home3,
//            txt_home4, txt_home5, txt_home6, txt_home7, txt_home8, txt_home9, txt_home10, txt_home11, txt_home12, txt_home13, txt_away1,
//            txt_away2, txt_away3, txt_away4, txt_away5, txt_away6, txt_away7, txt_away8, txt_away9, txt_away10, txt_away11, txt_away12, txt_away13;

    TextView txt_venue, txt_time, txt_home, txt_away, user_name, user_balance;

    ImageView  btn_refresh;

    Display getOrient;

    View first_view, second_view;

    private SportsLotteryData game;

    int lastGameSelectedIndex = 0, selectedPositionBeforeOther = -1;

    int totalView = 3, lastBetAmountPosition = 1;

    private JSONObject mainJsonObject;

    LinearLayout.LayoutParams param, param1;

    LayoutInflater inflater;

    ClickForSportsData clickForSportsData;

    View mainviewToadd;

    View lastBetAmountSelected;

    Intent intent;

    FrameLayout horizontalScroll;

    private ArrayList<SportsGameSelectionBean> game_one, game_two, game_three, game_four, game_five, game_six, game_seven, game_eight,
            game_nine, game_ten, game_eleven, game_twelve, game_thirteen;

    private SportsGameSelectionBean sportsGameSelectionBean;

    private double unitPrice;
    private String amount;

    private SleGameBean.SleData.GameData.GameTypeData currentGame;

    private String json = "{\"responseCode\":0,\"sleData\":{\"gameData\":[{\"maxEventCount\":1,\"gameDisplayName\":\"Soccer\",\"tktMaxAmt\":5000,\"gameId\":1,\"minEventCount\":1,\"gameDevname\":\"SOCCER\",\"tktThresholdAmt\":100,\"gameTypeData\":[{\"gameTypeDevName\":\"soccer13\",\"gameTypeId\":1,\"drawData\":[{\"drawId\":16387,\"drawDisplayString\":\"THURSDAY-S13\",\"drawDateTime\":\"13-10-2016 06:00:00\",\"drawNumber\":8775,\"eventData\":[{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Sevilla\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ESP\",\"eventDisplayHome\":\"Sevilla\",\"eventDate\":\"03-09-2016 13:59:15\",\"eventId\":3176,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Espanyol\",\"eventCodeHome\":\"SEV\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Valencia\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ATM\",\"eventDisplayHome\":\"Valencia\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3177,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Atletico Madrid\",\"eventCodeHome\":\"VAL\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Real Sociedad\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"BIL\",\"eventDisplayHome\":\"Real Sociedad\",\"eventDate\":\"12-10-2016 13:59:30\",\"eventId\":3170,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Athletic Bilbao\",\"eventCodeHome\":\"RSOC\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Real Madrid\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"BAR\",\"eventDisplayHome\":\"Real Madrid\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3178,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Barcelona\",\"eventCodeHome\":\"RMA\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Sevilla\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"VAL\",\"eventDisplayHome\":\"Sevilla\",\"eventDate\":\"12-10-2016 13:59:30\",\"eventId\":3171,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Valencia\",\"eventCodeHome\":\"SEV\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Barcelona\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RMA\",\"eventDisplayHome\":\"Barcelona\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3179,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Madrid\",\"eventCodeHome\":\"BAR\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Real Sociedad\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"SGJN\",\"eventDisplayHome\":\"Real Sociedad\",\"eventDate\":\"12-10-2016 13:59:30\",\"eventId\":3172,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Sporting Gijon\",\"eventCodeHome\":\"RSOC\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Barcelona\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ATM\",\"eventDisplayHome\":\"Barcelona\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3180,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Atletico Madrid\",\"eventCodeHome\":\"BAR\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Sporting Gijon\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"DEPO\",\"eventDisplayHome\":\"Sporting Gijon\",\"eventDate\":\"03-09-2016 13:59:15\",\"eventId\":3173,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Deportivo La Coruna\",\"eventCodeHome\":\"SGJN\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Atletico Madrid\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RMA\",\"eventDisplayHome\":\"Atletico Madrid\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3181,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Madrid\",\"eventCodeHome\":\"ATM\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Sporting Gijon\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"CEV\",\"eventDisplayHome\":\"Sporting Gijon\",\"eventDate\":\"03-09-2016 13:59:15\",\"eventId\":3174,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Celta Vigo\",\"eventCodeHome\":\"SGJN\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Espanyol\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"VAL\",\"eventDisplayHome\":\"Espanyol\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3182,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Valencia\",\"eventCodeHome\":\"ESP\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Real Sociedad\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"BAR\",\"eventDisplayHome\":\"Real Sociedad\",\"eventDate\":\"03-09-2016 13:59:15\",\"eventId\":3175,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Barcelona\",\"eventCodeHome\":\"RSOC\"}],\"ftg\":\"12-10-2016 12:59:30\"},{\"drawId\":16390,\"drawDisplayString\":\"SUNDAY-S13\",\"drawDateTime\":\"16-10-2016 06:00:00\",\"drawNumber\":8778,\"eventData\":[{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Sevilla\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"MAL\",\"eventDisplayHome\":\"Sevilla\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3183,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Malaga\",\"eventCodeHome\":\"SEV\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Athletic Bilbao\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ATM\",\"eventDisplayHome\":\"Athletic Bilbao\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3191,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Atletico Madrid\",\"eventCodeHome\":\"BIL\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Barcelona\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"DEPO\",\"eventDisplayHome\":\"Barcelona\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3184,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Deportivo La Coruna\",\"eventCodeHome\":\"BAR\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Atletico Madrid\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"SGJN\",\"eventDisplayHome\":\"Atletico Madrid\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3193,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Sporting Gijon\",\"eventCodeHome\":\"ATM\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Getafe\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"DEPO\",\"eventDisplayHome\":\"Getafe\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3185,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Deportivo La Coruna\",\"eventCodeHome\":\"GTF\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Barcelona\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"CEV\",\"eventDisplayHome\":\"Barcelona\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3194,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Celta Vigo\",\"eventCodeHome\":\"BAR\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Barcelona\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RSOC\",\"eventDisplayHome\":\"Barcelona\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3186,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Sociedad\",\"eventCodeHome\":\"BAR\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Atletico Madrid\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"SEV\",\"eventDisplayHome\":\"Atletico Madrid\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3195,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Sevilla\",\"eventCodeHome\":\"ATM\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Athletic Bilbao\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"GRA\",\"eventDisplayHome\":\"Athletic Bilbao\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3187,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Granada\",\"eventCodeHome\":\"BIL\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Rayo Vallecano\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"BIL\",\"eventDisplayHome\":\"Rayo Vallecano\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3196,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Athletic Bilbao\",\"eventCodeHome\":\"RAV\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Eibar\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RMA\",\"eventDisplayHome\":\"Eibar\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3188,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Madrid\",\"eventCodeHome\":\"EIB\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Celta Vigo\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RB\",\"eventDisplayHome\":\"Celta Vigo\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3197,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Betis\",\"eventCodeHome\":\"CEV\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Real Sociedad\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RMA\",\"eventDisplayHome\":\"Real Sociedad\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3189,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Madrid\",\"eventCodeHome\":\"RSOC\"}],\"ftg\":\"15-10-2016 12:59:30\"}],\"eventType\":\"[H, D, A]\",\"gameTypeDisplayName\":\"Soccer 13\",\"areEventsMappedForUpcomingDraw\":false,\"upcomingDrawStartTime\":\"18-10-2016 13:30:00\",\"eventSelectionType\":\"MULTIPLE\",\"gameTypeMaxBetAmtMultiple\":600,\"gameTypeUnitPrice\":1},{\"gameTypeDevName\":\"soccer10\",\"gameTypeId\":2,\"drawData\":[{\"drawId\":16391,\"drawDisplayString\":\"SUNDAY-S10\",\"drawDateTime\":\"23-09-2016 06:00:00\",\"drawNumber\":8779,\"eventData\":[{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Barcelona\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ATM\",\"eventDisplayHome\":\"Barcelona\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3180,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Atletico Madrid\",\"eventCodeHome\":\"BAR\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Sporting Gijon\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"DEPO\",\"eventDisplayHome\":\"Sporting Gijon\",\"eventDate\":\"03-09-2016 13:59:15\",\"eventId\":3173,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Deportivo La Coruna\",\"eventCodeHome\":\"SGJN\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Atletico Madrid\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RMA\",\"eventDisplayHome\":\"Atletico Madrid\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3181,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Madrid\",\"eventCodeHome\":\"ATM\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Sporting Gijon\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"CEV\",\"eventDisplayHome\":\"Sporting Gijon\",\"eventDate\":\"03-09-2016 13:59:15\",\"eventId\":3174,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Celta Vigo\",\"eventCodeHome\":\"SGJN\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Espanyol\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"VAL\",\"eventDisplayHome\":\"Espanyol\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3182,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Valencia\",\"eventCodeHome\":\"ESP\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Real Sociedad\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"BAR\",\"eventDisplayHome\":\"Real Sociedad\",\"eventDate\":\"03-09-2016 13:59:15\",\"eventId\":3175,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Barcelona\",\"eventCodeHome\":\"RSOC\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Sevilla\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ESP\",\"eventDisplayHome\":\"Sevilla\",\"eventDate\":\"03-09-2016 13:59:15\",\"eventId\":3176,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Espanyol\",\"eventCodeHome\":\"SEV\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Valencia\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ATM\",\"eventDisplayHome\":\"Valencia\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3177,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Atletico Madrid\",\"eventCodeHome\":\"VAL\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Real Madrid\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"BAR\",\"eventDisplayHome\":\"Real Madrid\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3178,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Barcelona\",\"eventCodeHome\":\"RMA\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Barcelona\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RMA\",\"eventDisplayHome\":\"Barcelona\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3179,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Madrid\",\"eventCodeHome\":\"BAR\"}],\"ftg\":\"22-09-2016 12:59:15\"}],\"eventType\":\"[H, D, A]\",\"gameTypeDisplayName\":\"Soccer 10\",\"areEventsMappedForUpcomingDraw\":false,\"upcomingDrawStartTime\":\"20-09-2016 13:30:00\",\"eventSelectionType\":\"MULTIPLE\",\"gameTypeMaxBetAmtMultiple\":600,\"gameTypeUnitPrice\":1},{\"gameTypeDevName\":\"soccer6\",\"gameTypeId\":3,\"drawData\":[{\"drawId\":16451,\"drawDisplayString\":\"SATURDAY-S6\",\"drawDateTime\":\"23-09-2016 06:00:00\",\"drawNumber\":8839,\"eventData\":[{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Valencia\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ATM\",\"eventDisplayHome\":\"Valencia\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3177,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Atletico Madrid\",\"eventCodeHome\":\"VAL\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Real Madrid\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"BAR\",\"eventDisplayHome\":\"Real Madrid\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3178,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Barcelona\",\"eventCodeHome\":\"RMA\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Barcelona\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RMA\",\"eventDisplayHome\":\"Barcelona\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3179,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Madrid\",\"eventCodeHome\":\"BAR\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Barcelona\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ATM\",\"eventDisplayHome\":\"Barcelona\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3180,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Atletico Madrid\",\"eventCodeHome\":\"BAR\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Atletico Madrid\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RMA\",\"eventDisplayHome\":\"Atletico Madrid\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3181,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Madrid\",\"eventCodeHome\":\"ATM\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Espanyol\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"VAL\",\"eventDisplayHome\":\"Espanyol\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3182,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Valencia\",\"eventCodeHome\":\"ESP\"}],\"ftg\":\"22-09-2016 12:59:45\"}],\"eventType\":\"[H+, H, D, A, A+]\",\"gameTypeDisplayName\":\"Soccer 6\",\"areEventsMappedForUpcomingDraw\":false,\"upcomingDrawStartTime\":\"21-09-2016 13:30:00\",\"eventSelectionType\":\"MULTIPLE\",\"gameTypeMaxBetAmtMultiple\":600,\"gameTypeUnitPrice\":0.5},{\"gameTypeDevName\":\"soccer4\",\"gameTypeId\":4,\"drawData\":[{\"drawId\":16463,\"drawDisplayString\":\"SATURDAY-S4\",\"drawDateTime\":\"23-09-2016 06:00:00\",\"drawNumber\":8851,\"eventData\":[{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Valencia\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ATM\",\"eventDisplayHome\":\"Valencia\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3177,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Atletico Madrid\",\"eventCodeHome\":\"VAL\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Real Madrid\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"BAR\",\"eventDisplayHome\":\"Real Madrid\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3178,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Barcelona\",\"eventCodeHome\":\"RMA\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Atletico Madrid\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"RMA\",\"eventDisplayHome\":\"Atletico Madrid\",\"eventDate\":\"02-09-2016 14:00:00\",\"eventId\":3181,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Real Madrid\",\"eventCodeHome\":\"ATM\"},{\"eventLeague\":\"La Liga\",\"favTeam\":\"\",\"eventVenue\":\"Espanyol\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"VAL\",\"eventDisplayHome\":\"Espanyol\",\"eventDate\":\"15-10-2016 13:59:30\",\"eventId\":3182,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Valencia\",\"eventCodeHome\":\"ESP\"}],\"ftg\":\"22-09-2016 13:00:00\"},{\"drawId\":16480,\"drawDisplayString\":\"SATURDAY-S4\",\"drawDateTime\":\"24-09-2016 06:00:00\",\"drawNumber\":8868,\"eventData\":[{\"eventLeague\":\"Argentina\",\"favTeam\":\"\",\"eventVenue\":\"Aduana Stars\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"ARSR\",\"eventDisplayHome\":\"Aldosivi\",\"eventDate\":\"11-10-2016 13:59:30\",\"eventId\":1,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Arsenal Sarandi\",\"eventCodeHome\":\"ALD\"},{\"eventLeague\":\"Argentina\",\"favTeam\":\"\",\"eventVenue\":\"Ado den haag\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"INDP\",\"eventDisplayHome\":\"Defensa y Justicia\",\"eventDate\":\"11-10-2016 13:59:30\",\"eventId\":2,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Independiente\",\"eventCodeHome\":\"DYJ\"},{\"eventLeague\":\"Argentina\",\"favTeam\":\"\",\"eventVenue\":\"Akhisar Belediye\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"LNS\",\"eventDisplayHome\":\"Gimnasia L.P.\",\"eventDate\":\"11-10-2016 13:59:30\",\"eventId\":3,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Lanus\",\"eventCodeHome\":\"GMLP\"},{\"eventLeague\":\"Argentina\",\"favTeam\":\"\",\"eventVenue\":\"Al Ahly Shendi\",\"awayTeamOdds\":\"\",\"eventCodeAway\":\"GDCR\",\"eventDisplayHome\":\"Newells Old Boys\",\"eventDate\":\"11-10-2016 13:59:30\",\"eventId\":4,\"homeTeamOdds\":\"\",\"drawOdds\":\"\",\"eventDisplayAway\":\"Godoy Cruz\",\"eventCodeHome\":\"NOB\"}],\"ftg\":\"23-09-2016 13:00:00\"}],\"eventType\":\"[H+, H, D, A, A+]\",\"gameTypeDisplayName\":\"Soccer 4\",\"areEventsMappedForUpcomingDraw\":false,\"upcomingDrawStartTime\":\"22-09-2016 13:30:00\",\"eventSelectionType\":\"MULTIPLE\",\"gameTypeMaxBetAmtMultiple\":600,\"gameTypeUnitPrice\":0.5}]}]},\"responseMsg\":\"SUCCESS\"}";

    LinearLayout sport_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getOrient = getWindowManager().getDefaultDisplay();
        overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
        if (TpsGamesClass.getInstance().getDeviceName().contains("550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx053")) {
            setContentView(R.layout.layout_dge_tps_land);
        } else {
            setContentView(R.layout.layout_dge_tps390);
        }
        sport_btn = (LinearLayout) findViewById(R.id.sport_btn);
        currentGame = TpsGamesClass.getInstance().getGameSle("soccer13");
        initialize();
        sideDrawer.addDrawerListener(this);
        sideDrawer.closeDrawers();
        threeLines.setOnClickListener(this);
        View moreView=findViewById(R.id.more_col_id);
        moreView.setVisibility(View.GONE);
        TpsGamesClass.getInstance().setActivitySleGamesTps390(this);

//        param = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT, 8.0f);
//
//        param1 = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);

//        if (i != selectedPosition) {
//            views.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.top_game_selector));
//        } else {
//            views.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
//        }

        SportsBean bean = TpsGamesClass.getInstance().getGson().fromJson(json, SportsBean.class);
        game = DrawDataCreator.getSportsLottery(bean);
        lucky_numbers_img.setImageDrawable(getResources().getDrawable(R.drawable.soccer13));
        game_text.setText("Soccer 13");

        mini_keno_img.setImageDrawable(getResources().getDrawable(R.drawable.soccer6));
        mini_keno_text.setText("Soccer 6");


        lucky_numbers.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
        mini_keno.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_game_selector));
        param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 8.0f);

        param1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 3.0f);
        inflater = LayoutInflater.from(this);


        lucky_numbers.setOnClickListener(this);
        mini_keno.setOnClickListener(this);

        if (TpsGamesClass.getInstance().getDeviceName().contains("550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx053")) {
            mainviewToadd = inflater.inflate(R.layout.layout_sports_land, null);
        } else {
            mainviewToadd = inflater.inflate(R.layout.layout_sports_tps390, null);
        }
        mainviewToadd.setLayoutParams(param);
        main_view.removeAllViews();
        main_view.addView(mainviewToadd);


        clickForSportsData = new ClickForSportsData(this, onSportsItemClick);
        if (TpsGamesClass.getInstance().getGameSle("soccer13") == null || (TpsGamesClass.getInstance().getGameSle("soccer13").getDrawData() == null || (TpsGamesClass.getInstance().getGameSle("soccer13").getDrawData() != null && TpsGamesClass.getInstance().getGameSle("soccer13").getDrawData().length == 0))) {
            lucky_numbers.setVisibility(View.GONE);
            lastGameSelectedIndex = 1;
            setGame();
        }
        if (TpsGamesClass.getInstance().getGameSle("soccer6") == null || (TpsGamesClass.getInstance().getGameSle("soccer6").getDrawData() == null || (TpsGamesClass.getInstance().getGameSle("soccer6").getDrawData() != null && TpsGamesClass.getInstance().getGameSle("soccer6").getDrawData().length == 0))) {
            mini_keno.setVisibility(View.GONE);
        }

        initializeGameLogic();

        setBetAmount();
        no_of_lines.setText("0");

        user_name.setText(TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
        String balance = TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance() + "";

        if (balance.contains(".")) {
            String[] decimalAmount = balance.split("[.]");
            balance = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        user_balance.setText("$ " + balance);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView imageView= (ImageView) sport_btn.findViewById(R.id.sport_img);
        imageView.setImageResource(R.mipmap.sportsselect);
        TpsGamesClass.getInstance().setCurrentActivity("sle390");
        String balance = TpsGamesClass.getInstance().getLoginResponse().getData().getAvailableBalance() + "";

        if (balance.contains(".")) {
            String[] decimalAmount = balance.split("[.]");
            balance = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }
        user_balance.setText("$ "+balance);

    }

    protected void purchaseRequest() {

        int totalView = 6;
        if (currentGame.getGameTypeDisplayName().contains("13")) {
            totalView = 13;
        }
        String requestString = "";
        String selectionString = "";
        requestString = requestString + "userName=" + TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName();
        requestString = requestString + "&gameId=1";
        requestString = requestString + "&gameTypeId=" + currentGame.getGameTypeId();

        for (int i = 0; i < totalView; i++) {

            SportsGameSelectionBean bean = null;
            String selection = "";
            if (i == 0) {
                bean = game_one.get(0);
            } else if (i == 1) {
                bean = game_two.get(0);

            } else if (i == 2) {
                bean = game_three.get(0);
            } else if (i == 3) {
                bean = game_four.get(0);
            } else if (i == 4) {
                bean = game_five.get(0);
            } else if (i == 5) {
                bean = game_six.get(0);
            } else if (i == 6) {
                bean = game_seven.get(0);
            } else if (i == 7) {
                bean = game_eight.get(0);
            } else if (i == 8) {
                bean = game_nine.get(0);
            } else if (i == 9) {
                bean = game_ten.get(0);
            } else if (i == 10) {
                bean = game_eleven.get(0);
            } else if (i == 11) {
                bean = game_twelve.get(0);
            } else if (i == 12) {
                bean = game_thirteen.get(0);
            }

            if (bean != null && bean.getTotalSize() > 0 && bean.getHomePlus().trim().length() > 0) {
                selection = selection + bean.getHomePlus() + "%2B" + ",";

            }
            if (bean != null && bean.getTotalSize() > 0 && bean.getHome().trim().length() > 0) {
                selection = selection + bean.getHome() + ",";
            }
            if (bean != null && bean.getTotalSize() > 0 && bean.getDraw().trim().length() > 0) {
                selection = selection + bean.getDraw() + ",";
            }
            if (bean != null && bean.getTotalSize() > 0 && bean.getAway().trim().length() > 0) {
                selection = selection + bean.getAway() + ",";
            }
            if (bean != null && bean.getTotalSize() > 0 && bean.getAwayPlus().trim().length() > 0) {
                selection = selection + bean.getAwayPlus() + "%2B";
            }
            if (selection.lastIndexOf(",") != -1) {
                selection = selection.substring(0, selection.lastIndexOf(","));
            }

            if (i == 0) {
                selectionString = currentGame.getDrawData()[0].getEventData()[i].getEventId() + "@" + selection;
            } else {
                selectionString = selectionString + "$" + currentGame.getDrawData()[0].getEventData()[i].getEventId() + "@" + selection;
            }


        }

        requestString = requestString + "&drawInfo=" + currentGame.getDrawData()[0].getDrawId() + "~" + lastBetAmountPosition + "~" + selectionString;
        requestString = requestString + "&drawCount=1&ticketAmt=" + amount;
        requestString = requestString + "&merCode=RMS&slLstTxnId=" + "0" + "&LSTktNo=" + "0" + "&LRespTime=0&CID=52261&LAC=106&reqCounter=7&respCounter=7";
        requestString = requestString + "&sessId=" + TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId();
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
        String time = df1.format(Calendar.getInstance().getTime());
        requestString = requestString + "&time=" + time + "&date=" + date + "&random=" + TpsGamesClass.getInstance().getRandom();

        String path = "/com/skilrock/sle/embedded/playMgmt/Action/sportsLotteryPurchaseTicket.action?";
        String uri = Utility.baseUrl + Utility.portNumber + "/SportsLottery/" + path + requestString;
        uri = Uri.parse(uri).toString();

        HttpRequest httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(uri, this, "fetching game data", this, "sports");
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("merchantCode", "PMS");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        httpRequest.setIsParams(true, mainJsonObject.toString());
        TpsGamesClass.getInstance().startLoader(this);
        httpRequest.executeTask();
    }


    protected void purchaseRequestSoccer13() {
        mainJsonObject = new JSONObject();
        try {
            mainJsonObject.put("merchantCode", "RMS");
            mainJsonObject.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            mainJsonObject.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());
            mainJsonObject.put("noOfBoard", "1");
            mainJsonObject.put("gameId", "1");
            mainJsonObject.put("gameTypeId", currentGame.getGameTypeId());
            mainJsonObject.put("totalPurchaseAmt", amount);

//            JSONArray jsonArray = new JSONArray();
            int totalView = 0;
            if (currentGame.getGameTypeDisplayName().contains("13")) {
                totalView = 13;
            }

            JSONArray drawInfo = new JSONArray();
            JSONObject info = new JSONObject();
            info.put("drawId", currentGame.getDrawData()[0].getDrawId());
            info.put("betAmtMul", lastBetAmountPosition + "");


            JSONArray eventData = new JSONArray();
            for (int i = 0; i < totalView; i++) {

                SportsGameSelectionBean bean = null;
                String selection = "";
                JSONObject evenObject = new JSONObject();
                if (i == 0) {
                    bean = game_one.get(0);
                } else if (i == 1) {
                    bean = game_two.get(0);

                } else if (i == 2) {
                    bean = game_three.get(0);
                } else if (i == 3) {
                    bean = game_four.get(0);
                } else if (i == 4) {
                    bean = game_five.get(0);
                } else if (i == 5) {
                    bean = game_six.get(0);
                } else if (i == 6) {
                    bean = game_seven.get(0);
                } else if (i == 7) {
                    bean = game_eight.get(0);
                } else if (i == 8) {
                    bean = game_nine.get(0);
                } else if (i == 9) {
                    bean = game_ten.get(0);
                } else if (i == 10) {
                    bean = game_eleven.get(0);
                } else if (i == 11) {
                    bean = game_twelve.get(0);
                } else if (i == 12) {
                    bean = game_thirteen.get(0);
                }
                if (bean != null && bean.getTotalSize() > 0 && bean.getHomePlus().trim().length() > 0) {
                    selection = selection + bean.getHomePlus() + "%2B" + ",";

                }
                if (bean != null && bean.getTotalSize() > 0 && bean.getHome().trim().length() > 0) {
                    selection = selection + bean.getHome() + ",";
                }
                if (bean != null && bean.getTotalSize() > 0 && bean.getDraw().trim().length() > 0) {
                    selection = selection + bean.getDraw() + ",";
                }
                if (bean != null && bean.getTotalSize() > 0 && bean.getAway().trim().length() > 0) {
                    selection = selection + bean.getAway() + ",";
                }
                if (bean != null && bean.getTotalSize() > 0 && bean.getAwayPlus().trim().length() > 0) {
                    selection = selection + bean.getAwayPlus() + "%2B";
                }
                if (selection.lastIndexOf(",") != -1) {
                    selection = selection.substring(0, selection.lastIndexOf(","));
                }
                evenObject.put("eventId", currentGame.getDrawData()[0].getEventData()[i].getEventId());
                evenObject.put("eventSelected", selection);
                eventData.put(evenObject);
            }
            info.put("eventData", eventData);
            drawInfo.put(info);
            mainJsonObject.put("drawInfo", drawInfo);
            Log.i("json == ", mainJsonObject.toString());
//                if(i == 0){
//                    game_one
//                }

            String url = Utility.baseUrl + Utility.portNumber + Utility.sportsPurchRequest;
            HttpRequest httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(url, this, "fetching game data", this, "sports", Utility.eBetSlipHeader);
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("merchantCode", "PMS");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            httpRequest.setIsParams(true, mainJsonObject.toString());
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void setBetAmount(double amount) {
        boolean isDecimalContail = false;
        if (!no_of_lines.getText().equals("0")) {
            this.amount = new BigDecimal((amount * Double.parseDouble(no_of_lines.getText().toString()))).toPlainString() + "";
            if (this.amount.contains(".")) {
                isDecimalContail = true;
                String[] decimalAmount = this.amount.split("[.]");
                this.amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
            }
            buy_amt.setText("$ " + this.amount + (!isDecimalContail ? ".0" : ""));
        } else {
            this.amount = new BigDecimal((amount * Double.parseDouble(no_of_lines.getText().toString()))).toPlainString() + "";
            if (this.amount.contains(".")) {
                isDecimalContail = true;
                String[] decimalAmount = this.amount.split("[.]");
                this.amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
            }
            buy_amt.setText("$ " + this.amount + (!isDecimalContail ? ".0" : ""));
        }
    }

    protected void setBetAmount() {
        if (currentGame != null) {
            unitPrice = Double.parseDouble(currentGame.getGameTypeUnitPrice());

            for (int i = 0; i < 10; i++) {
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
                }
            }
        }

    }


    protected void initializeGameLogic() {
        game1 = (LinearLayout) mainviewToadd.findViewById(R.id.game1);
        findViewByViewId(game1, 1, 1);
        findViewByViewId(game1, 1, 2);
        findViewByViewId(game1, 1, 3);
        findViewByViewId(game1, 1, 4);
        findViewByViewId(game1, 1, 5);

        game2 = (LinearLayout) mainviewToadd.findViewById(R.id.game2);
        findViewByViewId(game2, 2, 1);
        findViewByViewId(game2, 2, 2);
        findViewByViewId(game2, 2, 3);
        findViewByViewId(game2, 2, 4);
        findViewByViewId(game2, 2, 5);

        game3 = (LinearLayout) mainviewToadd.findViewById(R.id.game3);
        findViewByViewId(game3, 3, 1);
        findViewByViewId(game3, 3, 2);
        findViewByViewId(game3, 3, 3);
        findViewByViewId(game3, 3, 4);
        findViewByViewId(game3, 3, 5);

        game4 = (LinearLayout) mainviewToadd.findViewById(R.id.game4);
        findViewByViewId(game4, 4, 1);
        findViewByViewId(game4, 4, 2);
        findViewByViewId(game4, 4, 3);
        findViewByViewId(game4, 4, 4);
        findViewByViewId(game4, 4, 5);

        game5 = (LinearLayout) mainviewToadd.findViewById(R.id.game5);
        findViewByViewId(game5, 5, 1);
        findViewByViewId(game5, 5, 2);
        findViewByViewId(game5, 5, 3);
        findViewByViewId(game5, 5, 4);
        findViewByViewId(game5, 5, 5);

        game6 = (LinearLayout) mainviewToadd.findViewById(R.id.game6);
        findViewByViewId(game6, 6, 1);
        findViewByViewId(game6, 6, 2);
        findViewByViewId(game6, 6, 3);
        findViewByViewId(game6, 6, 4);
        findViewByViewId(game6, 6, 5);

        game_one = new ArrayList<>();
        game_one.add(new SportsGameSelectionBean());
        game_two = new ArrayList<>();
        game_two.add(new SportsGameSelectionBean());
        game_three = new ArrayList<>();
        game_three.add(new SportsGameSelectionBean());
        game_four = new ArrayList<>();
        game_four.add(new SportsGameSelectionBean());
        game_five = new ArrayList<>();
        game_five.add(new SportsGameSelectionBean());
        game_six = new ArrayList<>();
        game_six.add(new SportsGameSelectionBean());

        if (totalView == 3) {
            game_seven = new ArrayList<>();
            game_seven.add(new SportsGameSelectionBean());
            game_eight = new ArrayList<>();
            game_eight.add(new SportsGameSelectionBean());
            game_nine = new ArrayList<>();
            game_nine.add(new SportsGameSelectionBean());
            game_ten = new ArrayList<>();
            game_ten.add(new SportsGameSelectionBean());
            game_eleven = new ArrayList<>();
            game_eleven.add(new SportsGameSelectionBean());
            game_twelve = new ArrayList<>();
            game_twelve.add(new SportsGameSelectionBean());
            game_thirteen = new ArrayList<>();
            game_thirteen.add(new SportsGameSelectionBean());

            game7 = (LinearLayout) mainviewToadd.findViewById(R.id.game7);
            findViewByViewId(game7, 7, 1);
            findViewByViewId(game7, 7, 2);
            findViewByViewId(game7, 7, 3);
            findViewByViewId(game7, 7, 4);
            findViewByViewId(game7, 7, 5);

            game8 = (LinearLayout) mainviewToadd.findViewById(R.id.game8);
            findViewByViewId(game8, 8, 1);
            findViewByViewId(game8, 8, 2);
            findViewByViewId(game8, 8, 3);
            findViewByViewId(game8, 8, 4);
            findViewByViewId(game8, 8, 5);

            game9 = (LinearLayout) mainviewToadd.findViewById(R.id.game9);
            findViewByViewId(game9, 9, 1);
            findViewByViewId(game9, 9, 2);
            findViewByViewId(game9, 9, 3);
            findViewByViewId(game9, 9, 4);
            findViewByViewId(game9, 9, 5);

            game10 = (LinearLayout) mainviewToadd.findViewById(R.id.game10);
            findViewByViewId(game10, 10, 1);
            findViewByViewId(game10, 10, 2);
            findViewByViewId(game10, 10, 3);
            findViewByViewId(game10, 10, 4);
            findViewByViewId(game10, 10, 5);

            game11 = (LinearLayout) mainviewToadd.findViewById(R.id.game11);
            findViewByViewId(game11, 11, 1);
            findViewByViewId(game11, 11, 2);
            findViewByViewId(game11, 11, 3);
            findViewByViewId(game11, 11, 4);
            findViewByViewId(game11, 11, 5);

            game12 = (LinearLayout) mainviewToadd.findViewById(R.id.game12);
            findViewByViewId(game12, 12, 1);
            findViewByViewId(game12, 12, 2);
            findViewByViewId(game12, 12, 3);
            findViewByViewId(game12, 12, 4);
            findViewByViewId(game12, 12, 5);

            game13 = (LinearLayout) mainviewToadd.findViewById(R.id.game13);
            findViewByViewId(game13, 13, 1);
            findViewByViewId(game13, 13, 2);
            findViewByViewId(game13, 13, 3);
            findViewByViewId(game13, 13, 4);
            findViewByViewId(game13, 13, 5);
        }
//        else {
//            game7 = (LinearLayout) mainviewToadd.findViewById(R.id.game7);
//            game8 = (LinearLayout) mainviewToadd.findViewById(R.id.game8);
//            game9 = (LinearLayout) mainviewToadd.findViewById(R.id.game9);
//            game10 = (LinearLayout) mainviewToadd.findViewById(R.id.game10);
//            game11 = (LinearLayout) mainviewToadd.findViewById(R.id.game11);
//            game12 = (LinearLayout) mainviewToadd.findViewById(R.id.game12);
//            game13 = (LinearLayout) mainviewToadd.findViewById(R.id.game13);
//            game7.setVisibility(View.GONE);
//            game8.setVisibility(View.GONE);
//            game9.setVisibility(View.GONE);
//            game10.setVisibility(View.GONE);
//            game11.setVisibility(View.GONE);
//            game12.setVisibility(View.GONE);
//            game13.setVisibility(View.GONE);
//        }

    }

    protected void initialize() {
        below_sub_header = (LinearLayout) findViewById(R.id.below_sub_header);
        number_layout = (LinearLayout) findViewById(R.id.number_layout);
        middle_line = (LinearLayout) findViewById(R.id.middle_line);
        list_icon = (ImageView) findViewById(R.id.list_icon);
        lucky_numbers = (LinearLayout) findViewById(R.id.lucky_numbers);
        mini_keno = (LinearLayout) findViewById(R.id.mini_keno);
        mini_roulette = (LinearLayout) findViewById(R.id.mini_roulette);
        super_keno = (LinearLayout) findViewById(R.id.super_keno);
        one_to_twelve = (LinearLayout) findViewById(R.id.one_to_twelve);
        lucky_numbers_img = (ImageView) findViewById(R.id.lucky_numbers_img);
        game_text = (TextView) findViewById(R.id.game_text);
        mini_keno_img = (ImageView) findViewById(R.id.mini_keno_img);
        mini_keno_text = (TextView) findViewById(R.id.mini_keno_text);
        main_view = (LinearLayout) findViewById(R.id.main_view);
        no_of_lines = (TextView) findViewById(R.id.no_of_lines);

        first_amount = (TextView) findViewById(R.id.first_amount);
        second_amount = (TextView) findViewById(R.id.second_amount);
        third_amount = (TextView) findViewById(R.id.third_amount);
        four_amount = (TextView) findViewById(R.id.four_amount);
        five_amount = (TextView) findViewById(R.id.five_amount);
        six_amount = (TextView) findViewById(R.id.six_amount);
        seven_amount = (TextView) findViewById(R.id.seven_amount);
        eight_amount = (TextView) findViewById(R.id.eight_amount);
        nine_amount = (TextView) findViewById(R.id.nine_amount);
        bet_other = (TextView) findViewById(R.id.bet_other);

        user_name = (TextView) findViewById(R.id.user_name);

        user_balance = (TextView) findViewById(R.id.user_balance);

        buy_amt = (TextView) findViewById(R.id.buy_amt);

        bet_slip = (LinearLayout) findViewById(R.id.bet_slip);

        more_col_id = (LinearLayout) findViewById(R.id.more_col_id);

        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().contains("515") || TpsGamesClass.getInstance().getDeviceName().contains("rk30") || TpsGamesClass.getInstance().getDeviceName().contains("580")) {
            bet_slip.setVisibility(View.GONE);
        } else if (TpsGamesClass.getInstance().getDeviceName().contains("hdx")) {
            more_col_id.setVisibility(View.GONE);
        }

        buy_amt.setText("$ 0.0");
        threeLines= (ImageButton) findViewById(R.id.threeLines);
        olaButton=(LinearLayout) findViewById(R.id.ola_btn);
        olaButton.setOnClickListener(this);
        sideDrawer= (DrawerLayout) findViewById(R.id.drawer_layout_new);
        sport_btn = (LinearLayout) findViewById(R.id.sport_btn);

        buy_btn = (LinearLayout) findViewById(R.id.buy_btn);

        common_bet_slip_btn = (LinearLayout) findViewById(R.id.common_bet_slip_btn);

        common_winning_claim_btn = (LinearLayout) findViewById(R.id.common_winning_claim_btn);

        ebet_btn = (LinearLayout) findViewById(R.id.ebet_btn);

        btn_refresh = (ImageView) findViewById(R.id.btn_refresh);

        draw_btn = (LinearLayout) findViewById(R.id.draw_btn);

        olaButton = (LinearLayout) findViewById(R.id.ola_btn);

        draw_btn.setOnClickListener(this);

        olaButton.setOnClickListener(this);

        common_winning_claim_btn.setOnClickListener(this);

        btn_refresh.setOnClickListener(this);

        ebet_btn.setOnClickListener(this);

        common_bet_slip_btn.setOnClickListener(this);

        buy_btn.setOnClickListener(this);
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


        horizontalScroll = (FrameLayout) findViewById(R.id.horizontalScroll);


        list_icon.setVisibility(View.GONE);
        number_layout.setVisibility(View.GONE);
        middle_line.setVisibility(View.GONE);
        below_sub_header.setVisibility(View.GONE);

        mini_roulette.setVisibility(View.GONE);
        super_keno.setVisibility(View.GONE);
        one_to_twelve.setVisibility(View.GONE);
    }

    protected String converTime(String time) {
        String[] dateTime = time.split(" ");
        if (dateTime != null && dateTime.length > 1) {
            time = dateTime[1].substring(0, dateTime[1].lastIndexOf(":"));
        }
        return time;
    }


    protected void findViewByViewId(View view, int viewPosition, int positionInView) {
        txt_venue = (TextView) view.findViewById(R.id.txt_venue);
        txt_time = (TextView) view.findViewById(R.id.txt_time);
        txt_home = (TextView) view.findViewById(R.id.txt_home);
        txt_away = (TextView) view.findViewById(R.id.txt_away);
        SleGameBean.SleData.GameData.GameTypeData.DrawData.EventData eventData = currentGame.getDrawData()[0].getEventData()[viewPosition - 1];
        txt_venue.setText(eventData.getEventVenue());
        txt_time.setText(converTime(eventData.getEventDate()));
        txt_home.setText(eventData.getEventDisplayHome());
        txt_away.setText(eventData.getEventDisplayAway());
        if (positionInView == 1) {
            cb_home_extra = (TextView) view.findViewById(R.id.cb_home_extra);
            first_view = view.findViewById(R.id.first_view);
            if (totalView == 3) {
                first_view.setVisibility(View.GONE);
                cb_home_extra.setVisibility(View.GONE);
            } else {
                clickForSportsData.setOnClick(viewPosition, positionInView, cb_home_extra);
            }

        } else if (positionInView == 2) {
            cb_home = (TextView) view.findViewById(R.id.cb_home);
            if (totalView == 3) {
                cb_home.setBackgroundDrawable(getResources().getDrawable(R.drawable.rb_left_round));
            }
            clickForSportsData.setOnClick(viewPosition, positionInView, cb_home);
        } else if (positionInView == 3) {
            cb_draw = (TextView) view.findViewById(R.id.cb_draw);
            clickForSportsData.setOnClick(viewPosition, positionInView, cb_draw);
        } else if (positionInView == 4) {
            cb_away = (TextView) view.findViewById(R.id.cb_away);
            if (totalView == 3) {
                cb_away.setBackgroundDrawable(getResources().getDrawable(R.drawable.rb_right_round));
            }
            clickForSportsData.setOnClick(viewPosition, positionInView, cb_away);
        } else if (positionInView == 5) {
            cb_away_extra = (TextView) view.findViewById(R.id.cb_away_extra);
            second_view = view.findViewById(R.id.second_view);
            if (totalView == 3) {
                cb_away_extra.setVisibility(View.GONE);
                second_view.setVisibility(View.GONE);
            } else {
                clickForSportsData.setOnClick(viewPosition, positionInView, cb_away_extra);
            }
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.from_middle, R.anim.to_middle);

        TpsGamesClass.getInstance().closeScreen(this);
    }

    protected void setNoOfLines() {
        int totalNoOfLines = 0;
        if (totalView == 3) {
            totalNoOfLines = game_one.get(0).getTotalSize() * game_two.get(0).getTotalSize() * game_three.get(0).getTotalSize() * game_four.get(0).getTotalSize() * game_five.get(0).getTotalSize() * game_six.get(0).getTotalSize() * game_seven.get(0).getTotalSize() * game_eight.get(0).getTotalSize() * game_nine.get(0).getTotalSize() * game_ten.get(0).getTotalSize() * game_eleven.get(0).getTotalSize() * game_twelve.get(0).getTotalSize() * game_thirteen.get(0).getTotalSize();
        } else {
            totalNoOfLines = game_one.get(0).getTotalSize() * game_two.get(0).getTotalSize() * game_three.get(0).getTotalSize() * game_four.get(0).getTotalSize() * game_five.get(0).getTotalSize() * game_six.get(0).getTotalSize();
        }
        no_of_lines.setText(totalNoOfLines + "");
        setPrice();

    }


    protected void addNumberToPosition(SportsGameSelectionBean sportsGameSelectionBean, int positionInView, boolean isAdd) {
        if (positionInView == 1) {
            if (isAdd) {
                sportsGameSelectionBean.setHomePlus("H");
            } else {
                sportsGameSelectionBean.setHomePlus("");
            }
        } else if (positionInView == 2) {
            if (isAdd) {
                sportsGameSelectionBean.setHome("H");
            } else {
                sportsGameSelectionBean.setHome("");
            }
        } else if (positionInView == 3) {
            if (isAdd) {
                sportsGameSelectionBean.setDraw("D");
            } else {
                sportsGameSelectionBean.setDraw("");
            }
        } else if (positionInView == 4) {
            if (isAdd) {
                sportsGameSelectionBean.setAway("A");
            } else {
                sportsGameSelectionBean.setAway("");
            }
        } else if (positionInView == 5) {
            if (isAdd) {
                sportsGameSelectionBean.setAwayPlus("A");
            } else {
                sportsGameSelectionBean.setAwayPlus("");
            }
        }
        setNoOfLines();

    }

    protected void addNumber(int viewPosition, int positionInView, boolean isAdd) {
        if (viewPosition == 1) {
            addNumberToPosition(game_one.get(0), positionInView, isAdd);
        } else if (viewPosition == 2) {
            addNumberToPosition(game_two.get(0), positionInView, isAdd);
        } else if (viewPosition == 3) {
            addNumberToPosition(game_three.get(0), positionInView, isAdd);
        } else if (viewPosition == 4) {
            addNumberToPosition(game_four.get(0), positionInView, isAdd);
        } else if (viewPosition == 5) {
            addNumberToPosition(game_five.get(0), positionInView, isAdd);
        } else if (viewPosition == 6) {
            addNumberToPosition(game_six.get(0), positionInView, isAdd);
        } else if (viewPosition == 7) {
            addNumberToPosition(game_seven.get(0), positionInView, isAdd);
        } else if (viewPosition == 8) {
            addNumberToPosition(game_eight.get(0), positionInView, isAdd);
        } else if (viewPosition == 9) {
            addNumberToPosition(game_nine.get(0), positionInView, isAdd);
        } else if (viewPosition == 10) {
            addNumberToPosition(game_ten.get(0), positionInView, isAdd);
        } else if (viewPosition == 11) {
            addNumberToPosition(game_eleven.get(0), positionInView, isAdd);
        } else if (viewPosition == 12) {
            addNumberToPosition(game_twelve.get(0), positionInView, isAdd);
        } else if (viewPosition == 13) {
            addNumberToPosition(game_thirteen.get(0), positionInView, isAdd);
        }
    }

    OnSportsItemClick onSportsItemClick = new OnSportsItemClick() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onItemClick(int viewPosition, int positionInView, View view) {

            if (view.isSelected()) {
                if (totalView == 5) {
                    if (positionInView == 1) {
                        view.setBackground(getResources().getDrawable(R.drawable.rb_left_round));
                        view.setSelected(false);
                    } else if (positionInView == 5) {
                        view.setBackground(getResources().getDrawable(R.drawable.rb_right_round));
                        view.setSelected(false);
                    } else {
                        view.setBackground(getResources().getDrawable(R.drawable.rb_normal));
                        view.setSelected(false);
                    }
                    addNumber(viewPosition, positionInView, false);
                } else {
                    if (positionInView == 2) {
                        view.setBackground(getResources().getDrawable(R.drawable.rb_left_round));
                        view.setSelected(false);
                    } else if (positionInView == 4) {
                        view.setBackground(getResources().getDrawable(R.drawable.rb_right_round));
                        view.setSelected(false);
                    } else {
                        view.setBackground(getResources().getDrawable(R.drawable.rb_normal));
                        view.setSelected(false);
                    }
                    addNumber(viewPosition, positionInView, false);
                }


            } else {
                if (totalView == 5) {
                    if (positionInView == 1) {
                        view.setBackground(getResources().getDrawable(R.drawable.sports_slector));
                        view.setSelected(true);
                    } else if (positionInView == 5) {
                        view.setBackground(getResources().getDrawable(R.drawable.sports_slector_right));
                        view.setSelected(true);
                    } else {
                        view.setBackground(getResources().getDrawable(R.drawable.sports_slector_normal));
                        view.setSelected(true);
                    }
                    addNumber(viewPosition, positionInView, true);
                } else {
                    if (positionInView == 2) {
                        view.setBackground(getResources().getDrawable(R.drawable.sports_slector));
                        view.setSelected(true);
                    } else if (positionInView == 4) {
                        view.setBackground(getResources().getDrawable(R.drawable.sports_slector_right));
                        view.setSelected(true);
                    } else {
                        view.setBackground(getResources().getDrawable(R.drawable.sports_slector_normal));
                        view.setSelected(true);
                    }
                    addNumber(viewPosition, positionInView, true);
                }

            }
        }
    };

    protected void setGame() {
        lastBetAmountPosition = 1;
        if (lastGameSelectedIndex == 0) {
            currentGame = TpsGamesClass.getInstance().getGameSle("soccer13");
            if (TpsGamesClass.getInstance().getDeviceName().contains("550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx053")) {
                mainviewToadd = inflater.inflate(R.layout.layout_sports_land, null);
            } else {
                mainviewToadd = inflater.inflate(R.layout.layout_sports_tps390, null);
            }
            mainviewToadd.setLayoutParams(param);
            main_view.removeAllViews();
            main_view.addView(mainviewToadd);
            lucky_numbers.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
            mini_keno.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_game_selector));
            totalView = 3;
            initializeGameLogic();
            buy_amt.setText("$ 0.0");
            no_of_lines.setText("0");
            setBetAmount();
        } else {
            currentGame = TpsGamesClass.getInstance().getGameSle("soccer6");
            if (TpsGamesClass.getInstance().getDeviceName().contains("550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx053")) {
                mainviewToadd = inflater.inflate(R.layout.layout_sports_soccer6_land, null);
            } else {
                mainviewToadd = inflater.inflate(R.layout.layout_sports_soccer6_tps390, null);

//                if (getOrient.getWidth() > getOrient.getHeight() || getOrient.getWidth() == getOrient.getHeight()) {
//                    ((LinearLayout) mainviewToadd).setWeightSum(3.0f);
//                }


            }
            mainviewToadd.setLayoutParams(param);
            main_view.removeAllViews();
            main_view.addView(mainviewToadd);
            mini_keno.setBackgroundDrawable(getResources().getDrawable(R.drawable.game_selection_bg_pressed_new));
            lucky_numbers.setBackgroundDrawable(getResources().getDrawable(R.drawable.top_game_selector));
            totalView = 5;
            initializeGameLogic();
            buy_amt.setText("$ 0.0");
            no_of_lines.setText("0");
            setBetAmount();
        }
        if (TpsGamesClass.getInstance().getDeviceName().contains("TPS580")) {
            TpsGamesClass.getInstance().startFrontScreen("sle");
        }
    }

    public void updateFrontScreen() {
        if (TpsGamesClass.getInstance().getDeviceName().contains("TPS580")) {
            TpsGamesClass.getInstance().setSoccerScreen(currentGame.getGameTypeDevName().contains("13") ? "13" : "6", this);
        }
    }

    protected void setPrice() {
        unitPrice = Double.parseDouble(currentGame.getGameTypeUnitPrice());
        amount = (unitPrice * (lastBetAmountPosition)) + "";
        if (amount.contains(".")) {
            String[] decimalAmount = amount.split("[.]");
            amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
        }

        setBetAmount(Double.parseDouble(amount));
    }

    DrawDialog dialog;

    public void showOtherDialog(Context context) {

        dialog = (DrawDialog) new DrawDialog(context, true).setNegativeButton("CANCEL", null).setPositiveButton("DONE", null).setTitle("CHOOSE UNIT PRICE");
        double amountToStart = 0;
//        if (bet_other.getText().toString().contains("OTHER")) {
//            amountToStart = currentGame.getBets()[0].getUnitPrice() * lastBetAmountPosition;
//        } else {
//            amountToStart = amountOther;
//        }
        ((DrawDialog) dialog).setBetAmountOther(Double.parseDouble(currentGame.getGameTypeUnitPrice()), Double.parseDouble(currentGame.getGameTypeMaxBetAmtMultiple() + ""), false, Double.parseDouble(currentGame.getGameTypeUnitPrice()), Double.parseDouble(currentGame.getGameTypeUnitPrice()), totalAmountOther, (amountToStart + ""));
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    DrawDialog.TotalAmountOther totalAmountOther = new DrawDialog.TotalAmountOther() {
        @Override
        public void otherAmount(double amount, int betAmtMul) {

            lastBetAmountPosition = betAmtMul;
            setPrice();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                if (lastGameSelectedIndex == 0) {
                    return;
                }
                bet_other.setText("OTHER");
                lastBetAmountPosition = 1;
                lastGameSelectedIndex = 0;
                setGame();
                break;
            case R.id.mini_keno:
                if (lastGameSelectedIndex == 1) {
                    return;
                }
                bet_other.setText("OTHER");
                lastBetAmountPosition = 1;
                lastGameSelectedIndex = 1;
                setGame();
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
                setPrice();
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
                setPrice();
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
                setPrice();
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
                setPrice();
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
                setPrice();
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
                setPrice();
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
                setPrice();
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
                setPrice();
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
                setPrice();
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


                showOtherDialog(ActivitySleGamesTps390.this);

                break;
            case R.id.buy_btn:
                if (!no_of_lines.getText().toString().trim().equalsIgnoreCase("0")) {
                    openCard();
                }
                break;
            case R.id.common_winning_claim_btn:
//                if (!TpsGamesClass.getInstance().getIfHasCamera()) {
//                    TpsGamesClass.getInstance().showAToast("Device has No Camera", ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
//                    return;
//                }
                if (TpsGamesClass.getInstance().isCameraOpen()) {
                    return;
                }
                TpsGamesClass.getInstance().setCameraOpen(true);
                intent = new Intent(ActivitySleGamesTps390.this, ClaimWinningActivityTps390.class);
                startActivityForResult(intent, 055);
                break;
            case R.id.btn_refresh:
                setGame();
                break;

            case R.id.common_bet_slip_btn:
                if (TpsGamesClass.getInstance().getDeviceName().contains("550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx053")||TpsGamesClass.getInstance().getDeviceName().contains("390")) {
                    new AlertDialog.Builder(ActivitySleGamesTps390.this)
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
                    new AlertDialog.Builder(ActivitySleGamesTps390.this)
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
                TpsGamesClass.getInstance().setCameraOpen(true);
                startActivityForResult(new Intent(ActivitySleGamesTps390.this, ActivityBetSlipCamera.class), 056);
                break;
            case R.id.draw_btn:
                sideDrawerItemId=v.getId();
                sideDrawer.closeDrawers();
                break;

        }
    }

    protected void moveToLogin() {
        Intent intent = new Intent(ActivitySleGamesTps390.this, LoginActivityTpsGame.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void serverRequestCall(String serverRequest, String requestServer) {
        if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.ONETOTWELVE)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/oneToTwelveBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivitySleGamesTps390.this, "oneTwoTwelve", ActivitySleGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();

        } else if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.TWELVEBYTWENTYFOUR)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/twelveByTwentyFourBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivitySleGamesTps390.this, "TwleveByTwenty", ActivitySleGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();

        } else if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.MINIROULETTE)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/oneToTwelveRouletteBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivitySleGamesTps390.this, "TwleveByTwenty", ActivitySleGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();

        } else if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.KENOSIX)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/kenoSixBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivitySleGamesTps390.this, "TwleveByTwenty", ActivitySleGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();

        } else if (serverRequest != null && serverRequest.equalsIgnoreCase(ConstantField.KENOTWO)) {
            String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/kenoTwoBuy.action?json=" + getExtraParms(requestServer);
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivitySleGamesTps390.this, "TwleveByTwenty", ActivitySleGamesTps390.this, "TwleveByTwenty");
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();

        } else if (serverRequest != null && serverRequest.equalsIgnoreCase("SLE_SALE")) {
            SportsSaleBean sportsSaleBean = TpsGamesClass.getInstance().getSportsBean(requestServer);

            String gameId = sportsSaleBean.getGameId().substring(sportsSaleBean.getGameId().indexOf("=") + 1, sportsSaleBean.getGameId().length());
            String gameTypeId = sportsSaleBean.getGameTypeId().substring(sportsSaleBean.getGameTypeId().indexOf("=") + 1, sportsSaleBean.getGameTypeId().length());
            String drawCoount = sportsSaleBean.getDrawCount().substring(sportsSaleBean.getDrawCount().indexOf("=") + 1, sportsSaleBean.getDrawCount().length());
            String ticketAmount = sportsSaleBean.getTicketAmt().substring(sportsSaleBean.getTicketAmt().indexOf("=") + 1, sportsSaleBean.getTicketAmt().length());
            String url = TpsGamesClass.getInstance().getTicketSaleServicesForSle(ActivitySleGamesTps390.this, TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName()
                    , gameId, gameTypeId, sportsSaleBean.getDrawInfoString().substring(sportsSaleBean.getDrawInfoString().indexOf("=") + 1, sportsSaleBean.getDrawInfoString().length()), drawCoount, ticketAmount, sportsSaleBean);
//                url = url + "&tokenId=" + responseData.getTokenId();
            HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivitySleGamesTps390.this, "ebet slip", ActivitySleGamesTps390.this, "sports", null);
            TpsGamesClass.getInstance().startLoader(this);
            httpRequest.executeTask();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && data.hasExtra("resetRequest")) {
            bet_other.setText("OTHER");
            setGame();
        } else if (data != null && data.hasExtra("showDialog")) {
            Communcations.winningInside(ActivitySleGamesTps390.this, data.getStringExtra("message"));
        }else if (data != null && data.hasExtra("json")) {
            serverRequestCall(data.getStringExtra("methodName"), data.getStringExtra("json"));
        } else if (data != null && data.hasExtra("printResponse")) {

            if (TpsGamesClass.getInstance().getDeviceName().contains("rk30")) {
                intent = new Intent(ActivitySleGamesTps390.this, PrintWinningAzt.class);
            } else if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M")) {
                intent = new Intent(ActivitySleGamesTps390.this, ActivityBluetoothPrint.class);
            } else {
                intent = new Intent(ActivitySleGamesTps390.this, PrintWinningClaim.class);
            }

//            intent = new Intent(ActivitySleGamesTps390.this, PrintWinningClaim.class);
            intent.putExtra("response", data.getExtras().getString("printResponse"));
            startActivity(intent);
        }
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

    private WinnigDialog winnigDialog;
    public boolean isValueSetToDialog;
    String playerId;

    private boolean isRgCall;

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
        HttpRequest httpRequest = TpsGamesClass.getInstance().getHttpRequest(url, ActivitySleGamesTps390.this, "verifyUser", ActivitySleGamesTps390.this, "rgCall", Utility.rGGamingHeader);
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
                TpsGamesClass.getInstance().showAToast("Please Enter Some Value", ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
            }
        }
    };
    View.OnClickListener negativeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

//            TpsGamesClass.getInstance().closeScreen(HomeActivityTpsGame.this);
//            ServerCommClass.getServerCommClass().getOnKeyListener().onFragmentChange();
            purchaseRequest();

            winnigDialog.dismiss();
        }
    };

    @Override
    public void onResponse(String response, String requestedMethod) {
        TpsGamesClass.getInstance().stopLoader();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject == null) {
             //   if (ServerCommClass.getServerCommClass().getFragmentActivity() == null) {
                    moveToLogin();
             //   }
                TpsGamesClass.getInstance().showAToast("No data available!", ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
                return;
            } else if ((jsonObject.has("isSuccess") && jsonObject.optBoolean("isSuccess") == false)) {
              //  if (ServerCommClass.getServerCommClass().getFragmentActivity() == null) {
                    moveToLogin();
               // }
                TpsGamesClass.getInstance().showAToast(jsonObject.optString("errorMsg"), ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
                return;
            } else if (jsonObject.has("responseCode") && jsonObject.optString("responseCode").equals("500")) {
             //   if (ServerCommClass.getServerCommClass().getFragmentActivity() == null) {
                    moveToLogin();
               // }
                TpsGamesClass.getInstance().showAToast(jsonObject.optString("responseMsg"), ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (response.equalsIgnoreCase("failed")) {
            //if (ServerCommClass.getServerCommClass().getFragmentActivity() == null) {
                moveToLogin();
           // }
            TpsGamesClass.getInstance().showAToast("Server Error", ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
            return;
        }
        if (response.equalsIgnoreCase("ErrorMsg:Time Out, Login Again!!|ErrorCode:01|")) {
            TpsGamesClass.getInstance().showAToast("Time Out, Login Again!!", ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
            moveToLogin();
            return;
        }

        if (response.contains("login again")) {
            TpsGamesClass.getInstance().showAToast("Timer Out", ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
            moveToLogin();
            finish();
            return;
        }
        if (response.contains("<HTML>")) {
            TpsGamesClass.getInstance().showAToast("Connect to internet", ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
            moveToLogin();
            finish();
            return;
        }
        if (response.contains("<html>") || response.contains("<!DOCTYPE HTML PUBLIC")) {
            TpsGamesClass.getInstance().showAToast("Server not sending correct data", ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
            moveToLogin();
            finish();
            return;
        }
        if (requestedMethod.equalsIgnoreCase("fail")) {
           // if (ServerCommClass.getServerCommClass().getFragmentActivity() == null) {
                moveToLogin();
           // }
            TpsGamesClass.getInstance().showAToast("No data available!", ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
            return;
        } else if (requestedMethod.equalsIgnoreCase("gameData")) {
            TpsGamesClass.getInstance().setGameBean(TpsGamesClass.getInstance().getGson().fromJson(response, GameBean.class));
            TpsGamesClass.getInstance().getActivityDgeGamesTps390().resetEveryThing();
            onBackPressed();
        }else if (requestedMethod.equals("TwleveByTwenty")) {


//            USERNAME = getIntent().getStringExtra("userName") != null ? getIntent().getStringExtra("userName") : "";
//            SESSIONID = getIntent().getStringExtra("sessionId") != null ? getIntent().getStringExtra("sessionId") : "";
//            LASTDRAWRESULT = getIntent().getStringExtra("lastDrawResult") != null ? getIntent().getStringExtra("lastDrawResult") : "";
//            PLAYERBALANCE = getIntent().getStringExtra("playerBalance") != null ? getIntent().getStringExtra("playerBalance") : ""

            if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("PT7003")) {
                intent = new Intent(ActivitySleGamesTps390.this, PrintActivityAllGamesTousei.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            } else if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("TPS515")) {
                intent = new Intent(ActivitySleGamesTps390.this, PrintActivityDgeExternalPrinter.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            } else if (TpsGamesClass.getInstance().getDeviceName().contains("rk30")) {
                TpsGamesClass.getInstance().setPrintResponseForAzt(response);
                intent = new Intent(ActivitySleGamesTps390.this, PrintActivityAZT.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            } else {
                intent = new Intent(ActivitySleGamesTps390.this, PrintActivityAllGames.class);
                intent.putExtra("response", response);
                startActivityForResult(intent, 054);
            }

        } else if (requestedMethod.equalsIgnoreCase("sports")) {
            if (response.contains("ErrorCode")) {
                TpsGamesClass.getInstance().showAToast(response.substring(response.indexOf(":") + 1, response.indexOf("|")), ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
                return;
            }
            //ErrorMsg
            if (response.contains("ErrorMsg")) {
                TpsGamesClass.getInstance().showAToast(response.substring(response.indexOf(":") + 1, response.length()), ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
                return;
            }
            Intent intent = null;
            if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("TPS515")) {
                intent = new Intent(ActivitySleGamesTps390.this, PrintActivitySportsExternal.class);
            } else {
                intent = new Intent(ActivitySleGamesTps390.this, PrintActivitySportsGame.class);
            }
            intent.putExtra("response", response);
            startActivityForResult(intent, 59);
        } else if (requestedMethod.equals("rgCall")) {

            TpsGamesClass.getInstance().stopCard();
            try {
                JSONObject jsonObject = new JSONObject(response);
                if (jsonObject != null && jsonObject.optInt("responseCode") == 100) {

                    TpsGamesClass.getInstance().setPlayerVerified(true, "");
                    try {

                    } catch (Exception e) {

                    }
                    purchaseRequest();

                } else {
                    try {

//                        if (methodNameServer != null && methodNameServer.trim().length() > 0) {

//                            TpsGamesClass.getInstance().closeScreen(this);
//
//                            TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
//                            String url = "/sdcard/rainbow_header.png";
//                            TpsGamesClass.getInstance().pleaseWait(jsonObject.optString("responseMsg"), url);
//                            TpsGamesClass.getInstance().displayScreen(this);
                        TpsGamesClass.getInstance().showAToast(jsonObject.optString("responseMsg"), ActivitySleGamesTps390.this, Toast.LENGTH_SHORT);
//                        }
                    } catch (Exception e) {

                    }

                    TpsGamesClass.getInstance().setPlayerVerified(false, jsonObject.optString("responseMsg"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

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
                startActivity(new Intent(ActivitySleGamesTps390.this, ActivityEbetSlipTpsGame.class));
                break;
            case R.id.draw_btn:
                String url = Utility.baseUrl + Utility.portNumber + Utility.projectName + "drawGames/playMgmt/fetchDrawGameDataPCPOS.action";
                HttpRequest httpRequest = TpsGamesClass.getInstance(this).getHttpRequest(url, this, "fetching game data", this, "gameData");
                TpsGamesClass.getInstance().startLoader(this);
                httpRequest.executeTask();
                break;



        }
        sideDrawerItemId=0;

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
