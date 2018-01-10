
<?php
if(isset($_GET["data"]))
{
require_once 'database.php';
$pdo = Database::connect();
$stmt="Select * from profile where profile_id=".$_GET["data"];
$pr=$pdo->prepare($stmt);
$pr->execute();
$row = $pr->fetch(PDO::FETCH_ASSOC);
Database::disconnect();

}


 ?>
 <div class="container">
<h1>Profile information</h1>
<p>First Name:
<?=$row['first_name'] ?></p>
<p>Last Name:
<?=$row['last_name'] ?></p>
<p>Email:
<?=$row['email'] ?></p>
<p>Headline:<br/>
<?=$row['headline'] ?></p>
<p>Summary:<br/>
<?=$row['summary'] ?><p>
</p>
<a href="index.php">Done</a>
</div>
 <style>
body{font-size: 1.2em;
margin: 20px;}
 h1{color: grey;
 font-family: "Arial";}
 p{color:black;
 background-color: #f0f0f0;}
 </style>
