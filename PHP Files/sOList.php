<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
    $query="SELECT * FROM sponsor";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["slist"] = array();
        while ($row = mysqli_fetch_array($result))
        {
            $sponsor = array();
            $sponsor["sId"] = $row["sId"];
            $sponsor["sName"] = $row["sName"];
            array_push($response["slist"], $sponsor);
        }
        echo json_encode($response);
    } 
    else 
    {
        $response["slist"] = "";
        echo json_encode($response);
    }


?>