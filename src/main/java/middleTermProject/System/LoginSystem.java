package middleTermProject.System;

import middleTermProject.DAO.LoginDao;
import middleTermProject.DTO.UserDto;
import middleTermProject.Screen.LoginScreen;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginSystem implements LoginDao {

    @Autowired
    LoginScreen loginScreen;

    public static UserDto accessedUserDto = null;

    // 로그인  -> 관리자일때 화면 / 사용자일때 화면
    @Override
    public Boolean login() {
        Scanner sc = new Scanner(System.in);
        String[] userSchema = {"아이디", "비번"};
        String[] userInput = new String[userSchema.length];

        for (int i = 0; i < userSchema.length; i++) {
            System.out.print(userSchema[i] + " : ");
            userInput[i] = sc.next();
        }
        try {
            // 모든 유저의 아이디를 모으기
            BufferedReader br = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt"));
            String userLine = br.readLine();
            List<String> idList = new ArrayList<String>();

            while ((userLine = br.readLine()) != null) {
                String[] userSplit = userLine.split("/");
                idList.add(userSplit[0]);
            }
            // 아이디가 일치했을 경우
            if (idList.contains(userInput[0])) {
                BufferedReader profile_br = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + userInput[0] + "'s Info.txt"));
                String profileLine = profile_br.readLine();
                String[] profileSplit = profileLine.split("/"); //"[]"

                // 비밀번호가 맞았을 경우
                if (profileSplit[1].equals(userInput[1])) {
                    System.out.println("——> 로그인이 성공적으로 이루어졌습니다.\n");
                    // 회원 정보 불러오기
                    ArrayList<String> profile5 = new ArrayList<String>();
                    if(profileSplit[5].length() > 1) {
                        profile5.add(profileSplit[5].substring(1, profileSplit[5].length() - 1));
                    }else{
                        profile5.add(profileSplit[5].substring(0, profileSplit[5].length() - 1));
                    }

                    UserSystem.accessedUserDto = new UserDto();
                    UserSystem.accessedUserDto.setId(profileSplit[0]);
                    UserSystem.accessedUserDto.setPwd(profileSplit[1]);
                    UserSystem.accessedUserDto.setName(profileSplit[2]);
                    UserSystem.accessedUserDto.setPhone(profileSplit[3]);
                    UserSystem.accessedUserDto.setAddress(profileSplit[4]);
                    UserSystem.accessedUserDto.setLendBookList(profile5);
                    UserSystem.accessedUserDto.setOverdueDate(profileSplit[6]);
                    UserSystem.accessedUserDto.setBorrowedLimit(profileSplit[7]);
//                   System.out.println("---->나중에 지워"+profileSplit[6]+"dd"+UserSystem.accessedUserDto.getBorrowedLimit());
                    profile_br.close();
                    br.close();
                    return true;
                } else {
                    // 비밀번호가 틀렸을 경우
                    System.out.println("——> 비밀번호가 틀렸습니다.");
                    profile_br.close();
                    br.close();
                    return false;



                }
            } else {
                // 아이디가 없을 경우
                System.out.println("——> 등록되지 않은 아이디입니다. ");
                br.close();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    // 회원가입
    @Override
    public void register() {
        UserDto userDTO = new UserDto();
        Scanner sc = new Scanner(System.in);
        String[] userSchema = {"아이디", "비번", "비번확인", "이름", "폰번호", "주소"};
        String[] newUser = new String[userSchema.length];

        for (int i = 0; i < userSchema.length; i++) {
            System.out.print(userSchema[i] + " 입력 : ");
            newUser[i] = sc.next();
        }

        // 비번과 비번확인이 일치했을 경우
        if (newUser[1].equals(newUser[2])) {

            try {
                // id 중복 찾기
                BufferedReader br = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt"));
                String userLine = br.readLine();
                List<String> idList = new ArrayList<String>();
                ArrayList<String> lendList = new ArrayList<>();


                while ((userLine = br.readLine()) != null) {
                    String[] userSplit = userLine.split("/");
                    idList.add(userSplit[0]);
                }
                if(!idList.contains(newUser[0])) {
                    BufferedWriter all_bw = new BufferedWriter(new FileWriter("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt", true));
                    BufferedWriter profile_bw = new BufferedWriter(new FileWriter("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + newUser[0] + "'s Info.txt", true));
                    all_bw.write(String.format("%s/%s/%s/%s/%s/%s/%s/%s", newUser[0], newUser[1], newUser[3], newUser[4], newUser[5],lendList,"정상","3"));
                    profile_bw.write(String.format("%s/%s/%s/%s/%s/%s/%s/%s", newUser[0], newUser[1], newUser[3], newUser[4], newUser[5],lendList,"정상","3"));
                    all_bw.newLine();
                    profile_bw.newLine();
                    all_bw.flush();
                    profile_bw.flush();
                    all_bw.close();
                    profile_bw.close();

                    System.out.println("-----> 회원 정보 등록 성공 ");
                    System.out.println("\n----->  회원가입이 성공적으로 이루어졌습니다.");
                    System.out.println("-----------------------------------");

                }else{
                    System.out.println("-----> 이미 존재하는 id입니다. \n");
                    try {
                        System.out.println("3초 뒤에 다시 이전 화면으로 돌아갑니다.\n");
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        System.err.format("IOException: %s%n", e);
                    }
                }

            } catch (IOException e) {
                System.out.println("-----> 등록 실패 ");
                e.printStackTrace();
            }
            loginScreen.memuPrint();

        }
        else {
            System.out.println("\n-----> 비밀번호가 일치하지않습니다. 다시 입력해주세요 ");
            System.out.println("\n---------- 회원가입 화면 -----------\n");
            register();
        }

    }

    @Override
    public void searchId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n전화번호를 입력하세요 :");
        String phoneInput = sc.next();

        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt"));
            String userList = br.readLine();
            List<String> idList = new ArrayList<String>();
            List<String> phoneList = new ArrayList<String>();

            while ((userList = br.readLine()) != null) {
                String[] userSplit = userList.split("/");
                idList.add(userSplit[0]);
                phoneList.add(userSplit[3]);
            }
            // 만약 입력한 값이 있다면
            if (phoneList.contains(phoneInput)) {
                System.out.print("\n---------> 찾는 아이디 : ");
                System.out.println(idList.get(phoneList.indexOf(phoneInput))+"\n");
            } else {
                System.out.println("\n------> 찾는 아이디가 없습니다.\n");
            }
            loginScreen.memuPrint();
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void searchPwd() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n아이디를 입력하세요 :");
        String idInput = sc.next();

        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt"));
            String userLine = br.readLine();
            List<String> idList = new ArrayList<String>();
            List<String> pwdList = new ArrayList<String>();

            while ((userLine = br.readLine()) != null) {
                String[] userSplit = userLine.split("/");
                idList.add(userSplit[0]);
                pwdList.add(userSplit[1]);
            }
            if (idList.contains(idInput)) {
                System.out.print("\n---------> 찾는 비밀번호 : ");
                System.out.println(pwdList.get(idList.indexOf(idInput))+"\n");
            } else {
                System.out.println("\n------> 존재하지 않는 아이디입니다.\n");
            }
            loginScreen.memuPrint();
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void logout() {
        UserSystem.accessedUserDto = new UserDto();
        UserSystem.accessedUserDto.setId("로그인을 해주세요");
        UserSystem.accessedUserDto.setPwd("로그인을 해주세요");
        UserSystem.accessedUserDto.setName("로그인을 해주세요");
        UserSystem.accessedUserDto.setPhone("로그인을 해주세요");
        UserSystem.accessedUserDto.setAddress("로그인을 해주세요");
        UserSystem.accessedUserDto.setOverdueDate("로그인을 해주세요");
        UserSystem.accessedUserDto.setBorrowedLimit("로그인을 해주세요");
        try {
            System.out.println("3초 뒤에 로그인 화면으로 이동합니다..\n");
            Thread.sleep(3000);
            loginScreen.memuPrint();
        }catch (InterruptedException e){
            System.err.format("IOException: %s%n", e);
        }
    }

}
