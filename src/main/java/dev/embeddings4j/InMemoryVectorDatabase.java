package dev.embeddings4j;

public class InMemoryVectorDatabase extends AbstractInMemoryVectorDatabase<String, String, Float> {

    public InMemoryVectorDatabase(int dimensions, int maxSize) {
        super(dimensions, maxSize, new FloatCosineDistance());
    }
}
