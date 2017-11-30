package cns_project_code;

import java.io.IOException;

public class server_shutdown {

	//public static void main(String[] args) {
		public void shutdown(String host,String username,String password) throws IOException{
		SSHReadFile lr = new SSHReadFile();

		
		String command = "shutdown";
		System.out.println("-----------------------------------Machine "+host+" is shutting down--------------------------");
		lr.SSHConnection(host, username, password, command);
		System.out.println("-----------------------------------------------------------------------------------");

	}

}
