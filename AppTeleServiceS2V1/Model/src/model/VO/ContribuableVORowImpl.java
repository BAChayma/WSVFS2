package model.VO;

import java.math.BigDecimal;

import java.sql.Timestamp;

import oracle.jbo.RowIterator;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Jun 16 11:20:03 CEST 2020
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class ContribuableVORowImpl extends ViewRowImpl {

    public static final int ENTITY_CONTRIBUABLEEO = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        Kcnc,
        Kformjuri,
        Nif,
        Nomcommerciale,
        Raisonsociale,
        Datedebexp,
        Capitalsociale,
        Registrecommerce,
        AdresseVO,
        ComptebancaireVO,
        ActiviteentrepriseVO,
        DeclarationVO,
        PaysVO,
        PersonneVO,
        ReclamationVO,
        ContribuableuserVO,
        ImpotcontribuableVO,
        AdresseVO1;
        static AttributesEnum[] vals = null;
        ;
        private static final int firstIndex = 0;

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }

    public static final int KCNC = AttributesEnum.Kcnc.index();
    public static final int KFORMJURI = AttributesEnum.Kformjuri.index();
    public static final int NIF = AttributesEnum.Nif.index();
    public static final int NOMCOMMERCIALE = AttributesEnum.Nomcommerciale.index();
    public static final int RAISONSOCIALE = AttributesEnum.Raisonsociale.index();
    public static final int DATEDEBEXP = AttributesEnum.Datedebexp.index();
    public static final int CAPITALSOCIALE = AttributesEnum.Capitalsociale.index();
    public static final int REGISTRECOMMERCE = AttributesEnum.Registrecommerce.index();
    public static final int ADRESSEVO = AttributesEnum.AdresseVO.index();
    public static final int COMPTEBANCAIREVO = AttributesEnum.ComptebancaireVO.index();
    public static final int ACTIVITEENTREPRISEVO = AttributesEnum.ActiviteentrepriseVO.index();
    public static final int DECLARATIONVO = AttributesEnum.DeclarationVO.index();
    public static final int PAYSVO = AttributesEnum.PaysVO.index();
    public static final int PERSONNEVO = AttributesEnum.PersonneVO.index();
    public static final int RECLAMATIONVO = AttributesEnum.ReclamationVO.index();
    public static final int CONTRIBUABLEUSERVO = AttributesEnum.ContribuableuserVO.index();
    public static final int IMPOTCONTRIBUABLEVO = AttributesEnum.ImpotcontribuableVO.index();
    public static final int ADRESSEVO1 = AttributesEnum.AdresseVO1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public ContribuableVORowImpl() {
    }

    /**
     * Gets ContribuableEO entity object.
     * @return the ContribuableEO
     */
    public EntityImpl getContribuableEO() {
        return (EntityImpl) getEntity(ENTITY_CONTRIBUABLEEO);
    }

    /**
     * Gets the attribute value for KCNC using the alias name Kcnc.
     * @return the KCNC
     */
    public BigDecimal getKcnc() {
        return (BigDecimal) getAttributeInternal(KCNC);
    }

    /**
     * Sets <code>value</code> as attribute value for KCNC using the alias name Kcnc.
     * @param value value to set the KCNC
     */
    public void setKcnc(BigDecimal value) {
        setAttributeInternal(KCNC, value);
    }

    /**
     * Gets the attribute value for KFORMJURI using the alias name Kformjuri.
     * @return the KFORMJURI
     */
    public BigDecimal getKformjuri() {
        return (BigDecimal) getAttributeInternal(KFORMJURI);
    }

    /**
     * Sets <code>value</code> as attribute value for KFORMJURI using the alias name Kformjuri.
     * @param value value to set the KFORMJURI
     */
    public void setKformjuri(BigDecimal value) {
        setAttributeInternal(KFORMJURI, value);
    }

    /**
     * Gets the attribute value for NIF using the alias name Nif.
     * @return the NIF
     */
    public String getNif() {
        return (String) getAttributeInternal(NIF);
    }

    /**
     * Sets <code>value</code> as attribute value for NIF using the alias name Nif.
     * @param value value to set the NIF
     */
    public void setNif(String value) {
        setAttributeInternal(NIF, value);
    }

    /**
     * Gets the attribute value for NOMCOMMERCIALE using the alias name Nomcommerciale.
     * @return the NOMCOMMERCIALE
     */
    public String getNomcommerciale() {
        return (String) getAttributeInternal(NOMCOMMERCIALE);
    }

    /**
     * Sets <code>value</code> as attribute value for NOMCOMMERCIALE using the alias name Nomcommerciale.
     * @param value value to set the NOMCOMMERCIALE
     */
    public void setNomcommerciale(String value) {
        setAttributeInternal(NOMCOMMERCIALE, value);
    }

    /**
     * Gets the attribute value for RAISONSOCIALE using the alias name Raisonsociale.
     * @return the RAISONSOCIALE
     */
    public String getRaisonsociale() {
        return (String) getAttributeInternal(RAISONSOCIALE);
    }

    /**
     * Sets <code>value</code> as attribute value for RAISONSOCIALE using the alias name Raisonsociale.
     * @param value value to set the RAISONSOCIALE
     */
    public void setRaisonsociale(String value) {
        setAttributeInternal(RAISONSOCIALE, value);
    }

    /**
     * Gets the attribute value for DATEDEBEXP using the alias name Datedebexp.
     * @return the DATEDEBEXP
     */
    public Timestamp getDatedebexp() {
        return (Timestamp) getAttributeInternal(DATEDEBEXP);
    }

    /**
     * Sets <code>value</code> as attribute value for DATEDEBEXP using the alias name Datedebexp.
     * @param value value to set the DATEDEBEXP
     */
    public void setDatedebexp(Timestamp value) {
        setAttributeInternal(DATEDEBEXP, value);
    }

    /**
     * Gets the attribute value for CAPITALSOCIALE using the alias name Capitalsociale.
     * @return the CAPITALSOCIALE
     */
    public BigDecimal getCapitalsociale() {
        return (BigDecimal) getAttributeInternal(CAPITALSOCIALE);
    }

    /**
     * Sets <code>value</code> as attribute value for CAPITALSOCIALE using the alias name Capitalsociale.
     * @param value value to set the CAPITALSOCIALE
     */
    public void setCapitalsociale(BigDecimal value) {
        setAttributeInternal(CAPITALSOCIALE, value);
    }

    /**
     * Gets the attribute value for REGISTRECOMMERCE using the alias name Registrecommerce.
     * @return the REGISTRECOMMERCE
     */
    public String getRegistrecommerce() {
        return (String) getAttributeInternal(REGISTRECOMMERCE);
    }

    /**
     * Sets <code>value</code> as attribute value for REGISTRECOMMERCE using the alias name Registrecommerce.
     * @param value value to set the REGISTRECOMMERCE
     */
    public void setRegistrecommerce(String value) {
        setAttributeInternal(REGISTRECOMMERCE, value);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link AdresseVO.
     */
    public RowIterator getAdresseVO() {
        return (RowIterator) getAttributeInternal(ADRESSEVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link ComptebancaireVO.
     */
    public RowIterator getComptebancaireVO() {
        return (RowIterator) getAttributeInternal(COMPTEBANCAIREVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link ActiviteentrepriseVO.
     */
    public RowIterator getActiviteentrepriseVO() {
        return (RowIterator) getAttributeInternal(ACTIVITEENTREPRISEVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link DeclarationVO.
     */
    public RowIterator getDeclarationVO() {
        return (RowIterator) getAttributeInternal(DECLARATIONVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link PaysVO.
     */
    public RowIterator getPaysVO() {
        return (RowIterator) getAttributeInternal(PAYSVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link PersonneVO.
     */
    public RowIterator getPersonneVO() {
        return (RowIterator) getAttributeInternal(PERSONNEVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link ReclamationVO.
     */
    public RowIterator getReclamationVO() {
        return (RowIterator) getAttributeInternal(RECLAMATIONVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link ContribuableuserVO.
     */
    public RowIterator getContribuableuserVO() {
        return (RowIterator) getAttributeInternal(CONTRIBUABLEUSERVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link ImpotcontribuableVO.
     */
    public RowIterator getImpotcontribuableVO() {
        return (RowIterator) getAttributeInternal(IMPOTCONTRIBUABLEVO);
    }

    /**
     * Gets the associated <code>RowIterator</code> using master-detail link AdresseVO1.
     */
    public RowIterator getAdresseVO1() {
        return (RowIterator) getAttributeInternal(ADRESSEVO1);
    }
}

