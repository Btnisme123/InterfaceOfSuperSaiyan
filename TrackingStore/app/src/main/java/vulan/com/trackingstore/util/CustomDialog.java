package vulan.com.trackingstore.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.Product;

/**
 * Created by Thanh on 2/22/2017.
 */

public class CustomDialog extends Dialog {
    private View itemView;

    private Product product;
    private List<Product> productArrayList;
    private TextView mNameProduct, mPrice, mPromotion;
    private ImageView mImageProduct;

    public CustomDialog(Context context, Product product, List<Product> products) {
        super(context);
        this.productArrayList = products;
        this.product = product;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_product);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        getWindow().setLayout((8 * width)/9, WindowManager.LayoutParams.WRAP_CONTENT);
        findViews();
        init();
    }

    private void findViews() {
        mNameProduct = (TextView) findViewById(R.id.tv_name_dialog);
        mPrice = (TextView) findViewById(R.id.tv_price_dialog);
        mPromotion = (TextView) findViewById(R.id.tv_promotion_dialog);
        mImageProduct = (ImageView) findViewById(R.id.img_product_dialog);
    }

    private void init() {
        mNameProduct.setText(product.getmName());
//        mImageProduct.setImageResource(product.getmImageProduct());
        mPrice.setText(product.getmPrice() + " VND");
        if (Integer.parseInt(product.getmPromotion()) != 0) {
            mPrice.setPaintFlags(mPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            int newPrice = Integer.parseInt(product.getmPrice()) * (100 - Integer.parseInt(product.getmPromotion())) / 100;
            mPromotion.setText(newPrice + " VND");
        }else {
            mPromotion.setVisibility(View.GONE);
        }

    }
}
