package tokenring;

import java.util.LinkedList;
import java.util.Queue;

public class WorkstationImplementation extends Workstation {
	
	Queue<Message> qlist;
	private NetworkInterface network;

	public WorkstationImplementation(NetworkInterface nic) {
            // TODO
		network = nic;
        qlist = new LinkedList<Message>();
		
	}

	public NetworkInterface getNIC() {
            // TODO
            return network;
	}
	
	@Override
	public int compareTo(Workstation o) {
            // TODO
		return id-o.getId();
	}
	
	@Override
	public boolean equals(Object obj) {
        // TODO
		if (obj instanceof Workstation) {
            Workstation anything = (Workstation) obj;
            return id == anything.getId();
		}
		return false;
		}

	public void sendMessage(Message m) {
        // TODO
		qlist.add(m);
	}

	@Override
	public void tick() {
        // TODO
		 if (network.hasFrame()) {
			 Frame doingframe = network.read();
             if (doingframe.isTokenFrame()) {
            	 if (qlist.isEmpty()) { 
            		 network.write(doingframe);
            	 } 
            	 else {
                     Message writtenmessage = qlist.remove();
                     DataFrame df = new DataFrame(writtenmessage);
                     
                     network.write(df);
                     incMsgSent();
            	 }
              } 
              else if (doingframe.isDataFrame()) {
            	  
            	  DataFrame df = (DataFrame) doingframe;
            	  Message writtenmessage = df.getMessage();
            	  
            	  if (writtenmessage.getReceiver() == id) {
            		  df.setReceived(true);
            		  network.write(df);
                      incMsgRcvd();
            	  } 
            	  else if (!df.wasReceived() && writtenmessage.getSender() == id) {
                      network.write(TokenFrame.TOKEN);
            	 }
            	  else if (df.wasReceived() && writtenmessage.getSender() == id) {
            		  network.write(TokenFrame.TOKEN);
                      incMsgDelivered();
                  }
            	 else {
            		 network.write(doingframe);
            	 }
              	 }
             }
		}
}
