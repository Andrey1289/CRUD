package view;

import controller.DeveloperController;
import controller.SkillController;
import model.Developer;
import model.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DeveloperView {

        public static void main(String[] args) {
            DeveloperView developerView = new DeveloperView();
            developerView.devView();
        }

        private final DeveloperController dCont =new DeveloperController();
        public  void devView() {
            Skill skill = new Skill();
            ArrayList<Skill> skills = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
                System.out.println("Меню для создания Developer");
                System.out.println("Для завершения нажмите 'Exit'");

                String strUser ="";

                while(!strUser.equals("Exit")) {
                    System.out.println("Введите действие: 'create' 'get' 'update' 'delete' ");
                    strUser= br.readLine().trim();
                    switch (strUser) {
                        case "create":
                            System.out.println("Введите developer 'firstName'");
                            String firstName = br.readLine().trim();
                            System.out.println("Введите developer 'lastName'");
                            String lastName = br.readLine().trim();
                            System.out.println("создайте developer 'Skills'");
                            System.out.println("Введите 'Name'");
                            String name = br.readLine().trim();
                            skill.setName(name);
                            skills.add(skill);
                            dCont.create(firstName,lastName, skills);
                            System.out.println("Developer успешно создан");
                            break;
                        case "get":
                            Long id;
                            System.out.println("Введите 'ID' ");
                            try {
                                id = Long.parseLong(br.readLine().trim());
                            }catch (NumberFormatException e){
                                System.out.println("Вы ввели не число");
                                System.out.println("Введите 'ID'");
                                id = Long.parseLong(br.readLine().trim());
                            }
                           dCont.get(id);
                            break;
                        case "update":
                            id = 0L;
                            System.out.println("Введите 'ID' имя которого хотите изменить");
                            try {
                                id = Long.parseLong(br.readLine().trim());
                            }catch (NumberFormatException e){
                                System.out.println("Вы ввели не число");
                                System.out.println("Введите 'ID' имя которого хотите изменить");
                                id = Long.parseLong(br.readLine().trim());
                            }
                            System.out.println("Введите 'firstName'");
                            firstName = br.readLine().trim();
                            System.out.println("ВВедите 'lastName'");
                            lastName = br.readLine().trim();
                            System.out.println("Введите 'Developer Skill'  имя");
                            name = br.readLine().trim();
                            skill.setName(name);
                            skills.add(skill);
                            dCont.update(id,firstName,lastName,skills);
                            System.out.println("Developer успешно изменен");
                            break;
                        case "delete":
                            System.out.println("ВВедите 'ID' для удаления");
                            try {
                                id = Long.parseLong(br.readLine().trim());
                            }catch (NumberFormatException e){
                                System.out.println("Вы ввели не число");
                                System.out.println("ВВедите 'ID' для удаления");
                                id = Long.parseLong(br.readLine().trim());
                            }
                            dCont.delete(id);
                            System.out.println("Developer успешно удален");
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

