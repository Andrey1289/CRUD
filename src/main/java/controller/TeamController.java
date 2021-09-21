package controller;

import model.Developer;
import model.Team;
import repository.JsonTeamRepositoryImpl;

import java.util.List;

public class TeamController {
    private final JsonTeamRepositoryImpl tRepo = new JsonTeamRepositoryImpl();

    public Team get(Long id){
        return tRepo.getById(id);
    }

    public Team create( String name, List<Developer> developers){
     Team team = new Team();
     team.setName(name);
     team.setDevelopers(developers);
     return tRepo.save(team);
    }

    public Team update(Long id, String name, List<Developer> developers){
        Team team = new Team();
        team.setId(id);
        team.setName(name);
        team.setDevelopers(developers);
        return tRepo.update(team);
    }

    public void delete(Long id){
        tRepo.deleteById(id);
    }
}
