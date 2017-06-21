package com.themetanoia.game;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.themetanoia.game.Lone_Warrior1;
import com.themetanoia.game.Tools.AdHandler;

public class AndroidLauncher extends AndroidApplication implements AdHandler {
	private static final String TAG="AndroidLauncher";
	private final int SHOW_ADS=1;
	private final int HIDE_ADS=0;
	protected AdView adView;
	private InterstitialAd interstitialAd;


	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
				case SHOW_ADS:
					adView.setVisibility(View.VISIBLE);
					break;
				case HIDE_ADS:
					adView.setVisibility(View.GONE);
					break;
			}
		}
	};


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout layout=new RelativeLayout(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView=initializeForView(new Lone_Warrior1(this),config);
		layout.addView(gameView);
		adView=new AdView(this);

		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				int visibility=adView.getVisibility();
				adView.setVisibility(AdView.GONE);
				adView.setVisibility(visibility);
				Log.i(TAG,"Ad Loaded");
			}
		});

		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-8285187526835305/2712426479");
		AdRequest.Builder builder=new AdRequest.Builder();
		//builder.addTestDevice("4EB11DDBB2080300236B5251CFF60A6C");//test
		RelativeLayout.LayoutParams adParams=new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		layout.addView(adView,adParams);
		adView.loadAd(builder.build());
		setContentView(layout);

		setContentView(layout);

				   interstitialAd = new InterstitialAd(this);
		    interstitialAd.setAdUnitId("ca-app-pub-8285187526835305/7986904077");


	}

	@Override
	public void showAds(boolean show) {
		handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}

	@Override
	public void showOrLoadInterstital() {
			   try {
				      runOnUiThread(new Runnable() {
					        public void run() {
						          if (interstitialAd.isLoaded()) {
							            interstitialAd.show();
							Toast.makeText(getApplicationContext(), "Sorry for the ads!", Toast.LENGTH_SHORT).show();
							          }
						          else {
							            AdRequest interstitialRequest = new AdRequest.Builder().build();
							            interstitialAd.loadAd(interstitialRequest);
							            Toast.makeText(getApplicationContext(), "Sorry for the ads!", Toast.LENGTH_SHORT).show();
							          }
						        }
					      });
				    } catch (Exception e) {
				    }
			  }
}
