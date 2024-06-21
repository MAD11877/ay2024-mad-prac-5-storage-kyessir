package sg.edu.np.mad.madpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.annotation.SuppressLint;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Random;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;
    private static final String USERS = "User";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESC = "description";
    private static final String COLUMN_FOLL = "followed";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("Database Operations", "Creating a Table.");

        String createTableSQL = "CREATE TABLE " + USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_FOLL + " INTEGER)";
        db.execSQL(createTableSQL);

        for (int i = 0; i < 20; i++) {
            Random r = new Random();
            int name = new Random().nextInt(9999999);;
            int description = new Random().nextInt(9999999);;
            boolean followed = r.nextBoolean();
            ContentValues values = new ContentValues();

            values.put(COLUMN_NAME, "Name" + name);
            values.put(COLUMN_DESC, "Description" + description);
            values.put(COLUMN_FOLL, String.valueOf(followed));
            db.insert(USERS, null, values);
        }
        Log.i("Database Operations", "Table created successfully.");
    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }

    @SuppressLint("Range")
    public User getUser(String username) {
        SQLiteDatabase db = getReadableDatabase();
        User user = null;
        String query = "SELECT * FROM " + USERS + " WHERE " + COLUMN_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor.moveToFirst()) {
            user = new User();
            user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            user.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESC)));
            user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            user.setFollowed(cursor.getInt(cursor.getColumnIndex(COLUMN_FOLL)) == 1);
        }
        cursor.close();
        return user;
    }

//  Add a user record

    public User getUser(int user_id) {
        SQLiteDatabase db = getReadableDatabase();
        User user = new User("John Doe", "MAD Developer", 1, false);
        Cursor cursor = db.query(USERS, new String[] { COLUMN_NAME, COLUMN_DESC, COLUMN_ID, COLUMN_FOLL}, COLUMN_ID + "=?",
                new String[] { String.valueOf(user_id) }, null, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
            int id = cursor.getInt((int)cursor.getColumnIndex("id"));
            String name = cursor.getString((int)cursor.getColumnIndex("name"));
            String description = cursor.getString((int)cursor.getColumnIndex("description"));
            boolean followed = Boolean.parseBoolean(cursor.getString((int)cursor.getColumnIndex("followed")));
            user.setId(id);
            user.setName(name);
            user.setDescription(description);
            user.setFollowed(followed);
            cursor.close();
        }
        return user;
    }

    public ArrayList<User> getUsers() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + USERS;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt((int)cursor.getColumnIndex("id"));
            String name = cursor.getString((int)cursor.getColumnIndex("name"));
            String description = cursor.getString((int)cursor.getColumnIndex("description"));
            String  followed = cursor.getString((int)cursor.getColumnIndex("followed"));
            User user = new User(name, description, id, Boolean.parseBoolean(followed));
            userList.add(user);
        }
        cursor.close();
        return userList;
    }

    public void updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOLL,  user.getFollowed());
        String clause = "id=?";
        String[] args = {String.valueOf(user.getId())};
        db.update(USERS, values, clause, args);
    }

    public void close() {
        Log.i("Database Operations", "Database is closed.");
        super.close();
    }
}
