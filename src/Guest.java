/**
 * Creted by
 * Burak Demirci
 * 141044091
 */

public class Guest extends Users {

    private String name ;
    private int id,roomNo,capacity;

    /**
     *  Constructor
     * @param name guest name
     * @param id guest id
     * @param cp room capacity
     * @param roomN room door number
     */
    public Guest(String name,int id,int cp,int roomN){

        this.name=name;
        this.id=id;
        this.capacity=cp;
        this.roomNo=roomN;
    }

    /**
     *  Guest constructor
     */
    public Guest(){
        this.name ="";
        this.id=0;
        this.roomNo=-1;
        this.capacity=0;
    }

    /**
     *  get Guest id
     * @return guest id
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     *  get guest name
     * @return Guest name
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     *  Guests book this capacity room
     * @param cp capacity
     */

    public void setCapacity(int cp){

        this.capacity=cp;
    }

    /**
     *  Guests book this capacity
     * @return room capacity
     */
    public int getCapacity(){
        return this.capacity;
    }
    /**
     *  Guest book room number get
     * @return room door number
     */
    public int getRoomNo(){
        return this.roomNo;
    }

    /**
     *  Guest set nae
     * @param name guest name
     * @return if the name valid return true else false
     */
    public boolean setName(String name){
        if(name==null)
            return false;
        else
           this.name=name;

        return true;
    }

    /***
     *  Guest book room number set
     * @param no room door number
     */
    public void setRoomNo(int no){
        this.roomNo=no;
    }

    /**
     *  set guest id
     * @param id person id
     * @return if the id valid return true else false
     */
    public boolean setID(int id){

        if(id<=0 )
          return false;
        else
          this.id=id;

        return true;
    }

    /**
     *  To String
     * @return Guest Object string
     */
    @Override
    public String toString(){
        String str="";
        str = str.concat("Kimlik No :"+this.id+" İsim :"+this.name+" Oda No :"+
                this.roomNo);
        return str;
    }

    /**
     *  The equals method compare the guests
     * @param o the comparable guest object
     * @return if the objects same return true else return false
     */
    @Override
    public boolean equals(Object o){

        if(o!=null && getClass()==o.getClass()) {
            Guest t = (Guest)o;
            if (t.getId() == this.id && t.getName().equals(this.name))
                return true;
        }
        return false;
    }

}
