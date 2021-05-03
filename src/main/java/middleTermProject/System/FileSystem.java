package middleTermProject.System;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

public class FileSystem {

    @Autowired
    UserSystem userSystem;

    public void UpdateInfoFile(String FilePath, String Text) throws IOException {
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
                if(userSplit[2].equals(Text)){ // 이름
                    repLine = line.replace(userSplit[2],UserSystem.accessedUserDto.getName());
                    bw.write(repLine, 0, repLine.length());
                    bw.newLine();
                }else if(userSplit[3].equals(Text)){  // 번호
                    repLine = line.replace(userSplit[3],UserSystem.accessedUserDto.getPhone());
                    bw.write(repLine, 0, repLine.length());
                    bw.newLine();

                }else if(userSplit[4].equals(Text)){  // 주소
                    repLine = line.replace(userSplit[4],UserSystem.accessedUserDto.getAddress());
                    bw.write(repLine, 0, repLine.length());
                    bw.newLine();

                }else{
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
            System.out.println("--------> 수정이 성공적으로 로드되었습니다.");
        }
    }
}
