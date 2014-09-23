package tradeSummary;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

import resource.Summary;
import resource.Trade;

public class TradeSummary {
	
	private HashMap<String, Summary> summaries = new HashMap<String, Summary>();
	private FileInputStream inStream;
	private Scanner scan;
	
	public TradeSummary(){
		this("input.csv");
	}
	
	public TradeSummary(String filename){
		inStream = null;
		scan = null;
		try{
			inStream = new FileInputStream(filename);
			scan = new Scanner(inStream);
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Well that is not a good sign...");
		}
	}
	
	public void computeSummaries(){
		while(scan.hasNext()){
			String row = scan.nextLine();
			String[] column = row.split(",");
			if(column.length != 4){
				System.err.println("A row does not have 4 elements in it cannot parse...");
			}
			Trade trade = new Trade(column);
			Summary tradeOutput = summaries.get(trade.getSymbol());
			if(tradeOutput == null){
				tradeOutput = new Summary(trade);
			}
			else{
				tradeOutput.update(trade);
			}
			summaries.put(trade.getSymbol(), tradeOutput);
		}
		try {
			inStream.close();
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exportSummaries(){
		exportSummaries("output.csv");
	}
	
	public void exportSummaries(String filename) {
		FileOutputStream outStream = null;
		try {
			outStream = new FileOutputStream(filename);
			TreeMap<String, Summary> sortedOutputs = new TreeMap<String, Summary>(summaries);
			for(String symbol : sortedOutputs.keySet()){
				outStream.write(summaries.get(symbol).toString().getBytes());
				outStream.write('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		TradeSummary ts = new TradeSummary("input.csv");
		ts.computeSummaries();
		ts.exportSummaries("output.csv");
	}

}
