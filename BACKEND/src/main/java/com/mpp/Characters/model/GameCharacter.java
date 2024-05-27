package com.mpp.Characters.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
public class GameCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private int id;
    private String name;
    private int age;
    private String city;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cid", referencedColumnName = "cid")
    private List<Spell> spells;

    @ManyToOne
    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id")
    private ApplicationUser applicationUser;

    public GameCharacter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spellsList) {
        this.spells = spellsList;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GameCharacter) {
            GameCharacter character = (GameCharacter) obj;
            boolean spellsAreTheSame = true;
            if (character.spells == null) character.spells = new ArrayList<>();
            if (character.spells.size() != spells.size()) {
                spellsAreTheSame = false;
            } else {
                for (int i = 0; i < character.spells.size(); i++) {
                    if (character.spells.get(i).getId() != character.spells.get(i).getId()) {
                        spellsAreTheSame = false;
                        break;
                    }
                }
            }

            return id == character.getId() && name.equals(character.getName()) && age == character.getAge() && city.equals(character.getCity()) && spellsAreTheSame;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }
}
