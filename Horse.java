public class Horse extends Thread {

    /* 
     * I want to gamble on horseracing but there are not enough races for me to satisfy my addiction.
     * Instead, write a program which creates 8 different threads of the horse class where each 
     * object races to get to 10000 meters by incrementing by 1 repeatedly. use a static variable 
     * called place that stores what place the horse comes in, when a horse finishes the race print it's place
     * and which horse it is. figure out the rest.
     */

                            /* SOLUTION */
    
    private static int place = 1;
    private int competitor;
    public Horse(int competitor) {
        this.competitor = competitor;
    }
    private static synchronized void finish(int competitor) {
        System.out.printf("Competitor %d finished in place %d\n", competitor, place);
        place++;
    }
    public void run() {
        for (int i = 0; i < 10000; i++) {}
        finish(competitor);
    }

    public static void main(String[] args) {
        Horse [] racers = new Horse[8];
        for (int i = 0; i < 8; i++) {
            racers[i] = new Horse(i + 1);
        }
        for (Horse racer : racers) {
            racer.start();
        }
    }
}
