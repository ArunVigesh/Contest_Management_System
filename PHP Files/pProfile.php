<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));

    $pId = $_POST['pId'];
    $query="SELECT * FROM participant where pId='$pId'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["getlist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $profile = array();
            $profile["pFirstName"] = $row["pFirstName"];
            $profile["pLasttName"] = $row["pLastName"];
            $profile["pEmail"] = $row["pEmail"];
            $profile["pPhone"] = $row["pPhone"];
            $profile["pCollege"] = $row["pCollege"];
            array_push($response["getlist"], $profile);
        }
        echo json_encode($response);
    } 
    else 
    {
        $response["getlist"] = "";
        echo json_encode($response);
    }


?>