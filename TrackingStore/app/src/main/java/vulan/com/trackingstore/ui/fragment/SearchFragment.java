package vulan.com.trackingstore.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerTagAdapter;
import vulan.com.trackingstore.data.model.TagSearch;
import vulan.com.trackingstore.ui.base.BaseFragment;

/**
 * Created by Thanh on 10/27/2016.
 */

public class SearchFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerSearch;
    private ImageView mButtonAdd;
    private EditText mEditTag;
    private ArrayList<TagSearch> tagSearchArrayList;
    private RecyclerTagAdapter adapter;
    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findViews(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_search;
    }

    private void findViews(View view) {
        mRecyclerSearch = (RecyclerView) view.findViewById(R.id.recycler_search);
        mButtonAdd = (ImageView) view.findViewById(R.id.btn_add_tag);
        mEditTag = (EditText) view.findViewById(R.id.ed_tag);
    }
    private void init(){
        tagSearchArrayList = new ArrayList<>();
        mRecyclerSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerTagAdapter(getActivity(),tagSearchArrayList);
        mRecyclerSearch.setAdapter(adapter);
        mButtonAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_tag:
                tagSearchArrayList.add(new TagSearch(mEditTag.getText().toString()));
                mEditTag.setText("");
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
