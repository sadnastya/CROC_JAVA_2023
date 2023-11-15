package ru.croc.winter.nastyasad;

import java.util.*;
import java.util.function.Predicate;

public class Kitchen {
    protected ArrayList<Dish> dishes = new ArrayList<Dish>();
    protected ArrayList<Cook> cooks = new ArrayList<Cook>();
    protected HashMap<Cook, ArrayList<Dish>> cooksAndDishes = new HashMap<>();

    protected WhoWorks workers = (WeekDays day) -> {
        ArrayList<Cook> cooksAtWork = new ArrayList<Cook>();
        for(Cook cook: cooks){
            if(cook.monday && day==WeekDays.Monday){
                cooksAtWork.add(cook);
            } else if(cook.tuesday && day==WeekDays.Tuesday){
                cooksAtWork.add(cook);
            } else if (cook.wednesday && day==WeekDays.Wednesday) {
                cooksAtWork.add(cook);
            } else if (cook.thursday && day==WeekDays.Thursday) {
                cooksAtWork.add(cook);
            } else if (cook.friday && day==WeekDays.Friday) {
                cooksAtWork.add(cook);
            } else if (cook.saturday && day==WeekDays.Saturday) {
                cooksAtWork.add(cook);
            } else if (cook.sunday && day==WeekDays.Sunday) {
                cooksAtWork.add(cook);
            }
        }
        return cooksAtWork;};

    public Kitchen (){
        Dish[] dishesStartArray = {
                new Dish("Куриный бульон", "Первое", new String[]{"курица", "укроп"}, 10, 50),
                new Dish("Сырный суп", "Первое", new String[]{"плавленый сыр", "сливки", "курица", "картофель"}, 70, 40),
                new Dish("Паста Карбонара", "Второе", new String[]{"бекон", "сыр", "сливки", "спагетти"}, 90, 60),
                new Dish("Плов с курицей", "Второе", new String[]{"рис", "морковь", "курица", "чеснок"}, 55, 50),
                new Dish("Лазанья", "Второе", new String[]{"мясной фарш", "помидоры", "листы для лазаньи", "сливки", "морковь"}, 80, 90),
                new Dish("Тирамису", "Десерт", new String[]{"яйца", "какао", "мука", "сыр Маскарпоне"}, 30, 40),
                new Dish("Бананы в шоколаде", "Десерт", new String[]{"бананы", "шоколад"}, 55, 96)
        };

        Cook[] cooksStartArray = {
                new Cook("Повар1", true, true, false, false, false, true, true),
                new Cook("Повар2", false, true, true, false, false, false, true),
                new Cook("Повар3", true, true, true, true, false, false, true),
                new Cook("Повар4", true, false, false, false, false, true, true),
                new Cook("Повар5", true, false, true, false, true, false, true)
        };
        Collections.addAll(dishes, dishesStartArray);
        Collections.addAll(cooks, cooksStartArray);
        makeDefaultMap();
    }

    private void makeDefaultMap(){
        ArrayList<Dish> dishForCook0=new ArrayList<>();
        dishForCook0.add(dishes.get(0));
        dishForCook0.add(dishes.get(3));
        cooksAndDishes.put(cooks.get(0), dishForCook0);

        ArrayList<Dish> dishForCook1=new ArrayList<>();
        dishForCook1.add(dishes.get(5));
        dishForCook1.add(dishes.get(4));
        cooksAndDishes.put(cooks.get(1), dishForCook1);

        ArrayList<Dish> dishForCook2=new ArrayList<>();
        dishForCook2.add(dishes.get(1));
        dishForCook2.add(dishes.get(3));
        cooksAndDishes.put(cooks.get(2), dishForCook2);

        ArrayList<Dish> dishForCook3=new ArrayList<>();
        dishForCook3.add(dishes.get(0));
        dishForCook3.add(dishes.get(5));
        dishForCook3.add(dishes.get(2));
        cooksAndDishes.put(cooks.get(3), dishForCook3);

        ArrayList<Dish> dishForCook4=new ArrayList<>();
        dishForCook4.add(dishes.get(3));
        dishForCook4.add(dishes.get(2));
        dishForCook4.add(dishes.get(6));
        cooksAndDishes.put(cooks.get(4), dishForCook4);
    }

    public ArrayList<Dish> filterMenuWithPredicate (Predicate<Dish> predicate, WeekDays day, ArrayList<String> badProducts, int numberOfDishes)
    {


        List<Dish> list = makeMenu(day, badProducts, dishes.size()).stream()
                .filter( predicate )
                .toList();
        ArrayList<Dish> menu = new ArrayList<Dish>(list);

        ArrayList<Dish> newMenu = new ArrayList<>();
        for(int i=0; i<numberOfDishes;i++){
            try {
                newMenu.add(menu.get(i));
            } catch (Exception e){
                break;
            }
        }
        menu = newMenu;
        return menu;
    }

    public ArrayList<Dish> makeMenu(WeekDays day, ArrayList<String> badProducts, int numberOfDishes) {

        ArrayList<Cook> cooksAtWork = workers.whoWorksToday(day);
        ArrayList<Dish> menu = new ArrayList<Dish>();

        for (Cook ourCook : cooksAndDishes.keySet()) {
            if(cooksAtWork.contains(ourCook)){
                for(Dish dish: cooksAndDishes.get(ourCook)){
                    boolean flag=true;
                    for(String product: dish.ingredients){
                        if(badProducts.contains(product)) {
                            flag=false;
                            break;
                        }
                    }
                    if(flag && !menu.contains(dish)){
                        menu.add(dish);
                    }
                }
            }
        }
        Comparator<Dish> comparator = new GradeDishComparator();
        menu.sort(comparator.reversed());
        ArrayList<Dish> newMenu = new ArrayList<>();
        for(int i=0; i<numberOfDishes;i++){
            try {
                newMenu.add(menu.get(i));
            } catch (Exception e){
                break;
            }
        }
        return newMenu;
    }

    public void addNewCook(Cook newCook, ArrayList<Dish> dishesForNewCook){
        if(!dishesForNewCook.isEmpty()){
            cooksAndDishes.put(newCook, dishesForNewCook);
        }
        //добавить исключение для пустых блюд
    }

    public void deleteCook(Cook cook){
        try {
            cooksAndDishes.remove(cook);
        } catch (Exception e) {
            return ;
            //добавить ошибку, если удалять несуществующего повара
        }
    }
}
