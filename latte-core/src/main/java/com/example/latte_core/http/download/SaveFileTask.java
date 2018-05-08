package com.example.latte_core.http.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.latte_core.app.Latte;
import com.example.latte_core.http.callback.IRequest;
import com.example.latte_core.http.callback.ISuccess;
import com.example.latte_core.utils.FileUtil;
import com.example.latte_core.utils.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import okhttp3.ResponseBody;

/**
 * Created by huangjie on 2018/4/30.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected File doInBackground(Object... params) {

        String downloadDir = (String) params[0]; //下载存储路径
        String extension = (String) params[1]; //文件后缀名
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3]; //文件名
        final InputStream inputStream = body.byteStream();
        if (StringUtils.isEmpty(downloadDir)) {
            downloadDir = "down_load";
        }
        if (StringUtils.isEmpty(extension)) {
            extension = "";
        }

        if (StringUtils.isEmpty(name)) {
            return FileUtil.writeToDisk(inputStream, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(inputStream, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getAbsolutePath());
        }
        autoInstallApk(file);
    }


    public void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getAbsolutePath()).equals("APK")) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(intent);
        }
    }
}
