import java.util.Optional;
import java.io.File;
import java.io.FileInputStream;

public class FileHandler {
    private Optional<File> file;

    public FileHandler(String fileName){
        file = Optional.of(new File(fileName));
    }
    
    public Optional<Integer> getFileLength(){
        return
                .filter(f -> f.exists() && !f.isDirectory());
    }
    
    public Optional<byte[]> getFileContents() {
       this.getFileLength().map(FileHandler::fileContents); 
    }
    
    private static byte[] fileContents(File f) {
        byte[] file = new byte[f.length()];
        try {
            FileInputStream fileIn = new FileInputStream(f);
            fileIn.read(file);
        } catch (IOException e) {
            System.err.println("Error reading the file..");
            e.printStackTrace();
        } finally {
            fileIn.close();
        }
        return file;
    }
}
