package demo.stpl.com.tpsmergedbuild.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.telpo.tps550.api.TelpoException;
import com.telpo.tps550.api.printer.ThermalPrinter;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.SportsSaleBeanJson;
//
//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.beans.SportsSaleBeanJson;

/**
 * Created by stpl on 13-Oct-16.
 */
public class ActivityPrintSportsJson extends Activity {
    String responseString;
    SportsSaleBeanJson sportsSaleBeanJson;

    String saleResponseSle = "{\"responseCode\":0,\"tktData\":{\"gameTypeId\":2,\"reprintCount\":0,\"retailerName\":\"Test Retailer\",\"currSymbol\":\"USD\",\"gameId\":1,\"boardData\":[{\"drawId\":981,\"drawTime\":\"06:00:00\",\"drawName\":\"SUNDAY-S10\",\"unitPrice\":1,\"noOfLines\":24,\"eventData\":[{\"eventLeague\":\"AFC Champions League\",\"eventVenue\":\"Suwon\",\"eventCodeAway\":\"BEI\",\"eventDisplayHome\":\"Suwon\",\"eventDate\":\"2016-10-01 13:10:00.0\",\"eventDisplayAway\":\"Beijing Guoan\",\"selection\":\"D,A\",\"eventCodeHome\":\"SUW\"},{\"eventLeague\":\"CAF Champions League\",\"eventVenue\":\"Es setif\",\"eventCodeAway\":\"YOU\",\"eventDisplayHome\":\"Es setif\",\"eventDate\":\"2016-10-01 13:15:00.0\",\"eventDisplayAway\":\"Young Africans\",\"selection\":\"H\",\"eventCodeHome\":\"ESTF\"},{\"eventLeague\":\"AFC Champions League\",\"eventVenue\":\"Lekhwiya\",\"eventCodeAway\":\"FC \",\"eventDisplayHome\":\"Lekhwiya\",\"eventDate\":\"2016-10-01 13:15:00.0\",\"eventDisplayAway\":\"FC Seoul\",\"selection\":\"H\",\"eventCodeHome\":\"LEK\"},{\"eventLeague\":\"AFC Champions League\",\"eventVenue\":\"Seongnam\",\"eventCodeAway\":\"KAS\",\"eventDisplayHome\":\"Lekhwiya\",\"eventDate\":\"2016-10-01 13:25:00.0\",\"eventDisplayAway\":\"Kashiwa Reysol\",\"selection\":\"A\",\"eventCodeHome\":\"LEK\"},{\"eventLeague\":\"CAF Champions League\",\"eventVenue\":\"Audax Sao Paulo\",\"eventCodeAway\":\"YOU\",\"eventDisplayHome\":\"Al Hilal Omdurman\",\"eventDate\":\"2016-10-01 13:25:00.0\",\"eventDisplayAway\":\"Young Africans\",\"selection\":\"A\",\"eventCodeHome\":\"AHON\"},{\"eventLeague\":\"Belgium Pro League\",\"eventVenue\":\"Standard liege\",\"eventCodeAway\":\"CUU\",\"eventDisplayHome\":\"Standard liege\",\"eventDate\":\"2016-10-01 13:25:00.0\",\"eventDisplayAway\":\"Club brugge\",\"selection\":\"H,D\",\"eventCodeHome\":\"SADD\"},{\"eventLeague\":\"CAF Champions League\",\"eventVenue\":\"Qviding fif\",\"eventCodeAway\":\"ESTF\",\"eventDisplayHome\":\"El zamalek\",\"eventDate\":\"2016-10-01 13:40:00.0\",\"eventDisplayAway\":\"Es setif\",\"selection\":\"H,D\",\"eventCodeHome\":\"EAL\"},{\"eventLeague\":\"Belgium Pro League\",\"eventVenue\":\"Standard liege\",\"eventCodeAway\":\"KRRK\",\"eventDisplayHome\":\"Standard liege\",\"eventDate\":\"2016-10-01 13:40:00.0\",\"eventDisplayAway\":\"Kortrijk\",\"selection\":\"H\",\"eventCodeHome\":\"SADD\"},{\"eventLeague\":\"AFC Champions League\",\"eventVenue\":\"Perspolis\",\"eventCodeAway\":\"JEO\",\"eventDisplayHome\":\"Perspolis\",\"eventDate\":\"2016-10-01 13:45:00.0\",\"eventDisplayAway\":\"Jeonbuk\",\"selection\":\"H,D,A\",\"eventCodeHome\":\"PER\"},{\"eventLeague\":\"AFC Champions League\",\"eventVenue\":\"Lekhwiya\",\"eventCodeAway\":\"AUG\",\"eventDisplayHome\":\"Lekhwiya\",\"eventDate\":\"2016-10-01 13:45:00.0\",\"eventDisplayAway\":\"Augsburg\",\"selection\":\"H\",\"eventCodeHome\":\"LEK\"}],\"boardPrice\":24,\"drawDate\":\"2016-10-11\"}],\"eventType\":\"[H, D, A]\",\"gameName\":\"Soccer\",\"orgName\":\"AFRICA LOTTO\",\"jackpotMsg\":\"Current Jackpot Fund@(All Prizes Parimutuel)@10 out Of 10 Match@1000.00 USD\",\"purchaseTime\":\"06:23:22\",\"brCd\":\"107962274171280020\",\"balance\":\"19500.23\",\"ticketNo\":\"107962274171280020\",\"expiryPeriod\":\"\",\"purchaseDate\":\"2016-09-30\",\"gameTypeName\":\"Soccer 10\",\"trxId\":\"57449\",\"ticketAmt\":\"24.00\"},\"responseMsg\":\"Success\",\"sampleStatus\":\"NO\",\"mode\":\"Buy\"}\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("P01M") || TpsGamesClass.getInstance().getDeviceName().toLowerCase().contains("tps515")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        setContentView(R.layout.layout_print_activity);
//        responseString = getIntent().getExtras().getString("response");

        sportsSaleBeanJson = TpsGamesClass.getInstance(this).getGson().fromJson(saleResponseSle, SportsSaleBeanJson.class);
        TpsGamesClass.getInstance().closeScreen(this);

        TpsGamesClass.getInstance().saveImage("rainbow_header.png", this);
        String url = "/sdcard/rainbow_header.png";
        TpsGamesClass.getInstance().pleaseWait("Printing.....", url);
        TpsGamesClass.getInstance().displayScreen(this);
//        responseString = saleResponseSle;
        printTicket();

    }

    private void printTicket() {
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
                        PrintClass.printTitle(sportsSaleBeanJson.getTktData().getGameName());
                        ThermalPrinter.walkPaper(15);
                        ThermalPrinter.addString("\n\n");
                        PrintClass.printTwoString(sportsSaleBeanJson.getTktData().getPurchaseDate(), sportsSaleBeanJson.getTktData().getPurchaseTime());
                        ThermalPrinter.addString("\n\n");

                        PrintClass.printTwoString("TicNo", sportsSaleBeanJson.getTktData().getTicketNo());
                        ThermalPrinter.addString("\n\n");
                        PrintClass.printLine();
                        ThermalPrinter.addString("\n\n");

                        PrintClass.printTwoString("Draw Date", "Draw Time");
                        PrintClass.printTwoString(sportsSaleBeanJson.getTktData().getBoardData()[0].getDrawDate(), sportsSaleBeanJson.getTktData().getBoardData()[0].getDrawTime());
                        ThermalPrinter.addString("\n--------------------------------\n");
//                        ThermalPrinter.addString("\n\n");
                        ThermalPrinter.printString();

                        ThermalPrinter.setFontSize(2);
                        ThermalPrinter.enlargeFontSize(1, 2);
                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                        ThermalPrinter.addString(sportsSaleBeanJson.getTktData().getBoardData()[0].getDrawName());
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
                        if (sportsSaleBeanJson.getTktData().getGameTypeName().equalsIgnoreCase("Soccer 4") || sportsSaleBeanJson.getTktData().getGameTypeName().equalsIgnoreCase("Soccer 6")) {
                            ThermalPrinter.addString("H+" + " " + "H" + " " + "D" + " " + "A" + " " + "A+ ");
                            lenghtOfMatch = 5;
                        } else {
                            ThermalPrinter.addString("H" + " " + "D" + " " + "A   ");
                            lenghtOfMatch = 3;
                        }

                        ThermalPrinter.printString();
                        for (int i = 0; i < sportsSaleBeanJson.getTktData().getBoardData()[0].getEventData().length; i++) {

                            ThermalPrinter.enlargeFontSize(1, 1);
                            String match = "";
                            String winning = sportsSaleBeanJson.getTktData().getBoardData()[0].getEventData()[i].getSelection();
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
                            match = sportsSaleBeanJson.getTktData().getBoardData()[0].getEventData()[i].getEventCodeHome() + "vs" + sportsSaleBeanJson.getTktData().getBoardData()[0].getEventData()[i].getEventCodeAway();
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
                        PrintClass.printTwoString("Unit Price(USD)", sportsSaleBeanJson.getTktData().getBoardData()[0].getUnitPrice());
                        PrintClass.printTwoString("Betamount(USD)", sportsSaleBeanJson.getTktData().getBoardData()[0].getUnitPrice());
                        PrintClass.printTwoString("Total Amount(USD)", sportsSaleBeanJson.getTktData().getBoardData()[0].getBoardPrice());
                        ThermalPrinter.addString("\n\n");
                        PrintClass.printLine();
                        ThermalPrinter.printString();
//                        if (sportsSaleBeanJson.getTktData().getJackpotMsg() != null) {
//                            ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
//                            String printMes = "";
//
//                            for (int i = 0; i < tktBean.getAllMesage().length; i++) {
//
//
//                                if (tktBean.getAllMesage()[i].contains("@")) {
//                                    if(i == 0){
//                                        ThermalPrinter.addString(tktBean.getAllMesage()[i].split("[@]")[0] + "\n\n");
//                                        if (tktBean.getAllMesage()[i].split("[@]").length > 1)
//                                            printMes = tktBean.getAllMesage()[i].split("[@]")[1];
//                                    }else{
//                                        String[] printData = tktBean.getAllMesage()[i].split("[@]");
//                                        PrintClass.printTwoString(printData[0],printData[1]);
//                                    }
//
//                                }
//
//
//                            }
//                            if (printMes.trim().length() > 0) {
//                                ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
//                                ThermalPrinter.addString(printMes + "\n\n");
//                            }
//                            ThermalPrinter.addString("\n--------------------------------\n");
//                            ThermalPrinter.printString();
//                        }

                        PrintClass.printBarcode(sportsSaleBeanJson.getTktData().getTicketNo());
                        ThermalPrinter.printString();
                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                        ThermalPrinter.addString(sportsSaleBeanJson.getTktData().getBrCd());
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

                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                TpsGamesClass.getInstance().closeScreen(ActivityPrintSportsJson.this);
                finish();
            }
        }.execute();
    }
}
