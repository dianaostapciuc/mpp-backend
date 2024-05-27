package com.mpp.Characters.controller;

import com.mpp.Characters.model.ApplicationUser;
import com.mpp.Characters.model.GameCharacter;
import com.mpp.Characters.model.Spell;
import com.mpp.Characters.service.IService;
import com.mpp.Characters.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/character")
public class Controller {

    @Autowired
    private IService characterService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addGameCharacter(@RequestBody GameCharacter gameCharacter, Principal principal) {
        ApplicationUser applicationUser = (ApplicationUser) userService.loadUserByUsername(principal.getName());
        gameCharacter.setApplicationUser(applicationUser);
        if(characterService.saveGameCharacter(gameCharacter)) {
            return "New game character is added!";
        } else {
            return "Game character could not be added!";
        }
    }

    @GetMapping("/getAll")
    public List<GameCharacter> getAllGameCharacters(Principal principal) {
        return characterService.getAllGameCharactersByUser(principal.getName());
    }

    @GetMapping("/get/{id}")
    public GameCharacter getGameCharacterById(@PathVariable int id, Principal principal) {
        return characterService.getGameCharacterByIdByUser(principal.getName(), id);
    }

    @PutMapping("/update/{id}")
    public String updateGameCharacter(@PathVariable int id, @RequestBody GameCharacter gameCharacter, Principal principal) {
        if(characterService.updateGameCharacterByUser(principal.getName(), id, gameCharacter)) {
            return "Game character is updated!";
        }
        return "Game character could not be updated!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGameCharacter(@PathVariable int id, Principal principal) {
        if(characterService.deleteGameCharacterByUser(principal.getName(), id)) {
            return "Game character is deleted!";
        }
        return "Game character could not be deleted!";
    }

    // SPELLS

    @GetMapping("/{gameCharacterId}/spell/getAll")
    public List<Spell> getSpellsByGameCharacterId(@PathVariable int gameCharacterId, Principal principal) {
        return characterService.getSpellsByGameCharacterIdByUser(principal.getName(), gameCharacterId);
    }

    @GetMapping("/{gameCharacterId}/spell/get/{spellId}")
    public Spell getSpellById(@PathVariable int gameCharacterId, @PathVariable int spellId, Principal principal) {
        return characterService.getSpellByIdByUser(principal.getName(), gameCharacterId, spellId);
    }

    @PostMapping("/{gameCharacterId}/spell/add")
    public String addSpell(@PathVariable int gameCharacterId, @RequestBody Spell spell, Principal principal) {
        if (characterService.addSpellByUser(principal.getName(), gameCharacterId, spell)) {
            return "Spell is added!";
        }
        return "Spell could not be added!";
    }

    @PutMapping("/{gameCharacterId}/spell/update/{spellId}")
    public String updateSpell(@PathVariable int gameCharacterId, @PathVariable int spellId, @RequestBody Spell spell, Principal principal) {
        if (characterService.updateSpellByUser(principal.getName(), gameCharacterId, spellId, spell)) {
            return "Spell is updated!";
        }
        return "Spell could not be updated!";
    }

    @DeleteMapping("/{gameCharacterId}/spell/delete/{spellId}")
    public String deleteSpell(@PathVariable int gameCharacterId, @PathVariable int spellId, Principal principal) {
        if(characterService.deleteSpellByUser(principal.getName(), gameCharacterId, spellId)) {
            return "Spell is deleted!";
        }
        return "Spell could not be deleted!";
    }
}
