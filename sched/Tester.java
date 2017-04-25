package sched;

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		ArrayList<Job> jobArray = new ArrayList<Job>();
		
		Job j1 = new Job("A", 0, 16);
		Job j2 = new Job("B", 3, 2);
		Job j3 = new Job("C", 5, 11);
		Job j4 = new Job("D", 9, 6);
		Job j5 = new Job("E", 10, 1);
		Job j6 = new Job("F", 12, 9);
		Job j7 = new Job("G", 14, 4);
		Job j8 = new Job("H", 16, 14);
		Job j9 = new Job("I", 17, 1);
		Job j10 = new Job("J", 19, 8);
		
		jobArray.add(j1);
		jobArray.add(j2);
		jobArray.add(j3);
		jobArray.add(j4);
		jobArray.add(j5);
		jobArray.add(j6);
		jobArray.add(j7);
		jobArray.add(j8);
		jobArray.add(j9);
		jobArray.add(j10);
		
		Scheduler s = new Scheduler();
		
		System.out.println("Welcome to the Job Cycler");
		
		System.out.println("First Come, First Served: ");
		System.out.println();
		
		for (int i = 0; i < jobArray.size(); i++) {
			s.fillSchedulerFCFS(jobArray.get(i));
		}
		
		s.fcfs();
		s.resetStats();
		
		System.out.println("Shortest Job Next: ");
		System.out.println();
		for (int i = 0; i < jobArray.size(); i++) {
			s.fillSchedulerSJN(jobArray.get(i));
		}
		
		s.sjn();
		s.resetStats();
		
		System.out.println("Shortest Remaining Time: ");
		System.out.println();
		for (int i = 0; i < jobArray.size(); i++) {
			s.fillSchedulerSRT(jobArray.get(i));
		}		
		
		s.srt();
		s.resetStats();
		
		System.out.println("Round Robin: ");
		System.out.println();
		for (int i = 0; i < jobArray.size(); i++) {
			s.fillSchedulerRR(jobArray.get(i));
		}
		
		s.RR();
		s.resetStats();
	}

}
