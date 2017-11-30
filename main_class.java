package cns_project_code;
import java.io.IOException;

import cns_project_code.WANEM_config;

public class main_class {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//server_shutdown off=new server_shutdown();
		//off.shutdown("192.168.0.81", "root", "admin");
		
	String WANem_ip="192.168.0.80";
		String server_ip="192.168.0.97";
		String client_ip="192.168.0.79";
		int packetLimit= 1000;
		int delayTime=1000;
		
		int bandwidth = 0;
		WANEM_config wan_config= new WANEM_config();
		wan_config.WANem_Set(WANem_ip, server_ip, client_ip, delayTime, bandwidth, packetLimit);
  }

}
