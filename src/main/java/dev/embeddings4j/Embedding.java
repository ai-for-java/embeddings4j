package dev.embeddings4j;

import com.github.jelmerk.knn.Item;

import java.util.List;

public class Embedding<VectorType> implements Item<Long, List<VectorType>> {

    private final long id;
    private final String contents;
    private final List<VectorType> vector;

    public Embedding(long id, String contents, List<VectorType> vector) {
        this.id = id;
        this.contents = contents;
        this.vector = vector;
    }

    @Override
    public Long id() {
        return id;
    }

    public String contents() {
        return contents;
    }

    @Override
    public List<VectorType> vector() {
        return vector;
    }

    @Override
    public int dimensions() {
        return vector.size();
    }

    public static <VectorType> Embedding<VectorType> of(long id, List<VectorType> vector) {
        return new Embedding<>(id, null, vector);
    }

    public static <VectorType> Embedding<VectorType> of(String contents, List<VectorType> vector) {
        return new Embedding<>(contents.hashCode(), contents, vector);
    }

    public static <VectorType> Embedding<VectorType> of(long id, String contents, List<VectorType> vector) {
        return new Embedding<>(id, contents, vector);
    }
}
