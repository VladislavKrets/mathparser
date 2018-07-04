package ru.parser.math;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lollipop on 03.07.2017.
 */
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Math math = new Math();
        System.out.println(math.calculate(reader.readLine()));


    }

}
