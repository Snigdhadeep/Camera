package com.techpenta.camera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.core.Tag;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    ImageView ivCamera, ivGallery, ivUpload,ivImage,ivGetImage,ivBitmap;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    final int CAMERA_REQUEST = 1100;
    final int GALLERY_REQUEST = 1200;
    String selectPhoto;
    Firebase firebase;
    String encodedimage;
    int count=0;
    Bitmap myBitmapAgain;

   ListView listView;

   // com.firebase.ui.FirebaseListAdapter<String> myAdapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





      /*  firebase=new Firebase("https://pictureupload-874ec.firebaseio.com");



      //  ivGetImage = (ImageView) findViewById(R.id.ivGetImage);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        ivCamera = (ImageView) findViewById(R.id.ivCamera);
        ivGallery = (ImageView) findViewById(R.id.ivGallery);
        ivUpload = (ImageView) findViewById(R.id.ivUpload);
        ivBitmap = (ImageView) findViewById(R.id.ivBitmap);
        listView=(ListView)findViewById(R.id.listview);



        myAdapter = new FirebaseListAdapter<String>(this,String.class,R.layout.listitem,firebase) {
            @Override
            protected void populateView(View view, String s, int i) {
              //  TextView text = (TextView) view.findViewById(R.id.tvlistItem);
               // ImageView img=(ImageView)view.findViewById(R.id.ivListItem);
                Bitmap myBitmapAgain = decodeBase64(s);
                //img.setImageBitmap(myBitmapAgain);
            }
        };

        listView.setAdapter(myAdapter);


        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try {
                    cameraPhoto = new CameraPhoto(getApplicationContext());
                    Intent in = cameraPhoto.takePhotoIntent();
                    startActivityForResult(in, CAMERA_REQUEST);
                    cameraPhoto.addToGallery();
                    Toast.makeText(MainActivity.this, "do it fast,,snigdho is waiting..........", Toast.LENGTH_SHORT).show();
                    count=count+1;
                    SharedPreferences.Editor editor = getSharedPreferences("CAMERA", MODE_PRIVATE).edit();
                    editor.putInt("countkey", count);
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "Something goes wrong taking picture", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryPhoto=new GalleryPhoto(getApplicationContext());
                Intent in = galleryPhoto.openGalleryIntent();
                startActivityForResult(in, GALLERY_REQUEST);

                Toast.makeText(MainActivity.this, "Send your best pics from gallary one by one....yes he is waitinggg", Toast.LENGTH_SHORT).show();
            }
        });


        ivUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    try {


                        Bitmap  bitmap = ImageLoader.init().from(selectPhoto).
                                requestSize(512, 512).
                                getBitmap();
                        encodedimage= ImageBase64.encode(bitmap);
                       firebase.push().setValue(encodedimage);




                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                Toast.makeText(MainActivity.this, "thanks for upload yuor pic lina ghosh' ", Toast.LENGTH_SHORT).show();
               *//*  firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String data=dataSnapshot.getValue(String.class);
                        etEdit.setText("hello"+data);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });*//*




            }
        });





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {





  *//*              firebase.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                          String data=dataSnapshot.getValue(String.class);
                        Bitmap myBitmapAgain = decodeBase64(data);

                        productList = new ArrayList<>();
                        productList.add(new Product(myBitmapAgain,myBitmapAgain.toString()));


                        listViewAdapter = new ListViewAdapter(getApplicationContext(),
                                R.layout.listitem, productList);

                        listView.setAdapter(listViewAdapter);


                        listViewAdapter.notifyDataSetChanged();

                        Snackbar.make(view, "its not for you lina", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        Log.i("againbitmap",myBitmapAgain.toString());

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });





              *//**//*  firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String data=dataSnapshot.getValue(String.class);
                        Bitmap myBitmapAgain = decodeBase64(data);
                        ivBitmap.setVisibility(View.VISIBLE);
                        ivBitmap.setImageBitmap(myBitmapAgain);



                        Snackbar.make(view, "its not for you lina", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        Log.i("againbitmap",myBitmapAgain.toString());
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Toast.makeText(MainActivity.this, "nnnnn", Toast.LENGTH_SHORT).show();
                    }
                });*//*


            }
        });


*/

    }//oncreate













    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                String photoPath = cameraPhoto.getPhotoPath();
                selectPhoto=photoPath;
                Bitmap bitmap = null;
                try {
                    bitmap = ImageLoader.init().from(photoPath).
                            requestSize(512, 512).
                            getBitmap();
                    ivBitmap.setVisibility(View.VISIBLE);
                    ivBitmap.setImageBitmap(bitmap); //imageView is your ImageView
                } catch (FileNotFoundException e) {
                    Toast.makeText(MainActivity.this, "something is wrong in ", Toast.LENGTH_SHORT).show();
                }

            }

            else if(requestCode == GALLERY_REQUEST){

                galleryPhoto.setPhotoUri(data.getData());
                String photoPath = galleryPhoto.getPath();
                selectPhoto=photoPath;
                Bitmap bitmap = null;
                try {
                    bitmap = ImageLoader.init().from(photoPath).
                            requestSize(512, 512).
                            getBitmap();
                    ivImage.setImageBitmap(bitmap); //imageView is your ImageView
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }//end if resultCode
    }
}