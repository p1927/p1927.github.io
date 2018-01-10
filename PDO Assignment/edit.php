<?php
session_start();
if(!isset($_SESSION['name']))
{die("<pNot logged in</p>");}

if(isset($_POST['cancel']))
{
header("Location:index.php");
return;
  }


$failure="";
if(isset($_GET['msg']))
{$failure=$_GET['msg'];};
require_once 'database.php';
$pdo = Database::connect();
if (isset($_POST['first_name']))
{ if (
  strlen($_POST['first_name'])<1||
  strlen($_POST['last_name'])<1||
  strlen($_POST['email'])<1||
  strlen($_POST['headline'])<1||
  strlen($_POST['summary'])<1
  )
  {$failure="All fields are required";
  header("Location:edit.php?id=".$_GET['id']."&msg=".$failure);
  }
  elseif(strpos($_POST['email'],"@")<1)
  { $failure="Email address must contain @";
  header("Location:edit.php?id=".$_GET['id']."&msg=".$failure);
  }
else
{


    $stmt = $pdo->prepare('update Profile set first_name=:fn, last_name=:ln, email=:em, headline=:he, summary=:su where profile_id=:pid;');
    $stmt->execute(array(
        ':pid' => $_GET['id'],
        ':fn' => $_POST['first_name'],
        ':ln' => $_POST['last_name'],
        ':em' => $_POST['email'],
        ':he' => $_POST['headline'],
        ':su' => $_POST['summary'])
    );
$_SESSION['msg']="Profile Updated";
  header("Location:index.php");

}
}

$sql = "SELECT * FROM profile where profile_id=".$_GET['id'];
$result=$pdo->query($sql);
$result=$result->fetch(PDO::FETCH_ASSOC);
  Database::disconnect();
 ?>

<link rel="stylesheet" href="css/bootstrap.css">
<body>

<div class="container">
    <?php echo "<br><h1>Welcome ".$_SESSION['name']."</h1><br>";  ?>
<h3>Edit Profile</h3>
<br><p class="err"><?=$failure?></p><br>
<form action="edit.php?id=<?=$_GET['id']?>" method="post">
<p>First Name:
<input type="text" name="first_name" size="60" value="<?=$result['first_name']?>" /></p>
<p>Last Name:
<input type="text" name="last_name" size="60"  value="<?=$result['last_name']?>" /></p>
<p>Email:
<input type="text" name="email" size="30" value="<?=$result['email']?>"  /></p>
<p>Headline:<br/>
<input type="text" name="headline" size="80" value="<?=$result['headline']?>"  /></p>
<p>Summary:<br/>
<textarea name="summary" rows="5" cols="80" ><?=$result['summary']?></textarea>
<p>
<input type="submit" value="Save">
<input type="submit" name="cancel" value="Cancel">
</p>
</form>
</div>
<style>
.err{ color:red;
  background-color: lightgrey;
}

</style>
</body>
