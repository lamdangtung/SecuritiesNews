package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class DecreasingPointRecordFormat extends SecuritiesSentenceFormat { // mẫu câu về lượng tăng điểm chứng khoán

    private static final String TOPIC_NAME = "biến động";
    private static final String[] TAG_NAMES = {
            "giảm", "giảm kỷ lục", 
    };

    public DecreasingPointRecordFormat() {
        super(TOPIC_NAME);
        for (String tagName: TAG_NAMES) {
            tags.add(new LinkedTag(tagName));
        }
    }

    public DecreasingPointRecordFormat(List <DailySecuritiesNews> newsList) {
        super(TOPIC_NAME);
        super.setNewsList(newsList);
        for (String tagName: TAG_NAMES) {
            tags.add(new LinkedTag(tagName));
        }
    }

    private String formatStyle1( String stockName, String changePrice, String lowestPrice) {
        return "Chỉ số " + stockName + " tụt xuống " + changePrice + " điểm, chỉ còn " + lowestPrice + " điểm. Mức thấp nhất trong thời gian trở lại đây. ";
    }
    private String formatStyle2(String stockName, String changePrice, String lowestPrice) {
        return stockName + " xác lập một đáy mới để chốt phiên cuối tuần chỉ còn " + changePrice + " điểm, xác lập kỉ lục thấp nhất là: " + lowestPrice + " điểm. ";
    }
    private String formatStyle3(String changePrice, String stockName, String closingPrice, String lowestPrice ) {
        return " Với mức giảm là: " + changePrice + " điểm, " + stockName + " kết thúc phiên giao dịch với " + closingPrice + " điểm, đạt mức thấp nhất với : " + lowestPrice + " điểm. ";
    }
    private String formatStyle4(String stockName, String date, String changePrice, String lowestPrice) {
        return stockName + " giảm về sát ngưỡng, để sau " + date + " ngày giao dịch mất hết " + changePrice + " điểm, chỉ còn " + lowestPrice + " điểm, mức thấp nhất trong thời gian trở lại đây. ";
    }
    private String formatStyle5(String stockName, String changePrice, String openingPrice) {
        return "Mở đầu phiên, với giá mở cửa là: " + openingPrice + " điểm, " + stockName + " giảm nhẹ " + changePrice + " điểm, song giao dịch chậm, thanh khoản thấp. ";
    }

    @Override
    public String getSentence() {
        // lấy thông tin chứng khoán của 1 ngày ngẫu nhiên
        Random random = new Random();
        DailySecuritiesNews dailyNews = newsList.get(random.nextInt(newsList.size()));

        if (dailyNews.getChangePrice() > 0) return "";
        
        String stockName = dailyNews.getName();
        String date = dailyNews.getDate();
        String changePrice = String.valueOf(Math.abs(dailyNews.getChangePrice()));
        String lowestPrice = String.valueOf(dailyNews.getLowestPrice());
        String closingPrice = String.valueOf(dailyNews.getClosingPrice());
        String openingPrice = String.valueOf(dailyNews.getOpeningPrice());

        int formatStyle = random.nextInt(5) + 1;

        switch (formatStyle) {
            case 1:
                return formatStyle1(stockName, changePrice, lowestPrice);
            case 2:
                return formatStyle2(stockName, changePrice, lowestPrice);
            case 3:
                return formatStyle3(changePrice, stockName, closingPrice, lowestPrice);
            case 4:
                return formatStyle4(stockName, date, changePrice, lowestPrice);
            default:
                return formatStyle5(stockName, changePrice, openingPrice);
        }
    }

}

