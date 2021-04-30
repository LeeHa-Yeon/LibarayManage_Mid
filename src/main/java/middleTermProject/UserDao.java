package middleTermProject;


public interface UserDao {

    // 회원가입
    public void register();

    // 로그인
    public Boolean login();

    // 아이디 찾기
    public void find_id();

    // 비밀번호 찾기
    public void find_pwd();

    // 회원 정보 읽기

    // 회원 정보 수정 (우선 비밀번호 변경만 진행)
    public void pwd_modify();

}
