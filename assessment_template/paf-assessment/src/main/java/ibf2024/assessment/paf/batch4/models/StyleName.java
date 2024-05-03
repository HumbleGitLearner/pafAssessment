package ibf2024.assessment.paf.batch4.models;

public class StyleName {
     private int styleId;
    private  String name;
    public int getStyleId() {
        return styleId;
    }
    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public StyleName(int styleId, String name) {
        this.styleId = styleId;
        this.name = name;
    }
    public StyleName() {
    }

 
}
