package entity;

public class Game {
    DeckDisposed disposed;

    public Game(DeckDisposed disposed) {
        this.disposed = disposed;
    }

    public DeckDisposed getDisposed() {
        return disposed;
    }
}
