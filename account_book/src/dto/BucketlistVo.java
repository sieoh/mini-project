package dto;

// 클래스의 정보를 DB에 저장 및 로드하위한 매개 클래스
public class BucketlistVo {
	private int bl_number;
	private String bl_name;
	private int target_amount;
	private boolean bl_success;
	
	// 버킷리스트 시퀀스 번호
	public int getBl_number() {
		return bl_number;
	}
	public void setBl_number(int bl_number) {
		this.bl_number = bl_number;
	}
	// 버킷리스트 이름
	public String getBl_name() {
		return bl_name;
	}
	public void setBl_name(String bl_name) {
		this.bl_name = bl_name;
	}
	// 버킷리스트 목표금액
	public int getTarget_amount() {
		return target_amount;
	}
	public void setTarget_amount(int target_amount) {
		this.target_amount = target_amount;
	}
	// 버킷리스트 성공유무
	public boolean isBl_success() {
		return bl_success;
	}
	public void setBl_success(boolean bl_success) {
		this.bl_success = bl_success;
	}
	
	@Override
	public String toString() {
		return "BucketlistVo [bl_number=" + bl_number + ", bl_name=" + bl_name + ", target_amount=" + target_amount
				+ ", bl_success=" + bl_success + "]";
	}
	
	
}
