package cns_project_code;

public class route_add {

	public void routeadd(String sHostName,String server_ip,String client_ip,String user1,String pwd1,String user2,String pwd2) {

		SSHReadFile lr = new SSHReadFile();
		
        String command1="route add -host "+client_ip+" netmask 0.0.0.0 gw "+sHostName;
        String command2="route add -host "+server_ip+" netmask 0.0.0.0 gw "+sHostName;
        
		System.out.println("-----------------------------------adding route machine 1  "+server_ip+"--------------------------");

		lr.SSHConnection(server_ip, user1, pwd1, command1);

		System.out.println("-----------------------------------done------------------------------------------------");

		System.out.println("-----------------------------------adding route machine 2 "+client_ip+"--------------------------");

		lr.SSHConnection(client_ip, user2, pwd2, command2);

		System.out.println("-----------------------------------done------------------------------------------------");

		// TODO Auto-generated method stub

	}

}
