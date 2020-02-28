<?php
$response = array();
$con = mysqli_connect("localhost", "cms", "cms", "cms") or die(mysqli_error($con));
if (isset($_POST['oEmail'], $_POST['oPassword']))
{
    $oEmail = $_POST['oEmail'];
    $oPassword = $_POST['oPassword'];
    $query="SELECT * FROM organizer where oEmail='$oEmail' and oPassword='$oPassword'";
    $result = mysqli_query($con,$query) or die(mysqli_error($con));
    if (mysqli_num_rows($result) > 0) 
    {
        $response["getlist"] = array();
 
        while ($row = mysqli_fetch_array($result))
        {
            $oLogin = array();
            $oLogin["oId"] = $row["oId"];
            array_push($response["getlist"], $oLogin);
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