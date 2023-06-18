package dev.embeddings4j;

public class SearchNearestResult<IdType, ContentType, VectorType> {

    private final Embedding<IdType, ContentType, VectorType> embedding;
    private final VectorType distance;

    public SearchNearestResult(Embedding<IdType, ContentType, VectorType> embedding,
                               VectorType distance) {
        this.embedding = embedding;
        this.distance = distance;
    }

    public Embedding<IdType, ContentType, VectorType> embedding() {
        return embedding;
    }

    public VectorType distance() {
        return distance;
    }
}
