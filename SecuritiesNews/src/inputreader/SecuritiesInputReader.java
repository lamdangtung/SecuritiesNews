package inputreader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import securitiesnews.DailySecuritiesNews;
import webscraper.WebScraper;

public class SecuritiesInputReader implements InputReader {
	
	public SecuritiesInputReader() {
		//...
	}
	
	public void readInput(String stockName, String fileName, List <DailySecuritiesNews> newsList) {		
		WebScraper scraper = new WebScraper();
		scraper.scrapeToCSVFile();
		
		BufferedReader reader = null;
		
		try { 
            reader = new BufferedReader(new FileReader(fileName));     

            String text;

            while ((text = reader.readLine()) != null) {
                String spliter[] = text.split(",");
                
                DailySecuritiesNews dailyNews = new DailySecuritiesNews(stockName);
                
                String date = spliter[0];
                dailyNews.setDate(date);
                
                double closingPrice = Double.parseDouble(spliter[1]);
                dailyNews.setClosingPrice(closingPrice);
                
                double changePrice = Double.parseDouble(spliter[2].substring(0, spliter[2].lastIndexOf("(")));
                dailyNews.setChangePrice(changePrice);
                
                long finalTradingStock = Long.parseLong(spliter[3]);
                dailyNews.setFinalTradingStock(finalTradingStock);
                
                long finalTradingPrice = Long.parseLong(spliter[4]);
                dailyNews.setFinalTradingPrice(finalTradingPrice);
                
                long agreedTradingStock = Long.parseLong(spliter[5]);
                dailyNews.setAgreedTradingStock(agreedTradingStock);
                
                long agreedTradingPrice = Long.parseLong(spliter[6]);
                dailyNews.setAgreedTradingPrice(agreedTradingPrice);
                
                double openingPrice = Double.parseDouble(spliter[7]);
                dailyNews.setOpeningPrice(openingPrice);
                
                double highestPrice = Double.parseDouble(spliter[8]);
                dailyNews.setHighestPrice(highestPrice);
                
                double lowestPrice = Double.parseDouble(spliter[9]);
                dailyNews.setLowestPrice(lowestPrice);
                
                newsList.add(dailyNews);
                
                text = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

}
