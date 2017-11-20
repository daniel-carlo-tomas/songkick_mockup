"use strict";

(() => {
    const div1 = $('#1');

    const divSetter = () => {
        div1.append('hello there');
        console.log('oh I dont think so');
    };

    divSetter();
})();

$.ajax({
    type:"GET",
    url:"https://app.ticketmaster.com/discovery/v2/events.json?size=1&apikey=wmPHZTG5yArUv0visdGIqiVGcUslaFgH",
    async:true,
    dataType: "json",
    success: function(json) {
        console.log(json);
        // Parse the response.
        // Do other things.
    },
    error: function(xhr, status, err) {
        // This time, we do not end up here!
    }
});