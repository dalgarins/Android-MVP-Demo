package com.example.next.demo;

import com.alibaba.fastjson.JSON;
import com.jeanboy.app.api.ApiManager;
import com.jeanboy.app.config.AppConfig;
import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.manager.net.NetManager;

import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void dbFlow() {

        UserBean userBean = new UserBean();
        userBean.setUsername("testuser");
        userBean.setPassword("testpass");
        userBean.setNickname("testnick");

        userBean.save();
    }

    @Test
    public void testGetInfo(){
        NetManager.getInstance().init(AppConfig.SERVER_HOST);
        Call<UserBean> call= ApiManager.getInstance().userApi.getInfo("33");
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                System.out.println("==============="+ JSON.toJSONString(response));
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                System.out.println("----------------"+t.getMessage());
            }
        });
    }
}