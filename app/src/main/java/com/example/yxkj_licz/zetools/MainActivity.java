package com.example.yxkj_licz.zetools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yxkj_licz.zetools.ui.AutoScreensSupportTools;

/**
 * @author: Licz
 * date:   On 2018/7/31
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoScreensSupportTools.setCustomDensity(this, getApplication());
        setContentView(R.layout.activity_main);
    }
}
