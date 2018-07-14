package monster.artificial.bannerlah;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dariopellegrini.formbuilder.FormBuilder;
import com.dariopellegrini.formbuilder.FormButton;
import com.dariopellegrini.formbuilder.FormElement;
import com.dariopellegrini.formbuilder.FormHeader;
import com.dariopellegrini.formbuilder.FormObject;
import com.simplify.ink.InkView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import pl.aprilapps.easyphotopicker.EasyImage;


public class ApplicationActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout_1, mLinearLayout_2, mLinearLayout_3;
    private ScrollView mScrollView;
    private FormBuilder formBuilder_1, formBuilder_2, formBuilder_3;
    private InkView ink_1, ink_2;
    private TextView signature1, signature2;
    private ImageView imageView;
    private final int CAMERA_REQUEST = 3456;
    private final int PICK_IMAGE = 4567;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        createForm();
    }

    private void createForm() {
        mLinearLayout_1 = (LinearLayout) findViewById(R.id.linearLayout_1);
        mLinearLayout_2 = (LinearLayout) findViewById(R.id.linearLayout_2);
        mLinearLayout_3 = (LinearLayout) findViewById(R.id.linearLayout_3);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        signature1 = (TextView) findViewById(R.id.signature_1);
        signature2 = (TextView) findViewById(R.id.signature_2);
        ink_1 = (InkView) findViewById(R.id.ink_1);
        ink_2 = (InkView) findViewById(R.id.ink_2);
        imageView = (ImageView) findViewById(R.id.image_display);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_GRANTED)) {
                        ActivityCompat.requestPermissions(ApplicationActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_REQUEST);
                        return;
                    }
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });

        ink_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
        ink_1.setColor(getResources().getColor(android.R.color.black));
        ink_1.setMinStrokeWidth(1.5f);
        ink_1.setMaxStrokeWidth(6f);

        ink_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                } else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
        ink_2.setColor(getResources().getColor(android.R.color.black));
        ink_2.setMinStrokeWidth(1.5f);
        ink_2.setMaxStrokeWidth(6f);

        formBuilder_1 = new FormBuilder(this, mLinearLayout_1);
        List<FormObject> formObjects_1 = new ArrayList<FormObject>();
        formObjects_1.add(new FormHeader()
                .setTitle("Butir-butir permohonan : Bahagian 1"));
        formObjects_1.add(new FormElement().setHint("Nama penuh pemilik paparan " +
                "(Seperti di dalam kad pengenalan) / Nama syarikat")
                .setTag("Name 1")
                .setType(FormElement.Type.TEXT)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("No. Kad pegenalan")
                .setTag("IC 1")
                .setType(FormElement.Type.TEXT)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("Alamat surat menyurat")
                .setTag("Address 1")
                .setType(FormElement.Type.TEXT)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("No. Telefon")
                .setTag("Phone number 1")
                .setType(FormElement.Type.PHONE)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("Ukuran Sepanduk / Kain Pampang")
                .setTag("Dimensions")
                .setType(FormElement.Type.TEXT)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormHeader()
                .setTitle("Butir-butir permohonan : Bahagian 2 " +
                        "(Diisi sekiranya iklan bukan dipohon oleh pemilik paparan)"));
        formObjects_1.add(new FormElement().setHint("Nama penuh agen iklan (Seperti di " +
                "dalam kad pegenalan) / Nama syarikat")
                .setTag("Name 2")
                .setType(FormElement.Type.TEXT)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("Nama penuh agen iklan (Seperti di " +
                "dalam kad pegenalan) / Nama syarikat")
                .setTag("Name 2")
                .setType(FormElement.Type.TEXT)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("No. Kad pegenalan")
                .setTag("IC 2")
                .setType(FormElement.Type.TEXT)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("Alamat surat menyurat")
                .setTag("Address 2")
                .setType(FormElement.Type.TEXT)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("No. Telefon")
                .setTag("Phone number 2")
                .setType(FormElement.Type.PHONE)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("Tempoh pameran (Mula)")
                .setTag("Start Date")
                .setType(FormElement.Type.DATE)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("Tempoh pameran (Tamat)")
                .setTag("End Date")
                .setType(FormElement.Type.DATE)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("Lokasi sepanduk / Kain pampang akan dipamerkan")
                .setTag("Banner Location")
                .setType(FormElement.Type.TEXT)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("Tajuk visual")
                .setTag("Banner Title")
                .setType(FormElement.Type.TEXT)
                .setRequired(true)
                .setErrorMessage("Required"));
        formObjects_1.add(new FormElement().setHint("Kuantiti sepanduk / Kain pampang")
                .setTag("Banner Quantity")
                .setType(FormElement.Type.NUMBER)
                .setRequired(true)
                .setErrorMessage("Required"));

        formObjects_1.add(new FormHeader()
                .setTitle("Pengakuan Pemohon (Tandatangan)"));
        formBuilder_1.build(formObjects_1);

        signature1.setText("Saya akan menunaikan syarat-sysrat " +
                "seperti yang dikehendaki dan mengaku bahawa semua maklumat yang diberikan " +
                "adalah benar dan jika didpati palsu, maka Majlis Perbandaran Seberang Perai " +
                "berhak menolak permohonan ini ataupun menarik balik kelulusan yang telah " +
                "dikeluarkan kepada saya.");

        formBuilder_2 = new FormBuilder(this, mLinearLayout_2);
        List<FormObject> formObjects_2 = new ArrayList<FormObject>();
        formObjects_2.add(new FormHeader()
                .setTitle("Pengakuan Pemilik Bangunan / Tapak (Tandatangan)"));
        formBuilder_2.build(formObjects_2);

        signature2.setText("Saya dengan ini memberi pengakuan bahawa saya membenarkan pemohon ini " +
                "mengunnakan bangunan / tapak saya untuk tujuan pemasangan tersebut");

        formBuilder_3 = new FormBuilder(this, mLinearLayout_3);
        List<FormObject> formObjects_3 = new ArrayList<FormObject>();
        formObjects_3.add(new FormButton()
                .setTitle("Submit")
                .setBackgroundColor(Color.RED)
                .setTextColor(Color.WHITE)
                .setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        boolean isValid = formBuilder_1.validate();
                        Log.i("Forms", formBuilder_1.formMap.toString());
                    }
                })
        );
        formBuilder_3.build(formObjects_3);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                Toast.makeText(ApplicationActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(ApplicationActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Please grant camera permission to use app", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
