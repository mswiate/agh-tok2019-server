package pl.edu.agh.toik.infun.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskResult {
    private double result;
    @JsonProperty("group")
    private String room;
    private String nick;
    private int age;
}
