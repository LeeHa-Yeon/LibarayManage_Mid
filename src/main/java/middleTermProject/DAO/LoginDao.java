package middleTermProject.DAO;

public interface LoginDao {

    // 회원가입
    public void register();

    // 로그인
    public Boolean login();

    // 아이디 찾기
    public void searchId();

    // 비밀번호 찾기
    public void searchPwd();

}
