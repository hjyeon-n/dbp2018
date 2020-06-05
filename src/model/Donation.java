package model;

public class Donation {
	private String clientID;
	private Animal animalID;
	private int totalDon;
	
	public Donation(String clientID, Animal animalID, int totalDon) {
		super();
		this.clientID = clientID;
		this.animalID = animalID;
		this.totalDon = totalDon;
	}
	
	public Donation(String clientID) {
		super();
		this.clientID = clientID;
	}

	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public Animal getAnimalID() {
		return animalID;
	}
	public void setAnimalID(Animal animalID) {
		this.animalID = animalID;
	}
	public int getTotalDon() {
		return totalDon;
	}
	public void setTotalDon(int totalDon) {
		this.totalDon = totalDon;
	}
	
	@Override
	public String toString() {
		return "Donation [clientID=" + clientID + ", animalID=" + animalID + ", totalDon=" + totalDon + "]";
	}
}
