package demo.stpl.com.tpsmergedbuild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

import a1088sdk.PrnDspA1088Activity;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.GameSale;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

//import a1088sdk.PrnDspA1088Activity;
//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.GameSale;
//importdemo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

/**
 * Created by stpl on 27-Oct-16.
 */
public class PrintActivityAZT extends PrnDspA1088Activity {

    //rk30
    private String printString;
    private boolean isReponsePrint = false;
    String[] sunSin = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        isReponsePrint = false;
        setContentView(R.layout.layout_print_activity);
        System.setProperty("file.encoding", "gb2312");
//        TpsGamesClass.getInstance().showAToast("inside",this, Toast.LENGTH_SHORT);
        printReceipt();

    }

    protected void printReceipt() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GameSale gameSale = null;
                    try {

                        gameSale = TpsGamesClass.getInstance(PrintActivityAZT.this).getGson().fromJson(TpsGamesClass.getInstance().getPrintResponseForAzt(), GameSale.class);
                        TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(gameSale.getMainData().getAvlblCreditAmt());
                    } catch (Exception e) {
                        e.getLocalizedMessage();
                    }
//                    if (TpsGamesClass.getInstance().getTopMwessage() != null && TpsGamesClass.getInstance().getTopMwessage() != null) {
//                        printString = printString +
//                        ThermalPrinter.addString(TpsGamesClass.getInstance().getTopMwessage() + "\n");
//                        TpsGamesClass.getInstance().setTopMessage("");
//                    }

                    printString = "************************************************\n";
//                            ThermalPrinter.addString();

                    //print above message
                    if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getTOP() != null) {
                        String[] topMsg = gameSale.getMainData().getAdvMessage().getTOP();
                        for (int i = 0; i < topMsg.length; i++) {
                            printString = printString + topMsg[i] + "\n";
                            printString = printString + "************************************************\n";
                        }
                    }

//                    ThermalPrinter.setLineSpace(6);
//                    ThermalPrinter.setFontSize(2);
//                    ThermalPrinter.setGray(1);

//                    PrintClass.printTitleAtTop("SKILROCK");
                    printString = printString + PrintClass.printInMiddle("SKILROCK");
                    printString = printString + PrintClass.printInMiddle(gameSale.getMainData().getCommonSaleData().getGameName());

                    printString = printString + PrintClass.printTwoString("Ticket No :", gameSale.getMainData().getCommonSaleData().getTicketNumber(), "") + "\n";
                    printString = printString + PrintClass.printTwoString(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getPurchaseDate()), gameSale.getMainData().getCommonSaleData().getPurchaseTime(), "") + "\n";
//                    PrintClass.printTwoString();
                    printString = printString + "------------------------------------------------\n";
//                    ThermalPrinter.addString("");
                    printString = printString + PrintClass.printTwoString("Draw Date", "Draw Time", "");

                    for (int i = 0; i < gameSale.getMainData().getCommonSaleData().getDrawData().length; i++) {
                        printString = printString + PrintClass.printTwoString(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getDrawData()[i].getDrawDate()), gameSale.getMainData().getCommonSaleData().getDrawData()[i].getDrawTime(), "");
                    }

                    printString = printString + "------------------------------------------------\n";

                    for (int i = 0; i < gameSale.getMainData().getBetTypeData().length; i++) {

                        String isQp = gameSale.getMainData().getBetTypeData()[i].isQp() == true ? "QP" : "Manual";
                        printString = printString + PrintClass.printTwoString(gameSale.getMainData().getBetTypeData()[i].getBetName(), isQp, "");
                        String numberString = "";
                        if (gameSale.getMainData().getBetTypeData()[i].getBetName().equalsIgnoreCase("oneToTwelve")) {
                            numberString = gameSale.getMainData().getBetTypeData()[i].getPickedNumbers() + " - " + sunSin[Integer.parseInt(gameSale.getMainData().getBetTypeData()[i].getPickedNumbers()) - 1];
                        } else {
                            numberString = gameSale.getMainData().getBetTypeData()[i].getPickedNumbers();
                        }
                        printString = printString + PrintClass.printInMiddle(numberString);
//                        PrintClass.printMiddle(numberString);
                        printString = printString + PrintClass.printTwoString("No. of Line(s)", gameSale.getMainData().getBetTypeData()[i].getNoOfLines() + "", "") + "\n";
                        if (gameSale.getMainData().getBetTypeData()[i].getBetName().equalsIgnoreCase("roulette")) {

                            printString = printString + PrintClass.printTwoString("Bet Amount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(gameSale.getMainData().getBetTypeData()[i].getPanelPrice() + ""), "") + "\n";
                        } else {
                            PrintClass.printTwoString("Bet Amount(USD)", gameSale.getMainData().getBetTypeData()[i].getPanelPrice() + "", "");
                        }
                        printString = printString + "------------------------------------------------\n";
//                        ThermalPrinter.addString("--------------------------------\n");
                    }
                    printString = printString + PrintClass.printTwoString("No. of Draws", gameSale.getMainData().getCommonSaleData().getDrawData().length + "", "");
                    printString = printString + PrintClass.printTwoString("Total Amount(USD)", TpsGamesClass.getInstance().getAmountWithTwoDecim(gameSale.getMainData().getCommonSaleData().getPurchaseAmt() + ""), "");

                    byte[] dataBt = printString.getBytes("gb2312");
                    PRN_SendData(dataBt, dataBt.length);


                    String strBarcode = gameSale.getMainData().getCommonSaleData().getBarcodeCount();
                    byte dataBar3[] = strBarcode.getBytes();

                    PRN_PrintBarcode(dataBar3,
                            dataBar3.length, BCS_Code128, 50, 2, ALIGNMENT_LEFT, BC_TEXT_BELOW);
//                    ThermalPrinter.printString();
//                    PrintClass.printBarcode(gameSale.getMainData().getCommonSaleData().getBarcodeCount());
//
//
                    String bottomMsg = "\n";
//                    ThermalPrinter.printString();
//
                    bottomMsg = PrintClass.printInMiddle(gameSale.getMainData().getCommonSaleData().getBarcodeCount());
//
//                    ThermalPrinter.printString();
//                    ThermalPrinter.addString("********************************\n");
//                    if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getBOTTOM() != null) {
//                        String[] bottom = gameSale.getMainData().getAdvMessage().getBOTTOM();
//                        for (int i = 0; i < bottom.length; i++) {
//                            ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
//                            ThermalPrinter.addString(bottom[i] + "\n");
//                            ThermalPrinter.addString("********************************\n");
//                        }
//                    }
//                    ThermalPrinter.printString();
//
//                    ThermalPrinter.walkPaper(100);


                    if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getBOTTOM() != null) {
                        String[] bottom = gameSale.getMainData().getAdvMessage().getBOTTOM();
                        bottomMsg = bottomMsg + "************************************************\n";
                        for (int i = 0; i < bottom.length; i++) {

                            bottomMsg = bottomMsg + bottom[i] + "\n";
                            bottomMsg = bottomMsg + "************************************************\n";
                        }
                    }
                    byte[] dataBtBottom = bottomMsg.getBytes("gb2312");
                    PRN_SendData(dataBtBottom, dataBtBottom.length);
                    PRN_LineFeed(5);

                    PRN_CutPaper();


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    TpsGamesClass.getInstance().showAToast(e.toString(), PrintActivityAZT.this, Toast.LENGTH_SHORT);
                } finally {
//                    ThermalPrinter.stop();
                    isReponsePrint = true;
//                    TpsGamesClass.getInstance().closeScreen(PrintActivityAllGames.this);
                    Intent intent = new Intent();
                    intent.putExtra("resetRequest", "resetData");
                    setResult(054, intent);
                    PrintActivityAZT.this.finish();
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
