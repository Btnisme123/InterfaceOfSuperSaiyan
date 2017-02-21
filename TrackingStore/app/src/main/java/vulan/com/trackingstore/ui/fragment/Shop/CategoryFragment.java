package vulan.com.trackingstore.ui.fragment.Shop;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerViewAdapter;
import vulan.com.trackingstore.data.model.ProductCategory;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;
import vulan.com.trackingstore.util.FakeContainer;


public class CategoryFragment extends BaseFragment {
    private RecyclerViewAdapter adapter;
    private RecyclerView listCategoryMan, listCategoryWoman, listCategoryOther;
    private int NUM_OF_COLUMNS = 3;
    private List<ProductCategory> productCategoriesMan, productCategoriesWoman, productCategoriesOther;

    @Override
    protected void onCreateContentView(View rootView) {
        findView(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_category;
    }

    private void init() {
        productCategoriesMan = new ArrayList<>();
        productCategoriesMan = FakeContainer.getListCategory(1);
        listCategoryMan.setLayoutManager(new GridLayoutManager(getActivity(), NUM_OF_COLUMNS));
        adapter = new RecyclerViewAdapter(getActivity(), productCategoriesMan, null, Constants.RecyclerViewType.CATEGORY_TYPE);
        listCategoryMan.setAdapter(adapter);

        productCategoriesWoman = new ArrayList<>();
        productCategoriesWoman = FakeContainer.getListCategory(2);
        listCategoryWoman.setLayoutManager(new GridLayoutManager(getActivity(), NUM_OF_COLUMNS));
        adapter = new RecyclerViewAdapter(getActivity(), productCategoriesWoman, null, Constants.RecyclerViewType.CATEGORY_TYPE);
        listCategoryWoman.setAdapter(adapter);

        productCategoriesOther = new ArrayList<>();
        productCategoriesOther = FakeContainer.getListCategory(3);
        listCategoryOther.setLayoutManager(new GridLayoutManager(getActivity(), NUM_OF_COLUMNS));
        adapter = new RecyclerViewAdapter(getActivity(), productCategoriesOther, null, Constants.RecyclerViewType.CATEGORY_TYPE);
        listCategoryOther.setAdapter(adapter);
    }

    private void findView(View rootView) {
        listCategoryMan = (RecyclerView) rootView.findViewById(R.id.recycler_category_man);
        listCategoryWoman = (RecyclerView) rootView.findViewById(R.id.recycler_category_woman);
        listCategoryOther = (RecyclerView) rootView.findViewById(R.id.recycler_category_other);
    }
}

