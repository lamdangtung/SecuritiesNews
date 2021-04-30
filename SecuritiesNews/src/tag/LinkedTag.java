package tag;

public class LinkedTag extends Tag { 

	public LinkedTag(String tagName) {
		super(tagName);
	}
	
	//kiểm tra tính gần gũi với tag
	public boolean isLinkedWith(LinkedTag tag) {
		String spliter1[] = this.getTagName().split(" ");
		String spliter2[] = tag.getTagName().split(" ");
		
		// kiểm tra xem 2 tags có chung keyword đơn không
		for (String word1: spliter1) {
			for (String word2: spliter2) {
				if (word1.equalsIgnoreCase(word2)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
}
