package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;


public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User User1 = new User("User1", "Lastname1", "user1@mail.ru");
        User User2 = new User("User2", "Lastname2", "user2@mail.ru");
        User User3 = new User("User3", "Lastname3", "user3@mail.ru");
        User User4 = new User("User4", "Lastname4", "user4@mail.ru");
        User User5 = new User("User5", "Lastname5", "user5@mail.ru");


        Car Car1 = new Car("Car1", 1);
        Car Car2 = new Car("Car2", 2);
        Car Car3 = new Car("Car3", 3);
        Car Car4 = new Car("Car4", 4);

        userService.add(User1.setCar(Car1).setUser(User1));
        userService.add(User2.setCar(Car2).setUser(User2));
        userService.add(User3.setCar(Car3).setUser(User3));
        userService.add(User4.setCar(Car4).setUser(User4));
        userService.add(User5);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar() != null ? "Car = " + user.getCar() : "Нет авто");
            System.out.println();
        }

        System.out.println(userService.getUsercar("Car1", 1));

        try {
            System.out.println(userService.getUsercar("Car5", 5));
        } catch (NoResultException e) {
            System.out.println("Нет авто");
        }

        context.close();
    }
}
