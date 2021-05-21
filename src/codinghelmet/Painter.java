package codinghelmet;

import codinghelmet.Money;

import java.time.Duration;
import java.util.List;

public interface Painter {
    boolean isAvailable();
    Duration estimateTimeToPaint(double sqMeters);
    Money estimateCompensation(double sqMeters);
    String getName();

    static PainterStream stream(List<Painter> painters) {
        return new PainterStream(painters.stream());
    }
}

