package middleTermProject;

import java.io.*;
public class FileSystem {
    // 파일 생성
    private void CreateFile(String FilePath)
    {
        try
        {
            System.out.println(FilePath);

            int nLast = FilePath.lastIndexOf("\\");
            String strDir = FilePath.substring(0, nLast);
            String strFile = FilePath.substring(nLast+1, FilePath.length());

            File dirFolder = new File(strDir);
            dirFolder.mkdirs();
            File f = new File(dirFolder, strFile);
            f.createNewFile();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    // 파일 테스트 읽기
    private String ReadFileText(File file)
    {
        String strText = "";
        int nBuffer;
        try
        {
            BufferedReader buffRead = new BufferedReader(new FileReader(file));
            while ((nBuffer = buffRead.read()) != -1)
            {
                strText += (char)nBuffer;
            }
            buffRead.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        return strText;
    }

    // 파일 수정
    private void UpdateFile(String FilePath, String Text)
    {
        try
        {
            File f = new File(FilePath);
            if (f.exists() == false)
            {
                // 파일이 존재하지 않는 경우 파일을 만들ㄴ다.
                CreateFile(FilePath);
            }

            // 파일 읽기
            String fileText = ReadFileText(f);
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter(f));
            Text = fileText + "\r\n" + Text;
            // 파일 쓰기
            buffWrite.write(Text, 0, Text.length());
            // 파일 닫기
            buffWrite.flush();
            buffWrite.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


}
