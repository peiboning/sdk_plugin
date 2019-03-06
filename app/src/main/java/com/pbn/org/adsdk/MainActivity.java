package com.pbn.org.adsdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.pbn.org.adsdk.library.NativeAdLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadClick(View view) {
        NativeAdLoader loader = new NativeAdLoader();
        loader.load("df", new NativeAdLoader.INativeAdLoaderListener() {
            @Override
            public void onSuccess(INativeAd ad) {
                Log.e("MainActivity", "title:" + ad.getTitle() + ", desc:" + ad.getDesc());
            }

            @Override
            public void onFailed() {
                Log.e("MainActivity", "onLoadFailed");
            }
        });
    }
}
