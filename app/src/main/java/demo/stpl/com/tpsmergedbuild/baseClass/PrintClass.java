package demo.stpl.com.tpsmergedbuild.baseClass;

import android.graphics.Bitmap;
import android.util.Log;

import com.epson.epos2.Epos2Exception;
import com.epson.epos2.printer.Printer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.telpo.tps550.api.InternalErrorException;
import com.telpo.tps550.api.TelpoException;
import com.telpo.tps550.api.printer.ThermalPrinter;

/**
 * Created by stpl on 9/9/2016.
 */
public class PrintClass {

    static String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public static void printString() throws TelpoException {
        ThermalPrinter.printString();
        ThermalPrinter.clearString();
    }

    public static void printLine() throws TelpoException {
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.setGray(2);
        ThermalPrinter.addString("--------------------------------\n\n");
    }

    public static void printLine(Printer printer) throws TelpoException, Epos2Exception {
        printer.addTextFont(2);
        printer.addText("------------------------------------------\n");
    }

    public static void printTitleAtTop(String content) throws TelpoException {
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.enlargeFontSize(2, 2);
        // set Align for Text String
        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
        ThermalPrinter.setGray(20);
        ThermalPrinter.addString(content + "\n\n");
        ThermalPrinter.setGray(2);
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.enlargeFontSize(1, 1);
        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_LEFT);
    }

    public static void printTitleAtTop(String content, Printer printer) throws TelpoException, Epos2Exception {
        printer.addTextFont(2);
        printer.addTextSize(2, 2);
        // set Align for Text String
        printer.addTextAlign(Printer.ALIGN_CENTER);
//        ThermalPrinter.setGray(20);
        printer.addText(content + "\n\n");
//        ThermalPrinter.setGray(2);
        printer.addTextFont(2);
        printer.addTextSize(1, 1);
        printer.addTextAlign(Printer.ALIGN_CENTER);
    }


    public static void printTitle(String content) throws TelpoException {
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.enlargeFontSize(1, 2);
        // set Align for Text String
        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
        ThermalPrinter.setGray(20);
        ThermalPrinter.addString(content + "\n\n");
        ThermalPrinter.setGray(2);
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.enlargeFontSize(1, 1);
        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_LEFT);
    }

    public static void printTitleSub(String content) throws TelpoException {
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.enlargeFontSize(1, 2);
        // set Align for Text String
        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
        ThermalPrinter.setGray(20);
        ThermalPrinter.addString(content + "\n\n");
        ThermalPrinter.setGray(2);
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.enlargeFontSize(1, 1);
        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_LEFT);
    }

    public static void printTitleSub(String content, Printer printer) throws Epos2Exception {
        printer.addTextFont(2);
        printer.addTextSize(1, 2);
        // set Align for Text String
        printer.addTextAlign(Printer.ALIGN_CENTER);
        printer.addText(content + "\n\n");
        printer.addTextFont(2);
        printer.addTextSize(1, 1);
        printer.addTextAlign(Printer.ALIGN_LEFT);
        ;
    }

    public static void printMiddle(String content) throws TelpoException {
        // set Align for Text String
        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
        ThermalPrinter.addString(content + "\n\n");
        ThermalPrinter.setGray(2);
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_LEFT);
    }

    public static void printMiddle(String content, Printer printer) throws TelpoException, Epos2Exception {
        // set Align for Text String
        printer.addTextAlign(Printer.ALIGN_CENTER);
        printer.addText(content + "\n\n");
//        ThermalPrinter.setGray(2);
        printer.addTextFont(2);
        printer.addTextAlign(Printer.ALIGN_LEFT);
    }

    public static void printTitleWithQP(String content, int QP) throws TelpoException {
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.enlargeFontSize(2, 1);
        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_LEFT);
        // set Align for Text String
        ThermalPrinter.setGray(20);
        StringBuffer str = new StringBuffer();
        str.append(content);
        if (QP == 1) {
            for (int i = content.length(); i < 14; i++)
                str.append(" ");
            str.append("QP");
        }
        System.out.println(str.length());
        ThermalPrinter.addString(str.toString() + "\n\n");
        ThermalPrinter.setGray(2);
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.enlargeFontSize(1, 1);

    }

    public static String getPrintDateFormat(String data) {
        String[] date = data.split("[-]");
        return months[Integer.parseInt(date[1].toString().substring(0, date[1].length())) - 1] + " " + date[2] + ", " + date[0];
    }


    public static void printTwoString(String one, String two) throws TelpoException {
        ThermalPrinter.setFontSize(2);
        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_LEFT);
        if ((one.length() + two.length()) > 32) {
            ThermalPrinter.addString(one + "\n");
            ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_RIGHT);
            ThermalPrinter.addString(two + "\n");
        } else {
            StringBuffer str = new StringBuffer();
            str.append(one);
            for (int i = one.length(); i < (32 - two.length()); i++) {
                str.append(" ");
            }
            str.append(two + "\n");
            ThermalPrinter.addString(str.toString());
        }
    }

    public static String printTwoString(String one, String two, String s) {

        StringBuffer str = new StringBuffer();
        str.append(one);
        for (int i = one.length(); i < (48 - two.length()); i++) {
            str.append(" ");
        }
        str.append(two + "\n");
        return str.toString();

    }

    public static String printInMiddle(String one) {

        StringBuffer str = new StringBuffer();
        int totalCount = (48 - one.length()) / 2;
//        str.append(one);
        for (int i = 0; i < totalCount; i++) {
            str.append(" ");
        }
        str.append(one + "\n");
        return str.toString();

    }

    public static String printInRight(String one) {

        StringBuffer str = new StringBuffer();
        int totalCount = 48;
//        str.append(one);
        for (int i = one.length(); i < totalCount; i++) {
            str.append(" ");
        }
        str.append(one + "\n");
        return str.toString();

    }

    public static void printTwoString(String one, String two, Printer printer) throws TelpoException, Epos2Exception {
        printer.addTextFont(2);
        printer.addTextAlign(Printer.ALIGN_LEFT);
        if ((one.length() + two.length()) > 42) {
            printer.addText(one + "\n");
            printer.addTextAlign(Printer.ALIGN_RIGHT);
            printer.addText(two + "\n");
        } else {
            StringBuffer str = new StringBuffer();
            str.append(one);
            for (int i = one.length(); i < (42 - two.length()); i++) {
                str.append(" ");
            }
            str.append(two + "\n");
            printer.addText(str.toString());
        }
    }

    public static void printNum(String num) throws TelpoException {

        ThermalPrinter.setAlgin(ThermalPrinter.ALGIN_MIDDLE);
        ThermalPrinter.setFontSize(2);
        if (num.length() > 32) {
            if (num.charAt(31) >= '0' && num.charAt(31) <= '9') {
                if (num.charAt(32) >= '0' && num.charAt(32) <= '9') {
                    num = num.substring(0, 31) + "\n" + num.substring(31);
                }
            }
        }
        ThermalPrinter.addString(num + "\n\n");
    }

    public static void printDrawGameDetails(String drawGameName, String drawDate, String drawTime) throws TelpoException {
        if (drawGameName != null && !drawGameName.equalsIgnoreCase("null"))
            printTitle(drawGameName);
        printTwoString(drawDate, drawTime);
    }

    public static void printBetTypeDetails(String betType, int QP, String num, String noofLines, String unitPrice, String panelPrice, String currencyName) throws TelpoException {
        if (betType == null || betType.equalsIgnoreCase("null")) {
            betType = "null";
        }
        printTitleWithQP(betType, QP);
        String[] gameNumCol = num.split(",");
        printNum(gameNumCol[0] + "-" + gameNumCol[1] + "-" + gameNumCol[2]);
        if (gameNumCol.length > 3) {
            printNum(gameNumCol[3] + "-" + gameNumCol[4] + "-" + gameNumCol[5]);
        }
//        printTwoString("No. of Line(s)", noofLines);
//        printTwoString("Unit Price(" + currencyName + ")", unitPrice);
        printTwoString("Amount(" + currencyName + ")", panelPrice);
    }

    public static void printTotalAmount(int noofDraw, String totalAmount, String currencyName) throws TelpoException {
        PrintClass.printTwoString("No. of Draws", noofDraw + "");
        PrintClass.printTwoString("Total Amount(" + currencyName + ")", totalAmount);
        PrintClass.printLine();
    }

    public static void printTicketValidity(String days) throws TelpoException {
        PrintClass.printNum("Ticket Validity: " + days);
        PrintClass.printLine();
    }

    public static void printBarcode(String barcode) throws TelpoException {
        if (barcode != null && barcode.length() != 0 && barcode.length() <= 20 && barcode.length() >= 17) {
            Bitmap bitmap = CreateCode(barcode, BarcodeFormat.CODE_128, 384, 128);
            ThermalPrinter.printLogo(bitmap, ThermalPrinter.ALGIN_MIDDLE);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static Bitmap createBarCode(String barcode) throws TelpoException {
        Bitmap bitmap = null;
        if (barcode != null && barcode.length() != 0 && barcode.length() <= 20 && barcode.length() >= 17) {
            bitmap = CreateCode(barcode, BarcodeFormat.CODE_128, 384, 64);
//            ThermalPrinter.printLogo(bitmap,ThermalPrinter.ALGIN_MIDDLE);
        } else {
            throw new IllegalArgumentException();
        }
        return bitmap;
    }

    public static void PrintBarcodeImage(Bitmap bitmap) throws TelpoException {
        ThermalPrinter.printLogo(bitmap, ThermalPrinter.ALGIN_MIDDLE);
    }

    public static Bitmap CreateCodeNew(String str, BarcodeFormat type, int bmpWidth, int bmpHeight) throws WriterException {
        // 生成二维矩阵,编码时要指定大小,不要生成了图片以后再进行缩放,以防模糊导致识别失败
        BitMatrix matrix = new MultiFormatWriter().encode(str, type, bmpWidth, bmpHeight);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        // 二维矩阵转为一维像素数组（一直横着排）
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                } else {
                    pixels[y * width + x] = 0xffffffff;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    public static Bitmap CreateCode(String str, BarcodeFormat type, int bmpWidth, int bmpHeight) throws InternalErrorException {
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

    public static final int STATUS_OK = 0;
    private static final int MODE_PIC = 1;

    private static Bitmap CreateCodeNewApi(String str, BarcodeFormat type, int bmpWidth, int bmpHeight) throws WriterException {
        BitMatrix matrix = new MultiFormatWriter().encode(str, type, bmpWidth, bmpHeight);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[(width * height)];
        for (int y = STATUS_OK; y < height; y += MODE_PIC) {
            for (int x = STATUS_OK; x < width; x += MODE_PIC) {
                if (matrix.get(x, y)) {
                    pixels[(y * width) + x] = -16777216;
                } else {
                    pixels[(y * width) + x] = -1;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, STATUS_OK, width, STATUS_OK, STATUS_OK, width, height);
        return bitmap;
    }

    public static void printQrcode(String str) throws TelpoException, WriterException {
        //       Bitmap bitmap= BitmapFactory.decodeStream(getClass().getResourceAsStream("/res/drawable/time1.bmp"));
        Bitmap bitmap = CreateCodeNew(str, BarcodeFormat.QR_CODE, 128, 128);
        if (bitmap != null) {
            Log.v("Thermal Printer", "Find the Bmp");
            ThermalPrinter.printLogo(bitmap);
        }
    }

    public static void printQrcodeNew(String str) throws Exception {
        Bitmap bitmap = CreateCodeNew(str, BarcodeFormat.QR_CODE, 256, 256);
        if (bitmap != null) {
            Log.v("", "Find the Bmp");
            ThermalPrinter.printLogo(bitmap);
        }
    }
}
