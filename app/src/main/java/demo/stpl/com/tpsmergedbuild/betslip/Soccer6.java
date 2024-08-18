package demo.stpl.com.tpsmergedbuild.betslip;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.SportsBean;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryData;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryGame;
import demo.stpl.com.tpsmergedbuild.utils.DrawDataCreator;
import demo.stpl.com.tpsmergedbuild.utils.RequestdataCreator;
import demo.stpl.com.tpsmergedbuild.utils.widgets.SoccerPhysical;

//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.SportsBean;
//import tpsgames.beans.SportsLotteryData;
//import tpsgames.beans.SportsLotteryGame;
//import demo.stpl.com.tpsmergedbuild.utils.DrawDataCreator;
//import demo.stpl.com.tpsmergedbuild.utils.RequestdataCreator;
//import demo.stpl.com.tpsmergedbuild.utils.widgets.SoccerPhysical;

//import com.sparkzeal.sportslottery.beans.SportsBean;
//import com.sparkzeal.sportslottery.beans.SportsLotteryData;
//import com.sparkzeal.sportslottery.beans.SportsLotteryGame;
//import com.sparkzeal.sportslottery.utils.DrawDataCreator;
//import com.sparkzeal.sportslottery.utils.RequestdataCreator;
//import com.tablet.stpl.comman.interfaces.ServerCommClass;


public class Soccer6 {

    Mat newimage;
    private Point topleft, topright, bottomleft, bottomright;
    Context context;
    Rect roi;
    Mat boundingSquare;
    int whitepixelvalues;
    Boolean betslipcorrect = true;
    String errorstring;
    PreviewNew previewNew;
    String selectedbetamount = "";
    String selectedgame1 = "";
    String selectedgame2 = "";
    String selectedgame3 = "";
    String selectedgame4 = "";
    String selectedgame5 = "";
    String selectedgame6 = "";
    private SportsLotteryGame soccer6Game;
    private SportsLotteryData mainData;

    private ArrayList<TreeMap> selection = new ArrayList<TreeMap>();

    private ArrayList<String> selectedgame = new ArrayList<String>();

    List<String> betamountvalues = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "10", "20"));
    List<String> gamevalues = new ArrayList<>(Arrays.asList("H+", "H", "D", "A", "A+"));

    private TreeMap<Integer, String> betamount = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> game1 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> game2 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> game3 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> game4 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> game5 = new TreeMap<Integer, String>();
    private TreeMap<Integer, String> game6 = new TreeMap<Integer, String>();
    private boolean isDataAvailab = true;

    public Soccer6(PreviewNew previewNew, String json) {
        this.previewNew = previewNew;
        mainData = DrawDataCreator.getSportsLottery(new Gson().fromJson(json, SportsBean.class));
        if (mainData == null) {
            TpsGamesClass.getInstance().showAToast("No Data Available!", context, Toast.LENGTH_SHORT);
            return;
        }
        soccer6Game = SoccerPhysical.getSportsGame(mainData, "soccer6");
        if (soccer6Game == null) {
            isDataAvailab = false;
            TpsGamesClass.getInstance().showAToast("No Data Available for Soccer6!", TpsGamesClass.getInstance().getActivityDgeGames(), Toast.LENGTH_SHORT);
            Cleardata();
            return;
        }

    }

    public void Soccer_6_CalculateBets(Context context, Mat image, Point topl, Point topr, Point bottoml, Point bottomr) {
        if (!isDataAvailab) {
            return;
        }
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
        Mat m = newimage;
        final Mat grayMat = new Mat(m.size(), CvType.CV_8U);  // Convert to graymat
        Imgproc.cvtColor(m, grayMat, Imgproc.COLOR_BGRA2GRAY);
        Imgproc.adaptiveThreshold(grayMat, grayMat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 15, 40);
        Mat awehi = grayMat;

        try {
            if (betslipcorrect) {
                getbetamount(grayMat);
            }
            if (betslipcorrect) {
                soccer6Game.setBetAmountMul(Integer.parseInt(selectedbetamount));
                getgame(grayMat, 52, 107, 1, game1);
            }
            if (betslipcorrect) {
                getgame(grayMat, 320, 107, 2, game2);
            }
            if (betslipcorrect) {
                getgame(grayMat, 52, 194, 3, game3);
            }
            if (betslipcorrect) {
                getgame(grayMat, 320, 194, 4, game4);
            }
            if (betslipcorrect) {
                getgame(grayMat, 52, 280, 5, game5);
            }
            if (betslipcorrect) {
                getgame(grayMat, 320, 280, 6, game6);
            }
        } catch (Exception e) {
            betslipcorrect = false;
            errorstring = "Please keep the Betslip still and completely inside the rectangle";
        }
        Bitmap abc = createBitmapfromMat(newimage);

        if (betslipcorrect) {
            createjason();
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

    public void getselectedvalues() {
        try {
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


            Point SM4 = new Point(p2.x, p2.y);
            Point SM3 = new Point(p1.x, p1.y);

            Bitmap abc = createBitmapfromMat(newimage);

            Mat m = newimage;
            final Mat grayMat = new Mat(m.size(), CvType.CV_8U);  // Convert to graymat
            Imgproc.cvtColor(m, grayMat, Imgproc.COLOR_BGRA2GRAY);
            Imgproc.adaptiveThreshold(grayMat, grayMat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 15, 40);
            Mat awehi = grayMat;

            Imgproc.rectangle(newimage, new Point(SM4.x + 45, SM4.y + 10), new Point(SM4.x + (45 + 35), SM4.y + 45), new Scalar(0, 128, 255), 1);

            Log.d("VALUES", "Bet Amount");
            int i = 45;
            for (int c = 0; c < 7; c++) {

                Imgproc.rectangle(grayMat, new Point(SM4.x + i, SM4.y + 10), new Point(SM4.x + (i + 35), SM4.y + 45), new Scalar(0, 128, 255), 1);
                roi = new Rect(new Point(SM4.x + i, SM4.y + 10), new Point(SM4.x + (i + 35), SM4.y + 45));
                boundingSquare = new Mat(awehi, roi);
                whitepixelvalues = Core.countNonZero(boundingSquare);
                betamount.put(whitepixelvalues, c + "");
                Log.d("VALUES", whitepixelvalues + " " + c);
                Imgproc.rectangle(newimage, new Point(SM4.x + i, SM4.y + 10), new Point(SM4.x + (i + 35), SM4.y + 45), new Scalar(0, 128, 255), 1);
                abc = createBitmapfromMat(newimage);
                i = i + 35;
            }


            Log.d("VALUES", "GAME1");
            i = 50;
            for (int c = 0; c < 5; c++) {

                Imgproc.rectangle(grayMat, new Point(SM4.x + i, SM4.y + 107), new Point(SM4.x + (i + 35), SM4.y + 142), new Scalar(0, 128, 255), 1);
                roi = new Rect(new Point(SM4.x + i, SM4.y + 107), new Point(SM4.x + (i + 35), SM4.y + 142));
                boundingSquare = new Mat(awehi, roi);
                whitepixelvalues = Core.countNonZero(boundingSquare);
                game1.put(whitepixelvalues, c + "");
                Log.d("VALUES", whitepixelvalues + " " + c);
                Imgproc.rectangle(newimage, new Point(SM4.x + i, SM4.y + 107), new Point(SM4.x + (i + 35), SM4.y + 142), new Scalar(0, 128, 255), 1);
                abc = createBitmapfromMat(newimage);
                i = i + 44;
            }

            Log.d("VALUES", "GAME3");
            i = 50;
            for (int c = 0; c < 5; c++) {

                Imgproc.rectangle(grayMat, new Point(SM4.x + i, SM4.y + 194), new Point(SM4.x + (i + 35), SM4.y + 232), new Scalar(0, 128, 255), 1);
                roi = new Rect(new Point(SM4.x + i, SM4.y + 194), new Point(SM4.x + (i + 35), SM4.y + 232));
                boundingSquare = new Mat(awehi, roi);
                whitepixelvalues = Core.countNonZero(boundingSquare);
                game3.put(whitepixelvalues, c + "");
                Log.d("VALUES", whitepixelvalues + " " + c);
                Imgproc.rectangle(newimage, new Point(SM4.x + i, SM4.y + 194), new Point(SM4.x + (i + 35), SM4.y + 232), new Scalar(0, 128, 255), 1);
                abc = createBitmapfromMat(newimage);
                i = i + 44;
            }

            Log.d("VALUES", "GAME5");
            i = 50;
            for (int c = 0; c < 5; c++) {

                Imgproc.rectangle(grayMat, new Point(SM4.x + i, SM4.y + 280), new Point(SM4.x + (i + 35), SM4.y + 315), new Scalar(0, 128, 255), 1);
                roi = new Rect(new Point(SM4.x + i, SM4.y + 280), new Point(SM4.x + (i + 35), SM4.y + 315));
                boundingSquare = new Mat(awehi, roi);
                whitepixelvalues = Core.countNonZero(boundingSquare);
                game5.put(whitepixelvalues, c + "");
                Log.d("VALUES", whitepixelvalues + " " + c);
                Imgproc.rectangle(newimage, new Point(SM4.x + i, SM4.y + 280), new Point(SM4.x + (i + 35), SM4.y + 315), new Scalar(0, 128, 255), 1);
                abc = createBitmapfromMat(newimage);
                i = i + 44;
            }

            Log.d("VALUES", "GAME2");
            i = 322;
            for (int c = 0; c < 5; c++) {

                Imgproc.rectangle(grayMat, new Point(SM4.x + i, SM4.y + 107), new Point(SM4.x + (i + 35), SM4.y + 142), new Scalar(0, 128, 255), 1);
                roi = new Rect(new Point(SM4.x + i, SM4.y + 107), new Point(SM4.x + (i + 35), SM4.y + 142));
                boundingSquare = new Mat(awehi, roi);
                whitepixelvalues = Core.countNonZero(boundingSquare);
                game2.put(whitepixelvalues, c + "");
                Log.d("VALUES", whitepixelvalues + " " + c);
                Imgproc.rectangle(newimage, new Point(SM4.x + i, SM4.y + 107), new Point(SM4.x + (i + 35), SM4.y + 142), new Scalar(0, 128, 255), 1);
                abc = createBitmapfromMat(newimage);
                i = i + 44;
            }


            Log.d("VALUES", "GAME4");
            i = 322;
            for (int c = 0; c < 5; c++) {

                Imgproc.rectangle(grayMat, new Point(SM4.x + i, SM4.y + 194), new Point(SM4.x + (i + 35), SM4.y + 232), new Scalar(0, 128, 255), 1);
                roi = new Rect(new Point(SM4.x + i, SM4.y + 194), new Point(SM4.x + (i + 35), SM4.y + 232));
                boundingSquare = new Mat(awehi, roi);
                whitepixelvalues = Core.countNonZero(boundingSquare);
                game4.put(whitepixelvalues, c + "");
                Log.d("VALUES", whitepixelvalues + " " + c);
                Imgproc.rectangle(newimage, new Point(SM4.x + i, SM4.y + 194), new Point(SM4.x + (i + 35), SM4.y + 232), new Scalar(0, 128, 255), 1);
                abc = createBitmapfromMat(newimage);
                i = i + 44;
            }

            Log.d("VALUES", "GAME6");
            i = 322;
            for (int c = 0; c < 5; c++) {

                Imgproc.rectangle(grayMat, new Point(SM4.x + i, SM4.y + 280), new Point(SM4.x + (i + 35), SM4.y + 315), new Scalar(0, 128, 255), 1);
                roi = new Rect(new Point(SM4.x + i, SM4.y + 280), new Point(SM4.x + (i + 35), SM4.y + 315));
                boundingSquare = new Mat(awehi, roi);
                whitepixelvalues = Core.countNonZero(boundingSquare);
                game6.put(whitepixelvalues, c + "");
                Log.d("VALUES", whitepixelvalues + " " + c);
                Imgproc.rectangle(newimage, new Point(SM4.x + i, SM4.y + 280), new Point(SM4.x + (i + 35), SM4.y + 315), new Scalar(0, 128, 255), 1);
                abc = createBitmapfromMat(newimage);
                i = i + 44;
            }

            Bitmap abcd = createBitmapfromMat(newimage);
        } catch (Exception e) {
            betslipcorrect = false;
            errorstring = "Please keep the betslip still and completely inside the rectangle";
        }

    }

    public void getbetamount(Mat mat) {
        Log.d("VALUES", "BET AMOUNT");

        double X1 = topleft.x + 45;
        double X2 = topleft.x + 45 + 35;
        double Y1 = topleft.y + 10;
        double Y2 = topleft.y + 10 + 35;

        for (int c = 0; c < 7; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, betamount);
            X1 = X1 + 35;
            X2 = X2 + 35;
        }

        if (Integer.parseInt(betamount.keySet().toArray()[0] + "") < 80 && Integer.parseInt(betamount.keySet().toArray()[1] + "") > 80) {
            selectedbetamount = betamountvalues.get((Integer.parseInt(betamount.get(betamount.keySet().toArray()[0])))) + "";
        } else {
            betslipcorrect = false;
            errorstring = "Please Select Bet Amount Properly";
        }
        Bitmap abc = createBitmapfromMat(newimage);
    }

    public String getgame(Mat mat, double XC, double YC, int number, TreeMap<Integer, String> bet) {
        Log.d("VALUES", "GAME " + number);
        String selection = "";
        double X1 = topleft.x + XC;
        double X2 = topleft.x + XC + 36;
        double Y1 = topleft.y + YC;
        double Y2 = topleft.y + YC + 35;

        for (int c = 0; c < 5; c++) {
            getwhitepixelvalue(mat, X1, X2, Y1, Y2, c, bet);
            X1 = X1 + 43;
            X2 = X1 + 36;
        }

        if (Integer.parseInt(bet.keySet().toArray()[0] + "") < 80) {
                for (int x = 0; x < bet.size(); x++) {
                    if (Integer.parseInt(bet.keySet().toArray()[x] + "") < 80) {
                        setEvent(gamevalues.get(Integer.parseInt(bet.get(bet.keySet().toArray()[x]))), soccer6Game.getGameList().get(number - 1));
                    }
                }
        } else {
            betslipcorrect = false;
            errorstring = "Please Select Bet Properly For Game " + number;
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

    private void setEvent(String selections, SportsLotteryGame.EventData eventData) {
        switch (selections) {
            case "H+":
                eventData.setIsHomeExtra(true);
                break;
            case "H":
                eventData.setIsHome(true);
                break;
            case "D":
                eventData.setIsDraw(true);
                break;
            case "A":
                eventData.setIsAway(true);
                break;
            case "A+":
                eventData.setIsAwayExtra(true);
                break;

        }
    }


    public void calculateresult() {
        try {
            selection.add(game1);
            selection.add(game2);
            selection.add(game3);
            selection.add(game4);
            selection.add(game5);
            selection.add(game6);

            if (Integer.parseInt(betamount.keySet().toArray()[0] + "") < 120 && Integer.parseInt(betamount.keySet().toArray()[1] + "") > 120) {
                selectedbetamount = betamountvalues.get(Integer.parseInt(betamount.get(betamount.keySet().toArray()[0])));
            } else {
                betslipcorrect = false;
                errorstring = "Please select bet amount properly";
            }

            for (int f = 0; f < selection.size(); f++) {
                TreeMap<Integer, String> game = selection.get(f);
                for (int x = 0; x < game.size(); x++) {
                    if (Integer.parseInt(game.keySet().toArray()[x] + "") < 120) {
                        setEvent(gamevalues.get(Integer.parseInt(game.get(game.keySet().toArray()[x]))), soccer6Game.getGameList().get(f));
                    }
                }
            }


            for (int y = 0; y < selectedgame.size(); y++) {
                if (selectedgame.get(y).equals("")) {
                    betslipcorrect = false;
                    errorstring = "Please select all games properly";
                }
            }
        } catch (Exception e) {
            betslipcorrect = false;
            errorstring = "Please keep the betslip still and completely inside the rectangle";
        }


    }

    public void createjason() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        mainData.setTicketAmt(Float.parseFloat(selectedbetamount) + "");
        mainData.setUserName("username"); // change
        mainData.setDrawCount("1");
        mainData.setMerCode("RMS"); //change
        mainData.setLstktNo("0"); //change
        mainData.setSessId(""); //change
        mainData.setSlLstTxnId(""); // change
        mainData.setCID("48581"); //change
        mainData.setLAC("617"); // change
        mainData.setSimType("ECONET"); //change
        mainData.setDeviceType("TPS580"); //change
        mainData.setReqCounter("31"); //change
        mainData.setRespCounter("31"); //change
        mainData.setTime(currentDateandTime.split(" ")[1]);
        mainData.setTime(currentDateandTime.split(" ")[0]);
        mainData.setRandom(((int) Math.random() * 900) + "");

        String saleRequest = RequestdataCreator.getPurchaseString(mainData, SoccerPhysical.getPosition());


        Intent intent = new Intent();
        intent.putExtra("json", saleRequest);
        intent.putExtra("methodName", "SLE_SALE");
        ((Activity) context).setResult(056, intent);
//        ServerCommClass.getServerCommClass().getServerRequest().request(saleRequest, "saleRequest");
        Cleardata();
        TpsGamesClass.getInstance().getActivityBetSlipCamera().finish();
    }

    public void Cleardata() {
        whitepixelvalues = 0;
        betslipcorrect = true;
        selectedbetamount = "";
        errorstring = "";
        selectedbetamount = "";
        selectedgame1 = "";
        selectedgame2 = "";
        selectedgame3 = "";
        selectedgame4 = "";
        selectedgame5 = "";
        selectedgame6 = "";
        previewNew.setisisImageCaptured(false);
        betamount.clear();
        game1.clear();
        game2.clear();
        game3.clear();
        game4.clear();
        game5.clear();
        game6.clear();
    }


    public Bitmap createBitmapfromMat(Mat snap) {
        Bitmap bp = Bitmap.createBitmap(snap.cols(), snap.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(snap, bp);
        return bp;
    }


    /*         for(int x = 0; x < game1.size(); x++)
            {
                if(x > 0) {
                    if (Integer.parseInt(game1.keySet().toArray()[x] + "") < 120) {
                        selectedgame1 = selectedgame1 +","+ gamevalues.get(Integer.parseInt(game1.get(game1.keySet().toArray()[x])));
                    }
                }
                else
                {
                    if (Integer.parseInt(game1.keySet().toArray()[x] + "") < 120) {
                        selectedgame1 = "" + gamevalues.get(Integer.parseInt(game1.get(game1.keySet().toArray()[x])));
                    }
                }
            }

            for(int x = 0; x < game2.size(); x++)
            {
                if(x > 0) {
                    if (Integer.parseInt(game2.keySet().toArray()[x] + "") < 120) {
                        selectedgame2 = selectedgame2 +","+ gamevalues.get(Integer.parseInt(game2.get(game2.keySet().toArray()[x])));
                    }
                }
                else
                {
                    if (Integer.parseInt(game2.keySet().toArray()[x] + "") < 120) {
                        selectedgame2 = "" + gamevalues.get(Integer.parseInt(game2.get(game2.keySet().toArray()[x])));
                    }
                }
            }

            for(int x = 0; x < game3.size(); x++)
            {
                if(x > 0) {
                    if (Integer.parseInt(game3.keySet().toArray()[x] + "") < 120) {
                        selectedgame3 = selectedgame3 +","+ gamevalues.get(Integer.parseInt(game3.get(game3.keySet().toArray()[x])));
                    }
                }
                else
                {
                    if (Integer.parseInt(game3.keySet().toArray()[x] + "") < 120) {
                        selectedgame3 = "" + gamevalues.get(Integer.parseInt(game3.get(game3.keySet().toArray()[x])));
                    }
                }
            }

            for(int x = 0; x < game4.size(); x++)
            {
                if(x > 0) {
                    if (Integer.parseInt(game4.keySet().toArray()[x] + "") < 120) {
                        selectedgame4 = selectedgame4 +","+ gamevalues.get(Integer.parseInt(game4.get(game4.keySet().toArray()[x])));
                    }
                }
                else
                {
                    if (Integer.parseInt(game4.keySet().toArray()[x] + "") < 120) {
                        selectedgame4 = "" + gamevalues.get(Integer.parseInt(game4.get(game4.keySet().toArray()[x])));
                    }
                }
            }

            for(int x = 0; x < game5.size(); x++)
            {
                if(x > 0) {
                    if (Integer.parseInt(game5.keySet().toArray()[x] + "") < 120) {
                        selectedgame5 = selectedgame5 +","+ gamevalues.get(Integer.parseInt(game5.get(game5.keySet().toArray()[x])));
                    }
                }
                else
                {
                    if (Integer.parseInt(game5.keySet().toArray()[x] + "") < 120) {
                        selectedgame5 = "" + gamevalues.get(Integer.parseInt(game5.get(game5.keySet().toArray()[x])));
                    }
                }
            }

            for(int x = 0; x < game6.size(); x++)
            {
                if(x > 0) {
                    if (Integer.parseInt(game6.keySet().toArray()[x] + "") < 120) {
                        selectedgame6 = selectedgame6 +","+ gamevalues.get(Integer.parseInt(game6.get(game6.keySet().toArray()[x])));
                    }
                }
                else
                {
                    if (Integer.parseInt(game6.keySet().toArray()[x] + "") < 120) {
                        selectedgame6 = "" + gamevalues.get(Integer.parseInt(game6.get(game6.keySet().toArray()[x])));
                    }
                }
            }

            if(selectedgame1.equals("") || selectedgame2.equals("") || selectedgame3.equals("") || selectedgame4.equals("") || selectedgame5.equals("") || selectedgame6.equals("")){
                betslipcorrect = false;
                errorstring = "Please select all games properly";
            }

        }
        else{
            betslipcorrect = false;
            errorstring = "Please select bet amount properly";
        }

*/
}
