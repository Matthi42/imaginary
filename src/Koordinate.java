public class Koordinate implements Cloneable{
    private int x;
    private int y;

    public Koordinate(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Koordinate(){
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public boolean equals(Koordinate a){
        if(this.getY()==a.getY()&&this.getX()==a.getX())
            return true;
        return false;
    }

    @Override
    public Object clone()  {
        try {
            return super.clone();
        }catch (CloneNotSupportedException e){
            System.out.println("cloning not allowed");
            return this;
        }
    }

    @Override
    public String toString(){
        return "(" + x + ";" + y + ")";
    }

    @Override
        public int hashCode() {
            return x*7+y*13;
    }
}
