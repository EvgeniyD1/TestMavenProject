package com.htp.zhomework;

import java.util.ArrayList;
import java.util.List;

public class HomeWork {

    private static int newCount;
    private static List<UserUser> firstUser = new ArrayList<>();
    private static List<UserUser> friendsLvl1 = new ArrayList<>();
    private static List<UserUser> friendsLvl2 = new ArrayList<>();
    private static List<UserUser> friendsLvl3 = new ArrayList<>();
    private static List<UserUser> friendsLvl4 = new ArrayList<>();

    private static void userFriends(int count) {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < count; j++) {
                for (int k = 0; k < count - 1; k++) {
                    for (int l = 0; l < count - 1; l++) {
                        for (int m = 0; m < count - 1; m++) {
                            UserUser newUserM = new UserUser();
                            newUserM.setId(Integer.toUnsignedLong(newCount));
                            newUserM.setName("name " + newCount);
                            newUserM.setSurname("surname " + newCount);
                            newUserM.setFriends(null);
                            friendsLvl4.add(newUserM);
                            newCount++;
                        }
                        UserUser newUserL = new UserUser();
                        newUserL.setId(Integer.toUnsignedLong(newCount));
                        newUserL.setName("name " + newCount);
                        newUserL.setSurname("surname " + newCount);
                        newUserL.setFriends(friendsLvl4);
                        friendsLvl3.add(newUserL);
                        newCount++;
                    }
                    UserUser newUserK = new UserUser();
                    newUserK.setId(Integer.toUnsignedLong(newCount));
                    newUserK.setName("name " + newCount);
                    newUserK.setSurname("surname " + newCount);
                    newUserK.setFriends(friendsLvl3);
                    friendsLvl2.add(newUserK);
                    newCount++;
                }
                UserUser newUserJ = new UserUser();
                newUserJ.setId(Integer.toUnsignedLong(newCount));
                newUserJ.setName("name " + newCount);
                newUserJ.setSurname("surname " + newCount);
                newUserJ.setFriends(friendsLvl2);
                friendsLvl1.add(newUserJ);
                newCount++;
            }
            UserUser newUserI = new UserUser();
            newUserI.setId(Integer.toUnsignedLong(newCount));
            newUserI.setName("name " + newCount);
            newUserI.setSurname("surname " + newCount);
            newUserI.setFriends(friendsLvl1);
            firstUser.add(newUserI);
            newCount++;
        }
    }

    public static void main(String[] args) {
        userFriends(2);
        for (UserUser userUser : firstUser) {
            System.out.println(userUser);
        }
        findAllFriends(firstUser);
    }

    private static void findAllFriends(List<UserUser> userUsers) {
        for (UserUser userUser : userUsers) {
            String name = userUser.getName();
            System.out.print(" friends = {" + name+", ");
            List<UserUser> friends = userUser.getFriends();
            if (friends==null){
                System.out.print("not friends},");
                continue;
            }
            findAllFriends(friends);
            System.out.print("}");
        }
    }


//    private static void userFriendsRec(int count){
//        List<UserUser> userFriendRec = new ArrayList<>();
//        List<UserUser> userFriendRec2 = new ArrayList<>();
//        UserUser newUser = new UserUser();
//        newUser.setId(Integer.toUnsignedLong(count));
//        newUser.setName("name "+count);
//        newUser.setSurname("surname "+count);
//        newUser.setFriends(userFriendRec);
//        userFriendRec2.add(newUser);
//        if (count!=1){
//            userFriendsRec(count-1);
//        }
//    }


}
