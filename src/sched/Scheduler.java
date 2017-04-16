package sched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {
	private int waitingTime;
	private int turnaroundTime;
	private int totalWait;
	private int totalTurnaround;

	private Queue<Job> processFCFS;
	private ArrayList<Job> processSJN;
	
	private ArrayList<Job> completed;

	Scheduler(){
		waitingTime = 0;
		turnaroundTime = 0;
		totalTurnaround = 0;
		totalWait = 0;
		processFCFS = new LinkedList<Job>();
		processSJN = new ArrayList<Job>();
		completed = new ArrayList<Job>();
	}

	//first come, first serve
	public void fcfs(){
		while (!processFCFS.isEmpty()) {
			Job j = processFCFS.poll();
			getTurnTime(j);
			getTotalTurn();
			getAverageTurn();
			getWaitTime(j);
			getTotalWait();
			getAverageWait();
			completed.add(j);
			showStats(j);
		}
	}
	
	//fills the queue in order for FCFS
	public void fillSchedulerFCFS(Job j){
		processFCFS.offer(j);
	}
	
		//shortest job next
	public void sjn(){
		while (!processSJN.isEmpty()) {
			Job j = processSJN.remove(0);
			getTurnTime(j);
			getTotalTurn();
			getAverageTurn();
			getWaitTime(j);
			getTotalWait();
			getAverageWait();
			completed.add(j);
			showStats(j);
		}
	}
	
	public void fillSchedulerSJN(Job j){
		if (processSJN.isEmpty()) {
			processSJN.add(j);
		} else {
			for (int i = 0; i < processSJN.size(); i++) {
				if (j.getCycle() < processSJN.get(i).getCycle()) {
					processSJN.add(i, j);
				} else {
					processSJN.add(j);
				}
			}
		}
	}

	public void showStats(Job j){
		System.out.println("Job " + j.getId());
		System.out.println("Turnaround Time: " + getTurnTime(j));
		System.out.println("Wait Time: " + getWaitTime(j));
		System.out.println("Average Turnaround Time: " + getAverageTurn());
		System.out.println("Average Wait Time: " + getAverageWait());
		System.out.println();
		System.out.println();
	}
	
	public void resetStats(){
		waitingTime = 0;
		turnaroundTime = 0;
		totalTurnaround = 0;
		totalWait = 0;
	}
	
	private int getTurnTime(Job j){
		
		return turnaroundTime = j.getCycle() - j.getArrival();
	}
	
	private int getTotalTurn(){
		return totalTurnaround += turnaroundTime;
	}
	
	private double getAverageTurn(){
		return totalTurnaround / (completed.size() + 1);
	}
	
	private int getWaitTime(Job j){
		return waitingTime = turnaroundTime - j.getCycle();
	}
	
	private int getTotalWait(){
		return totalWait += waitingTime;
	}
	
	private double getAverageWait(){
		return totalWait / (completed.size() + 1);
	}

}
