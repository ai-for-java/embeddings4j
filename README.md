# embeddings4j
An in-memory vector database optimized for storage and efficient search of embeddings.

Currently:
- implemented using HNSW
- uses cosine distance for similarity search
- supports `float` and `double` types for vectors
- supports storing ID and contents along the vector, so you can:
  - store vectors together with contents in-memory (if you want to have the fastest search and your data fits into memory)
  - store vector by ID (if you want to store your data in another location and use in-memory database as a fast index)
  - both (mix as you want)

Plans:
- more data types for vectors
- updates/deletes of existing embeddings/vectors
- dynamic DB size (currently you have to define max size during DB initialization)
- serialization of DB to disk or other persistent storage and de-serializing it back
- consider using more efficient/compact data types instead of floats/doubles (to save on memory and speedup storage/search)
- consider using quantization (to save on memory and speedup storage/search)
- consider reducing dimensionality of vectors (to save on memory and speedup storage/search)
- please tell us what you need