package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Developer;
import model.Skill;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class DeveloperRepository {
    private  final Gson gson = new Gson();
    private  final String FILE_PATH = ("C:\\Users\\dobpu\\IdeaProjects\\CRUDAplications\\src\\main\\resources\\developers.json");

    public Developer getById(Long id){
        Developer devById = null;
        String json = null;
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));) {
         json = br.readLine();
        }catch (NullPointerException e){
          e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Type targetClassType = new TypeToken<ArrayList<Developer>>(){}.getType();
        ArrayList<Developer> developers = gson.fromJson(json,targetClassType);
        for (Developer developer : developers) {
            if(developer.getId().equals(id)){
                devById = developer;
            }
           }
        if(devById == null)
              System.out.println("Нет такого ID");
        return devById;
    }
    List<Developer> getAll(){
        String json =null;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

        }catch (IOException e){
            e.printStackTrace();
        }
        Type targetClassType = new TypeToken<ArrayList<Developer>>(){}.getType();
        ArrayList<Developer> developers = gson.fromJson(json,targetClassType);
        return developers;
    }
 public Developer save(Developer developer){
        String json= "" ;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        if(json== null){
            ArrayList<Developer> developers = new ArrayList<Developer>(Arrays.asList(developer));
            String jsonCollection = gson.toJson(developers);
            char[] buff = new char[jsonCollection.length()];
            jsonCollection.getChars(0,jsonCollection.length(),buff,0);
            try(FileWriter fw = new FileWriter(FILE_PATH);) {
                for (int i = 0; i < buff.length; i++) {
                    fw.write(buff[i]);
                }
            }catch (IOException e){
                e.printStackTrace();
        }}else{
        Type targetClassType = new TypeToken<ArrayList<Developer>>(){}.getType();
        ArrayList<Developer> developers = gson.fromJson(json,targetClassType);
        developers.add(developer);
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
     return developer;
    }
   public Developer update(Developer developer ){
        String json =null;
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH));){
            json = bf.readLine();

        }catch (IOException e){
            e.printStackTrace();
        }
        Type targetClassType = new TypeToken<ArrayList<Developer>>(){}.getType();
        ArrayList<Developer> skills = gson.fromJson(json,targetClassType);
        ListIterator<Developer> litr = skills.listIterator();
        while(litr.hasNext()){
            Developer element = litr.next();
            if(developer.getId().equals(element.getId()))
                litr.set(developer);
            if (developer.getFirstName().equals(element.getFirstName()))
                litr.set(developer);
            if (developer.getLastName().equals(element.getLastName()))
                litr.set(developer);
            if (developer.getSkills().equals(element.getSkills()))
                litr.set(developer);
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
        return developer;
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
        Type targetClassType = new TypeToken<ArrayList<Developer>>(){}.getType();
        ArrayList<Developer> developers = gson.fromJson(json,targetClassType);
        ListIterator<Developer> litr = developers.listIterator();
        while(litr.hasNext()){
            Developer element = litr.next();
            if (element.getId().equals(id)){
                litr.remove();
            }
        }
        String jsonCollection = gson.toJson(developers);
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
