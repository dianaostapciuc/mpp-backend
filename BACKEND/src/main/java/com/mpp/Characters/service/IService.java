package com.mpp.Characters.service;

import com.mpp.Characters.model.GameCharacter;
import com.mpp.Characters.model.Spell;

import java.util.List;

public interface IService {
    boolean saveGameCharacter(GameCharacter gameCharacter);

    List<GameCharacter> getAllGameCharacters();

    GameCharacter getGameCharacterById(int id);

    boolean deleteGameCharacter(int id);

    boolean updateGameCharacter(int id, GameCharacter updatedGameCharacter);

    List<Spell> getSpellsByGameCharacterId(int gameCharacterId);

    Spell getSpellById(int gameCharacterId, int spellId);

    boolean addSpell(int gameCharacterId, Spell spell);

    boolean deleteSpell(int gameCharacterId, int spellId);

    boolean updateSpell(int gameCharacterId, int spellId, Spell updatedSpell);

    boolean otherUser(String username, int gameCharacterId);

    List<GameCharacter> getAllGameCharactersByUser(String username);

    GameCharacter getGameCharacterByIdByUser(String username, int gameCharacterId);

    boolean updateGameCharacterByUser(String username, int gameCharacterId, GameCharacter gameCharacter);

    boolean deleteGameCharacterByUser(String username, int id);

    List<Spell> getSpellsByGameCharacterIdByUser(String username, int gameCharacterId);

    Spell getSpellByIdByUser(String username, int gameCharacterId, int spellId);

    boolean addSpellByUser(String username, int gameCharacterId, Spell spell);

    boolean updateSpellByUser(String username, int gameCharacterId, int spellId, Spell spell);

    boolean deleteSpellByUser(String username, int gameCharacterId, int spellId);
}
