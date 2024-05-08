/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto4;

import java.util.Date;
class Profile {
    private String name;
    private String lastName;
    private Date birthdate;

    public Profile(String name, String lastName, Date birthdate) {
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

   
    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}