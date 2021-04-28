package middleTermProject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Scanner;

@Component
// 호출할때마다 새로운 녀석 만들어야 하므로
@Scope("prototype")
public class User {

    // 문자열을 읽어올 수 있음
    // Scanner의 객체 생성
    // System.in은 입력한 값을 바이트 단위로 읽는
    Scanner sc = new Scanner(System.in);

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





        // 회원 등록
//    public void newUser(String id, String pwd, String name, String phone, String address, Integer borrowed_book ) {
//        try {
//            String userText = "id.text";
//            BufferedWriter user = new BufferedWriter(new FileWriter(userText,true));
//
//            // write 출력
//            user.write(String.format("%s,%s,%s,%s,%s,0",id,pwd,name,phone,address,borrowed_book));
//            // 개행 엔터역할
//            user.newLine();
//
//            // 버퍼에 남아있는 데이터 모두 출력하여 없앰
//            user.flush();
//            // 스트림 닫아주기
//            user.close();
//            System.out.println("회원 가입이 성공했습니다.");
//
//        } catch (IOException e) {
//            System.out.println("회원 가입이 실패하였습니다.");
//            e.printStackTrace(); }
//
//    }




}


