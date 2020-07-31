package com.htp.zhomework;

import java.util.ArrayList;
import java.util.List;

public class HomeWork {

    private static int newCount;

    private static List<UserUser> userGenerator(int count) {
        UserUser userUser;
        List<UserUser> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newCount++;
            userUser = UserUser.builder()
                    .id(Integer.toUnsignedLong(newCount))
                    .name("name " + newCount)
                    .surname("surname " + newCount)
                    .build();
            users.add(userUser);
        }
        return users;
    }

    private static List<UserUser> userGenerator2(int count) {
        UserUser userUser;
        List<UserUser> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newCount++;
            userUser = UserUser.builder()
                    .id(Integer.toUnsignedLong(newCount))
                    .name("name " + newCount)
                    .surname("surname " + newCount)
                    .friends(userGenerator(count))
                    .build();
            users.add(userUser);
        }
        return users;
    }

    private static List<UserUser> userGenerator3(int count) {
        UserUser userUser;
        List<UserUser> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newCount++;
            userUser = UserUser.builder()
                    .id(Integer.toUnsignedLong(newCount))
                    .name("name " + newCount)
                    .surname("surname " + newCount)
                    .friends(userGenerator2(count))
                    .build();
            users.add(userUser);
        }
        return users;
    }

    private static List<UserUser> userGenerator4(int count) {
        UserUser userUser;
        List<UserUser> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            newCount++;
            userUser = UserUser.builder()
                    .id(Integer.toUnsignedLong(newCount))
                    .name("name " + newCount)
                    .surname("surname " + newCount)
                    .friends(userGenerator3(count))
                    .build();
            users.add(userUser);
        }
        return users;
    }


    public static void main(String[] args) {
        findAllFriends2(userGenerator4(2),1);
    }

    private static void findAllFriends2(List<UserUser> userUsers, int lvl) {
        for (UserUser userUser : userUsers) {
            String name = userUser.getName();
            List<UserUser> friends = userUser.getFriends();
            System.out.print(" " + name);
            if (lvl!=0 && friends != null) {
                System.out.print(" friends = {");
                System.out.println();
            } else {
                System.out.print(" = { end lvl }");
                continue;
            }
            findAllFriends2(friends, --lvl);
            System.out.print("}");
            System.out.println();
            ++lvl;
        }
    }


}
