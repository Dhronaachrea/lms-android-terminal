package demo.stpl.com.tpsmergedbuild.DgeDialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.adapter.DrawDialogAdapter;
import demo.stpl.com.tpsmergedbuild.beans.FatchGameData;
import demo.stpl.com.tpsmergedbuild.utils.CalculationHelper;
import demo.stpl.com.tpsmergedbuild.utils.widgets.CommonCustomDialog;

//import skilrock.com.tpsgame.R;
//import tpsgames.adapter.DrawDialogAdapter;
//import tpsgames.beans.FatchGameData;
//import demo.stpl.com.tpsmergedbuild.utils.CalculationHelper;
//import demo.stpl.com.tpsmergedbuild.utils.widgets.CommonCustomDialog;

//import com.example.mylibrary.Adapters.DrawDialogAdapter;
//import com.example.mylibrary.Beans.FatchGameData;
//import com.tablet.stpl.comman.utils.CalculationHelper;

/**
 * Created by stpl on 9/19/2016.
 */
public class DgeDialog extends CommonCustomDialog {

    private TextView number;
    private double betAmount;
    private double minUnitPrice;
    private double maxUnitPrice;
    private double unitPrice;
    private boolean isBetAmt;
    private DrawDialogAdapter adapter;
    private ArrayList<Boolean> checkList;
    private FatchGameData.Games.Draws[] draws;

    public DgeDialog(Context context) {
        super(context);
    }

    public DgeDialog(Context context, boolean isCancelable) {
        super(context, isCancelable);
    }

    @Override
    protected void initDialog() {
        btnNegative = (TextView) findViewById(R.id.btn_negative);
        btnNegative.setText("OK");
        btnNegative.setOnClickListener(dismiss);

        // add here for Bet Amount and Draw Game
        dgeSpace = (FrameLayout) findViewById(R.id.add_view_frame);
        dgeSpace.setVisibility(View.VISIBLE);
        setMessages(null);
    }


    public DgeDialog setPositiveButton(String txtPositive, final View.OnClickListener listener) {
        if (txtPositive == null || txtPositive.trim().equals("")) return this;
        if (btnPositive == null)
            btnPositive = (TextView) findViewById(R.id.btn_positive);
        btnPositive.setVisibility(View.VISIBLE);
        btnPositive.setText(txtPositive);
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBetAmt) {
                    dismiss();
                    betAmount = Double.parseDouble(number.getText().toString());
                } else {
                    if (adapter.getCheckedDrawsCount() > 0) {
                        dismiss();
                    } else
                        Toast.makeText(context, "Select At Least One Draw", Toast.LENGTH_SHORT).show();
                }
                if (listener != null)
                    listener.onClick(v);
            }
        });
        return this;
    }

    public DgeDialog setNegativeButton(String txtNegative, final View.OnClickListener listener) {
        if (txtNegative == null || txtNegative.trim().equals("")) return this;
        if (btnNegative == null)
            btnNegative = (TextView) findViewById(R.id.btn_negative);
        btnNegative.setVisibility(View.VISIBLE);
        btnNegative.setText(txtNegative);
        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isBetAmt) {
                    for (int i = 0; i < draws.length; i++) {
                        draws[i].setChecked(checkList.get(i));
                    }
                } else {
                    dismiss();
                }
                if (listener != null)
                    listener.onClick(v);

            }
        });
        return this;
    }

    public DgeDialog setNegativeButton(String txtNegative) {
        return setNegativeButton(txtNegative, dismiss);
    }

    private void initialize(double minUnitPrice, double maxUnitPrice, double unitPrice, double betAmount) {
        this.maxUnitPrice = maxUnitPrice;
        this.minUnitPrice = minUnitPrice;
        this.unitPrice = unitPrice;
        this.betAmount = betAmount;
    }

    public DgeDialog setBetAmount(double minUnitPrice, double maxUnitPrice, double unitPrice, double betAmount) {
        isBetAmt = true;
        initialize(minUnitPrice, maxUnitPrice, unitPrice, betAmount);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.common_number_selected_panel, null);
        ImageView inc = (ImageView) layout.findViewById(R.id.pick_up);
        ImageView dec = (ImageView) layout.findViewById(R.id.pick_down);
        number = (TextView) layout.findViewById(R.id.number);
        number.setText(CalculationHelper.amountFormatOneDigit(minUnitPrice));

        inc.setOnClickListener(incDecHandler);
        dec.setOnClickListener(incDecHandler);
        dgeSpace.addView(layout);
        return this;
    }

    private View.OnClickListener incDecHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double value = Double.parseDouble(number.getText().toString());
            if (R.id.pick_down == v.getId())
                incDecHandler(value, false);
            else if (R.id.pick_up == v.getId())
                incDecHandler(value, true);
        }
    };

    public double getBetAmount() {
        return betAmount;
    }

    private void incDecHandler(double textValue, boolean isIncrement) {
        textValue = (isIncrement ? textValue + unitPrice <= maxUnitPrice ? textValue + unitPrice : textValue :
                textValue - unitPrice >= minUnitPrice ? textValue - unitPrice : textValue);
        number.setText(CalculationHelper.amountFormatOneDigit(textValue));
    }


    public DgeDialog setDraw(FatchGameData.Games.Draws[] draws) {
        this.draws = draws;
        GridView gridView = (GridView) LayoutInflater.from(context).inflate(R.layout.common_draw_dialog, null);
        dgeSpace.addView(gridView);
        checkList = new ArrayList<>();
        for (FatchGameData.Games.Draws draw : draws)
            checkList.add(draw.isChecked());
        adapter = new DrawDialogAdapter(context, draws);
        gridView.setAdapter(adapter);
        return this;
    }

    public DrawDialogAdapter getAdapter() {
        return adapter;
    }

    public ArrayList<Boolean> getCheckList() {
        return checkList;
    }
}
