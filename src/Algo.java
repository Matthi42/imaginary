import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Algo {
    private LinkedList<HashMap<Integer, Koordinate>> oldPositions=new LinkedList<>();
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

    private boolean isInMap(Board board){
        if(oldPositions!=null) {
            block:
            for (HashMap a :
                    oldPositions) {
                for (Koordinate ko :
                        board.getSatelliten()) {
                    if (!a.containsValue(ko))
                        break block;
                }
                return true;
            }
        }
        return false;
    }


    public void solve(Board board){
        Board brett=new Board(board);
        if(isInMap(brett))
            return;

        if(solutions != null){
            solutions.add(brett);
            return;
        }

        if(brett.getAstronaut().equals(brett.getShip())){
            solutions.add(brett);
            return;
        }

        if(brett.moves().size()==0){
            return;
        }

        for (Tupel<Koordinate> move: brett.moves()) {

            putInMap(brett.getSatelliten());
            solve(new Board(brett.moveNew(move)));
        }

    }


    public void calculate() {

        solve(new Board(startBoard));
        for (Board a:
             solutions) {
            System.out.println(a);
        }

    }

    public ArrayList<Board> getSolutions() {
        return solutions;
    }
}
