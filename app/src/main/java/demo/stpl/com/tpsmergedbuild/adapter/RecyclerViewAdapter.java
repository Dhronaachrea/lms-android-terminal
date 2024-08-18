package demo.stpl.com.tpsmergedbuild.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import demo.stpl.com.tpsmergedbuild.R;

//import skilrock.com.tpsgame.R;

/**
 * Created by stpl on 14-Oct-16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    LayoutInflater layoutInflater;
    private int[] icons = {R.mipmap.lucky_5_90, R.mipmap.mini_keno, R.mipmap.mini_roulette, R.mipmap.super_keno, R.mipmap.fortune};

    public RecyclerViewAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_recy_adapter, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.gameImg.setImageResource(icons[position % icons.length]);
//        holder.gameText.setText(gameSelectedViewBean.getGameDispName());
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView gameImg;
        private TextView gameText;
        private LinearLayout mainViewGameList;

        public ViewHolder(View view) {
            super(view);
            gameImg = (ImageView) view.findViewById(R.id.game_img);
            gameText = (TextView) view.findViewById(R.id.game_text);
            mainViewGameList = (LinearLayout) view.findViewById(R.id.main_view_game_list);
        }


    }
}
