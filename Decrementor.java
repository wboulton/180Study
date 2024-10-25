import java.util.*;

public class Decrementor {
    private static ArrayList<String> trades;
    private static int index;
    private final Object gatekeep = new Object();

    public Decrementor() {
        if (trades == null) {
            trades = new ArrayList<String>();
            index = 0;
        }
    }
    synchronized public void addTrade(String trade) {
        trades.add(trade);
    }
    synchronized public int getIndex() {
        return index;
    }

    public void run() {
        
        for (int i = getIndex(); i < trades.size(); i++) {
            String[] tradeData;
            synchronized (gatekeep) {
                i = getIndex(); index++;
                if (i >= trades.size()) {
                    break;
                }
                tradeData = trades.get(i).split(",");
            }
            if (Integer.parseInt(tradeData[4]) > 0) { //idk if this is meant to go through the trade until it reaches zero or not, to do so just change if to while on this line
                double rate = Double.parseDouble(tradeData[3]);
                double value = Double.parseDouble(tradeData[1]);
                value = rate * value;
                tradeData[1] = String.format("%.2f", value);
                int duration = Integer.parseInt(tradeData[4]);
                duration --;
                tradeData[4] = Integer.toString(duration);
                if (duration == 0) {
                    synchronized (gatekeep) {
                        System.out.printf("%s,%s,%s,%s,%s\n",tradeData[0], tradeData[1],tradeData[2],tradeData[3],tradeData[4]);

                    }
                }
                synchronized (gatekeep) {
                    String newTrade = String.format("%s,%s,%s,%s,%s",tradeData[0], tradeData[1],tradeData[2],tradeData[3],tradeData[4]);
                    trades.set(i, newTrade);
                }
            }
        }
    }

    public static void main(String[] args) {
        Decrementor one = new Decrementor();
        one.addTrade("10/10/2024,10.0,15.0,2.3,5");
        one.addTrade("12/10/1999,10.0,15.0,2.3,2");
        one.addTrade("1/1/1000,10.0,15.0,2.4,1");
        one.addTrade("1/1/2000,10.0,15.0,2.4,1");


        Thread threadOne = new Thread(one::run);
        Thread threadTwo = new Thread(one::run);

        threadOne.start();
        threadTwo.start();        
    }
}
