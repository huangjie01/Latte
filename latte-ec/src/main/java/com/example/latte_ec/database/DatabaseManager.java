package com.example.latte_ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

/**
 * Created by huangjie on 2018/5/12.
 */

public class DatabaseManager {
    private DaoSession mDaoSession = null;

    private UserProfileDao mUserDao = null;
    public static final String DB_NAME = "latte_ec.db";


    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();

    }

    public DatabaseManager init(Context context) {
        initDao(context);
        return this;
    }


    public static DatabaseManager getInstance() {

        return Holder.INSTANCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, DB_NAME);
        Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mUserDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getUserDao() {
        return mUserDao;
    }


}
