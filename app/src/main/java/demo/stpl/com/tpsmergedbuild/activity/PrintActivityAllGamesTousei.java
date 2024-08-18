package demo.stpl.com.tpsmergedbuild.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.pt.printer.Printer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.telpo.tps550.api.InternalErrorException;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.GameSale;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
import demo.stpl.com.tpsmergedbuild.baseClass.PrintTouseiClass;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//
//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.GameSale;
//importdemo.stpl.com.tpsmergedbuild.baseClass.PrintClass;
//importdemo.stpl.com.tpsmergedbuild.baseClass.PrintTouseiClass;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

public class PrintActivityAllGamesTousei extends AppCompatActivity {

    Printer printer;
    PrintTouseiClass printerClass;
    String saleResponse;
    Integer pos;
    Bitmap bitmap1;
    public static boolean printTouseiBoolean=true;
    private boolean isReponsePrint = false;
    String[] sunSin = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_print_activity);
        saleResponse = getIntent().getStringExtra("response");
        pos = getIntent().getExtras().getInt("selectedPosition");
        printTouseiBoolean=false;
        if (saleResponse != null) {
            bitmap1 = BitmapFactory.decodeResource(PrintActivityAllGamesTousei.this.getResources(), R.drawable.banner_tousei);
            TpsGamesClass.printBoolean = true;

            TpsGamesClass.getInstance().displayImagebyAbsolutePath("Printing.....", bitmap1);
            while (TpsGamesClass.printBoolean) {

            }

            printReceipt();
            TpsGamesClass.printBoolean = true;
        }

//        TpsGamesClass.getInstance().saveImage("banner1(1).png", this);
//        String url1 = "/sdcard/banner1(1).png";
        // TpsGamesClass.getInstance().displayImagebyAbsolutePath("Printing.....", url1);


    }


    protected void printReceipt() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    printer = new Printer();
                    int ret = printer.open();
                    int ret1 = printer.queState();
                    printerClass = new PrintTouseiClass(printer);
                    if (ret != 0 || ret1 == 1) {
                        //   Toast.makeText(PrintActivityAllGamesTousei.this,"Please put the Paper roll properly",Toast.LENGTH_LONG).show();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(PrintActivityAllGamesTousei.this, "Please put the Paper roll", Toast.LENGTH_LONG).show();
                            }
                        });
                        return;
                    } else {
                        GameSale gameSale = null;
                        try {
                            gameSale = TpsGamesClass.getInstance(PrintActivityAllGamesTousei.this).getGson().fromJson(saleResponse, GameSale.class);
                            TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(gameSale.getMainData().getAvlblCreditAmt());
                        } catch (Exception e) {
                            e.getLocalizedMessage();
                        }
                        if (TpsGamesClass.getInstance().getTopMwessage() != null && TpsGamesClass.getInstance().getTopMwessage() != null) {

                            TpsGamesClass.getInstance().setTopMessage("");
                        }
                        printer.printString("********************************");

                        //print above message
                        if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getTOP() != null) {
                            String[] topMsg = gameSale.getMainData().getAdvMessage().getTOP();
                            for (int i = 0; i < topMsg.length; i++) {

                                printer.setAlignment(1);
                                printer.printString(topMsg[i]);
                                printer.setAlignment(0);
                                printer.printString("********************************");
                            }
                        }

                        printerClass.printTitleAtTop("SKILROCK");
                        printerClass.printSubTitle(gameSale.getMainData().getCommonSaleData().getGameName());
                        printerClass.add2String("Ticket No :", gameSale.getMainData().getCommonSaleData().getTicketNumber());
                        printerClass.add2String(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getPurchaseDate()), gameSale.getMainData().getCommonSaleData().getPurchaseTime());
                        printer.printString("--------------------------------");
                        printerClass.add2String("Draw Date", "Draw Time");
                        TpsGamesClass.getInstance().getLoginResponse().getData().setAvailableBalance(gameSale.getMainData().getAvlblCreditAmt());

                        for (int i = 0; i < gameSale.getMainData().getCommonSaleData().getDrawData().length; i++) {

                            printerClass.add2String(PrintClass.getPrintDateFormat(gameSale.getMainData().getCommonSaleData().getDrawData()[i].getDrawDate()), gameSale.getMainData().getCommonSaleData().getDrawData()[i].getDrawTime());
                        }


                        printer.printString("--------------------------------");

                        for (int i = 0; i < gameSale.getMainData().getBetTypeData().length; i++) {

                            String isQp = gameSale.getMainData().getBetTypeData()[i].isQp() == true ? "QP" : "Manual";

                            printerClass.add2String(gameSale.getMainData().getBetTypeData()[i].getBetName(), isQp);
                            String numberString = "";
                            if (gameSale.getMainData().getBetTypeData()[i].getBetName().equalsIgnoreCase("oneToTwelve")) {
                                numberString = gameSale.getMainData().getBetTypeData()[i].getPickedNumbers() + " - " + sunSin[Integer.parseInt(gameSale.getMainData().getBetTypeData()[i].getPickedNumbers()) - 1];
                            } else {
                                numberString = gameSale.getMainData().getBetTypeData()[i].getPickedNumbers();
                            }

                            printer.printString(numberString);

                            printerClass.add2String("No. of Line(s)", gameSale.getMainData().getBetTypeData()[i].getNoOfLines() + "");


                            printerClass.add2String("Bet Amount(USD)", gameSale.getMainData().getBetTypeData()[i].getPanelPrice() + "");
//
                            printer.printString("--------------------------------");
                        }

                        printerClass.add2String("No. of Draws", gameSale.getMainData().getCommonSaleData().getDrawData().length + "");

                        printerClass.add2String("Total Amount(USD)", gameSale.getMainData().getCommonSaleData().getPurchaseAmt() + "");

                        printer.printString("   ");
//                        printerClass.printBarcode(gameSale.getMainData().getCommonSaleData().getBarcodeCount());

                        String barcode = gameSale.getMainData().getCommonSaleData().getTicketNumber();
                        Bitmap bitmap = null;
                        if (barcode != null && barcode.length() != 0 && barcode.length() <= 20 && barcode.length() >= 17) {
                            bitmap = CreateCode(barcode, BarcodeFormat.CODE_128, 376, 128);
                        }
                        if (bitmap != null) {
                            printer.printPicture(bitmap, bitmap.getWidth(), bitmap.getHeight());
                        }

                        printer.setAlignment(1);
                        printer.printString(gameSale.getMainData().getCommonSaleData().getBarcodeCount());


                        printer.setAlignment(0);
                        printer.printString("********************************");
                        if (gameSale.getMainData().getAdvMessage() != null && gameSale.getMainData().getAdvMessage().getBOTTOM() != null) {
                            String[] bottom = gameSale.getMainData().getAdvMessage().getBOTTOM();
                            for (int i = 0; i < bottom.length; i++) {
//
                                printer.setAlignment(1);
                                printer.printString(bottom[i]);
                                printer.setAlignment(0);
                                printer.printString("********************************");
                            }
                        }
                        printer.printBlankLines(10);
                        for (int i = 0; i < 5; i++)
                            printer.printString("      ");


                    }

                } catch (InternalErrorException e) {
                    e.printStackTrace();
                } finally {

                    printer.close();
                    isReponsePrint = true;

                    // TpsGamesClass.getInstance().changeBackScreen(pos);
                    //  TpsGamesClass.getInstance().closeScreen(PrintActivityAllGamesTousei.this);
                    Intent intent = new Intent();
                    intent.putExtra("resetRequest", "resetData");
                    setResult(054, intent);
                    PrintActivityAllGamesTousei.this.finish();

                }

            }

        }).start();


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

    }

    @Override
    public void onBackPressed() {

        if (isReponsePrint) {

            super.onBackPressed();
        } else {
            return;
        }
    }


}
