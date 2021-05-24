package codinghelmet;

import java.time.Duration;

public class Velocity {
    public final static Velocity ZERO = new Velocity(0);
    private double sqMetersPerSecond;

    public Velocity(double sqMeters, Duration time) {
        this(sqMeters/(double)time.getSeconds());
    }
    private Velocity(double sqMetersPerSecond) {
        this.sqMetersPerSecond = sqMetersPerSecond;
    }
    public Velocity add(Velocity other) {
        return new Velocity(this.sqMetersPerSecond + other.sqMetersPerSecond);
    }
    public double divideBy(Velocity other) {
        return this.sqMetersPerSecond / other.sqMetersPerSecond;
    }
}
