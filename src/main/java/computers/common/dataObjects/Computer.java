package computers.common.dataObjects;

public class Computer {

    private String name;
    private String introducedDate;
    private String discontinuedDate;
    private String company;

    public Computer(String name, String introducedDate, String discontinuedDate, String company) {
        this.name = name;
        this.introducedDate = introducedDate;
        this.discontinuedDate = discontinuedDate;
        this.company = company;
    };

    public String getName() {
        return name;
    }

    public String getIntroducedDate() {
        return introducedDate;
    }

    public String getDiscontinuedDate() {
        return discontinuedDate;
    }

    public String getCompany() {
        return company;
    }
}
