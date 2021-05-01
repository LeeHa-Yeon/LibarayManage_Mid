package middleTermProject.DAO;


public interface UserDao {

    // 비밀번호 변경 --->  회원 정보 수정도 확장
    public boolean changePwd();

    // 회원 정보 읽기
    public void showProfile();

    // 내가 대여한 목록 보기  -> 구현 예정


}
