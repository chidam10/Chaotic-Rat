package cns_project_code;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import cns_project_code.route_add;

public class WANEM_config {
	
	public void WANem_Set(String WANem_ip,String server_ip,String client_ip,int delayTime,int bandwidth, int packetLimit) throws IOException{
		//String WANem_ip = "192.168.0.80";

		String sessionID; //used later on
		
		//String server_ip1= "192.168.56.102";
		String user1= "root";
		String pwd1="admin";
		
	
		//String client_ip2= "192.168.56.101";
		String user2="root";
		String pwd2= "admin";
		

		
		route_add radd=new route_add();
		radd.routeadd(WANem_ip, server_ip, client_ip, user1, pwd1, user2, pwd2);
		
		
		
		String 	sUrlLink = "http://" + WANem_ip + "/WANem/index-advanced.php";
		String urlParameters  = "selInt=eth0&btnAdvanced=Start";
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		
		URL url = new URL(sUrlLink);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput( true );
		conn.setRequestMethod( "POST" );
		conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		
		try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
			wr.write( postData );
		}
				
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		int status = conn.getResponseCode();
		System.out.println("Status = " + status);
		String cookie;
        cookie = conn.getHeaderField("Set-Cookie");
        System.out.println("Cookie=" + cookie);
        
		StringBuffer sb = new StringBuffer();
		String line;

		while((line = in.readLine()) != null){
			sb.append(line);
		}
		in.close();

		System.out.println(sb.toString());

		System.out.println("Hello2");
		
		////////////////////////////////////////////////
		
		String urlParameters2 = "txtLimit1=" + packetLimit + "&selSym1=Yes&";
		urlParameters2 = urlParameters2 + "txtBandwidthAuto1=Other&";
		
		urlParameters2 = urlParameters2 + "txtBandwidth1="+ bandwidth +"&";
		urlParameters2 = urlParameters2 + "txtDelay1=" + delayTime + "&txtDelayJitter1=0&txtDelayCorrelation1=0&selDelayDistribution1=-N/A-&";
		//urlParameters2 = urlParameters2 + "txtLoss1=" + loss + "&txtLossCorrelation1=0&";
		urlParameters2 = urlParameters2 + "txtDup1=0&txtDupCorrelation1=0&";
		urlParameters2 = urlParameters2 + "txtReorder1=0&txtReorderCorrelation1=0&txtGap1=0&";
		urlParameters2 = urlParameters2 + "txtCorrupt1=0&";
		urlParameters2 = urlParameters2 + "selidtyp1=none&txtidtmr1=&txtidsctmr1=&";
		urlParameters2 = urlParameters2 + "selrndtyp1=none&txtrndmttflo1=&txtrndmttfhi1=&txtrndmttrlo1=&txtrndmttrhi1=&";
		urlParameters2 = urlParameters2 + "selrcdtyp1=none&txtrcdmttflo1=&txtrcdmttfhi1=&txtrcdmttrlo1=&txtrcdmttrhi1=&";
		urlParameters2 = urlParameters2 + "txtSrc1=any&txtSrcSub1=&txtDest1=any&txtDestSub1=&txtPort1=any&";
		urlParameters2 = urlParameters2 + "btnApply=Apply+settings";
		
		byte[] postData2       = urlParameters2.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength2 = postData2.length;
		URL url2 = new URL(sUrlLink);
		
		HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
		conn2.setRequestProperty("Cookie", cookie);
		conn2.setDoOutput( true );
		conn2.setRequestMethod( "POST" );
		conn2.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
		conn2.setRequestProperty( "Content-Length", Integer.toString( postDataLength2 ));

		try( DataOutputStream wr = new DataOutputStream( conn2.getOutputStream())) {
			wr.write( postData2 );
		}
		
		BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
		StringBuffer sb2 = new StringBuffer();
		String line2;

		while((line2 = in2.readLine()) != null){
			sb2.append(line2);
		}
		in2.close();

		System.out.println(sb2.toString());

		
	}
}
