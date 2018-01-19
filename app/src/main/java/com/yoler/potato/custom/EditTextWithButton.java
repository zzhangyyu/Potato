package com.yoler.potato.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.yoler.potato.R;

/**
 * Created by zhangyu on 2017/12/4.
 */

public class EditTextWithButton extends AppCompatEditText {

    private static final int DRAWABLE_LEFT = 0;
    private static final int DRAWABLE_TOP = 1;
    private static final int DRAWABLE_RIGHT = 2;
    private static final int DRAWABLE_BOTTOM = 3;
    private Drawable mClearDrawable;

    public EditTextWithButton(Context context) {
        super(context);
        init();
    }

    public EditTextWithButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClearDrawable = getResources().getDrawable(R.drawable.ic_normal_delete);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setClearIconVisible(hasFocus() && text.length() > 0);
    }

    /**
     * 当ClearEditText焦点发生变化、输入长度为零时，隐藏删除图标，否则，显示删除图标
     *
     * @param focused
     * @param direction
     * @param previouslyFocusedRect
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setClearIconVisible(focused && length() > 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP://用户抬起了手指
                Drawable drawable = getCompoundDrawables()[DRAWABLE_RIGHT];
                /**--------------复杂写法，便于理解代码------------------
                if (drawable != null) {
                    int xClick = (int) event.getX();//获取点击位置的x坐标
                    int xDrawableRight = getWidth() - getPaddingRight();//删除图标右边缘的x坐标
                    int xDrawableLeft = getWidth() - getPaddingRight() - drawable.getBounds().width();//删除图标左边缘的x坐标
                    if (xClick <= xDrawableRight && xClick >= xDrawableLeft) {
                        setText("");
                    }
                }
                 --------------------------------*/
                if (drawable != null && (int) event.getX() <= (getWidth() - getPaddingRight())
                        && (int) event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width())) {
                    setText("");
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void setClearIconVisible(boolean visible) {
        setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[DRAWABLE_LEFT], getCompoundDrawables()[DRAWABLE_TOP],
                visible ? mClearDrawable : null, getCompoundDrawables()[DRAWABLE_BOTTOM]);
    }

}
