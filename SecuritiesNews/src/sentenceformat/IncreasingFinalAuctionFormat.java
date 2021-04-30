package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class IncreasingFinalAuctionFormat extends SecuritiesSentenceFormat {
	
	private static final String TOPIC_NAME = "khớp lệnh";
	private static final String[] TAG_NAMES = {
		"khớp lệnh cuối", "tăng",
	};
	
	public IncreasingFinalAuctionFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public IncreasingFinalAuctionFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		super.setNewsList(newsList);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	private String formatStyle1(String name, String date, String changePrice, String closingPrice) {
		return "Đợt khớp lệnh cuối ngày " + date + ", " + name + " tăng " + changePrice + " điểm, tiến đến mức " + closingPrice + " điểm. ";
	}
	
	private String formatStyle2(String name, String date, String changePrice, String finalTradingStock, String finalTradingPrice) {
		return "Ngày " + date + name + " tăng " + changePrice + " mặc dù mua bán thận trọng với " + finalTradingStock + " cổ phiếu khớp lệnh, trị giá " + finalTradingPrice + " đồng. ";
	}
	
	private String formatStyle3(String name, String closingPrice, String finalTradingStock, String finalTradingPrice) {
		return "Kết thúc khớp lệnh cuối cùng, " + name + " tăng đến " + closingPrice + " điểm, với " + finalTradingStock + " cổ phiếu khớp lệnh, trị giá " + finalTradingPrice + " đồng. ";
	}
	
	@Override
	public String getSentence() {
		// nếu là mẫu câu đặc tả dữ liệu chứng khoán trong 1 ngày
		// lấy thông tin chứng khoán của 1 ngày ngẫu nhiên
		Random random = new Random();
		DailySecuritiesNews dailyNews = newsList.get(random.nextInt(newsList.size()));
		
		if (dailyNews.getChangePrice() < 0) return "";
		
		String name = dailyNews.getName();
		String date = dailyNews.getDate();
		String closingPrice = String.valueOf(dailyNews.getClosingPrice());
		String changePrice = String.valueOf(Math.abs(dailyNews.getChangePrice()));
		String finalTradingStock = String.valueOf(dailyNews.getFinalTradingStock());
		String finalTradingPrice = String.valueOf(dailyNews.getFinalTradingPrice());
		
		// chọn ngẫu nhiên 1 formatStyle
		int style = random.nextInt(3) + 1;
		
		switch (style) {
			case 1:  
				return formatStyle1(name, date, changePrice, closingPrice);
			
			case 2: 
				return formatStyle2(name, date, changePrice, finalTradingStock, finalTradingPrice);
			
			default: 
				return formatStyle3(name, closingPrice,finalTradingStock, finalTradingPrice);
		}
	}
}
