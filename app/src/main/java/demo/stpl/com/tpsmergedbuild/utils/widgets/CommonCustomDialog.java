package demo.stpl.com.tpsmergedbuild.utils.widgets;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

//import skilrock.com.tpsgame.R;
//importdemo.stpl.com.tpsmergedbuild.baseClass.TpsGamesClass;

/**
 * Created by stpl on 9/20/2016.
 */
public abstract class CommonCustomDialog extends android.app.Dialog {

    protected TextView txtTitle;
    protected TextView txtMsg;
    protected TextView btnPositive;
    protected TextView btnNegative;
    protected View divider;
    protected TextView btnNormal;
    protected LinearLayout btnCashout;
    protected EditText editText;
    protected TextView txtBalanceMsg;
    protected TextView txtBalance;
    protected TextView txtWithdrawal;
    protected TextView txtTotalAmount;
    protected ProgressBar progressBar;
    protected FrameLayout dgeSpace;

    protected Context context;

    protected View.OnClickListener dismiss = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };

    public CommonCustomDialog(Context context) {
        this(context, true);
    }

    public CommonCustomDialog(final Context context, boolean isCancelable) {
        super(context);
        this.context = context;
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        if (TpsGamesClass.getInstance().getDeviceName().contains("390"))
            setContentView(R.layout.common_custom_dialog_app_tps390);
        else
            setContentView(R.layout.common_custom_dialog_app);
        setCancelable(isCancelable);

        if (TpsGamesClass.getInstance().getDeviceName().contains("550") || TpsGamesClass.getInstance().getDeviceName().contains("hdx053"))
            getWindow().setLayout((int) (context.getResources().getDisplayMetrics().widthPixels * 0.7), (int) (LinearLayout.LayoutParams.WRAP_CONTENT));
        else if (TpsGamesClass.getInstance().getDeviceName().contains("7003")) {
            getWindow().setLayout((int) (context.getResources().getDisplayMetrics().widthPixels * 0.9), (int) (LinearLayout.LayoutParams.WRAP_CONTENT));
        } else
            getWindow().setLayout((int) (context.getResources().getDisplayMetrics().widthPixels * 0.7), (int) (LinearLayout.LayoutParams.WRAP_CONTENT));

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        txtTitle = (TextView) findViewById(R.id.dialogHeaderText);
        txtMsg = (TextView) findViewById(R.id.dialogTextContent);

        initDialog();

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
            }
        });
    }

    protected abstract void initDialog();

    public CommonCustomDialog setMessages(String message) {
        txtMsg.setVisibility(message == null || message.trim().equals("") ? View.GONE : View.VISIBLE);
        txtMsg.setText(message == null ? "Message" : message);
        return this;
    }

    public CommonCustomDialog setTitle(String title) {
        txtTitle.setVisibility(title == null || title.trim().equals("") ? View.GONE : View.VISIBLE);
        txtTitle.setText(title == null ? "Title" : title);
        return this;
    }
}