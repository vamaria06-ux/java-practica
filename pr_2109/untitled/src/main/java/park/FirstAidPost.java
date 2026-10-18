package park;

public class FirstAidPost extends Building {
    private int treated;

    public FirstAidPost() {
        super("Травмпункт");
    }

    public String treat(Visitor visitor) {
        treated++;
        return "Травмпункт: " + visitor + " оказана помощь";
    }

    public int getTreated() {
        return treated;
    }
}