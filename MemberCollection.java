import java.util.ArrayList;

public class MemberCollection {

	public Member[] members;
	public int count = 0;
	public MemberCollection() {
		members  = new Member[100];
	}
	
	public void addMember(Member m) {

		members[count]= m;
		count++;
	}
	
	public Member returnMember(int location) {
		return members[location];
	}
	
	public int findMemberIndex(String memberName) {
		try {
			int location = -1;
			for (int i = 0; i < members.length; i++) {
				if (members[i].username.equals(memberName)) {
					location = i;
					return location;
				}
			}
			return location;
		}catch(NullPointerException npe) {
			return -1;
		}
		
	}
	
	public String findPhoneNum(String name) {
		int location = findMemberIndex(name);
		if (location != -1) {
			return members[location].getContact();
		}
		return null;
	}
	
	

}
