package apps.unab.directorio_unab;

/**
 * Created by rene on 14/03/15.
 */
public class DatosDocentes {



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    int id;

    public DatosDocentes(int id, String nombre, String cargo, int telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.cargo = cargo;
        this.telefono = telefono;

    }

    public DatosDocentes(){}

    String nombre;
    int telefono;
    String cargo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    String correo;
}
