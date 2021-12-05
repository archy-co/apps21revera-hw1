import ua.edu.ucu.tempseries.TemperatureSeriesAnalysis;

public class Main {
    public static void main(String [] args) {
        double[] temperatureSeries = {2};
        TemperatureSeriesAnalysis seriesAnalysis = new
                TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }
}
