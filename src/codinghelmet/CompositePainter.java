package codinghelmet;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompositePainter implements Painter{

    private List<Painter> subordinatePainters;
    private PaintingScheduler scheduler;

    private CompositePainter(List<Painter> subordinatePainters, PaintingScheduler scheduler) {
        this.subordinatePainters = subordinatePainters;
        this.scheduler = scheduler;
    }

    public static Optional<CompositePainter> of(List<Painter> subordinatePainters, PaintingScheduler scheduler) {
        return subordinatePainters.isEmpty()
                ? Optional.empty()
                : Optional.of(new CompositePainter(subordinatePainters, scheduler));
    }
    @Override
    public Optional<Painter> available() {
        return painters().available().workTogether(this.scheduler);
    }
    @Override
    public Duration estimateTimeToPaint(double sqMeters) {
        return schedule(sqMeters).timesToPaint().maxOfMany();
    }

    @Override
    public Money estimateCompensation(double sqMeters){
        return schedule(sqMeters).compensations().sum();
    }
    private WorkStream schedule(double sqMeters) {
        return scheduler.schedule(this.subordinatePainters, sqMeters);
    }
    @Override
    public double estimateSqMeters(Duration time) {
        return painters().estimateSqMeters(time);
    }

    private PaintersStream painters() {
        return Painter.stream(this.subordinatePainters);
    }

    @Override
    public String getName() {
        return this.getPaintersNames()
                .collect(Collectors.joining(", ", "{ ", " }"));
    }
    private Stream<String> getPaintersNames(){
        return Painter.stream(this.subordinatePainters).map(Painter::getName);
    }
}
