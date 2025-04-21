package com.expertos.poker.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ManoPoker implements Comparable<ManoPoker> {

    private List<CartaPoker> mesa;
    private List<CartaPoker> mano;

    public ManoPoker(List<CartaPoker> mesa, List<CartaPoker> mano) {
        this.mesa = mesa;
        this.mano = mano;
    }

    public List<CartaPoker> getMesa() {
        return mesa;
    }

    public List<CartaPoker> getMano() {
        return mano;
    }

    public Combinacion5 getMejorCombinacion() {
        //TODO
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ManoPoker manoPoker = (ManoPoker) o;
        return Objects.equals(mesa, manoPoker.mesa) && Objects.equals(mano, manoPoker.mano);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mesa, mano);
    }

    @Override
    public int compareTo(ManoPoker o) {
        //TODO
        return 0;
    }
}
