package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Skill;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SkillRepository {
    private  final Gson gson = new Gson();
    private  final String FILE_PATH ="C:\\Users\\dobpu\\IdeaProjects\\CRUDAplications\\src\\main\\resources\\skills.json";

       Skill getById(Long id){
        Skill ById = null;
        String json =null;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

        }catch (NullPointerException e){
            e.printStackTrace();
        } catch (IOException e){
        e.printStackTrace();
    }
        Type targetClassType = new TypeToken<ArrayList<Skill>>(){}.getType();
        ArrayList<Skill> skills = gson.fromJson(json,targetClassType);
        for (Skill skill : skills) {
            if (skill.getId().equals(id)){
             ById= skill;
            }
        }
return ById;

   }
  List<Skill> getAll() {
        String json =null;
      try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

      }catch (IOException e){
        e.printStackTrace();
      }
     Type targetClassType = new TypeToken<ArrayList<Skill>>(){}.getType();
    ArrayList<Skill> skills = gson.fromJson(json,targetClassType);
       return skills;
    }
    Skill save(Skill skill){
        String json =null;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

        }catch (IOException e){
            e.printStackTrace();
        }
        Type targetClassType = new TypeToken<ArrayList<Skill>>(){}.getType();
        ArrayList<Skill> skills = gson.fromJson(json,targetClassType);
          skills.add(skill);
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

        return null;
    }
    Skill update(Skill skill){
        String json =null;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

        }catch (IOException e){
            e.printStackTrace();
        }
        Type targetClassType = new TypeToken<ArrayList<Skill>>(){}.getType();
        ArrayList<Skill> skills = gson.fromJson(json,targetClassType);
        ListIterator<Skill> litr = skills.listIterator();
        while(litr.hasNext()){
            Skill element = litr.next();
            if(skill.getId().equals(element.getId()))
                litr.set(skill);
            if (skill.getName().equals(element.getName()))
                litr.set(skill);
        }

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
        return null;
    }
    void deleteById(Long id){
        String json =null;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

        }catch (NullPointerException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        Type targetClassType = new TypeToken<ArrayList<Skill>>(){}.getType();
        ArrayList<Skill> skills = gson.fromJson(json,targetClassType);
        ListIterator<Skill> litr = skills.listIterator();
        while(litr.hasNext()){
            Skill element = litr.next();
            if (element.getId().equals(id)){
                litr.remove();
            }
        }
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
}
