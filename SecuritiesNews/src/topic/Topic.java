package topic;

import java.util.ArrayList;
import java.util.List;

public class Topic { // chủ đề
	
	private String topicName; // tên chủ đề
	protected List <String> contents; // danh sách nội dung của chủ đề
	
	public Topic() {
		topicName = "default name";
		contents = new ArrayList <String> ();
	}
	
	public Topic(String topicName) {
		this.topicName = topicName;
		contents = new ArrayList <String> ();
	}
	
	// lấy tổng hợp nội dung của chủ đề
	public String getAllContent() {
		String allContent = "";
		for (String content: contents) {
			allContent += content;
		}
		return allContent;
	}
	
	public void setName(String topicName) {
		this.topicName = topicName;
	}
	
	public String getName() {
		return topicName;
	}
	
}
