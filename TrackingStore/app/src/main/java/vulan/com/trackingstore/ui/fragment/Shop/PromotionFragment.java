package vulan.com.trackingstore.ui.fragment.Shop;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerViewAdapter;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;
import vulan.com.trackingstore.util.FakeContainer;

/**
 * Created by Thanh on 2/20/2017.
 */

public class PromotionFragment extends BaseFragment {
    private RecyclerView recyclerMan, recyclerWoman, recyclerOther;
    private List<Product> productsMan,productsWoman,productsOther;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreateContentView(View rootView) {
        findViews(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_promotion;
    }

    private void findViews(View rootView) {
        recyclerMan = (RecyclerView) rootView.findViewById(R.id.recycler_promotion_man);
        recyclerWoman = (RecyclerView) rootView.findViewById(R.id.recycler_promotion_woman);
        recyclerOther = (RecyclerView) rootView.findViewById(R.id.recycler_promotion_other);
    }

    private void init() {
        productsMan = new ArrayList<>();
        productsMan = FakeContainer.getListProduct(1);
        recyclerMan.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new RecyclerViewAdapter(getActivity(), null, productsMan, Constants.RecyclerViewType.PROMOTION_TYPE);
        recyclerMan.setAdapter(adapter);

        productsWoman = new ArrayList<>();
        productsWoman = FakeContainer.getListProduct(2);
        recyclerWoman.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new RecyclerViewAdapter(getActivity(), null, productsWoman, Constants.RecyclerViewType.PROMOTION_TYPE);
        recyclerWoman.setAdapter(adapter);

        productsOther = new ArrayList<>();
        productsOther = FakeContainer.getListProduct(3);
        recyclerOther.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new RecyclerViewAdapter(getActivity(), null, productsOther, Constants.RecyclerViewType.PROMOTION_TYPE);
        recyclerOther.setAdapter(adapter);
    }
}
