package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
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
 * Created by stpl on 9/22/2016.
 */
public class PrintActivitySportsGame extends Activity implements ReceiveListener {

    String responseString;

    String saleResponseSle = "currDate:2016-09-22|currTime:12:06:45|ticketNo:525562266171280030|brCd:525562266171280030|trxId:50054|ticketAmt:16.00|balance:9974107.57|gameId:1|gameTypeId:1|gameName:Soccer|gameTypeName:Soccer 13|drawInfo:2016-09-25,06:00:00,SUNDAY-S13,909,15,1.0,1.00,16.00,16~AL--vs-GUA@A#AL--vs-BEI@H,A#ARS-vs-BEI@H#ARS-vs-GUA@A#AL -vs-GUA@H,A#AL -vs-AL-@H#FC -vs-BEI@A#BEI-vs-AL-@H,A#AL--vs-GUA@H#AL -vs-ARS@A#AL -vs-ARS@H,A#AL--vs-FC @H#AL--vs-BEI@AjackpotMsg:Current Jackpot Fund@(All Prizes Parimutuel)~13 out Of 13 Match@10000.00 USD|";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_print_activity);
        responseString = getIntent().getExtras().getString("response");
        TpsGamesClass.getInstance().closeScreen(this);

        TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
        String url = "/sdcard/rainbow_header.png";
        TpsGamesClass.getInstance().pleaseWait("Printing.....", url);
        TpsGamesClass.getInstance().displayScreen(this);
//        responseString = saleResponseSle;
        if (responseString != null && (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("TPS515"))) {
//            IntentFilter pIntentFilter = new IntentFilter();
//            pIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
//            registerReceiver(printReceive, pIntentFilter);
            if (TpsGamesClass.getInstance().getBluetoothPrinterName().trim().length() > 0) {
                selectedTarget = TpsGamesClass.getInstance().getBluetoothPrinterName();
                runPrintReceiptSequence();
            } else {
                categoryPopup();
            }

        } else if (responseString != null) {
            printTicket(TpsGamesClass.getInstance().getSportLoater(responseString));
        }

        if(TpsGamesClass.getInstance().getDeviceName().contains("TPS580")){
            TpsGamesClass.getInstance().closeScreen(this);

            TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
            String url1 = "/sdcard/rainbow_header.png";
            TpsGamesClass.getInstance().pleaseWait("Printing.....", url1);
            TpsGamesClass.getInstance().displayScreen(this);
        }


    }

    private void printTicket(final SportsLotteryTktBean tktBean) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                int serialNo = 1;
                try {
                    int i = ThermalPrinter.checkStatus();
                    System.out.println("Printer Status " + i);
                    if (i == 1) {
                        ThermalPrinter.stop();
                        setResult(104, new Intent());
                        finish();
                    }
                } catch (Error e) {
                } catch (TelpoException e) {
                    e.printStackTrace();
                }
                try {
                    ThermalPrinter.start();
                } catch (TelpoException e) {
                    e.printStackTrace();
                }
                try {
                    if (true) {
                        ThermalPrinter.setLineSpace(6);
                        ThermalPrinter.setFontSize(2);
                        ThermalPrinter.setGray(1);
                        PrintClass.printTitle("Skilrock");
                        ThermalPrinter.addString("\n\n");
                        PrintClass.printTitle(tktBean.getGameName());
                        ThermalPrinter.walkPaper(15);
                        ThermalPrinter.addString("\n\n");
                        PrintClass.printTwoString(tktBean.getCurrDate(), tktBean.getCurrTime());
                        ThermalPrinter.addString("\n\n");

                        TpsGamesClass.getInstance().setStringPreferences(Utility.LAST_TXT_NUMBER_SPORTS, tktBean.getTrxId());
                        TpsGamesClass.getInstance().setStringPreferences(Utility.LAST_TICKET_NUMBER_SPORTS, tktBean.getTicketNo());

                        PrintClass.printTwoString("TicNo", tktBean.getTicketNo());
                        ThermalPrinter.addString("\n\n");
                        PrintClass.printLine();
                        ThermalPrinter.addString("\n\n");

                        PrintClass.printTwoString("Draw Date", "Draw Time");
                        PrintClass.printTwoString(tktBean.getDrawDate(), tktBean.getDrawTime());
                        ThermalPrinter.addString("\n--------------------------------\n");
//                        ThermalPrinter.addString("\n\n");
                        ThermalPrinter.printString();

                        ThermalPrinter.setFontSize(2);
                        ThermalPrinter.enlargeFontSize(1, 2);
                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                        ThermalPrinter.addString(tktBean.getDrawName());
                        ThermalPrinter.addString("\n--------------------------------\n");
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
                        ThermalPrinter.printString();
                        int lenghtOfMatch;
                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_RIGHT);
                        ThermalPrinter.enlargeFontSize(1, 2);
                        if (tktBean.getGameTypeName().equalsIgnoreCase("Soccer 4") || tktBean.getGameTypeName().equalsIgnoreCase("Soccer 6")) {
                            ThermalPrinter.addString("H+" + " " + "H" + " " + "D" + " " + "A" + " " + "A+ ");
                            lenghtOfMatch = 5;
                        } else {
                            ThermalPrinter.addString("H" + " " + "D" + " " + "A   ");
                            lenghtOfMatch = 3;
                        }
                        TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(Double.parseDouble(tktBean.getBalance()));

                        ThermalPrinter.printString();
                        for (int i = 0; i < tktBean.getDrawInfo().length; i++) {

                            ThermalPrinter.enlargeFontSize(1, 1);
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
                            PrintClass.printTwoString(match, str);
                            if (lenghtOfMatch == 5)
                                PrintClass.printTwoString("", "------------");
                            else
                                PrintClass.printTwoString("", "--------  ");
                        }

                        ThermalPrinter.printString();
                        ThermalPrinter.enlargeFontSize(1, 1);

//                        PrintClass.printTwoString("Unit Price($)", tktBean.getUnitPrice());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("No Of Lines", tktBean.getNoOfLines());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("Bet Amount", tktBean.getBetAmt());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("Total Amount($)", tktBean.getTotalAmt());

                        ThermalPrinter.addString("\n--------------------------------\n");
                        PrintClass.printTwoString("Unit Price(USD)", tktBean.getUnitPrice());
                        PrintClass.printTwoString("Betamount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(tktBean.getBetAmount()));
                        PrintClass.printTwoString("Total Amount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(tktBean.getBoardAmount()));
                        ThermalPrinter.addString("\n\n");
                        PrintClass.printLine();
                        ThermalPrinter.printString();
                        if (tktBean.getAllMesage() != null) {
                            ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                            String printMes = "";
                            for (int i = 0; i < tktBean.getAllMesage().length; i++) {


                                if (tktBean.getAllMesage()[i].contains("@")) {
                                    if (i == 0) {
                                        ThermalPrinter.addString(tktBean.getAllMesage()[i].split("[@]")[0] + "\n\n");
                                        if (tktBean.getAllMesage()[i].split("[@]").length > 1)
                                            printMes = tktBean.getAllMesage()[i].split("[@]")[1];
                                    } else {
                                        String[] printData = tktBean.getAllMesage()[i].split("[@]");
                                        PrintClass.printTwoString(printData[0], printData[1]);
                                    }

                                }


                            }
                            if (printMes.trim().length() > 0) {
                                ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                                ThermalPrinter.addString(printMes + "\n\n");
                            }
                            ThermalPrinter.addString("\n--------------------------------\n");
                            ThermalPrinter.printString();
                        }

                        PrintClass.printBarcode(tktBean.getBrCd());
                        ThermalPrinter.printString();
                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                        ThermalPrinter.addString(tktBean.getBrCd());
                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                        ThermalPrinter.addString("\n\n");
//                        ThermalPrinter.addString("TEST RETAILER");
                        ThermalPrinter.printString();
                        ThermalPrinter.addString("********************************");
                        ThermalPrinter.printString();
                        ThermalPrinter.walkPaper(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    ThermalPrinter.stop();
                    TpsGamesClass.getInstance().closeScreen(PrintActivitySportsGame.this);

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                TpsGamesClass.getInstance().closeScreen(PrintActivitySportsGame.this);
                Intent intent = new Intent();
                intent.putExtra("resetRequest", "resetData");
                setResult(59, intent);
                finish();
            }
        }.execute();
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

    Printer printer;

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

    private boolean isReponsePrint = false;

    private boolean createReceiptData() {
        StringBuilder textData = new StringBuilder();
        final int barcodeWidth = 2;
        final int barcodeHeight = 100;
        SportsLotteryTktBean tktBean = TpsGamesClass.getInstance().getSportLoater(responseString);
        if (printer == null) {
            return false;
        }
        try {
            printer.addText("******************************************\n");

            printer.addFeedLine(6);
            printer.addTextSize(2, 2);
            printer.addTextSize(1, 2);
            printer.addTextFont(2);

            PrintClass.printTitleAtTop("SKILROCK", printer);
            printer.addTextSize(1, 2);
            PrintClass.printTitleSub(tktBean.getGameName(), printer);
            PrintClass.printTwoString(tktBean.getCurrDate(), tktBean.getCurrTime(), printer);

            TpsGamesClass.getInstance().setStringPreferences(Utility.LAST_TXT_NUMBER_SPORTS, tktBean.getTrxId());
            TpsGamesClass.getInstance().setStringPreferences(Utility.LAST_TICKET_NUMBER_SPORTS, tktBean.getTicketNo());

            PrintClass.printTwoString("TicNo", tktBean.getTicketNo(), printer);
            printer.addText("------------------------------------------\n");

            TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(Double.parseDouble(tktBean.getBalance()));

            PrintClass.printTwoString("Draw Date", "Draw Time", printer);
            PrintClass.printTwoString(tktBean.getDrawDate(), tktBean.getDrawTime(), printer);
            printer.addText("------------------------------------------\n");
//                        ThermalPrinter.addString("\n\n");

            printer.addTextSize(1, 2);
            printer.addTextAlign(Printer.ALIGN_CENTER);
            printer.addText(tktBean.getDrawName() + "\n");
            printer.addText("------------------------------------------\n");
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
            int lenghtOfMatch;
            printer.addTextAlign(Printer.ALIGN_CENTER);
            printer.addTextSize(1, 2);
            if (tktBean.getGameTypeName().equalsIgnoreCase("Soccer 4") || tktBean.getGameTypeName().equalsIgnoreCase("Soccer 6")) {
                printer.addTextAlign(Printer.ALIGN_RIGHT);
                printer.addText("H+" + " " + "H" + " " + "D" + " " + "A" + " " + "A+ ");
                lenghtOfMatch = 5;
            } else {
                printer.addTextAlign(Printer.ALIGN_RIGHT);
                printer.addText("H" + " " + "D" + " " + "A   ");
                lenghtOfMatch = 3;
            }

            printer.addTextAlign(Printer.ALIGN_CENTER);
            for (int i = 0; i < tktBean.getDrawInfo().length; i++) {

                printer.addTextSize(1, 1);
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
                PrintClass.printTwoString(match, str, printer);
                if (lenghtOfMatch == 5)
                    PrintClass.printTwoString("", "------------", printer);
                else
                    PrintClass.printTwoString("", "--------  ", printer);
            }

            printer.addTextSize(1, 1);

//                        PrintClass.printTwoString("Unit Price($)", tktBean.getUnitPrice());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("No Of Lines", tktBean.getNoOfLines());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("Bet Amount", tktBean.getBetAmt());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("Total Amount($)", tktBean.getTotalAmt());

            printer.addText("------------------------------------------\n");
            PrintClass.printTwoString("Unit Price(USD)", tktBean.getUnitPrice(), printer);
            PrintClass.printTwoString("Betamount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(tktBean.getBetAmount()), printer);
            PrintClass.printTwoString("Total Amount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(tktBean.getBoardAmount()), printer);
            printer.addText("------------------------------------------\n");
            if (tktBean.getAllMesage() != null) {
                printer.addTextAlign(Printer.ALIGN_CENTER);
                String printMes = "";
                for (int i = 0; i < tktBean.getAllMesage().length; i++) {


                    if (tktBean.getAllMesage()[i].contains("@")) {
                        if (i == 0) {
                            printer.addText(tktBean.getAllMesage()[i].split("[@]")[0] + "\n\n");
                            if (tktBean.getAllMesage()[i].split("[@]").length > 1)
                                printMes = tktBean.getAllMesage()[i].split("[@]")[1];
                        } else {
                            String[] printData = tktBean.getAllMesage()[i].split("[@]");
                            PrintClass.printTwoString(printData[0], printData[1], printer);
                        }

                    }


                }
                if (printMes.trim().length() > 0) {
                    printer.addTextAlign(Printer.ALIGN_CENTER);
                }
                printer.addText("------------------------------------------\n");
            }

            printer.addTextAlign(Printer.ALIGN_CENTER);
            String barcode = tktBean.getBrCd();
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
            printer.addTextAlign(Printer.ALIGN_CENTER);
            printer.addText(tktBean.getBrCd());
            printer.addTextAlign(Printer.ALIGN_CENTER);
            printer.addText("\n******************************************\n");
            printer.addCut(Printer.CUT_FEED);
        } catch (Exception e) {

        } finally {
            isReponsePrint = true;
            TpsGamesClass.getInstance().closeScreen(PrintActivitySportsGame.this);
            Intent intent = new Intent();
            intent.putExtra("resetRequest", "resetData");
            setResult(59, intent);
            PrintActivitySportsGame.this.finish();
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

    TextView dialogHeaderText;
    private ArrayList<String> printerList = null;
    private ArrayList<String> printerTarget = null;
    private ArrayAdapter printerListAdapter = null;
    String selectedPrinter, selectedTarget;
    private Dialog dialog;

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
}
