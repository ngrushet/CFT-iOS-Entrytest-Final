package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Car{
    //создаём список словарей = список машин с характеристиками
    public static ArrayList<HashMap<String, String>> cars;

    //создаём экземпляр словаря
    public static HashMap<String, String> fields;

    //создаём список названий столбцов
    public static ArrayList<String> columns = new ArrayList<>();

    public static void err(String reason){
        if(reason.equals("Format"))
            System.out.print("Некорректный ввод. Попробуйте ещё раз.\n");
        if(reason.equals("OutRange"))
            System.out.print("Некорректный номер. Попробуйте ещё раз.\n");

    }

    public static void init(String Filepath) throws Exception { //заполняем список названиями
        cars = new ArrayList<>();
        fields = new HashMap<>();
        columns.add("number");
        columns.add("manuf");   //по идее нужно считывать из первой строки таблицы
        columns.add("model");   //но пока вводим те, что в ТЗ
        columns.add("year");
        columns.add("type");

        CSVIm.Import(Filepath);
    }

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void addCar(){      //добавляем машину=обьект=словарь в список  __работает как надда
        fields = new HashMap<>();
        Scanner in = new Scanner(System.in);
        String[] data = new String[0];

        System.out.print("\n");

        for(String s : columns){    //заполняем его по характеристикам
            if (s.equals("number")){
                fields.put(s, String.valueOf(cars.size())); //в поле номер вводим уникальный порядковый номер элемента в таблице
                continue;
            }
            System.out.printf("%s: ",s);
            fields.put(s,in.nextLine());

        }
        cars.add(fields);    //добавили словарь=машину в список
        displayLine(cars.get(0));
        displayLine(fields);
        fields = null;
    }

    public static void displayCars(){       //печать списка машин

        System.out.print("\n___________________________________________________________________________\n");
        for(HashMap c : cars) { //итерировать по списку словарей
            displayLine(c);
        }

    }

    public static void displayLine(HashMap c) {
        for( String i : columns) {
            if (i.equals("number"))
                System.out.printf("%2s", c.get(i));
            else
                System.out.printf(": %15s ", c.get(i));
        }
        System.out.println();
    }

    public static void configureCar(int i){     //редактирование информации //будем обращаться по номеру в таблице
        Scanner in = new Scanner(System.in);
        if(i>=cars.size()){
            err("OutRange");
            return;
        }
        displayLine(cars.get(i));
        System.out.print("Редактировать:\n" +
                        "     [0] - Производитель\n" +
                        "     [1] - Модель\n" +
                        "     [2] - Год выпуска\n" +
                        "     [3] - Тип кузова\n" +
                        "     [9] - Отмена\n"
                        );
        String quest = in.next();
        if (isDigit(quest) & (0<=parseInt(quest)) & (parseInt(quest)<4 )) {
            System.out.printf("%s:", columns.get(parseInt(quest) + 1));
            cars.get(i).put(columns.get(parseInt(quest) + 1), in.next()); //хочу обратиться к одному элементу словаря внутри списка
            displayLine(cars.get(i));
        }
        else if (parseInt(quest)==9) {
            System.out.print("Отмена.\n");
        }
        else err("Format");
    }

    public static void deleteCar(int i){
        Scanner in = new Scanner(System.in);
        if(i>=cars.size()){
            err("OutRange");
            return;
        }
        displayLine(cars.get(i));

        System.out.print("Удалить? ( [1] - удаление, [other] - отмена )\n");
        String quest = in.next();
        if(!isDigit(quest)){   //если не число, то ввод некорректен
            System.out.print("Отмена.\n");
        }
        else {
            if (parseInt(quest) == 1) {     //проверяем единичку

                cars.remove(i); //удаление из списка
                for (int j = i; j <= cars.size() - 1; j += 1) { //замена номеров
                    cars.get(j).put("number", String.valueOf(parseInt(cars.get(j).get("number")) - 1));
                }
                System.out.println("Удалено.\n");

            } else {
                System.out.println("Отмена.\n");   //если не единичка. то отмена
            }
        }
    }
}

