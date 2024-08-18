package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.epson.epos2.Epos2Exception;
import com.epson.epos2.printer.Printer;
import com.epson.epos2.printer.PrinterStatusInfo;
import com.epson.epos2.printer.ReceiveListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.telpo.tps550.api.InternalErrorException;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryTktBean;

//import skilrock.com.tpsgame.R;
//import tpsgames.Utility;
//importdemo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.SportsLotteryTktBean;

/**
 * Created by stpl on 26-Oct-16.
 */
public class PrintActivitySportsExternal extends Activity implements View.OnClickListener, ReceiveListener {

    String saleResponse, responseString;
    private boolean isReponsePrint = false;
    String printerName = "";
    String[] sunSin = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_print_activity);
        saleResponse = getIntent().getStringExtra("response");
        responseString = saleResponse;
//        if (saleResponse != null) {
//            printReceipt();
//        }

        printerName = TpsGamesClass.getInstance().getPrinterNameExternal();
        if (printerName.trim().length() == 0) {
            Intent intent = new Intent(this, ActivityDiscoveryExternalPrinter.class);
            startActivityForResult(intent, 0);
        } else {
            runPrintReceiptSequence();
        }

//        TpsGamesClass.getInstance().closeScreen(this);
//
//        TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
//        String url = "/sdcard/rainbow_header.png";
//        TpsGamesClass.getInstance().pleaseWait("Printing.....", url);
//        TpsGamesClass.getInstance().displayScreen(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && resultCode == RESULT_OK) {
            printerName = data.getStringExtra("Target");
            TpsGamesClass.getInstance().setPrinterNameExternal(printerName);
            runPrintReceiptSequence();

        }
    }

    @Override
    public void onBackPressed() {

        if (isReponsePrint) {

            super.onBackPressed();
        } else {
            return;
        }
    }


    private boolean printTicket(final SportsLotteryTktBean tktBean) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                int serialNo = 1;

                try {
                    if (true) {
                        mPrinter.addFeedLine(1);
                        mPrinter.addTextSize(2, 2);
                        mPrinter.addTextSize(1, 2);
                        mPrinter.addTextFont(2);

                        PrintClass.printTitleAtTop("SKILROCK", mPrinter);
                        mPrinter.addTextSize(1, 2);
                        PrintClass.printTitleSub(tktBean.getGameName(), mPrinter);

                        PrintClass.printTwoString(tktBean.getCurrDate(), tktBean.getCurrTime(), mPrinter);
//                        ThermalPrinter.addString("\n\n");

                        TpsGamesClass.getInstance().setStringPreferences(Utility.LAST_TXT_NUMBER_SPORTS, tktBean.getTrxId());
                        TpsGamesClass.getInstance().setStringPreferences(Utility.LAST_TICKET_NUMBER_SPORTS, tktBean.getTicketNo());
                        PrintClass.printTwoString("TicNo", tktBean.getTicketNo(), mPrinter);
//                        ThermalPrinter.addString("\n\n");
                        PrintClass.printLine(mPrinter);
//                        ThermalPrinter.addString("\n\n");

                        PrintClass.printTwoString("Draw Date", "Draw Time", mPrinter);
                        PrintClass.printTwoString(tktBean.getDrawDate(), tktBean.getDrawTime(), mPrinter);
                        TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(Double.parseDouble(tktBean.getBalance()));
                        mPrinter.addText("\n------------------------------------------\n");
//                        ThermalPrinter.addString("\n\n");
//                        ThermalPrinter.printString();

                        mPrinter.addTextFont(2);
                        mPrinter.addTextSize(1, 2);
                        mPrinter.addTextAlign(Printer.ALIGN_CENTER);
                        mPrinter.addText(tktBean.getDrawName());
                        mPrinter.addTextFont(2);
                        mPrinter.addTextSize(1, 2);
                        mPrinter.addText("\n------------------------------------------\n");
//                        ThermalPrinter.setFontSize(1);
//                        ThermalPrinter.enlargeFontSize(1, 1);
//                        ThermalPrinter.addString("--------------------------------\n");
//                        ThermalPrinter.walkPaper(10);
//                        ThermalPrinter.printString();
//                        PrintClass.printTwoString(tktBean.getDrawDate(), tktBean.getDrawTime());
//                        ThermalPrinter.addString("\n\n");
//
//                        ThermalPrinter.addString("\n\n");
//                        ThermalPrinter.addString("\n\n");
//                        ThermalPrinter.addString("\n\n");
//                        ThermalPrinter.printString();
                        int lenghtOfMatch;
                        mPrinter.addTextAlign(Printer.ALIGN_CENTER);
                        mPrinter.addTextSize(1, 2);
                        if (tktBean.getGameTypeName().equalsIgnoreCase("Soccer 4") || tktBean.getGameTypeName().equalsIgnoreCase("Soccer 6")) {
                            PrintClass.printTwoString("", "H+" + " " + "H" + " " + "D" + " " + "A" + " " + "A+ ", mPrinter);
                            lenghtOfMatch = 5;
                        } else {
                            PrintClass.printTwoString("", "H" + " " + "D" + " " + "A   ", mPrinter);
                            lenghtOfMatch = 3;
                        }

                        mPrinter.addText("\n");
                        for (int i = 0; i < tktBean.getDrawInfo().length; i++) {

                            mPrinter.addTextSize(1, 1);
                            String match = tktBean.getDrawInfo()[i].split("@")[0];
                            String winning = tktBean.getDrawInfo()[i].split("@")[1];
                            if (winning.contains(":")) {
                                winning = winning.substring(0, winning.indexOf(":"));
                            }
                            boolean a = false, ap = false, h = false, hp = false, d = false;
                            String[] winnings = null;

                            if (winning.contains(",")) {
                                winnings = winning.split("[,]");
                            }


//                            String matchList = match.contains("~") ? match.split("~")[1] : match;
                            String str;
                            str = new String();
//                            for (int j = 0; j < lenghtOfMatch; j++) {
//                                str = str + "|x";
//                                ThermalPrinter.setLineSpace(2);
////                                switch (winning.split(",")[i].toString()) {
////                                    case "H+":
////                                        str = str + "|*";
////                                        break;
////                                    case "H":
////                                        str = str + "|*";
////                                        break;
////                                    case "D":
////                                        str = str + "|*";
////                                        break;
////                                    case "A":
////                                        str = str + "|*";
////                                        break;
////                                    case "A+":
////                                        str = str + "|*";
////                                        break;
////                                    default:
////                                        str = str + "| ";
////                                }
//                            }


                            if (lenghtOfMatch == 5) {

                                if (winnings != null) {
                                    for (String win : winnings) {
                                        win = win.replaceAll(":", "");
                                        if (win.equalsIgnoreCase("a")) {
                                            a = true;
                                        }
                                        if (win.equalsIgnoreCase("a+")) {
                                            ap = true;
                                        }
                                        if (win.equalsIgnoreCase("d")) {
                                            d = true;
                                        }
                                        if (win.equalsIgnoreCase("h")) {
                                            h = true;
                                        }
                                        if (win.equalsIgnoreCase("h+")) {
                                            hp = true;
                                        }
                                    }
                                } else {
                                    if (winning.equalsIgnoreCase("a")) {
                                        a = true;
                                    }
                                    if (winning.equalsIgnoreCase("a+")) {
                                        ap = true;
                                    }
                                    if (winning.equalsIgnoreCase("d")) {
                                        d = true;
                                    }
                                    if (winning.equalsIgnoreCase("h")) {
                                        h = true;
                                    }
                                    if (winning.equalsIgnoreCase("h+")) {
                                        hp = true;
                                    }
                                }

                                str = "|" + (hp == true ? "X" : " ") + "" + "|" + (h == true ? "X" : " ") + "" + "|" + (d == true ? "X" : " ") + "" + "|" + (a == true ? "X" : " ") + "" + "|" + (ap == true ? "X|" : " |") + " ";
                            } else {
                                if (winnings != null) {
                                    for (String win : winnings) {
                                        win = win.replaceAll(":", "");
                                        if (win.equalsIgnoreCase("a")) {
                                            a = true;
                                        }
                                        if (win.equalsIgnoreCase("d")) {
                                            d = true;
                                        }
                                        if (win.equalsIgnoreCase("h")) {
                                            h = true;
                                        }
                                    }
                                } else {
                                    if (winning.equalsIgnoreCase("a")) {
                                        a = true;
                                    }
                                    if (winning.equalsIgnoreCase("d")) {
                                        d = true;
                                    }
                                    if (winning.equalsIgnoreCase("h")) {
                                        h = true;
                                    }
                                }

                                str = "|" + (h == true ? "X" : " ") + "|" + (d == true ? "X" : " ") + "|" + (a == true ? "X" : " ") + "|  ";
                            }
//                            |X" +""+ "| " +""+ "| " +""+ "| " +""+ "| |
//                            str=str+"|";
                            PrintClass.printTwoString(match, str, mPrinter);
                            if (lenghtOfMatch == 5)
                                PrintClass.printTwoString("", "------------", mPrinter);
                            else
                                PrintClass.printTwoString("", "--------  ", mPrinter);
                        }

//                        ThermalPrinter.printString();
                        mPrinter.addTextSize(1, 1);

//                        PrintClass.printTwoString("Unit Price($)", tktBean.getUnitPrice());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("No Of Lines", tktBean.getNoOfLines());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("Bet Amount", tktBean.getBetAmt());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("Total Amount($)", tktBean.getTotalAmt());

                        mPrinter.addText("\n------------------------------------------\n");
                        PrintClass.printTwoString("Unit Price(USD)", tktBean.getUnitPrice(), mPrinter);
                        PrintClass.printTwoString("Betamount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(tktBean.getBetAmount()), mPrinter);
                        PrintClass.printTwoString("Total Amount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(tktBean.getBoardAmount()), mPrinter);
                        PrintClass.printLine(mPrinter);

                        mPrinter.addTextAlign(Printer.ALIGN_CENTER);
                        String barcode = tktBean.getBrCd();
                        Bitmap bitmap = null;
                        if (barcode != null && barcode.length() != 0 && barcode.length() <= 20 && barcode.length() >= 17) {
                            bitmap = CreateCode(barcode, BarcodeFormat.CODE_128, 384, 128);
                        }
//            ThermalPrinter.printLogo(bitmap, ThermalPrinter.ALGIN_MIDDLE);

                        if (bitmap != null) {
                            mPrinter.addImage(bitmap, 0, 0,
                                    bitmap.getWidth(),
                                    bitmap.getHeight(),
                                    Printer.COLOR_1,
                                    Printer.MODE_MONO,
                                    Printer.HALFTONE_DITHER,
                                    Printer.PARAM_DEFAULT,
                                    Printer.COMPRESS_AUTO);
                        }

//                        PrintClass.printBarcode(tktBean.getBrCd());
//                        ThermalPrinter.printString();
                        mPrinter.addTextAlign(Printer.ALIGN_CENTER);
                        mPrinter.addText(tktBean.getBrCd());
                        mPrinter.addTextAlign(Printer.ALIGN_CENTER);
                        mPrinter.addText("\n\n");

//                        ThermalPrinter.printString();
                        String printMes = "";
                        if (tktBean.getAllMesage() != null) {
                            mPrinter.addTextAlign(Printer.ALIGN_CENTER);

                            for (int i = 0; i < tktBean.getAllMesage().length; i++) {

                                if (tktBean.getAllMesage()[i].contains("@")) {
                                    if (i == 0) {
                                        mPrinter.addText("******************************************\n");
                                        mPrinter.addText(tktBean.getAllMesage()[i].split("[@]")[0] + "\n\n");
                                        if (tktBean.getAllMesage()[i].split("[@]").length > 1)
                                            printMes = tktBean.getAllMesage()[i].split("[@]")[1];
                                    } else {
                                        String[] printData = tktBean.getAllMesage()[i].split("[@]");
                                        PrintClass.printTwoString(printData[0], printData[1], mPrinter);
                                    }

                                }


                            }
                            if (printMes.trim().length() > 0) {
                                mPrinter.addTextAlign(Printer.ALIGN_CENTER);
                                mPrinter.addText(printMes + "\n\n");
                                mPrinter.addText("******************************************\n");
                            }

//                            ThermalPrinter.printString();
                        }


//                        ThermalPrinter.addString("TEST RETAILER");
//                        ThermalPrinter.printString();
                        if (printMes == null || printMes.trim().length() == 0) {

                            mPrinter.addText("******************************************\n");
                        }
                        mPrinter.addCut(mPrinter.CUT_FEED);
//                        ThermalPrinter.printString();
//                        ThermalPrinter.walkPaper(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    TpsGamesClass.getInstance().closeScreen(PrintActivitySportsExternal.this);
                    Intent intent = new Intent();
                    intent.putExtra("resetRequest", "resetData");
                    setResult(59, intent);
                    finish();

                }
            }
        }).start();


        return true;
    }

    // printer <code></code>
    private Printer mPrinter = null;
    boolean returnValue;
//
//    private boolean createReceiptData() {
//        returnValue = true;
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                StringBuilder textData = new StringBuilder();
//                final int barcodeWidth = 2;
//                final int barcodeHeight = 100;
//
//                if (mPrinter == null) {
//                    returnValue = false;
//                }
//                if (returnValue) {
//                    try {
//
//                        GameSale gameSale = null;
//                        try {
//                            gameSale = TpsGamesClass.getInstance(PrintActivitySportsExternal.this).getGson().fromJson(saleResponse, GameSale.class);
//                            TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(gameSale.getAvlblCreditAmt());
//                        } catch (Exception e) {
//                            e.getLocalizedMessage();
//                        }
//                        if (TpsGamesClass.getInstance().getTopMwessage() != null && TpsGamesClass.getInstance().getTopMwessage() != null) {
//                            mPrinter.addText(TpsGamesClass.getInstance().getTopMwessage() + "\n");
//                            TpsGamesClass.getInstance().setTopMessage("");
//                        }
//
//                        mPrinter.addText("******************************************\n");
//
//                        //print above message
//                        if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getTOP() != null) {
//                            String[] topMsg = gameSale.getMainData().getAdvMessage().getTOP();
//                            for (int i = 0; i < topMsg.length; i++) {
//                                mPrinter.addTextAlign(mPrinter.ALIGN_CENTER);
//                                mPrinter.addText(topMsg[i] + "\n");
//                                mPrinter.addText("******************************************\n");
//                            }
//                        }
//
////            ThermalmPrinter.setLineSpace(6);
////            ThermalmPrinter.setFontSize(2);
////            ThermalmPrinter.setGray(1);
//
//                        mPrinter.addFeedLine(1);
//                        mPrinter.addTextSize(2, 2);
//                        mPrinter.addTextSize(1, 2);
//                        mPrinter.addTextFont(2);
//
//                        PrintClass.printTitleAtTop("SKILROCK", mPrinter);
//                        mPrinter.addTextSize(1, 2);
//                        PrintClass.printTitleSub(gameSale.getMainData().getCommonSaleData().getGameName(), mPrinter);
////            ThermalmPrinter.printString();
//
//
//                        PrintClass.printTwoString("Ticket No :", gameSale.getMainData().getCommonSaleData().getTicketNumber(), mPrinter);
//                        PrintClass.printTwoString(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getPurchaseDate()), gameSale.getMainData().getCommonSaleData().getPurchaseTime(), mPrinter);
//                        mPrinter.addText("------------------------------------------\n");
//
//                        PrintClass.printTwoString("Draw Date", "Draw Time", mPrinter);
//
//                        for (int i = 0; i < gameSale.getMainData().getCommonSaleData().getDrawData().length; i++) {
//                            PrintClass.printTwoString(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getDrawData()[i].getDrawDate()), gameSale.getMainData().getCommonSaleData().getDrawData()[i].getDrawTime(), mPrinter);
//                        }
//
//                        mPrinter.addText("------------------------------------------\n");
//
//                        for (int i = 0; i < gameSale.getMainData().getBetTypeData().length; i++) {
//
//                            String isQp = gameSale.getMainData().getBetTypeData()[i].isQp() == true ? "QP" : "Manual";
//                            PrintClass.printTwoString(gameSale.getMainData().getBetTypeData()[i].getBetName(), isQp, mPrinter);
//                            String numberString = "";
//                            if (gameSale.getMainData().getBetTypeData()[i].getBetName().equalsIgnoreCase("oneToTwelve")) {
//                                numberString = gameSale.getMainData().getBetTypeData()[i].getPickedNumbers() + " - " + sunSin[Integer.parseInt(gameSale.getMainData().getBetTypeData()[i].getPickedNumbers()) - 1];
//                            } else {
//                                numberString = gameSale.getMainData().getBetTypeData()[i].getPickedNumbers();
//                            }
//                            PrintClass.printMiddle(numberString, mPrinter);
//                            PrintClass.printTwoString("No. of Line(s)", gameSale.getMainData().getBetTypeData()[i].getNoOfLines() + "", mPrinter);
////                        if(gameSale.getMainData().getBetTypeData()[i].getBetName().equalsIgnoreCase("roulette")){
//
//                            PrintClass.printTwoString("Bet Amount(USD)", gameSale.getMainData().getBetTypeData()[i].getPanelPrice() + "", mPrinter);
////                        }else {
////                            PrintClass.printTwoString("Bet Amount(USD)", gameSale.getMainData().getBetTypeData()[i].getPanelPrice() + "");
////                        }
//                            mPrinter.addText("------------------------------------------\n");
//                        }
//                        PrintClass.printTwoString("No. of Draws", gameSale.getMainData().getCommonSaleData().getDrawData().length + "", mPrinter);
//                        PrintClass.printTwoString("Total Amount(USD)", gameSale.getMainData().getCommonSaleData().getPurchaseAmt() + "", mPrinter);
////            ThermalmPrinter.printString();
////                        mPrinter.addBarcode(gameSale.getMainData().getCommonSaleData().getBarcodeCount(),
////                                mPrinter.BARCODE_CODE39,
////                                mPrinter.HRI_BELOW,
////                                mPrinter.FONT_A,
////                                barcodeWidth,
////                                barcodeHeight);
//                        String barcode = gameSale.getMainData().getCommonSaleData().getBarcodeCount();
//                        Bitmap bitmap = null;
//                        if (barcode != null && barcode.length() != 0 && barcode.length() <= 20 && barcode.length() >= 17) {
//                            bitmap = CreateCode(barcode, BarcodeFormat.CODE_128, 384, 128);
//                        }
////            ThermalPrinter.printLogo(bitmap, ThermalPrinter.ALGIN_MIDDLE);
//
//                        if (bitmap != null) {
//                            mPrinter.addImage(bitmap, 0, 0,
//                                    bitmap.getWidth(),
//                                    bitmap.getHeight(),
//                                    Printer.COLOR_1,
//                                    Printer.MODE_MONO,
//                                    Printer.HALFTONE_DITHER,
//                                    Printer.PARAM_DEFAULT,
//                                    Printer.COMPRESS_AUTO);
//                        }
//
////            PrintClass.printBarcode(gameSale.getMainData().getCommonSaleData().getTicketNumber());
//
//
////            ThermalmPrinter.printString();
//                        mPrinter.addText("\n");
//                        PrintClass.printMiddle(gameSale.getMainData().getCommonSaleData().getBarcodeCount(), mPrinter);
//
////            ThermalmPrinter.printString();
//                        mPrinter.addText("******************************************\n");
//                        if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getBOTTOM() != null) {
//                            String[] bottom = gameSale.getMainData().getAdvMessage().getBOTTOM();
//                            for (int i = 0; i < bottom.length; i++) {
//                                mPrinter.addTextAlign(mPrinter.ALIGN_CENTER);
//                                mPrinter.addText(bottom[i] + "\n");
//                                mPrinter.addText("******************************************\n");
//                            }
//                        }
////            ThermalmPrinter.printString();
//
//                        mPrinter.addCut(mPrinter.CUT_FEED);
////            mPrinter.clearCommandBuffer();
//
//
//                    } catch (TelpoException e) {
//                        e.printStackTrace();
//                    } catch (Epos2Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        isReponsePrint = true;
//                        TpsGamesClass.getInstance().closeScreen(PrintActivitySportsExternal.this);
//                        Intent intent = new Intent();
//                        intent.putExtra("resetRequest", "resetData");
//                        setResult(054, intent);
////            runOnUiThread(new Runnable() {
////                @Override
////                public void run() {
////                    unregisterReceiver(printReceive);
////                }
////            });
//
//                        PrintActivitySportsExternal.this.finish();
//                    }
//                }
//
//
//            }
//        }).start();
//
//
//        return returnValue;
//    }

//    public Bitmap printBarcode(String barcode) throws TelpoException {
//        Bitmap bitmap = null;
//        if (barcode != null && barcode.length() != 0 && barcode.length() <= 20 && barcode.length() >= 17) {
//            bitmap = CreateCode(barcode, BarcodeFormat.CODE_128, 384, 128);
////            ThermalPrinter.printLogo(bitmap, ThermalPrinter.ALGIN_MIDDLE);
//        } else {
//            throw new IllegalArgumentException();
//        }
//        return bitmap;
//    }

    public Bitmap CreateCode(String str, BarcodeFormat type, int bmpWidth, int bmpHeight) throws InternalErrorException {
        BitMatrix matrix = null;

        try {
            matrix = (new MultiFormatWriter()).encode(str, type, bmpWidth, bmpHeight);
        } catch (WriterException var10) {
            var10.printStackTrace();
            throw new InternalErrorException("Failed to encode bitmap");
        }

        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];

        for (int bitmap = 0; bitmap < height; ++bitmap) {
            for (int x = 0; x < width; ++x) {
                if (matrix.get(x, bitmap)) {
                    pixels[bitmap * width + x] = -16777216;
                } else {
                    pixels[bitmap * width + x] = -1;
                }
            }
        }

        Bitmap var11 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        var11.setPixels(pixels, 0, width, 0, 0, width, height);
        return var11;
    }

    private boolean runPrintReceiptSequence() {
        if (!initializeObject()) {
            return false;
        }

        if (!printTicket(TpsGamesClass.getInstance().getSportLoater(responseString))) {
            finalizeObject();
            return false;
        }

        if (!printData()) {
            finalizeObject();
            return false;
        }

        return true;
    }

    private void finalizeObject() {
        if (mPrinter == null) {
            return;
        }

//        mPrinter.clearCommandBuffer();
//
//        mPrinter.setReceiveEventListener(null);

//        mPrinter = null;
    }

    private void dispPrinterWarnings(PrinterStatusInfo status) {
//        EditText edtWarnings = (EditText)findViewById(R.id.edtWarnings);
        String warningsMsg = "";

        if (status == null) {
            return;
        }

        if (status.getPaper() == Printer.PAPER_NEAR_END) {
            warningsMsg += "Roll paper is nearly end.\\n";
        }

        if (status.getBatteryLevel() == Printer.BATTERY_LEVEL_1) {
            warningsMsg += "Battery level of printer is low.\\n";
        }

//        edtWarnings.setText(warningsMsg);
    }

    private boolean isPrintable(PrinterStatusInfo status) {
        if (status == null) {
            return false;
        }

        if (status.getConnection() == Printer.FALSE) {
            return false;
        } else if (status.getOnline() == Printer.FALSE) {
            return false;
        } else {
            ;//print available
        }

        return true;
    }

    private boolean printData() {
        if (mPrinter == null) {
            return false;
        }

        if (!connectPrinter()) {
            return false;
        }

        PrinterStatusInfo status = mPrinter.getStatus();

        dispPrinterWarnings(status);

        if (!isPrintable(status)) {
//            ShowMsg.showMsg(makeErrorMessage(status), mContext);
            try {
                mPrinter.disconnect();
            } catch (Exception ex) {
                // Do nothing
            }
            return false;
        }

        try {
            mPrinter.sendData(Printer.PARAM_DEFAULT);
        } catch (Exception e) {
//            ShowMsg.showException(e, "sendData", mContext);
            try {
                mPrinter.disconnect();
            } catch (Exception ex) {
                // Do nothing
            }
            return false;
        }

        return true;
    }

    private boolean initializeObject() {
        try {
            mPrinter = new Printer(Printer.TM_T88,
                    Printer.MODEL_ANK,
                    getApplicationContext());
        } catch (Exception e) {
//            ShowMsg.showException(e, "Printer", mContext);
            return false;
        }

        mPrinter.setReceiveEventListener(this);

        return true;
    }

    @Override
    public void onClick(View v) {

    }

    private void disconnectPrinter() {
        if (mPrinter == null) {
            return;
        }

        try {
            mPrinter.endTransaction();
        } catch (final Exception e) {
            runOnUiThread(new Runnable() {
                @Override
                public synchronized void run() {
//                    ShowMsg.showException(e, "endTransaction", mContext);
                }
            });
        }

        try {
            mPrinter.disconnect();
        } catch (final Exception e) {

        }

        finalizeObject();
    }

    @Override
    public void onPtrReceive(Printer printer, int i, PrinterStatusInfo printerStatusInfo, String s) {
        runOnUiThread(new Runnable() {
            @Override
            public synchronized void run() {


//                dispPrinterWarnings(Printer.PAPER_NEAR_END);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        disconnectPrinter();
                    }
                }).start();
            }
        });
    }

    private boolean connectPrinter() {
        boolean isBeginTransaction = false;

        if (mPrinter == null) {
            return false;
        }

        try {
            mPrinter.connect(printerName, Printer.PARAM_DEFAULT);
        } catch (Exception e) {
//            ShowMsg.showException(e, "connect", mContext);
            return false;
        }

        try {
            mPrinter.beginTransaction();
            isBeginTransaction = true;
        } catch (Exception e) {
//            ShowMsg.showException(e, "beginTransaction", mContext);
        }

        if (isBeginTransaction == false) {
            try {
                mPrinter.disconnect();
            } catch (Epos2Exception e) {
                // Do nothing
                return false;
            }
        }

        return true;
    }
}
