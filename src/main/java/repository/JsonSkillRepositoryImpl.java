package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Skill;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class JsonSkillRepositoryImpl implements SkillRepository {
    private  final Gson gson = new Gson();
    private  final String FILE_PATH ="C:\\Users\\dobpu\\IdeaProjects\\CRUDAplications\\src\\main\\resources\\skills.json";

     public Skill getById(Long id){
         getAllSkillsInternal().stream().filter(s -> s.getId().equals(id)).forEach(System.out::println);
         return  getAllSkillsInternal().stream().filter(s -> s.getId().equals(id)).findFirst().orElse(null);
   }
 public List<Skill> getAll() {
      return getAllSkillsInternal();
    }
   public Skill save(Skill skill){
      List<Skill> skills = getAllSkillsInternal();
      Long maxId = generateMaxId(skills);
       skill.setId(maxId);
       skills.add(skill);
       writeToFile(skills);
       return skill;
    }
   public Skill update(Skill skill){
       List<Skill> skills =  getAllSkillsInternal();
       skills.forEach(s -> {
           if(s.getId().equals(skill.getId())){
               s.setName(skill.getName());
           }
       });
       writeToFile(skills);
       return skill;
    }
   public void deleteById(Long id){
        List<Skill> skills =  getAllSkillsInternal();
        skills.removeIf(s -> s.getId().equals(id));
        writeToFile(skills);
    }
    private List<Skill> getAllSkillsInternal(){
        String json =null;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

        }catch (IOException e){
            e.printStackTrace();
        }
        Type targetClassType = new TypeToken<ArrayList<Skill>>(){}.getType();
        return gson.fromJson(json,targetClassType);

    }
    private void writeToFile(List<Skill> skills) {
        String json =null;
        String jsonCollection = gson.toJson(skills);
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
    private Long generateMaxId(List<Skill> skills) {
        Long id = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null).getId();
           id++;
         return id;

    }
}
