package tl2h.reminder.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;
import tl2h.reminder.R;
import tl2h.reminder.db.DbOp;

public class ListItemViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener, View.OnLongClickListener {
    private final Switch enable;
    private final TextView title;
    private final TextView minutes;
    private int id;
    private DbOp db;

    public ListItemViewHolder(@NonNull final View itemView, @NonNull final DbOp db)  {
        super(itemView);
        this.db = db;
        enable = itemView.findViewById(R.id.enable);
        title = itemView.findViewById(R.id.action_name);
        minutes = itemView.findViewById(R.id.minutes);
        enable.setOnCheckedChangeListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Switch getEnable() {
        return enable;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getMinutes() {
        return minutes;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        db.updateEnable(this.id, isChecked);
    }

    @Override
    public boolean onLongClick(View v) {
        new AlertDialog.Builder(v.getContext())
                .setTitle("sure?")
                .setPositiveButton("delect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.removeAlarm(id);
                    }
                })
                .setNegativeButton("cancel", null)
                .show();
        return true;
    }
}
