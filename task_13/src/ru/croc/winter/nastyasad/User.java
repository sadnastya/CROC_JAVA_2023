package ru.croc.winter.nastyasad;

import java.math.BigDecimal;
import java.util.ArrayList;

public class User implements Runnable{
    public String name;
    public AuctionLot lot;
    User(String userName, AuctionLot lot){
        this.name=userName;
        this.lot=lot;
    }

    @Override
    public void run() {
        simulateBids();
    }

    private void simulateBids(){
        for(int i=0; i<100; i++) {
            try {
                lot.makeBid(name, BigDecimal.valueOf(Math.random() * (10_000_000 - lot.currentPrice.intValue() + 1) + 10_000_000));
            } catch (LotSoldException e) {
                e.getMessage();
            }
        }


    }

    public void makeRealBid(BigDecimal price){
        try {
            lot.makeBid(name, price);
        } catch (LotSoldException e) {
            e.getMessage();

        }
    }
}
