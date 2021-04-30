package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class DecreasingLiquidityFormat extends SecuritiesSentenceFormat { // mẫu câu về thanh khoản của cổ phiếu
	
	private static final String TOPIC_NAME = "thanh khoản";
	private static final String[] TAG_NAMES = {
		"giảm", "sụt giảm", 
	};
	
	public DecreasingLiquidityFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public DecreasingLiquidityFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		super.setNewsList(newsList);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	private String formatStyle1(String stockName, String finalTradingStock, String finalTradingPrice) {
		return "Thanh khoản " + stockName + " sụt giảm về khối lượng và giá trị so với hôm qua, đạt " 
				+ finalTradingStock + " chứng khoán, tương đương " + finalTradingPrice + " đồng. ";
	}
	
	private String formatStyle2(String stockName, String changePrice, String finalTradingStock, String finalTradingPrice) {
		return stockName + " khởi đầu giao dịch bằng việc giảm thêm " + changePrice + " điểm, thanh khoản xuống thấp khi chỉ khớp lệnh " 
				+ finalTradingStock + " cổ phiếu, trị giá " + finalTradingPrice + " đồng. ";
	}
	
	private String formatStyle3(String stockName, String finalTradingStock) {
		return "Khác với phiên mua bán dồn dập hôm qua, sự tham gia dè chừng của nhà đầu tư khiến thanh khoản " + stockName 
				+ " giảm xuống còn " + finalTradingStock + " chứng khoán. ";
	}
	
	@Override
	public String getSentence() {
		// lấy thông tin chứng khoán của 1 ngày ngẫu nhiên
		Random random = new Random();
		DailySecuritiesNews dailyNews = newsList.get(random.nextInt(newsList.size()));
		
		if (dailyNews.getChangePrice() > 0) return "";
		
		String stockName = dailyNews.getName();
		String changePrice = String.valueOf(Math.abs(dailyNews.getChangePrice()));
		String finalTradingStock = String.valueOf(dailyNews.getFinalTradingStock());
		String finalTradingPrice = String.valueOf(dailyNews.getFinalTradingPrice());
		
		int style = random.nextInt(3) + 1;
		
		switch (style) {
			case 1: return formatStyle1(stockName, finalTradingStock, finalTradingPrice);
			
			case 2: return formatStyle2(stockName, changePrice, finalTradingStock, finalTradingPrice);
			
			default: return formatStyle3(stockName, finalTradingStock);
		}
	}

}
