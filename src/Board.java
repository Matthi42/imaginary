import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Board {
    private char[][] board;
    private Koordinate ship;
    private LinkedList<Koordinate> satelliten;
    private Koordinate astronaut;
    private LinkedList<Board> vorganger;

    public Board(Koordinate ship, LinkedList<Koordinate> satelliten, Koordinate astronaut) {
        this.board = new char[7][7];
        for (int i = 0; i < 7; i++) {
            for (int u = 0; u < 7; u++) {
                board[i][u] = ' ';
            }
        }
        this.ship = ship;
        satelliten.add(astronaut);
        this.satelliten = satelliten;
        for (Koordinate a :
                satelliten) {
            board[a.getX()][a.getY()] = 'S';
        }
        board[astronaut.getX()][astronaut.getY()] = 'A';
        this.ship = ship;
        this.astronaut=astronaut;
    }

    public Board(){};


    public ArrayList<Tupel<Koordinate>> moves() {
        ArrayList<Tupel<Koordinate>> moves = new ArrayList<>();
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
                            flag = true;
                        } else {
                            for (int i = sat.getY() + 1; i < ziel.getY(); i++) {
                                if (((board[ziel.getX()][i] == 'S') || (board[ziel.getX()][i] == 'A'))) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                moves.add(new Tupel<>(sat, new Koordinate(ziel.getX(), ziel.getY() - 1)));
                            flag = true;
                        }
                    }

                    if (sat.getY() == ziel.getY()&& Math.abs(sat.getX()-ziel.getX())>1) {
                        if (sat.getX() > ziel.getX()) {
                            for (int i = ziel.getX() + 1; i < sat.getX(); i++) {
                                if (((board[ziel.getY()][i] == 'S') || (board[ziel.getY()][i] == 'A'))) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                moves.add(new Tupel<>(sat, new Koordinate(ziel.getY(), ziel.getX() + 1)));
                            flag = true;
                        } else {
                            for (int i = sat.getX() + 1; i < ziel.getX(); i++) {
                                if (((board[ziel.getY()][i] == 'S') || (board[ziel.getY()][i] == 'A'))) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag)
                                moves.add(new Tupel<>(sat, new Koordinate(ziel.getY(), ziel.getX() - 1)));
                            flag = true;
                        }

                    }

                }

            }

        }
        return moves;
    }


    public void move(Tupel<Koordinate> a) {
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

    public Board moveNew(Tupel<Koordinate> a) {
        Board returnBoard =this;
        Iterator<Koordinate> iterator = satelliten.iterator();
        int i=0;
        while (iterator.hasNext()) {
            if(iterator.next().equals(a.getFirst())){
                satelliten.set(i,a.getSecond());
                break;
            }
            i++;
        }
        if(board[a.getFirst().getX()][a.getFirst().getY()]=='S') {
            returnBoard.board[a.getSecond().getX()][a.getSecond().getY()] = 'S';
        }
        else {
            returnBoard.board[a.getSecond().getX()][a.getSecond().getY()] = 'A';
            this.astronaut=new Koordinate(a.getSecond().getX(),a.getSecond().getY());
        }
        this.board[a.getFirst().getX()][a.getFirst().getY()]=' ';
        return returnBoard;
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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
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
