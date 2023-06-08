package pec;

public class Eventos {
    private final Double Tempo;
    private final String Tipo;
    private final Integer ID;

    Eventos(Double tempo, String type, Integer id) {
        this.Tempo = tempo;
        this.Tipo = type;
        if (id == 0) {
            this.ID = null;
        } else {
            this.ID = id;
        }
    }

    public Double getTempo() {
        return Tempo;
    }

    public String getTipo() {
        return Tipo;
    }

    public Integer getID() {
        return ID;
    }

}
