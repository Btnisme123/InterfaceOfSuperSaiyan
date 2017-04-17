package vulan.com.trackingstore.ui.fragment.Shop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.ui.base.BaseFragment;
import vulan.com.trackingstore.util.Constants;

/**
 * Created by Thanh on 10/21/2016.
 */

public class ShopFragment extends BaseFragment {
    private Shop shop;
    private ImageView mImageLogo;
    private TextView mTextName, mTextAddress, mTextDescript, mTextEmail, mTextPhoneNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
        shop = (Shop) getArguments().getSerializable(Constants.ShopInfo.SHOP_MODEL);
        findViews(rootView);
        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_info_shop;
    }

    private void findViews(View view) {
        mImageLogo = (ImageView) view.findViewById(R.id.img_shop);
        mTextName = (TextView) view.findViewById(R.id.tv_shop_name);
        mTextAddress = (TextView) view.findViewById(R.id.tv_address);
        mTextDescript = (TextView) view.findViewById(R.id.tv_desription);
        mTextEmail = (TextView) view.findViewById(R.id.tv_email);
        mTextPhoneNum = (TextView) view.findViewById(R.id.tv_phone_num);
    }

    private void init() {
        Glide.with(getActivity())
                .load(shop.getmUrlLogo())
                .fitCenter()
                .into(mImageLogo);
        mTextName.setText(shop.getmShopName());
        mTextAddress.setText(shop.getmAddress());
        mTextPhoneNum.setText(shop.getmPhoneNum());
        mTextEmail.setText(shop.getmEmail());
        mTextDescript.setText(shop.getmDescript());
    }
}
