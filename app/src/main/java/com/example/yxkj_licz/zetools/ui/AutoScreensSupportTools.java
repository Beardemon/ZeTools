package com.example.yxkj_licz.zetools.ui;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;


/**
 * @author: Licz
 * date:   On 2018/7/31
 */
public class AutoScreensSupportTools {

    private static float sNoncompatDensity = 0;
    private static float sNoncompatScaledDensity = 0;


    public AutoScreensSupportTools() {
    }


    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application) {

        setCustomDensity(activity, application, 360);
    }

    /**
     * 根据适配图的宽度来适配ui尺寸
     *
     * @param activity
     * @param application
     * @param widthDp
     */

    public static void setCustomDensity(@NonNull Activity activity, @NonNull final Application application, int widthDp) {
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (0 == sNoncompatDensity) {
            sNoncompatDensity = appDisplayMetrics.density;
            sNoncompatScaledDensity = appDisplayMetrics.scaledDensity;
            //系统设置中切换了字体，回到应用中要做相应处理
            application.registerComponentCallbacks(new ComponentCallbacks2() {


                @Override
                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.densityDpi > 0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;

                    }
                }

                @Override
                public void onTrimMemory(int i) {

                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        float targetDensity = appDisplayMetrics.widthPixels / widthDp;
        float targetScaleDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        //该魔法值"160"是dp转px中的一个常量
        int targetDensityDpi = (int) (160 * targetDensity);
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;
        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaleDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;


    }

}
