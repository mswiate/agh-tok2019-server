package pl.edu.agh.toik.infun.model.requests;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateRoomInputConfig {
    public String roomId;
    public int taskNumber = 10;
    public List<String> tasksConfig = new ArrayList<>();
}
