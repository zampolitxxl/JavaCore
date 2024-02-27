package Lesson2;


import java.util.Random;
import java.util.Scanner;

public class Program {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;

    private static final int WIN_COUNT = 4; // Выигрышная комбинация

    /**
     * Инициализация объектов игры
     */
    static void initialize(){
        fieldSizeX = 6;
        fieldSizeY = 6;
        field = new char[fieldSizeX][fieldSizeY];

        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                field[x][y] = DOT_EMPTY;
            }
        }
    }

    /**
     * Печать текущего состояния игрового поля
     */
    static void printField(){
        System.out.print("+");
        for (int i = 0; i < fieldSizeX; i++){
            System.out.print("-" + (i + 1));
        }
        System.out.println("-");

        for (int x = 0; x < fieldSizeX; x++){
            System.out.print(x + 1 + "|");
            for (int y = 0; y < fieldSizeY; y++){
                System.out.print(field[x][y] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < fieldSizeX * 2 + 2; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Ход игрока (человека)
     */
    static void humanTurn(){
        int x;
        int y;
        do {
            System.out.print("Введите координаты хода X и Y\n(от 1 до 3) через пробел: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        }
        while (!isCellValid(x, y) || !isCellEmpty(x, y));
        field[x][y] = DOT_HUMAN;
    }

    /**
     * Ход игрока (компьютера)
     */
    static void aiTurn(){
        int x;
        int y;
        do{
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }
        while (!isCellEmpty(x, y));
        field[x][y] = DOT_AI;
    }

    /**
     * Проверка, является ли ячейка игрового поля пустой
     * @param x координата
     * @param y координата
     * @return результат проверки
     */
    static boolean isCellEmpty(int x, int y){
        return field[x][y] == DOT_EMPTY;
    }

    /**
     * Проверка валидности координат хода
     * @param x координата
     * @param y координата
     * @return результат проверки
     */
    static boolean isCellValid(int x, int y){
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    /**
     * Поверка на ничью (все ячейки игрового поля заполнены фишками человека или компьютера)
     * @return
     */
    static boolean checkDraw(){
        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                if (isCellEmpty(x, y)) return false;
            }
        }
        return true;
    }

    /**
     * TODO: Переработать в рамках домашней работы
     * Метод проверки победы
     * @param dot фишка игрока
     * @return результат проверки победы
     */
    static boolean checkWin(char dot){
        // Проверка по трем горизонталям
        if (field[0][0] == dot && field[0][1] == dot && field[0][2] == dot) return true;
        if (field[1][0] == dot && field[1][1] == dot && field[1][2] == dot) return true;
        if (field[2][0] == dot && field[2][1] == dot && field[2][2] == dot) return true;

        // Проверка по трем вертикалям
        if (field[0][0] == dot && field[1][0] == dot && field[2][0] == dot) return true;
        if (field[0][1] == dot && field[1][1] == dot && field[2][1] == dot) return true;
        if (field[0][2] == dot && field[1][2] == dot && field[2][2] == dot) return true;

        // Проверка по двум диагоналям
        if (field[0][0] == dot && field[1][1] == dot && field[2][2] == dot) return true;
        if (field[0][2] == dot && field[1][1] == dot && field[2][0] == dot) return true;

        return false;
    }

    static boolean checkWinV2(char dot, int win){
        for (int x = 0; x < fieldSizeX; x++){
            for (int y = 0; y < fieldSizeY; y++){
                if (check1(x,y,dot,win) == true){
                    return  true;
                }
                if (check2(x,y,dot,win) == true){
                    return  true;
                }
                if (check3(x,y,dot,win) == true){
                    return  true;
                }
                if (check4(x,y,dot,win) == true){
                    return  true;
                }

            }
        }
        return false;
    }

    static boolean check1(int x, int y, char dot, int win){
        int result = 1;

        try {
            for (int i = 0; i <= win - 2; i++) {
                if (field[x][y] == dot && field[x][y + i + 1] == dot && field[x][y] ==  field[x][y + i + 1]) {
                    result = result + 1;
                }

                //System.out.println("X= "+ x +"Y= "+ y+"check 1 result = "+ result + "win = "+ win+ "dot = "+ dot + "i =" + i);

            }
            if (result == win) {
                //System.out.println("условие равенства сработало");
                //win =4, 1. i=0, xy=xy+1, i=1, xy+1=xy+2, i=2, xy+2=xy+3/ O X X X X O O
                return true;
            }
        }
        catch (IndexOutOfBoundsException a){
            return  false;
        }
        /*if (result == win) {
            System.out.println("условие равенства сработало");
            //win =4, 1. i=0, xy=xy+1, i=1, xy+1=xy+2, i=2, xy+2=xy+3/ O X X X X O O
            return true;
        }*/
        return false;
    }

    static boolean check2(int x, int y, char dot, int win){
        try {
            int result = 1;
            for (int i = 0; i <= win - 2; i++) {
                if (field[x + i][y] == dot && field[x + i + 1][y] == dot) {
                    result = result + 1;
                }
            }
            if (result == win) {
                //win =4, 1. i=0, xy=xy+1, i=1, xy+1=xy+2, i=2, xy+2=xy+3/ O X X X X O O
                return true;
            }
        }
        catch (IndexOutOfBoundsException a){
            return  false;
        }
        return false;
    }

    static boolean check3(int x, int y, char dot, int win){
        try {
            int result = 1;
            for (int i = 0; i <= win - 2; i++) {
                if (field[x + i][y + i] == dot && field[x + i + 1][y + i + 1] == dot) {
                    result = result + 1;
                }
            }
            if (result == win) {
                //win =4, 1. i=0, xy=xy+1, i=1, xy+1=xy+2, i=2, xy+2=xy+3/ O X X X X O O
                return true;
            }
        }
        catch (IndexOutOfBoundsException a){
            return  false;
        }
        return false;
    }

    //диагаональ право вверх
    static boolean check4(int x, int y, char dot, int win){
        try {
            int result = 1;
            for (int i = 0; i <= win - 2; i++) {
                if (field[x - i][y + i] == dot && field[x - i - 1][y + i + 1] == dot) {
                    result = result + 1;
                }
            }

            if (result == win) {
                //win =4, 1. i=0, xy=xy+1, i=1, xy+1=xy+2, i=2, xy+2=xy+3/ O X X X X O O
                return true;
            }
        }
        catch (IndexOutOfBoundsException a){
            return  false;
        }
        return false;
    }


    /**
     * Проверка состояния игры
     * @param dot фишка игрока
     * @param s победный слоган
     * @return состояние игры
     */
    static boolean checkState(char dot, String s, int win){
        if (checkWinV2(dot, win)){
            System.out.println(s);
            return true;
        }
        else if (checkDraw()){
            System.out.println("Ничья!");
            return true;
        }
        // Игра продолжается
        return false;
    }

    public static void main(String[] args) {
        int win = 4;
        while (true) {
            initialize();
            printField();
            while (true) {
                humanTurn();
                printField();
                if (checkState(DOT_HUMAN, "Вы победили!", win))
                    break;
                aiTurn();
                printField();
                if (checkState(DOT_AI, "Вы победили!", win))
                    break;
            }
            System.out.print("Желаете сыграть еще раз? (Y - да): ");
            if(!scanner.next().equalsIgnoreCase("Y"))
                break;
        }
    }

}
