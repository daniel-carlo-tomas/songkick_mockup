"use strict";

(function () {

    let submit = document.getElementById("submit").addEventListener("click", function (event) {

        event.preventDefault();

        let userSearch = document.getElementById("bandname");
        userSearch = userSearch.value;

        $.ajax({
            type: "GET",
            url: "http://api.jambase.com/artists",
            data: {"name": userSearch, "api_key": "h3fxhwz2qkyc5u8dtd4dbw9c"},
            async: true,
            dataType: "json",
            success: function (json) {
                // Parse the response.
                // Do other things.
                console.log(json);
                let html = "";
                var bandName= json.Artists[0].Name;
                html += "<h1>Showing results for '" + userSearch + "': </h1>";
                html += "<h2>Bandname: " + bandName + "</h2>";
                html += "<form method='post' action='/band/add'>" +
                    "<input class='btn form-btn' type='submit' value='Add to My Bands' />" +
                    "<input name='jambase_bandname' type='hidden' value='" + bandName + "' />" +
                    "<input name='jambase_id' type='hidden' value=" + json.Artists[0].Id + " />" +
                    "<input name='_csrf' type='hidden' value=" + $('#csrf').val() + " />" +
                    "</form>";
                document.getElementById("searchResult").innerHTML = html;
            },
            error: function (xhr, status, err) {
                // This time, we do not end up here!
            }
        });
    });
})();