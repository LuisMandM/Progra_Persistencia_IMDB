package com.ikasgela;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class Peliculas_Table_Model extends AbstractTableModel {
    private String[] columnas = {"Rank", "Title", "Genre", "Director", "Runtime (Minutes)", "Rating"};

    private List<Pelicula> peliculas;

    public Peliculas_Table_Model() {
        peliculas = new ArrayList<>();
    }

    public Peliculas_Table_Model(List<Pelicula> peliculaList) {
        this.peliculas = peliculaList;
    }

    @Override
    public int getRowCount() {
        return peliculas.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pelicula actual = peliculas.get(rowIndex);
        switch (columnIndex) {

            case 0:
                return actual.getRank();

            case 1:
                return actual.getTitle();

            case 2:
                return actual.getGenre();

            case 3:
                return actual.getDirector();

            case 4:
                return actual.getRuntime();

            case 5:
                return actual.getRating();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
