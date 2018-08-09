package com.example.yxkj_licz.zetools.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

/**
 * @author: Licz
 * date:   On 2018/8/1
 */
@SuppressLint("AppCompatCustomView")
public class PswEditText extends EditText {
    private static final String TAG = "PswEditText";
    //是否显示数字密码
    private boolean isShowPswNum = true;
    //数字密码位数
    private int mPswLength = 6;
    //数字密码框总宽度
    private int mPswWidth;
    //分割线大小
    private int mDivisionLineSize = 1;
    //外层边框大小
    private int mBoardSize = 1;
    // 一个密码所占的宽度
    private int mPswItemWidth;
    //字体大小
    private float mTextSize = 40;
    //边框颜色
    private int boardColor = Color.parseColor("#9999A1");
    //绘制边框的画笔宽度
    private int paintWeight = 3;


    private Paint mPaint;

    public PswEditText(Context context) {
        this(context, null);
    }

    public PswEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //设置只可以输入数字
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        //设置不显示光标
        setCursorVisible(false);
        //设置最长输入
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(mPswLength)});

        initPaint();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawBoard(canvas);
        drawDivisionLine(canvas);
        drawPsw(canvas);
    }


    private void initPaint() {
        mPaint = new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        //防抖动
        mPaint.setDither(true);
    }

    /**
     * 绘制边框
     *
     * @param canvas
     */
    private void drawBoard(Canvas canvas) {
        mPaint.setColor(boardColor);
        mPaint.setStrokeWidth(paintWeight);
        mPaint.setStyle(Paint.Style.STROKE);
        RectF rectF = new RectF(mBoardSize, mBoardSize, getWidth() - mBoardSize, getHeight() - mBoardSize);
        canvas.drawRect(rectF, mPaint);


    }

    /**
     * 绘制分割线
     *
     * @param canvas
     */
    private void drawDivisionLine(Canvas canvas) {
        mPaint.setStrokeWidth(paintWeight);
        mPaint.setColor(boardColor);
        mPswWidth = getWidth() - (mPswLength - paintWeight) * mDivisionLineSize;
        mPswItemWidth = mPswWidth / mPswLength;
        for (int i = 1; i < mPswLength; i++) {
            int startX = i + 1 * mDivisionLineSize + i * mPswItemWidth + mBoardSize;
            canvas.drawLine(startX, mBoardSize, startX, getHeight() - mBoardSize, mPaint);
        }
    }

    /**
     * 绘制密码
     *
     * @param canvas
     */
    private void drawPsw(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(mTextSize);
        int nowPswLength = getText().length();
        String nowText = getText().toString().trim();
        Log.d(TAG, "drawPsw: " + nowText + "--------" + nowPswLength);

        for (int i = 0; i < nowPswLength; i++) {
            int startX = i * mDivisionLineSize + i * mPswItemWidth + mPswItemWidth / 2 + mBoardSize;
            //此处要处理掉字体带来的一部分偏移量
            if (isShowPswNum) {
                //截取nowText的+1位并绘制
                canvas.drawText(nowText.substring(i, i + 1), (float) (startX - 0.25 * mTextSize), (float) ((getHeight() / 2) + 0.5 * mTextSize), mPaint);
            } else {
                canvas.drawText("*", (float) (startX - 0.25 * mTextSize), (float) ((getHeight() / 2) + 0.5 * mTextSize), mPaint);
            }
        }


    }

    /**
     * 设置是否显示数字密码
     *
     * @param isShowPswNum
     */
    public void isShowPswNum(Boolean isShowPswNum) {
        this.isShowPswNum = isShowPswNum;
    }


}
