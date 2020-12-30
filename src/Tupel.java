public class Tupel<k>extends Object implements Cloneable{
    private k first;
    private k second;



    public Tupel(k first, k second){
        this.second=second;
        this.first=first;
    }

    public Tupel(){}

    public k getFirst() {
        return first;
    }

    public void setFirst(k first) {
        this.first = first;
    }

    public k getSecond() {
        return second;
    }

    public void setSecond(k second) {
        this.second = second;
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
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean equals(Tupel<k> a){
        if(this.first.equals(a.first)&&this.second.equals(a.second))
            return true;
        else return false;
    }

    public String toString(){
        return "{" + first.toString() + " -> " +second.toString() +"}";
    }
}
