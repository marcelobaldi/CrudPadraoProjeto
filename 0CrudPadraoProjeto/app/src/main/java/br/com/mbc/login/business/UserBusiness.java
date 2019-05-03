package br.com.mbc.login.business;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import java.util.List;

import br.com.mbc.login.model.User;
import br.com.mbc.login.repository.UserRepository;

public class UserBusiness {
    private UserRepository userRepository;

    public UserBusiness(){
        userRepository = new UserRepository();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User update(User user){
        return userRepository.update(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public Boolean isUserFinded(User user){
        User userReturn = userRepository.findByEmail(user.getEmail());
        return (userReturn != null);
    }

    public User validateLogin (User user) {
        User userReturn = userRepository.findByEmail(user.getEmail());
        if (userReturn != null ) {
            if(userReturn.getEmail().equals(user.getEmail()) && userReturn.getPasswd().equals(user.getPasswd())){
                return userReturn;
            } else { return null; }
        } else { return null; }
    }

    public User findbyEmail (User user){
        if(userRepository.findByEmail(user.getEmail()) != null){
            return userRepository.findByEmail(user.getEmail());
        } else {
            return null;
        }
    }

    public List<User> listAll(){
        return userRepository.listAll();
    }

    public int listSize(){
        return userRepository.listSize();
    }

    public void printMessage(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    public void goView(Activity activity, Class tela){
       activity.startActivity(new Intent(activity, tela));
    }
}