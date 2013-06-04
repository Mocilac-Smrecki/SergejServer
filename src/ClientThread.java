import java.io.*;
import java.net.*;


public class ClientThread extends Thread{
	private Socket socket = null;
	private PrintWriter outputMessage;
	private String inputMessage;
	private Boolean ide = true;
	Sync s;
	
	public void sync(Sync s){
		this.s =s;
	}
	
	public ClientThread(String threadName, Socket socket){
		super(threadName);
		this.socket = socket;
	}
	
    public void sendMessage(String message){
        if (outputMessage != null && !outputMessage.checkError()) {
        	outputMessage.println(message);
        	outputMessage.flush();
        }
    }
    
    public void stopListeningForMessages(){
    	ide = false;
    }
	
	public void run(){
		try {
			outputMessage = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			//sendMessage("Konekcija sa serverom uspjesno uspostavljena.");
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            		
			while(ide){
				inputMessage = input.readLine();
				if (inputMessage != null)
				{
					System.out.println(inputMessage);
					if(inputMessage.equals("left")) s.promjeniSmjer(inputMessage);
					else if (inputMessage.equals("right")) s.promjeniSmjer(inputMessage);
					else s.posaljiNa(Integer.parseInt(inputMessage));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}/*finally{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}
}
