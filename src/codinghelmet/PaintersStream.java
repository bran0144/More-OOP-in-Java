package codinghelmet;

import java.time.Duration;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaintersStream implements ForwardingStream<Painter> {
    private Stream<Painter> stream;

    public PaintersStream(Stream<Painter> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<Painter> getStream() {
        return  this.stream;
    }

    public PaintersStream available() {
        return new PaintersStream(this.getStream()
        .map(Painter::available)
        .filter(Optional::isPresent)
        .map(Optional::get));
    }

    public double estimateSqMeters(Duration time) {
        return this.getStream().mapToDouble(painter -> painter.estimateSqMeters(time)).sum();
    }
    public Optional<Painter> workTogether(PaintingScheduler scheduler) {
        return CompositePainter.of(this.stream.collect(Collectors.toList()), scheduler)
                .map(Function.identity());
    }
    public Optional<Painter> cheapest(double sqMeters) {
        return this.getStream().min(Comparator.comparing(painter -> painter.estimateCompensation(sqMeters)));
    }
    public Painter fastest(double sqMeters) {
        return this.getStream().min(Comparator.comparing(painter -> painter.estimateTimeToPaint(sqMeters)))
                .get();
    }
}
