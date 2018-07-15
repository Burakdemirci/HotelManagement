/**
 * Creted by
 * Burak Demirci
 * 141044091
 */

public class Receptionist extends Users {

    private int id;
    private String name ,pass;

    /**
     *  Constructor
     * @param name Receptionists name
     * @param id   Receptionists id
     * @param pass Receptionists password
     */
    public Receptionist(String name,int id,String pass){
        this.id=id;
        this.name=name;
        this.pass=pass;
    }

    /**
     * get Receptionist name
     * @return name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     *  get Receptionist id
     * @return id
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     *  get Password
     * @return Receptionists password
     */
    public String getPassword(){
        return this.pass;
    }

    /**
     *  Set password
     * @param pass new password
     */
    public boolean setPassword(String pass){
        if(pass==null) {
            System.out.println("Şifre oluşturulmadı");
            return false;
        }else {
            this.pass = pass;
            System.out.println("Şifreniz başarı ile değiştirildi");
        }
        return true;
    }
    
    @Override
    public String toString(){
        String str= new String();
        str= str.concat("Kimlik No:"+this.id+"İsim:"+this.name);
        return str;
    }

}
