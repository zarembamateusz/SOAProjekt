package rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/info")
public class ZoneInfoRest {
    @GET
    public String index() {
        return JSONHelper.getJSONFromObject(TestData.zoneList);
    }
}
