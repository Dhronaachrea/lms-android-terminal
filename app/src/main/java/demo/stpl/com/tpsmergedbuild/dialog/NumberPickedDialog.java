package demo.stpl.com.tpsmergedbuild.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.utils.widgets.CommonCustomDialog;

//import skilrock.com.tpsgame.R;
//import demo.stpl.com.tpsmergedbuild.utils.widgets.CommonCustomDialog;

/**
 * Created by stpl on 9/26/2016.
 */
public class NumberPickedDialog extends CommonCustomDialog {
    private TextView edit_view;
    private TextView number;
    private int minNoS, maxNoS;
    public int pickedValues;
    private ImageView inc, dec;
    private LinearLayout layout;
    private TextView title_txt;

    public NumberPickedDialog(Context context) {
        super(context);
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

    public NumberPickedDialog setNegativeButton(String txtNegative) {
        return setNegativeButton(txtNegative, dismiss);
    }

    public NumberPickedDialog setNegativeButton(String txtNegative, final View.OnClickListener listener) {
        btnNegative.setVisibility(View.VISIBLE);
        btnNegative.setText(txtNegative);
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(v);
                    pickedValues = minNoS;
                }
            }
        });

        return this;
    }

    public NumberPickedDialog setPositiveButton(String txtPositive, final View.OnClickListener listener) {
        btnPositive.setVisibility(View.VISIBLE);
        btnPositive.setText(txtPositive);
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
//                    ServerCommClass.getServerCommClass().getServerResponse().response("numberselect&" + pickedValues);
                    listener.onClick(v);
                }
                dismiss();
            }
        });
        return this;
    }

    public NumberPickedDialog setLayoutInitializer(String minNo, String maxNo) {
        //initialize
        this.minNoS = Integer.parseInt(minNo);
        this.maxNoS = Integer.parseInt(maxNo);
        this.pickedValues = Integer.parseInt(minNo);

        layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.common_number_selected_panel, null);
        title_txt = (TextView) layout.findViewById(R.id.title_txt);
        inc = (ImageView) layout.findViewById(R.id.pick_up);
        dec = (ImageView) layout.findViewById(R.id.pick_down);
        number = (TextView) layout.findViewById(R.id.number);

        title_txt.setText("Number picked");
        number.setText(pickedValues + "");

        inc.setOnClickListener(incDecHandler);
        dec.setOnClickListener(incDecHandler);

        dgeSpace.addView(layout);
        return this;
    }


    public void setEditInput(boolean editButton) {
        if (editButton)
            edit_view.setVisibility(View.VISIBLE);
    }

    public View.OnClickListener incDecHandler = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (R.id.pick_down == v.getId()) {
                incDecHandlerMethod(pickedValues, "M");
            } else if (R.id.pick_up == v.getId()) {
                incDecHandlerMethod(pickedValues, "P");
            }
        }
    };

    public void incDecHandlerMethod(int pickedValue, String opration) {
//        M  P
        if (opration.contains("P")) {
            this.pickedValues = pickedValue + 1 <= maxNoS ? pickedValue + 1 : this.maxNoS;
        } else if (opration.contains("M")) {
            this.pickedValues = pickedValue - 1 >= minNoS ? pickedValue - 1 : this.minNoS;
        } else {
            Toast.makeText(context, "max min no selection should be in between" + minNoS + " " + maxNoS, Toast.LENGTH_SHORT).show();
        }
        number.setText(pickedValues + "");
    }

    public int getNumbers() {
        return pickedValues;
    }
}