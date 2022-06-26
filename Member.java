
import java.util.ArrayList;


public class Member {

	String memberPhone; 
	String first_name, last_name, address;
	int pwd;
	String username;
	
	
	
	
	int moviesCount = 0;
	ArrayList<String> moviesBorrowed = new ArrayList<>();

	public Member(String first_name, String last_name, String address, String memberPhone, int pwd) {
		// TODO Auto-generated constructor stub
		this.memberPhone = memberPhone;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.memberPhone = memberPhone;
		this.pwd = pwd;
		this.username = last_name + first_name;
	}
	
	public String getContact() {
		return memberPhone;
	}

}
