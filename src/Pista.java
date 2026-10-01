public class Pista {
    private String codigo;
    private String descripcion;
    private String evidencia;
    private int importancia;
    private int confiabilidad;

    public Pista(String codigo, String descripcion, String evidencia, int importancia, int confiabilidad){
        if (codigo==null || codigo.isBlank()){
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        if (descripcion==null || descripcion.isBlank()){
            throw new IllegalArgumentException("La descripcion no puede estar vacía");
        }
        if (evidencia==null || evidencia.isBlank()){
            throw new IllegalArgumentException("La evidencia no puede estar vacía");
        }

        if (importancia<1 || importancia>10){
            throw new IllegalArgumentException("La importancia debe encontrarse entre 1 y 10");
        }

        if (confiabilidad<0 || confiabilidad>100){
            throw new IllegalArgumentException("La confiabilidad debe encontrarse entre 0 y 100");
        }

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.evidencia = evidencia;
        this.importancia = importancia;
        this.confiabilidad = confiabilidad;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getEvidencia(){
        return evidencia;
    }

    public int getImportancia(){
        return importancia;
    }

    public int getConfiabilidad(){
        return confiabilidad;
    }

    public void setCodigo(String newcodigo){
        if (newcodigo==null || newcodigo.isBlank()){
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        codigo = newcodigo;
    }

    public void setDescripcion(String newdescripcion){
        if (newdescripcion==null || newdescripcion.isBlank()){
            throw new IllegalArgumentException("La descripcion no puede estar vacía");
        }
        descripcion = newdescripcion;
    }

    public void setEvidencia(String newevidencia){
        if (newevidencia==null || newevidencia.isBlank()){
            throw new IllegalArgumentException("La evidencia no puede estar vacía");
        }
        evidencia = newevidencia;
    }

    public void setImportancia(int newimportancia){
        if (newimportancia<1 || newimportancia>10){
            throw new IllegalArgumentException("La importancia debe encontrarse entre 1 y 10");
        }
        importancia = newimportancia;
    }

    public void setConfiabilidad(int newconfiabilidad){
        if (newconfiabilidad<0 || newconfiabilidad>100){
            throw new IllegalArgumentException("La confiabilidad debe encontrarse entre 0 y 100");
        }
        confiabilidad = newconfiabilidad;
    }

    @Override
    public String toString() {
        return "Pista:\n" +
                "Codigo: " + codigo + "\n" +
                "Descripcion: " + descripcion + "\n" +
                "Evidencia: " + evidencia + "\n" +
                "Importancia: " + importancia + "\n" +
                "Confiabilidad: " + confiabilidad + "\n";
    }
}
