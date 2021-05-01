package middleTermProject.DTO;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
// 호출할때마다 새로운 녀석 만들어야 하므로
@Scope("prototype")
public class UserDto {

    private String id;
    private String pwd;
    private String name;
    private String phone;
    private String address;
    // 처음엔 이거 0 으로 초기화
    private int borrowedLimit;
    // 빌린 책 -> 책이름, 빌린날짜 ,반납날짜
    private List<BookDto> lendBookList = new ArrayList<BookDto>();


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

    public int getBorrowedLimit(){
        return borrowedLimit;
    }

    public void setBorrowedLimit(int borrowedLimit){
        this.borrowedLimit = borrowedLimit;
    }

    public List<BookDto> getLendBookList() { return lendBookList; }

    public void setLendBookList(List<BookDto> lendBookList) { this.lendBookList = lendBookList; }
}


