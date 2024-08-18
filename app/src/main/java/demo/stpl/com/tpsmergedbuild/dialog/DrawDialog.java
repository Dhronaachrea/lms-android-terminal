package demo.stpl.com.tpsmergedbuild.dialog;

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
import demo.stpl.com.tpsmergedbuild.beans.GameBean;
import demo.stpl.com.tpsmergedbuild.utils.CalculationHelper;
import demo.stpl.com.tpsmergedbuild.utils.widgets.CommonCustomDialog;

//import skilrock.com.tpsgame.R;
//import tpsgames.beans.GameBean;
//import demo.stpl.com.tpsmergedbuild.utils.CalculationHelper;
//import demo.stpl.com.tpsmergedbuild.utils.widgets.CommonCustomDialog;

//import com.tablet.stpl.comman.utils.CalculationHelper;

/**
 * Created by stpl on 17-Oct-16.
 */
public class DrawDialog extends CommonCustomDialog {

    private TextView number;
    private double betAmount;
    private double minUnitPrice;
    private double maxUnitPrice;
    private double unitPrice;
    private boolean isBetAmt;
    private DrawDialogAdapterClass adapter;
    private ArrayList<Boolean> checkList;
    private GameBean.Games.Draws[] draws;
    private int minValue, maxValue;
    private boolean isNumberOnly;
    private TotalNumberSelected totalNumberSelected;
    private TotalAmountOther totalAmountOther;
    private int totalBetAmt = 1;

    public DrawDialog(Context context) {
        super(context);
    }

    public DrawDialog(Context context, boolean isCancelable) {
        super(context, isCancelable);
    }

    @Override
    public void onBackPressed() {

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


    public DrawDialog setPositiveButton(String txtPositive, final View.OnClickListener listener) {
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
                    } else {
                        Toast.makeText(context, "Select At Least One Draw", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                if (listener != null && totalNumberSelected == null)
                    listener.onClick(v);
                if (totalNumberSelected != null) {
                    totalNumberSelected.onNumberSelected(Integer.parseInt(number.getText().toString()));
                }
                if (totalAmountOther != null) {
                    double amount = Double.parseDouble(number.getText().toString());
//                    double betMul = amount / unitPrice;
                    totalAmountOther.otherAmount(amount, totalBetAmt);
                }
            }
        });
        return this;
    }

    public DrawDialog setNegativeButton(String txtNegative, final View.OnClickListener listener) {
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
                        draws[i].setIsChecked(checkList.get(i));

                    }
                } else {
                    dismiss();
                }
                if (listener != null)
                    listener.onClick(v);

                if (totalAmountOther != null) {
                    totalAmountOther.canClick();
                }

            }
        });
        return this;
    }

    public DrawDialog setNegativeButton(String txtNegative) {
        return setNegativeButton(txtNegative, dismiss);
    }

    private void initialize(double minUnitPrice, double maxUnitPrice, double unitPrice, double betAmount) {
        this.maxUnitPrice = maxUnitPrice;
        this.minUnitPrice = minUnitPrice;
        this.unitPrice = unitPrice;
        this.betAmount = betAmount;
    }

    private void initialize(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public DrawDialog setBetAmount(double minUnitPrice, double maxUnitPrice, double unitPrice, double betAmount) {
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

    public DrawDialog setBetAmount(int miniValue, int maxValue, boolean isNumberOnly, TotalNumberSelected totalNumberSelected) {
        isBetAmt = true;
        this.isNumberOnly = isNumberOnly;
        this.totalNumberSelected = totalNumberSelected;
        initialize(miniValue, maxValue);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.common_number_selected_panel, null);
        ImageView inc = (ImageView) layout.findViewById(R.id.pick_up);
        ImageView dec = (ImageView) layout.findViewById(R.id.pick_down);
        TextView title_txt = (TextView) layout.findViewById(R.id.title_txt);
        title_txt.setText("Number Picked");
        number = (TextView) layout.findViewById(R.id.number);
        number.setText(miniValue + "");

        inc.setOnClickListener(incDecHandler);
        dec.setOnClickListener(incDecHandler);
        dgeSpace.addView(layout);
        return this;
    }

    public DrawDialog setBetAmountOther(double miniValue, double maxValue, boolean isNumberOnly, double unitPrice, double betAmount, TotalAmountOther totalAmountOther, String textValue) {
        isBetAmt = true;
        totalBetAmt = 1;
        this.isNumberOnly = isNumberOnly;
        this.totalAmountOther = totalAmountOther;
        initialize(miniValue, maxValue, unitPrice, betAmount);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.common_number_selected_panel, null);
        ImageView inc = (ImageView) layout.findViewById(R.id.pick_up);
        ImageView dec = (ImageView) layout.findViewById(R.id.pick_down);
        TextView title_txt = (TextView) layout.findViewById(R.id.title_txt);
        title_txt.setText("Unite Price($)");
        number = (TextView) layout.findViewById(R.id.number);
        number.setText(miniValue + "");

        inc.setOnClickListener(incDecHandler);
        dec.setOnClickListener(incDecHandler);
        dgeSpace.addView(layout);
        return this;
    }

    private View.OnClickListener incDecHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if (R.id.pick_down == v.getId())
                if (isNumberOnly) {
                    int incValue = Integer.parseInt(number.getText().toString());
                    incDecHandler(incValue, false);
                } else {
                    if (totalBetAmt > 1) {
                        totalBetAmt--;
                    }
                    double value = Double.parseDouble(number.getText().toString());
                    incDecHandler(value, false);
                }

            else if (R.id.pick_up == v.getId())
                if (isNumberOnly) {
                    int incValue = Integer.parseInt(number.getText().toString());
                    incDecHandler(incValue, true);
                } else {
                    double value = Double.parseDouble(number.getText().toString());
                    if (value < maxUnitPrice) {
                        totalBetAmt++;
                    }

                    incDecHandler(value, true);
                }

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

    private void incDecHandler(int textValue, boolean isIncrement) {
        if (isIncrement) {
            textValue = textValue < maxValue ? (textValue + 1) : textValue;

        } else {
            textValue = textValue > minValue ? (textValue - 1) : textValue;
        }
        number.setText(textValue + "");
    }


    public DrawDialog setDraw(GameBean.Games.Draws[] draws) {
        this.draws = draws;
        GridView gridView = (GridView) LayoutInflater.from(context).inflate(R.layout.common_draw_dialog, null);
        dgeSpace.addView(gridView);
        checkList = new ArrayList<>();
        for (GameBean.Games.Draws draw : draws)
            checkList.add(draw.isChecked());
        adapter = new DrawDialogAdapterClass(context, draws);
        gridView.setAdapter(adapter);
        return this;
    }

    public DrawDialogAdapterClass getAdapter() {
        return adapter;
    }

    public ArrayList<Boolean> getCheckList() {
        return checkList;
    }

    public interface TotalNumberSelected {
        public void onNumberSelected(long totalNumber);
    }

    public interface TotalAmountOther {
        public void otherAmount(double amount, int betAmtMul);

        public void canClick();
    }

}
