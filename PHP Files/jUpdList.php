<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if(isset($_POST['eId']))
{
    $eId=$_POST['eId'];
    $query="SELECT DISTINCT * FROM judge natural join judges where eId='$eId'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["judgelist"] = array();
        while ($row = mysqli_fetch_array($result))
        {
            $judge = array();
            $judge["jId"] = $row["jId"];
            $judge["jFirstName"] = $row["jFirstName"];
            $judge["jLastName"] = $row["jLastName"];
            $judge["jSpecialization"] = $row["jSpecialization"];
            array_push($response["judgelist"], $judge);
        }
        echo json_encode($response);
    } 
        else 
    {
        $response["judgelist"] = "";
        echo json_encode($response);
    }
}

?>