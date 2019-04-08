package pl.edu.agh.toik.infun.model.domain;

import lombok.AllArgsConstructor;
import lombok.Value;
import pl.edu.agh.toik.infun.model.User;

@Value
@AllArgsConstructor
public class UserResult {
    private String userName;
    private double score;
    private String color;

    public static UserResult fromUser(final User user) {
        return new UserResult(user.getNick(), user.getScore(), user.getColor());
    }
}
