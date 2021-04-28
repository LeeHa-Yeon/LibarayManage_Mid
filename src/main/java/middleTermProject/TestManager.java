package middleTermProject;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class TestManager {


    public void mainMemu(){
        int select;
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------- 회원 관리 memu -----------------");
        System.out.println("\t1. 로그인  2. 회원가입  3. 아이디/비번 찾기  0.종료 ");
        System.out.println("------------------------------------------------");
        System.out.print("\n 번호를 선택해주세요 : ");
        select = sc.nextInt();
        switch(select) {
            case 1 :
                System.out.println("\n---------- 로그인 화면 -----------\n");
                login();
                break;
            case 2 :
                System.out.println("\n---------- 회원가입 화면 -----------\n");
                register();
                break;
            case 3 :
                System.out.println("\n---------- 아이디 비번 찾기 -----------\n");
                System.out.print("1. 아이디 / 2.비번 찾기 : ");
                int select2 = sc.nextInt();
                if(select2==1){
                    System.out.println("\n--------- 아이디 찾기 --------------");
                    find_id();
                    break;
                }else {
                    System.out.println("\n-----------비밀번호 찾기------------");
                    find_pwd();
                    break;
                }
            default:
                System.out.println("종료");
                break;
        }
    }

    // 회원가입
    public void register(){
        User user = new User();
        Scanner sc = new Scanner(System.in);
        String[] userInput = {"아이디","비번","비번확인","이름","폰번호","주소"};
        String[] newUser = new String[userInput.length];

        for(int i =0; i<userInput.length; i++){
            System.out.print(userInput[i]+" 입력 : ");
            newUser[i] = sc.next();
        }

        if (newUser[1].equals(newUser[2])) {
            user.setId(newUser[0]);
            user.setPwd(newUser[1]);
            user.setName(newUser[3]);
            user.setPhone(newUser[4]);
            user.setAddress(newUser[5]);

            try {
                String usersPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/UserInfo/allUserInfo.txt";
                BufferedWriter users_w = new BufferedWriter(new FileWriter(usersPwd, true));
                String profilePwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/UserInfo/Profile/" + user.getId() + "'s Info.txt";
                BufferedWriter profile = new BufferedWriter(new FileWriter(profilePwd, true));

                users_w.write(String.format("%s/%s/%s/%s/%s",user.getId(),user.getPwd(),user.getName(),user.getPhone(),user.getAddress()));
                profile.write(String.format("%s/%s/%s/%s/%s", user.getId(),user.getPwd(),user.getName(),user.getPhone(),user.getAddress()));
                users_w.flush();
                profile.flush();
                users_w.close();
                profile.close();

                System.out.println("-----> 회원 정보 등록 성공 ");
            } catch(IOException e) {  System.out.println("-----> 등록 실패 "); e.printStackTrace(); }

            System.out.println("\n----->  회원가입이 성공적으로 이루어졌습니다.");
            System.out.println("-----------------------------------");
        }
        else {
            System.out.println("\n-----> 비밀번호가 일치하지않습니다. 다시 입력해주세요 ");
            System.out.println("\n---------- 회원가입 화면 -----------\n");
            register();
        }
    }

    // 로그인
    public void login(){
        Scanner sc = new Scanner(System.in);
        String[] userInput = {"아이디","비번"};
        String[] userInfo = new String[userInput.length];

        for(int i =0; i<userInput.length; i++){
            System.out.print(userInput[i]+" : ");
            userInfo[i] = sc.next();
        }
        try {
            String usersPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/UserInfo/allUserInfo.txt";
            BufferedReader users = new BufferedReader(new FileReader(usersPwd));
            String userList = users.readLine();
            List<String> idList = new ArrayList<String>();

            while ((userList = users.readLine()) != null) {
                String[] userSplit = userList.split("/");
                idList.add(userSplit[0]);
            }

            // 아이디가 일치했을 경우
            if (idList.contains(userInfo[0])){
                String profilePwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/UserInfo/Profile/" + userInfo[0] + "'s Info.txt";
                BufferedReader profile = new BufferedReader(new FileReader(profilePwd));
                String profileList = profile.readLine();
                String[] profileSplit = profileList.split("/");

                // 비밀번호가 맞았을 경우
                if(profileSplit[1].equals(userInfo[1])){
                    System.out.println("——> 로그인이 성공적으로 이루어졌습니다.");
                }
                else {
                    // 비밀번호가 틀렸을 경우
                    System.out.println("——> 비밀번호가 틀렸습니다.");
                }
                profile.close();
            }else {
                // 아이디가 없을 경우
                System.out.println("——> 등록되지 않은 아이디입니다. ");
            }
            users.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // 아이디,비번 찾기
    public void find_id(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\n전화번호를 입력하세요 :");
        String userInput = sc.next();

        try {
            String usersPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/UserInfo/allUserInfo.txt";
            BufferedReader users = new BufferedReader(new FileReader(usersPwd));
            String userList = users.readLine();
            List<String> idList = new ArrayList<String>();
            List<String> phoneList = new ArrayList<String>();

            while ((userList = users.readLine()) != null) {
                String[] userSplit = userList.split("/");
                idList.add(userSplit[0]);
                phoneList.add(userSplit[3]);
            }
            if (phoneList.contains(userInput)) {
                // 만약 입력한 값이 있다면
                int index = phoneList.indexOf(userInput);
                System.out.print("\n---------> 찾는 아이디 : ");
                System.out.println(idList.get(index));
                System.out.println("");
            }
            else{
                // 없다면
                System.out.println("\n------> 찾는 아이디가 없습니다.\n");
            }
            mainMemu();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void find_pwd(){

        Scanner sc = new Scanner(System.in);
        System.out.print("\n아이디를 입력하세요 :");
        String userInput = sc.next();

        try {
            String usersPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/UserInfo/allUserInfo.txt";
            BufferedReader users = new BufferedReader(new FileReader(usersPwd));
            String userList = users.readLine();
            List<String> idList = new ArrayList<String>();
            List<String> pwdList = new ArrayList<String>();

            while ((userList = users.readLine()) != null) {
                String[] userSplit = userList.split("/");
                idList.add(userSplit[0]);
                pwdList.add(userSplit[1]);
            }
            if (idList.contains(userInput)) {
                // 만약 입력한 값이 있다면
                int index = idList.indexOf(userInput);
                System.out.print("\n---------> 찾는 비밀번호 : ");
                System.out.println(pwdList.get(index));
                System.out.println("");
            }
            else{
                // 없다면
                System.out.println("\n------> 존재하지 않는 아이디입니다.\n");
            }
            mainMemu();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
