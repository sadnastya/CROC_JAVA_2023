package ru.croc.winter.nastyasad;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AuctionLot {
    protected BigDecimal currentPrice;
    protected String name;
    public final LocalDateTime endTime;
    public final String lotName;
    private LotStatus currentStatus;

    public AuctionLot(String lotName, BigDecimal price, LocalDateTime endTime) {
        this.lotName = lotName;
        this.currentPrice = price;
        this.name = null;
        this.endTime = endTime;
        this.currentStatus = LotStatus.ACTIVE;
    }
    public synchronized void makeBid(String userName, BigDecimal newPrice) throws LotSoldException {
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentStatus == LotStatus.ACTIVE && endTime.isAfter(currentTime)) {
            if (newPrice.compareTo(currentPrice) > 0) {
                this.name = userName;
                this.currentPrice = newPrice;
            }
        } else if (currentStatus == LotStatus.CLOSED) {
            throw new LotSoldException();
        } else if (endTime.isBefore(currentTime)) {
            currentStatus = LotStatus.CLOSED;
        }
    }
    public void closeLot() {
        this.currentStatus = LotStatus.CLOSED;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }
    public String getWinnerName() {
        if (currentStatus == LotStatus.CLOSED) {
            return name;
        } else {
            return "Лот еще не продан";
        }
    }
}
