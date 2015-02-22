package com.projektarbeit.duplo.pedo;

/**
 * Created by Duplo on 21.01.2015.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;

import java.util.LinkedList;

/**
 * A utility class for some helper methods.
 */
public class Utils {

    private static final int DEFAULT_VIBRATION_DURATION_MS = 200; // in millis
    private static final String PREF_KEY_COUNTER = "counter";

    /**
     * Causes device to vibrate for the given duration (in millis). If duration is set to 0, then it
     * will use the <code>DEFAULT_VIBRATION_DURATION_MS</code>.
     */
    public final static void vibrate(Context context, int duration) {
        if (duration == 0) {
            duration = DEFAULT_VIBRATION_DURATION_MS;
        }
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(duration);
    }

    /**
     * Saves the counter value in the preference storage. If <code>value</code>
     * is negative, then the value will be removed from the preferences.
     */
    public static void saveCounterToPreference(Context context, int value) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        if (value < 0) {
            // we want to remove
            pref.edit().remove(PREF_KEY_COUNTER).apply();
        } else {
            pref.edit().putInt(PREF_KEY_COUNTER, value).apply();
        }
    }

    /**
     * Retrieves the value of counter from preference manager. If no value exists, it will return
     * <code>0</code>.
     */
    public static int getCounterFromPreference(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getInt(PREF_KEY_COUNTER, 0);
    }



    /**
     * Simple Moving Average
     */
    public static class SMA
    {
        private LinkedList values = new LinkedList();

        private int length;

        private float sum = 0;

        private float average = 0;

        /**
         *
         * @param length the maximum length
         */
        public SMA(int length)
        {
            if (length <= 0)
            {
                throw new IllegalArgumentException("length must be greater than zero");
            }
            this.length = length;
        }

        public float currentAverage()
        {
            return average;
        }

        /**
         * Compute the moving average.
         * Synchronised so that no changes in the underlying data is made during calculation.
         * @param value The value
         * @return The average
         */
        public synchronized float compute(float value)
        {
            if (values.size() == length && length > 0)
            {
                sum -= ((Float) values.getFirst()).floatValue();
                values.removeFirst();
            }
            sum += value;
            values.addLast(new Float(value));
            average = sum / values.size();
            return average;
        }
    }



}