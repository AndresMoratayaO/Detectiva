public class Ubicacion {
    private String codigo;
    private String nombre;
    private String direccion;
    private int riesgo;
    private String estado;

    public Ubicacion(String codigo, String nombre, String direccion, int riesgo, String estado){
        if (codigo==null || codigo.isBlank()){
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        if (nombre==null || nombre.isBlank()){
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        if (direccion==null || direccion.isBlank()){
            throw new IllegalArgumentException("La direccion no puede estar vacío");
        }
        if (estado==null || estado.isBlank()){
            throw new IllegalArgumentException("El estado no puede estar vacío");
        }
        if (riesgo<1 || riesgo>10){
            throw new IllegalArgumentException("El riesgo debe encontrarse entre 1 y 10");
        }

        this.codigo=codigo;
        this.nombre=nombre;
        this.direccion=direccion;
        this.riesgo=riesgo;
        this.estado=estado;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public int getRiesgo(){
        return riesgo;
    }

    public String getEstado(){
        return estado;
    }

    public void setRiesgo(int newriesgo){
        if (newriesgo<1 || newriesgo>10){
            throw new IllegalArgumentException("El riesgo debe encontrarse entre 1 y 10");
        }
        riesgo = newriesgo;
    }

    public void setEstado(String newestado){
        if (newestado == null || newestado.isBlank()){
            throw new IllegalArgumentException("El estado no puede estar vacío");
        }
        estado = newestado;
    }

    @Override
    public String toString() {
        return "Ubicacion:\n" +
                "Codigo: " + codigo + "\n" +
                "Nombre: " + nombre + "\n" +
                "Direccion: " + direccion + "\n" +
                "Riesgo: " + riesgo + "\n" +
                "Estado: " + estado + "\n";
    }
}
