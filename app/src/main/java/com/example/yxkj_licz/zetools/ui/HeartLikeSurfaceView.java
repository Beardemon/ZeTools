package com.example.yxkj_licz.zetools.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.yxkj_licz.zetools.R;


/**
 * @author: Licz
 * date:   On 2018/8/9
 */
public class HeartLikeSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsDrawing;
    /**
     * 当前显示的图像
     */

    private Bitmap mBitmap;

    private Paint paint;
    int i = 0;

    public HeartLikeSurfaceView(Context context) {
        super(context);
        initView();
    }

    public HeartLikeSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        //surfaceview背景透明
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        //surfaceview嵌套层级最高,在最上层显示
        setZOrderOnTop(true);
        //设置获取焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);


    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mIsDrawing = true;


        try {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inPurgeable = true;
                mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.video_heart_like_icon, options);
            } else {
                mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.video_heart_like_icon);
            }

        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (mBitmap != null && !mBitmap.isRecycled()) {
                mBitmap.recycle();
            }
        }
        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
            //通过线程休眠以控制刷新速度
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void draw() {
        try {
            i = i+5;
            mCanvas = mHolder.lockCanvas();
//            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            mCanvas.drawBitmap(mBitmap, 100+i,100+i, paint);
            //初始化画布并在画布上画一些东西
        } catch (Exception e) {

        } finally {
            //判断画布是否为空，从而避免黑屏情况
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

}
