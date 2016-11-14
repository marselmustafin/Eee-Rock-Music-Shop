$(document).ready(function () {
    $('.removefromcart').each(function () {
        var form = $(this);
        form.submit(function () {
            var id = form.find('.prodid').val();
            $.ajax({
                url: "removefromcart",
                type: "POST",
                data: {
                    "prodid": id
                }
            }).done(function (data) {
                update();
                alert(data);
            });
            return false;
        })

    });
});

function update() {
    $.ajax({
        url: "/views/productlist.jsp",
        type: "GET"
    }).done(function (data) {
        var container = $('#products');
        container.html(data);
        container.hide();
        container.show(350);
    })
}

$(document).ready(function () {
    $('.buy').each(function () {
        var form = $(this);
        form.submit(function () {
            $.ajax({
                url: "buy",
                type: "POST",
                data: ""
            }).done(function (data) {
                update();
                alert(data);
            });
            return false;
        })

    });
});

$(document).ready(function () {
    $('.addform').each(function () {
        var form = $(this);

        form.submit(function () {
            var id = form.find('.prodid').val();
            var quantity = form.find('.prodq').val();

            $.ajax({
                url: "addtocart",
                type: "POST",
                data: {
                    "prodq": quantity,
                    "prodid": id
                }
            }).done(function (data) {
                alert(data)
            });

            return false;
        })

    });
});

$(document).ready(function () {
    $('.commentform').each(function () {
        var form = $(this);
        form.submit(function () {
            var id = form.find('.productid').val();
            var text = form.find('.msg').val();
            $.ajax({
                url: "comment_operation",
                type: "POST",
                data: {
                    "productid": id,
                    "msg": text
                }
            }).done(function () {
                updateComments(id);
            });
            return false;
        })

    });
});

function updateComments(id) {
    $.ajax({
        url: "/views/commentlist.jsp?id=" + id,
        type: "GET"
    }).done(function (data) {
        var container = $('#comments');
        container.html(data);
        container.hide();
        container.show(350);
    })
}

$(document).ready(function () {
    $("body").css("display", "none");

    $("body").fadeIn(600);

    $("a.fade").click(function (event) {
        event.preventDefault();
        linkLocation = this.href;
        $("body").fadeOut(600, redirectPage);
    });

    function redirectPage() {
        window.location = linkLocation;
    }
});


