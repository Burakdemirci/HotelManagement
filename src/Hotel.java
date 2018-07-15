/**
 * Creted by
 * Burak Demirci
 * 141044091
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {

    /*The hotel data */
    private List<Room> rooms = new ArrayList<>();
    private List<Guest> guests= new ArrayList<>();
    private List<Receptionist>receptionists =  new ArrayList<>();
    /*Use that flag testing */
    private boolean testFlag = false;
    private String  testFileName;
    private boolean runFlag ;
    /*The hotel data file information*/
    private String roomFile,receptionistFile,guestFile;

    /**
     *  The hotel Constructor
     * @param roomFile room file directory
     * @param receptionistFile receptionist file directory
     * @param guestFile guests file directory
     */
    public Hotel(String roomFile, String receptionistFile,String guestFile) {
        this.roomFile=roomFile;
        this.receptionistFile=receptionistFile;
        this.guestFile=guestFile;
        this.runFlag=true;
        loadHotelSystem();
    }
    /**
     *  The hotel Service testing from file
     *  @throws FileNotFoundException The test input file not find exception
     */
    public void testService(String fileName) throws FileNotFoundException {

        if(fileName!=null) {
            testFlag = true;
            this.testFileName=fileName;
        }
        service();
    }

    /**
     *  The hotel Service
     *  @throws FileNotFoundException The test input file not find exception
     */
    public void service() throws FileNotFoundException {
        Scanner reader;

        if(testFlag) {
            File input = new File(testFileName);
            reader = new Scanner (input);
        }else
            reader = new Scanner(System.in);

        try {

            do {
                System.out.println("######################################");
                System.out.println("#              OTEL SARI             #");
                System.out.println("######################################\n");
                System.out.println("Giriş Yapmak İstediğiniz Yöntemin kodunu Giriniz ");
                System.out.println("Kod    Yöntem ");
                System.out.println(" 1     Misafir" + "\n" + " 2     Resepsiyon\n");

                int type = reader.nextInt();

                if (type==1)
                    guestsWork(reader);
                else if(type==2)
                    receptionistWork(reader);
                else if(type==3)
                    runFlag=false;
                else
                    System.out.println("Yanlış Girdiniz");

                saveData();
                System.out.println("\n");
            } while (runFlag);
            System.out.println("\n              SİSTEM KAPATILDI ");
        }catch(Exception e) {
            System.out.println("Exception caught: " + e);
        }

    }

    /**
     *  The method do Receptionists work complitely
     *  @param reader read the file or console
     */
    private void receptionistWork(Scanner reader){
        int index = loginReceptionist(reader);
        //Scanner reader = new Scanner(System.in);

        int type=0;
        if(index!=-1){
            try {
                do {
                    System.out.println("Yapmak istediğiniz İşlemin Kodunu Giriniz");
                    System.out.println("Kod      İşlem");
                    System.out.println(" 1       Rezervasyon" + "\n" + " 2       Rezervasyon İptali\n"  +
                                   " 3       Kayıt Oluştur" + "\n" + " 4       Kayıt Sil\n" +
                                   " 5       Şifre Yenile"+"\n"+     " 6       Çıkış");
                    type = reader.nextInt();
                    if (type == 1)
                        bookRoom(reader);
                    if(type==2)
                        cancelReservation(reader);
                    if (type == 3)
                        checkIn(reader);
                    if (type == 4)
                        checkOut(reader);
                    if (type == 5) {
                        System.out.println("Yeni Şifrenizi Giriniz : ");
                        String pass = reader.next();
                        receptionists.get(index).setPassword(pass);
                    }
                    saveData();
                }while(type!=6);
            }catch(Exception e) {
                System.out.println("Exception caught: " + e);
            }
        }else
            System.out.println("Kullanıcı Adı veya Şifre Hatalı");
    }

    /**
     *  The method do Guests work complitely
     *  @param reader read the file or console
     */
    private void guestsWork(Scanner reader){
        //Scanner reader = new Scanner(System.in);
        try {
            System.out.println("\n"+" Misafir Girişi Yapıldı \n"+"\nYapmak istediğiniz İşlemin Kodunu Giriniz");
            System.out.println("Kod      İşlem");
            System.out.println(" 1       Rezervasyon"+"\n"+" 2       Rezervasyon İptali");

            int type = reader.nextInt();
            if(type==1)
                bookRoom(reader);
            else if(type==2)
                cancelReservation(reader);

            saveData();
        }catch(Exception e) {
            System.out.println("Exception caught: " + e);
        }
    }

    /**
     * Check-out the hotel
     * @param reader read the file or console
     */
    private void checkOut(Scanner reader) {
        //Scanner reader = new Scanner(System.in);
        boolean flag=false;
        int roomNo = -1;
        try {
            System.out.print("Kimlik Numarası Giriniz : ");
            int id = reader.nextInt();
            for (int i=0; i<guests.size(); i++) {
                if( guests.get(i).getId()==id){
                    System.out.println("\n"+guests.get(i).toString());
                    roomNo = guests.get(i).getRoomNo();
                    guests.get(i).setCapacity(0);
                    guests.get(i).setRoomNo(-1);
                    flag=true;
                    break;
                }
            }
            if(!flag){
                System.out.println("Sistemde bu kimlikte kayıt bulunamadı bulunamadı\n\n");
            }else{
                for( int i=0; i<rooms.size(); i++){
                    if(rooms.get(i).getRoomNo()==roomNo){
                        rooms.get(i).cancelRoom();
                        System.out.println("Kayıt Silme İşlemi Başarı İle Gerçekleşti");
                        flag=false;
                        break;
                    }
                }
            }

            if(flag){
                System.out.println("Bu Kullanıcıya Ait Oda Bulunamadı");
            }

        }catch(Exception e) {
            System.out.println("Exception caught: " + e);
        }

    }

    /**
     *  Check-in the hotel system
     * @param reader read the file or console
     */
    private void checkIn(Scanner reader) {
        //Scanner reader = new Scanner(System.in);
        boolean flag=false;
        int gues=-1;
        int cap=0;
        try {
            System.out.println("Kimlik Numarası Giriniz : ");
            int id = reader.nextInt();
            for (int i=0; i<guests.size(); i++) {
                if( guests.get(i).getId()==id){
                    if(guests.get(i).getCapacity()!=0){
                        System.out.println("Bu Müşteri "+guests.get(i).getCapacity()+
                                " Kişilik Oda İçin Rezarvasyon Yaptırmıştır");
                        cap= guests.get(i).getCapacity();
                    }
                    gues=i;
                    flag=true;
                    break;
                }
            }
            if(!flag){
                System.out.println("Sistemde kaydınız yok Kayıt işlemi için");
                System.out.println("İsminizi Giriniz : ");
                String name = reader.next();
                guests.add(new Guest(name,id,0,-1));
                gues=guests.size()-1;
            }

            if(cap==0){
                System.out.println("Kaç Kişilik Oda İstiyorsunuz Lütfen Giriş Yapınız");
                cap = reader.nextInt();
            }

            flag = false;
            for(int i=0; i<rooms.size(); i++){

                if(cap==rooms.get(i).getCapacity() && rooms.get(i).getSituation()){
                    rooms.get(i).bookRoom();
                    guests.get(gues).setRoomNo(rooms.get(i).getRoomNo());
                    guests.get(gues).setCapacity(rooms.get(i).getCapacity());
                    System.out.println("Kayıt Başarı İle Oluşturuldu.");
                    System.out.println("Kayıt Bilgisi : \nİsim :"+guests.get(gues).getName()+
                        "\nKimlik No :"+guests.get(gues).getId()+"\nOda No :"+rooms.get(i).getRoomNo()
                    );
                    flag=true;
                    break;
                }
            }

            if (!flag)
                System.out.println("İsteğinize Uygun Boş Oda Bulanamadı");

        }catch(Exception e) {
            System.out.println("Exception caught: " + e);
        }
    }



    /**
     *  Reservation cancelling
     *  @param reader read the file or console
     */
    private void cancelReservation(Scanner reader) {
        //Scanner reader = new Scanner(System.in);
        boolean flag=false;
        try {
            System.out.println("Kimlik Numaranızı Giriniz : ");
            int id = reader.nextInt();
            for (int i=0; i<guests.size(); i++) {
                if( guests.get(i).getId()==id ){

                    if(guests.get(i).getCapacity()>0) {
                        guests.get(i).setCapacity(0);
                        System.out.println("\nBaşarı ile iptal edildi\n");
                    }else
                        System.out.println("Rezarvasonunuz Bulunmamaktadır");

                    flag=true;
                    break;
                }
            }
            if(!flag){
                System.out.println("Sistemde kaydınız bulunamadı\n\n");
            }


        }catch(Exception e) {
            System.out.println("Exception caught: " + e);
        }
    }

    /**
     *  Book The room
     *  @param reader read the file or console
     */
    private void bookRoom(Scanner reader) {

        //Scanner reader = new Scanner(System.in);
        boolean flag=false;
        int gues=-1;
        try {
            System.out.println("Kimlik Numarası Giriniz : ");
            int id = reader.nextInt();
            for (int i=0; i<guests.size(); i++) {
                if( guests.get(i).getId()==id){
                    gues=i;
                    flag=true;
                    break;
                }
            }
            if(!flag){
                System.out.println("Sistemde kaydınız yok Kayıt işlemi için");
                System.out.println("İsminizi Giriniz :");
                String name = reader.next();

                guests.add(new Guest(name,id,0,-1));
                gues=guests.size()-1;
            }

            System.out.println("Kaç Kişilik Oda İstiyorsunuz Lütfen Giriniz:");
            int roomCp = reader.nextInt();
            flag =false;/*Use for loop*/
            for (int i=0; i<this.rooms.size(); i++){

                if(rooms.get(i).getCapacity()==roomCp && rooms.get(i).getSituation() ){

                    guests.get(gues).setCapacity(roomCp);
                    System.out.println("\nRezarvasyon İşlemi Başarı İle Yapılmıştır.");
                    System.out.println("Odaya kesin kayıt işlemi Otele geldiğinizde sağlanacaktır\n");
                    flag=true;
                }
            }
            if(!flag)
                System.out.println("\nİsteğinize Uygun Boş Oda Bulanamadı\n");

        }catch(Exception e) {
            System.out.println("Exception caught: " + e);
        }

    }



    /**
     *  Load the all hotel data
     */
    private void loadHotelSystem() {
        try {

            loadRoomsData(this.roomFile);
            loadGuestsData(this.guestFile);
            loadReceptionistsData(this.receptionistFile);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  login the receptinists
     * @param reader read the file or console
     * @return the index of  receptinists data
     */
    private int loginReceptionist(Scanner reader){

        int num=-1;
        //Scanner reader = new Scanner(System.in);

        try {
            System.out.println("İsminizi Giriniz :");
            String name = reader.next();
            System.out.println("Şifrenizi Giriniz :");
            String pass = reader.next();

            for(int i=0; i<receptionists.size(); i++){

                if(name.equals(receptionists.get(i).getName()) && pass.equals(receptionists.get(i).getPassword())){
                    num=i;
                    System.out.println("\nGiriş Başarılı");
                    break;
                }
            }

        }catch(Exception e) {
            System.out.println("Exception caught: " + e);
        }

        return num;
    }

    /**
     *  Load the Room Data from file
     * @param fileName File name
     * @throws Exception IO Exception
     */
    private void loadRoomsData(String fileName)throws Exception{

        int roomNo,stuation,capacity;
        File input = new File(fileName);
        Scanner inpFile = new Scanner (input);
        inpFile.useDelimiter(",");

        while (inpFile.hasNext())
        {
            roomNo = Integer.valueOf(inpFile.next());
            capacity =Integer.valueOf(inpFile.next());
            stuation =Integer.valueOf(inpFile.next());

            rooms.add(new Room(roomNo,capacity,stuation));
        }
        inpFile.close();
    }

    /**
     *  Load the Guests Data from file
     * @param fileName File name
     * @throws Exception IO Exception
     */
    private void loadGuestsData(String fileName) throws Exception{

        int id,roomNo,cp;
        String name= new String();

        File input = new File(fileName);
        Scanner inpFile = new Scanner (input);
        inpFile.useDelimiter(",");

        while (inpFile.hasNext())
        {
            name = inpFile.next();
            id =  Integer.valueOf(inpFile.next());
            cp = Integer.valueOf(inpFile.next());
            roomNo = Integer.valueOf(inpFile.next());
            guests.add(new Guest(name,id,cp,roomNo));

        }
        inpFile.close();

    }

    /**
     *  Load the Receptionist Data from file
     * @param fileName File name
     * @throws Exception IO Exception
     */
    private void loadReceptionistsData(String fileName) throws Exception{

        File input = new File(fileName);
        Scanner inpFile = new Scanner (input);
        inpFile.useDelimiter(",");
        String name,pass;
        int id;
        while (inpFile.hasNext())
        {
            name = inpFile.next();
            id =  Integer.valueOf(inpFile.next());
            pass= inpFile.next();
            receptionists.add( new Receptionist(name,id,pass));
        }
        inpFile.close();

    }

    /**
     *  Write the all data to files
     * @throws IOException  If an input or output
     *                      exception occurred
     */
    private void saveData() throws Exception{

        File file = new File(this.receptionistFile);
        FileWriter out = new FileWriter(file);
        int i=0;
        while(i < receptionists.size())
        {
            out.write(receptionists.get(i).getName());
            out.write(",");
            out.write(Integer.toString(receptionists.get(i).getId()));
            out.write(",");
            out.write(receptionists.get(i).getPassword());
            out.write(",");
            out.flush();
            i++;
        }

        out.close();
        file = new File(guestFile);
        out = new FileWriter(file);
        i=0;
        while (i<guests.size()){
            out.write(guests.get(i).getName());
            out.write(",");
            out.write(Integer.toString(guests.get(i).getId()));
            out.write(",");
            out.write(Integer.toString(guests.get(i).getCapacity()));
            out.write(",");
            out.write(Integer.toString(guests.get(i).getRoomNo()));
            out.write(",");
            out.flush();
            i++;
        }
        out.close();
        file = new File(roomFile);
        out = new FileWriter(file);
        i=0;
        while (i<rooms.size()){

            out.write(Integer.toString(rooms.get(i).getRoomNo()));
            out.write(",");
            out.write(Integer.toString(rooms.get(i).getCapacity()));
            out.write(",");
            if(rooms.get(i).getSituation())
                out.write(Integer.toString(0));
            else
                out.write(Integer.toString(1));
            out.write(",");
            out.flush();
            i++;
        }
        out.close();
    }

}
