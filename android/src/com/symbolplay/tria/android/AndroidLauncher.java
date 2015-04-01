package com.symbolplay.tria.android;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.symbolplay.tria.GameContainer;

public class AndroidLauncher extends AndroidApplication {
    
    private FacebookAndroid facebookAndroid;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = true;
        config.useCompass = false;
        
        PlatformSpecificAndroid platformSpecificAndroid = new PlatformSpecificAndroid(this);
        facebookAndroid = new FacebookAndroid(this, savedInstanceState);
        
        initialize(new GameContainer(platformSpecificAndroid, facebookAndroid), config);
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        facebookAndroid.onResume();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookAndroid.onActivityResult(requestCode, resultCode, data);
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        facebookAndroid.onSaveInstanceState(outState);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        facebookAndroid.onPause();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        facebookAndroid.onDestroy();
    }
    
//    public void printHashKey() {
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo("com.symbolplay.tria.android", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.i("TEMPTAGHASH KEY:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (NameNotFoundException e) {
//        } catch (NoSuchAlgorithmException e) {
//        }
//    }
}