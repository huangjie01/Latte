package com.example.latte_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.utils.storage.LattePreference;
import com.example.latte_core.view.launcher.LauncherHolderCreator;
import com.example.latte_core.view.launcher.ScrollLauncherTag;
import com.example.latte_ec.R;
import com.example.latte_ec.sign.SignUpDelegate;

import java.util.ArrayList;

/**
 * Created by huangjie on 2018/5/1.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mBanner;
    private static final ArrayList<Integer> BANNERLIST = new ArrayList<>();

    @Override
    public Object setLayout() {
        mBanner = new ConvenientBanner<Integer>(getContext());
        return mBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    public void initBanner() {
        BANNERLIST.add(R.mipmap.launcher_01);
        BANNERLIST.add(R.mipmap.launcher_02);
        BANNERLIST.add(R.mipmap.launcher_03);
        BANNERLIST.add(R.mipmap.launcher_04);
        BANNERLIST.add(R.mipmap.launcher_05);
        mBanner.setPages(new LauncherHolderCreator(), BANNERLIST)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(true);
    }

    @Override
    public void onItemClick(int position) {

        if (position == BANNERLIST.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //TODO 检查用户是否已经登录
            start(new SignUpDelegate());
        }
    }
}
