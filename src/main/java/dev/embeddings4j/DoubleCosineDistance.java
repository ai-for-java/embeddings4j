package dev.embeddings4j;

import com.github.jelmerk.knn.DistanceFunction;

import java.util.List;

public class DoubleCosineDistance implements DistanceFunction<List<Double>, Double> {

    @Override
    public Double distance(List<Double> u, List<Double> v) {
        double dot = 0.0;
        double nru = 0.0;
        double nrv = 0.0;

        for (int i = 0; i < u.size(); i++) {
            dot += u.get(i) * v.get(i);
            nru += u.get(i) * u.get(i);
            nrv += v.get(i) * v.get(i);
        }

        double similarity = dot / Math.sqrt(nru) * Math.sqrt(nrv);
        return 1 - similarity;
    }
}