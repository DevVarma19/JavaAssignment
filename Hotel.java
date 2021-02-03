import java.util.*;

class Hotel{
    String name;
    String location;
    int rating;
    int price;

    void rooms(){
        System.out.println("Rooms available are : ");
        System.out.println("\n1.Spl Suite\n2.Normal");
    }

}

class Rooms extends Hotel{
    int days;
    String cname;
    int age;
    int totprice;

    Rooms(String n, String l, int r, int p, int d, String cn, int a, int tp){
        name = n;
        location = l;
        rating = r;
        price = p;
        days = d;
        price = p;
        days = d;
        cname = cn;
        age = a;
        totprice = tp;
    }

    static void view(){
        System.out.println("-------VIEWING ALL THE HOTELS-----------");
        System.out.println("NAME     \t\tRATING\t\t\tLOCATION\t\t\tSuite\t\t\tNormal");
        for(String[] S: hotels){
            System.out.println(S[0] + "\t\t\t" + S[1] + "\t\t" + S[2] + "\t\t" + S[3] + "\t\t" + S[4]);
        }   
    }
    static void Rating(){
        System.out.println("--------VIEWING HOTELS BASED ON THE RATING-----------");
        int maxr = Integer.parseInt(hotels[0][1]);
        int index = 0;
        int count = 0;
        for(String[] S: hotels){
            if(maxr < Integer.parseInt(S[1]))
            {
                maxr = Integer.parseInt(S[1]);
                index = count;
            }
            count++;
        }

        System.out.println("This hotel has the best rating in the town : " + hotels[index][1]);
    }
    static void Location(){
        System.out.println("---------------VIEWING HOTELS BASED ON LOCATION------------");
        System.out.println("Enter the location : ");
        String loc = sc.next();

        for(String[] S: hotels){
            if(S[3].equals(loc))
            {
                System.out.println(S[0] + "\t\t" + S[1]);
            }
        }
    }
    static String getloc(String n){
        for(String[] S : hotels)
        {
            if(S[0].equals(n))
            {
                return S[2];
            }
        }
        return "";
    }
    static int getrating(String n){
        for(String[] S : hotels)
        {
            if(S[0].equals(n)){
                int r = Integer.parseInt(S[1]);
                return r;
            }
        }
        return 0;
    }
    static int getprice(String n, int r){
        for(String[] S : hotels)
        {
            if(S[0].equals(n))
            {
                if(r == 1)
                {
                    return Integer.parseInt(S[4]);
                }
                else if(r == 2)
                {
                    return Integer.parseInt(S[3]);
                }
            }
        }
        return 0;
    }

    static String[][] hotels = {{"Novotel", "4", "BeachRoad", "2000", "10000"}, 
                            {"Dolphin", "4", "Jagadamba","1000", "5000"},
                            {"Green Park", "3", "Valtair Jn","5000", "6000"},
                            {"Daba Gardens", "2", "BeachRoad", "1000", "3000"}
                        };

    static ArrayList<Rooms> RoomList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("\t\t\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("\t\t\t@@@@@@@@@@@@@@@@@@HOTEL MANAGEMENT SYSTEM@@@@@@@@@@@@@@@@@");
        System.out.println("\t\t\t@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        System.out.println("\t\tWelcome");  
        while(true)
        {
        System.out.println("\n\t1. Book Hotel\n\t2.View Hotels\n\t\t3.Exit");
        int c = sc.nextInt();

        if(c == 1){
            System.out.println("-----------------------BOOKING--------------------------");
            System.out.println("Enter your age : ");
            int age = sc.nextInt();
            System.out.println("Enter your name: ");
            String name = sc.next();
            view();
            System.out.println("Enter the hotel you want to stay");
            String hname = sc.next();
            System.out.println("Enter the type of room you want\n1.Spl Suite\n2. Normal");
            int rtype = sc.nextInt();
            System.out.println("Enter the number of days : ");
            int days = sc.nextInt();

            String loc = getloc(hname);
            int r   = getrating(hname);
            int price = getprice(hname, rtype);
            int tot = price*days;

            Rooms R = new Rooms(hname, loc, r, price, days, name, age, tot);
            RoomList.add(R);

            System.out.println("--------------------Your Booking was Successful----------------------------");
            System.out.println("Total Cost : " + Integer.toString(tot));

        }
        else if(c == 2){
            System.out.println("Do you want view the hotels Based on filters[Y/N]");
            String s = sc.next();
            
            if(s.toLowerCase().equals("y")){
                System.out.println("\t1.Rating\t2.Location");
                int c1 = sc.nextInt();
                if(c1 == 1){
                    Rating();
                } else if(c1 == 2)
                {
                    Location();
                }
            }
            view();
        }
        else {
            break;
        }
    }
}

}

