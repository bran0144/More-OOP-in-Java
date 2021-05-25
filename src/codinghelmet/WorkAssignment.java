package codinghelmet;

public class WorkAssignment {
    private Painter painter;
    private double sqMeters;

    public WorkAssignment(Painter painter, double sqMeters) {
        this.painter = painter;
        this.sqMeters = sqMeters;
    }

    public Painter getPainter() { return this.painter;}
    public double getSqMeters() { return this.sqMeters;}

    public Money estimateCompensation() {
        return this.getPainter().estimateCompensation(this.getSqMeters());
    }
}
