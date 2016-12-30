package com.techpenta.camera;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import com.firebase.client.FirebaseError;

import com.firebase.client.ValueEventListener;
import com.firebase.client.core.Context;
import com.firebase.ui.database.FirebaseListAdapter;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    private ExpandableHeightGridView gridView;
    private ArrayList<Beanclass> beanclassArrayList;
    private GridviewAdapter gridviewAdapter;

   public static String TAG;
    com.techpenta.camera.ImageLoader imageLoader;



    ArrayList<String> arrayList=new ArrayList<>();

    ProgressDialog progressDoalog = null;

FirebaseListAdapter<String> myAdapter;
    ImageView ivUpload;
    ImageView ivUploadedimage;
    ImageView ivCamera;
    CameraPhoto cameraPhoto;
    ImageView ivGallery;
    GalleryPhoto galleryPhoto;

    final int CAMERA_REQUEST = 1100;
    final int GALLERY_REQUEST = 1200;
    SharedPreferences.Editor editor;

    String selectPhoto;
    Bitmap bmp;
    String encodedimage;
    int i=0;
    ArrayAdapter<String>adapter;

    int back;

    private int[] IMAGEgrid = {R.drawable.ic_facebook, R.drawable.ic_menu_camera};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       back=1;
      //  gridview = (ExpandableHeightGridView)view.findViewById(R.id.gridview);
        beanclassArrayList= new ArrayList<Beanclass>();


        gridView=(ExpandableHeightGridView)findViewById(R.id.mygridview);
        ivUpload=(ImageView)findViewById(R.id.ivUpload);
        ivCamera=(ImageView)findViewById(R.id.ivCamera);
        ivGallery=(ImageView)findViewById(R.id.ivGallery);
        ivUploadedimage=(ImageView)findViewById(R.id.ivUploadedimage);



       /*adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.listitem,arrayList);

        gridView.setAdapter(adapter);



*/

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);


            AsyncTask asyncTask = new AsyncTask<Void, Void, Void>() {


                @Override
                protected void onPreExecute() {

                    progressDoalog = new ProgressDialog(HomeScreen.this);
                    progressDoalog.setCanceledOnTouchOutside(false);
                    progressDoalog.setMax(100);
                    progressDoalog.setMessage("Its loading....");
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDoalog.show();

                    super.onPreExecute();
                }

                @Override
                protected Void doInBackground(Void... params) {


                    final Firebase firebaseurl = new Firebase("https://pictureupload-874ec.firebaseio.com/pictures");


                    firebaseurl.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String  value = dataSnapshot.getValue(String.class);


                            Log.i("value", value);



                            try {

                                Bitmap myBitmap = decodeBase64(value);
                                Display display = getWindowManager().getDefaultDisplay();
                                Point size = new Point();
                                display.getSize(size);
                                int width = size.x;
                                int height = size.y;
                                Log.e("Screen width ", " " + width);
                                Log.e("Screen height ", " " + height);
                                Log.e("img width ", " " + myBitmap.getWidth());
                                Log.e("img height ", " " + myBitmap.getHeight());
                                float scaleHt = (float) width / myBitmap.getWidth();
                                Log.e("Scaled percent ", " " + scaleHt);
                                bmp = Bitmap.createScaledBitmap(myBitmap, width, height, true);


                                if (bmp != null) {
                                    progressDoalog.dismiss();

                                    //initialize imageview
                                    setUpImages(bmp);


                                }


                                Log.i("getbitmap", bmp.toString());
                                //  bmp=decodeBase64(s);
                            } catch (Exception e) {
                                //Toast.makeText(getContext(),"Some error occoured while loading images!",Toast.LENGTH_LONG).show();
                                Log.i("kingsukmajumder", "error in loading images " + e.toString());
                            }

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


                    return null;
                }

                @Override
                protected void onPostExecute(Void result) {
                    //loading.dismiss();


                }
            }.execute();








        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraPhoto = new CameraPhoto(getApplicationContext());
                Intent in = null;
                back=3;
                try {
                    in = cameraPhoto.takePhotoIntent();
                    startActivityForResult(in, CAMERA_REQUEST);
                    cameraPhoto.addToGallery();
                } catch (IOException e) {
                    Toast.makeText(HomeScreen.this, "sorry somthing goes wrong", Toast.LENGTH_SHORT).show();
                }


            }
        });

       ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back=4;
                galleryPhoto=new GalleryPhoto(getApplicationContext());
                Intent in = galleryPhoto.openGalleryIntent();
                startActivityForResult(in, GALLERY_REQUEST);

                Toast.makeText(HomeScreen.this, "send pic from GALLERY", Toast.LENGTH_SHORT).show();
            }
        });

       ivUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bitmap bitmap = null;



                try {


                    bitmap = ImageLoader.init().from(selectPhoto).
                            requestSize(512, 512).
                            getBitmap();
                    encodedimage = ImageBase64.encode(bitmap);
                    Log.i("encode", encodedimage);


                    final Firebase  firebaseurl=new Firebase("https://pictureupload-874ec.firebaseio.com/pictures");
                    firebaseurl.push().setValue(encodedimage);
                   firebaseurl.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           System.out.println("Size2"+dataSnapshot.getChildrenCount());

                       long v=dataSnapshot.getChildrenCount();

                           savememory(v);
                       }

                       @Override
                       public void onCancelled(FirebaseError firebaseError) {

                       }
                   });
                    ivUploadedimage.setVisibility(View.INVISIBLE);
                    gridView.setVisibility(View.VISIBLE);
                    Toast.makeText(HomeScreen.this, "successfully uploaded", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    Toast.makeText(HomeScreen.this, "somethig goes wrong while uploading", Toast.LENGTH_SHORT).show();
                }


            }
        });

        // Set an item click listener for GridView widget
        gridviewAdapter = new GridviewAdapter(getApplicationContext(), beanclassArrayList);
   gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       @Override
       public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {






           try {


               final ImageView imageView = (ImageView) view.findViewById(R.id.image1);
               final BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
               final Bitmap yourBitmap = bitmapDrawable.getBitmap();





               Display display = getWindowManager().getDefaultDisplay();
               Point size = new Point();
               display.getSize(size);
               int width = size.x;
               int height = size.y;
               Log.e("Screen width ", " "+width);
               Log.e("Screen height ", " "+height);
               Log.e("img width ", " "+yourBitmap.getWidth());
               Log.e("img height ", " "+yourBitmap.getHeight());
               float scaleHt =(float) width/yourBitmap.getWidth();
               Log.e("Scaled percent ", " "+scaleHt);
             Bitmap  bmp2 = Bitmap.createScaledBitmap(yourBitmap, 512,512, true);

               if (bmp2 != null)
               {
                  ivUploadedimage.setVisibility(View.VISIBLE);
                   ivUploadedimage.setImageBitmap(bmp2);
                   back=2;

               }


               Log.i("mybit2",yourBitmap.toString());


           } catch (Exception e) {

               Toast.makeText(HomeScreen.this, "cant convert", Toast.LENGTH_SHORT).show();

               e.printStackTrace();

           }




       }
   });

    }//oncreate











    public void setUpImages(Bitmap bmp)
    {
        Beanclass beanclass = new Beanclass(IMAGEgrid[1]);
        beanclass.setBmp(bmp);
      //  beanclass.setImageId(brandId);
        beanclassArrayList.add(beanclass);
        gridviewAdapter = new GridviewAdapter(getApplicationContext(), beanclassArrayList);
        gridView.setExpanded(true);
      //  gridview.setOnItemClickListener(onItemClick);
        gridView.setAdapter(gridviewAdapter);
    }



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

                    ivUploadedimage.setVisibility(View.VISIBLE);
                    ivUploadedimage.setImageBitmap(bitmap);



                } catch (FileNotFoundException e) {
                    Toast.makeText(HomeScreen.this, "something is wrong in ", Toast.LENGTH_SHORT).show();
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
                    gridView.setVisibility(View.INVISIBLE);
                   ivUploadedimage.setVisibility(View.VISIBLE);
                    ivUploadedimage.setImageBitmap(bitmap); //imageView is your ImageView
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }//end if resultCode
    }


    public void showForgetPassword()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.popup_date, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.etCoupone);




        dialogBuilder.setPositiveButton("Say somthing!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                Toast.makeText(HomeScreen.this, "write something about your pic", Toast.LENGTH_SHORT).show();




            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


    // Set an item click listener for GridView widget









    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else if(back==2||back==3||back==4){
            ivUploadedimage.setVisibility(View.INVISIBLE);
            gridView.setVisibility(View.VISIBLE
            );
            back=1;

        }else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void savememory(long memo) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
         editor = pref.edit();
        editor.putLong("size1", memo);
        // Save the changes in SharedPreferences
        editor.commit(); // commit changes

    }






}
