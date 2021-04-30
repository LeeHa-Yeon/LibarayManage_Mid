package middleTermProject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
// 호출할때마다 새로운 녀석 만들어야 하므로
@Scope("prototype")
public class User {

    private String id;
    private String pwd;
    private String name;
    private String phone;
    private String address;
    // 처음엔 이거 0 으로 초기화
    private int borrowed_book;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public int getBorrowed_book(){
        return borrowed_book;
    }

    public void setBorrowed_book(int borrowed_book){
        this.borrowed_book = borrowed_book;
    }


}


