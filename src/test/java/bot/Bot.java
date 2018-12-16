package bot;

public class Bot {

    private final String email;
    private final String pwd;

    public Bot(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public static Bot generateDefault() {
         return new Bot(new String("****"), new String("****"));
    }
}
