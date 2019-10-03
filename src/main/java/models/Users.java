package models;

import java.util.Objects;

public class Users {
    private String userName;
    private String userPosition;
    private String userRole;
//    private int departId;
    private int id;


    public Users(String userName,String userPosition,String userRole){
        this.userName=userName;
        this.userPosition=userPosition;
        this.userRole=userRole;
//        this.departId=departId;
    }

    public String getUserName(){
        return userName;
    }

//    public void setName(String name) {
//        this.userName = userName;
//    }
    public String getUserPosition(){
        return userPosition;
    }

//    public void setUserPosition(String userPosition) {
//        this.userPosition = userPosition;
//    }

    public String  getUserRole(){
        return userRole;
    }

//    public void setRole(String role) {
//        this.userRole = userRole;
//    }
    public int getId(){
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return Objects.equals(userName, users.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName,userPosition,userRole,id);
    }
}

