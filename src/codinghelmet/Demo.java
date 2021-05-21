package codinghelmet;

import java.util.List;

public class Demo {
    private static Painter findCheapest1(double sqMeters, List<Painter> painters) {
        Money lowestCost = Money.ZERO;
        Painter winner = null;
        for (Painter candidate: painters) {
            if (candidate.isAvailable()) {
                Money cost = candidate.estimateCompensation(sqMeters);
                if (winner == null || cost.compareTo(lowestCost) <= 0) {
                    winner = candidate;
                    lowestCost = cost;
                }
            }
        }
        return winner;
    }
    public void run(){

    }
}
