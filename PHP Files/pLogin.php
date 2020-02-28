<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if (isset($_POST['pEmail'], $_POST['pPassword']))
{
    $pEmail = $_POST['pEmail'];
    $pPassword = $_POST['pPassword'];
    $query="SELECT * FROM participant where pEmail='$pEmail' and pPassword='$pPassword'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["getlist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $pLogin = array();
            $pLogin["pId"] = $row["pId"];
            array_push($response["getlist"], $pLogin);
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