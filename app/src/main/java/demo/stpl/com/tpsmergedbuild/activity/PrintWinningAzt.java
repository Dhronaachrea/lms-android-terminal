package demo.stpl.com.tpsmergedbuild.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.telpo.tps550.api.printer.ThermalPrinter;

import a1088sdk.PrnDspA1088Activity;
import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.GameSale;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.beans.WinningBean;

/**
 * Created by stpl on 01-Nov-16.
 */
public class PrintWinningAzt extends PrnDspA1088Activity {
    String saleResponse;
    private boolean isReponsePrint = false;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        isReponsePrint = false;
        setContentView(R.layout.layout_print_activity);
        saleResponse = getIntent().getStringExtra("response");
//        TpsGamesClass.getInstance().showAToast(saleResponse,this, Toast.LENGTH_LONG);
        System.setProperty("file.encoding", "gb2312");
//        TpsGamesClass.getInstance().showAToast("inside",this, Toast.LENGTH_SHORT);
        printAzt();

    }

    protected void printAzt() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                WinningBean winningBean = null;
                GameSale gameSale = null;
                try {
                    winningBean = TpsGamesClass.getInstance(PrintWinningAzt.this).getGson().fromJson(saleResponse, WinningBean.class);
                    gameSale = TpsGamesClass.getInstance(PrintWinningAzt.this).getGson().fromJson(saleResponse, GameSale.class);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
//                    if (TpsGamesClass.getInstance().getTopMwessage() != null && TpsGamesClass.getInstance().getTopMwessage() != null) {
//                        ThermalPrinter.addString(TpsGamesClass.getInstance().getTopMwessage() + "\n");
//                        TpsGamesClass.getInstance().setTopMessage("");
//                    }

                try {
                    String printString;
                    printString = "************************************************\n";

                    //print above message
                    if (winningBean.getAdvMsg() != null && winningBean.getAdvMsg().getTOP() != null) {
                        String[] topMsg = winningBean.getAdvMsg().getTOP();
                        for (int i = 0; i < topMsg.length; i++) {
                            printString = printString + PrintClass.printInMiddle(topMsg[i] + "");
                            printString = printString + "************************************************\n";
                        }
                    }


                    printString = printString + PrintClass.printInMiddle("SKILROCK");
                    printString = printString + PrintClass.printInMiddle(winningBean.getGameName());


                    printString = printString + PrintClass.printTwoString("Ticket No :", winningBean.getTicketNumber(), "");
//                    PrintClass.printTwoString(PrintClass.getPrintDateFormat(winningBean.getBarcodeCount().), gameSale.getMainData().getCommonSaleData().getPurchaseTime());
                    printString = printString + "------------------------------------------------\n";

                    printString = printString + PrintClass.printTwoString("Draw Date", "Draw Time", "");

                    for (int i = 0; i < winningBean.getDrawData().length; i++) {
                        printString = printString + PrintClass.printTwoString(PrintClass.getPrintDateFormat(winningBean.getDrawData()[i].getDrawDate()), winningBean.getDrawData()[i].getDrawTime(), "");
                        if (winningBean.getDrawData()[i].getWinStatus() != null) {
                            printString = printString + PrintClass.printTwoString("WinStatus", winningBean.getDrawData()[i].getWinStatus(), "");
                        }
                        if (winningBean.getDrawData()[i].getWinningAmt() != null) {
                            printString = printString + PrintClass.printTwoString("WinningAmt", winningBean.getDrawData()[i].getWinningAmt(), "");
                        }


                    }

                    printString = printString + "------------------------------------------------\n";

                    byte[] dataBt = printString.getBytes("gb2312");
                    PRN_SendData(dataBt, dataBt.length);

//
//                ThermalPrinter.printString();
                    String bottomMsg = "\n";
                    if (winningBean.getBarcodeCount() != null) {
                        String strBarcode = winningBean.getBarcodeCount();
                        byte dataBar3[] = strBarcode.getBytes();

                        PRN_PrintBarcode(dataBar3,
                                dataBar3.length, BCS_Code128, 50, 2, ALIGNMENT_LEFT, BC_TEXT_BELOW);

                        bottomMsg = PrintClass.printInMiddle(winningBean.getBarcodeCount());
                    }

//               PrintClass.printBarcode(winningBean.getTicketNumber());


//                ThermalPrinter.printString();


//               ThermalPrinter.printString();
//               ThermalPrinter.addString("********************************\n");
                    bottomMsg = bottomMsg + "************************************************\n";
                    if (winningBean.getAdvMsg() != null && winningBean.getAdvMsg().getBOTTOM() != null) {
                        String[] bottom = winningBean.getAdvMsg().getBOTTOM();
                        for (int i = 0; i < bottom.length; i++) {
                            bottomMsg = bottomMsg + PrintClass.printInMiddle(bottom[i] + "");
                            bottomMsg = bottomMsg + "************************************************\n";
                        }
                    }

                    byte[] dataBtBottom = bottomMsg.getBytes("gb2312");
                    PRN_SendData(dataBtBottom, dataBtBottom.length);
                    PRN_LineFeed(5);

                    PRN_CutPaper();

                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TpsGamesClass.getInstance().showAToast(e.toString().toString(), PrintWinningAzt.this, Toast.LENGTH_LONG);
                        }
                    });

                } finally {
                    ThermalPrinter.stop();
                    isReponsePrint = true;
                    TpsGamesClass.getInstance().closeScreen(PrintWinningAzt.this);
                    PrintWinningAzt.this.finish();
                }


            }


        }).start();

    }
}
