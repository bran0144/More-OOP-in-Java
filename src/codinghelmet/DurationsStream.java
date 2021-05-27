package codinghelmet;

import java.time.Duration;
import java.util.stream.Stream;

public class DurationsStream implements ForwardingStream<Duration>{
    private Stream<Duration> stream;

    public DurationsStream(Stream<Duration> durations) {
        this.stream = durations;
    }
    @Override
    public Stream<Duration> getStream() {
        return this.stream;
    }
    public Duration maxOfMany() {
        return this.stream.max(Duration::compareTo).get();
    }

}
