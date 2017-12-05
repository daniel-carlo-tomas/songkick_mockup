"use strict";

(function () {

    let show_id = document.getElementById("show_id");
    show_id = show_id.getAttribute('value');

    $.ajax({
        type: "GET",
        url: "http://api.jambase.com/events",
        data: {"id": show_id, "api_key" : "j6g84upymucqbgr42fqze97b"},
        async: true,
        dataType: "json",
        success: function (json) {
            // Parse the response.
            // Do other things.

            let html = "";
            html += "<h2>";
            // for (let artist of json.Artists) {
            //     html += artist.Name + ", "
            // }

            let artistsList = json.Artists.map(function (artist) {
                return artist.Name;
            }).join(", ");

            html += artistsList;

            html += " at "
                + json.Venue.Name
                + "</h2>";

            html += "<h3>Date: " + json.Date + "</h3>";

            html += "<h4>Address: " + json.Venue.Address + ", " +
                json.Venue.City + ", " +
                json.Venue.StateCode +
                json.Venue.ZipCode +
                "</h4>";

            console.log(html);

            document.getElementById("moreInfo").innerHTML = html;


            let form = "";
            form += "<form method='post' action='/show/add'>";
            form += "<input class='btn form-btn' type='submit' value='Add to My Shows' />" +
                "<input name='id' type='hidden' value='" + json.Id + "' />" +
                "<input name='artists' type='hidden' value='" + artistsList + "' />" +
                "<input name='venue' type='hidden' value='" + json.Venue.Name + "' />" +
                "<input name='_csrf' type='hidden' value=" + $('#csrf').val() + " />" +
                "</form>";

            document.getElementById("form").innerHTML = form;


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