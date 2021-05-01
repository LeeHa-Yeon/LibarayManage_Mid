package middleTermProject.System;

import middleTermProject.DAO.UserDao;
import middleTermProject.DTO.UserDto;
import middleTermProject.Screen.LibraryUserScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.Scanner;

public class UserSystem implements UserDao {

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
        System.out.println("\n---> "+UserSystem.accessedUserDto.getName()+"의 회원 정보");
        System.out.println("  \n  아이디\t ㅣ  이름   ㅣ 핸드폰 번호\t ㅣ 주소 ㅣ 빌린책 개수 ");
        System.out.println(" ⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻");
        System.out.println(" "+UserSystem.accessedUserDto.getId()+"\t   "+UserSystem.accessedUserDto.getName()+"\t\t"+UserSystem.accessedUserDto.getPhone()+"\t\t"+UserSystem.accessedUserDto.getAddress()+"\t\t"+UserSystem.accessedUserDto.getBorrowed_book());
        System.out.println(" ⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽\n");

        System.out.println("-----> 1. 회원 정보를 수정\t\t 2. 뒤로가기 \n");
        System.out.print("-----> 선택해주세요 :");
        int num = sc.nextInt();
        if(num==1){
            System.out.println("회원 정보 수정창으로 이동");
        }else{
            libraryUserScreen.memuPrint();
        }

    }
}
