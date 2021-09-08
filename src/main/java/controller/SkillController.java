package controller;

import model.Skill;
import repository.SkillRepository;

public class SkillController {
    private final SkillRepository repo = new SkillRepository();

    public Skill get(Long id){
     return repo.getById(id);
    }
     public Skill create(Long id,String name){
        Skill s = new Skill();
        s.setId(id);
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
