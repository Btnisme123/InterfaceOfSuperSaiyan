package vulan.com.trackingstore.ui.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.FoodAdapter;
import vulan.com.trackingstore.data.model.Food;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.FakeContainer;
import vulan.com.trackingstore.util.widget.GridItemDecoration;
import vulan.com.trackingstore.util.widget.LinearItemDecoration;


public class FoodFragment extends BaseFragment implements View.OnClickListener {

    private static final int NUMBER_OF_COLUMN = 2;
    private RecyclerView mRecyclerView;
    private FoodAdapter mAdapter;
    private ImageView mGridOrder, mLinearOrder;
    private List<Food> foodList;
    private LinearItemDecoration mListViewItemDecoration;
    private GridItemDecoration mGridViewItemDecoration;

    @Override
    protected void onCreateContentView(View rootView) {
        findView(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_food;
    }

    private void init() {
        mListViewItemDecoration = new LinearItemDecoration(getActivity());
        mGridViewItemDecoration = new GridItemDecoration(getActivity());
        foodList = FakeContainer.getListFood();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), NUMBER_OF_COLUMN);
        mAdapter = new FoodAdapter(getActivity(), gridLayoutManager, foodList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.addItemDecoration(new GridItemDecoration(getActivity()));
    }

    private void findView(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_food);
        mGridOrder = (ImageView) rootView.findViewById(R.id.grid_order);
        mLinearOrder = (ImageView) rootView.findViewById(R.id.linear_order);
        mGridOrder.setOnClickListener(this);
        mLinearOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.grid_order:
                if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.removeItemDecoration(mGridViewItemDecoration);
                    mRecyclerView.addItemDecoration(mListViewItemDecoration);
                }
                break;
            case R.id.linear_order:
                if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), NUMBER_OF_COLUMN));
                    mRecyclerView.removeItemDecoration(mListViewItemDecoration);
                    mRecyclerView.addItemDecoration(mGridViewItemDecoration);
                }
                break;
        }
    }
}
