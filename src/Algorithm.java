import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Algorithm {
    private static LinkedList<HashMap<Integer, Koordinate>> oldPositions=new LinkedList<>();
    private Board startBoard;
    private LinkedList<Tup<LinkedList<Tupel<Koordinate>>,Board>> Warteschlange=new LinkedList<>();
    private LinkedList<Board> solutions=new LinkedList<>();


    public Algorithm(Board startBoard){
        this.startBoard=startBoard;
        this.Warteschlange.add(new Tup<>(startBoard.moves(),startBoard));
    }

    public void solve(){
        while (Warteschlange.peek()!=null){
            if(isInMap(Warteschlange.peek().getValue())){
                Warteschlange.remove();
            }
            Board b=new Board();
            while (Warteschlange.peek().getKey().peek()!=null){

                //es muss das resultierende boardobjekt eingefügt werden welches seinen vorgänger kennt und die moveque gefüllt hat

                b=new Board(Warteschlange.peek().getValue());
                Board a=move(Warteschlange.peek().getKey().peek(),b);
                a.setVorganger(Warteschlange.peek().getValue().getVorganger());
                a.addToVorganger(new Board(Warteschlange.peek().getValue()));
                putInMap(a.getSatelliten());
                this.Warteschlange.add(new Tup<>(a.moves(),a));
                this.Warteschlange.peek().getKey().remove();
            }
            if(Warteschlange.peek().getValue().getAstronaut().equals(Warteschlange.peek().getValue().getShip())){
                this.solutions.add(Warteschlange.peek().getValue());
            }
            Warteschlange.remove();
        }
        for(Board a: solutions.peek().getVorganger() ){
            System.out.println(a);
        }
    }

    private Board move(Tupel<Koordinate> a,Board retur) {
        Board returnBoard=new Board(retur);
        Iterator<Koordinate> iterator = returnBoard.getSatelliten().iterator();
        int i=0;
        while (iterator.hasNext()) {
            if(iterator.next().equals(a.getFirst())){
                returnBoard.getSatelliten().set(i,a.getSecond());
                break;
            }
            i++;
        }
        if(returnBoard.getBoard()[a.getFirst().getX()][a.getFirst().getY()]=='S') {
            returnBoard.setBoardKo('S',a.getSecond().getX(),a.getSecond().getY()); //  set
        }
        else {
            returnBoard.setBoardKo('A',a.getSecond().getX(),a.getSecond().getY());
            returnBoard.setAstronaut(new Koordinate(a.getSecond().getX(),a.getSecond().getY()));
        }
        returnBoard.setBoardKo(' ',a.getFirst().getX(),a.getFirst().getY());//set

        return new Board(returnBoard);
    }






    private void putInMap(LinkedList<Koordinate> test) {
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
}
