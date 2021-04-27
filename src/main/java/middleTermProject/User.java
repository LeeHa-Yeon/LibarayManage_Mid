package middleTermProject;

import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class User {

    Scanner sc = new Scanner(System.in);

    private String id;
    private String pwd;
    private String name;
    private String phone;
    private String address;
    // 처음엔 이거 0 으로 초기화
    private String borrowed_book;


    public String getId() {
            return id;
    }

    public void setId(String id) {
            this.id = id;
    }

}


