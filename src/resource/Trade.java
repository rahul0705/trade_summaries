package resource;

public class Trade {
	private long timeStamp;
	private String symbol;
	private int quantity;
	private int price;

	public Trade(){
		timeStamp = -1;
		symbol = null;
		quantity = -1;
		price = -1;
	}
	
	public Trade(String[] data){
		this.timeStamp = Long.parseLong(data[0]);
		this.symbol = data[1];
		this.quantity = Integer.parseInt(data[2]);
		this.price = Integer.parseInt(data[3]);
	}
	
	public Trade(long timestamp, String symbol, int quantity, int price){
		this.timeStamp = timestamp;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimestamp(long timestamp) {
		this.timeStamp = timestamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
