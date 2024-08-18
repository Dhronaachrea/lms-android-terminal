package demo.stpl.com.tpsmergedbuild.dialog;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
import demo.stpl.com.tpsmergedbuild.utils.widgets.CommonCustomDialog;

//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;
//import demo.stpl.com.tpsmergedbuild.utils.widgets.CommonCustomDialog;

/**
 * Created by stpl on 9/20/2016.
 */
public class WinnigDialog extends CommonCustomDialog {
    public TextView edit_view;

    public WinnigDialog(Context context) {
        super(context);
    }

    public WinnigDialog(Context context, boolean isCancelable) {
        super(context, isCancelable);
    }

    public String getEditTextValue() {
        return edit_view.getText().toString();
    }

    @Override
    protected void initDialog() {
        btnNegative = (TextView) findViewById(R.id.btn_negative);
        btnNegative.setText("OK");
        btnPositive = (TextView) findViewById(R.id.btn_positive);
        btnNegative.setOnClickListener(dismiss);
        edit_view = (TextView) findViewById(R.id.edit_view);



        // add here for Bet Amount and Draw Game
        dgeSpace = (FrameLayout) findViewById(R.id.add_view_frame);
        dgeSpace.setVisibility(View.VISIBLE);
        setMessages(null);
    }

    public void setEdit_view(String value) {
        edit_view.setText(value);
    }

    public WinnigDialog setNegativeButton(String txtNegative) {
        return setNegativeButton(txtNegative, dismiss);
    }

    public WinnigDialog  setNegativeButton(String txtNegative, final View.OnClickListener listener) {
        btnNegative.setVisibility(View.VISIBLE);
        btnNegative.setText(txtNegative);
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                else dismiss();
            }
        });

        return this;
    }

    public WinnigDialog setPositiveButton(String txtPositive, final View.OnClickListener listener) {
        btnPositive.setVisibility(View.VISIBLE);
        btnPositive.setText(txtPositive);
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                else dismiss();
            }
        });
        return this;
    }

    public void setEditInput(boolean editButton) {
        if (editButton)
            edit_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (!TpsGamesClass.getInstance().getDeviceName().equalsIgnoreCase("PT7003"))
            TpsGamesClass.getInstance().stopCard();
    }
}
