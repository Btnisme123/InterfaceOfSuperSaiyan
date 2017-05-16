package vulan.com.trackingstore.ui.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.podcopic.animationlib.library.AnimationType;
import com.podcopic.animationlib.library.StartSmartAnimation;

import vulan.com.trackingstore.R;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 2000;
    private final int PERMISSION_REQUEST_CODE = 1;

    private TextView mTextReload, mTextNetwork;
    private ImageView mImageSplash;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViews();
        changeUISplash();

    }

    private void changeUISplash() {
        if (isNetworkAvailable()) {
            mTextNetwork.setVisibility(View.GONE);
            mTextReload.setVisibility(View.GONE);
            requestPermission();
        } else {
            if (mProgressBar.getVisibility() == View.VISIBLE) {
                mProgressBar.setVisibility(View.GONE);
            }
            mTextNetwork.setVisibility(View.VISIBLE);
            mTextReload.setVisibility(View.VISIBLE);
        }
    }

    private void findViews() {
        mTextReload = (TextView) findViewById(R.id.tv_reload);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTextNetwork = (TextView) findViewById(R.id.tv_network);
        mImageSplash = (ImageView) findViewById(R.id.img_app);


        mTextReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressBar.setVisibility(View.VISIBLE);
                mTextNetwork.setVisibility(View.GONE);
                mTextReload.setVisibility(View.GONE);
                changeUISplash();
            }
        });
    }


    public void waitUntilFinishInitializing() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switchToMainActivity();
            }
        }, SPLASH_DURATION);
    }

    private void switchToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void requestPermission() {
        /** REQUEST PERMISSION */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.INTERNET") != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.INTERNET", "android.permission.ACCESS_FINE_LOCATION"}, PERMISSION_REQUEST_CODE);
            } else {
                waitUntilFinishInitializing();
            }
        } else {
            waitUntilFinishInitializing();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    waitUntilFinishInitializing();
                }
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connec = (ConnectivityManager) getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        if (connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;

        } else if (connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.DISCONNECTED ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }
}
