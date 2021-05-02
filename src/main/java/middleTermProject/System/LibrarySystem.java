package middleTermProject.System;

import middleTermProject.DAO.LibraryDao;
import middleTermProject.DTO.BookDto;
import middleTermProject.Screen.LibraryUserScreen;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LibrarySystem implements LibraryDao {

    @Autowired
    BookSystem bookSystem;

    @Autowired
    LibraryUserScreen libraryUserScreen;

    @Autowired
    UserSystem userSystem;

    @Override
    public void showBookList() {
        try {
            BufferedReader br_book = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt"));
            String bookLine = br_book.readLine();   // 첫번째 라인 날리기

            System.out.println("  도서번호 ㅣ           제목            ㅣ 지은이 ㅣ 출판사 ㅣ   카테고리");

            while ((bookLine = br_book.readLine()) != null) {
                String[] bookSplit = bookLine.split("/");
                System.out.println(" ⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻");
                if(bookSplit[2].length()<8){
                    System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t\t\t\t\t\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                }else if(bookSplit[2].length()>8 && bookSplit[2].length()<13){
                    System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t\t\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                }
                else {
                    System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                }
            }
            System.out.println(" ⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽\n");
        }catch(IOException e) {  System.out.println("-----> 보기 실패 "); e.printStackTrace(); }
    }

    @Override
    public void searchBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색할 도서 제목을 입력해주세요 : ");
        String search_title = sc.nextLine();

        try {
            BufferedReader br_books = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt"));
            String bookLine = br_books.readLine();

            System.out.println("  도서번호 ㅣ           제목            ㅣ 지은이 ㅣ 출판사 ㅣ   카테고리   ㅣ  대여가능여부");

            while ((bookLine = br_books.readLine()) != null) {
                String[] bookSplit = bookLine.split("/");
                if(search_title.equals(bookSplit[2])) {
                    System.out.println(" ⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻");
                    if (bookSplit[2].length() < 8) {
                        System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t\t\t\t\t\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                    } else if (bookSplit[2].length() > 8 && bookSplit[2].length() < 13) {
                        System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t\t\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                    } else {
                        System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                    }
                }
            }
            System.out.println(" ⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽");
            System.out.println("");
        }catch(IOException e) {  System.out.println("-----> 보기 실패 "); e.printStackTrace(); }
    }

    @Override
    public void showBookInfo() {
        Scanner sc = new Scanner(System.in);
        String[] bookSchema = {"isbn","도서번호","책 제목","지은이","출판사","카테고리","재고","대여가능여부","예약상태"};
        System.out.print("해당 책의 자세한 정보를 원하시면 도서번호를 입력해주세요 : ");
        String input_bookId = sc.nextLine();
        System.out.println("\n------------------------------------");
        try {
            BufferedReader br_books = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt"));
            String bookLine = br_books.readLine();

            while ((bookLine = br_books.readLine()) != null) {
                String[] bookSplit = bookLine.split("/");

                if(bookSplit[1].equals(input_bookId)){
                    for(int i =0; i<bookSchema.length; i++){
                        System.out.println(bookSchema[i]+" : "+bookSplit[i]);
                    }
                }
            }
        }catch(IOException e) {  System.out.println("-----> 실패 "); e.printStackTrace(); }
        System.out.println("------------------------------------");
        retrieveInfo(input_bookId);
        System.out.println("-----> 1. 대여 요청하기 \t\t 2. 예약 요청하기 \t\t 3. 뒤로가기 \n");
        System.out.print("-----> 선택해주세요 :");
        int num = sc.nextInt();
        if(num==1){
            lendBook();
        }else if(num==2){
            bookBook();
        }
        else{
            libraryUserScreen.memuPrint();
        }
    }

    @Override
    public void lendBook() {
        System.out.println("\n------------- 대여 진행 중 ---------------");
        String cnt = UserSystem.accessedUserDto.getBorrowedLimit();
        Scanner sc = new Scanner(System.in);

        if(LibraryManagerSystem.accessedBookDto.getIs_book_borrowed().equals("대여가능")){
            // 대여 가능
            System.out.print("이 책을 대여하시겠습니까 (yes/no) ? ");
            String answer = sc.nextLine();
            if(answer.equals("yes")) {
                if(Integer.parseInt(cnt) <= 0){
                    System.out.println("대여 가능한 최대 개수(3개)를 초과하였습니다. 반납 후 이용해주세요");
                    System.out.println("---------------------------------------\n");
                }else{
                    LibraryManagerSystem.accessedBookDto.setIs_book_borrowed("대여불가능");
                    // 유저정보에 대여가능수를 1감소하여 업데이트
                    UserSystem.accessedUserDto.setBorrowedLimit(Integer.toString(Integer.parseInt(cnt)-1));
                    System.out.println("-----> 대여 완료");
                    System.out.println("-----> 앞으로 대여할 수 있는 남은 횟수 : "+UserSystem.accessedUserDto.getBorrowedLimit());
                    System.out.println("---------------------------------------\n");
                    try {
                        bookSystem.updateLendFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt",Integer.toString(LibraryManagerSystem.accessedBookDto.getBook_id()));
                        bookSystem.updateLendFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/Book_detail/" + LibraryManagerSystem.accessedBookDto.getBook_title() + "'s Info.txt",Integer.toString(LibraryManagerSystem.accessedBookDto.getBook_id()));
                        userSystem.updateLendFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt",UserSystem.accessedUserDto.getId());
                        userSystem.updateLendFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + UserSystem.accessedUserDto.getId() + "'s Info.txt",UserSystem.accessedUserDto.getId());
                        Thread.sleep(3000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                libraryUserScreen.memuPrint();
            }
            else{
                System.out.println("\t\t대여를 취소하였습니다.");
                System.out.println("---------------------------------------\n");
                libraryUserScreen.memuPrint();
            }
        }else{
            // 대여 불가능
            System.out.println("\t\t이미 대여중인 도서입니다.");
            System.out.println("---------------------------------------\n");
            libraryUserScreen.memuPrint();
        }
    }

    @Override
    public void returnBook() {
        System.out.println("반납 함수");

    }

    @Override
    public void bookBook() {
        System.out.println("예약 함수");
    }

    @Override
    public void retrieveInfo(String id){
        try {
            BufferedReader br_books = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt"));
            String bookLine = br_books.readLine();

            while ((bookLine = br_books.readLine()) != null) {
                String[] bookSplit = bookLine.split("/");
                if(id.equals(bookSplit[1])){
                    // 3/3/이것은 취업을 위한 코딩테스트이다/동빈나/드림/컴퓨터/1/false/false
                    LibraryManagerSystem.accessedBookDto = new BookDto();
                    LibraryManagerSystem.accessedBookDto.setBook_ISBN(Integer.parseInt(bookSplit[0]));
                    LibraryManagerSystem.accessedBookDto.setBook_id(Integer.parseInt(bookSplit[1]));
                    LibraryManagerSystem.accessedBookDto.setBook_title(bookSplit[2]);
                    LibraryManagerSystem.accessedBookDto.setBook_author(bookSplit[3]);
                    LibraryManagerSystem.accessedBookDto.setBook_publisher(bookSplit[4]);
                    LibraryManagerSystem.accessedBookDto.setBook_category(bookSplit[5]);
                    LibraryManagerSystem.accessedBookDto.setBook_stock(Integer.parseInt(bookSplit[6]));
                    LibraryManagerSystem.accessedBookDto.setIs_book_borrowed(bookSplit[7]);
                    LibraryManagerSystem.accessedBookDto.setIs_book_reservation(Boolean.parseBoolean(bookSplit[8]));
                }
            }
        }catch(IOException e) { e.printStackTrace(); }

    }

}

