package codinghelmet;

import java.time.Duration;
import java.util.List;

public interface Painter {
    boolean isAvailable();
    Duration estimateTimeToPaint(double sqMeters);
    Money estimateCompensation(double sqMeters);
    String getName();

    default Velocity estimateVelocity(double sqMeters) {
        return new Velocity(sqMeters, this.estimateTimeToPaint(sqMeters));
    }
    static PainterStream stream(List<Painter> painters) {
        return new PainterStream(painters.stream());
    }
}

