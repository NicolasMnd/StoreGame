package util;

import java.util.ArrayList;
import java.util.List;

public class Logger {

    private List<String> log = new ArrayList<>();
    private List<Long> nums = new ArrayList<>();
    private final String logType;

    public Logger(String logType) {
        this.logType = logType;
    }

    public void log(String log) {
        this.log.add(log);
    }

    public void time(long time) {
        this.nums.add(time);
        if(this.nums.size() % 250 == 0) {
            reportTimes();
        }
    }

    private void reportTimes() {
        long sum = 0;
        for(int i = 0; i < nums.size(); i++)
            sum += nums.get(i);
        System.out.println("Average rendering time was " + (sum / nums.size()) + "ns or " + ((sum/nums.size())/1000000) + "ms");
        nums.clear();
    }

    public void report() {
        System.out.println("---------------------------------------------------");
        System.out.println("Log of " + logType + "\n");
        for(String s : log)
            System.out.println("   > " + log);
        System.out.println("\nEnd log");
        System.out.println("---------------------------------------------------");
    }

}
