package middleTermProject;

import org.apache.catalina.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserManagment implements UserDao {


//    // 회원 등록
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
//            e.printStackTrace();
//        }
//
//    }

}
