package middleTermProject.System;

import middleTermProject.DAO.FileDao;
import middleTermProject.DAO.UserDao;
import middleTermProject.DTO.UserDto;
import middleTermProject.Screen.LibraryUserScreen;
import middleTermProject.Screen.LoginScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserSystem implements UserDao, FileDao {

    @Autowired
    LibraryManagerSystem libraryManagerSystem;
    @Autowired
    LibraryUserScreen libraryUserScreen;
    @Autowired
    LoginScreen loginScreen;
    @Autowired
    FileSystem fileSystem;


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

            System.out.print("?????? ??????????????? ??????????????? : ");
            String inputPWD = sc.next();
            String presentPWD = UserSystem.accessedUserDto.getPwd();

            if (inputPWD.equals(presentPWD)) {
                System.out.print("????????? ??????????????? ??????????????? : ");
                String replacePWD = sc.next();
                UserSystem.accessedUserDto.setPwd(replacePWD);

                String line;
                String repLine;

                while ((line = br_users.readLine()) != null) {
                    String[] userSplit = line.split("/");
                    if (userSplit[0].equals(UserSystem.accessedUserDto.getId())) {
                        repLine = line.replaceAll(presentPWD, replacePWD);
                        bw_users.write(repLine, 0, repLine.length());
                        bw_users.newLine();
                    } else {
                        bw_users.write(line + "\n");
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
        System.out.println("\n---> " + UserSystem.accessedUserDto.getName() + "??? ?????? ??????");
        System.out.println("  \n  ?????????\t ???  ??????   ??? ????????? ??????\t ??? ?????? ??? ?????? ????????? ?????? ");
        System.out.println(" ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
        System.out.println(" " + UserSystem.accessedUserDto.getId() + "\t   " + UserSystem.accessedUserDto.getName() + "\t\t" + UserSystem.accessedUserDto.getPhone() + "\t\t" + UserSystem.accessedUserDto.getAddress() + "\t\t" + UserSystem.accessedUserDto.getBorrowedLimit());
        System.out.println(" ?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????\n");

        System.out.println("-----> 1. ?????? ????????? ??????\t\t 2. ???????????? \n");
        System.out.print("-----> ?????????????????? :");
        String num = sc.nextLine();
        if (num.equals("1")) {
            updateInfo(UserSystem.accessedUserDto.getId());
        } else {
            libraryUserScreen.memuPrint();
        }

    }

    @Override
    public void updateInfo(String id) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------\n");
        System.out.println(" ----------   ????????? ????????? ??????????????????   ----------");
        System.out.print("1. ??????   2. ????????? ??????   3. ??????  : ");
        String choose = sc.nextLine();

        String originInfo = "";
        String updateInfo = "";

        if (choose.equals("1"))  // ?????? ??????
        {
            originInfo = UserSystem.accessedUserDto.getName();
            System.out.print("????????? ????????? ?????????????????? :");
            updateInfo = sc.nextLine();
        } else if (choose.equals("2"))  // ????????? ??????
        {
            originInfo = UserSystem.accessedUserDto.getPhone();
            System.out.print("????????? ??????????????? ?????????????????? :");
            updateInfo = sc.nextLine();
        } else if (choose.equals("3")) {   // ??????
            originInfo = UserSystem.accessedUserDto.getAddress();
            System.out.print("????????? ????????? ?????????????????? :");
            updateInfo = sc.nextLine();
        }
        System.out.print(" ??????????????? ??????????????? : ");
        String inputPWD = sc.nextLine();
        String presentPWD = UserSystem.accessedUserDto.getPwd();

        if (inputPWD.equals(presentPWD) && !originInfo.equals(" ")) {
            try {
                if (choose.equals("1")) {
                    UserSystem.accessedUserDto.setName(updateInfo);
                } else if (choose.equals("2")) {
                    UserSystem.accessedUserDto.setPhone(updateInfo);
                } else if (choose.equals("3")) {
                    UserSystem.accessedUserDto.setAddress(updateInfo);
                }
                fileSystem.UpdateInfoFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + UserSystem.accessedUserDto.getId() + "'s Info.txt", originInfo);
                fileSystem.UpdateInfoFile("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt", originInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("------ ??????????????? ???????????????. \n------ ????????? ?????????????????????.");
        }
        showProfile();
    }

    @Override
    public void signout() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n ??????????????? ?????????????????? : ");
        String pwdInput = sc.nextLine();
        if (!pwdInput.equals(UserSystem.accessedUserDto.getPwd())) {
            System.out.println(" ----> ??????????????? ???????????????. \n ----> ?????? ???????????? ???????????????.\n");
            libraryUserScreen.memuPrint();
        } else {
            System.out.print(" ????????? ????????? ???????????????????????? (y/n)?  ");
            String answer = sc.nextLine();
            if (answer.equals("y")) {
                try {
                    String allPath = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt";
                    String profilePath = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + UserSystem.accessedUserDto.getId() + "'s Info.txt";
                    String alltempPath = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/temp.txt";

                    File originfile = new File(allPath);
                    File tempfile = new File(alltempPath);
                    File deleteFile = new File(profilePath);
                    BufferedReader br_users = new BufferedReader(new FileReader(allPath));
                    BufferedWriter bw_users = new BufferedWriter(new FileWriter(tempfile));
                    String alluserLine = br_users.readLine();

                    bw_users.write("Id/??????/??????/??????/??????/[]/?????????????????????(??????)/?????????\n");
                    while ((alluserLine = br_users.readLine()) != null) {
                        String[] userSplit = alluserLine.split("/");
                        if (!UserSystem.accessedUserDto.getId().equals(userSplit[0])) {
                            bw_users.write(alluserLine + "\n");
                        }
                        bw_users.flush();
                    }

                    if (deleteFile.exists()) {
                        deleteFile.delete();
                    } else {
                        System.out.println("????????? ???????????? ????????????.");
                    }

                    bw_users.close();
                    br_users.close();
                    originfile.delete();
                    tempfile.renameTo(originfile);

                    System.out.println(" ------> ?????????????????????.");
                    loginScreen.memuPrint();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(" ------> ?????????????????????.");
                libraryUserScreen.memuPrint();
            }

        }
    }

    @Override
    public boolean updateLendFile(String FilePath, String id) throws IOException {
        boolean result = false;
        File originFile = new File(FilePath);
        File tempFile = new File(FilePath + ".temp");

        FileInputStream fileOriginStream = new FileInputStream(originFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileOriginStream));
        FileOutputStream fileTempStream = new FileOutputStream(tempFile);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileTempStream));

        String line;
        String repLine;
        ArrayList<String> newLendBook = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat todayformat = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date();
        String lend_date = todayformat.format(time);
        cal.add(cal.DATE, +7);
        String return_date = todayformat.format(cal.getTime());

        newLendBook.add(LibraryManagerSystem.accessedBookDto.getBook_title());
        newLendBook.add(Integer.toString(LibraryManagerSystem.accessedBookDto.getBook_id()));
        newLendBook.add(lend_date);
        newLendBook.add(return_date);

        if (!UserSystem.accessedUserDto.getLendBookList().contains(newLendBook.toString())) {
            UserSystem.accessedUserDto.getLendBookList().add(newLendBook.toString());
        }
        while ((line = br.readLine()) != null) {
            String[] userSplit = line.split("/");
            if (userSplit[0].equals(id)) {
                ArrayList<String> profile5 = new ArrayList<String>();
                profile5.add(userSplit[5].substring(1, userSplit[5].length() - 1));

                String subStr = line.substring(line.length() - 1, line.length());
                repLine = line.substring(0, line.length() - 1) + subStr.replace(userSplit[7], UserSystem.accessedUserDto.getBorrowedLimit());
                repLine = repLine.replace(userSplit[5], UserSystem.accessedUserDto.getLendBookList().toString());
                bw.write(repLine, 0, repLine.length());
                bw.newLine();

            } else {
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

    @Override
    public void updateReturnFile(String FilePath, String id) throws IOException {
        boolean result = false;
        File originFile = new File(FilePath);
        File tempFile = new File(FilePath + ".temp");

        FileInputStream fileOriginStream = new FileInputStream(originFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fileOriginStream));
        FileOutputStream fileTempStream = new FileOutputStream(tempFile);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileTempStream));

        String line;
        String repLine;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat todayformat = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(cal.DATE, +7);
        String overdue_date = todayformat.format(cal.getTime());


        while ((line = br.readLine()) != null) {
            String[] userSplit = line.split("/");
            if (userSplit[0].equals(id)) {
                // ?????? 1??? ?????? 7
                String subStr = line.substring(line.length() - 1, line.length());
                repLine = line.substring(0, line.length() - 1) + subStr.replace(userSplit[7], UserSystem.accessedUserDto.getBorrowedLimit());
                // ?????? ?????? ?????? 6
                if (overdue_date.equals(UserSystem.accessedUserDto.getOverdueDate())) {
                    // ????????????
                    repLine = repLine.replace(userSplit[6], UserSystem.accessedUserDto.getOverdueDate());
                }
                //  id??? ????????????????????? ?????? 5
                repLine = repLine.replace(userSplit[5], UserSystem.accessedUserDto.getLendBookList().toString());
                bw.write(repLine, 0, repLine.length());
                bw.newLine();
            } else {
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
    }

    public void OverdueEnd(String FilePath, String id) throws IOException {
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
            if (userSplit[0].equals(id)) {
                repLine = line.replace(userSplit[6], UserSystem.accessedUserDto.getOverdueDate());
                bw.write(repLine, 0, repLine.length());
                bw.newLine();
            } else {
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
    }

    public void proposeBook() {
        Scanner sc = new Scanner(System.in);
        String[] bookSchema = {"isbn", "??????????????????", "?????????", "?????????", "?????????", "????????????"};
        String[] proposeBook = new String[bookSchema.length];

        for (int i = 0; i < bookSchema.length; i++) {
            System.out.print(bookSchema[i] + " ?????? : ");
            proposeBook[i] = sc.next();
        }

        try {
            // ????????? ?????? ??????
            BufferedReader br = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookApplyList.txt"));
            String userLine;
            List<String> titleList = new ArrayList<String>();

            while ((userLine = br.readLine()) != null) {
                String[] userSplit = userLine.split("/");
                titleList.add(userSplit[2]);
            }

            if (!titleList.contains(proposeBook[2])) {
                BufferedWriter all_bw = new BufferedWriter(new FileWriter("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/BookInfo/bookApplyList.txt", true));
                all_bw.write(String.format("%s/%s/%s/%s/%s/%s/%s", proposeBook[0], proposeBook[1], proposeBook[2], proposeBook[3], proposeBook[4],proposeBook[5],"?????????"));
                all_bw.newLine();
                all_bw.flush();
                all_bw.close();
                //???????????? ??????

                System.out.println("-----> ????????? ?????????????????????. ");
                System.out.println("\n----->  ????????? ?????? ??????");
                System.out.println("--------------------------------------------------------------");
                libraryManagerSystem.showApplyList();
                libraryUserScreen.memuPrint();

            } else {
                System.out.println("-----> ?????? ????????? ????????????. \n");
                try {
                    System.out.println("3??? ?????? ?????? ?????? ???????????? ???????????????.\n");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.err.format("IOException: %s%n", e);
                }
            }

        } catch (IOException e) {
            System.out.println("-----> ?????? ");
            e.printStackTrace();
        }

    }
}
