package tl2h.reminder.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import tl2h.reminder.R;
import tl2h.reminder.db.DbOp;
import tl2h.reminder.object.AlarmObject;

import java.util.Calendar;

public class AddAlarmFlagment extends DialogFragment {

    private DashboardViewModel dashboardViewModel;
private AddAlarmFlagment This;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.item_alarm_add, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        This = this;
        Button cancel = root.findViewById(R.id.addButtonCancel);
        Button add = root.findViewById(R.id.addButtonConfirm);
        final EditText time = root.findViewById(R.id.editTextTime);
        final EditText title = root.findViewById(R.id.CustomTitle);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }});
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbOp op = new DbOp(getActivity());
                AlarmObject ao = new AlarmObject();
                ao.setTitle(title.getText().toString());
                ao.setEnable(true);
                ao.setLastAlarm(Calendar.getInstance().getTime().getTime()/1000);
                ao.setMinutes(Integer.parseInt(time.getText().toString()));
                op.addAlarm(ao);

                dismiss();
            }
        });
        return root;
    }
}