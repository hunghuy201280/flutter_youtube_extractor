package ytextractor.utils;

import android.util.Log;

public class LogUtils {

    public static void log(String x) {
//		Log.d("Naveed AAA", x);

        int maxLogSize = 1000;
        for (int i = 0; i <= x.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = Math.min(end, x.length());
            Log.d("HB", x.substring(start, end));
        }
    }

    public static void log(int x) {
        Log.d("Naveed", String.valueOf(x));
    }

}
