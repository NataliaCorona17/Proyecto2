
package proyecto4;



import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

abstract class  User {
    protected Profile profile;
    protected String username;
    protected String password;

    public User(Profile profile, String username, String password) {
        this.profile = profile;
        this.username = username;
        setPassword(password);
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean isClient() {
        return this instanceof Client;
    }

    public boolean isAdministrator() {
        return this instanceof Administrator;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(hashPassword(password));
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

