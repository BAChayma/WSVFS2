package WS;

import java.math.BigDecimal;

import java.math.BigInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.Data.Contribuable;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.client.Configuration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.DBTransaction;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.sql.Timestamp;

import java.text.ParseException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import java.util.Set;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import model.AM.ContribuableAMImpl;

import model.Data.ActiviteEntreprise;
import model.Data.Adresse;
import model.Data.CompteBancaire;
import model.Data.ConsulterDossierContribuable;
import model.Data.InfoContribuable;

import model.VO.AdresseVOImpl;
import model.VO.ContribuableVOImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.model.OperationBinding;

import oracle.jbo.RowIterator;
import oracle.jbo.RowSetIterator;


@Path("/contribuableWs")

public class ContribuableWS {
    
    public static final String contribuableAM = "model.AM.ContribuableAM";
    public static final String contribuableAM_CONFIG = "ContribuableAMLocal";
    
    public static final String tabAM = "model.AM.AdresseAM";
    public static final String tabAM_CONFIG = "AdresseAMLocal";
        
    public static final String CompteBancaieAM = "model.AM.ComptebancaireAM";
    public static final String CompteBancaieAM_CONFIG = "ComptebancaireAMLocal";
        
        ContribuableAMImpl appModule1 = (ContribuableAMImpl)Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
        ApplicationModule appModule11=Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
        
       
        public void setBindVarAndExceuteLov(Integer deptId){
           ContribuableVOImpl     contribuablevo =(ContribuableVOImpl) appModule1.getContribuableVO1();
               contribuablevo.setNamedWhereClauseParam("bvnif", deptId);
               contribuablevo.executeQuery();
           }
        
        public OperationBinding getBindings(String binding){
               return (OperationBinding) BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding(binding);
           }
           
    
    @GET
           @Produces("application/json")
           @Path("/RechercheContribuable/")
           public List<ConsulterDossierContribuable>  RechercheContribuable (@QueryParam("nif") String nif) throws SQLException {            
               ConsulterDossierContribuable user = new ConsulterDossierContribuable();
               Adresse user1 = new Adresse();
               
               List<ConsulterDossierContribuable> ListContriWS = new ArrayList<ConsulterDossierContribuable>();
               List<Adresse> ListAdrWS = new ArrayList<Adresse>();
               List<CompteBancaire> ListCbWS = new ArrayList<CompteBancaire>();
               ContribuableVOImpl contribuablevo =(ContribuableVOImpl) appModule1.getContribuableVO1();
               contribuablevo.setNamedWhereClauseParam("bvnif", nif);
               contribuablevo.executeQuery();
              
                   for (Row rContri : contribuablevo.getAllRowsInRange() ) {
                       user.setNomCommerciale((String) rContri.getAttribute("Nomcommerciale"));
                       user.setRaisonSociale((String) rContri.getAttribute("Raisonsociale"));
                       user.setRegistreCommerce((String) rContri.getAttribute("Registrecommerce"));
                       user.setDateDebExp((Date) rContri.getAttribute("Datedebexp"));
                       //user.setCapitalSociale( rContri.getAttribute("Capitalsociale"));
                       ListContriWS.add(user);
                       
                       BigDecimal Kcnc =(BigDecimal)  rContri.getAttribute("Kcnc") ;
                       System.out.println("Kcnc "+Kcnc);
                       
                       /* adresse */
                       String SQL_SELECT = "Select * from  Adresse where kcnc =  " +Kcnc;
                       ViewObject vo = appModule11.createViewObjectFromQueryStmt(null, SQL_SELECT);
                       AdresseVOImpl adrvo =(AdresseVOImpl) appModule1.getAdresseVO1();
                       adrvo.executeQuery();
                       System.out.println("taille adrvo:"+vo.getEstimatedRowCount());
                       vo.executeQuery();
                        if (vo.getEstimatedRowCount() != 0 && vo.first() != null) {
                           Row row = vo.first();
                               while (row != null) {
                                   System.out.println("kadresse"+row.getAttribute(0));
                                   Adresse user11 = new Adresse();
                                   user11.setKadresse((Integer.valueOf(row.getAttribute(0).toString())));
                                   user11.setNumRue(Integer.valueOf(row.getAttribute(1).toString()));
                                   user11.setRue((String) row.getAttribute(2).toString());
                                   user11.setCp((String) row.getAttribute(3).toString());
                                   ListAdrWS.add(user11);
                                   row = vo.next();                        
                               } 
                          }
                        user.setLadr(ListAdrWS);      
                       
                       /* cb */
                       
                       
                       
                   }
                
           Configuration.releaseRootApplicationModule(appModule1, true);
        return ListContriWS ;
           
           }
    
    
    @GET
    @Path("/InfoContriDclByNif/")
    @Produces("application/json")
    @Consumes("application/json")
    public InfoContribuable InfoContriDclByNif (@QueryParam("nif") String nif) {
        Date p_dbexp = new Date();
        String p_nif = null, p_rs = null, p_nc = null, p_rc = null, p_lfj = null, p_lae = null, p_nat = null, p_rue = null, p_cp = null, p_libsadr = null, libtsadr = null;
        String p_rib = null, p_nb = null, p_na = null, p_idPer = null, p_nomPer = null, p_prenomPer = null;
        double p_cs = 0;
        int p_kcnc = 0, p_kc = 0, p_kadr = 0, p_numrue = 0;
        InfoContribuable contriWS = new InfoContribuable();
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
        String req = " select c.nif, c.kcnc, c.nomCommerciale,c.raisonSociale,c.registreCommerce,c.dateDebExp,c.capitalSociale,fj.libellefj , ae.libelleae , p.nationnalite \n" + 
        "from Contribuable c , FormeJuridique fj , ActiviteEntreprise ae , Pays p  \n" + 
        "where c.kformjuri = fj.kformjuri and c.kcnc = ae.kcnc and c.kcnc = p.kcnc and c.nif = ? " ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, nif);  
            resultSet = createPreparedStatement.executeQuery();
            if (resultSet.next()) {
                p_nif = resultSet.getString(1);
                p_kcnc = resultSet.getInt(2);
                p_nc = resultSet.getString(3);
                p_rs = resultSet.getString(4);
                p_rc = resultSet.getString(5);
                p_dbexp = resultSet.getDate(6);
                p_cs = resultSet.getDouble(7);
                p_lfj = resultSet.getString(8);
                p_lae = resultSet.getString(9);
                p_nat = resultSet.getString(10);    
            }
            contriWS.setKcnc(p_kcnc);
            contriWS.setNif(p_nif);
            contriWS.setNomCommerciale(p_nc);
            contriWS.setRaisonSociale(p_rs);
            contriWS.setRegistreCommerce(p_rc);
            contriWS.setDateDebExp(p_dbexp);
            contriWS.setCapitalSociale(p_cs);
            contriWS.setLibellefj(p_lfj);
            contriWS.setLibelleae(p_lae);
            contriWS.setNationnalite(p_nat);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return contriWS;
    }
    
    
    @GET
    @Path("/InfoContribuableById/")
    @Produces("application/json")
    @Consumes("application/json")
    public InfoContribuable InfoContribuableById (@QueryParam("nif") String nif) {
        Date p_dbexp = new Date();
        String p_rs = null, p_nc = null, p_rc = null, p_lfj = null, p_lae = null, p_nat = null, p_rue = null, p_cp = null, p_libsadr = null, libtsadr = null;
        String p_rib = null, p_nb = null, p_na = null, p_idPer = null, p_nomPer = null, p_prenomPer = null;
        double p_cs = 0;
        int p_kc = 0, p_kadr = 0, p_numrue = 0;
        InfoContribuable contriWS = new InfoContribuable();
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
        String req = " select c.nomCommerciale,c.raisonSociale,c.registreCommerce,c.dateDebExp,c.capitalSociale,fj.libellefj , ae.libelleae , p.nationnalite , per.identifiant , per.nom , per.prenom \n" + 
        "from Contribuable c , FormeJuridique fj , ActiviteEntreprise ae , Pays p  ,Personne per\n" + 
        "where c.kformjuri = fj.kformjuri and c.kcnc = ae.kcnc and c.kcnc = p.kcnc and per.kcnc = c.kcnc and per.qualite = 'responsable' and c.nif = ? " ;
        PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
        ResultSet resultSet = null;
        try {
            createPreparedStatement.setString(1, nif);  
            resultSet = createPreparedStatement.executeQuery();
            if (resultSet.next()) {
                p_nc = resultSet.getString(1);
                p_rs = resultSet.getString(2);
                p_rc = resultSet.getString(3);
                p_dbexp = resultSet.getDate(4);
                p_cs = resultSet.getDouble(5);
                p_lfj = resultSet.getString(6);
                p_lae = resultSet.getString(7);
                p_nat = resultSet.getString(8);
                p_idPer = resultSet.getString(9);
                p_nomPer = resultSet.getString(10);
                p_prenomPer = resultSet.getString(11);  
            }
            contriWS.setNomCommerciale(p_nc);
            contriWS.setRaisonSociale(p_rs);
            contriWS.setRegistreCommerce(p_rc);
            contriWS.setDateDebExp(p_dbexp);
            contriWS.setCapitalSociale(p_cs);
            contriWS.setLibellefj(p_lfj);
            contriWS.setLibelleae(p_lae);
            contriWS.setNationnalite(p_nat);
            contriWS.setIdentifiant(p_idPer);
            contriWS.setNom(p_nomPer);
            contriWS.setPrenom(p_prenomPer);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        Configuration.releaseRootApplicationModule(appModule, true);
        return contriWS;
    }
   
    @PUT
    @Path("/updateContribuable/")
    @Produces("application/json")
    @Consumes("application/json")
    public Contribuable updateContribuable(Contribuable contriWS){
        //Contribuable contriWS = new Contribuable();
        PreparedStatement createPreparedStatement = null;
        String req = " update contribuable c set nomCommerciale = '?', raisonSociale = '?', dateDebExp = ?, capitalSociale = '?', registreCommerce = '?'  where c.nif = ?  " ;
        ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.contribuableAM, this.contribuableAM_CONFIG);
        createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+"update contribuable c set nomCommerciale = ?, raisonSociale = ?, registreCommerce = ?, capitalSociale = ?  where c.nif = ?  ",0);
        try {
            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentTime = sdf.format(dt);
            
            createPreparedStatement.setString(1, contriWS.getNomCommerciale());
            createPreparedStatement.setString(2, contriWS.getRaisonSociale());
            createPreparedStatement.setString(3, contriWS.getRegistreCommerce());
            createPreparedStatement.setDouble(4, contriWS.getCapitalSociale());
            createPreparedStatement.setString(5, contriWS.getNif());
           
            //createPreparedStatement.setTimestamp(4, new java.sql.Timestamp(dt.getTime()) );
            //createPreparedStatement.setTimestamp(4,  new java.sql.Timestamp(dt.getTime())); 
            //createPreparedStatement.setString(4, currentTime) ; 
            
            createPreparedStatement.executeUpdate();
            System.out.println(String.format("Row affected %d", createPreparedStatement.executeUpdate()));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        appModule.getTransaction().commit();
        Configuration.releaseRootApplicationModule(appModule, true);
        return contriWS;
    }
    
    private static Adresse mapAdr(Row row ) throws SQLException {
              Adresse user1 = new Adresse();
              user1.setKadresse((Integer.valueOf(row.getAttribute(0).toString())));
              user1.setNumRue(Integer.valueOf(row.getAttribute(1).toString()));
              user1.setRue((String) row.getAttribute(2).toString());
              user1.setCp((String) row.getAttribute(3).toString());
              return user1;
          }
    
   /* private static Adresse mapAdr(ResultSet resultSet) throws SQLException {
           Adresse user = new Adresse();
           user.setKadresse(resultSet.getInt("kadresse"));
           user.setNumRue(resultSet.getInt("numRue"));
           user.setRue(resultSet.getString("rue"));
           user.setCp(resultSet.getString("cp"));
           user.setLibellesadr(resultSet.getString("libellesadr"));
           user.setLibelletsadr(resultSet.getString("libelletsadr"));
           return user;
       }
    
    private static ConsulterDossierContribuable mapContri(ResultSet resultSet) throws SQLException {
           ConsulterDossierContribuable user = new ConsulterDossierContribuable();
           user.setNomCommerciale(resultSet.getString("nomCommerciale"));
           user.setRaisonSociale(resultSet.getString("raisonSociale"));
           user.setRegistreCommerce(resultSet.getString("registreCommerce"));
           user.setDateDebExp(resultSet.getDate("dateDebExp"));
           user.setCapitalSociale(resultSet.getDouble("capitalSociale"));
           user.setLibellefj(resultSet.getString("libelleFJ"));
           user.setLibelleae(resultSet.getString("libelleAE"));
           user.setNationnalite(resultSet.getString("nationnalite"));
           return user;
    }
    
    
    
    private static List<ConsulterDossierContribuable>  map(ResultSet resultSet) throws SQLException {
        
           ConsulterDossierContribuable user = new ConsulterDossierContribuable();
           ArrayList<ConsulterDossierContribuable> al1 = new ArrayList<>();
           user.setNomCommerciale(resultSet.getString("nomCommerciale"));
           user.setRaisonSociale(resultSet.getString("raisonSociale"));
           user.setRegistreCommerce(resultSet.getString("registreCommerce"));
           user.setDateDebExp(resultSet.getDate("dateDebExp"));
           user.setCapitalSociale(resultSet.getDouble("capitalSociale"));
           user.setLibellefj(resultSet.getString("libelleFJ"));
           user.setLibelleae(resultSet.getString("libelleAE"));
           user.setNationnalite(resultSet.getString("nationnalite"));
           al1.add(user);
           ArrayList<ConsulterDossierContribuable> contriList = new ArrayList<>(Arrays.asList(user));
           
           Adresse user1 = new Adresse(); 
           ArrayList<Adresse> al2 = new ArrayList<>();
           user1.setKadresse(resultSet.getInt("kadresse"));
           user1.setKadresse(resultSet.getInt("kadresse"));
           user1.setNumRue(resultSet.getInt("numRue"));
           user1.setRue(resultSet.getString("rue"));
           user1.setCp(resultSet.getString("cp"));
           user1.setLibellesadr(resultSet.getString("libellesadr"));
           user1.setLibelletsadr(resultSet.getString("libelletsadr"));
           al2.add(user1);
           ArrayList<Adresse> adrList = new ArrayList<>(Arrays.asList(user1));
           
           CompteBancaire user2 = new CompteBancaire();
           ArrayList<CompteBancaire> al3 = new ArrayList<>();
           user2.setKcompte(resultSet.getInt("kcompte"));
           user2.setRib(resultSet.getString("rib"));
           user2.setNomBanque(resultSet.getString("nomBanque"));
           user2.setLibelleAgence(resultSet.getString("libelleAgence"));
           al3.add(user2);
           ArrayList<CompteBancaire> cbList = new ArrayList<>(Arrays.asList(user2));
           
           //user.setLadr(adrList);
           //user.setLcb( cbList);
           al1.add(user);
        
          /* ArrayList<ConsulterDossierContribuable> ListWS = new ArrayList<ConsulterDossierContribuable>();
           List<ArrayList<ConsulterDossierContribuable>> listOfLists = new ArrayList<ArrayList<ConsulterDossierContribuable>>();
           
           ConsulterDossierContribuable user = new ConsulterDossierContribuable();
           user.setNomCommerciale(resultSet.getString("nomCommerciale"));
           user.setRaisonSociale(resultSet.getString("raisonSociale"));
           user.setRegistreCommerce(resultSet.getString("registreCommerce"));
           user.setDateDebExp(resultSet.getDate("dateDebExp"));
           user.setCapitalSociale(resultSet.getDouble("capitalSociale"));
           user.setLibellefj(resultSet.getString("libelleFJ"));
           user.setLibelleae(resultSet.getString("libelleAE"));
           user.setNationnalite(resultSet.getString("nationnalite"));
                     
           List<ArrayList<Adresse>> listOfLists1 = new ArrayList<ArrayList<Adresse>>(); 
           ArrayList<Adresse> ListADR = new ArrayList<Adresse>();
           Adresse user1 = new Adresse(); 
           Set <Adresse> adresseSet = new HashSet<>();
           user1.setKadresse(resultSet.getInt("kadresse"));
           user1.setKadresse(resultSet.getInt("kadresse"));
           user1.setNumRue(resultSet.getInt("numRue"));
           user1.setRue(resultSet.getString("rue"));
           user1.setCp(resultSet.getString("cp"));
           user1.setLibellesadr(resultSet.getString("libellesadr"));
           user1.setLibelletsadr(resultSet.getString("libelletsadr"));
           adresseSet.add(user1);
           
           List<ArrayList<CompteBancaire>> listOfLists2 = new ArrayList<ArrayList<CompteBancaire>>(); 
           ArrayList<CompteBancaire> ListCB = new ArrayList<CompteBancaire>();
           CompteBancaire user2 = new CompteBancaire();
           user2.setKcompte(resultSet.getInt("kcompte"));
           user2.setRib(resultSet.getString("rib"));
           user2.setNomBanque(resultSet.getString("nomBanque"));
           user2.setLibelleAgence(resultSet.getString("libelleAgence"));
        
           ListADR.add(user1);
           ListCB.add(user2);
           
           user.setLadr(ListADR);
           user.setLcb( ListCB);
           
           ListWS.add(user);
           
           ListWS.retainAll(ListADR);
           ListWS.retainAll(ListCB);
           
           return  al1; user;
       }
    
    /*private static ConsulterDossierContribuable map(ResultSet resultSet) throws SQLException {
           ConsulterDossierContribuable user = new ConsulterDossierContribuable();
           user.setNomCommerciale(resultSet.getString("nomCommerciale"));
           user.setRaisonSociale(resultSet.getString("raisonSociale"));
           user.setRegistreCommerce(resultSet.getString("registreCommerce"));
           user.setDateDebExp(resultSet.getDate("dateDebExp"));
           user.setCapitalSociale(resultSet.getDouble("capitalSociale"));
           user.setLibellefj(resultSet.getString("libelleFJ"));
           user.setLibelleae(resultSet.getString("libelleAE"));
           user.setNationnalite(resultSet.getString("nationnalite"));
           user.setKcompte(resultSet.getInt("kcompte"));
           user.setRib(resultSet.getString("rib"));
           user.setNombanque(resultSet.getString("nomBanque"));
           user.setLibelleagence(resultSet.getString("libelleAgence"));
           user.setIdentifiant(resultSet.getString("identifiant"));
           user.setNom(resultSet.getString("nom"));
           user.setPrenom(resultSet.getString("prenom"));
           user.setKadresse(resultSet.getInt("kadresse"));
           user.setNumRue(resultSet.getInt("numRue"));
           user.setRue(resultSet.getString("rue"));
           user.setCp(resultSet.getString("cp"));
           //user.setKStructureAdr(resultSet.getInt("kStructureAdr"));
           user.setLibellesadr(resultSet.getString("libellesadr"));
           //user.setKTStructureAdr(resultSet.getInt("kTStructureAdr"));
           user.setLibelletsadr(resultSet.getString("libelletsadr"));
           return user;
       }*/
   
    
    
   
}
    