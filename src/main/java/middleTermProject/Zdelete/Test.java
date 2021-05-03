//package middleTermProject.Zdelete;
//
//import middleTermProject.DTO.BookDto;
//import middleTermProject.System.UserSystem;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class Test {
//
//    public void testPrint() {
//
////        String profileSplit = "[[아이무서워!, 2021-05-02, 2021-05-09]]";
////        ArrayList<String> profile5 = new ArrayList<String>();
////        profile5.add(profileSplit);
////        System.out.println(profileSplit.substring(1,profileSplit.length()-1));
//
//
////        ArrayList<String> profile5;
////        StringBuilder s = new StringBuilder();
////        String profileSplit = "[[아이무서워!, 2021-05-02, 2021-05-09]]";
////        String.substring()
//
////        ArrayList<String> profile5 = new ArrayList<String>(Arrays.asList(profileSplit.split("")));
////        System.out.println(s);
////
////        ArrayList<String> profile = new ArrayList<String>();
////        profile.add(profileSplit);
////        profile = profile.get(0);
////        System.out.println();
//
////        String str = "[[아이무서워!, 2021-05-02, 2021-05-09]]";
////        List<String> list = Arrays.asList(str.split("],"));
////        ArrayList<String> profile5 = new ArrayList<String>(Arrays.asList(str));
////
////        System.out.println(profile5);
////
////        ArrayList<String> newLendBook = new ArrayList<>();
////        newLendBook.add("아이무서워!");
////        newLendBook.add("2021-05-02");
////        newLendBook.add("2021-05-09");
////        System.out.println(newLendBook);  // [아이무서워!, 2021-05-02, 2021-05-09]
////
////        StringBuilder s = new StringBuilder();
////        for (String book : newLendBook) {
////            s.append(book);
////            s.append(",");
////        }
////        System.out.println(s);
//
////
////        ArrayList<List> newLendBook = new ArrayList<>();
////        newLendBook.add(LibraryManagerSystem.accessedBookDto.getBook_title());
////        newLendBook.add(lend_date);
////        newLendBook.add(return_date);
//
//
////        ArrayList<List> TestList = new ArrayList<List>();
////        ArrayList<String> a = new ArrayList<>();
////        ArrayList<String> b = new ArrayList<>();
////
////        Calendar cal = Calendar.getInstance();
////        SimpleDateFormat todayformat = new SimpleDateFormat ( "yyyy-MM-dd");
////        Date time = new Date();
////        String today = todayformat.format(time);
////
////        cal.add(cal.DATE,+7);
////        String next = todayformat.format(cal.getTime());
////
////
////        a.add("책이름");
////        a.add(today);
////        a.add(next);
////
////        TestList.add(a);
////
////        System.out.println(TestList);
////        System.out.println(TestList.get(0).get(0));
//    }
//
//}
//
//public class FileSystem {
//
//    @Autowired
//    UserSystem userSystem;
//
//    // 파일 생성
//    private void CreateFile(String FilePath) {
//        try {
//            System.out.println(FilePath);
//
//            int nLast = FilePath.lastIndexOf("\\");
//            String strDir = FilePath.substring(0, nLast);
//            String strFile = FilePath.substring(nLast + 1, FilePath.length());
//
//            File dirFolder = new File(strDir);
//            dirFolder.mkdirs();
//            File f = new File(dirFolder, strFile);
//            f.createNewFile();
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    // 파일 수정
//    private void UpdateFile(String FilePath, String Text)
//    {
//        try
//        {
//            File f = new File(FilePath);
//            if (f.exists() == false)
//            {
//                // 파일이 존재하지 않는 경우 파일을 만들ㄴ다.
//                CreateFile(FilePath);
//            }
//
//            // 파일 읽기
//            String fileText = ReadFileText(f);
//            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(f));
//            Text = fileText + "\r\n" + Text;
//            // 파일 쓰기
//            buffWrite.write(Text, 0, Text.length());
//            // 파일 닫기
//            buffWrite.flush();
//            buffWrite.close();
//        }
//        catch (Exception ex)
//        {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    // 파일 테스트 읽기
//    private String ReadFileText(File file) {
//        String strText = "";
//        int nBuffer;
//        try {
//            BufferedReader buffRead = new BufferedReader(new FileReader(file));
//            while ((nBuffer = buffRead.read()) != -1) {
//                strText += (char) nBuffer;
//            }
//            buffRead.close();
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return strText;
//    }
//
//    public void UpdateInfoFile(String FilePath, String Text) throws IOException {
//        boolean result = false;
//        File originFile = new File(FilePath);
//        File tempFile = new File(FilePath + ".temp");
//
//        FileInputStream fileOriginStream = new FileInputStream(originFile);
//        BufferedReader br = new BufferedReader(new InputStreamReader(fileOriginStream));
//        FileOutputStream fileTempStream = new FileOutputStream(tempFile);
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileTempStream));
//
//        String line;
//        String repLine;
//
//        while ((line = br.readLine()) != null) {
//            String[] userSplit = line.split("/");
//            if(userSplit[2].equals(Text)){ // 이름
//                repLine = line.replace(userSplit[2],UserSystem.accessedUserDto.getName());
//                bw.write(repLine, 0, repLine.length());
//                bw.newLine();
//            }else if(userSplit[3].equals(Text)){  // 번호
//                repLine = line.replace(userSplit[3],UserSystem.accessedUserDto.getPhone());
//                bw.write(repLine, 0, repLine.length());
//                bw.newLine();
//
//            }else if(userSplit[4].equals(Text)){  // 주소
//                repLine = line.replace(userSplit[4],UserSystem.accessedUserDto.getAddress());
//                bw.write(repLine, 0, repLine.length());
//                bw.newLine();
//
//            }else{
//                bw.write(line + "\n");
//            }
//            bw.flush();
//            result = true;
//        }
//        if (result) {
//            br.close();
//            bw.close();
//            originFile.delete();
//            tempFile.renameTo(originFile);
//            System.out.println("--------> 수정이 성공적으로 로드되었습니다.");
//        }
//    }
//}

