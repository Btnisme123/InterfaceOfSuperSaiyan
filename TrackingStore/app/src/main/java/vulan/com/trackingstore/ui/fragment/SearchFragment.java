package vulan.com.trackingstore.ui.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Switch;

import java.util.ArrayList;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.adapter.RecyclerTagAdapter;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.data.model.TagSearch;
import vulan.com.trackingstore.ui.activity.ShopActivity;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;

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
        mRecyclerSearch.setAdapter(adapter);

        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagSearchArrayList.add(new TagSearch(mEditTag.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });
        swSearch.setOnCheckedChangeListener(this);
        sharedPreferences = getActivity().getSharedPreferences(Constants.STATUS_SEARCH, Context.MODE_PRIVATE);
    }


    //switch search listener
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.STATUS_SEARCH, true);
            editor.commit();
            mKeyword = tagSearchArrayList.get(0).getTagContent().toString();
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.STATUS_SEARCH, false);
            editor.commit();
        }
    }

    private void notifySearch(Shop shop) {
        RemoteViews remoteViews = new RemoteViews(getActivity().getPackageName(), R.layout.notification_view);
//        remoteViews.setImageViewResource(R.id.img_notify, shop.getmImageShop());
        remoteViews.setTextViewText(R.id.tv_notify, "Tìm được cửa hàng " + shop.getmShopName() + " phù hợp với yêu cầu");
        notiBuilder = new NotificationCompat.Builder(getActivity())
                .setSmallIcon(R.drawable.adidas_logo)
                .setContent(remoteViews)
                .setAutoCancel(true);
        Intent intent = new Intent(getActivity(), ShopActivity.class);
//        intent.putExtra("shop", shop);
        int iUniqueId = (int) (System.currentTimeMillis() & 0xfffffff); //help intent transfer data (don't know why)
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), iUniqueId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notiBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = notiBuilder.build();
        notificationManager.notify(MY_NOTIFICATION_ID, notification);
    }
}
