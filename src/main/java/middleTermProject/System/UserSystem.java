package middleTermProject.System;

import middleTermProject.DAO.FileDao;
import middleTermProject.DAO.UserDao;
import middleTermProject.DTO.BookDto;
import middleTermProject.DTO.UserDto;
import middleTermProject.Screen.LibraryUserScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class UserSystem implements UserDao, FileDao {

    @Autowired
    LibraryUserScreen libraryUserScreen;


    public static UserDto accessedUserDto = null;

    @Override
    public boolean changePwd() {
        String usersPath = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt";

        File originFile_users = new File(usersPath);
        File tempFile_users = new File(usersPath + ".temp");
        FileInputStream fileOriginStream_users = null;
        BufferedReader br_users = null;
        FileOutputStream fileTempStream_users = null;
        BufferedWriter bw_users = null;

        String profilePath = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + UserSystem.accessedUserDto.getId() + "'s Info.txt";

        File originFile_profile = new File(profilePath);
        File tempFile_profile = new File(profilePath + ".temp");
        FileInputStream fileOriginStream_profile = null;
        BufferedReader br_profile = null;
        FileOutputStream fileTempStream_profile = null;
        BufferedWriter bw_profile = null;

        boolean result = false;

        try {
            fileOriginStream_users = new FileInputStream(originFile_users);
            fileTempStream_users = new FileOutputStream(tempFile_users);
            br_users = new BufferedReader(new InputStreamReader(fileOriginStream_users));
            bw_users = new BufferedWriter(new OutputStreamWriter(fileTempStream_users));

            Scanner sc = new Scanner(System.in);
            fileOriginStream_profile = new FileInputStream(originFile_profile);
            fileTempStream_profile = new FileOutputStream(tempFile_profile);
            br_profile = new BufferedReader(new InputStreamReader(fileOriginStream_profile));
            bw_profile = new BufferedWriter(new OutputStreamWriter(fileTempStream_profile));

            System.out.print("현재 비밀번호를 입력하세요 : ");
            String inputPWD = sc.next();
            String presentPWD = UserSystem.accessedUserDto.getPwd();

            if (inputPWD.equals(presentPWD)) {
                System.out.print("변경할 비밀번호를 입력하세요 : ");
                String replacePWD = sc.next();
                UserSystem.accessedUserDto.setPwd(replacePWD);

                String line;
                String repLine;

                while ((line = br_users.readLine()) != null) {
                    String[] userSplit = line.split("/");
                    if(userSplit[0].equals(UserSystem.accessedUserDto.getId())) {
                        repLine = line.replaceAll(presentPWD, replacePWD);
                        bw_users.write(repLine, 0, repLine.length());
                        bw_users.newLine();
                    }else{
                        bw_users.write(line+"\n");
                    }
                }
                String line2;
                String repLine2;

                while ((line2 = br_profile.readLine()) != null) {
                    repLine2 = line2.replaceAll(presentPWD, replacePWD);
                    bw_profile.write(repLine2, 0, repLine2.length());
                    bw_profile.newLine();
                }
                result = true;
            } else {
                result = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br_users.close();
                br_profile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                bw_users.close();
                bw_profile.close();
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
            if (result) {
                originFile_users.delete();
                tempFile_users.renameTo(originFile_users);
                originFile_profile.delete();
                tempFile_profile.renameTo(originFile_profile);
            }
        }
        return result;
    }

    @Override
    public void showProfile() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n---> " + UserSystem.accessedUserDto.getName() + "의 회원 정보");
        System.out.println("  \n  아이디\t ㅣ  이름   ㅣ 핸드폰 번호\t ㅣ 주소 ㅣ 대여 가능한 책수 ");
        System.out.println(" ⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻");
        System.out.println(" " + UserSystem.accessedUserDto.getId() + "\t   " + UserSystem.accessedUserDto.getName() + "\t\t" + UserSystem.accessedUserDto.getPhone() + "\t\t" + UserSystem.accessedUserDto.getAddress() + "\t\t" + UserSystem.accessedUserDto.getBorrowedLimit());
        System.out.println(" ⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽\n");

        System.out.println("-----> 1. 회원 정보를 수정\t\t 2. 뒤로가기 \n");
        System.out.print("-----> 선택해주세요 :");
        int num = sc.nextInt();
        if (num == 1) {
            System.out.println("회원 정보 수정창으로 이동");
        } else {
            libraryUserScreen.memuPrint();
        }

    }

    @Override
    public void updateInfo(String id) {
        try {
            BufferedReader br_books = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt"));
            String bookLine = br_books.readLine();

            while ((bookLine = br_books.readLine()) != null) {
                String[] bookSplit = bookLine.split("/");
                if(id.equals(bookSplit[1])){
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

    @Override
    public boolean updateLendFile(String FilePath, String id) throws IOException
    {
        boolean result = false;
        File originFile = new File(FilePath);
        File tempFile = new File(FilePath + ".temp");

        FileInputStream fileOriginStream = new FileInputStream(originFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileOriginStream));
        FileOutputStream fileTempStream = new FileOutputStream(tempFile);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileTempStream));

        String line;
        String repLine;

        while ((line = br.readLine()) != null) {
            String[] userSplit = line.split("/");
            if(userSplit[0].equals(id)) {
                String subStr = line.substring(line.length()-1,line.length());
                repLine = line.substring(0,line.length()-1) + subStr.replace(userSplit[5],UserSystem.accessedUserDto.getBorrowedLimit());
                bw.write(repLine, 0, repLine.length());
                bw.newLine();
            }else {
                bw.write(line + "\n");
            }
            bw.flush();

            result = true;
        }
        if (result) {
            br.close();
            bw.close();
            originFile.delete();
            tempFile.renameTo(originFile);
        }
        return result;
    }
}
