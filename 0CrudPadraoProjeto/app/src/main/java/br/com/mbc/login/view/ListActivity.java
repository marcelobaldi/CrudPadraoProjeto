package br.com.mbc.login.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import br.com.mbc.login.business.UserBusiness;
import br.com.mbc.login.R;
import br.com.mbc.login.model.User;

public class ListActivity extends AppCompatActivity {
    private ListView            listObjet;
    private List<User>          listValues;
    private ArrayAdapter<User>  listAdapter;
    private User                user;
    private UserBusiness        userBusiness;
    private final static String itemLinha = "itemLinha";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        loadObjects();
        loadList();
    }

    private void loadObjects() {
        listObjet = findViewById(R.id.ListaXml);
        user = new User();
    }

    public void loadList(){
        userBusiness = new UserBusiness();
        listValues    = userBusiness.listAll();
        listAdapter   = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listValues);
        listObjet.setAdapter(listAdapter);

        listObjet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getBaseContext(), ItemActivity.class);
                intent.putExtra(itemLinha, listValues.get(i));
            startActivity(intent);
        }});
    }
}
