package com.javiermarsicano.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


// [start_time, end_time]
// Input: list of overlapping meetings
// Output: list of merged and non-overlapping meetings.

// E.g.
// [[1,3], [2,4]] => [[1,4]]
// [[1,3], [7,9], [2,4]] => [[1,4], [7,9]]

public class MeetingsMerge {

	static class Meeting {
		int startTime;
		int endTime;
		
		public Meeting() {}
		
		public Meeting(int startTime, int endTime) {
			super();
			this.startTime = startTime;
			this.endTime = endTime;
		}		
	}
	
	static Meeting[] mergeMeetings(Meeting[] in) {
		ArrayList<Meeting> output = new ArrayList<Meeting>();

		if (in.length == 0)
			return new Meeting[0];

		//sort by start time
		Meeting[] input = Arrays.stream(in)
				.sorted((m1, m2) -> m1.startTime - m2.startTime)
				.toArray(Meeting[]::new);

		Meeting mergedMeeting = new Meeting(input[0].startTime, input[0].endTime);
		output.add(mergedMeeting);

		for (Meeting currentMeeting : input) {
			if (currentMeeting.startTime > mergedMeeting.endTime) {
				mergedMeeting = new Meeting(currentMeeting.startTime, currentMeeting.endTime);
				output.add(mergedMeeting);
			}
			
			if (currentMeeting.endTime > mergedMeeting.endTime) {
				mergedMeeting.endTime = currentMeeting.endTime;
			}    
		}

		Meeting[] array = new Meeting[output.size()];
		return output.toArray(array);
	}



	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        Meeting[] meetings = new Meeting[n]; 
        for(int a0 = 0; a0 < n; a0++){
            int start = in.nextInt();
            int end = in.nextInt();
            Meeting meeting = new Meeting(start,end);
            
            meetings[a0] = meeting;
        }
        
        Meeting[] array = mergeMeetings(meetings);  
        for (Meeting meeting : array) {
        	System.out.println(meeting.startTime + "," + meeting.endTime);
        }
	}
	
	/*Test case*/
	/*
	 6
	  
	1 3
	2 4
	7 11
	8 9
	12 15
	15 16
	*/
}

