package codinghelmet;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompositePainter implements Painter{

    private List<Painter> painters;

    private CompositePainter(List<Painter> painters) {
        this.painters = painters;
    }

    public static Optional<CompositePainter> of(List<Painter> painters) {
        return painters.isEmpty()
                ? Optional.empty()
                : Optional.of(new CompositePainter(painters));
    }
    @Override
    public Optional<Painter> available() {
        return CompositePainter.of(
            Painter.stream(this.painters).available().collect(Collectors.toList()))
                .<Painter>map(Function.identity());
    }
    @Override
    public Duration estimateTimeToPaint(double sqMeters) {
        return this.estimateTimeToPaint(sqMeters, this.estimateTotalVelocity(sqMeters));
    }
   private Duration estimateTimeToPaint(double sqMeters, Velocity totalVelocity){
        return Painter.stream(this.painters)
                .map(painter -> painter.estimateTimeToPaint(sqMeters * painter.estimateVelocity(sqMeters).divideBy(totalVelocity)))
                .max(Duration::compareTo)
                .get();
    }
    private Velocity estimateTotalVelocity(double sqMeters) {
        return Painter.stream(this.painters)
                .map(painter -> painter.estimateVelocity(sqMeters))
                .reduce(Velocity::add)
                .orElse(Velocity.ZERO);
    }

    @Override
    public Money estimateCompensation(double sqMeters){
        return this.estimateCompensation(sqMeters, this.estimateTotalVelocity(sqMeters));
    }

    private Money estimateCompensation(double sqMeters, Velocity totalVelocity) {
        return Painter.stream(this.painters)
                .map(painter -> painter.estimateCompensation(sqMeters * painter.estimateVelocity(sqMeters).divideBy(totalVelocity)))
                .reduce(Money::add)
                .orElse(Money.ZERO);
    }

    @Override
    public String getName() {
        return this.getPaintersNames()
                .collect(Collectors.joining(", ", "{ ", " }"));
    }
    private Stream<String> getPaintersNames(){
        return Painter.stream(this.painters).map(Painter::getName);
    }
}
