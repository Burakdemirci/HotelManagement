public class Main {

    public static void main(String[] args) throws Exception {
        String roomFile = "log/Rooms.csv";
        String guestFile = "log/Guests.csv";
        String receptionistFile= "log/Receptionists.csv";

        Hotel gtu = new Hotel(roomFile,receptionistFile,guestFile);
        gtu.service();
    }

}
