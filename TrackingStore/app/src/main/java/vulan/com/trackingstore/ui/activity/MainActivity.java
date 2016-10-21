package vulan.com.trackingstore.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import vulan.com.trackingstore.R;

public class MainActivity extends AppCompatActivity {

    NavigationView mNavigationView;
    Toolbar mToolbar;
    LinearLayout mLayoutSlideUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
    }

    private void findView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mLayoutSlideUp= (LinearLayout) findViewById(R.id.layout_slide_up);
    }

    public void init(){
        //mLayoutSlideUp.setV
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
