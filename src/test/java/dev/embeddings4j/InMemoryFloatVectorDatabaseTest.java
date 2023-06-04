package dev.embeddings4j;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class InMemoryFloatVectorDatabaseTest {

    private static final Random RANDOM = new Random();

    @Test
    public void should_store_embeddings_and_find_nearest() {

        InMemoryFloatVectorDatabase vectorDatabase = new InMemoryFloatVectorDatabase(2, 10);

        Embedding<Float> embedding1 = Embedding.of("text1", asList(1.0f, 3.0f));

        Embedding<Float> embedding2 = Embedding.of("text2", asList(3.0f, 5.0f));
        Embedding<Float> embedding3 = Embedding.of("text3", asList(2.0f, 2.0f));
        Embedding<Float> embedding4 = Embedding.of("text4", asList(4.0f, 2.0f));
        Embedding<Float> embedding5 = Embedding.of("text5", asList(3.0f, 1.0f));

        vectorDatabase.insert(embedding2);
        vectorDatabase.insert(embedding3);
        vectorDatabase.insert(embedding4);
        vectorDatabase.insert(embedding5);


        EmbeddingSearchQuery<Float> query = new EmbeddingSearchQuery<>(embedding1, 2);
        List<EmbeddingSearchResult<Float>> searchResults = vectorDatabase.searchNearest(query);


        List<Embedding<Float>> resultVectors = searchResults.stream()
                .map(EmbeddingSearchResult::embedding)
                .collect(toList());

        assertThat(searchResults).hasSize(2);
        assertThat(resultVectors).containsExactly(embedding2, embedding3);
    }

    @Test
    void loadTest() {

        for (int i = 7; i <= 20; i++) {

            int numberOfEmbeddings = (int) Math.pow(2, i); // 128...1.048.576

            for (int j = 7; j <= 11; j++) {

                int numberOfDimensions = (int) Math.pow(2, j); // 128...2.048

                InMemoryFloatVectorDatabase db = new InMemoryFloatVectorDatabase(numberOfDimensions, numberOfEmbeddings);

                List<Embedding<Float>> embeddings = new ArrayList<>();
                for (int k = 0; k < numberOfEmbeddings; k++) {
                    embeddings.add(createRandomEmbedding(numberOfDimensions));
                }

                try {
                    System.out.println("number of embeddings: " + numberOfEmbeddings);
                    System.out.println("number of dimensions: " + numberOfDimensions);

                    long now = System.currentTimeMillis();
                    db.insertAll(embeddings);
                    long elapsedTimeMillis = System.currentTimeMillis() - now;

                    System.out.println("elapsed time (seconds): " + (double) elapsedTimeMillis / 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("DONE");
                System.out.println();
                System.out.println();
            }
        }
    }

    private static Embedding<Float> createRandomEmbedding(int numberOfDimensions) {
        long id = RANDOM.nextLong();
        String contents = "contents " + id;
        List<Float> vector = RANDOM.doubles(numberOfDimensions, -1, 1)
                .mapToObj(d -> (float) d)
                .collect(toList());

        return Embedding.of(id, contents, vector);
    }
}