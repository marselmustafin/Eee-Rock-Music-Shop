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
            }).done(function(){
                updateComments(id);
            });
            return false;
        })

    });
});

function updateComments(id) {
    $.ajax({
        url: "commentlist.jsp?id=" + id,
        type: "GET"
    }).done(function(data) {
        var container = $('#comments');
        container.html(data);
        container.hide();
        container.show(350);
    })
}
