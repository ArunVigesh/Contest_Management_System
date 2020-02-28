<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if(isset($_POST['jId'],$_POST['eId'],$_POST['jFirstName'],$_POST['jLastName'],$_POST['jEmail'],$_POST['jPhone'],$_POST['jSpecialization']))
{
$jId = $_POST['jId'];
$eId = $_POST['eId'];
$jFirstName = $_POST['jFirstName'];
$jLastName = $_POST['jLastName'];
$jEmail = $_POST['jEmail'];
$jPhone = $_POST['jPhone'];
$jSpecialization = $_POST['jSpecialization'];

$post_query = "insert into Judge(jId,jFirstName,jLastName,jEmail,jPhone,jSpecialization) values ('$jId', '$jFirstName','$jLastName','$jEmail','$jPhone','$jSpecialization')";
$post_submit = mysqli_query($con, $post_query) or die(mysqli_error($con));


$judge_query = "insert into judges(jId,eId) values ('$jId', '$eId')";
$judge_submit = mysqli_query($con, $judge_query) or die(mysqli_error($con));
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
if ($judge_submit) {
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