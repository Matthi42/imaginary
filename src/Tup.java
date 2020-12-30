public class Tup<k, o> extends Object {
    private k key;
    private o value;


    public Tup(k first, o second) {
        this.value = second;
        this.key = first;
    }

    public k getKey() {
        return key;
    }

    public void setKey(k key) {
        this.key = key;
    }

    public o getValue() {
        return value;
    }

    public void setValue(o value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean equals(Tup<k, o> a) {
        if (this.key.equals(a.key) && this.value.equals(a.value))
            return true;
        else return false;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("cloning not allowed");
            return this;
        }
    }

    @Override
    public String toString() {
        return "{" + key.toString() + " -> " + value.toString() + "}";
    }
}
