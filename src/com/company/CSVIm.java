package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class CSVIm {
    public static void Import(String Filepath) throws Exception {
        try (FileInputStream fr = new FileInputStream(Filepath)) {
            Scanner scan = new Scanner(fr);
            String[] buf;

            while (scan.hasNextLine()) {        //если существует строка для ввода
                HashMap chars = new HashMap<>();
                String c = scan.nextLine();     //вводим её
                buf = c.split(";");       //разделяем по ";"
                int i = 0;

                for (String s : Car.columns) {    //заполняем машину по характеристикам
                    chars.put(s, buf[i]);  //все поля по порядку
                    if (s == "number") {         //поле номера - отдельно
                        chars.put(s, String.valueOf(Car.cars.size())); //вводим порядковый номер в таблице
                        continue;
                    }
                    i++;
                }
                Car.cars.add(Car.cars.size(), chars);
            }
            fr.close();
        }
    }

    public static void SaveCSV(String Filepath){
        try(FileWriter writer = new FileWriter(Filepath, false)) {//формат - CSV = разделители - ; и \n

            for(int i=0; i<Car.cars.size();i++) {      //для всех машин
                for (String s : Car.columns) {         //по всем столбцам
                    if (s == "number") continue;
                    else if (s == "type") {
                        writer.write(Car.cars.get(i).get(s)); //к строке прибавляем строковую запись поля машины i и колонной s
                    } else writer.write(Car.cars.get(i).get(s) + ";");

                }
                writer.append('\n');
            }
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
