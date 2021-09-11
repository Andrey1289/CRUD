package view;

import controller.DeveloperController;
import controller.TeamController;
import model.Developer;
import model.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TeamView {
 private final TeamController tcont = new TeamController();

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.tmView();
    }

    public  void tmView() {
        Developer developer =new Developer();
        ArrayList<Developer> developers = new ArrayList<>();
        Skill skill = new Skill();
        ArrayList<Skill> skills = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            System.out.println("Меню для создания Team");
            System.out.println("Для завершения нажмите 'EXIT'");
            String strUser ="";

            while(!strUser.equals("EXIT")) {
                System.out.println("Введите действие: 'create' 'get' 'update' 'delete' ");
                strUser= br.readLine().trim();
                switch (strUser) {
                    case "create":
                        System.out.println("Введите 'ID' Team");
                        Long id = Long.parseLong(br.readLine().trim());
                        System.out.println("Введите Team 'Name'");
                        String teamName = br.readLine().trim();
                        while(true){
                        System.out.println("Добавте 'Developers' в 'Team' для выхода напишите 'EXIT' для продолжения 'Enter'");
                        if(br.readLine().equals("EXIT"))
                            break;
                        System.out.println("Введите developer 'firstName'");
                        String firstName = br.readLine().trim();
                        System.out.println("Введите developer 'lastName'");
                        String lastName = br.readLine().trim();
                        developer.setFirstName(firstName);
                        developer.setLastName(lastName);
                        developers.add(developer);}
                       tcont.create(id,teamName,developers );
                        System.out.println("Team успешно создана");
                        break;
                    case "get":
                        System.out.println("Введите 'ID' ");
                        try {
                            id = Long.parseLong(br.readLine().trim());
                        }catch (NumberFormatException e){
                            System.out.println("Вы ввели не число");
                            System.out.println("Введите 'ID'");
                            id = Long.parseLong(br.readLine().trim());
                        }
                        tcont.get(id);
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
                       System.out.println("Введите Team 'Name'");
                        teamName = br.readLine().trim();
                       while(true){
                           System.out.println("Измените 'Developers' в 'Team' для выхода напишите 'EXIT' для продолжения 'Enter'");
                           if(br.readLine().equals("EXIT"))
                               break;
                           System.out.println("Введите developer 'firstName'");
                           String firstName = br.readLine().trim();
                           System.out.println("Введите developer 'lastName'");
                           String lastName = br.readLine().trim();
                           developer.setFirstName(firstName);
                           developer.setLastName(lastName);
                           developers.add(developer);}
                       tcont.update(id,teamName,developers );
                        System.out.println("Team успешно изменена");
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
                        tcont.delete(id);
                        System.out.println("Team успешно удалена");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
