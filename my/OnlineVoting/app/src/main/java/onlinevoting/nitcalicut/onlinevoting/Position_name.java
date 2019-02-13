package onlinevoting.nitcalicut.onlinevoting;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import onlinevoting.nitcalicut.onlinevoting.helper.FBDatabaseHelper;
import onlinevoting.nitcalicut.onlinevoting.model.Post;
import onlinevoting.nitcalicut.onlinevoting.utils.MyDividerItemDecoration;
import onlinevoting.nitcalicut.onlinevoting.utils.RecyclerTouchListener;
import onlinevoting.nitcalicut.onlinevoting.view.PostAdapter;

public class Position_name extends AppCompatActivity    {

    private PostAdapter mAdapter;
    private List<Post> postsList;
    private TextView noPostsView;
    private RecyclerView recyclerView;
    private FBDatabaseHelper db;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_name);

        db = new FBDatabaseHelper(this);

        postsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        noPostsView = findViewById(R.id.empty_posts_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));

        db.getAllPosts(new DBCallback(){
            @Override
            public void onSuccess(List<Post> posts){
                postsList.clear();
                postsList.addAll(posts);
                mAdapter = new PostAdapter(getApplicationContext(),postsList);
                recyclerView.setAdapter(mAdapter);
                toggleEmptyPosts();
            }

            @Override
            public void onSuccess(Post post){

            }

            @Override
            public void onSuccess(int count){

            }

            @Override
            public void onError(String msg){
                Toast.makeText(Position_name.this,msg, Toast.LENGTH_SHORT).show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),new Gson().toJson(postsList),Toast.LENGTH_LONG).show();
                showNoteDialog(false, null, -1);
            }
        });




        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));

        toggleEmptyPosts();
    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showNoteDialog(true, postsList.get(position), position);
                } else {
                    deletePost(position);
                }
            }
        });
        builder.show();
    }

    private void showNoteDialog(final boolean shouldUpdate, final Post post, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.postdialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(Position_name.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputPost = view.findViewById(R.id.post);
        final EditText inputDate = view.findViewById(R.id.date);
        final ImageView btnDatePicker=view.findViewById(R.id.set_date);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Position_name.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month= month+1;
                                inputDate.setText(day + "/" + month + "/" + year);

                            }
                        },mYear,mMonth,mDay);
                datePickerDialog.show();
            }
        });



        dialogTitle.setText(!shouldUpdate ? "New Post" : "Edit Post");

        if (shouldUpdate && post != null) {
            inputPost.setText(post.getPost_name());
            inputDate.setText(post.getDate());
        }

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(inputPost.getText().toString())) {
                    Toast.makeText(Position_name.this, "Enter Post!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(inputDate.getText().toString())){
                    Toast.makeText(Position_name.this, "Enter Date!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    alertDialog.dismiss();
                }

                if (shouldUpdate && post != null) {
                    updatePost(inputPost.getText().toString(), position);
                } else {
                    createPost(inputPost.getText().toString(),inputDate.getText().toString());
                }
            }
        });
    }

    private void createPost(final String postname,final String date) {
        int post_id=0;
        if(postsList.isEmpty())
        {
            post_id=1;
        }

        else
        {
            post_id= postsList.get(postsList.size()-1).getPost_id()+1;
        }

        int id = db.insertPost(post_id,postname,date);
        Post n = db.getPost(id);
        if (n != null) {
            postsList.add(0, n);
            mAdapter.notifyDataSetChanged();
            toggleEmptyPosts();
        }
    }

    private void updatePost(String post_name, int position) {
        Post n = postsList.get(position);
        n.setPost_name(post_name);
        db.updatePost(n);
        postsList.set(position, n);
        mAdapter.notifyItemChanged(position);
        toggleEmptyPosts();
    }

    private void deletePost(int position) {
        db.deletePost(postsList.get(position));
        postsList.remove(position);
        mAdapter.notifyItemRemoved(position);
        toggleEmptyPosts();
    }

    private void toggleEmptyPosts() {
        db.getPostsCount(new DBCallback() {
            @Override
            public void onSuccess(List<Post> posts) {

            }

            @Override
            public void onSuccess(Post post){

            }

            @Override
            public void onSuccess(int count) {
                if(count>0){
                    noPostsView.setVisibility(View.GONE);
                }else{
                    noPostsView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
