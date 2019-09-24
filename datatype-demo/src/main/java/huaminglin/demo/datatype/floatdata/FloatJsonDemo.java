package huaminglin.demo.datatype.floatdata;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
0.10000000149011613
0.10000000149011613
*/
public class FloatJsonDemo {
    public static void main(String[] args) throws IOException {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(100);

        String jsonString = "{\"k\":0.10000000149011614}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(jsonString);
        JsonNode k = actualObj.get("k");
        System.out.println(k.isDouble());
        System.out.println(k.asText());
        double d = k.asDouble();
        System.out.println(df.format(d));
    }
}
