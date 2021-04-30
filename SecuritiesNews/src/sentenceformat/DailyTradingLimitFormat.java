package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class DailyTradingLimitFormat extends SecuritiesSentenceFormat { // mẫu câu về biên độ dao động giá của cổ phiếu
	
	private static final String TOPIC_NAME = "biến động điểm";
	private static final String[] TAG_NAMES = {
		"dải biến động", "biên độ",
	};
	
	public DailyTradingLimitFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public DailyTradingLimitFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		super.setNewsList(newsList);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	private String formatStyle1(String stockName, String lowestPrice, String highestPrice, String priceLimit) {
		return  stockName + " biến động trong dải từ " + lowestPrice + " điểm tới " + highestPrice + 
				" điểm, tương đương biên độ xấp xỉ " + priceLimit + " điểm. ";
	}
	
	private String formatStyle2(String date, String stockName, String lowestPrice, String highestPrice) {
		return "Trong bản tin phiên giao dịch ngày " + date + ", nhiều công ty chứng khoán đều có chung nhận định rằng " + 
				stockName + " có thể sẽ đi ngang và giằng co trong dải từ " + lowestPrice + " - " + highestPrice + 
				" cho đến khi đạt đủ sự đồng thuận để bứt phá khỏi vùng này. ";
	}
	
	@Override
	public String getSentence() {
		// lấy thông tin chứng khoán của 1 ngày ngẫu nhiên
		Random random = new Random();
		DailySecuritiesNews dailyNews = newsList.get(random.nextInt(newsList.size()));
		
		String date = dailyNews.getDate();
		String stockName = dailyNews.getName();
		
		String lowestPrice = String.valueOf(dailyNews.getLowestPrice());
		String highestPrice = String.valueOf(dailyNews.getHighestPrice());
		String priceLimit = String.valueOf((double)Math.round(dailyNews.getHighestPrice() - dailyNews.getLowestPrice()));
		
		int style = random.nextInt(2) + 1;
		
		switch (style) {
			case 1: return formatStyle1(stockName, lowestPrice, highestPrice, priceLimit);
			
			default: return formatStyle2(date, stockName, lowestPrice, highestPrice); 
		}
	}

}
