package pe.edu.tecsup.tienda.webs;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pe.edu.tecsup.tienda.services.CategoriaService;
import pe.edu.tecsup.tienda.services.ProductoService;

@Log
@Controller
public class ProductoController {

    CategoriaService categoriaService;

    ProductoService productoService;

    public ProductoController(CategoriaService categoriaService, ProductoService productoService) {
        this.categoriaService = categoriaService;
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public String index(Model model) {

        log.info("Call index()..!");

        return "productos/index";
    }
}
