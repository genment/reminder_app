package tl2h.reminder.adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import tl2h.reminder.R;
import tl2h.reminder.db.DbOp;
import tl2h.reminder.object.AlarmObject;

import java.util.Calendar;
import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter {

    private DbOp db;
    private List<AlarmObject> data;

    public ListViewAdapter(Context context) {
        this.db = new DbOp(context);
        this.data = db.getAll();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder_list, parent, false);
        return new ListItemViewHolder(v, db);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListItemViewHolder vh = (ListItemViewHolder) holder;
        AlarmObject alarmObject = data.get(position);
        vh.getTitle().setText(alarmObject.getTitle());
//        Log.e("TIME", alarmObject.getLastAlarm() + "， " + alarmObject.getMinutes() + ", " + Calendar.getInstance().getTime().getTime()/1000);
        vh.getMinutes().setText("Next time: in " +  (alarmObject.getLastAlarm() + alarmObject.getMinutes()*60 - Calendar.getInstance().getTime().getTime()/1000)/60  + " min");
        vh.getEnable().setChecked(alarmObject.isEnable());
        vh.setId(alarmObject.getId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
