package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;
public class PointParallelFormat extends SecuritiesSentenceFormat {	
	private static final String TOPIC_NAME = "dự đoán";
	private static final String[] TAG_NAMES = { "ngưỡng dự đoán", "ngưỡng" };
	
	public PointParallelFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public PointParallelFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	private String formatStyle1(String name) {
		return "Theo dự đoán của VIS, nhiều khả năng " + name + " đi ngang trong những ngày tiếp theo. ";
	}
	
	private String formatStyle1(String date, String name, String closingPrice) {
		return name + " trụ vững ở phiên giao dịch ngày " + date + ", ở ngưỡng " + closingPrice + ". ";
	}
	
	private boolean checkPoint(int point, String closingPrice) {
		double closing = Double.parseDouble(closingPrice);
		double x = point - closing;
		if (x > 0 && x < 1) {
			return false;
		}
		return true;
	}
	private String formatStyle2(String date, String name, String closingPrice) {
		Random random = new Random();
		int point;
		do {
			point = random.nextInt(891) + 870;
			
		} while(checkPoint(point, closingPrice));			
		
		return "Dự đoán " + name + " đạt " + closingPrice + ", sát ngưỡng " + String.valueOf(point);
	}
	
	@Override
	public String getSentence() {
		Random random = new Random();
		PointParallelFormat test = new PointParallelFormat();
		DailySecuritiesNews dailyNews = newsList.get(random.nextInt(newsList.size()));
		
		String name = dailyNews.getName();
		String date = dailyNews.getDate();
		String closingPrice = String.valueOf(dailyNews.getClosingPrice());
		String changePrice = String.valueOf(dailyNews.getChangePrice());
		String finalTradingStock = String.valueOf(dailyNews.getFinalTradingStock());
		String finalTradingPrice = String.valueOf(dailyNews.getFinalTradingPrice());
		
		int style = random.nextInt(5) + 1;
		switch(style) {
		case 1:
			return random.nextBoolean() ? formatStyle1(name) : formatStyle1(date, name, closingPrice);
		default:
			return formatStyle2(date, name, closingPrice);
		}
	}
}
