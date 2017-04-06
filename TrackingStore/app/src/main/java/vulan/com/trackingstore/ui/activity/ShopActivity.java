package vulan.com.trackingstore.ui.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.ShopPagerAdapter;
import vulan.com.trackingstore.data.model.Shop;

public class ShopActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ShopPagerAdapter adapter;
    private Shop shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        findViews();
        init();
    }

    private void findViews() {
        viewPager = (ViewPager) this.findViewById(R.id.view_pager);
        tabLayout = (TabLayout) this.findViewById(R.id.tab_title_shop);
    }

    private void init() {
        Intent intent = getIntent();
        shop = (Shop) intent.getSerializableExtra("shop");

        FragmentManager manager = getFragmentManager();
        adapter = new ShopPagerAdapter(manager, this, shop);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
