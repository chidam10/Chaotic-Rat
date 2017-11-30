package cns_project_code;


import java.io.*;
public class cpu_usage
{
    static String cmdTop = "top -n 2 -b -d 0.2";
    
    
    // returns user cpu usage 
    public static void main()    {
        double cpu = -1;
        try
        {
            // start up the command in child process
            String cmd = cmdTop;
            Process child = Runtime.getRuntime().exec(cmd);

            // hook up child process output to parent
            InputStream lsOut = child.getInputStream();
            InputStreamReader r = new InputStreamReader(lsOut);
            BufferedReader in = new BufferedReader(r);

            // read the child process' output
            String line;
            int emptyLines = 0;
            while(emptyLines<3)
            {
                line = in.readLine();
                if (line.length()<1) emptyLines++;
            }
            in.readLine();
            in.readLine();
            line = in.readLine();
            System.out.println("Parsing line "+ line);
            String delims = "%";
            String[] parts = line.split(delims);
            System.out.println("Parsing fragment " + parts[0]);
            delims =" ";

            parts = parts[0].split(delims);
            cpu = Double.parseDouble(parts[parts.length-1]);
            System.out.println(cpu);
        }
        catch (Exception e)
        { // exception thrown
            System.out.println("Command failed!");
        }
        
    }
}