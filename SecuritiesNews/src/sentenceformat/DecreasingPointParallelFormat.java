package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class DecreasingPointParallelFormat extends SecuritiesSentenceFormat {
	private static final String TOPIC_NAME = "dự đoán";
	private static final String[] TAG_NAMES = { "ngưỡng giảm", "giảm mạnh", "giảm" };
	
	public DecreasingPointParallelFormat() {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	public DecreasingPointParallelFormat(List <DailySecuritiesNews> newsList) {
		super(TOPIC_NAME);
		for (String tagName: TAG_NAMES) {
			tags.add(new LinkedTag(tagName));
		}
	}
	
	private boolean checkPoint(int point, String closingPrice) {
		double closing = Double.parseDouble(closingPrice);
		double x = closing - point;
		if (x > 0 && x < 1) {
			return false;
		}
		return true;
	}

	private String formatStyle1(String date, String name, String changePrice, String closingPrice) {
		return "Dự đoán ngày " + date + ", " + name + " giảm mạnh " + changePrice + " điểm, về " + closingPrice + ". "; 
	}
		
	private String formatStyle2(String date, String name, String closingPrice) {
		Random random = new Random();
		int point;
		do {
			point = random.nextInt(891) + 870;
			
		} while(checkPoint(point, closingPrice));
		return "Dự đoán ngày " + date + ", " + name + " sẽ giảm về sát ngưỡng " + point + ", đạt " + closingPrice + " điểm. ";
	}
	
	private String formatStyle3(String name) {
		return "Giao dịch ảm đạm trong các đợt khớp lệnh trước đã không đủ sức kéo " + name + 
				" khỏi cơn lao dốc, khối lượng khớp lệnh của nhóm tăng giá quá nhỏ được dự đoán là nguyên nhân kéo " +  name + " đi xuống. ";
	}
	
	@Override
	public String getSentence() {
		Random random = new Random();
		DecreasingPointParallelFormat test = new DecreasingPointParallelFormat();
		DailySecuritiesNews dailyNews = newsList.get(random.nextInt(newsList.size()));
		
		if (dailyNews.getChangePrice() > 0) return "";
		
		String name = dailyNews.getName();
		String date = dailyNews.getDate();
		String closingPrice = String.valueOf(dailyNews.getClosingPrice());
		String changePrice = String.valueOf(dailyNews.getChangePrice());
		String finalTradingStock = String.valueOf(dailyNews.getFinalTradingStock());
		String finalTradingPrice = String.valueOf(dailyNews.getFinalTradingPrice());
		
		int style = random.nextInt(3) + 1;
		
		switch(style) {
		case 3:
			return formatStyle3(name);
		case 2:
			return formatStyle2(date, name, closingPrice);
		default:
			return formatStyle1(date, name, changePrice, closingPrice); 
		}
	}
}
