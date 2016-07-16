package in.technicus.studypro;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.technicus.studypro.Services.DbConnection;
import in.technicus.studypro.Services.ListAdapter;
import in.technicus.studypro.Services.ListPojo;

public class ViewAgenda extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    private Button addAgenda;
    private List<ListPojo> listP;
    private ListView agendalist;
    private ArrayList<ListPojo> list = new ArrayList<>();
    String agendaTitle;
    String agendImportance;
    ProgressDialog pd;
    ListAdapter listAp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_agenda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DbConnection DbConnection = new DbConnection(this);
        Integer c=DbConnection.getCountInDB();

        agendalist=(ListView) findViewById(R.id.agendalist);
        agendalist.setOnItemLongClickListener(this);
        listP=new ArrayList<ListPojo>();
        listAp=new ListAdapter(getApplicationContext(),R.layout.custom_list,listP);
        listP.removeAll(list);
        List<ListPojo> agendaList = DbConnection.getAllagenda();
        for (ListPojo dl : agendaList) {
            ListPojo lpjo = new ListPojo(dl.getAgendaTitle(),dl.getAgendaImportance());
            listP.add(lpjo);
        }
        agendalist.setAdapter(listAp);
        addAgenda=(Button) findViewById(R.id.addAgenda);
        addAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myInt=new Intent(getApplicationContext(),AddAgenda.class);
                startActivity(myInt);
            }
        });
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int pos, long l) {
        System.out.println(adapterView.getItemAtPosition(pos));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // TODO: 16/07/2016 Remove Item form listView and From Database 
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        return true;
    }
}
