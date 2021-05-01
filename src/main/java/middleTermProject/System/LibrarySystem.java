package middleTermProject.System;

import middleTermProject.DAO.LibraryDao;
import middleTermProject.DTO.BookDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LibrarySystem implements LibraryDao {

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
    }

    @Override
    public void lendBook() {

    }

    @Override
    public void returnBook() {

    }

    @Override
    public void bookBook() {

    }
}
