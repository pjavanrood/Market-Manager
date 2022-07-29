package model;

import java.time.LocalDateTime;

public class Order {
    final String id;
    final String costumerId;
    boolean orderCancel;
    int orderedGoodId;
    int orderVol;
    LocalDateTime orderTime;
    Long totalPrice;

    public Order(String costumerId, int goodId, int num, int vol, Long price) {
        this.id = costumerId + "_O" + num;
        this.costumerId = costumerId;
        this.orderCancel = false;
        this.orderedGoodId = goodId;
        this.orderVol = vol;
        this.orderTime = LocalDateTime.now();
        this.totalPrice = price*vol;
    }

    public boolean isOrderCancel() {
        return orderCancel;
    }

    public String getCostumerId() {
        return costumerId;
    }

    public String getId() {
        return id;
    }

    public int getOrderedGoodId() {
        return orderedGoodId;
    }

    public int getOrderVol() {
        return orderVol;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void cancelOrder(Costumer costumer){
        if (orderCancel) {
            return;
        }

        orderCancel = true;
        costumer.balance += this.totalPrice;
        for (Good good : Market.storage) {
            if(good.id == this.orderedGoodId){
                good.vol += this.orderVol;
                good.sellVol -= this.orderVol;
                good.totalSellPrice -= this.totalPrice;
                good.profit -= this.totalPrice - this.orderVol*good.buyPrice;
                if(good.vol >= 0)
                    good.available = true;
            }
        }
        for (int i = Market.orders.size() - 1; i > -1; i--) {
            if(Market.orders.get(i).id.equals(this.id)) {
                Market.orders.remove(i);
                Market.saveAll();
                return;
            }
        }
    }
}
