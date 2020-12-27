import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {


    public static void main(String args[]){
        LinkedList<Koordinate> Sateliten= new LinkedList<>(Arrays.asList(
                new Koordinate(5, 0),
                new Koordinate(5, 2),
                new Koordinate(5,4)
        ));

        Board a=new Board(new Koordinate(4,1), Sateliten,new Koordinate(2,2));
        System.out.println(a.toString());
        System.out.println(a.moves());
        ArrayList firstMove =a.moves();
        a.move((Tupel<Koordinate>) firstMove.get(0));
        System.out.println(a.toString());
        System.out.println(a.moves());
        System.out.println(Sateliten);

        Algo alex=new Algo(a);

    }
}
