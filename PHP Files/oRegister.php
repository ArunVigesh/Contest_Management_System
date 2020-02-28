<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if(isset($_POST['oId'],$_POST['oFirstName'],$_POST['oLastName'],$_POST['oEmail'],$_POST['oPhone'],$_POST['oPassword']))
{
$oId = $_POST['oId'];
$oFirstName = $_POST['oFirstName'];
$oLastName = $_POST['oLastName'];
$oEmail = $_POST['oEmail'];
$oPhone = $_POST['oPhone'];
$oPassword = $_POST['oPassword'];
$post_query = "insert into Organizer(oId,oFirstName,oLastName,oEmail,oPhone,oPassword) values ('$oId', '$oFirstName','$oLastName','$oEmail','$oPhone','$oPassword')";
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
}
?>