package codinghelmet;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Demo {
    private static Optional<Painter> findCheapest1(double sqMeters, List<Painter> painters) {
        return painters.stream()
                .filter(Painter::isAvailable)
                .min(Comparator.comparing(painter -> painter.estimateCompensation(sqMeters)));
    }

    private static Optional<Painter> findCheapest2(double sqMeters, List<Painter> painters) {
        return Painter.stream(painters).available().cheapest(sqMeters);
    }
    private static Money getTotalCost(double sqMeters, List<Painter> painters) {
        return painters.stream()
                .filter(Painter::isAvailable)
                .map(painter -> painter.estimateCompensation(sqMeters))
                .reduce(Money::add)
                .orElse(Money.ZERO);
    }

    public void workTogether(double sqMeters, List<Painter> painters){
        Velocity groupVelocity = Painter.stream(painters).available()
                .map(painter -> painter.estimateVelocity(sqMeters))
                .reduce(Velocity::add)
                .orElse(Velocity.ZERO);

        Painter.stream(painters).available()
                .forEach(painter -> {
                    double partialSqMeters = sqMeters * painter.estimateVelocity(sqMeters).divideBy(groupVelocity);
                    this.print(painter, partialSqMeters);
                });
    }
    public void run(){
        List<Painter> painters1 = this.createPainters1();
        double sqMeters = 200;

        this.print(painters1);
        System.out.println();
        System.out.println("Demo #1 = Letting all painters work together");
        this.workTogether(sqMeters, painters1);

    }
}
