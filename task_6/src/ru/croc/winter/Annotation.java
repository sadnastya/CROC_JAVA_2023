package ru.croc.winter;

public class Annotation
{
    protected Figure figure;
    protected String signature;

    Annotation(Figure figure, String signature){
        this.figure = figure;
        this.signature = signature;

    }

    @Override
    public String toString() {
        if(figure instanceof Rectangle) {
            return ((Rectangle) figure).figureName + " левый нижний угол: (" + ((Rectangle) figure).x1_leftLower +", " + ((Rectangle) figure).y1_leftLower +
                    "), правый верхний угол: (" + ((Rectangle) figure).x2_rightUpper +", " + ((Rectangle) figure).y2_rightUpper + "). Подпись: " + signature;
        }
        else if (figure instanceof Circle) {
            return ((Circle) figure).figureName + " центр: (" + ((Circle) figure).x0_center +", " + ((Circle) figure).y0_center + "), радиус: " + ((Circle) figure).radius +". Подпись: " + signature;
        }
        else {
            return "Нет такой фигуры";
        }
    }


    public String findPoint(int x, int y){
        if(figure instanceof Rectangle) {
            if ((x >= ((Rectangle) figure).x1_leftLower) && (x <= ((Rectangle) figure).x2_rightUpper)) {
                if ((y >= ((Rectangle) figure).y1_leftLower) && (y <= ((Rectangle) figure).y2_rightUpper)) {
                    return "Этот " + signature + " содержит искомую точку ("+x+", "+y+")";
                }
            }
        }
        if (figure instanceof Circle) {
                double distance = Math.sqrt(Math.pow(((Circle) figure).x0_center - x, 2)+Math.pow(((Circle) figure).y0_center, 2));
                if(distance<= ((Circle) figure).radius) {
                    return "Этот " + signature +" содержит искомую точку ("+x+", "+y+")";
                }
        }

        return "Нет";
    }

    public String findByLabel(String label) {
        if(signature.contains(label)) {
            return "Существует фигура с заданной строкой: " + label + ". Это - " + signature;
        }
        else {
            return "No";
        }

    }

    public void moveFigure(int dx, int dy){
        figure.move(dx, dy);
    }



}
