package vulan.com.trackingstore.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.FoodCategoryAdapter;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.FakeContainer;
import vulan.com.trackingstore.util.widget.LinearItemDecoration;

/**
 * Created by Thanh on 10/21/2016.
 */

public class RestaurantFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private FoodCategoryAdapter mAdapter;
    public final static String TAG_RESTAURANT_FRAGMENT = "restaurant fragment";

    @Override
    protected void onCreateContentView(View rootView) {
        findViews(rootView);
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_restaurant;
    }

    private void findViews(View view) {
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recycler_food_drink);
        mAdapter=new FoodCategoryAdapter(FakeContainer.getFoodDrinkList(),getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new LinearItemDecoration(getActivity()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
