package vulan.com.trackingstore.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import java.util.ArrayList;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerTagAdapter;
import vulan.com.trackingstore.data.listener.OnTagRemoveListener;
import vulan.com.trackingstore.data.model.TagSearch;
import vulan.com.trackingstore.ui.activity.MainActivity;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Thanh on 10/27/2016.
 */

public class SearchFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {
    private RecyclerView mRecyclerSearch;
    private ImageView mButtonAdd;
    private EditText mEditTag;
    private Switch swSearch;
    private ArrayList<TagSearch> tagSearchArrayList;
    private RecyclerTagAdapter adapter;

    private NotificationCompat.Builder notiBuilder;

    private static final int MY_NOTIFICATION_ID = 12345;
    private static final int MY_REQUEST_CODE = 100;

    public static String mKeyword = "";
    private String mTag;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        findViews(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onResume() {
        super.onResume();
        sharedPreferences = getActivity().getSharedPreferences(Constants.STATUS_SEARCH, MODE_PRIVATE);
        if (sharedPreferences.getBoolean(Constants.STATUS_SEARCH, false)) {
            swSearch.setChecked(true);
        } else {
            swSearch.setChecked(false);
        }
        sharedPreferences = getActivity().getSharedPreferences(Constants.TAG_SEARCH, MODE_PRIVATE);
        if (sharedPreferences != null) {
            if (tagSearchArrayList.size() == 0) {
                String mTagSearch = sharedPreferences.getString(Constants.TAG_SEARCH, "");
                if (!mTagSearch.equals("")) {
                    String[] mTagList = mTagSearch.split(",");
                    for (int i = 0; i < mTagList.length; i++) {
                        tagSearchArrayList.add(new TagSearch(mTagList[i]));
                    }
                    adapter.notifyDataSetChanged();
                }
            }

        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void findViews(View view) {
        mRecyclerSearch = (RecyclerView) view.findViewById(R.id.recycler_search);
        mButtonAdd = (ImageView) view.findViewById(R.id.btn_add_tag);
        mEditTag = (EditText) view.findViewById(R.id.ed_tag);
        swSearch = (Switch) view.findViewById(R.id.sw_notify);
    }

    private void init() {
        tagSearchArrayList = new ArrayList<>();
        mRecyclerSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerTagAdapter(getActivity(), tagSearchArrayList);
        adapter.setOnTagRemoveClick(new OnTagRemoveListener() {
            @Override
            public void onTagRemove(int position) {
                tagSearchArrayList.remove(position);
                adapter.notifyDataSetChanged();
                if (tagSearchArrayList.size() > 0) {
                    mTag = "";
                    for (int i = 0; i < tagSearchArrayList.size(); i++) {
                        mTag = mTag + tagSearchArrayList.get(i).getTagContent() + ",";
                    }
                    sharedPreferences = getActivity().getSharedPreferences(Constants.TAG_SEARCH, MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
                    editor1.putString(Constants.TAG_SEARCH, mTag);
                    editor1.commit();
                } else {
                    mTag = "";
                    sharedPreferences = getActivity().getSharedPreferences(Constants.TAG_SEARCH, MODE_PRIVATE);
                    SharedPreferences.Editor editor1 = sharedPreferences.edit();
                    editor1.putString(Constants.TAG_SEARCH, mTag);
                    editor1.commit();
                }
            }
        });
        mRecyclerSearch.setAdapter(adapter);
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = mEditTag.getText().toString().trim();
                if (!tag.equals("")) {
                    tagSearchArrayList.add(new TagSearch(mEditTag.getText().toString()));
                    adapter.notifyDataSetChanged();
                    if (tagSearchArrayList.size() > 0) {
                        mTag = "";
                        for (int i = 0; i < tagSearchArrayList.size(); i++) {
                            mTag = mTag + tagSearchArrayList.get(i).getTagContent() + ",";
                        }
                        sharedPreferences = getActivity().getSharedPreferences(Constants.TAG_SEARCH, MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sharedPreferences.edit();
                        editor1.putString(Constants.TAG_SEARCH, mTag);
                        editor1.commit();
                    }
                }
                mEditTag.setText("");
            }
        });
        swSearch.setOnCheckedChangeListener(this);
    }


    //switch search listener
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            sharedPreferences = getActivity().getSharedPreferences(Constants.STATUS_SEARCH, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.STATUS_SEARCH, true);
            editor.putBoolean(Constants.STATUS_MACID,true);
            editor.putString(Constants.MAC_ID_SEARCH, "");
            editor.commit();

            Log.e("test share",sharedPreferences.getBoolean(Constants.STATUS_MACID,false)+"");
            sharedPreferences = getActivity().getSharedPreferences(Constants.MAC_ID, MODE_PRIVATE);
            Intent intent = new Intent();
            intent.putExtra(Constants.MAC_ID, sharedPreferences.getString(Constants.MAC_ID, ""));
            intent.setAction(MainActivity.ACTION_BEACON_CHANGE);
            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        } else {
            sharedPreferences = getActivity().getSharedPreferences(Constants.STATUS_SEARCH, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.STATUS_SEARCH, false);
            editor.commit();
        }
    }
}
