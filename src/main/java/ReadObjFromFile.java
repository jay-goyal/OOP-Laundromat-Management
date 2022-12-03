import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
 
public class ReadObjFromFile {
 
    public static Object ReadObjectFromFile(String ID) {
    	Path relPathOut = Paths.get("files/"+ID+".txt");
    	Path absPathOut = relPathOut.toAbsolutePath();
 
        try {
 
            FileInputStream fileIn = new FileInputStream(absPathOut.toString());
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Student obj = (Student)objectIn.readObject();
            
 
            System.out.println("The Object has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
