package codinghelmet;

public class Money implements Comparable<Money>{
    public static Money ZERO;

    public Money scale(long multiply, long divide);
    public Money scale(double factor);
    public Money add(Money other);

    public int compareTo(Money other);
}
