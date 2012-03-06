<?
$ret = main();

$json =  json_encode(array(value => $ret));
echo $json;

function main(){
    global $argv;
    $opts = parseArgs($argv);

    //print_r($opts);
    if($opts['active']){
        return isActive();
    } else if($opts['getinfo']){
        return getInfo($opts['getinfo']);
    }else
    {
        echo json_encode($argv);
        exit(1);
    }
}


//class GdBridge{
function isActive(){
    return extension_loaded('gd');
}

function getInfo($file){
    //echo "get size $file";
    $size = getimagesize($file);

    //echo "size for $file is $size";
    //print_r($size);

    return array(width=>$size[0], height=>$size[1],mime=>$size['mime']);
}

function addFooter($file){

}

function parseArgs($argv){
    array_shift($argv); $o = array();
    foreach ($argv as $a){
        if (substr($a,0,2) == '--'){ $eq = strpos($a,'=');
            if ($eq !== false){ $o[substr($a,2,$eq-2)] = substr($a,$eq+1); }
            else { $k = substr($a,2); if (!isset($o[$k])){ $o[$k] = true; } } }
        else if (substr($a,0,1) == '-'){
            if (substr($a,2,1) == '='){ $o[substr($a,1,1)] = substr($a,3); }
            else { foreach (str_split(substr($a,1)) as $k){ if (!isset($o[$k])){ $o[$k] = true; } } } }
        else { $o[] = $a; } }
    return $o;
}
?>