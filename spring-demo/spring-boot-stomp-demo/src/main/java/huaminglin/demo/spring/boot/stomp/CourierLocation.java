package huaminglin.demo.spring.boot.stomp;

public class CourierLocation {
    private String courierId;
    private String location;

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}