package tag;

public class Tag {
	
	private String tagName; // tên tag
	
	public Tag() {
		this.tagName = "default name";
	}
	
	public Tag(String tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof Tag) {
			return ((Tag) object).getTagName().equalsIgnoreCase(getTagName());
		}
		return false;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public String getTagName() {
		return tagName;
	}
	
}
