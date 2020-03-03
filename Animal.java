public class Animal {
    protected double runLimit;
    protected double jumpLimit;
    protected double swimLimit;

    public Animal(double runLimit, double jumpLimit, double swimLimit) {
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
        this.swimLimit = swimLimit;
    }

    public boolean run(double length) {
        return runLimit >= length;
    }

    public boolean jump(double height) {
        return jumpLimit >= height;
    }

    public boolean swim(double length) {
        return swimLimit >= length;
    }

    public String getInfo() {
        return "Run limit = " + this.runLimit +
                ", Jump limit = " + this.jumpLimit +
                ", Swim limit = " + this.swimLimit;
    }
}
