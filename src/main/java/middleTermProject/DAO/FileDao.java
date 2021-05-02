package middleTermProject.DAO;

import java.io.IOException;

public interface FileDao {

    public boolean updateLendFile(String FilePath, String id) throws IOException;
}
