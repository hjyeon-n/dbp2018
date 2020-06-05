package model;

public class AdoptionCare {
	private String startCare;
	private String careTerm;
	private String clientID;
	private int animalID;
	private String state;
	
	public AdoptionCare(String clientID, int animalID, String careTerm, String state) {
		super();
		this.clientID = clientID;
		this.animalID = animalID;
		this.careTerm = careTerm;
		this.state = state;
	}

	public AdoptionCare(String clientID, int animalID, String startCare, String careTerm, String state) {
		super();
		this.clientID = clientID;
		this.animalID = animalID;
		this.startCare = startCare;
		this.careTerm = careTerm;
		this.state = state;
	}
	
	public String getStartCare() {
		return startCare;
	}
	public void setStartCare(String startCare) {
		this.startCare = startCare;
	}
	public String getCareTerm() {
		return careTerm;
	}
	public void setCareTerm(String careTerm) {
		this.careTerm = careTerm;
	}
	public String getClientID() {
		Client client = new Client();
		return client.getClientID();
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public int getAnimalID() {
		Animal animal = new Animal();
		return animal.getAnimalID();
	}
	public void setAnimalID(int animalID) {
		this.animalID = animalID;
	}
	public int getState() {
		Animal animal = new Animal();
		return animal.getState();
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
