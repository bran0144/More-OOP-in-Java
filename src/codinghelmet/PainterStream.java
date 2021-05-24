package codinghelmet;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class PainterStream implements ForwardingStream<Painter> {
    private Stream<Painter> stream;

    public PainterStream(Stream<Painter> stream) {
        this.stream = stream;
    }

    @Override
    public Stream<Painter> getStream() {
        return  this.stream;
    }

    public PainterStream available() {
        return new PainterStream(this.getStream().filter(Painter::isAvailable));
    }

    public Optional<Painter> cheapest(double sqMeters) {
        return this.getStream().min(Comparator.comparing(painter -> painter.estimateCompensation(sqMeters)));
    }
    public Optional<Painter> fastest(double sqMeters) {
        return this.getStream().min(Comparator.comparing(painter -> painter.estimateTimeToPaint(sqMeters)));
    }
}
