package ru.croc.winter.nastyasad;

import java.rmi.RemoteException;
import java.util.*;
import java.util.function.Predicate;

public class Kitchen {
    private HashMap<Cook, HashSet<Dish>> cooksAndDishes = new HashMap<>();

    public WhoWorks workers = (WeekDays day) -> {
        ArrayList<Cook> cooksAtWork = new ArrayList<Cook>();
        for(Cook cook: cooksAndDishes.keySet()){
            if(cook.workDays.contains(day)){
                cooksAtWork.add(cook);
            }
        }
        return cooksAtWork;};
    public ArrayList<Dish> createMenu(WeekDays day, ArrayList<String> badProducts, int size) {
        HashSet<Dish> menu = createMenu(day, badProducts);
        ArrayList<Dish> newArrayMenu = new ArrayList<>(menu);
        Comparator<Dish> comparator = new GradeDishComparator();
        newArrayMenu.sort(comparator.reversed());
        ArrayList<Dish> newMenu = new ArrayList<>();
        for(int i=0; i<size;i++){
            try {
                newMenu.add(newArrayMenu.get(i));
            } catch (Exception e){
                break;
            }
        }
        return newMenu;
    }
    private HashSet<Dish> createMenu(WeekDays day, ArrayList<String> badProducts)  {
        ArrayList<Cook> cooksAtWork = workers.whoWorksToday(day);
        HashSet<Dish> menu = new HashSet<>();

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
                    if(flag){
                        menu.add(dish);
                    }
                }
            }
        }
        return menu;
    }

    public HashSet<Dish> createMenu (Predicate<Dish> predicate, WeekDays day, ArrayList<String> badProducts, int size) {


        List<Dish> list = createMenu(day, badProducts, size).stream()
                .filter( predicate )
                .toList();
        ArrayList<Dish> menu = new ArrayList<Dish>(list);

        HashSet<Dish> newMenu = new HashSet<>();
        for(int i=0; i<size;i++){
            try {
                newMenu.add(menu.get(i));
            } catch (Exception e){
                break;
            }
        }
        return newMenu;
    }

    public void addNewCook(Cook newCook, HashSet<Dish> dishesForNewCook){
        if(!dishesForNewCook.isEmpty() ){
            cooksAndDishes.put(newCook, dishesForNewCook);
        }
    }

    public void deleteCook(Cook cook) throws RemoteException {
        try {
            cooksAndDishes.remove(cook);
        } catch (Exception e){
            throw new RemoteException("Не существует такого повара");
        }
    }

    public void setCooksAndDishes(Cook cook, HashSet<Dish> dishes) {
        if(!cooksAndDishes.containsKey(cook)){
            cooksAndDishes.put(cook, dishes);
        }
    }
}
