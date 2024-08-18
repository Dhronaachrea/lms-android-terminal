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
import com.telpo.tps550.api.TelpoException;
import com.telpo.tps550.api.printer.ThermalPrinter;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.GameSale;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.WinningBean;
import demo.stpl.com.tpsmergedbuild.interfaces.Tps515PrintCall;

//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.GameSale;
//importdemo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.WinningBean;

/**
 * Created by stpl on 27-Oct-16.
 */
public class PrintWinningClaim extends Activity implements View.OnClickListener, ReceiveListener,Tps515PrintCall {
    //    private String responseString;
    String saleResponse;
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
//        if (saleResponse != null) {
//            printReceipt();
//        }
        //hdx053

        if (TpsGamesClass.getInstance().getDeviceName().contains("hdx053") || TpsGamesClass.getInstance().getDeviceName().contains("TPS580") || TpsGamesClass.getInstance().getDeviceName().contains("TPS550") || TpsGamesClass.getInstance().getDeviceName().contains("390")) {
            printReceipt();
        } else if (TpsGamesClass.getInstance().getDeviceName().contains("TPS515")) {
            printerName = TpsGamesClass.getInstance().getPrinterNameExternal();
            if (printerName.trim().length() == 0) {

                TpsGamesClass.getInstance().setTps515PrintCall(this);
                TpsGamesClass.getInstance().checkUsbPrintPermission(this);
            } else {
                runPrintReceiptSequence();
            }
        }


//        TpsGamesClass.getInstance().closeScreen(this);
//
//        TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
//        String url = "/sdcard/rainbow_header.png";
//        TpsGamesClass.getInstance().pleaseWait("Printing.....", url);
//        TpsGamesClass.getInstance().displayScreen(this);
    }



    protected void printReceipt() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        ThermalPrinter.start();
                    } catch (TelpoException e) {
                        e.printStackTrace();
                    }
                    WinningBean winningBean = null;
                    GameSale gameSale = null;
                    try {
                        winningBean = TpsGamesClass.getInstance(PrintWinningClaim.this).getGson().fromJson(saleResponse, WinningBean.class);
                        gameSale = TpsGamesClass.getInstance(PrintWinningClaim.this).getGson().fromJson(saleResponse, GameSale.class);
                    } catch (Exception e) {
                        e.getLocalizedMessage();
                    }
//                    if (TpsGamesClass.getInstance().getTopMwessage() != null && TpsGamesClass.getInstance().getTopMwessage() != null) {
//                        ThermalPrinter.addString(TpsGamesClass.getInstance().getTopMwessage() + "\n");
//                        TpsGamesClass.getInstance().setTopMessage("");
//                    }

                    ThermalPrinter.addString("********************************\n");

                    //print above message
                    if (winningBean.getAdvMsg() != null && winningBean.getAdvMsg().getTOP() != null) {
                        String[] topMsg = winningBean.getAdvMsg().getTOP();
                        for (int i = 0; i < topMsg.length; i++) {
                            ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                            ThermalPrinter.addString(topMsg[i] + "\n");
                            ThermalPrinter.addString("********************************\n");
                        }
                    }

                    ThermalPrinter.setLineSpace(6);
                    ThermalPrinter.setFontSize(2);
                    ThermalPrinter.setGray(1);

                    PrintClass.printTitleAtTop("SKILROCK");
                    PrintClass.printTitleSub(winningBean.getGameName());
                    ThermalPrinter.printString();


                    PrintClass.printTwoString("Ticket No :", winningBean.getTicketNumber());
//                    PrintClass.printTwoString(PrintClass.getPrintDateFormat(winningBean.getBarcodeCount().), gameSale.getMainData().getCommonSaleData().getPurchaseTime());
                    ThermalPrinter.addString("--------------------------------\n");

                    PrintClass.printTwoString("Draw Date", "Draw Time");

                    for (int i = 0; i < winningBean.getDrawData().length; i++) {
                        PrintClass.printTwoString(PrintClass.getPrintDateFormat(winningBean.getDrawData()[i].getDrawDate()), winningBean.getDrawData()[i].getDrawTime());
                        if (winningBean.getDrawData()[i].getWinStatus() != null) {
                            PrintClass.printTwoString("WinStatus", winningBean.getDrawData()[i].getWinStatus());
                        }
                        if (winningBean.getDrawData()[i].getWinningAmt() != null) {
                            PrintClass.printTwoString("WinningAmt", winningBean.getDrawData()[i].getWinningAmt());
                        }


                    }

                    ThermalPrinter.addString("--------------------------------\n");

//
                    ThermalPrinter.printString();
                    PrintClass.printBarcode(winningBean.getTicketNumber());


                    ThermalPrinter.printString();

                    PrintClass.printMiddle(winningBean.getBarcodeCount());

                    ThermalPrinter.printString();
                    ThermalPrinter.addString("********************************\n");
                    if (winningBean.getAdvMsg() != null && winningBean.getAdvMsg().getBOTTOM() != null) {
                        String[] bottom = winningBean.getAdvMsg().getBOTTOM();
                        for (int i = 0; i < bottom.length; i++) {
                            ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                            ThermalPrinter.addString(bottom[i] + "\n");
                            ThermalPrinter.addString("********************************\n");
                        }
                    }
                    ThermalPrinter.printString();

                    ThermalPrinter.walkPaper(100);


                } catch (TelpoException e) {
                    e.printStackTrace();
                } finally {
                    ThermalPrinter.stop();
                    isReponsePrint = true;
                    TpsGamesClass.getInstance().closeScreen(PrintWinningClaim.this);
                    PrintWinningClaim.this.finish();
                }

            }
        }).start();
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


    // printer <code></code>
    private Printer mPrinter = null;
    boolean returnValue;

    private boolean createReceiptData() {
        returnValue = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder textData = new StringBuilder();
                final int barcodeWidth = 2;
                final int barcodeHeight = 100;

                if (mPrinter == null) {
                    returnValue = false;
                }
                if (returnValue) {
                    try {

//                        GameSale gameSale = null;
                        WinningBean winningBean = null;
                        try {
                            winningBean = TpsGamesClass.getInstance(PrintWinningClaim.this).getGson().fromJson(saleResponse, WinningBean.class);
//                            gameSale = TpsGamesClass.getInstance(PrintWinningClaim.this).getGson().fromJson(saleResponse, GameSale.class);
//                            TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(gameSale.getAvlblCreditAmt());
                        } catch (Exception e) {
                            e.getLocalizedMessage();
                        }
//                        if (winningBean.getAdvMsg() != null && winningBean.getAdvMsg().getTOP() != null) {
//                            mPrinter.addText(winningBean.getAdvMsg().getTOP() + "\n");
////                            TpsGamesClass.getInstance().setTopMessage("");
//                        }

                        mPrinter.addText("******************************************\n");

                        //print above message
                        if (winningBean.getAdvMsg() != null && winningBean.getAdvMsg().getTOP() != null) {
                            String[] topMsg = winningBean.getAdvMsg().getTOP();
                            for (int i = 0; i < topMsg.length; i++) {
                                mPrinter.addTextAlign(mPrinter.ALIGN_CENTER);
                                mPrinter.addText(topMsg[i] + "\n");
                                mPrinter.addText("******************************************\n");
                            }
                        }

//            ThermalmPrinter.setLineSpace(6);
//            ThermalmPrinter.setFontSize(2);
//            ThermalmPrinter.setGray(1);

                        mPrinter.addFeedLine(1);
                        mPrinter.addTextSize(2, 2);
                        mPrinter.addTextSize(1, 2);
                        mPrinter.addTextFont(2);

                        PrintClass.printTitleAtTop("SKILROCK", mPrinter);
                        mPrinter.addTextSize(1, 2);
                        PrintClass.printTitleSub(winningBean.getGameDevName(), mPrinter);
//            ThermalmPrinter.printString();


                        PrintClass.printTwoString("Ticket No :", winningBean.getTicketNumber(), mPrinter);
//                        PrintClass.printTwoString(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getPurchaseDate()), gameSale.getMainData().getCommonSaleData().getPurchaseTime(), mPrinter);
                        mPrinter.addText("------------------------------------------\n");

                        PrintClass.printTwoString("Draw Date", "Draw Time", mPrinter);

                        for (int i = 0; i < winningBean.getDrawData().length; i++) {
                            PrintClass.printTwoString(PrintClass.getPrintDateFormat(winningBean.getDrawData()[i].getDrawDate()), winningBean.getDrawData()[i].getDrawTime(), mPrinter);
                            if(winningBean.getDrawData()[i].getWinStatus() != null){
                                PrintClass.printTwoString("WinStatus", winningBean.getDrawData()[i].getWinStatus(), mPrinter);
                            }
                            if(winningBean.getDrawData()[i].getWinningAmt() != null){
                                PrintClass.printTwoString("WinningAmt", winningBean.getDrawData()[i].getWinningAmt(), mPrinter);
                            }


                        }

                        mPrinter.addText("------------------------------------------\n");

//            ThermalmPrinter.printString();
//                        mPrinter.addBarcode(gameSale.getMainData().getCommonSaleData().getBarcodeCount(),
//                                mPrinter.BARCODE_CODE39,
//                                mPrinter.HRI_BELOW,
//                                mPrinter.FONT_A,
//                                barcodeWidth,
//                                barcodeHeight);
                        mPrinter.addTextAlign(Printer.ALIGN_CENTER);
                        String barcode = winningBean.getBarcodeCount();
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

//            PrintClass.printBarcode(gameSale.getMainData().getCommonSaleData().getTicketNumber());


//            ThermalmPrinter.printString();
                        mPrinter.addText("\n");
                        PrintClass.printMiddle(winningBean.getBarcodeCount(), mPrinter);

//            ThermalmPrinter.printString();
                        mPrinter.addText("******************************************\n");
                        if (winningBean.getAdvMsg() != null && winningBean.getAdvMsg().getBOTTOM() != null) {
                            String[] bottom = winningBean.getAdvMsg().getBOTTOM();
                            for (int i = 0; i < bottom.length; i++) {
                                mPrinter.addTextAlign(mPrinter.ALIGN_CENTER);
                                mPrinter.addText(bottom[i] + "\n");
                                mPrinter.addText("******************************************\n");
                            }
                        }
//            ThermalmPrinter.printString();

                        mPrinter.addCut(mPrinter.CUT_FEED);
//            mPrinter.clearCommandBuffer();


                    } catch (TelpoException e) {
                        e.printStackTrace();
                    } catch (Epos2Exception e) {
                        e.printStackTrace();
                    } finally {
                        isReponsePrint = true;
//                        TpsGamesClass.getInstance().closeScreen(PrintWinningClaim.this);
//                        Intent intent = new Intent();
//                        intent.putExtra("resetRequest", "resetData");
//                        setResult(054, intent);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    unregisterReceiver(printReceive);
//                }
//            });

                        PrintWinningClaim.this.finish();
                    }
                }


            }
        }).start();


//        try {
//            ArrayList<PurchaseTicketBean.DrawType> drawTypes = new ArrayList<>();
//            ArrayList<PurchaseTicketBean.BetType> betTypes = new ArrayList<>();
//            for (int m = 0; m < beans.size(); m++) {
//                PurchaseTicketBean bean = beans.get(m);
//                drawTypes = bean.getDrawTypes();
//                betTypes = bean.getBetTypes();
//
//                printer.addTextAlign(Printer.ALIGN_CENTER);
//
//
//                printer.addFeedLine(1);
//                printer.addTextSize(2, 2);
//                printer.addText("Skilrock\n");
//                printer.addTextSize(1, 2);
//                printer.addText("------------------------------\n");
//                printer.addTextAlign(Printer.ALIGN_CENTER);
//                switch (betTypes.get(0).getPlayType()) {
//                    case "Perm1":
//                        gameName = "Lucky Numbers";
//                        break;
//                    case "Perm2":
//                        gameName = "Lucky Numbers";
//                        break;
//                    case "Perm3":
//                        gameName = "Lucky Numbers";
//                        break;
//                    case "Perm6":
//                        gameName = "LOTTO BONUS";
//                        break;
//                    case "Direct6":
//                        gameName = "LOTTO BONUS";
//                        break;
//                }
//
//                if (bean.getTicketCost() == 0) {
//                    gameName = gameName + " FREE";
//                }
//                printer.addText(gameName + "\n");
//                printer.addText("" + "\n");
//                printer.addText("------------------------------\n");
//                printer.addText(textData.toString());
//
//
//                printer.addTextSize(1, 2);
//                printTwoStringBt(bean.getDate(), bean.getTime(), printer);
//                printTwoStringBt("Ticket No.", bean.getTicketNo(), printer);
//                printer.addTextSize(1, 1);
//                printer.addFeedLine(1);
//
//                printer.addText("------------------------------\n\n");
//                printer.addTextSize(1, 2);
//                printTwoStringBt("DrawDate", "DrawTime", printer);
//
//                for (int i = 0; i < drawTypes.size(); i++) {
//                    printDrawGameDetails(drawTypes.get(i).getDrawName(), drawTypes.get(i).getDrawDate(), drawTypes.get(i).getDrawTime(), printer);
//                }
//
//                printer.addText("------------------------------\n\n");
//                for (int i = 0; i < betTypes.size(); i++) {
//                    printBetTypeDetails(betTypes.get(i).getPlayType(), betTypes.get(i).getQP(), betTypes.get(i).getNum(),
//                            nooflines[i] + "", Double.parseDouble(unitprice[i]) + "",
//                            AmountFormat.getCurrentAmountFormatForMobile(Double.parseDouble(panelprice[i])),
//                            VariableStorage.GlobalPref.getStringData(ActivityPrint.this, VariableStorage.GlobalPref.CURRENCY_SYMBOL), printer);
//                    printer.addText("------------------------------\n\n");
//                }
//
//                printer.addBarcode("0120945712",
//                        Printer.BARCODE_CODE39,
//                        Printer.HRI_BELOW,
//                        Printer.FONT_A,
//                        barcodeWidth,
//                        barcodeHeight);
//                printer.addText("------------------------------\n\n");
//                printer.addTextAlign(Printer.ALIGN_CENTER);
//                printer.addTextSize(1, 2);
//                printer.addText("7 days\n");
//                if (bean.getBottomAdvMsg().equals("")) {
//                } else {
//                    printer.addText(bean.getBottomAdvMsg());
//                    printer.addText("\n\n------------------------------\n\n");
//                }
//                printer.addText("Sample Ticket\n\n Not For Sale");
//                printer.addText("\n" +
//                        "\n------------------------------\n\n");
//
//
//                printer.addCut(Printer.CUT_FEED);
//            }
//
//        } catch (Exception e) {
//            return false;
//        } finally {
//
//        }


        return returnValue;
    }

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

        if (!createReceiptData()) {
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

        mPrinter.clearCommandBuffer();

        mPrinter.setReceiveEventListener(null);

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

    @Override
    public void onUsbPermissionSuccess() {
        Intent intent = new Intent(this, ActivityDiscoveryExternalPrinter.class);
        startActivityForResult(intent, 0);
    }
    @Override
    public void onPermissionDenied(){
        PrintWinningClaim.this.finish();
    }
}
