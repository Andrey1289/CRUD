package controller;

import model.Developer;

import model.Skill;
import repository.JsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    private final JsonDeveloperRepositoryImpl devRepo = new JsonDeveloperRepositoryImpl();

    public Developer get(Long id){
        return devRepo.getById(id);
    }

    public Developer create(String firstName, String lastName, List<Skill> skills){
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
       developer.setSkills(skills);
      return devRepo.save(developer);
    }

    public Developer update(Long id, String firstName, String lastName, List<Skill> skills){
        Developer developer = new Developer();
        developer.setId(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSkills(skills);
        return devRepo.update(developer);
    }

    public void delete(Long id){
        devRepo.deleteById(id);
    }
}
