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
