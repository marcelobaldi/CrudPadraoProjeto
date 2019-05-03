package br.com.mbc.login.repository;
import java.util.ArrayList;
import java.util.List;
import br.com.mbc.login.model.User;

public class UserRepository {
    private List<User> users;

    public UserRepository(){
        users = new ArrayList<>();
        users.add(new User(1,"marcelo@ig.com.br", "1" ));
        users.add(new User(2,"marcos@ig.com.br", "2" ));
        users.add(new User(3,"jose@ig.com.br", "3" ));
    }

    public User save(User user) {
        user.setId(users.size() + 1);
        users.add(user);
        return user;
    }

    public User update(User user){
        return users.set(user.getId()-1, user);
    }

    public void delete(User user){
        users.remove(user.getId()-1);
    }

    public User findById(Integer id){
        int position = id - 1;
        return users.get(position);
    }

    public User findByEmail(String email){
        for (User user : users){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public List<User> listAll(){
        return users;
    }

    public int listSize(){
        return users.size();
    }
}

