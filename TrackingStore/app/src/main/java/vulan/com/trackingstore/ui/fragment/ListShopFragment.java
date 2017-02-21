package vulan.com.trackingstore.ui.fragment;

import android.content.Intent;
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

/**
 * Created by Thanh on 2/15/2017.
 */

public class ListShopFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView mListShop;
    private SearchView mSearchView;
    private ListShopAdapter adapter;
    private ArrayList<Shop> shopArrayList = new ArrayList<>();

    @Override
    protected void onCreateContentView(View rootView) {
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
        shopArrayList.add(new Shop("Nike", "192 Cầu Giấy,Q.Cầu Giấy,Hà Nội"));
        shopArrayList.add(new Shop("Adidas", "65 Nguyễn Văn Huyên,Q.Cầu Giấy,Hà Nội"));
        shopArrayList.add(new Shop("The Blues", "Indochina Xuân Thủy,Q.Cầu Giấy,Hà Nội"));
        shopArrayList.add(new Shop("Vans", "74 Nguyễn Văn Cừ,Q.Long Biên,Hà Nội"));
        shopArrayList.add(new Shop("Famous", "139 Núi Trúc,Q.Cầu Giấy,Hà Nội"));
        shopArrayList.add(new Shop("Mẹ và bé", "Hồ Tùng Mậu,Q.Bắc Từ Liêm,Hà Nội"));
        shopArrayList.add(new Shop("Shop tên dài xem nhìn ra gì không", "Địa chỉ cũng thật là dài xem nhìn có bị xấu không còn chỉnh"));
        shopArrayList.add(new Shop("Đông Nobita", "167 Võ Chí Công,Q.Tây Hồ,Hà Nội"));
        shopArrayList.add(new Shop("Lân Quýt", "27 Phạm Hùng,Q.Nam Từ Liêm,Hà Nội"));
        shopArrayList.add(new Shop("Dell", "165 Xuân Thủy,Q.Cầu Giấy,Hà Nội"));
        shopArrayList.add(new Shop("Sion", "Liên Minh Huyền Thoại,Q.Bắc Từ Liêm,Hà Nội"));
        shopArrayList.add(new Shop("Clothes", "19 Mai Dịch,Q.Bắc Từ Liêm,Hà Nội"));
        shopArrayList.add(new Shop("Sport", "42 Tôn Đức Thắng,Q.Đống Đa,Hà Nội"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ShopActivity.class);
        startActivity(intent);
    }
}
