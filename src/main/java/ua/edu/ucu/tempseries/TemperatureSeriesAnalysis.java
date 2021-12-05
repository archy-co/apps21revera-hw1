package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    private static final double MIN_TEMP = -1000;
    private static final double MAX_TEMP = 1000;
    private static final double LOW_BOUND = -273;

    private double [] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[]{};
    }

    public double[] getTemperatureSeries() {
        return temperatureSeries;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for(double temp : temperatureSeries) {
            if (temp < LOW_BOUND) {
                throw new InputMismatchException();
            }
        }

        this.temperatureSeries = temperatureSeries.clone();
    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        double sum = 0;
        for (double temp : temperatureSeries) {
            sum += temp;
        }

        return sum / temperatureSeries.length;
    }

    public double deviation() {
        double sumSqDiff = 0;
        double mean = this.average();
        for (int i = 0; i < temperatureSeries.length; i++) {
            double cur = 0;
            cur = (mean - temperatureSeries[i]) * (mean - temperatureSeries[i]);
            sumSqDiff += cur;
        }
        return Math.sqrt(sumSqDiff/(temperatureSeries.length-1));
    }

    public double min() {
        return findTempClosestToValue(MIN_TEMP);
    }

    public double max() {
        return findTempClosestToValue(MAX_TEMP);
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        double minDiff = Double.POSITIVE_INFINITY;
        double val = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (Math.abs(temp - tempValue) < minDiff) {
                minDiff = Math.abs(temp - tempValue);
                val = temp;
            }
        }

        return val;
    }

    public double[] findTempsXThen(double tempValue, boolean isLess) {
        double [] res = new double[temperatureSeries.length];
        int counter = 0;
        for (double temp : temperatureSeries) {
            if (isLess && temp <= tempValue) {
                res[counter] = temp;
                counter++;
            }
            else if (!isLess && temp >= tempValue) {
                res[counter] = temp;
                counter++;
            }
        }
        double [] finalArr = new double[counter];

        for (int i = 0; i < finalArr.length; i++) {
            finalArr[i] = res[i];
        }

        return finalArr;
    }

    public double[] findTempsLessThen(double tempValue) {
        return findTempsXThen(tempValue, true);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return findTempsXThen(tempValue, false);
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }

        TempSummaryStatistics sumStat = new TempSummaryStatistics(this.average(), this.deviation(), this.min(), this.max());

        return sumStat ;
    }

    public int addTemps(double... temps) {
        int currIdx = temperatureSeries.length;
        for (double temp : temps) {
            if(currIdx  == temperatureSeries.length) {
                int newSize = temperatureSeries.length*2;
                temperatureSeries = Arrays.copyOf(temperatureSeries, newSize);
            }
            temperatureSeries[currIdx ] = temp;
            currIdx ++;
        }
        return currIdx ;
    }
}
