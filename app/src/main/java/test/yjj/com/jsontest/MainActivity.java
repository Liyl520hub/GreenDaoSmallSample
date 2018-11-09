package test.yjj.com.jsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    /**
     * 展示json串内容
     */
    private TextView mTvContent;

    String jsonContetn = "{code:" + "0" + ",msg: " + "登录成功！" + "}";
    private DBController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initData();

    }

    private void initData() {
        if (NetWorkUtils.isNetworkConnected(this)) {
            //有网 模拟掉接口 mPersenter.getxxxxxxx
            Gson gson = new Gson();
            UserBean UserBean = gson.fromJson(jsonContetn, UserBean.class);
            onSucess(UserBean);

        } else {
            //无网 获取数据库操作工具类 跟据url进行查询拿到数据  json串
            dbController = DBController.getInstance(this);
            DataCache urlName = dbController.searchByWhere("urlName");
            String cacheConent = urlName.getCacheConent();
            Gson gson = new Gson();
            UserBean userBean = gson.fromJson(cacheConent, UserBean.class);
            setData(userBean);
        }
    }

    private void onSucess(UserBean userBean) {
        //这里是接口回调回来数据的地方 返回的是对象 判空 不为空则将此对象转化为json串存储到数据库 字符串
        Gson gson = new Gson();
        String josnData = gson.toJson(userBean);
        if (dbController == null) {
            dbController = DBController.getInstance(this);
        }
        dbController.insertOrReplace("urlName", josnData);


        setData(userBean);


    }

    /**
     * 设置数据的方法 正常情况下接口回调回来的数据已经是对象了
     *
     * @param userBean
     */
    private void setData(UserBean userBean) {
        String msg = userBean.getMsg();
        mTvContent.setText(msg);
    }

    private void initView() {
        mTvContent = (TextView) findViewById(R.id.tv_content);
    }
}
