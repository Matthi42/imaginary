import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {


    public static void main(String args[]){
        LinkedList<Koordinate> Sateliten= new LinkedList<>(Arrays.asList(
                new Koordinate(1, 2),
                new Koordinate(4, 2),
                new Koordinate(4, 4)
        ));

        Board a=new Board(new Koordinate(3,3), Sateliten,new Koordinate(1,4));
        System.out.println(a.toString());


        Algorithm alex=new Algorithm(a);
        alex.solve();

    }
}
