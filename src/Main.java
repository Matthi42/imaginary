import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {


    public static void main(String args[]){
        //LinkedList<Koordinate> Sateliten= new LinkedList<>(Arrays.asList(
        //        new Koordinate(2, 0),
        //        new Koordinate(3, 4)
        //));
//
        //Board a=new Board(new Koordinate(3,3), Sateliten,new Koordinate(4,0));
        //System.out.println(a.toString());
//
//
        //Algorithm alex=new Algorithm(a);
        //alex.solve();


        Koordinate a=new Koordinate(1,2);
        Koordinate b=new Koordinate(3,4);
        Tupel<Koordinate> t1=new Tupel<>(a,b);
        Tupel<Koordinate> t2=t1;
        Tupel<Koordinate> t3=(Tupel<Koordinate>) t1.clone();
        t1.setFirst(new Koordinate(5,6));
        System.out.println("t1= "+t1.toString()+"\nt2= "+t2.toString()+"\nt3= "+t3.toString()+"\na= "+a.toString());
    }
}
