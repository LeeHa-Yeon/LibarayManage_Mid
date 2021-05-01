package middleTermProject.Screen;

import middleTermProject.DAO.SystemDao;
import middleTermProject.DTO.UserDto;

public class LibraryUserScreen implements SystemDao {

    public static UserDto accessedUserDto = null;

    @Override
    public void memuPrint() {
/*
System.out.println("현재 로그인 상태 : "+UserManagment.accessedUserDto.getId()+"님");
        System.out.println("------------------------------------------------");
        System.out.println("\t\t\t\t1. 도서관 관리 시스템\n  \t\t\t\t2. 회원 정보 보기\n  \t\t\t\t3. 비밀번호 수정\n  \t\t\t\t0. 로그아웃 ");
        System.out.println("------------------------------------------------");
        System.out.print("\n 번호를 선택해주세요 : ");
        Scanner sc = new Scanner(System.in);
        int select3 = sc.nextInt();
        if (select3 == 1) {
            // 목록보기
            System.out.println("목록보기");
        } else if (select3 == 2) {
            show_profile();
            try {
                System.out.println("5초 뒤에 다시 이전 화면으로 돌아갑니다.\n");
                Thread.sleep(5000);
            }catch (InterruptedException e){
                System.err.format("IOException: %s%n", e);
            }
            loginMemu();
        } else if (select3 == 3) {
            if (pwd_modify()) {
                System.out.println("----->  비밀번호를 변경하였습니다. \n");
            } else {
                System.out.println("----->  비밀번호가 틀렸습니다. \n");
            }
            loginMemu();
        } else {
            UserManagment.accessedUserDto = new UserDto();
            UserManagment.accessedUserDto.setId("로그인을 해주세요");
            UserManagment.accessedUserDto.setPwd("로그인을 해주세요");
            UserManagment.accessedUserDto.setName("로그인을 해주세요");
            UserManagment.accessedUserDto.setPhone("로그인을 해주세요");
            UserManagment.accessedUserDto.setAddress("로그인을 해주세요");
            UserManagment.accessedUserDto.setBorrowed_book(0);
            mainMemu();
        }



*/


    }
}
