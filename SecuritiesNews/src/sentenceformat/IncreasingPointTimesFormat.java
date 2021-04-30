package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class IncreasingPointTimesFormat extends SecuritiesSentenceFormat { // mẫu câu về số lần tăng điểm chứng khoán
	
	private static final String TOPIC_NAME = "biến động điểm";
	private static final String[] TAG_NAMES = {
		"tăng", "số lần tăng",
	};
	
	public IncreasingPointTimesFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public IncreasingPointTimesFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		super.setNewsList(newsList);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	private String formatStyle1(String dateStart, String dateEnd, String name, String times) {
		return "Từ ngày " + dateStart + " đến ngày " + dateEnd + ", chỉ số " + name + " tăng tổng cộng " + times + " lần. ";
	}
	
	private String formatStyle2(String dateStart, String dateEnd, String name, String times) {
		return "Trong những ngày " + dateStart + " - " + dateEnd + ", " + name + " tăng điểm khá rõ rệt với " + times + " lần tăng. ";
	}
	
	@Override
	public String getSentence() {
		String dateStart = newsList.get(newsList.size() - 1).getDate();
		String dateEnd = newsList.get(0).getDate();
		String name = newsList.get(0).getName();
		
		// đếm số lần tăng điểm chứng khoán
		int times = 0;
		
		for (DailySecuritiesNews dailyNews: newsList) {
			double changePrice = dailyNews.getChangePrice();
			if (changePrice > 0) times++;
		}	
		
		Random random = new Random();
		int style = random.nextInt(2) + 1;
		
		switch (style) {
			case 1: return formatStyle1(dateStart, dateEnd, name, String.valueOf(times));
			
			default: return formatStyle2(dateStart, dateEnd, name, String.valueOf(times));
		}
	}
}
