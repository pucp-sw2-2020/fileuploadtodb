package sw2.fileuploadtodb.entity;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String fotonombre;
    private String fotocontenttype;
    private byte[] foto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFotonombre() {
        return fotonombre;
    }

    public void setFotonombre(String fotonombre) {
        this.fotonombre = fotonombre;
    }

    public String getFotocontenttype() {
        return fotocontenttype;
    }

    public void setFotocontenttype(String fotocontenttype) {
        this.fotocontenttype = fotocontenttype;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
