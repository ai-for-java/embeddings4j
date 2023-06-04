package dev.embeddings4j;

public class SearchNearestResult<VectorType> {

    private final Embedding<VectorType> embedding;
    private final VectorType distance;

    public SearchNearestResult(Embedding<VectorType> embedding,
                               VectorType distance) {
        this.embedding = embedding;
        this.distance = distance;
    }

    public Embedding<VectorType> embedding() {
        return embedding;
    }

    public VectorType distance() {
        return distance;
    }
}
