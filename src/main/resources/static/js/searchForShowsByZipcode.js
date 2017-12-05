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
            data: {"zipcode": searchZip, "radius": searchRadius, "api_key": "j6g84upymucqbgr42fqze97b"},
            async: true,
            dataType: "json",
            success: function (json) {
                // Parse the response.
                // Do other things.
                let html = "";
                html += "<div class='row'><div class='col s12 center'>";

                html += "<h3>Shows within " + searchRadius + " miles of " + searchZip + ":</h3>";

                html += "</div></div>";

                let arrayMax = 0;

                if (json.Events.length >= 10) {
                    arrayMax = (10);
                } else {
                    arrayMax = json.length;
                }

                for (let i = 0; i < arrayMax; i++) {
                    html += i % 2 === 0 ?  "<div class='row'>" : '';
                    let events = json.Events[i];
                //for (let events of json.Events) {
                    html += "<div class='col s12 m6'><div class='card white center'><div class='card-content'>";
                    let artists = events.Artists.map(artist => artist.Name).join(', ');
                    html += "<i class='medium material-icons'>event</i>";
                    html += "<span class='card-title'>Artists: " + artists + "</span>";

                    html += "<p>Venue: " + events.Venue.Name + "</p>";

                    html += "<p>Date: " + events.Date + "</p></div>";

                    html += "<div class='card-action'><form method='post' style='padding: 2em' action='/show/add'>" +

                        "<input class=\"btn-large waves-effect waves-light orange darken-1\" type='submit' value='Add to My Shows' />" +
                        "<input name='id' type='hidden' value=" + events.Id + " />" +
                        "<input name='artists' type='hidden' value='" + artists + "' />" +
                        "<input name='venue' type='hidden' value='" + events.Venue.Name + "' />" +
                        "<input name='_csrf' type='hidden' value=" + $('#csrf').val() + " />" +

                        "</form>";

                    html += "<a class=\"btn-large waves-effect waves-light orange darken-1\" href='/show/" + events.Id + "/moreInfo'>See More</a>";

                    html += "</div></div></div>";
                    html += i % 2 === 1 ? '</div>' : '';
                //}
                }
                // html += "</div>";

                console.log(html);
                document.getElementById("searchResult").innerHTML = html;
            },
            error: function (xhr, status, err) {
                console.log(err)
            }
        });
    })
}());