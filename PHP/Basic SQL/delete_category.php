<?php
require_once('database.php');

// Get IDs
$category_id = filter_input(INPUT_POST, 'category_id', FILTER_VALIDATE_INT);

// Delete the category from the database
if ($category_id != FALSE) {
    $query = 'DELETE FROM categories
              WHERE categoryID = :id';
    $statement = $db->prepare($query);
    $statement->bindValue(':id', $category_id);
    $success = $statement->execute();
    $statement->closeCursor();    
}

// Display the Product List page
include('category_list.php');

