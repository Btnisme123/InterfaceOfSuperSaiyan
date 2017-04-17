package vulan.com.trackingstore.data.network;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vulan.com.trackingstore.data.model.Product;
import vulan.com.trackingstore.data.model.ProductCategory;
import vulan.com.trackingstore.data.model.Shop;

/**
 * Created by Ominext on 4/17/2017.
 */

public class ApiRequest {
    private static final String BASE_URL = "http://doanbeacon.com/";
    private static ApiRequest sInstance;
    private Retrofit mRetrofit;
    private ApiPoint mApi;
    private static okhttp3.OkHttpClient mClient;


    public static ApiRequest getInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient = new okhttp3.OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();
        if (sInstance == null) {
            synchronized (ApiRequest.class) {
                if (sInstance == null) {
                    sInstance = new ApiRequest();
                }
            }
        }
        return sInstance;
    }

    public void init() {
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(mClient).build();
        mApi = mRetrofit.create(ApiPoint.class);
    }

    public void initAuthToken(final String auth) {
        final okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(mClient).build();
        if (auth == null) {
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("Authorization", auth)
                            .method(original.method(), original.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

    }

    public void getAllShop(Callback<List<Shop>> callback) {
        mApi.getAllShop().enqueue(callback);
    }

    public void getCategoryByShopId(int shopId, Callback<List<ProductCategory>> callback) {
        mApi.getListCategory(shopId).enqueue(callback);
    }

    public void getListProduct(int categoryId, Callback<List<Product>> callback) {
        mApi.getListProduct(categoryId).enqueue(callback);
    }
}
