package middleTermProject.DTO;

import java.util.Date;

public class BookDto {
    // 도서번호, 책이름, 저자, 출판사, 카테고리, 책 재고, 대여가능여부, 예약중인지
    private int book_id;
    private int book_ISBN;  // 국제 표준 도서 번호
    private String book_title;  // 책 제목
    private String book_author;  // 저자(지은이)
    private String book_publisher; // 출판사
    private String book_category;   // 카테고리
    private int book_stock;   // 책 재고
    private Date lend_date;   // 빌린날짜
    private Date return_date;  // 반납일자
    private Boolean is_book_borrowed;  // 대여가능여부
    private Boolean is_book_reservation;  // 예약중인지 아닌지

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBook_ISBN(){
        return book_ISBN;
    }

    public void setBook_ISBN(int book_ISBN){
        this.book_ISBN = book_ISBN;
    }

    public String getBook_title(){
        return book_title;
    }

    public void setBook_title(String book_title){
        this.book_title = book_title;
    }

    public String getBook_author(){
        return book_author;
    }

    public void setBook_author(String book_author){
        this.book_author = book_author;
    }

    public String getBook_publisher(){
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher){
        this.book_publisher = book_publisher;
    }

    public String getBook_category(){
        return book_category;
    }

    public void setBook_category(String book_category){
        this.book_category = book_category;
    }

    public int getBook_stock(){
        return book_stock;
    }

    public void setBook_stock(int book_stock){
        this.book_stock = book_stock;
    }

    public Boolean getIs_book_borrowed() {
        return is_book_borrowed;
    }

    public void setIs_book_borrowed(Boolean is_book_borrowed) {
        this.is_book_borrowed = is_book_borrowed;
    }

    public Boolean getIs_book_reservation() {
        return is_book_reservation;
    }

    public void setIs_book_reservation(Boolean is_book_reservation) {
        this.is_book_reservation = is_book_reservation;
    }


    public Date getLend_date() {
        return lend_date;
    }

    public void setLend_date(Date lend_date) {
        this.lend_date = lend_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }
}
