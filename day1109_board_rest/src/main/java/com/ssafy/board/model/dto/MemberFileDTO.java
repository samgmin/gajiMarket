package com.ssafy.board.model.dto;

public class MemberFileDTO {
	private int fno;
	private String userid;
	private String originalName;
	private String savedPath;
	private String absolPath;
	public MemberFileDTO() {}
	public MemberFileDTO(String userid, String originalName, String savedPath,String absolPath) {
		super();
		this.userid = userid;
		this.originalName = originalName;
		this.savedPath = savedPath;
		this.absolPath = absolPath;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getSavedPath() {
		return savedPath;
	}
	public void setSavedPath(String savedPath) {
		this.savedPath = savedPath;
	}
	
	public String getAbsolPath() {
		return absolPath;
	}
	public void setAbsolPath(String absolPath) {
		this.absolPath = absolPath;
	}
	@Override
	public String toString() {
		return "MemberFileDTO [fno=" + fno + ", userid=" + userid + ", originalName=" + originalName + ", savedPath="
				+ savedPath + "]";
	}
	
}
