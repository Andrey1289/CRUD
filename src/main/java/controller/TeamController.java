package controller;

import model.Developer;
import model.Team;
import repository.TeamRepository;

import java.util.List;

public class TeamController {
    private final TeamRepository tRepo = new TeamRepository();

    public Team get(Long id){
        return tRepo.getById(id);
    }

    public Team create(Long id, String name, List<Developer> developers){
     Team team = new Team();
     team.setId(id);
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
