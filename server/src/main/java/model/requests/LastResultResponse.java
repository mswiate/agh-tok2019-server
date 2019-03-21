package model.requests;

import lombok.Data;

@Data
public class LastResultResponse {
    private double score;
    private double lastResult;
}
