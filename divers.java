public class divers extends Thread {
    String type;
    int id;
    boolean done = false;
    public divers(String type, int id){
        this.type = type;
        this.id = id;
    }
    public void run() {
        while(!done){
            while(hq.isSigningUp()){
                
            }
            if(type == "super"){
                System.out.println("Super Citizen ID:"+Integer.toString(id) +" is signing up.");
            }else if (type == "reg") {
                System.out.println("Regular Citizen ID:"+Integer.toString(id) +" is signing up.");
            }
            group();
            hq.doneSigningUp();
        }
      }
    void group(){
        int s = hq.scount;
        int r = hq.rcount;
        int total = s+r;
        if (total < 4) {
            if(type == "super"){
                if (s<2) {
                    hq.scount++;
                    done = true;
                    System.out.println("Super Citizen ID:"+Integer.toString(id) +" has joined team "+ Integer.toString(hq.teamid));
                }
            }else if (type == "reg") {
                if(s>0 || r<3){
                    hq.rcount++;
                    done = true;
                    System.out.println("Regular Citizen ID:"+Integer.toString(id) +" has joined team "+ Integer.toString(hq.teamid));
                }
            }
        }
    }
}
