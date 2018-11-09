package test.yjj.com.jsontest;

import android.app.Application;
import android.content.Context;

/**
 * @author lyl
 * <p>
 * created 2018/11/9
 * <p>
 * class use:
 */
public class MyApplication extends Application {
    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
    }
}
