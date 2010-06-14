package net.vvakame.twithubbridge.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2010-06-14 12:29:23")
/** */
public final class ReadStatusDataMeta extends org.slim3.datastore.ModelMeta<net.vvakame.twithubbridge.model.ReadStatusData> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.ReadStatusData, java.util.Date> createAt = new org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.ReadStatusData, java.util.Date>(this, "createAt", "createAt", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.ReadStatusData, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.ReadStatusData, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.ReadStatusData> screenName = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.ReadStatusData>(this, "screenName", "screenName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.ReadStatusData, java.lang.Long> statusId = new org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.ReadStatusData, java.lang.Long>(this, "statusId", "statusId", java.lang.Long.class);

    private static final ReadStatusDataMeta slim3_singleton = new ReadStatusDataMeta();

    /**
     * @return the singleton
     */
    public static ReadStatusDataMeta get() {
       return slim3_singleton;
    }

    /** */
    public ReadStatusDataMeta() {
        super("ReadStatusData", net.vvakame.twithubbridge.model.ReadStatusData.class);
    }

    @Override
    public net.vvakame.twithubbridge.model.ReadStatusData entityToModel(com.google.appengine.api.datastore.Entity entity) {
        net.vvakame.twithubbridge.model.ReadStatusData model = new net.vvakame.twithubbridge.model.ReadStatusData();
        model.setCreateAt((java.util.Date) entity.getProperty("createAt"));
        model.setKey(entity.getKey());
        model.setScreenName((java.lang.String) entity.getProperty("screenName"));
        model.setStatusId((java.lang.Long) entity.getProperty("statusId"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        net.vvakame.twithubbridge.model.ReadStatusData m = (net.vvakame.twithubbridge.model.ReadStatusData) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("createAt", m.getCreateAt());
        entity.setProperty("screenName", m.getScreenName());
        entity.setProperty("statusId", m.getStatusId());
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        net.vvakame.twithubbridge.model.ReadStatusData m = (net.vvakame.twithubbridge.model.ReadStatusData) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        net.vvakame.twithubbridge.model.ReadStatusData m = (net.vvakame.twithubbridge.model.ReadStatusData) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        throw new IllegalStateException("The version property of the model(net.vvakame.twithubbridge.model.ReadStatusData) is not defined.");
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