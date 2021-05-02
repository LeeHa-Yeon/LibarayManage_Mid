package middleTermProject.System;

import middleTermProject.DAO.FileDao;
import middleTermProject.DTO.BookDto;

import java.io.*;

public class BookSystem implements FileDao {

    public static BookDto accessedBookDto = null;

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

        while ((line = br.readLine()) != null) {
            String[] userSplit = line.split("/");
            if(userSplit[1].equals(id)) {
                repLine = line.replaceAll("대여가능","대여중");
                bw.write(repLine, 0, repLine.length());
                bw.newLine();
            }else {
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
}
