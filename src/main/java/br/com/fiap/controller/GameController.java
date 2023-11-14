package br.com.fiap.controller;

import br.com.fiap.model.Game;
import br.com.fiap.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String Games(Model model) {
        model.addAttribute("games", gameService.findAll());
        return "list-games";
    }

    @GetMapping("/new")
    public String showFormNewGame(Model model) {
        model.addAttribute("game", new Game());
        return "/register-game";
    }

    @PostMapping
    public String addGames(@ModelAttribute @Valid Game game, BindingResult result) {
        if(result.hasErrors()) {
            return "cadastrar-game";
        }
        gameService.save(game);
        return "redirect:/games";
    }

    @PostMapping("/editar/{id}")
    public String updateGame(@PathVariable Long id, @ModelAttribute @Valid Game game, BindingResult result) {
        if(result.hasErrors()) {
            return "cadastrar-game";
        }
        gameService.save(game);
        return "redirect:/games";
    }

    @GetMapping("delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        gameService.deleteById(id);
        return "redirect:/games";
    }
}
