package ru.croc.winter.nastyasad;

import java.math.BigDecimal;
import java.util.ArrayList;

public class User implements Runnable {
    public String name;
    public AuctionLot lot;

    public User(String userName, AuctionLot lot) {
        this.name = userName;
        this.lot = lot;
    }

    @Override
    public void run() {
        simulateBids();
    }

    private static BigDecimal generateRandomBid(BigDecimal currentPrice) {
        BigDecimal minBidIncrement = BigDecimal.valueOf(1_000); // Минимальное увеличение ставки
        BigDecimal maxBidIncrease = BigDecimal.valueOf(5_000); // Максимальное увеличение ставки
        BigDecimal randomIncrement = BigDecimal.valueOf(Math.random())
                .multiply(maxBidIncrease.subtract(minBidIncrement))
                .add(minBidIncrement); // Генерация случайного числа в диапазоне от минимального до максимального увеличения
        return currentPrice.add(randomIncrement);
    }

    private void simulateBids() {
        for (int i = 0; i < 100; i++) {
            try {
                lot.makeBid(name, generateRandomBid(lot.getCurrentPrice()));
            } catch (LotSoldException e) {
                break;
            }
        }
    }

    public void makeRealBid(BigDecimal price) throws LotSoldException {
        lot.makeBid(name, price);
    }
}
