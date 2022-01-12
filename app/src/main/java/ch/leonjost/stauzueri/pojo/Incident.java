package ch.leonjost.stauzueri.pojo;

public class Incident {
    public String type;
    private Properties properties;
    public Geometry geometry;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
