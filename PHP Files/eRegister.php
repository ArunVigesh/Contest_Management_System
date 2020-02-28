<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if(isset($_POST['eId'],$_POST['eEventName'],$_POST['eDescription'],$_POST['eTime'],$_POST['eDate'],$_POST['ePrizeMoney'],$_POST['eGoodies'],$_POST['eVouchers']))

{
    $eId = $_POST['eId'];
$eEventName = $_POST['eEventName'];
$eDescription = $_POST['eDescription'];
$eTime = $_POST['eTime'];
$eDate = $_POST['eDate'];
$ePrizeMoney = $_POST['ePrizeMoney'];
$eGoodies = $_POST['eGoodies'];
$eVouchers = $_POST['eVouchers'];

$post_query = "insert into Event(eId,eEventName,eDescription,eTime,eDate,ePrizeMoney,eGoodies,eVouchers) values ('$eId', '$eEventName','$eDescription','$eTime','$eDate','$ePrizeMoney','$eGoodies','$eVouchers')";
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