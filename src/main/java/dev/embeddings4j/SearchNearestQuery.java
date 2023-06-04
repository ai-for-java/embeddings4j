package dev.embeddings4j;

public class SearchNearestQuery<VectorType> {

    private final Embedding<VectorType> reference;
    private final Integer maxResults;

    public SearchNearestQuery(Embedding<VectorType> reference, Integer maxResults) {
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
