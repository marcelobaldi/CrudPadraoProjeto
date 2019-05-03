package br.com.mbc.login.view;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.mbc.login.R;
import br.com.mbc.login.business.UserBusiness;
import br.com.mbc.login.model.User;

public class ItemActivity extends AppCompatActivity {
    private EditText edtId, edtEmail, edtPasswd;
    private UserBusiness userBusiness;
    private User userSelecionado;
    private final static String itemLinha = "itemLinha";

    @Override protected void onCreate(Bundle savedInstanceState){super.onCreate(savedInstanceState);
        init();
    }

    public void buttonUpdate(View view){
        User userUpdate = getUser();
        userBusiness.update(userUpdate);

        User userUpdateFinded = userBusiness.findbyEmail(userUpdate);
        Toast.makeText(this, userUpdate.getEmail() + " " + userUpdateFinded.getPasswd(), Toast.LENGTH_LONG).show();
        finish();
    }

    public void buttonDelete(View view){
        userBusiness.delete(userSelecionado);
        Toast.makeText(this, "Tam. Lista: " + String.valueOf(userBusiness.listSize()), Toast.LENGTH_SHORT).show();
        finish();
    }

    public void init(){
        setContentView(R.layout.activity_item);

        userSelecionado = getIntent().getParcelableExtra(itemLinha);
        userBusiness = new UserBusiness();
        Toast.makeText(this, userSelecionado.getId() + " " + userSelecionado.getEmail(), Toast.LENGTH_LONG).show();

        edtId     = findViewById(R.id.edtId);
        edtEmail  = findViewById(R.id.edtEmail);
        edtPasswd = findViewById(R.id.edtPasswd);

        edtId.setText(String.valueOf(userSelecionado.getId()));
        edtEmail.setText(userSelecionado.getEmail());
        edtPasswd.setText(userSelecionado.getPasswd());
    }

    public User getUser (){
        String id = edtId.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtPasswd.getText().toString();
        return new User(Integer.parseInt(id), email, senha);
    }
}
