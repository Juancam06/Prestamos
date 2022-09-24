package com.Biblioteca.Prestamos.Servicios;

import com.Biblioteca.Prestamos.Entidades.Prestamo;
import com.Biblioteca.Prestamos.Respositorio.estudianteRepositorio;
import com.Biblioteca.Prestamos.Respositorio.libroRepositorio;
import com.Biblioteca.Prestamos.Respositorio.prestamoRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class prestamoServicio {

    private prestamoRepositorio pRepo;
    private libroRepositorio libRepo;
    private estudianteRepositorio estuRepo;

    public prestamoServicio(prestamoRepositorio pRepo, libroRepositorio libRepo, estudianteRepositorio estuRepo) {
        this.pRepo = pRepo;
        this.libRepo = libRepo;
        this.estuRepo = estuRepo;
    }

    public Prestamo agregarPrestamo(String isbn, String doc, Prestamo prestamo) {

        libRepo.findById(isbn).map(libro -> {
            prestamo.setLibro(libro);
            return libro;
        });
         return estuRepo.findById(doc).map(est -> {

            prestamo.setEstudiante(est);
            return pRepo.save(prestamo);

        }).get();

    }

    public ArrayList<Prestamo>porEstudiante(String doc){
        try {
            return estuRepo.findById(doc).map(est -> {
                return pRepo.findByEstudiante(est);
            }).get();

        }catch (Exception ex){
            System.out.println("Error: "+ex);
        }
        return null;

    }
}