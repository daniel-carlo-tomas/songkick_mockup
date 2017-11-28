"use strict";

(function () {
    let submit = document.getElementById("submit").addEventListener("click", function (event) {

        event.preventDefault();

        let searchZip = document.getElementById("zipcode");
        searchZip = searchZip.value;

        let searchRadius = document.getElementById("radius");
        searchRadius = searchRadius.value;

        $.ajax({
            type: "GET",
            url: "http://api.jambase.com/events",
            data: {"zipcode": searchZip, "radius": searchRadius, "api_key": "h3fxhwz2qkyc5u8dtd4dbw9c"},
            async: true,
            dataType: "json",
            success: function (json) {
                // Parse the response.
                // Do other things.
                let html = "";
                html += "<h1>Shows within " + searchRadius + " miles of " + searchZip + ":</h1>";
                for (let events of json.Events) {
                    html += "<div>";
                    html += "<h2>Artists: ";
                    for (let artist of events.Artists) {
                        html += artist.Name;
                        html += " ";
                    }
                    html += "</h2>";
                    html += "<h3>Date: " + events.Date + "</h3>";
                    html += "<h3>Venue: " + events.Venue.Name + "</h3>";
                    html += "<form method='post' action='/show/add'>" +
                        "<input type='submit' value='Add to My Shows' />" +
                        "<input name='show_id' type='hidden' value=" + events.Id + " />" +
                        "</form>";
                    html += "<a href='/show/" + events.Id + "/moreInfo'>See More</a>";
                    html += "</div>";
                }

                document.getElementById("searchResult").innerHTML = html;
            },
            error: function (xhr, status, err) {
                // This time, we do not end up here!
            }
        });
    })
}());