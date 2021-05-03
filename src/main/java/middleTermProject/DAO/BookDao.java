package middleTermProject.DAO;

import java.io.IOException;

public interface BookDao {

    // 도서관 관리자만 접근 권한 가지고 있음

    // 책 추가하기
    public void addBook();

    // 책 삭제하기
    public void deleteBook();

    // 고객 도서 요청 확인하기
    public void showApplyList();

    public void changeState(String path,String id) throws IOException;


}
