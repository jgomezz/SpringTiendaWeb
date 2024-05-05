package pe.edu.tecsup.tienda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.tienda.entities.Categoria;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    //@Query("SELECT c FROM Categoria c WHERE c.nombre = :name")
    //List<Categoria> findByNombre(@Param("name") String name);

    List<Categoria> findByNombre(String nombre) throws Exception;

}
