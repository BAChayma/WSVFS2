package WS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.Data.ContribuableImpot;
import model.Data.Impot;

import model.Data.StructureAdr;

import oracle.jbo.client.Configuration;
import oracle.jbo.server.ApplicationModuleImpl;

@Path("/ContribuableImpotWS")
    @Produces("application/json")
    @Consumes("application/json")

public class ContribuableImpotWS {
    
    public static final String impotAM = "model.AM.ImpotcontribuableAM";
    public static final String impotAM_CONFIG = "ImpotcontribuableAMLocal";
    
    @GET
    @Path("/ImpotByContribuable/")
    @Produces("application/json")
    @Consumes("application/json")
    public ContribuableImpot ImpotByContribuable (@QueryParam("nif") String nif) {
            String p_abri = null ;
            int p_ki = 0;
            Double p_taux = 0.0;
            String tx=null;
            ContribuableImpot list = new ContribuableImpot();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.impotAM, this.impotAM_CONFIG);
            String req = "select i.kimpot , i.abriviation , i.taux\n" + 
            "from IMPOTCONTRIBUABLE ic , CONTRIBUABLE c , IMPOT i\n" + 
            "where c.kcnc = ic.kcnc and i.kimpot = ic.kimpot and c.nif = ? ";
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                createPreparedStatement.setString(1, nif);  
                resultSet = createPreparedStatement.executeQuery();
                if (resultSet.next()) {
                    p_ki = resultSet.getInt(1);
                    p_abri = resultSet.getString(2); 
                    //tx =  resultSet.getString(3);
                    p_taux =  resultSet.getDouble(3);

                    System.out.println("p_ki:"+p_ki);
                    System.out.println("p_abri:"+p_abri);
                    System.out.println("p_taux:"+p_taux);
                    System.out.println( p_ki + p_abri + p_taux );
                }
                list.setAbriviation(p_abri);
                list.setKimpot(p_ki);
                list.setTaux(p_taux);
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return list;
        }
    
   /* @GET
    @Path("/ImpotByContribuable/")
    @Produces("application/json")
    @Consumes("application/json")
    public List<ContribuableImpot> ImpotByContribuable (@QueryParam("nif") String nif) {
            ContribuableImpot list = new ContribuableImpot();
            List<ContribuableImpot> ListWS = new ArrayList<ContribuableImpot>();
            ApplicationModuleImpl appModule = (ApplicationModuleImpl)Configuration.createRootApplicationModule(this.impotAM, this.impotAM_CONFIG);
            String req = "select i.kimpot , i.limpot , i.abriviation , i.taux\n" + 
            "from IMPOTCONTRIBUABLE ic , CONTRIBUABLE c , IMPOT i\n" + 
            "where c.kcnc = ic.kcnc and i.kimpot = ic.kimpot and c.nif = ? ";
            PreparedStatement createPreparedStatement = appModule.getDBTransaction().createPreparedStatement (""+req,0);
            ResultSet resultSet = null;
            try {
                createPreparedStatement.setString(1, nif);  
                resultSet = createPreparedStatement.executeQuery();
                while (resultSet.next()) {
                    ListWS.add(map(resultSet));
                }
                
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
            Configuration.releaseRootApplicationModule(appModule, true);
            return ListWS;
        }*/
    
    private static ContribuableImpot map(ResultSet resultSet) throws SQLException {
           ContribuableImpot user = new ContribuableImpot();
           user.setKimpot(resultSet.getInt("kimpot"));
           user.setAbriviation(resultSet.getString("abriviation"));
           //user.setTaux(resultSet.getDouble("taux"));
           return user;
       }
    
}
