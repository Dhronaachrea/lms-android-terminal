package demo.stpl.com.tpsmergedbuild.animation;

import android.graphics.drawable.AnimationDrawable;

/**
 * Created by stpl on 9/7/2016.
 */
public class LoadingAnimation extends AnimationDrawable {

    public interface IAnimationFinishListener {
        void onAnimationFinished();
    }

    private boolean finished = false;
    private IAnimationFinishListener animationFinishListener;

    public IAnimationFinishListener getAnimationFinishListener() {
        return animationFinishListener;
    }

    public void setAnimationFinishListener(IAnimationFinishListener animationFinishListener) {
        this.animationFinishListener = animationFinishListener;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public boolean selectDrawable(int idx) {
        boolean ret = super.selectDrawable(idx);

        if ((idx != 0) && (idx == getNumberOfFrames() - 1)) {
            if (!finished) {
                finished = true;
                if (animationFinishListener != null) animationFinishListener.onAnimationFinished();
            }
        }

        return ret;
    }
}
