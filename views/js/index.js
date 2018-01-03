function insertRow() {
    var datajs=sessionScope.data;
var table = document.querySelector("#myTable");
for (var i = 0; i < datajs.length; ++i) {
    // without parameters, insert at the end,
    // otherwise parameter = index where the row will be inserted
    var row = table.insertRow();

    let p = datajs[i];
    row.innerHTML = "<td>" + p.name + "</td><td>" + p.uname + "</td><td>" + p.passname + "</td><td>" + p.mbno+ "</td>";
}}


function deleteFirstRow() {
    var table = document.querySelector("#myTable");
    table.deleteRow(1); // 0 is the header
}

function compareValues(input) {

    let myTable = document.querySelector('#myTable');
    // console.log(myTable);
    let inputLength = input.value.length;
    input = input.value.toLowerCase();//lower case for comparison

    let countMatch = 0;
    let feedBack = document.querySelector('#notFound');
    //start loop at 1 to skip header
    for (var i = 1; i < myTable.rows.length; ++i) {
        //a - first and second cell of each row 
        //b -slice name to match lengh of input
        //c -set all tables to default color
        //d - if input matches, change color and input is not empty
        //e - change the color

        let name = myTable.rows[i].cells[0].innerText;//a
        //let lastName = myTable.rows[i].cells[1].innerText;
        let fullName = name ;//+ " " + lastName;

        fullName = fullName.slice(0, inputLength);//b
        fullName = fullName.toLowerCase();

        myTable.rows[i].style.backgroundColor = '#ffffff';//c

        if ((input === fullName) && (inputLength !== 0)) {//d
            myTable.rows[i].style.backgroundColor = '#ccff99';//e
            countMatch++;
        }
    }

    //display message if there is a match or not
    if (countMatch === 0) {
        feedBack.value = 'Sorry, no contact found.';
    } else {
        feedBack.value = countMatch + ' ' + 'contacts match.';
    }
}




