package com.brndev.gummytactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.appbrain.AppBrain;
import com.brndev.tictactoe.lib.GameActivity;
import com.brndev.tictactoe.lib.GameView.State;
import com.brndev.tictactoe.lib.R;
import com.revmob.RevMobAds;
import com.revmob.ads.banner.Banner;
import com.tapfortap.AdView;
import com.tapfortap.TapForTap;

public class MainActivity extends Activity {

	private WebView mWebView;
	private AdView adView;
	
	private static String APP_ID_AMAZON = "503cbf6d458c240c0000004a";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
     TapForTap.setDefaultAppId("6c62dc20-cf98-012f-fc39-4040d804a637");
     TapForTap.checkIn(this);
        
        AppBrain.init(this);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        
        
        setContentView(R.layout.activity_main);
        
        
        //RevMobAds.startSession(this,APP_ID_AMAZON);
        
        // Create the banner
        //Banner banner = new Banner(APP_ID_AMAZON, this);
         
         //final LinearLayout ll = (LinearLayout) findViewById(R.id.banner);
         
         //ll.addView(banner);
        
     adView = (AdView) findViewById(R.id.ad_view);

    adView.loadAds();
        
        final LinearLayout linLayout = (LinearLayout) findViewById(R.id.outer_layout);
        linLayout.setBackgroundColor(Color.BLACK);
        
        final Button button02 = (Button) findViewById(R.id.tactoebanner);  
        
        button02.setOnClickListener(new View.OnClickListener() {
    
        	
			@Override
			public void onClick(View v) {
				
		        //for android
		        //mWebView.loadUrl("http://slideme.org/applications/gummy-tac-toe");
		        //for amazon
		        openActionView("http://www.amazon.com/gp/product/B008POO4BO");
		        
			}
				
		});
        
     final Button button03 = (Button) findViewById(R.id.gummypopbanner);  
        
        button03.setOnClickListener(new View.OnClickListener() {
    
        	
			@Override
			public void onClick(View v) {

		        //for android
		        //mWebView.loadUrl("http://slideme.org/applications/gummy-pop");
		        //for amazon
		        openActionView("http://www.amazon.com/gp/product/B008N5QOTK");
		        
			}
				
		});

        findViewById(R.id.start_player).setOnClickListener(
                new OnClickListener() {
            public void onClick(View v) {
                startGame("player");
            }
        });

        findViewById(R.id.start_comp).setOnClickListener(
                new OnClickListener() {
            public void onClick(View v) {
                startGame("comp");
            }
        });
        
        
        
        findViewById(R.id.more_games).setOnClickListener(
                new OnClickListener() {
            public void onClick(View v) {
            	openActionView("http://www.amazon.com/s/ref=sr_nr_n_0?rh=n%3A2350149011%2Cp_4%3AB%26R+Nelson+Development%2Cn%3A%212350150011%2Cn%3A2478844011&bbn=2350150011&ie=UTF8&qid=1346540094&rnid=2350150011");
            }
        });
        
    }

    private void startGame(String str) {
        Intent i = new Intent(this, GameActivity.class);
        
		if(str=="player") {
	        i.putExtra(GameActivity.EXTRA_START_PLAYER, State.PLAYER1.getValue());
		}
		else if(str=="comp") {
	        i.putExtra(GameActivity.EXTRA_START_PLAYER, State.PLAYER2.getValue());
		}
		else if(str=="2player") {
	        i.putExtra(GameActivity.EXTRA_START_PLAYER, State.PLAYER3.getValue());
	        i.putExtra("2player", true);
		}
        

        
        startActivity(i);
    }
    
    
    
    private void openActionView(String url){

        Intent updateIntent = null;

        updateIntent = new Intent(Intent.ACTION_VIEW,

                Uri.parse(url));

        startActivity(updateIntent);

    }
	
    
    
    @Override
    public void onResume() {
    	super.onResume();
    	
        //RevMobAds.startSession(this,APP_ID_AMAZON);
     adView.loadAds();
    }
    
    
    @Override
    public void onBackPressed() {
    	super.onBackPressed();
    	
    	//RevMobAds.showFullscreenAd(this, APP_ID_AMAZON);
    	AppBrain.getAds().showInterstitial(this);
    }
}
