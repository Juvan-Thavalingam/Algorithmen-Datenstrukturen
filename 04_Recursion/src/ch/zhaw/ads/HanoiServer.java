package ch.zhaw.ads;

public class HanoiServer {

    public void moveDisk(int n){
        if(n == 1){
            System.out.println("Lege die oberste Scheibe von Turm A auf Turm B");
        } else if(n == 2){
            System.out.println("Lege die oberste Scheibe von Turm A auf Turm C");
            System.out.println("Lege die oberste Scheibe von Turm A auf Turm B");
            System.out.println("Lege die oberste Scheibe von Turm C auf Turm B");
        } else {

        }
    }
}
