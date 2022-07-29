package model;

import adminAppUI.Main;
import costumerAppUI.sample.OutputProcessor;

public class Good {
    int id;
    String name;
    final String unit;
    int vol;
    boolean available;
    long buyPrice;
    long sellPrice;
    final long totalBuyPrice;
    long totalSellPrice;
    long sellVol;
    long profit;
    int orderVol;

    public Good(String n, String u, int v, long bP, long sP){
        name = n;
        unit = u;
        vol = v;
        buyPrice = bP;
        sellPrice = sP;
        totalBuyPrice = bP*v;
        totalSellPrice = 0;
        sellVol = 0;
        profit = 0;
        available = true;
        id = Math.abs(name.hashCode());
        orderVol = 0;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getVol() {
        return vol;
    }

    public long getSellPrice() {
        return sellPrice;
    }

    public String getUnit() {
        return unit;
    }

    public long getBuyPrice() {
        return buyPrice;
    }

    public long getTotalBuyPrice() {
        return totalBuyPrice;
    }

    public long getTotalSellPrice() {
        return totalSellPrice;
    }

    public long getSellVol() {
        return sellVol;
    }

    public long getProfit() {
        return profit;
    }

    public int getOrderVol() {
        return orderVol;
    }

    public static int checkCorrectInputs(String name, String buyString, String sellString, String volString){
        for (Good good : Market.getStorage()) { //-1 if good already Exists. -2 if invalid input.
            if(good.name.equals(name))
                return -1;
        }
        long sellPrice, buyPrice;
        int volume;

        try{
            sellPrice = Long.parseLong(sellString);
            buyPrice = Long.parseLong(buyString);
            volume = Integer.parseInt(volString);
            if(sellPrice < 0 || buyPrice < 0 || volume < 0)
                return -2;
        } catch (Exception e){
            return -2;
        }

        return 0;
    }

    public static Good addGood(String name, String buyString, String sellString, String volString){
        Good newGood = new Good(name, "unit", Integer.parseInt(volString), Long.parseLong(buyString), Long.parseLong(sellString));
        Market.storage.add(newGood);
        Market.saveAll();
        return newGood;
    }

    void sell(int selledV){
        this.vol -= selledV;
        this.sellVol += selledV;
        this.totalSellPrice += selledV*this.sellPrice;
        this.profit += selledV*(this.sellPrice - this.buyPrice);
        if(this.vol <= 0)
            this.available = false;
        System.out.println(this.name + " " + this.vol);
    }

    public void changeNameRequest(String newName){
        for (Good good : Market.storage) {
            if(good.name.equals(newName)){
                OutputProcessor.showMessage("This name already Exists!");
                return;
            }
        }
        name = newName;
        id = Math.abs(name.hashCode());
        Market.saveAll();
    }

    public void changeVolumeRequest(int newVol){
        if(newVol < 0){
            OutputProcessor.showMessage("Input should be positive");
            return;
        }
        vol = newVol;
        if(newVol > 0)
            available = true;
        else
            available = false;
        Market.saveAll();
    }

    public void changeBuyPriceRequest(Long newValue){
        if(newValue < 0){
            OutputProcessor.showMessage("Input should be positive");
            return;
        }
        buyPrice = newValue;
        Market.saveAll();
    }

    public void changeSellPriceRequest(Long newValue){
        if(newValue < 0){
            OutputProcessor.showMessage("Input should be positive");
            return;
        }
        sellPrice = newValue;
        Market.saveAll();
    }





}
