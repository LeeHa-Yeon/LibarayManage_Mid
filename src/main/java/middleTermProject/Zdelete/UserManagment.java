package middleTermProject.Zdelete;


import middleTermProject.DTO.UserDto;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class UserManagment {

    public static UserDto accessedUserDto = null;

    // 해야할 것 : 회원 정보 읽기 , 회원 정보 수정

    public void mainMemu() {
        int select;
        // 문자열을 읽어올 수 있음
        // Scanner의 객체 생성
        // System.in은 입력한 값을 바이트 단위로 읽는
        Scanner sc = new Scanner(System.in);
        System.out.println("\n====> 로그인을 해주세요");
        System.out.println("----------------- 회원 관리 memu -----------------");
        System.out.println("\t\t\t\t1. 로그인\n  \t\t\t\t2. 회원가입\n  \t\t\t\t3. 아이디/비번 찾기\n  \t\t\t\t0. 종료 ");
        System.out.println("------------------------------------------------");
        System.out.print("\n 번호를 선택해주세요 : ");
        select = sc.nextInt();
        switch (select) {
            case 1:
                System.out.println("\n---------- 로그인 화면 -----------\n");
                if (login()) {
                    loginMemu();

                } else {
                    mainMemu();
                }
                break;
            case 2:
                System.out.println("\n---------- 회원가입 화면 -----------\n");
                register();
                break;
            case 3:
                System.out.println("\n---------- 아이디 비번 찾기 -----------\n");
                System.out.print("1. 아이디 / 2.비번 찾기 : ");
                int select2 = sc.nextInt();
                if (select2 == 1) {
                    System.out.println("\n--------- 아이디 찾기 --------------");
                    find_id();
                    break;
                } else {
                    System.out.println("\n-----------비밀번호 찾기------------");
                    find_pwd();
                    break;
                }
            default:
                System.out.print("시스템을 정말 종료하시겠습니까?(yes or no) ? ");
                String answer = sc.next();
                if(answer.equals("yes")){
                    System.out.println("종료");
                }else {
                    mainMemu();
                }
                break;
        }
    }


    // 회원가입 ---> 아이디 중복 제거하기 ( o)
    public void register() {
        UserDto userDTO = new UserDto();
        Scanner sc = new Scanner(System.in);
        String[] userInput = {"아이디", "비번", "비번확인", "이름", "폰번호", "주소"};
        String[] newUser = new String[userInput.length];

        for (int i = 0; i < userInput.length; i++) {
            System.out.print(userInput[i] + " 입력 : ");
            newUser[i] = sc.next();
        }

        if (newUser[1].equals(newUser[2])) {
            userDTO.setId(newUser[0]);
            userDTO.setPwd(newUser[1]);
            userDTO.setName(newUser[3]);
            userDTO.setPhone(newUser[4]);
            userDTO.setAddress(newUser[5]);

            try {
                // id 중복 찾기
                String usersPwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt";
                BufferedReader users_r = new BufferedReader(new FileReader(usersPwd));
                String userL = users_r.readLine();
                List<String> idL = new ArrayList<String>();

                while ((userL = users_r.readLine()) != null) {
                    String[] userSplit = userL.split("/");
                    idL.add(userSplit[0]);
                }
                if(!idL.contains(newUser[0])) {
                    BufferedWriter users_w = new BufferedWriter(new FileWriter(usersPwd, true));
                    String profilePwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + userDTO.getId() + "'s Info.txt";
                    BufferedWriter profile = new BufferedWriter(new FileWriter(profilePwd, true));
                    // write 출력
                    users_w.write(String.format("%s/%s/%s/%s/%s", userDTO.getId(), userDTO.getPwd(), userDTO.getName(), userDTO.getPhone(), userDTO.getAddress()));
                    profile.write(String.format("%s/%s/%s/%s/%s", userDTO.getId(), userDTO.getPwd(), userDTO.getName(), userDTO.getPhone(), userDTO.getAddress()));
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
            mainMemu();


        } else {
            System.out.println("\n-----> 비밀번호가 일치하지않습니다. 다시 입력해주세요 ");
            System.out.println("\n---------- 회원가입 화면 -----------\n");
            register();
        }
    }
    // 사용자일때 화면
    public void loginMemu(){
        System.out.println("현재 로그인 상태 : "+UserManagment.accessedUserDto.getId()+"님");
        System.out.println("------------------------------------------------");
        System.out.println("\t\t\t\t1. 도서관 관리 시스템\n  \t\t\t\t2. 회원 정보 보기\n  \t\t\t\t3. 비밀번호 수정\n  \t\t\t\t0. 로그아웃 ");
        System.out.println("------------------------------------------------");
        System.out.print("\n 번호를 선택해주세요 : ");
        Scanner sc = new Scanner(System.in);
        int select3 = sc.nextInt();
        if (select3 == 1) {
            // 목록보기
            System.out.println("목록보기");
        } else if (select3 == 2) {
            show_profile();
            try {
                System.out.println("5초 뒤에 다시 이전 화면으로 돌아갑니다.\n");
                Thread.sleep(5000);
            }catch (InterruptedException e){
                System.err.format("IOException: %s%n", e);
            }
            loginMemu();
        } else if (select3 == 3) {
            if (pwd_modify()) {
                System.out.println("----->  비밀번호를 변경하였습니다. \n");
            } else {
                System.out.println("----->  비밀번호가 틀렸습니다. \n");
            }
            loginMemu();
        } else {
            UserManagment.accessedUserDto = new UserDto();
            UserManagment.accessedUserDto.setId("로그인을 해주세요");
            UserManagment.accessedUserDto.setPwd("로그인을 해주세요");
            UserManagment.accessedUserDto.setName("로그인을 해주세요");
            UserManagment.accessedUserDto.setPhone("로그인을 해주세요");
            UserManagment.accessedUserDto.setAddress("로그인을 해주세요");
            UserManagment.accessedUserDto.setBorrowed_book(0);
            mainMemu();
        }
    }

    public Boolean login() {
        Scanner sc = new Scanner(System.in);
        String[] userInput = {"아이디", "비번"};
        String[] userInfo = new String[userInput.length];

        for (int i = 0; i < userInput.length; i++) {
            System.out.print(userInput[i] + " : ");
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
            if (idList.contains(userInfo[0])) {
                String profilePwd = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + userInfo[0] + "'s Info.txt";
                BufferedReader profile = new BufferedReader(new FileReader(profilePwd));
                String profileList = profile.readLine();
                String[] profileSplit = profileList.split("/");

                // 비밀번호가 맞았을 경우
                if (profileSplit[1].equals(userInfo[1])) {
                    System.out.println("——> 로그인이 성공적으로 이루어졌습니다.\n");
                    UserManagment.accessedUserDto = new UserDto();
                    UserManagment.accessedUserDto.setId(userInfo[0]);
                    UserManagment.accessedUserDto.setPwd(profileSplit[1]);
                    UserManagment.accessedUserDto.setName(profileSplit[2]);
                    UserManagment.accessedUserDto.setPhone(profileSplit[3]);
                    UserManagment.accessedUserDto.setAddress(profileSplit[4]);
                    UserManagment.accessedUserDto.setBorrowed_book(0);
                    profile.close();
                    users.close();
                    return true;
                } else {
                    // 비밀번호가 틀렸을 경우
                    System.out.println("——> 비밀번호가 틀렸습니다.\n");
                    profile.close();
                    users.close();
                    return false;
                }
            } else {
                // 아이디가 없을 경우
                System.out.println("——> 등록되지 않은 아이디입니다. \n");
                users.close();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void show_profile() {
        System.out.println("\n---> "+UserManagment.accessedUserDto.getName()+"의 회원 정보");
        System.out.println("  \n  아이디\t ㅣ  이름   ㅣ 핸드폰 번호\t ㅣ 주소 ㅣ 빌린책 개수 ");
        System.out.println(" ⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻⎻");

        System.out.println(" "+UserManagment.accessedUserDto.getId()+"\t   "+UserManagment.accessedUserDto.getName()+"\t\t"+UserManagment.accessedUserDto.getPhone()+"\t\t"+UserManagment.accessedUserDto.getAddress()+"\t\t"+UserManagment.accessedUserDto.getBorrowed_book());
        System.out.println(" ⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽⎽\n");
    }

    // 아이디,비번 찾기
    public void find_id() {
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
            } else {
                // 없다면
                System.out.println("\n------> 찾는 아이디가 없습니다.\n");
            }
            mainMemu();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void find_pwd() {

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
            } else {
                // 없다면
                System.out.println("\n------> 존재하지 않는 아이디입니다.\n");
            }
            mainMemu();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean pwd_modify() {
        String usersPath = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/allUserInfo.txt";
        String profilePath = "/Users/hayeon/IdeaProjects/LibarayManage_Mid/Info/UserInfo/Profile/" + UserManagment.accessedUserDto.getId() + "'s Info.txt";

        File originFile_a = new File(usersPath);
        File originFile = new File(profilePath);
        File tempFile_a = new File(usersPath + ".temp");
        File tempFile = new File(profilePath + ".temp");
        FileInputStream fileOriginStream = null;
        BufferedReader br = null;
        FileOutputStream fileTempStream = null;
        BufferedWriter bw = null;
        FileInputStream fileOriginStream2 = null;
        BufferedReader br2 = null;
        FileOutputStream fileTempStream2 = null;
        BufferedWriter bw2 = null;

        boolean result = false;

        try {
            Scanner sc = new Scanner(System.in);
            fileOriginStream = new FileInputStream(originFile);
            fileTempStream = new FileOutputStream(tempFile);
            br = new BufferedReader(new InputStreamReader(fileOriginStream));
            bw = new BufferedWriter(new OutputStreamWriter(fileTempStream));
            fileOriginStream2 = new FileInputStream(originFile_a);
            fileTempStream2 = new FileOutputStream(tempFile_a);
            br2 = new BufferedReader(new InputStreamReader(fileOriginStream2));
            bw2 = new BufferedWriter(new OutputStreamWriter(fileTempStream2));

            String line;
            String repLine;
            String line2;
            String repLine2;

            String presentPWD = UserManagment.accessedUserDto.getPwd();
            System.out.print("현재 비밀번호를 입력하세요 : ");
            String inputPWD = sc.next();

            if (inputPWD.equals(presentPWD)) {
                System.out.print("변경할 비밀번호를 입력하세요 : ");
                String replacePWD = sc.next();
                UserManagment.accessedUserDto.setPwd(replacePWD);

                while ((line = br.readLine()) != null) {
                    repLine = line.replaceAll(presentPWD, replacePWD);
                    bw.write(repLine, 0, repLine.length());
                    bw.newLine();
                }
                while ((line2 = br2.readLine()) != null) {
                    String[] userSplit = line2.split("/");
                    if(userSplit[0].equals(UserManagment.accessedUserDto.getId())) {
                        repLine2 = line2.replaceAll(presentPWD, replacePWD);
                        bw2.write(repLine2, 0, repLine2.length());
                        bw2.newLine();
                    }else{
                        bw2.write(line2+"\n");
                    }
                }

                result = true;
            } else {
                result = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                br2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                bw.close();
                bw2.close();
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
            if (result) {
                originFile.delete();
                tempFile.renameTo(originFile);
                originFile_a.delete();
                tempFile_a.renameTo(originFile_a);
            }

        }
        return result;
    }
}


// Files.write(Files.createFile(Paths.get("mytest.txt")), contents.replaceFirst("HOME", "HOUSE").getBytes())
//        System.out.println(UserManagment.accessedId.getId());
//        System.out.println(UserManagment.accessedId.getPwd());
//        System.out.println(UserManagment.accessedId.getName());
//        System.out.println(UserManagment.accessedId.getPhone());
//        System.out.println(UserManagment.accessedId.getAddress());



