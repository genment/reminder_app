package tl2h.reminder.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import static tl2h.reminder.BuildConfig.VERSION_CODE;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "alarm.db";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // alarm table
        db.execSQL("CREATE TABLE alarm (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, minutes INT, lastAlarm INT, enable INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
