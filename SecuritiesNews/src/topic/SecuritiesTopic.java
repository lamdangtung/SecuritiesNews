package topic;

import java.util.ArrayList;
import java.util.List;

import sentenceformat.SecuritiesSentenceFormat;
import tag.Tag;

public class SecuritiesTopic extends Topic { // chủ đề chứng khoán
	
	private List <Tag> tags; // danh sách các tags ứng với chủ đề
	List <SecuritiesSentenceFormat> formats = new ArrayList <SecuritiesSentenceFormat> (); // danh sách những mẫu câu user chọn thuộc chủ đề
	
	public SecuritiesTopic() {
		super();
		tags = new ArrayList <Tag> ();
		formats = new ArrayList <SecuritiesSentenceFormat> ();
	}
	
	public SecuritiesTopic(String topicName) {
		super(topicName);
		tags = new ArrayList <Tag> ();
		formats = new ArrayList <SecuritiesSentenceFormat> ();
	}
	
	// thêm mẫu câu mới cho chủ đề
	public void addFormat(SecuritiesSentenceFormat format) {
		// thêm trực tiếp format vào nếu formats rỗng
		if (formats.isEmpty()) {
			formats.add(format);
			contents.add(format.getSentence());
			return;
		}
		
		// tìm ra mẫu câu gần gũi với format nhất của chủ đề
		// mẫu câu gần gũi nhất với format là mẫu câu có số lượng cặp tags gần gũi nhiều nhất với format
		int maxPairsOfRelatedTags = 0; // số lượng cặp tags gần gũi nhiều nhất
		for (SecuritiesSentenceFormat currentFormat: formats) {
			int pairsOfRelatedTags = currentFormat.pairsOfLinkedTagsWith(format);
			if (maxPairsOfRelatedTags < pairsOfRelatedTags) {
				maxPairsOfRelatedTags = pairsOfRelatedTags;
			}
		}
		
		for (int i = formats.size() - 1; i >= 0; i--) {
			SecuritiesSentenceFormat currentFormat = formats.get(i);
			int pairsOfRelatedTags = currentFormat.pairsOfLinkedTagsWith(format);
			if (pairsOfRelatedTags == maxPairsOfRelatedTags) {
				formats.add(i + 1, format); // thêm format vào ngay sau mẫu câu thỏa mãn tìm được
				contents.add(i + 1, format.getSentence()); // thêm câu tương ứng với format
				return;
			}
		}
	}
	
	public boolean containTag(Tag tag) {
		return tags.contains(tag);
	}
	
	public void addTag(Tag tag) {
		tags.add(tag);
	}
	
	public List <Tag> getTags() {
		return new ArrayList <Tag> (tags);
	}
	
}
