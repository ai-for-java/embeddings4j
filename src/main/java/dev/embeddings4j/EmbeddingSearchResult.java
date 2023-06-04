package dev.embeddings4j;

public class EmbeddingSearchResult<VectorType> {

    private final Embedding<VectorType> embedding;
    private final VectorType distance;

    public EmbeddingSearchResult(Embedding<VectorType> embedding,
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
