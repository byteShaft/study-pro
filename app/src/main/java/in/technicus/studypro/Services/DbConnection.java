package in.technicus.studypro.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubham on 6/7/16.
 */
public class DbConnection  extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "studyPro";
    private static final String TABLE_NAME = "agenda";

    private static final String CREATE_TABLE_AGENDA = "CREATE TABLE "
            + TABLE_NAME + "("
            + "agendaID" + " INTEGER PRIMARY KEY ,"
            + "agendaTitle" + " TEXT,"
            + "agendaImportance" + " TEXT,"
            + "agendaStatus" + " TEXT)";


    public DbConnection(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_AGENDA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertAgenda(ListPojo listPojo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("agendaTitle", listPojo.getAgendaTitle());
        values.put("agendaImportance", listPojo.getAgendaImportance());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public List<ListPojo> getAllagenda(){
        List<ListPojo> listPojos = new ArrayList<ListPojo>();
        String selectQuery = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ListPojo lp = new ListPojo();
//                Log.e("getAllagenda: ", cursor.getString(1)+cursor.getString(2));
                Log.e("TAG", cursor.getString(1) + " " + cursor.getString(2) + " id " + cursor.getInt(0));
                lp.setAgendaId("" + cursor.getInt(0));
                lp.setAgendaTitle("Agenda Title: "+cursor.getString(1));
                lp.setAgendaImportance("Priority: "+cursor.getString(2));
                listPojos.add(lp);
            }while (cursor.moveToNext());
        }
        return listPojos;
    }
    public int getCountInDB()
    {
        int cnt=0;
        String countQ="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery(countQ,null);
        cnt=cur.getCount();
        cur.close();
        return cnt;
    }

    public boolean deleteAgenda(Integer id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, "agendaID" + "=" + id, null) > 0;
    }
}
