package pe.edu.tecsup.tienda.webs;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.tecsup.tienda.entities.Producto;
import pe.edu.tecsup.tienda.services.CategoriaService;
import pe.edu.tecsup.tienda.services.ProductoService;

import java.util.List;

@Log
@RequestMapping("/productos")
@Controller
public class ProductoController {

    CategoriaService categoriaService;

    ProductoService productoService;

    public ProductoController(CategoriaService categoriaService, ProductoService productoService) {
        this.categoriaService = categoriaService;
        this.productoService = productoService;
    }

    @GetMapping()
    public String index(Model model) throws Exception {

        log.info("Call index()..!");

        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);

        return "productos/index";
    }
}
