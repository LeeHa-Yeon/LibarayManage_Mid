package middleTermProject.Zdelete;

import middleTermProject.DTO.BookDto;

import java.io.*;
import java.util.*;

public class BookManagment {

    public static BookDto accessedBookDto = null;

    public void bookMemu(){
        int select;

        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        System.out.println("----------------- Book 관리 목록창 -----------------");
        System.out.println("\t\t\t\t1. 도서 목록보기\n  \t\t\t\t2. 도서 검색하기\n  \t\t\t\t3. 책 추가하기\n  \t\t\t\t4. 책 수정하기\n  \t\t\t\t5. 책 삭제하기\n  \t\t\t\t0. 종료 ");
        System.out.println("------------------------------------------------");
        System.out.print("\n 번호를 선택해주세요 : ");
        select = sc.nextInt();
        switch(select) {
            case 1 :

                // 책 정보 보기 추가하기
                System.out.println("\n-------------------------- 책 목록 화면 ---------------------------\n");
                showBookList();
                System.out.print("목록창으로 돌아가시려면 yes를 입력해주세요 : ");
                String answer = sc1.nextLine();
                if(answer.equals("yes")){
                    System.out.println("");
                    bookMemu();
                }
                break;
            case 2 :
                System.out.println("\n---------- 책 정보보기 -----------\n");
                search_Book();
                bookMemu();
                break;
            case 3 :
                System.out.println("\n---------- 책 추가하기 -----------\n");
                addBook();
                bookMemu();
                break;
            case 4 :
                System.out.println("\n---------- 책 수정하기 -----------\n");
                showBookInfo();
                //updateBook();
                bookMemu();
                break;
            case 5 :
                System.out.println("\n---------- 책 삭제하기 -----------\n");
                deleteBook();
                bookMemu();
                break;
            default:
                System.out.println("종료");
                break;
        }
    }

    public void showBookList(){
        try {
            String booksPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt";
            BufferedReader booksList = new BufferedReader(new FileReader(booksPwd));
            String bookList = booksList.readLine();

            System.out.println("  도서번호 ㅣ           제목            ㅣ 지은이 ㅣ 출판사 ㅣ   카테고리");

            while ((bookList = booksList.readLine()) != null) {
                String[] bookSplit = bookList.split("/");
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
            System.out.println(" ⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽");
            System.out.println("");



        }catch(IOException e) {  System.out.println("-----> 보기 실패 "); e.printStackTrace(); }


    }

    public void search_Book(){
        BookDto bookDTO = new BookDto();
        Scanner sc = new Scanner(System.in);
        System.out.print("검색할 도서 제목을 입력해주세요 : ");
        String search_title = sc.nextLine();

        try {
            String booksPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt";
            BufferedReader booksList = new BufferedReader(new FileReader(booksPwd));
            String bookList = booksList.readLine();

            System.out.println("  도서번호 ㅣ           제목            ㅣ 지은이 ㅣ 출판사 ㅣ   카테고리");

            while ((bookList = booksList.readLine()) != null) {
                String[] bookSplit = bookList.split("/");
                if(search_title.equals(bookSplit[2])) {
                    System.out.println(" ⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻");
                    if (bookSplit[2].length() < 8) {
                        System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t\t\t\t\t\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                    } else if (bookSplit[2].length() > 8 && bookSplit[2].length() < 13) {
                        System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t\t\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                    } else {
                        System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                    }
                }
            }
            System.out.println(" ⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽");
            System.out.println("");


        }catch(IOException e) {  System.out.println("-----> 보기 실패 "); e.printStackTrace(); }

    }
    public void showBookInfo() {
        BookDto bookDTO = new BookDto();
        Scanner sc = new Scanner(System.in);
        String[] bookSchema = {"isbn","도서번호","책 제목","지은이","출판사","카테고리","재고","대여가능여부","예약상태"};
        System.out.print("해당 책의 자세한 정보를 원하시면 도서번호를 입력해주세요 : ");
        String input_bookId = sc.nextLine();


        try {
            String booksPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt";
            BufferedReader booksList = new BufferedReader(new FileReader(booksPwd));
            String bookList = booksList.readLine();

            while ((bookList = booksList.readLine()) != null) {
                String[] bookSplit = bookList.split("/");

                if(bookSplit[1].equals(input_bookId)){
                    for(int i =0; i<bookSchema.length; i++){
                        System.out.println(bookSchema[i]+" : "+bookSplit[i]);

                    }

                }

            }

        }catch(IOException e) {  System.out.println("-----> 실패 "); e.printStackTrace(); }


    }


    // 도서아이디, 도서번호, 책이름, 저자, 출판사, 카테고리, 책 재고, 대여가능여부, 예약중인지
    public void addBook(){

        BookDto bookDTO = new BookDto();

        Scanner sc = new Scanner(System.in);
        String[] bookInput = {"isbn","도서고유번호","책이름","지은이","출판사","카테고리"};
        String[] newBook = new String[bookInput.length];

        for(int i =0; i<bookInput.length; i++){
            System.out.print(bookInput[i]+" 입력 : ");
            newBook[i] = sc.nextLine();
        }
        bookDTO.setBook_ISBN(Integer.parseInt(newBook[0]));
        bookDTO.setBook_id(Integer.parseInt(newBook[1]));
        bookDTO.setBook_title(newBook[2]);
        bookDTO.setBook_author(newBook[3]);
        bookDTO.setBook_publisher(newBook[4]);
        bookDTO.setBook_stock(1);
        bookDTO.setBook_category(newBook[5]);
        bookDTO.setIs_book_borrowed(false);
        bookDTO.setIs_book_reservation(false);

        try {
            String booksPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt";
            BufferedReader booksList = new BufferedReader(new FileReader(booksPwd));
            String bookList = booksList.readLine();
            List<Integer> isbnList = new ArrayList<Integer>();
            List<Integer> idList = new ArrayList<Integer>();
            List<String> titleList = new ArrayList<String>();

            while ((bookList = booksList.readLine()) != null) {
                String[] bookSplit = bookList.split("/");
                isbnList.add(Integer.parseInt(bookSplit[0]));
                idList.add(Integer.parseInt(bookSplit[1]));
                titleList.add(bookSplit[2]);

            }
            int index = isbnList.indexOf(Integer.parseInt(newBook[0]));

            if(idList.contains(Integer.parseInt(newBook[1]))) {
                System.out.println("\n----->  이미 존재하는 도서 번호입니다.");
                System.out.println("1");
            }
            else if(isbnList.contains(Integer.parseInt(newBook[0])) && !(titleList.get(index)).equals(newBook[2])) {

                System.out.println("\n-----> "+newBook[0]+"은 "+ titleList.get(index)+"의 isbn번호입니다.");
                System.out.println("-----> 일련번호에 맞는 책을 등록해주세요\n");
            }
            else{
                BufferedWriter books_w = new BufferedWriter(new FileWriter(booksPwd, true));
                String bookPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/Book_detail/" + newBook[2] + "'s Info.txt";
                BufferedWriter book_w = new BufferedWriter(new FileWriter(bookPwd, true));

                books_w.write(String.format("%d/%d/%s/%s/%s/%s/%d/%b/%b", bookDTO.getBook_ISBN(), bookDTO.getBook_id(), bookDTO.getBook_title(), bookDTO.getBook_author(), bookDTO.getBook_publisher(), bookDTO.getBook_category(), bookDTO.getBook_stock(), bookDTO.getIs_book_borrowed(), bookDTO.getIs_book_reservation()));
                book_w.write(String.format("%d/%d/%s/%s/%s/%s/%d/%b/%b", bookDTO.getBook_ISBN(), bookDTO.getBook_id(), bookDTO.getBook_title(), bookDTO.getBook_author(), bookDTO.getBook_publisher(), bookDTO.getBook_category(), bookDTO.getBook_stock(), bookDTO.getIs_book_borrowed(), bookDTO.getIs_book_reservation()));

                books_w.newLine();
                book_w.newLine();

                books_w.flush();
                book_w.flush();

                books_w.close();
                book_w.close();

                System.out.println("\n----->  책 등록이 성공적으로 이루어졌습니다.");
                System.out.println("-----------------------------------");
            }

        }catch(IOException e) {  System.out.println("-----> 등록 실패 "); e.printStackTrace(); }

    }

    public void updateBook(){


    }

    public void deleteBook(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\n삭제할 책의 이름을 입력해주세요 : ");
        String bookInput = sc.nextLine();

        try {
            String booksPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt";
            String bookPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/Book_detail/" + bookInput + "'s Info.txt";

            BufferedReader booksList = new BufferedReader(new FileReader(booksPwd));
            String bookList = booksList.readLine();
            List<Integer> isbnList = new ArrayList<Integer>();
            List<Integer> idList = new ArrayList<Integer>();
            List<String> titleList = new ArrayList<String>();
            List<String> titleAllList = new ArrayList<String>();

            while ((bookList = booksList.readLine()) != null) {
                String[] bookSplit = bookList.split("/");
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
                        String tempPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/temp.txt";
                        String tempPwd2 = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/Book_detail/temp2.text";

                        File originfile = new File(booksPwd);
                        File tempfile = new File(tempPwd);

                        File originfile2 = new File(bookPwd);
                        File tempfile2 = new File(tempPwd2);
                       try {
                            BufferedReader bL = new BufferedReader(new InputStreamReader(new FileInputStream(originfile)));
                            BufferedWriter wL = new BufferedWriter(new FileWriter(tempPwd));
                            BufferedReader bL2 = new BufferedReader(new InputStreamReader(new FileInputStream(originfile2)));
                            BufferedWriter wL2 = new BufferedWriter(new FileWriter(tempPwd2));

                            String line=bL.readLine();
                            String line2;

                            wL.write("0/00/책이름/저자/출판사/카테고리/1/대여가능여부/예약중인지\n");
                           while ((line = bL.readLine()) != null) {
                               String[] bookSplit = line.split("/");
                               if(!idInput.equals(bookSplit[1])){
//                                   System.out.println(Arrays.toString(bookSplit));
                                   wL.write(line+"\n");
                               }

                               wL.flush();

                           }

                           wL.close();
                           bL.close();
                           originfile.delete();
                           tempfile.renameTo(originfile);

                           while ((line2 = bL2.readLine()) != null) {
                               String[] bookSplit2 = line2.split("/");
                               if(!idInput.equals(bookSplit2[1])){
//                                   System.out.println(Arrays.toString(bookSplit2));
                                   wL2.write(line2+"\n");
                               }

                               wL2.flush();

                           }

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

}
