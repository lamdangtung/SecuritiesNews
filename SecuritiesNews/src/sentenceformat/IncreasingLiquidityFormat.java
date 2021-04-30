package sentenceformat;

import java.util.List;
import java.util.Random;

import securitiesnews.DailySecuritiesNews;
import tag.LinkedTag;

public class IncreasingLiquidityFormat extends SecuritiesSentenceFormat { // mẫu câu về lượng tăng điểm chứng khoán

    private static final String TOPIC_NAME = "thanh khoản";
    private static final String[] TAG_NAMES = {
        "tăng", "tăng vọt",
    };

    public IncreasingLiquidityFormat() {
        super(TOPIC_NAME);
        for (String tagName: TAG_NAMES) {
            tags.add(new LinkedTag(tagName));
        }
    }

    public IncreasingLiquidityFormat(List <DailySecuritiesNews> newsList) {
        super(TOPIC_NAME);
        super.setNewsList(newsList);
        for (String tagName: TAG_NAMES) {
            tags.add(new LinkedTag(tagName));
        }
    }

    private String formatStyle1(String stockName, String changePrice){
        return  "Thị trường tăng vọt trong cuối ngày, giúp " + stockName + " tăng " + changePrice + " điểm. Đây là điểm sáng giúp thanh khoản được duy trì. ";
    }
    private String formatStyle2(String stockName, String changePrice, String highestPrice){
        return  "Thanh khoản của thị trường tiếp tục đi lên vào giữa phiên, giúp " + stockName + " tiếp tục tăng với số điểm là: " + changePrice + "xác lập kỉ lục cao nhất là: "+ highestPrice + " điểm. ";
    }
    private String formatStyle3(String changePrice, String stockName, String closingPrice ){
        return "Xu hướng lạc quan kéo dài đến cuối phiên, giúp " + stockName+ " cải thiện được tình hình với số điểm là: " + changePrice + " điểm, với giá đóng cửa là: " + closingPrice+ " điểm, kết thúc phiên giao dịch. ";
    }
    private String formatStyle4(String stockName,  String changePrice){
        return "Điểm sáng duy nhất của: " + stockName + " là thanh khoản có sự cải thiện đáng kể vào cuối phiên, với số điểm là: " + changePrice + " điểm. " ;
    }
    private String formatStyle5(String stockName, String changePrice, String closingPrice){
        return "Giao dịch sôi động của" + stockName + " kéo dài đến cuối phiên với số điểm đóng cửa là: " + closingPrice + " điểm, tăng"  + changePrice + " điểm. Thanh khoản ở mức cao.";
    }
    private String formatStyle6(String stockName, String changePrice, String highestPrice){
        return "Thanh khoản thị trường cũng có phần khởi sắc, khi " + stockName + " tăng " + changePrice + " điểm. Cập nhật mức kỷ lục mới trong ngày là: " + highestPrice + " điểm. ";
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
        String highestPrice = String.valueOf(dailyNews.getHighestPrice());

        int formatStyle = random.nextInt(6) + 1;

        switch (formatStyle) {
            case 1:
                return formatStyle1(stockName, changePrice);
            case 2:
                return formatStyle2(stockName, changePrice, highestPrice);
            case 3:
                return formatStyle3(changePrice, stockName, closingPrice);
            case 4:
                return formatStyle4(stockName, changePrice);
            case 5:
                return formatStyle5(stockName, changePrice,closingPrice);
            default:
                return formatStyle6(stockName, changePrice, highestPrice);
        }
    }

}

