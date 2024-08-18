package demo.stpl.com.tpsmergedbuild.baseClass;

//import android.pt.printer.Printer;

import android.pt.printer.Printer;

/**
 * Created by stpl on 10/18/2016.
 */
   public class PrintTouseiClass {
    Printer printer;
    public PrintTouseiClass(Printer printer){
      this.printer=printer;
    }

    public void printHeaderandFooter(String msg){
        if(msg.length()<32) {
         printer.printString(msg);
        }
        else {
            StringBuffer sbr=new StringBuffer();

        }
    }

    public  void printTitleAtTop(String title){
        printer.setAlignment(1);
        printer.setFontHeightZoomIn(3);
        printer.setFontwidthZoomIn(2);
        printer.setBold(true);
        printer.printString(title);

    }
    public void printSubTitle(String subtitle){
        printer.setFontHeightZoomIn(2);
        printer.setFontwidthZoomIn(1);
        printer.printString(subtitle);

    }
    public void add2String(String one, String two){
        printer.setAlignment(0);
        printer.setBold(false);
        printer.setFontHeightZoomIn(1);

        StringBuffer str = new StringBuffer();
        str.append(one);
        for (int i = one.length(); i < (32 - two.length()); i++) {
            str.append(" ");
        }
        str.append(two);
        printer.printString(str.toString());

    }
    public void printBarcode(String barcode){
        printer.setAlignment(1);
        printer.setOnebarWidthZoomIn(2);
        printer.setOnebarHeight(128);

//		Bitmap bitmap = CreateCode(barcode, BarcodeFormat.CODE_128, 384, 128);
//		printer.printPicture(bitmap,384,128);
        printer.printCODE128(barcode);
        printer.setAlignment(0);
    }

}
