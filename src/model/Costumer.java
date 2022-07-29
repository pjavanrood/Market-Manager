package model;

import com.google.gson.*;

import costumerAppUI.sample.OutputProcessor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;

public class Costumer {
    final String id;
    String password;
    String name;
    int orderNum;
    ArrayList<Integer> orders;
    long balance;
    static Costumer costumer;

    public static void main(String[] args) throws IOException {
        Market.importGoods();
        Market.importOrders();
        Market.importCostumers();
        costumer = null;
        costumerAppUI.sample.Main.main(args);
    }

    public static Costumer findCostumer(String id){
        for (Costumer costumer : Market.costumers) {
            if(costumer.id.equals(id))
                return costumer;
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public static Costumer getCostumer() {
        return costumer;
    }

    public static long getBalance() {
        return costumer.balance;
    }

    public static boolean checkPassword(Costumer costumer, String password){
        if(costumer.password.equals(password))
            return true;
        return false;
    }

    public static Costumer addUser(String name, String id, String password) throws IOException {
        Costumer c = new Costumer(name, id, password);
        Gson gson = new Gson();
        FileWriter jsonFile = new FileWriter("Data//Costumers//costumerList.txt", true);
        jsonFile.write(gson.toJson(c, Costumer.class) + "\n");
        Market.costumers.add(c);
        jsonFile.close();
        return c;
    }

    public Costumer(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.orderNum = 0;
        this.orders = new ArrayList<>();
        this.balance = 1000000;
    }

    public static void setCostumer(Costumer costumer) {
        Costumer.costumer = costumer;
    }

    public static int checkQuantity(Good good, String quantity){ //-1 if invalid input;
        int q = -1;
        try {
            q = Integer.parseInt(quantity);
        } catch (NumberFormatException exception){
            return -1;
        }
        if(good.getVol() < q){
            return -2;
        }
        if(q * good.sellPrice > costumer.balance)
            return -3;

        return 0;
    }

    public static void buyGood(Costumer costumer, Good good, int quantity){
        Order order = new Order(costumer.id, good.id, costumer.orderNum, quantity, good.sellPrice);
        Market.orders.add(order);
        costumer.orders.add(costumer.orderNum);
        costumer.orderNum++;
        good.sell(order.orderVol);
        good.orderVol++;
        costumer.balance -= quantity*good.sellPrice;
        OutputProcessor.buySuccessful(order.id);
        Market.saveAll();
    }
}
