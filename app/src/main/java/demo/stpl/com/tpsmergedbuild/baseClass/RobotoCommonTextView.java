package demo.stpl.com.tpsmergedbuild.baseClass;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import demo.stpl.com.tpsmergedbuild.R;

//import skilrock.com.tpsgame.R;


public class RobotoCommonTextView extends TextView {
    public RobotoCommonTextView(Context context) {
        super(context);
    }

    public RobotoCommonTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RobotoCommonTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RobotoCommonTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    protected void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RobotoCommonTextView);
            String fontNumber = a.getString(R.styleable.RobotoCommonTextView_typeface_common);
            String fontName = "regular";
            if (fontNumber != null) {
                switch (fontNumber) {
                    case "0":
                        fontName = "bold";
                        break;
                    case "1":
                        fontName = "medium";
                        break;
                    case "2":
                        fontName = "regular";
                        break;
                    case "3":
                        fontName = "light";
                        break;
                    case "4":
                        fontName = "thin";
                        break;
//                    case "5":
//                        fontName = "OPEN-SANS_REGULAR.ttf";
//                        break;
//                    case "6":
//                        fontName = "OPEN-SANS_SEMIBOLD.ttf";
//                        break;
                }
            }
//            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), fontName);
            setTypeface(TpsGamesClass.getInstance().getTypeFace(fontName));
            a.recycle();
        }
    }
}
