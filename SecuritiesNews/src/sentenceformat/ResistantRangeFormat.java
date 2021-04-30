package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class ResistantRangeFormat extends SecuritiesSentenceFormat { // mẫu câu về vùng kháng cự của cổ phiếu
	
	private static final String TOPIC_NAME = "biến động điểm";
	private static final String[] TAG_NAMES = {
		"vùng kháng cự",
	};
	
	public ResistantRangeFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public ResistantRangeFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		super.setNewsList(newsList);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	private String formatStyle1(String stockName, String closingPrice, String highestPrice) {
		return "Ngưỡng kháng cự " + closingPrice + " - " + highestPrice + " điểm là thách thức cho " + stockName + " trong khoảng thời gian này. ";
	}
	
	private String formatStyle2(String stockName, String closingPrice, String highestPrice) {
		return "Nhà đầu tư nên giảm dần tỷ lệ nắm giữ khi " + stockName + " tiến đến vùng kháng cự " + closingPrice + " - " + highestPrice + " điểm. ";

	}
	
	private String formatStyle3(String stockName, String closingPrice, String highestPrice) {
		return "Trên phương diện kỹ thuật, chỉ số " + stockName + " đang tiếp cận các vùng kháng cự ngắn hạn " + closingPrice + " - " + highestPrice + 
				", do đó thị trường có khả năng sẽ sớm xuất hiện các phiên điều chỉnh tại đây. ";
	}
	
	private String formatStyle4(String stockName, String closingPrice, String highestPrice) {
		return "Tốc độ tăng giá của thị trường ở thời điểm hiện tại vẫn tương đối chậm do " + stockName + 
				" đang ở trạng thái tích lũy phía dưới vùng kháng cự dài hạn " + closingPrice + " - " + highestPrice + " điểm. ";

	}
	
	@Override
	public String getSentence() {
		// lấy thông tin chứng khoán của 1 ngày ngẫu nhiên
		Random random = new Random();
		DailySecuritiesNews dailyNews = newsList.get(random.nextInt(newsList.size()));
		
		String stockName = dailyNews.getName();
		String highestPrice = String.valueOf(dailyNews.getHighestPrice());
		String closingPrice = String.valueOf(dailyNews.getClosingPrice());
		
		int style = random.nextInt(4) + 1;
		
		switch (style) {
			case 1: return formatStyle1(stockName, closingPrice, highestPrice);
			
			case 2: return formatStyle2(stockName, closingPrice, highestPrice);
			
			case 3: return formatStyle3(stockName, closingPrice, highestPrice);
			
			default: return formatStyle4(stockName, closingPrice, highestPrice); 
		}
	}

}
