package demo.stpl.com.tpsmergedbuild.betslip;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;

//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.GameBean;

//import com.tablet.stpl.comman.interfaces.ServerCommClass;

public class MiniRoulette {

    Mat newimage;
    private Point topleft, topright, bottomleft, bottomright;
    Rect roi;
    private Map<String, GameBean.Games.Bets> betsMap;
    Context context;
    Mat boundingSquare;
    int whitepixelvalues;
    Boolean betslipcorrect = true;
    String errorstring;

    GameBean.Games gameBean;
    PreviewNew previewNew;
    public String selecteddraw = "";
    private HashMap<Integer, String> selected_numbers = new HashMap<>();

    List<String> DrawValues = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
    List<String> BetValues = new ArrayList<>(Arrays.asList("10", "20", "30", "50"));

    private TreeMap<Integer, String> draws = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet1 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet2 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet3 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet4 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet5 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet6 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet7 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet8 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet9 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet10 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet11 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bet12 = new TreeMap<Integer, String>();

    public MiniRoulette(GameBean.Games games, PreviewNew previewNew) {
        this.gameBean = games;
        this.previewNew = previewNew;
    }

    public void MiniRoulette_CalculateBets(Context context, Mat image, Point topl, Point topr, Point bottoml, Point bottomr) {
        this.context = context;
        newimage = image;
        topright = topr;
        topleft = topl;
        bottomleft = bottoml;
        bottomright = bottomr;
        betsMap = new HashMap<>();

        Point p1, p2, p3, p4;
        p1 = topright;
        Imgproc.rectangle(newimage, new Point(p1.x - 5, p1.y - 5), new Point(p1.x + 5, p1.y + 5), new Scalar(0, 128, 255), -1);
        Imgproc.circle(newimage, new Point(p1.x, p1.y), 20, new Scalar(255, 0, 0), 5); // top right corner
        p2 = topleft;
        Imgproc.rectangle(newimage, new Point(p2.x - 5, p2.y - 5), new Point(p2.x + 5, p2.y + 5), new Scalar(0, 128, 255), -1);
        Imgproc.circle(newimage, new Point(p2.x, p2.y), 20, new Scalar(0, 255, 0), 5); // top left corner
        p3 = bottomleft;
        Imgproc.rectangle(newimage, new Point(p3.x - 5, p3.y - 5), new Point(p3.x + 5, p3.y + 5), new Scalar(0, 128, 255), -1);
        Imgproc.circle(newimage, new Point(p3.x, p3.y), 20, new Scalar(0, 0, 255), 5); // bottom left corner
        p4 = bottomright;
        Imgproc.rectangle(newimage, new Point(p4.x - 5, p4.y - 5), new Point(p4.x + 5, p4.y + 5), new Scalar(0, 128, 255), -1);
        Imgproc.circle(newimage, new Point(p4.x, p4.y), 20, new Scalar(0, 0, 255), 5); // bottom left corner

        Bitmap abc = createBitmapfromMat(newimage);

        Mat m = newimage;
        final Mat grayMat = new Mat(m.size(), CvType.CV_8U);  // Convert to graymat
        Imgproc.cvtColor(m, grayMat, Imgproc.COLOR_BGRA2GRAY);
        Imgproc.adaptiveThreshold(grayMat, grayMat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 15, 40);
        Mat awehi = grayMat;

        try {
            if (betslipcorrect) {
                getdraws(grayMat);
            }
            if (betslipcorrect) {
                getbets(grayMat, 67, 80, 1, bet1);
            }
            if (betslipcorrect) {
                getbets(grayMat, 201, 80, 2, bet2);
            }
            if (betslipcorrect) {
                getbets(grayMat, 335, 80, 3, bet3);
            }
            if (betslipcorrect) {
                getbets(grayMat, 469, 80, 4, bet4);
            }
            if (betslipcorrect) {
                getbets(grayMat, 67, 157, 5, bet5);
            }
            if (betslipcorrect) {
                getbets(grayMat, 201, 157, 6, bet6);
            }
            if (betslipcorrect) {
                getbets(grayMat, 335, 157, 7, bet7);
            }
            if (betslipcorrect) {
                getbets(grayMat, 469, 157, 8, bet8);
            }
            if (betslipcorrect) {
                getbets(grayMat, 67, 234, 9, bet9);
            }
            if (betslipcorrect) {
                getbets(grayMat, 201, 234, 10, bet10);
            }
            if (betslipcorrect) {
                getbets(grayMat, 335, 234, 11, bet11);
            }
            if (betslipcorrect) {
                getbets(grayMat, 469, 234, 12, bet12);
            }
        }
        catch (Exception e)
        {
            betslipcorrect = false;
            errorstring = "Please keep the Betslip still and completely inside the rectangle";
        }

        if (betslipcorrect) {
            CreateJason();
        } else {
            TpsGamesClass.getInstance().stopLoader();
            new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setTitle("ERROR")
                    .setMessage(errorstring)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Cleardata();
                        }
                    })
                    .show();
        }

    }

    public void getdraws(Mat mat) {
        Log.d("VALUES", "NUMBER OF DRAWS");

        double X1 = topleft.x + 144;
        double X2 = topleft.x + 144 + 35;
        double Y1 = topleft.y + 8;
        double Y2 = topleft.y + 8 + 33;

        for (int c = 0; c < 3; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, draws);
            X1 = X1 + 35;
            X2 = X2 + 35;
        }

        if (Integer.parseInt(draws.keySet().toArray()[0] + "") < 80 && Integer.parseInt(draws.keySet().toArray()[1] + "") > 80) {
            selecteddraw = (Integer.parseInt(draws.get(draws.keySet().toArray()[0])) + 1) + "";
        } else {
            betslipcorrect = false;
            errorstring = "Please Select Number Of Draws Properly";
        }
        Bitmap abc = createBitmapfromMat(newimage);
    }

    public void getbets(Mat mat, double XC, double YC, int number, TreeMap<Integer, String> bet) {
        Log.d("VALUES", "BET " + number);

        double X1 = topleft.x + XC;
        double X2 = topleft.x + XC + 35;
        double Y1 = topleft.y + YC;
        double Y2 = topleft.y + YC + 34;
        int c = 0;
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {
                getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, bet);
                X1 = X1 + 35;
                X2 = X2 + 35;
                c++;
            }
            X1 = topleft.x + XC;
            X2 = topleft.x + XC + 35;
            Y1 = topleft.y + YC + 34;
            Y2 = topleft.y + YC + 34 + 34;
        }

        if (Integer.parseInt(bet.keySet().toArray()[0] + "") < 30 ) {
            selected_numbers.put(number, BetValues.get(Integer.parseInt(bet.get(bet.keySet().toArray()[0]))));
        }  if( Integer.parseInt(bet.keySet().toArray()[1] + "") < 30) {
            betslipcorrect = false;
            errorstring = "Please Select bets properly for bet " + number;
        }

        Bitmap abc = createBitmapfromMat(newimage);
    }



    public void getwhitepixelvalue(Mat mat, double X1, double X2, double Y1, double Y2, int position, TreeMap calculation) {
        roi = new Rect(new Point(X1, Y1), new Point(X2, Y2));
        boundingSquare = new Mat(mat, roi);
        whitepixelvalues = Core.countNonZero(boundingSquare);
        calculation.put(whitepixelvalues, position + "");
        Log.d("VALUES", whitepixelvalues + " " + position);
        Imgproc.rectangle(newimage, new Point(X1, Y1), new Point(X2, Y2), new Scalar(0, 128, 255), 1);
    }


    public void CreateJason() {
        JSONArray betTypeData = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        int int_total = 0;


        GameBean.Games powerPlay = null;
        GameBean gameBean = TpsGamesClass.getInstance().getGameBean();
        for (GameBean.Games games : gameBean.getGames()) {
            if (games.getGameCode().equals("MiniRoulette")) {
                powerPlay = games;
                break;
            }
        }

        try {

            for (int x = 1; x <= 12; x++) {

                if (selected_numbers.get(x) != null) {
                    JSONObject intallOddNumbers = new JSONObject();
                    intallOddNumbers.put("noPicked", "1");
                    intallOddNumbers.put("betAmtMul", 1);
                    intallOddNumbers.put("isQp", "false");

                    intallOddNumbers.put("pickedNumbers", x + "");
                    intallOddNumbers.put("betName", "roulette");
                    intallOddNumbers.put("unitPrice", Integer.parseInt(selected_numbers.get(x)));
                    intallOddNumbers.put("noOfLines", 1);

                    int_total = int_total + Integer.parseInt(selected_numbers.get(x));
                    betTypeData.put(intallOddNumbers);
                }

            }
            double unitprice = powerPlay.getBets()[0].getUnitPrice();
            jsonObject.put("noOfPanel", betTypeData.length());
            jsonObject.put("totalPurchaseAmt", int_total + "");


            JSONObject commonSaleData = new JSONObject();
            commonSaleData.put("isAdvancePlay", false);
            commonSaleData.put("drawData", new JSONArray());
            commonSaleData.put("noOfDraws", Integer.parseInt(selecteddraw));
            commonSaleData.put("isDrawManual", true);
            commonSaleData.put("gameName", "MiniRoulette");

            jsonObject.put("commonSaleData", commonSaleData);

            jsonObject.put("betTypeData", betTypeData);
            jsonObject.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());
            jsonObject.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());

            System.out.println("request-> "
                    + jsonObject.toString());
            Intent intent = new Intent();
            intent.putExtra("json", jsonObject.toString());
            intent.putExtra("methodName", powerPlay.getGameCode());
            ((Activity) context).setResult(056, intent);
//            ServerCommClass.getServerCommClass().getServerRequest().request(jsonObject.toString(), powerPlay.getGameCode()+"betSlip");
            Cleardata();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        TpsGamesClass.getInstance().getActivityBetSlipCamera().finish();

    }

    public void Cleardata() {
        whitepixelvalues = 0;
        betslipcorrect = true;
        selecteddraw = "";
        selected_numbers.clear();
        errorstring = "";
        previewNew.setisisImageCaptured(false);
        draws.clear();
        bet1.clear();
        bet2.clear();
        bet3.clear();
        bet4.clear();
        bet5.clear();
        bet6.clear();
        bet7.clear();
        bet8.clear();
        bet9.clear();
        bet10.clear();
        bet11.clear();
        bet12.clear();
    }

    public Bitmap createBitmapfromMat(Mat snap) {
        Bitmap bp = Bitmap.createBitmap(snap.cols(), snap.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(snap, bp);
        return bp;
    }

}
