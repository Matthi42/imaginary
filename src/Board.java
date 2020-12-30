import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Board implements Cloneable{
    private char[][] board;
    private Koordinate ship;
    private LinkedList<Koordinate> satelliten;
    private Koordinate astronaut;
    private LinkedList<Board> vorganger=new LinkedList<>();



    public Board(Koordinate ship, LinkedList<Koordinate> satelliten, Koordinate astronaut) {
        this.board = new char[7][7];
        for (int i = 0; i < 7; i++) {
            for (int u = 0; u < 7; u++) {
                board[i][u] = ' ';
            }
        }
        this.ship = ship;
        satelliten.add(astronaut);
        this.satelliten = new LinkedList<>();
        this.setSatelliten(satelliten);
        for (Koordinate a :
                satelliten) {
            board[a.getX()][a.getY()] = 'S';
        }
        board[astronaut.getX()][astronaut.getY()] = 'A';
        this.ship = ship;
        this.astronaut=astronaut;
    }

    public Board(Board b){
        this.board=b.getBoard();
        this.astronaut=new Koordinate();
        this.setAstronaut(b.getAstronaut());
        this.ship=b.getShip();
        this.satelliten=new LinkedList<>();
        for (Koordinate o:b.getSatelliten()) {
            this.satelliten.add(o);
        }
        ;
    }
    public Board(){}


    public LinkedList<Tupel<Koordinate>> moves() {
        LinkedList<Tupel<Koordinate>> moves = new LinkedList<>();
        boolean flag = true;
        for (Koordinate sat : satelliten) {
            for (Koordinate ziel : satelliten) {
                if (!sat.equals(ziel)) {
                    if (sat.getX() == ziel.getX() && Math.abs(sat.getY()-ziel.getY())>1) {
                        if (sat.getY() > ziel.getY()) {
                            for (int i = ziel.getY() + 1; i < sat.getY(); i++) {
                                if (((board[ziel.getX()][i] == 'S') || (board[ziel.getX()][i] == 'A'))) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                moves.add(new Tupel<>(sat, new Koordinate(ziel.getX(), ziel.getY() + 1)));
                        } else {
                            for (int i = sat.getY() + 1; i < ziel.getY(); i++) {
                                if (((board[ziel.getX()][i] == 'S') || (board[ziel.getX()][i] == 'A'))) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                moves.add(new Tupel<>(sat, new Koordinate(ziel.getX(), ziel.getY() - 1)));
                        }
                        flag = true;
                    }

                    if (sat.getY() == ziel.getY()&& Math.abs(sat.getX()-ziel.getX())>1) {
                        if (sat.getX() > ziel.getX()) {
                            for (int i = ziel.getX() + 1; i < sat.getX(); i++) {
                                if (((board[i][ziel.getY()] == 'S') || (board[i][ziel.getY()] == 'A'))) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                moves.add(new Tupel<>(sat, new Koordinate(ziel.getX() + 1, ziel.getY())));
                        } else {
                            for (int i = sat.getX() + 1; i < ziel.getX(); i++) {
                                if (((board[i][ziel.getY()] == 'S') || (board[i][ziel.getY()] == 'A'))) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                moves.add(new Tupel<>(sat, new Koordinate(ziel.getX() - 1, ziel.getY())));
                        }
                        flag = true;

                    }

                }

            }

        }
        return moves;
    }


    /*public void move(Tupel<Koordinate> a) {
        Iterator<Koordinate> iterator = satelliten.iterator();
        int i=0;
        while (iterator.hasNext()) {
            if(iterator.next().equals(a.getFirst())){
                satelliten.set(i,a.getSecond());
                break;
            }
            i++;
        }
        if(board[a.getFirst().getX()][a.getFirst().getY()]=='S')
            this.board[a.getSecond().getX()][a.getSecond().getY()]='S';
        else
            this.board[a.getSecond().getX()][a.getSecond().getY()]='A';
        this.board[a.getFirst().getX()][a.getFirst().getY()]=' ';
    }
*/
    public Board move(Tupel<Koordinate> a,Board returnBoard) {
        Iterator<Koordinate> iterator = returnBoard.satelliten.iterator();
        int i=0;
        while (iterator.hasNext()) {
            if(iterator.next().equals(a.getFirst())){
                returnBoard.satelliten.set(i,a.getSecond());
                break;
            }
            i++;
        }
        if(board[a.getFirst().getX()][a.getFirst().getY()]=='S') {
            returnBoard.board[a.getSecond().getX()][a.getSecond().getY()] = 'S';
        }
        else {
            returnBoard.board[a.getSecond().getX()][a.getSecond().getY()] = 'A';
            returnBoard.astronaut=new Koordinate(a.getSecond().getX(),a.getSecond().getY());
        }
        returnBoard.board[a.getFirst().getX()][a.getFirst().getY()]=' ';

        return new Board(returnBoard);
    }

    public void setBoardKo(char a,int x,int y){
        char[][] b= new char[7][7];


        for(int w=0;w<7;w++){
            for(int v=0;v<7;v++){
                b[w][v]=board[w][v];
            }
        }
        b[x][y]=a;
        this.board=b;
    }

    public LinkedList<Koordinate> getSatelliten() {
        return satelliten;
    }



    public Koordinate getShip() {
        return ship;
    }

    public Koordinate getAstronaut() {
        return astronaut;
    }

    public LinkedList<Board> getVorganger() {
        return vorganger;
    }

    public void setVorganger(LinkedList<Board> vorganger) {

        this.vorganger = vorganger;
    }

    public void addToVorganger(Board board){
        this.vorganger.add(board);
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public void setShip(Koordinate ship) {
        this.ship = ship;
    }

    public void setSatelliten(LinkedList<Koordinate> satelliten) {
        this.satelliten = satelliten;
    }

    public void setSatellit(int i, Koordinate k){
        this.satelliten.set(i,k);
    }

    public void setAstronaut(Koordinate astronaut) {
        this.astronaut.setX(astronaut.getX());
        this.astronaut.setY(astronaut.getY());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public Object clone(){
        try {
            return super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("cloning not allowed");
            return this;
        }
    }

    @Override
    public String toString() {
        String a = "";
        for (int i = 0; i < 7; i++) {
            a += "[";
            for (int u = 0; u < 7; u++) {
                a += "[" + board[u][i] + "]";
            }
            a += "]\n";
        }
        return a;
    }


}
