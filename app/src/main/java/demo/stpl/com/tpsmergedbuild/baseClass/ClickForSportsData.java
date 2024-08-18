package demo.stpl.com.tpsmergedbuild.baseClass;

import android.content.Context;
import android.view.View;

import demo.stpl.com.tpsmergedbuild.interfaces.OnSportsItemClick;

//import tpsgames.interfaces.OnSportsItemClick;

/**
 * Created by stpl on 20-Oct-16.
 */
public class ClickForSportsData {


    private OnSportsItemClick onSportsItemClick;

    private Context context;

    private int totalInView, totalView;

    public ClickForSportsData(Context context, OnSportsItemClick onSportsItemClick) {

        this.context = context;
        this.onSportsItemClick = onSportsItemClick;
    }

    public void setOnClick(final int viewPosition, final int positionInView, final View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSportsItemClick.onItemClick(viewPosition, positionInView,view);
            }
        });
    }


}
