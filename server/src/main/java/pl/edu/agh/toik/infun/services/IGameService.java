package pl.edu.agh.toik.infun.services;

import org.springframework.stereotype.Service;
import pl.edu.agh.toik.infun.exceptions.*;
import pl.edu.agh.toik.infun.model.Game;
import pl.edu.agh.toik.infun.model.requests.LastResultResponse;
import pl.edu.agh.toik.infun.model.requests.TaskConfig;

import java.util.List;
import java.util.Map;

@Service
public interface IGameService {
    List<TaskConfig> getTasks(List<String> jsons);

    void addGame(Game game) throws GameAlreadyExistsException;

    void addUser(String name, int age, String group, String cookie) throws UserAlreadyExistsException;

    String getConfig(String task, String cookie) throws NoSuchUserException;

    String getRandomTask(String cookie) throws NoMoreAvailableTasksException, NoSuchUserException;

    boolean isCreator(String groupId, String cookie);

    void removeGame(String groupId, String cookie) throws CannotRemoveGameException;

    void addResult(String taskName, String cookie, String nick, String group, double result) throws NoSuchGameException, NoSuchUserException;

    Map<String, Double> getResults(String groupId, String cookie) throws NoSuchGameException, AccessDeniedException;

    String generateRandomGroupId();

    LastResultResponse getLastResults(String cookie);

    TaskConfig getTaskConfig(String taskName);

    void checkUserCurrentTask(String task, String cookie) throws WrongTaskException;
}
