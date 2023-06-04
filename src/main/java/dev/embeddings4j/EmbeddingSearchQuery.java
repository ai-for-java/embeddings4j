package dev.embeddings4j;

public class EmbeddingSearchQuery<VectorType> {

    private final Embedding<VectorType> reference; // rename
    private final Integer maxResults;

    public EmbeddingSearchQuery(Embedding<VectorType> reference, Integer maxResults) {
        this.reference = reference;
        this.maxResults = maxResults;
    }

    public Embedding<VectorType> reference() {
        return reference;
    }

    public Integer maxResults() {
        return maxResults;
    }
}
