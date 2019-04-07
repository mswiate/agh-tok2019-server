package pl.edu.agh.toik.infun.model.requests;

import lombok.Data;

@Data
public class JoinGameInput {
    public String groupId;
    public String nick;
    public int age = 20;
}
