package test.yjj.com.jsontest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 展示json串内容
     */
    private TextView mTvContent;

    String jsonContetn = "{code:" + "0" + ",msg: " + "登录成功！" + "}";
    private DBController dbController;
    /**
     * 申请 单独权限
     */
    private Button mBtnOne;
    /**
     * 申请 组权限
     */
    private Button mBtnGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        initData();

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
            userBean.setMsg("我是db的");
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
        ArrayList<UserBean> userBeans = new ArrayList<>();
        UserBean userBean = new UserBean();
        userBean.setCode("1");
        userBean.setMsg("22");
        UserBean userBean2 = new UserBean();
        userBean2.setCode("1222");
        userBean2.setMsg("23333332");

        userBeans.add(userBean);
        userBeans.add(userBean2);

//        String s = JSON.toJSONString(userBeans);
        Gson gson = new Gson();
        String s = JSON.toJSONString(userBeans);
        Log.e("initView: ", s);


        List<UserBean> userBeans1 = JSON.parseArray(s, UserBean.class);
        for (int i = 0; i < userBeans1.size(); i++) {
            Log.e("initView: ", userBeans1.get(i).getMsg());
        }

        mBtnOne = (Button) findViewById(R.id.btn_one);
        mBtnOne.setOnClickListener(this);
        mBtnGroup = (Button) findViewById(R.id.btn_group);
        mBtnGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_one:
                PermissionUtils.getPremission(this, Permission.WRITE_EXTERNAL_STORAGE, new PermissionUtils.InterfacePermission() {
                    @Override
                    public void permissionCallBack() {
                        //成功回调  todo
                        Toast.makeText(MainActivity.this, "申请成功了", Toast.LENGTH_SHORT).show();


                    }
                });

                break;
            case R.id.btn_group:
                PermissionUtils.getPremissions(this, Permission.Group.CAMERA, new PermissionUtils.InterfacePermission() {
                    @Override
                    public void permissionCallBack() {
                        //成功回调  todo
                        Toast.makeText(MainActivity.this, "申请成功了", Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            default:
        }
    }
}
