package tl2h.reminder.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import tl2h.reminder.object.AlarmObject;

import java.util.ArrayList;
import java.util.List;

public class DbOp {
    private DbHelper mHelper;

    public DbOp(Context context) {
        mHelper = new DbHelper(context);
    }

    /**
     * close
     */
    public void close() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    /**
     * add
     */
    public void addAlarm(AlarmObject ao) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("INSERT INTO alarm(title, minutes, lastAlarm, enable) VALUES( ?, ?, ?, 1)",
                new Object[]{
                        ao.getTitle(),
                        ao.getLastAlarm(),
                        ao.getMinutes()
                });
        db.close();
    }

    /**
     * update
     */
    public void updateAlarm(AlarmObject alarmObject) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("UPDATE alarm SET title=?, minutes=?, lastAlarm=?, enable=? WHERE _id = ?",
                new Object[]{
                        alarmObject.getTitle(),
                        alarmObject.getMinutes(),
                        alarmObject.getLastAlarm(),
                        alarmObject.isEnable() ? 1 : 0,
                        alarmObject.getId()
                });
        db.close();
    }

    /**
     * delete
     */
    public void removeAlarm(int id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("DELETE FROM alarm WHERE _id = ?",
                new Integer[]{
                        id
                });
        db.close();
    }

    /**
     * clear
     */
    public void clearAllAlarm() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("DELETE FROM alarm");
        db.close();
    }

    /**
     * update minutes
     */
    public void updateEnable(int id, boolean enable) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("UPDATE alarm SET enable=? WHERE _id = ?", new Integer[]{enable ? 1 : 0, id});
        db.close();
    }

    /**
     * update minutes
     */
    public void updateMinutes(int id, int minutes) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("UPDATE alarm SET minutes=? WHERE _id = ?", new Integer[]{minutes, id});
        db.close();
    }

    /**
     * update lastAlarm
     */
    public void updateLastAlarm(int id, int minutes) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("UPDATE alarm SET lastAlarm=date('now') WHERE _id = ?", new Integer[]{id});
        db.close();
    }

    /**
     * get one
     */
    public AlarmObject getOne(int id) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM alarm WHERE _id = ?",
                new String[]{String.valueOf(id)});
        AlarmObject alarmObject = null;
        if (cursor != null && cursor.moveToFirst()) {
            alarmObject = fillObjectEntity(cursor, true);
        }
        db.close();
        return alarmObject;
    }

    /**
     * get latest
     */
    public AlarmObject getLastOne() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM alarm order by _id DESC limit 1", null);
        AlarmObject alarmObject = null;
        if (cursor != null && cursor.moveToFirst()) {
            alarmObject = fillObjectEntity(cursor, true);
        }
        db.close();
        return alarmObject;
    }

    /**
     * get all
     */
    public List<AlarmObject> getAll() {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM alarm order by _id DESC", null);
        List<AlarmObject> datalist = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                datalist.add(fillObjectEntity(cursor, false));
            }
            cursor.close();
        }
        db.close();
        return datalist;
    }

    /**
     * is enable
     */
    public boolean isEnable(int id) {
        boolean isEnable = false;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT enable FROM alarm WHERE id=? ", new String[]{Integer.valueOf(id).toString()});
        if (cursor != null && cursor.moveToFirst()) {
            isEnable = cursor.getInt(0) == 1;
            cursor.close();
        }
        db.close();
        return isEnable;
    }

    private AlarmObject fillObjectEntity(Cursor cursor, boolean closeCursor) {
        AlarmObject entity = new AlarmObject();
        entity.setId(cursor.getInt(0));
        entity.setTitle(cursor.getString(1));
        entity.setMinutes(cursor.getInt(3));
        entity.setLastAlarm(cursor.getInt(2));
        entity.setEnable(cursor.getInt(4) == 1);

        if (closeCursor)
            cursor.close();
        return entity;
    }
}
