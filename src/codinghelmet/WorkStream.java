package codinghelmet;

import java.util.stream.Stream;

public class WorkStream implements ForwardingStream<WorkAssignment>{
    private Stream<WorkAssignment> stream;

    public WorkStream(Stream<WorkAssignment> stream) {
        this.stream = stream;
    }
    @Override
    public Stream<WorkAssignment> getStream() {
        return this.stream;
    }
    public MoneyStream compensations() {
        return Money.stream(this.stream.map(WorkAssignment::estimateCompensation));
    }
    public DurationsStream timesToPaint() {
        return new DurationsStream(this.stream.map(WorkAssignment::estimateTimeToPaint));
    }
}
