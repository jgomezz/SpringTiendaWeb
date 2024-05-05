package pe.edu.tecsup.tienda.services;

import org.springframework.stereotype.Service;
import pe.edu.tecsup.tienda.entities.Categoria;
import pe.edu.tecsup.tienda.repositories.CategoriaRepository;

import java.util.List;

/**
 *
 */
@Service
public class CategoriaServiceImpl implements CategoriaService{

    CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Categoria> findAll() throws Exception {
        return this.categoriaRepository .findAll();
    }

    @Override
    public List<Categoria> findByName(String name) throws Exception {
        return this.categoriaRepository.findByNombre(name);
    }
}
