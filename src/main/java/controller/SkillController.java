package controller;

import model.Skill;
import repository.JsonSkillRepositoryImpl;

public class SkillController {
    private final JsonSkillRepositoryImpl repo = new JsonSkillRepositoryImpl();

    public Skill get(Long id){
     return repo.getById(id);
    }
     public Skill create(String name){
        Skill s = new Skill();
        s.setName(name);
        return repo.save(s);
     }
     public void  delete(Long id){
       repo.deleteById(id);
     }
     public Skill update(Long id,String name){
        Skill s = new Skill();
        s.setId(id);
        s.setName(name);
        return repo.update(s);
     }

}
