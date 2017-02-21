package vulan.com.trackingstore.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.ShopPagerAdapter;
import vulan.com.trackingstore.ui.base.BaseFragment;

public class ShopActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    ShopPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        findViews();
    }

    private void findViews() {
        viewPager = (ViewPager) this.findViewById(R.id.view_pager);
        tabLayout = (TabLayout) this.findViewById(R.id.tab_title_shop);

        FragmentManager manager = getFragmentManager();
        adapter = new ShopPagerAdapter(manager, this);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    public void replaceFragment(BaseFragment fragment, String tag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.animator.fragment_slide_right_enter, R.animator.fragment_slide_left_exit,
                R.animator.fragment_slide_left_enter, R.animator.fragment_slide_right_exit)
                .replace(R.id.fragment_container, fragment, tag)
                .addToBackStack("").commit();
    }

}
