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

public class LibraryUserScreen implements SystemDao {

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
        public void memuPrint () {
            Scanner sc = new Scanner(System.in);

            System.out.println("현재 로그인 상태 : "+ UserSystem.accessedUserDto.getId()+"님");
            System.out.println("----------------- 도서관 이용자 목록창 -----------------");
            System.out.println("\t\t\t\t1. 도서관 이용하기\n  \t\t\t\t2. 회원 정보 보기\n  \t\t\t\t3. 비밀번호 수정\n  \t\t\t\t4. 회원 탈퇴\n  \t\t\t\t0. 로그아웃 ");
            System.out.println("------------------------------------------------");
            System.out.print("\n 번호를 선택해주세요 : ");
            int select = sc.nextInt();
            int s;

            switch (select) {
                case 1:
                    System.out.println("\n-------------- 도서 이용 시스템 ----------------");
                    System.out.println("\t\t\t1. 전체 도서 목록보기\n  \t\t\t2. 도서 검색하기(찾기)\n  \t\t\t3. 도서 반납하기\n  \t\t\t4. 도서 요청하기 ");
                    System.out.println("----------------------------------------------");
                    System.out.print("\n 번호를 선택해주세요 : ");
                    s = sc.nextInt();
                    if (s == 1) {
                        System.out.println("\n-------------------------- 도서 목록 화면 ---------------------------\n");
                        librarySystem.showBookList();
                        System.out.println("-----> 1. 책 자세히 보기 \t\t 2. 뒤로가기 \n");
                        System.out.print("-----> 선택해주세요 :");
                        int num = sc.nextInt();
                        if(num==1){
                            librarySystem.showBookInfo();
                        }else{
                            memuPrint();
                        }
                    } else if (s == 2) {
                        System.out.println("\n-------------------------- 도서 검색 화면 ---------------------------\n");
                        librarySystem.searchBook();

                        System.out.println("-----> 1. 책 자세히 보기 \t\t 2. 뒤로가기 \n");
                        System.out.print("-----> 선택해주세요 :");
                        int num = sc.nextInt();
                        if(num==1){
                            librarySystem.showBookInfo();
                        }else{
                            memuPrint();
                        }
                    } else if (s == 3) {
                        System.out.println("\n-------------------- 도서 반납하기 ---------------------\n");
                        librarySystem.returnBook();
                    } else if (s == 4) {
                        System.out.println("\n-------------------- 도서 요청하기 ---------------------\n");
                    }
                    break;
                case 2:
                    System.out.println("\n-------------- 회원 정보 보기 ----------------\n");
                    userSystem.showProfile();
                    break;
                case 3:
                    System.out.println("\n-------------- 비밀 번호 수정 ----------------\n");
                    if (userSystem.changePwd()) {
                        System.out.println("---> 비밀번호가 수정되었습니다.");
                    } else {
                        System.out.println("---> 비밀번호가 틀렸습니다.");
                    }
                    memuPrint();
                    break;
                case 4:
                    System.out.println("\n-------------- 회원 탈퇴 창 ----------------\n");
                    userSystem.signout();
                    break;
                default:
                    System.out.println("\n--------> 로그아웃되었습니다.\n");
                    loginSystem.logout();
                    break;
            }

        }
}