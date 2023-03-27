package com.ikasgela;

//import com.sun.tools.jdeprscan.CSV;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class V_Imdb {
    private JSpinner year_Spinner;
    private JScrollPane moviesTable_Scroll;

    private JTable movies_Table;
    private JPanel panel;
    private JLabel advet_Label;

    private static List<Integer> anios = new ArrayList<>();
    private static List<Pelicula> peliculas = new ArrayList<>();

    public V_Imdb(List<Integer> years, List<Pelicula> movies) {
        anios = years;
        peliculas = movies;
        SetAnios();

        if (!anios.contains(LocalDate.now().getYear())) advet_Label.setText("Sin registros");

        movies_Table = new JTable();
        movies_Table.setModel(new Peliculas_Table_Model());
        moviesTable_Scroll.setViewportView(movies_Table);

        year_Spinner.addChangeListener(e -> {
            int year_Selected = (int) year_Spinner.getModel().getValue();
            if (anios.contains(year_Selected)) {
                List<Pelicula> peliculas_Year = filtrado_Pelicula(year_Selected);

                if (peliculas_Year.size() > 0) {
                    advet_Label.setText("");
                    movies_Table.setModel(new Peliculas_Table_Model(peliculas_Year));
                }
            } else {
                advet_Label.setText("Sin registros");
                movies_Table.setModel(new Peliculas_Table_Model());
            }
        });

    }

    public void SetAnios() {
        SpinnerModel spinnerModel = new SpinnerNumberModel(LocalDate.now().getYear(), min_Year(),
                LocalDate.now().getYear(), 1);
        year_Spinner.setModel(spinnerModel);
    }

    public int min_Year() {
        int min_Year = Integer.MAX_VALUE;
        for (Integer anio : anios) {
            if (anio < min_Year) min_Year = anio;
        }
        return min_Year;
    }

    public List<Pelicula> filtrado_Pelicula(int year) {
        List<Pelicula> filtrado = new ArrayList<>();
        int rank = 1;
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getYear() == year) {
                Pelicula copia = pelicula;
                copia.setRank(String.valueOf(rank));
                filtrado.add(copia);
                rank++;
            }
        }

        LinkedList<Pelicula> top = new LinkedList<>(filtrado);
        for (Pelicula pelicula : filtrado) {
            if (pelicula != top.get(0) && Double.parseDouble(pelicula.getRating()) >= Double.parseDouble(top.get(0).getRating())) {
                Pelicula actual = pelicula;
                top.remove(pelicula);
                top.addFirst(actual);
            }
        }


        List<Pelicula> top_ordenado = ordenar(top);
        List<Pelicula> top_10 = new ArrayList<>();
        for (int i = top_ordenado.size() - 2; i > top.size() - 12; i--) {
            top_10.add(top_ordenado.get(i));
        }
        return top_10;
    }

    private List<Pelicula> ordenar(List<Pelicula> movies) {
        Pelicula[] copia = new Pelicula[movies.size()];
        movies.toArray(copia);

        for (int i = 0; i < movies.size(); i++) {
            for (int j = 0; j < movies.size() - i - 2; j++) {
                double rating_a = Double.parseDouble(copia[j].getRating());
                double rating_b = Double.parseDouble(copia[j + 1].getRating());
                if (rating_a > rating_b) {
                    Pelicula aux = copia[j];
                    copia[j] = copia[j + 1];
                    copia[j + 1] = aux;
                }
            }
        }
        return Arrays.asList(copia);
    }

    public JPanel getPanel() {
        return panel;
    }
}
