package resource;

public class Summary {
	private String symbol;
	private long maxTimeGap;
	private long prevTimeStamp;
	private long volume;
	private int maxPrice;
	private int totalTradedAmount;
	
	public Summary(){
		symbol = null;
		prevTimeStamp = 0;
		maxTimeGap = 0;
		volume = 0;
		maxPrice = 0;
		totalTradedAmount = 0;
	}
	
	public Summary(Trade trade){
		this.symbol = trade.getSymbol();
		this.prevTimeStamp = trade.getTimeStamp();
		this.maxTimeGap = 0;
		this.volume += trade.getQuantity();
		this.maxPrice = Math.max(maxPrice, trade.getPrice());
		this.totalTradedAmount += (trade.getQuantity() * trade.getPrice());
		
	}
	
	public Summary(String symbol, long timeGap, long volume, int maxPrice, int averagePrice){
		this.symbol = symbol;
		this.maxTimeGap = timeGap;
		this.volume = volume;
		this.maxPrice = maxPrice;
		this.totalTradedAmount = averagePrice;
		
	}
	
	public void update(Trade trade){
		if(!(this.symbol).equals(trade.getSymbol())){
			System.err.println("Not the same symbol...");
			return;
		}
		long potentialTimeGap = trade.getTimeStamp() - this.prevTimeStamp;
		this.maxTimeGap = Math.max(maxTimeGap, potentialTimeGap);
		this.prevTimeStamp = trade.getTimeStamp();
		this.volume += trade.getQuantity();
		this.maxPrice = Math.max(maxPrice, trade.getPrice());
		this.totalTradedAmount += (trade.getQuantity() * trade.getPrice());
	}

	@Override
	public String toString() {
		return symbol + "," + this.maxTimeGap + "," + this.volume + ","
				+ (int) (this.totalTradedAmount/this.volume) + "," + this.maxPrice;
	}

	public long getMaxTimeGap() {
		return this.maxTimeGap;
	}

	public long getVolume() {
		return this.volume;
	}

	public int getMaxPrice() {
		return this.maxPrice;
	}

	public int getWeightedAveragePrice() {
		return (int) (this.totalTradedAmount/this.volume);
	}
}
