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
                let html = "";
                html += "<h1>Showing results for '" + userSearch + "': </h1>";
                html += "<h2>Bandname: " + json.Artists[0].Name + "</h2>";
                html += "<form method='post' action='/band/add'>" +
                    "<input type='submit' value='Add to My Bands' />" +
                    "<input name='jambase_bandname' type='hidden' value=" + json.Artists[0].Name + " />" +
                    "<input name='jambase_id' type='hidden' value=" + json.Artists[0].Id + " />" +
                    "</form>";
                document.getElementById("searchResult").innerHTML = html;
            },
            error: function (xhr, status, err) {
                // This time, we do not end up here!
            }
        });
    });
})();