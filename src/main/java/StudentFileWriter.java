import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class StudentFileWriter {
    private final File file;
    public final Object writeLock = new Object();

    public StudentFileWriter() {
        Path relPath = Paths.get("files/Student_data.txt");
        Path absPath = relPath.toAbsolutePath();
        file = new File(absPath.toUri());
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkUserExists(String userName, String ID) {
        System.out.println("RUNNING");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            //now read the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] student_data = line.split(",");

                if (student_data[0].equals(userName) && student_data[4].equals(ID)) {
                    return true;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            assert scanner != null;
            scanner.close();
        }
        return false;
    }

    public void regUserToFile(String userName, String fullName, String password, String secretWord,String ID,String phoneNumber, Hostel hostel) throws IOException {
        Writer out = null;
        try {
            String string_data=userName+","+fullName+","+password+","+secretWord+","+ID+","+phoneNumber+","+hostel;
            Path relPathOut = Paths.get("files/Student_data.txt");
            Path absPathOut = relPathOut.toAbsolutePath();
            out = new FileWriter(absPathOut.toString(), true);
            out.write(System.lineSeparator());
            out.write(string_data);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            assert out != null;
            out.close();
        }
    }

    public void writeStudentToFile(Student student, boolean callIfAlreadyExists) throws IOException {
        String fileName = "files/" + student.userName + "_" + student.getID() + ".txt";
        Path relPathStud = Paths.get(fileName);
        Path absPathStud = relPathStud.toAbsolutePath();
        try {
            File myObj = new File(absPathStud.toUri());
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                FileOutputStream fos = new FileOutputStream(absPathStud.toString(), true);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(student);
                oos.close();
                fos.close();
            } else {
                System.out.println("File already exists.");
                if (callIfAlreadyExists) {
                    FileOutputStream fos = new FileOutputStream(absPathStud.toString(), true);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(student);
                    oos.close();
                    fos.close();
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
