package controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UITrackUtils {

    private static final int SECONDS_IN_HOUR = 3600;
    private static final int SECONDS_IN_MINUTE = 60;

    /**
     * Creates a string representation of time.
     * Hours, minutes and seconds will be separated with ':'
     * If duration is too short hours and minutes will not appear.
     * @param time time in seconds
     * @return string representation of time
     */
    public static String intTimeToString(int time){
        StringBuilder str = new StringBuilder();

        if (time >= SECONDS_IN_HOUR) {
            str.append(time / SECONDS_IN_HOUR).append(':');
            time %= SECONDS_IN_HOUR;
        }
        if (time >= SECONDS_IN_MINUTE) {
            str.append(time / SECONDS_IN_MINUTE).append(':');
            time %= SECONDS_IN_MINUTE;
        }
        if (time < 10) {
            str.append(0).append(time);
        } else {
            str.append(time);
        }

        return str.toString();
    }

    /**
     * Parses the amount of time in seconds from its string representation.
     * Hours minutes and seconds must be in that order and separated with ':'
     * @param time string representation of time
     * @return time in seconds
     */
    public static int stringTimeToInt(String time){
        String[] splitTime = time.split(":");

        if (splitTime.length == 3) {
            return (Integer.parseInt(splitTime[0]) * SECONDS_IN_HOUR) + (Integer.parseInt(splitTime[1]) * SECONDS_IN_MINUTE) + Integer.parseInt(splitTime[2]);
        } else if (splitTime.length == 2) {
            return (Integer.parseInt(splitTime[0]) * SECONDS_IN_MINUTE) + Integer.parseInt(splitTime[1]);
        } else if (splitTime.length == 1) {
            return Integer.parseInt(splitTime[0]);
        } else {
            // ??? исключение кидать ???
            return 0;
        }
    }

    /**
     * Transforms a list into a string with items separated by a chosen separator.
     * @param list list to convert
     * @param separator separator to use
     * @return string representation of a list
     */
    public static String listToString(List<String> list, String separator) {
        if (list.isEmpty()) return "unknown";
        else {
            StringBuilder str = new StringBuilder();

            for (int i = 0; i < list.size() - 1; i++) {
                str.append(list.get(i)).append(separator);
            }
            str.append(list.get(list.size() - 1));

            return str.toString();
        }
    }

    /**
     * Splits a string representation of a list back into a list.
     * @param string string representation of a list
     * @param separator separator used to create string
     * @return original list
     */
    public static List<String> stringToList(String string, String separator) {
        List<String> res = new ArrayList<>();

        if (string.contentEquals("unknown")) return res;
        else {
            String[] arr = string.split(separator);
            res.addAll(Arrays.asList(arr));
            return res;
        }
    }
}
