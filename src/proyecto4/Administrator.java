
package proyecto4;

import java.util.ArrayList;

public class Administrator extends User {
    private ArrayList<Permissions> permissions;
    private boolean isSuperAdmin;

    public Administrator(ArrayList<Permissions> permissions, boolean isSuperAdmin, Profile profile, String username, String password) {
        super(profile, username, password);
        this.permissions = permissions;
        this.isSuperAdmin = isSuperAdmin;
        this.password = hashPassword(password); 
    }

    public ArrayList<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<Permissions> permissions) {
        this.permissions = permissions;
    }

    public boolean isIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
