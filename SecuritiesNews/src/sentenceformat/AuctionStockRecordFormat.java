package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class AuctionStockRecordFormat extends SecuritiesSentenceFormat {
	
	private static final String TOPIC_NAME = "khớp lệnh";
	private static final String[] TAG_NAMES = {
		"khối lượng", "kỷ lục", "đỉnh điểm", "đáy",
	};
	
	public AuctionStockRecordFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public AuctionStockRecordFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		super.setNewsList(newsList);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	

	private String formatStyle1(String name, String dateStart, String dateEnd, String maxfinalTradingStock, String minfinalTradingStock) {
		return  "Từ ngày " + dateStart + " đến ngày " + dateEnd + ", khối lượng khớp lệnh của " + name + " đạt giá trị lớn nhất là " + maxfinalTradingStock + " và giá trị nhỏ nhất là " + minfinalTradingStock + ". ";
	}
	
	private String formatStyle2(String name, String dateStart, String dateEnd, String maxfinalTradingStock, String minfinalTradingStock) {
		return "Trong những ngày " + dateStart + " - " + dateEnd + ", " + name + " có khối lượng khớp lệnh đạt đến đỉnh điểm là " + maxfinalTradingStock + " và ở đáy là " + minfinalTradingStock + ". ";
	}
		
	@Override
	public String getSentence() {
		// nếu là mẫu câu đặc tả dữ liệu chứng khoán trong 1 ngày
		// lấy thông tin chứng khoán của 1 ngày ngẫu nhiên
			
		Random random = new Random();
		String dateStart = newsList.get(newsList.size() - 1).getDate();
		String dateEnd = newsList.get(0).getDate();
		String name = newsList.get(0).getName();

		long maxfinalTradingStock = newsList.get(0).getFinalTradingStock(), minfinalTradingStock = newsList.get(0).getFinalTradingStock();
		
		for (int i = 1; i < newsList.size() - 1; i++) {
			if (newsList.get(i+1).getFinalTradingStock() > maxfinalTradingStock) maxfinalTradingStock = newsList.get(i+1).getFinalTradingStock();
			if (newsList.get(i+1).getFinalTradingStock() < minfinalTradingStock) minfinalTradingStock = newsList.get(i+1).getFinalTradingStock();
		}
		// chọn ngẫu nhiên 1 formatStyle
		int style = random.nextInt(2) + 1;
		
		switch (style) {
			case 1:  
				return formatStyle1(name, dateStart, dateEnd,String.valueOf(maxfinalTradingStock), String.valueOf(minfinalTradingStock));
			
			default: 
				return formatStyle2(name, dateStart, dateEnd,String.valueOf(maxfinalTradingStock), String.valueOf(minfinalTradingStock));
		}
	}
}
