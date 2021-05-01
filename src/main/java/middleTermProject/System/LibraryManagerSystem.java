package middleTermProject.System;

import middleTermProject.DAO.BookDao;
import middleTermProject.DTO.BookDto;
import middleTermProject.DTO.UserDto;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class LibraryManagerSystem implements BookDao {

    public static BookDto accessedBookDto = null;
    @Override
    public void addBook() {
        System.out.println("----------------------------------------------");

        Scanner sc = new Scanner(System.in);
        String[] bookInput = {"isbn","도서고유번호","책이름","지은이","출판사","카테고리"};
        String[] newBook = new String[bookInput.length];

        for(int i =0; i<bookInput.length; i++){
            System.out.print(bookInput[i]+" 입력 : ");
            newBook[i] = sc.nextLine();
        }

        try {
            String booksPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt";
            BufferedReader br_book = new BufferedReader(new FileReader(booksPwd));
            String bookLine = br_book.readLine();
            List<Integer> isbnList = new ArrayList<Integer>();
            List<Integer> idList = new ArrayList<Integer>();
            List<String> titleList = new ArrayList<String>();

            while ((bookLine = br_book.readLine()) != null) {
                String[] bookSplit = bookLine.split("/");
                isbnList.add(Integer.parseInt(bookSplit[0]));
                idList.add(Integer.parseInt(bookSplit[1]));
                titleList.add(bookSplit[2]);
            }

            int index = isbnList.indexOf(Integer.parseInt(newBook[0]));

            if(idList.contains(Integer.parseInt(newBook[1]))) {
                System.out.println("\n----->  이미 존재하는 도서 번호입니다.");
            }
            else if(isbnList.contains(Integer.parseInt(newBook[0])) && !(titleList.get(index)).equals(newBook[2])) {
                System.out.println("\n-----> "+newBook[0]+"은 "+ titleList.get(index)+"의 isbn번호입니다.");
                System.out.println("-----> 일련번호에 맞는 책을 등록해주세요\n");
            }
            else{
                BufferedWriter bw_books = new BufferedWriter(new FileWriter(booksPwd, true));
                BufferedWriter bw_book = new BufferedWriter(new FileWriter("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/Book_detail/" + newBook[2] + "'s Info.txt", true));

                bw_books.write(String.format("%d/%d/%s/%s/%s/%s/%d/%b/%b", Integer.parseInt(newBook[0]), Integer.parseInt(newBook[1]), newBook[2], newBook[3], newBook[4], newBook[5], 1,false, false));
                bw_book.write(String.format("%d/%d/%s/%s/%s/%s/%d/%b/%b", Integer.parseInt(newBook[0]), Integer.parseInt(newBook[1]), newBook[2], newBook[3], newBook[4], newBook[5], 1, false, false));

                bw_books.newLine();
                bw_book.newLine();

                bw_books.flush();
                bw_book.flush();

                bw_books.close();
                bw_book.close();

                System.out.println("\n----->  책 등록이 성공적으로 이루어졌습니다.");
            }

        }catch(IOException e) {  System.out.println("-----> 등록 실패 "); e.printStackTrace(); }

    }

    @Override
    public void deleteBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n삭제할 책의 이름을 입력해주세요 : ");
        String bookInput = sc.nextLine();

        try {
            String booksPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt";
            String bookPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/Book_detail/" + bookInput + "'s Info.txt";

            BufferedReader br_books = new BufferedReader(new FileReader(booksPwd));
            String bookLine = br_books.readLine();
            List<Integer> isbnList = new ArrayList<Integer>();
            List<Integer> idList = new ArrayList<Integer>();
            List<String> titleList = new ArrayList<String>();
            List<String> titleAllList = new ArrayList<String>();

            while ((bookLine = br_books.readLine()) != null) {
                String[] bookSplit = bookLine.split("/");
                if(bookInput.equals(bookSplit[2])){
                    isbnList.add(Integer.parseInt(bookSplit[0]));
                    idList.add(Integer.parseInt(bookSplit[1]));
                    titleList.add(bookSplit[2]);
                }
                titleAllList.add(bookSplit[2]);
            }
            if(titleAllList.contains(bookInput)){
                HashSet<Integer> temp = new HashSet<Integer>(isbnList);
                ArrayList<Integer> isbnList2 = new ArrayList<Integer>(temp);
                System.out.println("\""+bookInput+"\"의 일련번호 : "+isbnList2);
                System.out.print(bookInput+"의 삭제할 일련번호를 선택해주세요 :");
                String isbnInput = sc.nextLine();

                System.out.print("id를 입력해주세요 : ");
                String idInput = sc.nextLine();

                if(idList.contains(Integer.parseInt(idInput))){
                    System.out.print("일련번호가 "+isbnInput+"인 \""+bookInput+"\"을 정말 삭제하시겠습니까(yes / no)? ");
                    String answer = sc.nextLine();
                    if(answer.equals("yes")) {
                        String tempPath = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/temp.txt";
                        String tempPath2 = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/Book_detail/temp2.text";

                        File originfile = new File(booksPwd);
                        File tempfile = new File(tempPath);

                        File originfile2 = new File(bookPwd);
                        File tempfile2 = new File(tempPath2);
                        try {
                            BufferedReader bL = new BufferedReader(new InputStreamReader(new FileInputStream(originfile)));
                            BufferedWriter wL = new BufferedWriter(new FileWriter(tempPath));
                            BufferedReader bL2 = new BufferedReader(new InputStreamReader(new FileInputStream(originfile2)));
                            BufferedWriter wL2 = new BufferedWriter(new FileWriter(tempPath2));

                            String line = bL.readLine();
                            String line2;

                            wL.write("0/00/책이름/저자/출판사/카테고리/1/대여가능여부/예약중인지\n");
                            while ((line = bL.readLine()) != null) {
                                String[] bookSplit = line.split("/");
                                if(!idInput.equals(bookSplit[1])){
                                    wL.write(line+"\n");
                                }
                                wL.flush();
                            }
                            while ((line2 = bL2.readLine()) != null) {
                                String[] bookSplit2 = line2.split("/");
                                if(!idInput.equals(bookSplit2[1])){
                                    wL2.write(line2+"\n");
                                }
                                wL2.flush();
                            }

                            wL.close();
                            bL.close();
                            originfile.delete();
                            tempfile.renameTo(originfile);

                            wL2.close();
                            bL2.close();
                            originfile2.delete();
                            tempfile2.renameTo(originfile2);

                            System.out.println("------> 해당 책을 삭제하였습니다.");
                        }catch (Exception e) { e.printStackTrace(); }
                    }
                    else{
                        System.out.println("------> 취소되었습니다.");
                    }
                }else{
                    System.out.println("------>  올바르지 않는 id를 입력하였습니다.");
                }

            }else{
                System.out.println("------>  해당 책이 존재하지 않습니다.");
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void updateBook() {
        System.out.println("----> 차후에 구현할 계획");
    }
}
