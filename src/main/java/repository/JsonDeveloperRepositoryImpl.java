package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import model.Developer;
import model.Skill;

import java.awt.event.AdjustmentEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class JsonDeveloperRepositoryImpl {
    private  final Gson gson = new Gson();
    private  final String FILE_PATH = ("C:\\Users\\dobpu\\IdeaProjects\\CRUDAplications\\src\\main\\resources\\developers.json");

    public Developer getById(Long id){
        getAllDevelopersInternal().stream().filter(dev -> dev.getId().equals(id)).forEach(System.out::println);
        return getAllDevelopersInternal().stream().filter(dev -> dev.getId().equals(id)).findFirst().orElse(null);
    }
   public List<Developer> getAll(){
        return getAllDevelopersInternal();
    }


 public Developer save(Developer developer){
      List<Developer> developers = getAllDevelopersInternal();
      Long maxId = generateMaxId(developers);
      developer.setId(maxId);
      developers.add(developer);
      writeToFile(developers);
     return developer;
    }
   public Developer update(Developer developer ){
         List<Developer> developers = getAllDevelopersInternal();
         developers.forEach(dev ->{
             if(dev.getId().equals(developer.getId()))
                 dev.setFirstName(developer.getFirstName());
                 dev.setLastName(developer.getLastName());
                 dev.setSkills(developer.getSkills());

         });
         writeToFile(developers);
        return developer;
    }
   public void deleteById(Long id){
        List<Developer> developers = getAllDevelopersInternal();
        developers.removeIf(dev -> dev.getId().equals(id));
        writeToFile(developers);
    }


    private List<Developer> getAllDevelopersInternal(){
        String json =null;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

        }catch (NullPointerException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        Type targetClassType = new TypeToken<ArrayList<Developer>>(){}.getType();
        return gson.fromJson(json,targetClassType);

    }
    private void writeToFile(List<Developer> developers ) {
        String jsonCollection = gson.toJson(developers);
        char[] buff = new char[jsonCollection.length()];
        jsonCollection.getChars(0,jsonCollection.length(),buff,0);
        try(FileWriter fw = new FileWriter(FILE_PATH);) {
            for (int i = 0; i < buff.length; i++) {
                fw.write(buff[i]);
            }
        }catch (IOException e){
            e.printStackTrace();
        }}

    private Long generateMaxId(List<Developer> developers) {
        Long id = developers.stream().max(Comparator.comparing(Developer::getId)).orElse(null).getId();
        id++;
        return id;
    }
  }
