package huaminglin.demo.wsdl.server;

public class HelloResponse {
    private HelloStatus status;
    private String message;

    public HelloStatus getStatus() {
        return status;
    }

    public void setStatus(HelloStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
