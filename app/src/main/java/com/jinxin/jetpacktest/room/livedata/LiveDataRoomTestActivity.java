package com.jinxin.jetpacktest.room.livedata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.jinxin.jetpacktest.R;
import com.jinxin.jetpacktest.room.MyDatabase;
import com.jinxin.jetpacktest.room.RoomTestActivity;
import com.jinxin.jetpacktest.room.StudentAdapter;
import com.jinxin.jetpacktest.room.asynctask.DeleteStudentTask;
import com.jinxin.jetpacktest.room.asynctask.InsertStudentTask;
import com.jinxin.jetpacktest.room.asynctask.UpdateStudentTask;
import com.jinxin.jetpacktest.room.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JinXin
 */
public class LiveDataRoomTestActivity extends AppCompatActivity {

    private StudentViewModel studentViewModel;

    private List<Student> studentList;
    private StudentAdapter studentAdapter;
    private MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_room_test);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);


        initView();
    }

    private void initView() {

        findViewById(R.id.btnInsertStudent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddStudentDialog();
            }
        });

        ListView lvStudent = findViewById(R.id.lvStudent);
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(LiveDataRoomTestActivity.this, studentList);
        lvStudent.setAdapter(studentAdapter);
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                updateOrDeleteDialog(studentList.get(position));
                return false;
            }
        });

        studentViewModel.getLiveDataStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                studentList.clear();
                studentList.addAll(students);
                studentAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 添加学生弹窗
     */
    private void openAddStudentDialog() {

        View customView = this.getLayoutInflater().inflate(R.layout.dialog_layout_student, null);
        final EditText etName = customView.findViewById(R.id.etName);
        final EditText etAge = customView.findViewById(R.id.etAge);

        final AlertDialog.Builder builder = new AlertDialog.Builder(LiveDataRoomTestActivity.this);
        AlertDialog dialog = builder.create();
        dialog.setTitle("Add Student");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etAge.getText().toString())) {
                    Toast.makeText(LiveDataRoomTestActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    studentViewModel.addStudent(etName.getText().toString(), etAge.getText().toString());
                }
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setView(customView);
        dialog.show();
    }

    /**
     * 更新/删除弹窗
     * @param student
     */
    private void updateOrDeleteDialog(final Student student) {
        final String[] options = new String[]{"更新", "删除"};
        new AlertDialog.Builder(LiveDataRoomTestActivity.this)
                .setTitle("")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            openUpdateStudentDialog(student);
                        } else if (which == 1) {
                            studentViewModel.deleteStudent(student);
                        }
                    }
                }).show();
    }

    /**
     * 更新学生弹窗
     * @param student
     */
    private void openUpdateStudentDialog(final Student student) {
        if (student == null) {
            return;
        }
        View customView = this.getLayoutInflater().inflate(R.layout.dialog_layout_student, null);
        final EditText etName = customView.findViewById(R.id.etName);
        final EditText etAge = customView.findViewById(R.id.etAge);
        etName.setText(student.name);
        etAge.setText(student.age);

        final AlertDialog.Builder builder = new AlertDialog.Builder(LiveDataRoomTestActivity.this);
        AlertDialog dialog = builder.create();
        dialog.setTitle("Update Student");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etAge.getText().toString())) {
                    Toast.makeText(LiveDataRoomTestActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    studentViewModel.updateStudent(student, etName.getText().toString(), etAge.getText().toString());
                }
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setView(customView);
        dialog.show();
    }
}