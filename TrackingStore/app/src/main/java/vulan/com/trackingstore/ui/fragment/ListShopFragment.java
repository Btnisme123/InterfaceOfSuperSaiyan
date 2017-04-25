package vulan.com.trackingstore.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.ListShopAdapter;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.data.network.ApiRequest;
import vulan.com.trackingstore.ui.activity.ShopActivity;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;

/**
 * Created by Thanh on 2/15/2017.
 */

public class ListShopFragment extends BaseFragment {
    private ListView mListShop;
    private SearchView mSearchView;
    private ListShopAdapter adapter;
    private List<Shop> shopArrayList = new ArrayList<>();

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findViews(rootView);
        init();
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
        //setup searchview
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSearchView.setIconified(false);
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
        //check if notify status = true-> show list search, = false -> normal
        ApiRequest.getInstance().init();
        if (!getArguments().getBoolean(Constants.NOTIFICATION_SHOW, false)) {
            ApiRequest.getInstance().getAllShop(new Callback<List<Shop>>() {
                @Override
                public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                    shopArrayList = response.body();
                    adapter = new ListShopAdapter(getActivity(), shopArrayList);
                    mListShop.setAdapter(adapter);
                    mListShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), ShopActivity.class);
                            intent.putExtra(Constants.ShopInfo.SHOP_MODEL, (Serializable) shopArrayList.get(i));
                            startActivity(intent);
                        }
                    });
                }

                @Override
                public void onFailure(Call<List<Shop>> call, Throwable t) {

                }
            });
        } else {
            shopArrayList = getArguments().getParcelableArrayList(Constants.NOTIFICATION_LIST);
            adapter = new ListShopAdapter(getActivity(), shopArrayList);
            mListShop.setAdapter(adapter);
            mListShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity(), ShopActivity.class);
                    intent.putExtra(Constants.ShopInfo.SHOP_MODEL, (Serializable) shopArrayList.get(i));
                    startActivity(intent);
                }
            });
        }
    }
}
