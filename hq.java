import java.util.ArrayList;
import java.util.List;

public class hq {
    static public volatile int scount;
    static public volatile int rcount;
    static private boolean signingup = false;
    static List<divers> allDivers = new ArrayList<divers>();
    static public volatile int teamid = 0;
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
        int stotal = 2;
        int rtotal = 6;
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
                System.out.println("team "+ Integer.toString(teamid) +" is ready and now launching to battle (sc: "+ Integer.toString(scount) +" | rc: "+ Integer.toString(rcount));
                teamid++;
                stotal -= scount;
                rtotal -= rcount;
                scount=0;
                rcount=0;
                if (stotal+rtotal<4 || stotal==0) {
                    for (divers t : allDivers) {
                        t.stop();
                    }
                    break;
                }
                doneSigningUp();
            }
        }
      }
}
