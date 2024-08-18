package demo.stpl.com.tpsmergedbuild.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import demo.stpl.com.tpsmergedbuild.R;
import demo.stpl.com.tpsmergedbuild.beans.GameBean;

//import skilrock.com.tpsgame.R;
//import tpsgames.beans.GameBean;

//import com.example.mylibrary.Beans.FatchGameData;

/**
 * Created by stpl on 17-Oct-16.
 */
public class DrawDialogAdapterClass extends BaseAdapter implements View.OnClickListener {
    private GameBean.Games.Draws[] draws;
    private LayoutInflater inflater;
    private final int HOLDER = 44444444;
    private final int BEAN = 55555555;
    private SimpleDateFormat format;
    private SimpleDateFormat formatted;

    public DrawDialogAdapterClass(Context context, GameBean.Games.Draws[] draws) {
        this.draws = draws;
        inflater = LayoutInflater.from(context);
        format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US);
        formatted = new SimpleDateFormat("E, dd MMMM yyyy, HH:mm", Locale.US);
    }

    @Override
    public int getCount() {
        return draws.length;
    }

    @Override
    public Object getItem(int position) {
        return draws[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = convertView == null ? new Holder(convertView = inflater.inflate(R.layout.single_draw_item, null)) : (Holder) convertView.getTag(HOLDER);
        convertView.setTag(HOLDER, holder);

        GameBean.Games.Draws draw = this.draws[position];
        try {
            holder.drawDateTime.setText(formatted.format(format.parse(draw.getDrawDateTime())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.layout.setOnClickListener(this);
        holder.layout.setTag(BEAN, draw);

        if (draw.isChecked())
            holder.iconCheck.setImageResource(R.mipmap.draw_checked);
        else
            holder.iconCheck.setImageResource(R.mipmap.draw_unchecked);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        GameBean.Games.Draws draw = (GameBean.Games.Draws) v.getTag(BEAN);
        draw.setIsChecked(!draw.isChecked());
        notifyDataSetChanged();
    }

    public int getCheckedDrawsCount() {
        int count = 0;
        for (GameBean.Games.Draws draw : draws)
            count = draw.isChecked() ? ++count : count;
        return count;
    }

    private class Holder {
        private LinearLayout layout;
        private TextView drawDateTime;
        private ImageView iconCheck;

        public Holder(View view) {
            this.layout = (LinearLayout) view.findViewById(R.id.single_item);
            this.drawDateTime = (TextView) view.findViewById(R.id.draw_date_time);
            this.iconCheck = (ImageView) view.findViewById(R.id.icon_check);
        }
    }
}
