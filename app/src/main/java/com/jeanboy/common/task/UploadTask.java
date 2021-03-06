package com.jeanboy.common.task;

import com.jeanboy.app.api.ApiManager;
import com.jeanboy.app.model.bean.FileBean;
import com.jeanboy.manager.net.RequestCallback;

import java.io.File;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Next on 2016/8/16.
 */
public class UploadTask extends Task {

    private FileBean fileBean;

    private Call<FileBean> call;



    public UploadTask(FileBean fileBean) {
        super();
        this.fileBean = fileBean;
    }

    @Override
    public void onAdded() {
        // TODO: 初始化操作，保存到数据库，或者恢复状态

    }

    @Override
    public void onRun() {

        // TODO: 具体操作逻辑
        call = ApiManager.getInstance().fileApi.upload(new File(fileBean.getPath()));
        ApiManager.getInstance().doSyncBack(call, new RequestCallback<FileBean>() {
            @Override
            public void success(Response<FileBean> response) {

            }

            @Override
            public void error(String msg) {

            }
        });
    }

    @Override
    public void onComplete() {
        // TODO: 执行完成更新数据库

    }

    @Override
    public void onCancel() {
        if (call != null) {//正在执行时取消
            call.cancel();
        }
    }
}
