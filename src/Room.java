/**
 * Creted by
 * Burak Demirci
 * 141044091
 */
public class Room {
    /**
     *  If room is empty situation true
     *  else situation false
     */
    private boolean situation;
    private int no,capacity;

    /**
     *  Costructor
     * @param No Room door number
     * @param st Room situation (empty or not)
     */
    public Room(int No ,int capacity, int st ){

        if(st==0) /* Empty */
            this.situation = true;
        else /* Not Empty */
            this.situation = false;

        this.capacity= capacity;
        this.no=No;
    }

    /**
     *  get room situation
     * @return If room is empty situation true else false
     */
    public boolean getSituation(){

        return this.situation;
    }

    /**
     *  Check in the room
     * @return if check in success return true else false
     */
    public boolean bookRoom(){

        if(!getSituation())
            return false;
        else
           this.situation= false;

        return true;
    }
    /**
     *  Check out the room
     * @return if check out success return true else false
     */
    public boolean cancelRoom(){
        if (getSituation())
            return false;
        else
            this.situation=true;

        return true;
    }

    /**
     *  get Room Capacity
     * @return Capacity of room
     */
    public int getCapacity(){
        return this.capacity;
    }


    /**
     *  get room door number
     * @return room door number
     */
    public int getRoomNo(){
        return this.no;
    }

    @Override
    public String toString(){
        String str = "";
        str = str.concat("Oda No:"+this.no+" Kapasite:"+ this.capacity + " Durum:");

        if(this.situation)
            str= str.concat(" Boş");
        else
            str = str.concat(" Dolu");

        return str;
    }
}
