package ua.edu.ucu.tempseries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class TemperatureSeriesAnalysis {

    private double MIN_TEMP = -1000;
    private double MAX_TEMP = 1000;

    double [] temperatureSeries;

    public TemperatureSeriesAnalysis() {
        temperatureSeries = new double[]{};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        boolean invalid = false;
        for(double temp : temperatureSeries)
            if(temp < -273)
                invalid = true;

        if (invalid)
            throw new InputMismatchException();

        this.temperatureSeries = temperatureSeries.clone();
    }

    public double average() {
        if (temperatureSeries.length == 0)
            throw new IllegalArgumentException();

        double sum = 0;
        for (double temp : temperatureSeries)
            sum += temp;

        return sum / temperatureSeries.length;
    }

    public double deviation() {
        double sum_sq_dif = 0;
        double mean = this.average();
        for (int i = 0; i < temperatureSeries.length; i++){
            double cur = 0;
            cur = (mean - temperatureSeries[i]) * (mean - temperatureSeries[i]);
            sum_sq_dif += cur;
        }
        return sqrt(sum_sq_dif/(temperatureSeries.length-1));
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
        if (temperatureSeries.length == 0)
            throw new IllegalArgumentException();

        double min_diff = Double.POSITIVE_INFINITY;
        double val = temperatureSeries[0];
        for (double temp : temperatureSeries){
            if (Math.abs(temp - tempValue) < min_diff) {
                min_diff = Math.abs(temp - tempValue);
                val = temp;
            }
        }

        return val;
    }

    public double[] findTempsXThen(double tempValue, boolean isLess) {
        double [] res = new double[temperatureSeries.length];
        int counter = 0;
        for (double temp : temperatureSeries){
            if (isLess && temp <= tempValue){
                res[counter] = temp;
                counter++;
            }
            else if (!isLess && temp >= tempValue){
                res[counter] = temp;
                counter++;
            }
        }
        double [] final_arr = new double[counter];

        for (int i = 0; i < final_arr.length; i++)
            final_arr[i] = res[i];

        return final_arr;
    }

    public double[] findTempsLessThen(double tempValue) {
        return findTempsXThen(tempValue, true);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return findTempsXThen(tempValue, false);
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length == 0)
            throw new IllegalArgumentException();

        TempSummaryStatistics sum_stat = new TempSummaryStatistics(this.average(), this.deviation(), this.min(), this.max());

        return sum_stat;
    }

    public int addTemps(double... temps) {
        int curr_idx = temperatureSeries.length;
        for (double temp : temps){
            if(curr_idx == temperatureSeries.length) {
                int new_size = temperatureSeries.length*2;
                temperatureSeries = Arrays.copyOf(temperatureSeries, new_size);
            }
            temperatureSeries[curr_idx] = temp;
            curr_idx++;
        }
        return curr_idx;
    }
}
