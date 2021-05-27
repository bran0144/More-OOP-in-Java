package codinghelmet;

import java.time.Duration;
import java.util.stream.Stream;

public class WorkAssignment {
    private Painter painter;
    private double sqMeters;

    public WorkAssignment(Painter painter, double sqMeters) {
        this.painter = painter;
        this.sqMeters = sqMeters;
    }

    public Money estimateCompensation() {
        return this.painter.estimateCompensation(this.sqMeters);
    }
    public Duration estimateTimeToPaint(){
        return this.painter.estimateTimeToPaint(this.sqMeters);
    }
    public static WorkStream stream(Stream<WorkAssignment> assignments) {
        return new WorkStream(assignments);
    }
}
