package model;

public class Animal {
	private int animalID;
	private String animalName;
	private String type;
	private String age;
	private String sex;
	private String detail;
	private String enterDate;
	private int state;
	private int shelterID;
	
	public Animal(int animalID, String animalName, String type, String age, String sex, String detail, String enterDate,
			int state, int shelterID) {
		super();
		this.animalID = animalID;
		this.animalName = animalName;
		this.type = type;
		this.age = age;
		this.sex = sex;
		this.detail = detail;
		this.enterDate = enterDate;
		this.state = state;
		this.shelterID = shelterID;
	}
	public Animal() {
		super();
	}
	
	public Animal(int animalID, String animalName, String type, String age, String sex) {
		super();
		this.animalID = animalID;
		this.animalName = animalName;
		this.type = type;
		this.age = age;
		this.sex = sex;
	}

	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getAnimalID() {
		return animalID;
	}
	public void setAnimalID(int animalID) {
		this.animalID = animalID;
	}
	public String getAnimalName() {
		return animalName;
	}
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}
	public int getShelterID() {
		return shelterID;
	}
	public void setShelterID(int shelterID) {
		this.shelterID = shelterID;
	}
	
	@Override
	public String toString() {
		return "Animal [animalID=" + animalID + ", animalName=" + animalName + ", type=" + type + ", age=" + age
				+ ", sex=" + sex + ", detail=" + detail + ", enterDate=" + enterDate + ", shelterID=" + shelterID + "]";
	}

}