package ru.croc.winter;


public class Main {
    public static void main(String[] args) {


        Annotation[] annotations = {
                new Annotation( new Rectangle(100, 20, 420, 520), "Черный прямоугольник"),
                new Annotation(new Circle(50, 50, 300), "Желтый круг"),
                new Annotation(new Circle(-555, 555, 70), "Синий круг"),
                new Annotation( new Rectangle(-50, -50, 10, 10), "Красный прямоугольник")
        };

        System.out.println("-------------------------------------Размеченные фигуры---------------------------------------------------");
        for (int i = 0; i < annotations.length; i++) {
            System.out.println((i + 1) + ") " + annotations[i].toString());
        }

        System.out.println();

        System.out.println("---------------------------Ищем первую фигуру из массива, которой  принадлежит точка----------------------");
        int flag=0;

        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i].findPoint(-120, -50)!="Нет") {
                flag=1;
                System.out.println(annotations[i].findPoint(-120, -50));
                break;
            }
        }
        if (flag==0) {
            System.out.println("Отсутствует фигура, содержащая точку (-120, -50)");
        }

        System.out.println();
        System.out.println("----------------------Ищем первую фигуру из массива, которая содержит строку в подписи---------------------");
        int flag2=0;

        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i].findByLabel("Красный")!="No") {
                flag2=1;
                System.out.println(annotations[i].findByLabel("Красный"));
                break;
            }
        }
        if (flag2==0) {
            System.out.println("Отсутствует фигура с такой подписью");
        }

        System.out.println();
        System.out.println("-------------------------------------------Подвинем фигуру------------------------------------------------");
        annotations[2].moveFigure(30, -70);
        System.out.println("Передвинули фигуру на dx=30, dy=-70");
        System.out.println(annotations[2].toString());
    }
}