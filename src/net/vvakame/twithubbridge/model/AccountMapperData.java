package net.vvakame.twithubbridge.model;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

@Model
public class AccountMapperData {
	@Attribute(primaryKey = true)
	private Key key = null;

	private String twitter = null;

	private String github = null;
	private String apiKeyEncrypted = null;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getApiKeyEncrypted() {
		return apiKeyEncrypted;
	}

	public void setApiKeyEncrypted(String apiKeyEncrypted) {
		this.apiKeyEncrypted = apiKeyEncrypted;
	}
}
