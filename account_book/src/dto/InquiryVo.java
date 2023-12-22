package dto;

public class InquiryVo {
	//수입
    private int in_number;
    private String in_date;
    private String in_list;
    private int in_amount;
    private String in_memo;
    //지출
    private int out_number;
    private String out_date;
    private String out_list;
    private int out_amount;
    private String out_memo;

    // 수입 시퀀스
    public int getIn_number() {
        return in_number;
    }
    public void setIn_number(int in_number) {
        this.in_number = in_number;
    }
    // 수입 날짜
    public String getIn_date() {
        return in_date;
    }
    public void setIn_date(String in_date) {
        this.in_date = in_date;
    }
    // 수입 목록
    public String getin_list() {
        return in_list;
    }
    public void setin_list(String in_list) {
        this.in_list = in_list;
    }
    // 수입 금액
    public int getIn_amount() {
        return in_amount;
    }
    public void setIn_amount(int in_amount) {
        this.in_amount = in_amount;
    }
    // 수입 상세 내용
    public String getIn_memo() {
        return in_memo;
    }
    public void setIn_memo(String in_memo) {
        this.in_memo = in_memo;
    }
    
    
    // 지출 시퀀스 
    public int getOut_number() {
        return out_number;
    }
    public void setOut_number(int out_number) {
        this.out_number = out_number;
    }
    // 지출 날짜 
    public String getOut_date() {
        return out_date;
    }
    public void setOut_date(String out_date) {
        this.out_date = out_date;
    }
    // 지출 목록 
    public String getout_list() {
        return out_list;
    }
    public void setout_list(String out_list) {
        this.out_list = out_list;
    }
    // 지출 금액 
    public int getOut_amount() {
        return out_amount;
    }
    public void setOut_amount(int out_amount) {
        this.out_amount = out_amount;
    }
    // 지출 상세 내용 
    public String getOut_memo() {
        return out_memo;
    }
    public void setOut_memo(String out_memo) {
        this.out_memo = out_memo;
    }

    @Override
    public String toString() {
        return "InquiryVo [in_number=" + in_number + ", in_date=" + in_date + ", in_list=" + in_list
                + ", in_amount=" + in_amount + ", in_memo=" + in_memo + ", out_number=" + out_number + ", out_date="
                + out_date + ", out_list=" + out_list + ", out_amount=" + out_amount + ", out_memo=" + out_memo
                + "]";
    }

}
