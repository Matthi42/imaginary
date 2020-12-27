import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Algo {
    private LinkedList<HashMap<Integer, Koordinate>> oldPositions;
    private Board startBoard;
    private ArrayList<Board> solutions;
    private LinkedList<Board> positions;

    public Algo(Board board) {
        this.startBoard = board;
    }

    private void putInMap(ArrayList<Koordinate> test) {
        HashMap<Integer, Koordinate> a = new HashMap<>();
        for (Koordinate koor :
                test) {
            a.put(koor.hashCode(), koor);
        }
        this.oldPositions.push(a);
    }


    public void calculate() {

        positions.push(startBoard);
        boolean flag=true;

        for (Board current : positions) {
            ArrayList<Tupel<Koordinate>> moves = current.moves();
            for (Tupel<Koordinate> next : moves) {
                loop:
                for (HashMap<Integer, Koordinate> old : oldPositions) {
                    for (Koordinate ko : current.moveNew(next).getSatelliten()) {
                        if (old.containsValue(ko)/*&&  Astronaut an der selben stelle*/) {
                            flag=false;
                            break loop;
                        }
                    }
                }
                if(flag) {
                    positions.push(current.moveNew(next));
                    putInMap(new ArrayList<>(current.moveNew(next).getSatelliten()));

                    flag=true;
                }
                //wenn der Zug das raumschiff erreicht wird das gelöste Bord objekt in solutions geschriben
                if(current.getAstronaut().equals(current.getShip())){
                    //this.solutions.add();

                }
            }
        }

        //sind alle möglichkeiten durchgearbeitet muss die liste solutions ausgegeben werden

    }

    public ArrayList<Board> getSolutions() {
        return solutions;
    }
}
