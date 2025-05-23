package com.expertos.poker.model;

import com.expertos.common.model.Card;
import com.expertos.poker.helpers.Play;

import java.util.*;
import java.util.stream.Collectors;


public class PokerHand {

    private final SortedSet<PokerCard> table;
    private final SortedSet<PokerCard> hand;

    public PokerHand(SortedSet<PokerCard> table, SortedSet<PokerCard> hand) {
        this.table = table;
        this.hand = hand;
    }

    public SortedSet<PokerCard> getTable() {
        return table;
    }

    public SortedSet<PokerCard> getHand() {
        return hand;
    }

    public SortedSet<PokerCard> getAllCards() {
        SortedSet<PokerCard> toReturn  = new TreeSet<>(getHand());
        toReturn.addAll(getTable());

        return toReturn;
    }

    // El comparator se me ha complicado
    private static Comparator<SortedSet<PokerCard>> createComparatorSortedSetOfEqualNumbers() {

        return new Comparator<SortedSet<PokerCard>>() {
            @Override
            public int compare(SortedSet<PokerCard> o1, SortedSet<PokerCard> o2) {
                Integer toReturn = o2.size() - o1.size();

                if(!toReturn.equals(0))
                    return toReturn;
                else {
                    toReturn = o1.getFirst().getValue().compareTo(o2.getFirst().getValue());
                }

                return toReturn;
            }
        };
    }

    // Se utiliza para encontrar parejas, dobles parejas, tríos, fulls y pokers.
    // Devuelve un SortedSet de SortedSets de CartaPoker con igual número,
    // ordenados por tamaño de (mayor a menor) y por valor de la carta en el juego (de mayor a menor).
    // Si no encuentra el set devuelto contendrá varios sets de 1 elemento.
    private SortedSet<SortedSet<PokerCard>> findAllSetsEqualNumber() {
        SortedSet<SortedSet<PokerCard>> toReturn =
                new TreeSet<>(createComparatorSortedSetOfEqualNumbers());

        SortedSet<PokerCard> allCards = getAllCards();

        for(PokerCard card : allCards) {
            SortedSet<PokerCard> cards = new TreeSet<>();
            cards.add(card);

            allCards.remove(card);

            for(PokerCard otherCard : allCards)
                if(card.getNum().equals(otherCard.getNum())) {
                    cards.add(otherCard);
                    allCards.remove(otherCard);
                }

            toReturn.add(cards);
        }

        return toReturn;
    }

    // Encuentra colores.
    // Si no encuentra devuelve un TreeSet vacío.
    private SortedSet<PokerCard> findColor() {
        SortedSet<PokerCard> color = new TreeSet<>();

        Map<Card.Suit, SortedSet<PokerCard>> colors = getAllCards().stream()
                .collect(Collectors.groupingBy(PokerCard::getSuit, Collectors.toCollection(TreeSet::new)));

        for(Map.Entry<Card.Suit, SortedSet<PokerCard>> colorEntry : colors.entrySet())
            if(colorEntry.getValue().size() >= 5) {
                color.addAll(colorEntry.getValue());
                break;
            }

        return color;
    }

    // Encuentra escaleras simples.
    // Si no encuentra devuelve un TreeSet vacío
    private SortedSet<PokerCard> findStair() {
        SortedSet<PokerCard> stair = new TreeSet<>(PokerCard.compareByNumber);

        SortedSet<PokerCard> allCards = new TreeSet<>(PokerCard.compareByNumber);
        allCards.addAll(getAllCards());

        Integer skippedCards = 0;

        for(PokerCard card : allCards) {
            if(stair.isEmpty() ||
                    card.getNum().equals(stair.getLast().getNum() + 1))
                stair.add(card);
            else if(card.getNum().equals(stair.getLast().getNum()))
                skippedCards++;
            else {
                skippedCards += stair.size();
                stair.clear();
                stair.add(card);
            }

            if(stair.size() == 5)
                break;

            if(skippedCards > 2){
                stair.clear();
                break;
            }
        }

        return stair;
    }

    // Encuentra escaleras de color.
    // Si no encuentra devuelve un TreeSet vacío
    private SortedSet<PokerCard> findColorStair() {
        SortedSet<PokerCard> colorStair = new TreeSet<>(PokerCard.compareByNumber);

        SortedSet<PokerCard> allCards = new TreeSet<>(PokerCard.compareByNumber);
        allCards.addAll(getAllCards());

        Integer skippedCards = 0;

        for(PokerCard card : allCards) {
            if(colorStair.isEmpty() ||
                    (card.getSuit().equals(colorStair.getLast().getSuit()) &&
                            card.getNum().equals(colorStair.getLast().getNum() + 1)))

                colorStair.add(card);

            else if(card.getNum().equals(colorStair.getLast().getNum()) ||
                    (!card.getSuit().equals(colorStair.getLast().getSuit()) &&
                            card.getNum().equals(colorStair.getLast().getNum() + 1)))

                skippedCards++;

            else {
                skippedCards += colorStair.size();
                colorStair.clear();
                colorStair.add(card);
            }

            if(colorStair.size() == 5)
                break;

            if(skippedCards > 2) {
                colorStair.clear();
                break;
            }
        }

        return colorStair;
    }

    // Este método utiliza los métodos privados "find" anteriores,
    // para encontrar la mejor combinación de 5 o 2 cartas.
    // Debe ordenar las cartas del ArrayList que hay que pasarle al constructor de Play según el tipo de jugada,
    // para que el método compareAllElementsOfPlays las compare en el orden correcto (demostración en la clase PlayTest).
    public Play getBestPlay() {
        Play bestPlay = null;
        //TODO terminar (faltan doble pareja, pareja y single)

        SortedSet<PokerCard> colorStair = findColorStair();
        SortedSet<PokerCard> color = findColor();
        SortedSet<PokerCard> stair = findStair();

        if(!colorStair.isEmpty())
            bestPlay = new Play(new ArrayList<>(colorStair), Play.Type.COLOR_STAIR);
        else if (!color.isEmpty())
            bestPlay = new Play(new ArrayList<>(color), Play.Type.COLOR);
        else if (!stair.isEmpty())
            bestPlay = new Play(new ArrayList<>(stair), Play.Type.STAIR);
        else {
            List<SortedSet<PokerCard>> equalValueSets = new ArrayList<>(findAllSetsEqualNumber());

            if(equalValueSets.getFirst().size() == 4) {
                List<PokerCard> bestPlayCards = new ArrayList<>(equalValueSets.getFirst());
                bestPlayCards.add(equalValueSets.get(2).getFirst());

                bestPlay = new Play(bestPlayCards, Play.Type.POKER);
            } else if (equalValueSets.getFirst().size() == 3) {
                if(equalValueSets.get(2).size() >= 2) {
                    List<PokerCard> bestPlayCards = new ArrayList<>(equalValueSets.getFirst());
                    bestPlayCards.addAll(equalValueSets.get(2).stream().limit(2).toList());

                    bestPlay = new Play(bestPlayCards, Play.Type.FULL_HOUSE);
                } else {
                    List<PokerCard> bestPlayCards = new ArrayList<>(equalValueSets.getFirst());
                    bestPlayCards.add(equalValueSets.get(2).getFirst());
                    bestPlayCards.add(equalValueSets.get(3).getFirst());

                    bestPlay = new Play(bestPlayCards, Play.Type.TRIO);
                }
            }
        }

        return bestPlay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand manoPoker = (PokerHand) o;
        return Objects.equals(table, manoPoker.table) && Objects.equals(hand, manoPoker.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(table, hand);
    }

}
