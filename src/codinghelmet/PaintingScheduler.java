package codinghelmet;

import java.util.List;
import java.util.stream.Stream;

public interface PaintingScheduler {
    WorkStream schedule(List<Painter> painters, double sqMeters);
}
