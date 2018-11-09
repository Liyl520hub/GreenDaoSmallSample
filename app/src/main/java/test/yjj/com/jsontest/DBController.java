package test.yjj.com.jsontest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * @author joy
 * <p>
 * created 2018/11/9
 * <p>
 * class use: 数据操作工具类
 */
public class DBController {
    /**
     * Helper 获取Helper对象
     */
    private DaoMaster.DevOpenHelper mHelper;
    /**
     * 数据库
     */
    private SQLiteDatabase db;
    /**
     * 数据库名
     */
    private String dbName = "dataCache.db";
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * 上下文
     */
    private Context context;
    /**
     * dao
     */
    private final DataCacheDao dataCacheDao;

    private static DBController mDbController;

    /**
     * 获取单例
     */
    public static DBController getInstance(Context context) {
        if (mDbController == null) {
            synchronized (DBController.class) {
                if (mDbController == null) {
                    mDbController = new DBController(context);
                }
            }
        }
        return mDbController;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public DBController(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        dataCacheDao = mDaoSession.getDataCacheDao();
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }

    /**
     * 会自动判定是插入还是替换
     *
     *
     */
    public void insertOrReplace(String urlName,String jsonData) {
        DataCache dataCache = new DataCache();
        dataCache.setCacheUrl(urlName);
        dataCache.setCacheConent(jsonData);
        long time = System.currentTimeMillis();
        dataCache.setCacheTime(time);
        dataCacheDao.insertOrReplace(dataCache);
    }

    /**
     * 插入一条记录，表里面要没有与之相同的记录
     *
     * @param dataCache
     */
    public long insert(DataCache dataCache) {
        return dataCacheDao.insert(dataCache);
    }

    /**
     * 更新数据
     *
     * @param urlName  要更新的url地址
     * @param jsonData 更新的内容
     */
    public void update(String urlName, String jsonData) {
        //拿到之前的记录
        DataCache mOldPersonInfor = dataCacheDao.queryBuilder().where(DataCacheDao.Properties.CacheUrl.eq(urlName)).build().unique();
        if (mOldPersonInfor != null) {
            mOldPersonInfor.setCacheConent(jsonData);
            dataCacheDao.update(mOldPersonInfor);
        }
    }

    /**
     * 按条件查询数据
     */
    public DataCache searchByWhere(String urlName) {
        List<DataCache> dataCaches = (List<DataCache>) dataCacheDao.queryBuilder().where(DataCacheDao.Properties.CacheUrl.eq(urlName)).build().unique();
        return dataCaches.get(0);
    }

    /**
     * 查询所有数据
     */
    public List<DataCache> searchAll() {
        List<DataCache> dataCaches = dataCacheDao.queryBuilder().list();
        return dataCaches;
    }

    /**
     * 删除数据
     *
     * @param urlName 要删除的数据的url地址
     */
    public void delete(String urlName) {
        dataCacheDao.queryBuilder().where(DataCacheDao.Properties.CacheUrl.eq(urlName)).buildDelete().executeDeleteWithoutDetachingEntities();
    }
}


