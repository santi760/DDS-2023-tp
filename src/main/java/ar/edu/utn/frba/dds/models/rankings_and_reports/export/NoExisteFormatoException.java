package ar.edu.utn.frba.dds.models.rankings_and_reports.export;

public class NoExisteFormatoException extends RuntimeException {

    public NoExisteFormatoException() {
        super("No existe el formato que está queriendo instanciar");
    }
}
