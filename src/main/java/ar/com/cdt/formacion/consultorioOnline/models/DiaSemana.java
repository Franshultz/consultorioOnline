package ar.com.cdt.formacion.consultorioOnline.models;

public class DiaSemana {

    private int idDia;
    private String diaSemana;


    public DiaSemana() {}

    public DiaSemana(int idDia, String diaSemana) {
        this.idDia = idDia;
        this.diaSemana = diaSemana;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }
}
