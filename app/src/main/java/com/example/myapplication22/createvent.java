package com.example.myapplication22;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
public class createvent extends AppCompatActivity {



    EditText ed1,ed2,ed3,ed4;
    Button ev;
    ImageView img ;


    Button b1,b;
    // FirebaseDatabase rootNode ;
    DatabaseReference reference ;
    private static final int GALLERY_REQUEST = 1;
    private Uri imageuri;
    private StorageReference reference1;
    private StorageTask uploadtask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createvent);
        ed1= (EditText) findViewById(R.id.eventname);
        ed2= (EditText) findViewById(R.id.eventdate);
        ed3= (EditText) findViewById(R.id.eventtime);
        ed4= (EditText) findViewById(R.id.eventdescription);
        //ajout image
        ev = (Button)findViewById(R.id.eventpic);
        img=(ImageView)findViewById(R.id.viewevent) ;

        b1= (Button) findViewById(R.id.add);
        b = (Button) findViewById(R.id.back);
        reference1 = FirebaseStorage.getInstance().getReference("images");



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class)); }
        });



        ev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, GALLERY_REQUEST);
                }
            });
        reference = FirebaseDatabase.getInstance().getReference().child("events");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uploadtask!=null && uploadtask.isInProgress()){
                    Toast.makeText(createvent.this, "Adding in progress", Toast.LENGTH_LONG).show();
                } else {

                    final StorageReference filepath = reference1.child(System.currentTimeMillis() + "." + getFileExtension(imageuri));
                    uploadtask = filepath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    insertevent(uri.toString());
                                    Toast.makeText(createvent.this, "Event added", Toast.LENGTH_LONG).show();

                                }

                            });
                        }

                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }



            }
        });



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GALLERY_REQUEST&&resultCode==RESULT_OK){

             imageuri=data.getData();
            img.setImageURI(imageuri);

        }

      }


    private void insertevent (String uri) {
        String name = ed1.getText().toString().trim();
        String date = ed2.getText().toString().trim();
        String time = ed3.getText().toString().trim();
        String description = ed4.getText().toString().trim();
        NewEvent newEvent =new NewEvent(name,date,time,description,uri);
        reference.child(name).setValue(newEvent);


    }





    private String getFileExtension(Uri mUri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
}
