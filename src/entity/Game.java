package entity;

public class Game {
    DeckDisposed discard;

    public Game() {
        this.discard = new DeckDisposed();
    }

    public DeckDisposed getDiscard() {
        return discard;
    }
}
