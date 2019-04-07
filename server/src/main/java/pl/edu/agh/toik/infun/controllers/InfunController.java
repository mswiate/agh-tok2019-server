package pl.edu.agh.toik.infun.controllers;

import pl.edu.agh.toik.infun.exceptions.*;
import pl.edu.agh.toik.infun.model.Game;
import pl.edu.agh.toik.infun.model.domain.TaskResult;
import pl.edu.agh.toik.infun.model.requests.CreateGameInput;
import pl.edu.agh.toik.infun.model.requests.CreateGameInputConfig;
import pl.edu.agh.toik.infun.model.requests.JoinGameInput;
import pl.edu.agh.toik.infun.model.requests.LastResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.toik.infun.services.IFolderScanService;
import pl.edu.agh.toik.infun.services.IGameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
//todo hide all functions body to service
public class InfunController {

    @Autowired
    IGameService gameService;

    @Autowired
    IFolderScanService folderScanService;

    @RequestMapping("/")
    String main() {
        return "redirect:/game/join";
    }

    @RequestMapping("/game/create")
    String createGame(Model model, @CookieValue("JSESSIONID") String cookie) {
        CreateGameInput createGameInput = new CreateGameInput(gameService.getTasks(
                folderScanService.scanFolder())
        );
        model.addAttribute("createGameInput", createGameInput);
//        System.out.println(createGameInput);
        return "create_game";
    }

    @GetMapping(value = "/game/join")
    String joinGame(Model model, @CookieValue("JSESSIONID") String cookie) {
        model.addAttribute("joinGameInput", new JoinGameInput());
        return "join_game";
    }

    @PostMapping(value = "/game/join")
    String getTask(@ModelAttribute JoinGameInput joinGameInput, @CookieValue("JSESSIONID") String cookie, Model model) throws NoSuchUserException, UserAlreadyExistsException, NoSuchGameException {
        gameService.addUser(joinGameInput.nick, joinGameInput.age, joinGameInput.groupId, cookie);
        return "redirect:/tasks/new";
    }
//
//    @GetMapping(value = "/newtask")
//    String getNewTask(@CookieValue("JSESSIONID") String cookie, Model model) {
//        return "redirect:/tasks/new";
//    }


    @GetMapping("/tasks/new")
    public String getFile(@CookieValue("JSESSIONID") String cookie) throws NoSuchUserException {
        try {
            return gameService.getRandomTask(cookie) + "/index";
        } catch (NoMoreAvailableTasksException e) {
            return "redirect:/end";
        }
    }

    @RequestMapping("/manage")
    String manage(@ModelAttribute("createGameInput") CreateGameInputConfig createGameInputConfig, @CookieValue("JSESSIONID") String cookie, Model model) throws GameAlreadyExistsException, NoGameSelectedException {
//        System.out.println(createGameInputConfig);

        List<String> userChoice = new ArrayList<>();
        if (createGameInputConfig.getTasksConfig() == null || createGameInputConfig.getTasksConfig().size() == 0) {
            throw new NoGameSelectedException("Nie wybrano żadnej gry");
        } else {
            userChoice = createGameInputConfig.getTasksConfig();
        }
        //todo
//        userChoice.forEach(System.out::println);
        String groupId = createGameInputConfig.getGroupId();
        if (groupId == null || groupId.trim().equals("")) {
            groupId = gameService.generateRandomGroupId();
        }
        gameService.addGame(new Game(groupId, userChoice.stream().map(tn -> gameService.getTaskConfig(tn)).collect(Collectors.toList()), cookie, createGameInputConfig.getTaskNumber()));
        model.addAttribute("group_id", groupId);
        return "manage";
    }

    @RequestMapping("/{task_name}/config")
    @ResponseBody
    String getConfig(@PathVariable(value = "task_name") final String taskName, @CookieValue("JSESSIONID") String cookie) throws NoSuchUserException {
        return gameService.getConfig(taskName, cookie);
    }


    @PostMapping(value = "/{task_name}/end")
    @ResponseBody
    public String endGame(@PathVariable(value = "task_name") final String taskName, @RequestBody TaskResult taskResult, @CookieValue("JSESSIONID") String cookie, Model model) throws NoSuchGameException, NoSuchUserException {
//        System.out.println("/{task_name}/end -> result " + taskResult + " cookie = " + cookie);
        gameService.addResult(taskName, cookie, taskResult.getNick(), taskResult.getGroup(), taskResult.getResult());
        model.addAttribute("result", taskResult);
        return "/task_result";
    }

    @GetMapping(value = "/task_result")
    String taskResult(@CookieValue("JSESSIONID") String cookie, Model model) {
        return "task_result";
    }

    @GetMapping(value = "/end")
    String end(@CookieValue("JSESSIONID") String cookie, Model model) {
        model.addAttribute("result", "Udało Ci się skończyć wszystkie zadania. \nCałkowity rezultat = " + gameService.getLastResults(cookie).getScore());
        return "end";
    }

    @GetMapping(value = "/{group_id}/remove")
    String endGame(@CookieValue("JSESSIONID") String cookie, @PathVariable(value = "group_id") final String groupId) throws CannotRemoveGameException {
        gameService.removeGame(groupId, cookie);
        return "redirect:/";
    }

    @RequestMapping("/{group_id}/results")
    @ResponseBody
    Map<String, Double> getResults(@PathVariable(value = "group_id") final String groupId, @CookieValue("JSESSIONID") String cookie) throws NoSuchUserException, NoSuchGameException, AccessDeniedException {
//        gameService.getResults(groupId, cookie).forEach((k, v) -> System.out.println(k + " : " + v));
        return gameService.getResults(groupId, cookie);
    }

    @RequestMapping("/last/results")
    @ResponseBody
    LastResultResponse getLastResults(@CookieValue("JSESSIONID") String cookie) throws NoSuchUserException {
        return gameService.getLastResults(cookie);
    }
}