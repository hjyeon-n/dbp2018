package model;

public class Season {
	private String clientId;
	private int seasonDon;
	private int seasonId;
	
	public Season() {
		super();
	}

	public Season(String clientId, int seasonDon, int seasonId) {
		super();
		this.clientId = clientId;
		this.seasonDon = seasonDon;
		this.seasonId = seasonId;
	}
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public int getSeasonDon() {
		return seasonDon;
	}
	public void setSeasonDon(int seasonDon) {
		this.seasonDon = seasonDon;
	}
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}

}
