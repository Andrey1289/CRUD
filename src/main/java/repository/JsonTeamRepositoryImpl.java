package repository;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Developer;
import model.Skill;
import model.Team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


public class JsonTeamRepositoryImpl {

private final Gson gson = new Gson();
private final String FILE_PATH ="C:\\Users\\dobpu\\IdeaProjects\\CRUDAplications\\src\\main\\resources\\teams.json";

   public Team getById(Long id){
     getAllTeamsInternal().stream().filter(team -> team.getId().equals(id)).forEach(System.out::println);
    return getAllTeamsInternal().stream().filter(team -> team.getId().equals(id)).findFirst().orElse(null);

    }

  public List<Team> getAll(){
        return getAllTeamsInternal();
    }

  public Team save(Team team){
       List<Team> teams = getAllTeamsInternal();
       Long maxid = generateMaxId(teams);
       team.setId(maxid);
       teams.add(team);
       writeToFile(teams);
       return team;
    }

   public Team update(Team team){
     List<Team> teams = getAllTeamsInternal();
      teams.forEach(tm ->{
          if(tm.getId().equals(team.getId()))
          tm.setName(team.getName());
          tm.setDevelopers(team.getDevelopers());

      });
       writeToFile(teams);
       return team;
           }

   public void deleteById(Long id){
      List<Team> teams = getAllTeamsInternal();
      teams.removeIf(tm -> tm.getId().equals(id));
      writeToFile(teams);
    }



    private List<Team> getAllTeamsInternal(){
        String json =null;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

        }catch (NullPointerException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        Type targetClassType = new TypeToken<ArrayList<Team>>(){}.getType();
        return  gson.fromJson(json,targetClassType);
    }
    private void writeToFile(List<Team> teams) {
        String jsonCollection = gson.toJson(teams);
        char[] buff = new char[jsonCollection.length()];
        jsonCollection.getChars(0,jsonCollection.length(),buff,0);
        try( FileWriter fw = new FileWriter(FILE_PATH);) {
            for (int i = 0; i < buff.length; i++) {
                fw.write(buff[i]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private Long generateMaxId(List<Team> teams) {
       Long id = teams.stream().max(Comparator.comparing(Team::getId)).orElse(null).getId();
       id++;
               return id;
    }
}
