public class Meal {

    private String name;
    private String time;
    private String kcal;

    public Meal(String name, String kcal, String time){
        this.name = name;
        this.time = time;
        this.kcal = kcal;
    }

    public String getKcal() {
        return kcal;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}
