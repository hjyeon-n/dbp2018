package model;

/**
 * 실종동물 관리를 위해 필요한 도메인 클래스. MISSINGANIMAL 테이블과 대응됨
 */
public class Missing {
	private int postId;
	private String clientId;
	private String missingName;
	private String missingType;
	private String missingAddr;
	private String missingDate;
	private String missingDetail;
	private String missingImgPath;

	public Missing() { }		// 기본 생성자
	
	public Missing(int postId, String clientId, String missingName, String missingType, String missingAddr, String missingDate, String missingDetail, String missingImgPath) {
		this.postId = postId;
		this.clientId = clientId;
		this.missingName = missingName;
		this.missingType = missingType;
		this.missingAddr = missingAddr;
		this.missingDate = missingDate;
		this.missingDetail = missingDetail;
		this.missingImgPath = missingImgPath;
	}
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getMissingName() {
		return missingName;
	}

	public void setMissingName(String missingName) {
		this.missingName = missingName;
	}

	public String getMissingType() {
		return missingType;
	}

	public void setMissingType(String missingType) {
		this.missingType = missingType;
	}

	public String getMissingAddr() {
		return missingAddr;
	}

	public void setMissingAddr(String missingAddr) {
		this.missingAddr = missingAddr;
	}

	public String getMissingDate() {
		return missingDate;
	}

	public void setMissingDate(String missingDate) {
		this.missingDate = missingDate;
	}

	public String getMissingDetail() {
		return missingDetail;
	}

	public void setMissingDetail(String missingDetail) {
		this.missingDetail = missingDetail;
	}

	public String getMissingImgPath() {
		return missingImgPath;
	}

	public void setMissingImgPath(String missingImgPath) {
		this.missingImgPath = missingImgPath;
	}
	
	
}
