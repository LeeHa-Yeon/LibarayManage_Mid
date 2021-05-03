package middleTermProject.Screen;

import middleTermProject.DAO.SystemDao;
import middleTermProject.DTO.BookDto;
import middleTermProject.DTO.UserDto;
import middleTermProject.System.LibraryManagerSystem;
import middleTermProject.System.LibrarySystem;
import middleTermProject.System.LoginSystem;
import middleTermProject.System.UserSystem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class LibraryManagerScreen implements SystemDao {

    @Autowired
    LibrarySystem librarySystem;
    @Autowired
    LoginSystem loginSystem;
    @Autowired
    LoginScreen loginScreen;
    @Autowired
    LibraryManagerSystem libraryManagerSystem;
    @Autowired
    UserSystem userSystem;

    public static UserDto accessedUserDto = null;
    public static BookDto accessedBookDto = null;

    @Override
    public void memuPrint() {
        Scanner sc = new Scanner(System.in);

        System.out.println("----------------- 도서관 관리자 목록창 -----------------");
        System.out.println("\t\t\t\t1. 도서관 관리 시스템\n  \t\t\t\t2. 비밀번호 수정\n  \t\t\t\t3. 로그아웃 ");
        System.out.println("------------------------------------------------");
        System.out.print("\n 번호를 선택해주세요 : ");
        int select = sc.nextInt();
        int s;

        switch(select) {
            case 1 :
                System.out.println("\n-------------- 도서 관리 시스템 ----------------");
                System.out.println("\t\t\t1. 새로운 책 추가하기\n  \t\t\t2. 고객도서 요청 리스트 확인하기\n  \t\t\t3. 책 삭제하기\n  \t\t\t4. 전체보기 ");
                System.out.println("----------------------------------------------");
                System.out.print("\n 번호를 선택해주세요 : ");
                s = sc.nextInt();
                if(s==1){
                    libraryManagerSystem.addBook();
                    memuPrint();
                }else if(s==2){
                    System.out.println("\n----- 고객도서 요청 리스트 확인하기");
                    libraryManagerSystem.showApplyList();
                    System.out.print("1. 고객 요청 책 추가  2. 뒤로가기 ");
                    String answer = sc.next();
                    if(answer.equals("1")) {
                        System.out.println("");
                        libraryManagerSystem.addBook();
                    }
                    memuPrint();
                }else if(s==3){
                    libraryManagerSystem.deleteBook();
                    memuPrint();
                }else if(s==4){
                    librarySystem.showBookList();
                    System.out.print("목록창으로 돌아가시려면 yes를 입력해주세요 : ");
                    String answer = sc.next();
                    if(answer.equals("yes")){
                        System.out.println("");
                        memuPrint();
                    }
                }
                break;
            case 2 :
                System.out.println("\n-------------- 비밀 번호 수정 ----------------\n");
                if(userSystem.changePwd()){
                    System.out.println("---> 비밀번호가 수정되었습니다.");
                }else{
                    System.out.println("---> 비밀번호가 틀렸습니다.");
                }
                memuPrint();
                break;
            default :
                System.out.println("\n--------> 로그아웃되었습니다.\n");
                loginSystem.logout();
                break;
        }

    }
}
