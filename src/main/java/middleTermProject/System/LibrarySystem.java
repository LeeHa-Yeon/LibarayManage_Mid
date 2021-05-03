package middleTermProject.System;

import middleTermProject.DAO.LibraryDao;
import middleTermProject.DTO.BookDto;
import middleTermProject.Screen.LibraryUserScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                if (bookSplit[2].length() < 8) {
                    System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t\t\t\t\t\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                } else if (bookSplit[2].length() > 8 && bookSplit[2].length() < 13) {
                    System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t\t\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                } else {
                    System.out.println("ㅣ\t" + bookSplit[1] + "\tㅣ" + bookSplit[2] + "\t" + bookSplit[3] + "\t  " + bookSplit[4] + "\t\t" + bookSplit[5] + "\t");
                }
            }
            System.out.println(" ⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽\n");
        } catch (IOException e) {
            System.out.println("-----> 보기 실패 ");
            e.printStackTrace();
        }
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
                if (search_title.equals(bookSplit[2])) {
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
        } catch (IOException e) {
            System.out.println("-----> 보기 실패 ");
            e.printStackTrace();
        }
    }

    @Override
    public void showBookInfo() {
        Scanner sc = new Scanner(System.in);
        String[] bookSchema = {"isbn", "도서번호", "책 제목", "지은이", "출판사", "카테고리", "재고", "대여가능여부", "예약상태"};
        System.out.print("해당 책의 자세한 정보를 원하시면 도서번호를 입력해주세요 : ");
        String input_bookId = sc.nextLine();
        System.out.println("\n------------------------------------");
        try {
            BufferedReader br_books = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt"));
            String bookLine = br_books.readLine();

            while ((bookLine = br_books.readLine()) != null) {
                String[] bookSplit = bookLine.split("/");

                if (bookSplit[1].equals(input_bookId)) {
                    for (int i = 0; i < bookSchema.length; i++) {
                        System.out.println(bookSchema[i] + " : " + bookSplit[i]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("-----> 실패 ");
            e.printStackTrace();
        }
        System.out.println("------------------------------------");
        retrieveInfo(input_bookId);
        System.out.println("-----> 1. 대여 요청하기 \t\t 2. 예약 요청하기 \t\t 3. 뒤로가기 \n");
        System.out.print("-----> 선택해주세요 :");
        int num = sc.nextInt();
        if (num == 1) {
            lendBook();
        } else if (num == 2) {
            bookBook();
        } else {
            libraryUserScreen.memuPrint();
        }
    }

    @Override
    public void lendBook() {
        System.out.println("\n------------- 대여 진행 중 ---------------");
        String cnt = UserSystem.accessedUserDto.getBorrowedLimit();
        Scanner sc = new Scanner(System.in);

        if (LibraryManagerSystem.accessedBookDto.getIs_book_borrowed().equals("대여가능")) {
            // 대여 가능
            System.out.print("이 책을 대여하시겠습니까 (yes/no) ? ");
            String answer = sc.nextLine();
            if (answer.equals("yes")) {
                if (Integer.parseInt(cnt) <= 0) {
                    System.out.println("대여 가능한 최대 개수(3개)를 초과하였습니다. 반납 후 이용해주세요");
                    System.out.println("---------------------------------------\n");
                } else {
                    LibraryManagerSystem.accessedBookDto.setIs_book_borrowed("대여불가능");
                    // 유저정보에 대여가능수를 1감소하여 업데이트
                    UserSystem.accessedUserDto.setBorrowedLimit(Integer.toString(Integer.parseInt(cnt) - 1));
                    System.out.println("-----> 대여 완료");
                    System.out.println("-----> 앞으로 대여할 수 있는 남은 횟수 : " + UserSystem.accessedUserDto.getBorrowedLimit());
                    System.out.println("---------------------------------------\n");
                    try {
                        bookSystem.updateLendFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt", Integer.toString(LibraryManagerSystem.accessedBookDto.getBook_id()));
                        bookSystem.updateLendFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/Book_detail/" + LibraryManagerSystem.accessedBookDto.getBook_title() + "'s Info.txt", Integer.toString(LibraryManagerSystem.accessedBookDto.getBook_id()));
                        userSystem.updateLendFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt", UserSystem.accessedUserDto.getId());
                        userSystem.updateLendFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + UserSystem.accessedUserDto.getId() + "'s Info.txt", UserSystem.accessedUserDto.getId());
                        Thread.sleep(3000);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                libraryUserScreen.memuPrint();
            } else {
                System.out.println("\t\t대여를 취소하였습니다.");
                System.out.println("---------------------------------------\n");
                libraryUserScreen.memuPrint();
            }
        } else {
            // 대여 불가능
            System.out.println("\t\t이미 대여중인 도서입니다.");
            System.out.println("---------------------------------------\n");
            libraryUserScreen.memuPrint();
        }
    }

    @Override
    public void returnBook() {
        Scanner sc = new Scanner(System.in);

        String lendString = UserSystem.accessedUserDto.getLendBookList().get(0);
        String[] lendList = lendString.split(",|\\[|\\]");

        ArrayList<String> bookList = new ArrayList<>();
        for (String a1 : lendList) {
            if (a1.length() > 0 && !a1.equals(" ")) {
                bookList.add(a1);
            }
        }

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd");
        Date time = new Date();
        String currentDate = format1.format(time);

        cal.add(cal.DATE,+7);
        String overdueDate = format1.format(cal.getTime());


        System.out.println("------------- 반납 진행 중 ---------------");

        if (bookList.size() == 0) {
            System.out.println("----> 반납할 책이 없습니다. \n----> 3초 뒤 메인으로 돌아갑니다. ");
            System.out.println("---------------------------------------\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            libraryUserScreen.memuPrint();
        } else {
            System.out.print("반납하실 도서 제목을 입력해주세요 : ");
            String titleInput = sc.nextLine();


            if (bookList.contains(titleInput)) {
                int index = bookList.indexOf(titleInput);
                int deadlineDate = dateCompareTo(currentDate, bookList.get(index + 2));
                ArrayList<String> removeLendBook = new ArrayList<>();
                ArrayList<List> returnRemoveList = new ArrayList<>();
                int cnt = 0;

                // 대여목록 수정하는 부분 시작  ---> 코드 더러움 주의 ㅠ
                for( int i = 0; i < bookList.size(); i++){

                    if(i>=index && i<=index+3){
                        continue;
                    }else {
                        if(cnt%4==0) {
                            removeLendBook = new ArrayList<>();
                            removeLendBook.add(String.join("",bookList.get(i).split(" ")));
                            returnRemoveList.add(removeLendBook);
                        }else{
                            removeLendBook.add(String.join("",bookList.get(i).split(" ")));
                        }
                        cnt+=1;
                    }
                }
                ArrayList<String> profile5 = new ArrayList<String>();
                if(returnRemoveList.toString().length() > 1) {
                    profile5.add(returnRemoveList.toString().substring(1, returnRemoveList.toString().length() - 1));
                }else{
                    profile5.add(returnRemoveList.toString().substring(0, returnRemoveList.toString().length() - 1));
                }
                UserSystem.accessedUserDto.setLendBookList(profile5);


                System.out.println("원래 :"+UserSystem.accessedUserDto.getLendBookList());
                // 대여목록 수정하는 부분 끝



                String inputId = Integer.toString(Integer.parseInt(String.join("",bookList.get(index + 1).split(" "))));
                System.out.print("책을 반납하시겠습니까(y/n)? ");
                String answer = sc.nextLine();
                if (answer.equals("y")) {
                    String stock = UserSystem.accessedUserDto.getBorrowedLimit();
                    UserSystem.accessedUserDto.setBorrowedLimit(Integer.toString(Integer.parseInt(stock) + 1));
                    try {
                        bookSystem.updateReturnFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/Book_detail/" + titleInput + "'s Info.txt", inputId);
                        bookSystem.updateReturnFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt", inputId);
                        if (deadlineDate == 1) {
                            UserSystem.accessedUserDto.setOverdueDate(overdueDate);
                            //-> 시간안에 반납 못했을 경우 -> 연체-> 일주일간 책을 못빌립니다.
                            System.out.println("반납기간은" + bookList.get(index + 2) + " ~" + bookList.get(index + 3) + "이였습니다.");
                            System.out.println("-----> 연체되었습니다.");
                            System.out.println("-----> 일주일간 대여 불가능합니다.\n");
                            System.out.println("-----> 반납이 완료되었습니다.");
                        } else {
                            //-> 시간 안에 반납했을 경우 -> 정상
                            System.out.println("-----> 감사합니다. 정상적으로 반납이 완료되었습니다.");
                        }
                        System.out.println("---------------------------------------\n");
                        libraryUserScreen.memuPrint();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("----> 메인 화면으로 돌아갑니다.\n");
                    System.out.println("---------------------------------------\n");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    libraryUserScreen.memuPrint();

                }
            } else {
                System.out.println("----> 반납하실 책 중 \"" + titleInput + "\"이 존재하지 않습니다.\n----> 다시 확인해주세요.\n");
                returnBook();
            }
        }


    }

    @Override
    public void bookBook() {
        System.out.println("예약 함수");
    }

    @Override
    public void retrieveInfo(String id) {
        try {
            BufferedReader br_books = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookInfoList.txt"));
            String bookLine = br_books.readLine();

            while ((bookLine = br_books.readLine()) != null) {
                String[] bookSplit = bookLine.split("/");
                if (id.equals(bookSplit[1])) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int dateCompareTo(String currentDate, String returnDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        try {
            today = format.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date end = null;
        try {
            end = format.parse(returnDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int result = today.compareTo(end);

        return result;
    }

}

