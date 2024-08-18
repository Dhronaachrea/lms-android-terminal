package demo.stpl.com.tpsmergedbuild.beans;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.dialog.NumberPickedDialog;
import demo.stpl.com.tpsmergedbuild.dialog.WinnigDialog;

//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import tpsgames.dialog.NumberPickedDialog;
//import tpsgames.dialog.WinnigDialog;

//import com.tablet.stpl.comman.interfaces.ServerCommClass;

/**
 * Created by stpl on 9/20/2016.
 */
public class Communcations {

    public static void winningDialog(Context context) {
        WinnigDialog winnigDialog = (WinnigDialog) new WinnigDialog(context).setNegativeButton("CANCEL").setPositiveButton("DONE", null).setTitle("WINNING CLAIM");
        winnigDialog.setMessages("TICKET ID");
        winnigDialog.setEditInput(true);
        winnigDialog.show();
    }


    public static void winningInside(Context context, String errorMsg) {
        WinnigDialog winnigDialog = (WinnigDialog) new WinnigDialog(context).setPositiveButton("OK", null).setTitle("ERROR");
        winnigDialog.setMessages(errorMsg);
        winnigDialog.setEditInput(false);
        winnigDialog.show();
    }
    public static void winningInside(Context context, String errorMsg, final int selectedPosition) {
        WinnigDialog winnigDialog = (WinnigDialog) new WinnigDialog(context).setPositiveButton("OK", null).setTitle("ERROR");
        winnigDialog.setMessages(errorMsg);
        winnigDialog.setEditInput(false);
        winnigDialog.show();
        winnigDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                TpsGamesClass.getInstance().changeBackScreen(selectedPosition);
            }
        });
    }
    public static void winningInside(Context context, String errorMsg,View.OnClickListener onClickListener) {
        WinnigDialog winnigDialog = (WinnigDialog) new WinnigDialog(context).setPositiveButton("OK", onClickListener).setTitle("ERROR");
        winnigDialog.setMessages(errorMsg);
        winnigDialog.setEditInput(false);
        winnigDialog.show();
    }


    public static void numberPickedDialogRequest(Context context, String minNo, String maxNo) {
        NumberPickedDialog numberPickedDialog = (NumberPickedDialog) new NumberPickedDialog(context).setNegativeButton("CANCEL").setPositiveButton("DONE", null).setTitle("QUCIK PICK");
        numberPickedDialog.setLayoutInitializer(minNo, maxNo);
        numberPickedDialog.setEditInput(false);
        numberPickedDialog.show();


        numberPickedDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                int numberSelection = ((NumberPickedDialog) dialog).getNumbers();
                dialog.dismiss();
              //  ServerCommClass.getServerCommClass().getServerResponse().response("numberselect&" + numberSelection);
            }
        });
    }
}
