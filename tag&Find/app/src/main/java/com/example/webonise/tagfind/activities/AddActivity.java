package com.example.webonise.tagfind.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.example.webonise.tagfind.database.MySQLiteHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddActivity extends AppCompatActivity {

    private MySQLiteHelper mydb;
    private EditText etTitle,etTag;
    private ImageView imageView;
    private Button btnTakePhoto,btnFromGallery,btncancle;
    private String imagePath;

    static final int TAKE_PHOTO = 0;
    static final int CHOOSE_FROM_GALLERY = 1;
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
        final CharSequence[] options =getResources().getStringArray(R.array.itemsArray);

        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        builder.setTitle(R.string.add_photo);
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                //if (options[item].equals(getString(R.string.dialog_take_photo)))
                if(item ==TAKE_PHOTO)
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,TAKE_PHOTO);
                }
                else  if(item ==CHOOSE_FROM_GALLERY)
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, CHOOSE_FROM_GALLERY);
                }
                else {
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
            if (requestCode == TAKE_PHOTO)
            {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(image);
                File file = new File(Environment.getExternalStorageDirectory().toString());
                imagePath = file.getAbsolutePath();

                for (File temp : file.listFiles())
                {
                    if (temp.getName().equals(""))
                    {
                        file = temp;
                        break;
                    }
                }
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                imageView.setVisibility(View.VISIBLE);
               // BitmapFactory.Options options = new BitmapFactory.Options();
                imagePath = file.getAbsolutePath();
                Log.i("",file.getAbsolutePath());
                imageView.setImageBitmap(image);
                //options.inSampleSize =20;
                File file1 = new File(Environment.getExternalStorageDirectory()+File.separator + System.currentTimeMillis()+ ".jpg");
                try
                {
                    file1.createNewFile();
                    FileOutputStream fo = new FileOutputStream(file1);
                    image.compress(Bitmap.CompressFormat.JPEG,50,bytes);
                    fo.write(bytes.toByteArray());
                    fo.flush();
                    fo.close();
                    imagePath = file1.getAbsolutePath();
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }else if (requestCode == CHOOSE_FROM_GALLERY) {

                Uri selectedImage = data.getData();

                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePath, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePath[0]);
                String picturePath = cursor.getString(columnIndex);
                imagePath = picturePath;
                cursor.close();
               Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                Log.i("path of image from gallery......******************.........", picturePath + "");
                imageView.setImageBitmap(bitmap);
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

           etTitle.setError("Required....");
            etTitle.requestFocus();
            return;
        }

        else
        {
            long id  = mydb.insertData(imagePath,etTag.getText().toString(),etTitle.getText().toString());
            if (id != -1)
            {
                Toast.makeText(AddActivity.this,R. string.data_inserted, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(AddActivity.this,R. string.data_not_inserted, Toast.LENGTH_LONG).show();
            }
        }
    }
}