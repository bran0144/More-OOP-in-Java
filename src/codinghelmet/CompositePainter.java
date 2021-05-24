package codinghelmet;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompositePainter implements Painter{

    private List<Painter> painters;

    public CompositePainter(List<Painter> painters) {
        this.painters = painters;
    }
    @Override
    public boolean isAvailable() {
    }
    @Override
    public Duration estimateTimeToPaint(double sqMeters){}

    @Override
    public Money estimateCompensation(double sqMeters){}

    @Override
    public String getName() {
        return this.getPaintersNames()
                .collect(Collectors.joining(", ", "{ ", " }"));
    }
    private Stream<String> getPaintersNames(){
        return Painter.stream(this.painters).map(Painter::getName);
    }
}
