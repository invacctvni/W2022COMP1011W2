package com.example.w2022comp1011w2;

import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Formatter;

public class FakeSalesData {
    /**
     * This method will create a file with fake phone sales data in it.
     * an example of a valid insert statement is
     * INSERT INTO camerasales(cameraId, dateSold) VALUES (2,'2020-07-15');
     *  valid cameraId = 1-12
     *  valid dates - anyday up to today
     */
    public static void createSQL() {
        //read files
        //this is a random number generator
        SecureRandom rng = new SecureRandom();

        //open the formatter in the try.... with resources block so that it will auto-close
        try(

                Formatter formatter = new Formatter("cameraSales.sql");
                ) {
            //create the fake data and write it to the file
            for (int i=1;i<=500;i++) {
                    int cameraId = rng.nextInt(1,3);
//                    365*3 any day in the last 3 years. create random sales in the last 3 years.
                    LocalDate dateSold = LocalDate.now().minusDays(rng.nextInt(1095));
                    formatter.format("INSERT INTO camerasales (cameraId,dateSold) VALUES (%d,'%s');\n",cameraId,dateSold);
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createSQL();
    }

}
