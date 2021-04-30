package middleTermProject;


import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class UserManagment {

    public static User accessedUser = null;

    // 해야할 것 : 회원 정보 읽기 , 회원 정보 수정

    public void mainMemu(){
        int select;
        // 문자열을 읽어올 수 있음
        // Scanner의 객체 생성
        // System.in은 입력한 값을 바이트 단위로 읽는
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------- 회원 관리 memu -----------------");
        System.out.println("\t\t\t\t1. 로그인\n  \t\t\t\t2. 회원가입\n  \t\t\t\t3. 아이디/비번 찾기\n  \t\t\t\t0. 종료 ");
        System.out.println("------------------------------------------------");
        System.out.print("\n 번호를 선택해주세요 : ");
        select = sc.nextInt();
        switch(select) {
            case 1 :
                System.out.println("\n---------- 로그인 화면 -----------\n");
                if(login()){
                    System.out.println("로그인성공");
                    modify();
                }else{
                    mainMemu();
                }
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
                String usersPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt";
                BufferedWriter users_w = new BufferedWriter(new FileWriter(usersPwd, true));
                String profilePwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + user.getId() + "'s Info.txt";
                BufferedWriter profile = new BufferedWriter(new FileWriter(profilePwd, true));
                // write 출력
                users_w.write(String.format("%s/%s/%s/%s/%s",user.getId(),user.getPwd(),user.getName(),user.getPhone(),user.getAddress()));
                profile.write(String.format("%s/%s/%s/%s/%s", user.getId(),user.getPwd(),user.getName(),user.getPhone(),user.getAddress()));
                // 개행 엔터역할
                users_w.newLine();
                profile.newLine();
                // 버퍼에 남아있는 데이터 모두 출력하여 없앰
                users_w.flush();
                profile.flush();
                // 스트림 닫아주기
                users_w.close();
                profile.close();

                System.out.println("-----> 회원 정보 등록 성공 ");
                mainMemu();
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

    // 로그인  ---> 아이디 중복 제거하기 ( )
    public Boolean login(){
        Scanner sc = new Scanner(System.in);
        String[] userInput = {"아이디","비번"};
        String[] userInfo = new String[userInput.length];

        for(int i =0; i<userInput.length; i++){
            System.out.print(userInput[i]+" : ");
            userInfo[i] = sc.next();
        }
        try {
            String usersPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt";
            BufferedReader users = new BufferedReader(new FileReader(usersPwd));
            String userList = users.readLine();
            List<String> idList = new ArrayList<String>();

            while ((userList = users.readLine()) != null) {
                String[] userSplit = userList.split("/");
                idList.add(userSplit[0]);
            }

            // 아이디가 일치했을 경우
            if (idList.contains(userInfo[0])){
                String profilePwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + userInfo[0] + "'s Info.txt";
                BufferedReader profile = new BufferedReader(new FileReader(profilePwd));
                String profileList = profile.readLine();
                String[] profileSplit = profileList.split("/");

                // 비밀번호가 맞았을 경우
                if(profileSplit[1].equals(userInfo[1])){
                    System.out.println("——> 로그인이 성공적으로 이루어졌습니다.\n");
                    UserManagment.accessedUser = new User();
                    UserManagment.accessedUser.setId(userInfo[0]);
                    UserManagment.accessedUser.setPwd(profileSplit[1]);
                    UserManagment.accessedUser.setName(profileSplit[2]);
                    UserManagment.accessedUser.setPhone(profileSplit[3]);
                    UserManagment.accessedUser.setAddress(profileSplit[4]);
                    UserManagment.accessedUser.setBorrowed_book(0);
                    profile.close();
                    users.close();
                    return true;
                }
                else {
                    // 비밀번호가 틀렸을 경우
                    System.out.println("——> 비밀번호가 틀렸습니다.\n");
                    profile.close();
                    users.close();
                    return false;
                }
            }else {
                // 아이디가 없을 경우
                System.out.println("——> 등록되지 않은 아이디입니다. \n");
                users.close();
                return false;
            }
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    // 아이디,비번 찾기
    public void find_id(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\n전화번호를 입력하세요 :");
        String userInput = sc.next();

        try {
            String usersPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt";
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
            String usersPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt";
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

    public void modify(){
        String profilePwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + UserManagment.accessedUser.getId() + "'s Info.txt";
        File inputFile = new File(profilePwd);
        File outputFile = new File(profilePwd+".temp");
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        boolean result = false;

        try {
            fileInputStream = new FileInputStream(inputFile);
            fileOutputStream = new FileOutputStream(outputFile);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            Scanner sc = new Scanner(System.in);
            String line;
            String repLine;

            String originalString = UserManagment.accessedUser.getPwd();
            System.out.print("변경할 비밀번호를 입력하세요 : ");
            String replaceString = sc.next();
            UserManagment.accessedUser.setPwd(replaceString);

            while ((line = bufferedReader.readLine()) != null) {
                repLine = line.replaceAll(originalString, replaceString);
                bufferedWriter.write(repLine, 0, repLine.length());
                bufferedWriter.newLine();
            }
            result = true;
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                bufferedReader.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
            try{
                bufferedWriter.close();
            }catch (IOException ex1){
                ex1.printStackTrace();
            }
            if(result){
                inputFile.delete();
                outputFile.renameTo(new File(profilePwd));
            }

        }


        // Files.write(Files.createFile(Paths.get("mytest.txt")), contents.replaceFirst("HOME", "HOUSE").getBytes())
//        System.out.println(UserManagment.accessedId.getId());
//        System.out.println(UserManagment.accessedId.getPwd());
//        System.out.println(UserManagment.accessedId.getName());
//        System.out.println(UserManagment.accessedId.getPhone());
//        System.out.println(UserManagment.accessedId.getAddress());
    }

}
