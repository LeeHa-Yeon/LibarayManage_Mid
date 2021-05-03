package middleTermProject.DAO;


public interface UserDao {

    // 비밀번호 변경 --->  회원 정보 수정도 확장
    public boolean changePwd();

    // 회원 정보 읽기
    public void showProfile();

    // 수정된 회원 정보 업데이트
    public void updateInfo(String id);

    // 내가 대여한 목록 보기  -> 구현 예정

    // 회원 탈퇴
    public void signout();


}
