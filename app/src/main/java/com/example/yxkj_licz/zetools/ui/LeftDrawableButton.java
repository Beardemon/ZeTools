package com.example.yxkj_licz.zetools.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * @author: Licz
 * date:   On 2018/8/2
 */
@SuppressLint("AppCompatCustomView")
public class LeftDrawableButton extends Button {
    public LeftDrawableButton(Context context) {
        super(context);
    }

    public LeftDrawableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // getCompoundDrawables() : Returns drawables for the left, top, right, and bottom borders.
        Drawable[] drawable = getCompoundDrawables();
        //得到drawableLeft设置的drawable对象
        Drawable leftDrawable = drawable[0];
        if (leftDrawable != null) {
            //得到leftdrawable 的宽度
            int leftDrawableWidth = leftDrawable.getIntrinsicWidth();
            //得到drawable与text之间的间距
            int drawablePadding = getCompoundDrawablePadding();

            //得到文本的宽度
            int textWidth = (int) getPaint().measureText(getText().toString().trim());

            int bodyWidth = leftDrawableWidth + drawablePadding + textWidth;
            canvas.save();
            //将内容在X轴方向平移
            canvas.translate((getWidth() - bodyWidth) / 2, 0);

        }
        super.onDraw(canvas);
    }
}
