package middleTermProject;

import java.io.*;
import java.util.*;

public class BookManagment {

    public static Book accessedBook = null;

    public void bookMemu(){
        int select;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------- Book 관리 목록창 -----------------");
        System.out.println("\t\t\t\t1. 책 목록보기\n  \t\t\t\t2. 책 정보보기\n  \t\t\t\t3. 책 추가하기\n  \t\t\t\t4. 책 수정하기\n  \t\t\t\t5. 책 삭제하기\n  \t\t\t\t0. 종료 ");
        System.out.println("------------------------------------------------");
        System.out.print("\n 번호를 선택해주세요 : ");
        select = sc.nextInt();
        switch(select) {
            case 1 :
                // 책 정보 보기 추가하기
                System.out.println("\n---------- 책 목록 화면 -----------\n");
                showBookList();
                break;
            case 2 :
                System.out.println("\n---------- 책 정보보기 -----------\n");
                showBookInfo();
                break;
            case 3 :
                System.out.println("\n---------- 책 추가하기 -----------\n");
                addBook();
                break;
            case 4 :
                System.out.println("\n---------- 책 수정하기 -----------\n");
                updateBook();
                break;
            case 5 :
                System.out.println("\n---------- 책 삭제하기 -----------\n");
                deleteBook();
                break;
            default:
                System.out.println("종료");
                break;
        }
    }

    public void showBookList(){


    }

    public void showBookInfo(){

    }

    // 도서아이디, 도서번호, 책이름, 저자, 출판사, 카테고리, 책 재고, 대여가능여부, 예약중인지
    public void addBook(){

        Book book = new Book();

        Scanner sc = new Scanner(System.in);
        String[] bookInput = {"isbn","도서고유번호","책이름","지은이","출판사","카테고리"};
        String[] newBook = new String[bookInput.length];

        for(int i =0; i<bookInput.length; i++){
            System.out.print(bookInput[i]+" 입력 : ");
            newBook[i] = sc.nextLine();
        }
        book.setBook_ISBN(Integer.parseInt(newBook[0]));
        book.setBook_id(Integer.parseInt(newBook[1]));
        book.setBook_title(newBook[2]);
        book.setBook_author(newBook[3]);
        book.setBook_publisher(newBook[4]);
        book.setBook_stock(1);
        book.setBook_category(newBook[5]);
        book.setIs_book_borrowed(false);
        book.setIs_book_reservation(false);

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

                System.out.println("\n-----> "+newBook[1]+"은 "+ titleList.get(index)+"의 isbn번호입니다.");
                System.out.println("-----> 일련번호에 맞는 책을 등록해주세요\n");
            }
            else{
                BufferedWriter books_w = new BufferedWriter(new FileWriter(booksPwd, true));
                String bookPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/Book_detail/" + newBook[2] + "'s Info.txt";
                BufferedWriter book_w = new BufferedWriter(new FileWriter(bookPwd, true));

                books_w.write(String.format("%d/%d/%s/%s/%s/%s/%d/%b/%b",book.getBook_ISBN(),book.getBook_id(),book.getBook_title(),book.getBook_author(),book.getBook_publisher(),book.getBook_category(),book.getBook_stock(),book.getIs_book_borrowed(),book.getIs_book_reservation()));
                book_w.write(String.format("%d/%d/%s/%s/%s/%s/%d/%b/%b",book.getBook_ISBN(),book.getBook_id(),book.getBook_title(),book.getBook_author(),book.getBook_publisher(),book.getBook_category(),book.getBook_stock(),book.getIs_book_borrowed(),book.getIs_book_reservation()));

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
//            System.out.println(isbnList+" "+idList+" "+isbnList2+" "+titleList);
                System.out.println("\""+bookInput+"\"의 일련번호 : "+isbnList2);
                System.out.print(bookInput+"의 삭제할 일련번호를 선택해주세요 :");
                String isbnInput = sc.nextLine();

                System.out.print("id를 입력해주세요 : ");
                String idInput = sc.nextLine();

                if(idList.contains(Integer.parseInt(idInput))){
                    System.out.print("일련번호가 "+isbnInput+"인"+bookInput+"책을 정말 삭제하시겠습니까(yes / no)? ");
                    String answer = sc.nextLine();
                    if(answer.equals("yes")){
                        // id에 해당하는 삭제할 책 코드 입력
                        System.out.println("------> 해당 책을 삭제하였습니다.");
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







//            int index = titleList.indexOf(bookInput);


        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
