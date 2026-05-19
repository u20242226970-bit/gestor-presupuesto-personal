package com.universidad.parcial_practica;

import com.universidad.parcial_practica.model.Categoria;
import com.universidad.parcial_practica.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0) {
            String[][] categorias = {
                {"Alimentacion", "Comida, restaurantes, mercado"},
                {"Transporte", "Bus, taxi, gasolina"},
                {"Salud", "Medico, medicamentos, gym"},
                {"Educacion", "Cursos, libros, universidad"},
                {"Entretenimiento", "Cine, juegos, streaming"},
                {"Ropa", "Ropa, zapatos, accesorios"},
                {"Servicios", "Agua, luz, internet, telefono"},
                {"Otros", "Gastos varios"}
            };

            for (String[] c : categorias) {
                Categoria cat = new Categoria();
                cat.setNombre(c[0]);
                cat.setDescripcion(c[1]);
                categoriaRepository.save(cat);
            }
            System.out.println("Categorias iniciales creadas!");
        }
    }
}