package pe.edu.tecsup.tienda.services;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pe.edu.tecsup.tienda.entities.Categoria;
import pe.edu.tecsup.tienda.entities.Producto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
@Log
@SpringBootTest
public class ProductoServiceTest {

	@Autowired
	private ProductoService productoService;

	/**
	 *  Test busqueda de tddos los productos
	 * @throws Exception
	 */
	@Test
	public void testFindAll() throws Exception {

		boolean VALUE_EXPECTED = false;

		List<Producto> productos = productoService.findAll();

		productos.stream().forEach(item -> log.info(item.toString()));

		assertEquals(VALUE_EXPECTED, productos.isEmpty());
	
	}

	/**
	 *  Test busqueda de productos por nombre
	 * @throws Exception
	 */
	@Test
	public void testFindByName() throws Exception {

		boolean VALUE_EXPECTED = false;

		List<Producto> productos = productoService.findByName("AMD");

		log.info("Productos encontrados :");
		productos.stream().forEach(item -> log.info(item.toString()));

		assertEquals(VALUE_EXPECTED, productos.isEmpty());

	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void testSave() throws Exception {

		// Leer todos los producvtos
		List<Producto> productos = productoService.findAll();
		int totalAntes = productos.size();

		// Preparar el producto
		Producto producto = new Producto();
		producto.setNombre("NVIDIA` GPU");
		producto.setDescripcion("NVIDIA GPU 1080-TI");
		producto.setPrecio(2000.0);
		producto.setStock(6);
		producto.setEstado(1);

		// Asigno categoria al producto
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		producto.setCategoria(categoria);

		// Crea el producto
		productoService.save(producto);

		// Leer nuevamente todos los producvtos
		productos = productoService.findAll();
		int totalDespues = productos.size();

		assertEquals(1, totalDespues - totalAntes);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void testDeleteById() throws Exception {

		// Leer todos los producvtos
		List<Producto> productos = productoService.findAll();
		int totalAntes = productos.size();
		if (productos.isEmpty()) {
			return; // test pass
		}

		// Obtiene el ultimo producto de la lista
		Producto ultimoProducto = productos.get(productos.size() - 1);

		// Elimina el producto
		productoService.deleteById(ultimoProducto.getId());

		// Leer nuevamente todos los producvtos
		productos = productoService.findAll();
		int totalDespues = productos.size();

		assertEquals(1, totalAntes - totalDespues);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {

		// Actualizar el nombre del producto
		Long id = 1L; // Relacionado con tus datos de pruebas
		String NOMBRE_ORIGINAL = "Kingstone" ;
		String NOMBRE_A_CAMBIAR = "Kingstone Cambiado" ;
		Producto prod = null;

		// Actualizar nombre original
		prod = productoService.findById(id);
		prod.setNombre(NOMBRE_ORIGINAL);
		productoService.update(prod);

		// Verificar que el nombre ha sido cambiado
		prod = productoService.findById(id);
		assertEquals(NOMBRE_ORIGINAL, prod.getNombre());

		// Actualizar nombre a cambiar
		//prod = productoService.findById(id);
		prod.setNombre(NOMBRE_A_CAMBIAR);
		productoService.update(prod);

		// Verificar que el nombre ha sido cambiado
		prod = productoService.findById(id);
		assertEquals(NOMBRE_A_CAMBIAR, prod.getNombre());
	}

}
