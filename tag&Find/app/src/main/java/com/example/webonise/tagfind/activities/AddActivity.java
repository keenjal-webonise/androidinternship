package com.example.webonise.tagfind.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.webonise.tagfind.R;
import com.example.webonise.tagfind.models.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AddActivity extends AppCompatActivity {

    MySQLiteHelper mydb;
    EditText etTitle,etTag;
    ImageView imageView;
    Button btnTakePhoto,btnFromGallery,btncancle;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mydb = new MySQLiteHelper(this);
        imageView = (ImageView) findViewById(R.id.imageView);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etTag = (EditText) findViewById(R.id.etTag);
        btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
        btnFromGallery = (Button) findViewById(R.id.btnFromGallery);
        btncancle = (Button) findViewById(R.id.btncancle);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });

        addData();
    }
    private void selectImage()
    {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals(getString(R.string.dialog_take_photo)))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1)
            {
                File file = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : file.listFiles())
                {
                    if (temp.getName().equals(""))
                    {
                        file = temp;
                        break;
                    }
                }
                try
                {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bitmapOptions);
                    imagePath = file.getAbsolutePath();
                    Log.i("",file.getAbsolutePath());
                    imageView.setImageBitmap(bitmap);
                    String path = android.os.Environment.getExternalStorageDirectory() + File.separator + "Phoenix" + File.separator + "default";
                   // String path = Environment.getExternalStorageDirectory().getPath() + "/camera/MyApp";
                    file.delete();
                    OutputStream outFile = null;
                    File file1 = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

                    try {
                        outFile = new FileOutputStream(file1);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                imagePath = picturePath;

                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery......******************.........", picturePath + "");
                imageView.setImageBitmap(thumbnail);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_save, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_save:
                addData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void addData()
    {
        String tag = etTag.getText().toString();
        String title = etTitle.getText().toString();

        if (TextUtils.isEmpty(tag))
        {
            etTag.setError("Required....");
            etTag.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(title))
        {
            Toast.makeText(getApplicationContext(),"First FillUp the Data", Toast.LENGTH_LONG).show();
            return;
        }

        else
        {
            long id  = mydb.insertData(imagePath,etTag.getText().toString(),etTitle.getText().toString());
            if (id != -1)
            {
                Toast.makeText(AddActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(AddActivity.this, "Data  not Inserted", Toast.LENGTH_LONG).show();
            }
        }
    }
}