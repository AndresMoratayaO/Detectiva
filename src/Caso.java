import java.util.ArrayList;

public class Caso {

    private String nombre;
    private String codigo;
    private String detective;
    private Ubicacion[] ubicaciones;
    private ArrayList<Pista> pistas;

    public Caso(String nombre, String codigo, String detective){
        if (nombre==null || nombre.isBlank()){
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (codigo==null || codigo.isBlank()){
            throw new IllegalArgumentException("El codigo no puede estar vacío");
        }
        if (detective==null || detective.isBlank()){
            throw new IllegalArgumentException("El detective no puede estar vacío");
        }

        this.nombre = nombre;
        this.codigo = codigo;
        this.detective = detective;
        this.ubicaciones = new Ubicacion[5];
        this.pistas = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getDetective(){
        return detective;
    }

    public void registrarUbicacion(int posicion, Ubicacion newubicacion){
        if (posicion<0 || posicion>4){
            throw new IllegalArgumentException(
                "La posición debe encontrarse entre 0 y 4"
            );
        }
        if (newubicacion==null || !(newubicacion instanceof Ubicacion)){
            throw new IllegalArgumentException("Ubicacion inválida");
        }
        if (ubicaciones[posicion]==null){
            ubicaciones[posicion] = newubicacion;
        }
        else{
            throw new Espacioinvalido("Este espacio se encuentra ocupado");
        }
    }

    public String consultarUbicacion(int posicion){
        if (posicion<0 || posicion>4){
            throw new IllegalArgumentException(
                "La posición debe encontrarse entre 0 y 4"
            );
        }
        if (ubicaciones[posicion] == null){
            throw new NullPointerException(
                "En esta posición no se encuentra ninguna ubicación"
            );
        }
        else{
            return ubicaciones[posicion].toString();
        }
    }

    public String consultarUbicaciones(){
        int c=0;
        String s="";

        for (int i=0; i<ubicaciones.length; i++){
            if (ubicaciones[i]!=null){
                s+="Posición: "+i+"\n";
                s+=ubicaciones[i].toString();
                s+="\n------------------------ \n";
                c+=1;
            }
        }

        return (c==0 ? "Sin ubicaciones registradas por el momento":s);
    }

    public void modificarUbicacionRiesgo(int posicion, int riesgo){
        if (posicion<0 || posicion>4){
            throw new IllegalArgumentException(
                "La posición debe encontrarse entre 0 y 4"
            );
        }
        if (riesgo<1 || riesgo>10){
            throw new IllegalArgumentException(
                "El riesgo debe encontrarse entre 1 y 10"
            );
        }
        if (ubicaciones[posicion]==null){
            throw new NullPointerException(
                "En esta posición no se encuentra ninguna ubicación"
            );
        }
        else{
            ubicaciones[posicion].setRiesgo(riesgo);
        }
    }

    public void modificarUbicacionEstado(int posicion, String estado){
        if (posicion<0 || posicion>4){
            throw new IllegalArgumentException(
                "La posición debe encontrarse entre 0 y 4"
            );
        }
        if (estado==null || estado.isBlank()){
            throw new IllegalArgumentException("El estado no puede estar vacío");
        }
        if (ubicaciones[posicion]==null){
            throw new NullPointerException(
                "En esta posición no se encuentra ninguna ubicación"
            );
        }
        else{
            ubicaciones[posicion].setEstado(estado);
        }
    }

    public void descartarUbicacion(int posicion){
        if (posicion<0 || posicion>4){
            throw new IllegalArgumentException(
                "La posición debe encontrarse entre 0 y 4"
            );
        }
        if (ubicaciones[posicion]==null){
            throw new NullPointerException(
                "En esta posición no se encuentra ninguna ubicación"
            );
        }
        else{
            ubicaciones[posicion] = null;
        }
    }

    private boolean codigoNoRepetido(String codigo){
        if (codigo == null || codigo.isBlank()){
            throw new IllegalArgumentException("El código no puede estar vacío");
        }

        for (Pista pista:pistas){
            if (codigo.equals(pista.getCodigo())){
                return false;
            }
        }

        return true;
    }

    public void registrarPista(Pista pista){
        if (pista==null || !(pista instanceof Pista)){
            throw new IllegalArgumentException("Pista inválida");
        }
        if (codigoNoRepetido(pista.getCodigo())){
            pistas.add(pista);
        }
        else{
            throw new IllegalArgumentException("El código ya existe");
        }
    }

    public String consultarPista(String codigo){
        if (codigo == null || codigo.isBlank()){
            throw new IllegalArgumentException("El código no puede estar vacío");
        }

        for (Pista pista:pistas){
            if (codigo.equals(pista.getCodigo())){
                return pista.toString();
            }
        }

        return "No se encontró el código";
    }

    public String consultarPistas(){
        String s="";
        int c=0;

        for (Pista pista:pistas){
            s+=pista.toString();
            s+="\n------------------------ \n";
            c+=1;
        }

        return (c==0 ? "Sin pistas registradas por el momento":s);
    }

    public void modificarPistacodigo(String codigoabuscar, String newcodigo){
        if (codigoabuscar == null || codigoabuscar.isBlank()){
            throw new IllegalArgumentException(
                "El código a buscar no puede estar vacío"
            );
        }
        if (newcodigo == null || newcodigo.isBlank()){
            throw new IllegalArgumentException(
                "El nuevo código no puede estar vacío"
            );
        }

        if (!codigoNoRepetido(codigoabuscar)){
            if (codigoabuscar.equals(newcodigo) || codigoNoRepetido(newcodigo)){
                for (Pista pista:pistas){
                    if (codigoabuscar.equals(pista.getCodigo())){
                        pista.setCodigo(newcodigo);
                        return;
                    }
                }
            }
            else{
                throw new IllegalArgumentException("El nuevo código ya existe");
            }
        }

        throw new Noencontrado("No se encontró el código");
    }

    public void modificarPistaDescripcion(
        String codigoabuscar, String newdescripcion
    ){
        if (codigoabuscar == null || codigoabuscar.isBlank()){
            throw new IllegalArgumentException(
                "El código a buscar no puede estar vacío"
            );
        }
        if (newdescripcion == null || newdescripcion.isBlank()){
            throw new IllegalArgumentException(
                "La nueva descripción no puede estar vacía"
            );
        }

        if (!codigoNoRepetido(codigoabuscar)){
            for (Pista pista:pistas){
                if (codigoabuscar.equals(pista.getCodigo())){
                    pista.setDescripcion(newdescripcion);
                    return;
                }
            }
        }

        throw new Noencontrado("No se encontró el código");
    }

    public void modificarPistaEvidencia(
        String codigoabuscar, String newevidencia
    ){
        if (codigoabuscar == null || codigoabuscar.isBlank()){
            throw new IllegalArgumentException(
                "El código a buscar no puede estar vacío"
            );
        }
        if (newevidencia == null || newevidencia.isBlank()){
            throw new IllegalArgumentException(
                "La nueva evidencia no puede estar vacía"
            );
        }

        if (!codigoNoRepetido(codigoabuscar)){
            for (Pista pista:pistas){
                if (codigoabuscar.equals(pista.getCodigo())){
                    pista.setEvidencia(newevidencia);
                    return;
                }
            }
        }

        throw new Noencontrado("No se encontró el código");
    }

    public void modificarPistaImportancia(
        String codigoabuscar, int newimportancia
    ){
        if (codigoabuscar == null || codigoabuscar.isBlank()){
            throw new IllegalArgumentException(
                "El código a buscar no puede estar vacío"
            );
        }
        if (newimportancia < 1 || newimportancia > 10){
            throw new IllegalArgumentException(
                "La importancia debe encontrarse entre 1 y 10"
            );
        }

        if (!codigoNoRepetido(codigoabuscar)){
            for (Pista pista:pistas){
                if (codigoabuscar.equals(pista.getCodigo())){
                    pista.setImportancia(newimportancia);
                    return;
                }
            }
        }

        throw new Noencontrado("No se encontró el código");
    }

    public void modificarPistaConfiabilidad(
        String codigoabuscar, int newconfiabilidad
    ){
        if (codigoabuscar == null || codigoabuscar.isBlank()){
            throw new IllegalArgumentException(
                "El código a buscar no puede estar vacío"
            );
        }
        if (newconfiabilidad < 0 || newconfiabilidad > 100){
            throw new IllegalArgumentException(
                "La confiabilidad debe encontrarse entre 0 y 100"
            );
        }

        if (!codigoNoRepetido(codigoabuscar)){
            for (Pista pista:pistas){
                if (codigoabuscar.equals(pista.getCodigo())){
                    pista.setConfiabilidad(newconfiabilidad);
                    return;
                }
            }
        }

        throw new Noencontrado("No se encontró el código");
    }

    public void eliminarPista(String codigoabuscar){
        if (codigoabuscar==null || codigoabuscar.isBlank()){
            throw new IllegalArgumentException(
                "El código a buscar no puede estar vacío"
            );
        }

        if (!codigoNoRepetido(codigoabuscar)){
            for (Pista pista:pistas){
                if (codigoabuscar.equals(pista.getCodigo())){
                    pistas.remove(pista);
                    return;
                }
            }
        }

        throw new Noencontrado("No se encontró el código");
    }

    public int contarUbicaciones(){
        int c=0;

        for (Ubicacion ubicacion:ubicaciones){
            if (ubicacion!=null){
                c+=1;
            }
        }

        return c;
    }

    public int ubicacionesDisponibles(){
        return ubicaciones.length - contarUbicaciones();
    }

    public String obtenerUbicacionMayorRiesgo(){
        Ubicacion mayor = null;

        for (int i=0; i<ubicaciones.length; i++){
            if (ubicaciones[i]!=null){
                if (mayor==null || mayor.getRiesgo()<ubicaciones[i].getRiesgo()){
                    mayor = ubicaciones[i];
                }
            }
        }

        if (mayor==null){
            throw new NullPointerException(
                "No se encuentran ubicaciones registradas por el momento"
            );
        }

        return mayor.toString();
    }

    public int contarPistas(){
        return pistas.size();
    }

    public String obtenerPistaMayorImportancia(){
        if (pistas.size()==0){
            throw new NullPointerException(
                "No se encuentran pistas registradas por el momento"
            );
        }

        Pista mayor = pistas.get(0);

        for (int i=1; i<pistas.size(); i++){
            if (mayor.getImportancia()<pistas.get(i).getImportancia()){
                mayor = pistas.get(i);
            }
        }

        return mayor.toString();
    }

    public String obtenerPistaMayorConfiabilidad(){
        if (pistas.size()==0){
            throw new NullPointerException(
                "No se encuentran pistas registradas por el momento"
            );
        }

        Pista mayor = pistas.get(0);

        for (int i=1; i<pistas.size(); i++){
            if (mayor.getConfiabilidad()<pistas.get(i).getConfiabilidad()){
                mayor = pistas.get(i);
            }
        }

        return mayor.toString();
    }

    private double calcularPromedioImportancia(ArrayList<Pista> pistas){
        if (pistas.size()==0){
            return 0;
        }
        else{
            double suma=0;

            for (Pista pista:pistas){
                suma+=pista.getImportancia();
            }

            return suma/pistas.size();
        }
    }

    public String generarReporte(){
        String s="Cantidad de ubicaciones registradas: "
            +contarUbicaciones()+"\n";

        s+="Espacios disponibles: "+ubicacionesDisponibles()+"\n";

        if (contarUbicaciones()==0){
            s+="Sin ubicaciones registradas por el momento\n";
        }
        else{
            s+="Ubicación con mayor riesgo:\n"
                +obtenerUbicacionMayorRiesgo()+"\n";
        }

        s+="Cantidad de pistas registradas: "+contarPistas()+"\n";

        if (pistas.size()==0){
            s+="Sin pistas\n";
        }
        else{
            s+="Pista con mayor importancia:\n"
                +obtenerPistaMayorImportancia()+"\n";

            s+="Pista con mayor confiabilidad:\n"
                +obtenerPistaMayorConfiabilidad()+"\n";

            s+="El promedio general es "+calcularPromedioImportancia(pistas);
        }

        return s;
    }
}