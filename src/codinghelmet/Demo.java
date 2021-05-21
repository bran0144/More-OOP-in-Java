package codinghelmet;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Demo {
    private static Optional<Painter> findCheapest1(double sqMeters, List<Painter> painters) {
        return painters.stream()
                .filter(Painter::isAvailable)
                .min(Comparator.comparing(painter -> painter.estimateCompensation(sqMeters)));
    }

    private static Money getTotalCost(double sqMeters, List<Painter> painters) {
        return painters.stream()
                .filter(Painter::isAvailable)
                .map(painter -> painter.estimateCompensation(sqMeters))
                .reduce(Money::add)
                .orElse(Money.ZERO);
    }
    public void run(){

    }
}
