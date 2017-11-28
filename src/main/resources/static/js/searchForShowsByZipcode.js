"use strict";

(function () {
    let submit = document.getElementById("submit").addEventListener("click", function (event) {

        console.log("hello");
        event.preventDefault();

        let searchZip = document.getElementById("zipcode");
        searchZip = searchZip.value;
        console.log(searchZip);

        let searchRadius = document.getElementById("raduis");
        searchRadius = searchRadius.value;
        console.log(searchRadius);

        $.ajax({
            type: "GET",
            url: "http://api.jambase.com/artists",
            data: {"zipcode" : searchZip, "radius" : searchRadius , "api_key": "h3fxhwz2qkyc5u8dtd4dbw9c"},
            async: true,
            dataType: "json",
            success: function (json) {
                console.log(json);
                // Parse the response.
                // Do other things.

            },
            error: function (xhr, status, err) {
                // This time, we do not end up here!
            }
        });
    })
}());