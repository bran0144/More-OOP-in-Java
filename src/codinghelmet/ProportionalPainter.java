package codinghelmet;

import java.time.Duration;

public class ProportionalPainter implements Painter{
    private String name;
    private double sqMetersPerHour;
    private MoneyRate rate;

    public ProportionalPainter(String name, double sqMetersPerHour, MoneyRate rate) {
        this.name = name;
        this.sqMetersPerHour = sqMetersPerHour;
        this.rate = rate;
    }
    @Override
    public String getName() {return this.name;}

    @Override
    public boolean isAvailable() {return true;}

    private int getSecondstoPaint(double sqMeters) {
        return (int) (sqMeters / this.sqMetersPerHour * 3600);
    }
    @Override
    public Money estimateCompensation( double sqMeters) {}

    @Override
    public Duration estimateTimeToPaint(double sqMeters) {
        return Duration.ofSeconds(this.getSecondstoPaint(sqMeters));
    }

}
