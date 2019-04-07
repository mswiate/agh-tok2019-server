package pl.edu.agh.toik.infun.model.requests;

import lombok.Data;

@Data
public class JoinRoomInput {
    public String roomId;
    public String nick;
    public int age = 20;
}
