package demo.stpl.com.tpsmergedbuild.baseClass;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import demo.stpl.com.tpsmergedbuild.R;

//import skilrock.com.tpsgame.R;


/**
 * Created by stpl on 9/7/2016.
 */
public class DialogLoader {

    private Context context;
    private Dialog dialog;
    private View loadView;

    private ProgressBar spinner;

    public DialogLoader(Context context) {
        this.context = context;
    }


    public Dialog createStartManualGameDialog(String message) {
        dialog = new Dialog(context);
        dialog.setCancelable(false);
        loadView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_circle_progress_bar, null);

        spinner = (ProgressBar) loadView.findViewById(R.id.progressBar1);
//        ImageView animView = (ImageView) loadView.findViewById(R.id.anim);
//        try {
//
//            animView.startAnimation(
//                    AnimationUtils.loadAnimation(context, R.anim.rotation));
//
//        } catch (OutOfMemoryError e) {
//            animView.setVisibility(View.GONE);
//        } catch (Exception e1) {
//            animView.setVisibility(View.GONE);
//        }

//        TextView loadingText = (TextView) loadView.findViewById(R.id.loading_text);
        loadView.setVisibility(View.VISIBLE);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(loadView);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

//        if (!message.equals("")) {
//            loadingText.setText(message);
//        } else {
//            loadingText.setText("Loading...");
//        }
        return dialog;
    }
}
