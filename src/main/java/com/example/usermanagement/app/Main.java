package com.example.usermanagement.app;

import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import com.example.usermanagement.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenu();
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1" -> createUser(scanner);
                    case "2" -> updateUser(scanner);
                    case "3" -> deleteUser(scanner);
                    case "4" -> searchUser(scanner);
                    case "5" -> listUsers();
                    case "6" -> loginUser(scanner);
                    case "0" -> {
                        System.out.println("Sortie...");
                        return;
                    }
                    default -> System.out.println("Choix invalide. Réessayez.");
                }
                System.out.println();
            }
        }
    }

    private static void printMenu() {
        System.out.println("=== Gestion des utilisateurs ===");
        System.out.println("1. Ajouter un utilisateur");
        System.out.println("2. Modifier un utilisateur");
        System.out.println("3. Supprimer un utilisateur");
        System.out.println("4. Rechercher un utilisateur");
        System.out.println("5. Lister tous les utilisateurs");
        System.out.println("6. Login utilisateur");
        System.out.println("0. Quitter");
        System.out.print("Choix : ");
    }

    private static void createUser(Scanner scanner) {
        System.out.print("Nom : ");
        String nom = scanner.nextLine().trim();
        System.out.print("Prénom : ");
        String prenom = scanner.nextLine().trim();
        System.out.print("Login : ");
        String login = scanner.nextLine().trim();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine().trim();

        User user = new User(nom, prenom, login, password);
        if (userService.createUser(user)) {
            System.out.println("Utilisateur ajouté avec l'ID " + user.getId());
        } else {
            System.out.println("Échec de l'ajout de l'utilisateur.");
        }
    }

    private static void updateUser(Scanner scanner) {
        System.out.print("ID de l'utilisateur à modifier : ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isEmpty()) {
            System.out.println("Utilisateur introuvable.");
            return;
        }

        User user = optionalUser.get();
        System.out.print("Nom (" + user.getNom() + "): ");
        String nom = scanner.nextLine().trim();
        System.out.print("Prénom (" + user.getPrenom() + "): ");
        String prenom = scanner.nextLine().trim();
        System.out.print("Login (" + user.getLogin() + "): ");
        String login = scanner.nextLine().trim();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine().trim();

        if (!nom.isEmpty()) user.setNom(nom);
        if (!prenom.isEmpty()) user.setPrenom(prenom);
        if (!login.isEmpty()) user.setLogin(login);
        if (!password.isEmpty()) user.setPassword(password);

        if (userService.updateUser(user)) {
            System.out.println("Utilisateur modifié avec succès.");
        } else {
            System.out.println("Échec de la modification.");
        }
    }

    private static void deleteUser(Scanner scanner) {
        System.out.print("ID de l'utilisateur à supprimer : ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        if (userService.deleteUser(id)) {
            System.out.println("Utilisateur supprimé.");
        } else {
            System.out.println("Échec de la suppression ou utilisateur introuvable.");
        }
    }

    private static void searchUser(Scanner scanner) {
        System.out.print("Rechercher par ID, login, nom ou prénom : ");
        String query = scanner.nextLine().trim();

        try {
            int id = Integer.parseInt(query);
            Optional<User> user = userService.getUserById(id);
            user.ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Aucun utilisateur trouvé pour l'ID donné."));
            return;
        } catch (NumberFormatException ignored) {
        }

        Optional<User> byLogin = userService.getUserByLogin(query);
        if (byLogin.isPresent()) {
            System.out.println(byLogin.get());
            return;
        }

        List<User> users = userService.searchUsers(query);
        if (users.isEmpty()) {
            System.out.println("Aucun utilisateur trouvé.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void listUsers() {
        List<User> users = userService.listUsers();
        if (users.isEmpty()) {
            System.out.println("Aucun utilisateur.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private static void loginUser(Scanner scanner) {
        System.out.print("Login : ");
        String login = scanner.nextLine().trim();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine().trim();

        Optional<User> user = userService.getUserByLogin(login);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            System.out.println("Connexion réussie. Bienvenue " + user.get().getPrenom() + " !");
        } else {
            System.out.println("Login ou mot de passe invalide.");
        }
    }
}
