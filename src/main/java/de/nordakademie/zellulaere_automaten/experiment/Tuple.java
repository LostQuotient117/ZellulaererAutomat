package de.nordakademie.zellulaere_automaten.experiment;

/**
 * This class is used as a helper class to orderly pass the
 * start configuration set for the start of the experiment in the grid,
 * which further is defined by the alive state of the individual cells on the grid.
 * @param <A> first generic value of tupel
 * @param <B> second generic value of tupel
 */
public class Tuple<A, B> {
    public final A first;
    public final B second;

    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }
}
