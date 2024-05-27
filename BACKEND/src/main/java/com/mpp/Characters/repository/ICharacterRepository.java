package com.mpp.Characters.repository;

import com.mpp.Characters.model.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharacterRepository extends JpaRepository<GameCharacter, Integer> { }
