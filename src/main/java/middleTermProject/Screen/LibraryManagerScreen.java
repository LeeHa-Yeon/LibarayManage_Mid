package middleTermProject.Screen;

import middleTermProject.DAO.SystemDao;
import middleTermProject.DTO.BookDto;
import middleTermProject.DTO.UserDto;
import middleTermProject.System.LibraryManagerSystem;
import middleTermProject.System.LoginSystem;
import middleTermProject.System.UserSystem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class LibraryManagerScreen implements SystemDao {

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
        int s = sc.nextInt();

        switch(select) {
            case 1 :
                System.out.println("\n-------------- 도서 관리 시스템 ----------------\n");
                System.out.println("\t\t\t\t1. 새로운 책 추가하기\n  \t\t\t\t2. 책 정보 수정하기\n  \t\t\t\t3. 책 삭제하기\n  \t\t\t\t4. 전체보기 ");
                if(s==1){
                    libraryManagerSystem.addBook();
                }else if(s==2){
                    libraryManagerSystem.updateBook();
                }else if(s==3){
                    libraryManagerSystem.deleteBook();
                }else if(s==4){
                    // 전체보기
                }
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
                System.out.println("\n-------------- 로그아웃 ----------------\n");
                loginSystem.logout();
                break;
        }

    }
}
