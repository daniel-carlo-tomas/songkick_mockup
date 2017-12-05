"use strict";

(function () {

    let zipcode = document.getElementById("zipcode");
    zipcode = zipcode.getAttribute('value');
    console.log(zipcode);

    $.ajax({
        type: "GET",
        url: "http://api.jambase.com/events",
        data: {"zipcode": zipcode, "api_key": "h3fxhwz2qkyc5u8dtd4dbw9c"},
        async: true,
        dataType: "json",
        success: function (json) {
            console.log(json);
            let html = "";
            html += "<div class='row'><div class='col s12'>";
            html += "<h4>Shows Near You!</h4></div></div>";

            for (let i = 0; i <= 3; i++) {
                html += i % 2 === 0 ?  "<div class='row'>" : '';
                // html += "<i class='medium material-icons'>event</i>"


                html += "<div class='col s12 m6'><div class='card white center' style='min-height: 15em'><div class='card-content'>" +
                    "<h5>" + json.Events[i].Artists.map(artist => artist.Name).join(", ") + " At " + json.Events[i].Venue.Name + "</h5></div><div class='card-action'>";

                html += "<a class='btn-large waves-effect waves-light orange darken-1' href='/show/" + json.Events[i].Id + "/moreInfo'>See More</a>";
                html += "</div></div></div>";
                // console.log(json.Events[i].Artists.map(artist => artist.Name).join(", "));
                // console.log(json.Events[i].Venue.Name);
                html += i % 2 === 1 ? '</div>' : '';
            }
            // html += "</div>";
            document.getElementById("showsnearme").innerHTML = html;

        },

        error: function (xhr, status, err) {
            console.log(err)
        }
    })
})();