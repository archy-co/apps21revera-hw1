package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    TempSummaryStatistics(double avgTempL, double devTempL,
                          double minTempL, double maxTempL) {
        avgTemp = avgTempL;
        devTemp = devTempL;
        minTemp = minTempL;
        maxTemp = maxTempL;
    }

    @Override
    public String toString() {
        return "TempSummaryStatistics{" +
                "avgTemp=" + avgTemp +
                ", devTemp=" + devTemp +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                '}';
    }
}
