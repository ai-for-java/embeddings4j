package dev.embeddings4j;

public class InMemoryDoubleVectorDatabase extends AbstractInMemoryVectorDatabase<Double> {

    public InMemoryDoubleVectorDatabase(int dimensions, int maxSize) {
        super(dimensions, maxSize, new DoubleCosineDistance());
    }
}
