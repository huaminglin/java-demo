package huaminglin.demo.datatype.floatdata;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
Double: true
NUMBER
0.10000000149011613
0.10000000149011613

Long: true
NUMBER
18014398509481985
18014398509481985

Int: true
NUMBER
180
180

BigInteger: true
NUMBER
1801439850948198500000000


BigDecimal: false
NUMBER
Infinity
*/
public class FloatJsonDemo {
    public static void main(String[] args) throws IOException {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(100);

        String jsonString = "{\"k\":0.10000000149011614, \"k2\":18014398509481985, \"k3\":180, \"k4\":1801439850948198500000000, \"k5\":180143985094819850000000018014398509481985000000001801439850948198500000000180143985094819850000000018014398509481985000000001801439850948198500000000180143985094819850000000018014398509481985000000001801439850948198500000000180143985094819850000000018014398509481985000000001801439850948198500000000180143985094819850000000018014398509481985000000001801439850948198500000000180143985094819850000000018014398509481985000000001801439850948198500000000.01}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(jsonString);
        {
            JsonNode k = actualObj.get("k");
            System.out.println("\nDouble: " + k.isDouble());
            System.out.println(k.getNodeType());
            System.out.println(k.asText());
            double d = k.asDouble();
            System.out.println(df.format(d));
        }
        {
            JsonNode k = actualObj.get("k2");
            System.out.println("\nLong: " + k.isLong());
            System.out.println(k.getNodeType());
            System.out.println(k.asText());
            System.out.println(k.asLong());
        }
        {
            JsonNode k = actualObj.get("k3");
            System.out.println("\nInt: " + k.isInt());
            System.out.println(k.getNodeType());
            System.out.println(k.asText());
            System.out.println(k.asInt());
        }
        {
            JsonNode k = actualObj.get("k4");
            System.out.println("\nBigInteger: " + k.isBigInteger());
            System.out.println(k.getNodeType());
            System.out.println(k.asText());
            System.out.println();
        }
        {
            JsonNode k = actualObj.get("k5");
            System.out.println("\nBigDecimal: " + k.isBigDecimal());
            System.out.println(k.getNodeType());
            System.out.println(k.asText());
            System.out.println();
        }
    }
}
