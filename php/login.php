<meta charset="utf-8">
<?php

// define('__XE__', true);

$id = $_REQUEST["user_id"];
$pw = $_REQUEST["user_pw"];

if (!$id){
echo "ERROR: 아이디를 입력하세요";
exit;
}
if (!$pw){
echo "ERROR: 비밀번호를 입력하세요";
exit;
}

//echo $id. '<br />';
//echo $pw. '<br />';

$DB['host'] = 'localhost';
$DB['db'] = '설정한아이디';
$DB['id'] = '설정한아이디';
$DB['pw'] = '설정한비밀번호';

$logsqli = new mysqli($DB['host'], $DB['id'], $DB['pw'], $DB['db']);

$q = "select user_pw from smart_users where user_id = '".$id."' limit 1";
//echo $q. '<br />';

$result = mysqli_query($logsqli, $q) or die(mysqli_error($logsqli));
$value = mysqli_fetch_field($result);
$row = $result->fetch_array(MYSQLI_NUM);

//echo $row[0];

$qnick = "select user_nick from smart_users where user_id = '".$id."'";
//echo $qnick. '<br />';

$resultnick = mysqli_query($logsqli, $qnick) or die(mysqli_error($logsqli));
$rownick = $resultnick->fetch_array(MYSQLI_NUM);

//echo $rownick[0];

if (!$row[0]){
echo "ERROR: 등록된 아이디가 아닙니다";
exit;
}

if ($row[0] == $pw)
{
	$response = iconv('euckr', 'utf-8', $rownick[0]);
	echo $response;
} else {
	echo "ERROR: 비밀번호가 일치하지 않습니다";
}

$logsqli->close();

?>