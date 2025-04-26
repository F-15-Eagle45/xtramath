package main.java.xtramath;

public abstract class NonComInputTaker extends InputTaker {
    // new variables for non commutative operations usch as subtraction and division
    protected int pseudoMin;
    protected int pseudoMax;

    public NonComInputTaker(int min, int max, int terms) {
        super(min, max, terms);
    }

}
