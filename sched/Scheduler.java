package sched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Scheduler {
	private long waitingTime;
	private long turnaroundTime;
	private long totalWait;
	private long totalTurnaround;
	private long timer = 0;
	private final int rrCycle = 4;
	private int rrTicks = 0;

	private Queue<Job> processFCFS;
	private LinkedList<Job> processSJN;
	private LinkedList<Job> processSRT;
	private LinkedList<Job> processRR;

	private ArrayList<Job> completed;

	Scheduler(){
		waitingTime = 0;
		turnaroundTime = 0;
		totalTurnaround = 0;
		totalWait = 0;
		processFCFS = new LinkedList<Job>();
		processSJN = new LinkedList<Job>();
		processSRT = new LinkedList<Job>();
		processRR = new LinkedList<Job>();
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
			processSJN.addFirst(j);
		} else if (!processSJN.isEmpty()) {
			for (int i = 0; i < processSJN.size(); i++) {
				Job temp = processSJN.get(i);
				if (j.getCycle() < temp.getCycle()) {
					processSJN.add(i, j);
					break;
				}
			}
		} else {
			processSJN.add(j);
		}
	}

		//shortest time remaining
		public void srt(){
			Job j = processSRT.removeFirst();
			while (!processSRT.isEmpty()) {
				if (timer == processSRT.getFirst().getArrival()) {
					j.setArrival(processSRT.getLast().getArrival() + processSRT.getLast().getCycle());
					processSRT.add(j);
					j = processSRT.removeFirst();
				}
				if (j.getCycle() == 0) {
					getTurnTime(j);
					getTotalTurn();
					getAverageTurn();
					getWaitTime(j);
					getTotalWait();
					getAverageWait();
					completed.add(j);
					showStats(j);
					j = processSRT.removeFirst();
				}
				j.setCycle(j.getCycle() - 1);
				timer++;
			}
		}

		//fill shortest remaining time queue
		public void fillSchedulerSRT(Job j){
			if (processSRT.isEmpty()) {
				processSRT.add(j);
			} else if (j.getCycle() > processSRT.getLast().getCycle()){
				processSRT.addLast(j);
			} else {
				for (int i = 0; i < processSRT.size(); i++) {
					if (j.getCycle() < processSRT.get(i).getCycle()) {
						processSRT.add(i, j);
						break;
					} 
				}
			}
		}

		//round robin
		public void RR(){
			Job j = processRR.removeFirst();
			while (!processRR.isEmpty()) {
				if (rrTicks == rrCycle) {
					j.setArrival(processRR.getLast().getArrival() + processRR.getLast().getCycle());
					processRR.add(j);
					j = processRR.removeFirst();
				}
				if (j.getCycle() == 0) {
					getTurnTime(j);
					getTotalTurn();
					getAverageTurn();
					getWaitTime(j);
					getTotalWait();
					getAverageWait();
					completed.add(j);
					showStats(j);
					j = processRR.removeFirst();
				}
				j.setCycle(j.getCycle() - 1);
				rrTicks++;
			}
		}

		//fill round robin
		public void fillSchedulerRR(Job j){
			processRR.addLast(j);
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
			processFCFS.clear();
			processSJN.clear();
			processSRT.clear();
			processRR.clear();
			completed.clear();
		}

		private long getTurnTime(Job j){

			return turnaroundTime = Math.abs(j.getCycle() - j.getArrival());
		}

		private long getTotalTurn(){
			return totalTurnaround += Math.abs(turnaroundTime);
		}

		private double getAverageTurn(){
			return Math.abs(totalTurnaround / (completed.size() + 1));
		}

		private long getWaitTime(Job j){
			return waitingTime = Math.abs(turnaroundTime - j.getCycle());
		}

		private long getTotalWait(){
			return totalWait += Math.abs(waitingTime);
		}

		private double getAverageWait(){
			return Math.abs(totalWait / (completed.size() + 1));
		}

	}
