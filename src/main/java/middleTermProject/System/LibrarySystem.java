package middleTermProject.System;

import middleTermProject.DAO.LibraryDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
}
