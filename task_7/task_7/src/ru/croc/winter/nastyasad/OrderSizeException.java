package ru.croc.winter.nastyasad;

public class OrderSizeException extends Exception {
        private final int maxSize;
        public OrderSizeException(int maxSize) {
            this.maxSize = maxSize;
        }

    @Override
    public String getMessage() {
        return "Заказ собран, но срок хранения истёк" + maxSize;
    }
}
