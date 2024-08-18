package demo.stpl.com.tpsmergedbuild.activity;

import android.content.Intent;
import android.os.Bundle;

import a1088sdk.PrnDspA1088Activity;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.Utility;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.SportsLotteryTktBean;

/**
 * Created by stpl on 01-Nov-16.
 */
public class PrintActivitySportsAzt extends PrnDspA1088Activity {
    private boolean isReponsePrint = false;
    private SportsLotteryTktBean tktBean;
    private String printString;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        isReponsePrint = false;
        setContentView(R.layout.layout_print_activity);
        System.setProperty("file.encoding", "gb2312");
        String responseString = getIntent().getExtras().getString("response");
        tktBean = TpsGamesClass.getInstance().getSportLoater(responseString);
//        TpsGamesClass.getInstance().showAToast("inside",this, Toast.LENGTH_SHORT);
        printReceipt();

    }

    private void printReceipt() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (true) {
                        printString = "************************************************\n";
                        printString = printString + PrintClass.printInMiddle("SKILROCK");
                        printString = printString + PrintClass.printInMiddle(tktBean.getGameName());
//                        PrintClass.printTitle(tktBean.getGameName());
//                        ThermalPrinter.walkPaper(15);
//                        ThermalPrinter.addString("\n\n");
                        printString = printString + PrintClass.printTwoString(tktBean.getCurrDate(), tktBean.getCurrTime(), "");
//                        ThermalPrinter.addString("\n\n");

                        TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(Double.parseDouble(tktBean.getBalance()));

                        TpsGamesClass.getInstance().setStringPreferences(Utility.LAST_TXT_NUMBER_SPORTS, tktBean.getTrxId());
                        TpsGamesClass.getInstance().setStringPreferences(Utility.LAST_TICKET_NUMBER_SPORTS, tktBean.getTicketNo());

                        printString = printString + PrintClass.printTwoString("TicNo", tktBean.getTicketNo(), "");
                        printString = printString + "------------------------------------------------\n";

                        printString = printString + PrintClass.printTwoString("Draw Date", "Draw Time", "");
                        printString = printString + PrintClass.printTwoString(tktBean.getDrawDate(), tktBean.getDrawTime(), "");
                        printString = printString + "------------------------------------------------\n";
//                        ThermalPrinter.addString("\n\n");
//                        ThermalPrinter.printString();

//                        ThermalPrinter.setFontSize(2);
//                        ThermalPrinter.enlargeFontSize(1, 2);
//                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                        printString = printString + PrintClass.printInMiddle(tktBean.getDrawName());
//                        ThermalPrinter.addString(tktBean.getDrawName());
                        printString = printString + "------------------------------------------------\n";
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
//                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_RIGHT);
//                        ThermalPrinter.enlargeFontSize(1, 2);
                        if (tktBean.getGameTypeName().equalsIgnoreCase("Soccer 4") || tktBean.getGameTypeName().equalsIgnoreCase("Soccer 6")) {
//                            ThermalPrinter.addString("H+" + " " + "H" + " " + "D" + " " + "A" + " " + "A+ ");
                            printString = printString + PrintClass.printInRight("H+" + " " + "H" + " " + "D" + " " + "A" + " " + "A+ ");
                            lenghtOfMatch = 5;
                        } else {
                            printString = printString + PrintClass.printInRight("H" + " " + "D" + " " + "A   ");
                            lenghtOfMatch = 3;
                        }

//                        ThermalPrinter.printString();
                        for (int i = 0; i < tktBean.getDrawInfo().length; i++) {

//                            ThermalPrinter.enlargeFontSize(1, 1);
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
                            printString = printString + PrintClass.printTwoString(match, str, "");
                            if (lenghtOfMatch == 5)
                                printString = printString + PrintClass.printTwoString("", "------------", "");
                            else
                                printString = printString + PrintClass.printTwoString("", "--------  ", "");
                        }

//                        ThermalPrinter.printString();
//                        ThermalPrinter.enlargeFontSize(1, 1);

//                        PrintClass.printTwoString("Unit Price($)", tktBean.getUnitPrice());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("No Of Lines", tktBean.getNoOfLines());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("Bet Amount", tktBean.getBetAmt());
//                        ThermalPrinter.addString("\n\n");
//                        PrintClass.printTwoString("Total Amount($)", tktBean.getTotalAmt());

                        printString = printString + "------------------------------------------------\n";
                        printString = printString + PrintClass.printTwoString("Unit Price(USD)", tktBean.getUnitPrice(), "");
                        printString = printString + PrintClass.printTwoString("Betamount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(tktBean.getBetAmount()), "");
                        printString = printString + PrintClass.printTwoString("Total Amount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(tktBean.getBoardAmount()), "");
                        printString = printString + "------------------------------------------------\n";
                        if (tktBean.getAllMesage() != null) {
//                            ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                            String printMes = "";
                            for (int i = 0; i < tktBean.getAllMesage().length; i++) {


                                if (tktBean.getAllMesage()[i].contains("@")) {
                                    if (i == 0) {
                                        printString = printString + PrintClass.printInMiddle(tktBean.getAllMesage()[i].split("[@]")[0] + "");
                                        if (tktBean.getAllMesage()[i].split("[@]").length > 1)
                                            printMes = tktBean.getAllMesage()[i].split("[@]")[1];
                                    } else {
                                        String[] printData = tktBean.getAllMesage()[i].split("[@]");
                                        printString = printString + PrintClass.printTwoString(printData[0], printData[1], "");
                                    }

                                }


                            }
                            if (printMes.trim().length() > 0) {
//                                ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
                                printString = printString + PrintClass.printInMiddle(printMes + "");
                            }
                            printString = printString + "------------------------------------------------\n";
//                            ThermalPrinter.printString();
                        }

                        byte[] dataBt = printString.getBytes("gb2312");
                        PRN_SendData(dataBt, dataBt.length);

                        String strBarcode = tktBean.getBrCd();
                        byte dataBar3[] = strBarcode.getBytes();

                        PRN_PrintBarcode(dataBar3,
                                dataBar3.length, BCS_Code128, 50, 2, ALIGNMENT_LEFT, BC_TEXT_BELOW);

//                        PrintClass.printBarcode(tktBean.getBrCd());
//                        ThermalPrinter.printString();
//                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);

                        String bottomMsg = "\n";
//                    ThermalPrinter.printString();
//
                        bottomMsg = PrintClass.printInMiddle(tktBean.getBrCd());
//                        ThermalPrinter.addString(tktBean.getBrCd());
////                        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
////                        ThermalPrinter.addString("\n\n");
////                        ThermalPrinter.addString("TEST RETAILER");
//                        ThermalPrinter.printString();
//                        ThermalPrinter.addString("********************************");
//                        ThermalPrinter.printString();
//                        ThermalPrinter.walkPaper(100);
                        bottomMsg = bottomMsg + "************************************************\n";
                        byte[] dataBtBottom = bottomMsg.getBytes("gb2312");
                        PRN_SendData(dataBtBottom, dataBtBottom.length);
                        PRN_LineFeed(5);

                        PRN_CutPaper();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    isReponsePrint = true;
//                    TpsGamesClass.getInstance().closeScreen(PrintActivityAllGames.this);
                    Intent intent = new Intent();
                    intent.putExtra("resetRequest", "resetData");
                    setResult(59, intent);
                    PrintActivitySportsAzt.this.finish();

                }
            }
        }).start();

    }

    @Override
    public void onBackPressed() {
        if (!isReponsePrint) {
            return;
        }
        super.onBackPressed();
    }
}
