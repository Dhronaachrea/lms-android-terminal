package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.pt.printer.Printer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.telpo.tps550.api.InternalErrorException;
import com.telpo.tps550.api.printer.ThermalPrinter;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintTouseiClass;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryTktBean;
//import com.telpo.tps550.api.printer.ThermalPrinter;
//
//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.PrintTouseiClass;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.SportsLotteryTktBean;

/**
 * Created by stpl on 9/22/2016.
 */
public class PrintActivitySportsGameTousei extends Activity {

    String responseString;
    Printer printer;
    Integer pos;
    PrintTouseiClass printerClass;
    String saleResponseSle = "currDate:2016-09-22|currTime:12:06:45|ticketNo:525562266171280030|brCd:525562266171280030|trxId:50054|ticketAmt:16.00|balance:9974107.57|gameId:1|gameTypeId:1|gameName:Soccer|gameTypeName:Soccer 13|drawInfo:2016-09-25,06:00:00,SUNDAY-S13,909,15,1.0,1.00,16.00,16~AL--vs-GUA@A#AL--vs-BEI@H,A#ARS-vs-BEI@H#ARS-vs-GUA@A#AL -vs-GUA@H,A#AL -vs-AL-@H#FC -vs-BEI@A#BEI-vs-AL-@H,A#AL--vs-GUA@H#AL -vs-ARS@A#AL -vs-ARS@H,A#AL--vs-FC @H#AL--vs-BEI@AjackpotMsg:Current Jackpot Fund@(All Prizes Parimutuel)~13 out Of 13 Match@10000.00 USD|";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_print_activity);
        responseString = getIntent().getExtras().getString("response");
        pos = getIntent().getExtras().getInt("selectedPosition");
        TpsGamesClass.printBoolean = true;
        PrintActivityAllGamesTousei.printTouseiBoolean=false;
        Bitmap bitmap1 = BitmapFactory.decodeResource(PrintActivitySportsGameTousei.this.getResources(), R.drawable.banner_tousei);
        TpsGamesClass.getInstance().displayImagebyAbsolutePath("Printing.....", bitmap1);
        while (TpsGamesClass.printBoolean) {

        }

        printTicket(TpsGamesClass.getInstance().getSportLoater(responseString));
        TpsGamesClass.printBoolean = true;
    }

    private void printTicket(final SportsLotteryTktBean tktBean) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                int serialNo = 1;
                printer = new Printer();
                printer.open();
                printerClass = new PrintTouseiClass(printer);
//                try {
//                    int i = ThermalPrinter.checkStatus();
//                    System.out.println("Printer Status " + i);
//                    if (i == 1) {
//                        ThermalPrinter.stop();
//                        setResult(104, new Intent());
//                        finish();
//                    }
//                } catch (Error e) {
//                } catch (TelpoException e) {
//                    e.printStackTrace();
//                }
//                try {
//                    ThermalPrinter.start();
//                } catch (TelpoException e) {
//                    e.printStackTrace();
//                }
                try {
                    if (true) {
//                        ThermalPrinter.setLineSpace(6);
//                        ThermalPrinter.setFontSize(2);
//                        ThermalPrinter.setGray(1);
//                        PrintClass.printTitle("Skilrock");
                        printerClass.printTitleAtTop("Skilrock");
                        // ThermalPrinter.addString("\n\n");
                        // PrintClass.printTitle(tktBean.getGameName());
                        printerClass.printSubTitle(tktBean.getGameName());
//                        ThermalPrinter.walkPaper(15);
//                        ThermalPrinter.addString("\n\n");
                        printerClass.add2String(tktBean.getCurrDate(), tktBean.getCurrTime());
                        // PrintClass.printTwoString(tktBean.getCurrDate(), tktBean.getCurrTime());
                        //  ThermalPrinter.addString("\n\n");
                        printerClass.add2String("TicNo", tktBean.getTicketNo());
                        //PrintClass.printTwoString("TicNo", tktBean.getTicketNo());
                        // ThermalPrinter.addString("\n\n");
                        //  PrintClass.printLine();
                        //ThermalPrinter.addString("\n\n");
                        printer.printString("--------------------------------");
                        printerClass.add2String("Draw Date", "Draw Time");
                        printerClass.add2String(tktBean.getDrawDate(), tktBean.getDrawTime());
                        //  PrintClass.printTwoString("Draw Date", "Draw Time");
                        // PrintClass.printTwoString(tktBean.getDrawDate(), tktBean.getDrawTime());
                        TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(Double.parseDouble(tktBean.getBalance()));
                        printer.printString("--------------------------------");
                        //ThermalPrinter.addString("\n--------------------------------\n");
//                        ThermalPrinter.addString("\n\n");
//                        ThermalPrinter.printString();
//
//                        ThermalPrinter.setFontSize(2);
//                        ThermalPrinter.enlargeFontSize(1, 2);
//                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                        printer.setAlignment(1);
                        printerClass.printSubTitle(tktBean.getDrawName());
                        printer.setAlignment(0);
                        printer.printString("--------------------------------");
                        printer.setAlignment(0);
                        //ThermalPrinter.addString(tktBean.getDrawName());
                        // ThermalPrinter.addString("\n--------------------------------\n");
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


                        // ThermalPrinter.printString();
                        int lenghtOfMatch;
//                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_RIGHT);
//                        ThermalPrinter.enlargeFontSize(1, 2);

                        printer.setAlignment(2);
                        printer.setFontHeightZoomIn(2);
                        printer.setFontwidthZoomIn(1);

                        if (tktBean.getGameTypeName().equalsIgnoreCase("Soccer 4") || tktBean.getGameTypeName().equalsIgnoreCase("Soccer 6")) {
                            //ThermalPrinter.addString("H+" + " " + "H" + " " + "D" + " " + "A" + " " + "A+ ");
                            printer.printString("H+" + " " + "H" + " " + "D" + " " + "A" + " " + "A+ ");
                            lenghtOfMatch = 5;
                        } else {
                            // ThermalPrinter.addString("H" + " " + "D" + " " + "A   ");
                            printer.printString("H" + " " + "D" + " " + "A   ");
                            lenghtOfMatch = 3;
                        }

                        // ThermalPrinter.printString();
                        for (int i = 0; i < tktBean.getDrawInfo().length; i++) {

                            // ThermalPrinter.enlargeFontSize(1, 1);
                            printer.setFontwidthZoomIn(1);
                            printer.setFontHeightZoomIn(1);
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
                            printerClass.add2String(match, str);
                            // PrintClass.printTwoString(match, str);
                            if (lenghtOfMatch == 5)
                                printerClass.add2String("", "------------");
                                // PrintClass.printTwoString("", "------------");
                            else
                                printerClass.add2String("", "--------  ");
                            //PrintClass.printTwoString("", "--------  ");
                        }

//                        ThermalPrinter.printString();
//                        ThermalPrinter.enlargeFontSize(1, 1);
                        printer.setFontHeightZoomIn(1);
                        printer.setFontwidthZoomIn(1);
//                        PrintClass.printTwoString("Unit Price($)", tktBean.getUnitPrice());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("No Of Lines", tktBean.getNoOfLines());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("Bet Amount", tktBean.getBetAmt());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("Total Amount($)", tktBean.getTotalAmt());
                        printer.printString("--------------------------------");
                        printerClass.add2String("Unit Price(USD)", tktBean.getUnitPrice());
                        printerClass.add2String("Betamount(USD)", tktBean.getBetAmount());
                        printerClass.add2String("Total Amount(USD)", tktBean.getBoardAmount());
                        printer.printString("--------------------------------");
//                        ThermalPrinter.addString("\n--------------------------------\n");
//                        PrintClass.printTwoString("Unit Price(USD)", tktBean.getUnitPrice());
//                        PrintClass.printTwoString("Betamount(USD)",tktBean.getBetAmount());
//                        PrintClass.printTwoString("Total Amount(USD)",tktBean.getBoardAmount());
//                        ThermalPrinter.addString("\n\n");
                        // PrintClass.printLine();
                        //  ThermalPrinter.printString();
                        if (tktBean.getAllMesage() != null) {
                            printer.setAlignment(1);
                            //  ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                            String printMes = "";
                            for (int i = 0; i < tktBean.getAllMesage().length; i++) {


                                if (tktBean.getAllMesage()[i].contains("@")) {
                                    if (i == 0) {
                                        printer.printString(tktBean.getAllMesage()[i].split("[@]")[0]);
                                        //  ThermalPrinter.addString(tktBean.getAllMesage()[i].split("[@]")[0] + "\n\n");
                                        if (tktBean.getAllMesage()[i].split("[@]").length > 1)
                                            printMes = tktBean.getAllMesage()[i].split("[@]")[1];
                                    } else {
                                        String[] printData = tktBean.getAllMesage()[i].split("[@]");
                                        printerClass.add2String(printData[0], printData[1]);
                                        // PrintClass.printTwoString(printData[0],printData[1]);
                                    }

                                }


                            }
                            if (printMes.trim().length() > 0) {
                                printer.setAlignment(1);
                                printer.printString(printMes);
                                printer.setAlignment(0);
                                printer.printString("--------------------------------");
//                                ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
//                                ThermalPrinter.addString(printMes + "\n\n");
                            }

//                            ThermalPrinter.addString("\n--------------------------------\n");
//                            ThermalPrinter.printString();
                        }

                        //   printerClass.printBarcode(tktBean.getBrCd());
                        String barcode =tktBean.getBrCd();
                        Bitmap bitmap = null;
                        if (barcode != null && barcode.length() != 0 && barcode.length() <= 20 && barcode.length() >= 17) {
                            bitmap = CreateCode(barcode, BarcodeFormat.CODE_128, 376, 128);
                        }
                        if (bitmap != null) {
                            printer.printPicture(bitmap, bitmap.getWidth(), bitmap.getHeight());
                        }

                        printer.setAlignment(1);
                        printer.printString(tktBean.getBrCd());


                        printer.setAlignment(0);
                        printer.printString("********************************");
                        printer.printBlankLines(10);
                        for (int i = 0; i < 5; i++)
                            printer.printString("      ");
//                        PrintClass.printBarcode(tktBean.getBrCd());
//                        ThermalPrinter.printString();
//                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
//                        ThermalPrinter.addString(tktBean.getBrCd());
//                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
//                        ThermalPrinter.addString("\n\n");
//                        ThermalPrinter.addString("TEST RETAILER");
//                        ThermalPrinter.printString();
//                        ThermalPrinter.addString("********************************");
//                        ThermalPrinter.printString();
//                        ThermalPrinter.walkPaper(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    ThermalPrinter.stop();
                    printer.close();

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                TpsGamesClass.getInstance().closeScreen(PrintActivitySportsGameTousei.this);
                Intent intent = new Intent();
                intent.putExtra("resetRequest", "resetData");
                setResult(59, intent);
                finish();
            }
        }.execute();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  TpsGamesClass.getInstance().changeBackScreen(pos);
    }
}
