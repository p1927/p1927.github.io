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
if (isset($_POST['first_name']))
{ if (
  strlen($_POST['first_name'])<1||
  strlen($_POST['last_name'])<1||
  strlen($_POST['email'])<1||
  strlen($_POST['headline'])<1||
  strlen($_POST['summary'])<1
  )
  {$failure="All fields are required";
  header("Location:add.php?msg=".$failure);
  }
  elseif(strpos($_POST['email'],"@")<1)
  { $failure="Email address must contain @";
  header("Location:add.php?msg=".$failure);
  }
else
{
  require_once 'database.php';
    $pdo = Database::connect();
    $stmt = $pdo->prepare('INSERT INTO Profile
        (user_id, first_name, last_name, email, headline, summary)
        VALUES ( :uid, :fn, :ln, :em, :he, :su)');
    $stmt->execute(array(
        ':uid' => $_SESSION['user_id'],
        ':fn' => $_POST['first_name'],
        ':ln' => $_POST['last_name'],
        ':em' => $_POST['email'],
        ':he' => $_POST['headline'],
        ':su' => $_POST['summary'])
    );
      Database::disconnect();
$_SESSION['msg']="Profile Added";
  header("Location:index.php");

}
}

 ?>

<link rel="stylesheet" href="css/bootstrap.css">
<body>

<div class="container">
    <?php echo "<br><h1>Welcome ".$_SESSION['name']."</h1><br>";  ?>
<h3>Add Profile</h3>
<br><p class="err"><?=$failure?></p><br>
<form action="add.php" method="post">
<p>First Name:
<input type="text" name="first_name" size="60"/></p>
<p>Last Name:
<input type="text" name="last_name" size="60"/></p>
<p>Email:
<input type="text" name="email" size="30"/></p>
<p>Headline:<br/>
<input type="text" name="headline" size="80"/></p>
<p>Summary:<br/>
<textarea name="summary" rows="5" cols="80"></textarea>
<p>
<input type="submit" value="Add">
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
