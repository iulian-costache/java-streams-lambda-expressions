package main;

@FunctionalInterface
public interface Instrument {
    void play();

    default void stop() {
        System.out.println("The instrument stops");
    }
}
