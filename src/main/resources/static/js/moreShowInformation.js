"use strict";

(function () {

    let show_id = document.getElementById("show_id");
    show_id = show_id.getAttribute('value');
    console.log(show_id);

    $.ajax({
        type: "GET",
        url: "http://api.jambase.com/events",
        data: {"id": show_id, "api_key": "suakw9fxjerxgssx95s993rd"},
        async: true,
        dataType: "json",
        success: function (json) {
            // Parse the response.
            // Do other things.
            console.log(json);

            let html = "";
            html += "<h1>";
            for (let artist of json.Artists) {
                html += json.Artists[0].Name + " "
            }
            html += " at "
                + json.Venue.Name
                + "</h1>";

            html += "<h3>Date: " + json.Date + "</h3>";

            html += "<h4>Address: " + json.Venue.Address + ", " +
                json.Venue.City + ", " +
                json.Venue.StateCode +
                json.Venue.ZipCode +
                "</h4>";
            html += "<a href='" + json.Venue.Url + "'>See Venue</a>";

            console.log(html);

            document.getElementById("moreInfo").innerHTML = html;

            let mapOptions = {
                zoom: 15,
            };
            let map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

            let address = json.Venue.Address;
            let geocoder = new google.maps.Geocoder();

            geocoder.geocode({"address": address}, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    map.setCenter(results[0].geometry.location);
                    let address2 = (results[0].geometry.location);

                    let marker = new google.maps.Marker({
                        position: address2,
                        animation: google.maps.Animation.DROP,
                        map: map
                    });

                } else {
                    alert("Geocoding was not successful - STATUS: " + status);
                }
            });

        },
        error: function (xhr, status, err) {
            // This time, we do not end up here!
        }

    });
}());