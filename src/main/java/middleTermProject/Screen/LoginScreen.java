package middleTermProject.Screen;

import middleTermProject.DAO.SystemDao;
import middleTermProject.DTO.UserDto;
import middleTermProject.System.LibraryManagerSystem;
import middleTermProject.System.LoginSystem;
import middleTermProject.System.UserSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

// 해야할 것 : 회원 정보 읽기 , 회원 정보 수정

@Component
public class LoginScreen implements SystemDao {

    @Autowired
    LoginSystem loginsystem;
    @Autowired
    LibraryManagerScreen libraryManagerScreen;
    @Autowired
    LibraryUserScreen libraryUserScreen;

    public static UserDto accessedUserDto = null;

    @Override
    public void memuPrint(){
        Scanner sc = new Scanner(System.in);

        System.out.println("\n====> 로그인을 해주세요");
        System.out.println("----------------- 회원 관리 memu -----------------");
        System.out.println("\t\t\t\t1. 로그인\n  \t\t\t\t2. 회원가입\n  \t\t\t\t3. 아이디/비번 찾기\n  \t\t\t\t0. 종료 ");
        System.out.println("------------------------------------------------\n");
        System.out.print("번호를 선택해주세요 : ");
        int select = sc.nextInt();
        int s;
        String exit;

        switch (select) {
            case 1:
                System.out.println("\n---------- 로그인 화면 -----------\n");
                if (loginsystem.login()) {
                    // 관리자인지 사용자인지에 따라 화면이 달라짐
                    if(!UserSystem.accessedUserDto.getId().equals("manager")) {
                        System.out.println("사용자인 경우 도서관 이용 시스템으로 이동");
                        libraryUserScreen.memuPrint();
                        // 사용자인 경우 화면
                    }else{
                        System.out.println(UserSystem.accessedUserDto.getId()+"님이 입장하셨습니다.");
                        libraryManagerScreen.memuPrint();
                        // 관리자인 경우 화면
                    }
                }
                else {  memuPrint(); }
                break;
            case 2:
                System.out.println("\n---------- 회원가입 화면 -----------\n");
                loginsystem.register();
                break;
            case 3:
                // 무조건 아이디비번 찾은 후에는 memuPrint로 이동
                System.out.println("\n---------- 아이디 비번 찾기 -----------\n");
                System.out.print("1. 아이디 / 2.비번 찾기 : ");
                s = sc.nextInt();
                if (s == 1) {
                    System.out.println("\n--------- 아이디 찾기 --------------");
                    loginsystem.searchId();
                } else {
                    System.out.println("\n-----------비밀번호 찾기------------");
                    loginsystem.searchPwd();
                }
                break;
            default:
                System.out.print("시스템을 정말 종료하시겠습니까?(yes or no) ? ");
                exit = sc.next();
                if(exit.equals("yes")){
                    System.out.println("종료");
                }else { memuPrint(); }
                break;
        } // switch
    }

}
