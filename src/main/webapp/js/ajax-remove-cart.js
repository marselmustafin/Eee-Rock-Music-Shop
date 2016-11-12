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
        url: "productlist.jsp",
        type: "GET"
    }).done(function(data) {
        var container = $('#products');
        container.html(data);
        container.hide();
        container.show(350);
    })
}
