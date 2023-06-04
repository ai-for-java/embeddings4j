package dev.embeddings4j;

public class InMemoryFloatVectorDatabase extends AbstractInMemoryVectorDatabase<Float> {

    public InMemoryFloatVectorDatabase(int dimensions, int maxSize) {
        super(dimensions, maxSize, new FloatCosineDistance());
    }
}
