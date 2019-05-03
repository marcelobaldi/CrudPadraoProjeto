package br.com.mbc.login.view;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.mbc.login.R;
import br.com.mbc.login.business.UserBusiness;
import br.com.mbc.login.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText     edtEmail, edtPasswd;
    private UserBusiness userBusiness;

    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void buttonLogin(View view) {
        if (getUser() != null){
            User user = getUser();
            if (isUserFinded(user)) { login(user);
            } else { userBusiness.printMessage(this, "Usuário Não Existe"); }
        }
    }

    public void buttonRegister(View view){
        userBusiness.goView(this,CadastrarActivity.class );
    }

    private void init() {
        edtEmail  = findViewById(R.id.edtEmail);
        edtPasswd = findViewById(R.id.edtPasswd);
        userBusiness = new UserBusiness();
    }

    public User getUser(){
        String email  = edtEmail.getText().toString();
        String passwd = edtPasswd.getText().toString();
        if (email.equals("") || passwd.equals("")) {
            userBusiness.printMessage(this,"Os Campos Não Podem Ser Vazios");
            return null;
        } else { return new User(email, passwd); }
    }

    private boolean isUserFinded(User user) {
        return (userBusiness.isUserFinded(user));
    }

    private void login(User user) {
       if(userBusiness.validateLogin(user) != null){
           userBusiness.goView(this, ListActivity.class);
           userBusiness.printMessage(this, "Id: " + String.valueOf(userBusiness.validateLogin(user).getId()));
       } else { userBusiness.printMessage(this,"Senha Não Confere"); }
    }
}

