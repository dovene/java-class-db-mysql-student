package com.dev.customer_order;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Repositories
        CustomerRepository customerRepo = new CustomerRepositoryImpl();
        OrderRepository orderRepo = new OrderRepositoryImpl();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        boolean exit = false;

        while (!exit) {
            System.out.println("\n----- MENU GESTION DE COMMANDES -----");
            System.out.println("1. Enregistrer un client");
            System.out.println("2. Enregistrer une commande");
            System.out.println("3. Afficher toutes les commandes");
            System.out.println("4. Afficher la commande la plus chère");
            System.out.println("5. Afficher le top 3 des clients (total commandes)");
            System.out.println("0. Quitter");
            System.out.print("Choisissez une option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Choix invalide. Réessayez.");
                continue;
            }

            switch (choice) {
                case 1:
                    // 1. Enregistrer un client
                    try {
                        System.out.print("Nom du client: ");
                        String name = scanner.nextLine();
                        System.out.print("Email du client: ");
                        String email = scanner.nextLine();

                        Customer customer = new Customer(name, email);
                        boolean added = customerRepo.addCustomer(customer);
                        if (added) {
                            System.out.println("Client enregistré avec succès!");
                        } else {
                            System.out.println("Échec de l'enregistrement du client.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    // 2. Enregistrer une commande
                    try {
                        // afficher la liste des clients pour choisir le client
                        List<Customer> customers = customerRepo.getAllCustomers();
                        System.out.println("\n--- Liste des clients ---");
                        for (Customer c : customers) {
                            System.out.println("ID: " + c.getId() + " | " + c.getName());
                        }

                        System.out.print("\nEntrez l'ID du client pour la commande: ");
                        int customerId = Integer.parseInt(scanner.nextLine());

                        System.out.print("Date de la commande (yyyy-MM-dd): ");
                        String dateStr = scanner.nextLine();
                        Date orderDate = sdf.parse(dateStr);

                        System.out.print("Montant total: ");
                        double totalAmount = Double.parseDouble(scanner.nextLine());

                        Order newOrder = new Order(customerId, orderDate, totalAmount);
                        boolean orderAdded = orderRepo.addOrder(newOrder);
                        if (orderAdded) {
                            System.out.println("Commande enregistrée avec succès!");
                        } else {
                            System.out.println("Échec de l'enregistrement de la commande.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (ParseException pe) {
                        System.out.println("Format de date invalide.");
                    }
                    break;

                case 3:
                    // 3. Afficher toutes les commandes
                    try {
                        List<Order> orders = orderRepo.getAllOrders();
                        System.out.println("\n--- Liste de toutes les commandes ---");
                        for (Order o : orders) {
                            System.out.println(o);
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    // 4. Afficher la commande la plus chère
                    try {
                        Order expensiveOrder = orderRepo.getMostExpensiveOrder();
                        if (expensiveOrder != null) {
                            System.out.println("La commande la plus chère: " + expensiveOrder);
                        } else {
                            System.out.println("Aucune commande trouvée.");
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    // 5. Afficher le top 3 des clients
                    try {
                        List<Customer> top3 = orderRepo.getTop3CustomersByTotalOrders();
                        System.out.println("\n--- TOP 3 Clients par total de commandes ---");
                        for (Customer c : top3) {
                            System.out.println(c);
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 0:
                    exit = true;
                    System.out.println("Fin du programme.");
                    break;

                default:
                    System.out.println("Choix invalide. Réessayez.");
            }
        }

        scanner.close();
    }
}

