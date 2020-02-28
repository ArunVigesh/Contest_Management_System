<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if(isset($_POST['pId'],$_POST['eId']))
{
$pId = $_POST['pId'];
$eId = $_POST['eId'];
$post_query = "insert into participantevents(pId,eId,eScore) values ('$pId', '$eId','0')";
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