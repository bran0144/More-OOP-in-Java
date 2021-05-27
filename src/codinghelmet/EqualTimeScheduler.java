package codinghelmet;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class EqualTimeScheduler implements PaintingScheduler{
    @Override
    public WorkStream schedule(List<Painter> painters, double sqMeters) {
        return this.getUpperDuration(painters, sqMeters)
                .map(upper -> scheduleNonEmpty(painters, sqMeters, upper))
                .orElse(WorkAssignment.stream(Stream.empty()));
    }
    private Optional<Duration> getUpperDuration(List<Painter> painters, double sqMeters) {
        return Painter.stream(painters)
                .map(painter -> painter.estimateTimeToPaint(sqMeters))
                .min(Duration::compareTo);
    }
    private WorkStream scheduleNonEmpty(
            List<Painter> painters, double sqMeters, Duration upper) {
        return this.scheduleNonEmpty(painters, this.getTotalTime(painters, sqMeters, upper));
    }

    private WorkStream scheduleNonEmpty(
            List<Painter> painters, Duration totalTime) {
        return WorkAssignment.stream(Painter.stream(painters)
                .map(painter -> painter.assign(painter.estimateSqMeters(totalTime))));
    }
    private Duration getTotalTime(List<Painter> painters, double sqMeters, Duration upper) {
        return this.getTotalTime(painters, sqMeters, Duration.ZERO, upper);
    }
    private Duration getTotalTime(List<Painter> painters, double sqMeters, Duration lower, Duration upper) {
        return upper.minus(lower).compareTo(Duration.ofMillis(1)) <= 0
                ? lower
                :getTotalTime(painters, sqMeters, lower, upper.plus(lower).dividedBy(2), upper);
    }
    private Duration getTotalTime(List<Painter> painters, double sqMeters, Duration lower, Duration middle, Duration upper) {
        return this.getTotalSqMeters(painters, middle) > sqMeters
                ? this.getTotalTime(painters, sqMeters, lower, middle)
                : this.getTotalTime(painters, sqMeters, middle, upper);
    }

    private double getTotalSqMeters(List<Painter> painters, Duration time) {
        return Painter.stream(painters)
                .mapToDouble(painter -> painter.estimateSqMeters(time))
                .sum();
    }

}
