package pe.edu.tecsup.tienda.services;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import pe.edu.tecsup.tienda.entities.Producto;
import pe.edu.tecsup.tienda.repositories.ProductoRepository;

import java.util.List;

/**
 *
 */
@Log
@Service
public class ProductoServiceImpl implements ProductoService {

	ProductoRepository productoRepository;

	public ProductoServiceImpl(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Producto> findAll() throws Exception {
		log.info("call findAll()");
		return productoRepository.findAll();
	}

	/**
	 *
	 * @param nombre
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Producto> findByName(String nombre) 
			throws Exception {
		log.info("call findByName()");
		return productoRepository.findByNombre(nombre);
	}

	/**
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Producto findById(Long id) throws Exception {
		log.info("call findById()");
		return productoRepository.findById(id).get();
	}

	/**
	 *
	 * @param producto
	 * @throws Exception
	 */
	@Override
	public void save(Producto producto) throws Exception {
		log.info("call save()");
		productoRepository.save(producto);
	}

	/**
	 *
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void deleteById(Long id) throws Exception {
		log.info("call deleteById()");
		productoRepository.deleteById(id);
	}

	/**
	 *
	 * @param producto
	 * @throws Exception
	 */
	@Override
	public void update(Producto producto) throws Exception {
		log.info("call update()");
		productoRepository.save(producto);
	}

}
