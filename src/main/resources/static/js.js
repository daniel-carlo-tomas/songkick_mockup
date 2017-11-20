"use strict";

(() => {
    const div1 = $('#1');

    const divSetter = () => {
        div1.append('hello there');
        console.log('oh I dont think so');
    };

    divSetter();
})();