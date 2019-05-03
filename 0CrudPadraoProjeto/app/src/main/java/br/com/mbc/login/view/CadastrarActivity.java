package br.com.mbc.login.view;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.mbc.login.R;
import br.com.mbc.login.business.UserBusiness;
import br.com.mbc.login.model.User;

public class CadastrarActivity extends AppCompatActivity {
    private EditText        edtEmail, edtPasswd;
    private User            user;
    private UserBusiness    userBusiness;

    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        init();
    }

    public void buttonSave(View view){
        User user = getUser();
        if(user != null) {
            if (userBusiness.isUserFinded(user)) {
                Toast.makeText(this, "Este Usuário Já Existe", Toast.LENGTH_SHORT).show();
            } else {
                userBusiness.save(user);
                User userCadastrado = userBusiness.findbyEmail(user);
                Toast.makeText(this, userCadastrado.getId() + " " + userCadastrado.getEmail(), Toast.LENGTH_LONG).show();
                userBusiness.goView(this, ListActivity.class);
            }
        }
    }

    private void init() {
        edtEmail    = findViewById(R.id.edtEmail);
        edtPasswd   = findViewById(R.id.edtPasswd);
        user = new User();
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
}
