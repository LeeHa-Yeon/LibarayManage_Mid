package middleTermProject.System;

import middleTermProject.DAO.UserDao;
import middleTermProject.Zdelete.UserManagment;

import java.io.*;
import java.util.Scanner;

public class UserSystem implements UserDao {
    @Override
    public boolean changePwd() {
        String usersPath = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt";

        File originFile_users = new File(usersPath);
        File tempFile_users = new File(usersPath + ".temp");
        FileInputStream fileOriginStream_users = null;
        BufferedReader br_users = null;
        FileOutputStream fileTempStream_users = null;
        BufferedWriter bw_users = null;

        String profilePath = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + UserManagment.accessedUserDto.getId() + "'s Info.txt";

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
            String presentPWD = UserManagment.accessedUserDto.getPwd();

            if (inputPWD.equals(presentPWD)) {
                System.out.print("변경할 비밀번호를 입력하세요 : ");
                String replacePWD = sc.next();
                UserManagment.accessedUserDto.setPwd(replacePWD);

                String line;
                String repLine;

                while ((line = br_users.readLine()) != null) {
                    String[] userSplit = line.split("/");
                    if(userSplit[0].equals(UserManagment.accessedUserDto.getId())) {
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
                br_profile.close();
                br_users.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                bw_profile.close();
                bw_users.close();
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
            if (result) {
                originFile_profile.delete();
                tempFile_profile.renameTo(originFile_profile);
                originFile_users.delete();
                tempFile_users.renameTo(originFile_users);
            }
        }
        return result;
    }

    @Override
    public void showProfile() {
        System.out.println("\n---> "+UserManagment.accessedUserDto.getName()+"의 회원 정보");
        System.out.println("  \n  아이디\t ㅣ  이름   ㅣ 핸드폰 번호\t ㅣ 주소 ㅣ 빌린책 개수 ");
        System.out.println(" ⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻");

        System.out.println(" "+UserManagment.accessedUserDto.getId()+"\t   "+UserManagment.accessedUserDto.getName()+"\t\t"+UserManagment.accessedUserDto.getPhone()+"\t\t"+UserManagment.accessedUserDto.getAddress()+"\t\t"+UserManagment.accessedUserDto.getBorrowed_book());
        System.out.println(" ⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽\n");
    }
}
