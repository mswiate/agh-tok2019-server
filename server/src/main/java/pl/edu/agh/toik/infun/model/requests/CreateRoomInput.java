package pl.edu.agh.toik.infun.model.requests;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateRoomInput {
    public String roomId;
    public int taskNumber = 10;
    public List<TaskConfig> tasksConfig;

    public CreateRoomInput(List<TaskConfig> tasks) {
        this.tasksConfig = tasks;
    }
}
