<?php
	$mysqli = new mysqli("localhost", 'arlenb_362user', '362pass', "arlenb_coms362db");

	/* check connection */
	if ($mysqli->connect_errno) {
		printf("Connect failed: %s\n", $mysqli->connect_error);
		exit();
	}
 
	$str = $_REQUEST['query'];
	$str = str_replace("+"," ",$str);//replace plus signs with spaces

	$myArray = array();
	$result = $mysqli->query($str);
	if($result->num_rows == 0){
		echo $mysqli->error;
	}
	else{

		while($row = $result->fetch_array(MYSQL_ASSOC)) {
				$myArray[] = $row;
		}
		echo json_encode($myArray);
	}

	if($result->num_rows != 0)$result->close();
	$mysqli->close();

?>