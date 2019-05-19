package API.REST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;



@Path("/info")
public class ZoneInfoRest {
    @GET
    public String index() {
        return JSONHelper.getJSONFromObject(TestData.zoneList);
    }
}
