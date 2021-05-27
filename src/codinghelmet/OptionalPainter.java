package codinghelmet;

import java.util.Optional;
import java.util.function.Function;

public class OptionalPainter {
    private Optional<Painter> content;

    private OptionalPainter(Optional<Painter> content) {
        this.content = content;
    }
    private static OptionalPainter of(Painter painter) {
        return new OptionalPainter(Optional.of(painter));
    }

    public Optional<Painter> asOptional() {
        return this.content;
    }
    public static OptionalPainter empty() {
        return new OptionalPainter(Optional.empty());
    }
    public OptionalPainter available() {
        return this.content
                .map(painter -> painter.available())
                .orElse(OptionalPainter.empty());
    }
    public OptionalPainter mapPainter(Function<Painter, Painter> mapping) {
        return new OptionalPainter(this.content.map(mapping::apply));
    }
    public <TResult> Optional<TResult> map(Function<Painter, TResult> mapping) {
        return this.content.map(mapping::apply);
    }
    public OptionalAssignment assign(double sqMeters) {
        return this.content
                .map(painter -> painter.assign(sqMeters))
                .map(OptionalAssignment::of)
                .orElse(OptionalAssignment.empty());
    }
}
