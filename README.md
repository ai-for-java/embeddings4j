# embeddings4j
Embeddings4j is an in-memory vector database optimized for storage and efficient searching of embeddings.

## Use Cases (Why would I need that?)
- You want to prototype quickly and do not want to depend on remote or commercial vector DBs.
- You want to store your data in separate persistent storage and use an in-memory DB as an index for fast lookup.
- You want to use it for integration testing.

## Current State:
- Implemented using HNSW.
- Uses cosine distance for similarity search.
- Supports `float` and `double` types for vectors.
- Supports storing ID and contents along with the vector, so you can:
  - Store vectors together with contents in-memory (if you want to have the fastest search and your data fits into memory).
  - Store vector by ID (if you want to store your data in another location and use in-memory database as a fast index).
  - Both (mix as you wish).

## Planned Improvements / Features:
- More data types for vectors.
- Updates/deletes of existing embeddings/vectors.
- Dynamic DB size (currently, you have to define max size during DB initialization).
- Serialization of DB to disk or other persistent storage and deserializing it back.
- Consider using more efficient/compact data types instead of floats/doubles (to save memory and speed up storage/search).
- Consider using quantization (to save memory and speed up storage/search).
- Consider reducing dimensionality of vectors (to save memory and speed up storage/search).
- Please let us know what you need.

## Requirements
- Java 8 or later.

## Start Using
Maven:
```
<dependency>
  <groupId>dev.ai4j</groupId>
  <artifactId>embeddings4j</artifactId>
  <version>0.2.0</version>
</dependency>
```

Gradle:
```
implementation 'dev.ai4j:embeddings4j:0.2.0'
```

## How to Use
```
// Init DB
int dimensions = 2; // low number for easy understanding
int maxSize = 10000;
InMemoryFloatVectorDatabase db = new InMemoryFloatVectorDatabase(dimensions, maxSize);

// Create embeddings
Embedding<Float> embedding1 = Embedding.of(1, "text 1", asList(3.0f, 5.0f));
Embedding<Float> embedding2 = Embedding.of(2, "text 2", asList(2.0f, 2.0f));
Embedding<Float> embedding3 = Embedding.of(3, "text 3", asList(4.0f, 2.0f));
Embedding<Float> embedding4 = Embedding.of(4, "text 4", asList(3.0f, 1.0f));

// Insert embeddings into DB
db.insert(embedding1, embedding2, embedding3, embedding4);

// Create search query
Embedding<Float> referenceEmbedding = Embedding.of(5, "text 5", asList(1.0f, 3.0f));
int maxResults = 2;
SearchNearestQuery<Float> query = new SearchNearestQuery<>(referenceEmbedding, maxResults);

// Execute query
List<SearchNearestResult<Float>> results = db.execute(query);

// Observe results
assertThat(results).hasSize(maxResults);
assertThat(results.get(0).embedding()).isEqualTo(embedding1);
assertThat(results.get(1).embedding()).isEqualTo(embedding2);
```
