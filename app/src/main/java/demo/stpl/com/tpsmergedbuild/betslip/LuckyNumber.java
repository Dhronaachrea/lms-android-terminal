package demo.stpl.com.tpsmergedbuild.betslip;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import java.util.TreeMap;

import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;
import demo.stpl.com.tpsmergedbuild.utils.widgets.PurchaseLabels;

//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.GameBean;
//import demo.stpl.com.tpsmergedbuild.utils.widgets.PurchaseLabels;

//import com.example.mylibrary.Utils.PurchaseLabels;

//import com.example.mylibrary.Beans.FatchGameData;
//import com.example.mylibrary.Utils.CalculationHelper;
//import com.example.mylibrary.Utils.PurchaseLabels;

public class LuckyNumber {

    Mat newimage, bandwmat;
    private Point topleft, topright, bottomleft, bottomright;
    Context context;
    Rect roi;
    Mat boundingSquare;
    int whitepixelvalues, drawselected, bettypeselected, numberspicked;
    String errorstring = "", numberselectedstring = "", betamountselected = "";
    PreviewNew previewNew;
    Boolean inException = false;
    String numberofdrawsvalue = "";
    String betamountvalue = "";
    String numberpickedvalue = "";
    String bettypevalue = "";
    Boolean betslipcorrect = true;

    List<String> DrawsValues = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
    List<String> BetAmountValues = new ArrayList<>(Arrays.asList("1", "2", "3", "5", "10"));
    List<String> BetTypeValues = new ArrayList<>(Arrays.asList("Direct1", "Direct2", "Direct3", "Direct4", "Direct5", "Perm2", "Perm3"));
    List<String> numberpickedvalues = new ArrayList<>(Arrays.asList("3", "4", "5", "6", "7", "8"));
    List<String> gamevalues = new ArrayList<>();
    ArrayList<String> selectednumber = new ArrayList<String>();

    Boolean isqp = false;

    private TreeMap<Integer, String> quickpick = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> game1 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> draws = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> betamount = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bettype = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> nopicked = new TreeMap<Integer, String>();

    public LuckyNumber(PreviewNew previewNew) {
        this.previewNew = previewNew;
    }

    public void Lucky_Number_CalculateBets(Context context, Mat image, Point topl, Point topr, Point bottoml, Point bottomr) {
        this.context = context;
        newimage = image;
        topright = topr;
        topleft = topl;
        bottomleft = bottoml;
        bottomright = bottomr;


        Point p1, p2, p3, p4;

        p1 = topright;
        Imgproc.rectangle(newimage, new Point(p1.x - 5, p1.y - 5), new Point(p1.x + 5, p1.y + 5), new Scalar(0, 128, 255), -1);
        Imgproc.circle(newimage, new Point(p1.x, p1.y), 20, new Scalar(255, 0, 0), 5); // top right corner
        p2 = topleft;
        Imgproc.rectangle(newimage, new Point(p2.x - 5, p2.y - 5), new Point(p2.x + 5, p2.y + 5), new Scalar(0, 128, 255), -1);
        Imgproc.circle(newimage, new Point(p2.x, p2.y), 20, new Scalar(0, 255, 0), 5); // top left corner
        //  bottomleft.y += 5;
        p3 = bottomleft;
        Imgproc.rectangle(newimage, new Point(p3.x - 5, p3.y - 5), new Point(p3.x + 5, p3.y + 5), new Scalar(0, 128, 255), -1);
        Imgproc.circle(newimage, new Point(p3.x, p3.y), 20, new Scalar(0, 0, 255), 5); // bottom left corner
        // bottomright.y += 5;
        p4 = bottomright;
        Imgproc.rectangle(newimage, new Point(p4.x - 5, p4.y - 5), new Point(p4.x + 5, p4.y + 5), new Scalar(0, 128, 255), -1);
        Imgproc.circle(newimage, new Point(p4.x, p4.y), 20, new Scalar(0, 0, 255), 5); // bottom left corner

        Bitmap abc = createBitmapfromMat(newimage);

        Mat m = newimage;
        final Mat grayMat = new Mat(m.size(), CvType.CV_8U);  // Convert to graymat
        Imgproc.cvtColor(m, grayMat, Imgproc.COLOR_BGRA2GRAY);
        Imgproc.adaptiveThreshold(grayMat, grayMat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 15, 40);
        bandwmat = grayMat;

        try {
            if (betslipcorrect) {
                getdraws(grayMat);
            }
            if (betslipcorrect) {
                getbettype(grayMat);
            }
            if (betslipcorrect) {
                getbetamount(grayMat);
            }
            if (betslipcorrect) {
                getquickpick(grayMat);
            }
            if (betslipcorrect) {
                if (!isqp) {
                    getnumbersselected(grayMat);
                } else {
                    getnumberpicked(grayMat);
                }
            }
            if (betslipcorrect) {
                getselected();
            }
        } catch (Exception e) {
            betslipcorrect = false;
            errorstring = "Please keep the Betslip still and completely inside the rectangle";
        }


        if (betslipcorrect) {
            callpurchase();
        } else {
            TpsGamesClass.getInstance().stopLoader();
            new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setTitle("ERROR")
                    .setMessage(errorstring)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            cleardata();
                        }
                    })
                    .show();
        }
    }


    public void getdraws(Mat mat) {
        Log.d("VALUES", "NUMBER OF DRAWS");

        double X1 = topleft.x + 12;
        double X2 = topleft.x + 12 + 35;
        double Y1 = topleft.y + 20;
        double Y2 = topleft.y + 20 + 33;

        for (int c = 0; c < 5; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, draws);
            X1 = X1 + 35;
            X2 = X2 + 35;
        }

        if (Integer.parseInt(draws.keySet().toArray()[0] + "") < 80 && Integer.parseInt(draws.keySet().toArray()[1] + "") > 80) {
            drawselected = Integer.parseInt(draws.get(draws.keySet().toArray()[0])) + 1;
        } else {
            betslipcorrect = false;
            errorstring = "Please Select Number Of Draws Properly";
        }

        Bitmap abc = createBitmapfromMat(newimage);
        Bitmap abcd = createBitmapfromMat(bandwmat);
    }

    public void getbettype(Mat mat) {
        Log.d("VALUES", "BET TYPE");

        double X1 = topleft.x + 11.4;
        double X2 = topleft.x + 11.4 + 35;
        double Y1 = topleft.y + 75;
        double Y2 = topleft.y + 75 + 33;

        for (int c = 0; c < 7; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, bettype);
            X1 = X1 + 35;
            X2 = X2 + 35;
        }

        if (Integer.parseInt(bettype.keySet().toArray()[0] + "") < 80 && Integer.parseInt(bettype.keySet().toArray()[1] + "") > 80) {
            bettypeselected = Integer.parseInt(bettype.get(bettype.keySet().toArray()[0]));
        } else {
            betslipcorrect = false;
            errorstring = "Please Select Bet Type Properly";
        }

        Bitmap abc = createBitmapfromMat(newimage);
    }

    public void getbetamount(Mat mat) {
        Log.d("VALUES", "BET AMOUNT");

        double X1 = topleft.x + 220;
        double X2 = topleft.x + 220 + 35;
        double Y1 = topleft.y + 20;
        double Y2 = topleft.y + 20 + 33;

        for (int c = 0; c < 5; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, betamount);
            X1 = X1 + 35;
            X2 = X2 + 35;
        }

        if (Integer.parseInt(betamount.keySet().toArray()[0] + "") < 80 && Integer.parseInt(betamount.keySet().toArray()[1] + "") > 80) {
            betamountselected = BetAmountValues.get(Integer.parseInt(betamount.get(betamount.keySet().toArray()[0])));
        } else {
            betslipcorrect = false;
            errorstring = "Please Select Bet Amount Properly";
        }


        Bitmap abc = createBitmapfromMat(newimage);
    }

    public void getquickpick(Mat mat) {
        Log.d("VALUES", "QUICK PICK");

        double X1 = topleft.x + 275;
        double X2 = topleft.x + 275 + 35;
        double Y1 = topleft.y + 75;
        double Y2 = topleft.y + 75 + 33;

        getwhitepixelvalue(mat, X1, X2, Y1, Y2, 1, quickpick);

        if (Integer.parseInt(quickpick.keySet().toArray()[0] + "") < 80) {
            isqp = true;
        }

        Bitmap abc = createBitmapfromMat(newimage);
    }

    public void getnumberpicked(Mat mat) {
        Log.d("VALUES", "NUMBER PICKED QP");

        double X1 = topleft.x + 327;
        double X2 = topleft.x + 327 + 35;
        double Y1 = topleft.y + 75;
        double Y2 = topleft.y + 75 + 33;

        for (int c = 0; c < 6; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, nopicked);
            X1 = X1 + 35;
            X2 = X2 + 35;
        }
        Bitmap abc = createBitmapfromMat(newimage);
    }


    public void getnumbersselected(Mat mat) {
        Log.d("VALUES", "NOS SELECTED");

        double X1 = topleft.x + 11.4;
        double X2 = topleft.x + 11.4 + 35;
        double Y1 = topleft.y + 115;
        double Y2 = topleft.y + 115 + 34.4;

        for (int d = 0; d < 15; d++) {

            for (int c = 0; c < 6; c++) {
                getwhitepixelvalue(mat, X1, X2, Y1, Y2, ((c * 15) + d), game1);
                Y1 = Y1 + 34.4;
                Y2 = Y2 + 34.4;
            }
            X1 = X1 + 35;
            X2 = X2 + 35;
            Y1 = topleft.y + 115;
            Y2 = topleft.y + 115 + 34.4;
        }

        Bitmap abc = createBitmapfromMat(newimage);
    }


    public void getselected() {


        if (!isqp) {
            int total = 0;
            for (int d = 0; d < game1.size(); d++) {

                if (Integer.parseInt(game1.keySet().toArray()[d] + "") < 80) {
                    if (d == 0) {
                        numberselectedstring = (Integer.parseInt(game1.get(game1.keySet().toArray()[d])) + 1) + "";
                    } else {
                        numberselectedstring = numberselectedstring + "," + (Integer.parseInt(game1.get(game1.keySet().toArray()[d])) + 1);
                    }
                    total++;
                }
            }
            if (bettypeselected < 5) {
                if (total != (bettypeselected + 1)) {
                    betslipcorrect = false;
                    errorstring = "Please Select Numbers Properly";
                }
            } else {
                if (bettypeselected == 5) {
                    if (total < 3 || total > 20) {
                        betslipcorrect = false;
                        errorstring = "Please Select Numbers Properly";
                    }
                } else if (bettypeselected == 6) {
                    if (total < 4 || total > 20) {
                        betslipcorrect = false;
                        errorstring = "Please Select Numbers Properly";
                    }
                }
            }
            numberspicked = total;
        } else {
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            int totalCount = 0;
            if (bettypeselected < 5) {
                while (totalCount != (bettypeselected + 1)) {
                    int rnd = generate(1, 90);
                    if (hashMap.get(rnd) == null) {
                        numberselectedstring = numberselectedstring + (totalCount == 0 ? "" : ",") + (rnd < 10 ? ("0" + rnd) : rnd);
                        hashMap.put(rnd, rnd);
                        totalCount++;
                    }
                }
                numberspicked = totalCount;
            } else {
                if (Integer.parseInt(nopicked.keySet().toArray()[0] + "") < 80 && Integer.parseInt(nopicked.keySet().toArray()[1] + "") > 80) {
                    numberpickedvalue = numberpickedvalues.get(Integer.parseInt(nopicked.get(nopicked.keySet().toArray()[0])));

                    while (totalCount != (Integer.parseInt(numberpickedvalue))) {
                        int rnd = generate(1, 80);
                        if (hashMap.get(rnd) == null) {
                            numberselectedstring = numberselectedstring + (totalCount == 0 ? "" : ",") + (rnd < 10 ? ("0" + rnd) : rnd);
                            hashMap.put(rnd, rnd);
                            totalCount++;
                        }
                    }
                    numberspicked = totalCount;

                } else {
                    betslipcorrect = false;
                    errorstring = "Please Select No Picked Properly";
                }
            }
        }
    }

    public void getwhitepixelvalue(Mat mat, double X1, double X2, double Y1, double Y2, int position, TreeMap calculation) {
        roi = new Rect(new Point(X1, Y1), new Point(X2, Y2));
        boundingSquare = new Mat(mat, roi);
        whitepixelvalues = Core.countNonZero(boundingSquare);
        calculation.put(whitepixelvalues, position + "");
        Log.d("VALUES", whitepixelvalues + " " + position);
        Imgproc.rectangle(newimage, new Point(X1, Y1), new Point(X2, Y2), new Scalar(0, 128, 255), 1);
    }

    public int generate(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }


    public void callpurchase() {
        GameBean.Games currentGame = TpsGamesClass.getInstance().getLuckyNumber();
        try {

            JSONObject superOjbedt = new JSONObject();
            JSONObject mainObject = new JSONObject();

            JSONArray drawData = new JSONArray();
            int total = 0;
            for (GameBean.Games.Draws draw : currentGame.getDraws()) {
                if (total < drawselected) {
                    JSONObject drawId = new JSONObject();
                    drawId.put(PurchaseLabels.drawId, draw.getDrawId());
                    drawData.put(drawId);
                }
                total = total + 1;
            }

            mainObject.put("drawData", drawData);
            mainObject.put("gameName", currentGame.getGameCode());
            mainObject.put("noOfDraws", total);
            mainObject.put("isAdvancePlay", false);
            mainObject.put("isDrawManual", true);

            JSONArray panelData = new JSONArray();
            JSONObject panel = new JSONObject();

            panel.put("betName", BetTypeValues.get(bettypeselected));
            panel.put("noPicked", numberspicked + "");

            panel.put("isQp", isqp);
            panel.put("pickedNumbers", numberselectedstring);
            panel.put("betAmtMul", Integer.parseInt(betamountselected));
            panel.put("QPPreGenerated", isqp);
//            panel.put(PurchaseLabels.noOfLines, no_of_lines.getText().toString() + "");
            panelData.put(panel);


            superOjbedt.put("commonSaleData", mainObject);
            superOjbedt.put("betTypeData", panelData);

            double unitPrice = currentGame.getBets()[0].getUnitPrice();
            String amount = (unitPrice * Integer.parseInt(betamountselected)) + "";

            amount = (Double.parseDouble(amount) * numberspicked) + "";
            if (amount.contains(".")) {
                String[] decimalAmount = amount.split("[.]");
                amount = decimalAmount[0] + "." + decimalAmount[1].substring(0, 1);
            }
            superOjbedt.put(PurchaseLabels.totalPurchaseAmt, amount + "");

            superOjbedt.put(PurchaseLabels.noOfPanel, 1);
            superOjbedt.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            superOjbedt.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());

            Log.i("VALUES == ", superOjbedt.toString());

            TpsGamesClass.getInstance().stopLoader();
            Intent intent = new Intent();
            intent.putExtra("json", superOjbedt.toString());
            intent.putExtra("methodName", currentGame.getGameCode());
            ((Activity) context).setResult(056, intent);
            cleardata();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        TpsGamesClass.getInstance().getActivityBetSlipCamera().finish();
    }


    public void cleardata() {
        whitepixelvalues = 0;
        isqp = false;
        betslipcorrect = true;
        numberofdrawsvalue = "";
        betamountvalue = "";
        numberpickedvalue = "";
        bettypevalue = "";
        selectednumber.clear();
        previewNew.setisisImageCaptured(false);
        draws.clear();
        game1.clear();
        betamount.clear();
        bettype.clear();
        nopicked.clear();


    }

    public Bitmap createBitmapfromMat(Mat snap) {
        Bitmap bp = Bitmap.createBitmap(snap.cols(), snap.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(snap, bp);
        return bp;
    }

}
