package dev.embeddings4j;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class InMemoryVectorDatabaseTest {

    @Test
    public void should_store_embeddings_and_find_nearest() {

        InMemoryVectorDatabase vectorDatabase = new InMemoryVectorDatabase(2, 10);

        Embedding<String, String, Float> embedding1 = new Embedding<>(generateRandomId(), "text1", asList(1.0f, 3.0f));

        Embedding<String, String, Float> embedding2 = new Embedding<>(generateRandomId(), "text2", asList(3.0f, 5.0f));
        Embedding<String, String, Float> embedding3 = new Embedding<>(generateRandomId(), "text3", asList(2.0f, 2.0f));
        Embedding<String, String, Float> embedding4 = new Embedding<>(generateRandomId(), "text4", asList(4.0f, 2.0f));
        Embedding<String, String, Float> embedding5 = new Embedding<>(generateRandomId(), "text5", asList(3.0f, 1.0f));

        vectorDatabase.insert(embedding2);
        vectorDatabase.insert(embedding3);
        vectorDatabase.insert(embedding4);
        vectorDatabase.insert(embedding5);


        SearchNearestQuery<String, String, Float> query = new SearchNearestQuery<>(embedding1, 2);
        List<SearchNearestResult<String, String, Float>> searchResults = vectorDatabase.execute(query);


        List<Embedding<String, String, Float>> resultVectors = searchResults.stream()
                .map(SearchNearestResult::embedding)
                .collect(toList());

        assertThat(searchResults).hasSize(2);
        assertThat(resultVectors).containsExactly(embedding2, embedding3);
    }

    public static String generateRandomId() {
        return UUID.randomUUID().toString();
    }
}