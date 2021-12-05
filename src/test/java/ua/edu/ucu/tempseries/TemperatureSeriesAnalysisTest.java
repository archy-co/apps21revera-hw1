package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testTemperatureSeriesAnalysisConstructor() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(1, seriesAnalysis.getTemperatureSeries().length);
        assertEquals(-1.0, seriesAnalysis.getTemperatureSeries()[0], 0.001);

        double[] temperatureSeriesEmpty = {};
        TemperatureSeriesAnalysis seriesAnalysisEmpty = new TemperatureSeriesAnalysis(temperatureSeriesEmpty);
        assertEquals(0, seriesAnalysisEmpty.getTemperatureSeries().length);
    }

    @Test(expected = InputMismatchException.class)
    public void testInvalidSeriesValForConstructor() {
        double[] temperatureSeriesEmpty = {-300};
        TemperatureSeriesAnalysis seriesAnalysisEmpty = new TemperatureSeriesAnalysis(temperatureSeriesEmpty);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        seriesAnalysis.max();
        seriesAnalysis.min();

    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(-5.0, seriesAnalysis.min(), 0.00001);

        double[] temperatureSeries2 = {3.25};
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);

        assertEquals(3.25, seriesAnalysis2.min(), 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(5.0, seriesAnalysis.max(), 0.00001);
    }

    @Test
    public void testClosestToZero() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(1.0, seriesAnalysis.findTempClosestToZero(), 0.00001);
    }

    @Test
    public void testDeviation() {
        double[] temperatureSeries = {3.0, 3.0, 3.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals(0, seriesAnalysis.deviation(), 0.00001);

        double[] temperatureSeries2 = {-3, 2, 3, 1, 4};
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);

        assertEquals(2.701, seriesAnalysis2.deviation(), 0.001);
    }

    @Test
    public void testFindTempsLessThen() {
        double[] temperatureSeries = {4.63, 14.12, 11.72, -1.04, -5.89};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] expectedRes1 = {-1.04, -5.89};

        assertTrue(expectedRes1.length == seriesAnalysis.findTempsLessThen(0).length);
        assertTrue(expectedRes1[0] == seriesAnalysis.findTempsLessThen(0)[0]);
        assertTrue(expectedRes1[1] == seriesAnalysis.findTempsLessThen(0)[1]);
    }

    @Test
    public void testFindTempsGreaterThen() {
        double[] temperatureSeries = {4.63, 14.12, 11.72, -1.04, -5.89};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] expectedRes1 = {14.12, 11.72};

        assertTrue(expectedRes1.length == seriesAnalysis.findTempsGreaterThen(11.72).length);
        assertTrue(expectedRes1[0] == seriesAnalysis.findTempsGreaterThen(11.72)[0]);
        assertTrue(expectedRes1[1] == seriesAnalysis.findTempsGreaterThen(11.72)[1]);
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {4.63, 14.12, 11.72, -1.04, -5.89};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double[] additionals = {-3.24, 2.19, 3.43, 1.13, 4.7};

        assertEquals(10, seriesAnalysis.addTemps(additionals));
        assertEquals(4.63, seriesAnalysis.getTemperatureSeries()[0], 0.01);
        assertEquals(-3.24, seriesAnalysis.getTemperatureSeries()[5], 0.01);
    }

    @Test
    public void testSummaryStatistics(){
        double[] temperatureSeries = {4.63, 14.12, 11.72, -1.04, -5.89};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        assertEquals("TempSummaryStatistics{avgTemp=4.708, devTemp=8.413041661610858, minTemp=-5.89, maxTemp=14.12}",
                                seriesAnalysis.summaryStatistics().toString());
    }
}
