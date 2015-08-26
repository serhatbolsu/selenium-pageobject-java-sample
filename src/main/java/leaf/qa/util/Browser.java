package leaf.qa.util;

/*
 * Bean representing a browser. It contains name, version and platform fields.
 */
public class Browser {

	private String name;
	private String version;
	private String platform;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}