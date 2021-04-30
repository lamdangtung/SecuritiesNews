package securitiesnews;

import java.util.Date;

public class DailySecuritiesNews { // thông tin chứng khoán ngày
	
	private String stockName; // tên mã cổ phiếu
	private Date date; // thời gian
	private double closingPrice; // giá đóng cửa
	private double changePrice; // giá thay đổi
	private long finalTradingStock; // khối lượng giao dịch khớp lệnh
	private long finalTradingPrice; // giá tiền giao dịch khớp lệnh
	private long agreedTradingStock; // khối lượng giao dịch thỏa thuận
	private long agreedTradingPrice; // giá tiền giao dịch thỏa thuận
	private double openingPrice; // giá mở cửa
	private double highestPrice; // giá cao nhất 
	private double lowestPrice; // giá thấp nhất
	
	public DailySecuritiesNews(String stockName) {
		this.stockName = stockName;
		this.date = new Date();
	}
	
	public void setName(String stockName) {
		this.stockName = stockName;
	}
	
	public void setDate(String date) {
		String spliter[] = date.split("/");
		int day = Integer.parseInt(spliter[0]);
		int month = Integer.parseInt(spliter[1]);
		int year = Integer.parseInt(spliter[2]);
		this.date = new Date(year, month, day);
	}
	
	public int compareDateWith(Date date) {
		return this.date.compareTo(date);
	}
	
	public void setClosingPrice(double price) {
		closingPrice = price;
	}
	
	public void setChangePrice(double price) {
		changePrice = price;
	}
	
	public void setOpeningPrice(double price) {
		openingPrice = price;
	}
	
	public void setFinalTradingStock(long amount) {
		finalTradingStock = amount;
	}
	
	public void setFinalTradingPrice(long price) {
		finalTradingPrice = price;
	}
	
	public void setAgreedTradingStock(long amount) {
		agreedTradingStock = amount;
	}
	
	public void setAgreedTradingPrice(long price) {
		agreedTradingPrice = price;
	}
	
	public void setHighestPrice(double price) {
		highestPrice = price;
	}
	
	public void setLowestPrice(double price) {
		lowestPrice = price;
	}
	
	public String getName() {
		return stockName;
	}
	
	public String getDate() {
		return String.valueOf(date.getDate()) + "/" 
				+ String.valueOf(date.getMonth()) + "/" 
				+ String.valueOf(date.getYear());
	}
	
	public double getClosingPrice() {
		return closingPrice;
	}
	
	public double getChangePrice() {
		return changePrice;
	}
	
	public long getFinalTradingStock() {
		return finalTradingStock;
	}
	
	public long getFinalTradingPrice() {
		return finalTradingPrice;
	}
	
	public long getAgreedTradingStock() {
		return agreedTradingStock;
	}
	
	public long getAgreedTradingPrice() {
		return agreedTradingPrice;
	}
	
	public double getOpeningPrice() {
		return openingPrice;
	}
	
	public double getHighestPrice() {
		return highestPrice;
	}
	
	public double getLowestPrice() {
		return lowestPrice;
	}
	
}


