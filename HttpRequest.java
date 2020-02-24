import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;
import java.lang.Runnable;

public class HttpRequest implements Runnable{
    private Socket connection;
    
    public HttpRequest(Socket connect) {
        connection = connect;
    }
    
    // Handle the connection
    public void run() {
        // Initalize the IO streams
        BufferedReader in;
        PrintWriter out;
        BufferedOutputStream dataOut;
        
        // Surround setting up the IO wih try/catch
        try {
            in = new BufferedReader(new InputStreamReader(connect.getInputStream));
            out = new PrintWriter(connect.getOutputStream());
            dataOut = new BufferedOutputStream(connect.getOutputStream);
        } catch (IOException ioe) {
            System.err.println("Server error : " + ioe);
            ioe.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                dataOut.close();
                connect.close();
                // This conncetion is severed, end the thread
                return;
            } catch (Exception e) {
                System.err.println("Error closing stream : " + e.getMessage());
            } 
        }
        // The streams have been successfully initalized
        String input = in.readLine();
        StringTokenizer parser = new StringTokenizer(input);
        // HTTP Method
        String method = parser.nextToken().toUpperCase();
        // File Requested
        String fileName = parser.nextToken().toLowerCase();
        
        // Get File
        FileHandler fileHandler = new FileHandler(fileName); 
        int fileLength = fileHandler.getFileLength().orElse(-1);
        byte[] fileData = fileHandler.getFileContents().orElse(null);
	    String contentMimeType = "text/html";
		
	    // we send HTTP Headers with data to client
	    out.println("HTTP/1.1 200 OK");
	    out.println("Server: Java HTTP Server from Danny and Derrek: 1.0");
	    out.println("Date: " + new Date());
	    out.println("Content-type: " + contentMimeType);
	    out.println("Content-length: " + fileLength);
	    out.println(); // blank line between headers and content, very important !
	    out.flush(); // flush character output stream buffer
	    // file
	    dataOut.write(fileData, 0, fileLength);
	    dataOut.flush();
        
    
}
