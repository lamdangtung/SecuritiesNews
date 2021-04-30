package sentenceformat;

import java.util.ArrayList;
import java.util.List;

import securitiesnews.DailySecuritiesNews;
import tag.Tag;
import tag.LinkedTag;

public abstract class SecuritiesSentenceFormat implements SentenceFormat { // mẫu câu chứng khoán
	
	private String topicName; // tên chủ đề ứng với mẫu câu
	
	protected List <Tag> tags; // danh sách các tags ứng với mẫu câu
	protected List <DailySecuritiesNews> newsList; // danh sách thông tin chứng khoán ngày
	
	public SecuritiesSentenceFormat() {
		topicName = "default name";
		tags = new ArrayList <Tag> ();
		newsList = new ArrayList <DailySecuritiesNews> ();
	}
	
	public SecuritiesSentenceFormat(String topicName) {
		this.topicName = topicName;
		tags = new ArrayList <Tag> ();
		newsList = new ArrayList <DailySecuritiesNews> ();
	}
	
	// số lượng cặp tags gần gũi với format
	public int pairsOfLinkedTagsWith(SecuritiesSentenceFormat format) {
		int count = 0;
		for (Tag currentTag1: tags) {
			for (Tag currentTag2: format.tags) {
				if (((LinkedTag)currentTag1).isLinkedWith((LinkedTag)currentTag2)) {
					count++;
				}
			}
		}
		return count;
	}
	
	public void setNewsList(List <DailySecuritiesNews> newsList) {
		this.newsList = newsList;
	}
	
	public String getTopicName() {
		return topicName;
	}
	
	public List <Tag> getTags() {
		return new ArrayList <Tag> (tags);
	}
		
}
