package com.ikasgela;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {


    private static final Set<Integer> anios_Set = new HashSet<>();
    private static final List<Pelicula> peliculas = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Reader in = new FileReader("IMDB-Movie-Data.csv");
            Iterable<CSVRecord> lecturas = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            for (CSVRecord lectura : lecturas) {
                int anio_record = Integer.parseInt(lectura.get("Year"));
                String rank_record = lectura.get("Rank");
                String title_record = lectura.get("Title");
                String genre_record = lectura.get("Genre");
                String director_record = lectura.get("Director");
                //int year_record = Integer.parseInt(lectura.get("Year"));
                String rating_record = lectura.get("Rating");
                String runtime_record = lectura.get("Runtime (Minutes)");

                Pelicula actual = new Pelicula(rank_record, title_record, genre_record, director_record,
                        anio_record, rating_record, runtime_record);

                anios_Set.add(anio_record);
                peliculas.add(actual);
            }

            try {
                // Set System L&F
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (UnsupportedLookAndFeelException e) {
                // handle exception
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            List<Integer> anios = new ArrayList<>(anios_Set);
            JFrame frame = new JFrame("TOP 10 IMDB/AÑO");
            frame.setContentPane(new V_Imdb(anios, peliculas).getPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int x = (screenSize.width - frame.getWidth()) / 2;
            int y = (screenSize.height - frame.getHeight()) / 2;
            frame.setLocation(x, y);

        } catch (IOException e) {
            System.err.println("Error al cargar datos: " + e.getLocalizedMessage());
        }

    }
}
