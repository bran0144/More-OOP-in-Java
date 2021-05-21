package codinghelmet;

import codinghelmet.Money;

import java.time.Duration;

public interface Painter {
    boolean isAvailable();
    Duration estimateTimeToPaint(double sqMeters);
    Money estimateCompensation(double sqMeters);
    String getName();
}
