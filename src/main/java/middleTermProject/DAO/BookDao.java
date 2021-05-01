package middleTermProject.DAO;

public interface BookDao {

    // 도서관 관리자만 접근 권한 가지고 있음

    // 책 추가하기
    public void addBook();

    // 책 삭제하기
    public void deleteBook();

    // 책 수정하기
    public void updateBook();


}
