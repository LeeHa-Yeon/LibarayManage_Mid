package middleTermProject;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TestManager {


    public void mainMemu(){
        int select;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------- 회원 관리 memu -----------------");
        System.out.println("\t1. 로그인  2. 회원가입  3. 아이디/비번 찾기  0.종료 ");
        System.out.println("------------------------------------------------");
        System.out.print(" 번호를 선택해주세요 : ");
        select = sc.nextInt();
        switch(select) {
            case 1 :
                System.out.println("로그인");
                break;
            case 2 :
                System.out.println("\n---------- 회원가입 화면 -----------");
                register();
                break;
            case 3 :
                System.out.println("아이디 / 비번 찾기");
            default:
                System.out.println("종료");
                break;
        }
    }

    public void login(){

    }

    public void register(){
        // 아이디 비번 이름 폰번호 주소
        String[] a = {"아이디","비번","비번확인","이름","폰번호","주소"};
        String[] b = new String[a.length];
//        for(int i =0; i<a.length; i++){
//            System.out.println(a[i]);
//        }

    }


}
