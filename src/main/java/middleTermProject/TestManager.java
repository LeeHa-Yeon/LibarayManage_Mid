package middleTermProject;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class TestManager {


    public void mainMemu(){
        int select;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------- 회원 관리 memu -----------------");
        System.out.println("\t1. 로그인  2. 회원가입  3. 아이디/비번 찾기  0.종료 ");
        System.out.println("------------------------------------------------");
        System.out.print("\n 번호를 선택해주세요 : ");
        select = sc.nextInt();
        switch(select) {
            case 1 :
                System.out.println("\n---------- 로그인 화면 -----------\n");
                //login();
                break;
            case 2 :
                System.out.println("\n---------- 회원가입 화면 -----------\n");
                register();
                break;
            case 3 :
                System.out.println("아이디 / 비번 찾기");
            default:
                System.out.println("종료");
                break;
        }
    }

    public void register(){

        User user = new User();
        Scanner sc = new Scanner(System.in);
        String[] userInput = {"아이디","비번","비번확인","이름","폰번호","주소"};
        String[] newUser = new String[userInput.length];

        for(int i =0; i<userInput.length; i++){
            System.out.print(userInput[i]+" 입력 : ");
            newUser[i] = sc.next();
        }

        if (newUser[1].equals(newUser[2])) {
            user.setId(newUser[0]);
            user.setPwd(newUser[1]);
            user.setName(newUser[3]);
            user.setPhone(newUser[4]);
            user.setAddress(newUser[5]);

            try {
                String usersPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/UserInfo/allUserInfo.txt";
                BufferedWriter users_w = new BufferedWriter(new FileWriter(usersPwd, true));
                String profilePwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/UserInfo/Profile/" + user.getId() + "'s Info.txt";
                BufferedWriter profile = new BufferedWriter(new FileWriter(profilePwd, true));

                users_w.write(String.format("%s/%s/%s/%s/%s",user.getId(),user.getPwd(),user.getName(),user.getPhone(),user.getAddress()));
                profile.write(String.format("%s/%s/%s/%s/%s", user.getId(),user.getPwd(),user.getName(),user.getPhone(),user.getAddress()));
                users_w.flush();
                profile.flush();
                users_w.close();
                profile.close();

                System.out.println("-----> 회원 정보 등록 성공 ");
            } catch(IOException e) {  System.out.println("-----> 등록 실패 "); e.printStackTrace(); }

            System.out.println("\n----->  회원가입이 성공적으로 이루어졌습니다.");
            System.out.println("-----------------------------------");
        }
        else {
            System.out.println("\n-----> 비밀번호가 일치하지않습니다. 다시 입력해주세요 ");
            System.out.println("\n---------- 회원가입 화면 -----------\n");
            register();
        }
    }
}
