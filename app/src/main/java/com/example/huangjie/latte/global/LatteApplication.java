package com.example.huangjie.latte.global;

import android.app.Application;

import com.example.huangjie.latte.R;
import com.example.latte_core.app.Latte;
import com.example.latte_core.http.interceptor.DebugInterceptor;
import com.example.latte_ec.database.DatabaseManager;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by huangjie on 2018/4/11.
 */

public class LatteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1")
                .withContext(getApplicationContext())
                .withIcon(new FontAwesomeModule())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
        DatabaseManager.getInstance().init(this);
        initStetho();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
