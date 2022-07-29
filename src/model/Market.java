package model;

import adminAppUI.Main;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Market {
    static public ArrayList<Good> storage = new ArrayList<>();
    static public ArrayList<Order> orders = new ArrayList<>();
    static ArrayList<Costumer> costumers = new ArrayList<>();

    public static void main(String arg[]) throws IOException {
        importGoods();
        importOrders();
        importCostumers();
        Main.main(arg);
    }


    public static ArrayList<Good> getStorage(){
        return storage;
    }

    static void importGoods() throws IOException {
        File goodsFile = new File("Data//Goods//GoodsList.txt");
        Scanner scanner = new Scanner(goodsFile);
        Gson gson = new Gson();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            storage.add(gson.fromJson(line, Good.class));
        }
        System.out.println("Goods imported");
    }

    static void saveGoods() throws IOException {
        File goodsFile = new File("Data//Goods//GoodsList.txt");
        FileWriter fileWriter = new FileWriter(goodsFile);
        Gson gson = new Gson();
        for (Good good : storage) {
            fileWriter.write(gson.toJson(good) + "\n");
        }
        fileWriter.close();
    }

    public static void saveOrders() throws IOException {
        FileWriter fileWriter = new FileWriter("Data//Orders//orderList.txt");
        Gson gson = new Gson();
        for (Order order : orders) {
            fileWriter.write(gson.toJson(order, Order.class) + "\n");
        }
        fileWriter.close();
    }

    static void importOrders() throws IOException {
        File ordersFile = new File("Data//Orders//orderList.txt");
        Scanner scanner = new Scanner(ordersFile);
        Gson gson = new Gson();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            orders.add(gson.fromJson(line, Order.class));
        }
    }

    static void importCostumers() throws IOException {
        File costumersFile = new File("Data//Costumers//costumerList.txt");
        Scanner scanner = new Scanner(costumersFile);
        Gson gson = new Gson();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            costumers.add(gson.fromJson(line, Costumer.class));
        }

    }

    public static void saveCostumers() throws IOException {
        FileWriter fileWriter = new FileWriter("..//Data//Costumers//costumerList.txt");
        Gson gson = new Gson();
        for (Costumer costumer : costumers) {
            fileWriter.write(gson.toJson(costumer, Costumer.class) + "\n");
        }
        fileWriter.close();
    }

    public static void saveAll(){
        try {
            saveGoods();
            saveOrders();
            saveCostumers();
        }catch (Exception exception){

        }
    }

    public static void removeGood(Good good){
        storage.remove(good);
        saveAll();
    }


}

