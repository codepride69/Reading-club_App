package com.codepride.rahacreditfinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {
    private Button gopagebutton;
    private static final String TAG = "";
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gopagebutton = findViewById(R.id.button);

        showads();

        gopagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd!=null){
                    mInterstitialAd.show(MainActivity.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        private static final String TAG = "";

                        @Override
                        public void onAdClicked() {
                            // Called when a click is recorded for an ad.
                            Log.d(TAG, "Ad was clicked.");
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Called when ad is dismissed.
                            // Set the ad reference to null so you don't show the ad a second time.
                            Log.d(TAG, "Ad dismissed fullscreen content.");

                            startActivity(new Intent(MainActivity.this, About.class));
                            mInterstitialAd = null;
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            // Called when ad fails to show.
                            Log.e(TAG, "Ad failed to show fullscreen content.");


                            mInterstitialAd = null;
                        }

                        @Override
                        public void onAdImpression() {
                            // Called when an impression is recorded for an ad.
                            Log.d(TAG, "Ad recorded an impression.");
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            // Called when ad is shown.

                            Log.d(TAG, "Ad showed fullscreen content.");
                        }
                    });
                }else {

                    Log.d(TAG, "Ad showed fullscreen content.");
                    startActivity(new Intent(MainActivity.this, About.class));
                    mInterstitialAd = null;


                }
            }
        });
    }
    private void showads() {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        String TAG = "";
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        String TAG = "";
                        Log.i(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });
    }
}