package middleTermProject.DAO;

public interface LibraryDao {

    // 책 목록보기
    public void showBookList();

    // 도서 검색
    public void searchBook();

    // 책 정보보기
    public void showBookInfo();

    // 도서 대여
    public void lendBook();

    // 도서 반납
    public void returnBook();

    // 도서 예약
    public void bookBook();

    // 기타 추가
}
