package com.company;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static String TableFile  = ".\\src\\Resourses\\MainTable.txt";

    public static void main(String[] args) throws Exception {
	    System.out.println("Приветствуем в AutoTable!");

        Car.init(TableFile); //инициализируем таблицу и считываем ее из файла

        boolean work = true ;
        while(work) {

            System.out.print("\nВыберите функцию:\n" +
                    "[0]. Вывести таблицу на экран\n" +
                    "[1]. Добавить новый автомобиль\n" +
                    "[2]. Редактировать таблицу\n" +
                    "[3]. Удалить автомобиль\n" +
                    "[9]. Завершить работу\n");

            Scanner in = new Scanner(System.in);
            String quest = in.next();
            if (!Car.isDigit(quest)){
                Car.err("Format");
                continue;
            }
            switch (parseInt(quest)) {    //запрашиваем функции
                case 0: {
                    System.out.println("--------->   [0]. Выводим таблицу на экран...");
                    Car.displayCars();
                    break;
                }
                case 1: {
                    System.out.println("--------->   [1]. Добавим новый автомобиль:");
                    Car.addCar();  //добавляем новый обьект машины
                    break;
                }
                case 2: {
                    System.out.println("--------->   [2]. Редактируем. Выберите порядковый номер машины:");
                    Car.displayCars();
                    System.out.print("Номер: ");
                    String ans = in.next();
                    if(Car.isDigit(ans)) {   //если строка = число, то пробуем
                        Car.configureCar(parseInt(ans));
                    }
                    else Car.err("Format");
                    break;
                }
                case 3: {
                    System.out.println("--------->   [3]. Удалить автомобиль. Выберите порядковый номер машины:");
                    Car.displayCars();
                    System.out.print("Номер: ");
                    String ans = in.next();
                    if(Car.isDigit(ans))
                        Car.deleteCar(parseInt(ans));
                    else Car.err("Format");
                    break;
                }
                case 9: {
                    System.out.println("Завершение работы ...");
                    CSVIm.SaveCSV(TableFile);
                    //закрыть и сохранить файлы
                    work = false;
                    break;
                }
                default:{
                    System.out.print("\nПопробуйте ещё раз.\n");
                }
            }
        }
    }
}