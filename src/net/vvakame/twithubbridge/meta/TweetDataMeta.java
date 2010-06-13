package net.vvakame.twithubbridge.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2010-06-13 23:23:39")
/** */
public final class TweetDataMeta extends org.slim3.datastore.ModelMeta<net.vvakame.twithubbridge.model.TweetData> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.TweetData> body = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.TweetData>(this, "body", "body");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.TweetData, java.util.Date> date = new org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.TweetData, java.util.Date>(this, "date", "date", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.TweetData, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.TweetData, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.TweetData> project = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.TweetData>(this, "project", "project");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.TweetData> screenName = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.TweetData>(this, "screenName", "screenName");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.TweetData> title = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.TweetData>(this, "title", "title");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.TweetData> tweet = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.TweetData>(this, "tweet", "tweet");

    private static final TweetDataMeta slim3_singleton = new TweetDataMeta();

    /**
     * @return the singleton
     */
    public static TweetDataMeta get() {
       return slim3_singleton;
    }

    /** */
    public TweetDataMeta() {
        super("TweetData", net.vvakame.twithubbridge.model.TweetData.class);
    }

    @Override
    public net.vvakame.twithubbridge.model.TweetData entityToModel(com.google.appengine.api.datastore.Entity entity) {
        net.vvakame.twithubbridge.model.TweetData model = new net.vvakame.twithubbridge.model.TweetData();
        model.setBody((java.lang.String) entity.getProperty("body"));
        model.setDate((java.util.Date) entity.getProperty("date"));
        model.setKey(entity.getKey());
        model.setProject((java.lang.String) entity.getProperty("project"));
        model.setScreenName((java.lang.String) entity.getProperty("screenName"));
        model.setTitle((java.lang.String) entity.getProperty("title"));
        model.setTweet((java.lang.String) entity.getProperty("tweet"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        net.vvakame.twithubbridge.model.TweetData m = (net.vvakame.twithubbridge.model.TweetData) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("body", m.getBody());
        entity.setProperty("date", m.getDate());
        entity.setProperty("project", m.getProject());
        entity.setProperty("screenName", m.getScreenName());
        entity.setProperty("title", m.getTitle());
        entity.setProperty("tweet", m.getTweet());
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        net.vvakame.twithubbridge.model.TweetData m = (net.vvakame.twithubbridge.model.TweetData) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        net.vvakame.twithubbridge.model.TweetData m = (net.vvakame.twithubbridge.model.TweetData) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        throw new IllegalStateException("The version property of the model(net.vvakame.twithubbridge.model.TweetData) is not defined.");
    }

    @Override
    protected void incrementVersion(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

}