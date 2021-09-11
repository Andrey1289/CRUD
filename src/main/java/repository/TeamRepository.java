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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


public class TeamRepository {

private final Gson gson = new Gson();
private final String FILE_PATH ="C:\\Users\\dobpu\\IdeaProjects\\CRUDAplications\\src\\main\\resources\\teams.json";

   public Team getById(Long id){
        Team teamById = null;
        String json = null;
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));) {
            json = br.readLine();
        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Type targetClassType = new TypeToken<ArrayList<Team>>(){}.getType();
        ArrayList<Team>teams = gson.fromJson(json,targetClassType);
        for (Team team : teams) {
            if(team.getId().equals(id)) {
                teamById = team;
                System.out.println(teamById);
            }
        }
        if(teamById == null)
            System.out.println("Нет такого ID");
      return teamById;
    }

  public List<Team> getAll(){
       String json= null;
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            json = br.readLine();

        }catch (IOException e){
            e.printStackTrace();
        }
       Type targetClassType = new TypeToken<ArrayList<Team>>(){}.getType();
        ArrayList<Team> teams = gson.fromJson(json,targetClassType);
        return teams;
    }

  public Team save(Team team){
        String json= "" ;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        if(json== null){
            ArrayList<Team> teams = new ArrayList<Team>(Arrays.asList(team));
            String jsonCollection = gson.toJson(teams);
            char[] buff = new char[jsonCollection.length()];
            jsonCollection.getChars(0,jsonCollection.length(),buff,0);
            try(FileWriter fw = new FileWriter(FILE_PATH);) {
                for (int i = 0; i < buff.length; i++) {
                    fw.write(buff[i]);
                }
            }catch (IOException e){
                e.printStackTrace();

            }}else{
            Type targetClassType = new TypeToken<ArrayList<Team>>(){}.getType();
            ArrayList<Team> teams = gson.fromJson(json,targetClassType);
            teams.add(team);
            String jsonCollection = gson.toJson(teams);
            char[] buff = new char[jsonCollection.length()];
            jsonCollection.getChars(0,jsonCollection.length(),buff,0);
            try(FileWriter fw = new FileWriter(FILE_PATH);) {
                for (int i = 0; i < buff.length; i++) {
                    fw.write(buff[i]);
                }
            }catch (IOException e){
                e.printStackTrace();
            }}
        return team;
    }

   public Team update(Team team){
       String json =null;
       try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
           json = bf.readLine();

       }catch (IOException e){
           e.printStackTrace();
       }
       Type targetClassType = new TypeToken<ArrayList<Team>>(){}.getType();
       ArrayList<Team> teams = gson.fromJson(json,targetClassType);
       ListIterator<Team> litr = teams.listIterator();
       while(litr.hasNext()){
           Team element = litr.next();
           if(team.getId().equals(element.getId()))
               litr.set(team);
           if (team.getName().equals(element.getName()))
               litr.set(team);
           if (team.getDevelopers().equals(element.getDevelopers()))
               litr.set(team);

       }
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
       return team;
           }

   public void deleteById(Long id){
       String json =null;
       try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
           json = bf.readLine();

       }catch (NullPointerException e){
           e.printStackTrace();
       } catch (IOException e){
           e.printStackTrace();
       }
       Type targetClassType = new TypeToken<ArrayList<Team>>(){}.getType();
       ArrayList<Team> teams = gson.fromJson(json,targetClassType);
       ListIterator<Team> litr = teams.listIterator();
       while(litr.hasNext()){
           Team element = litr.next();
           if (element.getId().equals(id)){
               litr.remove();
           }
       }
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
}
