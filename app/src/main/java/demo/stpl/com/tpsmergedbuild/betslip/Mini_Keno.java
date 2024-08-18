package demo.stpl.com.tpsmergedbuild.betslip;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import org.json.JSONArray;
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

import demo.stpl.com.tpsmergedbuild.baseClass.GameSale;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;

//importdemo.stpl.com.tpsmergedbuild.baseClass.GameSale;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.GameBean;

/**
 * Created by stpl on 9/19/2016.
 */
public class Mini_Keno {

    int whitepixelvalues,noOfPanel = 0;
    Mat newimage,boundingSquare;
    private Point topleft, topright, bottomleft, bottomright;
    Rect roi;
    Boolean isPanel1Void = false,isPanel2Void = false,isQPpanel1 = false,isQPpanel2 = false,betslipcorrect = true;
    String errorstring, Number_of_Draws = "", Bet_Amount_Panel1 = "0", Pick_Type_Panel1 = "", Select_Number_Panel1 = "", Bet_Amount_Panel2 = "0", Pick_Type_Panel2 = "", Select_Number_Panel2 = "";
    PreviewNew previewNew;
    public Mini_Keno(PreviewNew previewNew) {
        this.previewNew = previewNew;
    }
    List<String> BetAmountValues = new ArrayList<>(Arrays.asList("1", "2", "3", "5", "10"));
    List<String> PickTypeValues = new ArrayList<>(Arrays.asList("Direct12", "First12", "Last12", "AllOdd", "AllEven", "OddEven", "EvenOdd", "JumpEvenOdd", "JumpOddEven", "QP"));
    private TreeMap<Integer, String> draws = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> betamount_panel1 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> picktype_panel1 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> selectnumbers_panel1 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> void_panel1 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> betamount_panel2 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> picktype_panel2 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> selectnumbers_panel2 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> void_panel2 = new TreeMap<Integer, String>();
    Context context;


    public void Mini_Keno_CalculateBets(Context context, Mat image, Point topl, Point topr, Point bottoml, Point bottomr) {
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
        Mat awehi = grayMat;

        try {
            if (betslipcorrect) {
                isPanel1Void = getvoid(grayMat, 14, 290, 1, void_panel1);
            }
            if (betslipcorrect) {
                isPanel2Void = getvoid(grayMat, 285, 290, 2, void_panel2);
            }
            if (betslipcorrect && (!isPanel1Void || !isPanel2Void)) {
                getdraws(grayMat);
                if (!isPanel1Void) {
                    noOfPanel = 1;
                    if (betslipcorrect) {
                        Bet_Amount_Panel1 =  getbetamount(grayMat, 94, 63, 1, betamount_panel1);
                    }
                    if (betslipcorrect) {
                        Pick_Type_Panel1 = getpicktype(grayMat, 94, 104, 1, picktype_panel1);
                    }
                    if (betslipcorrect && Pick_Type_Panel1.equals("Direct12") && !isQPpanel1) {
                        Select_Number_Panel1 =  getnumbers(grayMat, 59, 184, 1, selectnumbers_panel1);
                    }
                }

                if (!isPanel2Void) {
                    noOfPanel = 2;
                    if (betslipcorrect) {
                        Bet_Amount_Panel2 = getbetamount(grayMat, 365, 63, 2, betamount_panel2);
                    }
                    if (betslipcorrect) {
                        Pick_Type_Panel2 = getpicktype(grayMat, 365, 104, 2, picktype_panel2);
                    }
                    if (betslipcorrect && Pick_Type_Panel2.equals("Direct12") && !isQPpanel2) {
                        Select_Number_Panel2 = getnumbers(grayMat, 330, 184, 2, selectnumbers_panel2);
                    }
                }
            } else {
                betslipcorrect = false;
                errorstring = "VOID SLIP";
            }
        } catch (Exception e) {
            betslipcorrect = false;
            errorstring = "Please keep the Betslip still and completely inside the rectangle";
        }

        abc = createBitmapfromMat(newimage);


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

    public boolean getvoid(Mat mat, double XC, double YC, int number, TreeMap<Integer, String> bet) {
        Log.d("VALUES", "VOID FOR PANEL " + number);

        double X1 = topleft.x + XC;
        double X2 = topleft.x + XC + 35;
        double Y1 = topleft.y + YC;
        double Y2 = topleft.y + YC + 35;

        getwhitepixelvalue(mat, X1, X2, Y1, Y2, 1, bet);

        Bitmap abc = createBitmapfromMat(newimage);

        if (Integer.parseInt(bet.keySet().toArray()[0] + "") < 80) {
            return true;
        }
        return false;
    }


    public void getdraws(Mat mat) {
        Log.d("VALUES", "NUMBER OF DRAWS");

        double X1 = topleft.x + 94;
        double X2 = topleft.x + 94 + 35;
        double Y1 = topleft.y + 16;
        double Y2 = topleft.y + 16 + 35;

        for (int c = 0; c < 5; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, draws);
            X1 = X1 + 35;
            X2 = X2 + 35;
        }

        if (Integer.parseInt(draws.keySet().toArray()[0] + "") < 80 && Integer.parseInt(draws.keySet().toArray()[1] + "") > 80) {
            Number_of_Draws = (Integer.parseInt(draws.get(draws.keySet().toArray()[0])) + 1) + "";
        } else {
            betslipcorrect = false;
            errorstring = "Please Select Number Of Draws Properly";
        }
        Bitmap abc = createBitmapfromMat(newimage);
    }


    public String getbetamount(Mat mat, double XC, double YC, int number, TreeMap<Integer, String> bet) {
        Log.d("VALUES", "BET AMOUNT " + number);
        String selection = "";
        double X1 = topleft.x + XC;
        double X2 = topleft.x + XC + 35;
        double Y1 = topleft.y + YC;
        double Y2 = topleft.y + YC + 35;

        for (int c = 0; c < 5; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, bet);
            X1 = X1 + 35;
            X2 = X2 + 35;
        }

        if (Integer.parseInt(bet.keySet().toArray()[0] + "") < 80) {
            if (Integer.parseInt(bet.keySet().toArray()[1] + "") < 80) {
                betslipcorrect = false;
                errorstring = "Please Select Bet Amount Properly For Panel " + number;
            }
            else {
                selection = BetAmountValues.get(Integer.parseInt(bet.get(bet.keySet().toArray()[0])));
            }
        }
        else
        {
            betslipcorrect = false;
            errorstring = "Please Select Bet Amount Properly For Panel " + number;
        }

        Bitmap abc = createBitmapfromMat(newimage);
        return selection;
    }

    public String getpicktype(Mat mat, double XC, double YC, int number, TreeMap<Integer, String> bet) {
        Log.d("VALUES", "PICK TYPE " + number);
        String selection = "";
        String  numberselection = "";
        double X1 = topleft.x + XC;
        double X2 = topleft.x + XC + 35;
        double Y1 = topleft.y + YC;
        double Y2 = topleft.y + YC + 34;
        int c = 0;
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 5; x++) {
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

        if (Integer.parseInt(bet.keySet().toArray()[0] + "") < 80) {
            if (Integer.parseInt(bet.keySet().toArray()[1] + "") < 80) {
                betslipcorrect = false;
                errorstring = "Please Select Pick Type Properly For Panel " + number;
            }
            else {
            selection = PickTypeValues.get(Integer.parseInt(bet.get(bet.keySet().toArray()[0])));

            if (selection.equals("First12")) {
                numberselection = "1,2,3,4,5,6,7,8,9,10,11,12";
            } else if (selection.equals("Last12")) {
                numberselection = "13,14,15,16,17,18,19,20,21,22,23,24";
            } else if (selection.equals("AllOdd")) {
                numberselection = "1,3,5,7,9,11,13,15,17,19,21,23";
            } else if (selection.equals("AllEven")) {
                numberselection = "2,4,6,8,10,12,14,16,18,20,22,24";
            } else if (selection.equals("OddEven")) {
                numberselection = "1,3,5,7,9,11,14,16,18,20,22,24";
            } else if (selection.equals("EvenOdd")) {
                numberselection = "2,4,6,8,12,13,15,17,19,21,23";
            } else if (selection.equals("JumpEvenOdd")) {
                numberselection = "3,4,7,8,11,12,15,16,19,20,23,24";
            } else if (selection.equals("JumpOddEven")) {
                numberselection = "1,2,5,6,9,10,13,14,17,18,21,22";
            } else if (selection.equals("QP")) {
                selection = "Direct12";
                HashMap<Integer, Integer> hashMap = new HashMap<>();
                if (number == 1) {
                    isQPpanel1 = true;
                } else {
                    isQPpanel2 = true;
                }
                int totalCount = 0;
                while (totalCount != 12) {
                    int rnd = generate(1, 24);
                    if (hashMap.get(rnd) == null) {
                        numberselection = numberselection + (totalCount == 0 ? "" : ",") + (rnd < 10 ? ("0" + rnd) : rnd);
                        hashMap.put(rnd, rnd);
                        totalCount++;
                    }
                }
            }
            if (number == 1) {
                Select_Number_Panel1 = numberselection;
            } else {
                Select_Number_Panel2 = numberselection;
            }
        }
        }
        else {

            betslipcorrect = false;
            errorstring = "Please Select Pick Type Properly For Panel " + number;
        }

        Bitmap abc = createBitmapfromMat(newimage);
        return selection;
    }

    public String getnumbers(Mat mat, double XC, double YC, int number, TreeMap<Integer, String> bet) {
        Log.d("VALUES", "BET " + number);
        String selection = "";
        double X1 = topleft.x + XC;
        double X2 = topleft.x + XC + 35;
        double Y1 = topleft.y + YC;
        double Y2 = topleft.y + YC + 34;
        int c = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 6; x++) {
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

        int total = 0;
        for (int d = 0; d < bet.size(); d++) {

            if (Integer.parseInt(bet.keySet().toArray()[d] + "") < 80) {
                if (d == 0) {
                    selection = (Integer.parseInt(bet.get(bet.keySet().toArray()[d])) + 1) + "";
                } else {
                    selection = selection + "," + (Integer.parseInt(bet.get(bet.keySet().toArray()[d])) + 1);
                }
                total++;
            }
        }
        if (total != 12) {
            betslipcorrect = false;
            errorstring = "Please Select Numbers Properly";
        }

        Bitmap abc = createBitmapfromMat(newimage);
        return selection;
    }


    public void getwhitepixelvalue(Mat mat, double X1, double X2, double Y1, double Y2, int position, TreeMap calculation) {
        roi = new Rect(new Point(X1, Y1), new Point(X2, Y2));
        boundingSquare = new Mat(mat, roi);
        whitepixelvalues = Core.countNonZero(boundingSquare);
        while (calculation.containsKey(whitepixelvalues)) {
            whitepixelvalues++;
        }
        calculation.put(whitepixelvalues, position + "");
        Log.d("VALUES", whitepixelvalues + " " + position);
        Imgproc.rectangle(newimage, new Point(X1, Y1), new Point(X2, Y2), new Scalar(0, 128, 255), 1);
    }


    public int generate(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public void CreateJason() {

        GameBean.Games powerPlay = null;
        GameBean gameBean = TpsGamesClass.getInstance().getGameBean();
        for (GameBean.Games games : gameBean.getGames()) {
            if (games.getGameCode().equals("TwelveByTwentyFour")) {
                powerPlay = games;
                break;
            }
        }

        int noOfDraws = Integer.parseInt(Number_of_Draws);
        String[] totalDrawSelected = new String[noOfDraws];

        JSONObject commonSaleJson = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        try {
            if (noOfDraws > powerPlay.getDraws().length) {
                noOfDraws = powerPlay.getDraws().length;
            }
            jsonObject.put("isAdvancePlay", false);
            jsonObject.put("noOfDraws", noOfDraws);

            jsonObject.put("isDrawManual", true);
            JSONArray drawData = new JSONArray();
            if (powerPlay != null) {

                for (GameBean.Games.Draws draws : powerPlay.getDraws()) {

                    JSONObject jsonObject1 = new JSONObject();
                    if (noOfDraws < 0) {
                        break;
                    }
                    noOfDraws = noOfDraws - 1;

                    jsonObject1.put("drawId", draws.getDrawId() + "");
                    drawData.put(jsonObject1);
                    totalDrawSelected[noOfDraws] = String.valueOf(draws.getDrawId());

                }
            }
            jsonObject.put("drawData", drawData);
            jsonObject.put("gameName", powerPlay.getGameCode());
            commonSaleJson.put("commonSaleData", jsonObject);


            GameSale.MainData.BetTypeData[] betTypeData = new GameSale.MainData.BetTypeData[noOfPanel];
            JSONArray betTypeArray = new JSONArray();

            if (noOfPanel > 0) {
                JSONObject betTypeDataJson = new JSONObject();
                betTypeDataJson.put("noPicked", "12");
                betTypeDataJson.put("betAmtMul", 1);
                betTypeDataJson.put("isQp", isQPpanel1);
                betTypeDataJson.put("pickedNumbers", Select_Number_Panel1);
                betTypeDataJson.put("betName", Pick_Type_Panel1);
                betTypeDataJson.put("QPPreGenerated", isQPpanel1);
                betTypeArray.put(betTypeDataJson);
            }
            if (noOfPanel > 1) {
                JSONObject betTypeDataJson = new JSONObject();
                betTypeDataJson.put("noPicked", "12");
                betTypeDataJson.put("betAmtMul", 1);
                betTypeDataJson.put("isQp", isQPpanel2);
                betTypeDataJson.put("pickedNumbers", Select_Number_Panel2);
                betTypeDataJson.put("betName", Pick_Type_Panel2);
                betTypeDataJson.put("QPPreGenerated", isQPpanel2);
                betTypeArray.put(betTypeDataJson);
            }
            double unitprice = powerPlay.getBets()[0].getUnitPrice();
            commonSaleJson.put("betTypeData", betTypeArray);
            commonSaleJson.put("noOfPanel", noOfPanel);
            commonSaleJson.put("totalPurchaseAmt", (Integer.parseInt(Bet_Amount_Panel1) * unitprice) + (Integer.parseInt(Bet_Amount_Panel2) * unitprice) + "");
            commonSaleJson.put("userName", TpsGamesClass.getInstance().getLoginResponse().getData().getRetailerName());
            commonSaleJson.put("sessionId", TpsGamesClass.getInstance().getLoginResponse().getData().getSessionId());

            System.out.println("request-> "
                    + commonSaleJson.toString());
            TpsGamesClass.getInstance().stopLoader();
            Intent intent = new Intent();
            intent.putExtra("json", commonSaleJson.toString());
            intent.putExtra("methodName", powerPlay.getGameCode());
            ((Activity) context).setResult(056, intent);
//            ServerCommClass.getServerCommClass().getServerRequest().request(commonSaleJson.toString(), );
            Cleardata();

        } catch (Exception e) {

            e.getLocalizedMessage();
        }
        TpsGamesClass.getInstance().getActivityBetSlipCamera().finish();
//        Cleardata();

//        String response = "noofdraws:"+Number_of_Draws+"noofpanels"+"2"+"betamount1:"+Bet_Amount_Panel1+"picktype1"++""

    }

    public Bitmap createBitmapfromMat(Mat snap) {
        Bitmap bp = Bitmap.createBitmap(snap.cols(), snap.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(snap, bp);
        return bp;
    }


    public void Cleardata() {
        whitepixelvalues = 0;
        noOfPanel = 0;
        errorstring = "";
        isPanel2Void = false;
        isPanel1Void = false;
        isQPpanel2 = false;
        isQPpanel1 = false;
        betslipcorrect = true;
        Number_of_Draws = "";
        Bet_Amount_Panel1 = "0";
        Pick_Type_Panel1 = "";
        Select_Number_Panel1 = "";
        Bet_Amount_Panel2 = "0";
        Pick_Type_Panel2 = "";
        Select_Number_Panel2 = "";
        previewNew.setisisImageCaptured(false);
        draws.clear();
        betamount_panel1.clear();
        picktype_panel1.clear();
        selectnumbers_panel1.clear();
        void_panel1.clear();
        betamount_panel2.clear();
        picktype_panel2.clear();
        selectnumbers_panel2.clear();
        void_panel2.clear();
    }

}

