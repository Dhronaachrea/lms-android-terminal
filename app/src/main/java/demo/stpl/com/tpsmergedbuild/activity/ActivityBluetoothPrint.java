package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
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

import java.util.ArrayList;
import java.util.HashMap;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.WinningBean;

//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.WinningBean;

/**
 * Created by stpl on 03-Nov-16.
 */
public class ActivityBluetoothPrint extends Activity implements ReceiveListener {

    TextView dialogHeaderText;
    private ArrayList<String> printerList = null;
    private ArrayList<String> printerTarget = null;
    private ArrayAdapter printerListAdapter = null;
    String selectedPrinter, selectedTarget;
    private Dialog dialog;
    private String saleResponse;
    Printer printer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_print_activity);
        saleResponse = getIntent().getStringExtra("response");

        if (TpsGamesClass.getInstance().getBluetoothPrinterName().trim().length() > 0) {
            selectedTarget = TpsGamesClass.getInstance().getBluetoothPrinterName();
            runPrintReceiptSequence();
        } else {
            categoryPopup();
        }

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

    private boolean isReponsePrint;

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
    boolean returnValue;

    protected boolean createReceiptData() {
        returnValue = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder textData = new StringBuilder();
                final int barcodeWidth = 2;
                final int barcodeHeight = 100;

                if (printer == null) {
                    returnValue = false;
                }
                if (returnValue) {
                    try {

//                        GameSale gameSale = null;
                        WinningBean winningBean = null;
                        try {
                            winningBean = TpsGamesClass.getInstance(ActivityBluetoothPrint.this).getGson().fromJson(saleResponse, WinningBean.class);
//                            gameSale = TpsGamesClass.getInstance(PrintWinningClaim.this).getGson().fromJson(saleResponse, GameSale.class);
//                            TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(gameSale.getAvlblCreditAmt());
                        } catch (Exception e) {
                            e.getLocalizedMessage();
                        }
//                        if (winningBean.getAdvMsg() != null && winningBean.getAdvMsg().getTOP() != null) {
//                            printer.addText(winningBean.getAdvMsg().getTOP() + "\n");
////                            TpsGamesClass.getInstance().setTopMessage("");
//                        }

                        printer.addText("******************************************\n");

                        //print above message
                        if (winningBean.getAdvMsg() != null && winningBean.getAdvMsg().getTOP() != null) {
                            String[] topMsg = winningBean.getAdvMsg().getTOP();
                            for (int i = 0; i < topMsg.length; i++) {
                                printer.addTextAlign(printer.ALIGN_CENTER);
                                printer.addText(topMsg[i] + "\n");
                                printer.addText("******************************************\n");
                            }
                        }

//            Thermalprinter.setLineSpace(6);
//            Thermalprinter.setFontSize(2);
//            Thermalprinter.setGray(1);

                        printer.addFeedLine(1);
                        printer.addTextSize(2, 2);
                        printer.addTextSize(1, 2);
                        printer.addTextFont(2);

                        PrintClass.printTitleAtTop("SKILROCK", printer);
                        printer.addTextSize(1, 2);
                        PrintClass.printTitleSub(winningBean.getGameDevName(), printer);
//            Thermalprinter.printString();


                        PrintClass.printTwoString("Ticket No :", winningBean.getTicketNumber(), printer);
//                        PrintClass.printTwoString(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getPurchaseDate()), gameSale.getMainData().getCommonSaleData().getPurchaseTime(), printer);
                        printer.addText("------------------------------------------\n");

                        PrintClass.printTwoString("Draw Date", "Draw Time", printer);

                        for (int i = 0; i < winningBean.getDrawData().length; i++) {
                            PrintClass.printTwoString(PrintClass.getPrintDateFormat(winningBean.getDrawData()[i].getDrawDate()), winningBean.getDrawData()[i].getDrawTime(), printer);
                            if (winningBean.getDrawData()[i].getWinStatus() != null) {
                                PrintClass.printTwoString("WinStatus", winningBean.getDrawData()[i].getWinStatus(), printer);
                            }
                            if (winningBean.getDrawData()[i].getWinningAmt() != null) {
                                PrintClass.printTwoString("WinningAmt", winningBean.getDrawData()[i].getWinningAmt(), printer);
                            }


                        }

                        printer.addText("------------------------------------------\n");

//            Thermalprinter.printString();
//                        printer.addBarcode(gameSale.getMainData().getCommonSaleData().getBarcodeCount(),
//                                printer.BARCODE_CODE39,
//                                printer.HRI_BELOW,
//                                printer.FONT_A,
//                                barcodeWidth,
//                                barcodeHeight);
                        printer.addTextAlign(Printer.ALIGN_CENTER);
                        String barcode = winningBean.getBarcodeCount();
                        Bitmap bitmap = null;
                        if (barcode != null && barcode.length() != 0 && barcode.length() <= 20 && barcode.length() >= 17) {
                            bitmap = CreateCode(barcode, BarcodeFormat.CODE_128, 384, 128);
                        }
//            ThermalPrinter.printLogo(bitmap, ThermalPrinter.ALGIN_MIDDLE);

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


//            Thermalprinter.printString();
                        printer.addText("\n");
                        PrintClass.printMiddle(winningBean.getBarcodeCount(), printer);

//            Thermalprinter.printString();
                        printer.addText("******************************************\n");
                        if (winningBean.getAdvMsg() != null && winningBean.getAdvMsg().getBOTTOM() != null) {
                            String[] bottom = winningBean.getAdvMsg().getBOTTOM();
                            for (int i = 0; i < bottom.length; i++) {
                                printer.addTextAlign(printer.ALIGN_CENTER);
                                printer.addText(bottom[i] + "\n");
                                printer.addText("******************************************\n");
                            }
                        }
//            Thermalprinter.printString();

                        printer.addCut(printer.CUT_FEED);
//            printer.clearCommandBuffer();


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

                        ActivityBluetoothPrint.this.finish();
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
}
