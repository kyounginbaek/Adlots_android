<meta charset="utf-8">
<?
define('__XE__', true);

/*
require_once("/host/home1/filenanumi/html/config/config.inc.php");
$oContext = &Context::getInstance();
 
$oContext->init();
*/
/*
require_once("/host/home1/filenanumi/html/modules/member/member.class.php");
*/

$DB['host'] = 'localhost';
$DB['db'] = 'bluery3';
$DB['id'] = 'bluery3';
$DB['pw'] = 'qortl5882!';

$regsqli = new mysqli($DB['host'], $DB['id'], $DB['pw'], $DB['db']);
// mysql_set_charset("euckr",$regsqli);
$regsqli->set_charset("utf8");

$user_id = $_REQUEST['user_id'];
$user_pw = $_REQUEST['user_pw'];
$user_email = $_REQUEST['user_email'];
$user_nick = $_REQUEST['user_nick'];

if(!$user_id){
echo "ERROR: 아이디를 입력해주세요";
exit;
}
if(!$user_pw){
echo "ERROR: 비밀번호를 입력해주세요";
exit;
}
if(!$user_email){
echo "ERROR: 이메일을 입력해주세요";
exit;
}
if(!$user_nick){
echo "ERROR: 닉네임을 입력해주세요";
exit;
}

$q = "INSERT INTO smart_users ( user_id, user_pw, user_email, user_nick ) VALUES ( '$user_id', '$user_pw', '$user_email', '$user_nick' )";

$result = mysqli_query($regsqli, $q) or die(mysqli_error($regsqli));
echo 'SUCCESS: 가입이 완료되었습니다';

$regsqli->close();

?>
