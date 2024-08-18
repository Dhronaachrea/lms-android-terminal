package demo.stpl.com.tpsmergedbuild.DgeDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import demo.stpl.com.tpsmergedbuild.beans.FatchGameData;

//import tpsgames.beans.FatchGameData;
//import tpsgames.interfaces.ServerCommClass;

//import com.example.mylibrary.Beans.FatchGameData;
//import com.tablet.stpl.comman.interfaces.ServerCommClass;

/**
 * Created by stpl on 9/19/2016.
 */
public class DrawGameDialogs {
    public static double showBetAmountDialog(Context context, double minUnitPrice, double maxUnitPrice, double unitPrice, double betAmount) {
        Dialog dialog = new DgeDialog(context, true).setNegativeButton("CANCEL").setPositiveButton("DONE", null).setTitle("CHOOSE UNIT PRICE");
        ((DgeDialog) dialog).setBetAmount(minUnitPrice, maxUnitPrice, unitPrice, betAmount);
        dialog.show();

        final double[] betAmounts = new double[1];
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                betAmounts[0] = ((DgeDialog) dialog).getBetAmount();
              //  ServerCommClass.getServerCommClass().getServerResponse().response("betAmtDialog&" + betAmounts[0]);
            }
        });
        return betAmounts[0];
    }

    public static void showDrawDialog(Context context, final FatchGameData.Games.Draws[] draws) {

        Dialog dialog = new DgeDialog(context, true).setNegativeButton("CANCEL").setPositiveButton("DONE", null).setTitle("SELECT DRAW");
        ((DgeDialog) dialog).setDraw(draws);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                //ServerCommClass.getServerCommClass().getServerResponse().response("drawDialog&");
            }
        });
    }
}
