package dev.embeddings4j;

public class SearchNearestQuery<IdType, ContentType, VectorType> {

    private final Embedding<IdType, ContentType, VectorType> reference;
    private final Integer maxResults;

    public SearchNearestQuery(Embedding<IdType, ContentType, VectorType> reference, Integer maxResults) {
        this.reference = reference;
        this.maxResults = maxResults;
    }

    public Embedding<IdType, ContentType, VectorType> reference() {
        return reference;
    }

    public Integer maxResults() {
        return maxResults;
    }
}
