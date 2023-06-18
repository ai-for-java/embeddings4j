package dev.embeddings4j;

import com.github.jelmerk.knn.Item;

import java.util.List;

public class Embedding<IdType, ContentType, VectorType> implements Item<IdType, List<VectorType>> {

    private final IdType id;
    private final ContentType contents;
    private final List<VectorType> vector;

    public Embedding(IdType id, ContentType contents, List<VectorType> vector) {
        this.id = id;
        this.contents = contents;
        this.vector = vector;
    }

    public Embedding(IdType id, List<VectorType> vector) {
        this(id, null, vector);
    }

    @Override
    public IdType id() {
        return id;
    }

    public ContentType contents() {
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
}
