package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epson.epos2.Epos2Exception;
import com.epson.epos2.discovery.DeviceInfo;
import com.epson.epos2.discovery.Discovery;
import com.epson.epos2.discovery.DiscoveryListener;
import com.epson.epos2.discovery.FilterOption;
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

import java.util.ArrayList;
import java.util.HashMap;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.GameSale;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.GameSale;
//importdemo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

/**
 * Created by stpl on 9/10/2016.
 */
public class PrintActivityAllGames extends Activity implements ReceiveListener {

    String saleResponse;
    private boolean isReponsePrint = false;
    String[] sunSin = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_print_activity);
        saleResponse = getIntent().getStringExtra("response");
        if (saleResponse != null && (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("TPS515"))) {
//            IntentFilter pIntentFilter = new IntentFilter();
//            pIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
//            registerReceiver(printReceive, pIntentFilter);
            if (TpsGamesClass.getInstance().getBluetoothPrinterName().trim().length() > 0) {
                selectedTarget = TpsGamesClass.getInstance().getBluetoothPrinterName();
                runPrintReceiptSequence();
            } else {
                categoryPopup();
            }

        } else if (saleResponse != null) {
            printReceipt();
        }

        if(TpsGamesClass.getInstance().getDeviceName().contains("TPS580")){
            TpsGamesClass.getInstance().closeScreen(this);

            TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
            String url = "/sdcard/rainbow_header.png";
            TpsGamesClass.getInstance().pleaseWait("Printing.....", url);
            TpsGamesClass.getInstance().displayScreen(this);
        }

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
                    GameSale gameSale = null;
                    try {
                        gameSale = TpsGamesClass.getInstance(PrintActivityAllGames.this).getGson().fromJson(saleResponse, GameSale.class);
                        TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(gameSale.getMainData().getAvlblCreditAmt());
                    } catch (Exception e) {
                        e.getLocalizedMessage();
                    }
                    if (TpsGamesClass.getInstance().getTopMwessage() != null && TpsGamesClass.getInstance().getTopMwessage() != null) {
                        ThermalPrinter.addString(TpsGamesClass.getInstance().getTopMwessage() + "\n");
                        TpsGamesClass.getInstance().setTopMessage("");
                    }

                    ThermalPrinter.addString("********************************\n");

                    //print above message
                    if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getTOP() != null) {
                        String[] topMsg = gameSale.getMainData().getAdvMessage().getTOP();
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
                    PrintClass.printTitleSub(gameSale.getMainData().getCommonSaleData().getGameName());
                    ThermalPrinter.printString();


                    PrintClass.printTwoString("Ticket No :", gameSale.getMainData().getCommonSaleData().getTicketNumber());
                    PrintClass.printTwoString(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getPurchaseDate()), gameSale.getMainData().getCommonSaleData().getPurchaseTime());
                    ThermalPrinter.addString("--------------------------------\n");

                    PrintClass.printTwoString("Draw Date", "Draw Time");

                    for (int i = 0; i < gameSale.getMainData().getCommonSaleData().getDrawData().length; i++) {
                        PrintClass.printTwoString(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getDrawData()[i].getDrawDate()), gameSale.getMainData().getCommonSaleData().getDrawData()[i].getDrawTime());
                    }

                    ThermalPrinter.addString("--------------------------------\n");

                    for (int i = 0; i < gameSale.getMainData().getBetTypeData().length; i++) {

                        String isQp = gameSale.getMainData().getBetTypeData()[i].isQp() == true ? "QP" : "Manual";
                        PrintClass.printTwoString(gameSale.getMainData().getBetTypeData()[i].getBetName(), isQp);
                        String numberString = "";
                        if (gameSale.getMainData().getBetTypeData()[i].getBetName().equalsIgnoreCase("oneToTwelve")) {
                            numberString = gameSale.getMainData().getBetTypeData()[i].getPickedNumbers() + " - " + sunSin[Integer.parseInt(gameSale.getMainData().getBetTypeData()[i].getPickedNumbers()) - 1];
                        } else {
                            numberString = gameSale.getMainData().getBetTypeData()[i].getPickedNumbers();
                        }
                        PrintClass.printMiddle(numberString);
                        PrintClass.printTwoString("No. of Line(s)", gameSale.getMainData().getBetTypeData()[i].getNoOfLines() + "");
//                        if(gameSale.getMainData().getBetTypeData()[i].getBetName().equalsIgnoreCase("roulette")){

                        PrintClass.printTwoString("Bet Amount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(gameSale.getMainData().getBetTypeData()[i].getPanelPrice() + ""));
//                        }else {
//                            PrintClass.printTwoString("Bet Amount(USD)", gameSale.getMainData().getBetTypeData()[i].getPanelPrice() + "");
//                        }
                        ThermalPrinter.addString("--------------------------------\n");
                    }
                    PrintClass.printTwoString("No. of Draws", gameSale.getMainData().getCommonSaleData().getDrawData().length + "");
                    PrintClass.printTwoString("Total Amount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(gameSale.getMainData().getCommonSaleData().getPurchaseAmt() + ""));
                    ThermalPrinter.printString();
                    PrintClass.printBarcode(gameSale.getMainData().getCommonSaleData().getTicketNumber());


                    ThermalPrinter.printString();

                    PrintClass.printMiddle(gameSale.getMainData().getCommonSaleData().getBarcodeCount());

                    ThermalPrinter.printString();
                    ThermalPrinter.addString("********************************\n");
                    if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getBOTTOM() != null) {
                        String[] bottom = gameSale.getMainData().getAdvMessage().getBOTTOM();
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
                    TpsGamesClass.getInstance().closeScreen(PrintActivityAllGames.this);
                    Intent intent = new Intent();
                    intent.putExtra("resetRequest", "resetData");
                    setResult(054, intent);
                    PrintActivityAllGames.this.finish();
                }

            }
        }).start();
    }

    @Override
    public void onBackPressed() {

        if (isReponsePrint) {

            super.onBackPressed();
        } else {
            return;
        }
    }

    TextView dialogHeaderText;
    private ArrayList<String> printerList = null;
    private ArrayList<String> printerTarget = null;
    private ArrayAdapter printerListAdapter = null;
    String selectedPrinter, selectedTarget;
    private Dialog dialog;
    Printer printer;

    private void categoryPopup() {
//        this.beans = beans;
        dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_alert_dialog_devices);
        dialog.setCancelable(false);
//        SystemUi.enableImmersiveMode(dialog.getWindow().getDecorView());
        dialogHeaderText = (TextView) dialog.findViewById(R.id.dialogHeaderText);
        dialogHeaderText.setText("Please wait...");
        printerList = new ArrayList<>();
        printerTarget = new ArrayList<>();
        printerListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, printerList);
        ListView list = (ListView) dialog.findViewById(R.id.list_item);
        list.setAdapter(printerListAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPrinter = printerList.get(position);
                selectedTarget = printerTarget.get(position);
                dialog.dismiss();
                runPrintReceiptSequence();
            }
        });

        dialog.show();
        startDiscovery();
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

    private boolean connectPrinter() {
        boolean isBeginTransaction = false;

        if (printer == null) {
            return false;
        }

        try {
            printer.connect(selectedTarget, Printer.PARAM_DEFAULT);
        } catch (Exception e) {
            return false;
        }

        try {
            printer.beginTransaction();
            isBeginTransaction = true;
        } catch (Exception e) {
        }

        if (isBeginTransaction == false) {
            try {
                printer.disconnect();
            } catch (Epos2Exception e) {
                // Do nothing
                return false;
            }
        }

        return true;
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
        if (printer == null) {
            return false;
        }

        if (!connectPrinter()) {
            return false;
        }

        PrinterStatusInfo status = printer.getStatus();


        if (!isPrintable(status)) {
            try {
                printer.disconnect();
            } catch (Exception ex) {
                // Do nothing
            }
            return false;
        }

        try {
            printer.sendData(Printer.PARAM_DEFAULT);
        } catch (Exception e) {
            try {
                printer.disconnect();
            } catch (Exception ex) {
                // Do nothing
            }
            return false;
        }
        try {
            Discovery.stop();
        } catch (Epos2Exception e) {
            e.printStackTrace();
        }
//        ThermalPrinter.stop();
//        setResult(101, new Intent());
//        unregisterReceiver(printReceive);
//        finish();
        return true;
    }

    private boolean LowBattery;

    private BroadcastReceiver printReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_NOT_CHARGING);
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                if (status != BatteryManager.BATTERY_STATUS_CHARGING) {
                    if (level * 5 <= scale) {
                        LowBattery = true;
                    } else {
                        LowBattery = false;
                    }
                } else {
                    LowBattery = false;
                }
            }
        }
    };

    private void finalizeObject() {
        if (printer == null) {
            return;
        }

//        printer.clearCommandBuffer();
//
//        printer.setReceiveEventListener(null);
//
//        printer = null;
    }


    private boolean createReceiptData() {
        StringBuilder textData = new StringBuilder();
        final int barcodeWidth = 2;
        final int barcodeHeight = 100;

        if (printer == null) {
            return false;
        }

        try {

            GameSale gameSale = null;
            try {
                gameSale = TpsGamesClass.getInstance(PrintActivityAllGames.this).getGson().fromJson(saleResponse, GameSale.class);
                TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(gameSale.getMainData().getAvlblCreditAmt());
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
            if (TpsGamesClass.getInstance().getTopMwessage() != null && TpsGamesClass.getInstance().getTopMwessage() != null) {
                printer.addText(TpsGamesClass.getInstance().getTopMwessage() + "\n");
                TpsGamesClass.getInstance().setTopMessage("");
            }

            printer.addText("******************************************\n");

            //print above message
            if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getTOP() != null) {
                String[] topMsg = gameSale.getMainData().getAdvMessage().getTOP();
                for (int i = 0; i < topMsg.length; i++) {
                    printer.addTextAlign(Printer.ALIGN_CENTER);
                    printer.addText(topMsg[i] + "\n");
                    printer.addText("******************************************\n");
                }
            }

//            ThermalPrinter.setLineSpace(6);
//            ThermalPrinter.setFontSize(2);
//            ThermalPrinter.setGray(1);

            printer.addFeedLine(6);
            printer.addTextSize(2, 2);
            printer.addTextSize(1, 2);
            printer.addTextFont(2);

            PrintClass.printTitleAtTop("SKILROCK", printer);
            printer.addTextSize(1, 2);
            PrintClass.printTitleSub(gameSale.getMainData().getCommonSaleData().getGameName(), printer);
//            ThermalPrinter.printString();


            PrintClass.printTwoString("Ticket No :", gameSale.getMainData().getCommonSaleData().getTicketNumber(), printer);
            PrintClass.printTwoString(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getPurchaseDate()), gameSale.getMainData().getCommonSaleData().getPurchaseTime(), printer);
            printer.addText("------------------------------------------\n");

            PrintClass.printTwoString("Draw Date", "Draw Time", printer);

            for (int i = 0; i < gameSale.getMainData().getCommonSaleData().getDrawData().length; i++) {
                PrintClass.printTwoString(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getDrawData()[i].getDrawDate()), gameSale.getMainData().getCommonSaleData().getDrawData()[i].getDrawTime(), printer);
            }

            printer.addText("------------------------------------------\n");

            for (int i = 0; i < gameSale.getMainData().getBetTypeData().length; i++) {

                String isQp = gameSale.getMainData().getBetTypeData()[i].isQp() == true ? "QP" : "Manual";
                PrintClass.printTwoString(gameSale.getMainData().getBetTypeData()[i].getBetName(), isQp, printer);
                String numberString = "";
                if (gameSale.getMainData().getBetTypeData()[i].getBetName().equalsIgnoreCase("oneToTwelve")) {
                    numberString = gameSale.getMainData().getBetTypeData()[i].getPickedNumbers() + " - " + sunSin[Integer.parseInt(gameSale.getMainData().getBetTypeData()[i].getPickedNumbers()) - 1];
                } else {
                    numberString = gameSale.getMainData().getBetTypeData()[i].getPickedNumbers();
                }
                PrintClass.printMiddle(numberString, printer);
                PrintClass.printTwoString("No. of Line(s)", gameSale.getMainData().getBetTypeData()[i].getNoOfLines() + "", printer);
//                        if(gameSale.getMainData().getBetTypeData()[i].getBetName().equalsIgnoreCase("roulette")){

                PrintClass.printTwoString("Bet Amount(USD)", gameSale.getMainData().getBetTypeData()[i].getPanelPrice() + "", printer);
//                        }else {
//                            PrintClass.printTwoString("Bet Amount(USD)", gameSale.getMainData().getBetTypeData()[i].getPanelPrice() + "");
//                        }
                printer.addText("------------------------------------------\n");
            }
            PrintClass.printTwoString("No. of Draws", gameSale.getMainData().getCommonSaleData().getDrawData().length + "", printer);
            PrintClass.printTwoString("Total Amount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(gameSale.getMainData().getCommonSaleData().getPurchaseAmt() + ""), printer);
//            ThermalPrinter.printString();
            printer.addTextAlign(Printer.ALIGN_CENTER);
            String barcode = gameSale.getMainData().getCommonSaleData().getBarcodeCount();
            Bitmap bitmap = null;
            if (barcode != null && barcode.length() != 0 && barcode.length() <= 20 && barcode.length() >= 17) {
                bitmap = CreateCode(barcode, BarcodeFormat.CODE_128, 384, 128);
            }
            if (bitmap != null) {
                printer.addImage(bitmap, 0, 0,
                        bitmap.getWidth(),
                        bitmap.getHeight(),
                        Printer.COLOR_1,
                        Printer.MODE_MONO,
                        Printer.HALFTONE_DITHER,
                        Printer.PARAM_DEFAULT,
                        Printer.COMPRESS_AUTO);
            }
//            PrintClass.printBarcode(gameSale.getMainData().getCommonSaleData().getTicketNumber());


//            ThermalPrinter.printString();

            PrintClass.printMiddle(gameSale.getMainData().getCommonSaleData().getBarcodeCount(), printer);

//            ThermalPrinter.printString();
            printer.addText("******************************************\n");
            if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getBOTTOM() != null) {
                String[] bottom = gameSale.getMainData().getAdvMessage().getBOTTOM();
                for (int i = 0; i < bottom.length; i++) {
                    printer.addTextAlign(Printer.ALIGN_CENTER);
                    printer.addText(bottom[i] + "\n");
                    printer.addText("******************************************\n");
                }
            }
//            ThermalPrinter.printString();

            printer.addCut(Printer.CUT_FEED);
//            printer.clearCommandBuffer();


        } catch (TelpoException e) {
            e.printStackTrace();
        } catch (Epos2Exception e) {
            e.printStackTrace();
        } finally {
            isReponsePrint = true;
            TpsGamesClass.getInstance().closeScreen(PrintActivityAllGames.this);
            Intent intent = new Intent();
            intent.putExtra("resetRequest", "resetData");
            setResult(054, intent);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    unregisterReceiver(printReceive);
//                }
//            });

            PrintActivityAllGames.this.finish();
        }


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

        textData = null;

        return true;
    }

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

    private boolean initializeObject() {
        try {
            printer = new Printer(Printer.TM_P20,
                    Printer.MODEL_ANK,
                    this);
        } catch (Exception e) {
            return false;
        }

        printer.setReceiveEventListener(this);

        return true;
    }


    private void startDiscovery() {

        try {
            Discovery.stop();
        } catch (Epos2Exception e) {
            e.printStackTrace();
        }

        FilterOption filterOption = null;

        printerList.clear();
        printerTarget.clear();
        printerListAdapter.notifyDataSetChanged();

        filterOption = new FilterOption();
        filterOption.setPortType(Discovery.PORTTYPE_BLUETOOTH);
        filterOption.setBroadcast("255.255.255.255");
        filterOption.setDeviceModel(Discovery.MODEL_ALL);
        filterOption.setEpsonFilter(Discovery.FILTER_NAME);
        filterOption.setDeviceType(Discovery.TYPE_ALL);

        try {
            Discovery.start(this, filterOption, mDiscoveryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private DiscoveryListener mDiscoveryListener = new DiscoveryListener() {
        @Override
        public void onDiscovery(final DeviceInfo deviceInfo) {
            runOnUiThread(new Runnable() {
                @Override
                public synchronized void run() {
                    dialogHeaderText.setText("Select printer");
                    HashMap<String, String> item = new HashMap<String, String>();
                    item.put("PrinterName", deviceInfo.getDeviceName());
                    item.put("Target", deviceInfo.getTarget());
                    printerList.add(deviceInfo.getDeviceName());
                    printerTarget.add(deviceInfo.getTarget());
                    printerListAdapter.notifyDataSetChanged();
                    selectedPrinter = deviceInfo.getDeviceName();
                    selectedTarget = deviceInfo.getTarget();
                    TpsGamesClass.getInstance().setBluetoothPrinterName(selectedTarget);
                    dialog.dismiss();
                    runPrintReceiptSequence();
                }
            });
        }
    };

    private void disconnectPrinter() {
        if (printer == null) {
            return;
        }

        try {
            printer.endTransaction();
        } catch (final Exception e) {
            runOnUiThread(new Runnable() {
                @Override
                public synchronized void run() {
                }
            });
        }

        try {
            printer.disconnect();
        } catch (final Exception e) {
            runOnUiThread(new Runnable() {
                @Override
                public synchronized void run() {
                }
            });
        }

        finalizeObject();
    }

    @Override
    public void onPtrReceive(Printer printer, int i, PrinterStatusInfo printerStatusInfo, String s) {
        runOnUiThread(new Runnable() {
            @Override
            public synchronized void run() {


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        disconnectPrinter();
                    }
                }).start();
            }
        });
    }
}
