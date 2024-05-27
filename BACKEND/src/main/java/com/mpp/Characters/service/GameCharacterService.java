package com.mpp.Characters.service;

import com.mpp.Characters.model.GameCharacter;
import com.mpp.Characters.model.Spell;
import com.mpp.Characters.repository.ICharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GameCharacterService implements IService {

    @Autowired
    private ICharacterRepository gameCharacterRepository;

    @Override
    public boolean saveGameCharacter(GameCharacter gameCharacter) {
        GameCharacter savedGameCharacter = gameCharacterRepository.save(gameCharacter);
        return gameCharacter.equals(savedGameCharacter);
    }

    @Override
    public List<GameCharacter> getAllGameCharacters() {
        return gameCharacterRepository.findAll();
    }

    @Override
    public GameCharacter getGameCharacterById(int id) {
        return gameCharacterRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteGameCharacter(int id) {
        try {
            gameCharacterRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateGameCharacter(int id, GameCharacter updatedGameCharacter) {
        GameCharacter gameCharacter = gameCharacterRepository.getReferenceById(id);
        gameCharacter.setName(updatedGameCharacter.getName());
        gameCharacter.setAge(updatedGameCharacter.getAge());
        gameCharacter.setCity(updatedGameCharacter.getCity());
        gameCharacter.setSpells(updatedGameCharacter.getSpells());
        GameCharacter savedGameCharacter = gameCharacterRepository.save(gameCharacter);
        updatedGameCharacter.setId(id);
        return savedGameCharacter.equals(updatedGameCharacter);
    }

    @Override
    public List<Spell> getSpellsByGameCharacterId(int gameCharacterId) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(gameCharacterId).orElse(null);
        if (gameCharacter == null) return null;
        return gameCharacter.getSpells();
    }

    @Override
    public Spell getSpellById(int gameCharacterId, int spellId) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(gameCharacterId).orElse(null);
        if (gameCharacter == null) return null;
        List<Spell> spells = gameCharacter.getSpells();
        if (spells == null) return null;
        return spells.stream().filter(s -> s.getId() == spellId).findFirst().orElse(null);
    }

    @Override
    public boolean addSpell(int gameCharacterId, Spell spell) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(gameCharacterId).orElse(null);
        if (gameCharacter == null) return false;
        gameCharacter.getSpells().add(spell);
        gameCharacterRepository.save(gameCharacter);
        return true;
    }

    @Override
    public boolean deleteSpell(int gameCharacterId, int spellId) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(gameCharacterId).orElse(null);
        if (gameCharacter == null) return false;
        List<Spell> spells = new ArrayList<>();
        for (Spell spell : gameCharacter.getSpells()) {
            if (spell.getId() != spellId) {
                spells.add(spell);
            }
        }
        gameCharacter.setSpells(spells);
        gameCharacterRepository.save(gameCharacter);
        return true;
    }

    @Override
    public boolean updateSpell(int gameCharacterId, int spellId, Spell updatedSpell) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(gameCharacterId).orElse(null);
        if (gameCharacter == null) return false;
        Spell spell = gameCharacter.getSpells().stream().filter(s -> s.getId() == spellId).findFirst().orElse(null);
        if (spell == null) return false;
        spell.setName(updatedSpell.getName());
        spell.setWeight(updatedSpell.getWeight());
        spell.setCrit(updatedSpell.getCrit());
        gameCharacterRepository.save(gameCharacter);
        return true;
    }

    @Override
    public boolean otherUser(String username, int gameCharacterId) {
        return !getGameCharacterById(gameCharacterId).getApplicationUser().getUsername().equals(username);
    }

    @Override
    public List<GameCharacter> getAllGameCharactersByUser(String username) {
        return getAllGameCharacters()
                .stream()
                .filter(gameCharacter -> Objects.equals(gameCharacter.getApplicationUser().getUsername(), username))
                .collect(Collectors.toList());
    }

    @Override
    public GameCharacter getGameCharacterByIdByUser(String username, int gameCharacterId) {
        GameCharacter gameCharacter = getGameCharacterById(gameCharacterId);
        if (gameCharacter.getApplicationUser().getUsername().equals(username)) return gameCharacter;
        return null;
    }

    @Override
    public boolean updateGameCharacterByUser(String username, int gameCharacterId, GameCharacter gameCharacter) {
        if (otherUser(username, gameCharacterId)) return false;
        return updateGameCharacter(gameCharacterId, gameCharacter);
    }

    @Override
    public boolean deleteGameCharacterByUser(String username, int id) {
        if (otherUser(username, id)) return false;
        return deleteGameCharacter(id);
    }

    @Override
    public List<Spell> getSpellsByGameCharacterIdByUser(String username, int gameCharacterId) {
        if (otherUser(username, gameCharacterId)) return new ArrayList<>();
        return getSpellsByGameCharacterId(gameCharacterId);
    }

    @Override
    public Spell getSpellByIdByUser(String username, int gameCharacterId, int spellId) {
        if (otherUser(username, gameCharacterId)) return null;
        return getSpellById(gameCharacterId, spellId);
    }

    @Override
    public boolean addSpellByUser(String username, int gameCharacterId, Spell spell) {
        if (otherUser(username, gameCharacterId)) return false;
        return addSpell(gameCharacterId, spell);
    }

    @Override
    public boolean updateSpellByUser(String username, int gameCharacterId, int spellId, Spell spell) {
        if (otherUser(username, gameCharacterId)) return false;
        return updateSpell(gameCharacterId, spellId, spell);
    }

    @Override
    public boolean deleteSpellByUser(String username, int gameCharacterId, int spellId) {
        if (otherUser(username, gameCharacterId)) return false;
        return deleteSpell(gameCharacterId, spellId);
    }
}
