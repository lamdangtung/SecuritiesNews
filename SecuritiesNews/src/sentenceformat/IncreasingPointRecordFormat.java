package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class IncreasingPointRecordFormat extends SecuritiesSentenceFormat { // mẫu câu về lượng tăng điểm chứng khoán

    private static final String TOPIC_NAME = "biến động điểm";
    private static final String[] TAG_NAMES = {
            "tăng", "tăng kỷ lục", 
    };

    public IncreasingPointRecordFormat() {
        super(TOPIC_NAME);
        for (String tagName: TAG_NAMES) {
            tags.add(new LinkedTag(tagName));
        }
    }

    public IncreasingPointRecordFormat(List <DailySecuritiesNews> newsList) {
        super(TOPIC_NAME);
        super.setNewsList(newsList);
        for (String tagName: TAG_NAMES) {
            tags.add(new LinkedTag(tagName));
        }
    }

    private String formatStyle1(String stockName, String changePrice, String highestPrice) {
        return stockName + " khởi đầu phiên giao dịch trong không khí hứng khởi, giành được thêm " + changePrice +" điểm, xác lập kỉ lục cao nhất là: "+ highestPrice + " điểm. ";
    }
    private String formatStyle2(String changePrice, String stockName, String closingPrice ) {
        return "Xu hướng lạc quan kéo dài đến cuối phiên, giúp " + stockName + " tăng mạnh " + changePrice + " điểm, với giá đóng cửa là: " + closingPrice + " điểm. ";
    }
    private String formatStyle3(String stockName,  String changePrice) {
        return "Tiếp tục tín hiệu khởi sắc, bước vào đầu phiên giao dịch " + stockName + " tăng mạnh " + changePrice + " điểm. " ;
    }
    private String formatStyle4(String stockName, String changePrice, String openingPrice) {
        return "Mở đầu phiên, với giá mở cửa là: " + openingPrice + " điểm, " + stockName + " tăng mạnh " + changePrice + " điểm, đây là một khởi đầu thuận lợi.";
    }
    private String formatStyle5(String stockName, String changePrice, String highestPrice) {
        return "Chốt phiên giao dịch, " + stockName + " tăng " + changePrice + " điểm, xác lập kỷ lục mới: " + highestPrice + " điểm. ";
    }


    @Override
    public String getSentence() {
        // lấy thông tin chứng khoán của 1 ngày ngẫu nhiên
        Random random = new Random();
        DailySecuritiesNews dailyNews = newsList.get(random.nextInt(newsList.size()));
        
        if (dailyNews.getChangePrice() < 0) return "";
        
        String stockName = dailyNews.getName();
        String changePrice = String.valueOf(Math.abs(dailyNews.getChangePrice()));
        String closingPrice = String.valueOf(dailyNews.getClosingPrice());
        String openingPrice = String.valueOf(dailyNews.getOpeningPrice());
        String highestPrice = String.valueOf(dailyNews.getHighestPrice());

        int formatStyle = random.nextInt(5) + 1;

        switch (formatStyle) {
            case 1:
                return formatStyle1(stockName, changePrice, highestPrice);
            case 2:
                return formatStyle2(changePrice, stockName, closingPrice);
            case 3:
                return formatStyle3(stockName, changePrice);
            case 4:
                return formatStyle4(stockName, changePrice,openingPrice);
            default:
                return formatStyle5(stockName, changePrice, highestPrice);
        }
    }

}

