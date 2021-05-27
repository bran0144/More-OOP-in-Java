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
        return Painter.stream(painters).timesToPaint(sqMeters).min();
    }
    private WorkStream scheduleNonEmpty(
            List<Painter> painters, double sqMeters, Duration upper) {
        return this.scheduleNonEmpty(painters, this.totalTime(painters, sqMeters, upper));
    }

    private WorkStream scheduleNonEmpty(
            List<Painter> painters, Duration totalTime) {
        return Painter.stream(painters).assign(totalTime);
    }
    private Duration totalTime(List<Painter> painters, double sqMeters, Duration upper) {
        return DurationRange.zeroTo(upper)
                .bisect(time -> this.totalSqMeters(painters, time))
                .convergeTo(sqMeters, Duration.ofMillis(1))
                .middle();
    }

    private double totalSqMeters(List<Painter> painters, Duration time) {
        return Painter.stream(painters).estimateSqMeters(time);
    }

}
