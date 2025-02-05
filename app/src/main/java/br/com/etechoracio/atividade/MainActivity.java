package br.com.etechoracio.atividade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

public class MainActivity extends AppCompatActivity implements CustomDialog.ItemListener,
        AdapterView.OnItemLongClickListener,
        PopupMenu.OnMenuItemClickListener{

    private boolean insertMode;
    private ItemAdapter adapter;
    private ListView listView;

    private String selectedItemName;
    private int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ItemAdapter(this);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItem(String name) {
        if (insertMode) {
            adapter.insertItem(name);
        } else {
            adapter.updateItem(selectedItem, name);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.menu,menu);

    return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu)
        {
            CustomDialog dialog = new CustomDialog(this);
            dialog.show(getFragmentManager(), "CustomDialog");

            insertMode = true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        PopupMenu popup = new PopupMenu(  this, view);
        popup.inflate(R.menu.menu2);
        popup.setOnMenuItemClickListener(this);
        popup.show();
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.Excluir) {

            adapter.removeItem(selectedItem);

            insertMode = false;
            return true;
        }
        else if(menuItem.getItemId() == R.id.Editar){

            CustomDialog dialog = new CustomDialog(this);
            dialog.show(getFragmentManager(), "Editar");

            insertMode = false;
            return true;
        }
        else{
            return super.onOptionsItemSelected(menuItem);
        }
    }
}
