package com.mpp.Characters.model;

import jakarta.persistence.*;

@Entity
@Table(name = "spells")
public class Spell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private int id;
    private String name;
    private int weight;
    private int crit;

    public Spell() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Spell) {
            Spell e = (Spell) obj;
            return e.getId() == this.id && e.getName().equals(this.name) && e.getWeight() == this.weight && e.getCrit() == this.crit;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Egg{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", crit=" + crit +
                '}';
    }
}
