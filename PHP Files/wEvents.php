<?php
$response = array();
$response1 = array();
$response2 = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if(isset($_POST['pId']))
{
$pId = $_POST['pId'];
$query = "SELECT pId,eId,max(eScore) from participantevents group by eId";
$result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["getlist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $ids = array();
            $ids["pId"] = $row["pId"];
            $ids["eId"] = $row["eId"];
            $p=$row["pId"];
            $e=$row["eId"];
            $push_query="insert into winner ( eId , pIdWon ) values ('$e','$p')";
            $post_submit = mysqli_query($con, $push_query) or die(mysqli_error($con));
            if ($post_submit) {

        // successfully inserted into database
        $response1["success"] = 1;
        $response1["message"] = "Data successfully Stored.";
// echoing JSON response
       // echo json_encode($response1);



        $querywin = "SELECT DISTINCT eEventName,eDescription from winner natural join event where pIdWon='$pId'";
        $results = mysqli_query($con,$querywin) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response2["wonlist"] = array();
 
        while ($row = mysqli_fetch_array($results))
        {
            $winner = array();
            $winner["eEventName"] = $row["eEventName"];
            $winner["eDescription"] = $row["eDescription"];
            array_push($response2["wonlist"], $winner);
        }
        echo json_encode($response2);
    } 
    else 
    {
        $response["wonlist"] = "";
        echo json_encode($response2);
    }
 




        
        } 
        else {
        // failed to insert row
        $response1["success"] = 0;
        $response1["message"] = "Oops! An Error occurred.";
 
        // echoing JSON response
       // echo json_encode($response1);
    }
            array_push($response["getlist"], $ids);
        }
        //echo json_encode($response);
    } 
    else 
    {
        $response["getlist"] = "";
        //echo json_encode($response);
    }
}
?>