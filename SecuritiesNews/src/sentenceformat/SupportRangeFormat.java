package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class SupportRangeFormat extends SecuritiesSentenceFormat { // mẫu câu về vùng hỗ trợ của cổ phiếu
	
	private static final String TOPIC_NAME = "biến động điểm";
	private static final String[] TAG_NAMES = {
		"vùng hỗ trợ",
	};
	
	public SupportRangeFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public SupportRangeFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		super.setNewsList(newsList);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	private String formatStyle1(String stockName, String lowestPrice, String closingPrice) {
		return "Nhà đầu tư nên tăng tỷ lệ nắm giữ khi Vn-Index tiến đến vùng hỗ trợ " + lowestPrice + " - " + closingPrice + " điểm. ";
	}
	
	private String formatStyle2(String stockName, String lowestPrice, String closingPrice) {
		return "Vùng hỗ trợ ngắn hạn đối với " + stockName + " ở mức " + lowestPrice + " - " + closingPrice + " điểm. ";
	}
	
	private String formatStyle3(String stockName, String lowestPrice, String closingPrice) {
		return "Vùng hỗ trợ " + stockName + " tại " + lowestPrice + " - " + closingPrice + " điểm vẫn là vùng hỗ trợ mạnh. ";
	}
	
	private String formatStyle4(String stockName, String lowestPrice, String closingPrice) {
		return stockName + " đang trong vùng hỗ trợ mạnh " + lowestPrice + " - " + closingPrice + 
				" có khả năng sẽ không giảm mạnh và hiện tượng tăng cục bộ số ít cổ phiếu vốn hóa lớn sẽ tiếp diễn. ";
	}
	
	@Override
	public String getSentence() {
		// lấy thông tin chứng khoán của 1 ngày ngẫu nhiên
		Random random = new Random();
		DailySecuritiesNews dailyNews = newsList.get(random.nextInt(newsList.size()));
		
		String stockName = dailyNews.getName();
		String lowestPrice = String.valueOf(dailyNews.getLowestPrice());
		String closingPrice = String.valueOf(dailyNews.getClosingPrice());
		
		int style = random.nextInt(4) + 1;
		
		switch (style) {
			case 1: return formatStyle1(stockName, lowestPrice, closingPrice);
			
			case 2: return formatStyle2(stockName, lowestPrice, closingPrice);
			
			case 3: return formatStyle3(stockName, lowestPrice, closingPrice);
			
			default: return formatStyle4(stockName, lowestPrice, closingPrice); 
		}
	}

}
