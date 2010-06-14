package net.vvakame.twithubbridge.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2010-06-14 22:41:39")
/** */
public final class AccountMapperDataMeta extends org.slim3.datastore.ModelMeta<net.vvakame.twithubbridge.model.AccountMapperData> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.AccountMapperData> apiKeyEncrypted = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.AccountMapperData>(this, "apiKeyEncrypted", "apiKeyEncrypted");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.AccountMapperData> github = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.AccountMapperData>(this, "github", "github");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.AccountMapperData, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<net.vvakame.twithubbridge.model.AccountMapperData, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.AccountMapperData> twitter = new org.slim3.datastore.StringAttributeMeta<net.vvakame.twithubbridge.model.AccountMapperData>(this, "twitter", "twitter");

    private static final AccountMapperDataMeta slim3_singleton = new AccountMapperDataMeta();

    /**
     * @return the singleton
     */
    public static AccountMapperDataMeta get() {
       return slim3_singleton;
    }

    /** */
    public AccountMapperDataMeta() {
        super("AccountMapperData", net.vvakame.twithubbridge.model.AccountMapperData.class);
    }

    @Override
    public net.vvakame.twithubbridge.model.AccountMapperData entityToModel(com.google.appengine.api.datastore.Entity entity) {
        net.vvakame.twithubbridge.model.AccountMapperData model = new net.vvakame.twithubbridge.model.AccountMapperData();
        model.setApiKeyEncrypted((java.lang.String) entity.getProperty("apiKeyEncrypted"));
        model.setGithub((java.lang.String) entity.getProperty("github"));
        model.setKey(entity.getKey());
        model.setTwitter((java.lang.String) entity.getProperty("twitter"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        net.vvakame.twithubbridge.model.AccountMapperData m = (net.vvakame.twithubbridge.model.AccountMapperData) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("apiKeyEncrypted", m.getApiKeyEncrypted());
        entity.setProperty("github", m.getGithub());
        entity.setProperty("twitter", m.getTwitter());
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        net.vvakame.twithubbridge.model.AccountMapperData m = (net.vvakame.twithubbridge.model.AccountMapperData) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        net.vvakame.twithubbridge.model.AccountMapperData m = (net.vvakame.twithubbridge.model.AccountMapperData) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        throw new IllegalStateException("The version property of the model(net.vvakame.twithubbridge.model.AccountMapperData) is not defined.");
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