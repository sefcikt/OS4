package sched;

public class Job {

	private String id;
	private long arrival;
	private long cycle;
	
	Job(String id, long arrival, long cycle){
		this.id = id;
		this.arrival = arrival;
		this.cycle = cycle;
	}

	public String getId() {
		return id;
	}
	
	public long getArrival() {
		return arrival;
	}
	
	public long setArrival(long l){
		return this.arrival = l;
	}

	public long getCycle() {
		return cycle;
	}
	
	public long setCycle(long cycle){
		return this.cycle = cycle;
	}
	
	public String toString(){
		System.out.println("Job " + getId());
		return "Job " + getId();
	}
}
