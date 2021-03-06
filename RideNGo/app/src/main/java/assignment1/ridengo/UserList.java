package assignment1.ridengo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mingjun on 10/11/2016.
 */
public class UserList {

    private static ArrayList<User> userList = null;
//    private static final long serialVersionUID = 6673446047991058932L;
    protected transient ArrayList<Listener> listeners = null;

    public UserList() {
        userList = new ArrayList<User>();
        listeners = new ArrayList<Listener>();
    }

    public boolean contains(User testUser) {
        for(User user : userList){
            if(user.getUsername().equals(testUser.getUsername())){
                return true;
            }
        }
        return false;
    }

    public boolean contains(String username) {
        for(User user : userList){
            if(username.equals(user.getUsername())){
                return true;
            }
        }
        return false;
    }

    public void addListener(Listener l) {
        getListeners().add(l);
        for(User user : this.getUsers()){
            user.addListener(l);
        }
    }

    public void removeListener(Listener l) {
        getListeners().remove(l);
        for(User user : this.getUsers()){
            user.removeListener(l);
        }
    }

    private ArrayList<Listener> getListeners() {
        if (listeners == null ) {
            listeners = new ArrayList<Listener>();
        }
        return listeners;
    }

    public ArrayList<User> getUsers() {
        return userList;
    }

    public void addUser(User testUser) throws RuntimeException{
        if(!this.contains(testUser)){
            userList.add(testUser);
        }else{
            throw new RuntimeException("User Already Exists.");
        }
        notifyListeners();
    }

    public User getUserByUsername(String name) {
        for(User user : userList){
            if(user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }

    public void clear(){
        userList.clear();
        notifyListeners();
    }

    private void notifyListeners() {
        for (Listener  listener : getListeners()) {
            listener.update();
        }
    }
}
