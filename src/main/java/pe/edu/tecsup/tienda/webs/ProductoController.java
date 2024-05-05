package pe.edu.tecsup.tienda.webs;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.tecsup.tienda.entities.Categoria;
import pe.edu.tecsup.tienda.entities.Producto;
import pe.edu.tecsup.tienda.services.CategoriaService;
import pe.edu.tecsup.tienda.services.ProductoService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Log
@RequestMapping("/productos")
@Controller
public class ProductoController {

    @Value("${app.storage.path}")
    private String STORAGEPATH;

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

    @GetMapping("/create")
    public String create(Model model) throws Exception {
        log.info("call create()");

        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);

        Producto producto = new Producto();
        model.addAttribute("producto", producto);

        return "productos/create";
    }

    @PostMapping("/store")
    public String store(@ModelAttribute("producto") Producto producto, Errors errors,
                        @RequestParam("file") MultipartFile file,
                        RedirectAttributes redirectAttrs) throws Exception{
        log.info("call store(producto: " + producto + ")");

        if(file != null && !file.isEmpty()) {
            String filename = System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            producto.setImagen_nombre(filename);
            if(Files.notExists(Paths.get(STORAGEPATH))){
                Files.createDirectories(Paths.get(STORAGEPATH));
            }
            Files.copy(file.getInputStream(), Paths.get(STORAGEPATH).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        }

        producto.setCreado(new Date());
        producto.setEstado(1);

        productoService.save(producto);

        redirectAttrs.addFlashAttribute("message", "Registro guardado correctamente");

        return "redirect:/productos";
    }

    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> images(@PathVariable String filename) throws Exception{
        log.info("call images(filename: " + filename + ")");

        Path path = Paths.get(STORAGEPATH).resolve(filename);
        log.info("Path: " + path);

        if(!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new UrlResource(path.toUri());
        log.info("Resource: " + resource);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+resource.getFilename()+"\"")
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Paths.get(STORAGEPATH).resolve(filename)))
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
                .body(resource);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttrs) throws Exception {
        log.info("edit delete(id: " + id + ")");

        productoService.deleteById(id);

        redirectAttrs.addFlashAttribute("message", "Registro eliminado correctamente");

        return "redirect:/productos";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) throws Exception {
        log.info("edit edit(id: " + id + ")");

        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);

        Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);

        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("producto") Producto producto, Errors errors,
                         @RequestParam("file") MultipartFile file,
                         RedirectAttributes redirectAttrs) throws Exception{
        log.info("call update(producto: " + producto + ")");

        if(file != null && !file.isEmpty()) {
            String filename = System.currentTimeMillis() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            producto.setImagen_nombre(filename);
            if(Files.notExists(Paths.get(STORAGEPATH))){
                Files.createDirectories(Paths.get(STORAGEPATH));
            }
            Files.copy(file.getInputStream(), Paths.get(STORAGEPATH).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        }

        productoService.save(producto);

        redirectAttrs.addFlashAttribute("message", "Registro guardado correctamente");

        return "redirect:/productos";
    }
}
