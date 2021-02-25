<?php
 
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','kampus');
$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

@$operasi = $_GET['operasi'];

switch ($operasi) {
    case "view":
        /* Source code untuk Menampilkan Biodata */
	
	$sql = "SELECT * FROM matakuliah";
	$query_tampil_biodata = mysqli_query($con,$sql);
        	$data_array = array();
        	while ($data = mysqli_fetch_assoc($query_tampil_biodata)) {
            $data_array[] = $data;
            }
        echo json_encode($data_array);
        break;

    case "insert":
        /* Source code untuk Insert data */
        @$kode_mk = $_GET['Kode_MK'];
        @$nama_mk = $_GET['Nama_MK'];
        @$sks = $_GET['SKS'];
        $sql = "INSERT INTO matakuliah (Kode_MK,Nama_MK,SKS) VALUES('$kode_mk','$nama_mk', '$sks')";
	$query_insert_data = mysqli_query($con,$sql);

        if ($query_insert_data) {
            echo "Data Berhasil Disimpan";
        } else {
            echo "Error Inser Biodata " . mysql_error();
        }
        break;
   
   case "get_matakuliah_by_id":
        /* Source code untuk Edit data dan mengirim data berdasarkan id yang diminta */
        @$id = $_GET['id'];
	$sql = "SELECT * FROM `matakuliah` WHERE id='$id'";
        $query_tampil_matakuliah= mysqli_query($con,$sql);
        $data_array = array();
        $data_array = mysqli_fetch_assoc($query_tampil_matakuliah);
        echo "[" . json_encode($data_array) . "]";
        break;

    case "update":
        /* Source code untuk Update Biodata */
        @$kode_mk = $_GET['Kode_MK'];
        @$nama_mk = $_GET['Nama_MK'];
        @$sks = $_GET['SKS'];
        @$id = $_GET['id'];
	$sql = "UPDATE matakuliah SET Kode_MK='$kode_mk' , Nama_MK='$nama_mk', SKS='$sks' WHERE id='$id'";
	$query_update_matakuliah = mysqli_query($con,$sql);

        if ($query_update_matakuliah) {
            echo "Update Data Berhasil";
        } else {
            echo mysql_error();
        }
        break;

    case "delete":
        /* Source code untuk Delete Biodata */
        @$id = $_GET['id'];
	$sql = "DELETE FROM matakuliah WHERE id='$id'";
	$query_delete_biodata = mysqli_query($con,$sql);
        
        if ($query_delete_biodata) {
            echo "Delete Data Berhasil";
        } else {
            echo mysql_error();
        }

        break;

    default:
        break;
}
?>
