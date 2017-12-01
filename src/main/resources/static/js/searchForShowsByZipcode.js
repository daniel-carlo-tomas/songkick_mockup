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
            data: {"zipcode": searchZip, "radius": searchRadius, "api_key": "suakw9fxjerxgssx95s993rd"},
            async: true,
            dataType: "json",
            success: function (json) {
                // Parse the response.
                // Do other things.
                let html = "";
                html += "<h1>Shows within " + searchRadius + " miles of " + searchZip + ":</h1>";
                for (let events of json.Events) {
                    html += "<div>";
                    let artists = events.Artists.map(artist => artist.Name).join(', ');
                    html += "<h2>Artists: " + artists + "</h2>";

                    html += "<h3>Date: " + events.Date + "</h3>";

                    html += "<h3>Venue: " + events.Venue.Name + "</h3>";

                    html += "<form method='post' action='/show/add'>" +

                        "<input type='submit' value='Add to My Shows' />" +
                        "<input name='id' type='hidden' value=" + events.Id + " />" +
                        "<input name='artists' type='hidden' value='" + artists + "' />" +
                        "<input name='venue' type='hidden' value='" + events.Venue.Name + "' />" +
                        "<input name='_csrf' type='hidden' value=" + $('#csrf').val() + " />" +

                        "</form>";

                    html += "<a href='/show/" + events.Id + "/moreInfo'>See More</a>";

                    html += "</div>";
                }

                console.log(html);
                document.getElementById("searchResult").innerHTML = html;
            },
            error: function (xhr, status, err) {
                // This time, we do not end up here!
            }
        });
    })
}());