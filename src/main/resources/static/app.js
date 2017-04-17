var board, rows, columns;
var animating = false;
var animation;

var $row = $("<div />", {
    class: 'row'
});
var $square = $("<div />", {
    class: 'square'
});

$(document).ready(function () {
    //add columns to the the temp row object
    fillBoard();
});

$("#new").click(function () {
    if (!animating) {
        rows = $("#height").val();
        columns = $("#width").val();
        board = Array(rows * columns).fill(0);
        fillBoard();
    }
});

$("#next").click(function () {
    if (!animating) {
        next();
    }
});

$("#play").click(function () {
    if (animating) {
        $(this).text("Play");
        clearInterval(animation);
        animating = false;
    } else {
        animating = true;
        animation = setInterval(function () {
            next();
        }, 100);
        $(this).text("Stop");
    }
});


function fillBoard() {
    $("#board").empty();
    for (var i = 0; i < rows; i++) {
        var row = $row.clone();
        for (var j = 0; j < columns; j++) {
            var cell = $square.clone();
            if (board[i * rows + j] === 1) {
                cell.addClass('filled');
            }
            cell.attr('index', i * rows + j);

            cell.click(function () {
                if (!animating) {
                    var value = board[$(this).attr("index")];
                    if (value === 1) {
                        board[$(this).attr("index")] = 0;
                        $(this).attr("class", "square");
                    } else {
                        board[$(this).attr("index")] = 1;
                        $(this).attr("class", "square filled");
                    }
                }
            });

            row.append(cell);
        }
        $("#board").append(row);
    }
}

function next() {
    var arr = {"content": board, "width": columns, "height": rows};
    $.ajax({
        url: 'board',
        type: 'POST',
        data: JSON.stringify(arr),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: true,
        success: function (data) {
            board = data.content;
            fillBoard();
        }
    });

}