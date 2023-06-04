package dev.embeddings4j;

import com.github.jelmerk.knn.DistanceFunction;

import java.util.List;

public class FloatCosineDistance implements DistanceFunction<List<Float>, Float> {

    @Override
    public Float distance(List<Float> u, List<Float> v) {
        float dot = 0.0f;
        float nru = 0.0f;
        float nrv = 0.0f;

        for (int i = 0; i < u.size(); i++) {
            dot += u.get(i) * v.get(i);
            nru += u.get(i) * u.get(i);
            nrv += v.get(i) * v.get(i);
        }

        float similarity = dot / (float) (Math.sqrt(nru) * Math.sqrt(nrv));
        return 1 - similarity;
    }
}
