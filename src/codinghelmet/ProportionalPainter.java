package codinghelmet;

import java.time.Duration;
import java.util.Optional;

public class ProportionalPainter implements Painter {
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
    public Optional<Painter> available() {
        return Optional.of(this);
    }

    private int getSecondstoPaint(double sqMeters) {
        return (int) (sqMeters / this.sqMetersPerHour * 3600);
    }

    @Override
    public Money estimateCompensation( double sqMeters) {
        return this.rate.getTotalFor(this.estimateTimeToPaint(sqMeters));
    }

    @Override
    public Duration estimateTimeToPaint(double sqMeters) {
        return Duration.ofSeconds(this.getSecondstoPaint(sqMeters));
    }
    public String toString(){
        return String.format(
                "%s painting %.2f sq meters per hour at rate %s",
                this.getName(), this.sqMetersPerHour, this.rate);
    }

}
