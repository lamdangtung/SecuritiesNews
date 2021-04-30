package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class AuctionPriceRecordFormat extends SecuritiesSentenceFormat{
	
	private static final String TOPIC_NAME = "khớp lệnh";
	private static final String[] TAG_NAMES = {
		"giá trị", "kỷ lục", "đỉnh điểm", "đáy",
	};
	
	public AuctionPriceRecordFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public AuctionPriceRecordFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		super.setNewsList(newsList);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	

	private String formatStyle1(String name, String dateStart, String dateEnd, String maxfinalTradingPrice, String minfinalTradingPrice) {
		return "Từ ngày " + dateStart + " đến " + dateEnd + ", giá trị giao dịch của " 
				+ name + " đạt giá trị lớn nhất là " + maxfinalTradingPrice + " và giá trị nhỏ nhất là " + minfinalTradingPrice + ". ";
	}
	
	private String formatStyle2(String name, String dateStart, String dateEnd, String maxfinalTradingPrice, String minfinalTradingPrice) {
		return "Trong những ngày " + dateStart + " - " + dateEnd + ", " + name 
				+ " có khối lượng khớp lệnh đạt đến đỉnh điểm là  " + minfinalTradingPrice + " và ở đáy là " + minfinalTradingPrice + ". ";
	}
	
	
	
	@Override
	public String getSentence() {
		// nếu là mẫu câu đặc tả dữ liệu chứng khoán trong 1 ngày
		// lấy thông tin chứng khoán của 1 ngày ngẫu nhiên

		Random random = new Random();
		String dateStart = newsList.get(newsList.size() - 1).getDate();
		String dateEnd = newsList.get(0).getDate();
		String name = newsList.get(0).getName();
		
		long maxfinalTradingPrice = newsList.get(0).getFinalTradingPrice(), minfinalTradingPrice = newsList.get(0).getFinalTradingPrice();
		
		for (int i = 1; i < newsList.size() - 1; i++) {
			if (newsList.get(i+1).getFinalTradingPrice() > maxfinalTradingPrice) maxfinalTradingPrice = newsList.get(i+1).getFinalTradingPrice();
			if (newsList.get(i+1).getFinalTradingPrice() < minfinalTradingPrice) minfinalTradingPrice = newsList.get(i+1).getFinalTradingPrice();
		}	
		
		// chọn ngẫu nhiên 1 formatStyle
		int style = random.nextInt(2) + 1;
		
		switch (style) {
			case 1:  
				return formatStyle1(name, dateStart, dateEnd, String.valueOf(maxfinalTradingPrice), String.valueOf(minfinalTradingPrice));
			
			default: 
				return formatStyle2(name, dateStart, dateEnd, String.valueOf(maxfinalTradingPrice), String.valueOf(minfinalTradingPrice));
		}
	}
}
