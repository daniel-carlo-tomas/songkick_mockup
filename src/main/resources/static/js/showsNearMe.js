"use strict";

(function () {

    let zipcode = document.getElementById("zipcode");
    zipcode = zipcode.getAttribute('value');
    console.log(zipcode);

    $.ajax({
        type: "GET",
        url: "http://api.jambase.com/events",
        data: {"zipcode": zipcode, "api_key": "suakw9fxjerxgssx95s993rd"},
        // IS HARD CODED...NEED TO SWITCH TO ZIPCODE AFTER VERIFYING THAT ZIPCODE IS CORRECT IN MODEL
        async: true,
        dataType: "json",
        success: function (json) {
            console.log(json);
            let html = "";

            html += "<h4>Shows Near " + zipcode + "</h4>";

            for (let i = 0; i <= 4; i++) {

                html += "<div>" +
                    "<h5>" + json.Events[i].Artists.map(artist => artist.Name).join(", ") + " At " + json.Events[i].Venue.Name + "</h5>";

                html += "<a href='/show/" + json.Events[i].Id + "/moreInfo'>See More</a>";
                html += "</div>";
                // console.log(json.Events[i].Artists.map(artist => artist.Name).join(", "));
                // console.log(json.Events[i].Venue.Name);
            }
            document.getElementById("showsnearme").innerHTML = html;

        },

        error: function (xhr, status, err) {
            console.log(err)
        }
    })
})();