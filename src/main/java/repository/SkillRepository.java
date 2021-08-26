package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import model.Skill;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SkillRepository {
    private static final Gson gson = new Gson();
    private static final String FILE_PATH ="C:\\Users\\dobpu\\IdeaProjects\\CRUDAplications\\src\\main\\resources\\skills.json";

    public static void main(String[] args) {
      getAll();
    }
    Skill getById(Long id){
        return null;
    }
 static List<Skill> getAll() {
        String json =null;
      try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

      }catch (IOException e){
        e.printStackTrace();
      }
     Type targetClassType = new TypeToken<ArrayList<Skill>>(){}.getType();
      ArrayList<Skill> skills = gson.fromJson(json, (Type) Skill.class);

      // System.out.println(skillObject.getId()+" "+ skillObject.getName());
       return skills;
    }
    Skill save(Skill skill){
        return null;
    }
    Skill update(Skill skill){
        return null;
    }
    void deleteById(Long id){

    }
}
