package net.vvakame.twithubbridge.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2010-06-14 22:41:39")
/** */
public final class BadDataMeta extends org.slim3.datastore.ModelMeta<net.vvakame.twithubbridge.model.BadData> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.BadData> apiKeyEncrypted = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.BadData>(this, "apiKeyEncrypted", "apiKeyEncrypted");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.BadData> error = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.BadData>(this, "error", "error");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.BadData> github = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.BadData>(this, "github", "github");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.BadData, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.BadData, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.BadData, java.lang.Long> statusId = new org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.BadData, java.lang.Long>(this, "statusId", "statusId", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.BadData> twitter = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.BadData>(this, "twitter", "twitter");

    private static final BadDataMeta slim3_singleton = new BadDataMeta();

    /**
     * @return the singleton
     */
    public static BadDataMeta get() {
       return slim3_singleton;
    }

    /** */
    public BadDataMeta() {
        super("BadData", net.vvakame.twithubbridge.model.BadData.class);
    }

    @Override
    public net.vvakame.twithubbridge.model.BadData entityToModel(com.google.appengine.api.datastore.Entity entity) {
        net.vvakame.twithubbridge.model.BadData model = new net.vvakame.twithubbridge.model.BadData();
        model.setApiKeyEncrypted((java.lang.String) entity.getProperty("apiKeyEncrypted"));
        model.setError((java.lang.String) entity.getProperty("error"));
        model.setGithub((java.lang.String) entity.getProperty("github"));
        model.setKey(entity.getKey());
        model.setStatusId((java.lang.Long) entity.getProperty("statusId"));
        model.setTwitter((java.lang.String) entity.getProperty("twitter"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        net.vvakame.twithubbridge.model.BadData m = (net.vvakame.twithubbridge.model.BadData) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("apiKeyEncrypted", m.getApiKeyEncrypted());
        entity.setProperty("error", m.getError());
        entity.setProperty("github", m.getGithub());
        entity.setProperty("statusId", m.getStatusId());
        entity.setProperty("twitter", m.getTwitter());
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        net.vvakame.twithubbridge.model.BadData m = (net.vvakame.twithubbridge.model.BadData) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        net.vvakame.twithubbridge.model.BadData m = (net.vvakame.twithubbridge.model.BadData) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        throw new IllegalStateException("The version property of the model(net.vvakame.twithubbridge.model.BadData) is not defined.");
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