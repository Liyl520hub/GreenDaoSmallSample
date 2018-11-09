package test.yjj.com.jsontest;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author joy
 * <p>
 * created 2018/11/9
 * <p>
 * class use:
 */
@Entity
public class DataCache {
    /**
     * 设置自增长
     */
    @Id(autoincrement = true)
    private Long id;
    /**
     * //设置唯一性
     */
    @Index(unique = true)
    /**
     * 缓存的url
     */
    private String cacheUrl;
    /**
     * 缓存的内容  json串
     */
    private String cacheConent;
    /**
     * 缓存的时间
     */
    private long cacheTime;

    @Generated(hash = 488346778)
    public DataCache(Long id, String cacheUrl, String cacheConent,
                     long cacheTime) {
        this.id = id;
        this.cacheUrl = cacheUrl;
        this.cacheConent = cacheConent;
        this.cacheTime = cacheTime;
    }

    @Generated(hash = 24611778)
    public DataCache() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCacheUrl() {
        return this.cacheUrl;
    }

    public void setCacheUrl(String cacheUrl) {
        this.cacheUrl = cacheUrl;
    }

    public String getCacheConent() {
        return this.cacheConent;
    }

    public void setCacheConent(String cacheConent) {
        this.cacheConent = cacheConent;
    }

    public long getCacheTime() {
        return this.cacheTime;
    }

    public void setCacheTime(long cacheTime) {
        this.cacheTime = cacheTime;
    }
}
