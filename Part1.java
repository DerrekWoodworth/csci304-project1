import java.io.* ;
import java.net.* ;
import java.util.* ;

public final class WebServer
{
	public static void main(String argv[]) throws Exception
	{
	// Establish the listen socket.
	    ServerSocket welcomeSocket = new ServerSocket(port);
	    
	    
	    // Process HTTP service requests in an infinite loop.
	    while (true) {
		// Listen for a TCP connection request.
	    Socket connectionSocket = welcomeSocket.accept();
	
// Process HTTP service requests in an infinite loop.
while (true) {
	// Listen for a TCP connection request.
}

// Construct an object to process the HTTP request message.
HttpRequest request = new HttpRequest( ? );

// Create a new thread to process the request.
Thread thread = new Thread(request);

// Start the thread.
thread.start();

	}
}


final class HttpRequest implements Runnable
{
	final static String CRLF = "\r\n";
	Socket socket;

	// Constructor
	public HttpRequest(Socket socket) throws Exception 
	{
		this.socket = socket;
	}

	
	private void processRequest() throws Exception
	{
		//use I/O stream and read a line
		//parse the line 
// check is file exists
		. . .
	}
}

// Implement the run() method of the Runnable interface.
public void run()
{
	try {
		processRequest();
	} catch (Exception e) {
		System.out.println(e);
	}
}

// Close streams and socket.
os.close();
br.close();
socket.close();



