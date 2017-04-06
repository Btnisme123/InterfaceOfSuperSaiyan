package vulan.com.trackingstore.ui.fragment.Shop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.Shop;
import vulan.com.trackingstore.ui.base.BaseFragment;

/**
 * Created by Thanh on 10/21/2016.
 */

public class ShopFragment extends BaseFragment {
    private Shop shop;
    private ImageView imgShop;
    private TextView tvShopName;
    private TextView tvShopAddress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onCreateContentView(View rootView, Bundle savedInstanceState) {
//        shop = (Shop) getArguments().getSerializable("shop");
        findViews(rootView);
//        init();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_info_shop;
    }

    private void findViews(View view) {
        imgShop = (ImageView) view.findViewById(R.id.img_shop);
        tvShopName = (TextView) view.findViewById(R.id.tv_shop_name);
        tvShopAddress = (TextView) view.findViewById(R.id.tv_address);
    }

    private void init() {
        imgShop.setImageResource(shop.getmImageShop());
        tvShopName.setText(shop.getmShopName());
        tvShopAddress.setText(shop.getmAddress());
    }
}
