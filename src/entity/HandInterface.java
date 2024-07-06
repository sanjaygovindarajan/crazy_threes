package entity;

import java.util.List;

public interface HandInterface extends CardCollectionInterface {
    public Card playCard(int i);
    public List<Card> viewCards();
}
