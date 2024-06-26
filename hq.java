import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hq {
    static public volatile int scount;
    static public volatile int rcount;
    static public volatile boolean signingup = false;
    static List<divers> allDivers = new ArrayList<divers>();
    static public volatile int teamid = 0;
    static int stotal, rtotal;
    public static synchronized boolean isSigningUp(){
        boolean temp = signingup;
        if(!signingup){
            signingup = true;
        }
        return temp;
    }
    public static synchronized void doneSigningUp(){
        signingup = false;
    }
    public static void main(String[] args) {
        Scanner inputs = new Scanner(System.in);
        System.out.println("Enter number of Super Citizens: ");
        stotal = inputs.nextInt();
        System.out.println("Enter number of Regular Citizens: ");
        rtotal = inputs.nextInt();
        
        for (int i = 0; i < stotal; i++) {
            divers t = new divers("super",allDivers.size());
            t.start();
            allDivers.add(t);
        }
        for (int i = 0; i < rtotal; i++) {
            divers t = new divers("reg",allDivers.size());
            t.start();
            allDivers.add(t);
        }
        while (true) {
            if (scount+rcount==4){
                isSigningUp();
                if(sendTeamToMission()){
                    break;
                }
                doneSigningUp();
            }
        }
        inputs.close(); 
      }
      public synchronized static boolean sendTeamToMission(){
        System.out.println("team "+ Integer.toString(teamid) +" is ready and now launching to battle (sc: "+ Integer.toString(scount) +" | rc: "+ Integer.toString(rcount)+")");
        teamid++;
        stotal -= scount;
        rtotal -= rcount;
        scount=0;
        rcount=0;
        if (stotal+rtotal<4 || stotal==0 || rtotal < 2) {
            for (divers t : allDivers) {
                t.stop();
            }
            System.out.println("\nResults:\nTeams Sent: "+ Integer.toString(teamid) +" \nRest of the citizens are sent home.(sc: "+ Integer.toString(stotal) +" | rc: "+ Integer.toString(rtotal)+")");
            return true;
        }
        return false;
      }
}
