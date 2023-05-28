package com.examples;

import android.os.Bundle;
import com.facebook.react.ReactActivity;
import org.devio.rn.splashscreen.SplashScreen;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript. This is used to schedule
     * rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "examples";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // here
        SplashScreen.show(this);
        super.onCreate(savedInstanceState);
    }
}
