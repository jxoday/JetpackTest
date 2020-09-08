package com.jinxin.jetpacktest.room;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.jinxin.jetpacktest.R;
import com.jinxin.jetpacktest.room.asynctask.DeleteStudentTask;
import com.jinxin.jetpacktest.room.asynctask.InsertStudentTask;
import com.jinxin.jetpacktest.room.asynctask.QueryStudentTask;
import com.jinxin.jetpacktest.room.asynctask.UpdateStudentTask;
import com.jinxin.jetpacktest.room.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/michaelye/RoomDemo/blob/master/app/src/main/java/com/michael/roomdemo/MainActivity.java
 *
 * @author JinXin
 */
public class RoomTestActivity extends AppCompatActivity {

    private MyDatabase myDatabase;
    private List<Student> studentList;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_test);

        initView();
        initData();
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
        studentAdapter = new StudentAdapter(RoomTestActivity.this, studentList);
        lvStudent.setAdapter(studentAdapter);
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                updateOrDeleteDialog(studentList.get(position));
                return false;
            }
        });
    }

    private void initData() {
        myDatabase = MyDatabase.getDatabase(this);

        QueryStudentTask queryStudentTask = new QueryStudentTask(myDatabase, studentList);
        queryStudentTask.execute();
        queryStudentTask.setOnTaskListener(new QueryStudentTask.OnTaskListener() {
            @Override
            public void onShowResult() {
                studentAdapter.notifyDataSetChanged();
            }
        });
    }

    private void openAddStudentDialog() {

        View customView = this.getLayoutInflater().inflate(R.layout.dialog_layout_student, null);
        final EditText etName = customView.findViewById(R.id.etName);
        final EditText etAge = customView.findViewById(R.id.etAge);

        final AlertDialog.Builder builder = new AlertDialog.Builder(RoomTestActivity.this);
        AlertDialog dialog = builder.create();
        dialog.setTitle("Add Student");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etAge.getText().toString())) {
                    Toast.makeText(RoomTestActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    InsertStudentTask insertStudentTask = new InsertStudentTask(etName.getText().toString(), etAge.getText().toString(), studentList, myDatabase);
                    insertStudentTask.execute();
                    insertStudentTask.setOnTaskListener(new InsertStudentTask.OnTaskListener() {
                        @Override
                        public void onShowResult() {
                            studentAdapter.notifyDataSetChanged();
                        }
                    });
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

    private void updateOrDeleteDialog(final Student student) {
        final String[] options = new String[]{"更新", "删除"};
        new AlertDialog.Builder(RoomTestActivity.this)
                .setTitle("")
                .setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            openUpdateStudentDialog(student);
                        } else if (which == 1) {
                            DeleteStudentTask deleteStudentTask = new DeleteStudentTask(student, studentList, myDatabase);
                            deleteStudentTask.execute();
                            deleteStudentTask.setOnTaskListener(new DeleteStudentTask.OnTaskListener() {
                                @Override
                                public void onShowResult() {
                                    studentAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                }).show();
    }

    private void openUpdateStudentDialog(final Student student) {
        if (student == null) {
            return;
        }
        View customView = this.getLayoutInflater().inflate(R.layout.dialog_layout_student, null);
        final EditText etName = customView.findViewById(R.id.etName);
        final EditText etAge = customView.findViewById(R.id.etAge);
        etName.setText(student.name);
        etAge.setText(student.age);

        final AlertDialog.Builder builder = new AlertDialog.Builder(RoomTestActivity.this);
        AlertDialog dialog = builder.create();
        dialog.setTitle("Update Student");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etAge.getText().toString())) {
                    Toast.makeText(RoomTestActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    UpdateStudentTask updateStudentTask = new UpdateStudentTask(student.id, etName.getText().toString(), etAge.getText().toString(), studentList, myDatabase);
                    updateStudentTask.execute();
                    updateStudentTask.setOnTaskListener(new UpdateStudentTask.OnTaskListener() {
                        @Override
                        public void onShowResult() {
                            studentAdapter.notifyDataSetChanged();
                        }
                    });
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