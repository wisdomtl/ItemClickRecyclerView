package taylor.com.recyclerview;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * a RecyclerView which has an child click listener
 */
public class BaseRecyclerView extends RecyclerView {
    private OnItemClickListener onItemClickListener;
    private GestureDetector gestureDetector;

    public BaseRecyclerView(Context context) {
        super(context);
        init();
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        gestureDetector = new GestureDetector(getContext(), new GestureListener());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //intercept touch event for reacting to click event on item of RecyclerView
        gestureDetector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }

    private class GestureListener implements GestureDetector.OnGestureListener {

        private static final int INVALID_POSITION = -1;
        //a Rect used to detect whether touch point is in child rectangle region or not
        private Rect mTouchFrame;

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            int x = (int) e.getX();
            int y = (int) e.getY();
            int position = pointToPosition(x, y);
            if (position != INVALID_POSITION) {
                try {
                    View child = getChildAt(position);
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(child, getChildAdapterPosition(child), getAdapter());
                    }
                } catch (Exception e1) {
                }
            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        /**
         * convert pointer to the layout position in RecyclerView
         *
         * @param x
         * @param y
         * @return
         */
        public int pointToPosition(int x, int y) {
            Rect frame = mTouchFrame;
            if (frame == null) {
                mTouchFrame = new Rect();
                frame = mTouchFrame;
            }

            final int count = getChildCount();
            for (int i = count - 1; i >= 0; i--) {
                final View child = getChildAt(i);
                if (child.getVisibility() == View.VISIBLE) {
                    child.getHitRect(frame);
                    if (frame.contains(x, y)) {
                        return i;
                    }
                }
            }
            return INVALID_POSITION;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View item, int adapterPosition, Adapter adapter);
    }
}
