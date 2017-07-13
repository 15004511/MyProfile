package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    CheckBox ckbLike;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.editTextName);
        etGPA = (EditText)findViewById(R.id.editTextGPA);
        rgGender = (RadioGroup)findViewById(R.id.RadioGroupGender);
        ckbLike = (CheckBox)findViewById(R.id.checkBoxLikeProgramming);
    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        float strGPA = Float.parseFloat(etGPA.getText().toString());
        Integer rg = rgGender.getCheckedRadioButtonId();
        boolean cb = ckbLike.isChecked();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", strGPA);
        prefEdit.putInt("rg", rg);
        prefEdit.putBoolean("cb", cb);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String strName = prefs.getString("name", "John");
        float strGPA = prefs.getFloat("gpa", 0);
        Integer rg = prefs.getInt("rg", 0);
        boolean cb = prefs.getBoolean("cb", false);

        etName.setText(strName);
        etGPA.setText(Float.toString(strGPA));
        rgGender.check(rg);
        ckbLike.setChecked(cb);
    }
}
