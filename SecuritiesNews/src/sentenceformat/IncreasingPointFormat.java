package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class IncreasingPointFormat extends SecuritiesSentenceFormat { // mẫu câu về tăng điểm chứng khoán 
	
	private static final String TOPIC_NAME = "biến động điểm";
	private static final String[] TAG_NAMES = {
		"tăng", "tăng điểm",
	};
	
	public IncreasingPointFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public IncreasingPointFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		super.setNewsList(newsList);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	private String formatStyle1(String date, String stockName, String changePrice, String closingPrice, String finalTradingPrice, String finalTradingStock) {
		return "Cuối ngày " + date + ", " + stockName + " tăng " + changePrice + " điểm, chốt ở " + closingPrice +
	            " điểm, chuyển nhượng " + finalTradingPrice + " đồng ứng với " + finalTradingStock + " cổ phiếu. ";
	}
	
	
	private String formatStyle2(String date, String stockName, String changePrice, String closingPrice, String finalTradingStock, String finalTradingPrice) {
		return "Chốt phiên giao dịch cuối ngày " + date + ", " + stockName + " tăng " + changePrice + " điểm, lên thành " + 
				closingPrice + " điểm, mua bán " + finalTradingStock + " chứng khoán, ứng với " + finalTradingPrice + " đồng. ";

	}
	
	private String formatStyle3(String date, String stockName, String closingPrice, String changePrice) {
		return "Chốt phiên ngày " + date + ", " + stockName + " đóng cửa với " + closingPrice + " điểm, tăng " + changePrice + " điểm so với hôm qua. ";

	}
	
	@Override
	public String getSentence() {
		// lấy thông tin chứng khoán của 1 ngày ngẫu nhiên
		Random random = new Random();
		DailySecuritiesNews dailyNews = newsList.get(random.nextInt(newsList.size()));
		
		if (dailyNews.getChangePrice() < 0) return "";
		
		String stockName = dailyNews.getName();
		String date = dailyNews.getDate();
		String closingPrice = String.valueOf(dailyNews.getClosingPrice());
		String changePrice = String.valueOf(Math.abs(dailyNews.getChangePrice()));
		String finalTradingStock = String.valueOf(dailyNews.getFinalTradingStock());
		String finalTradingPrice = String.valueOf(dailyNews.getFinalTradingPrice());
		
		int style = random.nextInt(3) + 1;
		
		switch (style) {
			case 1: return formatStyle1(date, stockName, changePrice, closingPrice, finalTradingPrice, finalTradingStock);
			
			case 2: return formatStyle2(date, stockName, changePrice, closingPrice, finalTradingStock, finalTradingPrice);
			
			default: return formatStyle3(date, stockName, closingPrice, changePrice);
		}
	}

}
