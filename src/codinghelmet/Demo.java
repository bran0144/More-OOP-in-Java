package codinghelmet;

import java.util.Comparator;
import java.util.List;

public class Demo {
    private static Painter findCheapest1(double sqMeters, List<Painter> painters) {
        return painters.stream()
                .filter(Painter::isAvailable)
                .min(Comparator.comparing(painter -> painter.estimateCompensation(sqMeters)))
                .get();

    }
    public void run(){

    }
}
