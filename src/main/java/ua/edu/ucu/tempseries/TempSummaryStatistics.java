package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    TempSummaryStatistics(double avgTemp_, double devTemp_, double minTemp_, double maxTemp_){
        avgTemp = avgTemp_;
        devTemp = devTemp_;
        minTemp = minTemp_;
        maxTemp = maxTemp_;
    }
}
