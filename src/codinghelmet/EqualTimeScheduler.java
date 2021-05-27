package codinghelmet;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class EqualTimeScheduler implements PaintingScheduler{
    @Override
    public Stream<WorkAssignment> schedule(List<Painter> painters, double sqMeters) {
        return this.getUpperDuration(painters, sqMeters)
                .map(upper -> scheduleNonEmpty(painters, sqMeters, upper))
                .orElse(Stream.empty());
    }
    private Optional<Duration> getUpperDuration(List<Painter> painters, double sqMeters) {
        return Painter.stream(painters)
                .map(painter -> painter.estimateTimeToPaint(sqMeters))
                .min(Duration::compareTo);
    }
    private Stream<WorkAssignment> scheduleNonEmpty(
            List<Painter> painters, double sqMeters, Duration upper) {
        Duration lower = Duration.ZERO;
        //use bisection algorithm

        while (upper.minus(lower).compareTo(Duration.ofMillis(1)) > 0) {
            Duration middle = upper.plus(lower).dividedBy(2);
            double actualSqMeters =
                    Painter.stream(painters)
                    .mapToDouble(painter -> painter.estimateSqMeters(middle))
                    .sum();
            if (actualSqMeters > sqMeters) {
                upper = middle;
            }else {
                lower = middle;
            }
        }
        return null;
    }

}
