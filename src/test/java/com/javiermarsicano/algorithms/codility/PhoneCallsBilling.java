package com.javiermarsicano.algorithms.codility;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 Your monthly phone bill has just arrived, and is unexpectedly large. You decide to venrify the amount by recalculating the bill
 based on your phone cell logs and the phone company's charges.
 The logs are given as a string S consisting of N lines separated by end-of-line characters (ASCII code 10). Each line describes
 one phone call using the following format: " hh:mm:ss,nnn-nnn-nnn", where "hh:mm:ss" denotes the duration of the call and "nnn-nnn-nnn"
 denotes the 9-digit phone number of the recipient (with no leading zeros). Each call is billed separately. The billing rules are
 as follows:
 • If the call was shorter than 5 minutes, then you pay 3 cents for every started second of the cell (e.g. for duration "00:01:07"
 you pay 67*3 = 201 cents).
 • If the call was at least 5 minutes long, then you pay 150 cents for every started minute of the call (e.g. for duration
 "00:05:00' you pay 5*150 = 750 cents and for duration "00:05:01" you pay 6*150 = 900 cents).
 • All cells to the phone number that has the longest total duration of cells are free. In the case of a tie, if more than one phone
 number shares the longest total duration, the promotion is applied only to the phone number whose numerical value is the smallest
 among these phone numbers.

 Write a function:
 class Solution { public int solution(String S); }
 that given a string S describing phone call logs, returns the amount of money you have to pay in cents.
 For example, given string S with N = 3 lines:
 "00:01:07,400-234-090
 00:05:01,701-080-080
 00:05:00,400-234-090"
 the function should return 900 (the total duration for number 400-234-090 is 6 minutes 7 seconds, and the total duration for number
 701-080-080 is 5 minutes 1 second; therefore, the free promotion applies to the former phone number).
 Assume that:
 • N is an integer within the range [1..100];
 • every phone number follows the format "nnn-nnn-nnn" strictly, there are no leading zeros;
 • the duration of every call follows the format "hh:mm:ss strictly (00 <= hh  <= 99, 00 <= mm, ss <= 99);
 • each line follows the format " hh:mm:ss,nnn-nnn-nnn" strictly, there are no empty lines and spaces.
 In your solution, focus on correctness. The performance of your solution will not be the focus.
 */
public class PhoneCallsBilling {

    @Test
    public void test_basic_case() {
        String logs = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090";
        assertEquals(900, Solution.find(logs));
    }

    @Test
    public void test_complex_case() {
        String logs = "00:00:01,400-234-090\n00:05:00,400-234-098";
        assertEquals(3, Solution.find(logs));
    }

    @Test
    public void test_complex_case2() {
        String logs = "00:00:00,400-234-090\n00:00:00,400-234-098";
        assertEquals(0, Solution.find(logs));
    }

    public static class Solution {
        static int find(String S) {
            String[] logs = S.split("\n");

            ArrayList<CallLog> callLogs = new ArrayList<>();

            for (String l :logs){
                callLogs.add(new CallLog(l));
            }

            callLogs.sort((callLog, t1) -> callLog.phoneNumber - t1.phoneNumber);
            callLogs.forEach(callLog -> callLog.billing = Rule.apply(callLog));


            int out = 0;
            for (int i = 0; i< callLogs.size() -1 ;i++) {
                if (callLogs.get(i).phoneNumber == callLogs.get(i+1).phoneNumber) {
                    callLogs.get(i).billing += callLogs.get(i+1).billing;
                    callLogs.remove(i+1);
                }
            }

            callLogs.sort((callLog, t1) -> callLog.billing - t1.billing); //FALTO
            callLogs.remove(callLogs.size() - 1); //ESTABA ANTES DEL BUCLE


            for (CallLog callLog : callLogs) {
                out += callLog.billing;
            }

            return out;
        }

        static class CallLog {
            int hours;
            int minutes;
            int seconds;

            int phoneNumber;

            int billing;

            CallLog(String log) {
                String[] entry = log.split(",");
                String[] time = entry[0].split(":");
                hours = Integer.valueOf(time[0]);
                minutes = Integer.valueOf(time[1]);
                seconds = Integer.valueOf(time[2]);
                phoneNumber = Integer.valueOf(entry[1].replace("-",""));
            }

            @Override
            public String toString() {
                return " "+hours+" "+minutes+" "+seconds+" "+phoneNumber+" "+billing;
            }
        }

        static class Rule {
            static final int MINUTES_IN_HOUR = 60;

            //returns the billing amount in cents
            static int apply(CallLog log) {
                if (log.minutes >= 5) {
                    int startedMinute = log.seconds > 0 ? 1 : 0;
                    return (log.hours * MINUTES_IN_HOUR + log.minutes + startedMinute) * 150;
                } else {
                    return (log.minutes * MINUTES_IN_HOUR + log.seconds) * 3;
                }
            }
        }
    }
}
