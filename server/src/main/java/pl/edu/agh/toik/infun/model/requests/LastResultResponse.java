package pl.edu.agh.toik.infun.model.requests;

import lombok.Data;

@Data
public class LastResultResponse {
    private double score;
    private double rank;
    private double lastResult;
}
