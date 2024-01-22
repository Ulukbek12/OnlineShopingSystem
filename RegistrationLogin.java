package OnlineShopingSystem;

//class RegistrationLogin {
//    private static final String USER_DATA_FILE = "userdata.txt";
//    protected static  boolean isAvailable = false;
//
//    //The main goal of this method is to convert file data to userData (hashMap)
//    private static Map<String,String> readUserData(){
//        Map<String,String> userData = new HashMap<>();
//        try(Scanner fileLine = new Scanner(new File(USER_DATA_FILE))){
//            while(fileLine.hasNext()){
//                String line = fileLine.nextLine();
//                String[] parts = line.split(",");
//                if(parts.length == 2){
//                    userData.put(parts[0],parts[1]);
//                }
//            }
//        }
//        catch (IOException e){
//
//        }
//        return userData;
//    }
//    private static void writeUserData(Map<String,String> userData){//opposite it gets all userData and pushes it to file
//        try(FileWriter fileWriter = new FileWriter(USER_DATA_FILE)){
//            for(Map.Entry<String,String> entry : userData.entrySet()){
//                fileWriter.write(entry.getKey() + "," + entry.getValue());
//            }
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//    static void login(Scanner scanner) {
//        System.out.println("Enter username: ");
//        String username = scanner.nextLine();
//        System.out.println("Enter password: ");
//        String password = scanner.nextLine();
//        Map<String,String> userData = readUserData();
//        if(userData.containsKey(username) && userData.get(username).equals(password)) {
//            System.out.println("Login successful. Welcome, " + username + "!");
//        } else {
//            System.out.println("Login failed. Invalid username or password.");
//        }
//    }
//    static void register(Scanner scanner){
//        System.out.println("Enter new username: ");
//        String username = scanner.nextLine();
//        System.out.println("Enter new password: ");
//        String password = scanner.nextLine();
//        Map<String,String> userData = readUserData();
//
//        if(!userData.containsKey(username)){
//            userData.put(username,password);
//            writeUserData(userData);
//            System.out.println("Registration successful. You can now log in with your new account.");
//        } else {
//            System.out.println("Registration failed. Username already exists.");
//        }
//        }
//    }




