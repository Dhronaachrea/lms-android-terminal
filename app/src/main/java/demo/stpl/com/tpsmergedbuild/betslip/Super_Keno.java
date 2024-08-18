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
import java.util.TreeMap;

import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;
import demo.stpl.com.tpsmergedbuild.utils.widgets.PurchaseLabels;

//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.GameBean;
//import demo.stpl.com.tpsmergedbuild.utils.widgets.PurchaseLabels;

//import com.example.mylibrary.Utils.PurchaseLabels;

/**
 * Created by stpl on 9/19/2016.
 */
public class Super_Keno {


    private TreeMap<Integer, String> draws = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> betamount = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> bettype = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> selectnumbers = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> quickpick = new TreeMap<Integer, String>();

    List<String> BetAmountValues = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
    List<String> BetTypeValues = new ArrayList<>(Arrays.asList("Direct1", "Direct2", "Direct3", "Direct4", "Direct5", "Direct6", "Direct7", "Direct8", "Direct9", "Direct10"));
    int whitepixelvalues, drawselected, bettypeselected;
    private Point topleft, topright, bottomleft, bottomright;
    String errorstring = "", numberselectedstring = "", betamountselected = "";
    Boolean betslipcorrect = true, isqp = false;

    Mat newimage, boundingSquare;
    Rect roi;
    PreviewNew previewNew;

    public Super_Keno(PreviewNew previewNew) {
        this.previewNew = previewNew;
    }

    Context context;


    public void Super_Keno_CalculateBets(Context context, Mat image, Point topl, Point topr, Point bottoml, Point bottomr) {
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
                getbettype(grayMat);
            }
            if (betslipcorrect) {
                getbetamount(grayMat);
            }
            if (betslipcorrect) {
                getquickpick(grayMat);
            }
            if (betslipcorrect) {
                getquickpick(grayMat);
            }
            if (betslipcorrect) {
                if (!isqp) {
                    getnumbersselected(grayMat);
                }
            }
            if (betslipcorrect) {
                getselected();
            }
        }
        catch (Exception e)
        {
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
                            Cleardata();
                        }
                    })
                    .show();
        }

        abc = createBitmapfromMat(newimage);

    }

    public void getdraws(Mat mat) {
        Log.d("VALUES", "NUMBER OF DRAWS");

        double X1 = topleft.x + 68;
        double X2 = topleft.x + 68 + 35;
        double Y1 = topleft.y + 11;
        double Y2 = topleft.y + 11 + 33;

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
    }

    public void getbettype(Mat mat) {
        Log.d("VALUES", "BET TYPE");

        double X1 = topleft.x + 68;
        double X2 = topleft.x + 68 + 35;
        double Y1 = topleft.y + 61;
        double Y2 = topleft.y + 61 + 33;

        for (int c = 0; c < 5; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, bettype);
            X1 = X1 + 35;
            X2 = X2 + 35;
        }

        X1 = topleft.x + 68;
        X2 = topleft.x + 68 + 35;
        Y1 = topleft.y + 94;
        Y2 = topleft.y + 94 + 33;

        for (int c = 5; c < 10; c++) {
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

        double X1 = topleft.x + 300;
        double X2 = topleft.x + 300 + 35;
        double Y1 = topleft.y + 61;
        double Y2 = topleft.y + 61 + 33;

        for (int c = 0; c < 5; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, betamount);
            X1 = X1 + 35;
            X2 = X2 + 35;
        }

        X1 = topleft.x + 300;
        X2 = topleft.x + 300 + 35;
        Y1 = topleft.y + 94;
        Y2 = topleft.y + 94 + 33;

        for (int c = 5; c < 10; c++) {
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

        double X1 = topleft.x + 495;
        double X2 = topleft.x + 495 + 35;
        double Y1 = topleft.y + 85;
        double Y2 = topleft.y + 85 + 33;

        getwhitepixelvalue(mat, X1, X2, Y1, Y2, 1, quickpick);

        if (Integer.parseInt(quickpick.keySet().toArray()[0] + "") < 80) {
            isqp = true;
        }

        Bitmap abc = createBitmapfromMat(newimage);
    }



    public void getnumbersselected(Mat mat) {
        Log.d("VALUES", "NOS SELECTED");

        double X1 = topleft.x + 9;
        double X2 = topleft.x + 9 + 33.2;
        double Y1 = topleft.y + 132;
        double Y2 = topleft.y + 132 + 38;

        for (int d = 0; d < 16; d++) {

            for (int c = 0; c < 5; c++) {
                getwhitepixelvalue(mat, X1, X2, Y1, Y2, ((c * 16) + d), selectnumbers);
                Y1 = Y1 + 38;
                Y2 = Y2 + 38;
            }
            X1 = X1 + 33.2;
            X2 = X2 + 33.2;
            Y1 = topleft.y + 132;
            Y2 = topleft.y + 132 + 38;
        }

        Bitmap abc = createBitmapfromMat(newimage);
    }

    public void getselected() {

        if(!isqp) {
            int total = 0;
            for (int d = 0; d < selectnumbers.size(); d++) {

                if (Integer.parseInt(selectnumbers.keySet().toArray()[d] + "") < 80) {
                    if (d == 0) {
                        numberselectedstring = (Integer.parseInt(selectnumbers.get(selectnumbers.keySet().toArray()[d])) + 1) + "";
                    } else {
                        numberselectedstring = numberselectedstring + "," + (Integer.parseInt(selectnumbers.get(selectnumbers.keySet().toArray()[d])) + 1);
                    }
                    total++;
                }
            }
            if (total != (bettypeselected + 1)) {
                betslipcorrect = false;
                errorstring = "Please Select Numbers Properly";
            }
        }
        else
        {
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            int totalCount = 0;
            while (totalCount != (bettypeselected + 1)) {
                int rnd = generate(1, 80);
                if (hashMap.get(rnd) == null) {
                    numberselectedstring = numberselectedstring + (totalCount == 0 ? "" : ",") + (rnd < 10 ? ("0" + rnd) : rnd);
                    hashMap.put(rnd, rnd);
                    totalCount++;
                }
            }
        }
    }

    public void callpurchase() {
        GameBean.Games currentGame = TpsGamesClass.getInstance().getSuperKeno();
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
            panel.put("noPicked", (bettypeselected + 1) + "");

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

            amount = (Double.parseDouble(amount) * total) + "";
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
            Cleardata();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        TpsGamesClass.getInstance().getActivityBetSlipCamera().finish();
    }

    public void getwhitepixelvalue(Mat mat, double X1, double X2, double Y1, double Y2, int position, TreeMap calculation) {
        roi = new Rect(new Point(X1, Y1), new Point(X2, Y2));
        boundingSquare = new Mat(mat, roi);
        whitepixelvalues = Core.countNonZero(boundingSquare);
        calculation.put(whitepixelvalues, position + "");
        Log.d("VALUES", whitepixelvalues + " " + position);
        Imgproc.rectangle(newimage, new Point(X1, Y1), new Point(X2, Y2), new Scalar(0, 128, 255), 1);
    }


    public Bitmap createBitmapfromMat(Mat snap) {
        Bitmap bp = Bitmap.createBitmap(snap.cols(), snap.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(snap, bp);
        return bp;
    }

    public int generate(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }


    public void Cleardata() {
        whitepixelvalues = 0;
        draws.clear();
        betamount.clear();
        bettype.clear();
        selectnumbers.clear();
        quickpick.clear();
        betslipcorrect = true;
        whitepixelvalues = 0;
        drawselected = 0;
        bettypeselected = 0;
        errorstring = "";
        numberselectedstring = "";
        betamountselected = "";
        betslipcorrect = true;
        isqp = false;
        previewNew.setisisImageCaptured(false);
    }


}

