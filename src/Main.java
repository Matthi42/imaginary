import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {


    public static void main(String args[]){
        ArrayList<Koordinate> Sateliten= new ArrayList<>(Arrays.asList(
                new Koordinate(2, 0),
                new Koordinate(3, 4)
        ));

        Board a=new Board(new Koordinate(3,3), Sateliten,new Koordinate(4,0));
        System.out.println(a.toString());


        Algo alex=new Algo(a);
        alex.calculate();

    }
}
