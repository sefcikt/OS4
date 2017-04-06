package sched;

public class Job {

	private String id;
	private int arrival;
	private int cycle;
	
	Job(String id, int arrival, int cycle){
		this.id = id;
		this.arrival = arrival;
		this.cycle = cycle;
	}

	public String getId() {
		return id;
	}
	
	public int getArrival() {
		return arrival;
	}

	public int getCycle() {
		return cycle;
	}
	
	public String toString(){
		System.out.println("Job " + getId());
		return "Job " + getId();
	}
}
