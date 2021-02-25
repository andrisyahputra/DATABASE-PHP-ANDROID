package com.uas;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class UASPAKLIMBONGActivity extends Activity implements OnClickListener {
Matakuliah Matakuliah = new Matakuliah();
 TableLayout tabelMatakuliah;

 Button buttonTambahMataKuliah;
 ArrayList<Button> buttonEdit = new ArrayList<Button>();
 ArrayList<Button> buttonDelete = new ArrayList<Button>();

 JSONArray arrayMatakuliah;


 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);

  tabelMatakuliah = (TableLayout) findViewById(R.id.tableMataKuliah);
  buttonTambahMataKuliah = (Button) findViewById(R.id.buttonTambahMataKuliah);
  buttonTambahMataKuliah.setOnClickListener(this);

  TableRow barisTabel = new TableRow(this);
  barisTabel.setBackgroundColor(Color.RED);

  TextView viewHeaderKode_MK = new TextView(this);
  TextView viewHeaderNama_MK = new TextView(this);
  TextView viewHeaderSKS = new TextView(this);
  TextView viewHeaderAction = new TextView(this);

  viewHeaderKode_MK.setText("Kode MK");  
  viewHeaderNama_MK.setText("Nama Matakuliah");
  viewHeaderSKS.setText("SKS");
  viewHeaderAction.setText("Action");

  viewHeaderKode_MK.setPadding(5, 1, 5, 1);
  viewHeaderNama_MK.setPadding(5, 1, 5, 1);
  viewHeaderSKS.setPadding(5, 1, 5, 1);
  viewHeaderAction.setPadding(5, 1, 5, 1);

  barisTabel.addView(viewHeaderKode_MK);
  barisTabel.addView(viewHeaderNama_MK);
  barisTabel.addView(viewHeaderSKS);
  barisTabel.addView(viewHeaderAction);

  tabelMatakuliah.addView(barisTabel, new TableLayout.LayoutParams());

  try {

	  arrayMatakuliah = new JSONArray(Matakuliah.tampilMatakuliah());

   for (int i = 0; i < arrayMatakuliah.length(); i++) {
    JSONObject jsonChildNode = arrayMatakuliah.getJSONObject(i);
    String kode_mk = jsonChildNode.optString("Kode_MK");
    String nama_mk = jsonChildNode.optString("Nama_MK");
    String sks = jsonChildNode.optString("SKS");
    String id = jsonChildNode.optString("id");

    System.out.println("Kode MK :" + kode_mk);
    System.out.println("Nama MK :" + nama_mk);
    System.out.println("SKS :" + sks);

    barisTabel = new TableRow(this);

    if (i % 2 == 0) {
    	barisTabel.setBackgroundColor(Color.rgb(220,20,60));;
    }

    TextView viewKodeNama = new TextView(this);
    viewKodeNama.setText(kode_mk);
    viewKodeNama.setPadding(5, 1, 5, 1);
    barisTabel.addView(viewKodeNama);
    
    TextView viewNamaKode = new TextView(this);
    viewNamaKode.setText(nama_mk);
    viewNamaKode.setPadding(5, 1, 5, 1);
    barisTabel.addView(viewNamaKode);

    TextView viewSKS = new TextView(this);
    viewSKS.setText(sks);
    viewSKS.setPadding(5, 1, 5, 1);
    barisTabel.addView(viewSKS);

    buttonEdit.add(i, new Button(this));
    buttonEdit.get(i).setId(Integer.parseInt(id));
    buttonEdit.get(i).setTag("Edit");
    buttonEdit.get(i).setText("Edit");
    buttonEdit.get(i).setOnClickListener(this);
    barisTabel.addView(buttonEdit.get(i));

    buttonDelete.add(i, new Button(this));
    buttonDelete.get(i).setId(Integer.parseInt(id));
    buttonDelete.get(i).setTag("Delete");
    buttonDelete.get(i).setText("Delete");
    buttonDelete.get(i).setOnClickListener(this);
    barisTabel.addView(buttonDelete.get(i));

    tabelMatakuliah.addView(barisTabel, new TableLayout.LayoutParams());
   }
  } catch (JSONException e) {
   e.printStackTrace();
  }
 }

 public void onClick(View view) {

  if (view.getId() == R.id.buttonTambahMataKuliah) {
   // Toast.makeText(MainActivity.this, "Button Tambah Data",
   // Toast.LENGTH_SHORT).show();

   tambahMatakuliah();

  } else {
   /*
    * Melakukan pengecekan pada data array, agar sesuai dengan index
    * masing-masing button
    */
   for (int i = 0; i < buttonEdit.size(); i++) {

    /* jika yang diklik adalah button edit */
    if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
     // Toast.makeText(MainActivity.this, "Edit : " +
     // buttonEdit.get(i).getId(), Toast.LENGTH_SHORT).show();
     int id = buttonEdit.get(i).getId();
     getDataByID(id);

    } /* jika yang diklik adalah button delete */
    else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")) {
     // Toast.makeText(MainActivity.this, "Delete : " +
     // buttonDelete.get(i).getId(), Toast.LENGTH_SHORT).show();
     int id = buttonDelete.get(i).getId();
     deleteMatakuliah(id);

    }
   }
  }
 }

 public void deleteMatakuliah(int id) {
	 Matakuliah.deleteMatakuliah(id);

  /* restart acrtivity */
  finish();
  startActivity(getIntent());

 }

 public void getDataByID(int id) {

  String kodeMkEdit = null, nama_mkEdit = null, SKSEdit = null;
  JSONArray arrayPersonal;

  try {

   arrayPersonal = new JSONArray(Matakuliah.getMatakuliahById(id));

   for (int i = 0; i < arrayPersonal.length(); i++) {
    JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
    kodeMkEdit = jsonChildNode.optString("Kode_MK");
    nama_mkEdit = jsonChildNode.optString("Nama_MK");
    SKSEdit = jsonChildNode.optString("SKS");
   }
  } catch (JSONException e) {
   e.printStackTrace();
  }

  LinearLayout layoutInput = new LinearLayout(this);
  layoutInput.setOrientation(LinearLayout.VERTICAL);

  // buat id tersembunyi di alertbuilder
  final TextView viewId = new TextView(this);
  viewId.setText(String.valueOf(id));
  viewId.setTextColor(Color.TRANSPARENT);
  layoutInput.addView(viewId);

  final EditText EditkodeMK = new EditText(this);
  EditkodeMK.setText(kodeMkEdit);
  layoutInput.addView(EditkodeMK);
  
  final EditText editNamaMK = new EditText(this);
  editNamaMK.setText(nama_mkEdit);
  layoutInput.addView(editNamaMK);

  final EditText editSKS = new EditText(this);
  editSKS.setText(SKSEdit);
  layoutInput.addView(editSKS);

  AlertDialog.Builder builderEditMatakuliah = new AlertDialog.Builder(this);
  builderEditMatakuliah.setIcon(R.drawable.ic_launcher);
  builderEditMatakuliah.setTitle("Update Mata Kuliah");
  builderEditMatakuliah.setView(layoutInput);
  builderEditMatakuliah.setPositiveButton("Update", new DialogInterface.OnClickListener() {

	  
   public void onClick(DialogInterface dialog, int which) {
	String kode_mk = EditkodeMK.getText().toString();
    String nama_mk = editNamaMK.getText().toString();
    String sks = editSKS.getText().toString();

    System.out.println("Kode MK : " + kode_mk + " Nama MK : " + nama_mk + "SKS : " + sks);

    String laporan = Matakuliah.updateMatakuliah(viewId.getText().toString(), 
    		EditkodeMK.getText().toString(),
    		editNamaMK.getText().toString(),
    		editSKS.getText().toString());

    Toast.makeText(UASPAKLIMBONGActivity.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
    finish();
    startActivity(getIntent());
   }

  });

  builderEditMatakuliah.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

   public void onClick(DialogInterface dialog, int which) {
    dialog.cancel();
   }
  });
  builderEditMatakuliah.show();

 }

 public void tambahMatakuliah() {
  /* layout akan ditampilkan pada AlertDialog */
  LinearLayout layoutInput = new LinearLayout(this);
  layoutInput.setOrientation(LinearLayout.VERTICAL);

  final EditText EditkodeMK = new EditText(this);
  EditkodeMK.setHint("Kode_MK");
  layoutInput.addView(EditkodeMK);
  
  final EditText editNamaMK = new EditText(this);
  editNamaMK.setHint("Nama");
  layoutInput.addView(editNamaMK);

  final EditText editSKS = new EditText(this);
  editSKS.setHint("SKS");
  layoutInput.addView(editSKS);

  AlertDialog.Builder builderEditMatakuliah = new AlertDialog.Builder(this);
  builderEditMatakuliah.setIcon(R.drawable.ic_launcher);
  builderEditMatakuliah.setTitle("Insert Matakuliah");
  builderEditMatakuliah.setView(layoutInput);
  builderEditMatakuliah.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
  
	  
   public void onClick(DialogInterface dialog, int which) {
	String kode_mk = EditkodeMK.getText().toString();
    String nama_mk = editNamaMK.getText().toString();
    String sks = editSKS.getText().toString();

    System.out.println("Kode MK : " + kode_mk + " Nama MK : " + nama_mk + " SKS : " + sks);

    String laporan = Matakuliah.inserMatakuliah(kode_mk, nama_mk, sks);

    Toast.makeText(UASPAKLIMBONGActivity.this, laporan, Toast.LENGTH_SHORT).show();

    /* restart acrtivity */
    finish();
    startActivity(getIntent());
   }

  });

  builderEditMatakuliah.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

	  
   public void onClick(DialogInterface dialog, int which) {
    dialog.cancel();
   }
  });
  builderEditMatakuliah.show();
 }
}