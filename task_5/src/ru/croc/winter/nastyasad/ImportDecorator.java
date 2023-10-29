package ru.croc.winter.nastyasad;

import ru.croc.winter.nastyasad.fridge.Fridge;

public class ImportDecorator extends ProductDecorator{
    public ImportDecorator(Appliances appliances, String country, int guarantee){
        super(appliances);
        this.country=country;
        this.guarantee=guarantee;
    }

    @Override
    public String toString() {
        return super.toString() +  ". Импорт:  страна-производитель -  " + country + ", гарантия -  " + guarantee;
    }

}
