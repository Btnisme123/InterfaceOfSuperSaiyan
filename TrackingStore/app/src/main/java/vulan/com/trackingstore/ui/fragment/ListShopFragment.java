package vulan.com.trackingstore.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.ListShopAdapter;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.ui.activity.ShopActivity;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.FakeContainer;

/**
 * Created by Thanh on 2/15/2017.
 */

public class ListShopFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView mListShop;
    private SearchView mSearchView;
    private ListShopAdapter adapter;
    private ArrayList<Shop> shopArrayList = new ArrayList<>();

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findViews(rootView);
        fakeData();
        init();
        adapter = new ListShopAdapter(getActivity(), shopArrayList);
        mListShop.setAdapter(adapter);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_list_shop;
    }

    private void findViews(View rootView) {
        mListShop = (ListView) rootView.findViewById(R.id.list_shop);
        mSearchView = (SearchView) rootView.findViewById(R.id.search_view);
    }

    private void init() {
        mListShop.setOnItemClickListener(this);
    }

    private void fakeData() {
        shopArrayList = FakeContainer.getListShop();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ShopActivity.class);
        startActivity(intent);
    }
}
