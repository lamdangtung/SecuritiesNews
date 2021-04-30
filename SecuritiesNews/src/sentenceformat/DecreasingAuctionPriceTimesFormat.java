package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import sentenceformat.SecuritiesSentenceFormat;
import tag.LinkedTag;

public class DecreasingAuctionPriceTimesFormat extends SecuritiesSentenceFormat {
	
	private static final String TOPIC_NAME = "khớp lệnh";
	private static final String[] TAG_NAMES = {
		"giá trị", "giảm", "số lần giảm", 
	};
	
	public DecreasingAuctionPriceTimesFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public DecreasingAuctionPriceTimesFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		super.setNewsList(newsList);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	private String formatStyle1(String name, String dateStart, String dateEnd, String times) {
		return "Trong thời gian từ " + dateStart + " đến ngày " + dateEnd + ", giá trị khớp lệnh của " + name + " giảm tổng cộng " + times + " lần. ";
	}
	
	private String formatStyle2(String name, String dateStart, String dateEnd, String times) {
		return "Trong những ngày " + dateStart + " - " + dateEnd + ", " + name + " có giá trị khớp lệnh giảm tổng cộng " + times + " lần. ";
	}
	
	@Override
	public String getSentence() {
		// nếu là mẫu câu đặc tả dữ liệu chứng khoán trong 1 ngày
		// lấy thông tin chứng khoán của 1 ngày ngẫu nhiên
		Random random = new Random();
		String dateStart = newsList.get(newsList.size() - 1).getDate();
		String dateEnd = newsList.get(0).getDate();
		String name = newsList.get(0).getName();
		int times = 0;
		
		for (int i = 0; i < newsList.size() - 1; i++) {
			if (newsList.get(i+1).getFinalTradingPrice() > newsList.get(i).getFinalTradingPrice()) times++;
		}	
		
		// chọn ngẫu nhiên 1 formatStyle
		int style = random.nextInt(2) + 1;
		
		switch (style) {
			case 1:  
				return formatStyle1(name, dateStart, dateEnd, String.valueOf(times));
			
			default: 
				return formatStyle2(name, dateStart, dateEnd, String.valueOf(times));
		}
	}
}
