<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if(isset($_POST['pId']))
{
$pId = $_POST['pId'];
    $query="SELECT DISTINCT * FROM event natural join  participantevents WHERE pId='$pId'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["eventlist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $event = array();
            $event["eEventName"] = $row["eEventName"];
            $event["eDescription"] = $row["eDescription"];
            $event["eDate"] = $row["eDate"];
            $event["eTime"] = $row["eTime"];
            array_push($response["eventlist"], $event);
        }
        echo json_encode($response);
    } 
    else 
    {
        $response["getlist"] = "";
        echo json_encode($response);
    }
}

?>