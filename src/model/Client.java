package model;

public class Client {
	private String clientID;
	private String clientPW;
	private String clientName;
	private String clientGrade;
	private int clientDon;
	private String clientAccount;
	private String clientTel;
	private String clientAddr;
	private String isBlackList;
	private int donTerm;
	private String animalName;
	private int state;
	
	public Client() {};	
	
	
	public Client(String clientID, String clientName) {
		super();
		this.clientID = clientID;
		this.clientName = clientName;
	}


	public Client(String clientID, String clientPW, String clientName, String clientTel, String clientAddr, int clientDon, String clientAccount
			) {
		super();
		this.clientID = clientID;
		this.clientPW = clientPW;
		this.clientName = clientName;
		this.clientDon = clientDon;
		this.clientAccount = clientAccount;
		this.clientTel = clientTel;
		this.clientAddr = clientAddr;
	}


	public Client(String clientName, String clientGrade, String clientTel, String clientAddr, String animalName,
			int state) {
		super();
		this.clientName = clientName;
		this.clientGrade = clientGrade;
		this.clientTel = clientTel;
		this.clientAddr = clientAddr;
		this.animalName = animalName;
		this.state = state;
	}

	public Client(String clientID) {
		this.clientID = clientID;
		Animal animal = new Animal();
		animalName = animal.getAnimalName();
		state = animal.getState();
	}
	
	public Client(String clientID, String clientPW, String clientName, String clientGrade, int clientDon,
			String clientAccount, String clientTel, String clientAddr) {
		super();
		this.clientID = clientID;
		this.clientPW = clientPW;
		this.clientName = clientName;
		this.clientGrade = clientGrade;
		this.clientDon = clientDon;
		this.clientAccount = clientAccount;
		this.clientTel = clientTel;
		this.clientAddr = clientAddr;
	}
	
	public Client(String clientID, String clientPW, String clientName, int clientDon,
			String clientAccount, String clientTel, String clientAddr) {
		super();
		this.clientID = clientID;
		this.clientPW = clientPW;
		this.clientName = clientName;
		this.clientDon = clientDon;
		this.clientAccount = clientAccount;
		this.clientTel = clientTel;
		this.clientAddr = clientAddr;
	}

	public void update(Client updateClient) {
		this.clientID = updateClient.clientID;
		this.clientPW = updateClient.clientPW;
		this.clientName =updateClient.clientName;
		this.clientGrade = updateClient.clientGrade;
		this.clientDon = updateClient.clientDon;
		this.clientAccount = updateClient.clientAccount;
		this.clientTel = updateClient.clientTel;
		this.clientAddr = updateClient.clientAddr;
		this.isBlackList = updateClient.isBlackList;
		this.donTerm = updateClient.donTerm;
	}

	public String getAnimalName() {
		Animal animal = new Animal();
		return animal.getAnimalName();
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getClientPW() {
		return clientPW;
	}

	public void setClientPW(String clientPW) {
		this.clientPW = clientPW;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientGrade() {
		return clientGrade;
	}

	public void setClientGrade(String clientGrade) {
		this.clientGrade = clientGrade;
	}

	public int getClientDon() {
		return clientDon;
	}

	public void setClientDon(int clientDon) {
		this.clientDon = clientDon;
	}

	public String getClientAccount() {
		return clientAccount;
	}

	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}

	public String getClientTel() {
		return clientTel;
	}

	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}

	public String getClientAddr() {
		return clientAddr;
	}

	public void setClientAddr(String clientAddr) {
		this.clientAddr = clientAddr;
	}

	public String getIsBlackList() {
		return isBlackList;
	}

	public void setIsBlackList(String isBlackList) {
		this.isBlackList = isBlackList;
	}

	public int getDonTerm() {
		return donTerm;
	}

	public void setDonTerm(int donTerm) {
		this.donTerm = donTerm;
	}
	
	
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.clientPW.equals(password);
	}
	
	public boolean isSameUser(String id) {
        return this.clientID.equals(id);
    }

	@Override
	public String toString() {
		return "Client [clientID=" + clientID + ", clientPW=" + clientPW + ", clientName=" + clientName
				+ ", clientGrade=" + clientGrade + ", clientDon=" + clientDon + ", clientAccount=" + clientAccount
				+ ", clientTel=" + clientTel + ", clientAddr=" + clientAddr + ", isBlackList=" + isBlackList
				+ ", donTerm=" + donTerm + "]";
	}

}
