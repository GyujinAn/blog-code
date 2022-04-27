package designpatterns.decorator01;

/**
 * @author agj017@gmail.com
 * @since 2022/04/27
 */
public class Member {

    String memberName;

    String gender;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
