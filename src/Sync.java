import java.util.ArrayList;


public class Sync extends Thread{
	ArrayList<ClientThread> threadList;
	//private Boolean ide = true;
	private String smjer = "right";
	//private Integer i = 1;
	
	Sync(String threadName, ArrayList<ClientThread> threadList){
		super(threadName);
		this.threadList = threadList;
	}
	
	public void promjeniSmjer(String smjer){
		this.smjer = smjer;
		System.out.println(smjer);
	}
	
	/*@SuppressWarnings("unused")
	private void stopSync(){
		ide = false;
	}*/
	
	public void posaljiNa(Integer i){
		ClientThread c = threadList.get(--i);
		c.sendMessage(smjer);
	}
	
	public void run(){
		Integer cn = 1;
		/*try {
			sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}*/
		//salje redne brojeve i ukupan broj uredaja
		for(ClientThread t :threadList){
			t.sendMessage(Integer.toString(cn++) + "," + Integer.toString(threadList.size()));
			t.sync(this);
		}

		/*
		while(ide){
			for(ClientThread t :threadList){
				t.sendMessage(Integer.toString(i));
			}
			i++;
			try {
				sleep(100, 0); //sleep(long millis, int nanos)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		*/
	}

}
