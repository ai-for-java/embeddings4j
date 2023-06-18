package dev.embeddings4j;

import java.util.List;

public class DefaultEmbedding extends Embedding<String, String, Float> {

    public DefaultEmbedding(String id, List<Float> vector) {
        this(id, null, vector);
    }

    public DefaultEmbedding(String id, String contents, List<Float> vector) {
        super(id, contents, vector);
    }

    public static DefaultEmbedding of(String id, String contents, List<Float> vector) {
        return new DefaultEmbedding(id, contents, vector);
    }
}
