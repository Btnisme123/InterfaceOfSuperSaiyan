package vulan.com.trackingstore.util.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import vulan.com.trackingstore.R;
import vulan.com.trackingstore.data.model.DrawerRightItem;
import vulan.com.trackingstore.data.model.Food;

/**
 * Created by vulan on 03/11/2016.
 */

public class DetailDialog extends Dialog {
    private Context mContext;
    private View mView;
    private TextView mTextTitle, mTextName, mTextMeter, mTextContent;
    private ImageView mImage;
    private CardView mCardview;

    public DetailDialog(Context context, Food food, DrawerRightItem drawerRightItem) {
        super(context);
        mContext = context;
        mView = View.inflate(mContext, R.layout.dialog_slide_up, null);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        findView(mView);
        init(food, drawerRightItem);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setCanceledOnTouchOutside(true);

        setContentView(mView);
        //set background color
        Drawable d = new ColorDrawable(Color.WHITE);
        d.setAlpha(200);
        this.getWindow().setBackgroundDrawable(d);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //getWindow().setWindowAnimations(R.style.DialogAnimation);
        getWindow().setAttributes(layoutParams);
        getWindow().setGravity(Gravity.BOTTOM);
    }

    private void init(Food food, DrawerRightItem item) {
        mTextTitle.setText(item.getTitle());
        mTextMeter.setText(item.getMeter());
        mTextName.setText(food.getName());
        mTextContent.setText(food.getContent());
        mImage.setImageResource(food.getImageFood());
    }

    private void findView(View mView) {
        mTextTitle = (TextView) mView.findViewById(R.id.text_location);
        mTextName = (TextView) mView.findViewById(R.id.text_name);
        mTextMeter = (TextView) mView.findViewById(R.id.text_meter);
        mTextContent = (TextView) mView.findViewById(R.id.text_content);
        mImage = (ImageView) mView.findViewById(R.id.image_food);
        mCardview = (CardView) mView.findViewById(R.id.card_go_to_restaurant);
    }
}
