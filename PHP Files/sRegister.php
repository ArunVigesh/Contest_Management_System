<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if(isset($_POST['sId'],$_POST['sName'],$_POST['eId']))
{
$sId = $_POST['sId'];
$sName = $_POST['sName'];
$eId=$_POST['eId'];
$post_query = "insert into Sponsor(sId,sName) values ('$sId', '$sName')";
$post_submit = mysqli_query($con, $post_query) or die(mysqli_error($con));


$sponsor_query = "insert into sponsors(sId,eId) values ('$sId', '$eId')";
$sponsor_submit = mysqli_query($con, $sponsor_query) or die(mysqli_error($con));

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
if ($sponsor_submit) {
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