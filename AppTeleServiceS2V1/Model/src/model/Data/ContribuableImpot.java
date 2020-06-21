package model.Data;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContribuableImpot {
    
    private int kimpot;
    private String abriviation;
    private Double taux;
    
    private String limpot;
    private String periodicite;
    private Date dateDebEffet;
    
    private String nif;
    
    private int KIMPOTCONTRIBUABLE;
    private int KCNC;


    public void setKimpot(int kimpot) {
        this.kimpot = kimpot;
    }

    public int getKimpot() {
        return kimpot;
    }

    public void setLimpot(String limpot) {
        this.limpot = limpot;
    }

    public String getLimpot() {
        return limpot;
    }

    public void setAbriviation(String abriviation) {
        this.abriviation = abriviation;
    }

    public String getAbriviation() {
        return abriviation;
    }

    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public void setTaux(Double taux) {
        this.taux = taux;
    }

    public Double getTaux() {
        return taux;
    }

    public void setDateDebEffet(Date dateDebEffet) {
        this.dateDebEffet = dateDebEffet;
    }

    public Date getDateDebEffet() {
        return dateDebEffet;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    public void setKIMPOTCONTRIBUABLE(int KIMPOTCONTRIBUABLE) {
        this.KIMPOTCONTRIBUABLE = KIMPOTCONTRIBUABLE;
    }

    public int getKIMPOTCONTRIBUABLE() {
        return KIMPOTCONTRIBUABLE;
    }

    public void setKCNC(int KCNC) {
        this.KCNC = KCNC;
    }

    public int getKCNC() {
        return KCNC;
    }

}
