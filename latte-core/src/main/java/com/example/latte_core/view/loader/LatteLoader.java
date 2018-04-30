package com.example.latte_core.view.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.latte_core.R;
import com.example.latte_core.utils.DimenUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by huangjie on 2018/4/22.
 */

public class LatteLoader {

    private static final int LOAD_SIZE_SCALE = 8;
    private static final ArrayList<AppCompatDialog> LOADS = new ArrayList<>();
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public static void showLoader(Enum<?> type, Context context) {
        showLoader(type.name(), context);
    }

    public static void showLoader(String type, Context context) {
        AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView loadingIndicatorView = Loadercreator.create(type, context);
        dialog.setContentView(loadingIndicatorView);

        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        if (attributes != null) {
            int windowWidth = DimenUtils.getSrrenWidth();
            int windowHeight = DimenUtils.getSrrenHeight();
            attributes.width = windowWidth / LOAD_SIZE_SCALE;
            attributes.height = windowHeight / LOAD_SIZE_SCALE;
            attributes.gravity = Gravity.CENTER;
        }
        LOADS.add(dialog);
        dialog.show();
    }

    public static void showLoader(Context context) {
        showLoader(DEFAULT_LOADER, context);
    }

    public static void stopLoader() {
        for (AppCompatDialog dialog : LOADS) {
            if (dialog != null && dialog.isShowing()) {
                dialog.cancel();
                dialog = null;
            }
        }
    }
}
