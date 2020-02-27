package sw2.fileuploadtodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sw2.fileuploadtodb.entity.Producto;
import sw2.fileuploadtodb.repository.ProductoRepository;

import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductoController {

    @Autowired
    ProductoRepository productoRepository;

    @GetMapping({"/", "lista"})
    public String listaEmpleados(Model model) {
        model.addAttribute("listaProductos", productoRepository.findAll());
        return "lista";
    }

    @GetMapping("/nuevo")
    public String nuevoEmpleado() {
        return "nuevoForm";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@RequestParam("archivo") MultipartFile file,
                                  Producto producto, Model model) {

        if (file.isEmpty()) {
            model.addAttribute("msg", "Debe subir un archivo");
            return "nuevoForm";
        }

        String fileName = file.getOriginalFilename();

        if (fileName.contains("..")) {
            model.addAttribute("msg", "No se permiten '..' en el archivo");
            return "nuevoForm";
        }

        try {
            producto.setFoto(file.getBytes());
            producto.setFotonombre(fileName);
            producto.setFotocontenttype(file.getContentType());
            productoRepository.save(producto);
            return "redirect:/";

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("msg", "ocurrió un error al subir el archivo");
            return "nuevoForm";
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> mostrarImagen(@PathVariable("id") int id) {
        Optional<Producto> opt = productoRepository.findById(id);
        if (opt.isPresent()) {
            Producto p = opt.get();

            byte[] imagenComoBytes = p.getFoto();

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(
                    MediaType.parseMediaType(p.getFotocontenttype()));

            return new ResponseEntity<>(
                    imagenComoBytes,
                    httpHeaders,
                    HttpStatus.OK);
        } else {
            return null;
        }
    }

}
