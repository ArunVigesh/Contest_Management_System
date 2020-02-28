<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
$pId = $_POST['pId'];
$pFirstName = $_POST['pFirstName'];
$pLastName = $_POST['pLastName'];
$pEmail = $_POST['pEmail'];
$pPhone = $_POST['pPhone'];
$pCollege = $_POST['pCollege'];
$pPassword = $_POST['pPassword'];
$post_query = "insert into participant(pId,pFirstName,pLastName,pEmail,pPhone,pCollege,pPassword) values ('$pId', '$pFirstName','$pLastName','$pEmail','$pPhone','$pCollege','$pPassword')";
$post_submit = mysqli_query($con, $post_query) or die(mysqli_error($con));
if ($post_submit) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Data successfully Stored.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An Error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
?>