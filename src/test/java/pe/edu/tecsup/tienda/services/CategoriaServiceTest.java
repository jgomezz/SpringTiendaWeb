package pe.edu.tecsup.tienda.services;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.tecsup.tienda.entities.Categoria;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Log
@SpringBootTest
class CategoriaServiceTest {

    @Autowired
    CategoriaService categoriaService;

    @Test
    void testfindAll() throws Exception {

        boolean VALUE_EXPECTED = false;

        List<Categoria> categorias = categoriaService.findAll();

        log.info("cats.toString() " + categorias.toString());

        log.info("Print by foreach");
        for(Categoria cat : categorias) log.info(cat.toString());

        log.info("Print by stream");
        categorias.stream().forEach(item -> log.info(item.toString()));

        assertEquals(VALUE_EXPECTED, categorias.isEmpty());

    }

    @Test
    void testFindByName() throws Exception {

        log.info("testFindByName()");

        boolean VALUE_EXPECTED = false;
        List<Categoria> categorias = categoriaService.findByName("Procesadores");

        categorias.stream().forEach(item -> log.info(item.toString()));

        assertEquals(VALUE_EXPECTED, categorias.isEmpty());

    }


}