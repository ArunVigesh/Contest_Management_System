<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if(isset($_POST['eId']))
{
    $eId = $_POST['eId'];
    $query="SELECT DISTINCT * FROM participant natural join participantevents where eId='$eId'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["partlist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $profile = array();
            $profile["pId"] = $row["pId"];
            $profile["pFirstName"] = $row["pFirstName"];
            $profile["pLasttName"] = $row["pLastName"];
            $profile["pCollege"] = $row["pCollege"];
            array_push($response["partlist"], $profile);
        }
        echo json_encode($response);
    } 
    else 
    {
        $response["partlist"] = "";
        echo json_encode($response);
    }

}
?>