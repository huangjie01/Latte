package com.example.huangjie.latte;

import com.example.latte_core.activities.ProxyActivity;
import com.example.latte_core.delegates.LatteDelegate;

/**
 * Created by huangjie on 2018/4/16.
 */

public class MainActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new MainDelegate();
    }
}
