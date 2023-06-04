package dev.embeddings4j;

import com.github.jelmerk.knn.DistanceFunction;
import com.github.jelmerk.knn.SearchResult;
import com.github.jelmerk.knn.hnsw.HnswIndex;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public abstract class AbstractInMemoryVectorDatabase<VectorType extends Comparable<VectorType>> {

    private final HnswIndex<Long, List<VectorType>, Embedding<VectorType>, VectorType> index;

    protected AbstractInMemoryVectorDatabase(int dimensions, int maxSize, DistanceFunction<List<VectorType>, VectorType> distanceFunction) {
        this.index = HnswIndex.newBuilder(dimensions, distanceFunction, maxSize)
                // TODO other params?
                .build();
    }

    public void insert(Embedding<VectorType> embedding) {
        validate(embedding);
        index.add(embedding);
    }

    @SafeVarargs
    public final void insert(Embedding<VectorType>... embeddings) {
        List<Embedding<VectorType>> embeddingList = asList(embeddings);
        embeddingList.forEach(this::validate);
        embeddingList.forEach(index::add);
    }

    public void insertAll(Collection<Embedding<VectorType>> embeddings) throws InterruptedException {
        embeddings.forEach(this::validate);
        index.addAll(embeddings);
    }

    private void validate(Embedding<VectorType> embedding) {
        if (embedding == null) {
            throw new IllegalArgumentException("Embedding must not be null");
        }

        if (embedding.dimensions() != index.getDimensions()) {
            throw new IllegalArgumentException(String.format("Dimensions of vector (%s) should match dimensions of DB (%s)", embedding.dimensions(), index.getDimensions()));
        }
    }

    public List<SearchNearestResult<VectorType>> execute(SearchNearestQuery<VectorType> query) {
        List<SearchResult<Embedding<VectorType>, VectorType>> searchResults
                = index.findNearest(query.reference().vector(), query.maxResults());

        return searchResults.stream()
                .sorted(Comparator.comparing(SearchResult::distance))
                .map(result -> new SearchNearestResult<>(result.item(), result.distance()))
                .collect(toList());
    }

    public int size() {
        return index.size();
    }
}
