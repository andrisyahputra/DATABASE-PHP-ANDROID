package com.uas;

public class Matakuliah extends Koneksi {
	 String URL = "http://10.0.2.2/Android_Kampus/server.php";
	 String url = "";
	 String response = "";

	 public String tampilMatakuliah() {
	  try {
	   url = URL + "?operasi=view";
	   System.out.println("URL Tampil matakuliah: " + url);
	   response = call(url);
	  } catch (Exception e) {
	  }
	  return response;
	 }

	 public String inserMatakuliah(String kode_mk , String nama_mk, String sks) {
	  try {
	   url = URL + "?operasi=insert&Kode_MK="+ kode_mk + "&Nama_MK=" + nama_mk + "&SKS=" + sks;
	   System.out.println("URL Insert Biodata : " + url);
	   response = call(url);
	  } catch (Exception e) {
	  }
	  return response;
	 }

	 public String getMatakuliahById(int id) {
	  try {
	   url = URL + "?operasi=get_matakuliah_by_id&id=" + id;
	   System.out.println("URL Insert Matakuliah : " + url);
	   response = call(url);
	  } catch (Exception e) {
	  }
	  return response;
	 }

	 public String updateMatakuliah(String id, String kode_mk , String nama_mk, String sks) {
	  try {
	   url = URL + "?operasi=update&id=" + id + "&Kode_MK=" + kode_mk + "&Nama_MK=" + nama_mk + "&SKS=" + sks;
	   System.out.println("URL Insert Matakuliah : " + url);
	   response = call(url);
	  } catch (Exception e) {
	  }
	  return response;
	 }

	 public String deleteMatakuliah(int id) {
	  try {
	   url = URL + "?operasi=delete&id=" + id;
	   System.out.println("URL Delete Matakuliah : " + url);
	   response = call(url);
	  } catch (Exception e) {
	  }
	  return response;
	 }

	}

